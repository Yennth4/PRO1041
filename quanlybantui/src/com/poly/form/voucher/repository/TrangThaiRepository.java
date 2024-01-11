package com.poly.form.voucher.repository;

import com.poly.database.DBConnect;
import com.poly.form.voucher.entity.TrangThaiVoucher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class TrangThaiRepository {

    private Connection conn;

    public TrangThaiRepository() {
        try {
            conn = DBConnect.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<TrangThaiVoucher> getAll() {
        List<TrangThaiVoucher> listTrangThaiVoucher = new ArrayList<>();
        try {
            String query = "SELECT * FROM trang_thai_voucher";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listTrangThaiVoucher.add(new TrangThaiVoucher(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTrangThaiVoucher;
    }

    public void them(TrangThaiVoucher v) throws Exception {
        try {
            String query = "INSERT INTO trang_thai_voucher (ma_trang_thai, ten_trang_thai, mo_ta) VALUES(?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, v.getMaTrangThai());
            ps.setObject(2, v.getTenTrangThai());
            ps.setObject(3, v.getMoTaTrangThai());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void sua(int id, TrangThaiVoucher v) throws Exception {
        try {
            String query = "UPDATE trang_thai_voucher SET ma_trang_thai= ?, ten_trang_thai = ?, mo_ta = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, v.getMaTrangThai());
            ps.setObject(2, v.getTenTrangThai());
            ps.setObject(3, v.getMoTaTrangThai());
            ps.setInt(4, id);
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
            String query = "DELETE FROM voucher WHERE trang_thai = ?;\n"
                    + "	DELETE FROM trang_thai_voucher WHERE id = ?";
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

    public List<TrangThaiVoucher> timKiemTheoMa(String ma) {
        List<TrangThaiVoucher> listTrangThaiVoucher = new ArrayList<>();
        try {
            String query = "SELECT * FROM trang_thai_voucher WHERE ma_trang_thai LIKE ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, '%' + ma + '%');
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listTrangThaiVoucher.add(new TrangThaiVoucher(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTrangThaiVoucher;
    }
}
