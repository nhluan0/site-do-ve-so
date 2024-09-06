package com.luan.siteveso.dao;

import com.luan.siteveso.entity.LichSuSua;
import com.luan.siteveso.entity.User;
import com.luan.siteveso.service.LichSuSuaService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LichSuSuaRepository extends JpaRepository<LichSuSua,Integer> {
    // get danh sach LichSuSua theo idkq truyen vao
    List<LichSuSua> findByIdKq(int idkq);

    // lay danh sach lichsusua theo user
    List<LichSuSua> findByUser(User user);
}
