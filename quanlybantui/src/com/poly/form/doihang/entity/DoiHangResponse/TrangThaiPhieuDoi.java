/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.doihang.entity.DoiHangResponse;

/**
 *
 * @author Admin
 */
public class TrangThaiPhieuDoi {

    private Long id;
    private String tenTrangThai;
    private String mota;

    public TrangThaiPhieuDoi() {
    }

    public TrangThaiPhieuDoi(Long id, String tenTrangThai, String mota) {
        this.id = id;
        this.tenTrangThai = tenTrangThai;
        this.mota = mota;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenTrangThai() {
        return tenTrangThai;
    }

    public void setTenTrangThai(String tenTrangThai) {
        this.tenTrangThai = tenTrangThai;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

}
