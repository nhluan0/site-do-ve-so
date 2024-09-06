package com.luan.siteveso.dao;

import com.luan.siteveso.entity.DaiXoSo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DaiXoSoRepository extends JpaRepository<DaiXoSo,Integer> {
    // lay thong tin Dai So Xo theo ten dai da truyen vao
    DaiXoSo findByNameLottery(String nameDai);

    // lay da ta theo khu vuc
    List<DaiXoSo> findByAria(String khuVuc);
}
