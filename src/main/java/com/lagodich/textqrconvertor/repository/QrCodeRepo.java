package com.lagodich.textqrconvertor.repository;

import com.lagodich.textqrconvertor.entity.QrCode;
import org.springframework.data.repository.CrudRepository;

public interface QrCodeRepo extends CrudRepository<QrCode, Long> {
    QrCode findByContent (String content);
    QrCode findBySize (String size);
}
