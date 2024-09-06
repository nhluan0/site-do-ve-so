package com.luan.siteveso.controller;

import com.luan.siteveso.dao.UserRepository;
import com.luan.siteveso.entity.Roles;
import com.luan.siteveso.entity.User;
import com.luan.siteveso.jsonAjax.JsonUserDeletes;
import com.luan.siteveso.service.UserServiceImpl;
import com.luan.siteveso.validation.UserValida;
import jakarta.servlet.http.HttpServletRequest;
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
import java.util.ArrayList;
import java.util.List;

// controller xu ly cho admin phan User
@Controller
@RequestMapping("/ad")
public class UserController {

    // define filed UserService
    private UserServiceImpl userServiceImpl;
    private UserRepository userRepository;
    private JsonUserDeletes jsonUserDeletes;


    // inject by inject constructor
    @Autowired
    public UserController(UserServiceImpl userServiceImpl,
                          UserRepository userRepository,JsonUserDeletes jsonUserDeletes) {
        this.userServiceImpl = userServiceImpl;
        this.userRepository = userRepository;
        this.jsonUserDeletes = jsonUserDeletes;

    }

    // hien thi thong tin User
    @GetMapping("/showUser")
    public String showUser(Model model, @ModelAttribute("mess") String mess, HttpServletRequest request,
                           @ModelAttribute("page") String page){
            System.out.println("page gui len la : " + page);
            System.out.println(page.equalsIgnoreCase(""));
            Page<User> userPage = null;
            if(page.equalsIgnoreCase("") || page.equalsIgnoreCase("1")){
                // lay du lieu theo page,sap xep hien thi theo ten tang dan
                userPage = userServiceImpl.getListUser(0,8);
            }else {
                int number = Integer.parseInt(page);
                // lay du lieu theo page,sap xep hien thi theo ten tang dan
                userPage = userServiceImpl.getListUser(number-1,8);
            }

            // list pageNum chua so page can hien thi
            List<Integer> pageNum = new ArrayList<>();
            for (Integer i = 1 ; i <= userPage.getTotalPages();i++) pageNum.add(i);

            // send data to html
            model.addAttribute("pageUser",userPage);
            // send so trang den html
            model.addAttribute("pageNum",pageNum);

            // lay url
            String url = request.getRequestURL().toString();
            // lay tham so duoc truyen len
            String queryString = request.getQueryString();
            System.out.println("Url dang " + queryString) ;
            System.out.println(queryString == null);
            // send xoa that bai hay thanh cong
            if(queryString == null){
                model.addAttribute("mess",mess);
            }

            return "/user/showUser";
    }

    // xoa 1 user theo id truyen len
    @GetMapping("/delete")
    public String deleteUser(@RequestParam(name = "userId") int id, RedirectAttributes redirectAttributes){
        System.out.println("User id = "+ id);

        User user = null ;
        try {
            user = userServiceImpl.getUserById(id);
            if(user == null){ // user không tồn tại
                redirectAttributes.addAttribute("mess","Người dùng không tồn tại");
                return "redirect:/ad/showUser";
                // kiểm tra user có phải admin hay không, nếu là admin thông báo không thể xóa
            }else if(user.getRole().getRole().equalsIgnoreCase("ROLE_ADMIN")){
                redirectAttributes.addAttribute("mess","Admin không thể xóa");
                return "redirect:/ad/showUser";
            }
            // user dang o trang thai hoat dong chuyen sang tai khoan khong hoat dong
            else if (user.getStatus() == 1 || user.getEnable() == 0){
                // chuyen sang tai khoan khong hoat dong
                user.setEnable(0);
                // chuyen sang trang thai khong hoat dong
                user.setStatus(0);
                // update thang trang thai tai khoan khong hoat dong enable = 0
                userRepository.save(user);
                redirectAttributes.addAttribute("mess","Tài khoản đã khóa, xóa thất bại");
                return "redirect:/ad/showUser";
            }else {
                Boolean check = userServiceImpl.deleteUserById(id);
                if(check){
                    redirectAttributes.addAttribute("mess","Xóa thành công");
                }else {
                    redirectAttributes.addAttribute("mess","Xóa thất bại");
                }

                // xóa user
                return "redirect:/ad/showUser";
            }
        }catch (Exception e){
            redirectAttributes.addAttribute("mess","Xóa thất bại");
        }
        return "redirect:/ad/showUser";
    }

    // tim kiem user theo tên username or so phone truyen vao
    @GetMapping("/findUser")
    public String findUser(@RequestParam("user") String user,Model model){
        List<User> userPage = new ArrayList<>();
        User user1 = null;
        List<Integer> pageNum = new ArrayList<>();
        model.addAttribute("pageNum",pageNum);

        if(!user.equalsIgnoreCase("")){
            user1 = userRepository.findByUserName(user);
            if(user1 == null){
                user1 = userRepository.findByPhoneNumber(user);
                // tim theo so dt ko co thi tim kiem theo van , cac ten theo van se duoc tim kiem
                if (user1 == null) {
                    userPage = userServiceImpl.findByNameUser(user);
                    if(userPage.size() ==0) return "/user/find-user-error";
                    // tim kiem theo ten co du lieu thi hien thi lai page
                    model.addAttribute("pageUser",userPage);
                    return "/user/find-user";

                }
            }
            // truong hop user theo ten co thong tin
            userPage.add(user1);
            model.addAttribute("pageUser",userPage);
            return "/user/find-user";
        }
        return "/user/find-user-error";

    }
    // reset lai mat khau
    @GetMapping("/reset")
    public String resetPw(@RequestParam(name = "userId") Integer id){
        // lấy user từ database by id
        User user = userServiceImpl.getUserById(id);
        System.out.println("Thong tin user " + user);
        // kiem tra user co tim dc hay ko
        if(user == null) return "redirect:/ad/showUser";
        try {
            // tạo password ngẫu nhiên và lưu pw mới vào cơ sở dữ liệu, send mật khẩu mới email đã đăng ký
            user = userServiceImpl.resetPwRandomAndSendMail(user);
            return "redirect:/ad/showUser";
        }catch (Exception e){
            System.out.println("gui mail va reset lai mat khau that bai");
        }

        return "redirect:/ad/showUser";
    }

    // xoa nhieu user bang Ajax theo mang id truyen len
    @PostMapping("/deletes")
    @ResponseBody
    public JsonUserDeletes deleteManyUser(@RequestBody Integer[] arr){
        // trả về true/fale khi xóa , duùng biến này để check để add các đối tượng ứng vào object JsonUserDeletes
        Boolean check = userServiceImpl.deleteManyUserByArrId(arr);
        System.out.println("check xoa nhieu :" + check);
        Page<User> userPage = null;
        Integer totalPage = 0;
        String mess = "Xóa thất bại";
        if(check){
            // lay 8 data user, du lieu theo page,sap xep hien thi theo ten tang dan,
            userPage = userServiceImpl.getListUser(0,8);
            totalPage = userPage.getTotalPages();
            mess = "Xóa thành công";
        }
        // add các biến đã tạo trên vào đối tượng class java JsonUserDeletes tương ứng với các field
        jsonUserDeletes.setUserPage(userPage);
        jsonUserDeletes.setTotalPage(totalPage);
        jsonUserDeletes.setMess(mess);
        return jsonUserDeletes;

    }

    // nut phan trang gui len lay gia tri hien thi theo phan trang
    @GetMapping("/phantrang")
    public String pagination(@RequestParam("id") String id,RedirectAttributes redirectAttributes){
        System.out.println("id nhan duowc : " + id);

        redirectAttributes.addAttribute("page",id);
        return "redirect:/ad/showUser";

    }

    // xoa khoang trang người dùng nhập vào
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    // chuyen tiep den 1 trang de update du lieu lai
    @GetMapping("/update")
    public String updateUserById(@RequestParam("userId") Integer id, Model theModel){
        System.out.println("id gui len update : " + id);
        // lấy user theo id truyền vào
        User user = userServiceImpl.getUserById(id);
        System.out.println("Phone number"+Long.parseLong(user.getPhoneNumber()));
        System.out.println("Password " + user.getPassword());
        theModel.addAttribute("webUser",new UserValida());
        // kiểm tra user có tồn tại hay không , nếu không truyền vào 1 trường dữ liệu trống
        if(user == null){
            theModel.addAttribute("webUser",new UserValida());
        }else{ // update du lieu tu server to man hinh de xem
            UserValida userValidation = new UserValida();
            userValidation.setId(user.getId());
            userValidation.setFirstName(user.getFirstName());
            userValidation.setLastName(user.getLastName());
            userValidation.setUserName(user.getUserName());
            userValidation.setPassword("password");
            userValidation.setEmail(user.getEmail());
            userValidation.setPhone(user.getPhoneNumber());
            userValidation.setRole(user.getRole().getRole());
            theModel.addAttribute("webUser",userValidation);
        }

        return "/user/update-form";
    }

    // update du lieu len bang user
    @PostMapping("/updateForm")
    public String updateUser(
            @Valid @ModelAttribute("webUser") UserValida webUser,
            BindingResult bindingResult, Model model){
        System.out.println("Thong tin user được gửi lên qua form by method post :" + webUser);
        // lấy user theo id từ database
        User user = userServiceImpl.getUserById(webUser.getId());
        if(user.getRole().getRole().equalsIgnoreCase("ROLE_ADMIN")){
            System.out.println("so sanh role admin: " + user.getRole().getRole().equalsIgnoreCase("ROLE_ADMIN"));
            model.addAttribute("errorRoleAdmin","Admin không cho phép cập nhật");
            return "/user/update-form";
        }
        // kiểm tra có lỗi xảy ra trả lại trang update-form.html
        if(bindingResult.hasErrors()){
            return "/user/update-form";
        }

        // ko có lỗi, thực hiện update và chuyển tiếp đến trang quản lý user
        // set thong tin cho user mới
        user.setFirstName(webUser.getFirstName());
        user.setLastName(webUser.getLastName());
        user.setEmail(webUser.getEmail());
        user.setPhoneNumber(webUser.getPhone());
        user.setRole(new Roles(webUser.getRole()));
        // update user mới
        User check = userServiceImpl.updateByUser(user);
        System.out.println("thong tin user da update: " + check);
        System.out.println("id user da update: " + check.getId());
        return "redirect:/ad/showUser";
    }

    // trang chuyen huong khi ấn nút thêm add user
    @GetMapping("/add-user")
    public String addUser(Model theModel){
        theModel.addAttribute("webUser",new UserValida());
        return "/user/add-form";
    }

    // add user bằng form
    @PostMapping("/add-user")
    public String addUser( @Valid @ModelAttribute("webUser") UserValida webUser,
                           BindingResult bindingResult, Model model){
        // neu co loi chuyen den trang form
        if(bindingResult.hasErrors()){
            return "/user/add-form";
        }
        // kiểm tra username có tồn tại hay ko
        User user = userRepository.findByUserName(webUser.getUserName());
        if(user != null){
            model.addAttribute("usernameExisted","Tên đăng nhập đã tồn tại");
            model.addAttribute("errorAddFail","Thêm thất bại");
            return "/user/add-form";
        }
        //  kiểm tra email tồn tại hay chưa
        user = userRepository.findByEmail(webUser.getEmail());
        if( user != null){
            model.addAttribute("emailExisted","Email đã tồn tại");
            model.addAttribute("errorAddFail","Thêm thất bại");
            return "/user/add-form";
        }
        //  kiểm tra thử số điện thoại có chưa
        user = userRepository.findByPhoneNumber(webUser.getPhone());
        if( user !=null){
            model.addAttribute("phoneExisted","Số điện thoại đã tồn tại");
            model.addAttribute("errorAddFail","Thêm thất bại");
            return "/user/add-form";
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
        user.setEnable(1);// 1: tài khoản không bị khóa
        user.setStatus(0);// 0. trạng thái đang off line
        user.setRole(new Roles(webUser.getRole()));
        // lưu user lên database
        user = userServiceImpl.updateByUser(user);
        System.out.println("Thông tin uer đã thêm vào database: "+ user);
        return "redirect:/ad/showUser";
    }

    // mở 1 tài khoản đã bị khóa
    @GetMapping("/mo-tai-khoan")
    public String openAnAccountLock(@RequestParam("userId") int id,RedirectAttributes redirectAttributes){
        // 1: lay user theo id truyen len
        User user = userRepository.getReferenceById(id);
        if(user != null){
            // 2: set trang thai user thành 1, tài khoản hoạt động bình thường
            user.setEnable(1);
            // 3: lưu vào database
            userRepository.save(user);
            redirectAttributes.addAttribute("mess","Mở khóa tài khoản thành công");
            return "redirect:/ad/showUser";
        }
        redirectAttributes.addAttribute("mess","Tài khoản không tồn tại");
        return "redirect:/ad/showUser";
    }
}
