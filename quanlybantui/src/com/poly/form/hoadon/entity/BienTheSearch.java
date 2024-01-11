/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.hoadon.entity;

/**
 *
 * @author longnvph31848
 */
public class BienTheSearch {

    private Long idBienThe;
    private String maSanPham;
    private String tenSanPham;
    private String tenMau;
    private Integer soLuong;
    private Float giaBan;
    private Integer phanTramKhuyenMai;

    public BienTheSearch() {
    }

    public BienTheSearch(Long idBienThe, String maSanPham, String tenSanPham, String tenMau, Integer soLuong, Float giaBan, Integer phanTramKhuyenMai) {
        this.idBienThe = idBienThe;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.tenMau = tenMau;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.phanTramKhuyenMai = phanTramKhuyenMai;
    }

    public Object[] toDataRow() {
        return new Object[]{
            maSanPham,
            tenSanPham,
            tenMau,
            soLuong,
            giaBan,
            "-" + phanTramKhuyenMai + "%"
        };
    }

    public Long getIdBienThe() {
        return idBienThe;
    }

    public void setIdBienThe(Long idBienThe) {
        this.idBienThe = idBienThe;
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

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Float getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(Float giaBan) {
        this.giaBan = giaBan;
    }

    public Integer getPhanTramKhuyenMai() {
        return phanTramKhuyenMai;
    }

    public void setPhanTramKhuyenMai(Integer phanTramKhuyenMai) {
        this.phanTramKhuyenMai = phanTramKhuyenMai;
    }

    @Override
    public String toString() {
        return "BienTheSearch{" + "idBienThe=" + idBienThe + ", maSanPham=" + maSanPham + ", tenSanPham=" + tenSanPham + ", tenMau=" + tenMau + ", soLuong=" + soLuong + ", giaBan=" + giaBan + ", phanTramKhuyenMai=" + phanTramKhuyenMai + '}';
    }

}
