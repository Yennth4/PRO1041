/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.hoadon.entityHung;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class HoaDonHung {

    private int idHoaDon;
    private String maHoaDon;
    private String ghiChu;
    private BigDecimal tongTienHoaDon;
    private BigDecimal TongTienSauKhuyenMai;
    private String tenKhachHang;
    private String tenNhanVien;
    private BigDecimal soTienDaGiam;
    private BigDecimal TongTienPhaiTra;
    private Date ThoiGianSua;
    private Date THoiGianTao;
    private int TrangThai;
    private BigDecimal TienMatKhachTra;
    private BigDecimal TienKhachChuyenKhoan;
    private int HinhThucThanhToan;
    private int HinhThuc;
    private String maKhachHang;

    public HoaDonHung() {

    }

    public HoaDonHung(int idHoaDon, String maHoaDon, String ghiChu, BigDecimal tongTienHoaDon, BigDecimal TongTienSauKhuyenMai, String tenKhachHang, String tenNhanVien, BigDecimal soTienDaGiam, BigDecimal TongTienPhaiTra, Date ThoiGianSua, Date THoiGianTao, int TrangThai, BigDecimal TienMatKhachTra, BigDecimal TienKhachChuyenKhoan, int HinhThucThanhToan, int HinhThuc, String maKhachHang) {
        this.idHoaDon = idHoaDon;
        this.maHoaDon = maHoaDon;
        this.ghiChu = ghiChu;
        this.tongTienHoaDon = tongTienHoaDon;
        this.TongTienSauKhuyenMai = TongTienSauKhuyenMai;
        this.tenKhachHang = tenKhachHang;
        this.tenNhanVien = tenNhanVien;
        this.soTienDaGiam = soTienDaGiam;
        this.TongTienPhaiTra = TongTienPhaiTra;
        this.ThoiGianSua = ThoiGianSua;
        this.THoiGianTao = THoiGianTao;
        this.TrangThai = TrangThai;
        this.TienMatKhachTra = TienMatKhachTra;
        this.TienKhachChuyenKhoan = TienKhachChuyenKhoan;
        this.HinhThucThanhToan = HinhThucThanhToan;
        this.HinhThuc = HinhThuc;
        this.maKhachHang = maKhachHang;
    }

    public BigDecimal getTienKhachChuyenKhoan() {
        return TienKhachChuyenKhoan;
    }

    public void setTienKhachChuyenKhoan(BigDecimal TienKhachChuyenKhoan) {
        this.TienKhachChuyenKhoan = TienKhachChuyenKhoan;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public int getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
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

    public BigDecimal getTongTienHoaDon() {
        return tongTienHoaDon;
    }

    public void setTongTienHoaDon(BigDecimal tongTienHoaDon) {
        this.tongTienHoaDon = tongTienHoaDon;
    }

    public BigDecimal getTongTienSauKhuyenMai() {
        return TongTienSauKhuyenMai;
    }

    public void setTongTienSauKhuyenMai(BigDecimal TongTienSauKhuyenMai) {
        this.TongTienSauKhuyenMai = TongTienSauKhuyenMai;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public BigDecimal getSoTienDaGiam() {
        return soTienDaGiam;
    }

    public void setSoTienDaGiam(BigDecimal soTienDaGiam) {
        this.soTienDaGiam = soTienDaGiam;
    }

    public BigDecimal getTongTienPhaiTra() {
        return TongTienPhaiTra;
    }

    public void setTongTienPhaiTra(BigDecimal TongTienPhaiTra) {
        this.TongTienPhaiTra = TongTienPhaiTra;
    }

    public Date getThoiGianSua() {
        return ThoiGianSua;
    }

    public void setThoiGianSua(Date ThoiGianSua) {
        this.ThoiGianSua = ThoiGianSua;
    }

    public Date getTHoiGianTao() {
        return THoiGianTao;
    }

    public void setTHoiGianTao(Date THoiGianTao) {
        this.THoiGianTao = THoiGianTao;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }

    public BigDecimal getTienMatKhachTra() {
        return TienMatKhachTra;
    }

    public void setTienMatKhachTra(BigDecimal TienMatKhachTra) {
        this.TienMatKhachTra = TienMatKhachTra;
    }

    public int getHinhThucThanhToan() {
        return HinhThucThanhToan;
    }

    public void setHinhThucThanhToan(int HinhThucThanhToan) {
        this.HinhThucThanhToan = HinhThucThanhToan;
    }

    public int getHinhThuc() {
        return HinhThuc;
    }

    public void setHinhThuc(int HinhThuc) {
        this.HinhThuc = HinhThuc;
    }

    @Override
    public String toString() {
        return "HoaDonHung{" + "idHoaDon=" + idHoaDon + ", maHoaDon=" + maHoaDon + ", ghiChu=" + ghiChu + ", tongTienHoaDon=" + tongTienHoaDon + ", TongTienSauKhuyenMai=" + TongTienSauKhuyenMai + ", tenKhachHang=" + tenKhachHang + ", tenNhanVien=" + tenNhanVien + ", soTienDaGiam=" + soTienDaGiam + ", TongTienPhaiTra=" + TongTienPhaiTra + ", ThoiGianSua=" + ThoiGianSua + ", THoiGianTao=" + THoiGianTao + ", TrangThai=" + TrangThai + ", TienMatKhachTra=" + TienMatKhachTra + ", HinhThucThanhToan=" + HinhThucThanhToan + '}';
    }

}
