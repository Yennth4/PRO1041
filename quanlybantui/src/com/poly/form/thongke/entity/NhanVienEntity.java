/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.thongke.entity;

/**
 *
 * @author Admin
 */
public class NhanVienEntity {

    private Long id;

    private String ten;

    private String email;

    private String sdt;

    private String maDinhDanh;

    private String userName;

    private String diaChi;

    private String gioiTinh;

    private String role;

    private Integer soLuongBanDuoc;

    private Integer tongSoLuongBanDuoc;

    private Float phanTram;

    private String trangThai;

    public NhanVienEntity() {
    }

    public NhanVienEntity(Long id, String ten, String email, String sdt, String maDinhDanh, String userName, String diaChi, String gioiTinh, String role, Integer soLuongBanDuoc, Integer tongSoLuongBanDuoc, Float phanTram, String trangThai) {
        this.id = id;
        this.ten = ten;
        this.email = email;
        this.sdt = sdt;
        this.maDinhDanh = maDinhDanh;
        this.userName = userName;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
        this.role = role;
        this.soLuongBanDuoc = soLuongBanDuoc;
        this.tongSoLuongBanDuoc = tongSoLuongBanDuoc;
        this.phanTram = phanTram;
        this.trangThai = trangThai;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getMaDinhDanh() {
        return maDinhDanh;
    }

    public void setMaDinhDanh(String maDinhDanh) {
        this.maDinhDanh = maDinhDanh;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getSoLuongBanDuoc() {
        return soLuongBanDuoc;
    }

    public void setSoLuongBanDuoc(Integer soLuongBanDuoc) {
        this.soLuongBanDuoc = soLuongBanDuoc;
    }

    public Integer getTongSoLuongBanDuoc() {
        return tongSoLuongBanDuoc;
    }

    public void setTongSoLuongBanDuoc(Integer tongSoLuongBanDuoc) {
        this.tongSoLuongBanDuoc = tongSoLuongBanDuoc;
    }

    public Float getPhanTram() {
        return phanTram;
    }

    public void setPhanTram(Float phanTram) {
        this.phanTram = phanTram;
    }

    @Override
    public String toString() {
        return "<td>" + userName + "</td><td>" + ten + "</td><td>" + gioiTinh + "</td><td>" + sdt + "</td><td>" + soLuongBanDuoc + "</td><td>" + phanTram + "%</td>";
    }
}
