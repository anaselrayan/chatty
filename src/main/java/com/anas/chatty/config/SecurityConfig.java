package com.anas.chatty.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {

    private final String[] ALLOWED_ORIGINS = {"http://localhost:4200"};
    private final String[] ALLOWED_METHODS = {"GET", "POST", "PUT", "OPTIONS"};
    private final String[] ALLOWED_HEADERS = {"Origin", "Content-Type", "Accept", "Authorization",
            "Access-Control-Allow-Headers", "Access-Control-Allow-Methods",
            "access-control-allow-origin", "Access-Control-Allow-Credentials"};
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(ALLOWED_ORIGINS)
                .allowedMethods("*")
                .allowedHeaders("*");
    }

}
