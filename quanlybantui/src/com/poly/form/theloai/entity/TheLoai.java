/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.theloai.entity;

/**
 *
 * @author longnvph31848
 */
public class TheLoai {

    private Long idTheLoai;
    private String maTheLoai;
    private String tenTheLoai;
    private String moTaTheLoai;
    private Integer trangThai;
    private String thoiGianTao;
    private String thoiGianSua;

    public TheLoai() {
    }

    public TheLoai(String maTheLoai, String tenTheLoai, String moTaTheLoai, Integer trangThai) {
        this.maTheLoai = maTheLoai;
        this.tenTheLoai = tenTheLoai;
        this.moTaTheLoai = moTaTheLoai;
        this.trangThai = trangThai;
    }

    public TheLoai(Long idTheLoai, String maTheLoai, String tenTheLoai, String moTaTheLoai, Integer trangThai) {
        this.idTheLoai = idTheLoai;
        this.maTheLoai = maTheLoai;
        this.tenTheLoai = tenTheLoai;
        this.moTaTheLoai = moTaTheLoai;
        this.trangThai = trangThai;
    }
    
    public Long getIdTheLoai() {
        return idTheLoai;
    }

    public void setIdTheLoai(Long idTheLoai) {
        this.idTheLoai = idTheLoai;
    }

    public String getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(String maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public String getMoTaTheLoai() {
        return moTaTheLoai;
    }

    public void setMoTaTheLoai(String moTaTheLoai) {
        this.moTaTheLoai = moTaTheLoai;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public String getThoiGianTao() {
        return thoiGianTao;
    }

    public void setThoiGianTao(String thoiGianTao) {
        this.thoiGianTao = thoiGianTao;
    }

    public String getThoiGianSua() {
        return thoiGianSua;
    }

    public void setThoiGianSua(String thoiGianSua) {
        this.thoiGianSua = thoiGianSua;
    }

    @Override
    public String toString() {
        return "TheLoai{" + "idTheLoai=" + idTheLoai + ", maTheLoai=" + maTheLoai + ", tenTheLoai=" + tenTheLoai + ", moTaTheLoai=" + moTaTheLoai + ", trangThai=" + trangThai + ", thoiGianTao=" + thoiGianTao + ", thoiGianSua=" + thoiGianSua + '}';
    }

}
