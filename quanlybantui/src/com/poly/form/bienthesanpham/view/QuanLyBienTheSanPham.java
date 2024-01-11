package com.poly.form.bienthesanpham.view;

import com.formdev.flatlaf.FlatClientProperties;
import com.poly.form.bienthesanpham.entity.BienTheSanPham;
import com.poly.form.bienthesanpham.entity.BienTheSanPhamDTO;
import com.poly.form.bienthesanpham.service.BienTheSanPhamService;
import com.poly.form.sanpham.entity.SanPhamDTO;
import com.poly.form.sanpham.service.SanPhamService;
import static com.poly.util.ph31848.MaRandom.renderMa;
import static com.poly.util.ph31848.Validate.checkChar;
import static com.poly.util.ph31848.Validate.checkEmpty;
import static com.poly.util.ph31848.Validate.checkFloat;
import static com.poly.util.ph31848.Validate.checkNumber;
import com.poly.form.thuoctinh.entity.ThuocTinhMauDTO;
import com.poly.form.thuoctinh.service.ThuocTinhMauService;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import raven.toast.Notifications;
import static com.poly.util.ph31848.OpenFile.openDocxFile;

/**
 *
 * @author Raven
 */
public class QuanLyBienTheSanPham extends javax.swing.JPanel {

    private BienTheSanPhamService service;
    private ThuocTinhMauService serviceMau;
    private DefaultTableModel dtm;
    private List<BienTheSanPhamDTO> list = new ArrayList<>();
    private List<ThuocTinhMauDTO> listMau = new ArrayList<>();

    // Biến xử lý phân trang
    private int currentPage = 0;
    private int length = 0;
    private int pageSize = 15;
    private int totalPage = 0;
    int startRow = 0;
    int endRow = 0;
    // SP
    private SanPhamDTO sp;

    // BTSP
    private String pathImageIcon = "";

    public QuanLyBienTheSanPham() {
        initComponents();
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        dtm = (DefaultTableModel) tblBienThe.getModel();
    }

    public QuanLyBienTheSanPham(SanPhamDTO sp) {
        initComponents();
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        this.sp = sp;
        setInfoSanPham();
        dtm = (DefaultTableModel) tblBienThe.getModel();
        service = new BienTheSanPhamService();
        serviceMau = new ThuocTinhMauService();
//        list = service.findByMaSanPham(this.sp.getMaSanPham());
        listMau = serviceMau.getAll();
        tableCalculation(list);
        resertList();
        loadComboBoxMau(listMau);
        txtMa.setEnabled(false);
    }

    public void resertList() {
        list = service.findByMaSanPham(this.sp.getMaSanPham());
        tableCalculation(list);
        updateTable(list);
        setDisablePre();
        setDisableNext();
        if (totalPage > 1) {
            setEnableNext();
        }
    }

    public void updateTable(List<BienTheSanPhamDTO> list) {
        int startRow = pageSize * currentPage; // 0
        int endRow = Math.min(startRow + pageSize - 1, length - 1);
        showTable(list, startRow, endRow);
        lbClick.setText(currentPage + 1 + "");
    }

    public void tableCalculation(List<BienTheSanPhamDTO> list) {
        length = list.size();
        totalPage = length / pageSize;
        totalPage = (length % pageSize != 0) ? totalPage + 1 : totalPage;
        currentPage = 0;
        lbSum.setText(totalPage + "");
        lbTongBienThe.setText(length + "");
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

    public void loadComboBoxMau(List<ThuocTinhMauDTO> list) {
        cbxMau.removeAllItems();
        for (ThuocTinhMauDTO mau : list) {
            cbxMau.addItem(mau.getTenMau());
        }
    }

    public void showTable(List<BienTheSanPhamDTO> list, int start, int end) {
//        dtm.setRowCount(0);
//        for (BienTheSanPhamDTO bt : list) {
//            dtm.addRow(bt.toDataRow());
//        }

        dtm.setRowCount(0);
        for (int i = start; i <= end; i++) {
            BienTheSanPhamDTO bt = list.get(i);
            dtm.addRow(bt.toDataRow());
        }
    }

    public void setInfoSanPham() {
        lbIDSanPham.setText(sp.getIdSanPham() + "");
        lbMaSanPham.setText(sp.getMaSanPham());
        lbTenSanPham.setText(sp.getTenSanPham());
        lbTheLoai.setText(sp.getTheLoai());
        lbNCC.setText(sp.getNhaCungCap());
        lbTongBienThe.setText(pathImageIcon);
    }

    public void thayDoiMainImage() {
        JFileChooser fileChooser = new JFileChooser("C:\\Users\\Admin\\Documents\\NetBeansProjects\\DuAn1\\DuAn1\\quanlybantui\\quanlybantui\\documents\\img_sp");
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File fileSelect = fileChooser.getSelectedFile();
            pathImageIcon = fileSelect.getPath();
            ImageIcon icon = new ImageIcon(pathImageIcon);
            lbMainImage.setIcon(icon);
        }
    }

    public BienTheSanPham getValue(String type) {
        Long id = 0l;
        String soLuong = txtSoLuong.getText().trim();
        String giaBan = txtGiaBan.getText().trim();
        String giaNiemYet = txtGiaNiemYet.getText().trim();
        String mainImage = pathImageIcon;
        Long idSanPham = Long.valueOf(lbIDSanPham.getText());
        Long idMau = listMau.get(cbxMau.getSelectedIndex()).getIDMau();
        Integer trangThai = cbxTrangThai.getSelectedIndex() == 0 ? 1 : 0;
        String ma = "";
        do {
            ma = "BT" + renderMa();
        } while (service.isExistMa(ma));
        try {
            if (checkEmpty(soLuong) || checkEmpty(giaBan) || checkEmpty(giaNiemYet)) {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Vui lòng điển đủ thông tin");
                return null;
            }
            if (!checkNumber(soLuong)) {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Số lượng phải là số nguyên dương");
                return null;
            }
            if (!checkFloat(giaBan) || !checkFloat(giaNiemYet)) {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Giá không hợp lệ");
                return null;
            }

            if (type.equals("insert")) {
                return new BienTheSanPham(Integer.valueOf(soLuong), trangThai, mainImage, idSanPham, idMau, Float.valueOf(giaBan), ma, Float.valueOf(giaNiemYet));
            } else if (type.equals("update")) {
                id = Long.valueOf(lbIDBienThe.getText());
                ma = txtMa.getText();
                return new BienTheSanPham(id, Integer.valueOf(soLuong), trangThai, mainImage, idSanPham, idMau, Float.valueOf(giaBan), ma, Float.valueOf(giaNiemYet));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBienThe = new javax.swing.JTable();
        btnPre = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        lbClick = new javax.swing.JLabel();
        lbClick1 = new javax.swing.JLabel();
        lbSum = new javax.swing.JLabel();
        lbTongBienThe = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lbMainImage = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbxMau = new javax.swing.JComboBox<>();
        cbxTrangThai = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        lbIDBienThe = new javax.swing.JLabel();
        btnExportQR = new javax.swing.JButton();
        txtGiaNiemYet = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbMaSanPham = new javax.swing.JLabel();
        lbTheLoai = new javax.swing.JLabel();
        lbTenSanPham = new javax.swing.JLabel();
        lbNCC = new javax.swing.JLabel();
        lbIDSanPham = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnImportExcel = new javax.swing.JButton();
        btnExportExcel = new javax.swing.JButton();
        btnMauNhapExcel = new javax.swing.JButton();

        lb.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("Biến thể sản phẩm");

        tblBienThe.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblBienThe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã biến thể", "Màu", "Số lượng", "Giá niêm yết", "Giá bán", "Đã bán"
            }
        ));
        tblBienThe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBienTheMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBienThe);

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
        jLabel7.setText("Tổng biến thể:");

        lbClick.setText("...");

        lbClick1.setText("of");

        lbSum.setText("...");

        lbTongBienThe.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbTongBienThe.setForeground(new java.awt.Color(255, 153, 0));
        lbTongBienThe.setText("...");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnPre, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbClick)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbClick1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbSum)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbTongBienThe, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(120, 120, 120))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbClick)
                        .addComponent(lbClick1)
                        .addComponent(lbSum)
                        .addComponent(jLabel7)
                        .addComponent(lbTongBienThe))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnNext)
                        .addComponent(btnLast))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnPre)
                        .addComponent(btnBack)))
                .addGap(14, 14, 14))
        );

        lbMainImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 0)));
        lbMainImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbMainImageMousePressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Mã");

        txtMa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtSoLuong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Số lượng");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Màu");

        cbxMau.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxMau.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbxTrangThai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hoạt động", "Không hoạt động" }));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Trạng thái:");

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setForeground(new java.awt.Color(255, 0, 51));
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Giá bán");

        txtGiaBan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("ID:");

        lbIDBienThe.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbIDBienThe.setText("0");

        btnExportQR.setText("Xuất QR");
        btnExportQR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportQRActionPerformed(evt);
            }
        });

        txtGiaNiemYet.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Giá niêm yết");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbMainImage, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel11)
                            .addComponent(jLabel13)
                            .addComponent(jLabel3)
                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnExportQR, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE))
                            .addComponent(txtSoLuong)
                            .addComponent(txtGiaNiemYet)
                            .addComponent(txtGiaBan)
                            .addComponent(cbxMau, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxTrangThai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbIDBienThe, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbMainImage, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel12)
                    .addComponent(lbIDBienThe))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGiaNiemYet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbxMau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(cbxTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExportQR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Thông tin sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Tên sản phẩm: ");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Thể loại:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Nhà cung cấp:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Mã SP:");

        lbMaSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbMaSanPham.setText("lbMaSanPham");

        lbTheLoai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbTheLoai.setText("lbTheLoai");

        lbTenSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbTenSanPham.setText("lbTenSanPham");

        lbNCC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbNCC.setText("lbNCC");

        lbIDSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbIDSanPham.setText("id");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Mã ID:");

        btnImportExcel.setText("Nhập Excel");
        btnImportExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportExcelActionPerformed(evt);
            }
        });

        btnExportExcel.setForeground(new java.awt.Color(0, 204, 51));
        btnExportExcel.setText("Xuất Excel");
        btnExportExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportExcelActionPerformed(evt);
            }
        });

        btnMauNhapExcel.setText("Mẫu nhập");
        btnMauNhapExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMauNhapExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExportExcel)
                        .addGap(18, 18, 18)
                        .addComponent(btnImportExcel)
                        .addGap(18, 18, 18)
                        .addComponent(btnMauNhapExcel)
                        .addGap(11, 11, 11))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(lbIDSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(lbIDSanPham)
                            .addComponent(jLabel10))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                                    .addComponent(btnMauNhapExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnImportExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnExportExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel8)
                                        .addComponent(lbNCC)))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lbMaSanPham)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbTenSanPham)
                        .addGap(18, 18, 18)
                        .addComponent(lbTheLoai)))
                .addGap(0, 17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblBienTheMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBienTheMouseClicked
        int index = tblBienThe.getSelectedRow();
        BienTheSanPhamDTO bienThe = list.get(index);
        pathImageIcon = bienThe.getMainImage();
        ImageIcon icon = new ImageIcon(pathImageIcon);
        lbMainImage.setIcon(icon);
        lbIDBienThe.setText(bienThe.getIdBienThe() + "");
        txtMa.setText(bienThe.getMa());
        txtSoLuong.setText(bienThe.getSoLuong() + "");
        txtGiaBan.setText(bienThe.getGiaBan() + "");
        txtGiaNiemYet.setText(bienThe.getGiaNiemYet() + "");
        cbxMau.setSelectedItem(bienThe.getMauSac());
        cbxTrangThai.setSelectedIndex(1);
        if (bienThe.getTrangThai() == 1) {
            cbxTrangThai.setSelectedIndex(0);
        }
    }//GEN-LAST:event_tblBienTheMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        BienTheSanPham bienThe = getValue("insert");
        System.out.println(bienThe);
        if (bienThe != null) {
            if (!service.isExistBienThe(bienThe)) {
                if (JOptionPane.showConfirmDialog(this, "Xác nhận thêm biến thể", "Xác nhận", JOptionPane.YES_NO_OPTION) == 0) {
                    service.insertBienThe(bienThe);
                    JOptionPane.showMessageDialog(this, "Mã tự sinh: " + bienThe.getMa());
                    list = service.findByMaSanPham(sp.getMaSanPham());
                    resertList();
                    updateTable(list);
                }
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Đã có sản phẩm màu " + cbxMau.getSelectedItem());

            }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        if (tblBienThe.getSelectedRow() != -1) {
            BienTheSanPham bienThe = getValue("update");
            System.out.println(bienThe);
            if (bienThe != null) {
                Long idMauMoi = bienThe.getIdMau();
                Long idMauCu = service.getIDMauBienTheByIDSanPham(Long.valueOf(lbIDBienThe.getText()));

                if (idMauMoi == idMauCu) {

                    service.updateBienThe(bienThe);
                    JOptionPane.showMessageDialog(this, "Đã sửa biến thể\n" + "Mã: " + bienThe.getMa());
                    list = service.findByMaSanPham(sp.getMaSanPham());
                    resertList();
                    updateTable(list);

                } else {
                    if (!service.isExistBienThe(bienThe)) {
                        service.updateBienThe(bienThe);
                        list = service.findByMaSanPham(sp.getMaSanPham());
                        updateTable(list);
                    } else {
                        Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Đã có sản phẩm màu " + cbxMau.getSelectedItem());

                    }
                }
            }
        } else {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Chọn hàng để sửa");

        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int index = tblBienThe.getSelectedRow();
        // check
        boolean isSale = service.bienTheIsSale(Long.valueOf(lbIDBienThe.getText()));

        if (index != -1) {
            if (Integer.valueOf(tblBienThe.getValueAt(index, 5) + "") == 0 && !isSale) {
                if (JOptionPane.showConfirmDialog(this, "Xóa biến thể\n", "Xác nhận xóa", JOptionPane.YES_NO_OPTION) == 0) {
                    service.deleteBienThe(Long.valueOf(lbIDBienThe.getText()));
                    JOptionPane.showMessageDialog(this, "Đã xóa biến thể\n" + "Mã: " + txtMa.getText());
                    list = service.findByMaSanPham(sp.getMaSanPham());
                    resertList();
                    updateTable(list);
                }
            } else if (isSale) {
                if (JOptionPane.showConfirmDialog(this, "Biến thể đang trong đợt khuyến mãi\nHỗ trợ tắt trạng thái", "Xác nhận", JOptionPane.YES_NO_OPTION) == 0) {
                    service.tatTrangThai(Long.valueOf(lbIDBienThe.getText()));
                    JOptionPane.showMessageDialog(this, "Đã tắt trạng thái biến thể\n" + "Mã: " + txtMa.getText());
                    list = service.findByMaSanPham(sp.getMaSanPham());
                    resertList();
                    updateTable(list);
                }
            } else {
                if (JOptionPane.showConfirmDialog(this, "Biến thể đã được bán\nHỗ trợ tắt trạng thái", "Xác nhận", JOptionPane.YES_NO_OPTION) == 0) {
                    service.tatTrangThai(Long.valueOf(lbIDBienThe.getText()));
                    JOptionPane.showMessageDialog(this, "Đã tắt trạng thái biến thể\n" + "Mã: " + txtMa.getText());
                    list = service.findByMaSanPham(sp.getMaSanPham());
                    resertList();
                    updateTable(list);
                }
            }
        } else {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Chọn hàng để xóa");

        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void lbMainImageMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbMainImageMousePressed
        if (evt.getButton() == MouseEvent.BUTTON3) {
            thayDoiMainImage();
        }
    }//GEN-LAST:event_lbMainImageMousePressed

    private void btnPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreActionPerformed
        currentPage = 0;
        setDisablePre();
        updateTable(list);
        setEnableNext();
    }//GEN-LAST:event_btnPreActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        currentPage--;
        updateTable(list);
        if (currentPage == 0) {
            setDisablePre();
        }

        setEnableNext();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        currentPage++;
        updateTable(list);
        if (currentPage == totalPage - 1) {
            setDisableNext();
        }
        setEnablePre();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        currentPage = totalPage - 1;
        setDisableNext();
        updateTable(list);
        setEnablePre();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnExportExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportExcelActionPerformed
        if (list.size() > 0) {
            String path = service.exportListToExcel(list, sp);
            if (JOptionPane.showConfirmDialog(this, "File lưu tại documents\\excel\\" + "\nTên file: " + path.substring(path.indexOf("documents\\excel\\") + "documents\\excel\\".length()) + "\n Xem file ngay?", "Thông báo", JOptionPane.YES_NO_OPTION) == 0) {
                openDocxFile(path);
            }

        } else {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Không có biến thể nào");

        }
    }//GEN-LAST:event_btnExportExcelActionPerformed

    private void btnImportExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportExcelActionPerformed
        String result = service.importExcel();
        Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, result);
        list = service.findByMaSanPham(sp.getMaSanPham());
        resertList();
        updateTable(list);
    }//GEN-LAST:event_btnImportExcelActionPerformed

    private void btnExportQRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportQRActionPerformed
        if (tblBienThe.getSelectedRow() != -1) {
            if (JOptionPane.showConfirmDialog(this, "Xuất mã QR?", "Thông báo", JOptionPane.YES_NO_OPTION) == 0) {
                String maSP = lbMaSanPham.getText();
                String maBTSP = txtMa.getText();
                String path = service.renderQRCodeByMaBienThe(maSP, maBTSP);
                service.insertTextToImage(path, maSP, maBTSP);
                if (JOptionPane.showConfirmDialog(this, "Ảnh QR lưu tại \\documents\\qr\\ \nTên file: " + maSP + "_" + maBTSP + "\nXem ngay?", "Thông báo", JOptionPane.YES_NO_OPTION) == 0) {
                    openDocxFile(path);
                }

            }
        } else {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Chọn biến thể để xuất QR");
        }
    }//GEN-LAST:event_btnExportQRActionPerformed

    private void btnMauNhapExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMauNhapExcelActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Tải mẫu nhập biến thể sản phẩm", "Tải mẫu nhập", JOptionPane.YES_NO_OPTION) == 0) {
            String path = service.exportMauExcel();
            if (JOptionPane.showConfirmDialog(this, "Mẫu nhập lưu tại \\documents\\excel\\MauNhapBienThe\\ \nTên file: MauNhapBienThe.xlsx \nMở ngay?", "Thông báo", JOptionPane.YES_NO_OPTION) == 0) {
                openDocxFile(path);
            }

        }
    }//GEN-LAST:event_btnMauNhapExcelActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnExportExcel;
    private javax.swing.JButton btnExportQR;
    private javax.swing.JButton btnImportExcel;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnMauNhapExcel;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPre;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbxMau;
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
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lbClick;
    private javax.swing.JLabel lbClick1;
    private javax.swing.JLabel lbIDBienThe;
    private javax.swing.JLabel lbIDSanPham;
    private javax.swing.JLabel lbMaSanPham;
    private javax.swing.JLabel lbMainImage;
    private javax.swing.JLabel lbNCC;
    private javax.swing.JLabel lbSum;
    private javax.swing.JLabel lbTenSanPham;
    private javax.swing.JLabel lbTheLoai;
    private javax.swing.JLabel lbTongBienThe;
    private javax.swing.JTable tblBienThe;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNiemYet;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtSoLuong;
    // End of variables declaration//GEN-END:variables
}
