package com.luan.siteveso.controller;

import com.luan.siteveso.dao.LichSuDoSoRepository;

import com.luan.siteveso.entity.DaiXoSo;
import com.luan.siteveso.entity.LichSuDoSo;


import com.luan.siteveso.service.DaiVeSoServiceImpl;
import com.luan.siteveso.service.LichSuDoSoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LichSuDoVeSoController {
    private LichSuDoSoServiceImpl lichSuDoSoServiceImpl;
    private LichSuDoSoRepository lichSuDoSoRepository;
    private DaiVeSoServiceImpl daiVeSoServiceImpl;
    @Autowired
    public LichSuDoVeSoController(LichSuDoSoServiceImpl lichSuDoSoServiceImpl,DaiVeSoServiceImpl daiVeSoServiceImpl,
                                  LichSuDoSoRepository lichSuDoSoRepository) {
        this.lichSuDoSoServiceImpl = lichSuDoSoServiceImpl;
        this.lichSuDoSoRepository = lichSuDoSoRepository;
        this.daiVeSoServiceImpl = daiVeSoServiceImpl;

    }
    // hien thi page dau tien khi load
    @GetMapping("/ad/lich-su-do-so")
    public String showPageLichSuDoSo(Model model,@ModelAttribute("mess") String mess,
                                     @ModelAttribute("page") String page){
        Page<LichSuDoSo> listLsDs = null;
        // kiem trang phan trang gui len de lay du lieu theo so phan trang gui len
        if(page.equalsIgnoreCase("") || page.equalsIgnoreCase("1")){
            // lay du lieu theo page,sap xep hien thi theo ten tang dan
            listLsDs = lichSuDoSoServiceImpl.getLichSuDoSo(0,8);
        }else {
            int number = Integer.parseInt(page);
            // lay du lieu theo page,sap xep hien thi theo ten tang dan
            listLsDs = lichSuDoSoServiceImpl.getLichSuDoSo(number-1,8);
        }

        // list pageNum chua so page can hien thi
        List<Integer> pageNum = new ArrayList<>();
        for (Integer i = 1 ; i <= listLsDs.getTotalPages();i++) pageNum.add(i);

        // send data to html
        model.addAttribute("listLsDs",listLsDs);
        // send so trang den html
        model.addAttribute("pageNum",pageNum);
        model.addAttribute("mess",mess);


        return "/LichSuDoSo/showLichSuDoSo";
    }
    // hiển trị theo số phân trang gửi lên
    @GetMapping("/ad/lich-su-do-so/phantrang")
    public String pagination(@RequestParam("page") String page, RedirectAttributes redirectAttributes){

        redirectAttributes.addAttribute("page",page);
        return "redirect:/ad/lich-su-do-so";

    }
    // xóa 1 phần tử theo id truyền lên
    @GetMapping("/ad/lich-su-do-so/delete")
    public String deleteById(@RequestParam("id") int id, RedirectAttributes redirectAttributes){
        System.out.println("id nhan duoc :"+ id);
        //  biến check kiểm tra xóa thành công hay thất bại
        Boolean check = lichSuDoSoServiceImpl.deleteById(id);
        if(check){
            redirectAttributes.addAttribute("mess","Xóa thành công");
        }else{
            redirectAttributes.addAttribute("mess","Xóa thất bại");
        }

        return "redirect:/ad/lich-su-do-so";
    }

    // xoa nhieu user bang Ajax theo mang id truyen len
    @PostMapping("/ad/lich-su-do-so/xoa-nhieu")
    @ResponseBody
    public Page<LichSuDoSo> deleteManyUser(@RequestBody Integer[] arr){
        // trả về true/fale khi xóa , dung biến này để check để add các đối tượng ứng vào object JsonUserDeletes
        Boolean check = lichSuDoSoServiceImpl.deletesByArrId(arr);
        Page<LichSuDoSo> listLichSuDoSos= null;
        if(check){
            // lay 8 data kqxs, du lieu theo page,sap xep hien thi theo ngày xổ số giảm dần
            listLichSuDoSos = lichSuDoSoServiceImpl.getLichSuDoSo(0,8);
            for (LichSuDoSo ls :listLichSuDoSos // set gia tri de gui len ui ko lo thong tin
                    ) {
                ls.getUser().setPassword("123");
                ls.getUser().setEmail("kk");
                ls.getUser().getRole().setRole("luan gia");
            }
        }

        return listLichSuDoSos;

    }
    // bieu dien lich su do so
    @GetMapping("/us/lich-su-do")
    public String getLichSuDoSoByUserName(Model model, Principal principal, Authentication authentication){
        // 1: lay dai ve so , de hien thi dai ve khu vuc
        // a: lấy khu vực
        List<String> khuVucList =daiVeSoServiceImpl.getDistinctByKhuVuc();
        // b: lấy tất cả đài vé số
        List<DaiXoSo> daiXoSoList = daiVeSoServiceImpl.getAll();
        // c: gửi lên modal hiển thị lên slideLeft
        model.addAttribute("khuVucList",khuVucList);
        model.addAttribute("daiXoSoList",daiXoSoList);
        // kiem tra username nhan duoc tu object da xac thuc trong spring security la outhentoken
        // thi thuc hien lay du lieu theo bang lich su so ngoai ra thi lay du lieu theo user
        List<LichSuDoSo> lichSuDoSoList = new ArrayList<>();
        if(principal instanceof OAuth2AuthenticationToken){
            OAuth2User oauthUser = ((OAuth2AuthenticationToken) authentication).getPrincipal();
            // Lấy username từ OAuth2User
            String social = oauthUser.getAttribute("name");
            if(social !=null)lichSuDoSoList = lichSuDoSoServiceImpl.getLichSuDoSoBySocialDesc(social);

        }else{
            String userName = principal.getName();
            if(userName !=null){
                // lay danh sach lich su do so theo username
                lichSuDoSoList = lichSuDoSoServiceImpl.getLichSuDoSoByUserName(userName);
            }
        }

        model.addAttribute("lichSuDoSoList",lichSuDoSoList);
        return "/trangchu/lich-su-do-ve";
    }
    @GetMapping("/us/lich-su-do-so/xoa_boi_nguoi_dung")
    public String deleteLichSuDoSoById(@RequestParam("id") int id){

        // 1: lay du lieu LichSuXoSo theo id truyen len
        LichSuDoSo lichSuDoSo = lichSuDoSoRepository.getReferenceById(id);
        if(lichSuDoSo != null){
            // 2: set ve so thanh status da xóa
            lichSuDoSo.setStatus("đã xóa");
            // 3: cap nhat lai database
            lichSuDoSoRepository.save(lichSuDoSo);
        }
        return "redirect:/us/lich-su-do";
    }


}
