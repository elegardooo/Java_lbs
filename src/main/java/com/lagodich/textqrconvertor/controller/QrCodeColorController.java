package com.lagodich.textqrconvertor.controller;

import com.lagodich.textqrconvertor.entity.QrCodeColor;
import com.lagodich.textqrconvertor.service.QrCodeColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/qr-code/colors")
public class QrCodeColorController {

    private static final String ERROR_MSG = "There's error";

    private final QrCodeColorService qrCodeColorService;

    @Autowired
    public QrCodeColorController(QrCodeColorService qrCodeColorService) {
        this.qrCodeColorService = qrCodeColorService;
    }

    @PostMapping(value = "/")
    public <T> ResponseEntity <T> createColors(@RequestBody QrCodeColor colors,
                                       @RequestParam Long qrCodeId) {
        try {
            return (ResponseEntity<T>) ResponseEntity.ok(qrCodeColorService.createColors(colors, qrCodeId));
        } catch (Exception e) {
            return (ResponseEntity<T>) ResponseEntity
                    .badRequest()
                    .body(ERROR_MSG);
        }
    }

    @GetMapping(value="/")
    public <T> ResponseEntity <T> getColors(@RequestParam Long id) {
        try {
            return (ResponseEntity<T>) ResponseEntity.ok(qrCodeColorService.getColors(id));
        } catch (Exception e) {
            return (ResponseEntity<T>) ResponseEntity
                    .badRequest()
                    .body(ERROR_MSG);
        }
    }

    @PutMapping(value = "/")
    public <T> ResponseEntity <T> updateColors(@RequestBody QrCodeColor colors,
                                       @RequestParam Long id) {
        try {
            return (ResponseEntity<T>) ResponseEntity.ok(qrCodeColorService.updateColors(colors, id));
        } catch (Exception e) {
            return (ResponseEntity<T>) ResponseEntity
                    .badRequest()
                    .body(ERROR_MSG);
        }
    }

    @DeleteMapping(value = "/{id}")
    public <T> ResponseEntity <T> deleteColors(@PathVariable Long id) {
        try {
            return (ResponseEntity<T>) ResponseEntity.ok(qrCodeColorService.deleteColors(id));
        } catch (Exception e) {
            return (ResponseEntity<T>) ResponseEntity
                    .badRequest()
                    .body(ERROR_MSG);
        }
    }

}
