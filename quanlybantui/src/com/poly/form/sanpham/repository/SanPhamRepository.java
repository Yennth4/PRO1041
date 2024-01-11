/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.sanpham.repository;

import com.poly.database.DBConnect;
import com.poly.form.sanpham.entity.SanPham;
import com.poly.form.sanpham.entity.SanPhamDTO;
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
public class SanPhamRepository {

    private Connection cn;

    public SanPhamRepository() {
        cn = DBConnect.getConnection();
    }

    public Long getIDSanPhamByMa(String ma) {
        String query = "SELECT IDSanPham FROM SanPham WHERE MaSanPham = ?";
        if (cn != null) {
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
        }
        return null;
    }

    public List<SanPhamDTO> getAll() {
        List<SanPhamDTO> list = new ArrayList<>();
        String query = "SELECT s.IDSanPham, s.MaSanPham, s.TenSanPham, s.MoTaSanPham,cc.TenNhaCungCap, "
                + "s.TrangThai, tl.TenTheLoai, nv.ten, s.ThoiGianTao, s.ThoiGianSua ,COUNT(ct.IDSanPhamChiTiet), SUM(ct.SoLuong), STRING_AGG(m.TenMau, ', ')\n"
                + "FROM SanPham s\n"
                + "LEFT JOIN SanPhamChiTiet ct ON s.IDSanPham=ct.IDSanPham\n"
                + "LEFT JOIN Mau m ON ct.IDMau=m.IDMau\n"
                + "LEFT JOIN NhaCungCap cc ON s.IDNhaCungCap=cc.IDNhaCungCap\n"
                + "LEFT JOIN  TheLoai tl ON s.IDTheLoai=tl.IDTheLoai\n"
                + "LEFT JOIN nhan_vien nv ON s.IDNhanVien=nv.id\n"
                + "GROUP BY s.IDSanPham, s.MaSanPham, s.TenSanPham, s.MoTaSanPham,cc.TenNhaCungCap, s.TrangThai, "
                + "tl.TenTheLoai, nv.ten, s.ThoiGianTao, s.ThoiGianSua";
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    SanPhamDTO sanPham = new SanPhamDTO(
                            rs.getLong(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getInt(6),
                            rs.getString(7),
                            rs.getString(8),
                            rs.getString(9),
                            rs.getString(10),
                            rs.getInt(11),
                            rs.getInt(12),
                            rs.getString(13)
                    );
                    list.add(sanPham);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public boolean isExistMa(String str) {
        String query = "SELECT MaSanPham FROM SanPham WHERE MaSanPham = ? ";
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

    public void insertSanPham(SanPham sanPham) {
        String query = "INSERT INTO SanPham(MaSanPham,TenSanPham,MoTaSanPham,IDNhaCungCap,TrangThai,IDTheLoai,IDNhanVien) "
                + " VALUES (?,?,?,?,?,?,?)";
        System.out.println(sanPham);
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(query);
                System.out.println(sanPham.toString()); // printr
                ps.setString(1, sanPham.getMaSanPham());
                ps.setString(2, sanPham.getTenSanPham());
                ps.setString(3, sanPham.getMoTaSanPham());
                ps.setLong(4, sanPham.getIdNhaCungCap());
                ps.setInt(5, sanPham.getTrangThai());
                ps.setLong(6, sanPham.getIdTheLoai());
                ps.setLong(7, sanPham.getIdNhanVien());
                System.out.println(ps.toString());
                ps.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void updateSanPham(SanPham sanPham) {
        String query = "UPDATE SanPham SET TenSanPham = ?, "
                + " MoTaSanPham = ?, IDNhaCungCap = ?, TrangThai = ?, IDTheLoai = ?, IDNhanVien = ? "
                + " WHERE IDSanPham = ? ";
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(query);
                ps.setString(1, sanPham.getTenSanPham());
                ps.setString(2, sanPham.getMoTaSanPham());
                ps.setLong(3, sanPham.getIdNhaCungCap());
                ps.setInt(4, sanPham.getTrangThai());
                ps.setLong(5, sanPham.getIdTheLoai());
                ps.setLong(6, sanPham.getIdNhanVien());
                ps.setLong(7, sanPham.getIdSanPham());
                ps.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteSanPham(Long id) {
        String query = "DELETE FROM SanPham WHERE IDSanPham = ? ";
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(query);
                ps.setLong(1, id);
                ps.execute();
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
    }

    public void tatTrangThai(Long id) {
        String query = "UPDATE SanPham SET "
                + " TrangThai = ? WHERE IDSanPham = ? ";
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(query);
                ps.setInt(1, 0);
                ps.setLong(2, id);
                ps.execute();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<String> getImageSanPham(Long id) {
        List<String> list = new ArrayList<>();
        String query = " SELECT ct.MainImage\n"
                + " FROM SanPham s\n"
                + " JOIN SanPhamChiTiet ct ON s.IDSanPham=ct.IDSanPham\n"
                + " WHERE s.IDSanPham = ?";
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(query);
                ps.setLong(1, id);

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String path = rs.getString(1);
                    System.out.println("path: " + path);
                    if (!path.equals("")) {
                        list.add(path);
                    }
                    list.add(rs.getString(1));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public List<SanPhamDTO> search(String keyword, Long idTheLoai, Long idNCC,
            Long idMau, int cbxSearchSoLuongCai, Integer txtCbxSearchSoLuongCai,
            int cbxSearchBienThe, Integer txtCbxSearchBienThe, int cbxSearchDateType,
            String txtDateFirst, String txtTimeFirst, String txtDateSecond,
            String txtTimeSecond, int cbxSearchTrangThai) {
        List<SanPhamDTO> list = new ArrayList<>();
        if (cn != null) {
            try {
                StringBuilder query = new StringBuilder(
                        "SELECT s.IDSanPham, s.MaSanPham, s.TenSanPham, s.MoTaSanPham,cc.TenNhaCungCap, "
                        + "s.TrangThai, tl.TenTheLoai, nv.ten, s.ThoiGianTao, s.ThoiGianSua ,COUNT(ct.IDSanPhamChiTiet), SUM(ct.SoLuong), STRING_AGG(m.TenMau, ', ')\n"
                        + "FROM SanPham s\n"
                        + "LEFT JOIN SanPhamChiTiet ct ON s.IDSanPham=ct.IDSanPham\n"
                        + "LEFT JOIN Mau m ON ct.IDMau=m.IDMau\n"
                        + "LEFT JOIN NhaCungCap cc ON s.IDNhaCungCap=cc.IDNhaCungCap\n"
                        + "LEFT JOIN  TheLoai tl ON s.IDTheLoai=tl.IDTheLoai\n"
                        + "LEFT JOIN nhan_vien nv ON s.IDNhanVien=nv.id\n"
                        + "GROUP BY s.IDSanPham, s.MaSanPham, s.TenSanPham, s.MoTaSanPham,cc.TenNhaCungCap, s.TrangThai, "
                        + "tl.TenTheLoai, nv.ten, s.ThoiGianTao, s.ThoiGianSua, tl.IDTheLoai, cc.IDNhaCungCap "
                );
                // trạng thái
                if (cbxSearchTrangThai == 0) {
                    query.append(" HAVING s.TrangThai = 1 ");
                } else if (cbxSearchTrangThai == 1) {
                    query.append(" HAVING s.TrangThai = 0 ");
                }

                //thể loại
                if (idTheLoai != null) {
                    query.append(" AND tl.IDTheLoai = " + idTheLoai);
                }

                //nhà cung cấp
                if (idNCC != null) {
                    query.append(" AND cc.IDNhaCungCap = " + idNCC);
                }

                //màu
                if (idMau != null) {
                    query.append(" AND s.IDSanPham IN (SELECT IDSanPham FROM SanPhamChiTiet WHERE IDMau = " + idMau + ") ");
                }

                //tồn kho / số lượng cái
                if (txtCbxSearchSoLuongCai != null) {
                    if (cbxSearchSoLuongCai == 0) {
                        query.append(" AND SUM(ct.SoLuong) >= ").append(txtCbxSearchSoLuongCai + "");
                    } else if (cbxSearchSoLuongCai == 1) {
                        query.append(" AND SUM(ct.SoLuong) <= ").append(txtCbxSearchSoLuongCai + "");
                    }
                }

                //biến thể
                if (txtCbxSearchBienThe != null) {
                    if (cbxSearchBienThe == 0) {
                        query.append(" AND COUNT(ct.IDSanPhamChiTiet) >= ").append(txtCbxSearchBienThe + "");
                    } else if (cbxSearchBienThe == 1) {
                        query.append(" AND COUNT(ct.IDSanPhamChiTiet) <= ").append(txtCbxSearchBienThe + "");
                    }
                }

                // nhập từ search
                if (keyword != null) {
                    query.append(" AND ")
                            .append(renderStringSearchSQL("s.MaSanPham", "s.TenSanPham", keyword));

                }

                // CbxThoiGianSearchType
                query.append(" AND ");
                if (cbxSearchDateType == 0) {
                    query.append(" s.ThoiGianTao BETWEEN ");
                } else if (cbxSearchDateType == 1) {
                    query.append(" s.ThoiGianSua BETWEEN ");
                }
                // Datetime first
                if (txtDateFirst != null) {
                    query.append(" \'" + txtDateFirst + " ");
                    if (txtTimeFirst != null) {
                        query.append(" " + txtTimeFirst + " ");
                    }
                    query.append("\' ");
                } else {
                    query.append(" \'" + "2000-01-01" + "\' ");
                }

                query.append(" AND ");

                // Date time second
                if (txtDateSecond != null) {
                    query.append(" \'" + txtDateSecond + " ");
                    if (txtTimeSecond != null) {
                        query.append(" " + txtTimeSecond + " ");
                    }
                    query.append("\' ");
                } else {
                    query.append(" \'" + "2100-01-01" + "\' ");
                }

                String queryFinal = query.toString();
                System.out.println(queryFinal); //print query
                PreparedStatement ps = cn.prepareStatement(queryFinal);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    SanPhamDTO theLoai = new SanPhamDTO(
                            rs.getLong(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getInt(6),
                            rs.getString(7),
                            rs.getString(8),
                            rs.getString(9),
                            rs.getString(10),
                            rs.getInt(11),
                            rs.getInt(12),
                            rs.getString(13)
                    );

                    list.add(theLoai);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

}
