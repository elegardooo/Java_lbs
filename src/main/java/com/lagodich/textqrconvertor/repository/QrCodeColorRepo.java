package com.lagodich.textqrconvertor.repository;

import com.lagodich.textqrconvertor.entity.QrCodeColor;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface QrCodeColorRepo extends CrudRepository<QrCodeColor, Long> {
  QrCodeColor findByColor(String color);

  QrCodeColor findByBgcolor(String bgcolor);

  @Query(value = "SELECT * FROM Qr_Code_Color qcc WHERE qcc.qr_Code_id = :colorId", nativeQuery = true)
  List<QrCodeColor> findQrCodeColorByColorId(@Param("colorId") Long colorId);

}
