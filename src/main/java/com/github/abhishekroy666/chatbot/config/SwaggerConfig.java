package com.github.abhishekroy666.chatbot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.github.abhishekroy666.chatbot.resource.api"))
                .build();
    }

    /* collective service description */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("chat-bot Rest APIs")
                .description("This page lists all the APIs for chat-bot")
                .version("0.0.1-SNAPSHOT")
                .build();
    }
}
