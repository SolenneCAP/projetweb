package com.simplon.projetweb;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**@Data
@ConfigurationProperties(prefix = "com.simplon.projetweb")
@Configuration
**/
@Configuration
@Component
@ConfigurationProperties(prefix = "com.simplon.projetweb")
public class CustomProperties {
    private String apiUrl = "http://localhost:8083";

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
}
