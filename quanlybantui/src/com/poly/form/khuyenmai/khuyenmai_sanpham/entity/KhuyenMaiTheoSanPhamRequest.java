package com.poly.form.khuyenmai.khuyenmai_sanpham.entity;

public class KhuyenMaiTheoSanPhamRequest {

    private String input;

    private Integer giaTri;

    private Long ngayBatDau;

    private Long ngayKetThuc;

    private Boolean trangThai;

    private Long thoiDiemHienTai;

    public KhuyenMaiTheoSanPhamRequest() {
    }

    public KhuyenMaiTheoSanPhamRequest(String input, Integer giaTri, Long ngayBatDau, Long ngayKetThuc, Boolean trangThai, Long thoiDiemHienTai) {
        this.input = input;
        this.giaTri = giaTri;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.trangThai = trangThai;
        this.thoiDiemHienTai = thoiDiemHienTai;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
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

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    public Long getThoiDiemHienTai() {
        return thoiDiemHienTai;
    }

    public void setThoiDiemHienTai(Long thoiDiemHienTai) {
        this.thoiDiemHienTai = thoiDiemHienTai;
    }

}
