package com.luan.siteveso.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tienthuong")
public class TienThuong {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "giai")
    private String giai;
    @Column(name = "tien_thuong")
    private String tienThuong;

    public TienThuong() {
    }

    public TienThuong(int id, String giai, String tienThuong) {
        this.id = id;
        this.giai = giai;
        this.tienThuong = tienThuong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGiai() {
        return giai;
    }

    public void setGiai(String giai) {
        this.giai = giai;
    }

    public String getTienThuong() {
        return tienThuong;
    }

    public void setTienThuong(String tienThuong) {
        this.tienThuong = tienThuong;
    }

    @Override
    public String toString() {
        return "TienThuong{" +
                "id=" + id +
                ", giai='" + giai + '\'' +
                ", tienThuong='" + tienThuong + '\'' +
                '}';
    }
}
