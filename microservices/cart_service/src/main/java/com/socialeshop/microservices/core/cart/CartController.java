package com.socialeshop.microservices.core.cart;


import com.socialeshop.microservices.api.core.DTO.SingleCart;
import com.socialeshop.microservices.api.core.payloads.MessageResponse;
import com.socialeshop.microservices.api.core.payloads.cart.*;
import com.socialeshop.microservices.api.core.payloads.order.GetCartResponse;
import com.socialeshop.microservices.api.model.Cart;
import com.socialeshop.microservices.api.model.Inventory;
import com.socialeshop.microservices.api.model.User;
import com.socialeshop.microservices.api.repositories.CartRepository;
import com.socialeshop.microservices.api.repositories.InventoryRepository;
import com.socialeshop.microservices.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    // Get method: input: username   return : list of cart
    @GetMapping("")
    public ResponseEntity<?> getAllItemsInCart(@Valid @RequestParam(value = "username") String username) {
        System.out.println("Get cart: current username: " + username);
        List<Cart> cartList_db = cartRepository.findAll();
        List<SingleCart> cartList = new ArrayList<>();
        for (Cart cart : cartList_db) {
            // check isOrder flag
            if (!cart.isOrder()) {
                if (cart.getUser().getUsername().endsWith(username))
                    cartList.add(new SingleCart(cart));
            }
        }
        return ResponseEntity.ok(new GetCartResponse(cartList));
    }

/*    @PostMapping("/user")
    public ResponseEntity<?> getCart(@Valid @RequestBody GetUsernameRequest getUsernameRequest) {
        System.out.println("Get cart: current username: "+ getUsernameRequest.getUsername());
        List<Cart> cartList_db = cartRepository.findAll();
        List<Cart> cartList = new ArrayList<Cart>();
        for(Cart cart: cartList_db){
            if(cart.getUser().getUsername().endsWith(getUsernameRequest.getUsername()))
                cartList.add(new Cart(cart));
        }
        return ResponseEntity.ok(new GetCartResponse(cartList));
    }*/

    @PostMapping("/add")
    public ResponseEntity<?> addCart(@Valid @RequestBody AddCartRequest addCartRequest) {
        //String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        String userName = addCartRequest.getUsername();
        Long itemId = addCartRequest.getItemID();
        System.out.println("add Cart userName:" + userName + "item Name: " + addCartRequest.getItem() + "item id: " + itemId);
        Optional<User> user = userRepository.findByUsername(userName);
        // get the inventory via its id
        Optional<Inventory> inventory = inventoryRepository.findById(itemId);
        Cart cart = new Cart();
        // store to cart table
        cart.setAmount(addCartRequest.getAmount());
        if (user.isPresent()) {
            cart.setUser(user.get());
            if (inventory.isPresent()) {
                System.out.println("add Cart - item name:" + inventory.get().getItemName());
                cart.setInventory(inventory.get());
            } else {
                System.out.println("the item with id " + itemId + " doesnt exsit.");
                cart.setInventory(new Inventory());
            }
        } else {
            cart.setUser(null);
        }
        //return price: response.data.cost, item: response.data.item, num: response.data.amount, id: response.data.id  to the front end
        //return ResponseEntity.ok(new MessageResponse("post success"));
        int cost = 0;

        if (inventory.isPresent()) {
            cost = inventory.get().getCost() * addCartRequest.getAmount();
            // store to cart
            cart.setCost(cost);
        }
        cartRepository.saveAndFlush(cart);
        Long cartId = cartRepository.count();
        System.out.println("Costs:" + cost + " itemName:" + addCartRequest.getItem() + " amount:" + addCartRequest.getAmount() + " id:" + cartId);
        return ResponseEntity.ok(new AddCartResponse(cost, addCartRequest.getItem(), addCartRequest.getAmount(), cartId));
    }

    // remove items from cart
    @PostMapping("/delete")
    public ResponseEntity<?> removeItems(@Valid @RequestBody RemoveItemsRequest removeItemsRequest) {
        List<Cart> orders = removeItemsRequest.getOrders();
        // orders will not be empty for sure
        for (Cart item : orders) {
            long deleteID = item.getId();
            Optional<Cart> cartOptional = cartRepository.findById(deleteID);
            if (cartOptional.isPresent()) {
                Cart curCart = cartOptional.get();
                cartRepository.delete(curCart);
                System.out.println("Delete Cart with ID: " + deleteID + "succesfully!");
            } else {
                System.out.println("Delete Cart with ID: " + deleteID + "failed!");
            }
        }
        return ResponseEntity.ok(new MessageResponse("Remove success"));
    }


    // edit items from cart
    @PostMapping("/edit")
    public ResponseEntity<?> editItems(@Valid @RequestBody EditItemsRequest editItemsRequest) {
        Long id = editItemsRequest.getId();
        int newAmount = editItemsRequest.getNewAmount();

        Optional<Cart> cartOptional = cartRepository.findById(id);

        if (cartOptional.isPresent()) {
            Cart curCart = cartOptional.get();
            curCart.setAmount(newAmount);
            int newCost = curCart.getInventory().getCost() * newAmount;
            curCart.setCost(newCost);
            cartRepository.saveAndFlush(curCart);
            return ResponseEntity.ok(new EditCartResponse(newCost, newAmount));
        }

        return ResponseEntity.badRequest().body("Edit Cart Failed!");

    }


}
