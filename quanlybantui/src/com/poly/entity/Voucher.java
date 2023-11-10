package com.poly.entity;

public class Voucher {
    private int id;
    private String ten;
    private int phanTramGiamGia;
    private int dateBatDau;
    private int dateKetThuc;
    private int dateSua;
    private int dateTao;

    public Voucher() {
    }

    public Voucher(int id, String ten, int phanTramGiamGia, int dateBatDau, int dateKetThuc, int dateSua, int dateTao) {
        this.id = id;
        this.ten = ten;
        this.phanTramGiamGia = phanTramGiamGia;
        this.dateBatDau = dateBatDau;
        this.dateKetThuc = dateKetThuc;
        this.dateSua = dateSua;
        this.dateTao = dateTao;
    }

    public Voucher(String ten, int phanTramGiamGia, int dateBatDau, int dateKetThuc, int dateSua, int dateTao) {
        this.ten = ten;
        this.phanTramGiamGia = phanTramGiamGia;
        this.dateBatDau = dateBatDau;
        this.dateKetThuc = dateKetThuc;
        this.dateSua = dateSua;
        this.dateTao = dateTao;
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

    public int getDateBatDau() {
        return dateBatDau;
    }

    public void setDateBatDau(int dateBatDau) {
        this.dateBatDau = dateBatDau;
    }

    public int getDateKetThuc() {
        return dateKetThuc;
    }

    public void setDateKetThuc(int dateKetThuc) {
        this.dateKetThuc = dateKetThuc;
    }

    public int getDateSua() {
        return dateSua;
    }

    public void setDateSua(int dateSua) {
        this.dateSua = dateSua;
    }

    public int getDateTao() {
        return dateTao;
    }

    public void setDateTao(int dateTao) {
        this.dateTao = dateTao;
    }

    @Override
    public String toString() {
        return "Voucher{" + "id=" + id + ", ten=" + ten + ", phanTramGiamGia=" + phanTramGiamGia + ", dateBatDau=" + dateBatDau + ", dateKetThuc=" + dateKetThuc + ", dateSua=" + dateSua + ", dateTao=" + dateTao + '}';
    }
    
   
}
