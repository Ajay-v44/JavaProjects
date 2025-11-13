package com.ecomm.backend.Services;

import com.ecomm.backend.Models.Product;
import com.ecomm.backend.Repositories.ProductRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ProductServices {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ChatClient chatClient;
    @Autowired
    private AiImageGenService aiImageGenService;
    @Autowired
    private VectorStore vectorStore;
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(int id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
    }

    public String addProduct(Product product, MultipartFile image) throws IOException {
        product.setImageName(image.getOriginalFilename());
        product.setImageType(image.getContentType());
        product.setImageDta(image.getBytes());
        Product savedProduct = productRepository.save(product);

        String content = String.format("""
                
                Product Name: %s
                Description: %s
                Brand: %s
                Category: %s
                Price: %.2f
                Release Date: %s
                Available: %s
                Stock: %s
                """,
                savedProduct.getName(),
                savedProduct.getDescription(),
                savedProduct.getBrand(),
                savedProduct.getCategory(),
                savedProduct.getPrice(),
                savedProduct.getReleaseDate(),
                savedProduct.isProductAvailable(),
                savedProduct.getStockQuantity()
        );

        Document document = new Document(
                UUID.randomUUID().toString(),
                content,
                Map.of("productId", String.valueOf(savedProduct.getId()))
        );

        vectorStore.add(List.of(document));

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
        return productRepository.findByNameContainingIgnoreCase(query);
    }


    public String generateDescription(String name, String category) {
        String description = String.format("""
                 Write a concise and professional product description for an e-commerce listing.
                
                                Product Name: %s
                                Category: %s
                
                                Keep it simple, engaging, and highlight its primary features or benefits.
                                Avoid technical jargon and keep it customer-friendly.
                                Limit the description to 250 characters maximum.
                """,name,category);
        return chatClient.prompt(description)
                .call()
                .chatResponse()
                .getResult()
                .getOutput()
                .getText();

    }

    public byte[] generateImage(String name, String category, String description) {
        String imagePrompt=String.format("""
                          Generate a highly realistic, professional-grade e-commerce product image.
                
                     Product Details:
                     - Category: %s
                     - Name: '%s'
                     - Description: %s
                
                     Requirements:
                     - Use a clean, minimalistic, white or very light grey background.
                     - Ensure the product is well-lit with soft, natural-looking lighting.
                     - Add realistic shadows and soft reflections to ground the product naturally.
                     - No humans, brand logos, watermarks, or text overlays should be visible.
                     - Showcase the product from its most flattering angle that highlights key features.
                     - Ensure the product occupies a prominent position in the frame, centered or slightly off-centered.
                     - Maintain a high resolution and sharpness, ensuring all textures, colors, and details are clear.
                     - Follow the typical visual style of top e-commerce websites like Amazon, Flipkart, or Shopify.
                     - Make the product appear life-like and professionally photographed in a studio setup.
                     - The final image should look immediately ready for use on an e-commerce website without further editing.
                     """, category, name, description);
        return aiImageGenService.generateImage(imagePrompt);
    }
}
