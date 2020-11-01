package com.socialeshop.identification.controllers;

import com.socialeshop.identification.Security.UserDetailsImpl;
import com.socialeshop.identification.models.Inventory;
import com.socialeshop.identification.models.Cart;
import com.socialeshop.identification.payloads.*;
import com.socialeshop.identification.repositories.CartRepository;
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
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    InventoryRepository inventoryRepository;


    @PostMapping("/add")
    public ResponseEntity<?> addCart(@Valid @RequestBody AddCartRequest addCartRequest){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("add Cart userName:"+userName);
        Optional<User> user = userRepository.findByUsername(userName);
        // get the inventory via its id
        // Optional<Inventory> inventory = inventoryRepository.findById(addCartRequest.getID());
        Optional<Inventory> inventory = inventoryRepository.findById((long)0);
        // get the inventory via its name

        Cart cart = new Cart();
        if(user.isPresent()){
            cart.setUser(user.get());
            if(inventory.isPresent()){
                cart.setInventory(inventory.get());
            }
            else{
                cart.setInventory(new Inventory());
            }
        } else {
            cart.setUser(null);
        }
        //cartRepository.saveAndFlush(cart);
        System.out.println("Cart ID:"+cart.getId());

        Long returnId = cart.getId();
        //return ID to the front end
        return ResponseEntity.ok(new MessageResponse("post success"));
        //return ResponseEntity.ok(new UploadResponse(returnId));
    }
}
