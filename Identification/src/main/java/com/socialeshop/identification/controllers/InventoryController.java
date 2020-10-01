package com.socialeshop.identification.controllers;

import com.socialeshop.identification.payloads.InventoryResponse;
import com.socialeshop.identification.payloads.MessageResponse;
import com.socialeshop.identification.repositories.InventoryRepository;
import com.socialeshop.identification.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class InventoryController {
    @Autowired
    InventoryRepository inventoryRepository;

    @GetMapping("")
    public ResponseEntity<?> getAllInventory(){
        return ResponseEntity.ok(new InventoryResponse(inventoryRepository.findAll()));
    }

}
