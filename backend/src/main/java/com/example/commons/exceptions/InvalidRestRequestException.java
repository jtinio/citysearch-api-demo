package com.example.commons.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.Errors;

@Getter
@Setter
public class InvalidRestRequestException extends RuntimeException {

	private Errors errors;

	public InvalidRestRequestException(String message) {
		super(message);
	}

	public InvalidRestRequestException(String message, Errors errors) {
		super(message);
		this.errors = errors;
	}

}