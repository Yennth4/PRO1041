/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.poly.database.DBConnect;
import com.poly.entity.NhanVien;

/**
 *
 * @author Admin
 */
public class NhanVienRepo {

    public List<NhanVien> getAllNhanVien() {
        String sql = "select * from nhanvien";
        List<NhanVien> list = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setHoTen(rs.getString("MaNV"));
                nv.setMatKhau(rs.getString("MatKhau"));
                nv.setHoTen(rs.getString("HoTen"));
                nv.setVaiTro(rs.getString("VaiTro"));
                list.add(nv);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }
}
