package com.poly.form.giaohang.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.poly.database.DBConnect;
import com.poly.form.giaohang.entity.PhieuGiaoHang;
import java.sql.Types;

public class PhieuGiaoHangRepository {

    public List<PhieuGiaoHang> getAllDataPhieuGiaoHang(Integer trangThai, String maHD) throws Exception {
        String query = "SELECT pgh.ma as 'maHoaDon',"
                + " pgh.nguoi_nhan, pgh.sdt_nguoi_nhan, pgh.nguoi_ship, pgh.sdt_nguoi_ship,"
                + " pgh.thoi_gian_giao,  pgh.thoi_gian_nhan, pgh.trang_thai, pgh.thoi_gian_uoc_tinh,"
                + " pgh.dia_chi, pgh.mo_ta  "
                + "FROM phieu_giao_hang pgh WHERE (pgh.trang_thai = ?) "
                + " AND (? IS NULL OR pgh.ma LIKE ?) "
                + " ORDER BY pgh.thoi_gian_sua DESC ";
        List<PhieuGiaoHang> listPhieuGiao = new ArrayList<>();

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps
                = conn.prepareStatement(query)) {
            ps.setInt(1, trangThai);
            ps.setString(2, maHD);
            ps.setString(3, "%" + maHD + "%");
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                String ma = rs.getString("maHoaDon");
                String nguoiNhan = rs.getString("nguoi_nhan");
                String sdtNguoiNhan = rs.getString("sdt_nguoi_nhan");
                String nguoiGiao = rs.getString("nguoi_ship");
                String sdtNguoiGiao = rs.getString("sdt_nguoi_ship");
                Long thoiGianGiao = rs.getLong("thoi_gian_giao");
                Long thoiGianNhan = rs.getLong("thoi_gian_nhan");
                Long thoiGianUocTinh = rs.getLong("thoi_gian_uoc_tinh");
                String diaChi = rs.getString("dia_chi");
                String moTa = rs.getString("mo_ta");
                Integer trangThaiPhieu = rs.getInt("trang_thai");
                listPhieuGiao.add(new PhieuGiaoHang(ma, nguoiNhan,
                        sdtNguoiNhan, nguoiGiao, sdtNguoiGiao,
                        diaChi, thoiGianGiao, thoiGianNhan, moTa, trangThaiPhieu, thoiGianUocTinh));
            }
            return listPhieuGiao;
        } catch (Exception e) {
            e.printStackTrace();
            return listPhieuGiao;
        }
    }

    public void chinhSuaPhieu(PhieuGiaoHang phieuGiaoHang) throws Exception {

        String query = "UPDATE phieu_giao_hang SET nguoi_nhan = ?, sdt_nguoi_nhan = ?, dia_chi = ?, nguoi_ship = ?, sdt_nguoi_ship = ?, mo_ta = ?, thoi_gian_uoc_tinh = ? WHERE ma = ?";

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps
                = conn.prepareStatement(query)) {
            ps.setString(1, phieuGiaoHang.getTenNguoiNhan());
            ps.setString(2, phieuGiaoHang.getSdtNguoiNhan());
            ps.setString(3, phieuGiaoHang.getDiaChi());
            ps.setString(4, phieuGiaoHang.getTenNguoiGiao());
            ps.setString(5, phieuGiaoHang.getSdtNguoiGiao());
            ps.setString(6, phieuGiaoHang.getMoTa());
            ps.setLong(7, phieuGiaoHang.getThoiGianUocTinh());
            ps.setString(8, phieuGiaoHang.getMa());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void themPhieu(PhieuGiaoHang phieuGiaoHang) {

        String query = "INSERT INTO phieu_giao_hang "
                + "(ma, nguoi_nhan, sdt_nguoi_nhan, dia_chi, nguoi_ship, "
                + "sdt_nguoi_ship, thoi_gian_giao, "
                + "mo_ta, trang_thai, thoi_gian_uoc_tinh) "
                + " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps
                = conn.prepareStatement(query)) {

            if (phieuGiaoHang.getTenNguoiGiao() == null) {
                ps.setString(1, phieuGiaoHang.getMa());
                ps.setString(2, phieuGiaoHang.getTenNguoiNhan());
                ps.setString(3, phieuGiaoHang.getSdtNguoiNhan());
                ps.setString(4, phieuGiaoHang.getDiaChi());
                ps.setNull(5, Types.NULL);
                ps.setNull(6, Types.NULL);
                ps.setNull(7, Types.NULL);
                ps.setString(8, phieuGiaoHang.getMoTa());
                ps.setInt(9, phieuGiaoHang.getTrangThai());
                ps.setNull(10, Types.NULL);
            } else {
                ps.setString(1, phieuGiaoHang.getMa());
                ps.setString(2, phieuGiaoHang.getTenNguoiNhan());
                ps.setString(3, phieuGiaoHang.getSdtNguoiNhan());
                ps.setString(4, phieuGiaoHang.getDiaChi());
                ps.setString(5, phieuGiaoHang.getTenNguoiGiao());
                ps.setString(6, phieuGiaoHang.getSdtNguoiGiao());
                ps.setLong(7, phieuGiaoHang.getThoiGianGiao());
                ps.setString(8, phieuGiaoHang.getMoTa());
                ps.setInt(9, phieuGiaoHang.getTrangThai());
                ps.setLong(10, phieuGiaoHang.getThoiGianUocTinh());
            }
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void chuyenTrangThai(Integer trangThai, String ma) {
        String query = "UPDATE phieu_giao_hang SET trang_thai = ?, thoi_gian_nhan = NULL, thoi_gian_giao = NULL WHERE ma = ?";

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps
                = conn.prepareStatement(query)) {
            ps.setInt(1, trangThai);
            ps.setString(2, ma);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void chuyenDaGiao(Integer trangThai, String ma, Long thoiGianNhan) {
        String query = "UPDATE phieu_giao_hang SET trang_thai = ?, thoi_gian_nhan = ? WHERE ma = ?";
        System.out.println("Đã giao repo");
        System.out.println(thoiGianNhan);
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps
                = conn.prepareStatement(query)) {
            ps.setInt(1, trangThai);
            ps.setLong(2, thoiGianNhan);
            ps.setString(3, ma);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // 2

    public void chuyenDangGiao(Integer trangThai, String ma, Long thoiGianGiao) {
        String query = "UPDATE phieu_giao_hang SET trang_thai = ?, thoi_gian_giao = ?, thoi_gian_nhan = NULL WHERE ma = ?";
        System.out.println("Đang giao repo");
        System.out.println(thoiGianGiao);
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps
                = conn.prepareStatement(query)) {
            ps.setInt(1, trangThai);
            ps.setLong(2, thoiGianGiao);
            ps.setString(3, ma);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // 5
}
