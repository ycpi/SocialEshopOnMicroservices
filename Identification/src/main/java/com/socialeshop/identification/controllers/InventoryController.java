package com.socialeshop.identification.controllers;

import com.socialeshop.identification.Security.UserDetailsImpl;
import com.socialeshop.identification.models.Inventory;
import com.socialeshop.identification.payloads.InventoryResponse;
import com.socialeshop.identification.payloads.MessageResponse;
import com.socialeshop.identification.repositories.InventoryRepository;
import com.socialeshop.identification.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import com.socialeshop.identification.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class InventoryController {
    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("")
    public ResponseEntity<?> getAllInventory(){
        return ResponseEntity.ok(new InventoryResponse(inventoryRepository.findAll()));
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadInventory(@Valid @RequestBody Inventory inventory){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("upload userName:"+userName);
        Optional<User> user = userRepository.findByUsername(userName);
        if(user.isPresent()){
            inventory.setUser(user.get());
        } else {
            inventory.setUser(null);
        }

        inventoryRepository.saveAndFlush(inventory);
        return ResponseEntity.ok(new MessageResponse("upload success"));
    }

}
