package com.luan.siteveso.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "lichsusua")
public class LichSuSua implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "ngay_sua")
    private Date ngaySua;

    @Column(name = "id_ketqua")
    private int idKq;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne (fetch = FetchType.LAZY,
    cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST})
    @JoinColumn(name = "id_user")
    private User user;

    public LichSuSua() {
    }

    public LichSuSua(Date ngaySua, int idKq) {
        this.ngaySua = ngaySua;
        this.idKq = idKq;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(Date ngaySua) {
        this.ngaySua = ngaySua;
    }

    public int getIdKq() {
        return idKq;
    }

    public void setIdKq(int idKq) {
        this.idKq = idKq;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "LichSuSua{" +
                "id=" + id +
                ", ngaySua=" + ngaySua +
                ", idKq=" + idKq +
                ", user=" + user +
                '}';
    }
}
