package com.luan.siteveso.service;

import com.luan.siteveso.dao.LichSuDoSoRepository;
import com.luan.siteveso.entity.LichSuDoSo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class LichSuDoSoServiceImpl implements LichSuDoSoService{
    @Autowired
    private LichSuDoSoRepository lichSuDoSoRepository;
    @Autowired
    private EntityManager entityManager;

    // delete mot lichSuDoSo
    @Transactional
    @Override
    public Boolean delete(LichSuDoSo lichSuDoSo) {
        if(lichSuDoSo == null){
            return false;
        }

        try {
            // lay lich su do so theo id nhan duoc
            lichSuDoSo = lichSuDoSoRepository.getReferenceById(lichSuDoSo.getId());
            if(lichSuDoSo != null){
                // set cac user va kqxs co lien quan ve null. de pha vo quan he
                lichSuDoSo.setUser(null);
                lichSuDoSo.setKetQuaXoSo(null);
                lichSuDoSoRepository.delete(lichSuDoSo);
                return true;
            }
            return false;
        }catch (Exception e){
            return false;
        }

    }
    // xoa nhieu lich su do so theo list Ltruyen vao
    @Transactional
    @Override
    public Boolean deleteByList(List<LichSuDoSo> lichSuDoSoList) {
        Boolean check = false;
        // xoa nhieu lich su do so theo list truyen vao
        for (LichSuDoSo lsDs:lichSuDoSoList
             ) {
            check = delete(lsDs);
            // neu xoa 1 phan tu trong list khong thanh cong thi thoat tra ve false
            if(!check) return false;
        }
        return true;
    }

    // lay list lich su do so theo so trang va number phan tu truyen vao
    @Override
    public Page<LichSuDoSo> getLichSuDoSo(int numPage, int size) {
      Page<LichSuDoSo> listLsDs =
              lichSuDoSoRepository.findAll(PageRequest.of(numPage,size, Sort.by("ngayDo").descending()));
        return listLsDs;
    }

    // xoa 1 lich su do so theo id truyen vao
    @Override
    public Boolean deleteById(int id) {
        // 1: check lich su do so theo id co ton tai trong database khong
        LichSuDoSo lichSuDoSo = lichSuDoSoRepository.getReferenceById(id);
        if (lichSuDoSo == null)return false;
        Boolean check = delete(lichSuDoSo);
        return check;
    }

    // xoa nhieu phan tu theo id mang id truyen vao
    @Override
    public Boolean deletesByArrId(Integer[] arr) {
        // 1: kiem tra tat ca id truyen vao co ton tai trong database, sau do moi thuc hien xoa tung id
        int check = 0;
        for(int i = 0 ; i < arr.length ; i++){
            // 2: lay lich su do theo id truyen vao va sau do kiem tra tung thuc the
            LichSuDoSo lichSuDoSo = lichSuDoSoRepository.getReferenceById(arr[i]);
            if(lichSuDoSo == null) return false;
            check++;
        }
        // kiem tr id co ton tai het trong database
        if(check == arr.length){
            // 3: thuc hien xoa tung id truyen vao
            for (int i = 0;i < arr.length;i++){
                try {
                    deleteById(arr[i]);
                }catch (Exception e){
                    return false;
                }

            }
            return true; // xoa duoc het tra ve true
        }
        return false;
    }

    // lay danh sach lich su do so theo username
    @Override
    public List<LichSuDoSo> getLichSuDoSoByUserName(String userName) {
        // create query
        TypedQuery<LichSuDoSo> query = entityManager.createQuery(
                "select u from LichSuDoSo u "
                        + "JOIN FETCH u.user "
                        + "where u.user.userName =:data and u.status like '%chưa xóa%' order by u.ngayDo desc",LichSuDoSo.class);
        // set get element limit
        query.setMaxResults(10);
        // set parameter
        query.setParameter("data",userName);
        // lay danh sach ket qua
        List<LichSuDoSo> lichSuDoSoList = query.getResultList();

        return lichSuDoSoList;
    }

    // lay lich su do so theo ten dang nhap facebook , google
    @Override
    public List<LichSuDoSo> getLichSuDoSoBySocialDesc(String social) {
        String jpql = "select ls from LichSuDoSo ls where ls.social = :data order by ls.ngayDo desc";
        Query query = entityManager.createQuery(jpql,LichSuDoSo.class);
        //set max ket qua lay
        query.setMaxResults(10);
        // set tham so
        query.setParameter("data",social);
        // lay ket qua
        List<LichSuDoSo> lichSuDoSoList = query.getResultList();
        return lichSuDoSoList;
    }
}
