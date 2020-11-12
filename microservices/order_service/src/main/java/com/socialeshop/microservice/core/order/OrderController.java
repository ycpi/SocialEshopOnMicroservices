package com.socialeshop.microservice.core.order;

import com.socialeshop.microservices.api.core.DTO.SingleCart;
import com.socialeshop.microservices.api.core.payloads.order.GetCartResponse;
import com.socialeshop.microservices.api.core.payloads.order.PostOrderRequest;
import com.socialeshop.microservices.api.core.payloads.order.PostOrderResponse;
import com.socialeshop.microservices.api.model.Cart;
import com.socialeshop.microservices.api.model.ERole;
import com.socialeshop.microservices.api.repositories.CartRepository;
import com.socialeshop.microservices.api.repositories.InventoryRepository;
import com.socialeshop.microservices.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        // check if business or normal user
        ERole curUserRole = ERole.normal;
        if(userRepository.findByUsername(username).isPresent()){
            curUserRole = userRepository.findByUsername(username).get().getRole();
        }
        // if business user
        if(curUserRole.equals(ERole.business)){
            for(Cart cart: cartList_db){
                if(cart.isOrder() && cart.getInventory().getUser().getUsername().equals(username))
                    cartList.add(new SingleCart(cart));
            }
        }
        // if nornal user
        for(Cart cart: cartList_db){
            if(cart.isOrder() && cart.getUser().getUsername().endsWith(username))
                cartList.add(new SingleCart(cart));
        }
        return ResponseEntity.ok(new GetCartResponse(cartList));
    }
    @PostMapping("/add")
    public ResponseEntity<?> postOrder(@Valid @RequestBody PostOrderRequest postOrderRequest){
        //String username = postOrderRequest.getUsername();
        //long orderL = postOrderRequest.getOrders();
        List<String> orders = postOrderRequest.getOrders();
        String username = orders.get(0);
        orders.remove(0);
        System.out.println("Place Order: Username: "+ username);
        // return a list of int to indicate the statue of orders
        List<Long> orderStatueList = new LinkedList<Long>();

        // update the status in cart table
        for (String orderIdString : orders){
            Long orderId = Long.parseLong(orderIdString);
            Optional<Cart> cartOptional = cartRepository.findById(orderId);
            if(cartOptional.isPresent()){
                int amountInInventory = cartOptional.get().getInventory().getAmount();
                int amountInOrder = cartOptional.get().getAmount();
                System.out.println("amountInOrder: " + amountInOrder + "amountInInventory: " + amountInInventory);
                if(amountInInventory < amountInOrder){
                    // out of stock
                    orderStatueList.add((long)-1);
                }
                else{
                    orderStatueList.add(orderId);
                }
                cartOptional.get().setOrder(true);
                cartRepository.saveAndFlush(cartOptional.get());
            }
            else{
                System.out.println("Can't place the order with id " + orderId + " since doesn't exist");
            }
        }
        return ResponseEntity.ok(new PostOrderResponse(orderStatueList));

    }




}
