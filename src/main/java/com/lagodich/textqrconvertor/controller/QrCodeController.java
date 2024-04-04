package com.lagodich.textqrconvertor.controller;

import com.lagodich.textqrconvertor.dto.QrCodeDto;
import com.lagodich.textqrconvertor.entity.QrCode;
import com.lagodich.textqrconvertor.exceptions.QrCodeAlreadyExistException;
import com.lagodich.textqrconvertor.exceptions.QrCodeNotFoundException;
import com.lagodich.textqrconvertor.service.QrCodeService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/qr-code")
public class QrCodeController {

  private static final String ERROR_MSG = "There's error";
  private final QrCodeService.QrTextService qrConvertorService;
  private final QrCodeService qrCodeService;

  @Autowired
    public QrCodeController(QrCodeService.QrTextService qrConvertorService, QrCodeService qrCodeService) {
    this.qrConvertorService = qrConvertorService;
    this.qrCodeService = qrCodeService;
  }

  @GetMapping(value = "", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> text(@RequestParam String text, @RequestParam String size, @RequestParam String color, @RequestParam String bgcolor) {
    log.info("GET endpoint /api/v1/qr-code was called");
    byte[] qrCode = qrConvertorService.qrCode(text, size, color, bgcolor);
    return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(qrCode);
  }

  @PostMapping(value = "/")
    public ResponseEntity<String> createQrCode(@RequestBody QrCode qrCode) {
    log.info("POST endpoint /api/v1/qr-code/ was called");
    try {
      qrCodeService.createQrCode(qrCode);
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

  @PostMapping(value = "/add/list")
    public <T> ResponseEntity<T> createQrCodes(@RequestBody List<QrCode> qrCodes) {
    log.info("POST endpoint /api/v1/qr-code/add/list was called");
    qrCodes = qrCodes.stream()
            .toList();
    List<QrCode> createdQrCodes = qrCodeService.createQrCodes(qrCodes);
    return (ResponseEntity<T>) ResponseEntity
            .ok()
            .body(createdQrCodes);
  }

  @GetMapping(value = "/")
    public <T> ResponseEntity<T> getOneQrCode(@RequestParam Long id) {
    log.info("GET endpoint /api/v1/qr-code/ was called");
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
    log.info("DELETE endpoint /api/v1/qr-code/{id} was called");
    try {
      return (ResponseEntity<T>) ResponseEntity.ok(qrCodeService.deleteQrCode(id));
    } catch (Exception e) {
      return (ResponseEntity<T>) ResponseEntity
                    .badRequest()
                    .body(ERROR_MSG);
    }
  }

  @PutMapping(value = "/{id}")
    public <T> ResponseEntity<T> updateQrCode(@PathVariable Long id, @RequestBody QrCode qrCode) {
    log.info("PUT endpoint /api/v1/qr-code/{id} was called");
    try {
      return (ResponseEntity<T>) ResponseEntity.ok(qrCodeService.updateQrCode(id, qrCode));
    } catch (QrCodeNotFoundException | QrCodeAlreadyExistException e) {
      return (ResponseEntity<T>) ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
    }
  }

}


