package com.lagodich.textqrconvertor.repository;

import com.lagodich.textqrconvertor.entity.QrCodeColor;
import org.springframework.data.repository.CrudRepository;

public interface QrCodeColorRepo extends CrudRepository<QrCodeColor, Long> {
    QrCodeColor findByColor (String color);
    QrCodeColor findByBgcolor (String bgcolor);
}
