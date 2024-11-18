package com.podcast_streaming.gustavo_duarte;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
  @Bean
  public OpenAPI customOpenAPI() {
     return new OpenAPI()
            .info(new Info()
                .title("API: Podcast Streaming") 
                .version("1.0")
                .description("API para gerenciamento conteúdo audio como podcast e músicas")
                .contact(new Contact()
                    .name("Gustavo Duarte")
                    .email("gustavo_dmuniz@hotmail.com")));
  }
}
