/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.doihang.entity.DoiHangResponse;

/**
 *
 * @author Admin
 */
public class MauSacResponse {

    private Integer id; 
    private String ten;
 

    public MauSacResponse() {
    }

    public MauSacResponse(Integer id,  String ten) {
        this.id = id; 
        this.ten = ten;
    }

     
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

}
