package com.poly.entity;

import java.util.Date;

public class LichSuVoucher {
    private int id;
    private String ma;
    private int idVoucher;
    private Date thoiGianSuDung;
    private int soTienGiamGia;
    private int soTienTruocGiamGia;
    private int soTienSauGiamGia;
    private String ghiChu;

    public LichSuVoucher() {
    }

    public LichSuVoucher(int id, String ma, int idVoucher, Date thoiGianSuDung, int soTienGiamGia, int soTienTruocGiamGia, int soTienSauGiamGia, String ghiChu) {
        this.id = id;
        this.ma = ma;
        this.idVoucher = idVoucher;
        this.thoiGianSuDung = thoiGianSuDung;
        this.soTienGiamGia = soTienGiamGia;
        this.soTienTruocGiamGia = soTienTruocGiamGia;
        this.soTienSauGiamGia = soTienSauGiamGia;
        this.ghiChu = ghiChu;
    }

    public LichSuVoucher(String ma, int idVoucher, Date thoiGianSuDung, int soTienGiamGia, int soTienTruocGiamGia, int soTienSauGiamGia, String ghiChu) {
        this.ma = ma;
        this.idVoucher = idVoucher;
        this.thoiGianSuDung = thoiGianSuDung;
        this.soTienGiamGia = soTienGiamGia;
        this.soTienTruocGiamGia = soTienTruocGiamGia;
        this.soTienSauGiamGia = soTienSauGiamGia;
        this.ghiChu = ghiChu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public int getIdVoucher() {
        return idVoucher;
    }

    public void setIdVoucher(int idVoucher) {
        this.idVoucher = idVoucher;
    }

    public Date getThoiGianSuDung() {
        return thoiGianSuDung;
    }

    public void setThoiGianSuDung(Date thoiGianSuDung) {
        this.thoiGianSuDung = thoiGianSuDung;
    }

    public int getSoTienGiamGia() {
        return soTienGiamGia;
    }

    public void setSoTienGiamGia(int soTienGiamGia) {
        this.soTienGiamGia = soTienGiamGia;
    }

    public int getSoTienTruocGiamGia() {
        return soTienTruocGiamGia;
    }

    public void setSoTienTruocGiamGia(int soTienTruocGiamGia) {
        this.soTienTruocGiamGia = soTienTruocGiamGia;
    }

    public int getSoTienSauGiamGia() {
        return soTienSauGiamGia;
    }

    public void setSoTienSauGiamGia(int soTienSauGiamGia) {
        this.soTienSauGiamGia = soTienSauGiamGia;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    
    
}