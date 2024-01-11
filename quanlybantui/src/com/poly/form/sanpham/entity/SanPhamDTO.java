/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.sanpham.entity;

/**
 *
 * @author longnvph31848
 */
public class SanPhamDTO {

    private Long idSanPham;
    private String maSanPham;
    private String tenSanPham;
    private String moTaSanPham;
    private String nhaCungCap;
    private Integer trangThai;
    private String theLoai;
    private String nhanVien;
    private String thoiGianTao;
    private String thoiGianSua;
    private Integer tongBienThe;
    private Integer soLuongCai;
    private String soLuongMau;

    public SanPhamDTO() {
    }

    public SanPhamDTO(Long idSanPham, String maSanPham, String tenSanPham, String moTaSanPham, String nhaCungCap, Integer trangThai, String theLoai, String nhanVien, String thoiGianTao, String thoiGianSua, Integer tongBienThe, Integer soLuongCai, String soLuongMau) {
        this.idSanPham = idSanPham;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.moTaSanPham = moTaSanPham;
        this.nhaCungCap = nhaCungCap;
        this.trangThai = trangThai;
        this.theLoai = theLoai;
        this.nhanVien = nhanVien;
        this.thoiGianTao = thoiGianTao;
        this.thoiGianSua = thoiGianSua;
        this.tongBienThe = tongBienThe;
        this.soLuongCai = soLuongCai;
        this.soLuongMau = soLuongMau;
    }

    public Object[] toDataRow() {
        return new Object[]{
            maSanPham, tenSanPham, theLoai, tongBienThe, soLuongCai, soLuongMau
        };
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

    public String getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(String nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public String getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(String nhanVien) {
        this.nhanVien = nhanVien;
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

    public Integer getTongBienThe() {
        return tongBienThe;
    }

    public void setTongBienThe(Integer tongBienThe) {
        this.tongBienThe = tongBienThe;
    }

    public Integer getSoLuongCai() {
        return soLuongCai;
    }

    public void setSoLuongCai(Integer soLuongCai) {
        this.soLuongCai = soLuongCai;
    }

    public String getSoLuongMau() {
        return soLuongMau;
    }

    public void setSoLuongMau(String soLuongMau) {
        this.soLuongMau = soLuongMau;
    }

    @Override
    public String toString() {
        return "SanPhamDTO{" + "idSanPham=" + idSanPham + ", maSanPham=" + maSanPham + ", tenSanPham=" + tenSanPham + ", moTaSanPham=" + moTaSanPham + ", nhaCungCap=" + nhaCungCap + ", trangThai=" + trangThai + ", theLoai=" + theLoai + ", nhanVien=" + nhanVien + ", thoiGianTao=" + thoiGianTao + ", thoiGianSua=" + thoiGianSua + ", tongBienThe=" + tongBienThe + ", soLuongCai=" + soLuongCai + ", soLuongMau=" + soLuongMau + '}';
    }

}
