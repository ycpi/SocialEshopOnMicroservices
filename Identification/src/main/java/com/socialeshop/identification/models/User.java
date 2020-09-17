//package com.socialeshop.identification.models;
//
//import org.hibernate.annotations.GenericGenerator;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import javax.persistence.*;
//
////@Entity
////@Table(name = "User")
//public class User implements UserDetails {
//    @Id
//    @GeneratedValue(generator = "increment")
//    @GenericGenerator(name = "increment", strategy = "increment")
//    @Column(name = "ID", unique = true, nullable = false)
//    private int id;
//
//    @Column(name = "username", unique = true, nullable = false)
//    private String username;
//
//    @Column(nullable = false)
//    private String password;
//
//    @Column(nullable = false)
//    private String address;
//
//    @Column(nullable = false)
//    private String email;
//
//    public User(int id, String username, String password, String address, String email) {
//        this.id = id;
//        this.username = username;
//        this.password = password;
//        this.address = address;
//        this.email = email;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//}
