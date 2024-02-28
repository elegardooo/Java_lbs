package com.lagodich.textqrconvertor.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class TextQrConvertorService {

    private TextQrConvertorService() {}
    @Value("${external.api-key.QrCode}")
    private String apiKey;

    @Value("${external.api.urlQrCode}")
    private String baseApiUrl;
    private static final String BEARER_HEADER = "Bearer %s";
    private static final String BEARER_HEADER_AUTH_TYPE = "Authorization";

    @Component
    public  class QrTextService {
        public byte[] qrCode(String qrCodeContent) {
            HttpHeaders headers = new HttpHeaders();
            headers.set(BEARER_HEADER_AUTH_TYPE, BEARER_HEADER.formatted(apiKey));
            return WebClient.create(baseApiUrl + qrCodeContent)
                    .get()
                    .accept(MediaType.IMAGE_PNG)
                    .headers(h -> h.addAll(headers))
                    .retrieve()
                    .bodyToMono(byte[].class)
                    .block();
        }
    }
}
