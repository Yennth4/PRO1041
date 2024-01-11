package com.poly.form.voucher.view;

import com.formdev.flatlaf.FlatClientProperties;
import com.poly.form.voucher.entity.LoaiVoucher;
import com.poly.form.voucher.entity.TrangThaiVoucher;
import com.poly.form.voucher.repository.LoaiRepository;
import com.poly.form.voucher.repository.TrangThaiRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import raven.toast.Notifications;

public class FormTrangThaiVoucher extends javax.swing.JPanel {

    TrangThaiRepository serviceTrangThai = new TrangThaiRepository();
    LoaiRepository serviceLoai = new LoaiRepository();
    List<TrangThaiVoucher> listTrangThai = new ArrayList<>();
    List<LoaiVoucher> listLoai = new ArrayList<>();

    public FormTrangThaiVoucher() {
        initComponents();
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        listLoai = serviceLoai.getAll();
        fillTableLoai(listLoai);
        listTrangThai = serviceTrangThai.getAll();
        fillTableTrangThai(listTrangThai);
    }

    public void fillTableTrangThai(List<TrangThaiVoucher> list) {
        DefaultTableModel dtm = (DefaultTableModel) tblTrangThai.getModel();
        dtm.setRowCount(0);
        for (TrangThaiVoucher trangThai : list) {
            dtm.addRow(trangThai.toDataRow());
        }
    }

    public void fillTableLoai(List<LoaiVoucher> list) {
        DefaultTableModel dtm = (DefaultTableModel) tblLoai.getModel();
        dtm.setRowCount(0);
        for (LoaiVoucher loai : list) {
            dtm.addRow(loai.toDataRow());
        }
    }

    public void showTrangThai(int index) {
        TrangThaiVoucher trangThai = serviceTrangThai.getAll().get(index);
        txtId.setText(trangThai.getIdTrangThai() + "");
        txtMaTT.setText(trangThai.getMaTrangThai());
        txtTenTT.setText(trangThai.getTenTrangThai());
        txtMoTaTT.setText(trangThai.getMoTaTrangThai());
    }

    public void showLoai(int index) {
        LoaiVoucher loai = serviceLoai.getAll().get(index);
        txtId1.setText(loai.getIdLoai() + "");
        txtMaL.setText(loai.getMaLoai());
        txtTenL.setText(loai.getTenLoai());
        txtMoTaL.setText(loai.getMoTaLoai());
    }

    public TrangThaiVoucher getForm() {
        String ma = txtMaTT.getText();
        String ten = txtTenTT.getText();
        String moTa = txtMoTaTT.getText();

        return new TrangThaiVoucher(ma, ten, moTa);
    }

    public LoaiVoucher getFormL() {
        String ma = txtMaL.getText();
        String ten = txtTenL.getText();
        String moTa = txtMoTaL.getText();

        return new LoaiVoucher(ma, ten, moTa);
    }

    public void clearTT() {
        txtId.setText("");
        txtMaTT.setText("");
        txtTenTT.setText("");
        txtMoTaTT.setText("");
    }

    public void clearLoai() {
        txtId1.setText("");
        txtMaL.setText("");
        txtTenL.setText("");
        txtMoTaL.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMaTT = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtId = new javax.swing.JLabel();
        txtTenTT = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMoTaTT = new javax.swing.JTextArea();
        btnThemTrangThai = new javax.swing.JButton();
        btnSuaTrangThai = new javax.swing.JButton();
        btnHuyTrangThai = new javax.swing.JButton();
        btnClearTrangThai = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTrangThai = new javax.swing.JTable();
        btnLamMoiTrangThai = new javax.swing.JButton();
        txtTimKiemTheoMaTT = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtMaL = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtId1 = new javax.swing.JLabel();
        txtTenL = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtMoTaL = new javax.swing.JTextArea();
        btnThemL = new javax.swing.JButton();
        btnSuaL = new javax.swing.JButton();
        btnHuyLoaiVoucher = new javax.swing.JButton();
        btnClearL = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblLoai = new javax.swing.JTable();
        btnLamMoiLoai = new javax.swing.JButton();
        txtTimKiemTheoMaLoai = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin chi tiết ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 3, 18))); // NOI18N
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("Danh sách voucher nè ");
        add(lb, new org.netbeans.lib.awtextra.AbsoluteConstraints(1256, 27, 0, 25));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Trạng thái voucher", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Id trạng thái : ");

        txtMaTT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Mã trạng thái :");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Tên trạng thái :");

        txtId.setToolTipText("");

        txtTenTT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Mô tả:");

        txtMoTaTT.setColumns(20);
        txtMoTaTT.setRows(5);
        jScrollPane2.setViewportView(txtMoTaTT);

        btnThemTrangThai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThemTrangThai.setText("Thêm");
        btnThemTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemTrangThaiActionPerformed(evt);
            }
        });

        btnSuaTrangThai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSuaTrangThai.setText("Sửa");
        btnSuaTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaTrangThaiActionPerformed(evt);
            }
        });

        btnHuyTrangThai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnHuyTrangThai.setText("Hủy");
        btnHuyTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyTrangThaiActionPerformed(evt);
            }
        });

        btnClearTrangThai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnClearTrangThai.setText("Clear");
        btnClearTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearTrangThaiActionPerformed(evt);
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
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane2)
                                    .addComponent(txtTenTT)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(26, 26, 26)
                                .addComponent(txtMaTT)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSuaTrangThai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnHuyTrangThai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnClearTrangThai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThemTrangThai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtId))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMaTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemTrangThai))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTenTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaTrangThai))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnHuyTrangThai)
                        .addGap(18, 18, 18)
                        .addComponent(btnClearTrangThai)))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Danh sách chi tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblTrangThai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Mã trạng thái ", "Tên trạng thái ", "Mô tả "
            }
        ));
        tblTrangThai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTrangThaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTrangThai);

        btnLamMoiTrangThai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLamMoiTrangThai.setText("Làm mới ");
        btnLamMoiTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiTrangThaiActionPerformed(evt);
            }
        });

        txtTimKiemTheoMaTT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemTheoMaTTKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Tìm kiếm mã :");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(btnLamMoiTrangThai)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(txtTimKiemTheoMaTT, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTimKiemTheoMaTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoiTrangThai))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 40, -1, -1));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Loại voucher", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel6.setText("Id loại voucher:");

        txtMaL.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel7.setText("Mã loại voucher:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Tên loại voucher:");

        txtId1.setToolTipText("");

        txtTenL.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Mô tả:");

        txtMoTaL.setColumns(20);
        txtMoTaL.setRows(5);
        jScrollPane3.setViewportView(txtMoTaL);

        btnThemL.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThemL.setText("Thêm");
        btnThemL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemLActionPerformed(evt);
            }
        });

        btnSuaL.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSuaL.setText("Sửa");
        btnSuaL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaLActionPerformed(evt);
            }
        });

        btnHuyLoaiVoucher.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnHuyLoaiVoucher.setText("Hủy");
        btnHuyLoaiVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyLoaiVoucherActionPerformed(evt);
            }
        });

        btnClearL.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnClearL.setText("Clear");
        btnClearL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearLActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txtId1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaL)
                            .addComponent(jScrollPane3)
                            .addComponent(txtTenL))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnThemL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSuaL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnHuyLoaiVoucher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnClearL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(49, 49, 49))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtId1))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtMaL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemL))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTenL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaL))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnHuyLoaiVoucher)
                        .addGap(18, 18, 18)
                        .addComponent(btnClearL)))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Danh sách chi tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblLoai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Mã loại voucher", "Tên loại voucher", "Mô tả "
            }
        ));
        tblLoai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLoaiMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblLoai);

        btnLamMoiLoai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLamMoiLoai.setText("Làm mới ");
        btnLamMoiLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiLoaiActionPerformed(evt);
            }
        });

        txtTimKiemTheoMaLoai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemTheoMaLoaiKeyReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Tìm kiếm mã :");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(btnLamMoiLoai)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(txtTimKiemTheoMaLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtTimKiemTheoMaLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoiLoai))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 40, -1, 625));
    }// </editor-fold>//GEN-END:initComponents

    private void tblTrangThaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTrangThaiMouseClicked
        int row = tblTrangThai.getSelectedRow();
        showTrangThai(row);
    }//GEN-LAST:event_tblTrangThaiMouseClicked

    private void tblLoaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLoaiMouseClicked
        int row = tblLoai.getSelectedRow();
        showLoai(row);
    }//GEN-LAST:event_tblLoaiMouseClicked

    private void btnThemTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemTrangThaiActionPerformed
        TrangThaiVoucher t = getForm();
        try {
            serviceTrangThai.them(t);
            fillTableTrangThai(serviceTrangThai.getAll());
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Thêm trạng thái thành công");
        } catch (Exception ex) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Thêm trạng thái thất bại !!!");
            return;
        }
    }//GEN-LAST:event_btnThemTrangThaiActionPerformed

    private void btnSuaTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaTrangThaiActionPerformed
        int row = tblTrangThai.getSelectedRow();
        if (row > -1) {
            int id = Integer.valueOf(tblTrangThai.getValueAt(row, 0).toString());
            TrangThaiVoucher tt = getForm();
            try {
                serviceTrangThai.sua(id, tt);
                fillTableTrangThai(serviceTrangThai.getAll());
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Sửa trạng thái thành công");
            } catch (Exception ex) {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Sửa trạng thái thất bại !!!");
                return;
            }
        } else {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_RIGHT, "Chọn trạng thái cần sửa");
            return;
        }
    }//GEN-LAST:event_btnSuaTrangThaiActionPerformed

    private void btnHuyTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyTrangThaiActionPerformed
        int row = tblTrangThai.getSelectedRow();
        if (row > -1) {
            int id = Integer.valueOf(tblTrangThai.getValueAt(row, 0).toString());
            try {
                serviceTrangThai.xoa(id);
                fillTableTrangThai(serviceTrangThai.getAll());
                clearTT();
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Hủy trạng thái thành công");
            } catch (Exception ex) {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Hủy trạng thái thất bại !!!");
                return;
            }
        } else {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_RIGHT, "Chọn trạng thái cần hủy");
            return;
        }
    }//GEN-LAST:event_btnHuyTrangThaiActionPerformed

    private void btnClearTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearTrangThaiActionPerformed
        clearTT();
        Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Xóa trắng thành công");
    }//GEN-LAST:event_btnClearTrangThaiActionPerformed

    private void btnThemLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemLActionPerformed
        LoaiVoucher l = getFormL();
        try {
            serviceLoai.them(l);
            fillTableLoai(serviceLoai.getAll());
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Thêm loại voucher thành công");
        } catch (Exception ex) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Thêm loại voucher thất bại");
            return;
        }
    }//GEN-LAST:event_btnThemLActionPerformed

    private void btnHuyLoaiVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyLoaiVoucherActionPerformed
        int row = tblLoai.getSelectedRow();
        if (row > -1) {
            int id = Integer.valueOf(tblLoai.getValueAt(row, 0).toString());
            try {
                serviceLoai.xoa(id);
                fillTableLoai(serviceLoai.getAll());
                clearLoai();
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Hủy loại voucher thành công");
            } catch (Exception ex) {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Hủy loại voucher thất bại");
                return;
            }
        } else {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_RIGHT, "Chọn loại voucher cần hủy");
            return;
        }
    }//GEN-LAST:event_btnHuyLoaiVoucherActionPerformed

    private void btnClearLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearLActionPerformed
        clearLoai();
        Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Xóa trắng thành công");
    }//GEN-LAST:event_btnClearLActionPerformed

    private void txtTimKiemTheoMaTTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemTheoMaTTKeyReleased
        String maTT = txtTimKiemTheoMaTT.getText();
        listTrangThai = serviceTrangThai.timKiemTheoMa(maTT);
        fillTableTrangThai(listTrangThai);
    }//GEN-LAST:event_txtTimKiemTheoMaTTKeyReleased

    private void txtTimKiemTheoMaLoaiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemTheoMaLoaiKeyReleased
        String maLoai = txtTimKiemTheoMaLoai.getText();
        listLoai = serviceLoai.timKiemTheoMa(maLoai);
        fillTableLoai(listLoai);
    }//GEN-LAST:event_txtTimKiemTheoMaLoaiKeyReleased

    private void btnSuaLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaLActionPerformed
        int row = tblLoai.getSelectedRow();
        if (row > -1) {
            int id = Integer.valueOf(tblLoai.getValueAt(row, 0).toString());
            LoaiVoucher l = getFormL();
            try {
                serviceLoai.sua(id, l);
                fillTableLoai(serviceLoai.getAll());
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Sửa loại voucher thành công");
            } catch (Exception ex) {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Sửa loại voucher thất bại");
                return;
            }
        } else {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_RIGHT, "Chọn loại voucher cần sửa");
            return;
        }
    }//GEN-LAST:event_btnSuaLActionPerformed

    private void btnLamMoiTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiTrangThaiActionPerformed
        clearTT();
        txtTimKiemTheoMaTT.setText("");
        listTrangThai = serviceTrangThai.getAll();
        fillTableTrangThai(listTrangThai);
        Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Làm mới trạng thái voucher thành công");
    }//GEN-LAST:event_btnLamMoiTrangThaiActionPerformed

    private void btnLamMoiLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiLoaiActionPerformed
        clearLoai();
        txtTimKiemTheoMaLoai.setText("");
        listLoai = serviceLoai.getAll();
        fillTableLoai(listLoai);
        Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Làm mới loại voucher thành công");
    }//GEN-LAST:event_btnLamMoiLoaiActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClearL;
    private javax.swing.JButton btnClearTrangThai;
    private javax.swing.JButton btnHuyLoaiVoucher;
    private javax.swing.JButton btnHuyTrangThai;
    private javax.swing.JButton btnLamMoiLoai;
    private javax.swing.JButton btnLamMoiTrangThai;
    private javax.swing.JButton btnSuaL;
    private javax.swing.JButton btnSuaTrangThai;
    private javax.swing.JButton btnThemL;
    private javax.swing.JButton btnThemTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lb;
    private javax.swing.JTable tblLoai;
    private javax.swing.JTable tblTrangThai;
    private javax.swing.JLabel txtId;
    private javax.swing.JLabel txtId1;
    private javax.swing.JTextField txtMaL;
    private javax.swing.JTextField txtMaTT;
    private javax.swing.JTextArea txtMoTaL;
    private javax.swing.JTextArea txtMoTaTT;
    private javax.swing.JTextField txtTenL;
    private javax.swing.JTextField txtTenTT;
    private javax.swing.JTextField txtTimKiemTheoMaLoai;
    private javax.swing.JTextField txtTimKiemTheoMaTT;
    // End of variables declaration//GEN-END:variables
}
