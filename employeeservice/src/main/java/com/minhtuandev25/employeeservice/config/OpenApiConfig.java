package com.minhtuandev25.employeeservice.config;

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

	@Value("${server.port:9002}")
	private int serverPort;

	@Bean
	public OpenAPI employeeServiceOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Employee Service API")
						.description("CQRS employee microservice — command (write) and query (read) endpoints.")
						.version("v1")
						.contact(new Contact()
								.name("Minh Tuan")
								.email("minhtuan@example.com"))
						.license(new License()
								.name("Apache 2.0")
								.url("https://www.apache.org/licenses/LICENSE-2.0")))
				.servers(List.of(
						new Server()
                                // Add env later
								.url("http://localhost:" + serverPort)
								.description("Local")));
	}
}
