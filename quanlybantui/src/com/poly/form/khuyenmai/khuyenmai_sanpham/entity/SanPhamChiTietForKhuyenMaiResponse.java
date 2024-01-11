package com.poly.form.khuyenmai.khuyenmai_sanpham.entity;

public class SanPhamChiTietForKhuyenMaiResponse {

    private Long id;
    
    private String ten;

    private String maSanPham;

    private Double giaNiemYet;

    private Double giaBan;

    private Long soLuong;

    private String mau;

    public SanPhamChiTietForKhuyenMaiResponse() {
    }

    public SanPhamChiTietForKhuyenMaiResponse(Long id, String ten, String maSanPham, Double giaNiemYet, Double giaBan, Long soLuong, String mau) {
        this.id = id;
        this.ten = ten;
        this.maSanPham = maSanPham;
        this.giaNiemYet = giaNiemYet;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
        this.mau = mau;
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

    
    
    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public Double getGiaNiemYet() {
        return giaNiemYet;
    }

    public void setGiaNiemYet(Double giaNiemYet) {
        this.giaNiemYet = giaNiemYet;
    }

    public Double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(Double giaBan) {
        this.giaBan = giaBan;
    }

    public Long getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Long soLuong) {
        this.soLuong = soLuong;
    }

    public String getMau() {
        return mau;
    }

    public void setMau(String mau) {
        this.mau = mau;
    }

    public Object[] toRowTable(Long giaGiamKhuyenMai) {
        return new Object[]{
            maSanPham, ten, mau, giaNiemYet, giaBan, giaGiamKhuyenMai * giaNiemYet / 100, giaBan - giaGiamKhuyenMai * giaNiemYet / 100 
        };
    }

}
