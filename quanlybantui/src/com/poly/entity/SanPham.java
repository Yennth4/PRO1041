package com.poly.entity;

public class SanPham {

    private Boolean select;
    private String ma;
    private String ten;
    private int gia;
    private String hang;
    private String mauSac;
    private String moTa;
    private String trangThai;

    public SanPham() {
    }

    public SanPham(Boolean select, String ma, String ten, int gia, String hang, String mauSac, String moTa, String trangThai) {
        this.select = select;
        this.ma = ma;
        this.ten = ten;
        this.gia = gia;
        this.hang = hang;
        this.mauSac = mauSac;
        this.moTa = moTa;
        this.trangThai = trangThai;
    }

    public boolean isSelected() {
        return select;
    }

    public void setSelected(boolean selected) {
        this.select = selected;
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

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getHang() {
        return hang;
    }

    public void setHang(String hang) {
        this.hang = hang;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
