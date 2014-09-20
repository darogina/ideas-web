package com.github.darogina.ideas.config;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import com.wordnik.swagger.model.ApiInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.inject.Inject;

@Configuration
@EnableSwagger
public class DocumentationConfig {

    @Inject
    private SpringSwaggerConfig swaggerConfig;

    @Bean
    public SwaggerSpringMvcPlugin apiV1Plugin() {
        return new SwaggerSpringMvcPlugin(swaggerConfig)
                .includePatterns(".*v1.*")
                .swaggerGroup("v1")
                .apiVersion("v1")
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Ideas",
                "Idea Submission API",
                null,
                null,
                null,
                null
        );
        return apiInfo;
    }
}
