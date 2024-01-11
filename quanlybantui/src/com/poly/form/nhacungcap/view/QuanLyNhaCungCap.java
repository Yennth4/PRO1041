package com.poly.form.nhacungcap.view;

import com.formdev.flatlaf.FlatClientProperties;
import com.poly.form.nhacungcap.entity.NhaCungCap;
import com.poly.form.nhacungcap.entity.NhaCungCapDTO;
import com.poly.form.nhacungcap.service.NhaCungCapService;
import static com.poly.util.ph31848.MaRandom.renderMa;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import raven.toast.Notifications;
import static com.poly.util.ph31848.Validate.checkEmpty;
import static com.poly.util.ph31848.Validate.checkChar;
import static com.poly.util.ph31848.Validate.checkNumber;
import static com.poly.util.ph31848.Validate.checkFloat;
import static com.poly.util.ph31848.Validate.checkMaTheLoai;
import static com.poly.util.ph31848.Validate.formatDate;
import static com.poly.util.ph31848.Validate.isCorrectTime;
import java.awt.Desktop;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class QuanLyNhaCungCap extends javax.swing.JPanel {

    private NhaCungCapService service;
    private DefaultTableModel dtm;
    private List<NhaCungCapDTO> list = new ArrayList<>();

    // Biến xử lý phân trang
    private int currentPage = 0;
    private int length = 0;
    private int pageSize = 15;
    private int totalPage = 0;

    // Biến path Hợp đồng cung cấp
    String pathHDCC = "";

    public QuanLyNhaCungCap() {
        initComponents();
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        service = new NhaCungCapService();
        dtm = (DefaultTableModel) tblNCC.getModel();
        resertList();
        txtMa.setEnabled(false);
    }

    public void resertListSearch() {
        String keyword = txtSearch.getText().trim();
        //SP
        String soSanPham = txtCbxSearch.getText().trim();
        int cbxSanPhamSelect = cbxSearchSanPham.getSelectedIndex();
        //BTSP
        String bTSP = txtCbxSearchSanPhamChiTiet.getText().trim();
        int cbxBTSPSelect = cbxSearchSanPhamChiTiet.getSelectedIndex();
        //SLC
        String soLuongCai = txtCbxSearchSoLuongCai.getText().trim();
        int cbxSoLuongCai = cbxSearchSoLuongCai.getSelectedIndex();
        int cbxTrangThaiSelect = cbxSearchTrangThai.getSelectedIndex();
        //DATE
        int cbxThoiGianSearchType = cbxThoiGianSearch.getSelectedIndex();

        Date dateFirst = txtDateFirst.getDate();
        String timeFirst = txtTimeFirst.getText().trim();
        Date dateSecond = txtDateSecond.getDate();
        String timeSecond = txtTimeSecond.getText().trim();

        try {
            if (!checkNumber(soSanPham) && !checkEmpty(soSanPham)) {
                Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Số lượng sản phẩm phải là số nguyên dương");
                return;
            }
            if (!checkNumber(bTSP) && !checkEmpty(bTSP)) {
                Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Biến thể sản phẩm phải là số nguyên dương");
                return;
            }
            if (!checkNumber(soLuongCai) && !checkEmpty(soLuongCai)) {
                Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Số lượng cái phải là số nguyên dương");
                return;
            }
            String keywordSearch = (keyword.equals("")) ? null : keyword;
            Integer soSanPhamSearch = (soSanPham.equals("")) ? null : Integer.valueOf(soSanPham);
            Integer bTSPSearch = (bTSP.equals("")) ? null : Integer.valueOf(bTSP);
            Integer soLuongCaiSearch = (soLuongCai.equals("")) ? null : Integer.valueOf(soLuongCai);

            //check time
            if (timeFirst.equals("") || !isCorrectTime(timeFirst)) {
                timeFirst = null;
            }
            if (timeSecond.equals("") || !isCorrectTime(timeSecond)) {
                timeSecond = null;
            }
            // check Date 
            String dateFirstFormat = (dateFirst == null) ? null : formatDate(dateFirst);
            String dateSecondFormat = (dateFirst == null) ? null : formatDate(dateSecond);

            //
            list = service.search(keywordSearch, cbxSanPhamSelect, soSanPhamSearch, cbxBTSPSelect, bTSPSearch, cbxSoLuongCai, soLuongCaiSearch, cbxTrangThaiSelect, cbxThoiGianSearchType, dateFirstFormat, timeFirst, dateSecondFormat, timeSecond);
            tableCalculation(list);
            updateTable(list);
            setDisablePre();
            setDisableNext();
            if (totalPage > 1) {
                setEnableNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resertList() {
        list = service.getAll();
        tableCalculation(list);
        updateTable(list);
        setDisablePre();
        setDisableNext();
        if (totalPage > 1) {
            setEnableNext();
        }
    }

    public NhaCungCap getValue(String str) {
        String id = lbID.getText();
        String ma = "";
        do {
            ma = "NC" + renderMa();
        } while (service.isExistMa(ma));
        String ten = txtTen.getText();
        String sdt = txtSDT.getText();
        String email = txtEmailNCC.getText();
        String moTa = txtMoTa.getText();
        String trangThai = (String) cbxTrangThai.getSelectedItem();

        try {
            if (checkEmpty(ten) || checkEmpty(moTa) || checkEmpty(sdt) || checkEmpty(email)) {
                Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Vui lòng điền đủ thông tin");
                return null;
            }

            if (str.equals("insert")) {

                if (service.isExistTen(ten)) {
                    Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Tên đã tồn tại");
                    return null;
                }
            }
            if (str.equals("insert")) {
                NhaCungCap theLoai = new NhaCungCap(ma, ten, moTa, (trangThai.equals("Hoạt động")) ? 1 : 0, sdt, email, pathHDCC);
                return theLoai;
            } else if (str.equals("update")) {
                ma = txtMa.getText();
                NhaCungCap theLoai = new NhaCungCap(Long.valueOf(id), ma, ten, moTa, (trangThai.equals("Hoạt động")) ? 1 : 0, sdt, email, pathHDCC);
                return theLoai;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        }

    }

    public void clear() {
        lbID.setText("ID tự sinh");
        txtMa.setText("Mã tự sinh");
        txtMa.setText("");
        txtTen.setText("");
        txtMoTa.setText("");
        lbThoiGianSua.setText("");
        lbThoiGianTao.setText("");
    }

    public void tableCalculation(List<NhaCungCapDTO> list) {
        length = list.size();
        totalPage = length / pageSize;
        totalPage = (length % pageSize != 0) ? totalPage + 1 : totalPage;
        currentPage = 0;
        lbSum.setText(totalPage + "");
        lbTongTheLoai.setText(length + "");
    }

    public void updateTable(List<NhaCungCapDTO> list) {
        int startRow = pageSize * currentPage; // 0
        int endRow = Math.min(startRow + pageSize - 1, length - 1);
        showTable(list, startRow, endRow);
        lbClick.setText(currentPage + 1 + "");
    }

    public void showTable(List<NhaCungCapDTO> list, int start, int end) {
        dtm.setRowCount(0);
        for (int i = start; i <= end; i++) {
            NhaCungCapDTO tl = list.get(i);
            dtm.addRow(tl.toDataRow());
        }
    }

    public void setEnableNext() {
        btnNext.setEnabled(true);
        btnLast.setEnabled(true);
    }

    public void setEnablePre() {
        btnPre.setEnabled(true);
        btnBack.setEnabled(true);
    }

    public void setDisableNext() {
        btnNext.setEnabled(false);
        btnLast.setEnabled(false);
    }

    public void setDisablePre() {
        btnPre.setEnabled(false);
        btnBack.setEnabled(false);
    }

    public void fillData(int index) {
        NhaCungCapDTO ncc = list.get(index);
        lbID.setText(ncc.getId() + "");
        txtMa.setText(ncc.getMaNCC());
        txtTen.setText(ncc.getTenNCC());
        txtSDT.setText(ncc.getSdtNCC());
        txtEmailNCC.setText(ncc.getEmailNCC());
        txtMoTa.setText(ncc.getMoTaNCC());
        pathHDCC = ncc.getHopDongThoaThuan();
        int trangThai = ncc.getTrangThai();
        cbxTrangThai.setSelectedItem("Không hoạt động");
        if (trangThai == 1) {
            cbxTrangThai.setSelectedItem("Hoạt động");
        }
        lbThoiGianTao.setText(ncc.getThoiGianTao());
        lbThoiGianSua.setText(ncc.getThoiGianSua());

    }

    public void thayDoiPathHopDongCungCap() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File fileSelect = fileChooser.getSelectedFile();
            pathHDCC = fileSelect.getPath();
        }
    }

    //Mở file docx
    public void moHopDongCungCap(String docxFilePath) {
        try {

            // Kiểm tra xem Desktop API có thể được sử dụng không
            if (Desktop.isDesktopSupported()) {
                // Lấy Desktop instance
                Desktop desktop = Desktop.getDesktop();

                // Kiểm tra xem tệp có tồn tại không
                File file = new File(docxFilePath);
                if (file.exists()) {
                    // Mở tệp với ứng dụng mặc định
                    desktop.open(file);
                } else {
                    System.out.println("Tệp không tồn tại: " + docxFilePath);
                }
            } else {
                System.out.println("Desktop không được hỗ trợ trên hệ thống này.");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        cbxSearchSanPham = new javax.swing.JComboBox<>();
        txtCbxSearch = new javax.swing.JTextField();
        cbxSearchTrangThai = new javax.swing.JComboBox<>();
        cbxSearchSanPhamChiTiet = new javax.swing.JComboBox<>();
        txtCbxSearchSanPhamChiTiet = new javax.swing.JTextField();
        cbxSearchSoLuongCai = new javax.swing.JComboBox<>();
        txtCbxSearchSoLuongCai = new javax.swing.JTextField();
        cbxThoiGianSearch = new javax.swing.JComboBox<>();
        txtDateFirst = new com.toedter.calendar.JDateChooser();
        txtTimeFirst = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtDateSecond = new com.toedter.calendar.JDateChooser();
        txtTimeSecond = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNCC = new javax.swing.JTable();
        btnPre = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        lbTongTheLoai = new javax.swing.JLabel();
        lbClick = new javax.swing.JLabel();
        lbClick1 = new javax.swing.JLabel();
        lbSum = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbID = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        cbxTrangThai = new javax.swing.JComboBox<>();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lbThoiGianTao = new javax.swing.JLabel();
        lbThoiGianSua = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtEmailNCC = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        btnXemHDCC = new javax.swing.JButton();
        btnThayDoiHDCC = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();

        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("Nhà Cung Cấp");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Tên:");

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        cbxSearchSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxSearchSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tổng SP >=", "Tổng SP <=" }));
        cbxSearchSanPham.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxSearchSanPhamItemStateChanged(evt);
            }
        });

        txtCbxSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCbxSearchKeyReleased(evt);
            }
        });

        cbxSearchTrangThai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxSearchTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hoạt động", "Không hoạt động" }));
        cbxSearchTrangThai.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxSearchTrangThaiItemStateChanged(evt);
            }
        });

        cbxSearchSanPhamChiTiet.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxSearchSanPhamChiTiet.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tổng biến thể SP >=", "Tổng biến thể SP <=" }));
        cbxSearchSanPhamChiTiet.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxSearchSanPhamChiTietItemStateChanged(evt);
            }
        });

        txtCbxSearchSanPhamChiTiet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCbxSearchSanPhamChiTietKeyReleased(evt);
            }
        });

        cbxSearchSoLuongCai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxSearchSoLuongCai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tổng SL cái >=", "Tổng SL cái <=" }));
        cbxSearchSoLuongCai.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxSearchSoLuongCaiItemStateChanged(evt);
            }
        });

        txtCbxSearchSoLuongCai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCbxSearchSoLuongCaiKeyReleased(evt);
            }
        });

        cbxThoiGianSearch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxThoiGianSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tạo từ ngày", "Sửa từ ngày" }));
        cbxThoiGianSearch.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxThoiGianSearchItemStateChanged(evt);
            }
        });

        txtDateFirst.setDateFormatString("yyyy-MM-dd");
        txtDateFirst.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtDateFirstPropertyChange(evt);
            }
        });

        txtTimeFirst.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimeFirstKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Đến");

        txtDateSecond.setDateFormatString("yyyy-MM-dd");
        txtDateSecond.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtDateSecondPropertyChange(evt);
            }
        });

        txtTimeSecond.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimeSecondKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cbxThoiGianSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(cbxSearchSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtCbxSearch))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtDateFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimeFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addComponent(jLabel2)))
                .addGap(34, 34, 34)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtDateSecond, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimeSecond, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(cbxSearchSanPhamChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCbxSearchSanPhamChiTiet)))
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(cbxSearchSoLuongCai, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCbxSearchSoLuongCai, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cbxSearchTrangThai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxSearchSanPhamChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCbxSearchSanPhamChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxSearchSoLuongCai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCbxSearchSoLuongCai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxSearchSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCbxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxThoiGianSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTimeFirst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addComponent(txtDateFirst, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtTimeSecond, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDateSecond, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxSearchTrangThai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tblNCC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblNCC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã NCC", "Tên NCC", "SDT", "Email", "SP", "BTSP", "SL"
            }
        ));
        tblNCC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNCCMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNCC);

        btnPre.setText("|<");
        btnPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreActionPerformed(evt);
            }
        });

        btnBack.setText("<");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnLast.setText(">|");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        btnNext.setText(">");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setText("Tổng nhà cung cấp: ");

        lbTongTheLoai.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbTongTheLoai.setForeground(new java.awt.Color(255, 153, 0));
        lbTongTheLoai.setText("...");

        lbClick.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbClick.setText("...");

        lbClick1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbClick1.setText("of");

        lbSum.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbSum.setText("...");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnPre, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbClick)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbClick1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbSum)
                        .addGap(18, 18, 18)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(lbTongTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(lbTongTheLoai))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnNext)
                        .addComponent(btnLast)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbClick)
                            .addComponent(lbClick1)
                            .addComponent(lbSum)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnPre)
                        .addComponent(btnBack)))
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setText("ID: ");

        lbID.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbID.setText("...");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText("SDT");

        txtSDT.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("Tên NCC");

        txtTen.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setText("Mô tả:");

        txtMoTa.setColumns(20);
        txtMoTa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMoTa.setRows(5);
        jScrollPane2.setViewportView(txtMoTa);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setText("Trạng thái:");

        cbxTrangThai.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbxTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hoạt động", "Không hoạt động" }));

        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel9.setText("Tạo vào lúc:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setText("Sửa lần cuối:");

        lbThoiGianTao.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbThoiGianTao.setText("...");

        lbThoiGianSua.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbThoiGianSua.setText("...");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setText("Email");

        txtEmailNCC.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setText("Hợp đồng:");

        btnXemHDCC.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnXemHDCC.setText("Xem");
        btnXemHDCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemHDCCActionPerformed(evt);
            }
        });

        btnThayDoiHDCC.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnThayDoiHDCC.setText("Chọn/Thay đổi");
        btnThayDoiHDCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThayDoiHDCCActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel13.setText("Mã:");

        txtMa.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(cbxTrangThai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(lbThoiGianTao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(14, 14, 14)
                        .addComponent(lbThoiGianSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7)
                            .addComponent(jLabel12)
                            .addComponent(jLabel5)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(lbID, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSDT)
                                    .addComponent(txtTen)
                                    .addComponent(txtEmailNCC)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(btnXemHDCC, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnThayDoiHDCC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtMa)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbID)
                    .addComponent(jLabel13)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtEmailNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(btnXemHDCC)
                    .addComponent(btnThayDoiHDCC))
                .addGap(27, 27, 27)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lbThoiGianTao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lbThoiGianSua))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbxTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lb)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbxSearchTrangThaiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxSearchTrangThaiItemStateChanged
        resertListSearch();
    }//GEN-LAST:event_cbxSearchTrangThaiItemStateChanged

    private void tblNCCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNCCMouseClicked
        int index = tblNCC.getSelectedRow() + currentPage * pageSize;
        fillData(index);
    }//GEN-LAST:event_tblNCCMouseClicked

    private void btnPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreActionPerformed
        currentPage = 0;
        setDisablePre();
        updateTable(list);
        setEnableNext();
        clear();
    }//GEN-LAST:event_btnPreActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        currentPage--;
        updateTable(list);
        if (currentPage == 0) {
            setDisablePre();
        }

        setEnableNext();
        clear();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        currentPage = totalPage - 1;
        setDisableNext();
        updateTable(list);
        setEnablePre();
        clear();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        currentPage++;
        updateTable(list);
        if (currentPage == totalPage - 1) {
            setDisableNext();
        }
        setEnablePre();
        clear();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        NhaCungCap ncc = getValue("insert");
        if (ncc != null) {
            try {
                if (JOptionPane.showConfirmDialog(this, "Xác nhận thêm\nMã tự sinh: " + ncc.getMaNCC(), "Xác nhận thêm", JOptionPane.YES_NO_OPTION) == 0) {
                    service.insertNCC(ncc);
                    resertList();
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Thêm thành công");
                }

            } catch (Exception e) {
                e.printStackTrace();
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Vui lòng thử lại");
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        if (tblNCC.getSelectedRow() != -1) {
            NhaCungCap ncc = getValue("update");
            try {
                if (ncc != null) {
                    if (JOptionPane.showConfirmDialog(this, "Xác nhận sửa\nMã: " + ncc.getMaNCC(), "Xác nhận sửa", JOptionPane.YES_NO_OPTION) == 0) {
                        service.updateNCC(ncc);
                        resertList();
                        Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Sửa thành công");
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Vui lòng thử lại");
            }
        } else {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Chọn hàng để sửa");
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int index = tblNCC.getSelectedRow();
        if (index != -1) {

            try {
                Long id = Long.valueOf(lbID.getText());
                int tongSP = Integer.valueOf(tblNCC.getValueAt(index, 4) + "");
                int tongBTSP = Integer.valueOf(tblNCC.getValueAt(index, 5) + "");
                int tongSLC = Integer.valueOf(tblNCC.getValueAt(index, 6) + "");
                if (tongBTSP == 0 && tongSP == 0 && tongSLC == 0) {
                    if (JOptionPane.showConfirmDialog(this, "Xác nhận xóa", "Xác nhận", JOptionPane.YES_NO_OPTION) == 0) {
                        service.deleteNCC(id);
                        resertList();
                        Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Đã xóa thể loại");
                    }

                } else {
                    if (JOptionPane.showConfirmDialog(this, "NCC đã có sản phẩm\nHỗ trợ tắt trạng thái", "Xác nhận", JOptionPane.YES_NO_OPTION) == 0) {
                        service.tatTrangThai(id);
                        resertList();
                        Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "NCC này đã được tắt hoạt động");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Chọn hàng để xóa");
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        resertListSearch();
    }//GEN-LAST:event_txtSearchKeyReleased

    private void txtCbxSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCbxSearchKeyReleased
        resertListSearch();
    }//GEN-LAST:event_txtCbxSearchKeyReleased

    private void txtCbxSearchSanPhamChiTietKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCbxSearchSanPhamChiTietKeyReleased
        resertListSearch();
    }//GEN-LAST:event_txtCbxSearchSanPhamChiTietKeyReleased

    private void btnXemHDCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemHDCCActionPerformed
        int index = tblNCC.getSelectedRow();
        if (index != -1) {
            moHopDongCungCap(pathHDCC);
        } else
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Chọn 1 NCC để xem");
    }//GEN-LAST:event_btnXemHDCCActionPerformed

    private void btnThayDoiHDCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThayDoiHDCCActionPerformed
        int index = tblNCC.getSelectedRow();
        if (index != -1) {
            thayDoiPathHopDongCungCap();
            System.out.println(pathHDCC); // print
        } else
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Chọn 1 NCC để thay đổi HĐCC");
    }//GEN-LAST:event_btnThayDoiHDCCActionPerformed

    private void cbxSearchSanPhamItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxSearchSanPhamItemStateChanged
        resertListSearch();
    }//GEN-LAST:event_cbxSearchSanPhamItemStateChanged

    private void cbxSearchSanPhamChiTietItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxSearchSanPhamChiTietItemStateChanged
        resertListSearch();
    }//GEN-LAST:event_cbxSearchSanPhamChiTietItemStateChanged

    private void cbxSearchSoLuongCaiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxSearchSoLuongCaiItemStateChanged
        resertListSearch();
    }//GEN-LAST:event_cbxSearchSoLuongCaiItemStateChanged

    private void txtCbxSearchSoLuongCaiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCbxSearchSoLuongCaiKeyReleased
        resertListSearch();
    }//GEN-LAST:event_txtCbxSearchSoLuongCaiKeyReleased

    private void cbxThoiGianSearchItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxThoiGianSearchItemStateChanged
        resertListSearch();
    }//GEN-LAST:event_cbxThoiGianSearchItemStateChanged

    private void txtDateFirstPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtDateFirstPropertyChange
        if (txtDateFirst.getDate() != null) {
            resertListSearch();
        }
    }//GEN-LAST:event_txtDateFirstPropertyChange

    private void txtTimeFirstKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimeFirstKeyReleased
        resertListSearch();
    }//GEN-LAST:event_txtTimeFirstKeyReleased

    private void txtDateSecondPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtDateSecondPropertyChange
        if (txtDateSecond.getDate() != null) {
            resertListSearch();
        }
    }//GEN-LAST:event_txtDateSecondPropertyChange

    private void txtTimeSecondKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimeSecondKeyReleased
        resertListSearch();
    }//GEN-LAST:event_txtTimeSecondKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPre;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThayDoiHDCC;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXemHDCC;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbxSearchSanPham;
    private javax.swing.JComboBox<String> cbxSearchSanPhamChiTiet;
    private javax.swing.JComboBox<String> cbxSearchSoLuongCai;
    private javax.swing.JComboBox<String> cbxSearchTrangThai;
    private javax.swing.JComboBox<String> cbxThoiGianSearch;
    private javax.swing.JComboBox<String> cbxTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lbClick;
    private javax.swing.JLabel lbClick1;
    private javax.swing.JLabel lbID;
    private javax.swing.JLabel lbSum;
    private javax.swing.JLabel lbThoiGianSua;
    private javax.swing.JLabel lbThoiGianTao;
    private javax.swing.JLabel lbTongTheLoai;
    private javax.swing.JTable tblNCC;
    private javax.swing.JTextField txtCbxSearch;
    private javax.swing.JTextField txtCbxSearchSanPhamChiTiet;
    private javax.swing.JTextField txtCbxSearchSoLuongCai;
    private com.toedter.calendar.JDateChooser txtDateFirst;
    private com.toedter.calendar.JDateChooser txtDateSecond;
    private javax.swing.JTextField txtEmailNCC;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTimeFirst;
    private javax.swing.JTextField txtTimeSecond;
    // End of variables declaration//GEN-END:variables
}
