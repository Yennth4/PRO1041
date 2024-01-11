/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.thuoctinh.service;

import com.poly.form.thuoctinh.entity.ThuocTinhMau;
import com.poly.form.thuoctinh.entity.ThuocTinhMauDTO;
import com.poly.form.thuoctinh.repository.ThuocTinhMauRepository;
import java.util.List;

/**
 *
 * @author longnvph31848
 */
public class ThuocTinhMauService implements IThuocTinhMauService {

    private ThuocTinhMauRepository repo;

    public ThuocTinhMauService() {
        repo = new ThuocTinhMauRepository();
    }

    @Override
    public List<ThuocTinhMauDTO> getAll() {
        return repo.getAll();
    }

    @Override
    public boolean isExistTen(String str) {
        return repo.isExistTen(str.trim());
    }

    @Override
    public void insertMau(ThuocTinhMau mau) {
        repo.insertMau(mau);
    }

    @Override
    public void updateMau(ThuocTinhMau mau) {
        repo.updateMau(mau);
    }

    @Override
    public void deleteMau(Long id) {
        repo.deleteMau(id);
    }

    @Override
    public void tatTrangThai(Long id) {
        repo.tatTrangThai(id);
    }

    @Override
    public List<ThuocTinhMauDTO> search(String keyword, int cbxSanPhamIndexType, Integer soSanPham, int cbxBTSPType, Integer soBTSP, int cbxSoLuongCai, Integer soLuongCai, int cbxTrangThai, int cbxThoiGianSearchType, String dateFirstFormat, String timeFirst, String dateSecondFormat, String timeSecond) {
        return repo.search(keyword, cbxSanPhamIndexType, soSanPham, cbxBTSPType, soBTSP, cbxSoLuongCai, soLuongCai, cbxTrangThai, cbxThoiGianSearchType, dateFirstFormat, timeFirst, dateSecondFormat, timeSecond);
    }

    @Override
    public boolean isExistMa(String str) {
        return repo.isExistMa(str.trim());
    }

    @Override
    public Long getIDMauByTen(String ten) {
        return repo.getIDMauByTen(ten.trim());
    }

}
