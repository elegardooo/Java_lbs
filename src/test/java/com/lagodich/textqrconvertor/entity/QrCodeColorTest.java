package com.lagodich.textqrconvertor.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QrCodeColorTest {

    @Test
    void getQrCode() {
        // Create a QrCodeColor entity instance
        QrCodeColor qrCodeColor = new QrCodeColor();

        // Create a sample QrCode entity
        QrCode qrCode = new QrCode();
        qrCode.setId(1L);

        // Set the QrCode in the QrCodeColor entity
        qrCodeColor.setQrCode(qrCode);

        // Retrieve the QrCode using getQrCode() method
        QrCode retrievedQrCode = qrCodeColor.getQrCode();

        // Assert that the retrieved QrCode is not null
        assertNotNull(retrievedQrCode);

        // Assert that the retrieved QrCode has the correct ID
        assertNotNull(retrievedQrCode.getId());
        assertEquals(qrCode.getId(), retrievedQrCode.getId());
    }

    @Test
    void setQrCode() {
        // Create a QrCodeColor entity instance
        QrCodeColor qrCodeColor = new QrCodeColor();

        // Create a sample QrCode entity
        QrCode qrCode = new QrCode();
        qrCode.setId(1L);

        // Set the QrCode in the QrCodeColor entity
        qrCodeColor.setQrCode(qrCode);

        // Retrieve the QrCode using getQrCode() method
        QrCode retrievedQrCode = qrCodeColor.getQrCode();

        // Assert that the retrieved QrCode is not null
        assertNotNull(retrievedQrCode);

        // Assert that the retrieved QrCode has the correct ID
        assertEquals(qrCode.getId(), retrievedQrCode.getId());
    }
}