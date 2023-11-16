package com.poly.repository;

import com.poly.database.DBConnect;
import com.poly.entity.SanPham;
import com.poly.entity.Voucher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class VoucherRepository {

    private Connection conn;

    public VoucherRepository() {
        try {
            conn = DBConnect.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<SanPham> fakeData() {
        List<SanPham> list = new ArrayList<>();
        list.add(new SanPham(false, "SP0001", "TÚI ĐEO VAI MII", 11000, "Brand1", "Black", "1 găn lớn, 3 ngăn nhỏ", "Còn hàng"));
        list.add(new SanPham(false, "SP0002", "TÚI XÁCH TAY DỄ THƯƠG", 3999, "Brand2", "Pink", "1 găn lớn, 3 ngăn nhỏ", "Còn hàng"));
        list.add(new SanPham(false, "SP0003", "TÚI CHAEL", 9999, "Brand1", "White", "1 găn lớn, 3 ngăn nhỏ", "Còn hàng"));
        list.add(new SanPham(false, "SP0004", "TÚI HERMES", 14999, "Brand2", "Brown", "1 găn lớn, 3 ngăn nhỏ", "Còn hàng"));
        list.add(new SanPham(false, "SP0005", "TÚI ĐEO VAI CỠ VỪA", 41999, "Brand1", "Blue", "1 găn lớn, 3 ngăn nhỏ", "Còn hàng"));
        list.add(new SanPham(false, "SP0006", "TÚI XÁCH TAY QUÝ PHÁI", 7999, "Brand2", "Red", "1 găn lớn, 3 ngăn nhỏ", "Còn hàng"));
        list.add(new SanPham(false, "SP0007", "TÚI GUCCI", 19999, "Brand1", "Green", "1 găn lớn, 3 ngăn nhỏ", "Còn hàng"));
        list.add(new SanPham(false, "SP0008", "TÚI LV", 17999, "Brand2", "Yellow", "1 găn lớn, 3 ngăn nhỏ", "Còn hàng"));
        return list;
    }

    public List<Voucher> getAll() {
        List<Voucher> listVoucher = new ArrayList<>();
        try {
            String query = "SELECT v.id ,ma_voucher,ten_voucher ,l.ten_loai ,muc_giam_gia,thoi_gian_bat_dau,thoi_gian_ket_thuc,thoi_gian_sua,thoi_gian_tao,so_luong,t.ten_trang_thai FROM voucher v \n"
                    + "	JOIN loai_voucher l ON l.id = v.loai_voucher\n"
                    + "	JOIN trang_thai_voucher t ON t.id = v.trang_thai\n"
                    + "	ORDER BY v.id DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listVoucher.add(new Voucher(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getDate(6), rs.getDate(7), rs.getDate(8), rs.getDate(9), rs.getInt(10), rs.getString(11)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listVoucher;
    }

    public void them(Voucher v, String tenLoaiVoucher, String tenTrangThai) throws Exception {
        try {
            String query = "INSERT INTO voucher (ma_voucher, ten_voucher, loai_voucher, muc_giam_gia, thoi_gian_bat_dau, thoi_gian_ket_thuc, so_luong, trang_thai) "
                    + "SELECT ?, ?, lv.id AS loai_voucher, ?, ?, ?, ?, tt.id AS trang_thai "
                    + "FROM loai_voucher lv, trang_thai_voucher tt "
                    + "WHERE lv.ten_loai = ? AND tt.ten_trang_thai = ?";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, v.getMa());
            ps.setObject(2, v.getTen());
            ps.setObject(3, v.getPhanTramGiamGia());
            ps.setTimestamp(4, new java.sql.Timestamp(v.getDateBatDau().getTime()));
            ps.setTimestamp(5, new java.sql.Timestamp(v.getDateKetThuc().getTime()));
            ps.setInt(6, v.getSoLuong());
            ps.setString(7, tenLoaiVoucher);
            ps.setString(8, tenTrangThai);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void capNhat(Voucher v, String tenLoaiVoucher, String tenTrangThai, int id) throws Exception {
        try {
            String query = "UPDATE voucher "
                    + "SET ma_voucher = ?, ten_voucher = ?, loai_voucher = lv.id, muc_giam_gia = ?, "
                    + "thoi_gian_bat_dau = ?, thoi_gian_ket_thuc = ?, so_luong = ?, trang_thai = tt.id "
                    + "FROM loai_voucher lv, trang_thai_voucher tt "
                    + "WHERE lv.ten_loai = ? AND tt.ten_trang_thai = ? AND voucher.id = ?";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, v.getMa());
            ps.setObject(2, v.getTen());
            ps.setObject(3, v.getPhanTramGiamGia());
            ps.setTimestamp(4, new java.sql.Timestamp(v.getDateBatDau().getTime()));
            ps.setTimestamp(5, new java.sql.Timestamp(v.getDateKetThuc().getTime()));
            ps.setInt(6, v.getSoLuong());
            ps.setString(7, tenLoaiVoucher);
            ps.setString(8, tenTrangThai);
            ps.setLong(9, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void xoa(int index) throws Exception {
        try {
            String query = "DELETE FROM voucher_history WHERE id_voucher = ?;\n"
                    + "DELETE FROM voucher WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, index);
            ps.setObject(2, index);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
//OK

    public List<Voucher> locTimKiemTheoTrangThai(String trangThai) {
        List<Voucher> listVoucher = new ArrayList<>();
        try {
            String query = "SELECT v.id ,ma_voucher,ten_voucher ,l.ten_loai ,muc_giam_gia,thoi_gian_bat_dau,thoi_gian_ket_thuc,thoi_gian_sua,thoi_gian_tao,so_luong,t.ten_trang_thai FROM voucher v \n"
                    + "	JOIN loai_voucher l ON l.id = v.loai_voucher\n"
                    + "	JOIN trang_thai_voucher t ON t.id = v.trang_thai\n"
                    + "	WHERE t.ten_trang_thai LIKE ? ";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, trangThai);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listVoucher.add(new Voucher(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getDate(6), rs.getDate(7), rs.getDate(8), rs.getDate(9), rs.getInt(10), rs.getString(11)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listVoucher;
    }
// OK

    public List<Voucher> locTimKiemTheoLoaiVoucher(String loai) {
        List<Voucher> listVoucher = new ArrayList<>();
        try {
            String query = "SELECT v.id, ma_voucher, ten_voucher, l.ten_loai, muc_giam_gia, thoi_gian_bat_dau, thoi_gian_ket_thuc, thoi_gian_sua, thoi_gian_tao, so_luong, t.ten_trang_thai\n"
                    + "FROM voucher v\n"
                    + "JOIN loai_voucher l ON l.id = v.loai_voucher\n"
                    + "JOIN trang_thai_voucher t ON t.id = v.trang_thai\n"
                    + "WHERE l.ten_loai LIKE ?;";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, loai);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listVoucher.add(new Voucher(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getDate(6), rs.getDate(7), rs.getDate(8), rs.getDate(9), rs.getInt(10), rs.getString(11)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listVoucher;
    }
// ok

    public List<Voucher> locTimKiemTheoMa(String ma) {
        List<Voucher> listVoucher = new ArrayList<>();
        try {
            String query = "SELECT v.id ,ma_voucher,ten_voucher ,l.ten_loai ,muc_giam_gia,thoi_gian_bat_dau,thoi_gian_ket_thuc,thoi_gian_sua,thoi_gian_tao,so_luong,t.ten_trang_thai FROM voucher v \n"
                    + "	JOIN loai_voucher l ON l.id = v.loai_voucher\n"
                    + "	JOIN trang_thai_voucher t ON t.id = v.trang_thai\n"
                    + "	WHERE ma_voucher LIKE ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, '%' + ma + '%');
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listVoucher.add(new Voucher(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getDate(6), rs.getDate(7), rs.getDate(8), rs.getDate(9), rs.getInt(10), rs.getString(11)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listVoucher;
    }
// ok

    public List<Voucher> sortLamMoi() {
        List<Voucher> listVoucher = new ArrayList<>();
        try {
            String query = "	SELECT v.id ,ma_voucher,ten_voucher ,l.ten_loai ,muc_giam_gia,thoi_gian_bat_dau,thoi_gian_ket_thuc,thoi_gian_sua,thoi_gian_tao,so_luong,t.ten_trang_thai FROM voucher v\n"
                    + "	JOIN loai_voucher l ON l.id = v.loai_voucher\n"
                    + "	JOIN trang_thai_voucher t ON t.id = v.trang_thai";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listVoucher.add(new Voucher(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getDate(6), rs.getDate(7), rs.getDate(8), rs.getDate(9), rs.getInt(10), rs.getString(11)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listVoucher;
    }

}
