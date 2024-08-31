package com.techlabs.lending.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


import com.techlabs.lending.errors.ErrorResponse;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class ExceptionsHandler {

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(CustomerNotFoundException exception)
	{
		ErrorResponse error = new ErrorResponse();
		error.setStatusCode(HttpStatus.NOT_FOUND.value());
		error.setErrorMessage(exception.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleLoanNotFoundException(LoanNotFoundException exception)
	{
		ErrorResponse error = new ErrorResponse();
		error.setStatusCode(HttpStatus.NOT_FOUND.value());
		error.setErrorMessage(exception.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handlePaymentNotFoundException(PaymentNotFoundException exception)
	{
		ErrorResponse error = new ErrorResponse();
		error.setStatusCode(HttpStatus.NOT_FOUND.value());
		error.setErrorMessage(exception.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleStudentException(MethodArgumentTypeMismatchException exception)
	{
		ErrorResponse error = new ErrorResponse();
		error.setStatusCode(HttpStatus.BAD_REQUEST.value());
		error.setErrorMessage("id must be an integer");
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<?> handleStudentException(MethodArgumentNotValidException exception)
	{
		Map<String,String> errors =new HashMap<>();
		
		exception.getBindingResult().getFieldErrors().forEach((error)->{
			errors.put(error.getField(), error.getDefaultMessage());
		});
		
		return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler
	public ResponseEntity<?> handleConstraintExceptions(ConstraintViolationException exception)
	{
		Map<String,String> errors =new HashMap<>();
		
		exception.getConstraintViolations().forEach((error)->{
			errors.put( error.getPropertyPath().toString(), error.getMessage());
		});
		
		return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        // Construct the error response
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorMessage("input cannot be blank");
        errorResponse.setStatusCode(400); 
        errorResponse.setTimeStamp(System.currentTimeMillis());

        // Return a ResponseEntity with a meaningful error message
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
	
	
}
