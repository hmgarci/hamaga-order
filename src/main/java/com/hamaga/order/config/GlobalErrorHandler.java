package com.hamaga.order.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hamaga.order.exceptions.DomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@Order(-2)
public class GlobalErrorHandler implements ErrorWebExceptionHandler {
    private final ObjectMapper objectMapper;

    public GlobalErrorHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        HttpStatus status = ex instanceof DomainException domainEx ? domainEx.getStatus() : HttpStatus.INTERNAL_SERVER_ERROR;
        String error = ex instanceof DomainException ? "Business Error" : "Internal Server Error";

        log.error("Handled exception: {}", ex.getMessage(), ex);

        exchange.getResponse().setStatusCode(status);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> errorAttributes = Map.of(
                "error", error,
                "message", ex.getMessage(),
                "status", status.value(),
                "path", exchange.getRequest().getPath().value()
        );

        return Mono.fromCallable(() -> objectMapper.writeValueAsBytes(errorAttributes))
                .map(bytes -> exchange.getResponse().bufferFactory().wrap(bytes))
                .flatMap(buffer -> exchange.getResponse().writeWith(Mono.just(buffer)))
                .onErrorResume(e -> exchange.getResponse().setComplete());
    }
}
