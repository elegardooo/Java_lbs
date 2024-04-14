package com.lagodich.textqrconvertor.cache;

import org.junit.jupiter.api.Test;
import com.lagodich.textqrconvertor.dto.QrCodeColorDto;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
}