package com.lagodich.textqrconvertor.model;

import com.lagodich.textqrconvertor.entity.QrCodeColorEntity;

public class QrCodeColor {
    private Long id;
    private String color;
    private String bgcolor;

    public static QrCodeColor toModel(QrCodeColorEntity entity) {
        QrCodeColor model = new QrCodeColor();
        model.setId(entity.getId());
        model.setColor(entity.getColor());
        model.setBgcolor(entity.getBgcolor());
        return model;
    }

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
}
