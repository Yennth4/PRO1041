/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.thuoctinh.repository;

import com.poly.database.DBConnect;
import com.poly.form.thuoctinh.entity.ThuocTinhMau;
import com.poly.form.thuoctinh.entity.ThuocTinhMauDTO;
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
public class ThuocTinhMauRepository {

    private Connection cn;

    public ThuocTinhMauRepository() {
        cn = DBConnect.getConnection();
    }

    public Long getIDMauByTen(String ten) {
        String query = "SELECT IDMau FROM Mau WHERE TenMau = ?";
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(query);
                ps.setString(1, ten);
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

    public List<ThuocTinhMauDTO> getAll() {
        List<ThuocTinhMauDTO> list = new ArrayList<>();
        String query = "SELECT m.IDMau,m.Ma, m.TenMau, m.MoTaMau, m.TrangThai,m.ThoiGianTao, m.ThoiGianSua, COUNT(DISTINCT s.IDSanPham), COUNT(ct.IDSanPhamChiTiet), SUM(ct.SoLuong)\n"
                + "FROM Mau m\n"
                + "LEFT JOIN SanPhamChiTiet ct ON m.IDMau=ct.IDMau\n"
                + "LEFT JOIN SanPham s ON ct.IDSanPham=s.IDSanPham\n"
                + "GROUP BY m.IDMau,m.Ma, m.TenMau, m.MoTaMau, m.TrangThai,m.ThoiGianTao, m.ThoiGianSua ";
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    ThuocTinhMauDTO mau = new ThuocTinhMauDTO(
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

                    list.add(mau);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public boolean isExistTen(String str) {
        String query = "SELECT TenMau FROM Mau WHERE TenMau = ? ";
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

    public boolean isExistMa(String str) {
        String query = "SELECT Ma FROM Mau WHERE Ma = ? ";
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

    public void insertMau(ThuocTinhMau mau) {
        String query = "INSERT INTO Mau(Ma, TenMau,MoTaMau,TrangThai) "
                + "VALUES (?,?,?,?)";
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(query);
                ps.setString(1, mau.getMaMau());
                ps.setString(2, mau.getTenMau());
                ps.setString(3, mau.getMoTaMau());
                ps.setString(4, mau.getTrangThai() + "");

                ps.execute();
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
    }

    public void updateMau(ThuocTinhMau mau) {
        String query = "UPDATE Mau SET TenMau = ?, MoTaMau = ?, "
                + "TrangThai = ?, ThoiGianSua = GETDATE() WHERE IDMau = ? ";
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(query);
                ps.setString(1, mau.getTenMau());
                ps.setString(2, mau.getMoTaMau());
                ps.setInt(3, mau.getTrangThai());
                ps.setLong(4, mau.getIDMau());
                ps.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteMau(Long id) {
        String query = "DELETE FROM Mau WHERE IDMau = ? ";
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
        String query = "UPDATE Mau SET "
                + "TrangThai = ? WHERE IDMau = ? ";
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

    public List<ThuocTinhMauDTO> search(String keyword, int cbxSanPhamIndexType, Integer soSanPham, int cbxBTSPType, Integer soBTSP, int cbxSoLuongCai, Integer soLuongCai, int cbxTrangThai, int cbxThoiGianSearchType, String dateFirstFormat, String timeFirst, String dateSecondFormat, String timeSecond) {
        List<ThuocTinhMauDTO> list = new ArrayList<>();
        if (cn != null) {
            try {
                StringBuilder query = new StringBuilder(
                        "SELECT m.IDMau,m.Ma, m.TenMau, m.MoTaMau, m.TrangThai,m.ThoiGianTao, m.ThoiGianSua, COUNT(DISTINCT s.IDSanPham), COUNT(ct.IDSanPhamChiTiet), SUM(ct.SoLuong)\n"
                        + "FROM Mau m\n"
                        + "LEFT JOIN SanPhamChiTiet ct ON m.IDMau=ct.IDMau\n"
                        + "LEFT JOIN SanPham s ON ct.IDSanPham=s.IDSanPham\n"
                        + "GROUP BY m.IDMau,m.Ma, m.TenMau, m.MoTaMau, m.TrangThai,m.ThoiGianTao, m.ThoiGianSua "
                );
                if (cbxTrangThai == 0) {
                    query.append(" HAVING m.TrangThai = 1 ");
                }
                if (cbxTrangThai == 1) {
                    query.append(" HAVING m.TrangThai = 0 ");
                }
                if (soSanPham != null) {
                    if (cbxSanPhamIndexType == 0) {
                        query.append("AND COUNT(DISTINCT s.IDSanPham) >= ").append(soSanPham + "");
                    } else if (cbxSanPhamIndexType == 1) {
                        query.append("AND COUNT(DISTINCT s.IDSanPham) <= ").append(soSanPham + "");
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
                        query.append(" AND SUM(ct.SoLuong) >= ").append(soLuongCai + "");
                    } else if (cbxSoLuongCai == 1) {
                        query.append(" AND (SUM(ct.SoLuong) <= ").append(soLuongCai + "")
                                .append(" OR SUM(ct.SoLuong) IS NULL) ");
                    }
                }
                if (keyword != null) {
                    query.append(" AND ")
                            .append(renderStringSearchSQL("m.TenMau", "m.Ma", keyword));

                }

                // CbxThoiGianSearchType
                query.append(" AND ");
                if (cbxThoiGianSearchType == 0) {
                    query.append(" m.ThoiGianTao BETWEEN ");
                } else if (cbxThoiGianSearchType == 1) {
                    query.append(" m.ThoiGianSua BETWEEN ");
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
                    ThuocTinhMauDTO mau = new ThuocTinhMauDTO(
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

                    list.add(mau);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

}
