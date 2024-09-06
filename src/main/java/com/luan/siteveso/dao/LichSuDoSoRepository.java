package com.luan.siteveso.dao;

import com.luan.siteveso.entity.KetQuaXoSo;
import com.luan.siteveso.entity.LichSuDoSo;
import com.luan.siteveso.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LichSuDoSoRepository extends JpaRepository<LichSuDoSo,Integer> {
    // lay lich su do theo id_user
    List<LichSuDoSo> findByUser(User user);

    // lay lich su do so theo ketqua xo so
    List<LichSuDoSo> findByKetQuaXoSo(KetQuaXoSo ketQuaXoSo);

}
