package com.lagodich.textqrconvertor.dto;

import com.lagodich.textqrconvertor.entity.QrCodeColor;

public class QrCodeColorDto {
    private Long id;
    private String color;
    private String bgcolor;

    public static QrCodeColorDto toModel(QrCodeColor entity) {
        QrCodeColorDto model = new QrCodeColorDto();
        model.setId(entity.getId());
        model.setColor(entity.getColor());
        model.setBgcolor(entity.getBgcolor());
        return model;
    }

    public QrCodeColorDto() { //def
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
