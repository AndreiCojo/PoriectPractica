package com.marketplace.model;


import com.marketplace.dto.AdminDto;

import javax.persistence.*;

@Entity
@Table(name="admins")
public class Admin {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String name;
    private int age;
    private String password;

    @ManyToOne(fetch=FetchType.LAZY)
    private Role role;

    public Admin() {
    }

    public Admin(String email, String name, int age) {
        this.email=email;
        this.name=name;
        this.age=age;
    }

    public Admin(AdminDto adminDto) {
        this.name= adminDto.getName();
        this.age= adminDto.getAge();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age=age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password=password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email=email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role=role;
    }
}
