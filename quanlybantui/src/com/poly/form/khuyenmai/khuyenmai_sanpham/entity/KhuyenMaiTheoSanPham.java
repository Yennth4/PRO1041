package com.poly.form.khuyenmai.khuyenmai_sanpham.entity;

import java.util.Date;
import java.text.SimpleDateFormat;

public class KhuyenMaiTheoSanPham {

    private Long id;

    private String ma;

    private String ten;

    private Integer giaTri;

    private Long ngayBatDau;

    private Long ngayKetThuc;

    private Long thoiGianTao;

    private Long thoiGianSua;

    private Boolean trangThai;

    public KhuyenMaiTheoSanPham() {
    }

    public KhuyenMaiTheoSanPham(Long id, String ma, String ten, Integer giaTri,
            Long ngayBatDau, Long ngayKetThuc, Long thoiGianTao,
            Long thoiGianSua, Boolean trangThai) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.giaTri = giaTri;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.thoiGianTao = thoiGianTao;
        this.thoiGianSua = thoiGianSua;
        this.trangThai = trangThai;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public Integer getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(Integer giaTri) {
        this.giaTri = giaTri;
    }

    public Long getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Long ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Long getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Long ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public Long getThoiGianTao() {
        return thoiGianTao;
    }

    public void setThoiGianTao(Long thoiGianTao) {
        this.thoiGianTao = thoiGianTao;
    }

    public Long getThoiGianSua() {
        return thoiGianSua;
    }

    public void setThoiGianSua(Long thoiGianSua) {
        this.thoiGianSua = thoiGianSua;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    public Object[] toRowTable() {

        Date ngayBatDauConvert = new Date(ngayBatDau * 1000);
        Date ngayKetThucConvert = new Date(ngayKetThuc * 1000);

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");

        String ngayBatDauConverted = sdf.format(ngayBatDauConvert);
        String ngayKetThucConverted = sdf.format(ngayKetThucConvert);

        return new Object[]{
            ma, ten, giaTri + "%", ngayBatDauConverted, ngayKetThucConverted, trangThai ? "Hoạt động" : "Không hoạt động"
        };
    }

}