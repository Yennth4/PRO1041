/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.nhacungcap.service;

import com.poly.form.nhacungcap.entity.NhaCungCap;
import com.poly.form.nhacungcap.entity.NhaCungCapDTO;
import com.poly.form.nhacungcap.repository.NhaCungCapRepository;
import java.util.List;

/**
 *
 * @author longnvph31848
 */
public class NhaCungCapService implements INhaCungCapService {

    private NhaCungCapRepository repo;

    public NhaCungCapService() {
        repo = new NhaCungCapRepository();
    }

    @Override
    public List<NhaCungCapDTO> getAll() {
        return repo.getAll();
    }

    @Override
    public boolean isExistTen(String str) {
        return repo.isExistTen(str.trim());
    }

    @Override
    public void insertNCC(NhaCungCap ncc) {
        repo.insertNCC(ncc);
    }

    @Override
    public void updateNCC(NhaCungCap ncc) {
        repo.updateNCC(ncc);
    }

    @Override
    public void deleteNCC(Long id) {
        repo.deleteNCC(id);
    }

    @Override
    public void tatTrangThai(Long id) {
        repo.tatTrangThai(id);
    }

    @Override
    public List<NhaCungCapDTO> search(String keyword, int cbxSanPhamIndexType, Integer soSanPham, int cbxBTSPType, Integer soBTSP, int cbxSoLuongCai, Integer soLuongCai, int cbxTrangThai, int cbxThoiGianSearchType, String dateFirstFormat, String timeFirst, String dateSecondFormat, String timeSecond) {
        return repo.search(keyword, cbxSanPhamIndexType, soSanPham, cbxBTSPType, soBTSP, cbxSoLuongCai, soLuongCai, cbxTrangThai, cbxThoiGianSearchType, dateFirstFormat, timeFirst, dateSecondFormat, timeSecond);
    }

    @Override
    public boolean isExistMa(String str) {
        return repo.isExistMa(str.trim());
    }

}
