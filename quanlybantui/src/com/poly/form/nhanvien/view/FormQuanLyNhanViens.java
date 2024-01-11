package com.poly.form.nhanvien.view;

import com.formdev.flatlaf.FlatClientProperties;
import com.poly.Application;
import com.poly.entity.NhanVien;
import com.poly.form.nhanvien.entity.NhanVienEntity;
import com.poly.form.nhanvien.repository.NhanVienRepo;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.security.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import static java.time.LocalDateTime.now;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import raven.toast.Notifications;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.awt.Desktop;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FormQuanLyNhanViens extends javax.swing.JPanel {

    private NhanVienRepo service = new NhanVienRepo();
    private DefaultTableModel mol = new DefaultTableModel();
    int index = -1;
    List<NhanVienEntity> listnv = new ArrayList<>();

    public FormQuanLyNhanViens() {
        initComponents();
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        listnv = service.getAllNhanVien();
        filltable(listnv);
    }

    void filltable(List<NhanVienEntity> list) {
        mol = (DefaultTableModel) tblqlnhanvien.getModel();
        mol.setRowCount(0);
        for (NhanVienEntity n : list) {
            mol.addRow(n.todataRow());
        }
    }

    void showdata(int index) {
        NhanVienEntity nv = listnv.get(index);
        txtusername.setText(nv.getUsername());
        txtdiachi.setText(nv.getDiaChi());
        txtemail.setText(nv.getEmail());
        txtmaddanh.setText(nv.getMaDinhDanh());
        Date ngaySinh = nv.getNgaySinh();
        if (ngaySinh != null) {
            jdcngaysinh.setDate(ngaySinh);
        }
        txtsdt.setText(nv.getSoDienThoai());
        txttennv.setText(nv.getTen());
        if (nv.getGioiTinh().equals("Nam")) {
            this.rdonam.setSelected(true);
        } else if (nv.getGioiTinh().equals("Nữ")) {
            this.rdonu.setSelected(true);
        }
        if (nv.getTrangThai().equals("Đang Làm")) {
            this.rdodanglam.setSelected(true);
        } else if (nv.getTrangThai().equals("Nghỉ Làm")) {
            this.rdonghilam.setSelected(true);
        }
        datesua.setText(nv.getThoiGianSua() + "");
        datetime.setText(nv.getThoiGianSua() + "");

        //datetime.setText(formatDate(nv.getThoiGianTao()));
        //datesua.setText(formatDate(nv.getThoiGianSua()));
    }

    void showdata2(int index) {
        NhanVienEntity nv = service.getAllNhanVien().get(index);
        txtusername.setText(nv.getUsername());
        txtdiachi.setText(nv.getDiaChi());
        txtemail.setText(nv.getEmail());
        txtmaddanh.setText(nv.getMaDinhDanh());
        Date ngaySinh = nv.getNgaySinh();
        if (ngaySinh != null) {
            jdcngaysinh.setDate(ngaySinh);
        }
        txtsdt.setText(nv.getSoDienThoai());
        txttennv.setText(nv.getTen());
        if (nv.getGioiTinh().equals("Nam")) {
            this.rdonam.setSelected(true);
        } else if (nv.getGioiTinh().equals("Nữ")) {
            this.rdonu.setSelected(true);
        }
        if (nv.getTrangThai().equals("Đang Làm")) {
            this.rdodanglam.setSelected(true);
        } else if (nv.getTrangThai().equals("Nghỉ Làm")) {
            this.rdonghilam.setSelected(true);
        }
        datetime.setText(formatDate(nv.getThoiGianTao()));
        datesua.setText(formatDate(nv.getThoiGianSua()));
    }

// Hàm chuyển đổi định dạng ngày
    private String formatDate(Date date) {
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        return outputFormat.format(date);
    }

    NhanVienEntity readForm() {
        String username = txtusername.getText().trim();
        String name = txttennv.getText().trim();
        String email = txtemail.getText().trim();
        String sdt = txtsdt.getText().trim();
        String gt = rdonam.isSelected() ? "Nam" : "Nữ";
        String status = rdodanglam.isSelected() ? "Đang Làm" : "Nghỉ Làm";
        Date ngaysinh = jdcngaysinh.getDate();
        String madd = txtmaddanh.getText().trim();
        String diachi = txtdiachi.getText().trim();
        // Lấy thời gian tạo
        Date thoiGianTao = null; // Để thời gian tạo bằng null khi cập nhật
        // Lấy thời gian sửa
        Date thoiGianSua = new Date(); // Lấy thời gian hiện tại
        if (isThemMoi) {
            // Nếu đang thêm mới, đặt ThoiGianTao thành thời gian hiện tại
            thoiGianTao = new Date();
        }
        return new NhanVienEntity(username, name, madd, sdt, ngaysinh, email, gt, diachi, status, thoiGianTao, thoiGianSua);
    }

    void clearForm() {
        txtusername.setText("");
        txttennv.setText("");
        txtdiachi.setText("");
        txtemail.setText("");
        txtmaddanh.setText("");
        jdcngaysinh.setDate(null);
        rdonam.setSelected(true);
        rdodanglam.setSelected(true);
        txtsdt.setText("");
        datetime.setText("");
        datesua.setText("");
    }

    void loadNhanVienToTable(List<NhanVienEntity> nhanVienList) {
        DefaultTableModel model = (DefaultTableModel) tblqlnhanvien.getModel();
        model.setRowCount(0);
        int stt = 1;
        for (NhanVienEntity nv : nhanVienList) {
            Object[] rowData = {
                stt,
                //nv.getId(), 
                nv.getUsername(),
                nv.getTen(),
                nv.getEmail(),
                nv.getSoDienThoai(),
                nv.getGioiTinh(),
                nv.getTrangThai(),
                nv.getNgaySinh(),
                nv.getDiaChi(),
                nv.getMaDinhDanh()};
            model.addRow(rowData);
            stt++;
        }
    }

    public void filterNhanVienByStatus(String status) {
        List<NhanVienEntity> filteredList = new ArrayList<>();
        for (NhanVienEntity nv : filteredList) {
            if (nv.getTrangThai().equalsIgnoreCase(status)) {
                filteredList.add(nv);
            }
        }
        loadNhanVienToTable(filteredList);
    }
    private boolean isThemMoi = true;

    public void openFile(String file) {
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);

        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    boolean isDataEmpty() {
        boolean isEmpty = true;
        if (txtusername.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập UserName.");
            return false;
        } else if (!service.isUsernameUnique(txtusername.getText())) {
            JOptionPane.showMessageDialog(this, "Tên đăng nhập đã tồn tại. Vui lòng chọn tên khác.");
            return false;
        }
        if (txttennv.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên nhân viên.");
            return false;
        }
        // Kiểm tra số điện thoại chỉ chứa số không có chữ
        if (txtsdt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại.");
            return false;
        } else if (!Pattern.matches("^0\\d{9}$", txtsdt.getText())) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ. Vui lòng nhập số điện thoại bắt đầu từ 0 và có 10 chữ số.");
            return false;
        }
        if (txtemail.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập địa chỉ email.");
            return false;
        }
        // Kiểm tra định dạng email
        if (!Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", txtemail.getText())) {
            JOptionPane.showMessageDialog(this, "Email không đúng định dạng.");
            return false;
        }
        if (txtmaddanh.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã định danh.");
            return false;
        }
// Kiểm tra mã định danh chỉ chứa số không có chữ
        if (!Pattern.matches("^\\d+$", txtmaddanh.getText())) {
            JOptionPane.showMessageDialog(this, "Mã định danh chỉ được chứa số.");
            return false;
        }
        if (jdcngaysinh.getDate().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày sinh.");
            return false;
        }
        // Kiểm tra định dạng ngày sinh (dd/MM/yyyy)
//        if (!Pattern.matches("^\\d{2}/\\d{2}/\\d{4}$", txtngaysinh.getText())) {
//            JOptionPane.showMessageDialog(this, "Ngày sinh không đúng định dạng (dd/MM/yyyy).");
//            return false;
//        }
        if (txtdiachi.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập địa chỉ.");
            return false;
        }

        return isEmpty;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        btnsreach = new javax.swing.JButton();
        checkdanglam = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        checknghilam = new javax.swing.JRadioButton();
        btnnvdaxoa = new javax.swing.JButton();
        lb = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txttimkiem = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblqlnhanvien = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtusername = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txttennv = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtmaddanh = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtsdt = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtdiachi = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        rdonam = new javax.swing.JRadioButton();
        rdonu = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        rdodanglam = new javax.swing.JRadioButton();
        rdonghilam = new javax.swing.JRadioButton();
        datetime = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        datesua = new javax.swing.JLabel();
        jdcngaysinh = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        btnupdatenv = new javax.swing.JButton();
        btnadd = new javax.swing.JButton();
        btndeletenv = new javax.swing.JButton();
        btndeletenv1 = new javax.swing.JButton();
        btnexxportexcel = new javax.swing.JButton();
        btntop = new javax.swing.JButton();
        btnprevious = new javax.swing.JButton();
        btnext = new javax.swing.JButton();
        btnend = new javax.swing.JButton();
        lbltrang = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cbbcheckstatus = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cbblocgioitinh = new javax.swing.JComboBox<>();

        btnsreach.setText("Tìm Kiếm");
        btnsreach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsreachActionPerformed(evt);
            }
        });

        buttonGroup2.add(checkdanglam);
        checkdanglam.setText("Đang Làm");
        checkdanglam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkdanglamActionPerformed(evt);
            }
        });

        buttonGroup2.add(jRadioButton3);
        jRadioButton3.setSelected(true);
        jRadioButton3.setText("Tất Cả");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        buttonGroup2.add(checknghilam);
        checknghilam.setText("Nghỉ Làm");
        checknghilam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checknghilamActionPerformed(evt);
            }
        });

        btnnvdaxoa.setBackground(new java.awt.Color(51, 255, 51));
        btnnvdaxoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnnvdaxoa.setText("Backup");
        btnnvdaxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnvdaxoaActionPerformed(evt);
            }
        });

        lb.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("Quản Lý Nhân Viên");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Nhập ( Tên , Email , Sdt )");

        txttimkiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttimkiemKeyReleased(evt);
            }
        });

        tblqlnhanvien.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tblqlnhanvien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "#", "UserName", "Ho&Ten", "Email", "Số Điện Thoại", "Giới Tính", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblqlnhanvien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblqlnhanvienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblqlnhanvien);
        if (tblqlnhanvien.getColumnModel().getColumnCount() > 0) {
            tblqlnhanvien.getColumnModel().getColumn(0).setResizable(false);
            tblqlnhanvien.getColumnModel().getColumn(1).setResizable(false);
            tblqlnhanvien.getColumnModel().getColumn(2).setResizable(false);
            tblqlnhanvien.getColumnModel().getColumn(3).setResizable(false);
            tblqlnhanvien.getColumnModel().getColumn(4).setResizable(false);
            tblqlnhanvien.getColumnModel().getColumn(5).setResizable(false);
            tblqlnhanvien.getColumnModel().getColumn(6).setResizable(false);
        }

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setForeground(new java.awt.Color(153, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("* UserName");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setText("* Tên Nhân Viên");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setText("* Mã Định Danh ( CMT , CCCD )");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setText("* Ngày Sinh");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setText("* Số Điện Thoại");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel9.setText("* Địa Chỉ Cụ Thể");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel10.setText("* Email");

        txtdiachi.setColumns(20);
        txtdiachi.setRows(5);
        jScrollPane3.setViewportView(txtdiachi);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel11.setText("* Giới Tính");

        buttonGroup1.add(rdonam);
        rdonam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdonam.setSelected(true);
        rdonam.setText("Nam");

        buttonGroup1.add(rdonu);
        rdonu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdonu.setText("Nữ");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("* Trạng Thái");

        buttonGroup3.add(rdodanglam);
        rdodanglam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdodanglam.setSelected(true);
        rdodanglam.setText("Đang Làm");

        buttonGroup3.add(rdonghilam);
        rdonghilam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdonghilam.setText("Nghỉ Làm");

        datetime.setText("               ");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Ngày Tạo:");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Sửa Lần Cuối:");

        datesua.setText("                 ");

        jdcngaysinh.setForeground(new java.awt.Color(255, 255, 255));
        jdcngaysinh.setDateFormatString("dd-MM-yyyy");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2)
                        .addComponent(jLabel5)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtusername)
                        .addComponent(txttennv)
                        .addComponent(txtmaddanh))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(rdodanglam)
                                .addGap(18, 18, 18)
                                .addComponent(rdonghilam)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(datesua, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                            .addComponent(datetime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7)
                            .addComponent(txtsdt)
                            .addComponent(jLabel8)
                            .addComponent(jdcngaysinh, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel11)
                                        .addComponent(rdonu))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rdonam))
                                .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(102, 102, 102))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtusername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txttennv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtmaddanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jdcngaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdonu)
                            .addComponent(rdonam))
                        .addGap(44, 44, 44)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(datetime))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(datesua)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(19, 19, 19)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdodanglam)
                            .addComponent(rdonghilam))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnupdatenv.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnupdatenv.setText("Sửa Nhân Viên");
        btnupdatenv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdatenvActionPerformed(evt);
            }
        });

        btnadd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnadd.setText("Thêm Nhân Viên");
        btnadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddActionPerformed(evt);
            }
        });

        btndeletenv.setBackground(new java.awt.Color(255, 102, 102));
        btndeletenv.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btndeletenv.setText("Xóa Nhân Viên");
        btndeletenv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeletenvActionPerformed(evt);
            }
        });

        btndeletenv1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btndeletenv1.setText("Làm Mới");
        btndeletenv1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeletenv1ActionPerformed(evt);
            }
        });

        btnexxportexcel.setBackground(new java.awt.Color(51, 255, 51));
        btnexxportexcel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnexxportexcel.setText("Export");
        btnexxportexcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexxportexcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnadd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnupdatenv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btndeletenv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btndeletenv1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnexxportexcel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnupdatenv, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btndeletenv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btndeletenv1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnexxportexcel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        btntop.setText("|<");
        btntop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntopActionPerformed(evt);
            }
        });

        btnprevious.setText("<<");
        btnprevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpreviousActionPerformed(evt);
            }
        });

        btnext.setText(">>");
        btnext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnextActionPerformed(evt);
            }
        });

        btnend.setText(">|");
        btnend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnendActionPerformed(evt);
            }
        });

        lbltrang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbltrang.setText("0");
        lbltrang.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel12.setText("Trạng Thái :");

        cbbcheckstatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất Cả", "Đang Làm", "Nghỉ Làm" }));
        cbbcheckstatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbcheckstatusActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setText("Giới Tính");

        cbblocgioitinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất Cả", "Nam", "Nữ" }));
        cbblocgioitinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbblocgioitinhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txttimkiem)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbbcheckstatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btntop)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnprevious)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbltrang, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnext)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnend)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(7, 7, 7)
                                .addComponent(cbblocgioitinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(78, 78, 78))))
                    .addComponent(lb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)
                        .addComponent(cbbcheckstatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbblocgioitinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btntop)
                            .addComponent(btnprevious)
                            .addComponent(btnext)
                            .addComponent(btnend)
                            .addComponent(lbltrang))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(36, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblqlnhanvienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblqlnhanvienMouseClicked
        if (SwingUtilities.isLeftMouseButton(evt)) {
            // Left-click (BUTTON1) action
            index = tblqlnhanvien.getSelectedRow();
            showdata(index);
        } else if (SwingUtilities.isRightMouseButton(evt)) {
            // Right-click (BUTTON3) action
            index = tblqlnhanvien.getSelectedRow();
            showdata2(index);
        } else if (evt.getClickCount() == 2) {
            // updateTableData();
        }
    }//GEN-LAST:event_tblqlnhanvienMouseClicked

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        if (isDataEmpty()) {
            int confirmResult = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn thêm?", "Xác nhận thêm Nhân Viên", JOptionPane.YES_NO_OPTION);
            if (confirmResult == JOptionPane.YES_OPTION) {
                isThemMoi = true; // Đặt trạng thái là thêm mới
                NhanVienEntity nv = readForm();
                if (service.insertNV(nv) > 0) {
                    Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Thêm Thành Công");
                    // Lấy thời gian tạo và thời gian sửa từ đối tượng nv
                    Date thoiGianTao = nv.getThoiGianTao();
                    Date thoiGianSua = nv.getThoiGianSua();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    datetime.setText(dateFormat.format(thoiGianTao));
                    datesua.setText(dateFormat.format(thoiGianSua));
                    filltable(service.getAllNhanVien());
                    //filltable(); 
                    clearForm();
                } else {
                    Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Thêm Thất Bại");
                    return;
                }
            } else {
                isThemMoi = false; // Đặt trạng thái là cập nhật
            }
        }
    }//GEN-LAST:event_btnaddActionPerformed

    private void btnupdatenvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdatenvActionPerformed
        int confirmResult = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn Sửa?", "Xác nhận Sửa Nhân Viên", JOptionPane.YES_NO_OPTION);
        if (confirmResult == JOptionPane.YES_OPTION) {
            int index = tblqlnhanvien.getSelectedRow();
            int id = Integer.parseInt(tblqlnhanvien.getValueAt(index, 0).toString());
            NhanVienEntity nv = readForm();
            if (service.updateNhanVien(nv, id) > 0) {
                System.out.println("da click");
                // Lấy thời gian tạo và thời gian sửa từ đối tượng nv
                Date thoiGianTao = nv.getThoiGianTao();
//                Date thoiGianSua = nv.getThoiGianSua();
                // Định dạng ngày giờ
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                // Set giá trị vào label
//                datesua.setText(dateFormat.format(thoiGianSua));
                datesua.setText(dateFormat.format(thoiGianTao));
                listnv = service.getAllNhanVien();
                filltable(listnv);
                show();
                clearForm();
                Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Sửa Thành Công");
            } else {
                Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Sửa Thất Bại");
            }
        } else {
            // Không thực hiện thêm bản ghi
            // ... 
        }
    }//GEN-LAST:event_btnupdatenvActionPerformed

    private void btndeletenvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeletenvActionPerformed
        if (index < 0) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Bạn Chưa Chọn Nhân Viên Cần Xóa");
        } else {
            int confirmResult = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn Xóa?", "Xác nhận Xóa Nhân Viên", JOptionPane.YES_NO_OPTION);
            if (confirmResult == JOptionPane.YES_OPTION) {
                index = tblqlnhanvien.getSelectedRow();
                int id = Integer.parseInt(tblqlnhanvien.getValueAt(index, 0).toString());
                if (service.deleteNhanvien(id) > 0) {
                    Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Xóa Thành Công");
                    filltable(service.getAllNhanVien());
                    clearForm();
                } else {
                    Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Xóa Thất Bại");
                }
            } else {
            }
        }
    }//GEN-LAST:event_btndeletenvActionPerformed

    private void btndeletenv1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeletenv1ActionPerformed
        clearForm();
    }//GEN-LAST:event_btndeletenv1ActionPerformed

    private void btnsreachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsreachActionPerformed
    }//GEN-LAST:event_btnsreachActionPerformed

    private void txttimkiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimkiemKeyReleased
        String keyword = txttimkiem.getText().trim(); // Get the keyword from TextField
        if (keyword.isEmpty()) {
            // If the keyword is empty, display the entire list of employees
            listnv = service.getAllNhanVien();
        } else {
            // Otherwise, perform a search with the entered keyword
            listnv = service.findNhanVienByNameOrSoDienThoaiOrEmail(keyword);
        }
        // Display the list of employees found in the table
        DefaultTableModel model = (DefaultTableModel) tblqlnhanvien.getModel();
        model.setRowCount(0); // Clear the old data in the table
        int stt = 1;
        for (NhanVienEntity nv : listnv) {
            model.addRow(new Object[]{
                stt,
                nv.getUsername(),
                nv.getTen(),
                nv.getEmail(),
                nv.getSoDienThoai(),
                nv.getGioiTinh(),
                nv.getTrangThai(),
                nv.getNgaySinh(),
                nv.getDiaChi(),
                nv.getMaDinhDanh(),
                nv.getThoiGianTao(),
                nv.getThoiGianSua()
            });
            stt++;
        }
        listnv = service.getAllNhanVien();
    }//GEN-LAST:event_txttimkiemKeyReleased

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        loadNhanVienToTable(service.getAllNhanVien());
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void checkdanglamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkdanglamActionPerformed
    }//GEN-LAST:event_checkdanglamActionPerformed

    private void checknghilamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checknghilamActionPerformed
    }//GEN-LAST:event_checknghilamActionPerformed
    private String currentStatus = "Tất Cả";
    private String currentGender = "Tất Cả";
    private void btnexxportexcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexxportexcelActionPerformed
        //start new ...................................................................................
        try {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showSaveDialog(this);
            File saveFile = jFileChooser.getSelectedFile();

            if (saveFile != null) {
                saveFile = new File(saveFile.toString() + ".xlsx");
                Workbook wb = new XSSFWorkbook();
                Sheet sheet = wb.createSheet("customer");

                Row rowCol = sheet.createRow(0);
                for (int i = 0; i < tblqlnhanvien.getColumnCount(); i++) {
                    Cell cell = rowCol.createCell(i);
                    cell.setCellValue(tblqlnhanvien.getColumnName(i));
                }
                for (int j = 0; j < tblqlnhanvien.getRowCount(); j++) {
                    Row row = sheet.createRow(j + 1);
                    for (int k = 0; k < tblqlnhanvien.getColumnCount(); k++) {
                        Cell cell = row.createCell(k);
                        if (tblqlnhanvien.getValueAt(j, k) != null) {
                            cell.setCellValue(tblqlnhanvien.getValueAt(j, k).toString());
                        }
                    }
                }
                Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Exported Successfully..........!!!");
                FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
                wb.write(out);
                wb.close();
                out.close();
                openFile(saveFile.toString());
            } else {
                JOptionPane.showMessageDialog(null, "Error al generar archivo");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException io) {
            System.out.println(io);
        }
    }//GEN-LAST:event_btnexxportexcelActionPerformed

    private int currentPage = 1;
    private int rowsPerPage = 10;

    private void updateTableData() {
//        NhanVien nv = service.getAllNhanVien().get(index);
        listnv = service.getAllNhanVien();
        // Tính toán chỉ số bắt đầu và kết thúc cho trang hiện tại
        int startIndex = (currentPage - 1) * rowsPerPage;
        int endIndex = Math.min(startIndex + rowsPerPage, listnv.size());
        // Xóa dữ liệu cũ trong bảng
        DefaultTableModel model = (DefaultTableModel) tblqlnhanvien.getModel();
        model.setRowCount(0);
        int stt = 1;
        // Điền bảng với dữ liệu cho trang hiện tại
        for (int i = startIndex; i < endIndex; i++) {
            NhanVienEntity nv = listnv.get(i);
            model.addRow(new Object[]{
                stt,
                nv.getUsername(),
                nv.getTen(),
                nv.getEmail(),
                nv.getSoDienThoai(),
                nv.getGioiTinh(),
                nv.getTrangThai(),
                nv.getNgaySinh(),
                nv.getDiaChi(),
                nv.getMaDinhDanh(),
                nv.getThoiGianTao(),
                nv.getThoiGianSua()
            });
            stt++;
        }
        int totalPages = (int) Math.ceil((double) listnv.size() / rowsPerPage);
        lbltrang.setText(currentPage + "/" + totalPages);
    }

    private void btntopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntopActionPerformed
        currentPage = 1; // Di chuyển đến trang đầu tiên
        updateTableData();
    }//GEN-LAST:event_btntopActionPerformed
    private void btnendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnendActionPerformed
        listnv = service.getAllNhanVien();
        int totalRows = listnv.size();
        int totalPages = (int) Math.ceil((double) totalRows / rowsPerPage);
        currentPage = totalPages; // Di chuyển đến trang cuối cùng
        updateTableData();
    }//GEN-LAST:event_btnendActionPerformed

    private void btnpreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpreviousActionPerformed
        // TODO add your handling code here:
        currentPage = Math.max(currentPage - 1, 1); // Di chuyển đến trang trước
        updateTableData();
    }//GEN-LAST:event_btnpreviousActionPerformed

    private void btnextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnextActionPerformed
        // TODO add your handling code here:
        listnv = service.getAllNhanVien();
        int totalRows = listnv.size();
        int totalPages = (int) Math.ceil((double) totalRows / rowsPerPage);
        currentPage = Math.min(currentPage + 1, totalPages); // Di chuyển đến trang tiếp theo
        updateTableData();
    }//GEN-LAST:event_btnextActionPerformed

    private void btnnvdaxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnvdaxoaActionPerformed
        // Application.showForm(new FormQuanLyNVDaXoa());
    }//GEN-LAST:event_btnnvdaxoaActionPerformed

    private void cbbcheckstatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbcheckstatusActionPerformed
        // TODO add your handling code here:
        // Xử lý sự kiện khi giá trị của cbbcheckstatus thay đổi
        String selectedStatus = cbbcheckstatus.getSelectedItem().toString();
        String selectedGender = cbblocgioitinh.getSelectedItem().toString();
        if (!selectedStatus.equals("Tất Cả")) {
            // Nếu giá trị được chọn là khác "Tất Cả", thực hiện lọc dữ liệu
            if (!selectedGender.equals("Tất Cả")) {
                listnv = service.filterByStatusAndGender(selectedStatus, selectedGender);
            } else {
                listnv = service.filterByStatus(selectedStatus);
            }
            loadNhanVienToTable(listnv);
        } else {
            // Nếu giá trị được chọn là "Tất Cả", không thực hiện lọc
            listnv = service.getAllNhanVien();  // Hoặc load lại dữ liệu đầy đủ từ cơ sở dữ liệu
            loadNhanVienToTable(listnv);
        }
    }//GEN-LAST:event_cbbcheckstatusActionPerformed

    private void cbblocgioitinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbblocgioitinhActionPerformed
        // TODO add your handling code here:
        // Xử lý sự kiện khi giá trị của cbblocgioitinh thay đổi
        String selectedStatus = cbbcheckstatus.getSelectedItem().toString();
        String selectedGender = cbblocgioitinh.getSelectedItem().toString();
        if (!selectedGender.equals("Tất Cả")) {
            // Nếu giá trị được chọn là khác "Tất Cả", thực hiện lọc dữ liệu
            if (!selectedStatus.equals("Tất Cả")) {
                listnv = service.filterByStatusAndGender(selectedStatus, selectedGender);
            } else {
                listnv = service.filterByGender(selectedGender);
            }
            loadNhanVienToTable(listnv);
        } else {
            // Nếu giá trị được chọn là "Tất Cả", không thực hiện lọc
            listnv = service.getAllNhanVien();  // Hoặc load lại dữ liệu đầy đủ từ cơ sở dữ liệu
            loadNhanVienToTable(listnv);
        }
    }//GEN-LAST:event_cbblocgioitinhActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnadd;
    private javax.swing.JButton btndeletenv;
    private javax.swing.JButton btndeletenv1;
    private javax.swing.JButton btnend;
    private javax.swing.JButton btnext;
    private javax.swing.JButton btnexxportexcel;
    private javax.swing.JButton btnnvdaxoa;
    private javax.swing.JButton btnprevious;
    private javax.swing.JButton btnsreach;
    private javax.swing.JButton btntop;
    private javax.swing.JButton btnupdatenv;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox<String> cbbcheckstatus;
    private javax.swing.JComboBox<String> cbblocgioitinh;
    private javax.swing.JRadioButton checkdanglam;
    private javax.swing.JRadioButton checknghilam;
    private javax.swing.JLabel datesua;
    private javax.swing.JLabel datetime;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private com.toedter.calendar.JDateChooser jdcngaysinh;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lbltrang;
    private javax.swing.JRadioButton rdodanglam;
    private javax.swing.JRadioButton rdonam;
    private javax.swing.JRadioButton rdonghilam;
    private javax.swing.JRadioButton rdonu;
    private javax.swing.JTable tblqlnhanvien;
    private javax.swing.JTextArea txtdiachi;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtmaddanh;
    private javax.swing.JTextField txtsdt;
    private javax.swing.JTextField txttennv;
    private javax.swing.JTextField txttimkiem;
    private javax.swing.JTextField txtusername;
    // End of variables declaration//GEN-END:variables
}
