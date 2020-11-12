//package com.socialeshop.microservices.core.inventory.services;
//
//import com.socialEshop.util.exceptions.InvalidInputException;
//import com.socialEshop.util.http.ServiceUtil;
//import com.socialeshop.api.core.inventory.Inventory;
//import com.socialeshop.api.core.inventory.InventoryService;
//import com.socialeshop.api.core.payloads.InventoryResponse;
//import com.socialeshop.api.core.payloads.MessageResponse;
//import com.socialeshop.microservices.core.inventory.persistence.InventoryEntity;
//import com.socialeshop.microservices.core.inventory.persistence.InventoryRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/category")
//public class InventoryController {
//    @Autowired
//    InventoryRepository inventoryRepository;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    InventoryMapper mapper;
//
//    // Only return the business users' items
//    @GetMapping("/business")
//    public ResponseEntity<?> getAllInventoryByUser(@Valid @RequestParam(value = "name") String userName){
//        //String userName = input.getInput();
//        System.out.println("current bussiness userName:"+userName);
//        List<InventoryEntity> inventoryList_db = inventoryRepository.findAll();
////        List<SingleInventory> inventoryList = new ArrayList<>();
////        for(Inventory inventory:inventoryList_db){
////            if(inventory.getUser().getUsername().endsWith(userName))
////                inventoryList.add(new SingleInventory(inventory));
////        }
//        return ResponseEntity.ok(new InventoryResponse(inventoryList));
//    }
//
//    // Only return items under the tag
//    @GetMapping("")
//    public ResponseEntity<?> getAllInventoryByTag(@Valid @RequestParam(value = "tag") String tag){
//        System.out.println("current tag:"+tag);
//        List<InventoryEntity> inventoryList_db = inventoryRepository.findByTag(tag);
//        List<Inventory> inventoryList = mapper.entityListToDtoList(inventoryList_db);
//        return ResponseEntity.ok(new InventoryResponse(inventoryList));
//    }
//
//    // Search for items
//    @GetMapping("search")
//    public ResponseEntity<?> getAllInventoryBySearch(@Valid @RequestParam(value = "search") String itemName){
//        System.out.println("ItemName for search:"+ itemName);
//        List<InventoryEntity> inventoryList_db = inventoryRepository.findByItemName(itemName);
//        List<Inventory> inventoryList = mapper.entityListToDtoList(inventoryList_db);
//        return ResponseEntity.ok(new InventoryResponse(inventoryList));
//
//    }
//
//    @PostMapping("/upload")
//    public ResponseEntity<?> uploadInventory(@Valid @RequestBody Inventory inventory){
//        System.out.println("Tag:"+inventory.getTag());
//        // TODO: use token to get name instead
//        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
//        System.out.println("SecurityContextHolder.getContext():"+SecurityContextHolder.getContext());
//        System.out.println("SecurityContextHolder.getContext().getAuthentication()"+ SecurityContextHolder.getContext().getAuthentication());
//        System.out.println("upload userName:"+userName);
//        Optional<User> user = userRepository.findByUsername(userName);
//        if(user.isPresent()){
//            inventory.setUser(user.get());
//        } else {
//            inventory.setUser(null);
//        }
//        inventoryRepository.saveAndFlush(inventory);
//        System.out.println("Inventory ID:"+inventory.getId());
//
//        Long returnId = inventory.getId();
//        //return ID to the front end
//        //return ResponseEntity.ok(new MessageResponse("post success"));
//        return ResponseEntity.ok(new UploadResponse(returnId));
//    }
//
//    /*
//Json Fomat
//                    id: orgid,
//                    itemName: itemname,
//                    cost: price,
//                    amount: amount,
//                    tag: tag,
//                    description: description
// */
//    @PostMapping("/edit")
//    public ResponseEntity<?> deleteInventory(@Valid @RequestBody EditRequest editRequest){
//        Long editID = editRequest.getId();
//        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
//        System.out.println("edit userName:"+userName+ "edit ID:"+ editID);
//        Optional<Inventory> inventory = inventoryRepository.findById(editID);
//
//        if(inventory.isPresent()){
//            String itemName = editRequest.getItemName();
//            Integer cost = editRequest.getCost();
//            Integer amount = editRequest.getAmount();
//            String tag = editRequest.getTag();
//            String description = editRequest.getDescription();
//            Inventory curInventory = inventory.get();
//            curInventory.setItemName(itemName);
//            curInventory.setAmount(amount);
//            curInventory.setTag(tag);
//            curInventory.setDescription(description);
//            inventoryRepository.saveAndFlush(curInventory);
//            System.out.println("Edit succesfully!");
//        }
//        else{
//            System.out.println("Inventory doesn't exist");
//        }
//        //inventoryRepository.saveAndFlush(inventory);
//        return ResponseEntity.ok(new MessageResponse("Edit success"));
//    }
//
//
//    @PostMapping("/delete")
//    public ResponseEntity<?> editInventory(@Valid @RequestBody DeleteRequest deleteRequest){
//        System.out.println("ID: " + deleteRequest.getId());
//        Long deleteID = deleteRequest.getId();
//
//
//        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
//        System.out.println("delete userName:"+userName+ "delete ID:"+deleteID);
//        Optional<Inventory> inventory = inventoryRepository.findById(deleteID);
//
//        if(inventory.isPresent()){
//            Inventory curInventory = inventory.get();
//            inventoryRepository.delete(curInventory);
//            System.out.println("Delete succesfully!");
//        }
//        else{
//            System.out.println("Inventory doesn't exist");
//        }
//        //inventoryRepository.saveAndFlush(inventory);
//        return ResponseEntity.ok(new MessageResponse("delete success"));
//    }
//
//}
