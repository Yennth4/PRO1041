/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.theloai.entity;

/**
 *
 * @author longnvph31848
 */
public class TheLoaiDTO {

    private Long idTheLoai;
    private String maTheLoai;
    private String tenTheLoai;
    private String moTaTheLoai;
    private Integer trangThai;
    private String thoiGianTao;
    private String thoiGianSua;
    private Integer tongSanPham;
    private Integer tongBTSP;
    private Integer soLuongCai;

    public TheLoaiDTO() {
    }

    public TheLoaiDTO(Long idTheLoai, String maTheLoai, String tenTheLoai, String moTaTheLoai, Integer trangThai, String thoiGianTao, String thoiGianSua, Integer tongSanPham, Integer tongBTSP, Integer soLuongCai) {
        this.idTheLoai = idTheLoai;
        this.maTheLoai = maTheLoai;
        this.tenTheLoai = tenTheLoai;
        this.moTaTheLoai = moTaTheLoai;
        this.trangThai = trangThai;
        this.thoiGianTao = thoiGianTao;
        this.thoiGianSua = thoiGianSua;
        this.tongSanPham = tongSanPham;
        this.tongBTSP = tongBTSP;
        this.soLuongCai = soLuongCai;
    }

    public Long getIdTheLoai() {
        return idTheLoai;
    }

    public void setIdTheLoai(Long idTheLoai) {
        this.idTheLoai = idTheLoai;
    }

    public String getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(String maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public String getMoTaTheLoai() {
        return moTaTheLoai;
    }

    public void setMoTaTheLoai(String moTaTheLoai) {
        this.moTaTheLoai = moTaTheLoai;
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

    public Integer getTongSanPham() {
        return tongSanPham;
    }

    public void setTongSanPham(Integer tongSanPham) {
        this.tongSanPham = tongSanPham;
    }

    public Integer getTongBTSP() {
        return tongBTSP;
    }

    public void setTongBTSP(Integer tongBTSP) {
        this.tongBTSP = tongBTSP;
    }

    public Integer getSoLuongCai() {
        return soLuongCai;
    }

    public void setSoLuongCai(Integer soLuongCai) {
        this.soLuongCai = soLuongCai;
    }

    @Override
    public String toString() {
        return "TheLoaiDTO{" + "idTheLoai=" + idTheLoai + ", maTheLoai=" + maTheLoai + ", tenTheLoai=" + tenTheLoai + ", moTaTheLoai=" + moTaTheLoai + ", trangThai=" + trangThai + ", thoiGianTao=" + thoiGianTao + ", thoiGianSua=" + thoiGianSua + ", tongSanPham=" + tongSanPham + ", tongBTSP=" + tongBTSP + ", soLuongCai=" + soLuongCai + '}';
    }

    public Object[] toDataRow() {
        return new Object[]{(trangThai == 0) ? maTheLoai + " *" : maTheLoai, tenTheLoai, tongSanPham, tongBTSP, soLuongCai};
    }

}
