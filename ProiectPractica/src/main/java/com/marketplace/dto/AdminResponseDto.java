package com.marketplace.dto;

import com.marketplace.model.Admin;

import java.util.List;

public class AdminResponseDto {
    private List<Admin> admins;
    private long totalAdmins;
    private int from;
    private int to;

    public AdminResponseDto() {
    }

    public AdminResponseDto(List<Admin> admins, long totalAdmins, int from, int to) {
        this.admins=admins;
        this.totalAdmins=totalAdmins;
        this.from=from;
        this.to=to;
    }

    public List<Admin> getDoctors() {
        return admins;
    }

    public void setAdmins(List<Admin> doctors) {
        this.admins=admins;
    }

    public long getTotalAdmins() {
        return totalAdmins;
    }

    public void setTotalAdmins(long totalAdmins) {
        this.totalAdmins=totalAdmins;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from=from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to=to;
    }
}
