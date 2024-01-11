/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.hoadon.entity;

/**
 *
 * @author longnvph31848
 */
public class KhachHangSearch {

    private Long idKhachHang;
    private String hoTen;
    private String sdt;
    private String email;
    private String diaChi;

    public KhachHangSearch() {
    }

    public KhachHangSearch(String hoTen, String sdt, String email, String diaChi) {
        this.hoTen = hoTen;
        this.sdt = sdt;
        this.email = email;
        this.diaChi = diaChi;
    }

    public KhachHangSearch(Long idKhachHang, String hoTen, String sdt, String email, String diaChi) {
        this.idKhachHang = idKhachHang;
        this.hoTen = hoTen;
        this.sdt = sdt;
        this.email = email;
        this.diaChi = diaChi;
    }

    public Object[] toDataRow() {
        return new Object[]{hoTen, sdt, email};
    }

    public Long getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(Long idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    @Override
    public String toString() {
        return "KhachHangSearch{" + "idKhachHang=" + idKhachHang + ", hoTen=" + hoTen + ", sdt=" + sdt + ", email=" + email + ", diaChi=" + diaChi + '}';
    }

}
