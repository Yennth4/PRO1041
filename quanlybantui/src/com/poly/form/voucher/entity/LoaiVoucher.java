package com.poly.form.voucher.entity;

public class LoaiVoucher {

    private int idLoai;
    private String maLoai;
    private String tenLoai;
    private String moTaLoai;

    public LoaiVoucher() {
    }

    public LoaiVoucher(int idLoai, String maLoai, String tenLoai, String moTaLoai) {
        this.idLoai = idLoai;
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
        this.moTaLoai = moTaLoai;
    }

    public int getIdLoai() {
        return idLoai;
    }

    public void setIdLoai(int idLoai) {
        this.idLoai = idLoai;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public String getMoTaLoai() {
        return moTaLoai;
    }

    public void setMoTaLoai(String moTaLoai) {
        this.moTaLoai = moTaLoai;
    }

    public LoaiVoucher(String maLoai, String tenLoai, String moTaLoai) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
        this.moTaLoai = moTaLoai;
    }

    public Object[] toDataRow() {
        return new Object[]{
            idLoai, maLoai, tenLoai, moTaLoai
        };
    }
}
