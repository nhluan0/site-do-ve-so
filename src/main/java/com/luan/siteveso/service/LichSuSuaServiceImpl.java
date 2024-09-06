package com.luan.siteveso.service;

import com.luan.siteveso.dao.LichSuSuaRepository;
import com.luan.siteveso.entity.LichSuSua;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LichSuSuaServiceImpl implements LichSuSuaService {
    private LichSuSuaRepository lichSuSuaRepository;
    @Autowired
    public LichSuSuaServiceImpl(LichSuSuaRepository lichSuSuaRepository) {
        this.lichSuSuaRepository = lichSuSuaRepository;
    }

    // lay danh sach theo idkq
    @Override
    public List<LichSuSua> getLichSuSuaByIdKq(int idkq) {
        List<LichSuSua> lichSuSuas = new ArrayList<>();
        try {
            lichSuSuas = lichSuSuaRepository.findByIdKq(idkq);
        }catch (Exception e){
            return null;
        }
        return lichSuSuas;
    }

    // xóa 1 thực thể trong bảng
    @Transactional
    @Override
    public Boolean deleteByLichSuSua(LichSuSua lichSuSua) {
        try {
            if(lichSuSua == null){
                return false;
            }

            // kiem tra id lichsusua truyen vao co ton tai trong he thong khong
            if(lichSuSua.getId() == 0) return false;
            System.out.println("Toi day chua");
            lichSuSua = lichSuSuaRepository.getReferenceById(lichSuSua.getId());
            if(lichSuSua == null)return false;
            // xoa cac moi quan he lien quan den bang user

            lichSuSua.setUser(null);
            lichSuSuaRepository.delete(lichSuSua);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    // xoa danh sach thuc the theo list LichSuSua truyen vao
    @Transactional
    @Override
    public Boolean deleteListLichSuSua(List<LichSuSua> lichSuSuaList) {
        try {
            for (LichSuSua ls : lichSuSuaList
            ){
                // xoa tung doi tuong co trong list
                Boolean check = deleteByLichSuSua(ls);
                if(!check) return false;
            }
            return true;
        }catch (Exception e){
            return false;
        }

    }

    // xóa các phần tử theo id kết quả xổ số truyền vào
    @Transactional
    @Override
    public Boolean deleteByIdKqXs(int id) {
        // 1: lấy danh sách theo id kqxs truyền vào
        List<LichSuSua> lichSuSuas = getLichSuSuaByIdKq(id);
        // 2. check danh sách rỗng hay không , nếu rỗng trả về false
        if ( lichSuSuas.size() == 0) return false;
        // 3. danh sách không rỗng lặp qua danh sách thực hiện xóa từng thực thể trong list
        Boolean check =false; // biến kiểm tra xóa 1 thực thể có được hay không
        for (LichSuSua ls: lichSuSuas
             ) {
            check = deleteByLichSuSua(ls);
            if(!check) return false;
        }

        return true;
    }
    // luu 1 entity vao bang database lich su sua
    @Transactional
    @Override
    public LichSuSua save(LichSuSua lichSuSua) {
        LichSuSua lichSuSua1 = null;
        try {
            lichSuSua1= lichSuSuaRepository.save(lichSuSua);

        }catch (Exception e){

        }
        return lichSuSua1;
    }
}
