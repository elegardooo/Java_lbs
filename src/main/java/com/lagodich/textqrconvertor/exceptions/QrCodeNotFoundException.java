package com.lagodich.textqrconvertor.exceptions;

public class QrCodeNotFoundException extends Exception {
  public QrCodeNotFoundException(String code) {
    super(code + "QrCode not found");
  }
}
