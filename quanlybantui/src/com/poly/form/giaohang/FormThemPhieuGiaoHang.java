package com.poly.form.giaohang;

import com.formdev.flatlaf.FlatClientProperties;
import com.poly.form.giaohang.entity.HoaDonChuanBiResponse;
import com.poly.form.giaohang.entity.PhieuGiaoHang;
import com.poly.form.giaohang.entity.SanPhamTrongHoaDonResponse;
import com.poly.form.giaohang.service.PhieuGiaoHangService;
import com.poly.form.giaohang.service.impl.PhieuGiaoHangServiceImpl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FormThemPhieuGiaoHang extends javax.swing.JPanel {

    private DefaultTableModel defaultTableModel;

    private List<HoaDonChuanBiResponse> listView = new ArrayList<>();

    private List<SanPhamTrongHoaDonResponse> listSanPham = new ArrayList<>();

    private PhieuGiaoHangService service = new PhieuGiaoHangServiceImpl();

    public FormThemPhieuGiaoHang() {
        initComponents();

        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");

        this.init();
    }

    public void init() {

        this.txtSoTienCanThu.setEnabled(false);
        this.txtThoiGianUocTinh.setEnabled(false);

        try {
            this.listView = service.getAllHoaDonChuanBi(3);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi lấy data");
        }

        this.fillTableHoaDon(listView);

    }

    public void fillTableHoaDon(List<HoaDonChuanBiResponse> list) {

        if (list != null) {
            this.defaultTableModel = (DefaultTableModel) this.tblDanhSachHoaDon.getModel();

            this.defaultTableModel.setRowCount(0);

            for (HoaDonChuanBiResponse hoaDonChuanBiResponse : list) {
                this.defaultTableModel.addRow(hoaDonChuanBiResponse.toRowTable());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Không có giữ liệu hóa đơn");
        }
    }

    public void fillTableSanPham(List<SanPhamTrongHoaDonResponse> list) {

        if (list != null) {
            this.defaultTableModel = (DefaultTableModel) this.tblDanhSachSanPhamTrongHoaDon.getModel();

            this.defaultTableModel.setRowCount(0);

            for (SanPhamTrongHoaDonResponse sanPhamTrongHoaDonResponse : list) {
                this.defaultTableModel.addRow(sanPhamTrongHoaDonResponse.toRowTable());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Không có dữ liệu sản phẩm trong hóa đơn");
        }

    }

    public void fillTableToFrom(List<HoaDonChuanBiResponse> list, int index) {

        HoaDonChuanBiResponse hoaDonChuanBiResponse = list.get(index);

        txtDiaChiGiao.setText(hoaDonChuanBiResponse.getDiaChiKhachHang());
        txtNguoiGiao.setText("");
        txtNguoiNhan.setText(hoaDonChuanBiResponse.getTenKhachHang());
        txtSDTNguoiGiao.setText("");
        txtSDTNguoiNhan.setText(hoaDonChuanBiResponse.getSdtKhachHang());
        txtSoTienCanThu.setText("0 VND");
        txtThoiGianUocTinh.setText("");
        jlbTenNhanVien.setText(hoaDonChuanBiResponse.getTenNhanVien());
        txaMoTa.setText(hoaDonChuanBiResponse.getGhiChu());
        jlbMaHoaDon.setText(hoaDonChuanBiResponse.getMaHoaDon());

    }

    public void clearForm() {
        txtDiaChiGiao.setText("");
        txtNguoiGiao.setText("");
        txtNguoiNhan.setText("");
        txtSDTNguoiGiao.setText("");
        txtSDTNguoiNhan.setText("");
        txtSoTienCanThu.setText("");
        txtThoiGianUocTinh.setText("");
        jlbTenNhanVien.setText("");
        txaMoTa.setText("");
        jlbMaHoaDon.setText("");
    }

    public PhieuGiaoHang readForm(String ma) {
        String diaChi = txtDiaChiGiao.getText().toString().trim();
        String nguoiGiao = txtNguoiGiao.getText().toString().trim();
        String nguoiNhan = txtNguoiNhan.getText().toString().trim();
        String sdtNguoiGiao = txtSDTNguoiGiao.getText().toString().trim();
        String sdtNguoiNhan = txtSDTNguoiNhan.getText().toString().trim();
        Long ngayUocTinh = null;

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        Long ngayHienTai = cal.getTime().getTime() / 1000;

        if (jdcNgayUocTinh.getDate() != null) {
            Calendar ngayUocTinhCal = jdcNgayUocTinh.getCalendar();
            ngayUocTinh = ngayUocTinhCal.getTimeInMillis() / 1000;
        }

        String moTa = txaMoTa.getText();

        if (diaChi == null || diaChi.trim().isEmpty() || diaChi.length() > 200) {
            JOptionPane.showMessageDialog(this, "Địa chỉ trống hoặc không quá 255 ký tự");
            return null;
        }

        if (nguoiGiao == null || nguoiGiao.trim().isEmpty() || nguoiGiao.length() > 200) {
            JOptionPane.showMessageDialog(this, "Người giao trống hoặc không quá 255 ký tự");
            return null;
        }

        if (nguoiNhan == null || nguoiNhan.trim().isEmpty() || nguoiNhan.length() > 200) {
            JOptionPane.showMessageDialog(this, "Người nhận trống hoặc không quá 255 ký tự");
            return null;
        }

        String REGEXSO = "0[0-9]{9,10}";
        if (sdtNguoiGiao == null || sdtNguoiGiao.trim().isEmpty() || !sdtNguoiGiao.matches(REGEXSO)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại người giao trống hoặc không đúng định dạng");
            return null;
        }

        if (sdtNguoiNhan == null || sdtNguoiNhan.trim().isEmpty() || !sdtNguoiNhan.matches(REGEXSO)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại người nhận trống hoặc không đúng định dạng");
            return null;
        }

        if (sdtNguoiGiao == sdtNguoiNhan) {
            JOptionPane.showMessageDialog(this, "SDT người giao và nhận trùng nhau");
            return null;
        }
        
        if(ngayUocTinh == null){
            JOptionPane.showMessageDialog(this, "Ngày ước tính không đúng định dạng");
            return null;
        }
        
        return new PhieuGiaoHang(ma, nguoiNhan, sdtNguoiNhan, nguoiGiao, sdtNguoiGiao, diaChi, ngayHienTai, null, moTa, 1, ngayUocTinh);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDanhSachSanPhamTrongHoaDon = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDanhSachHoaDon = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        txtTimKiemHoaDonTheoMa = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        btnLamMoi1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNguoiNhan = new javax.swing.JTextField();
        txtSDTNguoiNhan = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNguoiGiao = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtSDTNguoiGiao = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDiaChiGiao = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtThoiGianUocTinh = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txaMoTa = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        txtSoTienCanThu = new javax.swing.JTextField();
        jdcNgayUocTinh = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jlbTenNhanVien = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jlbMaHoaDon = new javax.swing.JLabel();
        btnThemPhieu = new javax.swing.JButton();

        lb.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("Quản Lý Giao Hàng");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Danh sách sản phẩm trong hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblDanhSachSanPhamTrongHoaDon.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        tblDanhSachSanPhamTrongHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tên sản phẩm", "Giá", "Số lượng", "Trạng thái"
            }
        ));
        tblDanhSachSanPhamTrongHoaDon.setRowHeight(50);
        jScrollPane1.setViewportView(tblDanhSachSanPhamTrongHoaDon);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Danh sách hóa đơn chuẩn bị giao", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblDanhSachHoaDon.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        tblDanhSachHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tên sản phẩm", "Giá", "Số lượng", "Trạng thái"
            }
        ));
        tblDanhSachHoaDon.setRowHeight(40);
        tblDanhSachHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachHoaDonMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDanhSachHoaDon);

        jLabel11.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel11.setText("Tìm kiếm theo mã hóa đơn ");

        txtTimKiemHoaDonTheoMa.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtTimKiemHoaDonTheoMa.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtTimKiemHoaDonTheoMaPropertyChange(evt);
            }
        });

        btnTimKiem.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnTimKiem.setText("Tìm Kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        btnLamMoi.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnLamMoi.setText("Làm Mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnLamMoi1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnLamMoi1.setText("Làm Mới TT");
        btnLamMoi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoi1ActionPerformed(evt);
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
                        .addComponent(jLabel11)
                        .addGap(12, 12, 12)
                        .addComponent(txtTimKiemHoaDonTheoMa, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLamMoi1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(10, 10, 10))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTimKiemHoaDonTheoMa, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLamMoi)
                        .addComponent(btnLamMoi1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Danh sách sản phẩm trong hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel1.setText("Người nhận:");

        txtNguoiNhan.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        txtSDTNguoiNhan.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel2.setText("SDT người nhận:");

        txtNguoiGiao.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel3.setText("Người giao:");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel4.setText("SDT người giao:");

        txtSDTNguoiGiao.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel5.setText("Địa chỉ giao đến:");

        txtDiaChiGiao.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel6.setText("Thời gian ước tính:");

        txtThoiGianUocTinh.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel7.setText("Mô tả: ");

        txaMoTa.setColumns(20);
        txaMoTa.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txaMoTa.setRows(5);
        jScrollPane3.setViewportView(txaMoTa);

        jLabel9.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel9.setText("Sô tiền cần thu: ");

        txtSoTienCanThu.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jdcNgayUocTinh.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdcNgayUocTinhPropertyChange(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel8.setText("Nhân Viên:");

        jlbTenNhanVien.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jlbTenNhanVien.setForeground(java.awt.Color.orange);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDiaChiGiao)
                    .addComponent(txtSDTNguoiGiao)
                    .addComponent(txtNguoiGiao)
                    .addComponent(txtSDTNguoiNhan, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtThoiGianUocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jdcNgayUocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 27, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtSoTienCanThu, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNguoiNhan, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlbTenNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jlbTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNguoiNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtSDTNguoiNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNguoiGiao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtSDTNguoiGiao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtDiaChiGiao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdcNgayUocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtThoiGianUocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(txtSoTienCanThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, java.awt.Color.red), "Mã hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), java.awt.Color.red)); // NOI18N

        jlbMaHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jlbMaHoaDon.setForeground(java.awt.Color.red);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbMaHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbMaHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnThemPhieu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThemPhieu.setForeground(java.awt.Color.blue);
        btnThemPhieu.setText("Thêm Phiếu Giao");
        btnThemPhieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemPhieuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnThemPhieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThemPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblDanhSachHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachHoaDonMouseClicked
        try {
            int index = this.tblDanhSachHoaDon.getSelectedRow();
            String ma = (String) this.tblDanhSachHoaDon.getValueAt(index, 0);
            this.listSanPham = this.service.getAllSanPhamHoaDon(3, ma);
            this.fillTableSanPham(listSanPham);
            this.fillTableToFrom(listView, index);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi getData");
            Logger.getLogger(FormThemPhieuGiaoHang.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_tblDanhSachHoaDonMouseClicked

    private void btnThemPhieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemPhieuActionPerformed
        int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm phiếu giao hàng không ?", "Thông báo", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            try {
                String ma = this.jlbMaHoaDon.getText();
                if (ma != null && ma != "") {
                    if (readForm(ma) != null) {
                        if (this.service.findTonTai(ma)) {
                            JOptionPane.showMessageDialog(this, "Hóa đơn đã có phiếu giao hàng");
                            return;
                        }
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(new Date());
                        Long ngayHienTai = cal.getTime().getTime() / 1000;
                        this.service.themPhieu(readForm(ma));
                        this.service.updateTrangThaiHoaDon(4, ma);
                        this.service.chuyenDangGiao(1, ma, ngayHienTai);
                        this.listView = this.service.getAllHoaDonChuanBi(3);
                        this.fillTableHoaDon(listView);
                        this.listSanPham.clear();
                        this.fillTableSanPham(listSanPham);
                        JOptionPane.showMessageDialog(this, "Thêm thành công");
                    } else {
                        JOptionPane.showMessageDialog(this, "Thêm thất bại");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn và thêm thông tin phiếu giao");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Đã hủy");
        }
        this.clearForm();
    }//GEN-LAST:event_btnThemPhieuActionPerformed

    private void jdcNgayUocTinhPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdcNgayUocTinhPropertyChange
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        Calendar ngay = this.jdcNgayUocTinh.getCalendar();
        if (ngay != null) {
            Calendar ngayConver = ngay;
            ngayConver.set(Calendar.HOUR_OF_DAY, 0);
            ngayConver.set(Calendar.MINUTE, 0);
            ngayConver.set(Calendar.SECOND, 0);
            ngayConver.set(Calendar.MILLISECOND, 0);
            String ngayConverted = sdf.format(ngayConver.getTime());
            this.txtThoiGianUocTinh.setText(ngayConverted);
        }
    }//GEN-LAST:event_jdcNgayUocTinhPropertyChange

    private void txtTimKiemHoaDonTheoMaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtTimKiemHoaDonTheoMaPropertyChange
        String ma = txtTimKiemHoaDonTheoMa.getText().trim();
        if (ma != null && ma != "") {
            try {
                this.listView = this.service.getAllDataHoaDonChuanBiByMa(3, ma);
                if (listView != null) {
                    fillTableHoaDon(listView);
                }
            } catch (Exception ex) {
                Logger.getLogger(FormThemPhieuGiaoHang.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                this.listView = this.service.getAllHoaDonChuanBi(3);
                if (listView != null) {
                    fillTableHoaDon(listView);
                }
            } catch (Exception ex) {
                Logger.getLogger(FormThemPhieuGiaoHang.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtTimKiemHoaDonTheoMaPropertyChange

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        String ma = txtTimKiemHoaDonTheoMa.getText().trim();
        if (ma != null && ma != "") {
            try {
                this.listView = this.service.getAllDataHoaDonChuanBiByMa(3, ma);
                if (listView != null) {
                    fillTableHoaDon(listView);
                }
            } catch (Exception ex) {
                Logger.getLogger(FormThemPhieuGiaoHang.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                this.listView = this.service.getAllHoaDonChuanBi(3);
                if (listView != null) {
                    fillTableHoaDon(listView);
                }
            } catch (Exception ex) {
                Logger.getLogger(FormThemPhieuGiaoHang.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        this.txtTimKiemHoaDonTheoMa.setText("");
        try {
            this.listView = this.service.getAllHoaDonChuanBi(3);
            if (listView != null) {
                fillTableHoaDon(listView);
            }
        } catch (Exception ex) {
            Logger.getLogger(FormThemPhieuGiaoHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnLamMoi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoi1ActionPerformed
        this.txtDiaChiGiao.setText("");
        this.txtNguoiGiao.setText("");
        this.txtNguoiNhan.setText("");
        this.txtSDTNguoiGiao.setText("");
        this.txtSDTNguoiNhan.setText("");
        this.txtSoTienCanThu.setText("");
        this.txtThoiGianUocTinh.setText("");
        this.txaMoTa.setText("");
        this.jdcNgayUocTinh.setCalendar(null);
    }//GEN-LAST:event_btnLamMoi1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLamMoi1;
    private javax.swing.JButton btnThemPhieu;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private com.toedter.calendar.JDateChooser jdcNgayUocTinh;
    private javax.swing.JLabel jlbMaHoaDon;
    private javax.swing.JLabel jlbTenNhanVien;
    private javax.swing.JLabel lb;
    private javax.swing.JTable tblDanhSachHoaDon;
    private javax.swing.JTable tblDanhSachSanPhamTrongHoaDon;
    private javax.swing.JTextArea txaMoTa;
    private javax.swing.JTextField txtDiaChiGiao;
    private javax.swing.JTextField txtNguoiGiao;
    private javax.swing.JTextField txtNguoiNhan;
    private javax.swing.JTextField txtSDTNguoiGiao;
    private javax.swing.JTextField txtSDTNguoiNhan;
    private javax.swing.JTextField txtSoTienCanThu;
    private javax.swing.JTextField txtThoiGianUocTinh;
    private javax.swing.JTextField txtTimKiemHoaDonTheoMa;
    // End of variables declaration//GEN-END:variables
}
