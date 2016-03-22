package edu.scramjet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Incorrect format for one or more fields while adding user!")
public class BadUserException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
