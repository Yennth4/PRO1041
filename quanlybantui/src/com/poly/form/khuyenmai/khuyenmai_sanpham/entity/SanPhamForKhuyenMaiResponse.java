package com.poly.form.khuyenmai.khuyenmai_sanpham.entity;

public class SanPhamForKhuyenMaiResponse {

    private Long id;

    private String ma;

    private String ten;

    public SanPhamForKhuyenMaiResponse() {
    }

    public SanPhamForKhuyenMaiResponse(Long id, String ma, String ten) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Object[] toRowTable() {
        return new Object[]{
            ma, ten
        };
    }

}
