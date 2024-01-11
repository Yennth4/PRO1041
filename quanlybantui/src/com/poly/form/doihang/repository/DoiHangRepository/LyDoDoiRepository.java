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
public class LyDoDoiRepository {
     private Connection conn;

    public LyDoDoiRepository() {
        conn=DBConnect.getConnection();
    }
     
    public List<LyDoDoi> findAll(){
         List<LyDoDoi> listLyDo = new ArrayList<>();
        try {
            String query = "select * from Ly_Do_Doi ";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listLyDo.add( new LyDoDoi(rs.getLong("id"), rs.getString("Ly_do"), 
                        rs.getString("mo_ta")));
            }
            return listLyDo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
       public void createLyDoChiTiet(Long idPD,Long idLDD) throws Exception{
            String query = "INSERT INTO  ly_do_chi_tiet (id_phieu_doi, id_ly_do_doi) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);        

            ps.setObject(1, idPD);         

            ps.setObject(2, idLDD);
             ps.executeUpdate();          

    }
}
