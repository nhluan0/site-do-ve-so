package com.luan.siteveso.validation;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserResetPassword {
    @NotNull(message = "không bỏ trống")
    @Size(min = 1,message = "ít nhất 1 ký tự")
    private String userName;
    @NotNull(message = "không bỏ trống")
    @Size(min = 1,message = "ít nhất 1 ký tự")
    private String pwOld;
    @NotNull(message = "không bỏ trống")
    @Size(min = 6,message = "ít nhất 6 ký tự")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{6,}$",
            message = "Ít nhất 1 chữ hoa, 1 chữ thường, 1 ký tự số, 1 ký tự đặc biệt")
    private String pwNew;
    @NotNull(message = "không bỏ trống")
    @Size(min = 6,message = "ít nhất 6 ký tự")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{6,}$",
            message = "Ít nhất 1 chữ hoa, 1 chữ thường, 1 ký tự số, 1 ký tự đặc biệt")
    private String pwNewReEnter;

    public UserResetPassword() {
    }

    public UserResetPassword(String userName, String pwOld, String pwNew, String pwNewReEnter) {
        this.userName = userName;
        this.pwOld = pwOld;
        this.pwNew = pwNew;
        this.pwNewReEnter = pwNewReEnter;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwOld() {
        return pwOld;
    }

    public void setPwOld(String pwOld) {
        this.pwOld = pwOld;
    }

    public String getPwNew() {
        return pwNew;
    }

    public void setPwNew(String pwNew) {
        this.pwNew = pwNew;
    }

    public String getPwNewReEnter() {
        return pwNewReEnter;
    }

    public void setPwNewReEnter(String pwNewReEnter) {
        this.pwNewReEnter = pwNewReEnter;
    }
}
