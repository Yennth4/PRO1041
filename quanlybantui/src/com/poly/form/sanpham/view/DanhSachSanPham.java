package com.poly.form.sanpham.view;

import com.formdev.flatlaf.FlatClientProperties;
import com.poly.Application;
import com.poly.form.bienthesanpham.view.QuanLyBienTheSanPham;
import com.poly.form.nhacungcap.entity.NhaCungCapDTO;
import com.poly.form.nhacungcap.service.NhaCungCapService;
import com.poly.form.sanpham.entity.SanPham;
import com.poly.form.sanpham.entity.SanPhamDTO;
import com.poly.form.sanpham.service.SanPhamService;
import com.poly.form.theloai.entity.TheLoaiDTO;
import com.poly.form.theloai.service.TheLoaiService;
import com.poly.form.thuoctinh.entity.ThuocTinhMau;
import com.poly.form.thuoctinh.entity.ThuocTinhMauDTO;
import com.poly.form.thuoctinh.service.ThuocTinhMauService;
import static com.poly.util.ph31848.MaRandom.renderMa;
import static com.poly.util.ph31848.Validate.checkEmpty;
import static com.poly.util.ph31848.Validate.checkChar;
import static com.poly.util.ph31848.Validate.checkNumber;
import static com.poly.util.ph31848.Validate.checkFloat;
import static com.poly.util.ph31848.Validate.formatDate;
import static com.poly.util.ph31848.Validate.isCorrectTime;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import raven.toast.Notifications;

/**
 *
 * @author Raven
 */
public class DanhSachSanPham extends javax.swing.JPanel {

    private DefaultTableModel dtm;

    private SanPhamService service;
    private TheLoaiService serviceTL;
    private NhaCungCapService serviceNCC;
    private ThuocTinhMauService serviceMau;

    private List<SanPhamDTO> list = new ArrayList<>();
    private List<TheLoaiDTO> listTheLoai = new ArrayList<>();
    private List<NhaCungCapDTO> listNCC = new ArrayList<>();
    private List<ThuocTinhMauDTO> listMau = new ArrayList<>();
    private List<String> listImage = new ArrayList<>();
    // Biến xử lý phân trang
    private int currentPage = 0;
    private int length = 0;
    private int pageSize = 3;
    private int totalPage = 0;

    // Biến xử lý image
    private int totalImage = 0;
    private int currentImageIndex = 0;

    public DanhSachSanPham() {
        initComponents();
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        txtMa.setEnabled(false);
        service = new SanPhamService();
        serviceTL = new TheLoaiService();
        serviceNCC = new NhaCungCapService();
        serviceMau = new ThuocTinhMauService();
        dtm = (DefaultTableModel) tblSanPham.getModel();
        listTheLoai = serviceTL.getAll();
        listNCC = serviceNCC.getAll();
        listMau = serviceMau.getAll();
        loadComboBoxTheLoai(listTheLoai);
        loadComboBoxNCC(listNCC);
        loadComboBoxMau(listMau);
        resertList();
    }

    public void resertListSearch() {
        //tên
        String ten = txtSearch.getText().trim();
        if (ten.equals("")) {
            ten = null;
        }
        //thể loại
        Long idtheloai = null;
        if (!((cbxSearchTheLoai.getSelectedItem() + "").equals("ALL")) && cbxSearchTheLoai.getSelectedItem() != null) {

            idtheloai = listTheLoai.get(cbxSearchTheLoai.getSelectedIndex()).getIdTheLoai();
        }
        // NCC
        Long idNCC = null;
        if (!((cbxSearchNCC.getSelectedItem() + "").equals("ALL")) && cbxSearchNCC.getSelectedItem() != null) {
            idNCC = listNCC.get(cbxSearchNCC.getSelectedIndex()).getId();
        }
        // Màu
        Long idMau = null;
        if (!((cbxSearchMau.getSelectedItem() + "").equals("ALL")) && cbxSearchMau.getSelectedItem() != null) {
            idMau = listMau.get(cbxSearchMau.getSelectedIndex()).getIDMau();
        }

        //số lượng cái
        int cbxSoLuongCai = cbxSearchSoLuong.getSelectedIndex();
        String txtSoLuongCai = txtCbxSearchSoLuong.getText().trim();

        //biến thể
        int cbxBienThe = cbxSearchBienThe.getSelectedIndex();
        String txtBienThe = txtCbxSearchBienThe.getText().trim();

        // DATE
        int cbxThoiGianType = cbxThoiGianSearch.getSelectedIndex();
        Date dateFirst = txtDateFirst.getDate();
        String timeFirst = txtTimeFirst.getText().trim();
        Date dateSecond = txtDateSecond.getDate();
        String timeSecond = txtTimeSecond.getText().trim();

        // trạng thái
        int trangThai = cbxSearchTrangThai.getSelectedIndex();

        if (!checkNumber(txtSoLuongCai) && !checkEmpty(txtSoLuongCai)) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Tồn kho phải là số nguyên dương");
            return;
        }
        if (!checkNumber(txtBienThe) && !checkEmpty(txtBienThe)) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Biến thể sản phẩm phải là số nguyên dương");
            return;
        }

        // Chuẩn hóa dữ liệu
        Integer txtSoLuongCaiSearch = (txtSoLuongCai.equals("")) ? null : Integer.valueOf(txtSoLuongCai);
        Integer txtBienTheSearch = (txtBienThe.equals("")) ? null : Integer.valueOf(txtBienThe);
        if (timeFirst.equals("") || !isCorrectTime(timeFirst)) {
            timeFirst = null;
        }
        if (timeSecond.equals("") || !isCorrectTime(timeSecond)) {
            timeSecond = null;
        }
        String dateFirstFormat = (dateFirst == null) ? null : formatDate(dateFirst);
        String dateSecondFormat = (dateFirst == null) ? null : formatDate(dateSecond);

        // Action
        list = service.search(ten, idtheloai, idNCC, idMau, cbxSoLuongCai, txtSoLuongCaiSearch,
                cbxBienThe, txtBienTheSearch, cbxThoiGianType, dateFirstFormat, timeFirst,
                dateSecondFormat, timeSecond, trangThai);
        tableCalculation(list);
        updateTable(list);
        setDisablePre();
        setDisableNext();

        if (totalPage > 1) {
            setEnableNext();
        }
    }

    public SanPham getValue(String type) {
        String id = lbID.getText();
        String ma = "";
        do {
            ma = "SP" + renderMa();
        } while (service.isExistMa(ma));
        String ten = txtTen.getText().trim();
        String moTa = txtMoTa.getText().trim();
        Long nccID = listNCC.get(cbxNCC.getSelectedIndex()).getId();
        Integer trangThai = (cbxTrangThai.getSelectedIndex() == 0) ? 1 : 0;
        Long theLoaiID = listTheLoai.get(cbxTheLoai.getSelectedIndex()).getIdTheLoai();
        Long nhanVienID = 1L; // chưa làm chức năng nhận diện Nhân viên, tạm thế này

        try {
            if (checkEmpty(ten) || checkEmpty(moTa)) {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Điền đủ tên và mô tả");
                return null;
            }
            if (type.equals("insert")) {
                return new SanPham(ma, ten, moTa, nccID, trangThai, theLoaiID, nhanVienID);
            } else if (type.equals("update")) {
                ma = txtMa.getText();
                return new SanPham(Long.valueOf(id), ma, ten, moTa, nccID, trangThai, theLoaiID, nhanVienID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void loadComboBoxMau(List<ThuocTinhMauDTO> list) {
        cbxSearchMau.removeAllItems();
        for (ThuocTinhMauDTO mau : list) {
            cbxSearchMau.addItem(mau.getTenMau());
        }
        cbxSearchMau.addItem("ALL");
        cbxSearchMau.setSelectedItem("ALL");
    }

    public void loadComboBoxTheLoai(List<TheLoaiDTO> listTL) {
        cbxTheLoai.removeAllItems();
        cbxSearchTheLoai.removeAllItems();
        for (TheLoaiDTO tl : listTL) {
            cbxTheLoai.addItem(tl.getTenTheLoai());
            cbxSearchTheLoai.addItem(tl.getTenTheLoai());
        }

        cbxSearchTheLoai.addItem("ALL");
        cbxSearchTheLoai.setSelectedItem("ALL");
    }

    public void loadComboBoxNCC(List<NhaCungCapDTO> listNCC) {
        cbxNCC.removeAllItems();
        cbxSearchNCC.removeAllItems();
        for (NhaCungCapDTO ncc : listNCC) {
            cbxNCC.addItem(ncc.getTenNCC());
            cbxSearchNCC.addItem(ncc.getTenNCC());
        }
        cbxSearchNCC.addItem("ALL");
        cbxSearchNCC.setSelectedItem("ALL");
    }

    public void clear() {
        lbID.setText("ID tự sinh");
        txtMa.setText("");
        txtTen.setText("");
        txtMoTa.setText("");
        lbThoiGianSua.setText("");
        lbThoiGianTao.setText("");
        cbxTheLoai.setSelectedIndex(0);
        cbxNCC.setSelectedIndex(0);
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

    public void tableCalculation(List<SanPhamDTO> list) {
        length = list.size();
        totalPage = length / pageSize;
        totalPage = (length % pageSize != 0) ? totalPage + 1 : totalPage;
        currentPage = 0;
        lbSum.setText(totalPage + "");
        lbTongTheLoai.setText(length + "");
        //
        System.out.println("Total Page:" + totalPage);
        System.out.println("Tổng:" + length);
    }

    public void updateTable(List<SanPhamDTO> list) {
        int startRow = pageSize * currentPage; // 0
        int endRow = Math.min(startRow + pageSize - 1, length - 1);
        showTable(list, startRow, endRow);
        lbClick.setText(currentPage + 1 + "");
    }

    public void showTable(List<SanPhamDTO> list, int start, int end) {
        dtm.setRowCount(0);
        for (int i = start; i <= end; i++) {
            SanPhamDTO tl = list.get(i);
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

    public void showImage(int index) {
        ImageIcon icon = new ImageIcon(listImage.get(index));
        lbMainImage.setIcon(icon);
    }

    public void fillData(int index) {
        SanPhamDTO sanPham = list.get(index);
        lbID.setText(sanPham.getIdSanPham() + "");
        txtMa.setText(sanPham.getMaSanPham());
        txtTen.setText(sanPham.getTenSanPham());
        txtMoTa.setText(sanPham.getMoTaSanPham());
        int trangThai = sanPham.getTrangThai();
        cbxTrangThai.setSelectedItem("Không hoạt động");
        if (trangThai == 1) {
            cbxTrangThai.setSelectedItem("Hoạt động");
        }
        cbxTheLoai.setSelectedItem(sanPham.getTheLoai());
        cbxNCC.setSelectedItem(sanPham.getNhaCungCap());
        lbNhanVienTao.setText(sanPham.getNhanVien());
        lbThoiGianTao.setText(sanPham.getThoiGianTao());
        lbThoiGianSua.setText(sanPham.getThoiGianSua());
        listImage.clear();
        listImage = service.getImageSanPham(sanPham.getIdSanPham());
        totalImage = listImage.size();
        if (totalImage > 0) {
            showImage(0);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        btnPre = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        lbClick = new javax.swing.JLabel();
        lbClick1 = new javax.swing.JLabel();
        lbSum = new javax.swing.JLabel();
        lbTongTheLoai = new javax.swing.JLabel();
        lbNhanVienTao11 = new javax.swing.JLabel();
        lbNhanVienTao = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lbMainImage = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbxTrangThai = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        txtMa = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lbID = new javax.swing.JLabel();
        lbNextImage = new javax.swing.JLabel();
        lbPreImage = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lbThoiGianTao = new javax.swing.JLabel();
        lbThoiGianSua = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        cbxNCC = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        cbxTheLoai = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        cbxSearchTheLoai = new javax.swing.JComboBox<>();
        cbxSearchTrangThai = new javax.swing.JComboBox<>();
        cbxThoiGianSearch = new javax.swing.JComboBox<>();
        cbxSearchNCC = new javax.swing.JComboBox<>();
        cbxSearchSoLuong = new javax.swing.JComboBox<>();
        txtCbxSearchSoLuong = new javax.swing.JTextField();
        txtDateFirst = new com.toedter.calendar.JDateChooser();
        txtTimeFirst = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtDateSecond = new com.toedter.calendar.JDateChooser();
        txtTimeSecond = new javax.swing.JTextField();
        cbxSearchMau = new javax.swing.JComboBox<>();
        cbxSearchBienThe = new javax.swing.JComboBox<>();
        txtCbxSearchBienThe = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();

        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("Danh sách sản phẩm");

        tblSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Thể loại", "Số biến thể", "Tồn kho", "Màu"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblSanPhamMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

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

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setText("Tổng số sản phẩm: ");

        lbClick.setText("...");

        lbClick1.setText("of");

        lbSum.setText("...");

        lbTongTheLoai.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbTongTheLoai.setForeground(new java.awt.Color(255, 153, 0));
        lbTongTheLoai.setText("...");

        lbNhanVienTao11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbNhanVienTao11.setText("NV tạo:");

        lbNhanVienTao.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbNhanVienTao.setForeground(new java.awt.Color(102, 255, 102));
        lbNhanVienTao.setText("LongnvPh31848");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 751, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnPre, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbClick)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbClick1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbSum)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbTongTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbNhanVienTao11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbNhanVienTao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnNext)
                        .addComponent(btnLast)
                        .addComponent(lbClick)
                        .addComponent(lbClick1)
                        .addComponent(lbSum))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnPre)
                        .addComponent(btnBack))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(lbTongTheLoai)
                        .addComponent(lbNhanVienTao11)
                        .addComponent(lbNhanVienTao)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbMainImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 0)));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setText("Tên");

        txtTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText("Mô tả");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("Thể loại:");

        cbxTrangThai.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbxTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hoạt động", "Không hoạt động" }));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setText("Trạng thái:");

        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        txtMa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel9.setText("Mã:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setText("ID:");

        lbID.setText("lbID");

        lbNextImage.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbNextImage.setForeground(new java.awt.Color(255, 153, 0));
        lbNextImage.setText(" > ");
        lbNextImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbNextImageMouseClicked(evt);
            }
        });

        lbPreImage.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbPreImage.setForeground(new java.awt.Color(255, 153, 0));
        lbPreImage.setText(" < ");
        lbPreImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbPreImageMouseClicked(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel15.setText("Tạo vào lúc:");

        lbThoiGianTao.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbThoiGianTao.setText("...");

        lbThoiGianSua.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbThoiGianSua.setText("...");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel16.setText("Sửa lần cuối:");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel17.setText("Nhà cung cấp:");

        cbxNCC.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbxNCC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hoạt động", "Không hoạt động" }));

        txtMoTa.setColumns(20);
        txtMoTa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMoTa.setRows(5);
        jScrollPane2.setViewportView(txtMoTa);

        cbxTheLoai.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbxTheLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hoạt động", "Không hoạt động" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbMainImage, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel16)
                                    .addGap(14, 14, 14)
                                    .addComponent(lbThoiGianSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel15)
                                    .addGap(18, 18, 18)
                                    .addComponent(lbThoiGianTao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(txtTen)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(lbPreImage, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbNextImage, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbID, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbxTheLoai, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbxNCC, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbxTrangThai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSua)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(27, 27, 27))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(lbPreImage))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addComponent(lbNextImage)))
                        .addGap(76, 76, 76))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(lbMainImage, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(lbID)
                    .addComponent(jLabel9)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbxTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(cbxNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(cbxTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(lbThoiGianTao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(lbThoiGianSua))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Tìm kiếm"));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Tên:");

        txtSearch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        cbxSearchTheLoai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxSearchTheLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Thể loại" }));
        cbxSearchTheLoai.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxSearchTheLoaiItemStateChanged(evt);
            }
        });

        cbxSearchTrangThai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxSearchTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hoạt động", "Không hoạt động" }));
        cbxSearchTrangThai.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxSearchTrangThaiItemStateChanged(evt);
            }
        });

        cbxThoiGianSearch.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cbxThoiGianSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tạo từ ngày", "Sửa từ ngày" }));
        cbxThoiGianSearch.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxThoiGianSearchItemStateChanged(evt);
            }
        });
        cbxThoiGianSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxThoiGianSearchActionPerformed(evt);
            }
        });

        cbxSearchNCC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NCC" }));
        cbxSearchNCC.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxSearchNCCItemStateChanged(evt);
            }
        });

        cbxSearchSoLuong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxSearchSoLuong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tồn kho >=", "Tồn kho<=" }));
        cbxSearchSoLuong.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxSearchSoLuongItemStateChanged(evt);
            }
        });

        txtCbxSearchSoLuong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCbxSearchSoLuong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCbxSearchSoLuongKeyReleased(evt);
            }
        });

        txtDateFirst.setDateFormatString("yyyy-MM-dd");
        txtDateFirst.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtDateFirstPropertyChange(evt);
            }
        });

        txtTimeFirst.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtTimeFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimeFirstActionPerformed(evt);
            }
        });
        txtTimeFirst.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimeFirstKeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("đến");

        txtDateSecond.setDateFormatString("yyyy-MM-dd");
        txtDateSecond.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtDateSecondPropertyChange(evt);
            }
        });

        txtTimeSecond.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtTimeSecond.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimeSecondKeyReleased(evt);
            }
        });

        cbxSearchMau.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxSearchMau.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "màu" }));
        cbxSearchMau.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxSearchMauItemStateChanged(evt);
            }
        });

        cbxSearchBienThe.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxSearchBienThe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Biến thể >=", "Biến thể <=" }));
        cbxSearchBienThe.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxSearchBienTheItemStateChanged(evt);
            }
        });

        txtCbxSearchBienThe.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCbxSearchBienThe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCbxSearchBienTheKeyReleased(evt);
            }
        });

        btnTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTimKiem.setText("Tìm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(cbxThoiGianSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDateFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimeFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxSearchTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(cbxSearchNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbxSearchMau, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxSearchSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtDateSecond, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTimeSecond, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtCbxSearchSoLuong)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxSearchBienThe, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCbxSearchBienThe, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(cbxSearchTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(btnTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxSearchTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxSearchNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxSearchMau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxSearchSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCbxSearchSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxSearchBienThe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCbxSearchBienThe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbxSearchTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnTimKiem)
                                .addComponent(txtTimeSecond, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtTimeFirst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8))
                            .addComponent(txtDateFirst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxThoiGianSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(txtDateSecond, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        int index = tblSanPham.getSelectedRow() + currentPage * pageSize;
        fillData(index);
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void lbPreImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbPreImageMouseClicked
        if (currentImageIndex == 0) {
            currentImageIndex = totalImage - 1;
            showImage(currentImageIndex);
        } else {
            --currentImageIndex;
            showImage(currentImageIndex);
        }
    }//GEN-LAST:event_lbPreImageMouseClicked

    private void lbNextImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbNextImageMouseClicked
        if (currentImageIndex == (totalImage - 1)) {
            currentImageIndex = 0;
            showImage(currentImageIndex);
        } else {
            ++currentImageIndex;
            showImage(currentImageIndex);
        }
    }//GEN-LAST:event_lbNextImageMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        SanPham sp = getValue("insert");
        if (sp != null) {

            try {
                if (JOptionPane.showConfirmDialog(this, "Xác nhận thêm\nMã tự sinh: " + sp.getMaSanPham(), "Xác nhận thêm", JOptionPane.YES_NO_OPTION) == 0) {
                    service.insertSanPham(sp);
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
        if (tblSanPham.getSelectedRow() != -1) {
            SanPham sp = getValue("update");
            try {
                if (sp != null) {
                    if (JOptionPane.showConfirmDialog(this, "Xác nhận sửa\nMã: " + sp.getMaSanPham(), "Xác nhận sửa", JOptionPane.YES_NO_OPTION) == 0) {
                        service.updateSanPham(sp);
                        resertList();
                        Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Sửa thành công");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Chọn hàng để sửa");
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int index = tblSanPham.getSelectedRow();
        if (index != -1) {

            try {
                Long id = Long.valueOf(lbID.getText());
                if (Integer.valueOf(tblSanPham.getValueAt(index, 3) + "") != 0
                        || Integer.valueOf(tblSanPham.getValueAt(index, 4) + "") != 0) {

                    if (JOptionPane.showConfirmDialog(this, "Sản phẩm đã có biến thể\nHỗ trợ tắt trạng thái", "Xác nhận", JOptionPane.YES_NO_OPTION) == 0) {
                        service.tatTrangThai(id);
                        resertList();
                        Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Sản phẩm đã tắt hoạt động");
                    }

                } else {
                    if (JOptionPane.showConfirmDialog(this, "Xác nhận xóa", "Xác nhận", JOptionPane.YES_NO_OPTION) == 0) {
                        service.deleteSanPham(id);
                        resertList();
                        Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Đã xóa sản phẩm");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Chọn hàng để xóa");
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tblSanPhamMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMousePressed
        try {
            if (evt.getButton() == MouseEvent.BUTTON3) {
                int index = tblSanPham.getSelectedRow() + currentPage * pageSize;
                SanPhamDTO sp = list.get(index);
                Application.showForm(new QuanLyBienTheSanPham(sp));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tblSanPhamMousePressed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        resertListSearch();
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        resertListSearch();
    }//GEN-LAST:event_txtSearchKeyReleased

    private void cbxThoiGianSearchItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxThoiGianSearchItemStateChanged
        resertListSearch();
    }//GEN-LAST:event_cbxThoiGianSearchItemStateChanged

    private void txtDateFirstPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtDateFirstPropertyChange
        resertListSearch();
    }//GEN-LAST:event_txtDateFirstPropertyChange

    private void txtTimeFirstKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimeFirstKeyReleased
        resertListSearch();
    }//GEN-LAST:event_txtTimeFirstKeyReleased

    private void txtDateSecondPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtDateSecondPropertyChange
        resertListSearch();
    }//GEN-LAST:event_txtDateSecondPropertyChange

    private void txtTimeSecondKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimeSecondKeyReleased
        resertListSearch();
    }//GEN-LAST:event_txtTimeSecondKeyReleased

    private void cbxSearchTrangThaiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxSearchTrangThaiItemStateChanged
        resertListSearch();
    }//GEN-LAST:event_cbxSearchTrangThaiItemStateChanged

    private void cbxSearchSoLuongItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxSearchSoLuongItemStateChanged
        resertListSearch();
    }//GEN-LAST:event_cbxSearchSoLuongItemStateChanged

    private void txtCbxSearchSoLuongKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCbxSearchSoLuongKeyReleased
        resertListSearch();
    }//GEN-LAST:event_txtCbxSearchSoLuongKeyReleased

    private void cbxSearchBienTheItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxSearchBienTheItemStateChanged
        resertListSearch();
    }//GEN-LAST:event_cbxSearchBienTheItemStateChanged

    private void txtCbxSearchBienTheKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCbxSearchBienTheKeyReleased
        resertListSearch();
    }//GEN-LAST:event_txtCbxSearchBienTheKeyReleased

    private void cbxSearchTheLoaiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxSearchTheLoaiItemStateChanged
        resertListSearch();
    }//GEN-LAST:event_cbxSearchTheLoaiItemStateChanged

    private void cbxSearchNCCItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxSearchNCCItemStateChanged
        resertListSearch();
    }//GEN-LAST:event_cbxSearchNCCItemStateChanged

    private void cbxSearchMauItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxSearchMauItemStateChanged
        resertListSearch();
    }//GEN-LAST:event_cbxSearchMauItemStateChanged

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

    private void txtTimeFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimeFirstActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimeFirstActionPerformed

    private void cbxThoiGianSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxThoiGianSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxThoiGianSearchActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPre;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbxNCC;
    private javax.swing.JComboBox<String> cbxSearchBienThe;
    private javax.swing.JComboBox<String> cbxSearchMau;
    private javax.swing.JComboBox<String> cbxSearchNCC;
    private javax.swing.JComboBox<String> cbxSearchSoLuong;
    private javax.swing.JComboBox<String> cbxSearchTheLoai;
    private javax.swing.JComboBox<String> cbxSearchTrangThai;
    private javax.swing.JComboBox<String> cbxTheLoai;
    private javax.swing.JComboBox<String> cbxThoiGianSearch;
    private javax.swing.JComboBox<String> cbxTrangThai;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lbClick;
    private javax.swing.JLabel lbClick1;
    private javax.swing.JLabel lbID;
    private javax.swing.JLabel lbMainImage;
    private javax.swing.JLabel lbNextImage;
    private javax.swing.JLabel lbNhanVienTao;
    private javax.swing.JLabel lbNhanVienTao11;
    private javax.swing.JLabel lbPreImage;
    private javax.swing.JLabel lbSum;
    private javax.swing.JLabel lbThoiGianSua;
    private javax.swing.JLabel lbThoiGianTao;
    private javax.swing.JLabel lbTongTheLoai;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtCbxSearchBienThe;
    private javax.swing.JTextField txtCbxSearchSoLuong;
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
