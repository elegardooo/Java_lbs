package com.lagodich.textqrconvertor.dto;

import org.junit.jupiter.api.Test;
import com.lagodich.textqrconvertor.entity.QrCode;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class QrCodeDtoTest {

    @Test
    void toModel() {
        // Create a QrCode entity instance
        QrCode entity = new QrCode();
        entity.setId(1L);
        entity.setContent("Sample content");
        entity.setSize("Large");

        // Create a list of QrCodeColorDto objects
        List<QrCodeColorDto> colorDtoList = new ArrayList<>();
        colorDtoList.add(new QrCodeColorDto());
        colorDtoList.add(new QrCodeColorDto());

        // Call the toModel() method
        QrCodeDto dto = QrCodeDto.toModel(entity);

        // Set the list of QrCodeColorDto objects in the QrCodeDto
        dto.setColors(colorDtoList);

        // Assert that the returned dto is not null
        assertNotNull(dto);

        // Assert that the properties are correctly mapped
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getContent(), dto.getContent());
        assertEquals(entity.getSize(), dto.getSize());
        assertEquals(colorDtoList.size(), dto.getColors().size());
    }

    @Test
    void toEntity() {
        // Create a QrCodeDto object with sample data
        QrCodeDto dto = new QrCodeDto();
        dto.setId(1L);
        dto.setContent("Sample content");
        dto.setSize("Large");

        // Call the toEntity() method
        QrCode entity = QrCodeDto.toEntity(dto);

        // Assert that the returned entity is not null
        assertNotNull(entity);

        // Assert that the properties are correctly mapped
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getContent(), entity.getContent());
        assertEquals(dto.getSize(), entity.getSize());
    }

    @Test
    void getColors() {
        // Create a list of QrCodeColorDto objects
        List<QrCodeColorDto> expectedColors = new ArrayList<>();
        expectedColors.add(new QrCodeColorDto());
        expectedColors.add(new QrCodeColorDto());

        // Create a QrCodeDto instance and set the colors
        QrCodeDto dto = new QrCodeDto();
        dto.setColors(expectedColors);

        // Retrieve the colors using getColors() method
        List<QrCodeColorDto> actualColors = dto.getColors();

        // Assert that the retrieved colors match the expected colors
        assertEquals(expectedColors.size(), actualColors.size());
        assertEquals(expectedColors, actualColors);
    }

    @Test
    void setColors() {
        // Create a list of QrCodeColorDto objects
        List<QrCodeColorDto> expectedColors = new ArrayList<>();
        expectedColors.add(new QrCodeColorDto());
        expectedColors.add(new QrCodeColorDto());

        // Create a QrCodeDto instance
        QrCodeDto dto = new QrCodeDto();

        // Set the colors using setColors() method
        dto.setColors(expectedColors);

        // Retrieve the colors using getColors() method
        List<QrCodeColorDto> actualColors = dto.getColors();

        // Assert that the retrieved colors match the expected colors
        assertEquals(expectedColors.size(), actualColors.size());
        assertEquals(expectedColors, actualColors);
    }

    @Test
    void getId() {
        // Create a QrCodeDto instance
        QrCodeDto dto = new QrCodeDto();

        // Set a sample id
        Long expectedId = 123L;
        dto.setId(expectedId);

        // Retrieve the id using getId() method
        Long actualId = dto.getId();

        // Assert that the retrieved id matches the expected id
        assertEquals(expectedId, actualId);
    }

    @Test
    void setId() {
        // Create a QrCodeDto instance
        QrCodeDto dto = new QrCodeDto();

        // Set a sample id
        Long expectedId = 123L;
        dto.setId(expectedId);

        // Retrieve the id using getId() method
        Long actualId = dto.getId();

        // Assert that the retrieved id matches the expected id
        assertEquals(expectedId, actualId);
    }

    @Test
    void getContent() {
        // Create a QrCodeDto instance
        QrCodeDto dto = new QrCodeDto();

        // Set a sample content
        String expectedContent = "Sample content";
        dto.setContent(expectedContent);

        // Retrieve the content using getContent() method
        String actualContent = dto.getContent();

        // Assert that the retrieved content matches the expected content
        assertEquals(expectedContent, actualContent);
    }

    @Test
    void setContent() {
        // Create a QrCodeDto instance
        QrCodeDto dto = new QrCodeDto();

        // Set a sample content
        String expectedContent = "Sample content";
        dto.setContent(expectedContent);

        // Retrieve the content using getContent() method
        String actualContent = dto.getContent();

        // Assert that the retrieved content matches the expected content
        assertEquals(expectedContent, actualContent);
    }

    @Test
    void getSize() {
        // Create a QrCodeDto instance
        QrCodeDto dto = new QrCodeDto();

        // Set a sample size
        String expectedSize = "Large";
        dto.setSize(expectedSize);

        // Retrieve the size using getSize() method
        String actualSize = dto.getSize();

        // Assert that the retrieved size matches the expected size
        assertEquals(expectedSize, actualSize);
    }

    @Test
    void setSize() {
        // Create a QrCodeDto instance
        QrCodeDto dto = new QrCodeDto();

        // Set a sample size
        String expectedSize = "Large";
        dto.setSize(expectedSize);

        // Retrieve the size using getSize() method
        String actualSize = dto.getSize();

        // Assert that the retrieved size matches the expected size
        assertEquals(expectedSize, actualSize);
    }
}