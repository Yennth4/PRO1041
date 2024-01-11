/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.hoadon.repositoryHung;

import com.poly.database.DBConnect;
import com.poly.form.hoadon.entityHung.HoaDonChiTietHung;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class HoaDonChiTietRepository {

    public List<HoaDonChiTietHung> getAllSanPhamByIdHoaDon(int idHoaDon) {
        List<HoaDonChiTietHung> list = new ArrayList<>();
        String sql = "SELECT  s.MaSanPham, s.TenSanPham, m.TenMau, hdct.GiaTien, hdct.SoLuong, hdct.GiaTien * hdct.SoLuong as TongTienTheoSanPham\n"
                + "FROM HoaDon_SanPhamChiTiet hdct\n"
                + "JOIN SanPhamChiTiet spct ON hdct.IDSanPhamChiTiet = spct.IDSanPhamChiTiet\n"
                + "JOIN SanPham s ON spct.IDSanPham = s.IDSanPham\n"
                + "JOIN Mau m ON spct.IDMau = m.IDMau\n"
                + "WHERE hdct.IDHoaDon = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idHoaDon); // Gán giá trị cho tham số trong truy vấn

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTietHung hdcth = new HoaDonChiTietHung(
                        rs.getString("MaSanPham"),
                        rs.getString("TenSanPham"),
                        rs.getString("TenMau"),
                        rs.getBigDecimal("GiaTien"),
                        rs.getInt("SoLuong"),
                        rs.getBigDecimal("TongTienTheoSanPham")
                );
                list.add(hdcth);
            }
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu có
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        HoaDonChiTietRepository chiTietRepository = new HoaDonChiTietRepository();
        List<HoaDonChiTietHung> list = chiTietRepository.getAllSanPhamByIdHoaDon(1);
        for (HoaDonChiTietHung hd : list) {
            System.out.println(hd.toString());
        }
    }
}
