/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.hoadon.entity;

/**
 *
 * @author longnvph31848
 */
public class HoaDon {

    private Long idHoaDon;
    private String ghiChu;
    private Float tongTienHoaDon;
    private Float tongTienSauKhuyenMai;
    private Long idNhanVien;
    private Long idKhachHang;
    private Float soTienDaGiam;
    private Float tongTienPhaiTra;
    private String maHoaDon;
    private Integer trangThai;
    private Float tienMatKhachTra;
    private Integer hinhThucThanhToan;
    private Integer hinhThucMua;

    public HoaDon() {
    }

    public HoaDon(Long idNhanVien, String maHoaDon, Integer trangThai) {
        this.idNhanVien = idNhanVien;
        this.maHoaDon = maHoaDon;
        this.trangThai = trangThai;
    }

    public HoaDon(Long idHoaDon, String ghiChu, Float tongTienHoaDon, Float tongTienSauKhuyenMai, Long idNhanVien, Long idKhachHang, Float soTienDaGiam, Float tongTienPhaiTra, String maHoaDon, Integer trangThai, Float tienMatKhachTra, Integer hinhThucThanhToan, Integer hinhThucMua) {
        this.idHoaDon = idHoaDon;
        this.ghiChu = ghiChu;
        this.tongTienHoaDon = tongTienHoaDon;
        this.tongTienSauKhuyenMai = tongTienSauKhuyenMai;
        this.idNhanVien = idNhanVien;
        this.idKhachHang = idKhachHang;
        this.soTienDaGiam = soTienDaGiam;
        this.tongTienPhaiTra = tongTienPhaiTra;
        this.maHoaDon = maHoaDon;
        this.trangThai = trangThai;
        this.tienMatKhachTra = tienMatKhachTra;
        this.hinhThucThanhToan = hinhThucThanhToan;
        this.hinhThucMua = hinhThucMua;
    }

    public Long getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(Long idHoaDon) {
        this.idHoaDon = idHoaDon;
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

    public Long getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(Long idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public Long getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(Long idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public Float getSoTienDaGiam() {
        return soTienDaGiam;
    }

    public void setSoTienDaGiam(Float soTienDaGiam) {
        this.soTienDaGiam = soTienDaGiam;
    }

    public Float getTongTienPhaiTra() {
        return tongTienPhaiTra;
    }

    public void setTongTienPhaiTra(Float tongTienPhaiTra) {
        this.tongTienPhaiTra = tongTienPhaiTra;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public Float getTienMatKhachTra() {
        return tienMatKhachTra;
    }

    public void setTienMatKhachTra(Float tienMatKhachTra) {
        this.tienMatKhachTra = tienMatKhachTra;
    }

    public Integer getHinhThucThanhToan() {
        return hinhThucThanhToan;
    }

    public void setHinhThucThanhToan(Integer hinhThucThanhToan) {
        this.hinhThucThanhToan = hinhThucThanhToan;
    }

    public Integer getHinhThucMua() {
        return hinhThucMua;
    }

    public void setHinhThucMua(Integer hinhThucMua) {
        this.hinhThucMua = hinhThucMua;
    }

    @Override
    public String toString() {
        return "HoaDon{" + "idHoaDon=" + idHoaDon + ", ghiChu=" + ghiChu + ", tongTienHoaDon=" + tongTienHoaDon + ", tongTienSauKhuyenMai=" + tongTienSauKhuyenMai + ", idNhanVien=" + idNhanVien + ", idKhachHang=" + idKhachHang + ", soTienDaGiam=" + soTienDaGiam + ", tongTienPhaiTra=" + tongTienPhaiTra + ", maHoaDon=" + maHoaDon + ", trangThai=" + trangThai + ", tienMatKhachTra=" + tienMatKhachTra + ", hinhThucThanhToan=" + hinhThucThanhToan + ", hinhThucMua=" + hinhThucMua + '}';
    }

}
