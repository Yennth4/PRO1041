/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.hoadon.repository;

import com.poly.database.DBConnect;
import com.poly.form.hoadon.entity.BienTheSearch;
import com.poly.form.hoadon.entity.GioHang;
import com.poly.form.hoadon.entity.HoaDon;
import com.poly.form.hoadon.entity.HoaDonDTO;
import com.poly.form.hoadon.entity.KhachHangSearch;
import com.poly.form.hoadon.entity.VoucherSearch;
import com.poly.form.voucher.service.VoucherService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static com.poly.util.ph31848.Validate.renderStringSearchSQL;

/**
 *
 * @author longnvph31848
 */
public class HoaDonRepository {

    private Connection cn;

    public HoaDonRepository() {
        cn = DBConnect.getConnection();
    }

    public List<HoaDonDTO> getAll() {
        List<HoaDonDTO> list = new ArrayList<>();
        String query = "SELECT hd.IDHoaDon, hd.GhiChu, hd.TongTienHoaDon, \n"
                + "hd.TongTienSauKhuyenMai, nv.ten, kh.ho_ten, \n"
                + "hd.SoTienDaGiam, hd.TongTienPhaiTra, hd.MaHoaDon, \n"
                + "hd.TrangThai, hd.TienMatKhachTra, hd.HinhThucThanhToan, hd.HinhThuc,\n"
                + "(SELECT COUNT(id) FROM HoaDon_SanPhamChiTiet WHERE IDHoaDon = hd.IDHoaDon) AS TongSanPham, kh.sdt, kh.ho_ten\n"
                + "FROM HoaDon hd\n"
                + "LEFT JOIN nhan_vien nv ON hd.IDNhanVien=nv.id\n"
                + "LEFT JOIN khach_hang kh ON hd.IDKhachHang=kh.id\n"
                + "WHERE hd.THoiGianTao > CONVERT(DATE, GETDATE())\n";
        try {
            PreparedStatement ps = cn.prepareStatement(query);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonDTO hd = new HoaDonDTO(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getFloat(3),
                        rs.getFloat(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getFloat(7),
                        rs.getFloat(8),
                        rs.getString(9),
                        rs.getInt(10),
                        rs.getFloat(11),
                        rs.getInt(12),
                        rs.getInt(13),
                        rs.getInt(14),
                        rs.getString(15),
                        rs.getString(16)
                );
                list.add(hd);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HoaDonDTO> getAllSearch(Integer hinhThucMua, Integer trangThai) {
        List<HoaDonDTO> list = new ArrayList<>();
        StringBuilder strQuery = new StringBuilder(
                "SELECT hd.IDHoaDon, hd.GhiChu, hd.TongTienHoaDon, \n"
                + "hd.TongTienSauKhuyenMai, nv.ten, kh.ho_ten, \n"
                + "hd.SoTienDaGiam, hd.TongTienPhaiTra, hd.MaHoaDon, \n"
                + "hd.TrangThai, hd.TienMatKhachTra, hd.HinhThucThanhToan, hd.HinhThuc,\n"
                + "(SELECT COUNT(id) FROM HoaDon_SanPhamChiTiet WHERE IDHoaDon = hd.IDHoaDon) AS TongSanPham, kh.sdt, kh.ho_ten\n"
                + "FROM HoaDon hd\n"
                + "LEFT JOIN nhan_vien nv ON hd.IDNhanVien=nv.id\n"
                + "LEFT JOIN khach_hang kh ON hd.IDKhachHang=kh.id WHERE hd.THoiGianTao > CONVERT(DATE, GETDATE())\n"
        );

        if (hinhThucMua != null) {
            strQuery.append(" AND hd.HinhThuc = " + hinhThucMua);
        }
        if (trangThai != null) {
            strQuery.append(" AND hd.TrangThai = " + trangThai);
        }
        String query = strQuery.toString();
        System.out.println("\n\n" + query);
        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonDTO hd = new HoaDonDTO(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getFloat(3),
                        rs.getFloat(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getFloat(7),
                        rs.getFloat(8),
                        rs.getString(9),
                        rs.getInt(10),
                        rs.getFloat(11),
                        rs.getInt(12),
                        rs.getInt(13),
                        rs.getInt(14),
                        rs.getString(15),
                        rs.getString(16));
                list.add(hd);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<GioHang> getAllBienTheByIdHoaDon(Long id, Long time) {
        List<GioHang> list = new ArrayList<>();
        String query = "SELECT hdct.ID, spct.IDSanPhamChiTiet, s.MaSanPham, s.TenSanPham, m.TenMau, hdct.SoLuong, spct.GiaBan,\n"
                + "COALESCE(hdct.SoLuong*spct.GiaBan*SUM(km.phan_tram_giam_gia)/100 , 0) AS KhuyenMai,\n"
                + "hdct.SoLuong*spct.GiaBan-COALESCE(hdct.SoLuong*spct.GiaBan*SUM(km.phan_tram_giam_gia)/100,0) AS ThanhTien\n"
                + "FROM HoaDon_SanPhamChiTiet hdct\n"
                + "LEFT JOIN SanPhamChiTiet spct ON hdct.IDSanPhamChiTiet=spct.IDSanPhamChiTiet\n"
                + "LEFT JOIN Mau m ON spct.IDMau=m.IDMau\n"
                + "LEFT JOIN SanPham s ON spct.IDSanPham=s.IDSanPham\n"
                + "LEFT JOIN sanphamchitiet_khuyenmai spkm ON spct.IDSanPhamChiTiet=spkm.id_san_pham_chi_tiet\n"
                + "LEFT JOIN khuyen_mai km ON spkm.id_khuyen_mai=km.id\n"
                + "WHERE hdct.IDHoaDon = ? AND (spkm.id_san_pham_chi_tiet IS NULL OR ? BETWEEN km.thoi_gian_bat_dau AND km.thoi_gian_ket_thuc)\n"
                + "GROUP BY s.MaSanPham, s.TenSanPham, m.TenMau, hdct.SoLuong, spct.GiaBan,spct.IDSanPhamChiTiet, hdct.ID ";
        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setLong(1, id);
            ps.setLong(2, time);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                GioHang gioHang = new GioHang(
                        rs.getLong(1),
                        rs.getLong(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getFloat(7),
                        rs.getFloat(8),
                        rs.getFloat(9));
                list.add(gioHang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void insertHoaDon(HoaDon hd, Long IDkh) {
        String query = "INSERT INTO HoaDon(IDNhanVien,MaHoaDon,TrangThai, IDKhachHang, HinhThuc, HinhThucThanhToan, TienMatKhachTra,TongTienPhaiTra,SoTienDaGiam,TongTienSauKhuyenMai,TongTienHoaDon) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setLong(1, hd.getIdNhanVien());
            ps.setString(2, hd.getMaHoaDon());
            ps.setInt(3, 0);
            ps.setLong(4, IDkh);
            ps.setInt(5, 1);
            ps.setInt(6, 0);
            ps.setFloat(7, 0f);
            ps.setFloat(8, 0f);
            ps.setFloat(9, 0f);
            ps.setFloat(10, 0f);
            ps.setFloat(11, 0f);

            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateHoaDon(HoaDon hd) {
        String query = "UPDATE HoaDon SET GhiChu = ?, TongTienHoaDon = ?, "
                + "TongTienSauKhuyenMai = ?, IDKhachHang = ?, SoTienDaGiam = ?, "
                + "TongTienPhaiTra = ?, ThoiGianSua = GETDATE(), TrangThai = ?, "
                + "TienMatKhachTra = ?, HinhThucThanhToan = ?, HinhThuc = ? "
                + "WHERE IDHoaDon = ?";
        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setString(1, hd.getGhiChu());
            ps.setFloat(2, hd.getTongTienHoaDon());
            ps.setFloat(3, hd.getTongTienSauKhuyenMai());
            ps.setLong(4, hd.getIdKhachHang());
            ps.setFloat(5, hd.getSoTienDaGiam());
            ps.setFloat(6, hd.getTongTienPhaiTra());
            ps.setInt(7, hd.getTrangThai());
            ps.setFloat(8, hd.getTienMatKhachTra());
            ps.setInt(9, hd.getHinhThucThanhToan());
            ps.setInt(10, hd.getHinhThucMua());
            ps.setLong(11, hd.getIdHoaDon());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isExistMa(String str) {
        String query = "SELECT MaHoaDon FROM HoaDon WHERE MaHoaDon = ? ";
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(query);
                ps.setString(1, str);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
        return false;
    }

    public Long getIDBienTheByMa(String ma) {
        String query = "SELECT IDSanPhamChiTiet FROM SanPhamChiTiet WHERE Ma = ?";
        System.out.println(query);
        System.out.println(ma);

        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setString(1, ma);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getLong(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<BienTheSearch> searchSanPham(String keyword, String mau) {
        List<BienTheSearch> list = new ArrayList<>();
        StringBuilder queryString = new StringBuilder(
                "SELECT spct.IDSanPhamChiTiet ,s.MaSanPham, s.TenSanPham, m.TenMau, spct.SoLuong, spct.GiaBan, SUM(km.phan_tram_giam_gia) AS KhuyenMai\n"
                + "	FROM SanPhamChiTiet spct \n"
                + "	LEFT JOIN SanPham s ON spct.IDSanPham=s.IDSanPham\n"
                + "	LEFT JOIN Mau m ON spct.IDMau=m.IDMau\n"
                + "	LEFT JOIN sanphamchitiet_khuyenmai spkm ON spct.IDSanPhamChiTiet=spkm.id_san_pham_chi_tiet\n"
                + "	LEFT JOIN khuyen_mai km ON spkm.id_khuyen_mai=km.id\n"
                + "	GROUP BY s.MaSanPham, s.TenSanPham, m.TenMau, spct.SoLuong, spct.GiaBan,spct.IDSanPhamChiTiet\n"
                + "	Having 1=1"
        );

        if (mau != null) {
            queryString.append(" AND m.TenMau = N\'" + mau + "\' ");
        }
        if (keyword != null) {
            queryString.append(" AND " + renderStringSearchSQL("s.MaSanPham", "s.TenSanPham", keyword));
        }

        String query = queryString.toString();
        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BienTheSearch bt = new BienTheSearch(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getFloat(6),
                        rs.getInt(7));
                list.add(bt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<KhachHangSearch> searchKhachHang(String key) {
        List<KhachHangSearch> list = new ArrayList<>();
        StringBuilder queryString = new StringBuilder(
                "SELECT id, ho_ten, sdt, email, dia_chi FROM khach_hang WHERE 1=1 AND is_delete = 0 "
        );

        if (key != null) {
            queryString.append(" AND " + renderStringSearchSQL("ho_ten", "sdt", key));
        }

        String query = queryString.toString();
        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHangSearch bt = new KhachHangSearch(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5));
                list.add(bt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public KhachHangSearch findByID(Long id) {
        String query = "SELECT id, ho_ten, sdt, email, dia_chi FROM khach_hang WHERE id = ?";

        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new KhachHangSearch(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insertKhachHangSearch(KhachHangSearch kh) {
        String query = "INSERT INTO khach_hang(ho_ten, sdt, email, dia_chi, cap_bac, is_delete, thoi_gian_tao, thoi_gian_sua) VALUES(?,?,?,?,?,?,GETDATE(), GETDATE())";
        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setString(1, kh.getHoTen());
            ps.setString(2, kh.getSdt());
            ps.setString(3, kh.getEmail());
            ps.setString(4, kh.getDiaChi());
            ps.setInt(5, 1);
            ps.setInt(6, 0);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<KhachHangSearch> findKhachHangSearchBySDTEmail(String sdt, String email) {
        String query = "SELECT id, ho_ten, sdt, email, dia_chi FROM khach_hang WHERE sdt = ? AND email = ?";
        List<KhachHangSearch> list = new ArrayList<>();
        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setString(1, sdt);
            ps.setString(2, email);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHangSearch kh = new KhachHangSearch(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5));
                list.add(kh);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Float getTongTienHoaDonById(Long id) {
        String query = "SELECT (SELECT SUM(GiaTien*SoLuong) FROM HoaDon_SanPhamChiTiet WHERE IDHoaDon = hd.IDHoaDon) AS TongTien\n"
                + "FROM HoaDon hd\n"
                + "LEFT JOIN nhan_vien nv ON hd.IDNhanVien=nv.id\n"
                + "LEFT JOIN khach_hang kh ON hd.IDKhachHang=kh.id\n"
                + "WHERE 1 = 1\n"
                + "AND hd.IDHoaDon = ?";

        Float result = 0f;
        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getFloat(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = null;
        }

        return result;
    }

    public Float getTienGiamHoaDonById(Long id, Long time) {
        String query = "SELECT (SELECT COALESCE(SUM((hdct.GiaTien*hdct.SoLuong*km.phan_tram_giam_gia)/100),0) \n"
                + "		FROM HoaDon_SanPhamChiTiet hdct\n"
                + "		LEFT JOIN sanphamchitiet_khuyenmai kmct ON hdct.IDSanPhamChiTiet=kmct.id_san_pham_chi_tiet\n"
                + "		LEFT JOIN khuyen_mai km ON kmct.id_khuyen_mai=km.id\n"
                + "		WHERE hdct.IDHoaDon = hd.IDHoaDon\n"
                + "		AND ? BETWEEN km.thoi_gian_bat_dau AND km.thoi_gian_ket_thuc) AS SoTienGiam\n"
                + "FROM HoaDon hd\n"
                + "LEFT JOIN nhan_vien nv ON hd.IDNhanVien=nv.id\n"
                + "LEFT JOIN khach_hang kh ON hd.IDKhachHang=kh.id\n"
                + "WHERE 1 = 1\n"
                + "AND hd.IDHoaDon = ?";
        Float result = 0f;
        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setLong(1, time);
            ps.setLong(2, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getFloat(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = null;
        }

        return result;
    }

    public Float getTienThanhToanHoaDonById(Long id, Long time) {
        String query = "SELECT (SELECT SUM(GiaTien*SoLuong) FROM HoaDon_SanPhamChiTiet WHERE IDHoaDon = hd.IDHoaDon)\n"
                + "-	(\n"
                + "		SELECT COALESCE(SUM((hdct.GiaTien*hdct.SoLuong*km.phan_tram_giam_gia)/100),0) \n"
                + "		FROM HoaDon_SanPhamChiTiet hdct\n"
                + "		JOIN sanphamchitiet_khuyenmai kmct ON hdct.IDSanPhamChiTiet=kmct.id_san_pham_chi_tiet\n"
                + "		JOIN khuyen_mai km ON kmct.id_khuyen_mai=km.id\n"
                + "		WHERE hdct.IDHoaDon = hd.IDHoaDon\n"
                + "		AND ? BETWEEN km.thoi_gian_bat_dau AND km.thoi_gian_ket_thuc\n"
                + "	) AS TongTienThanhToan\n"
                + "FROM HoaDon hd\n"
                + "LEFT JOIN nhan_vien nv ON hd.IDNhanVien=nv.id\n"
                + "LEFT JOIN khach_hang kh ON hd.IDKhachHang=kh.id\n"
                + "WHERE 1 = 1\n"
                + "AND hd.IDHoaDon = ?";
        Float result = 0f;
        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setLong(1, time);
            ps.setLong(2, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getFloat(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = null;
        }

        return result;
    }
// bắt đầu làm thêm sản phẩm vào hóa đơn

    public void insertBienTheToHoaDon(Long idHoaDonLong, Long idBienThe, Float giaTien, Integer soLuong) {
        String query = "INSERT INTO HoaDon_SanPhamChiTiet(IDHoaDon, IDSanPhamChiTiet, GiaTien, SoLuong) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setLong(1, idHoaDonLong);
            ps.setLong(2, idBienThe);
            ps.setFloat(3, giaTien);
            ps.setInt(4, soLuong);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateBienTheToHoaDon(Long id, Integer soLuong) {
        String query = "UPDATE HoaDon_SanPhamChiTiet SET SoLuong = ? WHERE ID = ?";
        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setInt(1, soLuong);
            ps.setLong(2, id);
            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateSoLuongBienThe(Long id, Integer soLuong) {
        String query = "UPDATE SanPhamChiTiet SET SoLuong = ? WHERE IDSanPhamChiTiet = ?";
        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setInt(1, soLuong);
            ps.setLong(2, id);
            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Long isExistBienTheHoaDon(Long idHoaDon, Long idBienThe) {
        String query = "SELECT ID FROM HoaDon_SanPhamChiTiet WHERE IDHoaDon = ? AND IDSanPhamChiTiet = ?";
        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setLong(1, idHoaDon);
            ps.setLong(2, idBienThe);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getLong(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer getSoLuongBienTheTrongGioHangById(Long id) {
        String query = "SELECT SoLuong FROM HoaDon_SanPhamChiTiet WHERE ID = ?";
        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer getSoLuongBienTheById(Long id) {
        String query = "SELECT SoLuong FROM SanPhamChiTiet WHERE IDSanPhamChiTiet = ?";
        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Float getGiaBanBienTheById(Long id) {
        String query = "SELECT GiaBan FROM SanPhamChiTiet WHERE IDSanPhamChiTiet = ?";
        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getFloat(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteBienTheInGioHang(Long idHD, Long idBT) {
        String query = "DELETE FROM HoaDon_SanPhamChiTiet WHERE IDHoaDon = ? AND IDSanPhamChiTiet = ?";
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(query);
                ps.setLong(1, idHD);
                ps.setLong(2, idBT);
                ps.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteListBienTheInGioHang(Long idHD) {
        String query = "DELETE FROM HoaDon_SanPhamChiTiet WHERE IDHoaDon = ?";
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(query);
                ps.setLong(1, idHD);
                ps.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public KhachHangSearch getKhachHangById(Long id) {

        String query = "SELECT id, ho_ten, sdt, email, dia_chi FROM khach_hang WHERE id = ?";

        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHangSearch kh = new KhachHangSearch(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5));
                return kh;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    //voucher
    public VoucherSearch getVoucherIsBest(Float tongThanhToan) {
        String query = "SELECT TOP 1 id, ma_voucher, ten_voucher, loai_voucher, muc_giam_gia, muc_chi_tieu_toi_thieu\n"
                + "FROM voucher\n"
                + "WHERE muc_chi_tieu_toi_thieu < ?\n"
                + "AND trang_thai = 1\n"
                + "AND so_luong > 0\n"
                + "AND GETDATE() BETWEEN thoi_gian_bat_dau AND thoi_gian_ket_thuc\n"
                + "ORDER BY muc_chi_tieu_toi_thieu DESC";

        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(query);
                ps.setFloat(1, tongThanhToan);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    return new VoucherSearch(
                            rs.getLong(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(4),
                            rs.getInt(5),
                            rs.getInt(6),
                            rs.getInt(7));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void giamSoLuongVoucher(Integer soLuong, Long idVoucher) {
        String query = "UPDATE voucher SET so_luong = ? WHERE id = ?";
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(query);
                ps.setInt(1, soLuong);
                ps.setLong(2, idVoucher);
                ps.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void updateSoLuongBienTheAfterXoaGioHang(Long id, Integer soLuong) {
        String query = "UPDATE SanPhamChiTiet SET SoLuong =  SoLuong + ? WHERE IDSanPhamChiTiet = ?";
        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setInt(1, soLuong);
            ps.setLong(2, id);
            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
