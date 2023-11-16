package com.poly.form.khuyenmai;

import com.formdev.flatlaf.FlatClientProperties;
import com.poly.entity.SanPham;
import com.poly.entity.Voucher;
import com.poly.repository.VoucherRepository;
import java.awt.Dimension;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    VoucherRepository service = new VoucherRepository();
    List<Voucher> listVoucher = new ArrayList<>();

    public FormQuanLyVoucher() {
        initComponents();
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        loadComboBoxThoiGian();
        listVoucher = service.getAll();
        fillTable(listVoucher);
        sanPham();
    }

    public void loadComboBoxThoiGian() {
        DefaultComboBoxModel cbb = (DefaultComboBoxModel) cbbTimeBatDau.getModel();
        DefaultComboBoxModel cbb1 = (DefaultComboBoxModel) cbbTimeKetThuc.getModel();
        cbb.removeAllElements();
        cbb1.removeAllElements();
        cbb.addElement("Thời gian");
        cbb1.addElement("Thời gian");
        for (int h = 1; h <= 12; h++) {
            for (int m = 0; m < 60; m += 30) {
                String time = String.format("%02d:%02d", h, m);
                cbb.addElement(time + " AM");
                cbb1.addElement(time + " AM");
            }
        }
        for (int h = 1; h <= 12; h++) {
            for (int m = 0; m < 60; m += 30) {
                String time = String.format("%02d:%02d", h, m);
                cbb.addElement(time + " PM");
                cbb1.addElement(time + " PM");
            }
        }
    }

    public void fillTable(List<Voucher> list) {
        DefaultTableModel dtm = (DefaultTableModel) tblVoucher.getModel();
        dtm.setRowCount(0);
        for (Voucher voucher : list) {
            Object[] rowData = {
                voucher.getId(),
                voucher.getMa(),
                voucher.getTen(),
                voucher.getLoaiVoucher(),
                voucher.getPhanTramGiamGia(),
                voucher.getSoLuong(),
                voucher.getTrangThai(),
                voucher.getDateBatDau(),
                voucher.getDateKetThuc(),
                voucher.getDateSua(),
                voucher.getDateTao()
            };
            dtm.addRow(rowData);
        }
    }

    public void sanPham() {
        DefaultTableModel dtm = (DefaultTableModel) tblSanPham.getModel();
        dtm.setRowCount(0);
        for (SanPham o : service.fakeData()) {
            Object[] rowData = {
                o.isSelected(),
                o.getMa(),
                o.getTen(),
                o.getGia(),
                o.getHang(),
                o.getMauSac(),
                o.getMoTa(),
                o.getTrangThai()
            };
            dtm.addRow(rowData);
        }
    }

    public void show(int index) {
        Voucher v = listVoucher.get(index);
        lbId.setText(v.getId() + "");
        txtMa.setText(v.getMa());
        txtTen.setText(v.getTen());
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        lbThoiGianSua.setText(dateFormat.format(v.getDateSua()));
        lbThoiGianTao.setText(dateFormat.format(v.getDateTao()));
    }

    public void clear() {
        lbId.setText("");
        txtMa.setText("");
        txtTen.setText("");
        txtSoLuong.setText("");
        rdoHoatDong.setSelected(true);
        cbbLoaiGiamGia.setSelectedIndex(0);
        dateBatDau.setDate(null);
        dateKetThuc.setDate(null);
        lbThoiGianSua.setText("");
        lbThoiGianTao.setText("");
    }

    public Voucher getForm() {
        String ma = txtMa.getText().trim();
        String ten = txtTen.getText().trim();
        if (ma.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống mã!!!");
            return null;
        }
        if (ten.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống tên!!!");
            return null;
        }
        String soLuong = txtSoLuong.getText().trim();
        if (soLuong.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống số lượng!!!");
            return null;
        }
        String phanTramGiamGia = txtMucGiamGia.getText().trim();
        if (phanTramGiamGia.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống mức giảm giá!!!");
            return null;
        }
        String trangThai = rdoHoatDong.isSelected() ? "Hoạt động" : "Hết hạn";
        Date dateBatDau = this.dateBatDau.getDate();
        Date dateKetThuc = this.dateKetThuc.getDate();
        if (dateBatDau == null) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu không được để trống!!!");
            return null;
        }
        if (dateKetThuc == null) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc không được để trống!!!");
            return null;
        }
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        lbThoiGianSua.setText(formattedDateTime);
        lbThoiGianTao.setText(formattedDateTime);
        Date thoiGianTao = new Date();
        Date thoiGianSua = new Date();

        String loaiVoucher = cbbLoaiGiamGia.getSelectedItem().toString();
        return new Voucher(ma, ten, loaiVoucher, Integer.valueOf(phanTramGiamGia), dateBatDau, dateKetThuc, dateBatDau, dateBatDau, Integer.valueOf(soLuong), trangThai);
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
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cboSelectAll = new javax.swing.JCheckBox();
        btnDeSelectAll = new javax.swing.JButton();
        cbbHang = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txtTimKiemSanPham = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
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
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        dateBatDau = new com.toedter.calendar.JDateChooser();
        dateKetThuc = new com.toedter.calendar.JDateChooser();
        cbbTimeBatDau = new javax.swing.JComboBox<>();
        cbbTimeKetThuc = new javax.swing.JComboBox<>();
        btnLocTheoThoiGian = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblVoucher = new javax.swing.JTable();
        txtTimKiemVoucherTheoMa = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        cbbTimKiemTheoTrangThai = new javax.swing.JComboBox<>();
        btnSortGiamDan = new javax.swing.JButton();
        locHoatDong = new javax.swing.JRadioButton();
        locHetHan = new javax.swing.JRadioButton();
        btnPagePrevious = new javax.swing.JButton();
        btnPrevious = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnPageNext = new javax.swing.JButton();
        cbbLocTheoLoaiVoucher = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();

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

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thiết lập khuyến mại", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("ID :");

        txtMa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Mã khuyến mại :");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Mức giảm giá :");

        cboSelectAll.setText("Select All");
        cboSelectAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSelectAllActionPerformed(evt);
            }
        });

        btnDeSelectAll.setText("Bỏ chọn tất cả");
        btnDeSelectAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeSelectAllActionPerformed(evt);
            }
        });

        cbbHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Hãng 1 ", "Hãng 2", "Hãng 3", " " }));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Tìm kiếm : ");

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "", "Mã sản phẩm", "Tên sản phẩm", "Giá", "Hãng", "Màu sắc", "Mô tả", "Trạng thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblSanPham);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Thời gian sửa :");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Thời gian tạo : ");

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
        rdoHoatDong.setSelected(true);
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
        jLabel16.setText("Hình thức:");

        cbbLoaiGiamGia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbbLoaiGiamGia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn hình thức giảm giá", "Giảm giá theo tiền", "Giảm giá theo phần trăm" }));

        txtMucGiamGia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(cboSelectAll)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDeSelectAll, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbbHang, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiemSanPham))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel7)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtMa, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                                    .addComponent(lbId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jLabel13)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(rdoHoatDong)
                                                .addGap(18, 18, 18)
                                                .addComponent(rdoHetHan))
                                            .addComponent(cbbLoaiGiamGia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(71, 71, 71)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel11)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel6)
                                                .addGap(23, 23, 23))
                                            .addComponent(jLabel10))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtSoLuong)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lbThoiGianSua, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lbThoiGianTao, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 3, Short.MAX_VALUE))))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(23, 23, 23)
                                        .addComponent(jLabel5)
                                        .addGap(10, 10, 10)
                                        .addComponent(txtMucGiamGia)))))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
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
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(rdoHoatDong)
                                .addComponent(rdoHetHan))
                            .addComponent(jLabel13)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtMucGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(lbThoiGianSua, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lbThoiGianTao, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(1, 1, 1))
                    .addComponent(cbbLoaiGiamGia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTimKiemSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnDeSelectAll, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbHang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboSelectAll)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thời gian sử dụng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Thời gian bắt đầu giảm giá : ");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Thời gian kết thúc giảm giá : ");

        dateBatDau.setDateFormatString("yyyy-MM-dd");

        dateKetThuc.setDateFormatString("yyyy-MM-dd");

        cbbTimeBatDau.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbTimeKetThuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnLocTheoThoiGian.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnLocTheoThoiGian.setText("Lọc voucher");
        btnLocTheoThoiGian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocTheoThoiGianActionPerformed(evt);
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
                        .addComponent(dateBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbbTimeBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(btnLocTheoThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(dateKetThuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbbTimeKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbbTimeBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(dateBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbbTimeKetThuc, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(dateKetThuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(btnLocTheoThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
                "Id", "Mã voucher", "Tên voucher", "Hình thức giảm giá", "Mức giảm giá", "Số lượng", "Trạng thái", "Thời gian bắt đầu", "Thời gian kết thúc"
            }
        ));
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

        txtTimKiemVoucherTheoMa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemVoucherTheoMaKeyReleased(evt);
            }
        });

        jLabel12.setText("Tìm kiếm (mã voucher) : ");

        cbbTimKiemTheoTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Hoạt động", "Hết hạn" }));
        cbbTimKiemTheoTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTimKiemTheoTrangThaiActionPerformed(evt);
            }
        });

        btnSortGiamDan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/poly/icon/png/return.png"))); // NOI18N
        btnSortGiamDan.setText("Làm mới");
        btnSortGiamDan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSortGiamDanActionPerformed(evt);
            }
        });

        buttonGroup2.add(locHoatDong);
        locHoatDong.setText("Hoạt Động");
        locHoatDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                locHoatDongActionPerformed(evt);
            }
        });

        buttonGroup2.add(locHetHan);
        locHetHan.setText("Hết hạn");
        locHetHan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                locHetHanActionPerformed(evt);
            }
        });

        btnPagePrevious.setBackground(new java.awt.Color(204, 204, 204));
        btnPagePrevious.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnPagePrevious.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/poly/icon/png/back.png"))); // NOI18N
        btnPagePrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagePreviousActionPerformed(evt);
            }
        });

        btnPrevious.setBackground(new java.awt.Color(204, 204, 204));
        btnPrevious.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnPrevious.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/poly/icon/png/left.png"))); // NOI18N
        btnPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousActionPerformed(evt);
            }
        });

        btnNext.setBackground(new java.awt.Color(204, 204, 204));
        btnNext.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/poly/icon/png/right.png"))); // NOI18N
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnPageNext.setBackground(new java.awt.Color(204, 204, 204));
        btnPageNext.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnPageNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/poly/icon/png/next.png"))); // NOI18N
        btnPageNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPageNextActionPerformed(evt);
            }
        });

        cbbLocTheoLoaiVoucher.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbbLocTheoLoaiVoucher.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Giảm giá theo tiền", "Giảm giá theo phần trăm" }));
        cbbLocTheoLoaiVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocTheoLoaiVoucherActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("Hình thức:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(btnSortGiamDan, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbbTimKiemTheoTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(locHoatDong)
                        .addGap(49, 49, 49)
                        .addComponent(locHetHan)
                        .addGap(53, 53, 53)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbbLocTheoLoaiVoucher, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addGap(33, 33, 33)
                        .addComponent(txtTimKiemVoucherTheoMa, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnPagePrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnPageNext, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSortGiamDan)
                    .addComponent(cbbTimKiemTheoTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(locHoatDong)
                    .addComponent(locHetHan)
                    .addComponent(jLabel12)
                    .addComponent(txtTimKiemVoucherTheoMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbLocTheoLoaiVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPageNext, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPagePrevious, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(127, 127, 127)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(22, 22, 22)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnHuy, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))))
                                .addGap(0, 268, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblVoucherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVoucherMouseClicked
        int row = tblVoucher.getSelectedRow();
        show(row);
    }//GEN-LAST:event_tblVoucherMouseClicked

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        int thongBao = JOptionPane.showConfirmDialog(this, "Xác nhận xóa trắng", "Thông báo xác nhận", JOptionPane.YES_OPTION);
        if (thongBao == JOptionPane.YES_OPTION) {
            clear();
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Xóa trắng thành công");
        }
    }//GEN-LAST:event_btnClearActionPerformed

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
    // loc theo thoi gian 
    private void btnLocTheoThoiGianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocTheoThoiGianActionPerformed
        
    }//GEN-LAST:event_btnLocTheoThoiGianActionPerformed

    private void rdoHetHanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoHetHanActionPerformed

    }//GEN-LAST:event_rdoHetHanActionPerformed

    private void btnSortGiamDanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSortGiamDanActionPerformed
        listVoucher = service.sortLamMoi();
        fillTable(listVoucher);
        Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Làm mới dữ liệu thành công");
    }//GEN-LAST:event_btnSortGiamDanActionPerformed
// loc theo ten
    private void txtTimKiemVoucherTheoMaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemVoucherTheoMaKeyReleased
        String ma = txtTimKiemVoucherTheoMa.getText().trim();
        listVoucher = service.locTimKiemTheoMa(ma);
        fillTable(listVoucher);
    }//GEN-LAST:event_txtTimKiemVoucherTheoMaKeyReleased
// loc theo trangThai
    private void cbbTimKiemTheoTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTimKiemTheoTrangThaiActionPerformed
        String trangThai = (String) cbbTimKiemTheoTrangThai.getSelectedItem();
        if (!trangThai.equalsIgnoreCase("Tất cả")) {
            listVoucher = service.locTimKiemTheoTrangThai(trangThai);
            fillTable(listVoucher);
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Lọc trạng thái thành công");
        } else {
            fillTable(service.getAll()); // phần lọc của tất cả
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Lọc trạng thái thành công");
        }
    }//GEN-LAST:event_cbbTimKiemTheoTrangThaiActionPerformed

    private void rdoHoatDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoHoatDongActionPerformed

    }//GEN-LAST:event_rdoHoatDongActionPerformed

    private void locHoatDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_locHoatDongActionPerformed
        String trangThai = "";
        cbbTimKiemTheoTrangThai.setSelectedIndex(0);
        txtTimKiemVoucherTheoMa.setText("");
        if (locHetHan.isSelected()) {
            trangThai = "Hết hạn";
        } else if (locHoatDong.isSelected()) {
            trangThai = "Hoạt động";
        }
        listVoucher = service.locTimKiemTheoTrangThai(trangThai);
        fillTable(listVoucher);
    }//GEN-LAST:event_locHoatDongActionPerformed

    private void locHetHanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_locHetHanActionPerformed
        locHoatDongActionPerformed(evt);
    }//GEN-LAST:event_locHetHanActionPerformed
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

    private void btnPagePreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagePreviousActionPerformed

    }//GEN-LAST:event_btnPagePreviousActionPerformed

    private void btnPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousActionPerformed

    }//GEN-LAST:event_btnPreviousActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed

    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPageNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPageNextActionPerformed

    }//GEN-LAST:event_btnPageNextActionPerformed
// select all
    private void cboSelectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSelectAllActionPerformed
        Boolean selected = cboSelectAll.isSelected();
        for (SanPham sp : service.fakeData()) {
            sp.setSelected(selected);
        }
    }//GEN-LAST:event_cboSelectAllActionPerformed
// bỏ select all
    private void btnDeSelectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeSelectAllActionPerformed
        for (SanPham sp : service.fakeData()) {
            sp.setSelected(false);
        }
    }//GEN-LAST:event_btnDeSelectAllActionPerformed

    private void cbbLocTheoLoaiVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocTheoLoaiVoucherActionPerformed
        String loai = (String) cbbLocTheoLoaiVoucher.getSelectedItem();
        if (!loai.equalsIgnoreCase("Tất cả")) {
            listVoucher = service.locTimKiemTheoLoaiVoucher(loai);
            fillTable(listVoucher);
        } else {
            listVoucher = service.getAll();
            fillTable(listVoucher);
        }
    }//GEN-LAST:event_cbbLocTheoLoaiVoucherActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDeSelectAll;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLocTheoThoiGian;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPageNext;
    private javax.swing.JButton btnPagePrevious;
    private javax.swing.JButton btnPrevious;
    private javax.swing.JButton btnSortGiamDan;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbbHang;
    private javax.swing.JComboBox<String> cbbLoaiGiamGia;
    private javax.swing.JComboBox<String> cbbLocTheoLoaiVoucher;
    private javax.swing.JComboBox<String> cbbTimKiemTheoTrangThai;
    private javax.swing.JComboBox<String> cbbTimeBatDau;
    private javax.swing.JComboBox<String> cbbTimeKetThuc;
    private javax.swing.JCheckBox cboSelectAll;
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
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lbId;
    private javax.swing.JLabel lbThoiGianSua;
    private javax.swing.JLabel lbThoiGianTao;
    private javax.swing.JRadioButton locHetHan;
    private javax.swing.JRadioButton locHoatDong;
    private javax.swing.JRadioButton rdoHetHan;
    private javax.swing.JRadioButton rdoHoatDong;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTable tblVoucher;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtMucGiamGia;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTimKiemSanPham;
    private javax.swing.JTextField txtTimKiemVoucherTheoMa;
    // End of variables declaration//GEN-END:variables
}
