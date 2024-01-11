/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.form.bienthesanpham.service;

import com.poly.form.bienthesanpham.entity.BienTheSanPham;
import com.poly.form.bienthesanpham.entity.BienTheSanPhamDTO;
import com.poly.form.sanpham.entity.SanPhamDTO;
import java.io.File;
import java.util.List;

/**
 *
 * @author longnvph31848
 */
public interface IBienTheSanPhamService {

    List<BienTheSanPhamDTO> findByMaSanPham(String maSanPham);

    boolean isExistMa(String str);

    boolean isExistBienThe(BienTheSanPham bienThe);

    void insertBienThe(BienTheSanPham bienThe);

    void updateBienThe(BienTheSanPham bienThe);

    void deleteBienThe(Long id);

    void tatTrangThai(Long id);

    List<BienTheSanPhamDTO> getAll();

    BienTheSanPhamDTO findBienTheByID(Long id);

    Long getIDMauBienTheByIDSanPham(Long idSanPham);

    boolean bienTheIsSale(Long id);

    boolean insertListBienThe(List<BienTheSanPham> list);

    String exportListToExcel(List<BienTheSanPhamDTO> list, SanPhamDTO sp);

    String importExcel();

    String exportMauExcel();

    String renderQRCodeByMaBienThe(String maSP, String maBTSP);

    void insertTextToImage(String path, String maSP, String maBTSP);

}
