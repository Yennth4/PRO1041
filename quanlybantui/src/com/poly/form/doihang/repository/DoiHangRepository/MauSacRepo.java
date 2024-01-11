/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.doihang.repository.DoiHangRepository;

import com.poly.database.DBConnect;
import com.poly.form.doihang.entity.DoiHangResponse.MauSacResponse;
import com.poly.form.doihang.entity.DoiHangResponse.TheLoaiResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MauSacRepo {

    private Connection conn;

    public MauSacRepo() {
        conn= DBConnect.getConnection();
    }
    public List<MauSacResponse> findAll( ) {
        List<MauSacResponse> list  = new ArrayList<>();
        try {
            String query = " select IDMau,TenMau from Mau ";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               list.add(new MauSacResponse(rs.getInt("IDMau") , rs.getString("TenMau")));
            }
            return list ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
