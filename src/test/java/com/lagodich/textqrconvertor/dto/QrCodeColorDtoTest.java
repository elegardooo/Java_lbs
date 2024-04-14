package com.lagodich.textqrconvertor.dto;

import org.junit.jupiter.api.Test;
import com.lagodich.textqrconvertor.entity.QrCodeColor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class QrCodeColorDtoTest {

    @Test
    void toModel() {
        // Create a QrCodeColor entity for testing
        QrCodeColor entity = new QrCodeColor();
        entity.setId(1L);
        entity.setColor("red");
        entity.setBgcolor("white");

        // Call the toModel() method
        QrCodeColorDto dto = QrCodeColorDto.toModel(entity);

        // Assert that the returned dto is not null
        assertNotNull(dto);

        // Assert that the properties are correctly mapped
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getColor(), dto.getColor());
        assertEquals(entity.getBgcolor(), dto.getBgcolor());
    }

    @Test
    void getId() {
        // Create a QrCodeColorDto instance
        QrCodeColorDto dto = new QrCodeColorDto();

        // Set a sample id
        Long expectedId = 1L;
        dto.setId(expectedId);

        // Retrieve the id using getId() method
        Long actualId = dto.getId();

        // Assert that the retrieved id matches the expected id
        assertEquals(expectedId, actualId);
    }

    @Test
    void setId() {
        // Create a QrCodeColorDto instance
        QrCodeColorDto dto = new QrCodeColorDto();

        // Set a sample id
        Long expectedId = 1L;
        dto.setId(expectedId);

        // Retrieve the id using getId() method
        Long actualId = dto.getId();

        // Assert that the retrieved id matches the expected id
        assertEquals(expectedId, actualId);
    }

    @Test
    void getColor() {
        // Create a QrCodeColorDto instance
        QrCodeColorDto dto = new QrCodeColorDto();

        // Set a sample color
        String expectedColor = "red";
        dto.setColor(expectedColor);

        // Retrieve the color using getColor() method
        String actualColor = dto.getColor();

        // Assert that the retrieved color matches the expected color
        assertEquals(expectedColor, actualColor);
    }

    @Test
    void setColor() {
        // Create a QrCodeColorDto instance
        QrCodeColorDto dto = new QrCodeColorDto();

        // Set a sample color
        String expectedColor = "red";
        dto.setColor(expectedColor);

        // Retrieve the color using getColor() method
        String actualColor = dto.getColor();

        // Assert that the retrieved color matches the expected color
        assertEquals(expectedColor, actualColor);
    }

    @Test
    void getBgcolor() {
        // Create a QrCodeColorDto instance
        QrCodeColorDto dto = new QrCodeColorDto();

        // Set a sample bgcolor
        String expectedBgcolor = "white";
        dto.setBgcolor(expectedBgcolor);

        // Retrieve the bgcolor using getBgcolor() method
        String actualBgcolor = dto.getBgcolor();

        // Assert that the retrieved bgcolor matches the expected bgcolor
        assertEquals(expectedBgcolor, actualBgcolor);
    }

    @Test
    void setBgcolor() {
        // Create a QrCodeColorDto instance
        QrCodeColorDto dto = new QrCodeColorDto();

        // Set a sample bgcolor
        String expectedBgcolor = "white";
        dto.setBgcolor(expectedBgcolor);

        // Retrieve the bgcolor using getBgcolor() method
        String actualBgcolor = dto.getBgcolor();

        // Assert that the retrieved bgcolor matches the expected bgcolor
        assertEquals(expectedBgcolor, actualBgcolor);
    }
}