package com.lagodich.textqrconvertor.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class QrCodeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String content;
    private String size;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "qrCode")
    private List<QrCodeColorEntity> colors;

    public QrCodeEntity() { //def
    }

    public List<QrCodeColorEntity> getColors() {
        return colors;
    }

    public void setColors(List<QrCodeColorEntity> colors) {
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