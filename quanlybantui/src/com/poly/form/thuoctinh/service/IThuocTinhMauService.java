/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.form.thuoctinh.service;

import com.poly.form.thuoctinh.entity.ThuocTinhMau;
import com.poly.form.thuoctinh.entity.ThuocTinhMauDTO;
import java.util.List;

/**
 *
 * @author longnvph31848
 */
public interface IThuocTinhMauService {

    List<ThuocTinhMauDTO> getAll();

    boolean isExistTen(String str);

    void insertMau(ThuocTinhMau mau);

    void updateMau(ThuocTinhMau mau);

    void deleteMau(Long id);

    void tatTrangThai(Long id);

    List<ThuocTinhMauDTO> search(String keyword, int cbxSanPhamIndexType, Integer soSanPham, int cbxBTSPType, Integer soBTSP, int cbxSoLuongCai, Integer soLuongCai, int cbxTrangThai, int cbxThoiGianSearchType, String dateFirstFormat, String timeFirst, String dateSecondFormat, String timeSecond);

    boolean isExistMa(String str);

    Long getIDMauByTen(String ten);
}
