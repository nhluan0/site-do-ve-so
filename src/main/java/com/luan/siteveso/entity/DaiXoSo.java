package com.luan.siteveso.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "daiveso")
public class DaiXoSo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ten")
    private String nameLottery;
    @Column(name = "khuvuc")
    private String aria;



    public DaiXoSo() {
    }

    public DaiXoSo(String nameLottery, String aria) {
        this.nameLottery = nameLottery;
        this.aria = aria;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameLottery() {
        return nameLottery;
    }

    public void setNameLottery(String nameLottery) {
        this.nameLottery = nameLottery;
    }

    public String getAria() {
        return aria;
    }

    public void setAria(String aria) {
        this.aria = aria;
    }

    @Override
    public String toString() {
        return "DaiXoSo{" +
                "id=" + id +
                ", nameLottery='" + nameLottery + '\'' +
                ", aria='" + aria + '\'' +

                '}';
    }
}
