package com.poly.form.giaohang.service.impl;

import com.poly.form.giaohang.entity.HoaDonChuanBiResponse;
import com.poly.form.giaohang.entity.PhieuGiaoHang;
import com.poly.form.giaohang.entity.SanPhamTrongHoaDonResponse;
import com.poly.form.giaohang.repository.HoaDonChuanBiRepository;
import com.poly.form.giaohang.repository.PhieuGiaoHangRepository;
import com.poly.form.giaohang.service.PhieuGiaoHangService;
import java.util.List;

public class PhieuGiaoHangServiceImpl implements PhieuGiaoHangService {

    private PhieuGiaoHangRepository repo = new PhieuGiaoHangRepository();

    private HoaDonChuanBiRepository repoHoaDon = new HoaDonChuanBiRepository();

    @Override
    public List<PhieuGiaoHang> getAllDataGiaoHang(Integer trangThai, String maHD) throws Exception {
        return repo.getAllDataPhieuGiaoHang(trangThai, maHD);
    }

    @Override
    public List<SanPhamTrongHoaDonResponse> getAllSanPhamHoaDon(Integer trangThai, String maHoaDon) throws Exception {
        return repoHoaDon.getAllDataSanPhamHoaDonChuanBi(trangThai, maHoaDon);
    }

    @Override
    public List<HoaDonChuanBiResponse> getAllHoaDonChuanBi(Integer trangThai) throws Exception {
        return repoHoaDon.getAllDataHoaDonChuanBi(trangThai);
    }

    @Override
    public void themPhieu(PhieuGiaoHang phieuGiaoHang) throws Exception {
        System.out.println(phieuGiaoHang.getTenNguoiGiao());
        repo.themPhieu(phieuGiaoHang);
    }

    @Override
    public void chinhSuaPhieu(PhieuGiaoHang phieuGiaoHang) throws Exception {
        repo.chinhSuaPhieu(phieuGiaoHang);
    }

    @Override
    public void updateTrangThaiHoaDon(Integer trangThai, String ma) throws Exception {
        repoHoaDon.updateTrangThaiHoaDon(trangThai, ma);
    }

    @Override
    public void chuyenTrangThai(Integer trangThai, String ma) throws Exception {
        repo.chuyenTrangThai(trangThai, ma);
    }

    @Override
    public Boolean findTonTai(String ma) throws Exception {
        return repoHoaDon.findTonTai(ma);
    }

    @Override
    public void chuyenDaGiao(Integer trangThai, String ma, Long thoiGianNhan) {
        System.out.println("Đã giao service");
        System.out.println(thoiGianNhan);
        repo.chuyenDaGiao(trangThai, ma, thoiGianNhan);
    }

    @Override
    public void chuyenDangGiao(Integer trangThai, String ma, Long thoiGianGiao) {
        System.out.println("Đang giao service");
        System.out.println(thoiGianGiao);
        repo.chuyenDangGiao(trangThai, ma, thoiGianGiao);
    }

    @Override
    public List<HoaDonChuanBiResponse> getAllDataHoaDonChuanBiByMa(Integer trangThai, String maHoaDon) throws Exception {
        return repoHoaDon.getAllDataHoaDonChuanBiByMa(trangThai, maHoaDon);
    }

}
