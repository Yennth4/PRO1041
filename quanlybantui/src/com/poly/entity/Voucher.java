package com.poly.entity;

import java.util.Date;

public class Voucher {

    private int id;
    private String ten;
    private int phanTramGiamGia;
    private Date dateBatDau;
    private Date dateKetThuc;
    private Date dateSua;
    private Date dateTao;

    public Voucher() {
    }

    public Voucher(int id, String ten, int phanTramGiamGia, Date dateBatDau, Date dateKetThuc, Date dateSua, Date dateTao) {
        this.id = id;
        this.ten = ten;
        this.phanTramGiamGia = phanTramGiamGia;
        this.dateBatDau = dateBatDau;
        this.dateKetThuc = dateKetThuc;
        this.dateSua = dateSua;
        this.dateTao = dateTao;
    }

    public Voucher(String ten, int phanTramGiamGia, Date dateBatDau, Date dateKetThuc, Date dateSua, Date dateTao) {
        this.ten = ten;
        this.phanTramGiamGia = phanTramGiamGia;
        this.dateBatDau = dateBatDau;
        this.dateKetThuc = dateKetThuc;
        this.dateSua = dateSua;
        this.dateTao = dateTao;
    }

    public Voucher(int id, String ten, int phanTramGiamGia, Date dateBatDau, Date dateKetThuc) {
        this.id = id;
        this.ten = ten;
        this.phanTramGiamGia = phanTramGiamGia;
        this.dateBatDau = dateBatDau;
        this.dateKetThuc = dateKetThuc;
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
}
