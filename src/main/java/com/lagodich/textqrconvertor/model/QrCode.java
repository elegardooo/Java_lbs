package com.lagodich.textqrconvertor.model;

import com.lagodich.textqrconvertor.entity.QrCodeEntity;

import java.util.List;

public class QrCode {
    private Long id;
    private String content;
    private String size;
    private List<QrCodeColor> colors;
    public static QrCode toModel(QrCodeEntity entity) {
        QrCode model = new QrCode();
        model.setId(entity.getId());
        model.setContent(entity.getContent());
        model.setSize(entity.getSize());
        model.setColors(entity.getColors().stream().map(QrCodeColor::toModel).toList());
        return model;
    }
    public QrCode() { //def
    }

    public List<QrCodeColor> getColors() {
        return colors;
    }

    public void setColors(List<QrCodeColor> colors) {
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
