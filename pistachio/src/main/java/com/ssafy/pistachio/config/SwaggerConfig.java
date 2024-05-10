package com.ssafy.pistachio.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Pistachio Project")
                        .description("피스타치오 프로젝트입니다.")
                        .version("v0.0.1")
                        .license(new License().name("Pistachio").url("https://www.ssafy.com")));
    }
}
