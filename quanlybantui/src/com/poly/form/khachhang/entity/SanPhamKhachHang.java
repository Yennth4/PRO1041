/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.khachhang.entity;

/**
 *
 * @author Admin
 */
public class SanPhamKhachHang {
    
    private String maSanPham;
    private String tenSanPham;
    private String moTa;
    private String theLoai;

    public SanPhamKhachHang() {
    }

    public SanPhamKhachHang(String maSanPham, String tenSanPham, String moTa, String theLoai) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.moTa = moTa;
        this.theLoai = theLoai;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    @Override
    public String toString() {
        return "SanPhamKhachHang{" + "maSanPham=" + maSanPham + ", tenSanPham=" + tenSanPham + ", moTa=" + moTa + ", theLoai=" + theLoai + '}';
    }

    
    
    
}
