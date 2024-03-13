package com.lagodich.textqrconvertor.controller;

import com.lagodich.textqrconvertor.entity.QrCode;
import com.lagodich.textqrconvertor.exceptions.QrCodeAlreadyExistException;
import com.lagodich.textqrconvertor.exceptions.QrCodeNotFoundException;
import com.lagodich.textqrconvertor.service.QrCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/qr-code")
public class QrCodeController {

    private static final String ERROR_MSG = "There's error";
    private final QrCodeService.QrTextService qrConvertorService;
    private final QrCodeService qrCodeService;

    @Autowired
    public QrCodeController(QrCodeService.QrTextService qrConvertorService, QrCodeService qrCodeService){
        this.qrConvertorService = qrConvertorService;
        this.qrCodeService = qrCodeService;
    }

    @GetMapping(value = "", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> text(@RequestParam String text, @RequestParam String size, @RequestParam String color, @RequestParam String bgcolor) {
        byte[] qrCode = qrConvertorService.qrCode(text, size, color, bgcolor);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(qrCode);
    }

    @PostMapping(value = "/")
    public ResponseEntity<String> registration(@RequestBody QrCode qrCode) {
        try {
            qrCodeService.registration(qrCode);
            return ResponseEntity
                    .ok()
                    .body("qrCode was successfully saved");
        } catch (QrCodeAlreadyExistException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(ERROR_MSG);
        }
    }

    @GetMapping(value = "/")
    public <T> ResponseEntity<T> getOneQrCode(@RequestParam Long id) {
        try {
            return (ResponseEntity<T>) ResponseEntity.ok(qrCodeService.getOneQrCode(id));
        } catch (QrCodeNotFoundException e) {
            return (ResponseEntity<T>) ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        } catch (Exception e) {
            return (ResponseEntity<T>) ResponseEntity
                    .badRequest()
                    .body(ERROR_MSG);
        }
    }

    @DeleteMapping(value = "/{id}")
    public <T> ResponseEntity<T> deleteQrCode(@PathVariable Long id) {
        try {
            return (ResponseEntity<T>) ResponseEntity.ok(qrCodeService.deleteQrCode(id));
        } catch (Exception e) {
            return (ResponseEntity<T>) ResponseEntity
                    .badRequest()
                    .body(ERROR_MSG);
        }
    }

    @PutMapping(value = "/{id}")
    public <T>ResponseEntity<T> updateQrCode(@PathVariable Long id, @RequestBody QrCode qrCode) {
        try {
            return (ResponseEntity<T>) ResponseEntity.ok(qrCodeService.updateQrCode(id, qrCode));
        } catch (QrCodeNotFoundException | QrCodeAlreadyExistException e) {
            return (ResponseEntity<T>) ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

}


