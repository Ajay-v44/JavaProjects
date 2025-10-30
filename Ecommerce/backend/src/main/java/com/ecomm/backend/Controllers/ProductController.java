package com.ecomm.backend.Controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductController {

    @GetMapping("/products")
    public String getProducts(){
        try {
            return "OHM";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
