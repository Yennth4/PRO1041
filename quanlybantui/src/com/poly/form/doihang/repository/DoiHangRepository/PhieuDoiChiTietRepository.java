/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.doihang.repository.DoiHangRepository;

import com.poly.database.DBConnect;
import com.poly.form.doihang.entity.DoiHangResponse.HoaDon;
import com.poly.form.doihang.entity.DoiHangResponse.LyDoDoi;
import com.poly.form.doihang.entity.DoiHangResponse.PhieuDoiChiTiet;
import com.poly.form.doihang.entity.DoiHangResponse.ProductDetailResponse;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class PhieuDoiChiTietRepository {

    private Connection conn;

    public PhieuDoiChiTietRepository() {
        conn = DBConnect.getConnection();
    }

    public void createPhieuDoiChiTiet(ProductDetailResponse detailResponse, Long idPD, Long idLyDoDoi, String mota, Integer soLuongDoi)
            throws Exception {
        String query = "INSERT INTO phieu_doi_chi_tiet (gia_ban, ten_san_pham, mau, so_luong_doi,id_san_pham_chi_tiet, id_phieu_doi,id_ly_do_doi,mota) "
                + "VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setObject(1, detailResponse.getGiaTien());
        ps.setObject(2, detailResponse.getTenSanPham());
        ps.setObject(3, detailResponse.getTenMau());
        ps.setObject(4, soLuongDoi);
        ps.setObject(5, detailResponse.getIdsanphamdetail());
        ps.setObject(6, idPD);
        ps.setObject(7, idLyDoDoi);
        ps.setObject(8, mota);
        ps.executeUpdate();
    }

    public void createPhieuDoiChiTietTienMat(ProductDetailResponse detailResponse, Long idPD, Long idLyDoDoi, String mota, Integer soLuongDoi) throws Exception {
        String query = "INSERT INTO phieu_doi_chi_tiet (gia_ban, ten_san_pham, mau, so_luong_doi,id_san_pham_chi_tiet, id_phieu_doi,id_ly_do_doi,mota) "
                + "VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setObject(1, new BigDecimal(detailResponse.getGiaTien()).divide(new BigDecimal(detailResponse.getSoLuong())).multiply(new BigDecimal(soLuongDoi)));
        ps.setObject(2, "Tiền mặt");
        ps.setObject(3, null);
        ps.setObject(4, 1);
        ps.setObject(5, detailResponse.getIdsanphamdetail());
        ps.setObject(6, idPD);
        ps.setObject(7, idLyDoDoi);
        ps.setObject(8, mota);
        ps.executeUpdate();

    }

    public Boolean checkDoiHang(Long idSPCT, Long idPD, Long idLyDo) throws Exception {
        String query = "select * from phieu_doi_chi_tiet where id_san_pham_chi_tiet =? and id_phieu_doi =? and id_ly_do_doi =?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setObject(1, idSPCT);
        ps.setObject(2, idPD);
        ps.setObject(3, idLyDo);
        ResultSet rs = ps.executeQuery();
        List<String> list = new ArrayList<>();
        while (rs.next()) {
            list.add("lucluat");
        }
        return list.size() > 0;
    }

    public void addLyDoChiTiet(Integer soluong, Long idSPCT, Long idPhieuDoi, Long idLyDo) throws Exception {
        String query1 = "select * from phieu_doi_chi_tiet  where id_san_pham_chi_tiet =? and id_phieu_doi=? and id_ly_do_doi =?";
        PreparedStatement ps1 = conn.prepareStatement(query1);
        ps1.setObject(1, idSPCT);
        ps1.setObject(2, idPhieuDoi);
        ps1.setObject(3, idLyDo);
        ResultSet rs = ps1.executeQuery();
        Integer oldSoLuong = 0;
        System.out.println(soluong);
        System.out.println(oldSoLuong);
        while (rs.next()) {
            oldSoLuong = rs.getInt("so_luong_doi");
        }
        System.out.println(soluong);
        System.out.println(oldSoLuong);
        String query = "update phieu_doi_chi_tiet set so_luong_doi=? where id_san_pham_chi_tiet =? and id_phieu_doi=? and id_ly_do_doi =?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setObject(1, soluong + oldSoLuong);
        ps.setObject(2, idSPCT);
        ps.setObject(3, idPhieuDoi);
        ps.setObject(4, idLyDo);
        ps.executeUpdate();
    }

    public void deletePDCT(Long id) {
        try {
            String query = "delete phieu_doi_chi_tiet where id= ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletePDCT(Long id, Integer soLuongNew) {
        try {
            String query = "update phieu_doi_chi_tiet set so_Luong_doi = ? where id= ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, soLuongNew);
            ps.setObject(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<PhieuDoiChiTiet> findAll() {
        List<PhieuDoiChiTiet> list = new ArrayList<>();
        try {
            String query = "select * from phieu_doi_chi_tiet";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new PhieuDoiChiTiet(rs.getLong("id"), rs.getFloat("gia_ban"),
                        rs.getString("ten_san_pham"), rs.getString("mau"),
                        rs.getString("chat_lieu"), rs.getInt("so_luong_doi"),
                        rs.getInt("id_san_pham_chi_tiet"), rs.getInt("id_ly_do_doi"),
                        rs.getInt("id_phieu_doi"), rs.getString("mota")));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
