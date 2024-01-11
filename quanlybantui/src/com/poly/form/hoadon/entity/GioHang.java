/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.hoadon.entity;

/**
 *
 * @author longnvph31848
 */
public class GioHang {

    private Long id;
    private Long idSanPhamChiTiet;
    private String maSanPham;
    private String tenSanPham;
    private String tenMau;
    private Integer soLuong;
    private Float giaBan;
    private Float giamKhuyenMai;
    private Float thanhTien;

    public GioHang() {
    }

    public GioHang(Long id, Long idSanPhamChiTiet, String maSanPham, String tenSanPham, String tenMau, Integer soLuong, Float giaBan, Float giamKhuyenMai, Float thanhTien) {
        this.id = id;
        this.idSanPhamChiTiet = idSanPhamChiTiet;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.tenMau = tenMau;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.giamKhuyenMai = giamKhuyenMai;
        this.thanhTien = thanhTien;
    }

    public Object[] toDataRow() {
        return new Object[]{
            maSanPham,
            tenSanPham,
            tenMau,
            soLuong,
            giaBan,
            giamKhuyenMai,
            thanhTien
        };
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdSanPhamChiTiet() {
        return idSanPhamChiTiet;
    }

    public void setIdSanPhamChiTiet(Long idSanPhamChiTiet) {
        this.idSanPhamChiTiet = idSanPhamChiTiet;
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

    public Float getGiamKhuyenMai() {
        return giamKhuyenMai;
    }

    public void setGiamKhuyenMai(Float giamKhuyenMai) {
        this.giamKhuyenMai = giamKhuyenMai;
    }

    public Float getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(Float thanhTien) {
        this.thanhTien = thanhTien;
    }

    @Override
    public String toString() {
        return "GioHang{" + "id=" + id + ", idSanPhamChiTiet=" + idSanPhamChiTiet + ", maSanPham=" + maSanPham + ", tenSanPham=" + tenSanPham + ", tenMau=" + tenMau + ", soLuong=" + soLuong + ", giaBan=" + giaBan + ", giamKhuyenMai=" + giamKhuyenMai + ", thanhTien=" + thanhTien + '}';
    }

    

}
