package com.example.servingWebContent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableWebMvc
@EntityScan("com.example.domain")
@EnableJpaRepositories("com.example.repository")
public class ServingWebContent extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(ServingWebContent.class, args);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/assets/**")) {
            registry.addResourceHandler("/assets/**")
                    .addResourceLocations("classpath:/assets/");
        }
    }
}