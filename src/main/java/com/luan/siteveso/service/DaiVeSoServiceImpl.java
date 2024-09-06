package com.luan.siteveso.service;

import com.luan.siteveso.dao.DaiXoSoRepository;
import com.luan.siteveso.dao.KetQuaXoSoRepository;
import com.luan.siteveso.entity.DaiXoSo;
import com.luan.siteveso.entity.KetQuaXoSo;
import com.luan.siteveso.entity.LichSuSua;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DaiVeSoServiceImpl implements DaiVeSoService{
    // inject DaiVeSoRepository by constructor inject
    private DaiXoSoRepository daiXoSoRepository;
    private KetQuaXoSoRepository ketQuaXoSoRepository;
    private KetQuaXoSoServiceImpl ketQuaXoSoServiceImpl;
    private LichSuSuaServiceImpl lichSuSuaServiceImpl;
    private EntityManager entityManager;
    @Autowired
    public DaiVeSoServiceImpl(DaiXoSoRepository daiXoSoRepository,KetQuaXoSoRepository ketQuaXoSoRepository
    ,KetQuaXoSoServiceImpl ketQuaXoSoServiceImpl,LichSuSuaServiceImpl lichSuSuaServiceImpl,EntityManager entityManager) {
        this.daiXoSoRepository = daiXoSoRepository;
        this.ketQuaXoSoRepository = ketQuaXoSoRepository;
        this.ketQuaXoSoServiceImpl = ketQuaXoSoServiceImpl;
        this.lichSuSuaServiceImpl = lichSuSuaServiceImpl;
        this.entityManager = entityManager;
    }

    // lay dai ve so theo id truyen vao
    @Override
    public DaiXoSo getById(int id) {
        DaiXoSo daiXoSo = null;
        try {
            daiXoSo = daiXoSoRepository.getReferenceById(id);
            return daiXoSo;
        }catch (Exception e){
            return null;
        }

    }

    // lay tat ca dai ve so co trong database
    @Override
    public List<DaiXoSo> getAll() {
        List<DaiXoSo> daiXoSoList = new ArrayList<>();
        try {
            daiXoSoList = daiXoSoRepository.findAll();
            return daiXoSoList;
        }catch (Exception e){
            return daiXoSoList;
        }
    }

    // lay dai ve so theo ten dai truyen vao
    @Override
    public List<DaiXoSo> getDaiVeSoByTenDai(String tenDai) {
        try {
            List<DaiXoSo> daiXoSoList = new ArrayList<>();
            DaiXoSo daiXoSo = daiXoSoRepository.findByNameLottery(tenDai);
            if(daiXoSo != null)daiXoSoList.add(daiXoSo);
            return  daiXoSoList;
        }catch (Exception e){
            return null;
        }

    }

    // lay dai ve so theo ten khu vuc truyen vao
    @Override
    public List<DaiXoSo> getDaiVeSoByKhuVuc(String khuVuc) {
        List<DaiXoSo> daiXoSoList = new ArrayList<>();
        try {
            daiXoSoList = daiXoSoRepository.findByAria(khuVuc);
            return daiXoSoList;
        }catch (Exception e){
            return daiXoSoList;
        }

    }


    // xoa 1 dai ve so theo id truyen vao
    @Transactional
    @Override
    public Boolean deleteDaiVeSoById(int id) {
        // lay dai ve so theo id truyen len xem thu co ton tai hay khong
        DaiXoSo daiXoSo = null;
        daiXoSo = daiXoSoRepository.getReferenceById(id);
        // dai ve so theo id khong ton tai tra ve false
        if(daiXoSo == null){
            return false;
        }
        // dai ve so ton tai thuc hien xoa
        try {
            // 1: lay danh sach ket qua xo so co lien quan
            List<KetQuaXoSo> ketQuaXoSoList = ketQuaXoSoRepository.findByDaiXoSo(daiXoSo);
            // 2: delete cac ket qua dai so xo co lien quan va lich su sua co lien quan
            for (KetQuaXoSo kq: ketQuaXoSoList
                 ) {
                // kiem tra lich su sua co theo id khong
                List<LichSuSua> lichSuSuas = lichSuSuaServiceImpl.getLichSuSuaByIdKq(kq.getId());
                if(lichSuSuas.size() != 0){
                    // delete lịch su sua theo id kqxs
                    Boolean check1 = lichSuSuaServiceImpl.deleteByIdKqXs(kq.getId());
                    if(!check1) return false;

                }
                // delete ket qua xo so
                Boolean check2 = ketQuaXoSoServiceImpl.deleteById(kq.getId());
                if(!check2) return false;

            }
            // 3: lay danh sach lich su sua co lien quan với id kqsx
            daiXoSoRepository.delete(daiXoSo);
            return true;
        }catch (Exception e){
            return false;
        }
    }


    //delete nhiều dai vé số theo mảng id truyền vào
    @Transactional
    @Override
    public Boolean deleteDaiVeSosByArrayId(int[] arrId) {
        Boolean check = false;
        try {
            // lặp qua hết mảng chứa id và xóa từng id truyền vào
            for (int i = 0 ; i<arrId.length;i++){
                check = deleteDaiVeSoById(arrId[i]);
                if(!check) return false;
            }
            // nếu xóa được hết vòng lặp thì trả về true
            return true;
        }catch (Exception e){
            return false;
        }

    }

    // luu 1 dai xo so
    @Transactional
    @Override
    public DaiXoSo saveDaiXoSo(DaiXoSo daiXoSo) {
        try {
            return daiXoSoRepository.save(daiXoSo);
        }catch (Exception e){
            return null;
        }

    }

    // lay khu vuc trong database
    @Override
    public List<String> getDistinctByKhuVuc() {
        String jpql = "select DISTINCT(d.aria) from DaiXoSo d";
        Query query = entityManager.createQuery(jpql,DaiXoSo.class);
        List<String> khuVucList = query.getResultList();
        return khuVucList;
    }
}
