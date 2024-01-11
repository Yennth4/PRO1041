/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.thongke.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class SanPhamEntity {

    private Long id;
    private Integer tongSoLuong;
    private String tenMau;
    private String tenSanPham;
    private Integer soLuong;
    private String ma;
    private Float phanTram;
    private String tenTheLoai;

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public SanPhamEntity() {
    }

    public SanPhamEntity(Long id, Integer tongSoLuong, String tenMau, String tenSanPham, Integer soLuong, String ma, Float phanTram, String tenTheLoai) {
        this.id = id;
        this.tongSoLuong = tongSoLuong;
        this.tenMau = tenMau;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.ma = ma;
        this.phanTram = phanTram;
        this.tenTheLoai = tenTheLoai;
    }

    @Override
    public String toString() {
        return "<td>" + ma + "</td><td>" + tenSanPham + "</td><td>" + tenMau + "</td><td>" + soLuong + "</td><td>" + tenTheLoai + "</td><td>" + phanTram + "%</td>";

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTongSoLuong() {
        return tongSoLuong;
    }

    public void setTongSoLuong(Integer tongSoLuong) {
        this.tongSoLuong = tongSoLuong;
    }

    public String getTenMau() {
        return tenMau;
    }

    public void setTenMau(String tenMau) {
        this.tenMau = tenMau;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public Float getPhanTram() {
        return phanTram;
    }

    public void setPhanTram(Float phanTram) {
        this.phanTram = phanTram;
    }

}
