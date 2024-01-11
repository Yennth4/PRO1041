/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.doihang.entity.DoiHangResponse;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class HoaDon {

    private Long id;
    private String maHoaDon;
    private String ghiChu;
    private Float tongTienHoaDon;
    private Float tongTienSauKhuyenMai;
    private String tenKhachHang;
    private String trangThai;
    private Date thoiGianTao;
    private String sdt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }
 

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Float getTongTienHoaDon() {
        return tongTienHoaDon;
    }

    public void setTongTienHoaDon(Float tongTienHoaDon) {
        this.tongTienHoaDon = tongTienHoaDon;
    }

    public Float getTongTienSauKhuyenMai() {
        return tongTienSauKhuyenMai;
    }

    public void setTongTienSauKhuyenMai(Float tongTienSauKhuyenMai) {
        this.tongTienSauKhuyenMai = tongTienSauKhuyenMai;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public Date getThoiGianTao() {
        return thoiGianTao;
    }

    public void setThoiGianTao(Date thoiGianTao) {
        this.thoiGianTao = thoiGianTao;
    }

    public HoaDon() {
    }

    public HoaDon(Long id, String maHoaDon , String ghiChu, Float tongTienHoaDon, Float tongTienSauKhuyenMai, String tenKhachHang, 
            String trangThai, Date thoiGianTao, String sdt) {
        this.id = id;
        this.maHoaDon = maHoaDon; 
        this.ghiChu = ghiChu;
        this.tongTienHoaDon = tongTienHoaDon;
        this.tongTienSauKhuyenMai = tongTienSauKhuyenMai;
        this.tenKhachHang = tenKhachHang;
        this.trangThai = trangThai;
        this.thoiGianTao = thoiGianTao;
        this.sdt = sdt;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

}
