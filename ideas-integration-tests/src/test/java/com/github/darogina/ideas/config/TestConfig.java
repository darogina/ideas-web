package com.github.darogina.ideas.config;

import com.github.darogina.ideas.assembler.api.ResourceAssembler;
import com.github.darogina.ideas.service.Service;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackageClasses = {Service.class, ResourceAssembler.class})
@Import({TestJpaConfig.class, ServletConfig.class})
@PropertySource("classpath:/persistence.properties")
public class TestConfig {

}
