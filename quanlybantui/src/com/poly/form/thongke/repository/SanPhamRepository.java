/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.thongke.repository;

import com.poly.database.DBConnect;
import com.poly.form.doihang.entity.DoiHangResponse.HoaDon;
import com.poly.form.doihang.entity.DoiHangResponse.ProductDetailResponse;
import com.poly.form.doihang.service.DoiHangService.impl.ProductDetailService;
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
public class SanPhamRepository {

    private Connection conn;

    public SanPhamRepository() {
        conn = DBConnect.getConnection();
    }

    public List<SanPhamEntity> findAll() {
        List<SanPhamEntity> list = new ArrayList<>();
        try {
            String query = "  declare @tongSoLuong Int   "
                    + "                    set @tongSoLuong = (select  sum(ct.SoLuong) as tongSoLuong    "
                    + "                    from HoaDon hd   "
                    + "                    join hoaDon_sanPhamchitiet ct on ct.IDHoaDon = hd.IDHoaDon    "
                    + "                      WHERE ( CONVERT(bigint, hd.THoiGianTao) >CONVERT(bigint,  DATEADD(DAY, -30, GETDATE())) ) AND   "
                    + "                    (CONVERT(bigint, hd.THoiGianTao)<(CONVERT(bigint,  GETDATE())+1)) )   "
                    + "                      "
                    + "                    select ct.IDSanPhamChiTiet as idSanPhamChiTiet,   "
                    + "                    @tongSoLuong as tongSoLuong,   "
                    + "                     mau.TenMau,   "
                    + "                    sum(ct.SoLuong) as soLuong,    "
                    + "                    spct.Ma,sp.TenSanPham,   "
                    + "                     tl.tenTheLoai,   "
                    + "                     cast (ROUND(CASE   "
                    + "                            WHEN @tongSoLuong = 0 THEN 0    "
                    + "                            ELSE (SUM(ct.SoLuong) * 100.0) / @tongSoLuong  "
                    + "                        END, 1) AS DECIMAL(15,1)) as phanTram   "
                    + "                    from HoaDon hd   "
                    + "                    join hoaDon_sanPhamchitiet ct on ct.IDHoaDon = hd.IDHoaDon   "
                    + "                    join SanPhamChiTiet spct on spct.IDSanPhamChiTiet= ct.IDSanPhamChiTiet  "
                    + "                    join SanPham sp on sp.IDSanPham = spct.IDSanPham   "
                    + "                    join Mau on mau.IDMau = spct.IDMau   "
                    + "                     join TheLoai tl on tl.IDTheLoai= sp.IDTheLoai   "
                    + "                     WHERE ( CONVERT(bigint, hd.THoiGianTao) >CONVERT(bigint,  DATEADD(DAY, -30, GETDATE())) ) AND    "
                    + "                    (CONVERT(bigint, hd.THoiGianTao)<(CONVERT(bigint,  GETDATE())+1))  "
                    + "                    group by ct.IDSanPhamChiTiet,spct.Ma,sp.TenSanPham,mau.TenMau, tl.tenTheLoai  ";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery(); 
            while (rs.next()) {
                SanPhamEntity enity = new SanPhamEntity(rs.getLong("idSanPhamChiTiet"),
                        rs.getInt("tongSoLuong"),
                        rs.getString("TenMau"),
                        rs.getString("TenSanPham"),
                        rs.getInt("soLuong"),
                        rs.getString("Ma"),
                        rs.getFloat("phanTram"),
                        rs.getString("tenTheLoai"));
                list.add(enity);  
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        SanPhamRepository s = new SanPhamRepository();
        s.findAll();
    }
}
