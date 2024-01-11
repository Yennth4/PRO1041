/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.sanpham.entity;

/**
 *
 * @author longnvph31848
 */
public class SanPham {

    private Long idSanPham;
    private String maSanPham;
    private String tenSanPham;
    private String moTaSanPham;
    private Long idNhaCungCap;
    private Integer trangThai;
    private Long idTheLoai;
    private Long idNhanVien;
    private String thoiGianTao;
    private String thoiGianSua;

    public SanPham() {
    }

    public SanPham(String maSanPham, String tenSanPham, String moTaSanPham, Long idNhaCungCap, Integer trangThai, Long idTheLoai, Long idNhanVien) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.moTaSanPham = moTaSanPham;
        this.idNhaCungCap = idNhaCungCap;
        this.trangThai = trangThai;
        this.idTheLoai = idTheLoai;
        this.idNhanVien = idNhanVien;
    }

    public SanPham(Long idSanPham, String maSanPham, String tenSanPham, String moTaSanPham, Long idNhaCungCap, Integer trangThai, Long idTheLoai, Long idNhanVien) {
        this.idSanPham = idSanPham;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.moTaSanPham = moTaSanPham;
        this.idNhaCungCap = idNhaCungCap;
        this.trangThai = trangThai;
        this.idTheLoai = idTheLoai;
        this.idNhanVien = idNhanVien;
    }

    public Long getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(Long idSanPham) {
        this.idSanPham = idSanPham;
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

    public String getMoTaSanPham() {
        return moTaSanPham;
    }

    public void setMoTaSanPham(String moTaSanPham) {
        this.moTaSanPham = moTaSanPham;
    }

    public Long getIdNhaCungCap() {
        return idNhaCungCap;
    }

    public void setIdNhaCungCap(Long idNhaCungCap) {
        this.idNhaCungCap = idNhaCungCap;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public Long getIdTheLoai() {
        return idTheLoai;
    }

    public void setIdTheLoai(Long idTheLoai) {
        this.idTheLoai = idTheLoai;
    }

    public Long getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(Long idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public String getThoiGianTao() {
        return thoiGianTao;
    }

    public void setThoiGianTao(String thoiGianTao) {
        this.thoiGianTao = thoiGianTao;
    }

    public String getThoiGianSua() {
        return thoiGianSua;
    }

    public void setThoiGianSua(String thoiGianSua) {
        this.thoiGianSua = thoiGianSua;
    }

    @Override
    public String toString() {
        return "SanPham{" + "idSanPham=" + idSanPham + ", maSanPham=" + maSanPham + ", tenSanPham=" + tenSanPham + ", moTaSanPham=" + moTaSanPham + ", idNhaCungCap=" + idNhaCungCap + ", trangThai=" + trangThai + ", idTheLoai=" + idTheLoai + ", idNhanVien=" + idNhanVien + ", thoiGianTao=" + thoiGianTao + ", thoiGianSua=" + thoiGianSua + '}';
    }

}
