package com.poly.form.voucher.repository;

import com.poly.database.DBConnect;
import com.poly.form.voucher.entity.LoaiVoucher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoaiRepository {

    private Connection conn;

    public LoaiRepository() {
        try {
            conn = DBConnect.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<LoaiVoucher> getAll() {
        List<LoaiVoucher> listLoaiVoucher = new ArrayList<>();
        try {
            String query = "SELECT * FROM loai_voucher";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listLoaiVoucher.add(new LoaiVoucher(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listLoaiVoucher;
    }

    public void them(LoaiVoucher v) throws Exception {
        try {
            String query = "INSERT INTO loai_voucher (ma_loai, ten_loai, mo_ta) VALUES(?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, v.getMaLoai());
            ps.setObject(2, v.getTenLoai());
            ps.setObject(3, v.getMoTaLoai());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void sua(int id, LoaiVoucher v) throws Exception {
        try {
            String query = "UPDATE loai_voucher SET ma_loai= ?, ten_loai = ?, mo_ta = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, v.getMaLoai());
            ps.setObject(2, v.getTenLoai());
            ps.setObject(3, v.getMoTaLoai());
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
            String query = "DELETE FROM voucher WHERE loai_voucher = ?;\n"
                    + "	DELETE FROM loai_voucher WHERE id = ?";
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

    public List<LoaiVoucher> timKiemTheoMa(String ma) {
        List<LoaiVoucher> listLoaiVoucher = new ArrayList<>();
        try {
            String query = "SELECT * FROM loai_voucher WHERE ma_loai LIKE ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, '%' + ma + '%');
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listLoaiVoucher.add(new LoaiVoucher(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listLoaiVoucher;
    }
}
