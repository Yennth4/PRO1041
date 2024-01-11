package com.poly.form.giaohang.repository;

import com.poly.database.DBConnect;
import com.poly.form.giaohang.entity.HoaDonChuanBiResponse;
import com.poly.form.giaohang.entity.SanPhamTrongHoaDonResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HoaDonChuanBiRepository {

    public List<HoaDonChuanBiResponse> getAllDataHoaDonChuanBi(Integer trangThai) throws Exception {
        String query = "SELECT DISTINCT  hd.IDHoaDon, hd.GhiChu ,nv.ten as "
                + " 'tenNhanVien', nv.so_dien_thoai as 'sdtNhanVien', kh.ho_ten "
                + "as 'tenKhachHang', kh.sdt as 'sdtKhachHang', hd.TongTienHoaDon,"
                + " hd.MaHoaDon, kh.dia_chi FROM HoaDon hd "
                + "LEFT JOIN khach_hang kh ON kh.id = hd.IDKhachHang "
                + "LEFT JOIN nhan_vien nv ON nv.id = hd.IDNhanVien "
                + "WHERE hd.TrangThai = ? "
                + "AND NOT EXISTS (SELECT 1 FROM phieu_giao_hang pgh WHERE pgh.ma = hd.MaHoaDon) ORDER BY hd.IDHoaDon DESC";

        List<HoaDonChuanBiResponse> list = new ArrayList<>();

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps
                = conn.prepareStatement(query)) {
            ps.setInt(1, trangThai);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                Long idHoaDon = rs.getLong("IDHoaDon");
                String ghiChu = rs.getString("GhiChu");
                String tenNhanVien = rs.getString("tenNhanVien");
                String soDTNhanVien = rs.getString("sdtNhanVien");
                String tenKhachHang = rs.getString("tenKhachHang");
                String soDTKhachHang = rs.getString("sdtKhachHang");
                Float tongTien = rs.getFloat("TongTienHoaDon");
                String maHoaDon = rs.getString("MaHoaDon");
                String diaChi = rs.getString("dia_chi");
                list.add(new HoaDonChuanBiResponse(idHoaDon, maHoaDon, ghiChu,
                        tongTien, tenNhanVien, tenKhachHang,
                        soDTNhanVien, soDTKhachHang, diaChi));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<HoaDonChuanBiResponse> getAllDataHoaDonChuanBiByMa(Integer trangThai, String ma) throws Exception {

        String query = "SELECT "
                + "hd.IDHoaDon, "
                + "hd.GhiChu, "
                + "nv.ten AS 'tenNhanVien', "
                + "nv.so_dien_thoai AS 'sdtNhanVien', "
                + "kh.ho_ten AS 'tenKhachHang', "
                + "kh.sdt AS 'sdtKhachHang', "
                + "hd.TongTienHoaDon, "
                + "hd.MaHoaDon, kh.dia_chi "
                + "FROM HoaDon hd "
                + "LEFT JOIN khach_hang kh ON kh.id = hd.IDKhachHang "
                + "LEFT JOIN nhan_vien nv ON nv.id = hd.IDNhanVien "
                + "WHERE hd.TrangThai = ? "
                + "AND (? IS NULL OR hd.MaHoaDon LIKE ?) "
                + "AND NOT EXISTS (SELECT 1 FROM phieu_giao_hang pgh WHERE pgh.ma = hd.MaHoaDon) ORDER BY hd.IDHoaDon DESC";

        List<HoaDonChuanBiResponse> list = new ArrayList<>();

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps
                = conn.prepareStatement(query)) {
            ps.setInt(1, trangThai);
            ps.setString(2, ma);
            ps.setString(3, "%" + ma + "%");
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                Long idHoaDon = rs.getLong("IDHoaDon");
                String ghiChu = rs.getString("GhiChu");
                String tenNhanVien = rs.getString("tenNhanVien");
                String soDTNhanVien = rs.getString("sdtNhanVien");
                String tenKhachHang = rs.getString("tenKhachHang");
                String soDTKhachHang = rs.getString("sdtKhachHang");
                Float tongTien = rs.getFloat("TongTienHoaDon");
                String maHoaDon = rs.getString("MaHoaDon");
                String diaChi = rs.getString("dia_chi");
                list.add(new HoaDonChuanBiResponse(idHoaDon, maHoaDon, ghiChu,
                        tongTien, tenNhanVien, tenKhachHang,
                        soDTNhanVien, soDTKhachHang, diaChi));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<SanPhamTrongHoaDonResponse> getAllDataSanPhamHoaDonChuanBi(Integer trangThai, String maHoaDon) throws Exception {
        String query = "SELECT DISTINCT  hd.IDHoaDon, hd.MaHoaDon, sp.TenSanPham, hdspct.SoLuong, spct.GiaBan FROM HoaDon hd "
                + "	LEFT JOIN HoaDon_SanPhamChiTiet hdspct ON hdspct.IDHoaDon = hd.IDHoaDon "
                + "	LEFT JOIN SanPhamChiTiet spct ON spct.IDSanPham = hdspct.IDSanPhamChiTiet "
                + "	LEFT JOIN SanPham sp ON sp.IDSanPham = spct.IDSanPham "
                + "	WHERE hd.TrangThai = ? AND hd.MaHoaDon = ? ORDER BY hd.IDHoaDon DESC ";
        List<SanPhamTrongHoaDonResponse> list = new ArrayList<>();

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps
                = conn.prepareStatement(query)) {
            ps.setInt(1, trangThai);
            ps.setString(2, maHoaDon);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                String ten = rs.getString("TenSanPham");
                Long soLuong = rs.getLong("SoLuong");
                Float giaBan = rs.getFloat("GiaBan");
                list.add(new SanPhamTrongHoaDonResponse(ten, giaBan, soLuong));
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateTrangThaiHoaDon(Integer trangThai, String ma) {
        String query = "UPDATE HoaDon SET TrangThai = ? WHERE MaHoaDon = ? ";

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps
                = conn.prepareStatement(query)) {
            ps.setInt(1, trangThai);
            ps.setString(2, ma);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Boolean findTonTai(String ma) {
        String query = "SELECT ma FROM phieu_giao_hang WHERE ma = ? ";

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps
                = conn.prepareStatement(query)) {
            ps.setString(1, ma);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

}
