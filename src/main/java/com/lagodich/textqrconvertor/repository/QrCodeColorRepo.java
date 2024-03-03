package com.lagodich.textqrconvertor.repository;

import com.lagodich.textqrconvertor.entity.QrCodeColorEntity;
import org.springframework.data.repository.CrudRepository;

public interface QrCodeColorRepo extends CrudRepository<QrCodeColorEntity, Long> {
    QrCodeColorEntity findByColor (String color);
    QrCodeColorEntity findByBgcolor (String bgcolor);
}
