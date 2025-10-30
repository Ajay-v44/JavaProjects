package com.ecomm.backend.Services;

import com.ecomm.backend.Models.Product;
import com.ecomm.backend.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductServices {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(int id) {
        return productRepository.findById(id).orElseThrow(()->new RuntimeException("Not Found"));
    }

    public String addProduct(Product product, MultipartFile image) throws IOException {
        product.setImageName(image.getOriginalFilename());
        product.setImageType(image.getContentType());
        product.setImageDta(image.getBytes());
        productRepository.save(product);
        return "{message:inserted successfully}";
    }

    public String updateProduct(Product product, MultipartFile image) throws IOException {
        product.setImageName(image.getOriginalFilename());
        product.setImageType(image.getContentType());
        product.setImageDta(image.getBytes());
        productRepository.save(product);
        return "{message:updated successfully}";
    }

    public String deleteProduct(Integer id) {
        productRepository.deleteById(id);
        return "{message:deleted successfully}";
    }

    public List<Product> serachProduct(String query) {
      return   productRepository.findByNameContainingIgnoreCase(query);
    }
}
