package com.empresa.toolsapi.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //Construye la respuesta a partir de una excepcion
    private ResponseEntity<ErrorResponse>buildErrorResponse(
            Exception ex, HttpStatus status, HttpServletRequest request
    ){
        //Extrae el mensaje de la excepcion y delega al metodo principal
        return buildErrorResponse(ex.getMessage(), status, request);
    }

    //Construye a partir de un mensaje personalizado
    private ResponseEntity<ErrorResponse> buildErrorResponse(
            //Recibe -> ex.getMessage()
            String message, HttpStatus status, HttpServletRequest request
    ){
        ErrorResponse error = ErrorResponse.builder()
                .dateTime(LocalDateTime.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(message)
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(status).body(error);
    }

    //404 - Recurso no encontrado
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex, HttpServletRequest request){
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND, request);
    }

    //400 - Mala solicitud
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(BadRequestException ex, HttpServletRequest request){
        return buildErrorResponse(ex, HttpStatus.BAD_REQUEST, request);
    }

    //400 - Validaciones con @Valid en DTOs
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex, HttpServletRequest request){
        //Obtiene los errores vinculados por el cual se lanza la excepcion.
        //Extrae el mensaje del primer error de campo, ejemplo:"dni: ingrese su dni"
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .findFirst() //Muestra solo el primer error
                .orElse("Datos invalidos"); //Si no se encuentra un error especifico

        return buildErrorResponse(message, HttpStatus.BAD_REQUEST, request);
    }
}
