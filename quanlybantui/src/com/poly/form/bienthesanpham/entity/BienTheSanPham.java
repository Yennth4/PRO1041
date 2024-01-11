/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.bienthesanpham.entity;

/**
 *
 * @author longnvph31848
 */
public class BienTheSanPham {

    private Long idBienThe;
    private Integer soLuong;
    private Integer trangThai;
    private String mainImage;
    private Long idSanPham;
    private Long idMau;
    private Float giaBan;
    private String ma;
    private Float giaNiemYet;

    public BienTheSanPham() {
    }

    public BienTheSanPham(Integer soLuong, Integer trangThai, String mainImage, Long idSanPham, Long idMau, Float giaBan, String ma, Float giaNiemYet) {
        this.soLuong = soLuong;
        this.trangThai = trangThai;
        this.mainImage = mainImage;
        this.idSanPham = idSanPham;
        this.idMau = idMau;
        this.giaBan = giaBan;
        this.ma = ma;
        this.giaNiemYet = giaNiemYet;
    }

    public BienTheSanPham(Long idBienThe, Integer soLuong, Integer trangThai, String mainImage, Long idSanPham, Long idMau, Float giaBan, String ma, Float giaNiemYet) {
        this.idBienThe = idBienThe;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
        this.mainImage = mainImage;
        this.idSanPham = idSanPham;
        this.idMau = idMau;
        this.giaBan = giaBan;
        this.ma = ma;
        this.giaNiemYet = giaNiemYet;
    }

    public Long getIdBienThe() {
        return idBienThe;
    }

    public void setIdBienThe(Long idBienThe) {
        this.idBienThe = idBienThe;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public Long getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(Long idSanPham) {
        this.idSanPham = idSanPham;
    }

    public Long getIdMau() {
        return idMau;
    }

    public void setIdMau(Long idMau) {
        this.idMau = idMau;
    }

    public Float getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(Float giaBan) {
        this.giaBan = giaBan;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public Float getGiaNiemYet() {
        return giaNiemYet;
    }

    public void setGiaNiemYet(Float giaNiemYet) {
        this.giaNiemYet = giaNiemYet;
    }

    @Override
    public String toString() {
        return "BienTheSanPham{" + "idBienThe=" + idBienThe + ", soLuong=" + soLuong + ", trangThai=" + trangThai + ", mainImage=" + mainImage + ", idSanPham=" + idSanPham + ", idMau=" + idMau + ", giaBan=" + giaBan + ", ma=" + ma + '}';
    }

}
