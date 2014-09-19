package com.github.darogina.ideas.config;

import com.github.darogina.ideas.service.Service;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {Service.class})
public class ServiceConfig {
}
