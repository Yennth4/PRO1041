package com.poly.form.voucher.repository;

import com.poly.database.DBConnect;
import com.poly.form.voucher.entity.Voucher;
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

    public List<Voucher> getAll() {
        List<Voucher> listVoucher = new ArrayList<>();
        try {
            String query = "SELECT v.id ,ma_voucher,ten_voucher ,muc_chi_tieu_toi_thieu,l.ten_loai ,muc_giam_gia,thoi_gian_bat_dau,thoi_gian_ket_thuc,thoi_gian_sua,thoi_gian_tao,so_luong,t.ten_trang_thai FROM voucher v \n"
                    + "	JOIN loai_voucher l ON l.id = v.loai_voucher\n"
                    + "	JOIN trang_thai_voucher t ON t.id = v.trang_thai\n"
                    + " ORDER BY v.id DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listVoucher.add(new Voucher(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getInt(6), rs.getDate(7), rs.getDate(8), rs.getString(9), rs.getString(10), rs.getInt(11), rs.getString(12)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listVoucher;
    }

    public void them(Voucher v, String tenLoaiVoucher, String tenTrangThai) throws Exception {
        try {
            String query = "INSERT INTO voucher (ma_voucher, ten_voucher,muc_chi_tieu_toi_thieu,loai_voucher, muc_giam_gia, thoi_gian_bat_dau, thoi_gian_ket_thuc, so_luong, trang_thai) "
                    + "SELECT ?, ?, ?, lv.id AS loai_voucher, ?, ?, ?, ?, tt.id AS trang_thai "
                    + "FROM loai_voucher lv, trang_thai_voucher tt "
                    + "WHERE lv.ten_loai = ? AND tt.ten_trang_thai = ?";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, v.getMa());
            ps.setObject(2, v.getTen());
            ps.setObject(3, v.getMucChiTieuToiThieu());
            ps.setObject(4, v.getPhanTramGiamGia());
            ps.setTimestamp(5, new java.sql.Timestamp(v.getDateBatDau().getTime()));
            ps.setTimestamp(6, new java.sql.Timestamp(v.getDateKetThuc().getTime()));
            ps.setInt(7, v.getSoLuong());
            ps.setString(8, tenLoaiVoucher);
            ps.setString(9, tenTrangThai);
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
                    + "SET ma_voucher = ?, "
                    + "ten_voucher = ?, "
                    + "muc_chi_tieu_toi_thieu = ?, "
                    + "loai_voucher = lv.id, "
                    + "muc_giam_gia = ?, "
                    + "thoi_gian_bat_dau = ?, "
                    + "thoi_gian_ket_thuc = ?, "
                    + "thoi_gian_sua = GETDATE() , "
                    + "so_luong = ?, trang_thai = tt.id "
                    + "FROM loai_voucher lv, "
                    + "trang_thai_voucher tt "
                    + "WHERE lv.ten_loai = ? "
                    + "AND tt.ten_trang_thai = ? "
                    + "AND voucher.id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, v.getMa());
            ps.setObject(2, v.getTen());
            ps.setObject(3, v.getMucChiTieuToiThieu());
            ps.setObject(4, v.getPhanTramGiamGia());
            ps.setTimestamp(5, new java.sql.Timestamp(v.getDateBatDau().getTime()));
            ps.setTimestamp(6, new java.sql.Timestamp(v.getDateKetThuc().getTime()));
            ps.setInt(7, v.getSoLuong());
            ps.setString(8, tenLoaiVoucher);
            ps.setString(9, tenTrangThai);
            ps.setObject(10, id);
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

    public List<Voucher> locTimKiemTheoTrangThai(String trangThai) {
        List<Voucher> listVoucher = new ArrayList<>();
        try {
            String query = "SELECT v.id ,ma_voucher,ten_voucher,muc_chi_tieu_toi_thieu ,l.ten_loai ,muc_giam_gia,thoi_gian_bat_dau,thoi_gian_ket_thuc,thoi_gian_sua,thoi_gian_tao,so_luong,t.ten_trang_thai FROM voucher v \n"
                    + "	JOIN loai_voucher l ON l.id = v.loai_voucher\n"
                    + "	JOIN trang_thai_voucher t ON t.id = v.trang_thai\n"
                    + "	WHERE t.ten_trang_thai LIKE ? ";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, trangThai);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listVoucher.add(new Voucher(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getInt(6), rs.getDate(7), rs.getDate(8), rs.getString(9), rs.getString(10), rs.getInt(11), rs.getString(12)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listVoucher;
    }

    public List<Voucher> locTimKiemTheoLoaiVoucher(String loai) {
        List<Voucher> listVoucher = new ArrayList<>();
        try {
            String query = "SELECT v.id, ma_voucher, ten_voucher,muc_chi_tieu_toi_thieu, l.ten_loai, muc_giam_gia, thoi_gian_bat_dau, thoi_gian_ket_thuc, thoi_gian_sua, thoi_gian_tao, so_luong, t.ten_trang_thai\n"
                    + "FROM voucher v\n"
                    + "JOIN loai_voucher l ON l.id = v.loai_voucher\n"
                    + "JOIN trang_thai_voucher t ON t.id = v.trang_thai\n"
                    + "WHERE l.ten_loai LIKE ?;";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, loai);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listVoucher.add(new Voucher(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getInt(6), rs.getDate(7), rs.getDate(8), rs.getString(9), rs.getString(10), rs.getInt(11), rs.getString(12)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listVoucher;
    }

    public List<Voucher> locTimKiemTheoMa(String ma) {
        List<Voucher> listVoucher = new ArrayList<>();
        try {
            String query = "SELECT v.id ,ma_voucher,ten_voucher ,muc_chi_tieu_toi_thieu,l.ten_loai ,muc_giam_gia,thoi_gian_bat_dau,thoi_gian_ket_thuc,thoi_gian_sua,thoi_gian_tao,so_luong,t.ten_trang_thai FROM voucher v \n"
                    + "	JOIN loai_voucher l ON l.id = v.loai_voucher\n"
                    + "	JOIN trang_thai_voucher t ON t.id = v.trang_thai\n"
                    + "	WHERE ma_voucher LIKE ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, '%' + ma + '%');
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listVoucher.add(new Voucher(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getInt(6), rs.getDate(7), rs.getDate(8), rs.getString(9), rs.getString(10), rs.getInt(11), rs.getString(12)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listVoucher;
    }

    public List<Voucher> sortLamMoi() {
        List<Voucher> listVoucher = new ArrayList<>();
        try {
            String query = "	SELECT v.id ,ma_voucher,ten_voucher ,muc_chi_tieu_toi_thieu,l.ten_loai ,muc_giam_gia,thoi_gian_bat_dau,thoi_gian_ket_thuc,thoi_gian_sua,thoi_gian_tao,so_luong,t.ten_trang_thai FROM voucher v\n"
                    + "	JOIN loai_voucher l ON l.id = v.loai_voucher\n"
                    + "	JOIN trang_thai_voucher t ON t.id = v.trang_thai";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listVoucher.add(new Voucher(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getInt(6), rs.getDate(7), rs.getDate(8), rs.getString(9), rs.getString(10), rs.getInt(11), rs.getString(12)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listVoucher;
    }

    // Phan trang 
    public List<Voucher> phanTrang(int min, int max) {
        List<Voucher> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM ( \n"
                + "    SELECT v.id, ma_voucher, ten_voucher, muc_chi_tieu_toi_thieu, l.ten_loai, muc_giam_gia, thoi_gian_bat_dau, thoi_gian_ket_thuc, thoi_gian_sua, thoi_gian_tao, so_luong, t.ten_trang_thai,\n"
                + "    ROW_NUMBER() OVER (ORDER BY v.id) AS rownum\n"
                + "    FROM voucher v\n"
                + "    JOIN loai_voucher l ON l.id = v.loai_voucher\n"
                + "    JOIN trang_thai_voucher t ON t.id = v.trang_thai\n"
                + " ) AS temp WHERE rownum BETWEEN ? AND ?;";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, min);
            ps.setObject(2, max);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Voucher voucher = new Voucher(
                        rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getString(5), rs.getInt(6),
                        rs.getDate(7), rs.getDate(8), rs.getString(9),
                        rs.getString(10), rs.getInt(11), rs.getString(12)
                );
                list.add(voucher);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
