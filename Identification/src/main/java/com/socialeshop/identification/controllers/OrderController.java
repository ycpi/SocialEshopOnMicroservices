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
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    InventoryRepository inventoryRepository;

    // Get method: input: username   return : list of cart
    @GetMapping("")
    public ResponseEntity<?> getAllItemsInCart(@Valid @RequestParam(value = "username") String username){
        System.out.println("Get cart: current username: "+ username);
        List<Cart> cartList_db = cartRepository.findAll();
        List<SingleCart> cartList = new ArrayList<>();
        for(Cart cart: cartList_db){
            if(cart.isOrder() && cart.getUser().getUsername().endsWith(username))
                cartList.add(new SingleCart(cart));
        }
        return ResponseEntity.ok(new GetCartResponse(cartList));
    }
    @PostMapping("/add")
    public ResponseEntity<?> postOrder(@Valid @RequestBody PostOrderRequest postOrderRequest){
        String username = postOrderRequest.getUsername();
        List<Cart> orderList = postOrderRequest.getOrderList();
        System.out.println("Place Order: Username: "+ username);
        // return a list of int to indicate the statue of orders
        List<Long> orderStatueList = new LinkedList<Long>();

        // update the status in cart table
        for (Cart order : orderList){
            int amountInOrder = order.getAmount();
            Optional<Cart> cartOptional = cartRepository.findById(order.getId());
            if(cartOptional.isPresent()){
                int amountInInventory = cartOptional.get().getInventory().getAmount();
                System.out.println("amountInOrder: " + amountInOrder+ "amountInInventory: " + amountInInventory);
                if(amountInInventory < amountInOrder){
                    // out of stock
                    orderStatueList.add((long)-1);
                }
                else{
                    orderStatueList.add(order.getId());
                }
                Cart curCart = cartOptional.get();
                curCart.setOrder(true);
            }
            else{
                System.out.println("Can't place the order with id "+ order.getId()+" since doesn't exist");
            }
        }
        return ResponseEntity.ok(new PostOrderResponse(orderStatueList));

    }




}
