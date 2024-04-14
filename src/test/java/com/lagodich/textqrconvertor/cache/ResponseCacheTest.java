package com.lagodich.textqrconvertor.cache;

import com.lagodich.textqrconvertor.dto.QrCodeColorDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ResponseCacheTest {

    private ResponseCache responseCache;
    private Map<Long, QrCodeColorDto> testMap;

    @BeforeEach
    void setUp() {
        testMap = new HashMap<>();
        responseCache = new ResponseCache();
    }

    @Test
    void testSaveQrCodeColor() {
        Long key = 1L;
        QrCodeColorDto value = new QrCodeColorDto();
        responseCache.saveQrCodeColor(key, value);
        assertEquals(value, testMap.get(key));
    }

    @Test
    void testGetQrCodeColor() {
        Long key = 1L;
        QrCodeColorDto value = new QrCodeColorDto();
        testMap.put(key, value);
        assertEquals(value, responseCache.getQrCodeColor(key));
    }

    @Test
    void testRemoveQrCodeColor() {
        Long key = 1L;
        QrCodeColorDto value = new QrCodeColorDto();
        testMap.put(key, value);
        responseCache.removeQrCodeColor(key);
        assertEquals(null, testMap.get(key));
    }

    @Test
    void testClearQrCodeColor() {
        Long key = 1L;
        QrCodeColorDto value = new QrCodeColorDto();
        testMap.put(key, value);
        responseCache.clearQrCodeColor();
        assertEquals(0, testMap.size());
    }

    @Test
    void testGetSizeOfQrCodeColor() {
        int size = 5;
        for (int i = 0; i < size; i++) {
            testMap.put((long) i, new QrCodeColorDto());
        }
        assertEquals(size, responseCache.getSizeOfQrCodeColor());
    }
}
