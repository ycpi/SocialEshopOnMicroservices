package com.socialeshop.identification.controllers;

import com.socialeshop.identification.Security.UserDetailsImpl;
import com.socialeshop.identification.models.Inventory;
import com.socialeshop.identification.models.Cart;
import com.socialeshop.identification.payloads.*;
import com.socialeshop.identification.repositories.InventoryRepository;
import com.socialeshop.identification.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import com.socialeshop.identification.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class InventoryController {
    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    UserRepository userRepository;


    // Only return the business users' items
    @GetMapping("/business")
    public ResponseEntity<?> getAllInventoryByUser(@Valid @RequestParam(value = "name") String userName){
        //String userName = input.getInput();
        System.out.println("current bussiness userName:"+userName);
        List<Inventory> inventoryList_db = inventoryRepository.findAll();
        List<SingleInventory> inventoryList = new ArrayList<>();
        for(Inventory inventory:inventoryList_db){
            if(inventory.getUser().getUsername().endsWith(userName))
                inventoryList.add(new SingleInventory(inventory));
        }
        return ResponseEntity.ok(new InventoryResponse(inventoryList));
    }


    // Only return items under the tag
    @GetMapping("")
    public ResponseEntity<?> getAllInventoryByTag(@Valid @RequestParam(value = "tag") String tag){
        System.out.println("current tag:"+tag);
        List<Inventory> inventoryList_db = inventoryRepository.findAll();
        List<SingleInventory> inventoryList = new ArrayList<>();
        for(Inventory inventory:inventoryList_db){
            if(inventory.getTag().endsWith(tag))
                inventoryList.add(new SingleInventory(inventory));
        }
        return ResponseEntity.ok(new InventoryResponse(inventoryList));
    }

    // Search for items
    @GetMapping("search")
    public ResponseEntity<?> getAllInventoryBySearch(@Valid @RequestParam(value = "search") String itemName){
        System.out.println("ItemName for search:"+ itemName);
        List<Inventory> inventoryList_db = inventoryRepository.findAll();
        List<SingleInventory> inventoryList = new ArrayList<>();
        for(Inventory inventory:inventoryList_db){
            if(inventory.getItemName().endsWith(itemName))
                inventoryList.add(new SingleInventory(inventory));
        }
        return ResponseEntity.ok(new InventoryResponse(inventoryList));

    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadInventory(@Valid @RequestBody Inventory inventory){
        System.out.println("Tag:"+inventory.getTag());
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("SecurityContextHolder.getContext():"+SecurityContextHolder.getContext());
        System.out.println("SecurityContextHolder.getContext().getAuthentication()"+ SecurityContextHolder.getContext().getAuthentication());
        System.out.println("upload userName:"+userName);
        Optional<User> user = userRepository.findByUsername(userName);
        if(user.isPresent()){
            inventory.setUser(user.get());
        } else {
            inventory.setUser(null);
        }
        inventoryRepository.saveAndFlush(inventory);
        System.out.println("Inventory ID:"+inventory.getId());

        Long returnId = inventory.getId();
        //return ID to the front end
        //return ResponseEntity.ok(new MessageResponse("post success"));
        return ResponseEntity.ok(new UploadResponse(returnId));
    }

    /*
Json Fomat
                    id: orgid,
                    itemName: itemname,
                    cost: price,
                    amount: amount,
                    tag: tag,
                    description: description
 */
    @PostMapping("/edit")
    public ResponseEntity<?> deleteInventory(@Valid @RequestBody EditRequest editRequest){
        Long editID = editRequest.getId();
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("edit userName:"+userName+ "edit ID:"+ editID);
        Optional<Inventory> inventory = inventoryRepository.findById(editID);

        if(inventory.isPresent()){
            String itemName = editRequest.getItemName();
            Integer cost = editRequest.getCost();
            Integer amount = editRequest.getAmount();
            String tag = editRequest.getTag();
            String description = editRequest.getDescription();
            Inventory curInventory = inventory.get();
            curInventory.setItemName(itemName);
            curInventory.setAmount(amount);
            curInventory.setTag(tag);
            curInventory.setDescription(description);
            System.out.println("Edit succesfully!");
        }
        else{
            System.out.println("Inventory doesn't exist");
        }
        //inventoryRepository.saveAndFlush(inventory);
        return ResponseEntity.ok(new MessageResponse("Edit success"));
    }


    @PostMapping("/delete")
    public ResponseEntity<?> editInventory(@Valid @RequestBody DeleteRequest deleteRequest){
        System.out.println("ID: " + deleteRequest.getId());
        Long deleteID = deleteRequest.getId();


        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("delete userName:"+userName+ "delete ID:"+deleteID);
        Optional<Inventory> inventory = inventoryRepository.findById(deleteID);

        if(inventory.isPresent()){
            Inventory curInventory = inventory.get();
            inventoryRepository.delete(curInventory);
            System.out.println("Delete succesfully!");
        }
        else{
            System.out.println("Inventory doesn't exist");
        }
        //inventoryRepository.saveAndFlush(inventory);
        return ResponseEntity.ok(new MessageResponse("delete success"));
    }



}
