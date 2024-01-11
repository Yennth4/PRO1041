/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.khachhang.service;

import com.poly.form.khachhang.entity.HoaDonKhachHang;
import com.poly.form.khachhang.entity.KhachHangHung;
import com.poly.form.khachhang.entity.LichSuGiaoDich;
import com.poly.form.khachhang.entity.SanPhamKhachHang;
import com.poly.form.khachhang.repository.KhachHangRepository;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class KhachHangService {
    
    KhachHangRepository khachHangRepository = new KhachHangRepository();
    
    public List<KhachHangHung> getAllKhachHang() {
        return khachHangRepository.getAllKhachHang();
    }
    
    public KhachHangHung getKhachHangById(String ma) {
        return khachHangRepository.getKhachHangById(ma);
    }
    
    public KhachHangHung getKhachHangById2(Long id) {
        return khachHangRepository.getKhachHangById2(id);
    }
    
    public boolean createKhachHang(KhachHangHung kh) {
        return khachHangRepository.createKhachHang(kh);
    }
    
    public boolean updateKhachHang(String ma, KhachHangHung kh) {
        return khachHangRepository.updateKhachHang(ma, kh);
    }
    
    public boolean deleteKhachHangById(String ma) {
        return khachHangRepository.deleteKhachHangById(ma);
    }
    
    public List<KhachHangHung> getAllKhachHangDaXoa() {
        return khachHangRepository.getAllKhachHangDaXoa();
    }
    
    public List<KhachHangHung> searchByNameOrPhoneOrCode(String name) {
        return khachHangRepository.searchByNameOrPhoneOrCode(name);
    }
    
    public List<KhachHangHung> getAllKhachHangByGioiTinh(int gioiTinh) {
        return khachHangRepository.getAllKhachHangByGioiTinh(gioiTinh);
    }
    
    public List<KhachHangHung> searchByNameAndGioiTinh(String name, int gioiTinh) {
        return khachHangRepository.getAllKhachHangByGioiTinh(gioiTinh);
    }
    
    public List<KhachHangHung> getAllKhachHangByCapBac(int capbac) {
        return khachHangRepository.getAllKhachHangByCapBac(capbac);
    }
    
    public List<KhachHangHung> searchByNameKhachHangDaXoa(String name) {
        return khachHangRepository.searchByNameKhachHangDaXoa(name);
    }
    
    public boolean updateIsDeleteKhachHang(String ma) {
        return khachHangRepository.updateIsDeleteKhachHang(ma);
    }
    
    public boolean updateAllIsDeleteKhachHang() {
        return khachHangRepository.updateAllIsDeleteKhachHang();
    }
    
    public List<HoaDonKhachHang> getHoaDonByIdKhachHang(int idKhachHang) {
        return khachHangRepository.getHoaDonByIdKhachHang(idKhachHang);
    }
    
    public int getTongSoTienDaTra(int idKhachHang) {
        return khachHangRepository.getTongSoTienDaTra(idKhachHang);
    }
    
    public void updateCapBacTheoTien(int idKhachHang) {
        khachHangRepository.updateCapBacupdateCapBacTheoTien(idKhachHang);
    }
    
    public List<SanPhamKhachHang> getSanPhamKhachHangTuHoaDon(String maKhachhang) {
        return khachHangRepository.getSanPhamKhachHangTuHoaDon(maKhachhang);
    }
    
    public List<HoaDonKhachHang> getHoaDonByIdKhachHangAndMaHoaDon(int idKhachHang, String maHd) {
        return khachHangRepository.getHoaDonByIdKhachHangAndMaHoaDon(idKhachHang, maHd);
    }
    
    public List<KhachHangHung> boloc(String keyword, int gioiTinh, int capBac, int is_delete) {
        return khachHangRepository.boloc(keyword, gioiTinh, capBac, is_delete);
    }
    
    public boolean CreateLihSuThanhToan(String maHoaDon, Long idKhachHang, Date thoiGianTao, Float tongTien, int capBac) {
        return khachHangRepository.CreateLihSuThanhToan(maHoaDon, idKhachHang, thoiGianTao, tongTien, capBac);
    }
    
    public List<LichSuGiaoDich> getAllLichSuGiaoDich(int id) {
        return khachHangRepository.getAllLichSuGiaoDich(id);
    }
}
