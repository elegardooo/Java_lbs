package com.lagodich.textqrconvertor.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class QrCodeColor {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String color;
  private String bgcolor;

  @ManyToOne
  @JoinColumn(name = "qr_code_id")
  private QrCode qrCode;

  public QrCodeColor() { //def
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getBgcolor() {
    return bgcolor;
  }

  public void setBgcolor(String bgcolor) {
    this.bgcolor = bgcolor;
  }

  public QrCode getQrCode() {
    return qrCode;
  }

  public void setQrCode(QrCode qrCode) {
    this.qrCode = qrCode;
  }
}
