/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.nhacungcap.repository;

import com.poly.database.DBConnect;
import com.poly.form.nhacungcap.entity.NhaCungCap;
import com.poly.form.nhacungcap.entity.NhaCungCapDTO;
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
public class NhaCungCapRepository {

    private Connection cn;

    public NhaCungCapRepository() {
        cn = DBConnect.getConnection();
    }

    public List<NhaCungCapDTO> getAll() {
        List<NhaCungCapDTO> list = new ArrayList<>();
        String query = "SELECT cc.IDNhaCungCap, cc.Ma, cc.TenNhaCungCap,cc.MoTaNhaCungCap, \n"
                + "cc.TrangThai, cc.SDTNhaCungCap, cc.EmailNhaCungCap, cc.HopDongThoaThuan, \n"
                + "cc.ThoiGianTao, cc.ThoiGianSua, COUNT(s.IDSanPham), \n"
                + "COUNT(ct.IDSanPhamChiTiet) AS SPCT, sum(ct.SoLuong) \n"
                + "FROM NhaCungCap cc\n"
                + "LEFT JOIN SanPham s ON cc.IDNhaCungCap=s.IDNhaCungCap\n"
                + "LEFT JOIN SanPhamChiTiet ct ON s.IDSanPham=ct.IDSanPham\n"
                + "GROUP BY cc.IDNhaCungCap, cc.Ma, cc.EmailNhaCungCap, cc.TenNhaCungCap,cc.MoTaNhaCungCap,\n"
                + "cc.TrangThai, cc.SDTNhaCungCap, cc.EmailNhaCungCap, \n"
                + "cc.HopDongThoaThuan, cc.ThoiGianTao, cc.ThoiGianSua\n";
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    NhaCungCapDTO ncc = new NhaCungCapDTO(
                            rs.getLong(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getInt(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getString(8),
                            rs.getString(9),
                            rs.getString(10),
                            rs.getInt(11),
                            rs.getInt(12),
                            rs.getInt(13)
                    );

                    list.add(ncc);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public boolean isExistTen(String str) {
        String query = "SELECT TenNhaCungCap FROM NhaCungCap WHERE TenNhaCungCap = ? ";
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

    public void insertNCC(NhaCungCap ncc) {
        String query = "INSERT INTO NhaCungCap(Ma, TenNhaCungCap,MoTaNhaCungCap,TrangThai,SDTNhaCungCap,EmailNhaCungCap,HopDongThoaThuan) "
                + "VALUES (?,?,?,?,?,?,?)";
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(query);
                ps.setString(1, ncc.getMaNCC());
                ps.setString(2, ncc.getTenNCC());
                ps.setString(3, ncc.getMoTaNCC());
                ps.setInt(4, ncc.getTrangThai());
                ps.setString(5, ncc.getSdtNCC());
                ps.setString(6, ncc.getEmailNCC());
                ps.setString(7, ncc.getHopDongThoaThuan());
                ps.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateNCC(NhaCungCap ncc) {
        String query = "UPDATE NhaCungCap SET TenNhaCungCap = ?, "
                + "MoTaNhaCungCap = ?, TrangThai = ?, SDTNhaCungCap = ?, EmailNhaCungCap = ?, "
                + "HopDongThoaThuan = ?, ThoiGianSua = GETDATE() WHERE IDNhaCungCap = ? ";
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(query);
                ps.setString(1, ncc.getTenNCC());
                ps.setString(2, ncc.getMoTaNCC());
                ps.setInt(3, ncc.getTrangThai());
                ps.setString(4, ncc.getSdtNCC());
                ps.setString(5, ncc.getEmailNCC());
                ps.setString(6, ncc.getHopDongThoaThuan());
                ps.setLong(7, ncc.getId());
                ps.execute();
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
    }

    public void deleteNCC(Long id) {
        String query = "DELETE FROM NhaCungCap WHERE IDNhaCungCap = ? ";
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
        String query = "UPDATE NhaCungCap SET "
                + "TrangThai = ? WHERE IDNhaCungCap = ? ";
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
    
    public boolean isExistMa(String str) {
        String query = "SELECT Ma FROM NhaCungCap WHERE Ma = ? ";
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

    public List<NhaCungCapDTO> search(String keyword, int cbxSanPhamIndexType, Integer soSanPham, int cbxBTSPType, Integer soBTSP, int cbxSoLuongCai, Integer soLuongCai, int cbxTrangThai, int cbxThoiGianSearchType, String dateFirstFormat, String timeFirst, String dateSecondFormat, String timeSecond) {
        List<NhaCungCapDTO> list = new ArrayList<>();
        if (cn != null) {
            try {
                StringBuilder query = new StringBuilder(
                        "SELECT cc.IDNhaCungCap, cc.Ma, cc.TenNhaCungCap,cc.MoTaNhaCungCap, \n"
                        + "cc.TrangThai, cc.SDTNhaCungCap, cc.EmailNhaCungCap, cc.HopDongThoaThuan, \n"
                        + "cc.ThoiGianTao, cc.ThoiGianSua, COUNT(s.IDSanPham) AS SP, \n"
                        + "COUNT(ct.IDSanPhamChiTiet) AS SPCT, sum(ct.SoLuong) AS cai\n"
                        + "FROM NhaCungCap cc\n"
                        + "LEFT JOIN SanPham s ON cc.IDNhaCungCap=s.IDNhaCungCap\n"
                        + "LEFT JOIN SanPhamChiTiet ct ON s.IDSanPham=ct.IDSanPham\n"
                        + "GROUP BY cc.IDNhaCungCap, cc.Ma, cc.TenNhaCungCap,cc.MoTaNhaCungCap,\n"
                        + "cc.TrangThai, cc.SDTNhaCungCap, cc.EmailNhaCungCap, \n"
                        + "cc.HopDongThoaThuan, cc.ThoiGianTao, cc.ThoiGianSua\n"
                );
                if (cbxTrangThai == 0) {
                    query.append(" HAVING cc.TrangThai = 1 ");
                }
                if (cbxTrangThai == 1) {
                    query.append(" HAVING cc.TrangThai = 0 ");
                }
                if (soSanPham != null) {
                    if (cbxSanPhamIndexType == 0) {
                        query.append(" AND COUNT(s.IDSanPham) >= ").append(soSanPham + "");
                    } else if (cbxSanPhamIndexType == 1) {
                        query.append(" AND COUNT(s.IDSanPham) <= ").append(soSanPham + "");
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
                            .append(renderStringSearchSQL("cc.Ma", "cc.TenNhaCungCap", "cc.SDTNhaCungCap", keyword));

                }
                
                // CbxThoiGianSearchType
                query.append(" AND ");
                if (cbxThoiGianSearchType == 0) {
                    query.append(" cc.ThoiGianTao BETWEEN ");
                } else if (cbxThoiGianSearchType == 1) {
                    query.append(" cc.ThoiGianSua BETWEEN ");
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
                //print query
                System.out.println(queryFinal);
                //print query
                PreparedStatement ps = cn.prepareStatement(queryFinal);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    NhaCungCapDTO theLoai = new NhaCungCapDTO(
                            rs.getLong(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getInt(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getString(8),
                            rs.getString(9),
                            rs.getString(10),
                            rs.getInt(11),
                            rs.getInt(12),
                            rs.getInt(13)
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
