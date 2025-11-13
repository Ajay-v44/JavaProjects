package com.ecomm.backend.Controllers;

import com.ecomm.backend.Models.Product;
import com.ecomm.backend.Services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/product")
@CrossOrigin
public class ProductController {

    @Autowired
    ProductServices productServices;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        try {
            return new ResponseEntity<>(productServices.getAllProducts(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {
        try {
            return new ResponseEntity<>(productServices.getProduct(id), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> addProduct(@RequestPart Product product, @RequestPart MultipartFile image) {
        try {
            return new ResponseEntity<>(productServices.addProduct(product,image), HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping
    public ResponseEntity<String> updateProduct(@RequestPart Product product, @RequestPart MultipartFile image) {
        try {
            return new ResponseEntity<>(productServices.updateProduct(product,image), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(productServices.deleteProduct(id), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/generate_description")
    public ResponseEntity<String> generateDescription(@RequestParam String name,@RequestParam String category){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(productServices.generateDescription(name,category));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/generate_image")
    public ResponseEntity<?> generateImage(@RequestParam String name,@RequestParam String category,@RequestParam String description){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(productServices.generateImage(name,category,description));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getProductImage(@PathVariable int id){
        try{
            Product product=productServices.getProduct(id);
            return new ResponseEntity<>(product.getImageDta(),HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/search/{query}")
    public ResponseEntity<List<Product>> searchProduct(@PathVariable String query){
        try {
            return new ResponseEntity<>(productServices.serachProduct(query),HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
