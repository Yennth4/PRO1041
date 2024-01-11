package com.poly.form.khuyenmai.khuyenmai_sanpham.repository;

import com.poly.database.DBConnect;
import com.poly.form.khuyenmai.khuyenmai_sanpham.entity.KhuyenMaiTheoSanPham;
import com.poly.form.khuyenmai.khuyenmai_sanpham.entity.SanPhamChiTietForKhuyenMaiResponse;
import com.poly.form.khuyenmai.khuyenmai_sanpham.entity.SanPhamForKhuyenMaiResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class KMSanPhamRepository {

    private static final int PAGE_SIZE = 10;

    public List<SanPhamChiTietForKhuyenMaiResponse> getAllDataSanPhamChiTiet() throws Exception {
        String query = "SELECT spct.IDSanPhamChiTiet, spct.Ma, sp.TenSanPham, SoLuong, m.TenMau, GiaBan, GiaNiemYet"
                + " FROM SanPhamChiTiet spct"
                + " LEFT JOIN Mau m ON m.IDMau = spct.IDMau"
                + " LEFT JOIN SANPHAM sp ON sp.IDSanPham = spct.IDSanPham"
                + " WHERE spct.TrangThai = 1"
                + " ORDER BY spct.ThoiGianSua DESC";

        List<SanPhamChiTietForKhuyenMaiResponse> listSanPham = new ArrayList<>();

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps
                = conn.prepareStatement(query)) {
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                Long id = rs.getLong("IDSanPhamChiTiet");
                String ten = rs.getString("TenSanPham");
                String ma = rs.getString("Ma");
                Long soLuong = rs.getLong("SoLuong");
                String tenMau = rs.getString("TenMau");
                Double giaBan = rs.getDouble("GiaBan");
                Double giaNiemYet = rs.getDouble("GiaNiemYet");
                listSanPham.add(new SanPhamChiTietForKhuyenMaiResponse(id, ten, ma, giaNiemYet, giaBan, soLuong, tenMau));
            }
            return listSanPham;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listSanPham;
    }

    public List<SanPhamChiTietForKhuyenMaiResponse> findSanPhamChiTietByMaSanPham(Long idSanPham) {
        String query = "SELECT spct.IDSanPhamChiTiet, spct.Ma, sp.TenSanPham, SoLuong, m.TenMau, GiaBan, GiaNiemYet"
                + " FROM SanPhamChiTiet spct"
                + " LEFT JOIN Mau m ON m.IDMau = spct.IDMau"
                + " LEFT JOIN SANPHAM sp ON sp.IDSanPham = spct.IDSanPham"
                + " WHERE spct.TrangThai = 1 AND sp.IDSanPham = ?"
                + " ORDER BY spct.ThoiGianSua DESC";

        List<SanPhamChiTietForKhuyenMaiResponse> listSanPham = new ArrayList<>();

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps
                = conn.prepareStatement(query)) {
            ps.setLong(1, idSanPham);
            ps.execute();
            ResultSet rs = ps.getResultSet();

            while (rs.next()) {
                Long id = rs.getLong("IDSanPhamChiTiet");
                String ten = rs.getString("TenSanPham");
                String ma = rs.getString("Ma");
                Long soLuong = rs.getLong("SoLuong");
                String tenMau = rs.getString("TenMau");
                Double giaBan = rs.getDouble("GiaBan");
                Double giaNiemYet = rs.getDouble("GiaNiemYet");
                listSanPham.add(new SanPhamChiTietForKhuyenMaiResponse(id, ten, ma, giaNiemYet, giaBan, soLuong, tenMau));
            }
            return listSanPham;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listSanPham;
    }

    public List<SanPhamForKhuyenMaiResponse> getAllDataSanPham() throws Exception {
        String query = "SELECT sp.IDSanPham, sp.MaSanPham, sp.TenSanPham"
                + " FROM SanPham sp";

        List<SanPhamForKhuyenMaiResponse> listSanPham = new ArrayList<>();

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps
                = conn.prepareStatement(query)) {
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                Long id = rs.getLong("IDSanPham");
                String ten = rs.getString("TenSanPham");
                String ma = rs.getString("MaSanPham");
                listSanPham.add(new SanPhamForKhuyenMaiResponse(id, ma, ten));
            }
            return listSanPham;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listSanPham;
    }
}
