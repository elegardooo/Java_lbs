package com.lagodich.textqrconvertor.repository;

import com.lagodich.textqrconvertor.entity.QrCodeEntity;
import org.springframework.data.repository.CrudRepository;

public interface QrCodeRepo extends CrudRepository<QrCodeEntity, Long> {
    QrCodeEntity findByContent (String content);
    QrCodeEntity findBySize (String size);
}
