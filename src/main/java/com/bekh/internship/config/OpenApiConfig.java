package com.bekh.internship.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        org.springdoc.core.SpringDocConfigProperties.class,
        org.springdoc.core.SpringDocConfiguration.class,
        org.springdoc.webmvc.core.SpringDocWebMvcConfiguration.class,
        org.springdoc.webmvc.core.MultipleOpenApiSupportConfiguration.class,
        org.springdoc.core.SwaggerUiConfigProperties.class, org.springdoc.core.SwaggerUiOAuthProperties.class,
        org.springdoc.webmvc.ui.SwaggerConfig.class,
        org.springdoc.core.CacheOrGroupedOpenApiCondition.class,
        org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration.class })
public class OpenApiConfig {

  @Bean
  public GroupedOpenApi publicApi() {
    return GroupedOpenApi.builder()
            .group("user")
            .pathsToExclude("/api/v2/**")
            .pathsToMatch("/api/v1/**")
            .build();
  }

  @Bean
  public GroupedOpenApi adminApi() {
    return GroupedOpenApi.builder()
            .group("admin")
            .pathsToExclude("/api/v1/**")
            .pathsToMatch("/api/v2/**")
            .build();
  }

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
            .components(new Components())
            .info(new Info()
                    .title("Spring MVC REST API")
                    .contact(new Contact().name("Andrey Bekh"))
                    .version("1.0.0"));
  }
}