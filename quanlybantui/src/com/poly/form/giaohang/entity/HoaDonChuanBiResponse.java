package com.poly.form.giaohang.entity;

public class HoaDonChuanBiResponse {

    private Long idHoaDon;

    private String maHoaDon;

    private String ghiChu;

    private Float tongTienHoaDon;

    private String tenNhanVien;

    private String tenKhachHang;

    private String sdtNhanVien;

    private String sdtKhachHang;
    
    private String diaChiKhachHang;

    public HoaDonChuanBiResponse() {
    }

    public HoaDonChuanBiResponse(Long idHoaDon, String maHoaDon, String ghiChu, Float tongTienHoaDon, String tenNhanVien, String tenKhachHang, String sdtNhanVien, String sdtKhachHang, String diaChiKhachHang) {
        this.idHoaDon = idHoaDon;
        this.maHoaDon = maHoaDon;
        this.ghiChu = ghiChu;
        this.tongTienHoaDon = tongTienHoaDon;
        this.tenNhanVien = tenNhanVien;
        this.tenKhachHang = tenKhachHang;
        this.sdtNhanVien = sdtNhanVien;
        this.sdtKhachHang = sdtKhachHang;
        this.diaChiKhachHang = diaChiKhachHang;
    }



    public Long getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(Long idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Float getTongTienHoaDon() {
        return tongTienHoaDon;
    }

    public void setTongTienHoaDon(Float tongTienHoaDon) {
        this.tongTienHoaDon = tongTienHoaDon;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getSdtNhanVien() {
        return sdtNhanVien;
    }

    public void setSdtNhanVien(String sdtNhanVien) {
        this.sdtNhanVien = sdtNhanVien;
    }

    public String getSdtKhachHang() {
        return sdtKhachHang;
    }

    public void setSdtKhachHang(String sdtKhachHang) {
        this.sdtKhachHang = sdtKhachHang;
    }

    public String getDiaChiKhachHang() {
        return diaChiKhachHang;
    }

    public void setDiaChiKhachHang(String diaChiKhachHang) {
        this.diaChiKhachHang = diaChiKhachHang;
    }   
    
    public Object[] toRowTable(){
        return new Object[]{
            maHoaDon, tenKhachHang, sdtKhachHang, tenNhanVien, sdtNhanVien, "Đang chuẩn bị giao"
        };
    }

}
