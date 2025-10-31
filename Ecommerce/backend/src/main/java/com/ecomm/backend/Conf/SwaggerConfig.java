package com.ecomm.backend.Conf;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Ecomm API")
                        .version("v1.0")
                        .description("API documentation for Ecomm backend services")
                        .contact(new Contact()
                                .name("Ajay")
                                .email("ajay@example.com")
                                .url("https://github.com/Ajay-v44/JavaProjects")));
    }
}