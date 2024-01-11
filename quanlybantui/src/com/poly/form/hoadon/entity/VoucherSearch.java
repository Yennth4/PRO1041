/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.hoadon.entity;

/**
 *
 * @author longnvph31848
 */
public class VoucherSearch {

    private Long idVoucher;
    private String maVoucher;
    private String tenVoucher;
    private Integer loaiVoucher;
    private Integer mucGiamGia;
    private Integer mucChiTieuToiThieu;
    private Integer soLuong;

    public VoucherSearch() {
    }

    public VoucherSearch(Long idVoucher, String maVoucher, String tenVoucher, Integer loaiVoucher, Integer mucGiamGia, Integer mucChiTieuToiThieu, Integer soLuong) {
        this.idVoucher = idVoucher;
        this.maVoucher = maVoucher;
        this.tenVoucher = tenVoucher;
        this.loaiVoucher = loaiVoucher;
        this.mucGiamGia = mucGiamGia;
        this.mucChiTieuToiThieu = mucChiTieuToiThieu;
        this.soLuong = soLuong;
    }

    public Long getIdVoucher() {
        return idVoucher;
    }

    public void setIdVoucher(Long idVoucher) {
        this.idVoucher = idVoucher;
    }

    public String getMaVoucher() {
        return maVoucher;
    }

    public void setMaVoucher(String maVoucher) {
        this.maVoucher = maVoucher;
    }

    public String getTenVoucher() {
        return tenVoucher;
    }

    public void setTenVoucher(String tenVoucher) {
        this.tenVoucher = tenVoucher;
    }

    public Integer getLoaiVoucher() {
        return loaiVoucher;
    }

    public void setLoaiVoucher(Integer loaiVoucher) {
        this.loaiVoucher = loaiVoucher;
    }

    public Integer getMucGiamGia() {
        return mucGiamGia;
    }

    public void setMucGiamGia(Integer mucGiamGia) {
        this.mucGiamGia = mucGiamGia;
    }

    public Integer getMucChiTieuToiThieu() {
        return mucChiTieuToiThieu;
    }

    public void setMucChiTieuToiThieu(Integer mucChiTieuToiThieu) {
        this.mucChiTieuToiThieu = mucChiTieuToiThieu;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public String toString() {
        return "VoucherSearch{" + "idVoucher=" + idVoucher + ", maVoucher=" + maVoucher + ", tenVoucher=" + tenVoucher + ", loaiVoucher=" + loaiVoucher + ", mucGiamGia=" + mucGiamGia + ", mucChiTieuToiThieu=" + mucChiTieuToiThieu + ", soLuong=" + soLuong + '}';
    }

}
