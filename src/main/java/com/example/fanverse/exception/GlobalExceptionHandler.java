package com.example.fanverse.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

		@ExceptionHandler(ClientErrorException.class)
		public ResponseEntity<ClientErrorResponse> handleClientErrorException(ClientErrorException e) {
				return new ResponseEntity<>(
								new ClientErrorResponse(e.getStatus(), e.getMessage(), null), e.getStatus());
		}

		@ExceptionHandler(MethodArgumentNotValidException.class)
		public ResponseEntity<ClientErrorResponse> handleValidation(MethodArgumentNotValidException e) {
			Map<String, String> fieldErrors = new HashMap<>();
			e.getBindingResult().getFieldErrors()
					.forEach(err -> fieldErrors.put(err.getField(), err.getDefaultMessage()));

			ClientErrorResponse body =
					new ClientErrorResponse(HttpStatus.BAD_REQUEST, "Validation failed", fieldErrors);

			return ResponseEntity.badRequest().body(body);
		}
}
