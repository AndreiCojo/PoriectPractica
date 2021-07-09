package com.marketplace.dto;

import com.marketplace.model.Admin;

public class AdminDto {
    private String name;
    private int age;

    public AdminDto() {
    }

    public AdminDto(Admin admin) {
        this.name = admin.getName();
        this.age = admin.getAge();
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
}
