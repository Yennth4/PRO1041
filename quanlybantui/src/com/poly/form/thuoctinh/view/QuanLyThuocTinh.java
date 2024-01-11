package com.poly.form.thuoctinh.view;

import com.formdev.flatlaf.FlatClientProperties;
import com.poly.form.thuoctinh.entity.ThuocTinhMau;
import com.poly.form.thuoctinh.entity.ThuocTinhMauDTO;
import com.poly.form.thuoctinh.service.ThuocTinhMauService;
import static com.poly.util.ph31848.MaRandom.renderMa;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import raven.toast.Notifications;
import static com.poly.util.ph31848.Validate.checkEmpty;
import static com.poly.util.ph31848.Validate.checkChar;
import static com.poly.util.ph31848.Validate.checkNumber;
import static com.poly.util.ph31848.Validate.checkFloat;
import static com.poly.util.ph31848.Validate.formatDate;
import static com.poly.util.ph31848.Validate.isCorrectTime;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Raven
 */
public class QuanLyThuocTinh extends javax.swing.JPanel {

    private ThuocTinhMauService service;
    private DefaultTableModel dtm;
    private List<ThuocTinhMauDTO> list = new ArrayList<>();

    // Biến xử lý phân trang
    private int currentPage = 0;
    private int length = 0;
    private int pageSize = 3;
    private int totalPage = 0;

    public QuanLyThuocTinh() {
        initComponents();
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        service = new ThuocTinhMauService();
        dtm = (DefaultTableModel) tblMau.getModel();
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

    public void clear() {
        lbID.setText("ID tự sinh");
        txtMa.setText("Mã tự sinh");
        txtTen.setText("");
        txtMoTa.setText("");
        lbThoiGianSua.setText("");
        lbThoiGianTao.setText("");
    }

    public void tableCalculation(List<ThuocTinhMauDTO> list) {
        length = list.size();
        totalPage = length / pageSize;
        totalPage = (length % pageSize != 0) ? totalPage + 1 : totalPage;
        currentPage = 0;
        lbSum.setText(totalPage + "");
        lbTongTheLoai.setText(length + "");
    }

    public void updateTable(List<ThuocTinhMauDTO> list) {
        int startRow = pageSize * currentPage;
        int endRow = Math.min(startRow + pageSize - 1, length - 1);
        showTable(list, startRow, endRow);
        lbClick.setText(currentPage + 1 + "");
    }

    public void showTable(List<ThuocTinhMauDTO> list, int start, int end) {
        dtm.setRowCount(0);
        for (int i = start; i <= end; i++) {
            ThuocTinhMauDTO tl = list.get(i);
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
        ThuocTinhMauDTO mau = list.get(index);
        lbID.setText(mau.getIDMau() + "");
        txtMa.setText(mau.getMaMau());
        txtTen.setText(mau.getTenMau());
        txtMoTa.setText(mau.getMoTaMau());
        int trangThai = mau.getTrangThai();
        cbxTrangThai.setSelectedItem("Không hoạt động");
        if (trangThai == 1) {
            cbxTrangThai.setSelectedItem("Hoạt động");
        }
        lbThoiGianTao.setText(mau.getThoiGianTao());
        lbThoiGianSua.setText(mau.getThoiGianSua());
    }

    public ThuocTinhMau getValue(String str) {
        String id = lbID.getText();
        String ma = "";
        do {
            ma = "MA" + renderMa();
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

                if (service.isExistTen(ten)) {
                    Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Tên đã tồn tại");
                    return null;
                }
            }
            if (str.equals("insert")) {
                ThuocTinhMau mau = new ThuocTinhMau(ma, ten, moTa, (trangThai.equals("Hoạt động")) ? 1 : 0);
                return mau;
            } else if (str.equals("update")) {
                ma = txtMa.getText();
                ThuocTinhMau mau = new ThuocTinhMau(Long.valueOf(id), ma, ten, moTa, (trangThai.equals("Hoạt động")) ? 1 : 0);
                return mau;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
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
        cbxThoiGianSearch = new javax.swing.JComboBox<>();
        txtDateFirst = new com.toedter.calendar.JDateChooser();
        txtTimeFirst = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtDateSecond = new com.toedter.calendar.JDateChooser();
        txtTimeSecond = new javax.swing.JTextField();
        cbxSearchSanPhamChiTiet = new javax.swing.JComboBox<>();
        txtCbxSearchSanPhamChiTiet = new javax.swing.JTextField();
        cbxSearchSoLuongCai = new javax.swing.JComboBox<>();
        txtCbxSearchSoLuongCai = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMau = new javax.swing.JTable();
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
        txtMa = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("Quản lý thuộc tính màu");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Tên:");

        txtSearch.setBackground(new java.awt.Color(255, 255, 204));
        txtSearch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        cbxSearchSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxSearchSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Có sản phẩm >=", "Có sản phẩm <=" }));
        cbxSearchSanPham.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxSearchSanPhamItemStateChanged(evt);
            }
        });

        txtCbxSearch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
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
        txtTimeSecond.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimeSecondActionPerformed(evt);
            }
        });
        txtTimeSecond.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimeSecondKeyReleased(evt);
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cbxThoiGianSearch, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbxSearchSanPham, 0, 1, Short.MAX_VALUE)
                    .addComponent(txtDateFirst, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCbxSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                    .addComponent(txtTimeFirst))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDateSecond, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cbxSearchSanPhamChiTiet, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTimeSecond, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                    .addComponent(txtCbxSearchSanPhamChiTiet))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(cbxSearchSoLuongCai, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtCbxSearchSoLuongCai, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cbxSearchTrangThai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbxSearchSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCbxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbxSearchSanPhamChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCbxSearchSanPhamChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbxSearchSoLuongCai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCbxSearchSoLuongCai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtDateFirst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbxThoiGianSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTimeFirst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2))
                            .addComponent(txtDateSecond, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTimeSecond, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbxSearchTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7))
        );

        tblMau.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tblMau.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Tên màu", "Mô tả màu", "SP", "BTSP", "SL"
            }
        ));
        tblMau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMauMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMau);

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
        jLabel8.setText("Tổng số màu: ");

        lbTongTheLoai.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbClick)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbClick1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbSum)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 176, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbTongTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(155, 155, 155))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnNext)
                        .addComponent(btnLast)
                        .addComponent(lbClick)
                        .addComponent(lbClick1)
                        .addComponent(lbSum)
                        .addComponent(jLabel8)
                        .addComponent(lbTongTheLoai))
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
        jLabel3.setText("Tên màu:");

        txtTen.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setText("Mô tả màu:");

        txtMoTa.setColumns(20);
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

        lbThoiGianTao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbThoiGianTao.setText("...");

        lbThoiGianSua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbThoiGianSua.setText("...");

        txtMa.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("Mã:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(53, 53, 53)
                                .addComponent(lbID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(cbxTrangThai, 0, 1, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                .addComponent(lbThoiGianSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTen)
                            .addComponent(txtMa))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(14, 14, 14)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addGap(24, 24, 24)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lbThoiGianTao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lbThoiGianSua))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbxTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, 1077, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(24, 24, 24)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(34, 34, 34)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(453, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbxSearchTrangThaiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxSearchTrangThaiItemStateChanged
        resertListSearch();
    }//GEN-LAST:event_cbxSearchTrangThaiItemStateChanged

    private void tblMauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMauMouseClicked
        int index = tblMau.getSelectedRow() + currentPage * pageSize;
        fillData(index);
    }//GEN-LAST:event_tblMauMouseClicked

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
        ThuocTinhMau mau = getValue("insert");
        if (mau != null) {
            try {
                if (JOptionPane.showConfirmDialog(this, "Xác nhận thêm\nMã tự sinh: " + mau.getMaMau(), "Xác nhận thêm", JOptionPane.YES_NO_OPTION) == 0) {
                    service.insertMau(mau);
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
        if (tblMau.getSelectedRow() != -1) {
            ThuocTinhMau mau = getValue("update");
            if (mau != null) {
                try {
                    if (JOptionPane.showConfirmDialog(this, "Xác nhận sửa\nMã tự sinh: " + mau.getMaMau(), "Xác nhận sửa", JOptionPane.YES_NO_OPTION) == 0) {
                        service.updateMau(mau);
                        resertList();
                        Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Sửa thành công");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Sửa thất bại");
                }

            }
        } else {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Chọn hàng để sửa");
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int index = tblMau.getSelectedRow();
        if (index != -1) {
            try {
                Long id = Long.valueOf(lbID.getText());
                if (Integer.valueOf(tblMau.getValueAt(index, 3) + "") != 0
                        || Integer.valueOf(tblMau.getValueAt(index, 4) + "") != 0
                        || Integer.valueOf(tblMau.getValueAt(index, 5) + "") != 0) {

                    if (JOptionPane.showConfirmDialog(this, "Màu đã có sản phẩm sử dụng\nHỗ trợ tắt trạng thái", "Xác nhận", JOptionPane.YES_NO_OPTION) == 0) {
                        service.tatTrangThai(id);
                        resertList();
                        Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Thể loại đã tắt hoạt động");
                    }

                } else {
                    if (JOptionPane.showConfirmDialog(this, "Xác nhận xóa", "Xác nhận", JOptionPane.YES_NO_OPTION) == 0) {
                        service.deleteMau(id);
                        resertList();
                        Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Đã xóa màu");
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

    private void txtTimeSecondActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimeSecondActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimeSecondActionPerformed

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
    private javax.swing.JTable tblMau;
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
