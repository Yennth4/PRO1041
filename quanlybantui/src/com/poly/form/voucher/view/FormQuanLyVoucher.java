package com.poly.form.voucher.view;

import com.formdev.flatlaf.FlatClientProperties;
import com.poly.form.voucher.entity.Voucher;
import com.poly.form.voucher.repository.VoucherRepository;
import com.poly.form.voucher.service.VoucherService;
import java.awt.Dimension;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import raven.toast.Notifications;

/**
 *
 * @author HaiYenNg
 */
public class FormQuanLyVoucher extends javax.swing.JPanel {

    VoucherService service = new VoucherService();
    List<Voucher> listVoucher = new ArrayList<>();
    DefaultTableModel dtm = new DefaultTableModel();

    public FormQuanLyVoucher() {
        initComponents();
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        listVoucher = service.getAll();
        fillTable(listVoucher);
    }

    public void fillTable(List<Voucher> list) {
        DefaultTableModel dtm = (DefaultTableModel) tblVoucher.getModel();
        dtm.setRowCount(0);
        for (Voucher voucher : list) {
            dtm.addRow(voucher.toRowData());
        };
    }

    public void show(int index) {
        Voucher v = listVoucher.get(index);
        lbId.setText(v.getId() + "");
        txtMa.setText(v.getMa());
        txtTen.setText(v.getTen());
        txtMucChiTieuToiThieu.setText(v.getMucChiTieuToiThieu() + "");
        txtSoLuong.setText(v.getSoLuong() + "");
        cbbLoaiGiamGia.setSelectedItem(v.getLoaiVoucher());
        txtMucGiamGia.setText(v.getPhanTramGiamGia() + "");
        String trangThai = v.getTrangThai();
        if (trangThai.equalsIgnoreCase("Hết hạn")) {
            rdoHetHan.setSelected(true);
        } else if (trangThai.equalsIgnoreCase("Hoạt động")) {
            rdoHoatDong.setSelected(true);
        }
        dateBatDau.setDate(v.getDateBatDau());
        dateKetThuc.setDate(v.getDateKetThuc());
        lbThoiGianSua.setText(v.getDateSua());
        lbThoiGianTao.setText(v.getDateTao());
    }

    public void clear() {
        lbId.setText("Id tự sinh");
        txtMa.setText("");
        txtTen.setText("");
        txtSoLuong.setText("");
        rdoHoatDong.setSelected(true);
        cbbLoaiGiamGia.setSelectedIndex(0);
        dateBatDau.setDate(null);
        dateKetThuc.setDate(null);
        lbThoiGianSua.setText("");
        lbThoiGianTao.setText("");
        txtMucGiamGia.setText("");
        txtDateBatDau.setText("");
        txtDateKetThuc.setText("");
        txtMucChiTieuToiThieu.setText("");
    }

    public Voucher getForm() {
        String ma = txtMa.getText().trim();
        String ten = txtTen.getText().trim();
        String mucChiTieuToiThieu = txtMucChiTieuToiThieu.getText().trim();
        String soLuong = txtSoLuong.getText().trim();
        String phanTramGiamGia = txtMucGiamGia.getText().trim();
        if (ma.isEmpty() || ten.isEmpty() || mucChiTieuToiThieu.isEmpty() || soLuong.isEmpty() || phanTramGiamGia.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống!!!");
            return null;
        }

        try {
            if ((Integer.valueOf(mucChiTieuToiThieu)) <= 0 || Integer.valueOf(soLuong) <= 0 || Integer.valueOf(phanTramGiamGia) <= 0) {
                JOptionPane.showMessageDialog(this, "Phải lớn hơn 0");
                return null;
            } else if (Integer.valueOf(phanTramGiamGia) > 99) {
                JOptionPane.showMessageDialog(this, "Phần trăm giảm giá không được quá 100%");
                return null;
            } else if (Integer.valueOf(soLuong) > 1000) {
                JOptionPane.showMessageDialog(this, "Số lượng không được quá 1000");
                return null;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Phải là số");
            return null;
        }

        Date dateBatDau = this.dateBatDau.getDate();
        Date dateKetThuc = this.dateKetThuc.getDate();
        if (dateBatDau != null && dateKetThuc != null && dateBatDau.before(dateKetThuc)) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String dateBatDauStr = sdf.format(dateBatDau);
            String dateKetThucStr = sdf.format(dateKetThuc);
        } else if (!dateBatDau.before(dateKetThuc)) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu không được lớn hơn ngày kết thúc !!!");
            return null;
        } else if (dateBatDau == null || dateKetThuc == null) {
            JOptionPane.showMessageDialog(this, "Ngày không được để trống!!!");
            return null;
        } else if (txtDateBatDau.getText().isEmpty() || txtDateKetThuc.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ngày không được để trống!!!");
            return null;
        }

        String trangThai = rdoHoatDong.isSelected() ? "Hoạt động" : "Hết hạn";
        String loaiVoucher = cbbLoaiGiamGia.getSelectedItem().toString();
        return new Voucher(ma, ten, Integer.valueOf(mucChiTieuToiThieu), loaiVoucher, Integer.valueOf(phanTramGiamGia), dateBatDau, dateKetThuc, Integer.valueOf(soLuong), trangThai);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        lb = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        dateBatDau = new com.toedter.calendar.JDateChooser();
        dateKetThuc = new com.toedter.calendar.JDateChooser();
        txtDateBatDau = new javax.swing.JTextField();
        txtDateKetThuc = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblVoucher = new javax.swing.JTable();
        btnSortGiamDan = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtTimKiemVoucherTheoMa = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        cbbLocTheoLoaiVoucher = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        cbbTimKiemTheoTrangThai = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lbId = new javax.swing.JLabel();
        lbThoiGianSua = new javax.swing.JLabel();
        lbThoiGianTao = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        rdoHoatDong = new javax.swing.JRadioButton();
        rdoHetHan = new javax.swing.JRadioButton();
        txtTen = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        cbbLoaiGiamGia = new javax.swing.JComboBox<>();
        txtMucGiamGia = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtMucChiTieuToiThieu = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("quản lý khuyến mại");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Chương trình khuyến mại", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 20))); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thời gian sử dụng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Thời gian bắt đầu giảm giá : ");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Thời gian kết thúc giảm giá : ");

        dateBatDau.setDateFormatString("yyyy-MM-dd");
        dateBatDau.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateBatDauPropertyChange(evt);
            }
        });

        dateKetThuc.setDateFormatString("yyyy-MM-dd");
        dateKetThuc.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateKetThucPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDateBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDateKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 54, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dateBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDateBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDateKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnThem.setBackground(new java.awt.Color(204, 204, 204));
        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/poly/icon/png/add.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(204, 204, 204));
        btnClear.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/poly/icon/png/clear.png"))); // NOI18N
        btnClear.setText("Xóa trắng");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(204, 204, 204));
        btnSua.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/poly/icon/png/refresh.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnHuy.setBackground(new java.awt.Color(255, 51, 51));
        btnHuy.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnHuy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/poly/icon/png/delete.png"))); // NOI18N
        btnHuy.setText("Hủy");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Danh sách voucher", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        tblVoucher.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tblVoucher.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Mã voucher", "Tên voucher", "Mức chi tiêu tối thiểu", "Mức giảm giá", "Số lượng", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblVoucher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVoucherMouseClicked(evt);
            }
        });
        tblVoucher.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblVoucherKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblVoucher);

        btnSortGiamDan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/poly/icon/png/return.png"))); // NOI18N
        btnSortGiamDan.setText("Làm mới");
        btnSortGiamDan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSortGiamDanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 585, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSortGiamDan, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSortGiamDan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Bộ lọc tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel12.setText("Tìm kiếm mã  : ");

        txtTimKiemVoucherTheoMa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemVoucherTheoMaKeyReleased(evt);
            }
        });

        jLabel17.setText("Hình thức:");

        cbbLocTheoLoaiVoucher.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Giảm giá theo tiền", "Giảm giá theo phần trăm" }));
        cbbLocTheoLoaiVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocTheoLoaiVoucherActionPerformed(evt);
            }
        });

        jLabel18.setText("Trạng thái:");

        cbbTimKiemTheoTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Hoạt động", "Hết hạn" }));
        cbbTimKiemTheoTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTimKiemTheoTrangThaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cbbLocTheoLoaiVoucher, javax.swing.GroupLayout.Alignment.LEADING, 0, 203, Short.MAX_VALUE)
                    .addComponent(cbbTimKiemTheoTrangThai, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTimKiemVoucherTheoMa))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtTimKiemVoucherTheoMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel17)
                    .addComponent(cbbLocTheoLoaiVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(cbbTimKiemTheoTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thiết lập khuyến mại", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("ID :");

        txtMa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Mã khuyến mại :");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Mức giảm giá :");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Sửa lần cuối:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Tạo vào lúc::");

        lbId.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lbThoiGianSua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lbThoiGianTao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Số lượng : ");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("*");

        txtSoLuong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Trạng thái : ");

        buttonGroup1.add(rdoHoatDong);
        rdoHoatDong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdoHoatDong.setText("Hoạt động");
        rdoHoatDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoHoatDongActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoHetHan);
        rdoHetHan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdoHetHan.setText("Hết hạn");
        rdoHetHan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoHetHanActionPerformed(evt);
            }
        });

        txtTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 0, 0));
        jLabel14.setText("*");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Tên khuyến mại :");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Hình thức :");

        cbbLoaiGiamGia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbbLoaiGiamGia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Giảm giá theo phần trăm", "Giảm giá theo tiền" }));
        cbbLoaiGiamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLoaiGiamGiaActionPerformed(evt);
            }
        });

        txtMucGiamGia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMucGiamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMucGiamGiaActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("Mức chi tiêu min :");

        txtMucChiTieuToiThieu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("VNĐ");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel20.setText("%");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(lbId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtMa))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14))
                            .addComponent(jLabel16)
                            .addComponent(jLabel19)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel13)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTen))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(rdoHoatDong)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                                                .addComponent(rdoHetHan)
                                                .addGap(50, 50, 50))
                                            .addComponent(lbThoiGianTao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lbThoiGianSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbbLoaiGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(txtMucGiamGia, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtMucChiTieuToiThieu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE))
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addGap(17, 17, 17)
                                                        .addComponent(jLabel9))
                                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jLabel20)))))))
                                .addGap(0, 3, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(13, 13, 13)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel4)
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lbId, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtMucChiTieuToiThieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(cbbLoaiGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtMucGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdoHoatDong)
                        .addComponent(rdoHetHan)))
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbThoiGianTao, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(1, 1, 1))
                    .addComponent(lbThoiGianSua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnHuy, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnClear, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(11, 11, 11))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rdoHetHanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoHetHanActionPerformed

    }//GEN-LAST:event_rdoHetHanActionPerformed

    private void rdoHoatDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoHoatDongActionPerformed
        //  code
    }//GEN-LAST:event_rdoHoatDongActionPerformed

    private void cbbTimKiemTheoTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTimKiemTheoTrangThaiActionPerformed
        String trangThai = (String) cbbTimKiemTheoTrangThai.getSelectedItem();
        if (!trangThai.equalsIgnoreCase("Tất cả")) {
            cbbLocTheoLoaiVoucher.setSelectedIndex(0);
            txtTimKiemVoucherTheoMa.setText("");
            listVoucher = service.locTimKiemTheoTrangThai(trangThai);
            fillTable(listVoucher);
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Lọc trạng thái thành công");
        } else {
            fillTable(service.getAll()); // phần lọc của tất cả
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Lọc trạng thái thành công");
        }
    }//GEN-LAST:event_cbbTimKiemTheoTrangThaiActionPerformed

    private void cbbLocTheoLoaiVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocTheoLoaiVoucherActionPerformed
        String loai = (String) cbbLocTheoLoaiVoucher.getSelectedItem();
        if (!loai.equalsIgnoreCase("Tất cả")) {
            cbbTimKiemTheoTrangThai.setSelectedIndex(0);
            txtTimKiemVoucherTheoMa.setText("");
            listVoucher = service.locTimKiemTheoLoaiVoucher(loai);
            fillTable(listVoucher);
        } else {
            listVoucher = service.getAll();
            fillTable(listVoucher);
        }
    }//GEN-LAST:event_cbbLocTheoLoaiVoucherActionPerformed

    private void txtTimKiemVoucherTheoMaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemVoucherTheoMaKeyReleased
        clear();
        String ma = txtTimKiemVoucherTheoMa.getText();
        listVoucher = service.locTimKiemTheoMa(ma);
        fillTable(listVoucher);
    }//GEN-LAST:event_txtTimKiemVoucherTheoMaKeyReleased

    private void btnSortGiamDanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSortGiamDanActionPerformed
        listVoucher = service.sortLamMoi();
        fillTable(listVoucher);
        show(0);
        Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Làm mới dữ liệu thành công");
    }//GEN-LAST:event_btnSortGiamDanActionPerformed
    // di chuyen ban phim trên JTable
    private void tblVoucherKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblVoucherKeyPressed
        int row = tblVoucher.getSelectedRow();
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_UP) {
            row = (row > 0) ? row - 1 : row;
        } else if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_DOWN) {
            row = (row < tblVoucher.getRowCount() - 1) ? row + 1 : row;
        }
        show(row);
        tblVoucher.setRowSelectionInterval(row, row); // Để di chuyển dòng được chọn trên JTable
    }//GEN-LAST:event_tblVoucherKeyPressed

    private void tblVoucherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVoucherMouseClicked
        int row = tblVoucher.getSelectedRow();
        show(row);
    }//GEN-LAST:event_tblVoucherMouseClicked
    // Xóa voucher
    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        int thongBao = JOptionPane.showConfirmDialog(this, "Xác nhận hủy voucher", "Thông báo xác nhận", JOptionPane.YES_OPTION);
        if (thongBao == JOptionPane.YES_OPTION) {
            int row = tblVoucher.getSelectedRow();
            if (row > -1) {
                try {
                    int id = (int) tblVoucher.getValueAt(row, 0);
                    service.xoa(id);
                    listVoucher = service.getAll();
                    fillTable(listVoucher);
                    clear();
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Hủy voucher thành công");
                } catch (SQLException e) {
                    e.printStackTrace();
                    Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Hủy voucher thất bại");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Vui lòng chọn voucher cần hủy");
                return;
            }
        }
    }//GEN-LAST:event_btnHuyActionPerformed
//    Sửa voucher
    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        int thongBao = JOptionPane.showConfirmDialog(this, "Xác nhận cập nhật voucher", "Thông báo xác nhận", JOptionPane.YES_OPTION);
        if (thongBao == JOptionPane.YES_OPTION) {
            int row = tblVoucher.getSelectedRow();
            if (row != -1) {
                Voucher v = getForm();
                if (v != null) {
                    try {
                        int id = (int) tblVoucher.getValueAt(row, 0);
                        String trangThai = rdoHoatDong.isSelected() ? "Hoạt động" : "Hết hạn";
                        String loaiVoucher = cbbLoaiGiamGia.getSelectedItem().toString();
                        service.capNhat(v, loaiVoucher, trangThai, id);
                        listVoucher = service.getAll();
                        fillTable(listVoucher);
                        show(row);
                        Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Cập nhật voucher thành công");
                    } catch (SQLException e) {
                        e.printStackTrace();
                        Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Cập nhật voucher thất bại!!!");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        int thongBao = JOptionPane.showConfirmDialog(this, "Xác nhận xóa trắng", "Thông báo xác nhận", JOptionPane.YES_OPTION);
        if (thongBao == JOptionPane.YES_OPTION) {
            clear();
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Xóa trắng thành công");
        }
    }//GEN-LAST:event_btnClearActionPerformed
//  Thêm voucher
    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        int thongBao = JOptionPane.showConfirmDialog(this, "Xác nhận thêm voucher", "Thông báo xác nhận", JOptionPane.YES_OPTION);
        if (thongBao == JOptionPane.YES_OPTION) {
            Voucher v = getForm();
            if (v != null) {
                try {
                    String trangThai = rdoHoatDong.isSelected() ? "Hoạt động" : "Hết hạn";
                    String loaiVoucher = cbbLoaiGiamGia.getSelectedItem().toString();
                    service.them(v, loaiVoucher, trangThai);
                    listVoucher = service.getAll();
                    fillTable(listVoucher);
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Thêm voucher thành công");
                } catch (SQLException e) {
                    e.printStackTrace();
                    Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Thêm thất bại");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                return;
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void dateKetThucPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateKetThucPropertyChange
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Calendar day = dateKetThuc.getCalendar();
        if (day != null) {
            Calendar ngay = day;
            ngay.set(Calendar.HOUR_OF_DAY, 0);
            ngay.set(Calendar.MINUTE, 0);
            ngay.set(Calendar.SECOND, 0);
            ngay.set(Calendar.MILLISECOND, 0);
            String ngayFormat = dateFormat.format(ngay.getTime());
            txtDateKetThuc.setText(ngayFormat);
        }
    }//GEN-LAST:event_dateKetThucPropertyChange

    private void dateBatDauPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateBatDauPropertyChange
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Calendar day = dateBatDau.getCalendar();
        if (day != null) {
            Calendar ngay = day;
            ngay.set(Calendar.HOUR_OF_DAY, 0);
            ngay.set(Calendar.MINUTE, 0);
            ngay.set(Calendar.SECOND, 0);
            ngay.set(Calendar.MILLISECOND, 0);
            String ngayFormat = dateFormat.format(ngay.getTime());
            txtDateBatDau.setText(ngayFormat);
        }
    }//GEN-LAST:event_dateBatDauPropertyChange

    private void cbbLoaiGiamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLoaiGiamGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbLoaiGiamGiaActionPerformed

    private void txtMucGiamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMucGiamGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMucGiamGiaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnSortGiamDan;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbbLoaiGiamGia;
    private javax.swing.JComboBox<String> cbbLocTheoLoaiVoucher;
    private javax.swing.JComboBox<String> cbbTimKiemTheoTrangThai;
    private com.toedter.calendar.JDateChooser dateBatDau;
    private com.toedter.calendar.JDateChooser dateKetThuc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lbId;
    private javax.swing.JLabel lbThoiGianSua;
    private javax.swing.JLabel lbThoiGianTao;
    private javax.swing.JRadioButton rdoHetHan;
    private javax.swing.JRadioButton rdoHoatDong;
    private javax.swing.JTable tblVoucher;
    private javax.swing.JTextField txtDateBatDau;
    private javax.swing.JTextField txtDateKetThuc;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtMucChiTieuToiThieu;
    private javax.swing.JTextField txtMucGiamGia;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTimKiemVoucherTheoMa;
    // End of variables declaration//GEN-END:variables
}
