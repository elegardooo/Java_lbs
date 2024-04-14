package com.lagodich.textqrconvertor.service;

import com.lagodich.textqrconvertor.dto.QrCodeColorDto;
import com.lagodich.textqrconvertor.entity.QrCode;
import com.lagodich.textqrconvertor.entity.QrCodeColor;
import com.lagodich.textqrconvertor.exceptions.QrCodeAlreadyExistException;
import com.lagodich.textqrconvertor.exceptions.QrCodeNotFoundException;
import com.lagodich.textqrconvertor.repository.QrCodeColorRepo;
import com.lagodich.textqrconvertor.repository.QrCodeRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class QrCodeColorServiceTest {

    @Mock
    private QrCodeRepo qrCodeRepo;

    @Mock
    private QrCodeColorRepo qrCodeColorRepo;

    private QrCodeColorService qrCodeColorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        qrCodeColorService = new QrCodeColorService(qrCodeRepo, qrCodeColorRepo);
    }

    @Test
    void createColors() throws QrCodeAlreadyExistException {
        // Arrange
        QrCodeColor color = new QrCodeColor();
        color.setColor("Red");
        color.setBgcolor("White");
        QrCode qrCode = new QrCode();
        qrCode.setId(1L);
        when(qrCodeRepo.findById(1L)).thenReturn(java.util.Optional.of(qrCode));
        when(qrCodeColorRepo.findByColor("Red")).thenReturn(null);
        when(qrCodeColorRepo.findByBgcolor("White")).thenReturn(null);
        when(qrCodeColorRepo.save(color)).thenReturn(color);

        // Act
        QrCodeColorDto result = qrCodeColorService.createColors(color, 1L);

        // Assert
        assertEquals("Red", result.getColor());
        assertEquals("White", result.getBgcolor());
    }

    @Test
    void getColors() throws QrCodeNotFoundException {
        // Arrange
        Long colorId = 1L;
        QrCodeColor qrCodeColor = new QrCodeColor();
        qrCodeColor.setId(colorId);
        qrCodeColor.setColor("Red");
        qrCodeColor.setBgcolor("White");
        when(qrCodeColorRepo.findById(colorId)).thenReturn(Optional.of(qrCodeColor));

        // Act
        QrCodeColorDto result = qrCodeColorService.getColors(colorId);

        // Assert
        assertEquals(qrCodeColor.getId(), result.getId());
        assertEquals(qrCodeColor.getColor(), result.getColor());
        assertEquals(qrCodeColor.getBgcolor(), result.getBgcolor());
    }

    @Test
    void getQrCodeColorById() {
        // Arrange
        Long colorId = 1L;
        List<QrCodeColor> qrCodeColors = new ArrayList<>();
        QrCodeColor qrCodeColor1 = new QrCodeColor();
        qrCodeColor1.setId(1L);
        qrCodeColor1.setColor("Red");
        qrCodeColor1.setBgcolor("White");
        QrCodeColor qrCodeColor2 = new QrCodeColor();
        qrCodeColor2.setId(2L);
        qrCodeColor2.setColor("Blue");
        qrCodeColor2.setBgcolor("Black");
        qrCodeColors.add(qrCodeColor1);
        qrCodeColors.add(qrCodeColor2);
        when(qrCodeColorRepo.findQrCodeColorByColorId(colorId)).thenReturn(qrCodeColors);

        // Act
        List<QrCodeColorDto> result = qrCodeColorService.getQrCodeColorById(colorId);

        // Assert
        assertEquals(2, result.size());
        assertEquals(qrCodeColor1.getId(), result.get(0).getId());
        assertEquals(qrCodeColor1.getColor(), result.get(0).getColor());
        assertEquals(qrCodeColor1.getBgcolor(), result.get(0).getBgcolor());
        assertEquals(qrCodeColor2.getId(), result.get(1).getId());
        assertEquals(qrCodeColor2.getColor(), result.get(1).getColor());
        assertEquals(qrCodeColor2.getBgcolor(), result.get(1).getBgcolor());
    }

    @Test
    void updateColors() throws QrCodeNotFoundException, QrCodeAlreadyExistException {
        // Arrange
        QrCodeRepo qrCodeRepo = Mockito.mock(QrCodeRepo.class); // Mock QrCodeRepo
        QrCodeColorRepo qrCodeColorRepo = Mockito.mock(QrCodeColorRepo.class);
        QrCodeColorService qrCodeColorService = new QrCodeColorService(qrCodeRepo, qrCodeColorRepo); // Pass both mocks

        Long id = 1L;
        QrCodeColor existingColor = new QrCodeColor();
        existingColor.setId(id);
        existingColor.setColor("Red");
        existingColor.setBgcolor("White");
        when(qrCodeColorRepo.findById(id)).thenReturn(Optional.of(existingColor));

        QrCodeColor updatedColor = new QrCodeColor();
        updatedColor.setId(id);
        updatedColor.setColor("Blue");
        updatedColor.setBgcolor("Black");
        when(qrCodeColorRepo.save(updatedColor)).thenReturn(updatedColor);

        // Act
        QrCodeColorDto result = qrCodeColorService.updateColors(updatedColor, id);

        // Assert
        assertEquals(id, result.getId());
        assertEquals(updatedColor.getColor(), result.getColor());
        assertEquals(updatedColor.getBgcolor(), result.getBgcolor());
    }

    @Test
    void deleteColors() {
        // Arrange
        QrCodeRepo qrCodeRepo = Mockito.mock(QrCodeRepo.class);
        QrCodeColorRepo qrCodeColorRepo = Mockito.mock(QrCodeColorRepo.class);
        QrCodeColorService qrCodeColorService = new QrCodeColorService(qrCodeRepo, qrCodeColorRepo);

        Long id = 1L;
        doNothing().when(qrCodeColorRepo).deleteById(id);

        // Act
        Long result = qrCodeColorService.deleteColors(id);

        // Assert
        assertEquals(id, result);
    }
}