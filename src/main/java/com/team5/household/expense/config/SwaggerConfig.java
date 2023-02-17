package com.team5.household.expense.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI floOpenAPI() {
        Info info =
        new Info().version("1.0.0").title("지출내역 서비스 API").description("Expense Service Restful API");
        return new OpenAPI().info(info);
    }
}
