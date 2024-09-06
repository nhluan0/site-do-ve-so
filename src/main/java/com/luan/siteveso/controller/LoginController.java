package com.luan.siteveso.controller;


import com.luan.siteveso.dao.UserRepository;
import com.luan.siteveso.entity.User;
import com.luan.siteveso.password.GenerateRandomPw;
import com.luan.siteveso.service.UserServiceImpl;
import com.luan.siteveso.validation.UserResetPassword;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller

public class LoginController {
    private UserServiceImpl userServiceImpl;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private GenerateRandomPw generateRandomPw;

    @Autowired
    public LoginController(UserServiceImpl userServiceImpl, UserRepository userRepository,
                           PasswordEncoder passwordEncoder,GenerateRandomPw generateRandomPw) {
        this.userServiceImpl = userServiceImpl;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.generateRandomPw = generateRandomPw;
    }

    @GetMapping("/dang-nhap")
    public String login() {
        return "/dangnhap/login";
    }
    @GetMapping("/thanh-cong")
    public String loginSuccess(Principal principal){
        // set trang thai nguoi dung len 1 , tuc la trang thai dang hoat dong
        User user = userRepository.findByUserName(principal.getName());
        if(user != null){
            user.setStatus(1); // set trang thai user đang hoat dong
            userServiceImpl.updateByUser(user);
        }
        return "/dangnhap/page-dieu-huong";
    }
    // hiện trang quên password
    @GetMapping("/us/quen-pw")
    public String showForgetPassWord(){
        return "/dangnhap/forget-password";
    }
    // gửi mật khẩu mới tới email người dùng
    @PostMapping("/us/reset-password")
    public String resetNewPassword(@RequestParam("username") String userName, Model model){

        // lay user theo tên đăng nhập
       User user = userRepository.findByUserName(userName);
        // kiem tra user name co ton tai hay khong , sau do lay tiep theo email
       if(user == null) user = userRepository.findByEmail(userName);
       // kiem tra lay theo email co lay duoc gia tri hay khong
       if(user == null){
           model.addAttribute("messExist","Email hoặc tên đăng nhập không tồn tại");
       }else {
           // tạo 1 mat khau ngau nhiên và gửi mật khẩu ngẫu nhiên tới email
           user = userServiceImpl.resetPwRandomAndSendMail(user);
           model.addAttribute("messPw","Mật khẩu mới đã được gửi đến email của bạn");
       }

        return "/dangnhap/forget-password";
    }
    // xoa khoang trang người dùng nhập vào
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
    // hiện trang đổi mật khẩu
    @GetMapping("/us/doi-pw")
    public String showPageChangeNewPassword(Model model){
        model.addAttribute("userResetPassword", new UserResetPassword());
        return "dangnhap/forget-change-password";

    }
    @PostMapping("/us/doi-mat-khau")
    public String handleChangePassword(Model model,
                                       @Valid @ModelAttribute("userResetPassword")UserResetPassword userResetPassword,
                                       BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            model.addAttribute("userResetPassword",userResetPassword);
            return "dangnhap/forget-change-password";
        }
        // lay thong tin user theo userName
        User user = userRepository.findByUserName(userResetPassword.getUserName());
        if(user == null){ // nêu ko lấy được theo username thì lấy theo email
            user = userRepository.findByEmail(userResetPassword.getUserName());
        }
        // kiểm tra có lấy được user hay không
        if(user == null){
            model.addAttribute("userResetPassword",userResetPassword);
            model.addAttribute("userNameNotExist","Email hoặc tên đăng nhập không tồn tại");
            return "dangnhap/forget-change-password";
        }

        // lấy mật khẩu từ database
        String pwDatabase = user.getPassword();
        // check password cũ nhập vào có trùng không
        if(!passwordEncoder.matches(userResetPassword.getPwOld(),pwDatabase)){
            model.addAttribute("messPw","Mật khẩu cũ không đúng");
            model.addAttribute("userResetPassword",userResetPassword);
            return "dangnhap/forget-change-password";
        }
        // kiểm tra mật khẩu mới nhập vào có trùng với mật khẩu nhập lại hay không
        if(!userResetPassword.getPwNew().equals(userResetPassword.getPwNewReEnter())){
            model.addAttribute("mess","Mật khẩu mới không trùng khớp");
            model.addAttribute("userResetPassword",userResetPassword);
            return "dangnhap/forget-change-password";
        }
        // mã hóa password và lưu vào database
        String pwEcoder = generateRandomPw.encodePw(userResetPassword.getPwNewReEnter());
        // luu vao database
        user.setPassword(pwEcoder);
        user = userServiceImpl.updateByUser(user);
        if(user != null){ // kiểm tra luu thành công hay không
            model.addAttribute("messSuccess","Thay đổi mật khẩu thành công, vui lòng đăng nhập lại");
        }
        return "/dangnhap/login";
    }

}
