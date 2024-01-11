/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.bienthesanpham.entity;

/**
 *
 * @author longnvph31848
 */
public class BienTheSanPhamDTO {

    private Long IdBienThe;
    private Integer soLuong;
    private Integer trangThai;
    private String mainImage;
    private Float giaBan;
    private String thoiGianTao;
    private String ma;
    private Float giaNiemYet;
    private String thoiGianSua;
    private Integer tongDaBan;
    private String mauSac;

    public BienTheSanPhamDTO() {
    }

    public BienTheSanPhamDTO(Long IdBienThe, Integer soLuong, Integer trangThai, String mainImage, Float giaBan, String thoiGianTao, String ma, Float giaNiemYet, String thoiGianSua, Integer tongDaBan, String mauSac) {
        this.IdBienThe = IdBienThe;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
        this.mainImage = mainImage;
        this.giaBan = giaBan;
        this.thoiGianTao = thoiGianTao;
        this.ma = ma;
        this.giaNiemYet = giaNiemYet;
        this.thoiGianSua = thoiGianSua;
        this.tongDaBan = tongDaBan;
        this.mauSac = mauSac;
    }

    public Object[] toDataRow() {
        return new Object[]{ma, mauSac, soLuong, giaNiemYet, giaBan, tongDaBan};
    }

    public Long getIdBienThe() {
        return IdBienThe;
    }

    public void setIdBienThe(Long IdBienThe) {
        this.IdBienThe = IdBienThe;
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

    public Float getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(Float giaBan) {
        this.giaBan = giaBan;
    }

    public String getThoiGianTao() {
        return thoiGianTao;
    }

    public void setThoiGianTao(String thoiGianTao) {
        this.thoiGianTao = thoiGianTao;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getThoiGianSua() {
        return thoiGianSua;
    }

    public void setThoiGianSua(String thoiGianSua) {
        this.thoiGianSua = thoiGianSua;
    }

    public Integer getTongDaBan() {
        return tongDaBan;
    }

    public void setTongDaBan(Integer tongDaBan) {
        this.tongDaBan = tongDaBan;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public Float getGiaNiemYet() {
        return giaNiemYet;
    }

    public void setGiaNiemYet(Float giaNiemYet) {
        this.giaNiemYet = giaNiemYet;
    }

    @Override
    public String toString() {
        return "BienTheSanPhamDTO{" + "IdBienThe=" + IdBienThe + ", soLuong=" + soLuong + ", trangThai=" + trangThai + ", mainImage=" + mainImage + ", giaBan=" + giaBan + ", thoiGianTao=" + thoiGianTao + ", ma=" + ma + ", giaNiemYet=" + giaNiemYet + ", thoiGianSua=" + thoiGianSua + ", tongDaBan=" + tongDaBan + ", mauSac=" + mauSac + '}';
    }

}
