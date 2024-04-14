package com.lagodich.textqrconvertor.service;

import com.lagodich.textqrconvertor.dto.QrCodeColorDto;
import com.lagodich.textqrconvertor.entity.QrCode;
import com.lagodich.textqrconvertor.entity.QrCodeColor;
import com.lagodich.textqrconvertor.repository.QrCodeColorRepo;
import com.lagodich.textqrconvertor.repository.QrCodeRepo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class QrCodeColorService {
  private final QrCodeRepo qrCodeRepo;
  private final QrCodeColorRepo qrCodeColorRepo;

  public QrCodeColorService(QrCodeRepo qrCodeRepo, QrCodeColorRepo qrCodeColorRepo) {
    this.qrCodeRepo = qrCodeRepo;
    this.qrCodeColorRepo = qrCodeColorRepo;
  }

  public QrCodeColorDto createColors(QrCodeColor color, Long qrCodeId) {
    QrCode qrCode = qrCodeRepo.findById(qrCodeId).orElse(null);
    color.setQrCode(qrCode);
    return QrCodeColorDto.toModel(qrCodeColorRepo.save(color));
  }

  public QrCodeColorDto getColors(Long id) {

    QrCodeColor qrCodeColor = qrCodeColorRepo.findById(id).orElse(null);
    return QrCodeColorDto.toModel(qrCodeColor);

  }

  public List<QrCodeColorDto> getQrCodeColorById(Long colorId) {
    List<QrCodeColorDto> qrCodeColorDto = new ArrayList<>();
    for (QrCodeColor qrCodeColor : qrCodeColorRepo.findQrCodeColorByColorId(colorId)) {
      qrCodeColorDto.add(QrCodeColorDto.toModel(qrCodeColor));
    }
    return qrCodeColorDto;
  }

  public QrCodeColorDto updateColors(QrCodeColor color, Long id) {
    QrCodeColor updateQrCodeColor = qrCodeColorRepo.findById(id).orElse(null);
      assert updateQrCodeColor != null;
      updateQrCodeColor.setColor(color.getColor());
    updateQrCodeColor.setBgcolor(color.getBgcolor());
    qrCodeColorRepo.save(updateQrCodeColor);
    return QrCodeColorDto.toModel(updateQrCodeColor);
  }

  public Long deleteColors(Long id) {
    qrCodeColorRepo.deleteById(id);
    return id;
  }
}
