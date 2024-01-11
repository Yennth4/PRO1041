/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.hoadon.entityHung;

import java.math.BigDecimal;


public class HoaDonChiTietHung {
    private String maSanPham;
    private String tenSanPham;
    private String tenMau;
    private BigDecimal giaTien;
    private int soLuong;
    private BigDecimal TongTien;

    public HoaDonChiTietHung() {
    }

    public HoaDonChiTietHung(String maSanPham, String tenSanPham, String tenMau, BigDecimal giaTien, int soLuong, BigDecimal TongTien) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.tenMau = tenMau;
        this.giaTien = giaTien;
        this.soLuong = soLuong;
        this.TongTien = TongTien;
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

    public String getTenMau() {
        return tenMau;
    }

    public void setTenMau(String tenMau) {
        this.tenMau = tenMau;
    }

    public BigDecimal getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(BigDecimal giaTien) {
        this.giaTien = giaTien;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getTongTien() {
        return TongTien;
    }

    public void setTongTien(BigDecimal TongTien) {
        this.TongTien = TongTien;
    }

    @Override
    public String toString() {
        return "HoaDonChiTietHung{" + "maSanPham=" + maSanPham + ", tenSanPham=" + tenSanPham + ", tenMau=" + tenMau + ", giaTien=" + giaTien + ", soLuong=" + soLuong + ", TongTien=" + TongTien + '}';
    }

   
    
}
