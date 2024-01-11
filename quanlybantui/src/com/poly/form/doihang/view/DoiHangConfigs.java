/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.doihang.view;

/**
 *
 * @author Admin
 */
public class DoiHangConfigs {

    
    private String maHoaDon;

    private Long idHD;

    public Long getId() {
        return idHD;
    }

    public DoiHangConfigs(String maHoaDon, Long idHD) {
        this.maHoaDon = maHoaDon;
        this.idHD = idHD;
    }

    public void setIdHD(Long id) {
        this.idHD = id;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public DoiHangConfigs() {
    }

}
