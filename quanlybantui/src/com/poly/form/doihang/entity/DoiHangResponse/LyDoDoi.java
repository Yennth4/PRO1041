/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.doihang.entity.DoiHangResponse;

/**
 *
 * @author Admin
 */
public class LyDoDoi {

    private Long id;
    private String lyDo;
    private String moTa;

    public LyDoDoi() {
    }

    public LyDoDoi(Long id, String lyDo, String moTa) {
        this.id = id;
        this.lyDo = lyDo;
        this.moTa = moTa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLyDo() {
        return lyDo;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

}
