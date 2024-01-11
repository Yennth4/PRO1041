package com.poly.form.hoadon.view;

import com.formdev.flatlaf.FlatClientProperties;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.poly.form.giaohang.entity.PhieuGiaoHang;
import com.poly.form.giaohang.service.PhieuGiaoHangService;
import com.poly.form.giaohang.service.impl.PhieuGiaoHangServiceImpl;
import com.poly.form.hoadon.entity.BienTheSearch;
import com.poly.form.hoadon.entity.GioHang;
import com.poly.form.hoadon.entity.HoaDon;
import com.poly.form.hoadon.entity.HoaDonDTO;
import com.poly.form.hoadon.entity.KhachHangSearch;
import com.poly.form.hoadon.entity.VoucherSearch;
import com.poly.form.hoadon.service.HoaDonService;
import com.poly.form.khachhang.entity.KhachHangHung;
import com.poly.form.khachhang.service.KhachHangService;
import com.poly.form.thuoctinh.entity.ThuocTinhMau;
import static com.poly.util.ph31848.Validate.checkEmpty;
import static com.poly.util.ph31848.Validate.checkNumber;
import static com.poly.util.ph31848.Validate.checkFloat;
import static com.poly.util.ph31848.Validate.checkChar;
import com.poly.form.thuoctinh.entity.ThuocTinhMauDTO;
import com.poly.form.thuoctinh.service.ThuocTinhMauService;
import static com.poly.util.ph31848.ConvertDateToLong.getLongTimeHienTai;
import static com.poly.util.ph31848.MaRandom.renderMa;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import raven.toast.Notifications;

public class FormBanHang extends javax.swing.JPanel implements Runnable, ThreadFactory {

    private DefaultTableModel dtmHoaDon;
    private DefaultTableModel dtmGioHang;
    private DefaultTableModel dtmBienThe;

    private HoaDonService serviceHoaDon;
    private ThuocTinhMauService serviceMau;
    KhachHangService khachHangService = new KhachHangService();

    private List<HoaDonDTO> listHoaDon;
    private List<GioHang> listGioHang;
    private List<BienTheSearch> listBienThe;
    private List<ThuocTinhMauDTO> listMau;
    private KhachHangSearch thongTinKhachHang;
    private VoucherSearch thongTinVoucher;

    private PhieuGiaoHangService phieuGiaoService = new PhieuGiaoHangServiceImpl();

    // Variable time current now
    private Long timeToday = 0l;

    //Webcam
    private boolean isThreadRuning = true;
    private WebcamPanel panel = null;
    private Webcam webcam = null;
    private final Executor executor = Executors.newSingleThreadExecutor(this);

    public FormBanHang() {
        initComponents();
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        timeToday = getLongTimeHienTai();
        dtmBienThe = (DefaultTableModel) tblBienThe.getModel();
        dtmHoaDon = (DefaultTableModel) tblHoaDon.getModel();
        dtmGioHang = (DefaultTableModel) tblGioHang.getModel();
        dtmBienThe = (DefaultTableModel) tblBienThe.getModel();
        serviceHoaDon = new HoaDonService();
        serviceMau = new ThuocTinhMauService();
        closeThreadAndWebcam();
        setDefaulView();
        initWebcam();
    }

    public void closeThreadAndWebcam() {
        if (webcam != null) {
            if (webcam.isOpen()) {
                webcam.close();
            }
            isThreadRuning = false;
        }

    }

    public boolean scanVoucher(Float tongTienThanhToan) {
        thongTinVoucher = serviceHoaDon.getVoucherIsBest(tongTienThanhToan);
        if (thongTinVoucher != null) {
            return true;
        }
        return false;
    }

    private void initWebcam() {

        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0); //0 is default webcam
        // tắt webcam để setSize
        if (webcam != null) {
            if (webcam.isOpen()) {
                webcam.close();
            }
        }
        webcam.setViewSize(size);

        // bật lại
        if (webcam != null) {
            if (!webcam.isOpen()) {
                webcam.open();
            }
        }
        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);

        pnlWebcam.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 140));

        executor.execute(this);

    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Result result = null;
            BufferedImage image = null;

            if (webcam.isOpen() && isThreadRuning) {
                if ((image = webcam.getImage()) == null) {
                    continue;
                }
            }
            if (image == null) {
                return;
            }
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            try {
                result = new MultiFormatReader().decode(bitmap);
            } catch (NotFoundException e) {

            }

            if (result != null) {
                String ma = result.getText();
                themBienTheVaoHoaDonQRCode(ma);
            }
        } while (true);
    }

    public void themBienTheVaoHoaDonQRCode(String ma) {
        Long idBT = serviceHoaDon.getIDBienTheByMa(ma);
        if (cboTrangThaiHoaDon.getSelectedIndex() == 0) {
            if (tblHoaDon.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(this, "Chọn một hóa đơn để quét mã QR");
                return;
            }
            if (idBT != null) {
                Integer soLuongBienThe = serviceHoaDon.getSoLuongBienTheById(idBT);
                Float giaBanBienThe = serviceHoaDon.getGiaBanBienTheById(idBT);
                String soLuongNhap = JOptionPane.showInputDialog(this, "Nhập số lượng\nSố lượng còn lại: " + soLuongBienThe) + "";
                if (checkEmpty(soLuongNhap)) {
                    return;
                }
                if (!checkNumber(soLuongNhap) && !soLuongNhap.equals("")) {
                    JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ");
                    return;
                }
                HoaDonDTO hd = listHoaDon.get(tblHoaDon.getSelectedRow());
                Long idGH = serviceHoaDon.isExistBienTheHoaDon(hd.getIdHoaDon(), idBT);

                ///
                if (idGH == null) {

                    // chưa có trong giỏ >> insert giỏ hàng và trừ số lượng biến thể
                    if (Integer.valueOf(soLuongNhap) > soLuongBienThe) {
                        JOptionPane.showMessageDialog(this, "Số lượng tồn kho không đủ");
                        return;
                    }
                    serviceHoaDon.insertBienTheToHoaDon(hd.getIdHoaDon(), idBT, giaBanBienThe, Integer.valueOf(soLuongNhap));
                    serviceHoaDon.updateSoLuongBienThe(idBT, soLuongBienThe - Integer.valueOf(soLuongNhap));
                } else {
                    // đã có trong giỏ >> so sánh 
                    Integer soLuongBienTheTrongGioHang = serviceHoaDon.getSoLuongBienTheTrongGioHangById(idGH);
                    if (Integer.valueOf(soLuongNhap) > soLuongBienThe) {
                        JOptionPane.showMessageDialog(this, "Số lượng tồn kho không đủ");
                        return;
                    }
                    serviceHoaDon.updateBienTheToHoaDon(idGH, soLuongBienTheTrongGioHang + Integer.valueOf(soLuongNhap));
                    serviceHoaDon.updateSoLuongBienThe(idBT, soLuongBienThe - Integer.valueOf(soLuongNhap));
                }

                // load lại 2 bảng giỏ hàng và biến thể
                reloadUpdateGioHang(hd.getIdHoaDon());
                ///
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy biến thể SP với mã " + ma);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Chỉ áp dụng với hóa đơn chờ thanh toán");
        }
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }

    //Webcam
    public void setDefaulView() {
        cboHinhThucGiaoHang.setSelectedIndex(2);
        cboTrangThaiHoaDon.setSelectedIndex(7);
        txtMaHoaDon.setEnabled(false);
        txtTenNhanVien.setEnabled(false);
        txtTongTien.setEnabled(false);
        txtTongTienGiam.setEnabled(false);
        txtTongTienThanhToan.setEnabled(false);
        txtTienKhachChuyenKhoan.setEnabled(false);
        txtSDTKhachHang.setEnabled(false);
        txtHoTenKhachHang.setEnabled(false);

        loadComboBoxMau();
        listBienThe = serviceHoaDon.searchSanPham(null, null);
        showTableBienThe(listBienThe);
    }

    public void resertTableHoaDon(String type) {
        Long timeHienTai = getLongTimeHienTai();
        Integer ht = cboHinhThucGiaoHang.getSelectedIndex();
        if (ht == 2) {
            ht = null;
        }
        Integer tt = cboTrangThaiHoaDon.getSelectedIndex();
        if (tt == 7) {
            tt = null;
        }
        if (type.contains("all")) {
            listHoaDon = serviceHoaDon.getAll();
        } else if (type.contains("search")) {
            listHoaDon = serviceHoaDon.getAllSearch(ht, tt);
        }
        showTableHoaDon(listHoaDon);

    }

    public void resertTableBienThe() {
        String key = txtSearchSanPham.getText().trim();
        if (key.equals("")) {
            key = null;
        }
        String mau = cboMau.getSelectedItem() + "";
        if (mau.equalsIgnoreCase("Tất cả")) {
            mau = null;
        }
        listBienThe = serviceHoaDon.searchSanPham(key, mau);
        showTableBienThe(listBienThe);
    }

    public void showTableHoaDon(List<HoaDonDTO> list) {
        dtmHoaDon.setRowCount(0);
        for (HoaDonDTO hd : list) {
            dtmHoaDon.addRow(hd.toDataRow());
        }
    }

    public void showTableBienThe(List<BienTheSearch> list) {
        dtmBienThe.setRowCount(0);
        for (BienTheSearch bt : list) {
            dtmBienThe.addRow(bt.toDataRow());
        }
    }

    public void loadComboBoxMau() {
        cboMau.removeAllItems();
        listMau = serviceMau.getAll();
        cboMau.addItem("Tất cả");
        for (ThuocTinhMauDTO mau : listMau) {
            cboMau.addItem(mau.getTenMau());
        }
        cboMau.setSelectedIndex(0);
    }

    public void fillData(int index) {
        int indexTrangThaiHD = cboTrangThaiHoaDon.getSelectedIndex();
        HoaDonDTO hd = listHoaDon.get(index);
        txtMaHoaDon.setText(hd.getMaHoaDon());
        txtTenNhanVien.setText(hd.getTenNhanVien());
        txtTongTien.setText(hd.getTongTienHoaDon() + "");
        txtTongTienGiam.setText(hd.getTongTienSauKhuyenMai() + "");
        txtTongTienThanhToan.setText(hd.getTongTienPhaiTra() + "");
        txtTienKhachDua.setText(hd.getTienMatKhachTra() + "");
        txtTienKhachChuyenKhoan.setText(hd.getTongTienPhaiTra() - hd.getTienMatKhachTra() + "");
        txtSDTKhachHang.setText(hd.getMaKhachHang());
        txtHoTenKhachHang.setText(hd.getHoTenKhachHang());
        // nếu hóa đơn chờ thanh toán thì tính auto bằng SQL
        if (indexTrangThaiHD == 0) {
            reloadTongTienHoaDon(hd.getIdHoaDon(), timeToday);
        }
    }

    public void showTableGioHang(List<GioHang> list) {
        dtmGioHang.setRowCount(0);
        for (GioHang giohang : list) {
            dtmGioHang.addRow(giohang.toDataRow());
        }
    }

    public void setKhachHangForHoaDon(Long id) {
        thongTinKhachHang = serviceHoaDon.findByID(id);
        if (thongTinKhachHang != null) {
            txtSDTKhachHang.setText(thongTinKhachHang.getSdt());
            txtHoTenKhachHang.setText(thongTinKhachHang.getHoTen());
        }
    }

    public void reloadTongTienHoaDon(Long idHoaDon, Long time) {
        BigDecimal tongTien = new BigDecimal(serviceHoaDon.getTongTienHoaDonById(idHoaDon));
        BigDecimal tongTienGiam = new BigDecimal(serviceHoaDon.getTienGiamHoaDonById(idHoaDon, time));
        BigDecimal tongThanhToan = new BigDecimal(serviceHoaDon.getTienThanhToanHoaDonById(idHoaDon, time));

        txtTongTien.setText(tongTien + "");
        txtTongTienGiam.setText(tongTienGiam + "");
        txtTongTienThanhToan.setText(tongThanhToan + "");
        txtTienKhachDua.setText(txtTongTienThanhToan.getText());
    }

    public void reloadUpdateGioHang(Long id) {
        resertTableBienThe();
        timeToday = getLongTimeHienTai();
        listGioHang = serviceHoaDon.getAllBienTheByIdHoaDon(id, timeToday);
        System.out.println("Giỏ hàng có: " + listGioHang.size());
        showTableGioHang(listGioHang);
        reloadTongTienHoaDon(id, timeToday);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        cboTrangThaiHoaDon = new javax.swing.JComboBox<>();
        cboHinhThucGiaoHang = new javax.swing.JComboBox<>();
        btnThem = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        pnlWebcam = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        txtSDTKhachHang = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtHoTenKhachHang = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        btnChonKhachHang = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txtMaHoaDon = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtTenNhanVien = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtTongTienGiam = new javax.swing.JTextField();
        txtTongTienThanhToan = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lbVoucher = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtTienKhachDua = new javax.swing.JTextField();
        cbxHTTT = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        txtTienKhachChuyenKhoan = new javax.swing.JTextField();
        btnLuuHoaDon = new javax.swing.JButton();
        txtGhiChu = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        cbxChonHinhThucMua = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        btnClearGioHang = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        txtSearchSanPham = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cboMau = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBienThe = new javax.swing.JTable();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("Bán hàng");
        add(lb, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 540, -1));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 16))); // NOI18N

        tblHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HĐ", "Nhân viên", "Khách hàng", "Hình thức", "Trạng thái", "Tổng SP"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.setSelectionBackground(new java.awt.Color(235, 235, 235));
        tblHoaDon.getTableHeader().setReorderingAllowed(false);
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseEntered(evt);
            }
        });
        jScrollPane3.setViewportView(tblHoaDon);

        cboTrangThaiHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboTrangThaiHoaDon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chờ thanh toán", "Đã thanh toán", "Đã hủy", "Chờ giao hàng", "Đang giao", "Đã giao", "Khách hẹn lại", "Tất cả" }));
        cboTrangThaiHoaDon.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTrangThaiHoaDonItemStateChanged(evt);
            }
        });
        cboTrangThaiHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTrangThaiHoaDonActionPerformed(evt);
            }
        });

        cboHinhThucGiaoHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboHinhThucGiaoHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tại quầy", "Đặt hàng", "Tất cả" }));
        cboHinhThucGiaoHang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboHinhThucGiaoHangItemStateChanged(evt);
            }
        });
        cboHinhThucGiaoHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboHinhThucGiaoHangActionPerformed(evt);
            }
        });

        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThem.setText("Tạo");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cboTrangThaiHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboHinhThucGiaoHang, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboTrangThaiHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboHinhThucGiaoHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
        );

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 26, 490, -1));

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quét mã vạch sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12))); // NOI18N

        pnlWebcam.setBackground(new java.awt.Color(255, 255, 255));
        pnlWebcam.setMaximumSize(new java.awt.Dimension(50, 50));
        pnlWebcam.setMinimumSize(new java.awt.Dimension(50, 50));
        pnlWebcam.setPreferredSize(new java.awt.Dimension(50, 50));
        pnlWebcam.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlWebcam, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlWebcam, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(139, 139, 139))
        );

        add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, -1, 185));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đơn hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 16))); // NOI18N

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 16))); // NOI18N

        txtSDTKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSDTKhachHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));
        txtSDTKhachHang.setSelectionColor(new java.awt.Color(140, 140, 140));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel14.setText("SĐT:");

        txtHoTenKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtHoTenKhachHang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));
        txtHoTenKhachHang.setSelectionColor(new java.awt.Color(140, 140, 140));

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel17.setText("Tên KH:");

        btnChonKhachHang.setText("Chọn");
        btnChonKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonKhachHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSDTKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                    .addComponent(txtHoTenKhachHang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(btnChonKhachHang)
                .addGap(20, 20, 20))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14)
                            .addComponent(txtSDTKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtHoTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnChonKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 17))); // NOI18N

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel18.setText("Mã HD:");

        txtMaHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaHoaDon.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));
        txtMaHoaDon.setSelectionColor(new java.awt.Color(140, 140, 140));

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel19.setText("Tên nhân viên:");

        txtTenNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTenNhanVien.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));
        txtTenNhanVien.setSelectionColor(new java.awt.Color(140, 140, 140));

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel20.setText("Tổng tiền:");

        txtTongTien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTongTien.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));
        txtTongTien.setSelectionColor(new java.awt.Color(140, 140, 140));

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel21.setText("Giảm giá khuyến mại:");

        txtTongTienGiam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTongTienGiam.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));
        txtTongTienGiam.setSelectionColor(new java.awt.Color(140, 140, 140));

        txtTongTienThanhToan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTongTienThanhToan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));
        txtTongTienThanhToan.setSelectionColor(new java.awt.Color(140, 140, 140));

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel22.setText("Thanh toán (Số):");

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel23.setText("Voucher tự động:");

        lbVoucher.setText("0đ");

        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel24.setText("Hình thức thanh toán:");

        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel25.setText("Tiền khách đưa:");

        txtTienKhachDua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTienKhachDua.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));
        txtTienKhachDua.setSelectionColor(new java.awt.Color(140, 140, 140));
        txtTienKhachDua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienKhachDuaKeyReleased(evt);
            }
        });

        cbxHTTT.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbxHTTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "Chuyển khoản", "Kết hợp" }));
        cbxHTTT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxHTTTItemStateChanged(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel26.setText("Tiền khách CK:");

        txtTienKhachChuyenKhoan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTienKhachChuyenKhoan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 204, 255)));
        txtTienKhachChuyenKhoan.setSelectionColor(new java.awt.Color(140, 140, 140));

        btnLuuHoaDon.setText("Lưu Hóa Đơn");
        btnLuuHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuHoaDonActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel28.setText("Ghi chú:");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(83, 83, 83)
                        .addComponent(txtTongTien))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxHTTT, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbVoucher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTienKhachDua))
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel9Layout.createSequentialGroup()
                                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel9Layout.createSequentialGroup()
                                            .addComponent(jLabel22)
                                            .addGap(10, 10, 10)
                                            .addComponent(txtTongTienThanhToan)
                                            .addGap(6, 6, 6))
                                        .addGroup(jPanel9Layout.createSequentialGroup()
                                            .addComponent(jLabel18)
                                            .addGap(98, 98, 98)
                                            .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel9Layout.createSequentialGroup()
                                            .addComponent(jLabel19)
                                            .addGap(53, 53, 53)
                                            .addComponent(txtTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel9Layout.createSequentialGroup()
                                            .addComponent(jLabel21)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtTongTienGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(jPanel9Layout.createSequentialGroup()
                                    .addComponent(jLabel26)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTienKhachChuyenKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addGap(6, 6, 6))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(txtGhiChu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLuuHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18)
                    .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(txtTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(txtTongTienGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(txtTongTienThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(lbVoucher))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(cbxHTTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txtTienKhachChuyenKhoan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLuuHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48))
        );

        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel27.setText("Hình thức mua:");

        cbxChonHinhThucMua.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbxChonHinhThucMua.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tại quầy", "Đặt hàng" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel27)
                        .addGap(18, 18, 18)
                        .addComponent(cbxChonHinhThucMua, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(cbxChonHinhThucMua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thông tin đặt hàng", jPanel4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 692, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 10, 400, 700));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giỏ hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        tblGioHang.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "Màu", "Số lượng", "Giá bán", "Giảm giá", "Thành tiền"
            }
        ));
        tblGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGioHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblGioHang);

        btnClearGioHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnClearGioHang.setForeground(new java.awt.Color(255, 0, 51));
        btnClearGioHang.setText("Xóa sản phẩm trong giỏ");
        btnClearGioHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearGioHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnClearGioHang)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(btnClearGioHang, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 223, 640, 270));

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        txtSearchSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSearchSanPham.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchSanPhamKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Tìm kiếm");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Màu sắc:");

        cboMau.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboMau.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboMau.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboMauItemStateChanged(evt);
            }
        });

        tblBienThe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "Màu", "Tồn kho", "Giá bán", "Giảm giá"
            }
        ));
        tblBienThe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBienTheMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblBienThe);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtSearchSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboMau, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jScrollPane2)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboMau, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearchSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, 630, 210));
    }// </editor-fold>//GEN-END:initComponents

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        int index = tblHoaDon.getSelectedRow();
        fillData(index);
        HoaDonDTO hd = listHoaDon.get(index);
        Long idHoaDon = hd.getIdHoaDon();
        timeToday = getLongTimeHienTai();
        listGioHang = serviceHoaDon.getAllBienTheByIdHoaDon(idHoaDon, timeToday);
        showTableGioHang(listGioHang);

    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void cboTrangThaiHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTrangThaiHoaDonActionPerformed

    }//GEN-LAST:event_cboTrangThaiHoaDonActionPerformed

    private void cboHinhThucGiaoHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboHinhThucGiaoHangActionPerformed

    }//GEN-LAST:event_cboHinhThucGiaoHangActionPerformed

    private void cboTrangThaiHoaDonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTrangThaiHoaDonItemStateChanged
        if (cboTrangThaiHoaDon.getSelectedIndex() == 7 && cboHinhThucGiaoHang.getSelectedIndex() == 2) {
            resertTableHoaDon("all");
        } else
            resertTableHoaDon("search");
    }//GEN-LAST:event_cboTrangThaiHoaDonItemStateChanged

    private void cboHinhThucGiaoHangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboHinhThucGiaoHangItemStateChanged
        if (cboTrangThaiHoaDon.getSelectedIndex() == 7 && cboHinhThucGiaoHang.getSelectedIndex() == 2) {
            resertTableHoaDon("all");
        } else
            resertTableHoaDon("search");
    }//GEN-LAST:event_cboHinhThucGiaoHangItemStateChanged

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        String tenKH = txtHoTenKhachHang.getText();
        String sdtKH = txtSDTKhachHang.getText();
        if (checkEmpty(sdtKH) || checkEmpty(tenKH)) {
            JOptionPane.showMessageDialog(this, "Chọn khách hàng trước khi tạo hóa đơn");
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "Tạo hóa đơn mới?", "Xác nhận", JOptionPane.YES_NO_OPTION) == 0) {
            String maHD = "";
            do {
                maHD = "HD" + renderMa();
            } while (serviceHoaDon.isExistMa(maHD));
            HoaDon hd = new HoaDon(1L, maHD, 0);
            Long idKH = 1l;
            if (thongTinKhachHang != null) {
                idKH = thongTinKhachHang.getIdKhachHang();
            }
            serviceHoaDon.insertHoaDon(hd, idKH); // mặc định insert DB khách lẻ đầu tiên
            cboTrangThaiHoaDon.setSelectedIndex(0);
            cboHinhThucGiaoHang.setSelectedIndex(2);
            if (listGioHang != null) {
                listGioHang.clear();
                showTableGioHang(listGioHang);
            }

        }

        //load lại hóa đơn
        if (cboTrangThaiHoaDon.getSelectedIndex() == 7 && cboHinhThucGiaoHang.getSelectedIndex() == 2) {
            resertTableHoaDon("all");
        } else
            resertTableHoaDon("search");
    }//GEN-LAST:event_btnThemActionPerformed

    private void txtSearchSanPhamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchSanPhamKeyReleased
        resertTableBienThe();
    }//GEN-LAST:event_txtSearchSanPhamKeyReleased

    private void cboMauItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboMauItemStateChanged
        resertTableBienThe();
    }//GEN-LAST:event_cboMauItemStateChanged

    private void btnChonKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonKhachHangActionPerformed
        new FormKhachHangSearch(this).setVisible(true);
    }//GEN-LAST:event_btnChonKhachHangActionPerformed

    private void cbxHTTTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxHTTTItemStateChanged
        int indexSelectThanhToan = cbxHTTT.getSelectedIndex();
        switch (indexSelectThanhToan) {

            case 0:
                txtTienKhachDua.setText(txtTongTienThanhToan.getText());
                txtTienKhachChuyenKhoan.setText("0");
                txtTienKhachDua.setEnabled(false);
                break;
            case 1:
                txtTienKhachDua.setText("0");
                txtTienKhachDua.setEnabled(false);
                txtTienKhachChuyenKhoan.setText(txtTongTienThanhToan.getText());
                break;
            case 2:
                txtTienKhachDua.setEnabled(true);
                break;
            default:
                break;
        }
    }//GEN-LAST:event_cbxHTTTItemStateChanged

    private void txtTienKhachDuaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachDuaKeyReleased
        String tienDua = txtTienKhachDua.getText().trim();
        String tongThanhToan = txtTongTienThanhToan.getText();
        if (checkFloat(tienDua) && !tienDua.isEmpty()) {
            Float tong = Float.valueOf(tongThanhToan);
            Float tienKhachDua = Float.valueOf(tienDua);
            if (tienKhachDua <= tong) {
                txtTienKhachChuyenKhoan.setText((tong - tienKhachDua) + "");
            } else {
                Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Nhập tiền khách đưa không lớn hơn tổng tiền");
            }

        } else {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Sai định dạng tiền");

        }
    }//GEN-LAST:event_txtTienKhachDuaKeyReleased

    private void btnLuuHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuHoaDonActionPerformed
        int indexHoaDon = tblHoaDon.getSelectedRow();
        if (indexHoaDon == -1) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Chọn hóa đơn cần lưu");
            return;
        }
        if (!checkFloat(txtTienKhachDua.getText())) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Tiền khách đưa không hợp lệ");
            return;
        }
        if (checkEmpty(txtSDTKhachHang.getText())) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Chọn khách hàng trước khi lưu hóa đơn");
            return;
        }
        Long idHoaDon = listHoaDon.get(indexHoaDon).getIdHoaDon();
        String ghiChu = "" + txtGhiChu.getText();
        Float tongTien = Float.valueOf(txtTongTien.getText());
        Float tongTienGiam = Float.valueOf(txtTongTienGiam.getText());
        Long idKhachHang = 1l;
        if (thongTinKhachHang != null) {
            idKhachHang = thongTinKhachHang.getIdKhachHang();
        }
        Float soTienGiam = 0f; // voucher
        Float tongTienThanhToan = Float.valueOf(txtTongTienThanhToan.getText());
        Integer trangThai = 1;
        Float tienMatKhachTra = Float.valueOf(txtTienKhachDua.getText());
        Integer hinhThucThanhToan = cbxHTTT.getSelectedIndex();
        Integer hinhThucMua = 1;
        String maHoaDon = txtMaHoaDon.getText();
        if (cbxChonHinhThucMua.getSelectedIndex() == 1) {
            hinhThucMua = 2;
            trangThai = 3;
            KhachHangHung kh = khachHangService.getKhachHangById2(idKhachHang);
            PhieuGiaoHang pgh = new PhieuGiaoHang(maHoaDon, kh.getHoTen(), kh.getSdt(), null, null, kh.getDiaChi(), null, null, ghiChu, 5, null);
            int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn in luôn phiếu giao", "Thông báo", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                try {
                    this.phieuGiaoService.themPhieu(pgh);
                    JOptionPane.showMessageDialog(this, "Thêm phiếu giao thành công");
                } catch (Exception ex) {
                    Logger.getLogger(FormBanHang.class.getName()).log(Level.SEVERE, null, ex);
                }

                // code cũ
                HoaDon hd = new HoaDon(
                        idHoaDon,
                        ghiChu,
                        tongTien,
                        tongTienGiam,
                        1l,
                        idKhachHang,
                        soTienGiam,
                        tongTienThanhToan,
                        maHoaDon,
                        trangThai,
                        tienMatKhachTra,
                        hinhThucThanhToan,
                        hinhThucMua);

                serviceHoaDon.updateHoaDon(hd);
                KhachHangHung kh1 = khachHangService.getKhachHangById2(idKhachHang);
                java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
                khachHangService.CreateLihSuThanhToan(maHoaDon, idKhachHang, currentDate, tongTien, kh1.getCapBac());

                Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Lưu hóa đơn thành công");
                cboTrangThaiHoaDon.setSelectedIndex(1);

            } else {
                JOptionPane.showMessageDialog(this, "Đã hủy thêm phiếu giao/ thêm hóa đơn thành công");
                HoaDon hd = new HoaDon(
                        idHoaDon,
                        ghiChu,
                        tongTien,
                        tongTienGiam,
                        1l,
                        idKhachHang,
                        soTienGiam,
                        tongTienThanhToan,
                        maHoaDon,
                        trangThai,
                        tienMatKhachTra,
                        hinhThucThanhToan,
                        hinhThucMua);

                serviceHoaDon.updateHoaDon(hd);
                KhachHangHung kh1 = khachHangService.getKhachHangById2(idKhachHang);
                java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
                khachHangService.CreateLihSuThanhToan(maHoaDon, idKhachHang, currentDate, tongTien, kh1.getCapBac());

                Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Lưu hóa đơn thành công");
                cboTrangThaiHoaDon.setSelectedIndex(1);
            }
        } else {
            HoaDon hd = new HoaDon(
                    idHoaDon,
                    ghiChu,
                    tongTien,
                    tongTienGiam,
                    1l,
                    idKhachHang,
                    soTienGiam,
                    tongTienThanhToan,
                    maHoaDon,
                    trangThai,
                    tienMatKhachTra,
                    hinhThucThanhToan,
                    hinhThucMua);
            if (JOptionPane.showConfirmDialog(this, "Xác nhận lưu hóa đơn\n", "Lưu hóa đơn", JOptionPane.YES_NO_OPTION) == 0) {
                serviceHoaDon.updateHoaDon(hd);

                // Thêm lịch sử giao dịch
                System.out.println(idKhachHang);
                KhachHangHung kh = khachHangService.getKhachHangById2(idKhachHang);
                java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
                khachHangService.CreateLihSuThanhToan(maHoaDon, idKhachHang, currentDate, tongTien, kh.getCapBac());

                Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Lưu hóa đơn thành công");
                cboTrangThaiHoaDon.setSelectedIndex(1);
            }
        }
    }//GEN-LAST:event_btnLuuHoaDonActionPerformed

    private void tblBienTheMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBienTheMouseClicked
        if (cboTrangThaiHoaDon.getSelectedIndex() == 0) {
            int indexHD = tblHoaDon.getSelectedRow();
            int indexBT = tblBienThe.getSelectedRow();

            if (indexHD != -1) {
                HoaDonDTO hd = listHoaDon.get(indexHD);
                BienTheSearch bt = listBienThe.get(indexBT);
                // Kiểm tra giá trị nhập input
                String soLuongNhap = JOptionPane.showInputDialog(this, "Nhập số lượng thêm vào giỏ hàng:") + "";
                soLuongNhap = soLuongNhap + "";
                if (checkEmpty(soLuongNhap) || Integer.valueOf(soLuongNhap) == 0) {
                    return;
                }
                if (!checkNumber(soLuongNhap) && !soLuongNhap.equals("")) {
                    JOptionPane.showMessageDialog(this, "Số lượng cần là số nguyên dương");
                    return;
                }

                Long idGH = serviceHoaDon.isExistBienTheHoaDon(hd.getIdHoaDon(), bt.getIdBienThe());
                if (idGH == null) {
                    // chưa có trong giỏ >> insert giỏ hàng và trừ số lượng biến thể
                    if (Integer.valueOf(soLuongNhap) > bt.getSoLuong()) {
                        JOptionPane.showMessageDialog(this, "Số lượng tồn kho không đủ");
                        return;
                    }
                    serviceHoaDon.insertBienTheToHoaDon(hd.getIdHoaDon(), bt.getIdBienThe(), bt.getGiaBan(), Integer.valueOf(soLuongNhap));
                    serviceHoaDon.updateSoLuongBienThe(bt.getIdBienThe(), bt.getSoLuong() - Integer.valueOf(soLuongNhap));
                } else {
                    // đã có trong giỏ >> so sánh 
                    Integer soLuongBienTheTrongGioHang = serviceHoaDon.getSoLuongBienTheTrongGioHangById(idGH);
                    if (Integer.valueOf(soLuongNhap) > bt.getSoLuong()) {
                        JOptionPane.showMessageDialog(this, "Số lượng tồn kho không đủ");
                        return;
                    }
                    serviceHoaDon.updateBienTheToHoaDon(idGH, soLuongBienTheTrongGioHang + Integer.valueOf(soLuongNhap));
                    serviceHoaDon.updateSoLuongBienThe(bt.getIdBienThe(), bt.getSoLuong() - Integer.valueOf(soLuongNhap));
                }

                // load lại 2 bảng giỏ hàng và biến thể
                reloadUpdateGioHang(hd.getIdHoaDon());
            } else {
                JOptionPane.showMessageDialog(this, "Chọn hóa đơn trước");
            }
        }


    }//GEN-LAST:event_tblBienTheMouseClicked

    private void tblGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGioHangMouseClicked
        try {
            if (cboTrangThaiHoaDon.getSelectedIndex() == 0) {
                int indexHD = tblHoaDon.getSelectedRow();
                int indexGH = tblGioHang.getSelectedRow();
                HoaDonDTO hd = listHoaDon.get(indexHD);
                GioHang gh = listGioHang.get(indexGH);
                String soLuongNhap = JOptionPane.showInputDialog(this, "Nhập số lượng") + "";
                // check số lượng nhập
                if (checkEmpty(soLuongNhap) || soLuongNhap == null) {
                    return;
                }
                if (!checkNumber(soLuongNhap) && !soLuongNhap.equals("")) {
                    JOptionPane.showMessageDialog(this, "Số lượng phải là số nguyên dương");
                }
                Integer soLuongBienThe = serviceHoaDon.getSoLuongBienTheById(gh.getIdSanPhamChiTiet());
                Integer soLuongTrongGioHang = gh.getSoLuong();
                Integer checkCase = Integer.valueOf(soLuongNhap) - soLuongTrongGioHang;
                if (checkCase == 0) {
                    return;
                }
                if (checkCase > 0) {
                    if (Integer.valueOf(soLuongNhap) - soLuongTrongGioHang > soLuongBienThe) {
                        JOptionPane.showMessageDialog(this, "Số lượng tồn kho không đủ");
                        return;
                    }
                    serviceHoaDon.updateBienTheToHoaDon(gh.getId(), Integer.valueOf(soLuongNhap));
                    serviceHoaDon.updateSoLuongBienThe(gh.getIdSanPhamChiTiet(), soLuongBienThe - ((Integer.valueOf(soLuongNhap) - soLuongTrongGioHang)));
                }
                if (checkCase < 0) {
                    serviceHoaDon.updateBienTheToHoaDon(gh.getId(), Integer.valueOf(soLuongNhap));
                    serviceHoaDon.updateSoLuongBienThe(gh.getIdSanPhamChiTiet(), soLuongBienThe + soLuongTrongGioHang - Integer.valueOf(soLuongNhap));
                }
                if (Integer.valueOf(soLuongNhap) == 0) {
                    serviceHoaDon.deleteBienTheInGioHang(hd.getIdHoaDon(), gh.getIdSanPhamChiTiet());
                }
                reloadUpdateGioHang(hd.getIdHoaDon());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_tblGioHangMouseClicked

    private void btnClearGioHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearGioHangActionPerformed
        if (cboTrangThaiHoaDon.getSelectedIndex() != 0) {
            JOptionPane.showMessageDialog(this, "Chỉ hóa đơn chờ thanh toán được phép\nchỉnh sửa sản phẩm trong giỏ hàng");
            return;
        }
        if (tblHoaDon.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn để kiểm tra\ndanh sách sản phẩm bên trong");
            return;
        }
        HoaDonDTO hd = listHoaDon.get(tblHoaDon.getSelectedRow());
        int soLuongSanPhamTrongGioHang = listGioHang.size();
        if (soLuongSanPhamTrongGioHang == 0) {
            JOptionPane.showMessageDialog(this, "Không có sản phẩm trong giỏ hàng");
            return;
        }

        if (JOptionPane.showConfirmDialog(this, "Xác nhận xóa " + soLuongSanPhamTrongGioHang + " sản phẩm\nTrong HĐ mã: " + hd.getMaHoaDon(), "Xóa sản phẩm trong hóa đơn", JOptionPane.YES_NO_OPTION) == 0) {
            // Cộng lại số lượng vào biến thể
            for (GioHang gioHang : listGioHang) {
                serviceHoaDon.updateSoLuongBienTheAfterXoaGioHang(gioHang.getIdSanPhamChiTiet(), gioHang.getSoLuong());
            }

            // Xóa bản ghi trong giỏ hàng
            serviceHoaDon.deleteListBienTheInGioHang(hd.getIdHoaDon());
            reloadUpdateGioHang(hd.getIdHoaDon());
            reloadTongTienHoaDon(hd.getIdHoaDon(), timeToday);
        }
    }//GEN-LAST:event_btnClearGioHangActionPerformed

    private void tblHoaDonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblHoaDonMouseEntered

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonKhachHang;
    private javax.swing.JButton btnClearGioHang;
    private javax.swing.JButton btnLuuHoaDon;
    private javax.swing.JButton btnThem;
    private javax.swing.JComboBox<String> cboHinhThucGiaoHang;
    private javax.swing.JComboBox<String> cboMau;
    private javax.swing.JComboBox<String> cboTrangThaiHoaDon;
    private javax.swing.JComboBox<String> cbxChonHinhThucMua;
    private javax.swing.JComboBox<String> cbxHTTT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lbVoucher;
    private javax.swing.JPanel pnlWebcam;
    private javax.swing.JTable tblBienThe;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTextField txtGhiChu;
    private javax.swing.JTextField txtHoTenKhachHang;
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtSDTKhachHang;
    private javax.swing.JTextField txtSearchSanPham;
    private javax.swing.JTextField txtTenNhanVien;
    private javax.swing.JTextField txtTienKhachChuyenKhoan;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTongTien;
    private javax.swing.JTextField txtTongTienGiam;
    private javax.swing.JTextField txtTongTienThanhToan;
    // End of variables declaration//GEN-END:variables
}
