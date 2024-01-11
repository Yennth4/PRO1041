package com.poly.form.khachhang.view;

import com.poly.form.khachhang.view.FormHoaDonKhachHang;
import com.poly.form.khachhang.view.FormCaiDaiMail;
import com.formdev.flatlaf.FlatClientProperties;
import com.poly.Application;
import com.poly.form.khachhang.entity.KhachHangHung;

import com.poly.menu.Menu;
import com.poly.form.khachhang.repository.KhachHangRepository;
import com.poly.form.khachhang.service.KhachHangService;
import static com.poly.util.ph42118.MaRandom.renderMa;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import raven.toast.Notifications;

/**
 *
 * @author Raven
 */
public class FormQuanLyKhachHang extends javax.swing.JPanel {

    private final KhachHangService khachHangService = new KhachHangService();

    public FormQuanLyKhachHang() {
        initComponents();
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        cbxCapBac.setEnabled(false);
        cbxGuiMail.setVisible(false);
        btnChinhSuaMail.setVisible(false);
        fillThongTinKhachHangToTable();
        fillCapBacComboBox();

        updateCapBacAndProgressBarForAllKhachHang();

        tblThongTinKhachHang.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void updateCapBacAndProgressBarForAllKhachHang() {
        List<KhachHangHung> allKhachHang = khachHangService.getAllKhachHang();
        for (KhachHangHung khachHang : allKhachHang) {
            khachHangService.updateCapBacTheoTien(khachHang.getId());
            updateProgressBar(khachHang);
        }
    }

    private void updateProgressBar(KhachHangHung khachHang) {
        double tongTienMua = khachHangService.getTongSoTienDaTra(khachHang.getId());
        int percent = calculatePercentage(tongTienMua);

        // Cập nhật giá trị và hiển thị lên thanh tiến trình
        SwingUtilities.invokeLater(() -> {
            pbTienTrinh.setValue(percent);
            phanTramCapBac.setText(percent + "%");
        });
    }

    private int calculatePercentage(double tongTienMua) {
        if (tongTienMua >= 40000000) {
            return 100; // Nếu lớn hơn hoặc bằng 40 triệu thì là 100%
        } else if (tongTienMua >= 10000000) {
            // Nếu từ 10 triệu đến 40 triệu, tính phần trăm trong khoảng 50-100%
            return 50 + (int) (((tongTienMua - 10000000) / 30000000) * 50);
        } else {
            // Nếu dưới 10 triệu, tính phần trăm trong khoảng 0-50%
            return (int) ((tongTienMua / 10000000) * 50);
        }
    }

    private void fillThongTinKhachHangToTable() {
        DefaultTableModel model = (DefaultTableModel) tblThongTinKhachHang.getModel();
        model.setRowCount(0);
        int stt = 1;
        for (KhachHangHung kh : khachHangService.getAllKhachHang()) {
            String gioiTinh = kh.isGioiTinh() ? "Nam" : "Nữ";
            String capBac = "";
            if (kh.getCapBac() == 1) {
                capBac = "Đồng";
            } else if (kh.getCapBac() == 2) {
                capBac = "Vàng";
            } else if (kh.getCapBac() == 3) {
                capBac = "Kim cương";
            }
            Object data[] = {stt, kh.getMa(), kh.getHoTen(), gioiTinh,
                kh.getSdt(), kh.getDiaChi(), kh.getEmail(), kh.getNgaySinh(),
                capBac, kh.getThoiGianTao(), kh.getThoiGianSua()};
            model.addRow(data);
            stt++;
        }
    }

    private void fillDataSearchToTable(List<KhachHangHung> list) {
        DefaultTableModel model = (DefaultTableModel) tblThongTinKhachHang.getModel();
        model.setRowCount(0);
        int stt = 1;
        for (KhachHangHung kh : list) {
            String gioiTinh = kh.isGioiTinh() ? "Nam" : "Nữ";
            String capBac = "";
            if (kh.getCapBac() == 1) {
                capBac = "Đồng";
            } else if (kh.getCapBac() == 2) {
                capBac = "Vàng";
            } else if (kh.getCapBac() == 3) {
                capBac = "Kim cương";
            }
            Object data[] = {stt, kh.getMa(), kh.getHoTen(), gioiTinh,
                kh.getSdt(), kh.getDiaChi(), kh.getEmail(), kh.getNgaySinh(),
                capBac, kh.getThoiGianTao(), kh.getThoiGianSua()};
            model.addRow(data);
            stt++;
        }
    }

    private void fillCapBacComboBox() {
        DefaultComboBoxModel<String> capBacModel = new DefaultComboBoxModel<>();

        Map<Integer, String> capBacMap = new HashMap<>();
        capBacMap.put(1, "Đồng");
        capBacMap.put(2, "Vàng");
        capBacMap.put(3, "Kim Cương");

        for (Map.Entry<Integer, String> entry : capBacMap.entrySet()) {
            capBacModel.addElement(entry.getValue());
        }

        cbxCapBac.setModel(capBacModel);
    }

    private void fillDataToFromThongTinKhachHang(KhachHangHung kh) {

        lbMaKhachHang.setText(kh.getMa() + "");
        txtEmail.setText(kh.getEmail());
        txtHoVaTen.setText(kh.getHoTen());
        txtSoDienThoai.setText(kh.getSdt());
        taDiaChi.setText(kh.getDiaChi());
        lbNgaySua.setText(kh.getThoiGianSua() + "");
        lbNgayTao.setText(kh.getThoiGianTao() + "");

        if (kh.isGioiTinh()) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }

        dateNgaySinh.setDate(kh.getNgaySinh());

        int capBacValue = kh.getCapBac();
        cbxCapBac.setSelectedItem(getCapBacDisplayString(capBacValue));

        updateProgressBar(kh);

    }

    // hiển thị lên combobox cấp bậc khi click table
    private String getCapBacDisplayString(int capBacValue) {
        Map<Integer, String> capBacMap = new HashMap<>();
        capBacMap.put(1, "Đồng");
        capBacMap.put(2, "Vàng");
        capBacMap.put(3, "Kim Cương");

        return capBacMap.get(capBacValue);
    }

    private void clearFormThongTinKhachHang() {
        lbMaKhachHang.setText("");
        txtEmail.setText("");
        txtHoVaTen.setText("");
        txtSoDienThoai.setText("");
        taDiaChi.setText("");

        dateNgaySinh.setDate(null);
        lbNgaySua.setText("");
        lbNgaySua.setText("");

        // Assuming cbxCapBac is a JComboBox
        cbxCapBac.setSelectedIndex(0);
        tblThongTinKhachHang.clearSelection();
    }

    private KhachHangHung getKhachHangToFormThongTinKhachHang() {
        KhachHangHung khachHang = new KhachHangHung();

        String generatedCode = renderMa();
        khachHang.setMa(generatedCode);

        khachHang.setHoTen(txtHoVaTen.getText());
        khachHang.setCapBac(1);
        khachHang.setDiaChi(taDiaChi.getText());
        khachHang.setEmail(txtEmail.getText());
        khachHang.setSdt(txtSoDienThoai.getText());
        if (rdoNam.isSelected()) {
            khachHang.setGioiTinh(true);
        } else if (rdoNu.isSelected()) {
            khachHang.setGioiTinh(false);
        }

        khachHang.setNgaySinh(new Date(dateNgaySinh.getDate().getTime()));

        Date currentDate = new Date(System.currentTimeMillis());
        khachHang.setThoiGianTao(currentDate);
        khachHang.setThoiGianSua(currentDate);
        return khachHang;
    }

    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        lb = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lbMaKhachHang = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtSoDienThoai = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtHoVaTen = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        dateNgaySinh = new com.toedter.calendar.JDateChooser();
        txtEmail = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taDiaChi = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        cbxCapBac = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        pbTienTrinh = new javax.swing.JProgressBar();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        btnTrangKhachHangDaXoa = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lbNgayTao = new javax.swing.JLabel();
        lbNgaySua = new javax.swing.JLabel();
        phanTramCapBac = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        cbxSearchGioiTinh = new javax.swing.JComboBox<>();
        cbxCapbacSearch = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblThongTinKhachHang = new javax.swing.JTable();
        btnGuiMail = new javax.swing.JButton();
        btnChinhSuaMail = new javax.swing.JButton();
        cbxGuiMail = new javax.swing.JComboBox<>();

        lb.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("Quản lý khách hàng");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setText("Mã khách hàng: ");

        lbMaKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbMaKhachHang.setForeground(new java.awt.Color(0, 0, 255));
        lbMaKhachHang.setText(" ");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("Số điện thoại:");

        txtSoDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setText("Họ và tên:");

        txtHoVaTen.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setText("Giới tính:");

        buttonGroup1.add(rdoNam);
        rdoNam.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");

        buttonGroup1.add(rdoNu);
        rdoNu.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        rdoNu.setText("Nữ");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setText("Ngày sinh:");

        dateNgaySinh.setDateFormatString("yyyy-MM-dd");
        dateNgaySinh.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel8.setText("Email:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel9.setText("Địa chỉ:");

        taDiaChi.setColumns(20);
        taDiaChi.setRows(5);
        jScrollPane1.setViewportView(taDiaChi);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setText("Cấp bậc:");

        cbxCapBac.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbxCapBac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setText("Tiến trình:");

        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnLamMoi.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnTrangKhachHangDaXoa.setText("Trang khách hàng đã xóa");
        btnTrangKhachHangDaXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrangKhachHangDaXoaActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Ngày tạo:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Ngày sủa:");

        lbNgayTao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbNgayTao.setText(" ");

        lbNgaySua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbNgaySua.setText(" ");

        phanTramCapBac.setText(" ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(rdoNam)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdoNu))
                                    .addComponent(txtHoVaTen)
                                    .addComponent(dateNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                                    .addComponent(cbxCapBac, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(55, 55, 55))
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(19, 19, 19)))
                                .addGap(26, 26, 26))))
                    .addComponent(jLabel10))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                        .addComponent(txtEmail)
                        .addComponent(txtSoDienThoai))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(pbTienTrinh, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(phanTramCapBac)))
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnXoa)
                            .addComponent(btnLamMoi)))
                    .addComponent(btnTrangKhachHangDaXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbNgayTao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbNgaySua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnLamMoi, btnSua, btnThem, btnXoa});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(lbMaKhachHang)
                            .addComponent(jLabel4)
                            .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(lbNgayTao))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtHoVaTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel6)
                                            .addComponent(rdoNam)
                                            .addComponent(rdoNu)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel9)))
                                .addGap(20, 20, 20)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7)
                                    .addComponent(dateNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(10, 10, 10)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(lbNgaySua))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pbTienTrinh, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(cbxCapBac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnTrangKhachHangDaXoa)))
                    .addComponent(phanTramCapBac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(17, 17, 17))
        );

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel1.setText("Thiết lập thông tin khách hàng");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel13.setText("Thông tin khách hàng");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel14.setText("Tìm kiếm");

        txtTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });
        txtTimKiem.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtTimKiemPropertyChange(evt);
            }
        });
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyTyped(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel15.setText("Giới tính");

        cbxSearchGioiTinh.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbxSearchGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nữ", "Nam", "Tất cả" }));
        cbxSearchGioiTinh.setSelectedIndex(2);
        cbxSearchGioiTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxSearchGioiTinhActionPerformed(evt);
            }
        });

        cbxCapbacSearch.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbxCapbacSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Đồng", "Vàng", "Kim cương" }));
        cbxCapbacSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxCapbacSearchActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel17.setText("Cấp bậc");

        tblThongTinKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblThongTinKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", "", "", "", "", "", "", "", null, null, ""}
            },
            new String [] {
                "STT", "Mã KH", "Họ Và Tên", "Giới tính", "SĐT", "Địa chỉ", "Email", "Ngày sinh", "Cấp bậc", "Ngày tạo", "Ngày sửa"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblThongTinKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThongTinKhachHangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblThongTinKhachHang);

        btnGuiMail.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnGuiMail.setText("Gửi mail");
        btnGuiMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuiMailActionPerformed(evt);
            }
        });

        btnChinhSuaMail.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnChinhSuaMail.setText("Tiếp tục");
        btnChinhSuaMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChinhSuaMailActionPerformed(evt);
            }
        });

        cbxGuiMail.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbxGuiMail.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Tất cả", "Chọn 1", "Cấp bậc kim cương" }));
        cbxGuiMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxGuiMailActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxSearchGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxCapbacSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnGuiMail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxGuiMail, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnChinhSuaMail, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 8, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(cbxCapbacSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuiMail)
                    .addComponent(jLabel17)
                    .addComponent(cbxSearchGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChinhSuaMail)
                    .addComponent(cbxGuiMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel13))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(3, 3, 3)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblThongTinKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThongTinKhachHangMouseClicked
        int row = tblThongTinKhachHang.getSelectedRow();
        String makh = (String) tblThongTinKhachHang.getValueAt(row, 1);
        KhachHangHung kh = khachHangService.getKhachHangById(makh);
        fillDataToFromThongTinKhachHang(kh);

        if (evt.getClickCount() == 3) {
            Application.showForm(new FormHoaDonKhachHang(kh));
        }
    }//GEN-LAST:event_tblThongTinKhachHangMouseClicked

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        clearFormThongTinKhachHang();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (txtHoVaTen.getText().trim().isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Vui lòng nhập họ và tên");
            return;
        }

        if (dateNgaySinh.getDate() == null) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Ngày sinh chưa hợp lệ");
            return;
        }

        if (txtSoDienThoai.getText().trim().isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Vui lòng nhập số điện thoại");
            return;
        } else if (!txtSoDienThoai.getText().startsWith("0")
                || !txtSoDienThoai.getText().matches("\\d+")
                || txtSoDienThoai.getText().length() < 10 || txtSoDienThoai.getText().length() > 12) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Số điện thoại không hợp lệ");
            return;
        }

        if (txtEmail.getText().trim().isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Vui lòng nhập email");
            return;
        } else if (!isValidEmail(txtEmail.getText().trim())) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Email chưa hợp lệ");
            return;
        }

        if (taDiaChi.getText().trim().isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Vui lòng nhập địa chỉ");
            return;
        }

        KhachHangHung kh = getKhachHangToFormThongTinKhachHang();
        if (khachHangService.createKhachHang(kh)) {
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Thêm thành công");
            fillThongTinKhachHangToTable();
            clearFormThongTinKhachHang();
        } else {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER,
                    "Thêm không thành công");
        }

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        int row = tblThongTinKhachHang.getSelectedRow();
        if (row != -1) {

            if (txtHoVaTen.getText().trim().isEmpty()) {
                Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Vui lòng nhập họ và tên");
                return;
            }

            if (dateNgaySinh.getDate() == null) {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Ngày sinh chưa hợp lệ");
                return;
            }

            if (txtSoDienThoai.getText().trim().isEmpty()) {
                Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Vui lòng nhập số điện thoại");
                return;
            } else if (!txtSoDienThoai.getText().startsWith("0")
                    || !txtSoDienThoai.getText().matches("\\d+")
                    || txtSoDienThoai.getText().length() < 10 || txtSoDienThoai.getText().length() > 12) {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Số điện thoại không hợp lệ");
                return;
            }

            if (txtEmail.getText().trim().isEmpty()) {
                Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Vui lòng nhập email");
                return;
            } else if (!isValidEmail(txtEmail.getText().trim())) {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Email chưa hợp lệ");
                return;
            }

            if (taDiaChi.getText().trim().isEmpty()) {
                Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Vui lòng nhập địa chỉ");
                return;
            }

            String maKhachHang = (String) tblThongTinKhachHang.getValueAt(row, 1);
            KhachHangHung khachHangUpdate = getKhachHangToFormThongTinKhachHang();

            int confirmResult = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn cập nhật khách hàng không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirmResult == JOptionPane.YES_OPTION) {
                if (khachHangService.updateKhachHang(maKhachHang, khachHangUpdate)) {
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Cập nhật thành công");
                    fillThongTinKhachHangToTable();
                    clearFormThongTinKhachHang();
                } else {
                    Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER,
                            "Cập nhật không thành công");
                    return;
                }
            }
        } else {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Vui lòng chọn khách hàng để cập nhật");
            return;
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int row = tblThongTinKhachHang.getSelectedRow();
        if (row != -1) {
            String idKhachHang = (String) tblThongTinKhachHang.getValueAt(row, 1);
            int confirmResult = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa khách hàng không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirmResult == JOptionPane.YES_OPTION) {
                if (khachHangService.deleteKhachHangById(idKhachHang)) {
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Xóa khách hàng thành công");
                    fillThongTinKhachHangToTable();
                    clearFormThongTinKhachHang();
                } else {
                    Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Xóa khách hàng không thành công");
                    return;
                }
            }
        } else {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Vui lòng chọn khách hàng để xóa");
            return;
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed

    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void txtTimKiemKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyTyped

    }//GEN-LAST:event_txtTimKiemKeyTyped

    private void txtTimKiemPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtTimKiemPropertyChange

    }//GEN-LAST:event_txtTimKiemPropertyChange

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        int gioiTinh = cbxSearchGioiTinh.getSelectedIndex(); // 0 là nữ 1 nam 2 là tất cả: 
        int capBac = cbxCapbacSearch.getSelectedIndex(); // 0 đồng, 1 vàng,2 kim cương 3 là tất cả;
        String keyword = txtTimKiem.getText().trim();

        System.out.println(keyword);
        System.out.println("capBac" + capBac);
        System.out.println("gioiTinh" + gioiTinh);

        List<KhachHangHung> listResult = new ArrayList<>();
        listResult = khachHangService.boloc(keyword, gioiTinh, capBac, 0);
        for (KhachHangHung kh : listResult) {
            System.out.println(kh.toString());
        }
        fillDataSearchToTable(listResult);

//        if (!keyword.isEmpty() || keyword != null) {
//          
//        } else {
//           fillThongTinKhachHangToTable();
//        }

    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void cbxSearchGioiTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxSearchGioiTinhActionPerformed
        int gioiTinh = cbxSearchGioiTinh.getSelectedIndex(); // 0 là nữ 1 nam 2 là tất cả: 
        int capBac = cbxCapbacSearch.getSelectedIndex(); // 0 đồng, 1 vàng,2 kim cương 3 là tất cả;
        String keyword = txtTimKiem.getText().trim();

        System.out.println(keyword);
        System.out.println("capBac" + capBac);
        System.out.println("gioiTinh" + gioiTinh);

        List<KhachHangHung> listResult = new ArrayList<>();
        listResult = khachHangService.boloc(keyword, gioiTinh, capBac, 0);
        for (KhachHangHung kh : listResult) {
            System.out.println(kh.toString());
        }
        fillDataSearchToTable(listResult);
    }//GEN-LAST:event_cbxSearchGioiTinhActionPerformed

    private void cbxCapbacSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCapbacSearchActionPerformed
        int gioiTinh = cbxSearchGioiTinh.getSelectedIndex(); // 0 là nữ 1 nam 2 là tất cả: 
        int capBac = cbxCapbacSearch.getSelectedIndex(); // 0 đồng, 1 vàng,2 kim cương 3 là tất cả;
        String keyword = txtTimKiem.getText().trim();

        System.out.println(keyword);
        System.out.println("capBac" + capBac);
        System.out.println("gioiTinh" + gioiTinh);

        List<KhachHangHung> listResult = new ArrayList<>();
        listResult = khachHangService.boloc(keyword, gioiTinh, capBac, 0);
        for (KhachHangHung kh : listResult) {
            System.out.println(kh.toString());
        }
        fillDataSearchToTable(listResult);
    }//GEN-LAST:event_cbxCapbacSearchActionPerformed

    private void btnTrangKhachHangDaXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangKhachHangDaXoaActionPerformed
        Application.showForm(new FormQuanLyKhachHangDaXoa());
    }//GEN-LAST:event_btnTrangKhachHangDaXoaActionPerformed

    private void btnGuiMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuiMailActionPerformed
        cbxGuiMail.setVisible(true);
        btnChinhSuaMail.setVisible(true);
    }//GEN-LAST:event_btnGuiMailActionPerformed

    private void cbxGuiMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxGuiMailActionPerformed
        String selectedRank = (String) cbxGuiMail.getSelectedItem();

        if (selectedRank.equals("Cấp bậc kim cương")) {
            List<KhachHangHung> filteredList = khachHangService.getAllKhachHangByCapBac(3);
            fillDataSearchToTable(filteredList);
        }
    }//GEN-LAST:event_cbxGuiMailActionPerformed

    private void btnChinhSuaMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChinhSuaMailActionPerformed
        String luaChon = (String) cbxGuiMail.getSelectedItem();
        List<KhachHangHung> customersToSendMail = new ArrayList<>();
        if(cbxGuiMail.getSelectedIndex()==0) {
             Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER,
                        "Vui lòng lựa chọn cách gửi");
             return;
        }
        if (luaChon.equals("Chọn 1")) {
            int selectedRow = tblThongTinKhachHang.getSelectedRow();

            if (selectedRow != -1) {
                String maKH = (String) tblThongTinKhachHang.getValueAt(selectedRow, 1);

                KhachHangHung selectedCustomer = khachHangService.getKhachHangById(maKH);

                customersToSendMail.add(selectedCustomer);
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER,
                        "Vui lòng chọn 1 khách hàng");
                return;
            }
        } else if (luaChon.equals("Cấp bậc kim cương")) {
            customersToSendMail = khachHangService.getAllKhachHangByCapBac(3);
            if(customersToSendMail.isEmpty()) {
                 Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER,
                        "Danh sách khách hàng trống");
                return;
            }
        } else if (luaChon.equals("Tất cả")) {
            customersToSendMail = khachHangService.getAllKhachHang();
             if(customersToSendMail.isEmpty()) {
                 Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER,
                        "Danh sách khách hàng trống");
                return;
            }
        } else {
            customersToSendMail = null;
            clearFormThongTinKhachHang();
        }

        Application.showForm(new FormCaiDaiMail(customersToSendMail));
    }//GEN-LAST:event_btnChinhSuaMailActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChinhSuaMail;
    private javax.swing.JButton btnGuiMail;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTrangKhachHangDaXoa;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbxCapBac;
    private javax.swing.JComboBox<String> cbxCapbacSearch;
    private javax.swing.JComboBox<String> cbxGuiMail;
    private javax.swing.JComboBox<String> cbxSearchGioiTinh;
    private com.toedter.calendar.JDateChooser dateNgaySinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lbMaKhachHang;
    private javax.swing.JLabel lbNgaySua;
    private javax.swing.JLabel lbNgayTao;
    private javax.swing.JProgressBar pbTienTrinh;
    private javax.swing.JLabel phanTramCapBac;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTextArea taDiaChi;
    private javax.swing.JTable tblThongTinKhachHang;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoVaTen;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
