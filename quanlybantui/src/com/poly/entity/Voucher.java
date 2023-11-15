package com.poly.entity;

import java.util.Date;

public class Voucher {

    private int id;
    private String ma;
    private String ten;
    private int phanTramGiamGia;
    private Date dateBatDau;
    private Date dateKetThuc;
    private Date dateSua;
    private Date dateTao;
    private int soLuong;
    private String trangThai;

    public Voucher() {
    }

    public Voucher(int id, String ma, String ten, int phanTramGiamGia, Date dateBatDau, Date dateKetThuc, Date dateSua, Date dateTao, int soLuong, String trangThai) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.phanTramGiamGia = phanTramGiamGia;
        this.dateBatDau = dateBatDau;
        this.dateKetThuc = dateKetThuc;
        this.dateSua = dateSua;
        this.dateTao = dateTao;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
    }

    public Voucher(String ma, String ten, int phanTramGiamGia, Date dateBatDau, Date dateKetThuc, Date dateSua, Date dateTao, int soLuong, String trangThai) {
        this.ma = ma;
        this.ten = ten;
        this.phanTramGiamGia = phanTramGiamGia;
        this.dateBatDau = dateBatDau;
        this.dateKetThuc = dateKetThuc;
        this.dateSua = dateSua;
        this.dateTao = dateTao;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getPhanTramGiamGia() {
        return phanTramGiamGia;
    }

    public void setPhanTramGiamGia(int phanTramGiamGia) {
        this.phanTramGiamGia = phanTramGiamGia;
    }

    public Date getDateBatDau() {
        return dateBatDau;
    }

    public void setDateBatDau(Date dateBatDau) {
        this.dateBatDau = dateBatDau;
    }

    public Date getDateKetThuc() {
        return dateKetThuc;
    }

    public void setDateKetThuc(Date dateKetThuc) {
        this.dateKetThuc = dateKetThuc;
    }

    public Date getDateSua() {
        return dateSua;
    }

    public void setDateSua(Date dateSua) {
        this.dateSua = dateSua;
    }

    public Date getDateTao() {
        return dateTao;
    }

    public void setDateTao(Date dateTao) {
        this.dateTao = dateTao;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }
    
}
