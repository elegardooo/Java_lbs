package com.lagodich.textqrconvertor.cache;

import com.lagodich.textqrconvertor.dto.QrCodeColorDto;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ResponseCache {
    private final Map<Long, QrCodeColorDto> qrCodeColorDtoMap = new ConcurrentHashMap<>();

    public void saveQrCodeColor(Long key, QrCodeColorDto value) {
        qrCodeColorDtoMap.put(key, value);
    }

    public QrCodeColorDto getQrCodeColor(Long key) {
        return qrCodeColorDtoMap.get(key);
    }

    public void removeQrCodeColor(Long key) {
        qrCodeColorDtoMap.remove(key);
    }

    public void clearQrCodeColor() {
        qrCodeColorDtoMap.clear();
    }

    public int getSizeOfQrCodeColor() {
        return qrCodeColorDtoMap.size();
    }

}
