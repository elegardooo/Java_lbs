package com.lagodich.textqrconvertor.service;

import com.lagodich.textqrconvertor.dto.QrCodeDto;
import com.lagodich.textqrconvertor.entity.QrCode;
import com.lagodich.textqrconvertor.exceptions.QrCodeAlreadyExistException;
import com.lagodich.textqrconvertor.exceptions.QrCodeNotFoundException;
import com.lagodich.textqrconvertor.repository.QrCodeRepo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class QrCodeServiceTest {

    @Test
    void createQrCode() throws QrCodeAlreadyExistException {
        // Arrange
        QrCodeRepo qrCodeRepo = Mockito.mock(QrCodeRepo.class);
        QrCodeService qrCodeService = new QrCodeService(qrCodeRepo);

        QrCode qrCode = new QrCode();
        qrCode.setContent("Test Content");
        qrCode.setSize("Large");

        when(qrCodeRepo.findByContent(qrCode.getContent())).thenReturn(null);
        when(qrCodeRepo.save(any(QrCode.class))).thenReturn(qrCode);

        // Act
        QrCode createdQrCode = qrCodeService.createQrCode(qrCode);

        // Assert
        assertEquals(qrCode.getContent(), createdQrCode.getContent());
        assertEquals(qrCode.getSize(), createdQrCode.getSize());
    }

    @Test
    void getOneQrCode() throws QrCodeNotFoundException {
        // Arrange
        QrCodeRepo qrCodeRepo = Mockito.mock(QrCodeRepo.class);
        QrCodeService qrCodeService = new QrCodeService(qrCodeRepo);

        Long id = 1L;
        QrCode qrCode = new QrCode();
        qrCode.setId(id);
        qrCode.setContent("Test Content");
        qrCode.setSize("Large");

        when(qrCodeRepo.findById(id)).thenReturn(java.util.Optional.of(qrCode));

        // Act
        QrCodeDto result = qrCodeService.getOneQrCode(id);

        // Assert
        assertEquals(qrCode.getId(), result.getId());
        assertEquals(qrCode.getContent(), result.getContent());
        assertEquals(qrCode.getSize(), result.getSize());
    }

    @Test
    void deleteQrCode() {
        // Arrange
        QrCodeRepo qrCodeRepo = Mockito.mock(QrCodeRepo.class);
        QrCodeService qrCodeService = new QrCodeService(qrCodeRepo);

        Long id = 1L;
        QrCode qrCode = new QrCode();
        qrCode.setId(id);

        when(qrCodeRepo.findById(id)).thenReturn(java.util.Optional.of(qrCode));
        doNothing().when(qrCodeRepo).deleteById(anyLong());

        // Act
        Long result = qrCodeService.deleteQrCode(id);

        // Assert
        assertEquals(id, result);
        verify(qrCodeRepo).deleteById(id);
    }

    @Test
    void updateQrCode() throws QrCodeNotFoundException, QrCodeAlreadyExistException {
        // Arrange
        QrCodeRepo qrCodeRepo = Mockito.mock(QrCodeRepo.class);
        QrCodeService qrCodeService = new QrCodeService(qrCodeRepo);

        Long id = 1L;
        QrCode existingQrCode = new QrCode();
        existingQrCode.setId(id);
        existingQrCode.setContent("Old Content");
        existingQrCode.setSize("Old Size");

        QrCode updatedQrCode = new QrCode();
        updatedQrCode.setId(id);
        updatedQrCode.setContent("New Content");
        updatedQrCode.setSize("New Size");

        when(qrCodeRepo.findById(id)).thenReturn(java.util.Optional.of(existingQrCode));
        when(qrCodeRepo.findByContent(updatedQrCode.getContent())).thenReturn(null);
        when(qrCodeRepo.save(existingQrCode)).thenReturn(existingQrCode);

        // Act
        QrCode result = qrCodeService.updateQrCode(id, updatedQrCode);

        // Assert
        assertEquals(updatedQrCode.getContent(), result.getContent());
        assertEquals(updatedQrCode.getSize(), result.getSize());
        verify(qrCodeRepo).save(existingQrCode);

    }

    @Test
    void updateQrCode_QrCodeNotFound() {
        // Arrange
        QrCodeRepo qrCodeRepo = Mockito.mock(QrCodeRepo.class);
        QrCodeService qrCodeService = new QrCodeService(qrCodeRepo);

        Long id = 1L;
        QrCode updatedQrCode = new QrCode();
        updatedQrCode.setId(id);
        updatedQrCode.setContent("New Content");
        updatedQrCode.setSize("New Size");

        when(qrCodeRepo.findById(id)).thenReturn(java.util.Optional.empty());

        // Act & Assert
        assertThrows(QrCodeNotFoundException.class, () -> qrCodeService.updateQrCode(id, updatedQrCode));
    }

    @Test
    void updateQrCode_QrCodeAlreadyExist() {
        // Arrange
        QrCodeRepo qrCodeRepo = Mockito.mock(QrCodeRepo.class);
        QrCodeService qrCodeService = new QrCodeService(qrCodeRepo);

        Long id = 1L;
        QrCode existingQrCode = new QrCode();
        existingQrCode.setId(id);
        existingQrCode.setContent("Existing Content");

        QrCode updatedQrCode = new QrCode();
        updatedQrCode.setId(id);
        updatedQrCode.setContent("New Content");

        when(qrCodeRepo.findById(id)).thenReturn(java.util.Optional.of(existingQrCode));
        when(qrCodeRepo.findByContent(updatedQrCode.getContent())).thenReturn(existingQrCode);

        // Act & Assert
        assertThrows(QrCodeAlreadyExistException.class, () -> qrCodeService.updateQrCode(id, updatedQrCode));
    }

    @Test
    void createQrCodes() throws QrCodeAlreadyExistException {
        // Mock QrCodeRepo
        QrCodeRepo qrCodeRepo = Mockito.mock(QrCodeRepo.class);

        // Create QrCodeService instance with the mocked repo
        QrCodeService qrCodeService = new QrCodeService(qrCodeRepo);

        // Create some sample QrCode objects
        QrCode qrCode1 = new QrCode();
        qrCode1.setContent("Content1");
        qrCode1.setSize("Size1");

        QrCode qrCode2 = new QrCode();
        qrCode2.setContent("Content2");
        qrCode2.setSize("Size2");

        QrCode qrCode3 = new QrCode();
        qrCode3.setContent("Content3");
        qrCode3.setSize("Size3");

        List<QrCode> qrCodeList = Arrays.asList(qrCode1, qrCode2, qrCode3);

        // Mock the save method of qrCodeRepo to return the same QrCode object that was passed to it
        when(qrCodeRepo.save(any(QrCode.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Call the method to be tested
        List<QrCode> savedQrCodes = qrCodeService.createQrCodes(qrCodeList);

        // Verify that save method was called for each QrCode object
        verify(qrCodeRepo, times(3)).save(any(QrCode.class));

        // Check if the returned list has the same QrCode objects
        assertEquals(qrCodeList, savedQrCodes);
    }
}