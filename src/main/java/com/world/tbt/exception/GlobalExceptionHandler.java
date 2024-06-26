package com.world.tbt.exception;

import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.thymeleaf.exceptions.TemplateInputException;
import org.thymeleaf.exceptions.TemplateProcessingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidDefinitionException.class)
    public ResponseEntity<?> handleInvalidDefinitionException(InvalidDefinitionException e)
    {
        return ResponseEntity.status(500).body("Internal Server Error");
    }
    @ExceptionHandler(RequestRejectedException.class)
    public ResponseEntity<?> handleRequestRejectedException(RequestRejectedException e)
    {
        System.out.println("The request was rejected because the URL contained a potentially malicious - double slash");
        return ResponseEntity.badRequest().body("The request was rejected because the URL contained a potentially malicious - double slash");
    }
    //Thymeleaf exception
    @ExceptionHandler(org.attoparser.ParseException.class)
    public String handleParseException(org.attoparser.ParseException e)
    {
        return "redirect:/403";
    }
    @ExceptionHandler(TemplateProcessingException.class)
    public String handleTemplateProcessingException(TemplateProcessingException e)
    {;
        return "redirect:/403";
    }
    @ExceptionHandler(TemplateInputException.class)
    public String handleTemplateInputException(TemplateInputException e)
    {
        return "redirect:/403";
    }
    //Thymeleaf exception end
    @ExceptionHandler(SpelEvaluationException.class)
    public ResponseEntity<?> handleSpelEvaluationException(SpelEvaluationException e)
    {
        return ResponseEntity.status(500).body("Internal Server Error");
    }
    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<?> handlePropertyReferenceException(PropertyReferenceException e)
    {
        return ResponseEntity.badRequest().body("Internal Server Error");
    }
    @ExceptionHandler(AccessDeniedException.class)
    public void handleAccessDeniedGlobalExceptionHandler(Exception e, HttpServletRequest request, HttpServletResponse response)
    {
        try {
            response.sendRedirect("accessdenied");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    @ExceptionHandler(NoHandlerFoundException.class)
    public void handlePageNotFound(Exception e)
    {
        System.out.println("handlePageNotFound()-GlobalExceptionHandler.class");
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public void handleUsernameNotFoundException(Exception e)
    {
        System.out.println("handleUsernameNotFoundException()-GlobalExceptionHandler.class");
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<?> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        System.out.println("handleUsernameNotFoundException()-GlobalExceptionHandler.class");
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("File too large!");
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exc) {
        System.out.println("handleMethodArgumentTypeMismatchException()-GlobalExceptionHandler.class");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc.getMessage());
    }
    //Another way similar to resolve in DefaultHandlerExceptionResolver.class
/*    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ModelAndView handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exc,HttpServletRequest request, HttpServletResponse response, @Nullable Object handler) throws IOException {
        System.out.println("handleMethodArgumentTypeMismatchException()-GlobalExceptionHandler.class");
        response.sendError(400);
        return new ModelAndView();
    }*/
}
