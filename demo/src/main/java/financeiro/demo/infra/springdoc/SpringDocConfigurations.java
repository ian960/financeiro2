package financeiro.demo.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {  // Corrigido o nome da classe

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .info(new Info()
                        .title("Financeiro API")
                        .description("API Rest financeira, contendo as funcionalidades de CRUD de pessoas e de lançamentos.")
                        .contact(new Contact()
                                .name("Time Backend")
                                .email("Ian Albuquerque"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://microsoft.financeiro/api/licenca")));
    }
}
