package com.lagodich.textqrconvertor.service;

import com.lagodich.textqrconvertor.dto.QrCodeDto;
import com.lagodich.textqrconvertor.entity.QrCode;
import com.lagodich.textqrconvertor.exceptions.QrCodeAlreadyExistException;
import com.lagodich.textqrconvertor.exceptions.QrCodeNotFoundException;
import com.lagodich.textqrconvertor.repository.QrCodeRepo;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class QrCodeService {

  private static final String QR_ERROR_MSG = "Qr code was not found";
  private final QrCodeRepo qrCodeRepo;

  public QrCodeService(QrCodeRepo qrCodeRepo) {
    this.qrCodeRepo = qrCodeRepo;
  }

  @Value("${external.api-key.QrCode}")
  private String apiKey;

  @Value("${external.api.urlQrCode}")
  private String baseApiUrl;
  private static final String BEARER_HEADER = "Bearer %s";
  private static final String BEARER_HEADER_AUTH_TYPE = "Authorization";

  @Component
  public  class QrTextService {
    public byte[] qrCode(String qrCodeContent, String qrCodeSize, String qrCodeColor, String qrCodeBgColor) {
      HttpHeaders headers = new HttpHeaders();
      headers.set(BEARER_HEADER_AUTH_TYPE, BEARER_HEADER.formatted(apiKey));
      return WebClient.create(baseApiUrl + "?data=" + qrCodeContent + "&size=" + qrCodeSize + "&color=" + qrCodeColor + "&bgcolor=" + qrCodeBgColor)
                    .get()
                    .accept(MediaType.IMAGE_PNG)
                    .headers(h -> h.addAll(headers))
                    .retrieve()
                    .bodyToMono(byte[].class)
                    .block();
    }
  }

  public QrCode createQrCode(QrCode qrCode) throws QrCodeAlreadyExistException {
    if (qrCodeRepo.findByContent(qrCode.getContent()) != null) {
      throw new QrCodeAlreadyExistException();
    }
    return qrCodeRepo.save(qrCode);
  }

  public List<QrCode> createQrCodes(List<QrCode> qrCodes) {
    Iterable<QrCode> savedQrCodesIterable = qrCodeRepo.saveAll(qrCodes);
    List<QrCode> qrCodeList = new ArrayList<>();
    StreamSupport.stream(savedQrCodesIterable.spliterator(), false)
            .forEach(qrCodeList::add);
    return qrCodeList;
  }

  public QrCodeDto getOneQrCode(Long id) throws QrCodeNotFoundException {
    if (qrCodeRepo.findById(id).isPresent()) {
      QrCode qrCode = qrCodeRepo.findById(id).orElse(null);
      if (qrCode == null) {
        throw new QrCodeNotFoundException(QR_ERROR_MSG);
      }
      return QrCodeDto.toModel(qrCode);
    }
    throw new QrCodeNotFoundException(QR_ERROR_MSG);
  }

  public Long deleteQrCode(Long id) {
    qrCodeRepo.deleteById(id);
    return id;
  }

  public QrCode updateQrCode(Long id, QrCode qrCode) throws QrCodeNotFoundException, QrCodeAlreadyExistException {
    if (qrCodeRepo.findById(id).isEmpty()) {
      throw new QrCodeNotFoundException(QR_ERROR_MSG);
    }
    if (qrCodeRepo.findByContent(qrCode.getContent()) != null) {
      throw new QrCodeAlreadyExistException();
    }
    QrCode updateQrCode = qrCodeRepo.findById(id).orElse(null);
    if (updateQrCode == null) {
      throw new QrCodeNotFoundException(QR_ERROR_MSG);
    }
    updateQrCode.setContent(qrCode.getContent());
    updateQrCode.setSize(qrCode.getSize());

    qrCodeRepo.save(updateQrCode);
    return updateQrCode;
  }

}
