package com.luan.siteveso.dao;

import com.luan.siteveso.entity.DaiXoSo;
import com.luan.siteveso.entity.KetQuaXoSo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface KetQuaXoSoRepository extends JpaRepository<KetQuaXoSo,Integer> {
    // lay danh sách dai xo so theo ngay xo so truyen vao
    List<KetQuaXoSo> findByDateLottery(Date dateSql);

    // lay ketquaxoso theo id dai truyen vao
    List<KetQuaXoSo> findByDaiXoSo(DaiXoSo daiXoSo);

}
