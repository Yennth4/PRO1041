package com.poly.repository;

import com.poly.database.DBConnect;
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

    public List<Voucher> getAll() {
        List<Voucher> listVoucher = new ArrayList<>();
        try {
            String query = "SELECT * FROM voucher";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listVoucher.add(new Voucher(rs.getInt(1), rs.getString(2),
                        rs.getInt(3), rs.getDate(4), rs.getDate(5), rs.getDate(6),
                        rs.getDate(7))); // hien thi vi tri len JTable
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listVoucher;
    }

    public void them(Voucher v) throws Exception {
        try {
            String query = "INSERT INTO voucher(ten_voucher,phan_tram_giam_gia,thoi_gian_bat_dau,thoi_gian_ket_thuc,thoi_gian_sua,thoi_gian_tao) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, v.getTen());
            ps.setObject(2, v.getPhanTramGiamGia());
            ps.setTimestamp(3, new java.sql.Timestamp(v.getDateBatDau().getTime())); // Sử dụng Timestamp thay vì Date
            ps.setTimestamp(4, new java.sql.Timestamp(v.getDateKetThuc().getTime())); // Sử dụng Timestamp thay vì Date
            ps.setTimestamp(5, new java.sql.Timestamp(v.getDateSua().getTime()));
            ps.setTimestamp(6, new java.sql.Timestamp(v.getDateTao().getTime()));
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void sua(Voucher v) throws Exception {
        try {
            String query = "UPDATE voucher SET ten_voucher = ? , phan_tram_giam_gia = ?, thoi_gian_bat_dau = ?,thoi_gian_ket_thuc = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, v.getTen());
            ps.setObject(2, v.getPhanTramGiamGia());
            ps.setObject(3, v.getDateBatDau());
            ps.setObject(4, v.getDateKetThuc());
            ps.setObject(5, v.getId());
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
            String query = "DELETE FROM voucher WHERE id = ?";
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
}
