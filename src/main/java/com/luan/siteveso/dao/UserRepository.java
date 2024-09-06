package com.luan.siteveso.dao;

import com.luan.siteveso.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    // chi lay du lieu mot bang User theo ten
    User findByUserName(String userName);

    // chi lay du lieu mot bang User theo email
    User findByEmail(String email);

    // chi lay du lieu theo phone
    User findByPhoneNumber(String phone);

}
