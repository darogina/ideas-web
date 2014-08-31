package com.github.darogina.ideas.config;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan({"com.github.darogina.ideas.service", "com.github.darogina.ideas.assembler"})
@Import({TestJpaConfig.class, ServletConfig.class})
@PropertySource("classpath:/persistence.properties")
public class TestConfig {

}
