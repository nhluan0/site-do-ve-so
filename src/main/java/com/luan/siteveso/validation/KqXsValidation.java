package com.luan.siteveso.validation;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;



import java.sql.Date;

public class KqXsValidation {
    private int idKqXs;
    private String tenDai;
    @NotNull(message = "Giải 8 không bỏ trống")
    @Size(min = 2,max = 2,message = "Giải 8 chỉ có 2 số")
    private String g8;
    @NotNull(message = "Giải 7 không bỏ trống")
    @Size(min = 3,max = 3,message = "Giải 7 chỉ có 3 số")
    private String g7;
    @NotNull(message = "Giải 6 không bỏ trống")
    @Size(min = 4,max = 4,message = "Giải 6 chỉ có 4 số")
    private String g61;
    @NotNull(message = "Giải 6 không bỏ trống")
    @Size(min = 4,max = 4,message = "Giải 6 chỉ có 4 số")
    private String g62;
    @NotNull(message = "Giải 6 không bỏ trống")
    @Size(min = 4,max = 4,message = "Giải 6 chỉ có 4 số")
    private String g63;
    @NotNull(message = "Giải 5 không bỏ trống")
    @Size(min = 4,max = 4,message = "Giải 5 chỉ có 4 số")
    private String g5;
    @NotNull(message = "Giải 4 không bỏ trống")
    @Size(min = 5,max = 5,message = "Giải 4 chỉ có 5 số")
    private String g41;
    @NotNull(message = "Giải 4 không bỏ trống")
    @Size(min = 5,max = 5,message = "Giải 4 chỉ có 5 số")
    private String g42;
    @NotNull(message = "Giải 4 không bỏ trống")
    @Size(min = 5,max = 5,message = "Giải 4 chỉ có 5 số")
    private String g43;
    @NotNull(message = "Giải 4 không bỏ trống")
    @Size(min = 5,max = 5,message = "Giải 4 chỉ có 5 số")
    private String g44;
    @NotNull(message = "Giải 4 không bỏ trống")
    @Size(min = 5,max = 5,message = "Giải 4 chỉ có 5 số")
    private String g45;
    @NotNull(message = "Giải 4 không bỏ trống")
    @Size(min = 5,max = 5,message = "Giải 4 chỉ có 5 số")
    private String g46;
    @NotNull(message = "Giải 4 không bỏ trống")
    @Size(min = 5,max = 5,message = "Giải 4 chỉ có 5 số")
    private String g47;
    @NotNull(message = "Giải 3 không bỏ trống")
    @Size(min = 5,max = 5,message = "Giải 3 chỉ có 5 số")
    private String g31;
    @NotNull(message = "Giải 3 không bỏ trống")
    @Size(min = 5,max = 5,message = "Giải 3 chỉ có 5 số")
    private String g32;
    @NotNull(message = "Giải 2 không bỏ trống")
    @Size(min = 5,max = 5,message = "Giải 2 chỉ có 5 số")
    private String g2;
    @NotNull(message = "Giải 1 không bỏ trống")
    @Size(min = 5,max = 5,message = "Giải 1 chỉ có 5 số")
    private String g1;
    @NotNull(message = "Giải đặc biệt không bỏ trống")
    @Size(min = 6,max = 6,message = "Giải đặc biệt chỉ có 6 số")
    private String db;

    private Date dateXoSo;

    public KqXsValidation() {
    }

    public KqXsValidation(String tenDai, String g8, String g7, String g61, String g62, String g63, String g5, String g41, String g42, String g43, String g44, String g45, String g46, String g47, String g31, String g32, String g2, String g1, String db, Date dateXoSo) {
        this.tenDai = tenDai;
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
        this.dateXoSo = dateXoSo;
    }

    public int getIdKqXs() {
        return idKqXs;
    }

    public void setIdKqXs(int idKqXs) {
        this.idKqXs = idKqXs;
    }

    public String getTenDai() {
        return tenDai;
    }

    public void setTenDai(String tenDai) {
        this.tenDai = tenDai;
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

    public Date getDateXoSo() {
        return dateXoSo;
    }

    public void setDateXoSo(Date dateXoSo) {
        this.dateXoSo = dateXoSo;
    }

    @Override
    public String toString() {
        return "KqXsValidation{" +
                "idKqXs=" + idKqXs +
                ", tenDai='" + tenDai + '\'' +
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
                ", dateXoSo=" + dateXoSo +
                '}';
    }
}
