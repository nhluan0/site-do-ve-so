package com.luan.siteveso.validation;

import jakarta.validation.constraints.*;

public class UserValida {
    private Integer id;
    @NotNull(message = "không bỏ trống")
    @Size(min = 1,message = "ít nhất 1 ký tự")
    private String firstName;
    @NotNull(message = "không bỏ trống")
    @Size(min = 1,message = "ít nhất 1 ký tự")
    private String lastName;
    @NotNull(message = "không bỏ trống")
    @Size(min = 1,message = "ít nhất 1 ký tự")
    private String userName;
    @NotNull(message = "không bỏ trống")
    @Size(min = 1,message = "ít nhất 1 ký tự")
    private String password;
    @NotNull(message = "không bỏ trống")
    @Size(min = 12,message = "ít nhất 12 ký tự")
    @Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    ,message = "định dạng như example@exam.com")
    private String email;
    @NotNull(message = "không bỏ trống")
    @Size(min = 10,message = "ít nhất 10 số")
    @Size(max = 12,message = "nhiều nhất 12 số")
    private String phone;

    private String role;

    public UserValida() {
    }

    public UserValida(String firstName, String lastName, String userName, String password, String email, String phone, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "UserValidation{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", role='" + role + '\'' +
                '}';
    }

    public void setRole(String role) {
        this.role = role;
    }

}
