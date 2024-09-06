package com.luan.siteveso.controller;

import com.luan.siteveso.dao.UserRepository;
import com.luan.siteveso.email.EmailService;
import com.luan.siteveso.entity.Roles;
import com.luan.siteveso.entity.User;
import com.luan.siteveso.service.UserServiceImpl;
import com.luan.siteveso.validation.UserValida;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;


@Controller
public class RegisterController {
    private UserServiceImpl userServiceImpl;
    private UserRepository userRepository;
    private EmailService emailService;
    @Autowired
    public RegisterController(UserServiceImpl userServiceImpl,UserRepository userRepository,
                              EmailService emailService) {
        this.userServiceImpl = userServiceImpl;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }
    @GetMapping("/us/dang-ky-moi")
    public String showFormRegister(Model model){
        model.addAttribute("webUser", new UserValida());
        return "/dangnhap/form-dang-ky";

    }

    // xoa khoang trang người dùng nhập vào
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
    // dnag ký moi nguoi dung
    @PostMapping("/dang-ky-moi")
    public String registerNewUser(@Valid @ModelAttribute("webUser") UserValida webUser,
                                  BindingResult bindingResult, Model model){
        // neu co loi chuyen den trang form
        if(bindingResult.hasErrors()){
            model.addAttribute("webUser",webUser);
            return "/dangnhap/form-dang-ky";
        }
        // kiểm tra username có tồn tại hay ko
        User user = userRepository.findByUserName(webUser.getUserName());
        if(user != null){
            model.addAttribute("webUser",webUser);
            model.addAttribute("usernameExisted","Tên đăng nhập đã tồn tại");
            model.addAttribute("errorAddFail","Đăng ký thất bại");
            return "/dangnhap/form-dang-ky";
        }
        //  kiểm tra email tồn tại hay chưa
        user = userRepository.findByEmail(webUser.getEmail());
        if( user != null){
            model.addAttribute("webUser",webUser);
            model.addAttribute("emailExisted","Email đã tồn tại");
            model.addAttribute("errorAddFail","Đăng ký thất bại");
            return "/dangnhap/form-dang-ky";
        }
        //  kiểm tra thử số điện thoại có chưa
        user = userRepository.findByPhoneNumber(webUser.getPhone());
        if( user !=null){
            model.addAttribute("webUser",webUser);
            model.addAttribute("phoneExisted","Số điện thoại đã tồn tại");
            model.addAttribute("errorAddFail","Đăng ký thất bại");
            return "/dangnhap/form-dang-ky";
        }
        // nếu dữ liệu check thành công thì mã hóa password và add user to database
        // mã hóa password
        String pw = userServiceImpl.encodePassword(webUser.getPassword());
        // set info user
        user = new User();
        user.setFirstName(webUser.getFirstName());
        user.setLastName(webUser.getLastName());
        user.setUserName(webUser.getUserName());
        user.setPassword(pw);// lưu mật khẩu đã mã hóa
        user.setEmail(webUser.getEmail());
        user.setPhoneNumber(webUser.getPhone());
        user.setRegisterDate(userServiceImpl.getCurrentDateSql());// set ngày tháng năm theo định dạng sql
        // 0: tài khoản bị khóa cần gửi email để xác nhận và khi user xác nhận api đc gửi tới email thì set lại trạng thái không bị khóa = 1
        user.setEnable(0);
        user.setStatus(0);// 0. trạng thái đang offline
        user.setRole(new Roles("ROLE_USER"));
        // lưu user lên database
        user = userServiceImpl.updateByUser(user);
        // gửi email để xác nhận mail
        String subject = "Mail xác nhận đăng ký thành công Xổ Số Vịt Ù";
        String body = "Bạn vui lòng click vào link http://localhost:8080/us/emai-hop-le?stt=" +
                user.getId() + " để hoàn tất đăng ký người dùng tại Xổ Số Vịt Ù";
        String toEmail = webUser.getEmail();
        // gửi mail xác nhận
        emailService.sendEmail(toEmail,subject,body);
        model.addAttribute("valid","Xác nhận email");
        return "/dangnhap/login";
    }
    // xác nhận email hợp lệ
    @GetMapping("/us/emai-hop-le")
    public String validEmail(@RequestParam("stt") int id,Model model){
        System.out.println(id);
        // lay user theo id nhận đuọc từ tham số trên
        User user = userServiceImpl.getUserById(id);
        // kiem tra id co ton tai
        if( user !=null){
            user.setEnable(1);// cho trang thái user lên trạng thái tài khoản hoạt đông
            // cập nhập thông tin user nay mới vào database
            user = userServiceImpl.updateByUser(user);
        }
        model.addAttribute("messValid","Xác thực email thành công");
        return "/dangnhap/login";
    }
}
