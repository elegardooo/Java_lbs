package com.lagodich.textqrconvertor.services;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class TextQrConvertorService {

    private TextQrConvertorService() {}
    private static final String API_KEY = "9e538d02fdmsh143dd83847c491fp1e9e2bjsn10018d801db522";
    private static final String BASE_API_URL = "http://api.qrserver.com/v1/create-qr-code/?data=";
    private static final String BEARER_HEADER = "Bearer %s";
    private static final String BEARER_HEADER_AUTH_TYPE = "Authorization";

    @Component
    public static class QrTextService {
        public byte[] qrCode(String qrCodeContent) {
            HttpHeaders headers = new HttpHeaders();
            headers.set(BEARER_HEADER_AUTH_TYPE, BEARER_HEADER.formatted(API_KEY));
            return WebClient.create(BASE_API_URL + qrCodeContent)
                    .get()
                    .accept(MediaType.IMAGE_PNG)
                    .headers(h -> h.addAll(headers))
                    .retrieve()
                    .bodyToMono(byte[].class)
                    .block();
        }
    }
}
