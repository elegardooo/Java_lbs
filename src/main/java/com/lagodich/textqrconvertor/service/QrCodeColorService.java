package com.lagodich.textqrconvertor.service;

import com.lagodich.textqrconvertor.entity.QrCodeColorEntity;
import com.lagodich.textqrconvertor.entity.QrCodeEntity;
import com.lagodich.textqrconvertor.exceptions.QrCodeAlreadyExistException;
import com.lagodich.textqrconvertor.exceptions.QrCodeNotFoundException;
import com.lagodich.textqrconvertor.model.QrCodeColor;
import com.lagodich.textqrconvertor.repository.QrCodeColorRepo;
import com.lagodich.textqrconvertor.repository.QrCodeRepo;
import org.springframework.stereotype.Service;

@Service
public class QrCodeColorService {
    private static final String QR_ERROR_MSG = "Qr code was not found";
    private final QrCodeRepo qrCodeRepo;
    private final QrCodeColorRepo qrCodeColorRepo;

    public QrCodeColorService(QrCodeRepo qrCodeRepo, QrCodeColorRepo qrCodeColorRepo) {
        this.qrCodeRepo = qrCodeRepo;
        this.qrCodeColorRepo = qrCodeColorRepo;
    }

    public QrCodeColor createColors(QrCodeColorEntity color, Long qrCodeId) throws QrCodeAlreadyExistException {
        if(qrCodeColorRepo.findByColor(color.getColor()) != null &&
                qrCodeColorRepo.findByBgcolor(color.getBgcolor()) != null) {
            throw new QrCodeAlreadyExistException("Qr code with this colors already exist");
        }
        QrCodeEntity qrCode = qrCodeRepo.findById(qrCodeId).get();
        color.setQrCode(qrCode);
        return QrCodeColor.toModel(qrCodeColorRepo.save(color));
    }

    public QrCodeColor getColors(Long id) throws QrCodeNotFoundException {
        QrCodeColorEntity qrCodeColor = qrCodeColorRepo.findById(id).get();
        if(qrCodeColor == null) {
            throw new QrCodeNotFoundException(QR_ERROR_MSG);
        }
        return QrCodeColor.toModel(qrCodeColor);

    }
    public QrCodeColor updateColors(QrCodeColorEntity color, Long id) throws QrCodeAlreadyExistException {
        if(qrCodeColorRepo.findByColor(color.getColor()) != null &&
                qrCodeColorRepo.findByBgcolor(color.getBgcolor()) != null) {
            throw new QrCodeAlreadyExistException("Qr code with this colors already exist");
        }
        QrCodeColorEntity updateQrCodeColor = qrCodeColorRepo.findById(id).get();
        updateQrCodeColor.setColor(color.getColor());
        updateQrCodeColor.setBgcolor(color.getBgcolor());
        qrCodeColorRepo.save(updateQrCodeColor);
        return QrCodeColor.toModel(updateQrCodeColor);
    }

    public Long deleteColors(Long id) {
        qrCodeColorRepo.deleteById(id);
        return id;
    }
}
