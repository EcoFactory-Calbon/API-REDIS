package com.example.apiredis.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {

        final Info info = new Info()
                .title("API de Notícias e Posts em Redis")
                .version("1.0.0")
                .description("API para gerenciar notícias e posts favoritados, utilizando Spring Boot e Redis como banco de dados principal.")
                .contact(new Contact()
                        .name("Seu Nome")
                        .email("seu-email@exemplo.com")
                        .url("https://seusite.com.br"))
                .license(new License()
                        .name("Apache 2.0")
                        .url("https://www.apache.org/licenses/LICENSE-2.0.html"));

        final List<Server> servers = List.of(
                new Server().url("http://localhost:8080").description("Ambiente de Desenvolvimento Local"),
                new Server().url("https://api.meudominio.com").description("Ambiente de Produção")
        );

        final String securitySchemeName = "bearerAuth";
        final SecurityScheme securityScheme = new SecurityScheme()
                .name(securitySchemeName)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .description("Token JWT de autenticação. Exemplo: Bearer <seu_token>");

        final Components components = new Components()
                .addSecuritySchemes(securitySchemeName, securityScheme);

        final SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList(securitySchemeName);

        return new OpenAPI()
                .info(info)
                .servers(servers)
                .components(components)
                .addSecurityItem(securityRequirement);
    }
}