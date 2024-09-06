package com.luan.siteveso.dao;

import com.luan.siteveso.entity.LichSuSua;
import com.luan.siteveso.entity.TienThuong;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TienThuongRepository extends JpaRepository<TienThuong,Integer> {
    TienThuong findByGiai(String giai);
}
