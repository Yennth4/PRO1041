/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.doihang.repository.DoiHangRepository;

import com.poly.database.DBConnect;
import com.poly.form.doihang.entity.DoiHangResponse.HoaDon;
import com.poly.form.doihang.entity.DoiHangResponse.ProductDetailResponse;
import com.poly.form.doihang.service.DoiHangService.impl.ProductDetailService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class HoaDonRepository {

    private ProductDetailService productDetailService = new ProductDetailService();

    private Connection conn;

    public HoaDonRepository() {
        conn = DBConnect.getConnection();
    }

    public List<HoaDon> findAllDaDoi(String keySearch, Integer trangThai) {
        List<HoaDon> listHoaDon = new ArrayList<>();
        try {

            String query = "select hd.IDHoaDon,"
                    + " hd.MaHoaDon,"
                    + " hd.GhiChu,"
                    + " hd.TongTienHoaDon,"
                    + " hd.TongTienSauKhuyenMai, "
                    + " nv.ten as TenNhanVien,"
                    + " kh.sdt,hd.SoTienDaGiam,"
                    + " kh.ho_ten as TenKhachHang,"
                    + " hd.TrangThai,"
                    + " hd.THoiGianTao "
                    + " FROM HoaDon hd "
                    + " left join nhan_vien nv on nv.id= hd.IDNhanVien "
                    + " left join khach_hang kh on kh.id= hd.IDKhachHang "
                    + " WHERE exists(select * from phieu_doi where id_hoa_don = hd.IDHoaDon) and "
                    + "  (hd.TrangThai = 1 or hd.TrangThai=5) and "
                    + "  ((hd.MaHoaDon like N'%" + keySearch + "%') or "
                    + "  (kh.ho_ten like N'%" + keySearch + "%') or "
                    + "  ( kh.sdt like N'%" + keySearch + "%') or "
                    + "  (hd.TongTienSauKhuyenMai like N'%" + keySearch + "%') or "
                    + "  (hd.TrangThai like N'%" + keySearch + "%'))"
                    + " order by hd.idhoadon desc";
            PreparedStatement ps = conn.prepareStatement(query);
//            ps.setObject(1, trangThai);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listHoaDon.add(new HoaDon(rs.getLong("IDHoaDon"),
                        rs.getString("MaHoaDon"),
                        rs.getString("GhiChu"),
                        rs.getFloat("TongTienHoaDon"),
                        rs.getFloat("tongTienSauKhuyenMai"),
                        rs.getString("TenKhachHang"),
                        rs.getString("TrangThai"),
                        rs.getDate("ThoiGianTao"),
                        rs.getString("Sdt"))
                );
            }

            return listHoaDon;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<HoaDon> findAllChuaDoi(String keySearch, Integer trangThai) {
        List<HoaDon> listHoaDon = new ArrayList<>();

        try {
            String query = "select hd.IDHoaDon, "
                    + " hd.MaHoaDon,"
                    + " hd.GhiChu,"
                    + " hd.TongTienHoaDon,"
                    + " hd.TongTienSauKhuyenMai, "
                    + " nv.ten as TenNhanVien,"
                    + " kh.sdt,hd.SoTienDaGiam,"
                    + " kh.ho_ten as TenKhachHang,"
                    + " hd.TrangThai,"
                    + " hd.THoiGianTao "
                    + " FROM HoaDon hd "
                    + " left join nhan_vien nv on nv.id= hd.IDNhanVien "
                    + " left join khach_hang kh on kh.id= hd.IDKhachHang "
                    + "  where (hd.TrangThai = 1 OR hd.TrangThai = 5) and "
                    + "  ((hd.MaHoaDon like N'%" + keySearch + "%') or "
                    + "  (kh.ho_ten like N'%" + keySearch + "%') or "
                    + "  ( kh.sdt like N'%" + keySearch + "%') or "
                    + "  (hd.TongTienSauKhuyenMai like N'%" + keySearch + "%') or "
                    + "  (hd.TrangThai like N'%" + keySearch + "%'))"
                    + " order by hd.idhoadon desc ";
            PreparedStatement ps = conn.prepareStatement(query);
//            ps.setObject(1, trangThai);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listHoaDon.add(new HoaDon(rs.getLong("IDHoaDon"),
                        rs.getString("MaHoaDon"),
                        rs.getString("GhiChu"),
                        rs.getFloat("TongTienHoaDon"),
                        rs.getFloat("tongTienSauKhuyenMai"),
                        rs.getString("TenKhachHang"),
                        rs.getString("TrangThai"),
                        rs.getDate("ThoiGianTao"),
                        rs.getString("Sdt")));
            }

            return listHoaDon;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public HoaDon finById(Long id) {
        HoaDon hoaDon = new HoaDon();
        try {
            String query = " select hd.IDHoaDon,"
                    + " hd.MaHoaDon,"
                    + " hd.GhiChu,"
                    + " hd.TongTienHoaDon,"
                    + " hd.TongTienSauKhuyenMai, "
                    + " nv.ten as TenNhanVien,"
                    + " kh.sdt,hd.SoTienDaGiam,"
                    + " kh.ho_ten as TenKhachHang,"
                    + " hd.TrangThai,"
                    + " hd.THoiGianTao "
                    + " FROM HoaDon hd "
                    + " left join nhan_vien nv on nv.id= hd.IDNhanVien"
                    + " left join khach_hang kh on kh.id= hd.IDKhachHang"
                    + " where IDHoaDon=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                hoaDon = new HoaDon(rs.getLong("IDHoaDon"),
                        rs.getString("MaHoaDon"),
                        rs.getString("GhiChu"),
                        rs.getFloat("TongTienHoaDon"),
                        rs.getFloat("tongTienSauKhuyenMai"),
                        rs.getString("TenKhachHang"),
                        rs.getString("TrangThai"),
                        rs.getDate("ThoiGianTao"),
                        rs.getString("Sdt"));
            }
            return hoaDon;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Long getIdHoaDonSanPham(Long idHoaDon, Long idSanPham) {
        try {
            String query = "select * from hoaDon_SanPhamchitiet where idHoaDon=? and idSanPhamChiTiet=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, idHoaDon);
            ps.setObject(2, idSanPham);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getLong("id");
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void updateLichSuChiTiet(Long idHoaDonSanPham, Integer soLuong) {
        try {
            String query = " update HoaDon_SanPhamChiTiet set SoLuong = ? where ID = ? ";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, soLuong  );
            ps.setObject(2, idHoaDonSanPham);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  public void updateDoiHang(Long idHoaDonSanPham, Integer soLuong) {
        Integer oleSoLuong = selectOldSoLuong(idHoaDonSanPham);
        try {
            String query = " update HoaDon_SanPhamChiTiet set SoLuong = ? where ID = ? ";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, soLuong);
            ps.setObject(2, idHoaDonSanPham);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void update(Long idHoaDonSanPham, Integer soLuong) {
        Integer oleSoLuong = selectOldSoLuong(idHoaDonSanPham);
        try {
            String query = " update HoaDon_SanPhamChiTiet set SoLuong = ? where ID = ? ";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, soLuong + oleSoLuong);
            ps.setObject(2, idHoaDonSanPham);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer selectOldSoLuong(Long idHoaDonSanPham) {
        try {
            String query = " select SoLuong from HoaDon_SanPhamChiTiet  where ID = ? ";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, idHoaDonSanPham);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("SoLuong");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void insert(ProductDetailResponse detailResponse, Long idHoaDon, Integer soLuong) {
        try {
            String query = "INSERT INTO HoaDon_SanPhamChiTiet(IDHoaDon, IDSanPhamChitiet, GiaTien, SoLuong) VALUES  "
                    + "	(?,?,?,?) ";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, idHoaDon);
            ps.setObject(2, detailResponse.getIdsanphamdetail());
            ps.setObject(3, detailResponse.getGiaTien());
            ps.setObject(4, soLuong);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Boolean checkHoaDonSanPham(ProductDetailResponse detailResponse, Long idHoaDon) {
        for (ProductDetailResponse productDetailResponse : productDetailService.findProductsById(idHoaDon)) {
            if (productDetailResponse.getIdsanphamdetail().equals(detailResponse.getIdsanphamdetail())) {
                return true;
            }
        }
        return false;
//        try {
//            String query = "select * from HoaDon_SanPham where idHoaDon= ? and idSanPham=? and giaTien = ?";
//            PreparedStatement ps = conn.prepareStatement(query);
//            ps.setObject(1, idHoaDon);
//            ps.setObject(2, detailResponse.getIdsanphamdetail());
//            ps.setObject(3, detailResponse.getGiaTien());
//            ResultSet rs = ps.executeQuery();
//            List<String> listt = new ArrayList<>();
//            while(rs.next()) {
//                listt.add("luat");
//            }
//            return listt.size() > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
    }

    public void deleteHoaDonSanPham(Long idHoaDonSanPham) {
        try {
            String query = " delete HoaDon_SanPhamChiTiet where ID =? ";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, idHoaDonSanPham);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("lỗi hoadonrepo dòng 265");
        }
    }

    public void doiTatCa(Long idHoaDon) {
        try {
            String query = " delete HoaDon_SanPhamChiTiet where IDHoaDon =? ";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, idHoaDon);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
