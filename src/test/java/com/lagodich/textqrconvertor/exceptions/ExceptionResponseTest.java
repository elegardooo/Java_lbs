package com.lagodich.textqrconvertor.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExceptionResponseTest {

    @Test
    void getStatusCode() {
        // Arrange
        int expectedStatusCode = 404;
        String message = "Not Found";
        ExceptionResponse exceptionResponse = new ExceptionResponse(expectedStatusCode, message);

        // Act
        int actualStatusCode = exceptionResponse.getStatusCode();

        // Assert
        assertEquals(expectedStatusCode, actualStatusCode);
    }

    @Test
    void setStatusCode() {
        // Arrange
        int expectedStatusCode = 404;
        String message = "Not Found";
        ExceptionResponse exceptionResponse = new ExceptionResponse(500, message);

        // Act
        exceptionResponse.setStatusCode(expectedStatusCode);

        // Assert
        assertEquals(expectedStatusCode, exceptionResponse.getStatusCode());
    }

    @Test
    void getMessage() {
        // Arrange
        int statusCode = 404;
        String expectedMessage = "Not Found";
        ExceptionResponse exceptionResponse = new ExceptionResponse(statusCode, expectedMessage);

        // Act
        String actualMessage = exceptionResponse.getMessage();

        // Assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void setMessage() {
        // Arrange
        int statusCode = 404;
        String initialMessage = "Initial Message";
        String expectedMessage = "New Message";
        ExceptionResponse exceptionResponse = new ExceptionResponse(statusCode, initialMessage);

        // Act
        exceptionResponse.setMessage(expectedMessage);

        // Assert
        assertEquals(expectedMessage, exceptionResponse.getMessage());
    }
}