package com.luan.siteveso.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "ketqua")
public class KetQuaXoSo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "G8")
    private String g8;
    @Column(name = "G7")
    private String g7;
    @Column(name = "G61")
    private String g61;
    @Column(name = "G62")
    private String g62;
    @Column(name = "G63")
    private String g63;
    @Column(name = "G5")
    private String g5;
    @Column(name = "G41")
    private String g41;
    @Column(name = "G42")
    private String g42;
    @Column(name = "G43")
    private String g43;
    @Column(name = "G44")
    private String g44;
    @Column(name = "G45")
    private String g45;
    @Column(name = "G46")
    private String g46;
    @Column(name = "G47")
    private String g47;
    @Column(name = "G31")
    private String g31;
    @Column(name = "G32")
    private String g32;
    @Column(name = "G2")
    private String g2;
    @Column(name = "G1")
    private String g1;
    @Column(name = "DB")
    private String db;
    @Column(name = "ngay_xoso")
    private Date dateLottery;
    @Column(name = "ngay_Tao")
    private Date dateCreated;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,CascadeType.MERGE, CascadeType.REFRESH,CascadeType.PERSIST})
    @JoinColumn(name = "id_dai")
    private DaiXoSo daiXoSo;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne (fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "AdminID_Tao")
    private User user;


    public KetQuaXoSo() {
    }

    public KetQuaXoSo(String g8, String g7, String g61, String g62, String g63, String g5, String g41, String g42, String g43,
                      String g44, String g45, String g46, String g47, String g31, String g32, String g2, String g1, String db,
                      Date dateLottery,Date dateCreated) {
        this.g8 = g8;
        this.g7 = g7;
        this.g61 = g61;
        this.g62 = g62;
        this.g63 = g63;
        this.g5 = g5;
        this.g41 = g41;
        this.g42 = g42;
        this.g43 = g43;
        this.g44 = g44;
        this.g45 = g45;
        this.g46 = g46;
        this.g47 = g47;
        this.g31 = g31;
        this.g32 = g32;
        this.g2 = g2;
        this.g1 = g1;
        this.db = db;
        this.dateLottery = dateLottery;
        this.dateCreated = dateCreated;

    }

    public Date getDateLottery() {
        return dateLottery;
    }

    public void setDateLottery(Date dateLottery) {
        this.dateLottery = dateLottery;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getG8() {
        return g8;
    }

    public void setG8(String g8) {
        this.g8 = g8;
    }

    public String getG7() {
        return g7;
    }

    public void setG7(String g7) {
        this.g7 = g7;
    }

    public String getG61() {
        return g61;
    }

    public void setG61(String g61) {
        this.g61 = g61;
    }

    public String getG62() {
        return g62;
    }

    public void setG62(String g62) {
        this.g62 = g62;
    }

    public String getG63() {
        return g63;
    }

    public void setG63(String g63) {
        this.g63 = g63;
    }

    public String getG5() {
        return g5;
    }

    public void setG5(String g5) {
        this.g5 = g5;
    }

    public String getG41() {
        return g41;
    }

    public void setG41(String g41) {
        this.g41 = g41;
    }

    public String getG42() {
        return g42;
    }

    public void setG42(String g42) {
        this.g42 = g42;
    }

    public String getG43() {
        return g43;
    }

    public void setG43(String g43) {
        this.g43 = g43;
    }

    public String getG44() {
        return g44;
    }

    public void setG44(String g44) {
        this.g44 = g44;
    }

    public String getG45() {
        return g45;
    }

    public void setG45(String g45) {
        this.g45 = g45;
    }

    public String getG46() {
        return g46;
    }

    public void setG46(String g46) {
        this.g46 = g46;
    }

    public String getG47() {
        return g47;
    }

    public void setG47(String g47) {
        this.g47 = g47;
    }

    public String getG31() {
        return g31;
    }

    public void setG31(String g31) {
        this.g31 = g31;
    }

    public String getG32() {
        return g32;
    }

    public void setG32(String g32) {
        this.g32 = g32;
    }

    public String getG2() {
        return g2;
    }

    public void setG2(String g2) {
        this.g2 = g2;
    }

    public String getG1() {
        return g1;
    }

    public void setG1(String g1) {
        this.g1 = g1;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public DaiXoSo getDaiXoSo() {
        return daiXoSo;
    }

    public void setDaiXoSo(DaiXoSo daiXoSo) {
        this.daiXoSo = daiXoSo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "KetQuaXoSo{" +
                "id=" + id +
                ", g8='" + g8 + '\'' +
                ", g7='" + g7 + '\'' +
                ", g61='" + g61 + '\'' +
                ", g62='" + g62 + '\'' +
                ", g63='" + g63 + '\'' +
                ", g5='" + g5 + '\'' +
                ", g41='" + g41 + '\'' +
                ", g42='" + g42 + '\'' +
                ", g43='" + g43 + '\'' +
                ", g44='" + g44 + '\'' +
                ", g45='" + g45 + '\'' +
                ", g46='" + g46 + '\'' +
                ", g47='" + g47 + '\'' +
                ", g31='" + g31 + '\'' +
                ", g32='" + g32 + '\'' +
                ", g2='" + g2 + '\'' +
                ", g1='" + g1 + '\'' +
                ", db='" + db + '\'' +
                ", dateLottery=" + dateLottery +
                ", dateCreated=" + dateCreated +
                ", daiXoSo=" + daiXoSo +
//                ", user=" + user +
                '}';
    }
}
