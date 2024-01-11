/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.nhacungcap.entity;

/**
 *
 * @author longnvph31848
 */
public class NhaCungCapDTO {

    private Long id;
    private String maNCC;
    private String tenNCC;
    private String moTaNCC;
    private Integer trangThai;
    private String sdtNCC;
    private String emailNCC;
    private String hopDongThoaThuan;
    private String thoiGianTao;
    private String thoiGianSua;
    private Integer sanPham;
    private Integer sanPhamChiTiet;
    private Integer soLuongCai;

    public NhaCungCapDTO() {
    }

    public NhaCungCapDTO(Long id, String maNCC, String tenNCC, String moTaNCC, Integer trangThai, String sdtNCC, String emailNCC, String hopDongThoaThuan, String thoiGianTao, String thoiGianSua, Integer sanPham, Integer sanPhamChiTiet, Integer soLuongCai) {
        this.id = id;//
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;//
        this.moTaNCC = moTaNCC;//
        this.trangThai = trangThai;//
        this.sdtNCC = sdtNCC;//
        this.emailNCC = emailNCC;//
        this.hopDongThoaThuan = hopDongThoaThuan;//
        this.thoiGianTao = thoiGianTao;//
        this.thoiGianSua = thoiGianSua;//
        this.sanPham = sanPham;
        this.sanPhamChiTiet = sanPhamChiTiet;
        this.soLuongCai = soLuongCai;
    }

    public Object[] toDataRow() {
        return new Object[]{tenNCC, maNCC, sdtNCC, emailNCC,
            (sanPham != null) ? sanPham : "0",
            (sanPhamChiTiet != null) ? sanPhamChiTiet : "0",
            (soLuongCai != null) ? soLuongCai : "0"};
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    public String getMoTaNCC() {
        return moTaNCC;
    }

    public void setMoTaNCC(String moTaNCC) {
        this.moTaNCC = moTaNCC;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public String getSdtNCC() {
        return sdtNCC;
    }

    public void setSdtNCC(String sdtNCC) {
        this.sdtNCC = sdtNCC;
    }

    public String getEmailNCC() {
        return emailNCC;
    }

    public void setEmailNCC(String emailNCC) {
        this.emailNCC = emailNCC;
    }

    public String getHopDongThoaThuan() {
        return hopDongThoaThuan;
    }

    public void setHopDongThoaThuan(String hopDongThoaThuan) {
        this.hopDongThoaThuan = hopDongThoaThuan;
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

    public Integer getSanPham() {
        return sanPham;
    }

    public void setSanPham(Integer sanPham) {
        this.sanPham = sanPham;
    }

    public Integer getSanPhamChiTiet() {
        return sanPhamChiTiet;
    }

    public void setSanPhamChiTiet(Integer sanPhamChiTiet) {
        this.sanPhamChiTiet = sanPhamChiTiet;
    }

    public Integer getSoLuongCai() {
        return soLuongCai;
    }

    public void setSoLuongCai(Integer soLuongCai) {
        this.soLuongCai = soLuongCai;
    }

    @Override
    public String toString() {
        return "NhaCungCapDTO{" + "id=" + id + ", maNCC=" + maNCC + ", tenNCC=" + tenNCC + ", moTaNCC=" + moTaNCC + ", trangThai=" + trangThai + ", sdtNCC=" + sdtNCC + ", emailNCC=" + emailNCC + ", hopDongThoaThuan=" + hopDongThoaThuan + ", thoiGianTao=" + thoiGianTao + ", thoiGianSua=" + thoiGianSua + ", sanPham=" + sanPham + ", sanPhamChiTiet=" + sanPhamChiTiet + ", soLuongCai=" + soLuongCai + '}';
    }

}
