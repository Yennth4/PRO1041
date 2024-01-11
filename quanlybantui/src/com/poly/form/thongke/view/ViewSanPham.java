package com.poly.form.thongke.view;

import com.poly.form.other.*;
import com.formdev.flatlaf.FlatClientProperties;
import com.poly.form.thongke.entity.SanPhamEntity;
import com.poly.form.thongke.service.impl.SanPhamServiceImpl;
import java.awt.Dimension;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import raven.toast.Notifications;

/**
 *
 * @author Raven
 */
public class ViewSanPham extends javax.swing.JPanel {

    private SanPhamServiceImpl sanPhamService = new SanPhamServiceImpl();

    public ViewSanPham() {
        customInit(bietdo());

    }

    public ChartPanel bietdo() {
        DefaultPieDataset dataset = new DefaultPieDataset();
//        dataset.setValue("Category 1" + 33 + "%", 30.333);
        for (SanPhamEntity sanPhamEntity : sanPhamService.findAll()) {
            dataset.setValue(sanPhamEntity.getMa()+" - "+sanPhamEntity.getPhanTram()+"%", sanPhamEntity.getPhanTram());
        }
        // Tạo biểu đồ tròn
        JFreeChart chart = ChartFactory.createPieChart(
                "Biểu Đồ Tỉ Lệ Sản Phẩm Bán Chạy Trong 30 Ngày Gần Đây"
              + "             (Đơn Vị: %)", // Tiêu đề biểu đồ
                dataset,
                true, // Hiển thị giải thích (legend)
                true,
                false
        );

        // Tinh chỉnh hiển thị biểu đồ (tuỳ chọn)
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionOutlinesVisible(false);
//        plot.setExplodePercent("Category 2", 0.20); // Phần bị nổ ra ,tách ra

        // Thêm biểu đồ vào giao diện
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(560, 370));
        return chartPanel;
    }

    private void customInit(ChartPanel chartPanel) {
        pnBang = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblList = new javax.swing.JTable();

        javax.swing.GroupLayout pnBangLayout = new javax.swing.GroupLayout(pnBang);
        pnBang.setLayout(pnBangLayout);
        pnBangLayout.setHorizontalGroup(
                pnBangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(chartPanel)
                        .addGap(0, 1044, Short.MAX_VALUE)
        );
        pnBangLayout.setVerticalGroup(
                pnBangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(chartPanel)
                        .addGap(0, 509, Short.MAX_VALUE)
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Top sản phẩm bán chạy nhất trong 30 ngày gần đây"));

        // Số lượng dòng trong table
        List<SanPhamEntity> list = sanPhamService.findAll();
        int rowCount = list.size();
// Số lượng cột trong table
        int columnCount = 7; // STT, Mã, Tên, Màu, Số lượng, Thể loại, Phần trăm

// Tạo mảng hai chiều để lưu dữ liệu
        Object[][] data = new Object[rowCount][columnCount];

// Duyệt qua danh sách và gán giá trị vào mảng
        for (int i = 0; i < rowCount; i++) {
            SanPhamEntity sanPham = list.get(i);
            data[i][0] = i + 1; // STT
            data[i][1] = sanPham.getMa();
            data[i][2] = sanPham.getTenSanPham();
            data[i][3] = sanPham.getTenMau();
            data[i][4] = sanPham.getSoLuong();
            data[i][5] = sanPham.getTenTheLoai();
            data[i][6] = sanPham.getPhanTram()+"%";
        }
        tblList.setModel(new javax.swing.table.DefaultTableModel(
                //                new Object[][]{
                //                    {"1", "11", "11", "11", "11", "11", "1"},
                //                    {null, null, null, null, null, null, null},
                //                    {null, null, null, null, null, null, null},
                //                    {null, null, null, null, null, null, null},
                //                    {null, null, null, null, null, null, null}
                //                }
                data,
                new String[]{
                    "STT", "Mã", "Tên", "Màu", "Số lượng đã bán", "Thể loại", "Chiếm phần trăm"
                }
        ));
        jScrollPane2.setViewportView(tblList);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1028, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 1, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnBang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(pnBang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnBang = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblList = new javax.swing.JTable();

        javax.swing.GroupLayout pnBangLayout = new javax.swing.GroupLayout(pnBang);
        pnBang.setLayout(pnBangLayout);
        pnBangLayout.setHorizontalGroup(
            pnBangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1044, Short.MAX_VALUE)
        );
        pnBangLayout.setVerticalGroup(
            pnBangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 503, Short.MAX_VALUE)
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Top sản phẩm bán chạy nhất trong 30 ngày gần đây", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N
        jPanel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        tblList.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tblList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "11", "11", "11", "11", "11", "1"},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã", "Tên", "Màu", "Số lượng", "Thể loại", "Phần trăm"
            }
        ));
        jScrollPane2.setViewportView(tblList);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1028, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnBang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnBang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnBang;
    private javax.swing.JTable tblList;
    // End of variables declaration//GEN-END:variables
}
