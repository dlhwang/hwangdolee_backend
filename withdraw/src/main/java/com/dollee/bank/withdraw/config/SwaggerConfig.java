package com.dollee.bank.withdraw.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info =
    @Info(
        title = "Dollee Bank - Withdraw Swagger",
        description = "Dollee Bank - Withdraw REST API"))
public class SwaggerConfig {

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI().components(new Components()).info(apiInfo());
  }

  private io.swagger.v3.oas.models.info.Info apiInfo() {
    return new io.swagger.v3.oas.models.info.Info()
        .title("Dollee Bank - Withdraw Swagger")
        .description("Dollee Bank - Withdraw 관한 REST API")
        .version("1.0.0");
  }
}
