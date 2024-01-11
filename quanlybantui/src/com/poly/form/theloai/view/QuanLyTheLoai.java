package com.poly.form.theloai.view;

import com.formdev.flatlaf.FlatClientProperties;
import com.poly.form.theloai.entity.TheLoai;
import com.poly.form.theloai.entity.TheLoaiDTO;
import com.poly.form.theloai.service.TheLoaiService;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import raven.toast.Notifications;
import static com.poly.util.ph31848.Validate.checkEmpty;
import static com.poly.util.ph31848.Validate.checkChar;
import static com.poly.util.ph31848.Validate.checkNumber;
import static com.poly.util.ph31848.Validate.checkFloat;
import static com.poly.util.ph31848.Validate.checkMaTheLoai;
import static com.poly.util.ph31848.Validate.isCorrectTime;
import static com.poly.util.ph31848.Validate.formatDate;

import static com.poly.util.ph31848.MaRandom.renderMa;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Nguyễn Vĩnh Long PH31848
 */
public class QuanLyTheLoai extends javax.swing.JPanel {

    private TheLoaiService service;
    private DefaultTableModel dtm;
    private List<TheLoaiDTO> list = new ArrayList<>();

    // Biến xử lý phân trang
    private int currentPage = 0;
    private int length = 0;
    private int pageSize = 15;
    private int totalPage = 0;

    public QuanLyTheLoai() {
        initComponents();
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        service = new TheLoaiService();
        dtm = (DefaultTableModel) tblTheLoai.getModel();
        addListenDocument();
        resertList();
        txtMa.setEnabled(false);
    }

    public void addListenDocument() {
        DocumentListener documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                resertListSearch();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                resertListSearch();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        };
        txtSearch.getDocument().addDocumentListener(documentListener);
        txtCbxSearch.getDocument().addDocumentListener(documentListener);

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

    public TheLoai getValue(String str) {
        String id = lbID.getText();
        String ma = "";
        do {
            ma = "TL" + renderMa();
        } while (service.isExistMa(ma));
        String ten = txtTen.getText();
        String moTa = txtMoTa.getText();
        String trangThai = (String) cbxTrangThai.getSelectedItem();

        try {
            if (checkEmpty(ten) || checkEmpty(moTa)) {
                Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Vui lòng điền đủ thông tin");
                return null;
            }

            if (!checkChar(ten)) {
                Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Tên sai định dạng");
                return null;
            }

            if (str.equals("insert")) {
                TheLoai theLoai = new TheLoai(ma, ten, moTa, (trangThai.equals("Hoạt động")) ? 1 : 0);
                return theLoai;
            } else if (str.equals("update")) {
                ma = txtMa.getText();
                TheLoai theLoai = new TheLoai(Long.valueOf(id), ma, ten, moTa, (trangThai.equals("Hoạt động")) ? 1 : 0);
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
        txtMa.setText("");
        txtTen.setText("");
        txtMoTa.setText("");
        lbThoiGianSua.setText("");
        lbThoiGianTao.setText("");
    }

    public void tableCalculation(List<TheLoaiDTO> list) {
        length = list.size();
        totalPage = length / pageSize;
        totalPage = (length % pageSize != 0) ? totalPage + 1 : totalPage;
        currentPage = 0;
        lbSum.setText(totalPage + "");
        lbTongTheLoai.setText(length + "");
    }

    public void updateTable(List<TheLoaiDTO> list) {
        int startRow = pageSize * currentPage; // 0
        int endRow = Math.min(startRow + pageSize - 1, length - 1);
        showTable(list, startRow, endRow);
        lbClick.setText(currentPage + 1 + "");
    }

    public void showTable(List<TheLoaiDTO> list, int start, int end) {
        dtm.setRowCount(0);
        for (int i = start; i <= end; i++) {
            TheLoaiDTO tl = list.get(i);
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
        TheLoaiDTO theLoai = list.get(index);
        lbID.setText(theLoai.getIdTheLoai() + "");
        txtMa.setText(theLoai.getMaTheLoai());
        txtTen.setText(theLoai.getTenTheLoai());
        txtMoTa.setText(theLoai.getMoTaTheLoai());
        int trangThai = theLoai.getTrangThai();
        cbxTrangThai.setSelectedItem("Không hoạt động");
        if (trangThai == 1) {
            cbxTrangThai.setSelectedItem("Hoạt động");
        }
        lbThoiGianTao.setText(theLoai.getThoiGianTao());
        lbThoiGianSua.setText(theLoai.getThoiGianSua());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTheLoai = new javax.swing.JTable();
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
        txtMa = new javax.swing.JTextField();
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
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        cbxSearchSanPham = new javax.swing.JComboBox<>();
        txtCbxSearch = new javax.swing.JTextField();
        cbxSearchTrangThai = new javax.swing.JComboBox<>();
        cbxThoiGianSearch = new javax.swing.JComboBox<>();
        cbxSearchSanPhamChiTiet = new javax.swing.JComboBox<>();
        txtCbxSearchSanPhamChiTiet = new javax.swing.JTextField();
        cbxSearchSoLuongCai = new javax.swing.JComboBox<>();
        txtCbxSearchSoLuongCai = new javax.swing.JTextField();
        txtDateFirst = new com.toedter.calendar.JDateChooser();
        txtTimeFirst = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtDateSecond = new com.toedter.calendar.JDateChooser();
        txtTimeSecond = new javax.swing.JTextField();

        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("Quản lý thể loại");

        tblTheLoai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblTheLoai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã TL", "Tên TL", "SP", "BTSP", "SL"
            }
        ));
        tblTheLoai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTheLoaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTheLoai);

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
        jLabel8.setText("Tổng số thể loại: ");

        lbTongTheLoai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbClick)
                        .addGap(18, 18, 18)
                        .addComponent(lbClick1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbSum)
                        .addGap(12, 12, 12)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbTongTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(114, 114, 114))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPre)
                    .addComponent(btnBack)
                    .addComponent(btnNext)
                    .addComponent(btnLast)
                    .addComponent(lbClick)
                    .addComponent(lbClick1)
                    .addComponent(lbSum)
                    .addComponent(jLabel8)
                    .addComponent(lbTongTheLoai))
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setText("ID: ");

        lbID.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbID.setText("...");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText("Mã:");

        txtMa.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("Tên TL:");

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

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setForeground(new java.awt.Color(255, 51, 51));
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMa)
                            .addComponent(txtTen)
                            .addComponent(lbID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(cbxTrangThai, 0, 1, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(lbThoiGianTao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(14, 14, 14)
                        .addComponent(lbThoiGianSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lbThoiGianTao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lbThoiGianSua))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbxTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnThem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                            .addComponent(btnSua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Tên:");

        txtSearch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        cbxSearchSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxSearchSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Có sản phẩm >=", "Có sản phẩm <=" }));
        cbxSearchSanPham.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxSearchSanPhamItemStateChanged(evt);
            }
        });

        txtCbxSearch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        cbxSearchTrangThai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxSearchTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hoạt động", "Không hoạt động" }));
        cbxSearchTrangThai.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxSearchTrangThaiItemStateChanged(evt);
            }
        });
        cbxSearchTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxSearchTrangThaiActionPerformed(evt);
            }
        });

        cbxThoiGianSearch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxThoiGianSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tạo từ ngày", "Sửa từ ngày" }));
        cbxThoiGianSearch.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxThoiGianSearchItemStateChanged(evt);
            }
        });

        cbxSearchSanPhamChiTiet.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxSearchSanPhamChiTiet.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tổng biến thể SP >=", "Tổng biến thể SP <=" }));
        cbxSearchSanPhamChiTiet.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxSearchSanPhamChiTietItemStateChanged(evt);
            }
        });

        txtCbxSearchSanPhamChiTiet.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
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

        txtCbxSearchSoLuongCai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCbxSearchSoLuongCai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCbxSearchSoLuongCaiKeyReleased(evt);
            }
        });

        txtDateFirst.setDateFormatString("yyyy-MM-dd");
        txtDateFirst.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtDateFirstPropertyChange(evt);
            }
        });

        txtTimeFirst.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTimeFirst.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimeFirstKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("đến");

        txtDateSecond.setDateFormatString("yyyy-MM-dd");
        txtDateSecond.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtDateSecondPropertyChange(evt);
            }
        });

        txtTimeSecond.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
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
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxSearchSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCbxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxSearchSanPhamChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCbxSearchSanPhamChiTiet)
                        .addGap(18, 18, 18)
                        .addComponent(cbxSearchSoLuongCai, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtCbxSearchSoLuongCai, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(cbxThoiGianSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDateFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimeFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDateSecond, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimeSecond, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxSearchTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbxSearchSanPhamChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCbxSearchSanPhamChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbxSearchSoLuongCai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCbxSearchSoLuongCai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbxSearchSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCbxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtDateFirst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbxThoiGianSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTimeFirst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTimeSecond, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(txtDateSecond, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cbxSearchTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, 1081, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblTheLoaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTheLoaiMouseClicked
        int index = tblTheLoai.getSelectedRow() + currentPage * pageSize;
        fillData(index);
    }//GEN-LAST:event_tblTheLoaiMouseClicked

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

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        currentPage++;
        updateTable(list);
        if (currentPage == totalPage - 1) {
            setDisableNext();
        }
        setEnablePre();
        clear();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        currentPage = totalPage - 1;
        setDisableNext();
        updateTable(list);
        setEnablePre();
        clear();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        TheLoai theLoai = getValue("insert");
        if (theLoai != null) {
            try {
                if (JOptionPane.showConfirmDialog(this, "Xác nhận thêm\nMã tự sinh: " + theLoai.getMaTheLoai(), "Xác nhận thêm", JOptionPane.YES_NO_OPTION) == 0) {
                    service.insertTheLoai(theLoai);
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
        if (tblTheLoai.getSelectedRow() != -1) {
            TheLoai theLoai = getValue("update");
            if (theLoai != null) {
                if (JOptionPane.showConfirmDialog(this, "Xác nhận sửa\nMã: " + theLoai.getMaTheLoai(), "Xác nhận sửa", JOptionPane.YES_NO_OPTION) == 0) {
                    service.updateTheLoai(theLoai);
                    resertList();
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Sửa thành công");
                }

            }
        } else {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Chọn hàng để sửa");
        }

    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int index = tblTheLoai.getSelectedRow();
        if (index != -1) {

            try {
                Long id = Long.valueOf(lbID.getText());
                if (Integer.valueOf(tblTheLoai.getValueAt(index, 2) + "") != 0
                        || Integer.valueOf(tblTheLoai.getValueAt(index, 3) + "") != 0
                        || Integer.valueOf(tblTheLoai.getValueAt(index, 4) + "") != 0) {

                    if (JOptionPane.showConfirmDialog(this, "Thể loại đã có sản phẩm\nHỗ trợ tắt trạng thái", "Xác nhận", JOptionPane.YES_NO_OPTION) == 0) {
                        service.tatTrangThai(id);
                        resertList();
                        Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Thể loại đã tắt hoạt động");
                    }

                } else {
                    if (JOptionPane.showConfirmDialog(this, "Xác nhận xóa", "Xác nhận", JOptionPane.YES_NO_OPTION) == 0) {
                        service.deleteTheLoai(id);
                        resertList();
                        Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Đã xóa thể loại");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Chọn hàng để xóa");
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void cbxSearchTrangThaiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxSearchTrangThaiItemStateChanged
        resertListSearch();
    }//GEN-LAST:event_cbxSearchTrangThaiItemStateChanged

    private void cbxSearchSanPhamChiTietItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxSearchSanPhamChiTietItemStateChanged
        resertListSearch();
    }//GEN-LAST:event_cbxSearchSanPhamChiTietItemStateChanged

    private void txtCbxSearchSanPhamChiTietKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCbxSearchSanPhamChiTietKeyReleased
        resertListSearch();
    }//GEN-LAST:event_txtCbxSearchSanPhamChiTietKeyReleased

    private void cbxSearchSoLuongCaiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxSearchSoLuongCaiItemStateChanged
        resertListSearch();
    }//GEN-LAST:event_cbxSearchSoLuongCaiItemStateChanged

    private void txtCbxSearchSoLuongCaiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCbxSearchSoLuongCaiKeyReleased
        resertListSearch();
    }//GEN-LAST:event_txtCbxSearchSoLuongCaiKeyReleased

    private void cbxSearchSanPhamItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxSearchSanPhamItemStateChanged
        resertListSearch();
    }//GEN-LAST:event_cbxSearchSanPhamItemStateChanged

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

    private void cbxSearchTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxSearchTrangThaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxSearchTrangThaiActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPre;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
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
    private javax.swing.JTable tblTheLoai;
    private javax.swing.JTextField txtCbxSearch;
    private javax.swing.JTextField txtCbxSearchSanPhamChiTiet;
    private javax.swing.JTextField txtCbxSearchSoLuongCai;
    private com.toedter.calendar.JDateChooser txtDateFirst;
    private com.toedter.calendar.JDateChooser txtDateSecond;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTimeFirst;
    private javax.swing.JTextField txtTimeSecond;
    // End of variables declaration//GEN-END:variables
}
