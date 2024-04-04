package com.lagodich.textqrconvertor.dto;

import com.lagodich.textqrconvertor.entity.QrCode;
import java.util.List;

public class QrCodeDto {
  private Long id;
  private String content;
  private String size;
  private List<QrCodeColorDto> colors;

  public static QrCodeDto toModel(QrCode entity) {
    QrCodeDto model = new QrCodeDto();
    model.setId(entity.getId());
    model.setContent(entity.getContent());
    model.setSize(entity.getSize());
    model.setColors(entity.getColors().stream().map(QrCodeColorDto::toModel).toList());
    return model;
  }

  public static QrCode toEntity(QrCodeDto qrCodeDto) {
    QrCode qrCode = new QrCode();
    qrCode.setId(qrCodeDto.getId());
    qrCode.setContent(qrCodeDto.getContent());
    qrCode.setSize(qrCodeDto.getSize());
    qrCode.setColors(null);
    return qrCode;
  }

  public QrCodeDto() { //def
  }

  public List<QrCodeColorDto> getColors() {
    return colors;
  }

  public void setColors(List<QrCodeColorDto> colors) {
    this.colors = colors;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }
}
