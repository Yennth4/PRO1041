/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.form.thongke.service;

import com.poly.form.thongke.entity.SanPhamEntity;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface SanPhamService {

    List<SanPhamEntity> findAll();
    
}
