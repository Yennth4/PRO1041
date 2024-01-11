/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.theloai.repository;

import com.poly.database.DBConnect;
import com.poly.form.theloai.entity.TheLoai;
import com.poly.form.theloai.entity.TheLoaiDTO;
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
public class TheLoaiRepository {

    private Connection cn;

    public TheLoaiRepository() {
        cn = DBConnect.getConnection();
    }

    public List<TheLoaiDTO> getAll() {
        List<TheLoaiDTO> list = new ArrayList<>();
        String query = "SELECT t.IDTheLoai, t.MaTheLoai,t.TenTheLoai,t.MoTaTheLoai,t.TrangThai, t.ThoiGianTao, t.ThoiGianSua, COUNT(s.IDSanPham),COUNT(ct.IDSanPhamChiTiet), SUM(ct.SoLuong) "
                + "FROM TheLoai t "
                + "LEFT JOIN SanPham s ON t.IDTheLoai=s.IDTheLoai "
                + "LEFT JOIN SanPhamChiTiet ct ON s.IDSanPham=ct.IDSanPham "
                + "GROUP BY t.IDTheLoai, t.MaTheLoai,t.TenTheLoai,t.MoTaTheLoai,t.TrangThai, t.ThoiGianTao, t.ThoiGianSua "
                + "ORDER BY t.TrangThai DESC ";

        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    TheLoaiDTO theLoai = new TheLoaiDTO(
                            rs.getLong(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getInt(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getInt(8),
                            rs.getInt(9),
                            rs.getInt(10)
                    );

                    list.add(theLoai);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public boolean isExistMa(String str) {
        String query = "SELECT MaTheLoai FROM TheLoai WHERE MaTheLoai = ? ";
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

    public boolean isExistTen(String str) {
        String query = "SELECT TenTheLoai FROM TheLoai WHERE TenTheLoai = ? ";
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

    public void insertTheLoai(TheLoai theLoai) {
        String query = "INSERT INTO TheLoai(MaTheLoai,TenTheLoai,MoTaTheLoai,TrangThai) "
                + "VALUES (?,?,?,?)";
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(query);
                ps.setString(1, theLoai.getMaTheLoai());
                ps.setString(2, theLoai.getTenTheLoai());
                ps.setString(3, theLoai.getMoTaTheLoai());
                ps.setInt(4, theLoai.getTrangThai());
                ps.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateTheLoai(TheLoai theLoai) {
        String query = "UPDATE TheLoai SET TenTheLoai = ?, "
                + "MoTaTheLoai = ?, TrangThai = ?, ThoiGianSua = GETDATE() WHERE IDTheLoai = ? ";
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(query);

                ps.setString(1, theLoai.getTenTheLoai());
                ps.setString(2, theLoai.getMoTaTheLoai());
                ps.setInt(3, theLoai.getTrangThai());
                ps.setLong(4, theLoai.getIdTheLoai());
                ps.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteTheLoai(Long id) {
        String query = "DELETE FROM TheLoai WHERE IDTheLoai = ? ";
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
        String query = "UPDATE TheLoai SET "
                + "TrangThai = ? WHERE IDTheLoai = ? ";
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

    public List<TheLoaiDTO> search(String keyword, int cbxSanPhamIndexType, Integer soSanPham, 
            int cbxBTSPType, Integer soBTSP, int cbxSoLuongCai, Integer soLuongCai, 
            int cbxTrangThai, int cbxThoiGianSearchType, String dateFirstFormat, String timeFirst, 
            String dateSecondFormat, String timeSecond) {
        List<TheLoaiDTO> list = new ArrayList<>();
        if (cn != null) {
            try {
                StringBuilder query = new StringBuilder(
                        "SELECT t.IDTheLoai, t.MaTheLoai,t.TenTheLoai,t.MoTaTheLoai,t.TrangThai, t.ThoiGianTao, t.ThoiGianSua, COUNT(s.IDSanPham),COUNT(ct.IDSanPhamChiTiet), SUM(ct.SoLuong) "
                        + "FROM TheLoai t "
                        + "LEFT JOIN SanPham s ON t.IDTheLoai=s.IDTheLoai "
                        + "LEFT JOIN SanPhamChiTiet ct ON s.IDSanPham=ct.IDSanPham "
                        + "GROUP BY t.IDTheLoai, t.MaTheLoai,t.TenTheLoai,t.MoTaTheLoai,t.TrangThai, t.ThoiGianTao, t.ThoiGianSua "
                );
                if (cbxTrangThai == 0) {
                    query.append(" HAVING t.TrangThai = 1 ");
                }
                if (cbxTrangThai == 1) {
                    query.append(" HAVING t.TrangThai = 0 ");
                }
                if (soSanPham != null) {
                    if (cbxSanPhamIndexType == 0) {
                        query.append("AND COUNT(s.IDSanPham) >= ").append(soSanPham + "");
                    } else if (cbxSanPhamIndexType == 1) {
                        query.append("AND COUNT(s.IDSanPham) <= ").append(soSanPham + "");
                    }
                }
                if (soBTSP != null) {
                    if (cbxBTSPType == 0) {
                        query.append(" AND COUNT(ct.IDSanPhamChiTiet) >= ").append(soBTSP + "");
                    } else if (cbxBTSPType == 1) {
                        query.append(" AND COUNT(ct.IDSanPhamChiTiet) <= ").append(soBTSP + "");
                    }
                }
                if (soLuongCai != null) {
                    if (cbxSoLuongCai == 0) {
                        query.append(" AND sum(ct.SoLuong) >= ").append(soLuongCai + "");
                    } else if (cbxSoLuongCai == 1) {
                        query.append(" AND (sum(ct.SoLuong) <= ").append(soLuongCai + "")
                                .append(" OR sum(ct.SoLuong) IS NULL) ");
                    }
                }
                if (keyword != null) {
                    query.append(" AND ")
                            .append(renderStringSearchSQL("t.TenTheLoai", "t.MaTheLoai", keyword));
                }

                // CbxThoiGianSearchType
                query.append(" AND ");
                if (cbxThoiGianSearchType == 0) {
                    query.append(" t.ThoiGianTao BETWEEN ");
                } else if (cbxThoiGianSearchType == 1) {
                    query.append(" t.ThoiGianSua BETWEEN ");
                }
                // Datetime first
                if (dateFirstFormat != null) {
                    query.append(" \'" + dateFirstFormat + " ");
                    if (timeFirst != null) {
                        query.append(" " + timeFirst + " ");
                    }
                    query.append("\' ");
                } else {
                    query.append(" \'" + "2000-01-01" + "\' ");
                }

                query.append(" AND ");

                // Date time second
                if (dateSecondFormat != null) {
                    query.append(" \'" + dateSecondFormat + " ");
                    if (timeSecond != null) {
                        query.append(" " + timeSecond + " ");
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
                    TheLoaiDTO theLoai = new TheLoaiDTO(
                            rs.getLong(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getInt(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getInt(8),
                            rs.getInt(9),
                            rs.getInt(10)
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
