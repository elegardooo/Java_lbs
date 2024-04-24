package com.lagodich.textqrconvertor.controller;

import com.lagodich.textqrconvertor.cache.ResponseCache;
import com.lagodich.textqrconvertor.dto.QrCodeColorDto;
import com.lagodich.textqrconvertor.entity.QrCodeColor;
import com.lagodich.textqrconvertor.exceptions.QrCodeAlreadyExistException;
import com.lagodich.textqrconvertor.exceptions.QrCodeNotFoundException;
import com.lagodich.textqrconvertor.service.QrCodeColorService;
import com.lagodich.textqrconvertor.service.RequestCounterService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class QrCodeColorControllerTest {

    @Mock
    private QrCodeColorService qrCodeColorService;
    @Mock
    private ResponseCache responseCache;

    @Mock
    private RequestCounterService requestCounterService;

    @InjectMocks
    private QrCodeColorController qrCodeColorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        qrCodeColorController = new QrCodeColorController(qrCodeColorService, responseCache);
    }

    @Test
    void createColors_Success() throws QrCodeAlreadyExistException {
        // Mock request data
        QrCodeColor colors = new QrCodeColor();
        Long qrCodeId = 123L;

        // Mock service response
        QrCodeColorDto expectedDto = new QrCodeColorDto();
        when(qrCodeColorService.createColors(colors, qrCodeId)).thenReturn(expectedDto);

        // Call the controller method
        ResponseEntity<QrCodeColorDto> responseEntity = qrCodeColorController.createColors(colors, qrCodeId);

        // Verify the response
        assertEquals(ResponseEntity.ok(expectedDto), responseEntity);

        // Verify that the service method was called with correct arguments
        verify(qrCodeColorService).createColors(colors, qrCodeId);
    }

    @Test
    void getColors() throws QrCodeNotFoundException {
        // Mock request data
        Long id = 123L;

        // Mock cache hit scenario
        QrCodeColorDto expectedDto = new QrCodeColorDto();
        when(responseCache.getQrCodeColor(id)).thenReturn(expectedDto);

        // Call the controller method
        ResponseEntity<QrCodeColorDto> responseEntity = qrCodeColorController.getColors(id);

        // Verify the response
        assertEquals(ResponseEntity.ok(expectedDto), responseEntity);

        // Verify that the service method was not called
        verifyNoInteractions(qrCodeColorService);

        // Verify that the cache method was called with correct arguments
        verify(responseCache).getQrCodeColor(id);

        // Verify that the service method was not called
        verifyNoMoreInteractions(responseCache);
    }

    @Test
    void getColorByColorId() {
        // Mock request data
        Long colorId = 123L;

        // Mock service response
        List<QrCodeColorDto> expectedDtoList = new ArrayList<>();
        // Add some dummy data to the list
        expectedDtoList.add(new QrCodeColorDto());
        expectedDtoList.add(new QrCodeColorDto());

        when(qrCodeColorService.getQrCodeColorById(colorId)).thenReturn(expectedDtoList);

        // Call the controller method
        List<QrCodeColorDto> result = qrCodeColorController.getColorByColorId(colorId);

        // Verify the response
        assertEquals(expectedDtoList, result);

        // Verify that the service method was called with correct arguments
        verify(qrCodeColorService).getQrCodeColorById(colorId);
    }

    @Test
    void updateColors() throws QrCodeNotFoundException, QrCodeAlreadyExistException {
        // Mock request data
        QrCodeColor colors = new QrCodeColor();
        Long id = 123L;

        // Mock service response
        QrCodeColorDto expectedDto = new QrCodeColorDto();
        when(qrCodeColorService.updateColors(colors, id)).thenReturn(expectedDto);

        // Call the controller method
        ResponseEntity<QrCodeColorDto> responseEntity = qrCodeColorController.updateColors(colors, id);

        // Verify the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedDto, responseEntity.getBody());

        // Verify that the service method was called with correct arguments
        verify(qrCodeColorService).updateColors(colors, id);
    }

    @Test
    void deleteColors() {
        // Mock request data
        Long id = 123L;
        Long deletedId = 123L; // Assume the ID of the deleted record

        // Mock service method to return the ID of the deleted record
        Mockito.when(qrCodeColorService.deleteColors(id)).thenReturn(deletedId);

        // Call the controller method
        ResponseEntity<String> responseEntity = qrCodeColorController.deleteColors(id);

        // Verify the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Success", responseEntity.getBody());

        // Verify that the service method was called with correct arguments
        verify(qrCodeColorService).deleteColors(id);
    }
}