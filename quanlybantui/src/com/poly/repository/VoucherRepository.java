package com.poly.repository;

import com.poly.database.DBConnect;
import com.poly.entity.Voucher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            String query = "SELECT * FROM id_khuyen_mai";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listVoucher.add(new Voucher(rs.getInt(1), rs.getString(2), 
                        rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6),
                        rs.getInt(7))); // hien thi vi tri len JTable
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listVoucher;
    }

    public void them(Voucher v) throws Exception {
        try {
            String query = "INSERT INTO id_khuyen_mai(ten_khuyen_mai,phan_tram_giam_gia,thoi_gian_bat_dau,thoi_gian_ket_thuc,thoi_gian_sua,thoi_gian_tao) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, v.getTen());
            ps.setObject(2, v.getPhanTramGiamGia());
            ps.setObject(3, v.getDateBatDau());
            ps.setObject(4, v.getDateKetThuc());
            ps.setObject(5, v.getDateSua());
            ps.setObject(6, v.getDateTao());
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
            String query = "UPDATE id_khuyen_mai SET ten_khuyen_mai = ? , phan_tram_giam_gia = ?, thoi_gian_bat_dau = ?,thoi_gian_ket_thuc = ?,thoi_gian_sua = ? , thoi_gian_tao = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, v.getTen());
            ps.setObject(2, v.getPhanTramGiamGia());
            ps.setObject(3, v.getDateBatDau());
            ps.setObject(4, v.getDateKetThuc());
            ps.setObject(5, v.getDateSua());
            ps.setObject(6, v.getDateTao());
            ps.setObject(7, v.getId());
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
            String query = "DELETE FROM id_sanphamchitiet_khuyenmai WHERE id = ?;\n"
                            + "DELETE FROM id_khuyen_mai WHERE id = ?";
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
    
        public List<Voucher> locTheoThoiGian(int batDau, int ketThuc) {
        List<Voucher> listVoucher = new ArrayList<>();
        try {
            String query = "SELECT * FROM id_khuyen_mai WHERE  ";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listVoucher.add(new Voucher(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listVoucher;
    }
}
