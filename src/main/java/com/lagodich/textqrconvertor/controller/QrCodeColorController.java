package com.lagodich.textqrconvertor.controller;

import com.lagodich.textqrconvertor.cache.ResponseCache;
import com.lagodich.textqrconvertor.dto.QrCodeColorDto;
import com.lagodich.textqrconvertor.entity.QrCodeColor;
import com.lagodich.textqrconvertor.exceptions.QrCodeAlreadyExistException;
import com.lagodich.textqrconvertor.exceptions.QrCodeNotFoundException;
import com.lagodich.textqrconvertor.service.QrCodeColorService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/v1/qr-code/colors")
public class QrCodeColorController {

  private final QrCodeColorService qrCodeColorService;
  private final ResponseCache responseCache;

  @Autowired
  public QrCodeColorController(QrCodeColorService qrCodeColorService, ResponseCache responseCache) {
    this.qrCodeColorService = qrCodeColorService;
    this.responseCache = responseCache;
  }

  @PostMapping(value = "/")
    public <T> ResponseEntity<T> createColors(@RequestBody QrCodeColor colors,
                                       @RequestParam Long qrCodeId) throws QrCodeAlreadyExistException {
    log.info("POST endpoint /api/v1/qr-code/colors/ was called");
    return (ResponseEntity<T>) ResponseEntity.ok(qrCodeColorService.createColors(colors, qrCodeId));
  }

  @GetMapping(value = "/{id}")
    public <T> ResponseEntity<T> getColors(@PathVariable Long id) throws QrCodeNotFoundException {
    log.info("GET endpoint /api/v1/qr-code/colors/{id} was called");
    QrCodeColorDto qrCodeColorDto = responseCache.getQrCodeColor(id);
    if (qrCodeColorDto != null) {
      log.info("Found qrCodeColorDto in cache");
      return (ResponseEntity<T>) ResponseEntity.ok(qrCodeColorDto);
    } else {
      log.info("Saving qrCodeColorDto in cache");
      qrCodeColorDto = qrCodeColorService.getColors(id);
      responseCache.saveQrCodeColor(id, qrCodeColorDto);
      return (ResponseEntity<T>) ResponseEntity.ok(qrCodeColorDto);
    }
  }

  @GetMapping(value = "/database/{colorId}")  //useful request
    public List<QrCodeColorDto> getColorByColorId(@PathVariable Long colorId) {
    log.info("GET endpoint /api/v1/qr-code/colors/database/{colorId} was called");
    return qrCodeColorService.getQrCodeColorById(colorId);
  }


  @PutMapping(value = "/")
    public <T> ResponseEntity<T> updateColors(@RequestBody QrCodeColor colors,
                                       @RequestParam Long id) throws QrCodeNotFoundException, QrCodeAlreadyExistException {
    log.info("PUT endpoint /api/v1/qr-code/colors/ was called");
    return (ResponseEntity<T>) ResponseEntity.ok(qrCodeColorService.updateColors(colors, id));
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<String> deleteColors(@PathVariable Long id) {
    log.info("DELETE endpoint /api/v1/qr-code/colors/{id} was called");
    responseCache.removeQrCodeColor(id);
    log.info("Color was removed from cache");
    qrCodeColorService.deleteColors(id);
    return ResponseEntity.ok("Success");
  }

}
