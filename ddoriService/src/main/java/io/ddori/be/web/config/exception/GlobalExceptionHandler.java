package io.ddori.be.web.config.exception;

//import org.apache.commons.lang3.exception.ExceptionUtils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import io.ddori.be.common.model.ResultInfo;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
        //ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        //return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
		
		ResultInfo result = makeErrorResult(ex);	
		return new ResponseEntity(result, HttpStatus.OK);
		
    }
	
	@ExceptionHandler({ AccessDeniedException.class })
    public ResponseEntity<Object> handleAccessDeniedException(
      Exception ex, WebRequest request) {
		ResultInfo result = makeErrorResult(ex);	
		return new ResponseEntity(result, HttpStatus.FORBIDDEN);
    }

	private ResultInfo makeErrorResult(Exception ex){
		
		ResultInfo result = new ResultInfo();	
		String sDesc = ex.getMessage();
		result.setFlag(ResultInfo.RESULT_FAIL);
		result.setDesc(sDesc);
		return result;		
	}	
	
}
