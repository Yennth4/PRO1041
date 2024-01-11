/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.doihang.entity.DoiHangResponse;

/**
 *
 * @author Admin
 */
public class PhieuDoiChiTiet {

    private Long id;
    private Float giaBan;
    private String tenSanPham;
    private String mau;
    private String chatLieu;
    private Integer soLuongDoi;
    private Integer idSanPhamChiTiet;
    private Integer idLyDoDoi;
    private Integer idPhieuDoi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(Float giaBan) {
        this.giaBan = giaBan;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getMau() {
        return mau;
    }

    public void setMau(String mau) {
        this.mau = mau;
    }

    public String getChatLieu() {
        return chatLieu;
    }

    public void setChatLieu(String chatLieu) {
        this.chatLieu = chatLieu;
    }

    public Integer getSoLuongDoi() {
        return soLuongDoi;
    }

    public void setSoLuongDoi(Integer soLuongDoi) {
        this.soLuongDoi = soLuongDoi;
    }

    public Integer getIdSanPhamChiTiet() {
        return idSanPhamChiTiet;
    }

    public void setIdSanPhamChiTiet(Integer idSanPhamChiTiet) {
        this.idSanPhamChiTiet = idSanPhamChiTiet;
    }

    public Integer getIdLyDoDoi() {
        return idLyDoDoi;
    }

    public void setIdLyDoDoi(Integer idLyDoDoi) {
        this.idLyDoDoi = idLyDoDoi;
    }

    public Integer getIdPhieuDoi() {
        return idPhieuDoi;
    }

    public void setIdPhieuDoi(Integer idPhieuDoi) {
        this.idPhieuDoi = idPhieuDoi;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
    private String moTa;

    public PhieuDoiChiTiet() {
    }

    public PhieuDoiChiTiet(Long id, Float giaBan, String tenSanPham, String mau, String chatLieu, Integer soLuongDoi, Integer idSanPhamChiTiet, Integer idLyDoDoi, Integer idPhieuDoi, String moTa) {
        this.id = id;
        this.giaBan = giaBan;
        this.tenSanPham = tenSanPham;
        this.mau = mau;
        this.chatLieu = chatLieu;
        this.soLuongDoi = soLuongDoi;
        this.idSanPhamChiTiet = idSanPhamChiTiet;
        this.idLyDoDoi = idLyDoDoi;
        this.idPhieuDoi = idPhieuDoi;
        this.moTa = moTa;
    }

}
