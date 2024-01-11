/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.form.theloai.service;

import com.poly.form.sanpham.entity.SanPhamDTO;
import com.poly.form.theloai.entity.TheLoai;
import com.poly.form.theloai.entity.TheLoaiDTO;
import java.util.List;

/**
 *
 * @author longnvph31848
 */
public interface ITheLoaiService {

    List<TheLoaiDTO> getAll();

    boolean isExistMa(String str);

    boolean isExistTen(String str);

    void insertTheLoai(TheLoai theLoai);

    void updateTheLoai(TheLoai theLoai);

    void deleteTheLoai(Long id);

    void tatTrangThai(Long id);

    List<TheLoaiDTO> search(String keyword, int cbxSanPhamIndexType, Integer soSanPham,
            int cbxBTSPType, Integer soBTSP, int cbxSoLuongCai, Integer soLuongCai,
            int cbxTrangThai, int cbxThoiGianSearchType, String dateFirstFormat, String timeFirst,
            String dateSecondFormat, String timeSecond);
}
