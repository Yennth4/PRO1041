/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.hoadon.serviceHung;

import com.poly.form.hoadon.entityHung.HoaDonHung;
import com.poly.form.hoadon.repositoryHung.HoaDonRepository;
import java.util.List;

/**
 *
 * @author Admin
 */
public class HoaDonService {

    HoaDonRepository hoaDonRepository = new HoaDonRepository();

    public List<HoaDonHung> getAllHoaDonTaiQuayTrongNgay(int hinhThuc) {
        
        return hoaDonRepository.getAllHoaDonTaiQuayTrongNgay(hinhThuc);
        
    }

    public HoaDonHung findHoaDonByMa(int hinhThuc, String ma) {
        
        return hoaDonRepository.findHoaDonByMa(hinhThuc, ma);
        
    }

    public List<HoaDonHung> BoLocHoaDonTaiQuay(int hinhThuc, String seachKey,
            int trangThai, int hinhThuThanToan, String tuNgay, String denNgay,
            int thoiGian) {
        
        return hoaDonRepository.BoLocHoaDonTaiQuay(hinhThuc, seachKey,
                trangThai, hinhThuThanToan, tuNgay, denNgay, thoiGian);
        
    }

    public List<HoaDonHung> SearchHoaDonTaiQuayById(int hinhThuc,
            String searchValue) {

        return hoaDonRepository.SearchHoaDonTaiQuayById(hinhThuc, searchValue);

    }

    public boolean updateDonHangTaiQuayByMa(int hinhThuc, String ma,
            int trangThaiDonHang, int hinhThucThanhToan, String ghiChu) {

        return hoaDonRepository.updateDonHangTaiQuayByMa(hinhThuc, ma,
                trangThaiDonHang, hinhThucThanhToan, ghiChu);
        
    }
    
    public boolean updateTrangThaiHoaDon(int hinhThuc, String ma, 
            int trangThaiDonHang, String lyDoHuy) {
        
        return hoaDonRepository.updateTrangThaiHoaDon(hinhThuc, ma, 
                trangThaiDonHang, lyDoHuy);
    }

}
