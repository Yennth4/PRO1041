package com.poly.form.doihang.view;

import com.formdev.flatlaf.FlatClientProperties;
import com.poly.form.doihang.entity.DoiHangResponse.HoaDon;
import com.poly.menu.Menu;
import com.poly.form.doihang.service.DoiHangService.impl.PhieuDoiService;
import com.poly.form.doihang.service.DoiHangService.impl.ProductDetailService;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Raven
 */
public class LichSuDoiHang extends javax.swing.JPanel {
    private DoiHangConfigs doiHangConfigs;
    private PhieuDoiService phieuDoiService= new PhieuDoiService();
    private ProductDetailService detailService= new ProductDetailService(); 
    private DefaultTableModel tableModel= new DefaultTableModel();
    private Menu menu;
    public LichSuDoiHang( Menu menu, DoiHangConfigs doiHangConfigs) { 
        this.menu= menu;
        this.doiHangConfigs= doiHangConfigs;
        initComponents(); 
        tableModel= (DefaultTableModel) tblListHoaDon.getModel();
        showTable(phieuDoiService.lichSuDoi(txtSearch.getText(),cbxTrangThai.getSelectedIndex()==0?1:0));
        showCBX();
    }
    public void showCBX(){
        cbxTrangThai.removeAllItems();
        cbxTrangThai.addItem("Đã thanh toán");
        cbxTrangThai.addItem("Chưa thanh toán");
    }
  public void showTable(List<HoaDon> list){
        tableModel.setRowCount(0);
        tableModel.setColumnIdentifiers(new Object[]{"STT","Mã hóa đơn","tên khách hàng","Số điện thoại","tổng tiền","ngày tạo","Trạng thái"});
        for(int i=0;i<list.size();i++){
            HoaDon hoaDon=list.get(i);
            tableModel.addRow(new Object[]{i+1,hoaDon.getMaHoaDon() 
                    ,hoaDon.getTenKhachHang(),hoaDon.getSdt(),hoaDon.getTongTienSauKhuyenMai()+" VND"
                    ,hoaDon.getThoiGianTao(),hoaDon.getTrangThai().equalsIgnoreCase("1")?"Đã thanh toán":"Chưa thanh toán"});
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        ctnBoLoc = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbxTrangThai = new javax.swing.JComboBox<>();
        Title = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListHoaDon = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Hóa đơn đã đổi");
        jLabel2.setAutoscrolls(true);

        ctnBoLoc.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bộ lọc", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        txtSearch.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setText("Tìm kiếm");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setText("Trạng thái");

        cbxTrangThai.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbxTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTrangThaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ctnBoLocLayout = new javax.swing.GroupLayout(ctnBoLoc);
        ctnBoLoc.setLayout(ctnBoLocLayout);
        ctnBoLocLayout.setHorizontalGroup(
            ctnBoLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ctnBoLocLayout.createSequentialGroup()
                .addGap(292, 292, 292)
                .addGroup(ctnBoLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ctnBoLocLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ctnBoLocLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(cbxTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(378, Short.MAX_VALUE))
        );
        ctnBoLocLayout.setVerticalGroup(
            ctnBoLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ctnBoLocLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(ctnBoLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(27, 27, 27)
                .addGroup(ctnBoLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbxTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        Title.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Title.setText("Lịch Sử Đổi Hàng");

        tblListHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tblListHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã hd", "Tên nhân viên", "Hành động"
            }
        ));
        tblListHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblListHoaDon);

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setText("<");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("1/1");

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton2.setText(">");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 984, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ctnBoLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 31, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(434, 434, 434)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(409, 409, 409)
                        .addComponent(Title)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ctnBoLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void tblListHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListHoaDonMouseClicked
        int i=tblListHoaDon.getSelectedRow() ;
        Long id=-1L;         
        for (HoaDon hoaDon : phieuDoiService.lichSuDoi(txtSearch.getText(),cbxTrangThai.getSelectedIndex()==0?1:0)) {
            if (hoaDon.getMaHoaDon().equalsIgnoreCase((String) tblListHoaDon.getValueAt(i, 1))) { 
                id=hoaDon.getId(); 
            }
        }
        doiHangConfigs.setMaHoaDon((String) tblListHoaDon.getValueAt(i, 1)); 
        doiHangConfigs.setIdHD(id); 
        menu.setSelectedMenu(3, 5); 
    }//GEN-LAST:event_tblListHoaDonMouseClicked

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
         showTable(phieuDoiService.lichSuDoi(txtSearch.getText(),cbxTrangThai.getSelectedIndex()==0?1:0));
    }//GEN-LAST:event_txtSearchKeyReleased

    private void cbxTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTrangThaiActionPerformed
         showTable(phieuDoiService.lichSuDoi(txtSearch.getText(),cbxTrangThai.getSelectedIndex()==0?1:0));
    }//GEN-LAST:event_cbxTrangThaiActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Title;
    private javax.swing.JComboBox<String> cbxTrangThai;
    private javax.swing.JPanel ctnBoLoc;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblListHoaDon;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
