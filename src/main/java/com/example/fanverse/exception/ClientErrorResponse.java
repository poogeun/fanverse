package com.example.fanverse.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Map;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record ClientErrorResponse(HttpStatus status, Object message, Map<String, String> errors) {
}
