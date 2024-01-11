/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.khachhang.entity;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class HoaDonKhachHang {

    private String maHoaDon;
    private Date ngayTao;
    private int tongSanPham;
    private double tongTienHoaDon;
    private String tenNhanVien;
    private String tenKhachHang;
    private double soTienDaGiam;
    private double tongTienSauKhuyenMai;
    private double tongTienPhaiTra;
    private String ghiChu;

    public HoaDonKhachHang() {
    }

    public HoaDonKhachHang(String maHoaDon, Date ngayTao, int tongSanPham, double tongTienHoaDon, String tenNhanVien, String tenKhachHang, double soTienDaGiam, double tongTienSauKhuyenMai, double tongTienPhaiTra, String ghiChu) {
        this.maHoaDon = maHoaDon;
        this.ngayTao = ngayTao;
        this.tongSanPham = tongSanPham;
        this.tongTienHoaDon = tongTienHoaDon;
        this.tenNhanVien = tenNhanVien;
        this.tenKhachHang = tenKhachHang;
        this.soTienDaGiam = soTienDaGiam;
        this.tongTienSauKhuyenMai = tongTienSauKhuyenMai;
        this.tongTienPhaiTra = tongTienPhaiTra;
        this.ghiChu = ghiChu;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public int getTongSanPham() {
        return tongSanPham;
    }

    public void setTongSanPham(int tongSanPham) {
        this.tongSanPham = tongSanPham;
    }

    public double getTongTienHoaDon() {
        return tongTienHoaDon;
    }

    public void setTongTienHoaDon(double tongTienHoaDon) {
        this.tongTienHoaDon = tongTienHoaDon;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public double getSoTienDaGiam() {
        return soTienDaGiam;
    }

    public void setSoTienDaGiam(double soTienDaGiam) {
        this.soTienDaGiam = soTienDaGiam;
    }

    public double getTongTienSauKhuyenMai() {
        return tongTienSauKhuyenMai;
    }

    public void setTongTienSauKhuyenMai(double tongTienSauKhuyenMai) {
        this.tongTienSauKhuyenMai = tongTienSauKhuyenMai;
    }

    public double getTongTienPhaiTra() {
        return tongTienPhaiTra;
    }

    public void setTongTienPhaiTra(double tongTienPhaiTra) {
        this.tongTienPhaiTra = tongTienPhaiTra;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    @Override
    public String toString() {
        return "HoaDonSanPham{" + "maHoaDon=" + maHoaDon + ", ngayTao=" + ngayTao + ", tongSanPham=" + tongSanPham + ", tongTienHoaDon=" + tongTienHoaDon + ", tenNhanVien=" + tenNhanVien + ", tenKhachHang=" + tenKhachHang + ", soTienDaGiam=" + soTienDaGiam + ", tongTienSauKhuyenMai=" + tongTienSauKhuyenMai + ", tongTienPhaiTra=" + tongTienPhaiTra + ", ghiChu=" + ghiChu + '}';
    }
    
    

}
