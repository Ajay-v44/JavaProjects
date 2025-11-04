package com.auth.authentications.Controllers;


import com.auth.authentications.Models.DTO.TokenUserResponse;
import com.auth.authentications.annotations.CurrentUser;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/add")
    public ResponseEntity<?> addToCart(
          @Parameter(hidden = true) @CurrentUser TokenUserResponse user,
            @RequestParam Long itemId) {

       try{
           // You now have `user` auto-injected from Bearer token!
           return ResponseEntity.ok("Added item " + itemId + " for user "+user.username());
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }
}
