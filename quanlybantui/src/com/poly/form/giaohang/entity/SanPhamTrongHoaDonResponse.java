package com.poly.form.giaohang.entity;

public class SanPhamTrongHoaDonResponse {

    private String ten;

    private Float gia;

    private Long soLuong;

    public SanPhamTrongHoaDonResponse() {
    }

    public SanPhamTrongHoaDonResponse(String ten, Float gia, Long soLuong) {
        this.ten = ten;
        this.gia = gia;
        this.soLuong = soLuong;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Float getGia() {
        return gia;
    }

    public void setGia(Float gia) {
        this.gia = gia;
    }

    public Long getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Long soLuong) {
        this.soLuong = soLuong;
    }

    public Object[] toRowTable() {
        return new Object[]{
            ten, gia, soLuong, "Đang đóng gói"
        };
    }

}
