package com.simplon.projetweb;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties(prefix = "com.simplon.projetweb")
@Configuration
public class CustomProperties {
    private String apiUrl;
}
