package com.luan.siteveso.controller;


import com.luan.siteveso.entity.DaiXoSo;

import com.luan.siteveso.service.DaiVeSoServiceImpl;
import com.luan.siteveso.validation.DaiVeSoValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/ad")
public class DaiVeSoController {
    private DaiVeSoServiceImpl daiVeSoServiceImpl;
    @Autowired
    public DaiVeSoController(DaiVeSoServiceImpl daiVeSoServiceImpl) {
        this.daiVeSoServiceImpl = daiVeSoServiceImpl;
    }

    // lay tat ca du lieu Dai ve so va hien thi len trang dieu khien
    @GetMapping("/dai-ve-so")
    public String getAllDaiVeSo(Model model, @ModelAttribute("mess") String mess){
        // lay tat va danh sách dai ve so trong database
        List<DaiXoSo> daiXoSoList = new ArrayList<>();
        try {
            daiXoSoList = daiVeSoServiceImpl.getAll();
        }catch (Exception e){

        }
        // gửi danh sách đài vé lên trang html hiển thị
        model.addAttribute("daiXoSoList",daiXoSoList);
        model.addAttribute("mess",mess);
        return "/daiveso/daiveso";
    }

    // xao mot thuc the dai ve theo id nhan duoc
    @GetMapping("/xoa-dai-ve-so")
    public String deleteDaiVeSoById(@RequestParam("id") int id, RedirectAttributes redirectAttributes){
        Boolean check = false;
        // xoa dai ve so theo id truyen vao
        try {
            check = daiVeSoServiceImpl.deleteDaiVeSoById(id);
        }catch (Exception e){
            redirectAttributes.addAttribute("mess","Xóa thất bại");
            return "redirect:/ad/dai-ve-so";
        }
        String mess =""; // giữ tin nhắn xóa thành công hay thất bại
        if(check){
            mess = "Xóa thành công";
        }else {
            mess = "Xóa thất bại";
        }
        redirectAttributes.addAttribute("mess",mess);
        return "redirect:/ad/dai-ve-so";
    }

    // thuc hien tim kiem theo ten dai khu vuc
    @GetMapping("/tim-ten-dai-khu-vuc")
    public String searchByTenDaiOrKhuVuc(@RequestParam("daiVeSo") String daiOrKhuVuc,Model model){
        // list chứa dữ liệu
        List<DaiXoSo> daiXoSoList = new ArrayList<>();
        // lay du lieu theo tên đài or khu vực truyền lên
        try {
            // lấy dữ liệu theo tên đài
            daiXoSoList = daiVeSoServiceImpl.getDaiVeSoByTenDai(daiOrKhuVuc);
            // kiểm tra có lấy được dũ liệu theo tên đài không , nếu không lấy được tiến hành lấy dữ liệu theo khu vực
            if(daiXoSoList.size() == 0){
                daiXoSoList = daiVeSoServiceImpl.getDaiVeSoByKhuVuc(daiOrKhuVuc);
            }
        }catch (Exception e){
            model.addAttribute("daiXoSoList",daiXoSoList);
            return "/daiveso/daiveso";
        }
        model.addAttribute("daiXoSoList",daiXoSoList);
        return "/daiveso/daiveso";
    }

    // xoa nhieu dai ve so theo mang id truyen len
    @PostMapping("/xoa-nhieu-dai-ve-so")
    @ResponseBody
    public List<DaiXoSo> deleteManyDaiVeSoByArrId(@RequestBody int[] arrId){
        List<DaiXoSo> daiXoSoList = new ArrayList<>();
        try {
            Boolean check = false;
            // xoa dai ve so theo mang id truyen vao
            check = daiVeSoServiceImpl.deleteDaiVeSosByArrayId(arrId);
            // kiểm tra xóa được cả mảng thì lấy tất cả dữ liệu sau đến gửi đến ui
            if(check) daiXoSoList=daiVeSoServiceImpl.getAll();
            return daiXoSoList;
        }catch (Exception e){
            return daiXoSoList;
        }

    }



    // hien thi trang cap nhat dai ve so
    // 1. kiem tra id gui len co ton tai tron database khong
    // 2. lay du lieu trong database theo id va set model gui den trang frontend
    @GetMapping("/sua-dai-ve-so")
    public String updateUserById(@RequestParam("id") Integer id, Model theModel){
        // lấy list chứa khu vực để gửi dữ liệu hiển thị về frontend
        List<String> khuVucList = daiVeSoServiceImpl.getDistinctByKhuVuc();

        // 1 : kiem tra id co ton tai trong database khong
        DaiXoSo daiXoSo = daiVeSoServiceImpl.getById(id);
        if(daiXoSo == null){
            theModel.addAttribute("daiVeSo",new DaiVeSoValidation());
            theModel.addAttribute("errorUpdate","Đài vé số không tồn tại");
            theModel.addAttribute("listKhuVuc",khuVucList);
            return "/daiveso/update-dai-xo-so";

        }
        // 2 : id có tồn tại trong database , set dữ liệu vào Object DaiVeSoValidation sau đó gửi đến frontend để hiển thị
        DaiVeSoValidation daiVeSoValidation = new DaiVeSoValidation();
        // 3 : set du lieu cho object DaiVeSoValidation
        daiVeSoValidation.setId(daiXoSo.getId());
        daiVeSoValidation.setTenDai(daiXoSo.getNameLottery());
        daiVeSoValidation.setKhuVuc(daiXoSo.getAria());
        // 4: gửi dữ liệu đền frontend
        theModel.addAttribute("daiVeSo",daiVeSoValidation);
        theModel.addAttribute("listKhuVuc",khuVucList);
        return "/daiveso/update-dai-xo-so";

    }
    // xoa khoang trang người dùng nhập vào
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
    // update dai ve so
    @PostMapping("/update-dai-ve-so")
    public String updateDaiVeSo( @Valid @ModelAttribute("daiVeSo") DaiVeSoValidation daiVeSoValidation,
                                BindingResult bindingResult,Model model){
        // 1: lay danh sach khu vuc có trong database để hiển thị len ui
        List<String> listKhuVuc = daiVeSoServiceImpl.getDistinctByKhuVuc();
        // 2: check có lỗi validation
        if(bindingResult.hasErrors()){
            // gửi lại giá trị nguời dùng đã nhập vào
            model.addAttribute("daiVeSo",daiVeSoValidation);
            // gửi danh sách khu vực tới ui
            model.addAttribute("listKhuVuc",listKhuVuc);
            return "/daiveso/update-dai-xo-so";
        }
        // 3 : không có lỗi thực hiện cập nhập
        DaiXoSo daiXoSo = null;
        try {
            // 4: check id truyen vao co ton tai khong
            daiXoSo = daiVeSoServiceImpl.getById(daiVeSoValidation.getId());
            // 5: kiểm tra id không tồn tại
            if(daiXoSo == null){
                model.addAttribute("errorUpdate","Cập nhật đài vé số không thành công");
                // gửi lại giá trị nguời dùng đã nhập vào
                model.addAttribute("daiVeSo",daiVeSoValidation);
                // gửi danh sách khu vực tới ui
                model.addAttribute("listKhuVuc",listKhuVuc);
                return "/daiveso/update-dai-xo-so";
            }
            // 6: id truyền vào có tồn tại thực hiện set giá trị và lưu
            daiXoSo.setNameLottery(daiVeSoValidation.getTenDai());
            daiXoSo.setAria(daiVeSoValidation.getKhuVuc());
            // lưu kết quả vào cơ sở dữ liệu
            daiXoSo = daiVeSoServiceImpl.saveDaiXoSo(daiXoSo);
            // 7: kiểm tra có lưu thành công không
            if(daiXoSo == null){
                model.addAttribute("errorUpdate","Cập nhật đài vé số không thành công");
                // gửi lại giá trị nguời dùng đã nhập vào
                model.addAttribute("daiVeSo",daiVeSoValidation);
                // gửi danh sách khu vực tới ui
                model.addAttribute("listKhuVuc",listKhuVuc);
                return "/daiveso/update-dai-xo-so";
            }
            // 8: cập nhật thành công điều hướng đến trang quản lý đài xổ số
            return "redirect:/ad/dai-ve-so";

        }catch (Exception e){
            // 9: xảy ra ngoại lệ thì gửi lỗi lại trang cập nhật
            model.addAttribute("errorUpdate","Cập nhật đài vé số không thành công");
            // gửi lại giá trị nguời dùng đã nhập vào
            model.addAttribute("daiVeSo",daiVeSoValidation);
            // gửi danh sách khu vực tới ui
            model.addAttribute("listKhuVuc",listKhuVuc);
            return "/daiveso/update-dai-xo-so";
        }

    }
    // điều hướng màn hình thêm mới đài vé số
    @GetMapping("/add-dai-xo-so")
    public String addNewDaiXoSo(Model  model){
        //1: lấy list khu vực
        List<String> listKhuVuc = daiVeSoServiceImpl.getDistinctByKhuVuc();
        //2: gửi thông tin lên modal
        model.addAttribute("listKhuVuc",listKhuVuc);
        model.addAttribute("daiVeSo", new DaiVeSoValidation());
        return "/daiveso/add-dai-xo-so";
    }
    // thêm mới đài vé số
    @PostMapping("/add-dai-ve-so")
    public String addNewDaiVeSo(@Valid @ModelAttribute("daiVeSo") DaiVeSoValidation daiVeSoValidation,
                                BindingResult bindingResult,Model model){
        // 1: lấy danh sách khu vực để gửi lên ui
        List<String> listKhuVuc = daiVeSoServiceImpl.getDistinctByKhuVuc();
        // 2: check validation
        if(bindingResult.hasErrors()){
            model.addAttribute("daiVeSo", daiVeSoValidation);
            model.addAttribute("listKhuVuc",listKhuVuc);
            return "/daiveso/add-dai-xo-so";
        }
        // 3: không có lỗi validation thực hiện thêm mới vào database
        DaiXoSo daiXoSo = new DaiXoSo();
        // 4: set cá giá trị nhận được từ người dùng vaào object daiXoSo
        daiXoSo.setAria(daiVeSoValidation.getKhuVuc());
        daiXoSo.setNameLottery(daiVeSoValidation.getTenDai());

        // 4: thêm mới vào database
        try {
            daiXoSo = daiVeSoServiceImpl.saveDaiXoSo(daiXoSo);
            // 5: kiểm tra có thêm được không
            if(daiXoSo == null){
                model.addAttribute("daiVeSo", daiVeSoValidation);
                model.addAttribute("listKhuVuc",listKhuVuc);
                model.addAttribute("errorUpdate","Thêm đài vé số không thành công");
                return "/daiveso/add-dai-xo-so";
            }
            // 6: thêm thành công đài số chuyển tiếp đến trang quản lý chính hiển thị dài vé số
            return "redirect:/ad/dai-ve-so";
        }catch (Exception e){
            // 7: xảy ra ngoại lệ trong quá trình thêm thì gửi thông báo thêm thất bại , laod lại màn hình thêm
            model.addAttribute("daiVeSo", daiVeSoValidation);
            model.addAttribute("listKhuVuc",listKhuVuc);
            model.addAttribute("errorUpdate","Thêm đài vé số không thành công");
            return "/daiveso/add-dai-xo-so";
        }
    }
}
