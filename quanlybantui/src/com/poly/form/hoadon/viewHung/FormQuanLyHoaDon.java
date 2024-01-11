package com.poly.form.hoadon.viewHung;

import com.formdev.flatlaf.FlatClientProperties;
import com.poly.Application;
import com.poly.form.hoadon.entityHung.HoaDonChiTietHung;
import com.poly.form.hoadon.entityHung.HoaDonHung;
import com.poly.form.hoadon.repositoryHung.HoaDonChiTietRepository;
import com.poly.form.hoadon.repositoryHung.HoaDonRepository;
import com.poly.form.hoadon.serviceHung.HoaDonChiTietService;
import com.poly.form.hoadon.serviceHung.HoaDonService;
import com.poly.form.hoadon.view.FormBanHang;
import com.poly.form.khachhang.view.FormHoaDonKhachHang;
import com.poly.util.ph42118.XuatHoaDon;
import java.awt.Desktop;
import java.awt.geom.Path2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.compress.harmony.pack200.BHSDCodec;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import raven.toast.Notifications;

/**
 *
 * @author Raven
 */
public class FormQuanLyHoaDon extends javax.swing.JPanel {

//    HoaDonRepository hoaDonRepository = new HoaDonRepository();
//    HoaDonChiTietRepository hoaDonChiTietRepository = new HoaDonChiTietRepository();
    HoaDonService hoaDonService = new HoaDonService();
    HoaDonChiTietService hoaDonChiTietService = new HoaDonChiTietService();

    DefaultTableModel modelHoaDonTaiQuay;
    DefaultTableModel modelHoaDonDatHang;
    DefaultTableModel modelHoaDonSanPhamChiTietTaiQuay;
    DefaultTableModel modelHoaDonSanPhamChiTietDatHang;
    List<HoaDonHung> listHoaDonTaiQuay;
    List<HoaDonHung> listHoaDatHang;

    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("vi-VN"));

    public FormQuanLyHoaDon() {
        initComponents();
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");

        this.modelHoaDonTaiQuay = (DefaultTableModel) tblHoadonTaiQuay.getModel();
        this.modelHoaDonDatHang = (DefaultTableModel) tblHoaDonDatHang.getModel();
        this.modelHoaDonSanPhamChiTietTaiQuay = (DefaultTableModel) tblHoadonSanPhamChiTietTaiQuay.getModel();
        this.modelHoaDonSanPhamChiTietDatHang = (DefaultTableModel) tblHoadonSanPhamChiTietDatHang.getModel();

        listHoaDonTaiQuay = hoaDonService.getAllHoaDonTaiQuayTrongNgay(1);
        listHoaDatHang = hoaDonService.getAllHoaDonTaiQuayTrongNgay(2);

        loadDataToTableHoaDonTaiQuayTrongNgay(listHoaDonTaiQuay);
        loadDataToTableHoaDonDatHangTrongNgay(listHoaDatHang);

        tblHoadonTaiQuay.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblHoaDonDatHang.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    public void updateAfterTrangThaiChanged() {
        String searchKey = txtSearchTaiQuay.getText().trim();
        int trangThaiHoaDON = cboTrangthaithanhtoanTaiQuay.getSelectedIndex();
        int hinhThucThanhToan = cboHinhthucthanhtoanTaiQuay.getSelectedIndex();
        int thoiGian = cbThoiGianTaiQuay.getSelectedIndex();
        Date fromDate = CTimeTaiQuay1.getDate();
        Date toDate = CTimeTaiQuay2.getDate();
        List<HoaDonHung> hoaDons = new ArrayList<>();
        if (trangThaiHoaDON == 7) {
            trangThaiHoaDON = -1;
        }

        if (hinhThucThanhToan == 3) {
            hinhThucThanhToan = -1;
        }

        if (!searchKey.isEmpty() || searchKey != null) {
            if (fromDate != null && toDate != null && fromDate.before(toDate)) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String sqlFromDate = sdf.format(fromDate);
                String sqlToDate = sdf.format(toDate);

                hoaDons = hoaDonService.BoLocHoaDonTaiQuay(1, searchKey, trangThaiHoaDON, hinhThucThanhToan, sqlFromDate, sqlToDate, thoiGian);

            } else if ((fromDate == null && toDate == null) || (fromDate != null && toDate != null && !fromDate.after(toDate))) {

                hoaDons = hoaDonService.BoLocHoaDonTaiQuay(1, searchKey, trangThaiHoaDON, hinhThucThanhToan, "", "", thoiGian);
            }
            loadDataToTableHoaDonTaiQuayTrongNgay(hoaDons);
        }
    }

    public void updateAfterTrangThaiChanged2() {
        String searchKey = txtSearchHoaDonDatHang.getText().trim();
        int trangThaiHoaDON = cboTrangThaiThanhToanDatHang.getSelectedIndex();
        int hinhThucThanhToan = cboHinhThucThanhToanDatHang.getSelectedIndex();
        int thoiGian = cbThoiGianDatHang.getSelectedIndex();
        Date fromDate = CTimeDatHang1.getDate();
        Date toDate = CTimeDatHang2.getDate();
        List<HoaDonHung> hoaDons = new ArrayList<>();
        if (trangThaiHoaDON == 7) {
            trangThaiHoaDON = -1;
        }

        if (hinhThucThanhToan == 3) {
            hinhThucThanhToan = -1;
        }

        if (!searchKey.isEmpty() || searchKey != null) {
            if (fromDate != null && toDate != null && fromDate.before(toDate)) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String sqlFromDate = sdf.format(fromDate);
                String sqlToDate = sdf.format(toDate);

                hoaDons = hoaDonService.BoLocHoaDonTaiQuay(2, searchKey, trangThaiHoaDON, hinhThucThanhToan, sqlFromDate, sqlToDate, thoiGian);

            } else if ((fromDate == null && toDate == null) || (fromDate != null && toDate != null && !fromDate.after(toDate))) {

                hoaDons = hoaDonService.BoLocHoaDonTaiQuay(2, searchKey, trangThaiHoaDON, hinhThucThanhToan, "", "", thoiGian);
            }
            loadDataToTableHoaDonTaiQuayTrongNgay(hoaDons);
        }
    }

    public void loadDataToTableHoaDonDatHangTrongNgay(List<HoaDonHung> list) {
        modelHoaDonDatHang.setRowCount(0);
        int stt = 1;

        for (HoaDonHung hd : list) {
            String tthd = "";
            if (hd.getTrangThai() == 0) {
                tthd = "Chờ thanh toán";
            } else if (hd.getTrangThai() == 1) {
                tthd = "Đã thanh toán";
            } else if (hd.getTrangThai() == 2) {
                tthd = "Đã hủy";
            } else if (hd.getTrangThai() == 3) {
                tthd = "Chờ giao hàng";
            } else if (hd.getTrangThai() == 4) {
                tthd = "Đang giao";
            } else if (hd.getTrangThai() == 5) {
                tthd = "Đã giao";
            } else if (hd.getTrangThai() == 6) {
                tthd = "Khách hẹn lại";
            } else if (hd.getTrangThai() == 7) {
                tthd = "Đã trả hàng";
            }

            String httt = "";
            if (hd.getHinhThucThanhToan() == 0) {
                httt = "Tiền mặt";
            } else if (hd.getHinhThucThanhToan() == 1) {
                httt = "Chuyển khoản";
            } else if (hd.getHinhThucThanhToan() == 2) {
                httt = "Kết hợp";
            }

            Object data[] = {stt, hd.getMaHoaDon(), hd.getTHoiGianTao(),
                hd.getTenNhanVien(),
                currencyFormatter.format(hd.getTongTienPhaiTra()),
                httt,
                hd.getTenKhachHang(), tthd, hd.getGhiChu()};
            modelHoaDonDatHang.addRow(data);
            stt++;
        }
    }

    public void loadDataToTableHoaDonTaiQuayTrongNgay(List<HoaDonHung> list) {
        modelHoaDonTaiQuay.setRowCount(0);
        int stt = 1;

        for (HoaDonHung hd : list) {
            String tthd = "";
            if (hd.getTrangThai() == 0) {
                tthd = "Chờ thanh toán";
            } else if (hd.getTrangThai() == 1) {
                tthd = "Đã thanh toán";
            } else if (hd.getTrangThai() == 2) {
                tthd = "Đã hủy";
            } else if (hd.getTrangThai() == 3) {
                tthd = "Chờ giao hàng";
            } else if (hd.getTrangThai() == 4) {
                tthd = "Đang giao";
            } else if (hd.getTrangThai() == 5) {
                tthd = "Đã giao";
            } else if (hd.getTrangThai() == 6) {
                tthd = "Khách hẹn lại";
            } else if (hd.getTrangThai() == 7) {
                tthd = "Đã trả hàng";
            }

            String httt = "";
            if (hd.getHinhThucThanhToan() == 0) {
                httt = "Tiền mặt";
            } else if (hd.getHinhThucThanhToan() == 1) {
                httt = "Chuyển khoản";
            } else if (hd.getHinhThucThanhToan() == 2) {
                httt = "Kết hợp";
            }
            System.out.println("");

       
           
            Object data[] = {stt, hd.getMaHoaDon(), hd.getTHoiGianTao(),
                hd.getTenNhanVien(),
             
               hd.getTongTienPhaiTra(),
                httt,
                hd.getTenKhachHang(), tthd, hd.getGhiChu()};
            modelHoaDonTaiQuay.addRow(data);
            stt++;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtSearchTaiQuay = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cboTrangthaithanhtoanTaiQuay = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        CTimeTaiQuay1 = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        CTimeTaiQuay2 = new com.toedter.calendar.JDateChooser();
        btnLocTaiQuay = new javax.swing.JToggleButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHoadonTaiQuay = new javax.swing.JTable();
        btnHuyHoaDonTaiQuay = new javax.swing.JButton();
        btnXuatDanhSachTaiQuay = new javax.swing.JButton();
        btnInHoaDonTaiQuay = new javax.swing.JButton();
        cboHinhthucthanhtoanTaiQuay = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cbThoiGianTaiQuay = new javax.swing.JComboBox<>();
        btnXemChiTietTaiQuay = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblHoadonSanPhamChiTietTaiQuay = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHoadonSanPhamChiTietDatHang = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtSearchHoaDonDatHang = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        cboTrangThaiThanhToanDatHang = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        CTimeDatHang1 = new com.toedter.calendar.JDateChooser();
        jLabel13 = new javax.swing.JLabel();
        CTimeDatHang2 = new com.toedter.calendar.JDateChooser();
        btnLocDatHang = new javax.swing.JToggleButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblHoaDonDatHang = new javax.swing.JTable();
        btnHuyHoaDonDatHang = new javax.swing.JButton();
        btnXuatDanhSachDatHang = new javax.swing.JButton();
        btnInHoaDonDatHang = new javax.swing.JButton();
        cboHinhThucThanhToanDatHang = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        cbThoiGianDatHang = new javax.swing.JComboBox<>();
        btnXemChiTietDatHang = new javax.swing.JButton();

        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("Quản lý hóa đơn");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 16))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("Tìm kiếm hóa đơn:");

        txtSearchTaiQuay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSearchTaiQuay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchTaiQuayKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setText("Trạng thái hóa đơn");

        cboTrangthaithanhtoanTaiQuay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboTrangthaithanhtoanTaiQuay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chờ thanh toán", "Đã thanh toán", "Đã hủy", "Chờ giao hàng", "Đang giao", "Đã giao", "Khách hẹn lại", "Tất cả" }));
        cboTrangthaithanhtoanTaiQuay.setSelectedIndex(7);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setText("Từ:");

        CTimeTaiQuay1.setDateFormatString("yyyy-MM-dd HH:mm:ss");
        CTimeTaiQuay1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setText("Đến:");

        CTimeTaiQuay2.setDateFormatString("yyyy-MM-dd HH:mm:ss");
        CTimeTaiQuay2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnLocTaiQuay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLocTaiQuay.setText("Lọc");
        btnLocTaiQuay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocTaiQuayActionPerformed(evt);
            }
        });

        tblHoadonTaiQuay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã hóa đơn", "Ngày tạo", "Nhân viên tạo", "Tổng tiền phải trả", "Hình thức thanh toán", "Tên khách hàng", "Trạng thái hóa đơn", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoadonTaiQuay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoadonTaiQuayMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblHoadonTaiQuay);

        btnHuyHoaDonTaiQuay.setBackground(new java.awt.Color(255, 0, 0));
        btnHuyHoaDonTaiQuay.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnHuyHoaDonTaiQuay.setText("Hủy hóa đơn");
        btnHuyHoaDonTaiQuay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyHoaDonTaiQuayActionPerformed(evt);
            }
        });

        btnXuatDanhSachTaiQuay.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnXuatDanhSachTaiQuay.setText("Xuất danh sách");
        btnXuatDanhSachTaiQuay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatDanhSachTaiQuayActionPerformed(evt);
            }
        });

        btnInHoaDonTaiQuay.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnInHoaDonTaiQuay.setText("In hóa đơn");
        btnInHoaDonTaiQuay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInHoaDonTaiQuayActionPerformed(evt);
            }
        });

        cboHinhthucthanhtoanTaiQuay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboHinhthucthanhtoanTaiQuay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "Chuyển khoản", "Kết hợp", "Tất cả" }));
        cboHinhthucthanhtoanTaiQuay.setSelectedIndex(3);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setText("Hình thức thanh thoán:");

        cbThoiGianTaiQuay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbThoiGianTaiQuay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mới nhất", "Trong ngày", "Tất cả" }));
        cbThoiGianTaiQuay.setSelectedIndex(1);

        btnXemChiTietTaiQuay.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnXemChiTietTaiQuay.setText("Xem chi tiết");
        btnXemChiTietTaiQuay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemChiTietTaiQuayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboTrangthaithanhtoanTaiQuay, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboHinhthucthanhtoanTaiQuay, 0, 154, Short.MAX_VALUE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSearchTaiQuay)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CTimeTaiQuay1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CTimeTaiQuay2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLocTaiQuay))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbThoiGianTaiQuay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnInHoaDonTaiQuay)
                        .addGap(18, 18, 18)
                        .addComponent(btnXuatDanhSachTaiQuay)
                        .addGap(18, 18, 18)
                        .addComponent(btnXemChiTietTaiQuay, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHuyHoaDonTaiQuay, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtSearchTaiQuay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbThoiGianTaiQuay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(cboTrangthaithanhtoanTaiQuay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(1, 1, 1))
                            .addComponent(btnLocTaiQuay)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CTimeTaiQuay2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(CTimeTaiQuay1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(jLabel9)
                                .addComponent(cboHinhthucthanhtoanTaiQuay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHuyHoaDonTaiQuay)
                    .addComponent(btnXuatDanhSachTaiQuay)
                    .addComponent(btnInHoaDonTaiQuay)
                    .addComponent(btnXemChiTietTaiQuay))
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết sản phẩm hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblHoadonSanPhamChiTietTaiQuay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Màu sắc", "Giá tiền", "Số lượng ", "Tổng tiền theo sản phẩm"
            }
        ));
        jScrollPane4.setViewportView(tblHoadonSanPhamChiTietTaiQuay);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        jTabbedPane1.addTab("Hóa đơn tại quầy", jPanel3);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết sản phẩm hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblHoadonSanPhamChiTietDatHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Màu sắc", "Giá tiền", "Số lượng", "Tổng tiền theo sản phẩm"
            }
        ));
        tblHoadonSanPhamChiTietDatHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoadonSanPhamChiTietDatHangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblHoadonSanPhamChiTietDatHang);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                .addGap(19, 19, 19))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 16))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setText("Tìm kiếm hóa đơn:");

        txtSearchHoaDonDatHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSearchHoaDonDatHang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchHoaDonDatHangKeyReleased(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setText("Trạng thái hóa đơn");

        cboTrangThaiThanhToanDatHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboTrangThaiThanhToanDatHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chờ thanh toán", "Đã thanh toán", "Đã hủy", "Chờ giao hàng", "Đang giao", "Đã giao", "Khách hẹn lại", "Tất cả" }));
        cboTrangThaiThanhToanDatHang.setSelectedIndex(7);

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel12.setText("Từ:");

        CTimeDatHang1.setDateFormatString("yyyy-MM-dd HH:mm:ss");
        CTimeDatHang1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel13.setText("Đến:");

        CTimeDatHang2.setDateFormatString("yyyy-MM-dd HH:mm:ss");
        CTimeDatHang2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnLocDatHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLocDatHang.setText("Lọc");
        btnLocDatHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocDatHangActionPerformed(evt);
            }
        });

        tblHoaDonDatHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã hóa đơn", "Ngày tạo", "Nhân viên tạo", "Tổng tiền phải trả", "Hình thức thanh toán", "Tên khách hàng", "Trạng thái hóa đơn", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDonDatHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonDatHangMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblHoaDonDatHang);

        btnHuyHoaDonDatHang.setBackground(new java.awt.Color(255, 0, 0));
        btnHuyHoaDonDatHang.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnHuyHoaDonDatHang.setText("Hủy hóa đơn");
        btnHuyHoaDonDatHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyHoaDonDatHangActionPerformed(evt);
            }
        });

        btnXuatDanhSachDatHang.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnXuatDanhSachDatHang.setText("Xuất danh sách");
        btnXuatDanhSachDatHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatDanhSachDatHangActionPerformed(evt);
            }
        });

        btnInHoaDonDatHang.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnInHoaDonDatHang.setText("In hóa đơn");
        btnInHoaDonDatHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInHoaDonDatHangActionPerformed(evt);
            }
        });

        cboHinhThucThanhToanDatHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboHinhThucThanhToanDatHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "Chuyển khoản", "Kết hợp", "Tất cả" }));
        cboHinhThucThanhToanDatHang.setSelectedIndex(3);

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel14.setText("Hình thức thanh thoán:");

        cbThoiGianDatHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbThoiGianDatHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mới nhất", "Trong ngày", "Tất cả", " " }));
        cbThoiGianDatHang.setSelectedIndex(1);

        btnXemChiTietDatHang.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnXemChiTietDatHang.setText("Xem chi tiết");
        btnXemChiTietDatHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemChiTietDatHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboTrangThaiThanhToanDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboHinhThucThanhToanDatHang, 0, 154, Short.MAX_VALUE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSearchHoaDonDatHang)))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CTimeDatHang1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CTimeDatHang2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLocDatHang))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbThoiGianDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnInHoaDonDatHang)
                        .addGap(18, 18, 18)
                        .addComponent(btnXuatDanhSachDatHang)
                        .addGap(18, 18, 18)
                        .addComponent(btnXemChiTietDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHuyHoaDonDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtSearchHoaDonDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbThoiGianDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(cboTrangThaiThanhToanDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(1, 1, 1))
                            .addComponent(btnLocDatHang)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CTimeDatHang2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(CTimeDatHang1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12)
                                .addComponent(jLabel14)
                                .addComponent(cboHinhThucThanhToanDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHuyHoaDonDatHang)
                    .addComponent(btnXuatDanhSachDatHang)
                    .addComponent(btnInHoaDonDatHang)
                    .addComponent(btnXemChiTietDatHang))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Hóa đơn đặt hàng", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnLocTaiQuayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocTaiQuayActionPerformed

        String searchKey = txtSearchTaiQuay.getText().trim();
        int trangThaiHoaDON = cboTrangthaithanhtoanTaiQuay.getSelectedIndex();
        int hinhThucThanhToan = cboHinhthucthanhtoanTaiQuay.getSelectedIndex();
        int thoiGian = cbThoiGianTaiQuay.getSelectedIndex();
        System.out.println("thoiGian" + thoiGian);

        Date fromDate = CTimeTaiQuay1.getDate();
        Date toDate = CTimeTaiQuay2.getDate();

        List<HoaDonHung> hoaDons = new ArrayList<>();

        if (trangThaiHoaDON == 7) {
            trangThaiHoaDON = -1;
        }

        if (hinhThucThanhToan == 3) {
            hinhThucThanhToan = -1;
        }

        if (fromDate != null && toDate != null && fromDate.before(toDate)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sqlFromDate = sdf.format(fromDate);
            String sqlToDate = sdf.format(toDate);
            hoaDons = hoaDonService.BoLocHoaDonTaiQuay(1, searchKey, trangThaiHoaDON, hinhThucThanhToan, sqlFromDate, sqlToDate, thoiGian);
        } else if ((fromDate == null && toDate == null) || (fromDate != null && toDate != null && !fromDate.after(toDate))) {
            hoaDons = hoaDonService.BoLocHoaDonTaiQuay(1, searchKey, trangThaiHoaDON, hinhThucThanhToan, "", "", thoiGian);
        } else {
            JOptionPane.showMessageDialog(null, "Ngày 'Từ' phải nhỏ hơn ngày 'Đến'");
        }

        loadDataToTableHoaDonTaiQuayTrongNgay(hoaDons);

    }//GEN-LAST:event_btnLocTaiQuayActionPerformed

    private void txtSearchTaiQuayKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchTaiQuayKeyReleased
        String searchKey = txtSearchTaiQuay.getText().trim();
        int trangThaiHoaDON = cboTrangthaithanhtoanTaiQuay.getSelectedIndex();
        int hinhThucThanhToan = cboHinhthucthanhtoanTaiQuay.getSelectedIndex();
        int thoiGian = cbThoiGianTaiQuay.getSelectedIndex();
        Date fromDate = CTimeTaiQuay1.getDate();
        Date toDate = CTimeTaiQuay2.getDate();
        List<HoaDonHung> hoaDons = new ArrayList<>();
        if (trangThaiHoaDON == 7) {
            trangThaiHoaDON = -1;
        }

        if (hinhThucThanhToan == 3) {
            hinhThucThanhToan = -1;
        }

        if (!searchKey.isEmpty() || searchKey != null) {
            if (fromDate != null && toDate != null && fromDate.before(toDate)) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String sqlFromDate = sdf.format(fromDate);
                String sqlToDate = sdf.format(toDate);

                hoaDons = hoaDonService.BoLocHoaDonTaiQuay(1, searchKey, trangThaiHoaDON, hinhThucThanhToan, sqlFromDate, sqlToDate, thoiGian);

            } else if ((fromDate == null && toDate == null) || (fromDate != null && toDate != null && !fromDate.after(toDate))) {

                hoaDons = hoaDonService.BoLocHoaDonTaiQuay(1, searchKey, trangThaiHoaDON, hinhThucThanhToan, "", "", thoiGian);
            }
            loadDataToTableHoaDonTaiQuayTrongNgay(hoaDons);
        } else {
            loadDataToTableHoaDonTaiQuayTrongNgay(listHoaDonTaiQuay);
        }
    }//GEN-LAST:event_txtSearchTaiQuayKeyReleased

    private void tblHoadonTaiQuayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoadonTaiQuayMouseClicked
        if (evt.getClickCount() == 2) {
            try {
                int row = this.tblHoadonTaiQuay.getSelectedRow();
                if (row != -1) {
                    String ma = (String) tblHoadonTaiQuay.getValueAt(row, 1);
                    HoaDonHung hd = hoaDonService.findHoaDonByMa(1, ma);
                    if (hd != null) {
                        List<HoaDonChiTietHung> result = hoaDonChiTietService.getAllSanPhamByIdHoaDon(hd.getIdHoaDon());
                        modelHoaDonSanPhamChiTietTaiQuay.setRowCount(0);
                        for (HoaDonChiTietHung hdct : result) {
                            Object data[] = {hdct.getMaSanPham(),
                                hdct.getTenSanPham(), hdct.getTenMau(),
                                currencyFormatter.format(hdct.getGiaTien()), hdct.getSoLuong(), currencyFormatter.format(hdct.getTongTien())};
                            modelHoaDonSanPhamChiTietTaiQuay.addRow(data);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_tblHoadonTaiQuayMouseClicked

    private void btnLocDatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocDatHangActionPerformed

        String searchKey = txtSearchHoaDonDatHang.getText().trim();
        int trangThaiHoaDON = cboTrangThaiThanhToanDatHang.getSelectedIndex();
        int hinhThucThanhToan = cboHinhThucThanhToanDatHang.getSelectedIndex();
        int thoiGian = cbThoiGianDatHang.getSelectedIndex();
        Date fromDate = CTimeDatHang1.getDate();
        Date toDate = CTimeDatHang2.getDate();

        List<HoaDonHung> hoaDons = new ArrayList<>();

        if (trangThaiHoaDON == 7) {
            trangThaiHoaDON = -1;
        }

        if (hinhThucThanhToan == 3) {
            hinhThucThanhToan = -1;
        }

        if (fromDate != null && toDate != null && fromDate.before(toDate)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sqlFromDate = sdf.format(fromDate);
            String sqlToDate = sdf.format(toDate);
            hoaDons = hoaDonService.BoLocHoaDonTaiQuay(2, searchKey, trangThaiHoaDON, hinhThucThanhToan, sqlFromDate, sqlToDate, thoiGian);
            for (HoaDonHung hdh : listHoaDonTaiQuay) {
                System.out.println(hdh.toString());
            }
        } else if ((fromDate == null && toDate == null) || (fromDate != null && toDate != null && !fromDate.after(toDate))) {
            hoaDons = hoaDonService.BoLocHoaDonTaiQuay(2, searchKey, trangThaiHoaDON, hinhThucThanhToan, "", "", thoiGian);
        } else {
            JOptionPane.showMessageDialog(null, "Ngày 'Từ' phải nhỏ hơn ngày 'Đến'");
        }

        loadDataToTableHoaDonDatHangTrongNgay(hoaDons);

    }//GEN-LAST:event_btnLocDatHangActionPerformed

    private void txtSearchHoaDonDatHangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchHoaDonDatHangKeyReleased

        String searchKey = txtSearchHoaDonDatHang.getText().trim();
        int trangThaiHoaDON = cboTrangThaiThanhToanDatHang.getSelectedIndex();
        int hinhThucThanhToan = cboHinhThucThanhToanDatHang.getSelectedIndex();
        int thoiGian = cbThoiGianDatHang.getSelectedIndex();
        Date fromDate = CTimeDatHang1.getDate();
        Date toDate = CTimeDatHang2.getDate();

        List<HoaDonHung> hoaDons = new ArrayList<>();
        if (trangThaiHoaDON == 7) {
            trangThaiHoaDON = -1;
        }

        if (hinhThucThanhToan == 3) {
            hinhThucThanhToan = -1;
        }

        if (!searchKey.isEmpty() || searchKey != null) {
            if (fromDate != null && toDate != null && fromDate.before(toDate)) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String sqlFromDate = sdf.format(fromDate);
                String sqlToDate = sdf.format(toDate);

                hoaDons = hoaDonService.BoLocHoaDonTaiQuay(2, searchKey, trangThaiHoaDON, hinhThucThanhToan, sqlFromDate, sqlToDate, thoiGian);

            } else if ((fromDate == null && toDate == null) || (fromDate != null && toDate != null && !fromDate.after(toDate))) {

                hoaDons = hoaDonService.BoLocHoaDonTaiQuay(2, searchKey, trangThaiHoaDON, hinhThucThanhToan, "", "", thoiGian);
            }
            loadDataToTableHoaDonDatHangTrongNgay(hoaDons);
        } else {
            loadDataToTableHoaDonDatHangTrongNgay(listHoaDatHang);
        }

    }//GEN-LAST:event_txtSearchHoaDonDatHangKeyReleased

    private void btnInHoaDonTaiQuayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInHoaDonTaiQuayActionPerformed
        try {
            int row = this.tblHoadonTaiQuay.getSelectedRow();
            if (row == -1) {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER,
                        "Chọn 1 hóa đơn để in");
                return;
            }
            String ma = (String) this.tblHoadonTaiQuay.getValueAt(row, 1);
            HoaDonHung hoaDon = hoaDonService.findHoaDonByMa(1, ma);
            // kiểm tra đã thanh toán
            if (hoaDon.getTrangThai() == 1) {
                int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn in hóa đơn không?");
                if (confirm == JOptionPane.YES_OPTION) {
                    JFileChooser avatarChooser = new JFileChooser("E:\\");
                    avatarChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    FileNameExtensionFilter avatarFilter = new FileNameExtensionFilter("Exel File", "xlsx");
                    avatarChooser.setFileFilter(avatarFilter);
                    avatarChooser.setAcceptAllFileFilterUsed(false);
                    int selectFileCheck = avatarChooser.showOpenDialog(this);
                    File selectedFile = avatarChooser.getSelectedFile();
                    if (!(selectFileCheck == JFileChooser.APPROVE_OPTION)) {
                        return;
                    }
                    //Muốn lấy đường dẫn và để vào export PDF thì 
                    String path = selectedFile.getAbsolutePath();
                    if (hoaDon.getTrangThai() == 1) {
                        XuatHoaDon export = new XuatHoaDon();
                        List<HoaDonChiTietHung> listHDCT = hoaDonChiTietService.getAllSanPhamByIdHoaDon(hoaDon.getIdHoaDon());
                        export.exportBill(hoaDon, listHDCT, path);
                    }
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER,
                            "In hóa đơn thành công");
                }
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER,
                        "Chọn 1 hóa đơn đã thanh toán");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnInHoaDonTaiQuayActionPerformed

    private void btnXemChiTietTaiQuayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemChiTietTaiQuayActionPerformed
        int row = this.tblHoadonTaiQuay.getSelectedRow();
        if (row == -1) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER,
                    "Chọn 1 hóa đơn để xem chi tiết");
            return;
        }
        String ma = (String) tblHoadonTaiQuay.getValueAt(row, 1);
        HoaDonHung hdh = hoaDonService.findHoaDonByMa(1, ma);
        System.out.println(hdh.getIdHoaDon());
        if (hdh != null) {
            FormUpdateTrangThaiTaiQuay viewupdatetrangthai = new FormUpdateTrangThaiTaiQuay(hdh, this);
            viewupdatetrangthai.setVisible(true);
        }
    }//GEN-LAST:event_btnXemChiTietTaiQuayActionPerformed

    private void btnHuyHoaDonTaiQuayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyHoaDonTaiQuayActionPerformed
        try {
            int row = this.tblHoadonTaiQuay.getSelectedRow();
            if (row == -1) {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER,
                        "Chọn 1 hóa đơn để hủy");
                return;
            }
            String ma = (String) this.tblHoadonTaiQuay.getValueAt(row, 1);
            HoaDonHung hoaDon = hoaDonService.findHoaDonByMa(1, ma);
            if (hoaDon.getTrangThai() == 2) {
                Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER,
                        "Trạng thái hóa đơn đã được hủy trước đó");
                return;
            }
            if (hoaDon.getTrangThai() == 1) {
                Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER,
                        "Không thể hủy hóa đơn đã thanh toán");
                return;
            }
            String lydo = JOptionPane.showInputDialog("Lý do hủy:");
            if (lydo == null) {
                return;
            }
            if (lydo.isEmpty()) {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER,
                        "Bạn chưa nhập lý do");
                return;
            }
            if (lydo.length() > 255) {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER,
                        "Lý do không được vượt quá 255 kí tự");
                return;
            }

            // cập nhật trạng thái thành hủy đơn hàng
            if (hoaDonService.updateTrangThaiHoaDon(1, ma, 2, lydo)) {
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER,
                        "Hủy đơn hàng thành công");

                String searchKey = txtSearchTaiQuay.getText().trim();
                int trangThaiHoaDON = cboTrangthaithanhtoanTaiQuay.getSelectedIndex();
                int hinhThucThanhToan = cboHinhthucthanhtoanTaiQuay.getSelectedIndex();
                int thoiGian = cbThoiGianTaiQuay.getSelectedIndex();
                Date fromDate = CTimeTaiQuay1.getDate();
                Date toDate = CTimeTaiQuay2.getDate();
                List<HoaDonHung> hoaDons = new ArrayList<>();
                if (trangThaiHoaDON == 7) {
                    trangThaiHoaDON = -1;
                }

                if (hinhThucThanhToan == 3) {
                    hinhThucThanhToan = -1;
                }

                if (!searchKey.isEmpty() || searchKey != null) {
                    if (fromDate != null && toDate != null && fromDate.before(toDate)) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String sqlFromDate = sdf.format(fromDate);
                        String sqlToDate = sdf.format(toDate);

                        hoaDons = hoaDonService.BoLocHoaDonTaiQuay(1, searchKey, trangThaiHoaDON, hinhThucThanhToan, sqlFromDate, sqlToDate, thoiGian);

                    } else if ((fromDate == null && toDate == null) || (fromDate != null && toDate != null && !fromDate.after(toDate))) {

                        hoaDons = hoaDonService.BoLocHoaDonTaiQuay(1, searchKey, trangThaiHoaDON, hinhThucThanhToan, "", "", thoiGian);
                    }
                    loadDataToTableHoaDonTaiQuayTrongNgay(hoaDons);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnHuyHoaDonTaiQuayActionPerformed

    private void btnXuatDanhSachTaiQuayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatDanhSachTaiQuayActionPerformed
        String searchKey = txtSearchTaiQuay.getText().trim();
        int trangThaiHoaDON = cboTrangthaithanhtoanTaiQuay.getSelectedIndex();
        int hinhThucThanhToan = cboHinhthucthanhtoanTaiQuay.getSelectedIndex();
        int thoiGian = cbThoiGianTaiQuay.getSelectedIndex();
        Date fromDate = CTimeTaiQuay1.getDate();
        Date toDate = CTimeTaiQuay2.getDate();
        List<HoaDonHung> listHoaDon = new ArrayList<>();
        if (trangThaiHoaDON == 7) {
            trangThaiHoaDON = -1;
        }

        if (hinhThucThanhToan == 3) {
            hinhThucThanhToan = -1;
        }

        if (!searchKey.isEmpty() || searchKey != null) {
            if (fromDate != null && toDate != null && fromDate.before(toDate)) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String sqlFromDate = sdf.format(fromDate);
                String sqlToDate = sdf.format(toDate);

                listHoaDon = hoaDonService.BoLocHoaDonTaiQuay(1, searchKey, trangThaiHoaDON, hinhThucThanhToan, sqlFromDate, sqlToDate, thoiGian);

            } else if ((fromDate == null && toDate == null) || (fromDate != null && toDate != null && !fromDate.after(toDate))) {

                listHoaDon = hoaDonService.BoLocHoaDonTaiQuay(1, searchKey, trangThaiHoaDON, hinhThucThanhToan, "", "", thoiGian);
            }

        }

        if (listHoaDon.size() == 0) {
            JOptionPane.showMessageDialog(this, "Danh sách trống");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn xuất danh sách hóa đơn không?");
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                JFileChooser avatarChooser = new JFileChooser("D:\\");
                avatarChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); //Giới hạn chỉ chọn đc thư mục
                FileNameExtensionFilter avatarFilter = new FileNameExtensionFilter("Exel File", "xlsx");
                avatarChooser.setFileFilter(avatarFilter);
                avatarChooser.setAcceptAllFileFilterUsed(false);
                int selectFileCheck = avatarChooser.showOpenDialog(this);
                File selectedFile = avatarChooser.getSelectedFile();
                if (!(selectFileCheck == JFileChooser.APPROVE_OPTION)) {
                    return;
                }
                String path = selectedFile.getAbsolutePath();
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet sheet = workbook.createSheet("Danh Sách Hóa Đơn");
                XSSFRow row = null;
                Cell cell = null;
                row = sheet.createRow(1);
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue("STT");

                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue("Mã HD");

                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue("Ngày tạo");
                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue("Tổng tiền");

                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue("Tên NV");

                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue("Tên KH");

                cell = row.createCell(6, CellType.STRING);
                cell.setCellValue("Lý do");

                cell = row.createCell(7, CellType.STRING);
                cell.setCellValue("Trạng thái");
                int index = 2;
                int x = 1;

                for (HoaDonHung h : listHoaDon) {
                    row = sheet.createRow(index);
                    cell = row.createCell(0, CellType.STRING);
                    cell.setCellValue(x);

                    cell = row.createCell(1, CellType.STRING);
                    cell.setCellValue(h.getMaHoaDon());

                    cell = row.createCell(2, CellType.STRING);
                    cell.setCellValue(String.valueOf(h.getTHoiGianTao()));

                    cell = row.createCell(3, CellType.STRING);
                    cell.setCellValue(h.getTongTienPhaiTra() + "");

                    cell = row.createCell(4, CellType.STRING);
                    cell.setCellValue(h.getTenNhanVien());

                    cell = row.createCell(5, CellType.STRING);
                    cell.setCellValue(h.getTenKhachHang());

                    cell = row.createCell(6, CellType.STRING);
                    cell.setCellValue(h.getGhiChu());

                    cell = row.createCell(7, CellType.STRING);
                    cell.setCellValue(h.getTrangThai() + "");
                    x++;
                    index++;
                }
                try {
                    String pathFile = path + "\\" + "DanhSachHoaDon" + Calendar.getInstance().getTimeInMillis() + ".xlsx";
                    File file = new File(pathFile);
                    FileOutputStream outputStream = new FileOutputStream(pathFile);
                    workbook.write(outputStream);
                    workbook.close();
                    if (!Desktop.isDesktopSupported()) {
                        return;
                    }
                    Desktop desktop = Desktop.getDesktop();
                    if (file.exists()) {
                        desktop.open(file);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Xuất danh sách thành công");
        }
    }//GEN-LAST:event_btnXuatDanhSachTaiQuayActionPerformed

    private void tblHoaDonDatHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonDatHangMouseClicked
        if (evt.getClickCount() == 2) {
            try {
                int row = this.tblHoaDonDatHang.getSelectedRow();
                if (row != -1) {
                    String ma = (String) tblHoaDonDatHang.getValueAt(row, 1);
                    System.out.println("ma hoa don dat hang" + ma);
                    HoaDonHung hd = hoaDonService.findHoaDonByMa(2, ma);
                    if (hd != null) {
                        List<HoaDonChiTietHung> result = hoaDonChiTietService.getAllSanPhamByIdHoaDon(hd.getIdHoaDon());
                        modelHoaDonSanPhamChiTietDatHang.setRowCount(0);
                        for (HoaDonChiTietHung hdct : result) {
                            Object data[] = {hdct.getMaSanPham(),
                                hdct.getTenSanPham(), hdct.getTenMau(),
                                currencyFormatter.format(hdct.getGiaTien()), hdct.getSoLuong(), currencyFormatter.format(hdct.getTongTien())};
                            modelHoaDonSanPhamChiTietDatHang.addRow(data);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_tblHoaDonDatHangMouseClicked

    private void tblHoadonSanPhamChiTietDatHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoadonSanPhamChiTietDatHangMouseClicked

    }//GEN-LAST:event_tblHoadonSanPhamChiTietDatHangMouseClicked

    private void btnInHoaDonDatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInHoaDonDatHangActionPerformed
        try {
            int row = this.tblHoaDonDatHang.getSelectedRow();
            if (row == -1) {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER,
                        "Chọn 1 hóa đơn để in");
                return;
            }
            String ma = (String) this.tblHoaDonDatHang.getValueAt(row, 1);
            HoaDonHung hoaDon = hoaDonService.findHoaDonByMa(2, ma);
            // kiểm tra đã thanh toán
            if (hoaDon.getTrangThai() == 1) {
                int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn in hóa đơn không?");
                if (confirm == JOptionPane.YES_OPTION) {
                    JFileChooser avatarChooser = new JFileChooser("E:\\");
                    avatarChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    FileNameExtensionFilter avatarFilter = new FileNameExtensionFilter("Exel File", "xlsx");
                    avatarChooser.setFileFilter(avatarFilter);
                    avatarChooser.setAcceptAllFileFilterUsed(false);
                    int selectFileCheck = avatarChooser.showOpenDialog(this);
                    File selectedFile = avatarChooser.getSelectedFile();
                    if (!(selectFileCheck == JFileChooser.APPROVE_OPTION)) {
                        return;
                    }
                    String path = selectedFile.getAbsolutePath();

                    // Chỉ in những hóa đơn đã thanh toán
                    if (hoaDon.getTrangThai() == 1) {
                        XuatHoaDon export = new XuatHoaDon();
                        List<HoaDonChiTietHung> listHDCT = hoaDonChiTietService.getAllSanPhamByIdHoaDon(hoaDon.getIdHoaDon());
                        export.exportBill(hoaDon, listHDCT, path);

                    }
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER,
                            "In hóa đơn thành công");
                }
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER,
                        "Chọn 1 hóa đơn đã thanh toán");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnInHoaDonDatHangActionPerformed

    private void btnXuatDanhSachDatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatDanhSachDatHangActionPerformed

        String searchKey = txtSearchHoaDonDatHang.getText().trim();
        int trangThaiHoaDON = cboTrangThaiThanhToanDatHang.getSelectedIndex();
        int hinhThucThanhToan = cboHinhThucThanhToanDatHang.getSelectedIndex();
        int thoiGian = cbThoiGianDatHang.getSelectedIndex();
        Date fromDate = CTimeDatHang1.getDate();
        Date toDate = CTimeDatHang2.getDate();

        List<HoaDonHung> listHoaDon = new ArrayList<>();

        if (trangThaiHoaDON == 7) {
            trangThaiHoaDON = -1;
        }

        if (hinhThucThanhToan == 3) {
            hinhThucThanhToan = -1;
        }

        if (!searchKey.isEmpty() || searchKey != null) {
            if (fromDate != null && toDate != null && fromDate.before(toDate)) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String sqlFromDate = sdf.format(fromDate);
                String sqlToDate = sdf.format(toDate);

                listHoaDon = hoaDonService.BoLocHoaDonTaiQuay(2, searchKey, trangThaiHoaDON, hinhThucThanhToan, sqlFromDate, sqlToDate, thoiGian);

            } else if ((fromDate == null && toDate == null) || (fromDate != null && toDate != null && !fromDate.after(toDate))) {

                listHoaDon = hoaDonService.BoLocHoaDonTaiQuay(2, searchKey, trangThaiHoaDON, hinhThucThanhToan, "", "", thoiGian);
            }

        }

        if (listHoaDon.size() == 0) {
            JOptionPane.showMessageDialog(this, "Danh sách trống");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn xuất danh sách hóa đơn không?");
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                JFileChooser avatarChooser = new JFileChooser("D:\\");
                avatarChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                FileNameExtensionFilter avatarFilter = new FileNameExtensionFilter("Exel File", "xlsx");
                avatarChooser.setFileFilter(avatarFilter);
                avatarChooser.setAcceptAllFileFilterUsed(false);
                int selectFileCheck = avatarChooser.showOpenDialog(this);
                File selectedFile = avatarChooser.getSelectedFile();
                if (!(selectFileCheck == JFileChooser.APPROVE_OPTION)) {
                    return;
                }

                String path = selectedFile.getAbsolutePath();
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet sheet = workbook.createSheet("Danh Sách Hóa Đơn");
                XSSFRow row = null;
                Cell cell = null;
                row = sheet.createRow(1);
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue("STT");

                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue("Mã HD");

                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue("Ngày tạo");
                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue("Tổng tiền");

                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue("Tên NV");

                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue("Tên KH");

                cell = row.createCell(6, CellType.STRING);
                cell.setCellValue("Lý do");

                cell = row.createCell(7, CellType.STRING);
                cell.setCellValue("Trạng thái");
                int index = 2;
                int x = 1;

                for (HoaDonHung h : listHoaDon) {
                    row = sheet.createRow(index);
                    cell = row.createCell(0, CellType.STRING);
                    cell.setCellValue(x);

                    cell = row.createCell(1, CellType.STRING);
                    cell.setCellValue(h.getMaHoaDon());

                    cell = row.createCell(2, CellType.STRING);
                    cell.setCellValue(String.valueOf(h.getTHoiGianTao()));

                    cell = row.createCell(3, CellType.STRING);
                    cell.setCellValue(h.getTongTienPhaiTra() + "");

                    cell = row.createCell(4, CellType.STRING);
                    cell.setCellValue(h.getTenNhanVien());

                    cell = row.createCell(5, CellType.STRING);
                    cell.setCellValue(h.getTenKhachHang());

                    cell = row.createCell(6, CellType.STRING);
                    cell.setCellValue(h.getGhiChu());

                    cell = row.createCell(7, CellType.STRING);
                    cell.setCellValue(h.getTrangThai() + "");
                    x++;
                    index++;
                }
                try {
                    String pathFile = path + "\\" + "DanhSachHoaDon" + Calendar.getInstance().getTimeInMillis() + ".xlsx";
                    File file = new File(pathFile);
                    FileOutputStream outputStream = new FileOutputStream(pathFile);
                    workbook.write(outputStream);
                    workbook.close();
                    if (!Desktop.isDesktopSupported()) {
                        return;
                    }
                    Desktop desktop = Desktop.getDesktop();
                    if (file.exists()) {
                        desktop.open(file);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Xuất danh sách thành công");
        }
    }//GEN-LAST:event_btnXuatDanhSachDatHangActionPerformed

    private void btnXemChiTietDatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemChiTietDatHangActionPerformed
        int row = this.tblHoaDonDatHang.getSelectedRow();
        if (row == -1) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER,
                    "Chọn 1 hóa đơn để xem chi tiết");
            return;
        }
        String ma = (String) tblHoaDonDatHang.getValueAt(row, 1);
        HoaDonHung hdh = hoaDonService.findHoaDonByMa(2, ma);
        System.out.println(hdh.getIdHoaDon());
        if (hdh != null) {
            FormUpdateTrangThaiDatHang viewupdatetrangthai = new FormUpdateTrangThaiDatHang(hdh, this);
            viewupdatetrangthai.setVisible(true);
        }
    }//GEN-LAST:event_btnXemChiTietDatHangActionPerformed

    private void btnHuyHoaDonDatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyHoaDonDatHangActionPerformed

        try {
            int row = this.tblHoaDonDatHang.getSelectedRow();
            if (row == -1) {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER,
                        "Chọn 1 hóa đơn để hủy");
                return;
            }
            String ma = (String) this.tblHoaDonDatHang.getValueAt(row, 1);
            HoaDonHung hoaDon = hoaDonService.findHoaDonByMa(2, ma);
            if (hoaDon.getTrangThai() == 2) {
                Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER,
                        "Trạng thái hóa đơn đã được hủy trước đó");
                return;
            }
            if (hoaDon.getTrangThai() == 1) {
                Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER,
                        "Không thể hủy hóa đơn đã thanh toán");
                return;
            }
            String lydo = JOptionPane.showInputDialog("Lý do hủy:");
            if (lydo == null) {
                return;
            }
            if (lydo.isEmpty()) {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER,
                        "Bạn chưa nhập lý do");
                return;
            }
            if (lydo.length() > 255) {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER,
                        "Lý do không được vượt quá 255 kí tự");
                return;
            }

            // cập nhật trạng thái thành hủy đơn hàng
            if (hoaDonService.updateTrangThaiHoaDon(2, ma, 2, lydo)) {
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER,
                        "Hủy đơn hàng thành công");

                String searchKey = txtSearchHoaDonDatHang.getText().trim();
                int trangThaiHoaDON = cboTrangThaiThanhToanDatHang.getSelectedIndex();
                int hinhThucThanhToan = cboHinhThucThanhToanDatHang.getSelectedIndex();
                int thoiGian = cbThoiGianDatHang.getSelectedIndex();
                Date fromDate = CTimeDatHang1.getDate();
                Date toDate = CTimeDatHang2.getDate();

                List<HoaDonHung> hoaDons = new ArrayList<>();
                if (trangThaiHoaDON == 7) {
                    trangThaiHoaDON = -1;
                }

                if (hinhThucThanhToan == 3) {
                    hinhThucThanhToan = -1;
                }

                if (!searchKey.isEmpty() || searchKey != null) {
                    if (fromDate != null && toDate != null && fromDate.before(toDate)) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String sqlFromDate = sdf.format(fromDate);
                        String sqlToDate = sdf.format(toDate);

                        hoaDons = hoaDonService.BoLocHoaDonTaiQuay(2, searchKey, trangThaiHoaDON, hinhThucThanhToan, sqlFromDate, sqlToDate, thoiGian);

                    } else if ((fromDate == null && toDate == null) || (fromDate != null && toDate != null && !fromDate.after(toDate))) {

                        hoaDons = hoaDonService.BoLocHoaDonTaiQuay(2, searchKey, trangThaiHoaDON, hinhThucThanhToan, "", "", thoiGian);
                    }
                    loadDataToTableHoaDonDatHangTrongNgay(hoaDons);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnHuyHoaDonDatHangActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser CTimeDatHang1;
    private com.toedter.calendar.JDateChooser CTimeDatHang2;
    private com.toedter.calendar.JDateChooser CTimeTaiQuay1;
    private com.toedter.calendar.JDateChooser CTimeTaiQuay2;
    private javax.swing.JButton btnHuyHoaDonDatHang;
    private javax.swing.JButton btnHuyHoaDonTaiQuay;
    private javax.swing.JButton btnInHoaDonDatHang;
    private javax.swing.JButton btnInHoaDonTaiQuay;
    private javax.swing.JToggleButton btnLocDatHang;
    private javax.swing.JToggleButton btnLocTaiQuay;
    private javax.swing.JButton btnXemChiTietDatHang;
    private javax.swing.JButton btnXemChiTietTaiQuay;
    private javax.swing.JButton btnXuatDanhSachDatHang;
    private javax.swing.JButton btnXuatDanhSachTaiQuay;
    private javax.swing.JComboBox<String> cbThoiGianDatHang;
    private javax.swing.JComboBox<String> cbThoiGianTaiQuay;
    private javax.swing.JComboBox<String> cboHinhThucThanhToanDatHang;
    private javax.swing.JComboBox<String> cboHinhthucthanhtoanTaiQuay;
    private javax.swing.JComboBox<String> cboTrangThaiThanhToanDatHang;
    private javax.swing.JComboBox<String> cboTrangthaithanhtoanTaiQuay;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lb;
    private javax.swing.JTable tblHoaDonDatHang;
    private javax.swing.JTable tblHoadonSanPhamChiTietDatHang;
    private javax.swing.JTable tblHoadonSanPhamChiTietTaiQuay;
    private javax.swing.JTable tblHoadonTaiQuay;
    private javax.swing.JTextField txtSearchHoaDonDatHang;
    private javax.swing.JTextField txtSearchTaiQuay;
    // End of variables declaration//GEN-END:variables
}
