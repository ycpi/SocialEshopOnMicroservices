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

/*    // Get method: input: username   return : list of cart
    @GetMapping("")
    public ResponseEntity<?> getAllItemsInCart(@Valid @RequestParam(value = "username") String username){
        System.out.println("Get cart: current username: "+ username);
        List<Cart> cartList_db = cartRepository.findAll();
        List<Cart> cartList = new ArrayList<>();
        for(Cart cart: cartList_db){
            if(cart.getUser().getUsername().endsWith(username))
                cartList.add(new Cart(cart));
        }
        return ResponseEntity.ok(new GetCartResponse(cartList));
    }*/

    @PostMapping("/user")
    public ResponseEntity<?> getCart(@Valid @RequestBody GetUsernameRequest getUsernameRequest) {
        System.out.println("Get cart: current username: "+ getUsernameRequest.getUsername());
        List<Cart> cartList_db = cartRepository.findAll();
        List<Cart> cartList = new ArrayList<Cart>();
        for(Cart cart: cartList_db){
            if(cart.getUser().getUsername().endsWith(getUsernameRequest.getUsername()))
                cartList.add(new Cart(cart));
        }
        return ResponseEntity.ok(new GetCartResponse(cartList));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCart(@Valid @RequestBody AddCartRequest addCartRequest){
        //String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        String userName = addCartRequest.getUsername();
        Long itemId = addCartRequest.getItemID();
        System.out.println("add Cart userName:"+userName + "item Name: " + addCartRequest.getItem()+ "item id: " + itemId);
        Optional<User> user = userRepository.findByUsername(userName);
        // get the inventory via its id
        Optional<Inventory> inventory = inventoryRepository.findById(itemId);
        Cart cart = new Cart();
        if(user.isPresent()){
            cart.setUser(user.get());
            if(inventory.isPresent()){
                System.out.println("add Cart - item name:"+inventory.get().getItemName());
                cart.setInventory(inventory.get());
            }
            else{
                System.out.println("the item with id "+itemId+" doesnt exsit.");
                cart.setInventory(new Inventory());
            }
        } else {
            cart.setUser(null);
        }
        cartRepository.saveAndFlush(cart);
        Long cartId = cart.getId();
        //return price: response.data.cost, item: response.data.item, num: response.data.amount, id: response.data.id  to the front end
        //return ResponseEntity.ok(new MessageResponse("post success"));
        int cost = 0;

        if(inventory.isPresent()){
            cost = inventory.get().getCost() * addCartRequest.getAmount();
        }
        System.out.println("Costs:"+cost +" itemName:" + addCartRequest.getItem()+" amount:"+ addCartRequest.getAmount() +" id:"+itemId);
        return ResponseEntity.ok(new AddCartResponse(cost, addCartRequest.getItem(),addCartRequest.getAmount(),itemId));
    }

    // remove items from cart
    @PostMapping("/delete")
    public ResponseEntity<?> removeItems(@Valid @RequestBody RemoveItemsRequest removeItemsRequest){
        List<Cart> orders = removeItemsRequest.getOrders();
        // orders will not be empty for sure
        for(Cart item : orders){
            System.out.println("To be remove - item id: "+ item.getId());
            cartRepository.delete(item);
        }
        return ResponseEntity.ok(new MessageResponse("Remove success"));
    }


}
