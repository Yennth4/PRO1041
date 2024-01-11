/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.doihang.entity.DoiHangResponse;

/**
 *
 * @author Admin
 */
public class ProductDetailResponse {
    private Long idHoaDonSanPham;
    private Long idsanphamdetail;
    private Integer soLuong;
    private Float giaTien;
    private String tenMau;
    private String tenSanPham;
    private String maSanPham;
    private String tenNhaCungCap;
    private String trangThai;
    private String lyDoDoi;
    public ProductDetailResponse() {
    }

    public Long getIdHoaDonSanPham() {
        return idHoaDonSanPham;
    }

    public String getLyDoDoi() {
        return lyDoDoi;
    }

    public void setLyDoDoi(String lyDoDoi) {
        this.lyDoDoi = lyDoDoi;
    }

    public void setIdHoaDonSanPham(Long idHoaDonSanPham) {
        this.idHoaDonSanPham = idHoaDonSanPham;
    }

    public ProductDetailResponse(Long idHoaDonSanPham,Long idsanphamdetail, Integer soLuong, Float giaTien, String tenMau, String tenSanPham, String maSanPham, String tenNhaCungCap, String trangThai,  String lyDo) {
        this.idHoaDonSanPham=idHoaDonSanPham;
        this.idsanphamdetail = idsanphamdetail;
        this.soLuong = soLuong;
        this.giaTien = giaTien;
        this.tenMau = tenMau;
        this.tenSanPham = tenSanPham;
        this.maSanPham = maSanPham;
        this.tenNhaCungCap = tenNhaCungCap;
        this.trangThai = trangThai;
        this.lyDoDoi=lyDo;
    }
  public ProductDetailResponse(Long idHoaDonSanPham,Long idsanphamdetail, Integer soLuong, Float giaTien, String tenMau, String tenSanPham, String maSanPham, String tenNhaCungCap, String trangThai  ) {
        this.idHoaDonSanPham=idHoaDonSanPham;
        this.idsanphamdetail = idsanphamdetail;
        this.soLuong = soLuong;
        this.giaTien = giaTien;
        this.tenMau = tenMau;
        this.tenSanPham = tenSanPham;
        this.maSanPham = maSanPham;
        this.tenNhaCungCap = tenNhaCungCap;
        this.trangThai = trangThai;
    }
    public Long getIdsanphamdetail() {
        return idsanphamdetail;
    }

    public void setIdsanphamdetail(Long idsanphamdetail) {
        this.idsanphamdetail = idsanphamdetail;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Float getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(Float giaTien) {
        this.giaTien = giaTien;
    }

    public String getTenMau() {
        return tenMau;
    }

    public void setTenMau(String tenMau) {
        this.tenMau = tenMau;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
 

}
