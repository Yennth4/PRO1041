/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.doihang.repository.DoiHangRepository;

import com.poly.database.DBConnect;
import com.poly.form.doihang.entity.DoiHangResponse.ProductDetailResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ProductDetailRepository {

    private Connection conn;

    public ProductDetailRepository() {
        conn = DBConnect.getConnection();
    }

    public List<ProductDetailResponse> findById(Long id) {
        List<ProductDetailResponse> listResponse = new ArrayList<>();
        String query = "select hdsp.id, hdsp.idsanphamchitiet,hdsp.SoLuong,hdsp.GiaTien, "
                + " m.TenMau, sp.TenSanPham,ct.Ma as maSanPham,ncc.TenNhaCungCap,sp.TrangThai  "
                + " from HoaDon_SanPhamChiTiet hdsp "
                + " left join sanphamchitiet ct on ct.IDSanPhamChiTiet= hdsp.IDSanPhamChitiet "
                + " left join sanpham sp on sp.IDSanPham = ct.IDSanPham "
                + " left join NhaCungCap ncc on sp.IDNhaCungCap = ncc.IDNhaCungCap "
                + " left join Mau m on m.IDMau = ct.IDMau "
                + " where idhoadon =?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               listResponse.add(new ProductDetailResponse(rs.getLong("id"), rs.getLong("idsanphamchitiet"), rs.getInt("soluong"),
                        rs.getFloat("giatien"), rs.getString("tenMau"),
                        rs.getString("tensanPham"), rs.getString("maSanPham"),
                        rs.getString("tenNhaCungCap"), rs.getString("trangThai")));
            }
            return listResponse;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ProductDetailResponse> findById(Long id, String keySearch, String tenMauSac) {
        List<ProductDetailResponse> listResponse = new ArrayList<>();
        String query = "select hdsp.id, hdsp.idsanphamchitiet,hdsp.SoLuong,hdsp.GiaTien, "
                + " m.TenMau, sp.TenSanPham,ct.Ma as maSanPham,ncc.TenNhaCungCap,sp.TrangThai  "
                + " from HoaDon_SanPhamChiTiet hdsp "
                + " left join sanphamchitiet ct on ct.IDSanPhamChiTiet= hdsp.IDSanPhamChitiet "
                + " left join sanpham sp on sp.IDSanPham = ct.IDSanPham "
                + " left join NhaCungCap ncc on sp.IDNhaCungCap = ncc.IDNhaCungCap "
                + " left join Mau m on m.IDMau = ct.IDMau "
                + " where idhoadon =? and "
                + " ( (sp.MaSanPham like N'%" + keySearch + "%') or  "
                + "  (sp.TenSanPham like N'%" + keySearch + "%') or  "
                + "  (hdsp.SoLuong like N'%" + keySearch + "%') or  "
                + "  (hdsp.GiaTien like N'%" + keySearch + "%') or  "
                + "  (m.TenMau like N'%" + keySearch + "%') ) and  "
                + "   (m.TenMau like N'%" + tenMauSac + "%') "; 
        try {
            PreparedStatement ps = conn.prepareStatement(query); 
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) { 
                listResponse.add(new ProductDetailResponse(rs.getLong("id"), rs.getLong("idsanphamchitiet"), rs.getInt("soluong"),
                        rs.getFloat("giatien"), rs.getString("tenMau"),
                        rs.getString("tensanPham"), rs.getString("maSanPham"),
                        rs.getString("tenNhaCungCap"), rs.getString("trangThai")));
            }
             return listResponse;
        } catch (Exception e) {
            e.printStackTrace();
        } 

        return null;
    }

    public List<ProductDetailResponse> findByMaSanPham(String maSanPham) {
        List<ProductDetailResponse> listResponse = new ArrayList<>();
        String query = "select hdsp.id, hdsp.idsanphamchitiet,hdsp.SoLuong,hdsp.GiaTien, "
                + " m.TenMau, sp.TenSanPham,ct.Ma as maSanPham,ncc.TenNhaCungCap,sp.TrangThai,tl.TenTheLoai "
                + " from HoaDon_SanPhamchitiet hdsp "
                + " left join sanphamchitiet ct on ct.IDSanPhamChiTiet= hdsp.IDSanPhamchitiet "
                + " left join sanpham sp on sp.IDSanPham = ct.IDSanPham "
                + " left join NhaCungCap n cc on sp.IDNhaCungCap = ncc.IDNhaCungCap "
                + " left join TheLoai tl on sp.IDTheLoai = tl.IDTheLoai "
                + " left join Mau m on m.IDMau = ct.IDMau "
                + " where sp like ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, maSanPham);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listResponse.add(new ProductDetailResponse(rs.getLong("id"), rs.getLong("idsanphamchitiet"), rs.getInt("soluong"),
                        rs.getFloat("giatien"), rs.getString("tenMau"),
                        rs.getString("tensanPham"), rs.getString("maSanPham"),
                        rs.getString("tenNhaCungCap"), rs.getString("trangThai"),
                        rs.getString("tenTheLoai")));
            }
            return listResponse;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ProductDetailResponse> sanPhamDaDoi(Long id) {
        List<ProductDetailResponse> listResponse = new ArrayList<>();
        String query = " select pdct.id, pdct.id_san_pham_chi_tiet,pdct.so_luong_doi,pdct.gia_ban,   "
                + "   m.TenMau, sp.TenSanPham,ct.Ma as maSanPham,ncc.TenNhaCungCap,sp.TrangThai, "
                + "  ldd.ly_do from  phieu_doi_chi_tiet pdct "
                + "    left join phieu_doi pd on pdct.id_phieu_doi = pd.id  "
                + "  left join sanphamchitiet ct on ct.IDSanPhamChiTiet= pdct.id_san_pham_chi_tiet "
                + "  left join sanpham sp on sp.IDSanPham = ct.IDSanPham  "
                + "   left join NhaCungCap ncc on sp.IDNhaCungCap = ncc.IDNhaCungCap   "
                + "   left join Mau m on m.IDMau = ct.IDMau   "
                + " left join ly_do_doi ldd on ldd.id= pdct.id_ly_do_doi"
                + " where pd.id_hoa_don=?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listResponse.add(new ProductDetailResponse(rs.getLong("id"), rs.getLong("id_san_pham_chi_tiet"), rs.getInt("so_luong_doi"),
                        rs.getFloat("gia_ban"), rs.getString("tenMau"),
                        rs.getString("tensanPham"), rs.getString("maSanPham"),
                        rs.getString("tenNhaCungCap"), rs.getString("trangThai"),
                        rs.getString("Ly_Do")));
            }
            return listResponse;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ProductDetailResponse> sanPhamChuaDoi(Long id) {
        List<ProductDetailResponse> listResponse = new ArrayList<>();
        String query = "select hdsp.id, hdsp.idsanphamchitiet,hdsp.SoLuong,hdsp.GiaTien, "
                + " m.TenMau, sp.TenSanPham,ct.Ma as maSanPham,ncc.TenNhaCungCap,sp.TrangThai,tl.TenTheLoai "
                + "from HoaDon_SanPhamchitiet hdsp "
                + "left join sanphamchitiet ct on ct.IDSanPhamChiTiet= hdsp.IDSanPhamchitiet "
                + "left join sanpham sp on sp.IDSanPham = ct.IDSanPham "
                + "left join NhaCungCap ncc on sp.IDNhaCungCap = ncc.IDNhaCungCap "
                + "left join TheLoai tl on sp.IDTheLoai = tl.IDTheLoai "
                + "left join Mau m on m.IDMau = ct.IDMau "
                + " where hdsp.idhoadon =? and not exists (select * from phieu_doi_chi_tiet where id_san_pham_chi_tiet = ct.IDSanPhamChiTiet )";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listResponse.add(new ProductDetailResponse(rs.getLong("id"), rs.getLong("idsanphamchitiet"), rs.getInt("soluong"),
                        rs.getFloat("giatien"), rs.getString("tenMau"),
                        rs.getString("tensanPham"), rs.getString("maSanPham"),
                        rs.getString("tenNhaCungCap"), rs.getString("trangThai"),
                        rs.getString("tenTheLoai")));
            }
            return listResponse;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
