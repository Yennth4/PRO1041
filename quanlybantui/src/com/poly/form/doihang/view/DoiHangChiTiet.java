package com.poly.form.doihang.view;

import com.poly.form.doihang.view.ConfirmDoiHang;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.poly.form.doihang.entity.DoiHangResponse.HoaDon;
import com.poly.form.doihang.entity.DoiHangResponse.LyDoDoi;
import com.poly.form.doihang.entity.DoiHangResponse.MauSacResponse;
import com.poly.form.doihang.entity.DoiHangResponse.ProductDetailResponse;
import com.poly.form.doihang.entity.DoiHangResponse.TheLoaiResponse;
import com.poly.form.doihang.service.DoiHangService.impl.LyDoDoiService;
import com.poly.form.doihang.service.DoiHangService.impl.PhieuDoiChiTietService;
import com.poly.form.doihang.service.DoiHangService.impl.PhieuDoiService;
import com.poly.form.doihang.service.DoiHangService.impl.ProductDetailService;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sun.print.resources.serviceui;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;
import com.poly.menu.Menu;
import com.poly.form.doihang.repository.DoiHangRepository.HoaDonRepository;
import com.poly.form.doihang.repository.DoiHangRepository.MauSacRepo;
import com.poly.form.doihang.repository.DoiHangRepository.TheLoaiRepo;
import raven.toast.Notifications;

/**
 *
 * @author Raven
 */
public class DoiHangChiTiet extends javax.swing.JPanel {
    ChiTietHoaDon chiTietHoaDon ;
    private FormQuanLyDoiHang formQuanLyDoiHang;
    private Menu menu;
    private DoiHangConfigs doiHangConfigs;
    private ProductDetailService detailService = new ProductDetailService();
    private PhieuDoiService phieuDoiService = new PhieuDoiService();
    private PhieuDoiChiTietService phieuDoiChiTietService = new PhieuDoiChiTietService();
    private LyDoDoiService lyDoService = new LyDoDoiService();
    private DefaultTableModel model = new DefaultTableModel();
    private HoaDonRepository hoaDonRepository = new HoaDonRepository();
    private List<Long> listIdSPCT = new ArrayList<>();
    private Boolean doiHang = false;

   

    public DoiHangChiTiet(DoiHangConfigs doiHangConfigs, Menu menu,FormQuanLyDoiHang formQuanLyDoiHang,ChiTietHoaDon chiTietHoaDon) {
        this.doiHangConfigs = doiHangConfigs;
        this.menu = menu;
        initComponents();
        this.chiTietHoaDon=chiTietHoaDon;
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        lblHoaDon.setText(doiHangConfigs.getMaHoaDon());
        model = (DefaultTableModel) tblBuy.getModel();
        this.formQuanLyDoiHang=formQuanLyDoiHang;
        showDetail(doiHangConfigs); 
        showTable(detailService.findProductsById(doiHangConfigs.getId()));

    }

    public ProductDetailService getDetailService() {
        return detailService;
    }

    public void setDetailService(ProductDetailService detailService) {
        this.detailService = detailService;
    }
 

    public void showTable(List<ProductDetailResponse> list) {
        model.setRowCount(0);
        for (int i = 0; i < list.size(); i++) {
            ProductDetailResponse response = list.get(i);
            listIdSPCT.add(response.getIdsanphamdetail());
            model.addRow(new Object[]{i + 1, response.getMaSanPham(), response.getTenSanPham().trim(),
                response.getSoLuong(), response.getGiaTien()+" VND", response.getTenMau().trim() });
        }
    }

    public void showDetail(DoiHangConfigs doiHangConfigs) {
        HoaDon hoaDon = hoaDonRepository.finById(doiHangConfigs.getId());
        txtMaHoaDon.setText(hoaDon.getMaHoaDon());
        txtNgayTao.setText(hoaDon.getThoiGianTao() + "");
        txtTenKhachHang.setText(hoaDon.getTenKhachHang());
        txtTenNhanVien.setText(hoaDon.getSdt());
        txtTongTien.setText(hoaDon.getTongTienHoaDon() + "");
        txtTrangThai.setText(hoaDon.getTrangThai().equals("1")?"Hoàn thành":"chưa hoàn thành");
    }

    private List<ProductDetailResponse> GetSelected() {
        List<ProductDetailResponse> listselected = new ArrayList<>();
        List<ProductDetailResponse> listData = detailService.findProductsById(doiHangConfigs.getId());
        for (int i = 0; i < listData.size(); i++) {
            boolean check = Boolean.valueOf(tblBuy.getValueAt(i, 7) + "");
            if (check) {
                listselected.add(listData.get(i));
            }
        }
        return listselected;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBuy = new javax.swing.JTable();
        btnQuayLai = new javax.swing.JButton();
        lblHoaDon = new javax.swing.JLabel();
        btnDoiTatCa = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtMaHoaDon = new javax.swing.JLabel();
        txtTenNhanVien = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtTenKhachHang = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtTrangThai = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtNgayTao = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JLabel();

        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("Hóa đơn: ");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("Sản phẩm đổi");

        tblBuy.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tblBuy.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá", "Màu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBuy.setRowHeight(30);
        tblBuy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBuyMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBuy);

        btnQuayLai.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnQuayLai.setText("Quay lại");
        btnQuayLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuayLaiActionPerformed(evt);
            }
        });

        lblHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblHoaDon.setText("-");

        btnDoiTatCa.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnDoiTatCa.setForeground(new java.awt.Color(102, 255, 102));
        btnDoiTatCa.setText("Đổi tất cả");
        btnDoiTatCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiTatCaActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Mã hóa đơn");

        txtMaHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaHoaDon.setText("jLabel7");

        txtTenNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTenNhanVien.setText("jLabel7");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Số điện thoại");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Tên khách hàng");

        txtTenKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTenKhachHang.setText("jLabel7");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Trạng thái");

        txtTrangThai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTrangThai.setText("jLabel7");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Ngày tạo");

        txtNgayTao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNgayTao.setText("jLabel7");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Tổng tiền");

        txtTongTien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTongTien.setText("jLabel7");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(28, 28, 28)
                        .addComponent(txtMaHoaDon))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(28, 28, 28)
                        .addComponent(txtTongTien)))
                .addGap(93, 93, 93)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(28, 28, 28)
                        .addComponent(txtNgayTao))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(28, 28, 28)
                        .addComponent(txtTenKhachHang)))
                .addGap(120, 120, 120)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(28, 28, 28)
                        .addComponent(txtTrangThai))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(28, 28, 28)
                        .addComponent(txtTenNhanVien)))
                .addContainerGap(105, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(txtTenNhanVien))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtMaHoaDon)
                        .addComponent(jLabel10)
                        .addComponent(txtTenKhachHang)))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(txtTongTien))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(txtNgayTao))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(txtTrangThai)))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lb)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblHoaDon)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDoiTatCa, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnQuayLai, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHoaDon, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(btnDoiTatCa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnQuayLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuayLaiActionPerformed
            chiTietHoaDon.setVisible(false);
    }//GEN-LAST:event_btnQuayLaiActionPerformed


    private void tblBuyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBuyMouseClicked
        int index = tblBuy.getSelectedRow();
        ProductDetailResponse productDetail = new ProductDetailResponse();
        List<ProductDetailResponse> listData = detailService.findProductsById(doiHangConfigs.getId());
//        productDetail= listData.get(index);
        for (ProductDetailResponse productDetailResponse : listData) {
            if (productDetailResponse.getIdsanphamdetail().equals(listIdSPCT.get(index))) {
                productDetail = productDetailResponse;
            }
        }
        new ConfirmDoiHang(productDetail, doiHangConfigs.getId(), this, doiHang,formQuanLyDoiHang).setVisible(true);
    }//GEN-LAST:event_tblBuyMouseClicked

    private void btnDoiTatCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiTatCaActionPerformed
        List<LyDoDoi> listLyDo = lyDoService.findAll();
        List<String> lyDo = new ArrayList<>();
        for (LyDoDoi lyDoDoi : listLyDo) {
            lyDo.add(lyDoDoi.getLyDo());
        }
        Object[] possibilities = lyDo.toArray();
        String s = (String) JOptionPane.showInputDialog(
                this, "Chọn lý do đổi",
                "Xác nhận đổi?",
                JOptionPane.PLAIN_MESSAGE,
                null,
                possibilities,
                "ham");
        if ((s != null) && (s.length() > 0)) {
            //lấy id lý do đổi
            Long idLyDo = null;
            for (LyDoDoi lyDoDoi : listLyDo) {
                if (lyDoDoi.getLyDo().equalsIgnoreCase(s)) {
                    idLyDo = lyDoDoi.getId();
                }
            }
            Long idPhieuDoi = phieuDoiService.getIdPhieuDoibyIdHoaDon(doiHangConfigs.getId());
            if (idPhieuDoi == null) {
                phieuDoiService.createPhieuDoi(doiHangConfigs.getId());
                idPhieuDoi = phieuDoiService.getIdPhieuDoibyIdHoaDon(doiHangConfigs.getId());
            }
            List<ProductDetailResponse> listData = detailService.findProductsById(doiHangConfigs.getId());
            for (ProductDetailResponse productDetailResponse : listData) {
                if (phieuDoiChiTietService.checkDoiHang(productDetailResponse.getIdsanphamdetail(), idPhieuDoi, idLyDo)) {
                    phieuDoiChiTietService.addLyDoChiTiet( productDetailResponse.getSoLuong(), productDetailResponse.getIdsanphamdetail(), idPhieuDoi, idLyDo);
                } else {
                    phieuDoiChiTietService.createPhieuDoiChiTiet(productDetailResponse, idPhieuDoi, idLyDo, "-", productDetailResponse.getSoLuong());
                }
                hoaDonRepository.deleteHoaDonSanPham(productDetailResponse.getIdHoaDonSanPham());
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Đổi thành công");
                formQuanLyDoiHang.showSPChiTiet();
                return;
            }
        }
        formQuanLyDoiHang.showSPChiTiet();
        chiTietHoaDon.setVisible(false);
    }//GEN-LAST:event_btnDoiTatCaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDoiTatCa;
    private javax.swing.JButton btnQuayLai;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lblHoaDon;
    private javax.swing.JTable tblBuy;
    private javax.swing.JLabel txtMaHoaDon;
    private javax.swing.JLabel txtNgayTao;
    private javax.swing.JLabel txtTenKhachHang;
    private javax.swing.JLabel txtTenNhanVien;
    private javax.swing.JLabel txtTongTien;
    private javax.swing.JLabel txtTrangThai;
    // End of variables declaration//GEN-END:variables

}

enum Actions {
    PRINT, EDIT;
}

class ButtonsPanel extends JPanel {

    public final List<JButton> buttons = new ArrayList<>();

    public ButtonsPanel() {
        super(new FlowLayout(FlowLayout.LEFT));
        setOpaque(true);
        for (Actions a : Actions.values()) {
            JButton b = new JButton(a.toString());
            b.setFocusable(false);
            b.setRolloverEnabled(false);
            add(b);
            buttons.add(b);
        }
    }

    protected void updateButtons(Object value) {
        if (value instanceof EnumSet) {
            EnumSet ea = (EnumSet) value;
            removeAll();
            if (ea.contains(Actions.PRINT)) {
                add(buttons.get(0));
            }
            if (ea.contains(Actions.EDIT)) {
                add(buttons.get(1));
            }
        }
    }
}

class ButtonsRenderer implements TableCellRenderer {

    private final ButtonsPanel panel = new ButtonsPanel();

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        panel.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
        panel.updateButtons(value);
        return panel;
    }
}

class PrintAction extends AbstractAction {

    private final JTable table;

    public PrintAction(JTable table) {
        super(Actions.PRINT.toString());
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(table, "Printing");
    }
}

class EditAction extends AbstractAction {

    private final JTable table;

    public EditAction(JTable table) {
        super(Actions.EDIT.toString());
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int row = table.convertRowIndexToModel(table.getEditingRow());
        Object o = table.getModel().getValueAt(row, 0);
        JOptionPane.showMessageDialog(table, "Editing: " + o);
    }
}

class ButtonsEditor extends AbstractCellEditor implements TableCellEditor {

    private final ButtonsPanel panel = new ButtonsPanel();
    private final JTable table;
    private Object o;

    private class EditingStopHandler extends MouseAdapter implements ActionListener {

        @Override
        public void mousePressed(MouseEvent e) {
            Object o = e.getSource();
            if (o instanceof TableCellEditor) {
                actionPerformed(null);
            } else if (o instanceof JButton) {
                ButtonModel m = ((JButton) e.getComponent()).getModel();
                if (m.isPressed() && table.isRowSelected(table.getEditingRow()) && e.isControlDown()) {
                    panel.setBackground(table.getBackground());
                }
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    fireEditingStopped();
                }
            });
        }
    }

    public ButtonsEditor(JTable table) {
        super();
        this.table = table;
        panel.buttons.get(0).setAction(new PrintAction(table));
        panel.buttons.get(1).setAction(new EditAction(table));

        EditingStopHandler handler = new EditingStopHandler();
        for (JButton b : panel.buttons) {
            b.addMouseListener(handler);
            b.addActionListener(handler);
        }
        panel.addMouseListener(handler);
    }

    @Override
    public Component getTableCellEditorComponent(
            JTable table, Object value, boolean isSelected, int row, int column) {
        panel.setBackground(table.getSelectionBackground());
        panel.updateButtons(value);
        o = value;
        return panel;
    }

    @Override
    public Object getCellEditorValue() {
        return o;
    }
}
