package com.auth.authentications.Controllers;

import com.auth.authentications.Models.AuthUser;
import com.auth.authentications.Models.DTO.TokenUserResponse;
import com.auth.authentications.Models.DTO.UserRegister;
import com.auth.authentications.Repositories.AuthUserRepository;
import com.auth.authentications.Services.AuthUserServices;
import com.auth.authentications.annotations.CurrentUser;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {
    @Autowired
    AuthUserServices authUserServices;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegister userRegister) {
        return new ResponseEntity<>(authUserServices.save(userRegister), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserRegister userRegister) {
        return authUserServices.login(userRegister);
    }

    @GetMapping("/getUserDetails/{token}")
    public ResponseEntity<TokenUserResponse> getUserDetails(@RequestParam String token) {
        return new ResponseEntity<>(authUserServices.getTokenUser(token), HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/protected")
    public ResponseEntity<?> checkUserAuthenticity(@CurrentUser TokenUserResponse tokenUserResponse,@RequestParam Long itemId) {
        return ResponseEntity.ok("You Are On It");
    }

}
