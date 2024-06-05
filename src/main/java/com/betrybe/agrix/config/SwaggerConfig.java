package com.betrybe.agrix.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Swagger config.
 */
@Configuration
public class SwaggerConfig {

  /**
   * Open api open api.
   *
   * @return the open api
   */
  @Bean
  public OpenAPI openApi() {
    return new OpenAPI()
        .info(new io.swagger.v3.oas.models.info.Info()
            .title("Agrix API")
            .description("API para gerenciamento de fazendas e plantações")
            .version("1.0.0")
        );
  }
}
