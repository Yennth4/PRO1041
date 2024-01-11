/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.theloai.service;

import com.poly.form.sanpham.entity.SanPhamDTO;
import com.poly.form.theloai.entity.TheLoai;
import com.poly.form.theloai.entity.TheLoaiDTO;
import com.poly.form.theloai.repository.TheLoaiRepository;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author longnvph31848
 */
public class TheLoaiService implements ITheLoaiService {

    private TheLoaiRepository repo;

    public TheLoaiService() {
        repo = new TheLoaiRepository();
    }

    @Override
    public List<TheLoaiDTO> getAll() {
        return repo.getAll();
    }

    @Override
    public boolean isExistMa(String str) {
        return repo.isExistMa(str.trim());
    }

    @Override
    public boolean isExistTen(String str) {
        return repo.isExistTen(str.trim());
    }

    @Override
    public void insertTheLoai(TheLoai theLoai) {
        repo.insertTheLoai(theLoai);
    }

    @Override
    public void updateTheLoai(TheLoai theLoai) {
        repo.updateTheLoai(theLoai);
    }

    @Override
    public void deleteTheLoai(Long id) {
        repo.deleteTheLoai(id);
    }

    @Override
    public void tatTrangThai(Long id) {
        repo.tatTrangThai(id);
    }

    @Override
    public List<TheLoaiDTO> search(String keyword, int cbxSanPhamIndexType, Integer soSanPham,
            int cbxBTSPType, Integer soBTSP, int cbxSoLuongCai, Integer soLuongCai,
            int cbxTrangThai, int cbxThoiGianSearchType, String dateFirstFormat, String timeFirst,
            String dateSecondFormat, String timeSecond) {
        return repo.search(keyword, cbxSanPhamIndexType, soSanPham,
                cbxBTSPType, soBTSP, cbxSoLuongCai, soLuongCai,
                cbxTrangThai, cbxThoiGianSearchType, dateFirstFormat, timeFirst,
                dateSecondFormat, timeSecond);
    }

}
