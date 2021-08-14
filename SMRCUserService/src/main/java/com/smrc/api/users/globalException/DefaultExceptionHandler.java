package com.smrc.api.users.globalException;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.smrc.api.users.utils.ApiMessageHandler;
import com.smrc.api.users.utils.ApiMessageHandler.Type;

@RestControllerAdvice
public class DefaultExceptionHandler {
 	
	Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);
	
	@RequestMapping(produces = {"application/json"})
    @ExceptionHandler({MissingServletRequestParameterException.class,
            UnsatisfiedServletRequestParameterException.class,
            HttpRequestMethodNotSupportedException.class,
            ServletRequestBindingException.class
    })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<?>  handleRequestException(Exception ex) {
		ApiMessageHandler apiMessageHandler = new ApiMessageHandler(Type.ERROR, HttpStatus.BAD_REQUEST.toString(),"Bad Request");
		return new ResponseEntity<>(apiMessageHandler,HttpStatus.BAD_REQUEST);
    }
	
    @RequestMapping(produces = {"application/json"})
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public ResponseEntity<?> handleUnsupportedMediaTypeException(HttpMediaTypeNotSupportedException ex) throws IOException {
    	ApiMessageHandler apiMessageHandler = new ApiMessageHandler(Type.ERROR, HttpStatus.UNSUPPORTED_MEDIA_TYPE.toString(),"Unsupported Media Type");
    	return new ResponseEntity<>(apiMessageHandler,HttpStatus.UNSUPPORTED_MEDIA_TYPE);
   
    }

	@RequestMapping(produces = { "application/json" })
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<?>  handleUncaughtException(Exception ex) throws IOException {
		ApiMessageHandler apiMessageHandler =null;
    	if(ex!=null && ex.getMessage() !=null && ex.getMessage().equals("403")){
    	    apiMessageHandler = new ApiMessageHandler(Type.ERROR, "403","Access Denied");
    	    return new ResponseEntity<>(apiMessageHandler,HttpStatus.FORBIDDEN);
    	}else{
    		apiMessageHandler = new ApiMessageHandler(Type.ERROR, HttpStatus.INTERNAL_SERVER_ERROR.toString(),"Internal error occurred,Kindly contact your System Administrator!");
    	}
		if (ex.getCause() != null) {
			logger.error(ex.getCause().getMessage());
		} else {
			//logger.error(ex.getStackTrace());
		}
		return new ResponseEntity<>(apiMessageHandler,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	    @ExceptionHandler(NoHandlerFoundException.class)
	    @ResponseStatus(value= HttpStatus.NOT_FOUND)
	    public ResponseEntity<?> requestHandlingNoHandlerFound() {
	    	ApiMessageHandler apiMessageHandler = new ApiMessageHandler(Type.ERROR, HttpStatus.NOT_FOUND.toString(),"Request URL Not found !!!");
	    	return new ResponseEntity<>(apiMessageHandler,HttpStatus.INTERNAL_SERVER_ERROR);
	    }
}
