package com.luan.siteveso.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "accounts")
public class User implements Serializable {
    // define entity

    // define id

    // mapping field to database table

    // define mapping one-to-one

    // constructor

    // setter/getter

    // toString
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Integer id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;

    @Column(name = "username")
    private String userName;

    @Column(name="pw")
    private String password;

    @Column(name="email")
    private String email;

    @Column(name = "phone")
    private String phoneNumber;

    @Column(name = "register_date")
    private Date registerDate;

    @Column(name="enable")
    private int enable;
    @Column(name = "status")
    private int status;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_role")
    private Roles role;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "user")
    private List<LichSuDoSo> lichSuDoSoList;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "user")
    private List<LichSuSua> lichSuSuaList;

    public User() {
    }

    public User(String firstName, String lastName, String userName, String password, String email,
                String phoneNumber, Date registerDate, int enable, int status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.registerDate = registerDate;
        this.enable = enable;
        this.status = status;

    }

    public List<LichSuSua> getLichSuSuaList() {
        return lichSuSuaList;
    }

    public void setLichSuSuaList(List<LichSuSua> lichSuSuaList) {
        this.lichSuSuaList = lichSuSuaList;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<LichSuDoSo> getLichSuDoSoList() {
        return lichSuDoSoList;
    }

    public void setLichSuDoSoList(List<LichSuDoSo> lichSuDoSoList) {
        this.lichSuDoSoList = lichSuDoSoList;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", registerDate=" + registerDate +
                ", enable=" + enable +
                ", status=" + status +
                ", role=" + role +
                '}';
    }
}
