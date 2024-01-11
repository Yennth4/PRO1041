/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.hoadon.repositoryHung;

import com.poly.database.DBConnect;
import com.poly.form.hoadon.entityHung.HoaDonHung;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class HoaDonRepository {

    // 1: tại quầy, 2: giao hàng
    public List<HoaDonHung> getAllHoaDonTaiQuayTrongNgay(int hinhThuc) {
        List<HoaDonHung> hoaDons = new ArrayList<>();

        String query = "SELECT hd.IDHoaDon, hd.MaHoaDon, hd.GhiChu, hd.TongTienHoaDon, hd.TongTienSauKhuyenMai, "
                + "kh.ho_ten as TenKhachHang, kh.ma, nv.ten as TenNhanVien, hd.SoTienDaGiam, hd.TongTienPhaiTra, hd.ThoiGianSua, hd.THoiGianTao, "
                + "hd.TrangThai, hd.TienMatKhachTra, hd.HinhThucThanhToan, hd.HinhThuc, "
                + "(hd.TongTienPhaiTra - hd.TienMatKhachTra) AS TienKhachChuyenKhoan "
                + "FROM HoaDon hd "
                + "JOIN khach_hang kh ON kh.id = hd.IDKhachHang "
                + "JOIN nhan_vien nv ON hd.IDNhanVien = nv.id "
                + "WHERE hd.HinhThuc = ? AND CONVERT(date, hd.THoiGianTao) = CONVERT(date, GETDATE()) "
                + "ORDER BY hd.THoiGianTao DESC";

        try (Connection con = DBConnect.getConnection(); PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, hinhThuc);
            ResultSet resultSet = pst.executeQuery();

            while (resultSet.next()) {
                HoaDonHung hoaDon = new HoaDonHung();
                hoaDon.setIdHoaDon(resultSet.getInt("IDHoaDon"));
                hoaDon.setMaHoaDon(resultSet.getString("MaHoaDon"));
                hoaDon.setGhiChu(resultSet.getString("GhiChu"));
                hoaDon.setTongTienHoaDon(resultSet.getBigDecimal("TongTienHoaDon"));
                hoaDon.setTongTienSauKhuyenMai(resultSet.getBigDecimal("TongTienSauKhuyenMai"));
                hoaDon.setTenKhachHang(resultSet.getString("TenKhachHang"));
                hoaDon.setTenNhanVien(resultSet.getString("TenNhanVien"));
                hoaDon.setSoTienDaGiam(resultSet.getBigDecimal("SoTienDaGiam"));
                hoaDon.setTongTienPhaiTra(resultSet.getBigDecimal("TongTienPhaiTra"));
                hoaDon.setThoiGianSua(resultSet.getDate("ThoiGianSua"));
                hoaDon.setTHoiGianTao(resultSet.getDate("THoiGianTao"));
                hoaDon.setTrangThai(resultSet.getInt("TrangThai"));
                hoaDon.setTienMatKhachTra(resultSet.getBigDecimal("TienMatKhachTra"));
                hoaDon.setHinhThucThanhToan(resultSet.getInt("HinhThucThanhToan"));
                hoaDon.setHinhThuc(resultSet.getInt("HinhThuc"));
                hoaDon.setMaKhachHang(resultSet.getString("ma"));
                hoaDon.setTienKhachChuyenKhoan(resultSet.getBigDecimal("TienKhachChuyenKhoan"));
                hoaDons.add(hoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ
        }

        return hoaDons;
    }

    public HoaDonHung findHoaDonByMa(int hinhThuc, String ma) {
        String query = "SELECT hd.IDHoaDon, hd.MaHoaDon, hd.GhiChu, hd.TongTienHoaDon, hd.TongTienSauKhuyenMai, "
                + "kh.ho_ten as TenKhachHang, kh.ma, nv.ten as TenNhanVien, hd.SoTienDaGiam, hd.TongTienPhaiTra, hd.ThoiGianSua, hd.THoiGianTao, "
                + "hd.TrangThai, hd.TienMatKhachTra, hd.HinhThucThanhToan, hd.HinhThuc, "
                + "(hd.TongTienPhaiTra - hd.TienMatKhachTra) AS TienKhachChuyenKhoan "
                + "FROM HoaDon hd "
                + "JOIN khach_hang kh ON kh.id = hd.IDKhachHang "
                + "JOIN nhan_vien nv ON hd.IDNhanVien = nv.id "
                + "WHERE hd.HinhThuc = ? AND hd.MaHoaDon = ? "
                + "ORDER BY hd.THoiGianTao DESC";

        try (Connection con = DBConnect.getConnection(); PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, hinhThuc);
            pst.setString(2, ma);
            ResultSet resultSet = pst.executeQuery();

            if (resultSet.next()) {
                HoaDonHung hoaDon = new HoaDonHung();
                hoaDon.setIdHoaDon(resultSet.getInt("IDHoaDon"));
                hoaDon.setMaHoaDon(resultSet.getString("MaHoaDon"));
                hoaDon.setGhiChu(resultSet.getString("GhiChu"));
                hoaDon.setTongTienHoaDon(resultSet.getBigDecimal("TongTienHoaDon"));
                hoaDon.setTongTienSauKhuyenMai(resultSet.getBigDecimal("TongTienSauKhuyenMai"));
                hoaDon.setTenKhachHang(resultSet.getString("TenKhachHang"));
                hoaDon.setTenNhanVien(resultSet.getString("TenNhanVien"));
                hoaDon.setSoTienDaGiam(resultSet.getBigDecimal("SoTienDaGiam"));
                hoaDon.setTongTienPhaiTra(resultSet.getBigDecimal("TongTienPhaiTra"));
                hoaDon.setThoiGianSua(resultSet.getDate("ThoiGianSua"));
                hoaDon.setTHoiGianTao(resultSet.getDate("THoiGianTao"));
                hoaDon.setTrangThai(resultSet.getInt("TrangThai"));
                hoaDon.setTienMatKhachTra(resultSet.getBigDecimal("TienMatKhachTra"));
                hoaDon.setHinhThucThanhToan(resultSet.getInt("HinhThucThanhToan"));
                hoaDon.setHinhThuc(resultSet.getInt("HinhThuc"));
                hoaDon.setMaKhachHang(resultSet.getString("ma"));
                hoaDon.setTienKhachChuyenKhoan(resultSet.getBigDecimal("TienKhachChuyenKhoan"));
                return hoaDon;
            }
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
            // Xử lý ngoại lệ
        }

        return null;
    }

    public List<HoaDonHung> BoLocHoaDonTaiQuay(int hinhThuc, String seachKey, int trangThai, int hinhThuThanToan, String tuNgay, String denNgay, int thoiGian) {
        List<HoaDonHung> hoaDons = new ArrayList<>();
        String query = "SELECT hd.IDHoaDon, hd.MaHoaDon, hd.GhiChu, hd.TongTienHoaDon, hd.TongTienSauKhuyenMai, "
                + "kh.ho_ten as TenKhachHang,kh.ma, nv.ten as TenNhanVien, hd.SoTienDaGiam, hd.TongTienPhaiTra, hd.ThoiGianSua, hd.THoiGianTao, "
                + "hd.TrangThai, hd.TienMatKhachTra, hd.HinhThucThanhToan, hd.HinhThuc, "
                + "(hd.TongTienPhaiTra - hd.TienMatKhachTra) AS TienKhachChuyenKhoan "
                + "FROM HoaDon hd "
                + "JOIN khach_hang kh ON kh.id = hd.IDKhachHang "
                + "JOIN nhan_vien nv ON hd.IDNhanVien = nv.id "
                + "WHERE 1=1";

        if (hinhThuc != -1) {
            query += " AND hd.HinhThuc = " + hinhThuc;
        }

        System.out.println("seachKey repo: " + seachKey);
        if (!seachKey.trim().isEmpty()) {
            query += " AND hd.MaHoaDon like '%" + seachKey + "%'";
        }

        if (trangThai != -1) {
            query += " AND hd.TrangThai = " + trangThai;
        }
        if (hinhThuThanToan != -1) {
            query += " AND hd.HinhThucThanhToan = " + hinhThuThanToan;
        }
        if (!tuNgay.isEmpty() && !denNgay.isEmpty()) {
            query += " AND hd.THoiGianTao BETWEEN '" + tuNgay + "' AND '" + denNgay + "'";
        }

        if (thoiGian == 0) {
            System.out.println("Trước khi cắt: " + query);

            // Find the positions of "SELECT" and "FROM" in the query string
            int selectIndex = query.indexOf("SELECT");
            int fromIndex = query.indexOf("FROM");

            if (selectIndex != -1 && fromIndex != -1) {
                // Extract the substring from "FROM" to the end
                String columns = query.substring(selectIndex + 6, fromIndex); // Lấy phần chứa các cột

                // Reconstruct the query with "SELECT TOP 1" and specified columns
                query = "SELECT TOP 1 " + columns + " " + query.substring(fromIndex) + " ORDER BY hd.IDHoaDon DESC";

                System.out.println("Sau khi sửa: " + query);
            } else {
                System.out.println("Không tìm thấy 'SELECT' hoặc 'FROM' trong chuỗi truy vấn");
            }
        } else if (thoiGian == 1) {
            query += " AND CONVERT(date, hd.THoiGianTao) = CONVERT(date, GETDATE()) ";
        } else {
            query += " ORDER BY hd.THoiGianTao DESC";
        }

        try (Connection con = DBConnect.getConnection(); PreparedStatement pst = con.prepareStatement(query)) {
            ResultSet resultSet = pst.executeQuery();

            while (resultSet.next()) {
                HoaDonHung hoaDon = new HoaDonHung();
                hoaDon.setIdHoaDon(resultSet.getInt("IDHoaDon"));
                hoaDon.setMaHoaDon(resultSet.getString("MaHoaDon"));
                hoaDon.setGhiChu(resultSet.getString("GhiChu"));
                hoaDon.setTongTienHoaDon(resultSet.getBigDecimal("TongTienHoaDon"));
                hoaDon.setTongTienSauKhuyenMai(resultSet.getBigDecimal("TongTienSauKhuyenMai"));
                hoaDon.setTenKhachHang(resultSet.getString("TenKhachHang"));
                hoaDon.setTenNhanVien(resultSet.getString("TenNhanVien"));
                hoaDon.setSoTienDaGiam(resultSet.getBigDecimal("SoTienDaGiam"));
                hoaDon.setTongTienPhaiTra(resultSet.getBigDecimal("TongTienPhaiTra"));
                hoaDon.setThoiGianSua(resultSet.getDate("ThoiGianSua"));
                hoaDon.setTHoiGianTao(resultSet.getDate("THoiGianTao"));
                hoaDon.setTrangThai(resultSet.getInt("TrangThai"));
                hoaDon.setTienMatKhachTra(resultSet.getBigDecimal("TienMatKhachTra"));
                hoaDon.setHinhThucThanhToan(resultSet.getInt("HinhThucThanhToan"));
                hoaDon.setHinhThuc(resultSet.getInt("HinhThuc"));
                hoaDon.setMaKhachHang(resultSet.getString("ma"));
                hoaDon.setTienKhachChuyenKhoan(resultSet.getBigDecimal("TienKhachChuyenKhoan"));
                hoaDons.add(hoaDon);

                if (thoiGian == 0) { // Nếu lựa chọn là mới nhất thì chỉ cần lấy 1 hóa đơn
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ
        }
        return hoaDons;
    }

//    public List<HoaDonHung> BoLocHoaDonGiaoHang(int hinhThuc, String seachKey, int trangThai, int hinhThuThanToan, String tuNgay, String denNgay) {
//        List<HoaDonHung> hoaDons = new ArrayList<>();
//        String query = "SELECT hd.IDHoaDon, hd.MaHoaDon, hd.GhiChu, hd.TongTienHoaDon, hd.TongTienSauKhuyenMai, "
//                + "kh.ho_ten as TenKhachHang, nv.ten as TenNhanVien, hd.SoTienDaGiam, hd.TongTienPhaiTra, hd.ThoiGianSua, hd.THoiGianTao, "
//                + "hd.TrangThai, hd.TienMatKhachTra, hd.HinhThucThanhToan, hd.HinhThuc "
//                + "FROM HoaDon hd "
//                + "JOIN khach_hang kh ON kh.id = hd.IDKhachHang "
//                + "JOIN nhan_vien nv ON hd.IDNhanVien = nv.id "
//                + "WHERE 1=1";
//
//        if (hinhThuc != -1) {
//            query += " AND hd.HinhThuc = " + hinhThuc;
//        }
//
//        System.out.println("seachKey repo: " + seachKey);
//        if (!seachKey.trim().isEmpty()) {
//            query += " AND hd.MaHoaDon like '%" + seachKey + "%'";
//        }
//
//        if (trangThai != -1) {
//            query += " AND hd.TrangThai = " + trangThai;
//        }
//        if (hinhThuThanToan != -1) {
//            query += " AND hd.HinhThucThanhToan = " + hinhThuThanToan;
//        }
//        if (!tuNgay.isEmpty() && !denNgay.isEmpty()) {
//            query += " AND hd.THoiGianTao BETWEEN '" + tuNgay + "' AND '" + denNgay + "'";
//        }
//        query += " ORDER BY hd.THoiGianTao DESC";
//
//        try (Connection con = DBConnect.getConnection(); PreparedStatement pst = con.prepareStatement(query)) {
//            ResultSet resultSet = pst.executeQuery();
//
//            while (resultSet.next()) {
//                HoaDonHung hoaDon = new HoaDonHung();
//                hoaDon.setIdHoaDon(resultSet.getInt("IDHoaDon"));
//                hoaDon.setMaHoaDon(resultSet.getString("MaHoaDon"));
//                hoaDon.setGhiChu(resultSet.getString("GhiChu"));
//                hoaDon.setTongTienHoaDon(resultSet.getBigDecimal("TongTienHoaDon"));
//                hoaDon.setTongTienSauKhuyenMai(resultSet.getBigDecimal("TongTienSauKhuyenMai"));
//                hoaDon.setTenKhachHang(resultSet.getString("TenKhachHang"));
//                hoaDon.setTenNhanVien(resultSet.getString("TenNhanVien"));
//                hoaDon.setSoTienDaGiam(resultSet.getBigDecimal("SoTienDaGiam"));
//                hoaDon.setTongTienPhaiTra(resultSet.getBigDecimal("TongTienPhaiTra"));
//                hoaDon.setThoiGianSua(resultSet.getDate("ThoiGianSua"));
//                hoaDon.setTHoiGianTao(resultSet.getDate("THoiGianTao"));
//                hoaDon.setTrangThai(resultSet.getInt("TrangThai"));
//                hoaDon.setTienMatKhachTra(resultSet.getBigDecimal("TienMatKhachTra"));
//                hoaDon.setHinhThucThanhToan(resultSet.getInt("HinhThucThanhToan"));
//                hoaDon.setHinhThuc(resultSet.getInt("HinhThuc"));
//
//                hoaDons.add(hoaDon);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            // Xử lý ngoại lệ
//        }
//        return hoaDons;
//    }
    public List<HoaDonHung> SearchHoaDonTaiQuayById(int hinhThuc, String searchValue) {
        List<HoaDonHung> hoaDons = new ArrayList<>();

        String query = "SELECT hd.IDHoaDon, hd.MaHoaDon, hd.GhiChu, hd.TongTienHoaDon, hd.TongTienSauKhuyenMai, "
                + "kh.ho_ten as TenKhachHang, kh.ma, nv.ten as TenNhanVien, hd.SoTienDaGiam, hd.TongTienPhaiTra, hd.ThoiGianSua, hd.THoiGianTao, "
                + "hd.TrangThai, hd.TienMatKhachTra, hd.HinhThucThanhToan, hd.HinhThuc, "
                + "(hd.TongTienPhaiTra - hd.TienMatKhachTra) AS TienKhachChuyenKhoan "
                + "FROM HoaDon hd "
                + "JOIN khach_hang kh ON kh.id = hd.IDKhachHang "
                + "JOIN nhan_vien nv ON hd.IDNhanVien = nv.id "
                + "WHERE hd.HinhThuc = ? AND hd.MaHoaDon like ? "
                + "ORDER BY hd.THoiGianTao DESC";

        try (Connection con = DBConnect.getConnection(); PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, hinhThuc);
            pst.setString(2, "%" + searchValue + "%");
            ResultSet resultSet = pst.executeQuery();

            while (resultSet.next()) {
                HoaDonHung hoaDon = new HoaDonHung();
                hoaDon.setIdHoaDon(resultSet.getInt("IDHoaDon"));
                hoaDon.setMaHoaDon(resultSet.getString("MaHoaDon"));
                hoaDon.setGhiChu(resultSet.getString("GhiChu"));
                hoaDon.setTongTienHoaDon(resultSet.getBigDecimal("TongTienHoaDon"));
                hoaDon.setTongTienSauKhuyenMai(resultSet.getBigDecimal("TongTienSauKhuyenMai"));
                hoaDon.setTenKhachHang(resultSet.getString("TenKhachHang"));
                hoaDon.setTenNhanVien(resultSet.getString("TenNhanVien"));
                hoaDon.setSoTienDaGiam(resultSet.getBigDecimal("SoTienDaGiam"));
                hoaDon.setTongTienPhaiTra(resultSet.getBigDecimal("TongTienPhaiTra"));
                hoaDon.setThoiGianSua(resultSet.getDate("ThoiGianSua"));
                hoaDon.setTHoiGianTao(resultSet.getDate("THoiGianTao"));
                hoaDon.setTrangThai(resultSet.getInt("TrangThai"));
                hoaDon.setTienMatKhachTra(resultSet.getBigDecimal("TienMatKhachTra"));
                hoaDon.setHinhThucThanhToan(resultSet.getInt("HinhThucThanhToan"));
                hoaDon.setHinhThuc(resultSet.getInt("HinhThuc"));
                hoaDon.setMaKhachHang(resultSet.getString("ma"));
                hoaDon.setTienKhachChuyenKhoan(resultSet.getBigDecimal("TienKhachChuyenKhoan"));
                hoaDons.add(hoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ
        }

        return hoaDons;
    }

    public boolean updateDonHangTaiQuayByMa(int hinhThuc, String ma, int trangThaiDonHang, int hinhThucThanhToan, String ghiChu) {
        int check = 0;
        String query = "UPDATE HoaDon SET TrangThai = ?, HinhThucThanhToan =?, GhiChu =?  WHERE HinhThuc = ? and MaHoaDon = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, trangThaiDonHang);
            ps.setInt(2, hinhThucThanhToan);
            ps.setString(3, ghiChu);
            ps.setInt(4, hinhThuc);
            ps.setString(5, ma);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public boolean updateTrangThaiHoaDon(int hinhThuc, String ma, int trangThaiDonHang, String lyDoHuy) {
        int check = 0;
        String query = "UPDATE HoaDon SET TrangThai = ?, GhiChu =?  WHERE HinhThuc = ? and MaHoaDon = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, trangThaiDonHang);
            ps.setString(2, lyDoHuy);
            ps.setInt(3, hinhThuc);
            ps.setString(4, ma);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public static void main(String[] args) {
//        HoaDonRepository donRepository = new HoaDonRepository();
//        boolean resutl = donRepository.updateDonHangTaiQuayByMa(1, "HD0001", 2, "Mới cập nhật trạng thái");

    }
}
