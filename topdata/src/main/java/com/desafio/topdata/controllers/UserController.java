package com.desafio.topdata.controllers;

import com.desafio.topdata.dtos.LoginDto;
import com.desafio.topdata.models.User;
import com.desafio.topdata.repositories.UserRepository;
import com.desafio.topdata.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUser(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        String email = loginDto.email();
        String password = loginDto.password();

        User user = userRepository.findByEmailAndPassword(email, password);

        if (user != null) {
            return ResponseEntity.ok().body("Login bem-sucedido!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erro ao efetar login");
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> udateUser(@PathVariable Integer id,@RequestBody User userDetails){
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id, userDetails));
    }

    @DeleteMapping("/users/{id}")
    public void deleteUSer(@PathVariable Integer id){
        userService.deleteUser(id);
    }
}
