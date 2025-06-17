package com.dollee.market.dolleeMarket.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun swaggerApi() : OpenAPI = OpenAPI().components(Components())
        .info(swaggerInfo())

    private fun swaggerInfo() = Info()
        .title("스웨거 테스트")
        .description("스웨거로 API를 테스트")
        .version("1.0.0")
}