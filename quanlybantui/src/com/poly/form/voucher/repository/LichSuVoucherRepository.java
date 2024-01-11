package com.poly.form.voucher.repository;

import com.poly.database.DBConnect;
import com.poly.form.voucher.entity.LichSuVoucher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
                listLichSuVoucher.add(new LichSuVoucher(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getString(9)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listLichSuVoucher;
    }

    public List<LichSuVoucher> timKiemTheoIdVoucher(int id) {
        List<LichSuVoucher> listLichSuVoucher = new ArrayList<>();
        try {
            String query = "SELECT * FROM voucher_history WHERE id_voucher = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listLichSuVoucher.add(new LichSuVoucher(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getString(9)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listLichSuVoucher;
    }

    public List<LichSuVoucher> timKiemTheoIdHoaDon(int id) {
        List<LichSuVoucher> listLichSuVoucher = new ArrayList<>();
        try {
            String query = "SELECT * FROM voucher_history WHERE id_hoa_don = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listLichSuVoucher.add(new LichSuVoucher(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getString(9)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listLichSuVoucher;
    }

    public List<LichSuVoucher> getByTimeRange(Date minDate, Date maxDate) {
        List<LichSuVoucher> listLichSuVoucher = new ArrayList<>();
        try {
            String query = "SELECT * FROM voucher_history WHERE thoi_gian_su_dung >= ? AND thoi_gian_su_dung <= ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, new java.sql.Timestamp(minDate.getTime()));
            ps.setObject(2, new java.sql.Timestamp(maxDate.getTime()));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listLichSuVoucher.add(new LichSuVoucher(
                        rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
                        rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getString(9)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listLichSuVoucher;
    }

    public List<LichSuVoucher> phanTrang(int min, int max) {
        List<LichSuVoucher> listLichSuVoucher = new ArrayList<>();
        try {
            String query = "SELECT *FROM ( \n"
                    + "     SELECT \n"
                    + "        voucher_history.id,\n"
                    + "        voucher_history.ma_voucher_history,\n"
                    + "        voucher_history.id_hoa_don,\n"
                    + "        voucher_history.id_voucher,\n"
                    + "        voucher_history.thoi_gian_su_dung,\n"
                    + "        voucher_history.so_tien_giam_gia,\n"
                    + "        voucher_history.so_tien_before_giam_gia,\n"
                    + "        voucher_history.so_tien_after_giam_gia,\n"
                    + "        voucher_history.ghi_chu,\n"
                    + "        ROW_NUMBER() OVER (ORDER BY voucher_history.id) AS rownum\n"
                    + "    FROM voucher_history\n"
                    + "  ) AS temp\n"
                    + "WHERE rownum BETWEEN ? AND ?;";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, min);
            ps.setObject(2, max);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listLichSuVoucher.add(new LichSuVoucher(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getString(9)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listLichSuVoucher;
    }

    public int getTotalRecords() {
        try {
            String countQuery = "SELECT COUNT(*) FROM voucher_history";
            PreparedStatement countStatement = conn.prepareStatement(countQuery);
            ResultSet countResult = countStatement.executeQuery();
            if (countResult.next()) {
                return countResult.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
