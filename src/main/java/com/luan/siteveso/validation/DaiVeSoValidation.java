package com.luan.siteveso.validation;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class DaiVeSoValidation {
    private int id;
    @NotNull(message = "không bỏ trống")
    @Size(min = 5,message = "ít nhất 5 ký tự")
    private String tenDai;
    private String khuVuc;

    public DaiVeSoValidation() {
    }

    public DaiVeSoValidation(int id, String tenDai, String khuVuc) {
        this.id = id;
        this.tenDai = tenDai;
        this.khuVuc = khuVuc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenDai() {
        return tenDai;
    }

    public void setTenDai(String tenDai) {
        this.tenDai = tenDai;
    }

    public String getKhuVuc() {
        return khuVuc;
    }

    public void setKhuVuc(String khuVuc) {
        this.khuVuc = khuVuc;
    }

    @Override
    public String toString() {
        return "DaiVeSoValidation{" +
                "id=" + id +
                ", tenDai='" + tenDai + '\'' +
                ", khuVuc='" + khuVuc + '\'' +
                '}';
    }
}
