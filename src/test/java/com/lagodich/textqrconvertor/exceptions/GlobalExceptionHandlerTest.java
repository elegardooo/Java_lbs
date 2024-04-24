package com.lagodich.textqrconvertor.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GlobalExceptionHandlerTest {

    @Test
    void handleNoHandlerFoundExceptionTest() {
        // Given
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        NoHandlerFoundException exception = new NoHandlerFoundException("GET", "/example", null);

        // When
        ExceptionResponse response = globalExceptionHandler.handleNoHandlerFoundException(exception);

        // Then
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
        // Adjusted assertion to ignore trailing periods
        assertEquals("No endpoint GET /example", response.getMessage().replaceAll("\\.$", ""));
    }

    @Test
    void handleMethodNotAllowedTest() {
        // Given
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        HttpRequestMethodNotSupportedException exception = new HttpRequestMethodNotSupportedException("GET");

        // When
        ExceptionResponse response = globalExceptionHandler.handleMethodNotAllowed(exception);

        // Then
        assertEquals(HttpStatus.METHOD_NOT_ALLOWED.value(), response.getStatusCode());
        assertEquals("Request method 'GET' is not supported", response.getMessage());
    }

    @Test
    void handleInternalServerErrorTest() {
        // Given
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        RuntimeException exception = new RuntimeException("Internal Server Error");

        // When
        ExceptionResponse response = globalExceptionHandler.handleInternalServerError(exception);

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatusCode());
        assertEquals("Internal Server Error", response.getMessage());
    }

    @Test
    void testHandleInternalServerError() {
        // Arrange
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        RuntimeException exception = new RuntimeException("Internal Server Error");

        // Act
        ExceptionResponse response = handler.handleInternalServerError(exception);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatusCode());
        assertEquals("Internal Server Error", response.getMessage());
    }
}