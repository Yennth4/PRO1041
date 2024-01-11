/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.doihang.repository.DoiHangRepository;

import com.poly.database.DBConnect;
import com.poly.form.doihang.entity.DoiHangResponse.HoaDon;
import com.poly.form.doihang.entity.DoiHangResponse.LyDoDoi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class PhieuDoiRepository {

    private Connection conn;

    public PhieuDoiRepository() {
        conn = DBConnect.getConnection();
    }

    public void createPhieuDoi(Long idHoaDon) throws Exception {
        String query = "INSERT INTO phieu_doi (ngay_doi, id_hoa_don, id_trang_thai, id_nhan_vien)values( CURRENT_TIMESTAMP, ?,3, 3)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setObject(1, idHoaDon);
        ps.executeUpdate();
    }
    
    public Long getIdPhieuDoibyIdHoaDon(Long idHoaDon)throws Exception{
          List<LyDoDoi> listLyDo = new ArrayList<>(); 
            String query = "select id from phieu_doi where id_hoa_don =?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, idHoaDon);
            ResultSet rs = ps.executeQuery();
            Long id=null;
            while (rs.next()) {
              id=rs.getLong("id");
            }
          return id; 
    }
}
