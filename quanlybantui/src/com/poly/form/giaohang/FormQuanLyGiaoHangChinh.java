package com.poly.form.giaohang;

import com.formdev.flatlaf.FlatClientProperties;
import com.poly.form.giaohang.entity.PhieuGiaoHang;
import com.poly.form.giaohang.repository.PhieuGiaoHangRepository;
import com.poly.form.giaohang.service.PhieuGiaoHangService;
import com.poly.form.giaohang.service.impl.PhieuGiaoHangServiceImpl;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FormQuanLyGiaoHangChinh extends javax.swing.JPanel {

    private DefaultTableModel defaultTableModel;

    private PhieuGiaoHangService servicePhieuGiao = new PhieuGiaoHangServiceImpl();

    private List<PhieuGiaoHang> listPhieuGiao = new ArrayList<>();

    public FormQuanLyGiaoHangChinh() {
        initComponents();

        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");

        this.init();
    }

    public String ma() {
        String ma = txtTimKiemHoaDonTheoMa.getText().toString().trim();

        if (ma == null && ma == "") {
            return "";
        } else {
            return ma;
        }
    }

    public void init() {

        this.txtSoTienCanThu.setEnabled(false);
        this.txtSoTienCanThu.setText("0 VND");

        txtThoiGianGiaoDen.setEnabled(false);
        jdcNgayGiaoDen.setEnabled(false);
        txtThoiGianUocTinh.setEnabled(false);
        jdcNgayUocTinh.setEnabled(true);
        txtThoiGianGiao.setEnabled(false);
        jdcNgayGiao.setEnabled(false);
        txtDiaChiGiao.setEnabled(true);
        txtNguoiGiao.setEnabled(true);
        txtNguoiNhan.setEnabled(true);
        txtSDTNguoiGiao.setEnabled(true);
        txtSDTNguoiNhan.setEnabled(true);
        txtSoTienCanThu.setEnabled(false);
        txaMoTa.setEnabled(true);

        this.defaultTableModel = (DefaultTableModel) tblDanhSachPhieuGiao.getModel();

        try {
            int selectItem = this.cbxTimKiemPhieuGiaoTheoTrangThai.getSelectedIndex() + 1;
            this.listPhieuGiao = servicePhieuGiao.getAllDataGiaoHang(selectItem, ma());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "listPhieuGiao = service looix");
            Logger.getLogger(FormQuanLyGiaoHangChinh.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            this.fillTablePhieuGiao(listPhieuGiao);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Fill table Phieeu giao looi");
            Logger.getLogger(FormQuanLyGiaoHangChinh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fillTablePhieuGiao(List<PhieuGiaoHang> list) throws Exception {

        if (list != null) {
            this.defaultTableModel.setRowCount(0);

            for (PhieuGiaoHang phieuGiaoHang : list) {
                this.defaultTableModel.addRow(phieuGiaoHang.toRowTable());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Không có dữ liệu");
        }

    }

    private void fillTableToForm(List<PhieuGiaoHang> list, int index) throws Exception {
        PhieuGiaoHang phieuGiaoHang = list.get(index);
        this.jlbMaHoaDon.setText(phieuGiaoHang.getMa());
        this.txtDiaChiGiao.setText(phieuGiaoHang.getDiaChi());
        this.txtNguoiGiao.setText(phieuGiaoHang.getTenNguoiGiao());
        this.txtNguoiNhan.setText(phieuGiaoHang.getTenNguoiNhan());
        this.txtSDTNguoiGiao.setText(phieuGiaoHang.getSdtNguoiGiao());
        this.txtSDTNguoiNhan.setText(phieuGiaoHang.getSdtNguoiNhan());
        this.txtSoTienCanThu.setText("0 VND");
        String thoiGianUocTinh = (String) tblDanhSachPhieuGiao.getValueAt(index, 6);
        String thoiGianGiaoDen = (String) tblDanhSachPhieuGiao.getValueAt(index, 5);
        String thoiGianGiaoo = (String) tblDanhSachPhieuGiao.getValueAt(index, 4);
        this.txaMoTa.setText(phieuGiaoHang.getMoTa());

        if (thoiGianGiaoDen == "Chưa giao đến") {
            jdcNgayGiaoDen.setCalendar(null);
            this.txtThoiGianGiaoDen.setText(thoiGianGiaoDen);
        } else {
            Date ngayGiaoDen = new Date(phieuGiaoHang.getThoiGianNhan() * 1000);
            Calendar calNgayGiaoDen = Calendar.getInstance();
            calNgayGiaoDen.setTime(ngayGiaoDen);
            jdcNgayGiaoDen.setCalendar(calNgayGiaoDen);
        }

        if (thoiGianGiaoo == "Chưa giao") {
            jdcNgayGiao.setCalendar(null);
            this.txtThoiGianGiao.setText(thoiGianGiaoo);
        } else {
            Date thoiGianGiao = new Date(phieuGiaoHang.getThoiGianGiao() * 1000);
            Calendar calNgayGiao = Calendar.getInstance();
            calNgayGiao.setTime(thoiGianGiao);
            jdcNgayGiao.setCalendar(calNgayGiao);
        }

        if (thoiGianUocTinh == "Chưa ước tính") {
            jdcNgayUocTinh.setCalendar(null);
            this.txtThoiGianUocTinh.setText(thoiGianUocTinh);
        } else {
            Date ngayUocTinh = new Date(phieuGiaoHang.getThoiGianUocTinh() * 1000);
            Calendar calNgayUocTinh = Calendar.getInstance();
            calNgayUocTinh.setTime(ngayUocTinh);
            jdcNgayUocTinh.setCalendar(calNgayUocTinh);
        }

    }

    private PhieuGiaoHang readForm(int index, List<PhieuGiaoHang> list) throws Exception {
        String ma = this.jlbMaHoaDon.getText();
        String moTa = txaMoTa.getText();
        String diaChi = txtDiaChiGiao.getText();
        String nguoiGiao = txtNguoiGiao.getText();
        String nguoiNhan = txtNguoiNhan.getText();
        String SDTNguoiGiao = txtSDTNguoiGiao.getText();
        String SDTNguoiNhan = txtSDTNguoiNhan.getText();
        Long ngayUocTinh = null;

        if (jdcNgayUocTinh.getDate() != null) {
            Calendar ngayUocTinhCal = jdcNgayUocTinh.getCalendar();
            ngayUocTinhCal.set(Calendar.HOUR_OF_DAY, 0);
            ngayUocTinhCal.set(Calendar.MINUTE, 0);
            ngayUocTinhCal.set(Calendar.SECOND, 0);
            ngayUocTinhCal.set(Calendar.MILLISECOND, 0);
            ngayUocTinh = ngayUocTinhCal.getTimeInMillis() / 1000;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        Long ngayHienTai = cal.getTime().getTime() / 1000;
        PhieuGiaoHang phieuGiaoHang = list.get(index);

        if (ngayUocTinh == null) {
            JOptionPane.showMessageDialog(this, "Chưa chọn ngày ước tính hoặc ngày sai định dạng");
            return null;
        }

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
        if (SDTNguoiGiao == null || SDTNguoiGiao.trim().isEmpty() || !SDTNguoiGiao.matches(REGEXSO)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại người giao trống hoặc không đúng định dạng");
            return null;
        }

        if (SDTNguoiNhan == null || SDTNguoiNhan.trim().isEmpty() || !SDTNguoiNhan.matches(REGEXSO)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại người nhận trống hoặc không đúng định dạng");
            return null;
        }

        if (SDTNguoiGiao == SDTNguoiNhan) {
            JOptionPane.showMessageDialog(this, "SDT người giao và nhận trùng nhau");
            return null;
        }

        return new PhieuGiaoHang(ma, nguoiNhan, SDTNguoiNhan, nguoiGiao, SDTNguoiGiao, diaChi, ngayHienTai, null, moTa, phieuGiaoHang.getTrangThai(), ngayUocTinh);
    }

    public void clearForm() {
        this.jlbMaHoaDon.setText("");
        this.txtDiaChiGiao.setText("");
        this.txtNguoiGiao.setText("");
        this.txtNguoiNhan.setText("");
        this.txtSDTNguoiGiao.setText("");
        this.txtSDTNguoiNhan.setText("");
        this.txtSoTienCanThu.setText("0 VND");
        this.txtThoiGianGiaoDen.setText("");
        this.txtThoiGianUocTinh.setText("");
        this.txtThoiGianGiao.setText("");
        this.jdcNgayGiao.setCalendar(null);
        this.jdcNgayGiaoDen.setCalendar(null);
        this.jdcNgayUocTinh.setCalendar(null);
        this.txaMoTa.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDanhSachPhieuGiao = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        txtTimKiemHoaDonTheoMa = new javax.swing.JTextField();
        cbxTimKiemPhieuGiaoTheoTrangThai = new javax.swing.JComboBox<>();
        btnTimKiem = new javax.swing.JButton();
        btnLamMoiDanhSach = new javax.swing.JButton();
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
        btnLamMoiThongTin = new javax.swing.JButton();
        jdcNgayUocTinh = new com.toedter.calendar.JDateChooser();
        jdcNgayGiaoDen = new com.toedter.calendar.JDateChooser();
        txtThoiGianGiaoDen = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtThoiGianGiao = new javax.swing.JTextField();
        jdcNgayGiao = new com.toedter.calendar.JDateChooser();
        btnXacNhan = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jlbMaHoaDon = new javax.swing.JLabel();
        cbxChonCheDo = new javax.swing.JComboBox<>();

        lb.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("Quản Lý Phiếu Giao");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mã hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblDanhSachPhieuGiao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblDanhSachPhieuGiao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã", "Địa chỉ giao", "Người nhận", "Người giao", "Thời gian giao", "Thời gian nhận", "Thời gian ước tính", "Trạng thái"
            }
        ));
        tblDanhSachPhieuGiao.setRowHeight(40);
        tblDanhSachPhieuGiao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachPhieuGiaoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDanhSachPhieuGiao);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Tìm kiếm theo mã hóa đơn ");

        txtTimKiemHoaDonTheoMa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTimKiemHoaDonTheoMa.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtTimKiemHoaDonTheoMaPropertyChange(evt);
            }
        });

        cbxTimKiemPhieuGiaoTheoTrangThai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxTimKiemPhieuGiaoTheoTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang giao", "Đã giao", "Hủy giao", "Hẹn lại", "Chuẩn bị" }));
        cbxTimKiemPhieuGiaoTheoTrangThai.setToolTipText("");
        cbxTimKiemPhieuGiaoTheoTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTimKiemPhieuGiaoTheoTrangThaiActionPerformed(evt);
            }
        });

        btnTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTimKiem.setText("Tìm Kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        btnLamMoiDanhSach.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLamMoiDanhSach.setText("Làm Mới");
        btnLamMoiDanhSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiDanhSachActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(12, 12, 12)
                        .addComponent(txtTimKiemHoaDonTheoMa, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxTimKiemPhieuGiaoTheoTrangThai, 0, 234, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(btnLamMoiDanhSach, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTimKiemHoaDonTheoMa)
                    .addComponent(cbxTimKiemPhieuGiaoTheoTrangThai)
                    .addComponent(btnLamMoiDanhSach, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mã hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Người nhận:");

        txtNguoiNhan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtSDTNguoiNhan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Số người nhận:");

        txtNguoiGiao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Người giao:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Số người giao:");

        txtSDTNguoiGiao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Địa chỉ giao đến:");

        txtDiaChiGiao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Thời gian ước tính:");

        txtThoiGianUocTinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Mô tả: ");

        txaMoTa.setColumns(20);
        txaMoTa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txaMoTa.setRows(5);
        jScrollPane3.setViewportView(txaMoTa);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Sô tiền cần thu: ");

        txtSoTienCanThu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnLamMoiThongTin.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLamMoiThongTin.setText("Làm mới");
        btnLamMoiThongTin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiThongTinActionPerformed(evt);
            }
        });

        jdcNgayUocTinh.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdcNgayUocTinhPropertyChange(evt);
            }
        });

        jdcNgayGiaoDen.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdcNgayGiaoDenPropertyChange(evt);
            }
        });

        txtThoiGianGiaoDen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Thời gian nhận:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Thời gian giao:");

        txtThoiGianGiao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jdcNgayGiao.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdcNgayGiaoPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(btnLamMoiThongTin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDiaChiGiao)
                    .addComponent(txtSDTNguoiGiao)
                    .addComponent(txtNguoiGiao)
                    .addComponent(txtNguoiNhan)
                    .addComponent(txtSDTNguoiNhan)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtSoTienCanThu, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(txtThoiGianUocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jdcNgayUocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(15, 15, 15)
                                .addComponent(txtThoiGianGiaoDen, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jdcNgayGiaoDen, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(15, 15, 15)
                                .addComponent(txtThoiGianGiao, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jdcNgayGiao, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNguoiNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtSDTNguoiNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNguoiGiao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtSDTNguoiGiao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtDiaChiGiao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtThoiGianUocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jdcNgayUocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txtThoiGianGiaoDen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jdcNgayGiaoDen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(txtSoTienCanThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(txtThoiGianGiao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jdcNgayGiao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLamMoiThongTin))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnXacNhan.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        btnXacNhan.setText("Xác nhận");
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mã hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jlbMaHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jlbMaHoaDon.setForeground(java.awt.Color.red);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlbMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(292, 292, 292))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbMaHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        cbxChonCheDo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbxChonCheDo.setForeground(java.awt.Color.blue);
        cbxChonCheDo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chỉnh sửa thông tin", "Chuyển đang giao", "Chuyển đã giao", "Chuyển hủy giao", "Hẹn lại", "Chuyển chuẩn bị" }));
        cbxChonCheDo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxChonCheDoItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnXacNhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxChonCheDo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxChonCheDo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXacNhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        String ma = jlbMaHoaDon.getText();
        int index = tblDanhSachPhieuGiao.getSelectedRow();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        Long ngayHienTai = cal.getTime().getTime() / 1000;
        if (index >= 0) {
            if (ma == null || ma.trim().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(this, "Chọn phiếu giao để chỉnh sửa");
                return;
            } else {
                int luaChon = this.cbxChonCheDo.getSelectedIndex();
                System.out.println(luaChon);
                int selectedIndex = this.cbxTimKiemPhieuGiaoTheoTrangThai.getSelectedIndex() + 1;
                if (luaChon == 0) {
                    try {
                        if (readForm(index, listPhieuGiao) != null) {
                            this.servicePhieuGiao.chinhSuaPhieu(readForm(index, listPhieuGiao));
                            this.listPhieuGiao = this.servicePhieuGiao.getAllDataGiaoHang(selectedIndex, ma());
                            this.fillTablePhieuGiao(listPhieuGiao);
                            JOptionPane.showMessageDialog(this, "Sửa thành công");
                        } else {
                            JOptionPane.showMessageDialog(this, "Sửa thất bại");
                        }
                        return;
                    } catch (Exception ex) {
                        Logger.getLogger(FormQuanLyGiaoHangChinh.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (luaChon == 1) {
                    try {
                        System.out.println("Đang giao");
                        System.out.println(ngayHienTai);
                        this.servicePhieuGiao.chuyenDangGiao(luaChon, ma, ngayHienTai);
                        this.servicePhieuGiao.updateTrangThaiHoaDon(4, ma);
                        this.listPhieuGiao = this.servicePhieuGiao.getAllDataGiaoHang(selectedIndex, ma());
                        this.fillTablePhieuGiao(listPhieuGiao);
                        JOptionPane.showMessageDialog(this, "đã chuyển đang giao");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "chưa chuyển giao được");
                    }
//                    Chỉnh sửa thông tin, Chuyển đang giao, Chuyển đã giao, Chuyển hủy giao, Hẹn lại, Chuyển chuẩn bị
                } else if (luaChon == 2) {
                    try {
                        this.servicePhieuGiao.chuyenDaGiao(luaChon, ma, ngayHienTai);
                        System.out.println("Đã giao");
                        System.out.println(ngayHienTai);
                        this.servicePhieuGiao.updateTrangThaiHoaDon(5, ma);
                        this.listPhieuGiao = this.servicePhieuGiao.getAllDataGiaoHang(selectedIndex, ma());
                        this.fillTablePhieuGiao(listPhieuGiao);
                        JOptionPane.showMessageDialog(this, "đã Chuyển đã giao");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "chưa chuyển đã giao được");
                    }
                } else if (luaChon == 3) {
                    try {
                        System.out.println(ngayHienTai);
                        this.servicePhieuGiao.chuyenTrangThai(luaChon, ma);
                        this.servicePhieuGiao.updateTrangThaiHoaDon(2, ma);
                        this.listPhieuGiao = this.servicePhieuGiao.getAllDataGiaoHang(selectedIndex, ma());
                        this.fillTablePhieuGiao(listPhieuGiao);
                        JOptionPane.showMessageDialog(this, "đã chuyển hủy giao");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "chưa chuyển hủy giao được");
                    }
                } else if (luaChon == 4) {
                    try {
                        this.servicePhieuGiao.chuyenTrangThai(luaChon, ma);
                        this.servicePhieuGiao.updateTrangThaiHoaDon(6, ma);
                        this.listPhieuGiao = this.servicePhieuGiao.getAllDataGiaoHang(selectedIndex, ma());
                        this.fillTablePhieuGiao(listPhieuGiao);
                        JOptionPane.showMessageDialog(this, "đã chuyển Hẹn lại");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "chưa chuyển Hẹn lại được");
                    }
                } else if (luaChon == 5) {
                    try {
                        this.servicePhieuGiao.chuyenTrangThai(luaChon, ma);
                        this.servicePhieuGiao.updateTrangThaiHoaDon(3, ma);
                        this.listPhieuGiao = this.servicePhieuGiao.getAllDataGiaoHang(selectedIndex, ma());
                        this.fillTablePhieuGiao(listPhieuGiao);
                        JOptionPane.showMessageDialog(this, "đã chuyển chuẩn bị giao");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "chưa chuyển chuẩn bị giao được");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Lựa chọn chưa có");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Chọn phiếu giao trên danh sách phiếu giao để chỉnh sửa");
        }
        this.clearForm();
    }//GEN-LAST:event_btnXacNhanActionPerformed

    private void tblDanhSachPhieuGiaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachPhieuGiaoMouseClicked
        int index = tblDanhSachPhieuGiao.getSelectedRow();
        if (index >= 0) {
            try {
                this.fillTableToForm(listPhieuGiao, index);
            } catch (Exception ex) {
                Logger.getLogger(FormQuanLyGiaoHangChinh.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tblDanhSachPhieuGiaoMouseClicked

    private void cbxTimKiemPhieuGiaoTheoTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTimKiemPhieuGiaoTheoTrangThaiActionPerformed
        int selectItem = cbxTimKiemPhieuGiaoTheoTrangThai.getSelectedIndex() + 1;
        try {
            this.listPhieuGiao = this.servicePhieuGiao.getAllDataGiaoHang(selectItem, ma());
        } catch (Exception ex) {
            Logger.getLogger(FormQuanLyGiaoHangChinh.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.fillTablePhieuGiao(listPhieuGiao);
        } catch (Exception ex) {
            Logger.getLogger(FormQuanLyGiaoHangChinh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cbxTimKiemPhieuGiaoTheoTrangThaiActionPerformed

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

    private void jdcNgayGiaoDenPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdcNgayGiaoDenPropertyChange
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        Calendar ngay = this.jdcNgayGiaoDen.getCalendar();
        if (ngay != null) {
            Calendar ngayConver = ngay;
            ngayConver.set(Calendar.HOUR_OF_DAY, 0);
            ngayConver.set(Calendar.MINUTE, 0);
            ngayConver.set(Calendar.SECOND, 0);
            ngayConver.set(Calendar.MILLISECOND, 0);
            String ngayConverted = sdf.format(ngayConver.getTime());
            this.txtThoiGianGiaoDen.setText(ngayConverted);
        } else {
            this.txtThoiGianGiaoDen.setText("");
        }
    }//GEN-LAST:event_jdcNgayGiaoDenPropertyChange

    private void jdcNgayGiaoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdcNgayGiaoPropertyChange
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        Calendar ngay = this.jdcNgayGiao.getCalendar();
        if (ngay != null) {
            Calendar ngayConver = ngay;
            ngayConver.set(Calendar.HOUR_OF_DAY, 0);
            ngayConver.set(Calendar.MINUTE, 0);
            ngayConver.set(Calendar.SECOND, 0);
            ngayConver.set(Calendar.MILLISECOND, 0);
            String ngayConverted = sdf.format(ngayConver.getTime());
            this.txtThoiGianGiao.setText(ngayConverted);
        } else {
            this.txtThoiGianGiao.setText("");
        }
    }//GEN-LAST:event_jdcNgayGiaoPropertyChange

    private void cbxChonCheDoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxChonCheDoItemStateChanged
        int luaChon = this.cbxChonCheDo.getSelectedIndex();
        System.out.println(luaChon);
        if (luaChon == 0) {
            txtThoiGianGiaoDen.setEnabled(false);
            jdcNgayGiaoDen.setEnabled(false);
            txtThoiGianUocTinh.setEnabled(false);
            jdcNgayUocTinh.setEnabled(true);
            txtThoiGianGiao.setEnabled(false);
            jdcNgayGiao.setEnabled(false);
            txtDiaChiGiao.setEnabled(true);
            txtNguoiGiao.setEnabled(true);
            txtNguoiNhan.setEnabled(true);
            txtSDTNguoiGiao.setEnabled(true);
            txtSDTNguoiNhan.setEnabled(true);
            txtSoTienCanThu.setEnabled(false);
            txaMoTa.setEnabled(true);
        } else if (luaChon == 1 || luaChon == 2 || luaChon == 3 || luaChon == 4 || luaChon == 5) {
            txtThoiGianGiaoDen.setEnabled(false);
            jdcNgayGiaoDen.setEnabled(false);
            txtThoiGianUocTinh.setEnabled(false);
            jdcNgayUocTinh.setEnabled(false);
            txtThoiGianGiao.setEnabled(false);
            jdcNgayGiao.setEnabled(false);
            txtDiaChiGiao.setEnabled(false);
            txtNguoiGiao.setEnabled(false);
            txtNguoiNhan.setEnabled(false);
            txtSDTNguoiGiao.setEnabled(false);
            txtSDTNguoiNhan.setEnabled(false);
            txtSoTienCanThu.setEnabled(false);
            txaMoTa.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, "Lựa chọn chưa có");
        }
    }//GEN-LAST:event_cbxChonCheDoItemStateChanged

    private void txtTimKiemHoaDonTheoMaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtTimKiemHoaDonTheoMaPropertyChange
        int selectItem = cbxTimKiemPhieuGiaoTheoTrangThai.getSelectedIndex() + 1;
        try {
            this.listPhieuGiao = this.servicePhieuGiao.getAllDataGiaoHang(selectItem, ma());
        } catch (Exception ex) {
            Logger.getLogger(FormQuanLyGiaoHangChinh.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.fillTablePhieuGiao(listPhieuGiao);
        } catch (Exception ex) {
            Logger.getLogger(FormQuanLyGiaoHangChinh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtTimKiemHoaDonTheoMaPropertyChange

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        int selectItem = cbxTimKiemPhieuGiaoTheoTrangThai.getSelectedIndex() + 1;
        try {
            this.listPhieuGiao = this.servicePhieuGiao.getAllDataGiaoHang(selectItem, ma());
        } catch (Exception ex) {
            Logger.getLogger(FormQuanLyGiaoHangChinh.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.fillTablePhieuGiao(listPhieuGiao);
        } catch (Exception ex) {
            Logger.getLogger(FormQuanLyGiaoHangChinh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnLamMoiThongTinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiThongTinActionPerformed
        this.txtDiaChiGiao.setText("");
        this.txtNguoiGiao.setText("");
        this.txtNguoiNhan.setText("");
        this.txtSDTNguoiGiao.setText("");
        this.txtSDTNguoiNhan.setText("");
        this.txtThoiGianGiao.setText("");
        this.txtThoiGianGiaoDen.setText("");
        this.txtThoiGianUocTinh.setText("");
        this.txaMoTa.setText("");
        this.jdcNgayGiao.setCalendar(null);
        this.jdcNgayGiaoDen.setCalendar(null);
        this.jdcNgayUocTinh.setCalendar(null);
    }//GEN-LAST:event_btnLamMoiThongTinActionPerformed

    private void btnLamMoiDanhSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiDanhSachActionPerformed
        this.txtTimKiemHoaDonTheoMa.setText("");
        int selectItem = cbxTimKiemPhieuGiaoTheoTrangThai.getSelectedIndex() + 1;
        try {
            this.listPhieuGiao = this.servicePhieuGiao.getAllDataGiaoHang(selectItem, ma());
        } catch (Exception ex) {
            Logger.getLogger(FormQuanLyGiaoHangChinh.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.fillTablePhieuGiao(listPhieuGiao);
        } catch (Exception ex) {
            Logger.getLogger(FormQuanLyGiaoHangChinh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLamMoiDanhSachActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoiDanhSach;
    private javax.swing.JButton btnLamMoiThongTin;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXacNhan;
    private javax.swing.JComboBox<String> cbxChonCheDo;
    private javax.swing.JComboBox<String> cbxTimKiemPhieuGiaoTheoTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private com.toedter.calendar.JDateChooser jdcNgayGiao;
    private com.toedter.calendar.JDateChooser jdcNgayGiaoDen;
    private com.toedter.calendar.JDateChooser jdcNgayUocTinh;
    private javax.swing.JLabel jlbMaHoaDon;
    private javax.swing.JLabel lb;
    private javax.swing.JTable tblDanhSachPhieuGiao;
    private javax.swing.JTextArea txaMoTa;
    private javax.swing.JTextField txtDiaChiGiao;
    private javax.swing.JTextField txtNguoiGiao;
    private javax.swing.JTextField txtNguoiNhan;
    private javax.swing.JTextField txtSDTNguoiGiao;
    private javax.swing.JTextField txtSDTNguoiNhan;
    private javax.swing.JTextField txtSoTienCanThu;
    private javax.swing.JTextField txtThoiGianGiao;
    private javax.swing.JTextField txtThoiGianGiaoDen;
    private javax.swing.JTextField txtThoiGianUocTinh;
    private javax.swing.JTextField txtTimKiemHoaDonTheoMa;
    // End of variables declaration//GEN-END:variables

}
