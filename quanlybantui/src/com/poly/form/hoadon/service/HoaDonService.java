/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.hoadon.service;

import com.poly.form.hoadon.entity.BienTheSearch;
import com.poly.form.hoadon.entity.GioHang;
import com.poly.form.hoadon.entity.HoaDon;
import com.poly.form.hoadon.repository.HoaDonRepository;
import com.poly.form.hoadon.entity.HoaDonDTO;
import com.poly.form.hoadon.entity.KhachHangSearch;
import com.poly.form.hoadon.entity.VoucherSearch;
import java.util.List;

/**
 *
 * @author longnvph31848
 */
public class HoaDonService implements IHoaDonService {

    private HoaDonRepository repo;

    public HoaDonService() {
        repo = new HoaDonRepository();
    }

    @Override
    public List<HoaDonDTO> getAll() {
        return repo.getAll();
    }

    @Override
    public List<HoaDonDTO> getAllSearch(Integer hinhThucMua, Integer trangThai) {
        return repo.getAllSearch(hinhThucMua, trangThai);
    }

    @Override
    public List<GioHang> getAllBienTheByIdHoaDon(Long id, Long time) {
        return repo.getAllBienTheByIdHoaDon(id, time);
    }

    @Override
    public void insertHoaDon(HoaDon hd, Long IDkh) {
        repo.insertHoaDon(hd, IDkh);
    }

    @Override
    public void updateHoaDon(HoaDon hd) {
        repo.updateHoaDon(hd);
    }

    @Override
    public boolean isExistMa(String str) {
        return repo.isExistMa(str.trim());
    }

    @Override
    public Long getIDBienTheByMa(String ma) {
        return repo.getIDBienTheByMa(ma.trim());
    }

    @Override
    public List<BienTheSearch> searchSanPham(String keyword, String mau) {
        return repo.searchSanPham(keyword, mau);
    }

    @Override
    public List<KhachHangSearch> searchKhachHang(String key) {
        return repo.searchKhachHang(key);
    }

    @Override
    public List<KhachHangSearch> findKhachHangSearchBySDTEmail(String sdt, String email) {
        return repo.findKhachHangSearchBySDTEmail(sdt.trim(), email.trim());
    }

    @Override
    public void insertKhachHangSearch(KhachHangSearch kh) {
        repo.insertKhachHangSearch(kh);
    }

    @Override
    public KhachHangSearch findByID(Long id) {
        return repo.findByID(id);
    }

    @Override
    public Float getTongTienHoaDonById(Long id) {
        return repo.getTongTienHoaDonById(id);
    }

    @Override
    public Float getTienGiamHoaDonById(Long id, Long time) {
        return repo.getTienGiamHoaDonById(id, time);
    }

    @Override
    public Float getTienThanhToanHoaDonById(Long id, Long time) {
        return repo.getTienThanhToanHoaDonById(id, time);
    }

    @Override
    public void insertBienTheToHoaDon(Long idHoaDonLong, Long idBienThe, Float giaTien, Integer soLuong) {
        repo.insertBienTheToHoaDon(idHoaDonLong, idBienThe, giaTien, soLuong);
    }

    @Override
    public void updateBienTheToHoaDon(Long id, Integer soLuong) {
        repo.updateBienTheToHoaDon(id, soLuong);
    }

    @Override
    public void updateSoLuongBienThe(Long id, Integer soLuong) {
        repo.updateSoLuongBienThe(id, soLuong);
    }

    @Override
    public Long isExistBienTheHoaDon(Long idHoaDon, Long idBienThe) {
        return repo.isExistBienTheHoaDon(idHoaDon, idBienThe);
    }

    @Override
    public Integer getSoLuongBienTheTrongGioHangById(Long id) {
        return repo.getSoLuongBienTheTrongGioHangById(id);
    }

    @Override
    public Integer getSoLuongBienTheById(Long id) {
        return repo.getSoLuongBienTheById(id);
    }

    @Override
    public Float getGiaBanBienTheById(Long id) {
        return repo.getGiaBanBienTheById(id);
    }

    @Override
    public void deleteBienTheInGioHang(Long idHD, Long idBT) {
        repo.deleteBienTheInGioHang(idHD, idBT);
    }

    @Override
    public void deleteListBienTheInGioHang(Long idHD) {
        repo.deleteListBienTheInGioHang(idHD);
    }

    @Override
    public KhachHangSearch getKhachHangById(Long id) {
        return repo.getKhachHangById(id);
    }

    @Override
    public VoucherSearch getVoucherIsBest(Float tongThanhToan) {
        return repo.getVoucherIsBest(tongThanhToan);
    }

    @Override
    public void giamSoLuongVoucher(Integer soLuong, Long idVoucher) {
        repo.giamSoLuongVoucher(soLuong, idVoucher);
    }

    @Override
    public void updateSoLuongBienTheAfterXoaGioHang(Long id, Integer soLuong) {
        repo.updateSoLuongBienTheAfterXoaGioHang(id, soLuong);
    }

}
