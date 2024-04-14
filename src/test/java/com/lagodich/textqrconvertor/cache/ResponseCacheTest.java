package com.lagodich.textqrconvertor.cache;

import org.junit.jupiter.api.Test;
import com.lagodich.textqrconvertor.dto.QrCodeColorDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ResponseCacheTest {

    @Test
    void saveQrCodeColor() {
        // Create an instance of ResponseCache
        ResponseCache responseCache = new ResponseCache();

        // Create test data
        Long key = 1L;
        QrCodeColorDto value = new QrCodeColorDto();

        // Call the saveQrCodeColor method
        responseCache.saveQrCodeColor(key, value);

        // Assert that the value is stored correctly
        assertEquals(value, responseCache.getQrCodeColor(key));
    }

    @Test
    void removeQrCodeColor() {
        // Create an instance of ResponseCache
        ResponseCache responseCache = new ResponseCache();

        // Create test data
        Long key = 1L;
        QrCodeColorDto value = new QrCodeColorDto();

        // Save test data
        responseCache.saveQrCodeColor(key, value);

        // Remove the saved value
        responseCache.removeQrCodeColor(key);

        // Retrieve the removed value
        QrCodeColorDto removedValue = responseCache.getQrCodeColor(key);

        // Assert that the removed value is null
        assertNull(removedValue);
    }

    @Test
    void clearQrCodeColor() {
        // Create an instance of ResponseCache
        ResponseCache responseCache = new ResponseCache();

        // Add some test data
        responseCache.saveQrCodeColor(1L, new QrCodeColorDto());
        responseCache.saveQrCodeColor(2L, new QrCodeColorDto());
        responseCache.saveQrCodeColor(3L, new QrCodeColorDto());

        // Clear the cache
        responseCache.clearQrCodeColor();

        // Assert that the cache is empty
        assertEquals(0, responseCache.getSizeOfQrCodeColor());
    }

    @Test
    void getSizeOfQrCodeColor() {
        // Create an instance of ResponseCache
        ResponseCache responseCache = new ResponseCache();

        // Add some test data
        responseCache.saveQrCodeColor(1L, new QrCodeColorDto());
        responseCache.saveQrCodeColor(2L, new QrCodeColorDto());
        responseCache.saveQrCodeColor(3L, new QrCodeColorDto());

        // Get the size of the cache
        int size = responseCache.getSizeOfQrCodeColor();

        // Assert that the size is as expected
        assertEquals(3, size);
    }
}