/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.doihang.repository.DoiHangRepository;

import com.poly.database.DBConnect;
import com.poly.form.doihang.entity.DoiHangResponse.HoaDon; 
import com.poly.form.doihang.entity.DoiHangResponse.TheLoaiResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class TheLoaiRepo {

    private Connection conn;

    public TheLoaiRepo() {
        conn = DBConnect.getConnection();
    }

    public List<TheLoaiResponse> findAll( ) {
        List<TheLoaiResponse> list  = new ArrayList<>();
        try {
            String query = " select IDTheLoai,MaTheLoai,TenTheLoai from TheLoai ";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               list.add(new TheLoaiResponse(rs.getInt("IDTheLoai"), rs.getString("MaTheLoai"), rs.getString("TenTheLoai")));
            }
            return list ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
