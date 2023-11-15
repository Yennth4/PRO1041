package com.poly.repository;

import com.poly.database.DBConnect;
import com.poly.entity.LichSuVoucher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author haiyenng
 */
public class LichSuVoucherRepository {
     private Connection conn;

    public LichSuVoucherRepository() {
        try {
            conn = DBConnect.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<LichSuVoucher> getAll() {
        List<LichSuVoucher> listLichSuVoucher = new ArrayList<>();
        try {
            String query = "SELECT * FROM voucher_history ORDER BY id DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listLichSuVoucher.add(new LichSuVoucher(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDate(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getString(8)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listLichSuVoucher;
    }

    public void them(LichSuVoucher v) throws Exception {
        try {
            String query = "INSERT INTO voucher_history (ma_voucher_history, id_voucher, thoi_gian_su_dung, so_tien_giam_gia, so_tien_before_giam_gia, so_tien_after_giam_gia, ghi_chu) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, v.getMa());
            ps.setObject(2, v.getIdVoucher());
            ps.setTimestamp(3, new java.sql.Timestamp(v.getThoiGianSuDung().getTime()));
            ps.setInt(4, v.getSoTienGiamGia());
            ps.setInt(5, v.getSoTienTruocGiamGia());
            ps.setInt(6, v.getSoTienSauGiamGia());
            ps.setString(7, v.getGhiChu());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void sua(int id, LichSuVoucher v) throws Exception {
        try {
            String query = "UPDATE voucher_history SET ma_voucher_history = ?, id_voucher = ?, thoi_gian_su_dung = ?, so_tien_giam_gia = ?, so_tien_before_giam_gia = ?, so_tien_after_giam_gia = ?, ghi_chu = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, v.getMa());
            ps.setObject(2, v.getIdVoucher());
            ps.setTimestamp(3, new java.sql.Timestamp(v.getThoiGianSuDung().getTime()));
            ps.setInt(4, v.getSoTienGiamGia());
            ps.setInt(5, v.getSoTienTruocGiamGia());
            ps.setInt(6, v.getSoTienSauGiamGia());
            ps.setString(7, v.getGhiChu());
            ps.setInt(8, id);
            ps.executeUpdate();
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
            String query = "DELETE FROM voucher_history WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, index);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    public List<LichSuVoucher> timKiemTheoTen(int ten) {
        List<LichSuVoucher> listLichSuVoucher = new ArrayList<>();
        try {
            String query = "SELECT * FROM voucher_history WHERE id_voucher = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1,ten);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listLichSuVoucher.add(new LichSuVoucher(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDate(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getString(8)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listLichSuVoucher;
    }
}
