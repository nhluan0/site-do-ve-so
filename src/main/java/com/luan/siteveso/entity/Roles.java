package com.luan.siteveso.entity;

import jakarta.persistence.*;

@Entity
@Table(name ="roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name="role")
    private String role;

    @OneToOne(mappedBy = "role",cascade = CascadeType.ALL)
    private User user;

    public Roles() {
    }

    public Roles( String role) {

        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "id=" + id +
                ", role='" + role + '\'' +

                '}';
    }
}
