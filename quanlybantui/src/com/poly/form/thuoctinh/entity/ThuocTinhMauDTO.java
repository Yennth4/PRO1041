/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.thuoctinh.entity;

/**
 *
 * @author longnvph31848
 */
public class ThuocTinhMauDTO {

    private Long IDMau;
    private String maMau;
    private String tenMau;
    private String moTaMau;
    private Integer trangThai;
    private String thoiGianTao;
    private String thoiGianSua;
    private Integer soSanPham;
    private Integer soBTSP;
    private Integer soLuongCai;

    public ThuocTinhMauDTO() {
    }

    public ThuocTinhMauDTO(Long IDMau, String maMau, String tenMau, String moTaMau, Integer trangThai, String thoiGianTao, String thoiGianSua, Integer soSanPham, Integer soBTSP, Integer soLuongCai) {
        this.IDMau = IDMau;
        this.maMau = maMau;
        this.tenMau = tenMau;
        this.moTaMau = moTaMau;
        this.trangThai = trangThai;
        this.thoiGianTao = thoiGianTao;
        this.thoiGianSua = thoiGianSua;
        this.soSanPham = soSanPham;
        this.soBTSP = soBTSP;
        this.soLuongCai = soLuongCai;
    }

    public Object[] toDataRow() {
        return new Object[]{maMau, tenMau, moTaMau, soSanPham, soBTSP, soLuongCai};
    }

    public Long getIDMau() {
        return IDMau;
    }

    public void setIDMau(Long IDMau) {
        this.IDMau = IDMau;
    }

    public String getMaMau() {
        return maMau;
    }

    public void setMaMau(String maMau) {
        this.maMau = maMau;
    }

    public String getTenMau() {
        return tenMau;
    }

    public void setTenMau(String tenMau) {
        this.tenMau = tenMau;
    }

    public String getMoTaMau() {
        return moTaMau;
    }

    public void setMoTaMau(String moTaMau) {
        this.moTaMau = moTaMau;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
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

    public Integer getSoSanPham() {
        return soSanPham;
    }

    public void setSoSanPham(Integer soSanPham) {
        this.soSanPham = soSanPham;
    }

    public Integer getSoBTSP() {
        return soBTSP;
    }

    public void setSoBTSP(Integer soBTSP) {
        this.soBTSP = soBTSP;
    }

    public Integer getSoLuongCai() {
        return soLuongCai;
    }

    public void setSoLuongCai(Integer soLuongCai) {
        this.soLuongCai = soLuongCai;
    }

    @Override
    public String toString() {
        return "ThuocTinhMauDTO{" + "IDMau=" + IDMau + ", maMau=" + maMau + ", tenMau=" + tenMau + ", moTaMau=" + moTaMau + ", trangThai=" + trangThai + ", thoiGianTao=" + thoiGianTao + ", thoiGianSua=" + thoiGianSua + ", soSanPham=" + soSanPham + ", soBTSP=" + soBTSP + ", soLuongCai=" + soLuongCai + '}';
    }

}
