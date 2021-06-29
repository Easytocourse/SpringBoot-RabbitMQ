package com.easytocourse.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.easytocourse.model.EmployeeErrorResponse;

@ControllerAdvice
public class EmployeeExceptionHandler extends ResponseEntityExceptionHandler{

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<String> details = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        
        EmployeeErrorResponse errorresponse=new EmployeeErrorResponse(System.currentTimeMillis(),"Validation Failed",details);
        return new ResponseEntity<Object>(errorresponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	protected ResponseEntity<Object> handlenosuchelementexception(NoSuchElementException ex)
	{
		
		EmployeeErrorResponse errorresponse=new EmployeeErrorResponse(System.currentTimeMillis(),"Invalid Employee Request",Arrays.asList(ex.getMessage()));
        return new ResponseEntity<Object>(errorresponse, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex)
	{
		String error=ex.getName()+" should be of type "+ex.getRequiredType().getName();
		EmployeeErrorResponse errorresponse=new EmployeeErrorResponse(System.currentTimeMillis(),"Invalid Employee Request",Arrays.asList(error));
        return new ResponseEntity<Object>(errorresponse, HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(Employeenotfound.class)
	protected ResponseEntity<Object> handleEmployeenotfound(Employeenotfound ex)
	{
		
		EmployeeErrorResponse errorresponse=new EmployeeErrorResponse(System.currentTimeMillis(),"Employee NotFound",Arrays.asList(ex.getMessage()));
        return new ResponseEntity<Object>(errorresponse, HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler
	protected ResponseEntity<Object> handleanyotherexecption(Exception ex)
	{
				EmployeeErrorResponse errorresponse=new EmployeeErrorResponse(System.currentTimeMillis(),"Invalid Employee Request",Arrays.asList(ex.getMessage()));
        return new ResponseEntity<Object>(errorresponse, HttpStatus.BAD_REQUEST);
		
	}
	

}
