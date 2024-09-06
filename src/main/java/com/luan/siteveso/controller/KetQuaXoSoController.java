package com.luan.siteveso.controller;

import com.luan.siteveso.dao.DaiXoSoRepository;
import com.luan.siteveso.dao.KetQuaXoSoRepository;
import com.luan.siteveso.dao.UserRepository;
import com.luan.siteveso.date.DateSql;
import com.luan.siteveso.entity.DaiXoSo;
import com.luan.siteveso.entity.KetQuaXoSo;
import com.luan.siteveso.entity.LichSuSua;

import com.luan.siteveso.entity.User;
import com.luan.siteveso.jsonAjax.JsonKqXsSelectByTenDai;
import com.luan.siteveso.jsonAjax.JsonKqxsDeletes;

import com.luan.siteveso.service.KetQuaXoSoServiceImpl;
import com.luan.siteveso.service.LichSuSuaServiceImpl;
import com.luan.siteveso.service.UserServiceImpl;
import com.luan.siteveso.validation.KqXsValidation;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/ad")
public class KetQuaXoSoController {
    private KetQuaXoSoServiceImpl ketQuaXoSoServiceImpl;
    private KetQuaXoSoRepository ketQuaXoSoRepository;
    private LichSuSuaServiceImpl lichSuSuaServiceImpl;
    private JsonKqxsDeletes jsonKqxsDeletes;
    private DaiXoSoRepository daiXoSoRepository;
    private UserServiceImpl userServiceImpl;
    private JsonKqXsSelectByTenDai jsonKqXsSelectByTenDai;
    private DateSql dateSql;
    private UserRepository userRepository;
    @Autowired
    public KetQuaXoSoController(KetQuaXoSoServiceImpl ketQuaXoSoServiceImpl, KetQuaXoSoRepository ketQuaXoSoRepository,
                                LichSuSuaServiceImpl lichSuSuaServiceImpl,JsonKqxsDeletes jsonKqxsDeletes,
                                DaiXoSoRepository daiXoSoRepository,UserServiceImpl userServiceImpl,
                                JsonKqXsSelectByTenDai jsonKqXsSelectByTenDai,DateSql dateSql,UserRepository userRepository) {
        this.ketQuaXoSoServiceImpl = ketQuaXoSoServiceImpl;
        this.ketQuaXoSoRepository = ketQuaXoSoRepository;
        this.lichSuSuaServiceImpl = lichSuSuaServiceImpl;
        this.jsonKqxsDeletes = jsonKqxsDeletes;
        this.daiXoSoRepository = daiXoSoRepository;
        this.userServiceImpl = userServiceImpl;
        this.jsonKqXsSelectByTenDai = jsonKqXsSelectByTenDai;
        this.dateSql = dateSql;
        this.userRepository = userRepository;
    }

    // hiển thị danh sách kết quả xổ số 8 phần tử 1 trang
    @GetMapping("/showListKq")
    public String getListKq(Model model, @ModelAttribute("mess") String mess,
                            @ModelAttribute("page") String page){
        Page<KetQuaXoSo> listKq = null;
        // kiem trang phan trang gui len de lay du lieu theo so phan trang gui len
        if(page.equalsIgnoreCase("") || page.equalsIgnoreCase("1")){
            // lay du lieu theo page,sap xep hien thi theo ten tang dan
            listKq = ketQuaXoSoServiceImpl.getKetQuaXoSo(0,8);
        }else {
            int number = Integer.parseInt(page);
            // lay du lieu theo page,sap xep hien thi theo ten tang dan
            listKq = ketQuaXoSoServiceImpl.getKetQuaXoSo(number-1,8);
        }

        // list pageNum chua so page can hien thi
        List<Integer> pageNum = new ArrayList<>();
        for (Integer i = 1 ; i <= listKq.getTotalPages();i++) pageNum.add(i);

        // send data to html
        model.addAttribute("listKq",listKq);
        // send so trang den html
        model.addAttribute("pageNum",pageNum);
        model.addAttribute("mess",mess);
        // lay danh sach dai ve so
        List<DaiXoSo> listDaiVeSo = daiXoSoRepository.findAll();
        // gửi danh sách đài vé số đến trang html
        model.addAttribute("listDaiVeSo",listDaiVeSo);

        return "/ketqua/showKetQua";
    }

    // xóa 1 phần tử theo id truyền lên
    @GetMapping("/kq/delete")
    public String deleteById(@RequestParam("id") int id, RedirectAttributes redirectAttributes){
        System.out.println("id nhan duoc :"+ id);
        //  biến check kiểm tra xóa thành công hay thất bại
        Boolean check = ketQuaXoSoServiceImpl.deleteById(id);
        if(check){
            redirectAttributes.addAttribute("mess","Xóa thành công");
        }else{
            redirectAttributes.addAttribute("mess","Xóa thất bại");
        }

        return "redirect:/ad/showListKq";
    }

    // hiển trị theo số phân trang gửi lên
    @GetMapping("/kq/phantrang")
    public String pagination(@RequestParam("page") String page,RedirectAttributes redirectAttributes){

        redirectAttributes.addAttribute("page",page);
        return "redirect:/ad/showListKq";

    }
    // xem chi tiết 1 kết quả xổ số về ngày tạo, ai tạo, ngày sửa, ai sửa
    @GetMapping("/kq/chitiet")
    public String detailOneResultXoSo(@RequestParam("id") int id, Model model){
        try {
            // lay danh sach kqxs theo id
            KetQuaXoSo ketQuaXoSo = ketQuaXoSoServiceImpl.getKetQuaXoSoById(id);
            model.addAttribute("kqxs",ketQuaXoSo);
            // lay danh sách sửa kqxs
            List<LichSuSua> lichSuSuas = lichSuSuaServiceImpl.getLichSuSuaByIdKq(id);
            model.addAttribute("lss",lichSuSuas);
        }catch (Exception e){
            throw (e);
        }

        return "/ketqua/detailKetQua";
    }

    // xoa nhieu user bang Ajax theo mang id truyen len
    @PostMapping("/kq/xoa-nhieu")
    @ResponseBody
    public JsonKqxsDeletes deleteManyUser(@RequestBody Integer[] arr){
        // trả về true/fale khi xóa , dung biến này để check để add các đối tượng ứng vào object JsonUserDeletes
        Boolean check = ketQuaXoSoServiceImpl.deletesByArrId(arr);
        System.out.println("xoa toi day chua" + check);
        Page<KetQuaXoSo> listKqPage = null;
        Integer totalPage = 0;
        String mess = "Xóa thất bại";
        if(check){
            // lay 8 data kqxs, du lieu theo page,sap xep hien thi theo ngày xổ số giảm dần
            listKqPage = ketQuaXoSoServiceImpl.getKetQuaXoSo(0,8);
            System.out.println("toi day nua chua");
            totalPage = listKqPage.getTotalPages();
            mess = "Xóa thành công";
        }
        // add các biến đã tạo trên vào đối tượng class java jsonKqxsDeletes tương ứng với các field
        jsonKqxsDeletes.setKetQuaXoSos(listKqPage);
        jsonKqxsDeletes.setTotalPage(totalPage);
        jsonKqxsDeletes.setMess(mess);
        System.out.println("doi tuong json nhan duoc sau khi check :"+ jsonKqxsDeletes);
        return jsonKqxsDeletes;


    }




    // cập nhật kết quả sổ xố
    @GetMapping("/kq/update")
    public String updateKqXs(@RequestParam("id") int id,Model model){
        //1: lấy kqxs theo id gửi lên
        KetQuaXoSo ketQuaXoSo = null;
        ketQuaXoSo = ketQuaXoSoServiceImpl.getKetQuaXoSoById(id);
        KqXsValidation kqXsValidation = new KqXsValidation();
        List<DaiXoSo> listDaiXoSos = daiXoSoRepository.findAll();
        //2: kiểm tra kqxs có lấy được từ database hay không , nếu lấy được thì set vào Object kqXsValidation
        if(ketQuaXoSo !=null){
        //3: set dữ liệu vào Object KaSxValidation
            kqXsValidation.setIdKqXs(ketQuaXoSo.getId());
            kqXsValidation.setTenDai(ketQuaXoSo.getDaiXoSo().getNameLottery());
            kqXsValidation.setG8(ketQuaXoSo.getG8());
            kqXsValidation.setG7(ketQuaXoSo.getG7());
            kqXsValidation.setG61(ketQuaXoSo.getG61());
            kqXsValidation.setG62(ketQuaXoSo.getG62());
            kqXsValidation.setG63(ketQuaXoSo.getG63());
            kqXsValidation.setG5(ketQuaXoSo.getG5());
            kqXsValidation.setG41(ketQuaXoSo.getG41());
            kqXsValidation.setG42(ketQuaXoSo.getG42());
            kqXsValidation.setG43(ketQuaXoSo.getG43());
            kqXsValidation.setG44(ketQuaXoSo.getG44());
            kqXsValidation.setG45(ketQuaXoSo.getG45());
            kqXsValidation.setG46(ketQuaXoSo.getG46());
            kqXsValidation.setG47(ketQuaXoSo.getG47());
            kqXsValidation.setG31(ketQuaXoSo.getG31());
            kqXsValidation.setG32(ketQuaXoSo.getG32());
            kqXsValidation.setG2(ketQuaXoSo.getG2());
            kqXsValidation.setG1(ketQuaXoSo.getG1());
            kqXsValidation.setDb(ketQuaXoSo.getDb());
            kqXsValidation.setDateXoSo(ketQuaXoSo.getDateLottery());

        }
        //4: gửi object giữ dữ liệu KqXsValidation va du lieu dai lên page html
        model.addAttribute("kqXs",kqXsValidation);
        model.addAttribute("listDaiXoSo",listDaiXoSos);
        return "/ketqua/update-kqxs";
    }

    // xoa khoang trang người dùng nhập vào
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    // update dữ liệu gửi lên
    @PostMapping("/cap_nhat_kq_xs")
    public String updateKqXs(
            @Valid @ModelAttribute("kqXs") KqXsValidation kqXs,
            BindingResult bindingResult, Model model,Principal principal){

        // 1: lay kqxs tu database theo idkqxs nhan duoc
        KetQuaXoSo ketQuaXoSo = ketQuaXoSoServiceImpl.getKetQuaXoSoById(kqXs.getIdKqXs());

        // kiểm tra có lỗi xảy ra trả lại trang update-form.html
        if(bindingResult.hasErrors()){
            List<DaiXoSo> daiXoSos = daiXoSoRepository.findAll();
            model.addAttribute("listDaiXoSo",daiXoSos);

            return "/ketqua/update-kqxs";
        }
        // 2: set cac thuoc tinh nhan duoc tu trang giao dien gui len cho vao doi tuong kqxs
        // lay Dai so xo theo ten dai nhan duoc tu giao dien
        DaiXoSo daiXoSo1 = daiXoSoRepository.findByNameLottery(kqXs.getTenDai());
        // set dai xo so
        ketQuaXoSo.setDaiXoSo(daiXoSo1);
        ketQuaXoSo.setG8(kqXs.getG8());
        ketQuaXoSo.setG7(kqXs.getG7());
        ketQuaXoSo.setG61(kqXs.getG61());
        ketQuaXoSo.setG62(kqXs.getG62());
        ketQuaXoSo.setG63(kqXs.getG63());
        ketQuaXoSo.setG5(kqXs.getG5());
        ketQuaXoSo.setG41(kqXs.getG41());
        ketQuaXoSo.setG42(kqXs.getG42());
        ketQuaXoSo.setG43(kqXs.getG43());
        ketQuaXoSo.setG44(kqXs.getG44());
        ketQuaXoSo.setG45(kqXs.getG45());
        ketQuaXoSo.setG46(kqXs.getG46());
        ketQuaXoSo.setG47(kqXs.getG47());
        ketQuaXoSo.setG31(kqXs.getG31());
        ketQuaXoSo.setG32(kqXs.getG32());
        ketQuaXoSo.setG2(kqXs.getG2());
        ketQuaXoSo.setG1(kqXs.getG1());
        ketQuaXoSo.setDb(kqXs.getDb());
        ketQuaXoSo.setDateLottery(kqXs.getDateXoSo());
        // 3: update du lieu moi vao database
        KetQuaXoSo ketQuaXoSo1 = ketQuaXoSoServiceImpl.save(ketQuaXoSo);

        // 4: cap nhap thong tin nguoi sua vao bang lichsusua chua lam xong
        // 4:1 : lay thong tin admin da xac thuc tu spring security
        String userName = principal.getName();
        // 4:2 : lay thong tin admin se sua cai kqxs nay
        User user = userRepository.findByUserName(userName);
        // 4:3 tao doi tuong cua table lich su sua
        LichSuSua lichSuSua = new LichSuSua();
        // 4:4 set cac truong co lien quan den doi tuong lichSuSua tren
        lichSuSua.setUser(user);
        lichSuSua.setNgaySua(dateSql.getDateSqlCurrent());
        lichSuSua.setIdKq(ketQuaXoSo1.getId());
        // 4:5 cập nhật người sửa và id kết quả sửa vào bảng lịch sử sửa
        lichSuSua = lichSuSuaServiceImpl.save(lichSuSua);
        return "redirect:/ad/showListKq";
    }
    // dieu huong den trang them kqxs
    @GetMapping("/add-kqxs")
    public String addKqXs(Model model){
        try {
            // lay dai ve so , de hien thi ten dai
            List<DaiXoSo> listDaiVeSo = daiXoSoRepository.findAll();
            model.addAttribute("listDaiXoSo",listDaiVeSo);
        }catch (Exception e){
            throw (e);
        }

        model.addAttribute("kqXs",new KqXsValidation());
        return "/ketqua/add-kqxs";
    }
    // thêm kqxs vào database
    @PostMapping("/them_kq_xs")
    public String addKqXsToDatabase(Model model, @Valid @ModelAttribute("kqXs") KqXsValidation kqXsValidation,
                                    BindingResult bindingResult, Principal principal){
        List<DaiXoSo> listDaiVeSo = daiXoSoRepository.findAll();
        // check ngày tháng năm nhận được có null hay không
        Boolean check = Objects.isNull(kqXsValidation.getDateXoSo());
        // 1: kiểm tra validation các trường nếu có chuyển lại trang update
        if(bindingResult.hasErrors() ||check){
            model.addAttribute("tenDaiCurrent",kqXsValidation.getTenDai());
            model.addAttribute("listDaiXoSo",listDaiVeSo);
            if(check) model.addAttribute("error_date","Ngày tháng năm không bỏ trống");
            return "/ketqua/add-kqxs";
        }
        // 2: thực hiện cập nhật thông kqsx lên database
        try {
            // kiem tra thong tin ten dai va ngay xo so da ton tai trong he thong thi hien thong bao dai da
            // co trong he thong
            List<KetQuaXoSo> ketQuaXoSoList = ketQuaXoSoServiceImpl.getKqXsByTenDaiAndDateXs(kqXsValidation.getTenDai(),kqXsValidation.getDateXoSo());
            if(ketQuaXoSoList.size() > 0){
                model.addAttribute("errorAddFail","Đài "+kqXsValidation.getTenDai()+" xổ số ngày "
                        +kqXsValidation.getDateXoSo()+" đã tồn tại trong hệ thống");
                model.addAttribute("kqXs",kqXsValidation);
                model.addAttribute("tenDaiCurrent",kqXsValidation.getTenDai());
                model.addAttribute("listDaiXoSo",listDaiVeSo);
                return "/ketqua/add-kqxs";
            }
            KetQuaXoSo ketQuaXoSo = new KetQuaXoSo();
            // 3: set cac thuoc tinh ketqua xo so
            ketQuaXoSo.setG8(kqXsValidation.getG8());
            ketQuaXoSo.setG7(kqXsValidation.getG7());
            ketQuaXoSo.setG61(kqXsValidation.getG61());
            ketQuaXoSo.setG62(kqXsValidation.getG62());
            ketQuaXoSo.setG63(kqXsValidation.getG63());
            ketQuaXoSo.setG5(kqXsValidation.getG5());
            ketQuaXoSo.setG41(kqXsValidation.getG41());
            ketQuaXoSo.setG42(kqXsValidation.getG42());
            ketQuaXoSo.setG43(kqXsValidation.getG43());
            ketQuaXoSo.setG44(kqXsValidation.getG44());
            ketQuaXoSo.setG45(kqXsValidation.getG45());
            ketQuaXoSo.setG46(kqXsValidation.getG46());
            ketQuaXoSo.setG47(kqXsValidation.getG47());
            ketQuaXoSo.setG31(kqXsValidation.getG31());
            ketQuaXoSo.setG32(kqXsValidation.getG32());
            ketQuaXoSo.setG2(kqXsValidation.getG2());
            ketQuaXoSo.setG1(kqXsValidation.getG1());
            ketQuaXoSo.setDb(kqXsValidation.getDb());
            ketQuaXoSo.setDateLottery(kqXsValidation.getDateXoSo());
            ketQuaXoSo.setDateCreated(new DateSql().getDateSqlCurrent());
            // set dai xo so
            DaiXoSo daiXoSo = daiXoSoRepository.findByNameLottery(kqXsValidation.getTenDai());
            ketQuaXoSo.setDaiXoSo(daiXoSo);
            // 3 : lay thong tin admin da xac thuc thu spring security cap nhap tuong ung voi nguoi luu
            // lay thong tin admin da dang nhap bang user
            String userName = principal.getName();
            // lay thong tin admin theo user name nhan duoc
            User user = userRepository.findByUserName(userName);
            // set user da tao kqxs nay co lien quan den kqxs
            ketQuaXoSo.setUser(user);
            // cập nhập dữ liệu lên database
            ketQuaXoSo = ketQuaXoSoServiceImpl.save(ketQuaXoSo);
            System.out.println("Thong tin ket qua xo da update ");
            System.out.println(ketQuaXoSo);
            return "redirect:/ad/showListKq";

        }catch (Exception e){
            model.addAttribute("listDaiXoSo",listDaiVeSo);
            model.addAttribute("tenDaiCurrent",kqXsValidation.getTenDai());
            // 4: thêm không thành công gửi lỗi lại form add
            model.addAttribute("errorAddFail","Thêm không thành công");
            return "/ketqua/add-kqxs";
        }

    }

    // json lay du lieu theo nut select ten dai
    @PostMapping ("/kq/json-select-tenDai")
    @ResponseBody
    public JsonKqXsSelectByTenDai getDataSelectByTenDai(@RequestBody DaiXoSo daiXoSo){
        String tenDai = daiXoSo.getNameLottery();
        // step 1: lay 8 ket qua xo so theo ten dai truyen len
        List<KetQuaXoSo> ketQuaXoSoList = ketQuaXoSoServiceImpl.get8KetQuaXoSoByTenDai(tenDai,1,8);

        // bien giu totalPage
        int totalPage = 0;
        // step 2: kiem tra co lay duoc du lieu khong
        if(ketQuaXoSoList.size() != 0){
            // step3: khi da co du lieu can tong so trang kqxs theo ten dai de phan trang
           int totalKqXsByTenDai = ketQuaXoSoServiceImpl.countKqXsByTenDai(tenDai);
           int pageSize = 8 ;
           totalPage = (int)Math.floor(totalKqXsByTenDai/pageSize)+1;
        }
        // step 4: set du lieu len Object JsonKqXsSelectByTenDai, sau do gui Object nay ve cho frond end xu ly hien thi

        jsonKqXsSelectByTenDai.setKetQuaXoSoList(ketQuaXoSoList);
        jsonKqXsSelectByTenDai.setTotalPage(totalPage);
        return jsonKqXsSelectByTenDai;
    }
    // nút phân trang select_Dai khi nhan vao lay data theo ten dai va theo so trang truyen len
    @GetMapping("/kq/select_phantrang")
    public String showDataByTenDai(@RequestParam("page") int numPage,@RequestParam("tenDai") String tenDai,Model model){
        // lấy đài vé sô de hien thi ten dai tren nut select
        List<DaiXoSo> daiXoSoList = daiXoSoRepository.findAll();
        // lay ket qua xo so theo so trang truyen len va theo ten dai truyen len
        List<KetQuaXoSo> ketQuaXoSoList = ketQuaXoSoServiceImpl.get8KetQuaXoSoByTenDai(tenDai,numPage,8);
        int totalKqXs = ketQuaXoSoServiceImpl.countKqXsByTenDai(tenDai);
        int pageSize = 8;
        int totalPage = (int)Math.floor(totalKqXs/pageSize) + 1;
        List<Integer> pageNumber = new ArrayList<>();
        for (int i =1; i <=totalPage; i++){
            pageNumber.add(i);
        }

        // gui data len trang html
        model.addAttribute("listDaiVeSo",daiXoSoList);
        model.addAttribute("listKq",ketQuaXoSoList);
        model.addAttribute("pageNum",pageNumber);
        model.addAttribute("tenDai",tenDai);

        return "/ketqua/show_phan_trang_ten_dai.html";
    }

    // tim kiem kqxs theo ten dai va ngay xo so
    @GetMapping("/ketqua/tenDai_ngay_Xo_So")
    public String getKqXsByTenDaiAndDateXoSo(@RequestParam("tenDai") String tenDai,
                                             @RequestParam("date") String dateXs,Model model){

        // lấy đài vé sô de hien thi ten dai tren nut select
        List<DaiXoSo> daiXoSoList = daiXoSoRepository.findAll();
        // lay ket qua xo so theo so trang truyen len va theo ten dai truyen len
        List<KetQuaXoSo> ketQuaXoSoList = ketQuaXoSoServiceImpl.getKqXsByTenDaiAndDateXs(tenDai,dateSql.parseStringToDateSql(dateXs));

        List<Integer> pageNum = new ArrayList<>();
        // gui cho the input ten dai
        model.addAttribute("tenDai",tenDai); // ten dai
        model.addAttribute("dateXoSo",dateXs);// ngay xo so
        model.addAttribute("listDaiVeSo",daiXoSoList); // liet daixoso
        model.addAttribute("listKq", ketQuaXoSoList);// listKqXs de hien thi
        model.addAttribute("pageNum",pageNum); // phan trang
        return "/ketqua/search_by_ten_dai_and_date_xo_so";
    }
}
