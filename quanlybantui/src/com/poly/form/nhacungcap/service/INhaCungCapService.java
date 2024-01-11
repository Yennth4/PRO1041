/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poly.form.nhacungcap.service;

import com.poly.form.nhacungcap.entity.NhaCungCap;
import com.poly.form.nhacungcap.entity.NhaCungCapDTO;
import java.util.List;

/**
 *
 * @author longnvph31848
 */
public interface INhaCungCapService {

    List<NhaCungCapDTO> getAll();

    boolean isExistTen(String str);

    void insertNCC(NhaCungCap ncc);

    void updateNCC(NhaCungCap ncc);

    void deleteNCC(Long id);

    void tatTrangThai(Long id);

    List<NhaCungCapDTO> search(String keyword, int cbxSanPhamIndexType, Integer soSanPham, int cbxBTSPType, Integer soBTSP, int cbxSoLuongCai, Integer soLuongCai, int cbxTrangThai, int cbxThoiGianSearchType, String dateFirstFormat, String timeFirst, String dateSecondFormat, String timeSecond);

    boolean isExistMa (String str);
}
