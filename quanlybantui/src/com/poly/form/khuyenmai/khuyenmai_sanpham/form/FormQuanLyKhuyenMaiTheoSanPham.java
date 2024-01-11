package com.poly.form.khuyenmai.khuyenmai_sanpham.form;

import com.poly.form.khuyenmai.khuyenmai_sanpham.entity.KhuyenMaiTheoSanPhamRequest;
import com.poly.form.khuyenmai.khuyenmai_sanpham.entity.KhuyenMaiTheoSanPham;
import com.formdev.flatlaf.FlatClientProperties;
import com.poly.form.khuyenmai.khuyenmai_sanpham.entity.KhuyenMaiSanPham;
import com.poly.form.khuyenmai.khuyenmai_sanpham.entity.SanPhamChiTietForKhuyenMaiResponse;
import com.poly.form.khuyenmai.khuyenmai_sanpham.entity.SanPhamForKhuyenMaiResponse;
import com.poly.form.khuyenmai.khuyenmai_sanpham.repository.KMKhuyenMaiTheoSanPhamReposirory;
import com.poly.form.khuyenmai.khuyenmai_sanpham.repository.KMSPKhuyenMaiSanPhamRepository;
import com.poly.form.khuyenmai.khuyenmai_sanpham.repository.KMSanPhamRepository;
import com.poly.form.khuyenmai.khuyenmai_sanpham.service.SanPhamForKhuyenMaiService;
import com.poly.form.khuyenmai.khuyenmai_sanpham.service.impl.SanPhamForKhuyenMaiServiceImpl;
import com.poly.util.ph32148.RandomCodeGenerator;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class FormQuanLyKhuyenMaiTheoSanPham extends javax.swing.JPanel implements TableModelListener {

    private List<KhuyenMaiTheoSanPham> listKhuyenMais = new ArrayList<>();

    private List<SanPhamChiTietForKhuyenMaiResponse> listSanPhamChiTiet = new ArrayList<>();

    private List<SanPhamForKhuyenMaiResponse> listSanPham = new ArrayList<>();

    private DefaultTableModel defaultTableModel;

    private DefaultTableModel defaultTableModelSanPham;

    private DefaultTableModel defaultTableModelSanPhamChiTiet;

    private KMKhuyenMaiTheoSanPhamReposirory repo = new KMKhuyenMaiTheoSanPhamReposirory();

    private KMSanPhamRepository repoSanPhamChiTiet = new KMSanPhamRepository();

    private KMSPKhuyenMaiSanPhamRepository repoSanPhamKhuyenMai = new KMSPKhuyenMaiSanPhamRepository();

    private ArrayList<SanPhamChiTietForKhuyenMaiResponse> listSanPhamChoose = new ArrayList<>();

    private SanPhamForKhuyenMaiService serviceSanPham = new SanPhamForKhuyenMaiServiceImpl();

    public ArrayList<SanPhamChiTietForKhuyenMaiResponse> getListProductChose() {
        return this.listSanPhamChoose;
    }

    private int currentPage = 1;
    private final int pageSize = 10;
    private static final int COLUMN_CHECKBOX = 7;
    private static final int COLUMN_MA = 0;
    private Boolean chonTatCaSanPham = false;

    public FormQuanLyKhuyenMaiTheoSanPham() {
        initComponents();
        
        this.actionSuKien();

        this.lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");

        try {
            this.init();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi cấu hình FormQuanLyKhuyenMaiTheoSanPham");
            Logger.getLogger(FormQuanLyKhuyenMaiTheoSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void init() throws Exception {
        this.listKhuyenMais = this.repo.getData(currentPage);

        this.fillTableKhuyenMai(listKhuyenMais);

        this.initThem();

    }

    public void initThem() {
        txtTenApDung.setEnabled(false);
        txtGiaApDung.setEnabled(false);
    }

    public void actionSuKien() {
        this.defaultTableModelSanPhamChiTiet = (DefaultTableModel) tblDanhSachSanPhamChiTiet.getModel();

        this.defaultTableModelSanPhamChiTiet.addTableModelListener((TableModelEvent ev) -> {
            int firsRow = ev.getFirstRow();
            int column = ev.getColumn();

            if (column == COLUMN_CHECKBOX) {
                this.defaultTableModelSanPhamChiTiet = (DefaultTableModel) (TableModel) ev.getSource();
                System.out.println(firsRow);

                Boolean checked = (Boolean) this.defaultTableModelSanPhamChiTiet.getValueAt(firsRow, column);
                if (checked) {
                    String ma = (String) this.defaultTableModelSanPhamChiTiet.getValueAt(firsRow, COLUMN_MA);
                    SanPhamChiTietForKhuyenMaiResponse sanPhamForKhuyenMaiResponse = serviceSanPham.getSanPhamChiTietForKhuyenMaiResponse(listSanPhamChiTiet, ma);
                    this.listSanPhamChoose.add(sanPhamForKhuyenMaiResponse);
                } else {
                    String ma = (String) this.defaultTableModelSanPhamChiTiet.getValueAt(firsRow, COLUMN_MA);
                    SanPhamChiTietForKhuyenMaiResponse sanPhamForKhuyenMaiResponse = this.serviceSanPham.getSanPhamChiTietForKhuyenMaiResponse(listSanPhamChiTiet, ma);
                    this.listSanPhamChoose.remove(sanPhamForKhuyenMaiResponse);
                }
            }

        });

        this.defaultTableModelSanPham = (DefaultTableModel) tblDanhSachSanPham.getModel();

        this.defaultTableModelSanPham.addTableModelListener((TableModelEvent ev) -> {
            int firsRow = ev.getFirstRow();
            int column = ev.getColumn();

            if (column == 2) {
                this.defaultTableModelSanPham = (DefaultTableModel) (TableModel) ev.getSource();
                Boolean checked = (Boolean) this.defaultTableModelSanPham.getValueAt(firsRow, column);
                System.out.println("heck" + checked);
                if (checked) {
                    String ma = (String) this.defaultTableModelSanPham.getValueAt(firsRow, COLUMN_MA);
                    SanPhamForKhuyenMaiResponse sanPhamForKhuyenMaiResponse = this.serviceSanPham.getSanPhamForKhuyenMaiResponSe(listSanPham, ma);
                    Long id = sanPhamForKhuyenMaiResponse.getId();
                    List<SanPhamChiTietForKhuyenMaiResponse> list = this.serviceSanPham.findSanPhamChiTietForKhuyenMaiResponseByMaSanPham(id);
                    for (SanPhamChiTietForKhuyenMaiResponse sanPhamChiTietForKhuyenMaiResponse : list) {
                        this.listSanPhamChiTiet.add(sanPhamChiTietForKhuyenMaiResponse);
                    }
                    this.fillTableSanPhamChiTiet(listSanPhamChiTiet);
                } else {
                    String ma = (String) this.defaultTableModelSanPham.getValueAt(firsRow, COLUMN_MA);
                    SanPhamForKhuyenMaiResponse sanPhamForKhuyenMaiResponse = this.serviceSanPham.getSanPhamForKhuyenMaiResponSe(listSanPham, ma);
                    Long id = sanPhamForKhuyenMaiResponse.getId();
                    List<SanPhamChiTietForKhuyenMaiResponse> list = this.serviceSanPham.findSanPhamChiTietForKhuyenMaiResponseByMaSanPham(id);
                    for (SanPhamChiTietForKhuyenMaiResponse sanPhamChiTietForKhuyenMaiResponse : list) {
                        String maSP = sanPhamChiTietForKhuyenMaiResponse.getMaSanPham();
                        Iterator<SanPhamChiTietForKhuyenMaiResponse> iterator = listSanPhamChiTiet.iterator();
                        while (iterator.hasNext()) {
                            SanPhamChiTietForKhuyenMaiResponse sanPhamChiTietForKhuyenMaiResponseRemove = iterator.next();
                            if (sanPhamChiTietForKhuyenMaiResponseRemove.getMaSanPham().trim().equalsIgnoreCase(maSP)) {
                                iterator.remove();
                            }
                        }

                    }
                    this.fillTableSanPhamChiTiet(listSanPhamChiTiet);
                }
            }

        });
    }

    public void fillTableKhuyenMai(List<KhuyenMaiTheoSanPham> list) {
        this.defaultTableModel = (DefaultTableModel) tblDanhSachKhuyenMai.getModel();
        this.defaultTableModel.setRowCount(0);
        for (KhuyenMaiTheoSanPham khuyenMaiTheoSanPham : list) {
            this.defaultTableModel.addRow(khuyenMaiTheoSanPham.toRowTable());
        }
    }

    public void fillTableSanPham(List<SanPhamForKhuyenMaiResponse> list) {
        this.defaultTableModel = (DefaultTableModel) tblDanhSachSanPham.getModel();
        this.defaultTableModel.setRowCount(0);
        for (SanPhamForKhuyenMaiResponse SanPhamForKhuyenMaiResponse : list) {
            this.defaultTableModel.addRow(SanPhamForKhuyenMaiResponse.toRowTable());
        }
    }

    public void fillTableSanPhamChiTiet(List<SanPhamChiTietForKhuyenMaiResponse> list) {
        this.defaultTableModel = (DefaultTableModel) tblDanhSachSanPhamChiTiet.getModel();
        this.defaultTableModel.setRowCount(0);
        for (SanPhamChiTietForKhuyenMaiResponse sanPhamChiTietForKhuyenMaiResponse : list) {
            Long gia = Long.valueOf(this.jslGiaTri.getValue());
            this.defaultTableModel.addRow(sanPhamChiTietForKhuyenMaiResponse.toRowTable(gia));
        }
    }

    public KhuyenMaiTheoSanPham findKhuyenMaiByMa(String ma, List<KhuyenMaiTheoSanPham> list) {
        KhuyenMaiTheoSanPham khuyenMaiTheoSanPham1 = null;

        for (KhuyenMaiTheoSanPham khuyenMaiTheoSanPham : list) {
            if (khuyenMaiTheoSanPham.getMa().equals(ma)) {
                return khuyenMaiTheoSanPham;// Nếu mã khớp, thêm vào danh sách kết quả
            }
        }
        return khuyenMaiTheoSanPham1;
    }

    public void fillTableToForm(String ma, List<KhuyenMaiTheoSanPham> list) {
        KhuyenMaiTheoSanPham khuyenMaiTheoSanPham = findKhuyenMaiByMa(ma, list);
        this.txtTen.setText(khuyenMaiTheoSanPham.getTen());
        this.jlbMa.setText(khuyenMaiTheoSanPham.getMa());
        this.jslGiaTri.setValue(Integer.valueOf(khuyenMaiTheoSanPham.getGiaTri()));
        if (khuyenMaiTheoSanPham.getTrangThai()) {
            this.rdHoatDong.setSelected(true);
        } else {
            this.rdNgungHoatDong.setSelected(true);
        }

        Date ngayBatDauConvert = new Date(khuyenMaiTheoSanPham.getNgayBatDau() * 1000);
        Date ngayKetThucConvert = new Date(khuyenMaiTheoSanPham.getNgayKetThuc() * 1000);
        Date ngayTaoConvert = new Date(khuyenMaiTheoSanPham.getThoiGianTao() * 1000);
        Date ngaySuaConvert = new Date(khuyenMaiTheoSanPham.getThoiGianSua() * 1000);

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");

        String ngayBatDauConverted = sdf.format(ngayBatDauConvert);
        String ngayKetThucConverted = sdf.format(ngayKetThucConvert);
        String ngayTaoConverted = sdf.format(ngayTaoConvert);
        String ngaySuaConverted = sdf.format(ngaySuaConvert);

        Calendar calNgayBatDau = Calendar.getInstance();
        calNgayBatDau.setTime(ngayBatDauConvert);

        Calendar calNgayKetThuc = Calendar.getInstance();
        calNgayKetThuc.setTime(ngayKetThucConvert);

        jdcNgayBatDau.setCalendar(calNgayBatDau);
        jdcNgayKetThuc.setCalendar(calNgayKetThuc);

        this.jlbNgayBatDau.setText(ngayBatDauConverted);
        this.jlbNgayKetThuc.setText(ngayKetThucConverted);
        this.jlbNgaySua.setText(ngaySuaConverted);
        this.jlbNgayTao.setText(ngayTaoConverted);
    }

    public KhuyenMaiTheoSanPham readForm(String ma) {
        try {
            String ten = this.txtTen.getText().trim();
            Integer giaTri = Integer.valueOf(this.jslGiaTri.getValue());

            Long ngayBatDau = null;
            Long ngayKetThuc = null;

            if (jdcNgayBatDau.getDate() != null && jdcNgayKetThuc.getDate() != null) {
                Calendar ngayBatDauCal = jdcNgayBatDau.getCalendar();
                Calendar ngayKetThucCal = jdcNgayKetThuc.getCalendar();

                ngayBatDau = ngayBatDauCal.getTimeInMillis() / 1000;
                ngayKetThuc = ngayKetThucCal.getTimeInMillis() / 1000;
            }

            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date()); // Set the time for the current date
            Long ngayTaoVaSua = cal.getTime().getTime() / 1000;

            Boolean trangThai = this.rdHoatDong.isSelected();

            // Validation
            if (ten.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tên khuyến mại không được để trống");
                return null;
            }

            if (ngayBatDau == null || ngayKetThuc == null) {
                JOptionPane.showMessageDialog(this, "Ngày của đợt khuyến mại không được để trống");
                return null;
            }

            if (ngayBatDau >= ngayKetThuc) {
                JOptionPane.showMessageDialog(this, "Ngày kết thúc phải sau ngày bắt đầu");
                return null;
            }

            return new KhuyenMaiTheoSanPham(null, ma, ten, giaTri, ngayBatDau, ngayKetThuc, ngayTaoVaSua, ngayTaoVaSua, trangThai);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá trị không hợp lệ");
            return null;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Đọc file lỗi");
            e.printStackTrace();
            return null;
        }
    }

    public KhuyenMaiTheoSanPhamRequest readFormSearch() {
        KhuyenMaiTheoSanPhamRequest khuyenMaiTheoSanPham = null;

        try {
            String input = this.txtTimKiemMaOrTen.getText();

            Integer giaTri = null;
            if (!this.txtGiaTriTimKiem.getText().trim().equals("")) {
                giaTri = Integer.valueOf(this.txtGiaTriTimKiem.getText());
            }
            Calendar ngayKetThucCal = jdcNgayKetThucTimKiem.getCalendar();
            Calendar ngayBatDauCal = jdcNgayBatDauTimKiem.getCalendar();

            Long ngayBatDau = null;
            Long ngayKetThuc = null;

            if (ngayBatDauCal != null) {
                ngayBatDau = ngayBatDauCal.getTimeInMillis() / 1000;
            }

            if (ngayKetThucCal != null) {
                ngayKetThuc = ngayKetThucCal.getTimeInMillis() / 1000;
            }

            System.out.println("Ngay Bat Dau: " + ngayBatDau);
            System.out.println("Ngay Ket Thuc: " + ngayKetThuc);

            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date()); // Đặt thời gian cho ngày hiện tại
            Long ngayTaoVaSua = cal.getTime().getTime() / 1000;

            Boolean trangThai;
            if (this.rdHoatDongTimKiem.isSelected()) {
                trangThai = true;
            } else if (this.rdTatCaTimKiem.isSelected()) {
                trangThai = null;
            } else {
                trangThai = false;
            }

            return new KhuyenMaiTheoSanPhamRequest(input, giaTri, ngayBatDau, ngayKetThuc, trangThai, ngayTaoVaSua);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Đọc file lỗi");
            e.printStackTrace();
            return khuyenMaiTheoSanPham;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rdgrTrangThaiForm = new javax.swing.ButtonGroup();
        rdgrTrangThaiTimKiem = new javax.swing.ButtonGroup();
        jdlFormThemSanPham = new javax.swing.JDialog();
        jpnFormThemSanPham = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDanhSachSanPhamChiTiet = new javax.swing.JTable();
        btnChonTatCa = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jlbMaApDung = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtTenApDung = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jlbNgayBatDauApDung = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jlbNgayKetThucApDung = new javax.swing.JLabel();
        txtGiaApDung = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jlbTrangThaiApDung = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDanhSachSanPham = new javax.swing.JTable();
        txtTimKiemSanPham = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnTimKiemSanPham = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtTimKiemSanPhamCT = new javax.swing.JTextField();
        btnTimKiemSanPhamChiTiet = new javax.swing.JButton();
        btnApDungSanPhamChiTiet = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        lb = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jlbMa = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jslGiaTri = new javax.swing.JSlider();
        jlbGiaTri = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        rdHoatDong = new javax.swing.JRadioButton();
        rdNgungHoatDong = new javax.swing.JRadioButton();
        jlbNgayBatDau = new javax.swing.JLabel();
        jlbNgayKetThuc = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jlbNgaySua = new javax.swing.JLabel();
        jlbNgayTao = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoaMem = new javax.swing.JButton();
        btnDonForm = new javax.swing.JButton();
        jdcNgayBatDau = new com.toedter.calendar.JDateChooser();
        jdcNgayKetThuc = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDanhSachKhuyenMai = new javax.swing.JTable();
        btnTrai = new javax.swing.JButton();
        btnPhai = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        txtTimKiemMaOrTen = new javax.swing.JTextField();
        rdHoatDongTimKiem = new javax.swing.JRadioButton();
        jLabel16 = new javax.swing.JLabel();
        rdTatCaTimKiem = new javax.swing.JRadioButton();
        rdNgungHoatDongTimKiem = new javax.swing.JRadioButton();
        jLabel19 = new javax.swing.JLabel();
        jlbNgayKetThucTimKiem = new javax.swing.JLabel();
        jlbNgayBatDauTimKiem = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        btnTimKiem = new javax.swing.JButton();
        btnDonFormTimKiem = new javax.swing.JButton();
        jdcNgayBatDauTimKiem = new com.toedter.calendar.JDateChooser();
        jdcNgayKetThucTimKiem = new com.toedter.calendar.JDateChooser();
        txtGiaTriTimKiem = new javax.swing.JTextField();

        tblDanhSachSanPhamChiTiet.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblDanhSachSanPhamChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null,  new Boolean(false)},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã", "Tên", "Màu", "Giá niêm yết", "Giá bán", "Giá sau áp dụng", "Giá giảm", "Chọn"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblDanhSachSanPhamChiTiet.setRowHeight(50);
        tblDanhSachSanPhamChiTiet.setRowMargin(1);
        jScrollPane2.setViewportView(tblDanhSachSanPhamChiTiet);

        btnChonTatCa.setText("Chọn/ Hủy chọn tất cả");
        btnChonTatCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonTatCaActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.orange), "Thông tin khuyến mại"));

        jLabel4.setText("Mã: ");

        jlbMaApDung.setForeground(java.awt.Color.red);

        jLabel20.setText("Tên:");

        jLabel23.setText("Giá trị:");

        jlbNgayBatDauApDung.setForeground(java.awt.Color.red);

        jLabel12.setText("Ngày bắt đầu:");

        jLabel11.setText("Ngày kết thúc:");

        jlbNgayKetThucApDung.setForeground(java.awt.Color.red);

        jLabel17.setText("Trạng thái: ");

        jlbTrangThaiApDung.setForeground(java.awt.Color.red);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(jLabel20)
                            .addComponent(jLabel4))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jlbTrangThaiApDung, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel11))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtGiaApDung, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jlbNgayBatDauApDung, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtTenApDung, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jlbMaApDung, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jlbNgayKetThucApDung, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(32, 32, 32))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jlbMaApDung, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtTenApDung, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGiaApDung, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jlbNgayBatDauApDung, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jlbNgayKetThucApDung, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlbTrangThaiApDung, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.orange), "Lọc sản phẩm"));

        tblDanhSachSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã", "Tên", "Chọn "
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblDanhSachSanPham.setRowHeight(40);
        jScrollPane4.setViewportView(tblDanhSachSanPham);

        jLabel10.setText("Tìm kiếm (Tên & Mã)");

        btnTimKiemSanPham.setText("Tìm kiếm");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(30, 30, 30)
                .addComponent(txtTimKiemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btnTimKiemSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(btnTimKiemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel13.setText("Tìm kiếm (Tên & Mã)");

        btnTimKiemSanPhamChiTiet.setText("Tìm kiếm");

        btnApDungSanPhamChiTiet.setText("Áp dụng");
        btnApDungSanPhamChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApDungSanPhamChiTietActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnFormThemSanPhamLayout = new javax.swing.GroupLayout(jpnFormThemSanPham);
        jpnFormThemSanPham.setLayout(jpnFormThemSanPhamLayout);
        jpnFormThemSanPhamLayout.setHorizontalGroup(
            jpnFormThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnFormThemSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 964, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jpnFormThemSanPhamLayout.createSequentialGroup()
                .addGroup(jpnFormThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpnFormThemSanPhamLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel13)
                        .addGap(30, 30, 30)
                        .addComponent(txtTimKiemSanPhamCT)))
                .addGap(24, 24, 24)
                .addGroup(jpnFormThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnFormThemSanPhamLayout.createSequentialGroup()
                        .addComponent(btnTimKiemSanPhamChiTiet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(32, 32, 32)
                        .addComponent(btnApDungSanPhamChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btnChonTatCa, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jpnFormThemSanPhamLayout.setVerticalGroup(
            jpnFormThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnFormThemSanPhamLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpnFormThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(32, 32, 32)
                .addGroup(jpnFormThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnChonTatCa, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpnFormThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTimKiemSanPhamCT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13)
                        .addComponent(btnTimKiemSanPhamChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnApDungSanPhamChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );

        javax.swing.GroupLayout jdlFormThemSanPhamLayout = new javax.swing.GroupLayout(jdlFormThemSanPham.getContentPane());
        jdlFormThemSanPham.getContentPane().setLayout(jdlFormThemSanPhamLayout);
        jdlFormThemSanPhamLayout.setHorizontalGroup(
            jdlFormThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnFormThemSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jdlFormThemSanPhamLayout.setVerticalGroup(
            jdlFormThemSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnFormThemSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jList1);

        lb.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lb.setText("Quản lý đợt khuyến mại");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin khuyến mại", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 24))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Mã: ");

        jlbMa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jlbMa.setForeground(java.awt.Color.red);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Tên:");

        txtTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTen.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtTenPropertyChange(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Giá trị:");

        jslGiaTri.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jslGiaTriStateChanged(evt);
            }
        });

        jlbGiaTri.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jlbGiaTri.setForeground(java.awt.Color.red);
        jlbGiaTri.setText("50");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(java.awt.Color.red);
        jLabel5.setText("%");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Ngày kết thúc:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Ngày bắt đầu:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Trạng thái:");

        rdgrTrangThaiForm.add(rdHoatDong);
        rdHoatDong.setSelected(true);
        rdHoatDong.setText("Hoạt động");

        rdgrTrangThaiForm.add(rdNgungHoatDong);
        rdNgungHoatDong.setText("Ngừng hoạt động");

        jlbNgayBatDau.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jlbNgayBatDau.setForeground(java.awt.Color.red);

        jlbNgayKetThuc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jlbNgayKetThuc.setForeground(java.awt.Color.red);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Ngày sửa:");

        jlbNgaySua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jlbNgaySua.setForeground(java.awt.Color.red);

        jlbNgayTao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jlbNgayTao.setForeground(java.awt.Color.red);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Ngày tạo:");

        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoaMem.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnXoaMem.setText("Xóa mềm/ Khôi phục");
        btnXoaMem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaMemActionPerformed(evt);
            }
        });

        btnDonForm.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnDonForm.setText("Dọn Form");
        btnDonForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDonFormActionPerformed(evt);
            }
        });

        jdcNgayBatDau.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdcNgayBatDauPropertyChange(evt);
            }
        });

        jdcNgayKetThuc.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdcNgayKetThucPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel3)))
                        .addGap(40, 40, 40))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(rdHoatDong)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                            .addComponent(rdNgungHoatDong))
                        .addComponent(jlbMa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTen, javax.swing.GroupLayout.Alignment.LEADING))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jslGiaTri, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlbGiaTri, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jlbNgayKetThuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlbNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel14))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbNgaySua, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jdcNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdcNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoaMem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDonForm, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                    .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(50, 50, 50))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnXoaMem, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jlbMa, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jlbNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7))
                                        .addComponent(jdcNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(19, 19, 19)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel6)
                                            .addComponent(jlbNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jdcNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(6, 6, 6)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jslGiaTri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jlbGiaTri, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel5)))
                                    .addGap(18, 18, 18)
                                    .addComponent(rdNgungHoatDong))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel2)
                                            .addGap(28, 28, 28))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jlbNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel14))
                                            .addGap(18, 18, 18)))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jlbNgaySua, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel8)
                                            .addComponent(rdHoatDong)
                                            .addComponent(jLabel9))))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnDonForm, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(1, 1, 1))))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách khuyến mại", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 24))); // NOI18N

        tblDanhSachKhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã", "Tên", "Giá", "Ngày bắt đầu", "Ngày kết thúc", "Trạng thái"
            }
        ));
        tblDanhSachKhuyenMai.setRowHeight(40);
        tblDanhSachKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachKhuyenMaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDanhSachKhuyenMai);

        btnTrai.setText("<");
        btnTrai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTraiActionPerformed(evt);
            }
        });

        btnPhai.setText(">");
        btnPhai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhaiActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Tìm theo mã - tên");

        txtTimKiemMaOrTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        rdgrTrangThaiTimKiem.add(rdHoatDongTimKiem);
        rdHoatDongTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdHoatDongTimKiem.setText("Hoạt động");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Trạng thái:");

        rdgrTrangThaiTimKiem.add(rdTatCaTimKiem);
        rdTatCaTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdTatCaTimKiem.setSelected(true);
        rdTatCaTimKiem.setText("Tất cả");

        rdgrTrangThaiTimKiem.add(rdNgungHoatDongTimKiem);
        rdNgungHoatDongTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdNgungHoatDongTimKiem.setText("Không hoạt động");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("Giá trị:");

        jlbNgayKetThucTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jlbNgayKetThucTimKiem.setForeground(java.awt.Color.red);
        jlbNgayKetThucTimKiem.setText("_");

        jlbNgayBatDauTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jlbNgayBatDauTimKiem.setForeground(java.awt.Color.red);
        jlbNgayBatDauTimKiem.setText("_");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel22.setText("Ngày kết thúc:");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("Ngày bắt đầu:");

        btnTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        btnDonFormTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDonFormTimKiem.setText("Dọn Form");
        btnDonFormTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDonFormTimKiemActionPerformed(evt);
            }
        });

        jdcNgayBatDauTimKiem.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdcNgayBatDauTimKiemPropertyChange(evt);
            }
        });

        jdcNgayKetThucTimKiem.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdcNgayKetThucTimKiemPropertyChange(evt);
            }
        });

        txtGiaTriTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(395, 395, 395)
                        .addComponent(btnTrai)
                        .addGap(209, 209, 209)
                        .addComponent(btnPhai)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTimKiemMaOrTen, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGiaTriTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(18, 18, 18)
                                .addComponent(rdTatCaTimKiem)
                                .addGap(18, 18, 18)
                                .addComponent(rdHoatDongTimKiem)
                                .addGap(18, 18, 18)
                                .addComponent(rdNgungHoatDongTimKiem))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnDonFormTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(jLabel21))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbNgayBatDauTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                            .addComponent(jlbNgayKetThucTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jdcNgayKetThucTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdcNgayBatDauTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15)
                                    .addComponent(txtTimKiemMaOrTen, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16)
                                    .addComponent(rdTatCaTimKiem)
                                    .addComponent(rdHoatDongTimKiem)
                                    .addComponent(rdNgungHoatDongTimKiem)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jdcNgayBatDauTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbNgayBatDauTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnTimKiem)
                                .addComponent(btnDonFormTimKiem)
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jlbNgayKetThucTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtGiaTriTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jdcNgayKetThucTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTrai)
                    .addComponent(btnPhai))
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lb)
                .addGap(407, 407, 407))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnXoaMemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaMemActionPerformed
        int choice = JOptionPane.showConfirmDialog(this, "Bạn có xóa không", "Thông báo", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            String ma = this.jlbMa.getText().trim();

            if (ma != null) {
                try {
                    Boolean trangThai;
                    if (rdHoatDong.isSelected()) {
                        trangThai = false;
                    } else {
                        trangThai = true;
                    }
                    this.repo.capNhatTrangThaiKhuyenMaiBangMa(ma, trangThai);
                    this.listKhuyenMais = this.repo.getData(currentPage);
                    this.fillTableKhuyenMai(this.listKhuyenMais);
                    this.fillTableToForm(ma, this.listKhuyenMais);
                    JOptionPane.showMessageDialog(this, "cập nhật thành công");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "cập nhật thất bại");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Mã không được null");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Đã hủy cập nhật");
        }
    }//GEN-LAST:event_btnXoaMemActionPerformed

    private void btnDonFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDonFormActionPerformed
        this.txtTen.setText("");
        this.jlbMa.setText("");
        this.jlbNgayBatDau.setText("");
        this.jlbNgayKetThuc.setText("");
        this.jlbNgayTao.setText("");
        this.jlbNgaySua.setText("");
//        this.rdHoatDong.setSelected(true);
        this.jslGiaTri.setValue(50);
        this.jdcNgayBatDau.setCalendar(null);
        this.jdcNgayKetThuc.setCalendar(null);
    }//GEN-LAST:event_btnDonFormActionPerformed

    private void jslGiaTriStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jslGiaTriStateChanged
        this.jlbGiaTri.setText(String.valueOf(this.jslGiaTri.getValue()));
        this.txtGiaApDung.setText(String.valueOf(this.jslGiaTri.getValue() + "%"));
    }//GEN-LAST:event_jslGiaTriStateChanged

    private void btnDonFormTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDonFormTimKiemActionPerformed
        this.jlbNgayBatDauTimKiem.setText("");
        this.jlbNgayKetThucTimKiem.setText("");
        this.jdcNgayBatDauTimKiem.setCalendar(null);
        this.jdcNgayKetThucTimKiem.setCalendar(null);
        this.txtTimKiemMaOrTen.setText("");
        this.rdTatCaTimKiem.setSelected(true);
        this.txtGiaTriTimKiem.setText("");
        this.jdcNgayBatDauTimKiem.setCalendar(null);
        this.jdcNgayKetThucTimKiem.setCalendar(null);
        try {
            this.listKhuyenMais = this.repo.getAllDataKhuyenMai();
        } catch (Exception ex) {
            Logger.getLogger(FormQuanLyKhuyenMaiTheoSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        fillTableKhuyenMai(listKhuyenMais);
    }//GEN-LAST:event_btnDonFormTimKiemActionPerformed

    private void tblDanhSachKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachKhuyenMaiMouseClicked
        int index = tblDanhSachKhuyenMai.getSelectedRow();
        String ma = (String) tblDanhSachKhuyenMai.getValueAt(index, 0);
        this.fillTableToForm(ma, listKhuyenMais);
    }//GEN-LAST:event_tblDanhSachKhuyenMaiMouseClicked

    private void jdcNgayBatDauPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdcNgayBatDauPropertyChange
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        Calendar ngay = this.jdcNgayBatDau.getCalendar();
        if (ngay != null) {
            Calendar ngayConver = ngay;
            ngayConver.set(Calendar.HOUR_OF_DAY, 0);
            ngayConver.set(Calendar.MINUTE, 0);
            ngayConver.set(Calendar.SECOND, 0);
            ngayConver.set(Calendar.MILLISECOND, 0);
            String ngayConverted = sdf.format(ngayConver.getTime());
            this.jlbNgayBatDau.setText(ngayConverted);
            this.jlbNgayBatDauApDung.setText(ngayConverted);
        }
    }//GEN-LAST:event_jdcNgayBatDauPropertyChange

    private void jdcNgayKetThucPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdcNgayKetThucPropertyChange
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        Calendar ngay = this.jdcNgayKetThuc.getCalendar();
        if (ngay != null) {
            Calendar ngayConver = ngay;
            ngayConver.set(Calendar.HOUR_OF_DAY, 0);
            ngayConver.set(Calendar.MINUTE, 0);
            ngayConver.set(Calendar.SECOND, 0);
            ngayConver.set(Calendar.MILLISECOND, 0);
            String ngayConverted = sdf.format(ngayConver.getTime());
            this.jlbNgayKetThuc.setText(ngayConverted);
            this.jlbNgayKetThucApDung.setText(ngayConverted);
        }
    }//GEN-LAST:event_jdcNgayKetThucPropertyChange

    private void jdcNgayBatDauTimKiemPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdcNgayBatDauTimKiemPropertyChange
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        Calendar ngay = this.jdcNgayBatDauTimKiem.getCalendar();
        if (ngay != null) {
            Calendar ngayConver = ngay;
            ngayConver.set(Calendar.HOUR_OF_DAY, 0);
            ngayConver.set(Calendar.MINUTE, 0);
            ngayConver.set(Calendar.SECOND, 0);
            ngayConver.set(Calendar.MILLISECOND, 0);
            String ngayConverted = sdf.format(ngayConver.getTime());
            this.jlbNgayBatDauTimKiem.setText(ngayConverted);
        }
    }//GEN-LAST:event_jdcNgayBatDauTimKiemPropertyChange

    private void jdcNgayKetThucTimKiemPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdcNgayKetThucTimKiemPropertyChange
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        Calendar ngay = this.jdcNgayKetThucTimKiem.getCalendar();
        if (ngay != null) {
            Calendar ngayConver = ngay;
            ngayConver.set(Calendar.HOUR_OF_DAY, 0);
            ngayConver.set(Calendar.MINUTE, 0);
            ngayConver.set(Calendar.SECOND, 0);
            ngayConver.set(Calendar.MILLISECOND, 0);
            String ngayConverted = sdf.format(ngayConver.getTime());
            this.jlbNgayKetThucTimKiem.setText(ngayConverted);
        }
    }//GEN-LAST:event_jdcNgayKetThucTimKiemPropertyChange

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        String ma = "VelisaKMSP-" + RandomCodeGenerator.generateCode(5);
        if (readForm(ma) != null) {
            Boolean trangThai = rdHoatDong.isSelected();

            if (trangThai) {
                this.jlbTrangThaiApDung.setText("Hoạt động");
            } else {
                this.jlbTrangThaiApDung.setText("Tạm thời pending");
            }
            this.jlbMaApDung.setText(ma);
            this.txtTenApDung.setText(txtTen.getText());
            this.txtGiaApDung.setText(String.valueOf(jslGiaTri.getValue()));
            this.jdlFormThemSanPham = new JDialog();
            this.jdlFormThemSanPham.getContentPane().add(jpnFormThemSanPham);
            this.jdlFormThemSanPham.setSize(new Dimension(1366, 768));

            this.jdlFormThemSanPham.pack();
            this.jdlFormThemSanPham.setLocationRelativeTo(null);
            this.jdlFormThemSanPham.setVisible(true);

            try {
                this.listSanPham = repoSanPhamChiTiet.getAllDataSanPham();
            } catch (Exception ex) {
                Logger.getLogger(FormQuanLyKhuyenMaiTheoSanPham.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.fillTableSanPham(listSanPham);
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        int choice = JOptionPane.showConfirmDialog(this, "Bạn có sửa không", "Thông báo", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            String ma = this.jlbMa.getText();
            try {
                this.repo.suaKhuyenMai(readForm(ma));
                this.listKhuyenMais = this.repo.getData(currentPage);
                this.fillTableKhuyenMai(this.listKhuyenMais);
                this.fillTableToForm(ma, this.listKhuyenMais);
                JOptionPane.showMessageDialog(this, "Sửa thành công");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Sửa thất bại");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Đã hủy sửa");
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        int choice = JOptionPane.showConfirmDialog(this, "Bạn muốn tìm không", "Thông báo", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            try {
                this.listKhuyenMais = this.repo.timKiemTheoNhieuTruong(readFormSearch());
                this.fillTableKhuyenMai(this.listKhuyenMais);
                JOptionPane.showMessageDialog(this, "tìm thành công");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "tìm thất bại");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Đã hủy tìm");
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnTraiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraiActionPerformed
        if (currentPage > 1) {
            currentPage--;
            try {
                this.listKhuyenMais
                        = this.repo.getData(currentPage);
                this.fillTableKhuyenMai(listKhuyenMais);
            } catch (Exception ex) {
                Logger.getLogger(FormQuanLyKhuyenMaiTheoSanPham.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnTraiActionPerformed

    private void btnPhaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhaiActionPerformed
        try {
            currentPage++;
            try {
                this.listKhuyenMais
                        = this.repo.getData(currentPage);
                this.fillTableKhuyenMai(listKhuyenMais);
            } catch (Exception ex) {
                Logger.getLogger(FormQuanLyKhuyenMaiTheoSanPham.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnPhaiActionPerformed

    private void btnChonTatCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonTatCaActionPerformed
        int index = this.listSanPhamChiTiet.size();
        this.chonTatCaSanPham = !this.chonTatCaSanPham;

        if (index > 0) {
            if (this.chonTatCaSanPham == true) {
                System.out.println(index);
                for (int i = 0; i < index; i++) {
                    this.tblDanhSachSanPhamChiTiet.setValueAt(true, i, 7);
                }
            } else {
                System.out.println(index);
                for (int i = 0; i < index; i++) {
                    this.tblDanhSachSanPhamChiTiet.setValueAt(false, i, 7);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "database trống");
        }


    }//GEN-LAST:event_btnChonTatCaActionPerformed

    private void txtTenPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtTenPropertyChange
        String ten = txtTen.getText().trim();
        if (ten != null) {
            txtTenApDung.setText(ten);
        }
    }//GEN-LAST:event_txtTenPropertyChange

    private void btnApDungSanPhamChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApDungSanPhamChiTietActionPerformed
        int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm", "Thông báo", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            List<Long> danhSachID = this.serviceSanPham.danhSachIDSanPham(listSanPhamChiTiet);
            if (danhSachID != null) {
                String ma = jlbMaApDung.getText();
                if (ma != null) {
                    try {
                        try {
                            if (readForm(ma) != null) {
                                this.repo.themKhuyenMai(readForm(ma));
                            } else {
                                JOptionPane.showMessageDialog(this, "Điền form");
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(this, "Áp dụng thất bại");
                        }
                        this.listKhuyenMais = this.repo.getData(currentPage);
                        this.fillTableKhuyenMai(listKhuyenMais);
                        this.fillTableToForm(ma, listKhuyenMais);
                        Long idKM = repo.findIdKhuyenMaiTheoSanPhamMaKhuyenMai(ma);
                        for (Long id : danhSachID) {
                            repoSanPhamKhuyenMai.themKhuyenMaiSanPham(idKM, id);
                        }
                        JOptionPane.showMessageDialog(this, "Áp dụng Thành công");
                        this.jdlFormThemSanPham.dispose();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Áp dụng thất bại");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Mã không được null");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui long chọn sản phẩm chi tiết áp dụng");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Đã hủy Áp dụng");
        }
    }//GEN-LAST:event_btnApDungSanPhamChiTietActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApDungSanPhamChiTiet;
    private javax.swing.JButton btnChonTatCa;
    private javax.swing.JButton btnDonForm;
    private javax.swing.JButton btnDonFormTimKiem;
    private javax.swing.JButton btnPhai;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnTimKiemSanPham;
    private javax.swing.JButton btnTimKiemSanPhamChiTiet;
    private javax.swing.JButton btnTrai;
    private javax.swing.JButton btnXoaMem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private com.toedter.calendar.JDateChooser jdcNgayBatDau;
    private com.toedter.calendar.JDateChooser jdcNgayBatDauTimKiem;
    private com.toedter.calendar.JDateChooser jdcNgayKetThuc;
    private com.toedter.calendar.JDateChooser jdcNgayKetThucTimKiem;
    private javax.swing.JDialog jdlFormThemSanPham;
    private javax.swing.JLabel jlbGiaTri;
    private javax.swing.JLabel jlbMa;
    private javax.swing.JLabel jlbMaApDung;
    private javax.swing.JLabel jlbNgayBatDau;
    private javax.swing.JLabel jlbNgayBatDauApDung;
    private javax.swing.JLabel jlbNgayBatDauTimKiem;
    private javax.swing.JLabel jlbNgayKetThuc;
    private javax.swing.JLabel jlbNgayKetThucApDung;
    private javax.swing.JLabel jlbNgayKetThucTimKiem;
    private javax.swing.JLabel jlbNgaySua;
    private javax.swing.JLabel jlbNgayTao;
    private javax.swing.JLabel jlbTrangThaiApDung;
    private javax.swing.JPanel jpnFormThemSanPham;
    private javax.swing.JSlider jslGiaTri;
    private javax.swing.JLabel lb;
    private javax.swing.JRadioButton rdHoatDong;
    private javax.swing.JRadioButton rdHoatDongTimKiem;
    private javax.swing.JRadioButton rdNgungHoatDong;
    private javax.swing.JRadioButton rdNgungHoatDongTimKiem;
    private javax.swing.JRadioButton rdTatCaTimKiem;
    private javax.swing.ButtonGroup rdgrTrangThaiForm;
    private javax.swing.ButtonGroup rdgrTrangThaiTimKiem;
    private javax.swing.JTable tblDanhSachKhuyenMai;
    private javax.swing.JTable tblDanhSachSanPham;
    private javax.swing.JTable tblDanhSachSanPhamChiTiet;
    private javax.swing.JTextField txtGiaApDung;
    private javax.swing.JTextField txtGiaTriTimKiem;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTenApDung;
    private javax.swing.JTextField txtTimKiemMaOrTen;
    private javax.swing.JTextField txtTimKiemSanPham;
    private javax.swing.JTextField txtTimKiemSanPhamCT;
    // End of variables declaration//GEN-END:variables

    @Override
    public void tableChanged(TableModelEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
