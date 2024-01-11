/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.khachhang.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class LichSuGiaoDich {
    
    private int id;
    private String maHoaDon;
    private Date ngayGiaoDich;
    private int capBacKhachHang;
    private BigDecimal tongTienGiaoDich;

    public LichSuGiaoDich() {
    }

    public LichSuGiaoDich(int id, String maHoaDon, int trangThai, Date ngayGiaoDich, int capBacKhachHang, BigDecimal tongTienGiaoDich) {
        this.id = id;
        this.maHoaDon = maHoaDon;
        this.ngayGiaoDich = ngayGiaoDich;
        this.capBacKhachHang = capBacKhachHang;
        this.tongTienGiaoDich = tongTienGiaoDich;
    }

    public LichSuGiaoDich(String maHoaDon, int trangThai, Date ngayGiaoDich, int capBacKhachHang, BigDecimal tongTienGiaoDich) {
        this.maHoaDon = maHoaDon;
        this.ngayGiaoDich = ngayGiaoDich;
        this.capBacKhachHang = capBacKhachHang;
        this.tongTienGiaoDich = tongTienGiaoDich;
    }
    
    
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public Date getNgayGiaoDich() {
        return ngayGiaoDich;
    }

    public void setNgayGiaoDich(Date ngayGiaoDich) {
        this.ngayGiaoDich = ngayGiaoDich;
    }

    public BigDecimal getTongTienGiaoDich() {
        return tongTienGiaoDich;
    }

    public void setTongTienGiaoDich(BigDecimal tongTienGiaoDich) {
        this.tongTienGiaoDich = tongTienGiaoDich;
    }

    public int getCapBacKhachHang() {
        return capBacKhachHang;
    }

    public void setCapBacKhachHang(int capBacKhachHang) {
        this.capBacKhachHang = capBacKhachHang;
    }
    
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LichSuGiaoDich{");
        sb.append("id=").append(id);
        sb.append(", maHoaDon=").append(maHoaDon);
        sb.append(", ngayGiaoDich=").append(ngayGiaoDich);
        sb.append(", tongTienGiaoDich=").append(tongTienGiaoDich);
        sb.append('}');
        return sb.toString();
    }
    
    
}
