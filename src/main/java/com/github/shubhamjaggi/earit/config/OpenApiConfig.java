package com.github.shubhamjaggi.earit.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Ear It! API")
                        .description("REST API for the Ear It! ear training application. " +
                                "Plays musical elements via MIDI and returns what was played for the UI to check against.")
                        .version("1.0.0"));
    }
}
