/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.entity;

/**
 *
 * @author Admin
 */
public class NhanVien {
    private String manv;
    private String matKhau;
    private String HoTen;
    private String vaiTro;

    public NhanVien() {
    }

    public NhanVien(String manv, String matKhau, String HoTen, String vaiTro) {
        this.manv = manv;
        this.matKhau = matKhau;
        this.HoTen = HoTen;
        this.vaiTro = vaiTro;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }
    
    
}
