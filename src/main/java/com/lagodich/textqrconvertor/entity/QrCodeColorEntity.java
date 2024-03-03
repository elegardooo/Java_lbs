package com.lagodich.textqrconvertor.entity;

import jakarta.persistence.*;

@Entity
public class QrCodeColorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String color;
    private String bgcolor;

    @ManyToOne
    @JoinColumn(name = "qr_code_id")
    private QrCodeEntity qrCode;

    public QrCodeColorEntity() { //def
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

    public QrCodeEntity getQrCode() {
        return qrCode;
    }

    public void setQrCode(QrCodeEntity qrCode) {
        this.qrCode = qrCode;
    }
}
