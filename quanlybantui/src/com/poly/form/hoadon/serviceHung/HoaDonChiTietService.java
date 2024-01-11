/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.hoadon.serviceHung;

import com.poly.form.hoadon.entityHung.HoaDonChiTietHung;
import com.poly.form.hoadon.repositoryHung.HoaDonChiTietRepository;
import java.util.List;

/**
 *
 * @author Admin
 */
public class HoaDonChiTietService {
    
    HoaDonChiTietRepository hoaDonChiTietRepository = new HoaDonChiTietRepository();
    
     public List<HoaDonChiTietHung> getAllSanPhamByIdHoaDon(int idHoaDon) {
         return hoaDonChiTietRepository.getAllSanPhamByIdHoaDon(idHoaDon);
     }
     
}
