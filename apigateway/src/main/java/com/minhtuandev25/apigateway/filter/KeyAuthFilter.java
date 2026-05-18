package com.minhtuandev25.apigateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class KeyAuthFilter extends AbstractGatewayFilterFactory<KeyAuthFilter.Config> {

	private static final String API_KEY_HEADER = "X-API-KEY";

	@Value("${gateway.api-key}")
	private String apiKey;

	public KeyAuthFilter() {
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(Config config) {
		return (exchange, chain) -> {
			String key = exchange.getRequest().getHeaders().getFirst(API_KEY_HEADER);
			if (key == null || key.isBlank()) {
                return handleException(exchange,"Missing authorization information",HttpStatus.UNAUTHORIZED);
			}
			if (!apiKey.equals(key)) {
                return handleException(exchange,"Invalid Api Key",HttpStatus.FORBIDDEN);
			}
			return chain.filter(exchange);
		};
	}

    private Mono<Void> handleException(ServerWebExchange exchange, String message, HttpStatus status) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        String errorResponse = String.format(
                "{\"timestamp\": \"%s\", \"status\": %d, \"error\": \"%s\", \"message\": \"%s\", \"path\": \"%s\"}",
                java.time.ZonedDateTime.now().toString(),
                status.value(),status.getReasonPhrase(),message,exchange.getRequest().getURI().getPath()
        );
        return response.writeWith(Mono.just(response.bufferFactory().wrap(errorResponse.getBytes())));
    }

	static class Config {
	}
}
