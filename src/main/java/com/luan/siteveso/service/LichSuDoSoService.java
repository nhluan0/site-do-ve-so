package com.luan.siteveso.service;

import com.luan.siteveso.entity.KetQuaXoSo;
import com.luan.siteveso.entity.LichSuDoSo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LichSuDoSoService {
    // delete
    Boolean delete(LichSuDoSo lichSuDoSo);

    // delete theo mang truyen vao
    Boolean deleteByList(List<LichSuDoSo> lichSuDoSoList);

    // lay danh sach Lich su do so theo pagination
    Page<LichSuDoSo> getLichSuDoSo(int numPage, int size);
    // delete theo id
    Boolean deleteById(int id);

    // xoa nhieu doi tuong theo mang id truyen vao
    Boolean deletesByArrId(Integer[] arr);

    // lay danh sach lich su do so theo username
    List<LichSuDoSo> getLichSuDoSoByUserName(String userName);

    // lay lich su do so theo ten dang nhap facebook , google
    List<LichSuDoSo> getLichSuDoSoBySocialDesc(String social);
}
