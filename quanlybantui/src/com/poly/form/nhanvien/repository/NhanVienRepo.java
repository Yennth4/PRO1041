/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.nhanvien.repository;

import com.poly.database.DBConnect;
import com.poly.form.nhanvien.entity.Login;
import com.poly.form.nhanvien.entity.NhanVienEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.Optional;
import java.util.stream.Collectors;

public class NhanVienRepo {

    Connection con = null; // biên để kết nối
    PreparedStatement ps = null;
    String sql = null;
    ResultSet rs = null; // ket qua select

    public List<NhanVienEntity> getAllNhanVien() {
        sql = "SELECT id ,username, ten, email, so_dien_thoai, gioi_tinh, "
                + "trang_thai, ngay_sinh, dia_chi, ma_dinh_danh, thoi_gian_tao,"
                + " thoi_gian_sua FROM nhan_vien ORDER BY id DESC";
        List<NhanVienEntity> listNV = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVienEntity nv = new NhanVienEntity(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(10),
                        rs.getString(5),
                        rs.getDate(8),
                        rs.getString(4),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(7),
                        rs.getDate(11),
                        rs.getDate(12)
                );
                listNV.add(nv);
            }
            return listNV;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int insertNV(NhanVienEntity nv) {
        sql = "INSERT INTO nhan_vien(username, ten, email, so_dien_thoai, gioi_tinh,"
                + " trang_thai, ngay_sinh, dia_chi, ma_dinh_danh,thoi_gian_tao,thoi_gian_sua)"
                + " values(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, nv.getUsername());
            ps.setObject(2, nv.getTen());
            ps.setObject(3, nv.getEmail());
            ps.setObject(4, nv.getSoDienThoai());
            ps.setObject(5, nv.getGioiTinh());
            ps.setObject(6, nv.getTrangThai());
            ps.setObject(7, nv.getNgaySinh());
            ps.setObject(8, nv.getDiaChi());
            ps.setObject(9, nv.getMaDinhDanh());
            ps.setTimestamp(10, new java.sql.Timestamp(nv.getThoiGianTao().getTime()));
            ps.setTimestamp(11, new java.sql.Timestamp(nv.getThoiGianSua().getTime()));
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int deletePhanQuyenByNhanVienId(int nhanVienId) {
        String phanQuyenDeleteSql = "DELETE FROM phan_quyen WHERE nhan_vien_id = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(phanQuyenDeleteSql)) {
            ps.setObject(1, nhanVienId);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int deleteNhanvien(int id) {
        String nhanVienDeleteSql = "DELETE FROM nhan_vien WHERE ID = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(nhanVienDeleteSql)) {
            ps.setObject(1, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int updateNhanVien(NhanVienEntity nv, int id) {
        sql = "UPDATE nhan_vien SET username=?, ten=?, email=?, so_dien_thoai=?, "
                + "gioi_tinh=?, trang_thai=?, ngay_sinh=?, dia_chi=?, ma_dinh_danh=?, "
                + "thoi_gian_sua=? WHERE id=?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, nv.getUsername()); // dấu ? thứ nhất ở trên sql kia
            ps.setObject(2, nv.getTen());
            ps.setObject(3, nv.getEmail());
            ps.setObject(4, nv.getSoDienThoai());
            ps.setObject(5, nv.getGioiTinh());
            ps.setObject(6, nv.getTrangThai());
            ps.setObject(7, nv.getNgaySinh());
            ps.setObject(8, nv.getDiaChi());
            ps.setObject(9, nv.getMaDinhDanh());
            ps.setObject(10, nv.getThoiGianSua());
            ps.setObject(11, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<NhanVienEntity> findNhanVienByNameOrSoDienThoaiOrEmail(String keyword) {
        String sql = "SELECT id, username, ten, email, so_dien_thoai, gioi_tinh, trang_thai, ngay_sinh, dia_chi, ma_dinh_danh, thoi_gian_tao, thoi_gian_sua FROM nhan_vien WHERE ten LIKE ? OR so_dien_thoai LIKE ? OR email LIKE ?";
        List<NhanVienEntity> listNV = new ArrayList<>();

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ps.setString(3, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    NhanVienEntity nv = new NhanVienEntity(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("ten"),
                            rs.getString("ma_dinh_danh"),
                            rs.getString("so_dien_thoai"),
                            rs.getDate("ngay_sinh"),
                            rs.getString("email"),
                            rs.getString("gioi_tinh"),
                            rs.getString("dia_chi"),
                            rs.getString("trang_thai"),
                            rs.getDate("thoi_gian_tao"),
                            rs.getDate("thoi_gian_sua")
                    );
                    listNV.add(nv);
                }
            }
            return listNV;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<NhanVienEntity> filterNhanVienByGender(List<NhanVienEntity> nhanViens, String gioiTinh) {
        // Sử dụng Java Stream để lọc danh sách nhân viên
        return nhanViens.stream()
                .filter(nv -> nv.getGioiTinh().equalsIgnoreCase(gioiTinh))
                .collect(Collectors.toList());
    }

    public List<NhanVienEntity> filterByGender(String gender) {
        List<NhanVienEntity> listNV = new ArrayList<>();
        String sql = "SELECT id, username, ten, email, so_dien_thoai, gioi_tinh, trang_thai, "
                + "ngay_sinh, dia_chi, ma_dinh_danh, thoi_gian_tao, thoi_gian_sua "
                + "FROM nhan_vien WHERE gioi_tinh = ?";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, gender);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                NhanVienEntity nv = new NhanVienEntity(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("ten"),
                        rs.getString("ma_dinh_danh"),
                        rs.getString("so_dien_thoai"),
                        rs.getDate("ngay_sinh"),
                        rs.getString("email"),
                        rs.getString("gioi_tinh"),
                        rs.getString("dia_chi"),
                        rs.getString("trang_thai"),
                        rs.getDate("thoi_gian_tao"),
                        rs.getDate("thoi_gian_sua")
                );
                listNV.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listNV;
    }

    public List<NhanVienEntity> filterByStatusAndGender(String status, String gender) {
        List<NhanVienEntity> listNV = new ArrayList<>();
        String sql = "SELECT id, username, ten, email, so_dien_thoai, gioi_tinh, trang_thai, "
                + "ngay_sinh, dia_chi, ma_dinh_danh, thoi_gian_tao, thoi_gian_sua "
                + "FROM nhan_vien WHERE trang_thai = ? AND gioi_tinh = ?";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setString(2, gender);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                NhanVienEntity nv = new NhanVienEntity(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("ten"),
                        rs.getString("ma_dinh_danh"),
                        rs.getString("so_dien_thoai"),
                        rs.getDate("ngay_sinh"),
                        rs.getString("email"),
                        rs.getString("gioi_tinh"),
                        rs.getString("dia_chi"),
                        rs.getString("trang_thai"),
                        rs.getDate("thoi_gian_tao"),
                        rs.getDate("thoi_gian_sua")
                );
                listNV.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listNV;
    }

//    public List<NhanVienEntity> filterNhanVienByStatusAndGender(String status, String gender) {
//        sql = "SELECT id, username, ten, email, so_dien_thoai, gioi_tinh, trang_thai, ngay_sinh, dia_chi, ma_dinh_danh, thoi_gian_tao, thoi_gian_sua "
//                + "FROM nhan_vien WHERE trang_thai = ? AND gioi_tinh = ?";
//        List<NhanVien> filteredList = new ArrayList<>();
//
//        try {
//            con = DBConnect.getConnection();
//            ps = con.prepareStatement(sql);
//            ps.setString(1, status);
//            ps.setString(2, gender);
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//                NhanVien nv = new NhanVien(
//                        rs.getInt(1),
//                        rs.getString(2),
//                        rs.getString(3),
//                        rs.getString(10),
//                        rs.getString(5),
//                        rs.getString(8),
//                        rs.getString(4),
//                        rs.getString(6),
//                        rs.getString(9),
//                        rs.getString(7),
//                        rs.getDate(11),
//                        rs.getDate(12)
//                );
//                filteredList.add(nv);
//            }
//
//            return filteredList;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            // Đóng tài nguyên (ResultSet, PreparedStatement, Connection) ở đây nếu cần
//        }
//    }
    public List<NhanVienEntity> filterByStatus(String status) {
        String sql = "SELECT id, username, ten, email, so_dien_thoai, gioi_tinh, trang_thai, ngay_sinh, dia_chi, ma_dinh_danh, thoi_gian_tao, thoi_gian_sua FROM nhan_vien WHERE trang_thai = ?";
        List<NhanVienEntity> filteredList = new ArrayList<>();
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, status);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVienEntity nv = new NhanVienEntity(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("ten"),
                        rs.getString("ma_dinh_danh"),
                        rs.getString("so_dien_thoai"),
                        rs.getDate("ngay_sinh"),
                        rs.getString("email"),
                        rs.getString("gioi_tinh"),
                        rs.getString("dia_chi"),
                        rs.getString("trang_thai"),
                        rs.getDate("thoi_gian_tao"),
                        rs.getDate("thoi_gian_sua")
                );
                filteredList.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return filteredList;
    }

//    public List<SinhVien> Sortname(){
//        sql = "SELECT ID, NAME FROM SinhVien ORDER BY NAME DESC";
//        List<SinhVien> listSV = new ArrayList<>();
//        try {
//            con = DBconnect.getConnection();
//            ps = con.prepareStatement(sql);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                SinhVien sv = new SinhVien(rs.getInt(1), rs.getString(2)); // get lấy tên cột or cột getString(name)
//                listSV.add(sv);
//            }
//            return listSV;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//    public void backupNhanVien(NhanVienBackup backup) {
//        try {
//            con = DBConnect.getConnection();
//            sql = "INSERT INTO backup_table (id, username, ten, maDinhDanh, soDienThoai, ngaySinh, email, gioiTinh, diaChi, trangThai, thoiGianTao, thoiGianSua) "
//                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//
//            ps = con.prepareStatement(sql);
//
//            // Set các giá trị từ đối tượng NhanVienBackup vào câu lệnh SQL
//            ps.setInt(1, backup.getId());
//            ps.setString(2, backup.getUsername());
//            ps.setString(3, backup.getTen());
//            ps.setString(4, backup.getMaDinhDanh());
//            ps.setString(5, backup.getSoDienThoai());
//            ps.setString(6, backup.getNgaySinh());
//            ps.setString(7, backup.getEmail());
//            ps.setString(8, backup.getGioiTinh());
//            ps.setString(9, backup.getDiaChi());
//            ps.setString(10, backup.getTrangThai());
//            ps.setTimestamp(11, new Timestamp(backup.getThoiGianTao().getTime()));
//            ps.setTimestamp(12, new Timestamp(backup.getThoiGianSua().getTime()));
//
//            // Thực hiện câu lệnh SQL
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            DBConnect.closeAll(con, ps, rs);
//        }
//    }
    public NhanVienEntity getNhanVienById(int id) {
        String sql = "SELECT * FROM NhanVien WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                NhanVienEntity nv = new NhanVienEntity();
                nv.setId(rs.getInt("id"));
                nv.setUsername(rs.getString("username"));
                nv.setTen(rs.getString("ten"));
                nv.setMaDinhDanh(rs.getString("maDinhDanh"));
                nv.setSoDienThoai(rs.getString("soDienThoai"));
                nv.setNgaySinh(rs.getDate("ngaySinh"));
                nv.setEmail(rs.getString("email"));
                nv.setGioiTinh(rs.getString("gioiTinh"));
                nv.setDiaChi(rs.getString("diaChi"));
                nv.setTrangThai(rs.getString("trangThai"));
                nv.setThoiGianTao(rs.getTimestamp("thoiGianTao"));
                nv.setThoiGianSua(rs.getTimestamp("thoiGianSua"));

                return nv;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public boolean isUsernameUnique(String username) {
        String sql = "SELECT COUNT(*) FROM nhan_vien WHERE username = ?";
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count == 0; // Trả về true nếu tên đăng nhập là duy nhất
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Mặc định trả về false nếu có lỗi
    }
//                                      Login

    public NhanVienRepo() {
        this.con = DBConnect.getConnection();
    }

    public List<Login> getAllLogins() {
        sql = "SELECT username, Password, role, nhan_vien_id FROM phan_quyen"; // Thêm cột NhanVienId vào truy vấn
        List<Login> loginList = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Login lg = new Login(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4)
                );
                loginList.add(lg);
            }
            return loginList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Login checkLogin(String username, String password) {
        String sql = "SELECT username, Password, role FROM phan_quyen WHERE username = ? AND Password = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Login(
                            rs.getString("username"),
                            rs.getString("Password"),
                            rs.getString("role"),
                            rs.getInt("nhan_vien_id")
                    );
                } else {
                    return null;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Optional<NhanVienEntity> getNhanVienByLogin(String username, String password) {
        String sql = "SELECT * FROM nhan_vien NV INNER JOIN phan_quyen PQ ON "
                + "NV.id = PQ.nhan_vien_id WHERE PQ.username = ? AND PQ.password = ?";

        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                // Lấy thông tin nhân viên từ ResultSet và trả về Optional
                return Optional.of(new NhanVienEntity(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("ten"),
                        rs.getString("ma_dinh_danh"),
                        rs.getString("so_dien_thoai"),
                        rs.getDate("ngay_sinh"),
                        rs.getString("email"),
                        rs.getString("gioi_tinh"),
                        rs.getString("dia_chi"),
                        rs.getString("trang_thai"),
                        rs.getTimestamp("thoi_gian_tao"),
                        rs.getTimestamp("thoi_gian_sua")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public boolean login(String username, String password) {
        // Truy vấn cơ sở dữ liệu để kiểm tra tên đăng nhập và mật khẩu
        String sql = "SELECT COUNT(*) FROM phan_quyen WHERE username = ? AND Password = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0; // Trả về true nếu có ít nhất một bản ghi khớp
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Trả về false nếu có lỗi hoặc không có bản ghi khớp
    }

    public int updateMatkhau(Login lg, String username) {
        String sql = "UPDATE phan_quyen SET Password=? WHERE username=?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, lg.getPassword());
            ps.setObject(2, username);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String getUserRole(String username, String password) {
        String role = null;
        try {
            Connection connection = DBConnect.getConnection();
            String sql = "SELECT Role FROM phan_quyen WHERE username = ? AND Password = ?";
            ps = con.prepareStatement(sql);
            ps.setObject(1, username);
            ps.setObject(2, password);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                role = resultSet.getString("Role");
                System.out.println("Role service: " + role);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    // phan trang
    public List<NhanVienEntity> getAllNhanVienPaged(int page, int rowsPerPage) {
        List<NhanVienEntity> listNV = new ArrayList<>();

        try {
            con = DBConnect.getConnection();

            // Tính toán chỉ số bắt đầu dựa trên trang hiện tại và số dòng mỗi trang
            int startIndex = (page - 1) * rowsPerPage;

            // Sử dụng LIMIT và OFFSET để lấy dữ liệu theo trang
            sql = "SELECT id, username, ten, email, so_dien_thoai, gioi_tinh, trang_thai, "
                    + "ngay_sinh, dia_chi, ma_dinh_danh, thoi_gian_tao, thoi_gian_sua "
                    + "FROM nhan_vien LIMIT ? OFFSET ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, rowsPerPage);
            ps.setInt(2, startIndex);

            rs = ps.executeQuery();

            while (rs.next()) {
                NhanVienEntity nv = new NhanVienEntity(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("ten"),
                        rs.getString("ma_dinh_danh"),
                        rs.getString("so_dien_thoai"),
                        rs.getDate("ngay_sinh"),
                        rs.getString("email"),
                        rs.getString("gioi_tinh"),
                        rs.getString("dia_chi"),
                        rs.getString("trang_thai"),
                        rs.getDate("thoi_gian_tao"),
                        rs.getDate("thoi_gian_sua")
                );
                listNV.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng tài nguyên khi đã sử dụng xong
            closeResources();
        }

        return listNV;
    }

    public int getTotalNhanVien() {
        int totalRows = 0;

        try {
            con = DBConnect.getConnection();

            // Sử dụng COUNT để lấy tổng số dòng trong bảng
            sql = "SELECT COUNT(*) FROM nhan_vien";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                totalRows = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng tài nguyên khi đã sử dụng xong
            closeResources();
        }

        return totalRows;
    }

    // Các phương thức và biến hỗ trợ đóng tài nguyên
    private void closeResources() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<NhanVienEntity> filterByDateRange(Date startDate, Date endDate) {
        List<NhanVienEntity> filteredList = new ArrayList<>();

        try {
            con = DBConnect.getConnection();

            // Sử dụng BETWEEN để lấy dữ liệu trong khoảng ngày
            String sql = "SELECT * FROM nhan_vien WHERE ngay_sinh BETWEEN ? AND ?";
            ps = con.prepareStatement(sql);

            // Chuyển đổi java.util.Date thành java.sql.Date
            java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

            ps.setDate(1, sqlStartDate);
            ps.setDate(2, sqlEndDate);

            rs = ps.executeQuery();

            while (rs.next()) {
                NhanVienEntity nv = new NhanVienEntity(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("ten"),
                        rs.getString("ma_dinh_danh"),
                        rs.getString("so_dien_thoai"),
                        rs.getDate("ngay_sinh"),
                        rs.getString("email"),
                        rs.getString("gioi_tinh"),
                        rs.getString("dia_chi"),
                        rs.getString("trang_thai"),
                        rs.getDate("thoi_gian_tao"),
                        rs.getDate("thoi_gian_sua")
                );
                filteredList.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return filteredList;
    }

}
//                               Login

