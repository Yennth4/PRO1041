/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.form.sanpham.service;

import com.poly.form.sanpham.entity.SanPham;
import com.poly.form.sanpham.entity.SanPhamDTO;
import java.util.List;

/**
 *
 * @author longnvph31848
 */
public interface ISanPhamService {

    List<SanPhamDTO> getAll();

    boolean isExistMa(String str);

    void insertSanPham(SanPham sanPham);

    void updateSanPham(SanPham sanPham);

    void deleteSanPham(Long id);

    void tatTrangThai(Long id);

    List<String> getImageSanPham(Long id);

    List<SanPhamDTO> search(String keyword, Long idTheLoai, Long idNCC,
            Long idMau, int cbxSearchSoLuongCai, Integer txtCbxSearchSoLuongCai,
            int cbxSearchBienThe, Integer txtCbxSearchBienThe, int cbxSearchDateType,
            String txtDateFirst, String txtTimeFirst, String txtDateSecond,
            String txtTimeSecond, int cbxSearchTrangThai);

    Long getIDSanPhamByMa(String ma);

    
}
