/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.thongke.service.impl;

import com.poly.form.thongke.entity.SanPhamEntity;
import com.poly.form.thongke.repository.SanPhamRepository;
import com.poly.form.thongke.service.SanPhamService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class SanPhamServiceImpl implements SanPhamService {

    private SanPhamRepository phamRepository = new SanPhamRepository();

    public List<SanPhamEntity> findAll() {
        List<SanPhamEntity> list = phamRepository.findAll();
        list.sort((a, b) -> Float.compare(b.getPhanTram(), a.getPhanTram()));
        return list;
    }
}
