package com.luan.siteveso.service;

import com.luan.siteveso.entity.DaiXoSo;

import java.util.List;

public interface DaiVeSoService {
    // lay fu lieu theo id dai ve so truyen vao
    DaiXoSo getById(int id);

    // lay tat ca du lieu dai
    List<DaiXoSo> getAll();

    // lay Dai ve theo ten dai truyen vao
    List<DaiXoSo> getDaiVeSoByTenDai(String tenDai);

    // lay Dai ve so theo khu vuc
    List<DaiXoSo> getDaiVeSoByKhuVuc(String khuVuc);

    // xoa 1 dai ve so theo id truyen vao
    Boolean deleteDaiVeSoById(int id);


    // xoa nhieu dai ve so theo mang id truyen vao
    Boolean deleteDaiVeSosByArrayId(int[] arrId);

    // luu 1 dai ve so
    DaiXoSo saveDaiXoSo(DaiXoSo daiXoSo);

    // select DISTINCT theo ten Khu vuc
    List<String> getDistinctByKhuVuc();
}
