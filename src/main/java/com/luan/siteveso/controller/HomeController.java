package com.luan.siteveso.controller;

import com.luan.siteveso.dao.LichSuDoSoRepository;
import com.luan.siteveso.dao.TienThuongRepository;
import com.luan.siteveso.dao.UserRepository;
import com.luan.siteveso.date.DateSql;
import com.luan.siteveso.entity.*;

import com.luan.siteveso.service.DaiVeSoServiceImpl;
import com.luan.siteveso.service.KetQuaXoSoServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/us")
public class HomeController {
    private DaiVeSoServiceImpl daiVeSoServiceImpl;
    private KetQuaXoSoServiceImpl ketQuaXoSoServiceImpl;
    private DateSql dateSql;
    private UserRepository userRepository;
    private LichSuDoSoRepository lichSuDoSoRepository;
    @Autowired
    private TienThuongRepository tienThuongRepository;
    @Autowired
    public HomeController(DaiVeSoServiceImpl daiVeSoServiceImpl,KetQuaXoSoServiceImpl ketQuaXoSoServiceImpl,
                          DateSql dateSql,UserRepository userRepository,LichSuDoSoRepository lichSuDoSoRepository) {
        this.daiVeSoServiceImpl = daiVeSoServiceImpl;
        this.ketQuaXoSoServiceImpl =ketQuaXoSoServiceImpl;
        this.dateSql = dateSql;
        this.userRepository = userRepository;
        this.lichSuDoSoRepository = lichSuDoSoRepository;
    }


    // hien thi du lieu cho lan dau load trang
    @GetMapping("/trang-chu")
    public String getKqXsForShowFirstLoadPage(Model model){
        // 1: lay dai ve so , de hien thi dai ve khu vuc
        // a: lấy khu vực
        List<String> khuVucList =daiVeSoServiceImpl.getDistinctByKhuVuc();
        // b: lấy tất cả đài vé số
        List<DaiXoSo> daiXoSoList = daiVeSoServiceImpl.getAll();
        // c: gửi lên modal hiển thị lên slideLeft
        model.addAttribute("khuVucList",khuVucList);

        model.addAttribute("daiXoSoList",daiXoSoList);

        // 2: lay ket qua xo so de hien thi lan dau len trang chu , so luong lay theo tham so truyen vao, o day ta lay 2 ket qua thoi
        List<List<KetQuaXoSo>> listKqXs = ketQuaXoSoServiceImpl.getListKqXsForPageHomeByNumPass(1);
        // lay ngay kqxs phat hanh trong datbase moi nhat
        List<Date> date = ketQuaXoSoServiceImpl.getDateByNumberParamPasses(1);
        // lay khu vuc theo ngay truyen vao
        List<String> khuVucList1 = ketQuaXoSoServiceImpl.getAriaByDate(date.get(0));
        model.addAttribute("khuVucList1",khuVucList1);
        // a. gui len trang chu
        model.addAttribute("listKetQuaXoSo",listKqXs);
        return "/trangchu/home";
    }

    // hien thi du lieu theo ten khu vuc(mien trung, mien nam) truyen vao
    @GetMapping("/trang-chu/khuvuc")
    public String getKqXsByKhuVuc(Model model, @RequestParam("mien") String aria,
                                  @ModelAttribute("mien") String mien){
        // 1: lay dai ve so , de hien thi dai ve khu vuc
        // a: lấy khu vực
        List<String> khuVucList =daiVeSoServiceImpl.getDistinctByKhuVuc();
        List<String> khuVucList1 = new ArrayList<>();
        khuVucList1.add(aria);
        // b: lấy tất cả đài vé số
        List<DaiXoSo> daiXoSoList = daiVeSoServiceImpl.getAll();
        // c: gửi lên modal hiển thị lên slideLeft
        model.addAttribute("khuVucList",khuVucList);
        model.addAttribute("khuVucList1",khuVucList1);
        model.addAttribute("daiXoSoList",daiXoSoList);
        List<List<KetQuaXoSo>> listKqXs = new ArrayList<>();
        // 2: lay ket qua xo so de hien thi lan dau len trang chu , so luong lay theo tham so truyen vao, o day ta lay 2 ket qua thoi
        if(!mien.equalsIgnoreCase("")){
            listKqXs = ketQuaXoSoServiceImpl.getKqXsByKhuVucAndNumber(mien,2);
            model.addAttribute("tenDai",mien);
        }else{
            listKqXs  = ketQuaXoSoServiceImpl.getKqXsByKhuVucAndNumber(aria,2);
            model.addAttribute("tenDai",aria);
        }

//        System.out.println("do dai khu vuc lay duoc: " + listKqXs.size());
        model.addAttribute("listKetQuaXoSo",listKqXs);
        return "/trangchu/home";
    }
    // hien thi kqxs theo ten dai
    @GetMapping("/trang-chu/dai")
    public String getKqXsByNameDai(Model model,@RequestParam("daiVeSo") String nameDai,
                                   @ModelAttribute("daiVeSo") String tenDai){

        // 1: lay dai ve so , de hien thi dai ve khu vuc
        // a: lấy khu vực
        List<String> khuVucList =daiVeSoServiceImpl.getDistinctByKhuVuc();
        // b: lấy tất cả đài vé số
        List<DaiXoSo> daiXoSoList = daiVeSoServiceImpl.getAll();
        // c: gửi lên modal hiển thị lên slideLeft
        model.addAttribute("khuVucList",khuVucList);
        model.addAttribute("daiXoSoList",daiXoSoList);
        List<KetQuaXoSo> listKqXsByNameDai = new ArrayList<>();
        // 2: lay ket qua xo so de hien thi lan dau len trang chu , so luong lay theo tham so truyen vao, o day ta lay 2 ket qua thoi
        if(!tenDai.equalsIgnoreCase("")){
            listKqXsByNameDai = ketQuaXoSoServiceImpl.getKqXsByNameDaiAndNumQuantity(tenDai,2);
            model.addAttribute("tenDai",tenDai);
        }else{
            listKqXsByNameDai = ketQuaXoSoServiceImpl.getKqXsByNameDaiAndNumQuantity(nameDai,2);
            model.addAttribute("tenDai",nameDai);
        }

        model.addAttribute("listKqXsByNameDai",listKqXsByNameDai );
        return "/trangchu/page-ten-dai";
    }
    // phan xu ly chon dai cho nut select
    @GetMapping("/chon-dai")
    public String getKqXsByTenDaiByMethodPost(String tenDai, String date, Model model,
                                              RedirectAttributes redirectAttributes){
        // chuyen doi string sang dinh dang "yyyy-mm-dd" trong sql
        Date daySql = dateSql.parseStringToDateSql(date);
        // lay tat ca cac mien trong du lieu
        List<String> mienList = daiVeSoServiceImpl.getDistinctByKhuVuc();
        // 1: kiem tra ngay truyen len co gia tri la 1 thi chi tim data theo ten dai thoi
        if(date.equalsIgnoreCase("1")){
            // 2: kiem tra ten dai co phai len khu vuc thi dieu huong den page khu vuc
            for (String mien: mienList
                 ) {
                if(tenDai.equalsIgnoreCase(mien)){
                    redirectAttributes.addAttribute("mien",tenDai);
                    return "redirect:/us/trang-chu/khuvuc";
                }
            }
            // neu vong lap lap duoc het thi tenDai thuoc vao cac dai , thi dieu huong den trang ten dai
            redirectAttributes.addAttribute("daiVeSo",tenDai);
            return "redirect:/us/trang-chu/dai";
        }else if(!tenDai.equalsIgnoreCase("1")){ // truong hop vua co ngay va ten dai truyen len
            // lap qua vong lap chua khu vuc de kiem tra thu ten dai truyen len la khu vuc hay la ten dai
            for (String mien : mienList) {
                // kiem tra ten dai truyen len la khu vuc
                if(mien.equalsIgnoreCase(tenDai)){
                    // 1: lay dai ve so , de hien thi dai ve khu vuc
                    // a: lấy khu vực
                    List<String> khuVucList =daiVeSoServiceImpl.getDistinctByKhuVuc();
                    List<String> khuVucList1 = new ArrayList<>();

                    // b: lấy tất cả đài vé số
                    List<DaiXoSo> daiXoSoList = daiVeSoServiceImpl.getAll();
                    // c: gửi lên modal hiển thị lên slideLeft
                    model.addAttribute("khuVucList",khuVucList);

                    model.addAttribute("daiXoSoList",daiXoSoList);
                    List<KetQuaXoSo> listKqX= ketQuaXoSoServiceImpl.getKqXsByKhuVucAndDate(mien,daySql);

                    List<List<KetQuaXoSo>>  listKqXs = new ArrayList<>();
                    if(listKqX.size()>0){
                        listKqXs.add(listKqX);
                        khuVucList1.add(mien);// tranh truong hop hien len bang khi khong co du lieu
                    }
                    model.addAttribute("khuVucList1",khuVucList1);
                    model.addAttribute("listKetQuaXoSo",listKqXs);
                    model.addAttribute("tenDai",mien);
                    model.addAttribute("dateXs",date);

                    return "/trangchu/home";
                }
            }
            // lay ket qua xo so theo ten dai va ngay truyen vao
            // 1: lay dai ve so , de hien thi dai ve khu vuc
            // a: lấy khu vực
            List<String> khuVucList =daiVeSoServiceImpl.getDistinctByKhuVuc();
            // b: lấy tất cả đài vé số
            List<DaiXoSo> daiXoSoList = daiVeSoServiceImpl.getAll();
            // c: gửi lên modal hiển thị lên slideLeft
            model.addAttribute("khuVucList",khuVucList);
            model.addAttribute("daiXoSoList",daiXoSoList);
            List<KetQuaXoSo> listKqXsByNameDai = ketQuaXoSoServiceImpl.getKqXsByTenDaiAndDateXs(tenDai,daySql);
            model.addAttribute("tenDai",tenDai);
            model.addAttribute("dateXs",date);
            model.addAttribute("listKqXsByNameDai",listKqXsByNameDai );
            return "/trangchu/page-ten-dai";
        }else if( tenDai.equalsIgnoreCase("1")){ // truong hop nay ten dai khong co gia tri , chi co gia tri date
            // 1: lay dai ve so , de hien thi dai ve khu vuc
            // a: lấy khu vực
            System.out.println("dang o day ne");
            List<String> khuVucList =daiVeSoServiceImpl.getDistinctByKhuVuc();
            List<String> khuVucList1 = new ArrayList<>();

            // b: lấy tất cả đài vé số
            List<DaiXoSo> daiXoSoList = daiVeSoServiceImpl.getAll();
            // c: gửi lên modal hiển thị lên slideLeft
            model.addAttribute("khuVucList",khuVucList);
            model.addAttribute("daiXoSoList",daiXoSoList);
            // lay kqXs theo ngay truyen vao
            List<KetQuaXoSo> ketQuaXoSoList = ketQuaXoSoServiceImpl.getKqXsByDateLottery(daySql);
            List<List<KetQuaXoSo>>  listKqXs = new ArrayList<>();
            // kiem tra co lay duoc du lieu hay không
            if(ketQuaXoSoList != null){
                listKqXs.add(ketQuaXoSoList);// theo du lieu vao list gui len modal
                // lay tat ca cac khu vuc theo ngay truyen vao
                khuVucList1 = ketQuaXoSoServiceImpl.getAriaByDate(daySql);
            }
            model.addAttribute("khuVucList1",khuVucList1);
            model.addAttribute("listKetQuaXoSo",listKqXs);
            model.addAttribute("dateXs",date);
            return "/trangchu/home";
        }
        return "redirect:/us/trang-chu";
    }

    // trang do ket qua lan dau tien
    @GetMapping("/do-ve-so")
    public String showPageDoVeSo(Model model){
        // 1: lay dai ve so , de hien thi dai ve khu vuc
        // a: lấy khu vực
        List<String> khuVucList =daiVeSoServiceImpl.getDistinctByKhuVuc();
        // b: lấy tất cả đài vé số
        List<DaiXoSo> daiXoSoList = daiVeSoServiceImpl.getAll();
        // c: gửi lên modal hiển thị lên slideLeft
        model.addAttribute("khuVucList",khuVucList);
        model.addAttribute("daiXoSoList",daiXoSoList);
        List<KetQuaXoSo> listKqXsByNameDai = new ArrayList<>();
        model.addAttribute("listKqXsByNameDai",listKqXsByNameDai );
//        // gui lai cac tham so de hien le gia tri cac the input khi gui ve
//        model.addAttribute("boSo",0); // số dò
//        model.addAttribute("date","");
//        model.addAttribute("tinhThanh","");
        return "/trangchu/do-ve-so";
    }

    // trang bieu dien 1 lan do ve so
    @GetMapping("/do-ket-qua")
    public String getKqXsByDoVeSo(Model model, @RequestParam("boSo") String numDo, Principal principal,
                                  @RequestParam("date") Date dateXoSo, @RequestParam("tinhThanh") String daiVeSo,Authentication authentication){

        // 1: lay dai ve so , de hien thi dai ve khu vuc
        // a: lấy khu vực
        List<String> khuVucList =daiVeSoServiceImpl.getDistinctByKhuVuc();
        // b: lấy tất cả đài vé số
        List<DaiXoSo> daiXoSoList = daiVeSoServiceImpl.getAll();
        // c: gửi lên modal hiển thị lên slideLeft
        model.addAttribute("khuVucList",khuVucList);
        model.addAttribute("daiXoSoList",daiXoSoList);
        List<KetQuaXoSo> listKqXsByNameDai = ketQuaXoSoServiceImpl.getKqXsByTenDaiAndDateXs(daiVeSo,dateXoSo);
        model.addAttribute("listKqXsByNameDai",listKqXsByNameDai );
        // gui lai cac tham so de hien le gia tri cac the input khi gui ve
        model.addAttribute("boSo",numDo); // số dò
        model.addAttribute("date",dateXoSo);
        model.addAttribute("tinhThanh",daiVeSo);
        String messResult = "khong co gia tri";


        System.out.println(listKqXsByNameDai.size());
        // kiem tra ve do co lay duoc ket qua ve so theo ngay hay không
        if(listKqXsByNameDai.size() == 0){
            messResult = "khong mo thuong";
        }else{ // lay duoc du lieu ve so , thuc kiem tra co ket qua xo so hay khong
            for (KetQuaXoSo kqXs:listKqXsByNameDai) {
                numDo = numDo.trim();
                int lengthSoDo = numDo.length();
                if(numDo.equalsIgnoreCase(kqXs.getDb())){
                    messResult = "giải đặc biệt";
                    break;
                }
                if(numDo.substring(lengthSoDo-5).equalsIgnoreCase(kqXs.getG1())){
                    messResult = "giải nhất";
                    break;
                }
                if(numDo.substring(lengthSoDo-5).equalsIgnoreCase(kqXs.getG2())){
                    messResult = "giải hai";
                    break;
                }
                if(numDo.substring(lengthSoDo-5).equalsIgnoreCase(kqXs.getG31())){
                    messResult = "giải ba";
                    break;
                }
                if(numDo.substring(lengthSoDo-5).equalsIgnoreCase(kqXs.getG32())){
                    messResult = "giải ba";
                    break;

                }
                if(numDo.substring(lengthSoDo-5).equalsIgnoreCase(kqXs.getG41())){
                    messResult = "giải 4";
                    break;
                }
                if(numDo.substring(lengthSoDo-5).equalsIgnoreCase(kqXs.getG42())){
                    messResult = "giải 4";
                    break;
                }
                if(numDo.substring(lengthSoDo-5).equalsIgnoreCase(kqXs.getG43())){
                    messResult = "giải 4";
                    break;
                }
                if(numDo.substring(lengthSoDo-5).equalsIgnoreCase(kqXs.getG44())){
                    messResult = "giải 4";
                    break;
                }
                if(numDo.substring(lengthSoDo-5).equalsIgnoreCase(kqXs.getG45())){
                    messResult = "giải 4";
                    break;
                }
                if(numDo.substring(lengthSoDo-5).equalsIgnoreCase(kqXs.getG46())){
                    messResult = "giải 4";
                    break;
                }
                if(numDo.substring(lengthSoDo-5).equalsIgnoreCase(kqXs.getG47())){
                    messResult = "giải 4";
                    break;
                }
                if(numDo.substring(lengthSoDo-4).equalsIgnoreCase(kqXs.getG5())){
                    messResult = "giải 5";
                    break;
                }
                if(numDo.substring(lengthSoDo-4).equalsIgnoreCase(kqXs.getG61())){
                    messResult = "giải 6";
                    break;
                }
                if(numDo.substring(lengthSoDo-4).equalsIgnoreCase(kqXs.getG62())){
                    messResult = "giải 6";
                    break;
                }
                if(numDo.substring(lengthSoDo-4).equalsIgnoreCase(kqXs.getG63())){
                    messResult = "giải 6";
                    break;
                }
                if(numDo.substring(lengthSoDo-3).equalsIgnoreCase(kqXs.getG7())){
                    messResult = "giải 7";
                    break;
                }

                if(numDo.substring(lengthSoDo-2).equalsIgnoreCase(kqXs.getG8())){
                    messResult = "giải 8";
                    break;
                }
            }
            // add vao bang lich su do cho doi tuong vo role user
            // 1: check co user dang nhap dang do ve so thi thuc hien add vao bang lich su do
            String userName;
            if (principal instanceof OAuth2AuthenticationToken) {
                LichSuDoSo lichSuDoSo = new LichSuDoSo();
                OAuth2User oauthUser = ((OAuth2AuthenticationToken) authentication).getPrincipal();
                // Lấy username từ OAuth2User
                 userName = oauthUser.getAttribute("name");
                 if(userName != null){
                     KetQuaXoSo ketQuaXoSo = listKqXsByNameDai.get(0);
                     Date date = dateSql.getDateSqlCurrent();
                     String hourCurrent = dateSql.getHourCurrent();
                     // kiem tra co lay duoc user tu database thi thuc hien luu vao bang lich su do

                     lichSuDoSo.setUser(null);
                     lichSuDoSo.setKetQuaXoSo(ketQuaXoSo);
                     lichSuDoSo.setHourDoSo(hourCurrent);
                     lichSuDoSo.setNgayDo(date);
                     lichSuDoSo.setSoDo(numDo);
                     lichSuDoSo.setSocial(userName);
                     lichSuDoSo.setStatus("chưa xóa");
                     if(messResult.equalsIgnoreCase("khong co gia tri")){
                         lichSuDoSo.setMessKq("Bạn không trúng giải");
                         lichSuDoSo.setTienThuong(null);
                     }else {
                         lichSuDoSo.setMessKq(messResult);
                         setTienThuongToLsDv(lichSuDoSo.getMessKq(),lichSuDoSo);
                     }
                         // luu vao database
                         lichSuDoSoRepository.save(lichSuDoSo);


                 }
                model.addAttribute("lsDs",lichSuDoSo);
                System.out.println("user name la trong oauth2: "+ userName);
            }else {
                // set lich su do so doi voi nguoi dung da dang ky
                if(principal != null){
                    LichSuDoSo lichSuDoSo = new LichSuDoSo();
                    userName = principal.getName();
                    System.out.println("name le trong database"+ userName);

                    if(userName != null){
                        KetQuaXoSo ketQuaXoSo = listKqXsByNameDai.get(0);
                        User user = userRepository.findByUserName(userName);
                        Date date = dateSql.getDateSqlCurrent();
                        String hourCurrent = dateSql.getHourCurrent();
                        // kiem tra co lay duoc user tu database thi thuc hien luu vao bang lich su do

                        if(user != null){
                            lichSuDoSo.setUser(user);
                            lichSuDoSo.setKetQuaXoSo(ketQuaXoSo);
                            lichSuDoSo.setHourDoSo(hourCurrent);
                            lichSuDoSo.setNgayDo(date);
                            lichSuDoSo.setSoDo(numDo);
                            lichSuDoSo.setSocial(null);
                            lichSuDoSo.setStatus("chưa xóa");
                            if(messResult.equalsIgnoreCase("khong co gia tri")){
                                lichSuDoSo.setMessKq("Bạn không trúng giải");
                                lichSuDoSo.setTienThuong(null);
                            }else {
                                lichSuDoSo.setMessKq(messResult);
                                setTienThuongToLsDv(lichSuDoSo.getMessKq(),lichSuDoSo);
                            }
                            // luu vao database
                            lichSuDoSoRepository.save(lichSuDoSo);
                        }
                    }
                    model.addAttribute("lsDs",lichSuDoSo);
                }else{ // tien thuong doi voi nguoi dung khong dang ky thanh vien
                    TienThuong tienThuong = new TienThuong();
                    tienThuong = setMessResultDoVeSo(messResult);
                    model.addAttribute("tienThuong",tienThuong);
                }
            }

            }

        model.addAttribute("messResult",messResult);
        return "/trangchu/do-ve-so";
    }

    // ham set tien giai thuong trung cho nguoi do
    public LichSuDoSo setTienThuongToLsDv(String mess,LichSuDoSo lichSuDoSo){
        TienThuong tienThuong = new TienThuong();
        if(mess.equalsIgnoreCase("giải nhất")){
            tienThuong = tienThuongRepository.findByGiai("g1");
            lichSuDoSo.setTienThuong(tienThuong);
        }else if(mess.equalsIgnoreCase("giải hai")){
            tienThuong = tienThuongRepository.findByGiai("g2");
            lichSuDoSo.setTienThuong(tienThuong);
        }else if(mess.equalsIgnoreCase("giải đặc biệt")){
            tienThuong = tienThuongRepository.findByGiai("db");
            lichSuDoSo.setTienThuong(tienThuong);
        }else if(mess.equalsIgnoreCase("giải ba")) {
            tienThuong = tienThuongRepository.findByGiai("g3");
            lichSuDoSo.setTienThuong(tienThuong);
        }else if(mess.equalsIgnoreCase("giải 4")) {
            tienThuong = tienThuongRepository.findByGiai("g4");
            lichSuDoSo.setTienThuong(tienThuong);
        }else if(mess.equalsIgnoreCase("giải 5")){
            tienThuong = tienThuongRepository.findByGiai("g5");
            lichSuDoSo.setTienThuong(tienThuong);
        }else if(mess.equalsIgnoreCase("giải 6")) {
            tienThuong = tienThuongRepository.findByGiai("g6");
            lichSuDoSo.setTienThuong(tienThuong);
        }else if(mess.equalsIgnoreCase("giải 7")) {
            tienThuong = tienThuongRepository.findByGiai("g7");
            lichSuDoSo.setTienThuong(tienThuong);
        }else if(mess.equalsIgnoreCase("giải 8")) {
            tienThuong = tienThuongRepository.findByGiai("g8");
            lichSuDoSo.setTienThuong(tienThuong);
        }
        return lichSuDoSo;
    }

    // ham set tien thuong doi voi nguoi chua dang ký thành viên muốn xem dò vé số
    public TienThuong setMessResultDoVeSo(String mess){
        TienThuong tienThuong = new TienThuong();
        if(mess.equalsIgnoreCase("giải nhất")){
            tienThuong = tienThuongRepository.findByGiai("g1");
        }else if(mess.equalsIgnoreCase("giải hai")){
            tienThuong = tienThuongRepository.findByGiai("g2");

        }else if(mess.equalsIgnoreCase("giải đặc biệt")){
            tienThuong = tienThuongRepository.findByGiai("db");

        }else if(mess.equalsIgnoreCase("giải ba")) {
            tienThuong = tienThuongRepository.findByGiai("g3");

        }else if(mess.equalsIgnoreCase("giải 4")) {
            tienThuong = tienThuongRepository.findByGiai("g4");

        }else if(mess.equalsIgnoreCase("giải 5")){
            tienThuong = tienThuongRepository.findByGiai("g5");

        }else if(mess.equalsIgnoreCase("giải 6")) {
            tienThuong = tienThuongRepository.findByGiai("g6");

        }else if(mess.equalsIgnoreCase("giải 7")) {
            tienThuong = tienThuongRepository.findByGiai("g7");

        }else if(mess.equalsIgnoreCase("giải 8")) {
            tienThuong = tienThuongRepository.findByGiai("g8");

        }
        return tienThuong;

    }

}
