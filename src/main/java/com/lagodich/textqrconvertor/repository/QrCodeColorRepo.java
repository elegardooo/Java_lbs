package com.lagodich.textqrconvertor.repository;

import com.lagodich.textqrconvertor.entity.QrCodeColor;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface QrCodeColorRepo extends CrudRepository<QrCodeColor, Long> {
  QrCodeColor findByColor(String color);

  QrCodeColor findByBgcolor(String bgcolor);

  @Query("SELECT qcc FROM QrCodeColor qcc WHERE qcc.qrCode.id = :colorId")
  List<QrCodeColor> findQrCodeColorByColorId(@Param("colorId") Long colorId);

}
