package com.poly.form.voucher.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Voucher {

    private int id;
    private String ma;
    private String ten;
    private int mucChiTieuToiThieu;
    private String loaiVoucher;
    private int phanTramGiamGia;
    private Date dateBatDau;
    private Date dateKetThuc;
    private String dateSua;
    private String dateTao;
    private int soLuong;
    private String trangThai;

    public Voucher() {
    }

    public Voucher(int id, String ma, String ten, int mucChiTieuToiThieu, String loaiVoucher, int phanTramGiamGia, Date dateBatDau, Date dateKetThuc, String dateSua, String dateTao, int soLuong, String trangThai) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.mucChiTieuToiThieu = mucChiTieuToiThieu;
        this.loaiVoucher = loaiVoucher;
        this.phanTramGiamGia = phanTramGiamGia;
        this.dateBatDau = dateBatDau;
        this.dateKetThuc = dateKetThuc;
        this.dateSua = dateSua;
        this.dateTao = dateTao;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
    }

    public Voucher(String ma, String ten, int mucChiTieuToiThieu, String loaiVoucher, int phanTramGiamGia, Date dateBatDau, Date dateKetThuc, String dateSua, String dateTao, int soLuong, String trangThai) {
        this.ma = ma;
        this.ten = ten;
        this.mucChiTieuToiThieu = mucChiTieuToiThieu;
        this.loaiVoucher = loaiVoucher;
        this.phanTramGiamGia = phanTramGiamGia;
        this.dateBatDau = dateBatDau;
        this.dateKetThuc = dateKetThuc;
        this.dateSua = dateSua;
        this.dateTao = dateTao;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
    }

    public Voucher(String ma, String ten, int mucChiTieuToiThieu, String loaiVoucher, int phanTramGiamGia, Date dateBatDau, Date dateKetThuc, int soLuong, String trangThai) {
        this.ma = ma;
        this.ten = ten;
        this.mucChiTieuToiThieu = mucChiTieuToiThieu;
        this.loaiVoucher = loaiVoucher;
        this.phanTramGiamGia = phanTramGiamGia;
        this.dateBatDau = dateBatDau;
        this.dateKetThuc = dateKetThuc;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
    }

    public int getMucChiTieuToiThieu() {
        return mucChiTieuToiThieu;
    }

    public void setMucChiTieuToiThieu(int mucChiTieuToiThieu) {
        this.mucChiTieuToiThieu = mucChiTieuToiThieu;
    }

    public String getLoaiVoucher() {
        return loaiVoucher;
    }

    public void setLoaiVoucher(String loaiVoucher) {
        this.loaiVoucher = loaiVoucher;
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

    public String getDateSua() {
        return dateSua;
    }

    public void setDateSua(String dateSua) {
        this.dateSua = dateSua;
    }

    public String getDateTao() {
        return dateTao;
    }

    public void setDateTao(String dateTao) {
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

    public Object[] toRowData() {
        Date ngayBatDauConvert = dateBatDau;
        Date ngayKetThucConvert = dateKetThuc;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        String ngayBatDauConverted = sdf.format(ngayBatDauConvert);
        String ngayKetThucConverted = sdf.format(ngayKetThucConvert);
        return new Object[]{
            id, ma, ten, mucChiTieuToiThieu + " VNĐ", phanTramGiamGia + "%", soLuong, trangThai, ngayBatDauConverted, ngayKetThucConverted, dateSua, dateTao
        };
    }
}
