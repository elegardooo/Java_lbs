package com.lagodich.textqrconvertor.controller;

import com.lagodich.textqrconvertor.service.TextQrConvertorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/qr-code")
public class TextQrConvertorController {
    private final TextQrConvertorService.QrTextService qrConvertorService;

    @Autowired
    public TextQrConvertorController(TextQrConvertorService.QrTextService qrConvertorService) {
        this.qrConvertorService = qrConvertorService;
    }

    @GetMapping(value = "", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> text(@RequestParam String text) {
        byte[] qrCode = qrConvertorService.qrCode(text);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(qrCode);
    }

}


