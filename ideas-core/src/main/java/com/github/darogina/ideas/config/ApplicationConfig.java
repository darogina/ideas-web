package com.github.darogina.ideas.config;

import com.github.darogina.ideas.initializer.Initializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.hateoas.ResourceAssembler;

@Configuration
@ComponentScan(basePackageClasses = {Initializer.class})
@Import({SecurityConfig.class, MethodSecurityConfig.class, OAuth2ServerConfig.class, ServiceConfig.class})
public class ApplicationConfig {

}
