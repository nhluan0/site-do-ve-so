package com.luan.siteveso.service;

import com.luan.siteveso.entity.KetQuaXoSo;
import org.springframework.data.domain.Page;

import java.sql.Date;
import java.util.List;

public interface KetQuaXoSoService {

    // tim ket qua xo so theo id
    KetQuaXoSo getKetQuaXoSoById(int id);

    // lay tat ca thong tin ket qua
    List<KetQuaXoSo> getAllKetQuaXoSo();

    // lay thong tin admin tao ket qua theo id bang ket qua ve so
    KetQuaXoSo getUserForKetQuaXoSoById(int id);

    // lay danh sach bang ket qua theo pagination
    Page<KetQuaXoSo> getKetQuaXoSo(int numPage, int size);
    // xoa 1 ket theo id truyen vao
    Boolean deleteById(int id);

    // xoa nhieu doi tuong theo mang id truyen vao
    Boolean deletesByArrId(Integer[] arr);

    // luu 1 ket qua xo so
    KetQuaXoSo save(KetQuaXoSo ketQuaXoSo);

    // lấy 8 kết quả xổ số theo tên đài truyền vào, sap xep theo  ngay xo so thu tu giảm dần
    List<KetQuaXoSo> get8KetQuaXoSoByTenDai(String tenDai, int pageNumber,int pageSize);

    // đếm có bao nhiêu kết quả xổ số theo tên đài truyền vào
    int countKqXsByTenDai(String tenDai);

    // lấy kết quả xổ số theo tên đài và ngày truyền vào
    List<KetQuaXoSo> getKqXsByTenDaiAndDateXs(String tenDai, Date date);

    // phan lay ket qua xo so cho trang chu
    // lay list ngay xo so moi nhat
    List<Date> getDateByNumberParamPasses(Integer numQuantity);
    // lay ngay kqxs theo khu vuc truyen vao
    List<Date> getDateKqXsByKhuVuc(String aria, int size);
    // lay kqXs khi lan dau load trang chu , so luong ket qua theo tham so truyen vao
    List<List<KetQuaXoSo>> getListKqXsForPageHomeByNumPass(Integer numQuantity);

    // lay kqxs theo khu vuc va ngay truyen vao
    List<KetQuaXoSo> getKqXsByKhuVucAndDate(String aria,Date date);
    // lay ket qua xo so theo khu vuc va so luong can lay truyen vao
    List<List<KetQuaXoSo>> getKqXsByKhuVucAndNumber(String aria, Integer num);
    // lay kqxs theo ten dai va so luon can lay truyen vao
   List<KetQuaXoSo> getKqXsByNameDaiAndNumQuantity(String nameDai,Integer num);

   // lay du lieu theo ngay xo so
    List<KetQuaXoSo> getKqXsByDateLottery(Date date);
    // LAY DANH SACH KHU VUC VE SO THEO NGAY TRUYEN VAO
    List<String> getAriaByDate(Date date);

}
