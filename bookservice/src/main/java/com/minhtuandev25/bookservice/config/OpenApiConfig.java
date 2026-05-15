package com.minhtuandev25.bookservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

	@Value("${server.port:9001}")
	private int serverPort;

	@Bean
	public OpenAPI bookServiceOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Book Service API")
						.description("CQRS book microservice — command (write) and query (read) endpoints.")
						.version("v1")
						.contact(new Contact()
								.name("Minh Tuan")
								.email("minhtuan@example.com"))
						.license(new License()
								.name("Apache 2.0")
								.url("https://www.apache.org/licenses/LICENSE-2.0")))
				.servers(List.of(
						new Server()
								// TODO: Add env later
								.url("http://localhost:" + serverPort)
								.description("Local")));
	}
}
