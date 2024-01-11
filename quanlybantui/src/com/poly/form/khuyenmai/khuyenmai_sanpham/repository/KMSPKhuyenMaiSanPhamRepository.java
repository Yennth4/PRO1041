package com.poly.form.khuyenmai.khuyenmai_sanpham.repository;

import com.poly.database.DBConnect;
import com.poly.form.khuyenmai.khuyenmai_sanpham.entity.KhuyenMaiTheoSanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class KMSPKhuyenMaiSanPhamRepository {

    public void themKhuyenMaiSanPham(Long idKM, Long id)
            throws Exception {
        String query = "INSERT INTO sanphamchitiet_khuyenmai (id_khuyen_mai, id_san_pham_chi_tiet) VALUES (?, ?)";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps
                = conn.prepareStatement(query)) {
            ps.setLong(1, idKM);
            ps.setLong(2, id);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
