package com.luan.siteveso.service;

import com.luan.siteveso.dao.LichSuDoSoRepository;
import com.luan.siteveso.dao.LichSuSuaRepository;
import com.luan.siteveso.dao.UserRepository;
import com.luan.siteveso.date.DateSql;
import com.luan.siteveso.email.EmailService;
import com.luan.siteveso.entity.KetQuaXoSo;
import com.luan.siteveso.entity.LichSuDoSo;
import com.luan.siteveso.entity.LichSuSua;
import com.luan.siteveso.entity.User;
import com.luan.siteveso.password.GenerateRandomPw;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.metamodel.Type;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    // add field UserRepository
    private UserRepository userRepository;
    private GenerateRandomPw generateRandomPw;
    private EmailService emailService;
    private DateSql dateSql;
    private LichSuDoSoServiceImpl lichSuDoSoServiceImpl;
    private LichSuDoSoRepository lichSuDoSoRepository;
    @Autowired
    private LichSuSuaServiceImpl lichSuSuaServiceImpl;
    @Autowired
    private LichSuSuaRepository lichSuSuaRepository;
    @Autowired
    private EntityManager entityManager;

    // inject by constructor inject
    @Autowired
    public UserServiceImpl(UserRepository userRepository, GenerateRandomPw generateRandomPw,LichSuDoSoRepository lichSuDoSoRepository,
                           EmailService emailService,DateSql dateSql,LichSuDoSoServiceImpl lichSuDoSoServiceImpl) {
        this.userRepository = userRepository;
        this.generateRandomPw = generateRandomPw;
        this.emailService = emailService;
        this.dateSql = dateSql;
        this.lichSuDoSoServiceImpl = lichSuDoSoServiceImpl;
        this.lichSuDoSoRepository = lichSuDoSoRepository;
    }

    // lay list user theo chuc nang phan trang duoc ho tro
    public Page<User> getListUser(int pageNum, int pageSize){
        // lay list user theo so trang bat dau ( pageNum ) mac dinh trang 1 so 0 , va so user hien thi tren 1 page(pageSize)
        Page<User> userList =
                userRepository.findAll(PageRequest.of(pageNum,pageSize, Sort.by("registerDate").descending()));
        return userList;
    }
    // lay user theo id
    public User getUserById(int id){
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.get();
    }


    // delete user by id
    @Transactional
    public Boolean deleteUserById(int id){
        try{
            // kiem tra id co ton tai trong database khong
            User user = userRepository.getReferenceById(id);
            if(user !=null){
                if(user.getEnable() == 1){
                    // set trang thai user enable thanh 0
                    user.setEnable(0);
                    user.setStatus(0);
                    return true;
                }
                return false;
            }
            return false;
        }catch (Exception e){
            return false;
        }

    }

    // xoa nhieu theo mang chua id truyen vao
    @Transactional
    public Boolean deleteManyUserByArrId(Integer[] arr){
        // lap qua tung phan tu , thuc hien kiem tra user lay duoc theo id co null , co role admin, co dang trang thai dang nhap thi tra ve false
        // delete tung user theo id
        int check = 0;
        User user;
        for (int i=0; i<arr.length;i++){
            // kiem tra user co lay duoc tu id hay khong , kiem tra co phai role admin ,kiem tra tai khoan dang hoat dong
            user = getUserById(arr[i]);
            if(user == null || user.getRole().getRole().equalsIgnoreCase("ROLE_ADMIN"))return false;
            if(user != null) check++;

        }
        // kiem tra vong lap theo mang tren co chay het mang thi gia tri cac id trong mang truyen len ton tai
        // sau do thuc hien xoa
        if(check == arr.length){ // lap qua het thi thuc hien xoa
            for (int i=0; i< arr.length ; i++){
                deleteUserById(arr[i]); // xoa tung phan tu theo id

            }
            return true;
        }else {
            return false;
        }
    }

    // update 1 user theo id truyen vao
    @Transactional
    public User updateUser(int id){
        // lay user tu database
        User user = getUserById(id);
        // kiem tra user co ton tai thi update
        user.setFirstName("LienLien");
        if(user !=null){
           user = userRepository.save(user);
            return user;
        }
        return null;
    }

    // update/add 1 user truyen vao
    @Transactional
    public User updateByUser(User user){
        return  userRepository.save(user);
    }

    // mã hóa 1 password
    @Override
    public String encodePassword(String pw) {
        return generateRandomPw.encodePw(pw);
    }

    // gửi 1 email
    @Override
    public boolean sendEmail(String to, String subject, String body) {
        try {
            emailService.sendEmail(to,subject,body);
            return true;
        }catch (Exception e){
           return false;
        }

    }


    // create 1 password ngẫu nhiên và reset mật khẩu và gửi mail
    public User resetPwRandomAndSendMail(User user){
        // tao 1 password ngau nhien
        String pwRandom = generateRandomPw.generateSecurePassword();
        System.out.println("password tao ngau nhien : " + pwRandom);

        // lấy thông tin email user
        String email = user.getEmail();
        // send password mới tới mail người nhận
        emailService.sendEmail(email,"Mật khẩu mới của bạn tại Xổ số vịt ù","Vào trang "+"http://localhost:8080/dang-nhap để đăng nhập lại" +
                " mật khẩu mới của bạn là " + pwRandom  );

        // ma hoa password
        String encodedPw = generateRandomPw.encodePw(pwRandom);
        System.out.println("password được mã hóa : " + encodedPw);
        // set password mới mã hóa cho user
        user.setPassword(encodedPw);
        // update hay lưu password mới của user to database
        return updateByUser(user);

    }

    // lấy ngày tháng năm định dạng date sql hiện tại
    @Override
    public Date getCurrentDateSql() {
        return dateSql.getDateSqlCurrent();
    }


    // tim kiem user theo userName chua van tuong ung
    @Override
    public List<User> findByNameUser(String userName) {
        // create query
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.userName LIKE :data",User.class);
        // set parameter
        // set parameter
        query.setParameter("data", "%" + userName + "%");
        return query.getResultList();
    }

    // dang nhap qua username or email voi spring security
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = null;
        try {
           user = userRepository.findByUserName(username);

        }catch (Exception e){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        // kiem tra username lay duoc co null khong, neu co thi lay theo email nhap vao
        if (user == null) {
            user = userRepository.findByEmail(username);
            if(user == null){
                throw new UsernameNotFoundException("Invalid username or password.");
            }
        }
        // check tai khoan duoc cho phep hay khong , trang thai enable = 0 => tai khoan bi khoa,
        // enable = 1 , tai khoan duoc phep hoat dong
        if(user.getEnable() == 0){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().getRole()));
        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),grantedAuthorities);
    }
}
