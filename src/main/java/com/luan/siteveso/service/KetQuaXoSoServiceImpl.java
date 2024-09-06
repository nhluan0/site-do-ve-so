package com.luan.siteveso.service;

import com.luan.siteveso.dao.DaiXoSoRepository;
import com.luan.siteveso.dao.KetQuaXoSoRepository;
import com.luan.siteveso.dao.LichSuDoSoRepository;
import com.luan.siteveso.dao.LichSuSuaRepository;
import com.luan.siteveso.entity.DaiXoSo;
import com.luan.siteveso.entity.KetQuaXoSo;

import com.luan.siteveso.entity.LichSuDoSo;
import com.luan.siteveso.entity.LichSuSua;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class KetQuaXoSoServiceImpl implements KetQuaXoSoService{
    // add new field EntityManager
    // inject field EntityManager by constructor inject
    private KetQuaXoSoRepository ketQuaXoSoRepository;
    private EntityManager entityManager;
    private LichSuSuaServiceImpl lichSuSuaServiceImpl;
    private DaiXoSoRepository daiXoSoRepository;
    @Autowired
    private LichSuDoSoRepository lichSuDoSoRepository;
    @Autowired
    private LichSuDoSoServiceImpl lichSuDoSoServiceImpl;
    @Autowired
    public KetQuaXoSoServiceImpl(EntityManager entityManager,KetQuaXoSoRepository ketQuaXoSoRepository,
                                 LichSuSuaServiceImpl lichSuSuaServiceImpl,DaiXoSoRepository daiXoSoRepository) {
        this.daiXoSoRepository = daiXoSoRepository;
        this.entityManager = entityManager;
        this.ketQuaXoSoRepository = ketQuaXoSoRepository;
        this.lichSuSuaServiceImpl = lichSuSuaServiceImpl;
    }

    // lay ket qua xo so theo id truyen vao voi kieu fetch lazy
    @Override
    public KetQuaXoSo getKetQuaXoSoById(int id) {
        // create cau lenh sql
        TypedQuery<KetQuaXoSo>  theQuery = entityManager.createQuery("from KetQuaXoSo where id = :data", KetQuaXoSo.class);
        // set parameter
        theQuery.setParameter("data",id);
        // execute theQuery
        KetQuaXoSo ketQuaXoSo = theQuery.getSingleResult();


        return ketQuaXoSo;
    }

    @Override
    public List<KetQuaXoSo> getAllKetQuaXoSo() {
        // create sql
        TypedQuery<KetQuaXoSo> theQuery = entityManager.createQuery("from KetQuaXoSo", KetQuaXoSo.class);
        // execute sql
        List<KetQuaXoSo> ketQuaXoSos = theQuery.getResultList();
        return ketQuaXoSos;
    }

    // lay thong tin admin da tao ra theo ket qua id of bang ket qua
    @Override
    public KetQuaXoSo getUserForKetQuaXoSoById(int id) {
        // create sql
        TypedQuery<KetQuaXoSo> theQuery = entityManager.createQuery(
              "select u from KetQuaXoSo u "
              + "JOIN FETCH u.user "
                + "where u.id =:data",KetQuaXoSo.class
        );
        // set parameter
        theQuery.setParameter("data",id);
        // execute sql
        KetQuaXoSo ketQuaXoSo = theQuery.getSingleResult();
        return ketQuaXoSo;
    }


    // lay danh sach ket qua theo so phan trang va kich thuoc so hang can hien thi 1 trang
    @Override
    public Page<KetQuaXoSo> getKetQuaXoSo(int numPage, int size) {
        Page<KetQuaXoSo> listKq =
                ketQuaXoSoRepository.findAll(PageRequest.of(numPage,size, Sort.by("dateLottery").descending()));
        return listKq;
    }

    // delete 1 phan tu theo id
    @Transactional
    @Override
    public Boolean deleteById(int id) {
        KetQuaXoSo ketQuaXoSo = null;
        try {
            ketQuaXoSo = ketQuaXoSoRepository.getReferenceById(id);
            // kiem tra id co ton tai hay khong
            if(ketQuaXoSo == null)return false;
            // id ton tai
            // 1: pha vo su lien ket voi bang Dai Xo So va bang user
            ketQuaXoSo.setDaiXoSo(null);
            ketQuaXoSo.setUser(null);
            // xoa cac phan tu trong bang lich su sua lien ket với id kết qủa xổ số
            // 1.1 lấy danh sách LichSuSua theo id ket qua truyen vao
            List<LichSuSua> lichSuSuas = lichSuSuaServiceImpl.getLichSuSuaByIdKq(id);
            // 1.2 kiểm tra danh sách không trống thì thực hiện xóa thực thể lịch sủ sửa
            if(lichSuSuas.size() > 0){
                for (LichSuSua ls:lichSuSuas
                     ) {
                    boolean check = true;// biến kiểm tra xóa thực thể có thành công hay không
                    check = lichSuSuaServiceImpl.deleteByLichSuSua(ls);
                    if(!check) return false; // nếu có 1 lần xóa thất bại thì thoát
                }
            }
            // 2: xoa cac thuc the co lien quan trong bang lich su do
            // 2:1 lay danh sach cac lich su do so theo kqxs
            List<LichSuDoSo> lichSuDoSoList = lichSuDoSoRepository.findByKetQuaXoSo(ketQuaXoSo);
            Boolean checkDeleteLsDs = false;
            if(lichSuDoSoList != null){
                // 2:2 thuc hien xoa tung lich su do
                checkDeleteLsDs = lichSuDoSoServiceImpl.deleteByList(lichSuDoSoList);
                if(!checkDeleteLsDs) return false;

            }

            // 3: thuc hien remove lien ket
            entityManager.remove(ketQuaXoSo);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    // xóa nhiều đối tuọng theo mảng id truyền vào
    @Transactional
    @Override
    public Boolean deletesByArrId(Integer[] arr) {
        Boolean check = false;
        //1: lặp qua từng phần tử của mảng và delete đối tượng theo id lấy được trong mảng
        try {
            for (Integer id: arr ) {
                //2: delete từng id nhận được từ mảng theo id trong database , đồng thời kiểm tra việc delete
                // có thất bại thì thoát vòng lặp
                check = deleteById(id);
                if(!check)return false;
            }
            return true; // nếu lặp qua được hết vòng lặp thì trả về true
        }catch (Exception e){
            return false;
        }

    }
    // luu 1 kêt qua xo so
    @Transactional
    @Override
    public KetQuaXoSo save(KetQuaXoSo ketQuaXoSo) {
        KetQuaXoSo ketQuaXoSo1 = new KetQuaXoSo();
        if(ketQuaXoSo !=null){
            try {
            ketQuaXoSo1=  ketQuaXoSoRepository.save(ketQuaXoSo);
            return ketQuaXoSo1;
            }catch (Exception e){
                return null;
            }
        }

        return null;
    }

    // lấy 8 kết quả xổ số theo tên đài truyền vào, sap xep theo  ngay xo so thu tu giảm dần
    @Override
    public List<KetQuaXoSo> get8KetQuaXoSoByTenDai(String tenDai, int pageNumber,int pageSize) {
        // 1: lấy thông tin Đài vé số tương ứng với tên đài truyền vào mục đích lấy đươc id của đài
        DaiXoSo daiXoSo = daiXoSoRepository.findByNameLottery(tenDai);
        System.out.println("Dai ve so nhan duoc la" + daiXoSo);
        List<KetQuaXoSo> listKqXs = new ArrayList<>();
        // 2: check daiVeSo co null hay không
        if(daiXoSo !=null){
            try {
                // 3: create the query
                TypedQuery<KetQuaXoSo> theQuery = entityManager.createQuery(
                        "select kq from KetQuaXoSo kq "+
                                "JOIN FETCH kq.daiXoSo " +
                                "where kq.daiXoSo.nameLottery =:data  " +
                                "order by dateLottery desc",KetQuaXoSo.class);
                // 7: set first vi tri bat dau để lấy kết quả xổ số
                theQuery.setFirstResult((pageNumber-1)*pageSize);
                // 4: chi lay 8 ket qua
                theQuery.setMaxResults(pageSize);
                // 5: set tham so
                theQuery.setParameter("data",tenDai);
                // 6: get ket qua
                listKqXs = theQuery.getResultList();
                return listKqXs;
            }catch (Exception e){
                return listKqXs;
            }
        }
        return listKqXs;
    }

    // đếm có bao nhiêu kết quả xổ số theo tên đài truyền vào
    @Override
    public int countKqXsByTenDai(String tenDai) {
        // 1: lấy thông tin Đài vé số tương ứng với tên đài truyền vào mục đích lấy đươc id của đài
        DaiXoSo daiXoSo = daiXoSoRepository.findByNameLottery(tenDai);
       int pageNumberLast = 0;
        // 2: check daiVeSo co null hay không
        if(daiXoSo !=null){
            String jpql = "SELECT COUNT(kq.id) FROM KetQuaXoSo kq JOIN kq.daiXoSo d WHERE d.nameLottery = :data";
            Query query = entityManager.createQuery(jpql);
            query.setParameter("data", tenDai);

            long count = (Long) query.getSingleResult();
            pageNumberLast = (int)count;
        }
        return pageNumberLast;
    }
    // lấy kết quả xổ số theo tên đài và ngày truyền vào
    @Override
    public List<KetQuaXoSo> getKqXsByTenDaiAndDateXs(String tenDai, Date dateXs) {
        // 1: lấy thông tin Đài vé số tương ứng với tên đài truyền vào mục đích lấy đươc id của đài
        DaiXoSo daiXoSo = daiXoSoRepository.findByNameLottery(tenDai);
        List<KetQuaXoSo> resultList = new ArrayList<>();
        // 2: check daiVeSo co null hay không
        if(daiXoSo !=null){
            String jpql = "SELECT kq FROM KetQuaXoSo kq JOIN kq.daiXoSo d WHERE d.nameLottery = :data AND kq.dateLottery = :data2 ";
            TypedQuery<KetQuaXoSo> query = entityManager.createQuery(jpql, KetQuaXoSo.class);
            query.setParameter("data", tenDai).setParameter("data2", dateXs);
            resultList = query.getResultList();
        }
        return resultList;
    }





    // phan lay ket qua xo so cho trang chu
    // lay list ngay xo so moi nhat , so luong lay theo tham so truyen vao
    @Override
    public List<Date> getDateByNumberParamPasses(Integer numQuantity) {
        // 1: CREATE QUERY
        String jpql = "select DISTINCT(kq.dateLottery) from KetQuaXoSo kq order by dateLottery desc";
        Query query = entityManager.createQuery(jpql,KetQuaXoSo.class);
        // 2: set so luong lay
        query.setMaxResults(numQuantity);
        // 3: run statement
        List<Date> dateList = query.getResultList();
        return dateList;
    }
    // lay ngay kqxs theo Khu vuc truyen vao
    @Override
    public List<Date> getDateKqXsByKhuVuc(String aria, int size) {
        // 1: CREATE QUERY
        String jpql = "select DISTINCT(kq.dateLottery) from  KetQuaXoSo kq JOIN kq.daiXoSo d WHERE d.aria = :data order by dateLottery desc";
        Query query = entityManager.createQuery(jpql,KetQuaXoSo.class);
        query.setParameter("data",aria);
        // 2: set so luong lay
        query.setMaxResults(size);
        // 3: run statement
        List<Date> dateList = query.getResultList();
        return dateList;
    }

    // lay kqXs khi lan dau load trang chu , so luong ket qua theo tham so truyen vao
    @Override
    public List<List<KetQuaXoSo>> getListKqXsForPageHomeByNumPass(Integer numQuantity) {
        // 1: lay danh sach ngay xo so, so luong lay theo ngay truyen vao
        List<Date> dateList = getDateByNumberParamPasses(numQuantity);
        List<List<KetQuaXoSo>> lists = new ArrayList<>();
        // 2: kiem tra list<Date> co du lieu thi lay ket qua xo so theo ngay trong list
        if(dateList.size() > 0){
            // lap qua tung ket qua va lay du lieu xo so theo tung phan tu trong list
            for (Date date: dateList
                 ) {
                // 3: lay key qua xo so theo ngay xo so va them du lieu lay duoc vao list
                List<KetQuaXoSo> ketQuaXoSoList = new ArrayList<>();
                ketQuaXoSoList = ketQuaXoSoRepository.findByDateLottery(date);
                if(ketQuaXoSoList.size() > 0){
                    lists.add(ketQuaXoSoList);
                }
            }
        }
        return lists;
    }

    // lay kqXs theo khu vuc va ngay truyen vao
    @Override
    public List<KetQuaXoSo> getKqXsByKhuVucAndDate(String aria, Date date) {

        List<KetQuaXoSo> resultList = new ArrayList<>();
        try {
            // create query
            String jpql = "SELECT kq FROM KetQuaXoSo kq JOIN kq.daiXoSo d WHERE d.aria = :data AND kq.dateLottery = :data2 ";
            TypedQuery<KetQuaXoSo> query = entityManager.createQuery(jpql, KetQuaXoSo.class);
            query.setParameter("data", aria).setParameter("data2", date);
            resultList = query.getResultList();
            return resultList;
        }catch (Exception e){
            return resultList;
        }
    }

    // lay ket qua xo so theo khu vuc va so luong lay can truyen vao
    @Override
    public List<List<KetQuaXoSo>> getKqXsByKhuVucAndNumber(String aria, Integer num) {
        // 1: lay danh sach ngay xo so, so luong lay theo ngay truyen vao
        List<Date> dateList = getDateKqXsByKhuVuc(aria,num);
        List<List<KetQuaXoSo>> lists = new ArrayList<>();
        // 2: kiem tra list<Date> co du lieu thi lay ket qua xo so theo ngay trong list
        if(dateList.size() > 0){
            // lap qua tung ket qua va lay du lieu xo so theo tung phan tu trong list
            for (Date date: dateList
            ) {
                System.out.println("ngay xo so " + date);
                // 3: lay key qua xo so theo ngay xo so va them du lieu lay duoc vao list
                List<KetQuaXoSo> ketQuaXoSoList = new ArrayList<>();
                ketQuaXoSoList = getKqXsByKhuVucAndDate(aria,date);

                if(ketQuaXoSoList.size() > 0){
                    lists.add(ketQuaXoSoList);
                }
            }
        }
        return lists;

    }

    // lay ket qua xo so theo ten dai va so luong can lay truyen vao
    @Override
    public List<KetQuaXoSo> getKqXsByNameDaiAndNumQuantity(String nameDai,Integer num) {
        List<KetQuaXoSo> resultList = new ArrayList<>();
        try {
            // create query
            String jpql = "SELECT kq FROM KetQuaXoSo kq JOIN kq.daiXoSo d WHERE d.nameLottery = :data order by kq.dateLottery desc ";
            TypedQuery<KetQuaXoSo> query = entityManager.createQuery(jpql, KetQuaXoSo.class);
            query.setParameter("data", nameDai); // set param
            query.setMaxResults(num); // set max so luong can lay
            resultList = query.getResultList(); // execute query
            return resultList;
        }catch (Exception e){
            return resultList;
        }

    }

    // lay ket qua ve so theo ngay truyen vao
    @Override
    public List<KetQuaXoSo> getKqXsByDateLottery(Date date) {
        List<KetQuaXoSo> list = new ArrayList<>();
        try {
            list = ketQuaXoSoRepository.findByDateLottery(date);
            return list;
        }catch (Exception e){
            return null;
        }

    }
    // lay khu vuc theo ngay truyen vao
    @Override
    public List<String> getAriaByDate(Date date) {
        List<String> listAria = new ArrayList<>();
        try {
            // create query
            String jpql = " select DISTINCT(d.aria) FROM KetQuaXoSo kq JOIN kq.daiXoSo d WHERE kq.dateLottery  = :data ";
            Query query = entityManager.createQuery(jpql, KetQuaXoSo.class);
            query.setParameter("data", date); // set param

            listAria = query.getResultList(); // execute query
            return listAria;
        }catch (Exception e){
            return null;
        }
    }


}
