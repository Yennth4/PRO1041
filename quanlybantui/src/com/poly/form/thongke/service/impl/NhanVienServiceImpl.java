/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.thongke.service.impl;

import com.poly.form.thongke.entity.NhanVienEntity;
import com.poly.form.thongke.entity.SanPhamEntity;
import com.poly.form.thongke.repository.NhanVienRepository;
import com.poly.form.thongke.service.NhanVienService;
import java.util.List;

/**
 *
 * @author Admin
 */
public class NhanVienServiceImpl implements NhanVienService {

    private NhanVienRepository repo = new NhanVienRepository();

    public NhanVienServiceImpl() {
    }
    
    public List<NhanVienEntity> findAll() {
        List<NhanVienEntity> list = repo.findAll();
        list.sort((a, b) -> Float.compare(b.getPhanTram(), a.getPhanTram()));
        return list;
    }
}
