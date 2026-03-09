package com.addressbookapp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI addressBookOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Address Book API")
                        .description("REST API documentation for Address Book Application")
                        .version("1.0"));
    }
}