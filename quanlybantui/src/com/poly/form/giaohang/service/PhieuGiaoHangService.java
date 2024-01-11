package com.poly.form.giaohang.service;

import com.poly.form.giaohang.entity.HoaDonChuanBiResponse;
import com.poly.form.giaohang.entity.PhieuGiaoHang;
import com.poly.form.giaohang.entity.SanPhamTrongHoaDonResponse;
import java.util.List;

public interface PhieuGiaoHangService {

    List<PhieuGiaoHang> getAllDataGiaoHang(Integer trangThai, String maHD) throws Exception;

    List<SanPhamTrongHoaDonResponse> getAllSanPhamHoaDon(Integer trangThai, String maHoaDon) throws Exception;

    List<HoaDonChuanBiResponse> getAllHoaDonChuanBi(Integer trangThai) throws Exception;
    
    List<HoaDonChuanBiResponse> getAllDataHoaDonChuanBiByMa(Integer trangThai, String maHoaDon) throws Exception;

    void themPhieu(PhieuGiaoHang phieuGiaoHang) throws Exception;

    void chinhSuaPhieu(PhieuGiaoHang phieuGiaoHang) throws Exception;

    void updateTrangThaiHoaDon(Integer trangThai, String ma) throws Exception;
    
    void chuyenDaGiao(Integer trangThai, String ma, Long thoiGianNhan);
    
    void chuyenDangGiao(Integer trangThai, String ma, Long thoiGianGiao);

    void chuyenTrangThai(Integer trangThai, String ma) throws Exception;
    
    Boolean findTonTai(String ma) throws Exception;

}
