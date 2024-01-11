/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.khachhang.repository;

import com.poly.database.DBConnect;
import com.poly.form.khachhang.entity.HoaDonKhachHang;
import com.poly.form.khachhang.entity.KhachHangHung;
import com.poly.form.khachhang.entity.LichSuGiaoDich;
import com.poly.form.khachhang.entity.SanPhamKhachHang;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class KhachHangRepository {

    public List<KhachHangHung> getAllKhachHang() {
        List<KhachHangHung> list = new ArrayList<>();
        String sql = "SELECT id,ho_ten, ma, gioi_tinh, sdt, dia_chi, email, ngay_sinh, cap_bac, thoi_gian_tao, thoi_gian_sua FROM khach_hang where is_delete = 0 ORDER BY thoi_gian_tao DESC";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHangHung kh = new KhachHangHung();
                kh.setId(rs.getInt("id"));
                kh.setMa(rs.getString("ma"));
                kh.setHoTen(rs.getString("ho_ten"));
                kh.setGioiTinh(rs.getBoolean("gioi_tinh"));
                kh.setSdt(rs.getString("sdt"));
                kh.setDiaChi(rs.getString("dia_chi"));
                kh.setEmail(rs.getString("email"));
                kh.setNgaySinh(rs.getDate("ngay_sinh"));
                kh.setCapBac(rs.getInt("cap_bac"));
                kh.setThoiGianTao(rs.getDate("thoi_gian_tao"));
                kh.setThoiGianSua(rs.getDate("thoi_gian_sua"));
                list.add(kh);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<LichSuGiaoDich> getAllLichSuGiaoDich(int id) {
        List<LichSuGiaoDich> list = new ArrayList<>();
        String sql = "select MaHoaDon, ThoiGianTao ,TongTien, CapBacKhachHang from  LichSuGiaoDich where IDKhachHang = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LichSuGiaoDich lsgd = new LichSuGiaoDich();
                lsgd.setMaHoaDon(rs.getString("MaHoaDon"));
                lsgd.setNgayGiaoDich(rs.getDate("ThoiGianTao"));
                lsgd.setTongTienGiaoDich(rs.getBigDecimal("TongTien"));
                lsgd.setCapBacKhachHang(rs.getInt("CapBacKhachHang"));
                list.add(lsgd);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    

    public List<KhachHangHung> getAllKhachHangDaXoa() {
        List<KhachHangHung> list = new ArrayList<>();
        String sql = "SELECT id,ho_ten,ma, gioi_tinh, sdt, dia_chi, email, ngay_sinh, cap_bac, thoi_gian_tao, thoi_gian_sua FROM khach_hang where is_delete = 1";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHangHung kh = new KhachHangHung();
                kh.setId(rs.getInt("id"));
                kh.setMa(rs.getString("ma"));
                kh.setHoTen(rs.getString("ho_ten"));
                kh.setGioiTinh(rs.getBoolean("gioi_tinh"));
                kh.setSdt(rs.getString("sdt"));
                kh.setDiaChi(rs.getString("dia_chi"));
                kh.setEmail(rs.getString("email"));
                kh.setNgaySinh(rs.getDate("ngay_sinh"));
                kh.setCapBac(rs.getInt("cap_bac"));
                kh.setThoiGianTao(rs.getDate("thoi_gian_tao"));
                kh.setThoiGianSua(rs.getDate("thoi_gian_sua"));
                list.add(kh);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public KhachHangHung getKhachHangById(String ma) {
        String sql = "SELECT id,ma, ho_ten, gioi_tinh, sdt, dia_chi, email, ngay_sinh, cap_bac, thoi_gian_tao, thoi_gian_sua FROM khach_hang WHERE ma = ? and is_delete = 0";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, ma);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                KhachHangHung kh = new KhachHangHung();
                kh.setId(rs.getInt("id"));
                kh.setMa(rs.getString("ma"));
                kh.setHoTen(rs.getString("ho_ten"));
                kh.setGioiTinh(rs.getBoolean("gioi_tinh"));
                kh.setSdt(rs.getString("sdt"));
                kh.setDiaChi(rs.getString("dia_chi"));
                kh.setEmail(rs.getString("email"));
                kh.setNgaySinh(rs.getDate("ngay_sinh"));
                kh.setCapBac(rs.getInt("cap_bac"));
                kh.setThoiGianTao(rs.getDate("thoi_gian_tao"));
                kh.setThoiGianSua(rs.getDate("thoi_gian_sua"));
                return kh;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public KhachHangHung getKhachHangById2(Long id) {
        String sql = "SELECT id, ma, ho_ten, gioi_tinh, sdt, dia_chi, email, ngay_sinh, cap_bac, thoi_gian_tao, thoi_gian_sua FROM khach_hang WHERE id = ? and is_delete = 0";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                KhachHangHung kh = new KhachHangHung();
                kh.setId(rs.getInt("id"));
                kh.setMa(rs.getString("ma"));
                kh.setHoTen(rs.getString("ho_ten"));
                kh.setGioiTinh(rs.getBoolean("gioi_tinh"));
                kh.setSdt(rs.getString("sdt"));
                kh.setDiaChi(rs.getString("dia_chi"));
                kh.setEmail(rs.getString("email"));
                kh.setNgaySinh(rs.getDate("ngay_sinh"));
                kh.setCapBac(rs.getInt("cap_bac"));
                kh.setThoiGianTao(rs.getDate("thoi_gian_tao"));
                kh.setThoiGianSua(rs.getDate("thoi_gian_sua"));
                return kh;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    

    public boolean createKhachHang(KhachHangHung kh) {
        int check = 0;
        String sql = "INSERT INTO khach_hang (ma, ho_ten, gioi_tinh, sdt, dia_chi, email, ngay_sinh, cap_bac, thoi_gian_tao, thoi_gian_sua, is_delete) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, kh.getMa());
            ps.setString(2, kh.getHoTen());
            ps.setBoolean(3, kh.isGioiTinh());
            ps.setString(4, kh.getSdt());
            ps.setString(5, kh.getDiaChi());
            ps.setString(6, kh.getEmail());
            ps.setDate(7, kh.getNgaySinh());
            ps.setInt(8, kh.getCapBac());
            ps.setDate(9, kh.getThoiGianTao());
            ps.setDate(10, kh.getThoiGianSua());
            ps.setObject(11, 0);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return check > 0;
    }

    public boolean updateKhachHang(String ma, KhachHangHung kh) {
        int check = 0;
        String sql = "UPDATE khach_hang set ho_ten = ?, gioi_tinh = ?, sdt = ?, dia_chi = ?, email = ?, ngay_sinh = ?, cap_bac = ?,thoi_gian_sua = ?, is_delete = ? where ma = ? and is_delete = 0";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, kh.getHoTen());
            ps.setBoolean(2, kh.isGioiTinh());
            ps.setString(3, kh.getSdt());
            ps.setString(4, kh.getDiaChi());
            ps.setString(5, kh.getEmail());
            ps.setDate(6, kh.getNgaySinh());
            ps.setInt(7, kh.getCapBac());
            ps.setDate(8, kh.getThoiGianSua());
            ps.setObject(9, 0);
            ps.setString(10, ma);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return check > 0;
    }

    public boolean deleteKhachHangById(String ma) {
        int check = 0;
        String sql = "UPDATE khach_hang SET is_delete = 1 WHERE ma = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ma);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }
    
      

    public List<KhachHangHung> searchByNameOrPhoneOrCode(String keyword) {
        List<KhachHangHung> list = new ArrayList<>();
        String sql = "SELECT k.id, k.ma, k.ho_ten, k.gioi_tinh, k.sdt, k.dia_chi, k.email, k.ngay_sinh, k.cap_bac, k.thoi_gian_tao, k.thoi_gian_sua "
                + "FROM khach_hang AS k WHERE (k.ho_ten LIKE ? OR k.sdt LIKE ? OR k.ma LIKE ?) AND k.is_delete = 0";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            String keywordPattern = "%" + keyword + "%";
            ps.setString(1, keywordPattern);
            ps.setString(2, keywordPattern);
            ps.setString(3, keywordPattern);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHangHung kh = new KhachHangHung();
                kh.setId(rs.getInt("id"));
                kh.setMa(rs.getString("ma"));
                kh.setHoTen(rs.getString("ho_ten"));
                kh.setGioiTinh(rs.getBoolean("gioi_tinh"));
                kh.setSdt(rs.getString("sdt"));
                kh.setDiaChi(rs.getString("dia_chi"));
                kh.setEmail(rs.getString("email"));
                kh.setNgaySinh(rs.getDate("ngay_sinh"));
                kh.setCapBac(rs.getInt("cap_bac"));
                kh.setThoiGianTao(rs.getDate("thoi_gian_tao"));
                kh.setThoiGianSua(rs.getDate("thoi_gian_sua"));
                list.add(kh);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<KhachHangHung> boloc(String keyword, int gioiTinh, int capBac, int is_delete) {
        List<KhachHangHung> list = new ArrayList<>();

        String sql = "SELECT k.id, k.ma, k.ho_ten, k.gioi_tinh, k.sdt, k.dia_chi, k.email, k.ngay_sinh, k.cap_bac, k.thoi_gian_tao, k.thoi_gian_sua "
                + "FROM khach_hang AS k WHERE 1=1";

        List<Object> parameters = new ArrayList<>();

        if (is_delete != -1) {
            sql += " AND k.is_delete = ?";
            parameters.add(is_delete);
        }

        if (gioiTinh != 2) {
            sql += " AND k.gioi_tinh = ?";
            parameters.add(gioiTinh == 1); // Giả sử 1 là Nam, 0 là Nữ
        }

        if (capBac != 0) {
            sql += " AND k.cap_bac = ?";
            parameters.add(capBac);
        }

        if (!keyword.isEmpty()) {
            sql += " AND (k.ho_ten LIKE ? OR k.sdt LIKE ? OR k.ma LIKE ?)";
            String keywordPattern = "%" + keyword + "%";
            parameters.add(keywordPattern);
            parameters.add(keywordPattern);
            parameters.add(keywordPattern);
        }

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            for (int i = 0; i < parameters.size(); i++) {
                ps.setObject(i + 1, parameters.get(i));
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHangHung kh = new KhachHangHung();
                kh.setId(rs.getInt("id"));
                kh.setMa(rs.getString("ma"));
                kh.setHoTen(rs.getString("ho_ten"));
                kh.setGioiTinh(rs.getBoolean("gioi_tinh"));
                kh.setSdt(rs.getString("sdt"));
                kh.setDiaChi(rs.getString("dia_chi"));
                kh.setEmail(rs.getString("email"));
                kh.setNgaySinh(rs.getDate("ngay_sinh"));
                kh.setCapBac(rs.getInt("cap_bac"));
                kh.setThoiGianTao(rs.getDate("thoi_gian_tao"));
                kh.setThoiGianSua(rs.getDate("thoi_gian_sua"));
                list.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<KhachHangHung> searchByNameAndGioiTinh(String name, int gioiTinh) {
        List<KhachHangHung> list = new ArrayList<>();
        String sql = "SELECT id,ma, ho_ten, gioi_tinh, sdt, dia_chi, email, ngay_sinh, cap_bac, thoi_gian_tao, thoi_gian_sua "
                + "FROM khach_hang WHERE is_delete = 0 AND ho_ten LIKE ? and gioi_tinh = ?";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");
            ps.setInt(2, gioiTinh);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHangHung kh = new KhachHangHung();
                kh.setId(rs.getInt("id"));
                kh.setMa(rs.getString("ma"));
                kh.setHoTen(rs.getString("ho_ten"));
                kh.setGioiTinh(rs.getBoolean("gioi_tinh"));
                kh.setSdt(rs.getString("sdt"));
                kh.setDiaChi(rs.getString("dia_chi"));
                kh.setEmail(rs.getString("email"));
                kh.setNgaySinh(rs.getDate("ngay_sinh"));
                kh.setCapBac(rs.getInt("cap_bac"));
                kh.setThoiGianTao(rs.getDate("thoi_gian_tao"));
                kh.setThoiGianSua(rs.getDate("thoi_gian_sua"));
                list.add(kh);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<KhachHangHung> getAllKhachHangByGioiTinh(int gioiTinh) {
        List<KhachHangHung> list = new ArrayList<>();
        String sql = "SELECT id,ma, ho_ten, gioi_tinh, sdt, dia_chi, email, ngay_sinh, cap_bac, thoi_gian_tao,thoi_gian_sua "
                + "FROM khach_hang WHERE is_delete = 0 AND gioi_tinh = ?";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, gioiTinh);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHangHung kh = new KhachHangHung();
                kh.setId(rs.getInt("id"));
                kh.setMa(rs.getString("ma"));
                kh.setHoTen(rs.getString("ho_ten"));
                kh.setGioiTinh(rs.getBoolean("gioi_tinh"));
                kh.setSdt(rs.getString("sdt"));
                kh.setDiaChi(rs.getString("dia_chi"));
                kh.setEmail(rs.getString("email"));
                kh.setNgaySinh(rs.getDate("ngay_sinh"));
                kh.setCapBac(rs.getInt("cap_bac"));
                kh.setThoiGianTao(rs.getDate("thoi_gian_tao"));
                kh.setThoiGianSua(rs.getDate("thoi_gian_sua"));
                list.add(kh);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<KhachHangHung> getAllKhachHangByCapBac(int capbac) {
        List<KhachHangHung> list = new ArrayList<>();
        String sql = "SELECT id,ma, ho_ten,gioi_tinh, sdt, dia_chi, email, ngay_sinh, cap_bac, thoi_gian_tao, thoi_gian_sua "
                + "FROM khach_hang WHERE is_delete = 0 AND cap_bac = ?";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, capbac);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHangHung kh = new KhachHangHung();
                kh.setId(rs.getInt("id"));
                kh.setMa(rs.getString("ma"));
                kh.setHoTen(rs.getString("ho_ten"));
                kh.setGioiTinh(rs.getBoolean("gioi_tinh"));
                kh.setSdt(rs.getString("sdt"));
                kh.setDiaChi(rs.getString("dia_chi"));
                kh.setEmail(rs.getString("email"));
                kh.setNgaySinh(rs.getDate("ngay_sinh"));
                kh.setCapBac(rs.getInt("cap_bac"));
                kh.setThoiGianTao(rs.getDate("thoi_gian_tao"));
                kh.setThoiGianSua(rs.getDate("thoi_gian_sua"));
                list.add(kh);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<KhachHangHung> searchByNameKhachHangDaXoa(String name) {
        List<KhachHangHung> list = new ArrayList<>();
        String sql = "SELECT id,ma, ho_ten, gioi_tinh, sdt, dia_chi, email, ngay_sinh, cap_bac, thoi_gian_tao,thoi_gian_sua "
                + "FROM khach_hang WHERE is_delete = 1 AND ho_ten LIKE ?";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHangHung kh = new KhachHangHung();
                kh.setId(rs.getInt("id"));
                kh.setMa(rs.getString("ma"));
                kh.setHoTen(rs.getString("ho_ten"));
                kh.setGioiTinh(rs.getBoolean("gioi_tinh"));
                kh.setSdt(rs.getString("sdt"));
                kh.setDiaChi(rs.getString("dia_chi"));
                kh.setEmail(rs.getString("email"));
                kh.setNgaySinh(rs.getDate("ngay_sinh"));
                kh.setCapBac(rs.getInt("cap_bac"));
                kh.setThoiGianTao(rs.getDate("thoi_gian_tao"));
                kh.setThoiGianTao(rs.getDate("thoi_gian_sua"));
                list.add(kh);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateIsDeleteKhachHang(String ma) {
        int check = 0;
        String sql = "UPDATE khach_hang set is_delete = ? where ma = ? and is_delete = 1";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, 0);
            ps.setString(2, ma);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return check > 0;
    }

    public boolean updateAllIsDeleteKhachHang() {
        int check = 0;
        String sql = "UPDATE khach_hang set is_delete = ? where is_delete = 1";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, 0);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return check > 0;
    }

    public List<HoaDonKhachHang> getHoaDonByIdKhachHang(int idKhachHang) {
        List<HoaDonKhachHang> hoaDonList = new ArrayList<>();

        String sql = " SELECT \n"
                + "    hd.MaHoaDon, \n"
                + "    hd.ThoiGianTao, \n"
                + "    COUNT(hdsp.ID) AS TongSanPham,\n"
                + "    hd.TongTienHoaDon, \n"
                + "    nv.ten, \n"
                + "    kh.ho_ten, \n"
                + "    hd.SoTienDaGiam, \n"
                + "    hd.TongTienSauKhuyenMai, \n"
                + "    hd.TongTienPhaiTra, \n"
                + "    hd.GhiChu \n"
                + "FROM \n"
                + "    HoaDon hd \n"
                + "JOIN \n"
                + "    nhan_vien nv ON hd.IDNhanVien = nv.id \n"
                + "JOIN \n"
                + "    khach_hang kh ON kh.id = hd.IDKhachHang \n"
                + "LEFT JOIN \n"
                + "    HoaDon_SanPhamChiTiet hdsp ON hd.IDHoaDon = hdsp.IDHoaDon\n"
                + "WHERE \n"
                + "  kh.id = ?\n"
                + "GROUP BY \n"
                + "    hd.MaHoaDon, hd.ThoiGianTao, hd.TongTienHoaDon, nv.ten, kh.ho_ten, \n"
                + "	hd.SoTienDaGiam, hd.TongTienSauKhuyenMai, hd.TongTienPhaiTra, hd.GhiChu;";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idKhachHang);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                HoaDonKhachHang hoaDon = new HoaDonKhachHang(
                        rs.getString("MaHoaDon"),
                        rs.getDate("ThoiGianTao"),
                        rs.getInt("TongSanPham"),
                        rs.getDouble("TongTienHoaDon"),
                        rs.getString("ten"),
                        rs.getString("ho_ten"),
                        rs.getDouble("SoTienDaGiam"),
                        rs.getDouble("TongTienSauKhuyenMai"),
                        rs.getDouble("TongTienPhaiTra"),
                        rs.getString("GhiChu")
                );
                hoaDonList.add(hoaDon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return hoaDonList;
    }

    public List<HoaDonKhachHang> getHoaDonByIdKhachHangAndMaHoaDon(int idKhachHang, String maHd) {
        List<HoaDonKhachHang> hoaDonList = new ArrayList<>();

        String sql = "  SELECT \n"
                + "       hd.MaHoaDon, \n"
                + "       hd.ThoiGianTao, \n"
                + "       COUNT(hdsp.ID) AS TongSanPham, \n"
                + "        hd.TongTienHoaDon, \n"
                + "        nv.ten, \n"
                + "      kh.ho_ten, \n"
                + "       hd.SoTienDaGiam, \n"
                + "       hd.TongTienSauKhuyenMai, \n"
                + "        hd.TongTienPhaiTra, \n"
                + "       hd.GhiChu \n"
                + "      FROM \n"
                + "    HoaDon hd \n"
                + "       JOIN \n"
                + "       nhan_vien nv ON hd.IDNhanVien = nv.id \n"
                + "       JOIN \n"
                + "        khach_hang kh ON kh.id = hd.IDKhachHang \n"
                + "        LEFT JOIN \n"
                + "       HoaDon_SanPhamChiTiet hdsp ON hd.IDHoaDon = hdsp.IDHoaDon\n"
                + "      WHERE \n"
                + "        kh.id = ? AND hd.MaHoaDon LIKE ? \n"
                + "        GROUP BY \n"
                + "        hd.MaHoaDon, hd.ThoiGianTao, hd.TongTienHoaDon, nv.ten, kh.ho_ten, \n"
                + "        hd.SoTienDaGiam, hd.TongTienSauKhuyenMai, hd.TongTienPhaiTra, hd.GhiChu;";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idKhachHang);
            ps.setString(2, "%" + maHd + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                HoaDonKhachHang hoaDon = new HoaDonKhachHang(
                        rs.getString("MaHoaDon"),
                        rs.getDate("ThoiGianTao"),
                        rs.getInt("TongSanPham"),
                        rs.getDouble("TongTienHoaDon"),
                        rs.getString("ten"),
                        rs.getString("ho_ten"),
                        rs.getDouble("SoTienDaGiam"),
                        rs.getDouble("TongTienSauKhuyenMai"),
                        rs.getDouble("TongTienPhaiTra"),
                        rs.getString("GhiChu")
                );
                hoaDonList.add(hoaDon);
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return hoaDonList;
    }

    public int getTongSoTienDaTra(int idKhachHang) {
        int tongSoTienDaTra = 0;

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement("SELECT SUM(TongTienHoaDon) FROM HoaDon WHERE IDKhachHang = ?")) {
            ps.setInt(1, idKhachHang);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                tongSoTienDaTra = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tongSoTienDaTra;
    }

    public void updateCapBacupdateCapBacTheoTien(int idKhachHang) {
        int tongSoTienDaTra = getTongSoTienDaTra(idKhachHang);

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement("UPDATE khach_hang SET cap_bac = ? WHERE id = ?")) {
            int newCapBac = 1;

            if (tongSoTienDaTra >= 10000000) {
                newCapBac = 2;
            }

            if (tongSoTienDaTra >= 40000000) {
                newCapBac = 3;
            }

            ps.setInt(1, newCapBac);
            ps.setInt(2, idKhachHang);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<SanPhamKhachHang> getSanPhamKhachHangTuHoaDon(String maKhachhang) {
        List<SanPhamKhachHang> sanPhamList = new ArrayList<>();
        String sql = "SELECT sp.maSanPham, sp.TenSanPham, sp.MoTaSanPham, tl.TenTheLoai "
                + "FROM HoaDon hd "
                + "JOIN HoaDon_SanPhamChiTiet hdspct ON hd.IDHoaDon = hdspct.IDHoaDon "
                + "JOIN SanPhamChiTiet spct ON hdspct.IDSanPhamChiTiet = spct.IDSanPhamChiTiet "
                + "JOIN SanPham sp ON sp.IDSanPham = spct.IDSanPham "
                + "JOIN TheLoai tl ON sp.IDTheLoai = tl.IDTheLoai "
                + "WHERE hd.MaHoaDon  = ? "
                + "ORDER BY sp.maSanPham ASC;";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maKhachhang);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SanPhamKhachHang sanPhamKhachHang = new SanPhamKhachHang();
                sanPhamKhachHang.setMaSanPham(rs.getString("maSanPham"));
                sanPhamKhachHang.setTenSanPham(rs.getString("TenSanPham"));
                sanPhamKhachHang.setMoTa(rs.getString("MoTaSanPham"));
                sanPhamKhachHang.setTheLoai(rs.getString("TenTheLoai"));
                sanPhamList.add(sanPhamKhachHang);
            }
            return sanPhamList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean CreateLihSuThanhToan(String maHoaDon, Long idKhachHang, Date thoiGianTao, Float tongTien, int capBac) {
        int check = 0;
        String sql = "INSERT INTO LichSuGiaoDich (MaHoaDon, IDKhachHang, ThoiGianTao, TongTien, CapBacKhachHang) VALUES (?, ?, ?,?,?)";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maHoaDon);
            ps.setLong(2, idKhachHang);
            ps.setDate(3, (java.sql.Date) thoiGianTao);
            ps.setFloat(4, tongTien);
            ps.setInt(5, capBac);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return check > 0;
    }

    public static void main(String[] args) {
        KhachHangRepository hangRepository = new KhachHangRepository();
        KhachHangHung hangHung = hangRepository.getKhachHangById2(15L);
        System.out.println(hangHung.toString());
    }

}
