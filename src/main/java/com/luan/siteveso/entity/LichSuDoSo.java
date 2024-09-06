package com.luan.siteveso.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "lichsudove")
public class LichSuDoSo implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "date_search")
    private Date ngayDo;
    @Column(name = "hour_search")
    private String hourDoSo;
    @Column(name = "sodo")
    private String soDo;
    @Column(name = "kqds")
    private String messKq;
    @Column(name = "username")
    private String social;
    @Column(name = "status")
    private String status;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne (fetch = FetchType.EAGER,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "id_user")
    private User user;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "id_kq")
    private KetQuaXoSo ketQuaXoSo;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "id_tienthuong")
    private TienThuong tienThuong;




    public LichSuDoSo(Date ngayDo, String hourDoSo, String soDo, String messKq,String social,String status) {
        this.ngayDo = ngayDo;
        this.hourDoSo = hourDoSo;
        this.soDo = soDo;
        this.messKq = messKq;
        this.social = social;
        this.status = status;
    }

    public TienThuong getTienThuong() {
        return tienThuong;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTienThuong(TienThuong tienThuong) {
        this.tienThuong = tienThuong;
    }

    public String getSocial() {
        return social;
    }

    public void setSocial(String social) {
        this.social = social;
    }

    public String getSoDo() {
        return soDo;
    }

    public void setSoDo(String soDo) {
        this.soDo = soDo;
    }

    public String getMessKq() {
        return messKq;
    }

    public void setMessKq(String messKq) {
        this.messKq = messKq;
    }

    public LichSuDoSo() {
    }

    public String getHourDoSo() {
        return hourDoSo;
    }

    public void setHourDoSo(String hourDoSo) {
        this.hourDoSo = hourDoSo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getNgayDo() {
        return ngayDo;
    }

    public void setNgayDo(Date ngayDo) {
        this.ngayDo = ngayDo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public KetQuaXoSo getKetQuaXoSo() {
        return ketQuaXoSo;
    }

    public void setKetQuaXoSo(KetQuaXoSo ketQuaXoSo) {
        this.ketQuaXoSo = ketQuaXoSo;
    }

    @Override
    public String toString() {
        return "LichSuDoSo{" +
                "id=" + id +
                ", ngayDo=" + ngayDo +
                ", hourDoSo='" + hourDoSo + '\'' +
                ", soDo='" + soDo + '\'' +
                ", messKq='" + messKq + '\'' +
                ", social='" + social + '\'' +
                ", status='" + status + '\'' +
                ", user=" + user +
                ", ketQuaXoSo=" + ketQuaXoSo +
                ", tienThuong=" + tienThuong +
                '}';
    }
}
