package com.luan.siteveso.jsonAjax;

import com.luan.siteveso.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class JsonUserDeletes {
    private Page<User> userPage;
    private Integer totalPage;
    private String mess;

    public JsonUserDeletes() {
    }

    public JsonUserDeletes(Page<User> userPage, Integer totalPage, String mess) {
        this.userPage = userPage;
        this.totalPage = totalPage;
        this.mess = mess;
    }

    public Page<User> getUserPage() {
        return userPage;
    }

    public void setUserPage(Page<User> userPage) {
        this.userPage = userPage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    @Override
    public String toString() {
        return "UserDeletes{" +
                "userPage=" + userPage +
                ", totalPage=" + totalPage +
                ", mess='" + mess + '\'' +
                '}';
    }
}
