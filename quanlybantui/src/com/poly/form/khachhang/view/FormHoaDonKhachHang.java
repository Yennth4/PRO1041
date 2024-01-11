package com.poly.form.khachhang.view;

import com.formdev.flatlaf.FlatClientProperties;
import com.poly.Application;
import com.poly.form.khachhang.entity.HoaDonKhachHang;
import com.poly.form.khachhang.entity.KhachHangHung;
import com.poly.form.khachhang.entity.LichSuGiaoDich;
import com.poly.form.khachhang.entity.SanPhamKhachHang;
import com.poly.form.khachhang.service.KhachHangService;
import java.text.NumberFormat;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import java.util.Locale;
import javax.swing.ListSelectionModel;

public class FormHoaDonKhachHang extends javax.swing.JPanel {

    private KhachHangHung khachHang;
    KhachHangService khachHangService = new KhachHangService();
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("vi-VN"));

    public FormHoaDonKhachHang(KhachHangHung khachHang) {
        this.khachHang = khachHang;
        initComponents();
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");

        lbHoTenKhachHang.setText(khachHang.getHoTen());
        lbIdKhachHang.setText(khachHang.getMa() + "");
        fillDataHoaDonToTable(khachHang.getId());
        fillLichSuGiaoDichByIdKhachHang(khachHang.getId());

        tblHoaDonKhachHang.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblLichSuGiaoDich.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    void fillDataHoaDonToTable(int idKh) {
        DefaultTableModel model = (DefaultTableModel) tblHoaDonKhachHang.getModel();
        List<HoaDonKhachHang> lits = khachHangService.getHoaDonByIdKhachHang(idKh);
        model.setRowCount(0);
        int stt = 1;

        for (HoaDonKhachHang hd : lits) {
            Object data[] = {
                stt,
                hd.getMaHoaDon(),
                hd.getTongSanPham(),
                hd.getTenNhanVien(),
                currencyFormatter.format(hd.getTongTienHoaDon()),
                currencyFormatter.format(hd.getTongTienSauKhuyenMai()), 
                currencyFormatter.format(hd.getSoTienDaGiam()), 
                currencyFormatter.format(hd.getTongTienPhaiTra()),
                hd.getGhiChu()
            };

            model.addRow(data);
            stt++;
        }
    }
    
    public void fillLichSuGiaoDichByIdKhachHang(int id) {
        DefaultTableModel model = (DefaultTableModel) tblLichSuGiaoDich.getModel();
        List<LichSuGiaoDich> list = khachHangService.getAllLichSuGiaoDich(id);
        model.setRowCount(0);
        int stt = 1;
        for(LichSuGiaoDich ls: list) {
            Object data[] = {stt, ls.getMaHoaDon(), ls.getNgayGiaoDich(),  currencyFormatter.format(ls.getTongTienGiaoDich()) , ls.getCapBacKhachHang()};
            model.addRow(data);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTimKiemTheoMaHoaDon = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHoaDonKhachHang = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblLichSuGiaoDich = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lbIdKhachHang = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbHoTenKhachHang = new javax.swing.JLabel();

        lb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("Khách hàng hóa đơn và lịch sử giao dịch");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jLabel1.setText("Hóa đơn cửa khách hàng");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jLabel2.setText("Mã Hóa Đơn");

        txtTimKiemTheoMaHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTimKiemTheoMaHoaDon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemTheoMaHoaDonKeyReleased(evt);
            }
        });

        tblHoaDonKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblHoaDonKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã HĐ", "Số loại sản phẩm", "Nhân viên tạo", "Tổng tiền hóa đơn", "Tổng tiền sau khuyến mãi", "Số tiền đã giảm", "Tổng tiền phải trả", "Ghi Chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDonKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonKhachHangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblHoaDonKhachHang);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jLabel3.setText("Lịch sử giao dịch");

        tblLichSuGiaoDich.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblLichSuGiaoDich.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã hóa đơn", "Ngày giao dịch", "Tổng tiền", "Cấp bực tại thời điểm giao dịch"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblLichSuGiaoDich);

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setText("Trở lại trang quản lý khách hàng");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel4.setText("Mã khách hàng:");

        lbIdKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbIdKhachHang.setForeground(new java.awt.Color(0, 0, 255));
        lbIdKhachHang.setText("kh001");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel6.setText("Họ Và Tên:");

        lbHoTenKhachHang.setFont(new java.awt.Font("Segoe UI", 2, 24)); // NOI18N
        lbHoTenKhachHang.setText("Nguyễn Phi Hùng");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, 1020, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane3)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbIdKhachHang))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbHoTenKhachHang)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiemTheoMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addComponent(jLabel3))
                        .addGap(0, 775, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lb)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbIdKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lbHoTenKhachHang))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTimKiemTheoMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(17, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Application.showForm(new FormQuanLyKhachHang());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tblHoaDonKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonKhachHangMouseClicked
//        if (evt.getClickCount() == 2) {
//            DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
//            model.setRowCount(0); // Clear the table initially
//
//            int row = tblHoaDonKhachHang.getSelectedRow();
//            if (row < 0) {
//                // If tblHoaDonKhachHang is not selected, do not populate tblSanPham
//                model.setRowCount(0);
//                lbHoaDon.setText("");
//                return; // Exit the function or perform other actions as needed
//            }
//
//            String ma = (String) tblHoaDonKhachHang.getValueAt(row, 1);
//            List<SanPhamKhachHang> list = khachHangService.getSanPhamKhachHangTuHoaDon(ma);
//            lbHoaDon.setText(ma);
//            int stt = 1;
//            for (SanPhamKhachHang sp : list) {
//                Object data[] = {stt, sp.getMaSanPham(), sp.getTenSanPham(), sp.getTheLoai(),sp.getMoTa()};
//                model.addRow(data);
//                stt++;
//            }
//        }
    }//GEN-LAST:event_tblHoaDonKhachHangMouseClicked

    private void txtTimKiemTheoMaHoaDonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemTheoMaHoaDonKeyReleased
        String maHoaDon = txtTimKiemTheoMaHoaDon.getText();
        DefaultTableModel model = (DefaultTableModel) tblHoaDonKhachHang.getModel();

        if (maHoaDon.isEmpty()) {
            fillDataHoaDonToTable(khachHang.getId());
        } else {
            List<HoaDonKhachHang> lits = khachHangService.getHoaDonByIdKhachHangAndMaHoaDon(khachHang.getId(), maHoaDon.toUpperCase());
            model.setRowCount(0);
            int stt = 1;

            for (HoaDonKhachHang hd : lits) {
                Object data[] = {
                    stt,
                    hd.getMaHoaDon(),
                    hd.getTongSanPham(),
                    hd.getTenNhanVien(),
                    currencyFormatter.format(hd.getTongTienHoaDon()), // Format currency here
                    currencyFormatter.format(hd.getTongTienSauKhuyenMai()), // Format currency here
                    currencyFormatter.format(hd.getSoTienDaGiam()), // Format currency here
                    currencyFormatter.format(hd.getTongTienPhaiTra()), // Format currency here
                    hd.getGhiChu()
                };

                model.addRow(data);
                stt++;
            }
        }
    }//GEN-LAST:event_txtTimKiemTheoMaHoaDonKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lbHoTenKhachHang;
    private javax.swing.JLabel lbIdKhachHang;
    private javax.swing.JTable tblHoaDonKhachHang;
    private javax.swing.JTable tblLichSuGiaoDich;
    private javax.swing.JTextField txtTimKiemTheoMaHoaDon;
    // End of variables declaration//GEN-END:variables
}
