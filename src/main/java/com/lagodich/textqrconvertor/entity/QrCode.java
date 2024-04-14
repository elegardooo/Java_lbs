package com.lagodich.textqrconvertor.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class QrCode {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String content;
  private String size;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "qrCode")
  private List<QrCodeColor> colors = new ArrayList<>(); // Initialize the list

  public QrCode() {
    // Initialize the colors list if needed
    if (colors == null) {
      colors = new ArrayList<>();
    }
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