package com.lagodich.textqrconvertor.controller;

import com.lagodich.textqrconvertor.exceptions.QrCodeAlreadyExistException;
import com.lagodich.textqrconvertor.exceptions.QrCodeNotFoundException;
import com.lagodich.textqrconvertor.service.RequestCounterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.lagodich.textqrconvertor.service.QrCodeService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.lagodich.textqrconvertor.entity.QrCode;
import com.lagodich.textqrconvertor.dto.QrCodeDto;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class QrCodeControllerTest {

    @Mock
    private QrCodeService qrCodeService;
    @Mock
    private QrCodeService.QrTextService qrCodeConvertorService;

    @InjectMocks
    private QrCodeController qrCodeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        qrCodeController = new QrCodeController(qrCodeConvertorService, qrCodeService);
    }

    @Test
    void text() {
        // Arrange
        String text = "Sample Text";
        String size = "100x100";
        String color = "black";
        String bgcolor = "white";

        byte[] qrCodeBytes = new byte[0]; // Sample byte array

        QrCodeService.QrTextService qrTextServiceMock = mock(QrCodeService.QrTextService.class);
        when(qrTextServiceMock.qrCode(text, size, color, bgcolor)).thenReturn(qrCodeBytes);

        QrCodeController qrCodeController = new QrCodeController(qrTextServiceMock, null);

        // Act
        ResponseEntity<byte[]> response = qrCodeController.text(text, size, color, bgcolor);

        // Assert
        assertEquals(MediaType.IMAGE_PNG, response.getHeaders().getContentType());
        assertEquals(qrCodeBytes, response.getBody());
    }

    @Test
    void createQrCode() throws QrCodeAlreadyExistException {
        // Arrange
        QrCode qrCode = new QrCode();
        qrCode.setContent("Sample Content");

        QrCodeService qrCodeServiceMock = mock(QrCodeService.class);
        when(qrCodeServiceMock.createQrCode(qrCode)).thenReturn(qrCode);

        QrCodeController qrCodeController = new QrCodeController(null, qrCodeServiceMock);

        // Act
        ResponseEntity<String> response = qrCodeController.createQrCode(qrCode);

        // Assert
        assertEquals("qrCode was successfully saved", response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(qrCodeServiceMock, times(1)).createQrCode(qrCode);
    }

    @Test
    void createQrCode_AlreadyExist() throws QrCodeAlreadyExistException {
        // Arrange
        QrCode qrCode = new QrCode();
        qrCode.setContent("Sample Content");

        QrCodeService qrCodeServiceMock = mock(QrCodeService.class);
        when(qrCodeServiceMock.createQrCode(qrCode)).thenThrow(new QrCodeAlreadyExistException());

        QrCodeController qrCodeController = new QrCodeController(null, qrCodeServiceMock);

        // Act
        ResponseEntity<String> response = qrCodeController.createQrCode(qrCode);

        // Assert
        assertEquals(400, response.getStatusCodeValue());
        verify(qrCodeServiceMock, times(1)).createQrCode(qrCode);
    }

    @Test
    void createQrCode_Exception() throws QrCodeAlreadyExistException {
        // Arrange
        QrCode qrCode = new QrCode();
        qrCode.setContent("Sample Content");

        QrCodeService qrCodeServiceMock = mock(QrCodeService.class);
        when(qrCodeServiceMock.createQrCode(qrCode)).thenThrow(new RuntimeException());

        QrCodeController qrCodeController = new QrCodeController(null, qrCodeServiceMock);

        // Act
        ResponseEntity<String> response = qrCodeController.createQrCode(qrCode);

        // Assert
        assertEquals(400, response.getStatusCodeValue());
        assertEquals("There's error", response.getBody());
        verify(qrCodeServiceMock, times(1)).createQrCode(qrCode);
    }

    @Test
    void createQrCodes() {
        // Arrange
        List<QrCode> qrCodes = new ArrayList<>();
        qrCodes.add(new QrCode());
        qrCodes.add(new QrCode());

        QrCodeService qrCodeServiceMock = mock(QrCodeService.class);
        when(qrCodeServiceMock.createQrCodes(qrCodes)).thenReturn(qrCodes);

        QrCodeController qrCodeController = new QrCodeController(null, qrCodeServiceMock);

        // Act
        ResponseEntity<List<QrCode>> response = qrCodeController.createQrCodes(qrCodes);

        // Assert
        assertEquals(qrCodes, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(qrCodeServiceMock, times(1)).createQrCodes(qrCodes);
    }

    @Test
    void getOneQrCode() throws QrCodeNotFoundException {
        // Arrange
        Long id = 1L;
        QrCodeDto qrCodeDto = new QrCodeDto();
        qrCodeDto.setId(id);

        QrCodeService qrCodeServiceMock = mock(QrCodeService.class);
        when(qrCodeServiceMock.getOneQrCode(id)).thenReturn(qrCodeDto);

        QrCodeController qrCodeController = new QrCodeController(null, qrCodeServiceMock);

        // Act
        ResponseEntity<QrCodeDto> response = qrCodeController.getOneQrCode(id);

        // Assert
        assertEquals(qrCodeDto, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(qrCodeServiceMock, times(1)).getOneQrCode(id);
    }

    @Test
    void deleteQrCode() {
        // Arrange
        Long id = 1L;

        QrCodeService qrCodeServiceMock = mock(QrCodeService.class);
        when(qrCodeServiceMock.deleteQrCode(id)).thenReturn(id);

        QrCodeController qrCodeController = new QrCodeController(null, qrCodeServiceMock);

        // Act
        ResponseEntity<Long> response = qrCodeController.deleteQrCode(id);

        // Assert
        assertEquals(id, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(qrCodeServiceMock, times(1)).deleteQrCode(id);
    }

    @Test
    void updateQrCode() throws QrCodeNotFoundException, QrCodeAlreadyExistException {
        // Arrange
        Long id = 1L;
        QrCode updatedQrCode = new QrCode();
        updatedQrCode.setId(id);
        updatedQrCode.setContent("Updated content");
        updatedQrCode.setSize("Updated size");

        QrCodeService qrCodeServiceMock = mock(QrCodeService.class);
        when(qrCodeServiceMock.updateQrCode(id, updatedQrCode)).thenReturn(updatedQrCode);

        QrCodeController qrCodeController = new QrCodeController(null, qrCodeServiceMock);

        // Act
        ResponseEntity<QrCode> response = qrCodeController.updateQrCode(id, updatedQrCode);

        // Assert
        assertEquals(updatedQrCode, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(qrCodeServiceMock, times(1)).updateQrCode(id, updatedQrCode);
    }
}