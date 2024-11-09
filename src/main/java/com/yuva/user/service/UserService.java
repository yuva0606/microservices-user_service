package com.yuva.user.service;

import com.yuva.user.dto.LoginRequest;
import com.yuva.user.dto.LoginResponse;
import com.yuva.user.dto.RegisterRequest;
import com.yuva.user.dto.RegisterResponse;
import com.yuva.user.model.Role;
import com.yuva.user.model.User;
import com.yuva.user.repository.UserRepo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserRepo userRepo;
    private final PasswordEncoder bcrypt;

    public ResponseEntity<?> login(LoginRequest lreq) {
        Authentication authentication = authManager
                .authenticate(new UsernamePasswordAuthenticationToken(lreq.username(), lreq.password()));
        String token = null;
        if (authentication.isAuthenticated()) {
            User user = (User) authentication.getPrincipal();
            String role = user.getRole().name();
            token = jwtService.generateToken(lreq.username(),role);
        }else{
            return ResponseEntity.badRequest().body("Invalid credentials");
        }
        return ResponseEntity.ok(new LoginResponse(lreq.username(),token));
    }

    public ResponseEntity<?> registerUser(RegisterRequest regReq) {
        User newUser = new User(null, regReq.username(), regReq.email(), bcrypt.encode(regReq.password()), Role.USER);
        User savedUser = userRepo.save(newUser);

        return ResponseEntity.status(HttpStatus.OK).body(new RegisterResponse(savedUser.getUserId(), savedUser.getUsername(), savedUser.getEmail(),
                savedUser.getRole()));
    }

    public void updateEmail(String email, HttpServletRequest httpServletRequest) {
        String authHeader = httpServletRequest.getHeader("Authorization");
        String token = authHeader.substring(7);
        String userName = jwtService.extractUserName(token);

        User user = userRepo.findByUsername(userName).get();
        user.setEmail(email);
        userRepo.save(user);
    }

    public String updateRole(HttpServletRequest httpServletRequest) {
        String authHeader = httpServletRequest.getHeader("Authorization");
        String token = authHeader.substring(7);
        String userName = jwtService.extractUserName(token);

        User user = userRepo.findByUsername(userName).get();
        user.setRole(Role.ADMIN);
        userRepo.save(user);
        return "Role updated successfully";
    }
}
