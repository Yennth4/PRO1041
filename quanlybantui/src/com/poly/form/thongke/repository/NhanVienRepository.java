/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.thongke.repository;

import com.poly.database.DBConnect;
import com.poly.form.thongke.entity.NhanVienEntity;
import com.poly.form.thongke.entity.SanPhamEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class NhanVienRepository {
    
    private Connection conn;
    
    public NhanVienRepository() {
        conn = DBConnect.getConnection();
    }
    
    public List<NhanVienEntity> findAll() {
        List<NhanVienEntity> list = new ArrayList<>();
        try {
            String query = "  declare @tongSoLuong Int   "
                    + " set @tongSoLuong = (select  sum(ct.SoLuong) as tongSoLuong    "
                    + " from HoaDon hd   "
                    + " join hoaDon_sanPhamchitiet ct on ct.IDHoaDon = hd.IDHoaDon    "
                    + "  WHERE ( CONVERT(bigint, hd.THoiGianTao) >CONVERT(bigint,  DATEADD(DAY, -30, GETDATE())) ) AND   "
                    + " (CONVERT(bigint, hd.THoiGianTao)<(CONVERT(bigint,  GETDATE())+1)) )    "
                    + " "
                    + " select hd.IDNhanVien as idNhanVien, "
                    + " nv.ten, "
                    + " nv.email, "
                    + " nv.so_dien_thoai, "
                    + " nv.ma_dinh_danh, "
                    + " nv.username, "
                    + " nv.dia_chi, "
                    + " nv.gioi_tinh, "
                    + " pq.role, "
                    + " nv.trang_thai, "
                    + " sum(ct.SoLuong)as soLuongBanDuoc, "
                    + " @tongSoLuong as tongSoLuongBanDuoc , "
                    + " cast (ROUND(CASE   "
                    + "              WHEN @tongSoLuong = 0 THEN 0    "
                    + "              ELSE (SUM(ct.SoLuong) * 100.0) / @tongSoLuong  "
                    + "              END, 1) AS DECIMAL(15,1)) as phanTram   "
                    + " from HoaDon hd   "
                    + " left join hoaDon_sanPhamchitiet ct on ct.IDHoaDon = hd.IDHoaDon  "
                    + " left join nhan_vien nv on nv.id = hd.IDNhanVien "
                    + " left join phan_quyen pq on pq.nhan_vien_id = nv.id  "
                    + " WHERE ( CONVERT(bigint, hd.THoiGianTao) >CONVERT(bigint,  DATEADD(DAY, -30, GETDATE())) ) AND    "
                    + " (CONVERT(bigint, hd.THoiGianTao)<(CONVERT(bigint,  GETDATE())+1)) "
                    + " group by hd.IDNhanVien,nv.ten, "
                    + " nv.email, "
                    + " nv.so_dien_thoai, "
                    + " nv.ma_dinh_danh, "
                    + " nv.username, "
                    + " nv.dia_chi, "
                    + " nv.gioi_tinh, "
                    + "  pq.role, "
                    + "  nv.trang_thai ";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVienEntity enity = new NhanVienEntity(rs.getLong("idNhanVien"),
                        rs.getString("ten"),
                        rs.getString("email"),
                        rs.getString("so_dien_thoai"),
                        rs.getString("ma_dinh_danh"),
                        rs.getString("username"),
                        rs.getString("dia_chi"),
                        rs.getString("gioi_tinh"),
                        rs.getString("role"),
                        rs.getInt("soLuongBanDuoc"),
                        rs.getInt("tongSoLuongBanDuoc"),
                        rs.getFloat("phanTram"),
                        rs.getString("trang_thai"));
                list.add(enity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public static void main(String[] args) {
        NhanVienRepository s = new NhanVienRepository();
        s.findAll();
    }
}
