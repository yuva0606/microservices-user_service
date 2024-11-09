package com.yuva.user.controller;

import com.yuva.user.dto.LoginRequest;
import com.yuva.user.dto.LoginResponse;
import com.yuva.user.dto.RegisterRequest;
import com.yuva.user.dto.RegisterResponse;
import com.yuva.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ResourceBundle;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> loginHandler(@RequestBody LoginRequest loginRequest){
        return userService.login(loginRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerHandler(@RequestBody RegisterRequest registerRequest){
        return userService.registerUser(registerRequest);
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateHandler(@RequestParam String email, HttpServletRequest httpServletRequest){
        userService.updateEmail(email, httpServletRequest);
        return ResponseEntity.status(HttpStatus.OK).body("Email updated successfully");
    }
}
