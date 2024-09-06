package com.luan.siteveso.service;


import com.luan.siteveso.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;


import java.sql.Date;
import java.util.List;


public interface UserService extends UserDetailsService {
    // lay list user theo chuc nang phan trang duoc ho tro
    public Page<User> getListUser(int pageNum, int pageSize);

    // lay user theo id
    public User getUserById(int id);

    // delete user by id
    public Boolean deleteUserById(int id);

    // xoa nhieu theo mang chua id truyen vao
    public Boolean deleteManyUserByArrId(Integer[] arr);

    // update  1 user theo id truyen vao
    public User updateUser(int id);

    // update or add 1 user truyen vao
    public User updateByUser(User user);

    // ma hoa password
    public String encodePassword(String pw);

    // gửi 1 email tới địa chỉ maill đã xác định
    public boolean sendEmail(String to, String subject, String body );

    // lấy  ngày tháng năm hiện tại
    public Date getCurrentDateSql();

    // tim kiem theo ten user co van tuong ung
    List<User> findByNameUser(String userName);
}
