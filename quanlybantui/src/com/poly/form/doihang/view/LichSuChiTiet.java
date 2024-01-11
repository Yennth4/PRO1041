package com.poly.form.doihang.view;

import com.poly.form.doihang.view.ConfirmLichSuChiTiet;
import com.formdev.flatlaf.FlatClientProperties;
import com.poly.Application;
import com.poly.form.doihang.entity.DoiHangResponse.HoaDon;
import com.poly.form.doihang.entity.DoiHangResponse.ProductDetailResponse;
import com.poly.form.doihang.view.ConfirmHoanTac;
import com.poly.menu.Menu;
import com.poly.form.doihang.repository.DoiHangRepository.HoaDonRepository;
import com.poly.form.doihang.service.DoiHangService.IPhieuDoiService;
import com.poly.form.doihang.service.DoiHangService.impl.PhieuDoiService;
import com.poly.form.doihang.service.DoiHangService.impl.ProductDetailService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Raven
 */
public class LichSuChiTiet extends javax.swing.JPanel {

    private Menu menu;
    private DoiHangConfigs doiHangConfigs;
    private HoaDonRepository hoaDonRepository = new HoaDonRepository();
    private DefaultTableModel tableModelDaDoi = new DefaultTableModel();
    private ProductDetailService productDetailService = new ProductDetailService();
    private DefaultTableModel tableModelDaMua = new DefaultTableModel();
    private PhieuDoiService phieuDoiService = new PhieuDoiService();
    private Map<String, ProductDetailResponse> listIdDaDoi = new HashMap<>();
    private Map<String, ProductDetailResponse> mapDaMua = new HashMap<>();

    public LichSuChiTiet(Menu menu, DoiHangConfigs doiHangConfigs) {
        this.menu = menu;
        this.doiHangConfigs = doiHangConfigs;
        initComponents();
        Title.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        showDetail(doiHangConfigs);
        tblDaMua.setModel(tableModelDaMua);
        tblDaDoi.setModel(tableModelDaDoi);
        showTableDaMua(productDetailService.findProductsById(doiHangConfigs.getId()));
        showTableDaDoi(productDetailService.sanPhamDaDoi(doiHangConfigs.getId()));
    }

    public ProductDetailService getDetailService() {
        return productDetailService;
    }

    public void setProductDetailService(ProductDetailService productDetailService) {
        this.productDetailService = productDetailService;
    }

    public void showTableDaMua(List<ProductDetailResponse> list) {
        tableModelDaMua.setRowCount(0);
        tableModelDaMua.setColumnIdentifiers(new Object[]{"STT", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá", "Màu"});
        mapDaMua = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            ProductDetailResponse response = list.get(i);
            mapDaMua.put("SP00" + response.getIdsanphamdetail(), response);
            tableModelDaMua.addRow(new Object[]{i + 1, "SP00" + response.getIdsanphamdetail(), response.getTenSanPham(),
                response.getSoLuong(), response.getGiaTien() + " VND", response.getTenMau()});
        }
    }

    public void showDetail(DoiHangConfigs doiHangConfigs) {
        HoaDon hoaDon = hoaDonRepository.finById(doiHangConfigs.getId());
        txtMaHD.setText(hoaDon.getMaHoaDon());
        txtHoaDon.setText(hoaDon.getMaHoaDon());
        txtNgayDoi.setText(hoaDon.getThoiGianTao() + "");
        txtTenKhachHang2.setText(hoaDon.getTenKhachHang());
        txtTenNhanVien2.setText(hoaDon.getSdt());
        txtTongTien2.setText(hoaDon.getTongTienHoaDon() + "");
        txtTrangThai2.setText("Hoàn thành");
    }

    public void showTableDaDoi(List<ProductDetailResponse> list) {
        tableModelDaDoi.setRowCount(0);
        tableModelDaDoi.setColumnIdentifiers(new Object[]{"STT", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá", "Màu", "Lý do"});
        listIdDaDoi = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            ProductDetailResponse response = list.get(i);
            listIdDaDoi.put("SP00" + response.getIdsanphamdetail(), response);
            tableModelDaDoi.addRow(new Object[]{i + 1, "SP00" + response.getIdsanphamdetail(), response.getTenSanPham(),
                response.getSoLuong(), response.getGiaTien(), response.getTenMau(), response.getLyDoDoi()});
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Title = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JLabel();
        txtTenNhanVien2 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtTenKhachHang2 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtTrangThai2 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtNgayDoi = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtTongTien2 = new javax.swing.JLabel();
        lblTitle1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDaMua = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDaDoi = new javax.swing.JTable();
        txtHoaDon = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        Title.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Title.setText("Lịch Sử Đổi Hàng");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Mã hóa đơn");

        txtMaHD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaHD.setText("jLabel7");

        txtTenNhanVien2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTenNhanVien2.setText("jLabel7");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("Số điện thoại");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("Tên khách hàng");

        txtTenKhachHang2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTenKhachHang2.setText("jLabel7");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("Trạng thái");

        txtTrangThai2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTrangThai2.setText("jLabel7");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel22.setText("Ngày đổi");

        txtNgayDoi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNgayDoi.setText("jLabel7");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel23.setText("Tổng tiền");

        txtTongTien2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTongTien2.setText("jLabel7");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(28, 28, 28)
                        .addComponent(txtMaHD))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(28, 28, 28)
                        .addComponent(txtTongTien2)))
                .addGap(134, 134, 134)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel22)
                    .addComponent(jLabel19))
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTenNhanVien2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNgayDoi, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(111, 111, 111)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel21)
                    .addComponent(jLabel20))
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTenKhachHang2)
                    .addComponent(txtTrangThai2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(127, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtMaHD)
                    .addComponent(jLabel19)
                    .addComponent(txtTenNhanVien2)
                    .addComponent(jLabel20)
                    .addComponent(txtTenKhachHang2))
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel23)
                        .addComponent(txtTongTien2))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel22)
                        .addComponent(txtNgayDoi))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel21)
                        .addComponent(txtTrangThai2)))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        lblTitle1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblTitle1.setText("Sản phẩm mua");
        lblTitle1.setDoubleBuffered(true);

        tblDaMua.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Mã hd", "Tên nhân viên", "Hành động"
            }
        ));
        tblDaMua.setRowHeight(30);
        tblDaMua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDaMuaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDaMua);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản phẩm đổi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N

        tblDaDoi.setModel(new javax.swing.table.DefaultTableModel(
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
        tblDaDoi.setRowHeight(30);
        tblDaDoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDaDoiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDaDoi);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitle1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblTitle1)
                        .addGap(17, 17, 17)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        txtHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtHoaDon.setText("jLabel7");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Hóa Đơn : ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(425, 425, 425)
                        .addComponent(Title))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtHoaDon)))
                .addContainerGap(8, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtHoaDon))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblDaMuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDaMuaMouseClicked
        int index = tblDaMua.getSelectedRow();
        ProductDetailResponse productDetail = null;
//        List<ProductDetailResponse> listData = productDetailService.findProductsById(doiHangConfigs.getId());
//        productDetail= listData.get(index);
        Set<String> keys = mapDaMua.keySet();

        for (String key : keys) {
            if (key.equalsIgnoreCase(tblDaMua.getValueAt(index, 1) + "")) {
                productDetail = mapDaMua.get(key);
            }
        }
        new ConfirmLichSuChiTiet(productDetail, doiHangConfigs.getId(), this, true).setVisible(true);
    }//GEN-LAST:event_tblDaMuaMouseClicked

    private void tblDaDoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDaDoiMouseClicked
        int index = tblDaDoi.getSelectedRow();
        ProductDetailResponse productDetailResponse = null;
        Set<String> keys = listIdDaDoi.keySet();
        for (String key : keys) {
            if (key.equalsIgnoreCase(tblDaDoi.getValueAt(index, 1) + "")) {
                productDetailResponse = listIdDaDoi.get(key);
            }
        }
        new ConfirmHoanTac(productDetailResponse, doiHangConfigs.getId(), this).setVisible(true);
    }//GEN-LAST:event_tblDaDoiMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Title;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTitle1;
    private javax.swing.JTable tblDaDoi;
    private javax.swing.JTable tblDaMua;
    private javax.swing.JLabel txtHoaDon;
    private javax.swing.JLabel txtMaHD;
    private javax.swing.JLabel txtNgayDoi;
    private javax.swing.JLabel txtTenKhachHang2;
    private javax.swing.JLabel txtTenNhanVien2;
    private javax.swing.JLabel txtTongTien2;
    private javax.swing.JLabel txtTrangThai2;
    // End of variables declaration//GEN-END:variables
}
