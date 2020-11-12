package com.socialeshop.auth.controllers;

import com.socialeshop.api.core.payloads.JwtResponse;
import com.socialeshop.api.core.payloads.LoginRequest;
import com.socialeshop.api.core.payloads.MessageResponse;
import com.socialeshop.api.core.payloads.SignupRequest;
import com.socialeshop.api.core.payloads.user.EditPassword;
import com.socialeshop.api.core.payloads.user.EditProfileRequest;
import com.socialeshop.api.core.payloads.user.VerifyUserRequest;
import com.socialeshop.api.model.ERole;
import com.socialeshop.api.model.Role;
import com.socialeshop.api.model.User;
import com.socialeshop.api.repositories.RoleRepository;
import com.socialeshop.api.repositories.UserRepository;

import com.socialeshop.auth.Security.UserDetailsImpl;
import com.socialeshop.auth.Security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        System.out.println("username:"+signUpRequest.getUsername());
        User user = new User(signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getAddress(),
                signUpRequest.getEmail()
        );

//        Set<Role> roles = new HashSet<>();
//        Role userRole = roleRepository.findByName(ERole.normal)
//                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//        roles.add(userRole);
//        user.setRoles(roles);
        user.setRole(ERole.normal);
        userRepository.saveAndFlush(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/signup/business")
    public ResponseEntity<?> registerBusinessUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        System.out.println("username:"+signUpRequest.getUsername());
        User user = new User(signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getAddress(),
                signUpRequest.getEmail()
        );

//        Set<Role> roles = new HashSet<>();
//        Role userRole = roleRepository.findByName(ERole.business)
//                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//        roles.add(userRole);
//        user.setRoles(roles);
        user.setRole(ERole.business);
        userRepository.saveAndFlush(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        System.out.println(loginRequest.getUsername()+","+loginRequest.getPassword());
        Authentication authentication = null;
        try{
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse("Login failed!"));
        }


        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getAddress(),
                roles));
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyUser(@Valid @RequestBody VerifyUserRequest verifyUserRequest){
        String username = verifyUserRequest.getUsername();
        String password = verifyUserRequest.getPassword();
        String passwordToken = encoder.encode(password);
        // get password token
        Authentication authentication = null;
        try { // fixed: CORS bug
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse("Login failed!"));
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        if(userRepository.existsByUsername(username)){
            Optional<User> user = userRepository.findByUsername(username);
            if(user.isPresent()){
                if(user.get().getPassword().endsWith(userDetails.getPassword())){
                    return ResponseEntity.ok(new MessageResponse("Success!"));
                }
                else{
                    return ResponseEntity.badRequest().body(new MessageResponse("Error: Cann't find the user!"));
                }
            }
        }
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: The password is wrong!"));
    }
    @PostMapping("/edit")
    public ResponseEntity<?> editProfile(@Valid @RequestBody EditProfileRequest editProfileRequest){
        String username = editProfileRequest.getUsername();
        String email = editProfileRequest.getEmail();
        String address = editProfileRequest.getAddress();

        Optional<User> user = userRepository.findByUsername(username);

        if(user.isPresent()){
            user.get().setAddress(address);
            user.get().setEmail(email);
            userRepository.saveAndFlush(user.get());
            return ResponseEntity.ok(new MessageResponse("Edit Profile Success!"));
        }
        else{
            System.out.println("Cannot find the user!");
        }
        return ResponseEntity.badRequest().body("Error: Cannot find the user!");
    }

    @PostMapping("/edit/password")
    public ResponseEntity<?> editPassword(@Valid @RequestBody EditPassword editPassword){
        String username = editPassword.getUsername();
        String password = editPassword.getPassword();

        Optional<User> user = userRepository.findByUsername(username);

        if(user.isPresent()){

            // get password token
            String passwordToken = encoder.encode(password);
            user.get().setPassword(passwordToken);
            userRepository.saveAndFlush(user.get());
            return ResponseEntity.ok(new MessageResponse("Edit Password Success!"));

        }
        return ResponseEntity.badRequest().body("Error: Edit password failed!");
    }

}
