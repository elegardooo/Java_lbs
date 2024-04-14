package com.lagodich.textqrconvertor.entity;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class QrCodeTest {

    @Test
    void getColors() {
        // Create a QrCode entity instance
        QrCode qrCode = new QrCode();

        // Create a list of QrCodeColor objects
        List<QrCodeColor> expectedColors = new ArrayList<>();
        expectedColors.add(new QrCodeColor());
        expectedColors.add(new QrCodeColor());
        expectedColors.add(new QrCodeColor());

        // Set the list of QrCodeColor objects in the QrCode entity
        qrCode.setColors(expectedColors);

        // Retrieve the list of QrCodeColor objects using getColors() method
        List<QrCodeColor> actualColors = qrCode.getColors();

        // Assert that the retrieved list of QrCodeColor objects matches the expected list
        assertEquals(expectedColors, actualColors);
    }

    @Test
    void setColors() {
        // Create a QrCode entity instance
        QrCode qrCode = new QrCode();

        // Create a list of QrCodeColor objects
        List<QrCodeColor> expectedColors = new ArrayList<>();
        expectedColors.add(new QrCodeColor());
        expectedColors.add(new QrCodeColor());
        expectedColors.add(new QrCodeColor());

        // Set the list of QrCodeColor objects in the QrCode entity using setColors() method
        qrCode.setColors(expectedColors);

        // Retrieve the list of QrCodeColor objects using getColors() method
        List<QrCodeColor> actualColors = qrCode.getColors();

        // Assert that the retrieved list of QrCodeColor objects matches the expected list
        assertEquals(expectedColors, actualColors);
    }

    @Test
    void getContent() {
        // Create a QrCode entity instance
        QrCode qrCode = new QrCode();

        // Set a sample content
        String expectedContent = "Sample content";
        qrCode.setContent(expectedContent);

        // Retrieve the content using getContent() method
        String actualContent = qrCode.getContent();

        // Assert that the retrieved content matches the expected content
        assertEquals(expectedContent, actualContent);
    }

    @Test
    void setContent() {
        // Create a QrCode entity instance
        QrCode qrCode = new QrCode();

        // Set a sample content
        String expectedContent = "Sample content";
        qrCode.setContent(expectedContent);

        // Retrieve the content using getContent() method
        String actualContent = qrCode.getContent();

        // Assert that the retrieved content matches the expected content
        assertEquals(expectedContent, actualContent);
    }

    @Test
    void getSize() {
        // Create a QrCode entity instance
        QrCode qrCode = new QrCode();

        // Set a sample size
        String expectedSize = "Large";
        qrCode.setSize(expectedSize);

        // Retrieve the size using getSize() method
        String actualSize = qrCode.getSize();

        // Assert that the retrieved size matches the expected size
        assertEquals(expectedSize, actualSize);
    }

    @Test
    void setSize() {
        // Create a QrCode entity instance
        QrCode qrCode = new QrCode();

        // Set a sample size
        String expectedSize = "Large";
        qrCode.setSize(expectedSize);

        // Retrieve the size using getSize() method
        String actualSize = qrCode.getSize();

        // Assert that the retrieved size matches the expected size
        assertEquals(expectedSize, actualSize);
    }
}