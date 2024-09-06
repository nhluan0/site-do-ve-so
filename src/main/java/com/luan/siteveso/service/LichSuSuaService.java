package com.luan.siteveso.service;

import com.luan.siteveso.entity.LichSuSua;
import org.springframework.stereotype.Service;

import java.util.List;

public interface LichSuSuaService {
    // lay thong tin theo id ket qua xo so truyen vao
    List<LichSuSua> getLichSuSuaByIdKq(int id);

    // xóa 1 thực thể Lịch sử sửa
    Boolean deleteByLichSuSua(LichSuSua lichSuSua);
    // xoa nhieu thuc the Lich Su sua
    Boolean deleteListLichSuSua(List<LichSuSua> lichSuSuaList);

    // xóa phần tử trong bảng theo id kq xổ số truyền vào
    Boolean deleteByIdKqXs(int id);

    // luu 1 entity lich su sua vao database
    LichSuSua save(LichSuSua lichSuSua);
}
