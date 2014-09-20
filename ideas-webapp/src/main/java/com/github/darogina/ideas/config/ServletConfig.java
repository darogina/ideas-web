package com.github.darogina.ideas.config;

import com.github.darogina.ideas.assembler.api.ResourceAssembler;
import com.github.darogina.ideas.controller.Controller;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = {Controller.class, ResourceAssembler.class})
@EnableEntityLinks
public class ServletConfig extends WebMvcConfigurerAdapter {

    private List<HttpMessageConverter<?>> messageConverters; // Cached: this is not a bean.
    private static final String[] RESOURCE_PATTERNS = {"css/", "images/", "lib/", "swagger-ui.js"};

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        // Note: this overwrites the default message converters.
        converters.addAll(getMessageConverters());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/").addResourceLocations("/resources/**");
    }

    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    /**
     * The message converters for the content types we support.
     *
     * @return the message converters; returns the same list on subsequent calls
     */
    private List<HttpMessageConverter<?>> getMessageConverters() {

        if (messageConverters == null) {
            messageConverters = new ArrayList<>();

            MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
            StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();

            messageConverters.add(mappingJacksonHttpMessageConverter);
            messageConverters.add(stringHttpMessageConverter);
        }
        return messageConverters;
    }
}
