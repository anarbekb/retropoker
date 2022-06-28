package ru.balmukanov.retropoker.adapter.rest;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;
import ru.balmukanov.retropoker.application.api.exception.RoomNotFoundException;

import java.util.List;

@ControllerAdvice
public class ErrorHandler {
	@ExceptionHandler(RoomNotFoundException.class)
	public Mono<ResponseEntity<String>> handleRoomNotFound() {
		return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room not found"));
	}

	@ExceptionHandler(WebExchangeBindException.class)
	public Mono<ResponseEntity<String>> handleValidationException(WebExchangeBindException e) {
		List<String> message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
		return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.join(";", message)));
	}
}
