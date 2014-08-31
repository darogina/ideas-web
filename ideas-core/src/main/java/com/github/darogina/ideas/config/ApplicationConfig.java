package com.github.darogina.ideas.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan({"com.github.darogina.ideas.assembler"})
@ImportResource({"classpath:/spring/services-context.xml"})
public class ApplicationConfig {

}
