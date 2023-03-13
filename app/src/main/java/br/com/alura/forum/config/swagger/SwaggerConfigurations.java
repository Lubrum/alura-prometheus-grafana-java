package br.com.alura.forum.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfigurations {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(apiInfo());
	}

	private Info apiInfo() {
		return new Info()
				.title("Alura forum API")
				.description("API de criação de tópicos e respostas")
				.version("1.0")
				.contact(apiContact())
				.license(apiLicence());
	}

	private License apiLicence() {
		return new License()
				.name("MIT Licence")
				.url("https://opensource.org/licenses/mit-license.php");
	}

	private Contact apiContact() {
		return new Contact()
				.name("Luciano Moraes da Luz Brum")
				.email("teste@gmail.com")
				.url("https://github.com/Lubrum");
	}
}
