package com.example.ecarthub.Config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "services.base-url")
public class ServicesBaseURLConfig {
    private String productService;
    private String authService;
}
