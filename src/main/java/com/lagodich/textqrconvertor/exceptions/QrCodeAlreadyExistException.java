package com.lagodich.textqrconvertor.exceptions;

public class QrCodeAlreadyExistException extends Exception {
  public QrCodeAlreadyExistException() {
    super("This QrCode already exists");
  }
}
