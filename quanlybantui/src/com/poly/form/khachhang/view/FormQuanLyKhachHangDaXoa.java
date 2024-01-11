package com.poly.form.khachhang.view;

import com.poly.form.khachhang.view.FormQuanLyKhachHang;
import com.formdev.flatlaf.FlatClientProperties;
import com.poly.Application;
import com.poly.form.khachhang.entity.KhachHangHung;
import com.poly.form.khachhang.service.KhachHangService;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import raven.toast.Notifications;

public class FormQuanLyKhachHangDaXoa extends javax.swing.JPanel {

    private final KhachHangService khachHangService = new KhachHangService();

    public FormQuanLyKhachHangDaXoa() {
        initComponents();
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        fillThongTinKhachHangToTable();

        tblKhachHangDaXoa.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void fillThongTinKhachHangToTable() {
        DefaultTableModel model = (DefaultTableModel) tblKhachHangDaXoa.getModel();
        model.setRowCount(0);
        int stt = 1;
        for (KhachHangHung kh : khachHangService.getAllKhachHangDaXoa()) {
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
                capBac, kh.getThoiGianTao()};
            model.addRow(data);
            stt++;
        }
    }

    private void fillDataSearchToTable(List<KhachHangHung> list) {
        DefaultTableModel model = (DefaultTableModel) tblKhachHangDaXoa.getModel();
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
                capBac, kh.getThoiGianTao()};
            model.addRow(data);
            stt++;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKhachHangDaXoa = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTimKiemKhachHangDaXoa = new javax.swing.JTextField();
        btnKhoiPhuc = new javax.swing.JButton();
        cbxKhoiPhuc = new javax.swing.JComboBox<>();
        btnTroLaiTrangQuanLyKhachHang = new javax.swing.JButton();

        lb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("Quản lý khách hàng");

        tblKhachHangDaXoa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã KH", "Họ và tên", "Giới tính", "SĐT", "Địa chỉ", "Email", "Ngày sinh", "Cấp bực", "Ngày tạo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblKhachHangDaXoa);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Thông tin khách hàng đã xóa");

        jLabel2.setText("Tìm kiếm");

        txtTimKiemKhachHangDaXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemKhachHangDaXoaActionPerformed(evt);
            }
        });
        txtTimKiemKhachHangDaXoa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKhachHangDaXoaKeyReleased(evt);
            }
        });

        btnKhoiPhuc.setText("Khôi phục");
        btnKhoiPhuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhoiPhucActionPerformed(evt);
            }
        });

        cbxKhoiPhuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Khôi phục chọn", "Khôi phục tất cả" }));

        btnTroLaiTrangQuanLyKhachHang.setText("Trở lại trang quản lý khách hàng");
        btnTroLaiTrangQuanLyKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTroLaiTrangQuanLyKhachHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, 984, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTimKiemKhachHangDaXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnTroLaiTrangQuanLyKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnKhoiPhuc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxKhoiPhuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lb)
                .addGap(45, 45, 45)
                .addComponent(jLabel1)
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTimKiemKhachHangDaXoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbxKhoiPhuc, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnKhoiPhuc, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnTroLaiTrangQuanLyKhachHang)))
                .addContainerGap(98, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemKhachHangDaXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemKhachHangDaXoaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemKhachHangDaXoaActionPerformed

    private void btnTroLaiTrangQuanLyKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTroLaiTrangQuanLyKhachHangActionPerformed
        Application.showForm(new FormQuanLyKhachHang());
    }//GEN-LAST:event_btnTroLaiTrangQuanLyKhachHangActionPerformed

    private void txtTimKiemKhachHangDaXoaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKhachHangDaXoaKeyReleased
        String keyword = txtTimKiemKhachHangDaXoa.getText().trim();
        if (keyword.isEmpty()) {
            fillThongTinKhachHangToTable();
        } else {
            List<KhachHangHung> listResult = khachHangService.searchByNameKhachHangDaXoa(keyword);
            if (listenerList != null) {
                fillDataSearchToTable(listResult);
            }

        }
    }//GEN-LAST:event_txtTimKiemKhachHangDaXoaKeyReleased

    private void btnKhoiPhucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhoiPhucActionPerformed
        if (khachHangService.getAllKhachHangDaXoa().isEmpty() || khachHangService.getAllKhachHangDaXoa() == null) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Danh sách trống");
            return;
        }

        String luaChon = (String) cbxKhoiPhuc.getSelectedItem();

        if (luaChon.equalsIgnoreCase("Khôi phục chọn")) {
            int row = tblKhachHangDaXoa.getSelectedRow();
            if (row == -1) {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Vui lòng chọn dòng để khôi phục");
                return;
            } else {
                String ma = (String) tblKhachHangDaXoa.getValueAt(row, 1);

                int dialogResult = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn khôi phục khách hàng có mã " + ma + "?", "Xác nhận", JOptionPane.YES_NO_OPTION);

                if (dialogResult == JOptionPane.YES_OPTION) {
                    if (khachHangService.updateIsDeleteKhachHang(ma)) {
                        Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Khôi phục khách hàng có mã " + ma + " Thành công");
                        fillDataSearchToTable(khachHangService.getAllKhachHangDaXoa());
                    }
                }
            }
        } else if (luaChon.equalsIgnoreCase("Khôi phục tất cả")) {

            int dialogResult = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn khôi phục tất cả khách hàng?", "Xác nhận", JOptionPane.YES_NO_OPTION);

            if (dialogResult == JOptionPane.YES_OPTION) {
                if (khachHangService.updateAllIsDeleteKhachHang()) {
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Khôi phục tất cả khách hàng thành công");
                    fillDataSearchToTable(khachHangService.getAllKhachHangDaXoa());
                }
            }
        } else {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Vui lòng lựa chọn hình thức khôi phục");
        }

    }//GEN-LAST:event_btnKhoiPhucActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKhoiPhuc;
    private javax.swing.JButton btnTroLaiTrangQuanLyKhachHang;
    private javax.swing.JComboBox<String> cbxKhoiPhuc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lb;
    private javax.swing.JTable tblKhachHangDaXoa;
    private javax.swing.JTextField txtTimKiemKhachHangDaXoa;
    // End of variables declaration//GEN-END:variables
}
