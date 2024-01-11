package com.poly.form.khachhang.view;

import com.formdev.flatlaf.FlatClientProperties;
import com.poly.Application;
import com.poly.form.khachhang.entity.KhachHangHung;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import javax.swing.table.DefaultTableModel;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import raven.toast.Notifications;
import java.util.Date;

public class FormCaiDaiMail extends javax.swing.JPanel {
    
    private List<KhachHangHung> listKhachHang;
    
    public FormCaiDaiMail(List<KhachHangHung> list) {
        this.listKhachHang = (list != null) ? list : new ArrayList<>();
        initComponents();
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        
        showDataKhachHangGuiMail();

        // Lấy ngày và giờ hiện tại
        Calendar currentDate = Calendar.getInstance();
        Date today = currentDate.getTime();
        dateThoiGian.setDate(today);
        
        String luaChon = (String) cbxLuaChon.getSelectedItem();
        if (luaChon.equalsIgnoreCase("Trống")) {
            dateThoiGian.setDate(null);
            txtSubJect.setText("");
            txtLoiMoDau.setText("");
            txtNoiDung.setText("");
            txtThongTin.setText("");
            txtLienHeGiupDo.setText("");
            txtCauKet.setText("");
        } else if (luaChon.equalsIgnoreCase("Ngày lễ")) {
            fakeVanBanNgayLe();
        } else if (luaChon.equalsIgnoreCase("Khách hàng vip")) {
            fakeKhachHangVip();
        } else if (luaChon.equalsIgnoreCase("Chăm sóc khách hàng")) {
            fakeVanBanChamSoc();
        }        
        
    }
    
    void showDataKhachHangGuiMail() {
        DefaultTableModel model = (DefaultTableModel) tblKhachGui.getModel();
        model.setRowCount(0);
        int stt = 1;
        for (KhachHangHung kh : listKhachHang) {
            Object data[] = {stt, kh.getMa(), kh.getHoTen(), kh.getEmail()};
            model.addRow(data);
            stt++;
        }
        
    }
    
    private void fakeVanBanNgayLe() {
        txtSubJect.setText("Chúc mừng năm mới");
        Calendar cal = Calendar.getInstance();
        dateThoiGian.setDate(cal.getTime());
        txtLoiMoDau.setText("Kính gửi quý khách hàng,Nhân dịp ngày tết nguyên đán");
        txtNoiDung.setText("Năm mới đã đến, là dịp để chúng ta cùng nhau đón nhận những khoảnh khắc đẹp nhất, hạnh phúc và an lành bên gia đình.\n"
                + "Chúng tôi xin gửi đến Quý khách hàng những lời chúc tốt đẹp nhất, hy vọng rằng năm mới sẽ mang lại nhiều may mắn, thành công và hạnh phúc.\n"
                + "\n"
                + "Chân thành cảm ơn sự tin tưởng và đồng hành của Quý khách hàng.\n"
                + "Chúng tôi cam kết tiếp tục nỗ lực, mang đến những sản phẩm và dịch vụ tốt nhất để đáp ứng mọi nhu cầu của Quý khách.\n"
                + "\n"
                + "Mong rằng mối quan hệ giữa chúng ta sẽ càng phát triển mạnh mẽ trong năm mới.");
        txtCauKet.setText("Chúc Quý khách hàng một năm mới an khang, thịnh vượng và tràn đầy niềm vui!");
        txtThongTin.setText("Cửa hàng túi da velisa cao cấp - Địa chỉ: Trịnh Văn Bô - Hà Nội");
        txtLienHeGiupDo.setText("0866625229");
    }
    
    private void fakeVanBanChamSoc() {
        txtSubJect.setText("Dịch vụ chăm sóc khách hàng");
        Calendar cal = Calendar.getInstance();
        dateThoiGian.setDate(cal.getTime());
        txtLoiMoDau.setText("Kính gửi quý khách hàng tiềm năng của chúng tôi. \n\n\n");
        txtNoiDung.setText("Chúng tôi xin gửi lời cảm ơn sâu sắc nhất đến Quý khách hàng đã tin dùng sản phẩm & dịch vụ của chúng tôi. Hãy yên tâm rằng chúng tôi sẽ luôn nỗ lực hết mình để cung cấp những sản phẩm chất lượng nhất cũng như dịch vụ chăm sóc khách hàng tốt nhất. \n\n\n");
        txtCauKet.setText("Hãy liên hệ với chúng tôi nếu bạn có bất kỳ thắc mắc hoặc yêu cầu nào, chúng tôi luôn sẵn lòng hỗ trợ bạn. \n");
        txtThongTin.setText("Cửa hàng túi da velisa cao cấp - Địa chỉ: Trịnh Văn Bô - Hà Nội \n");
        txtLienHeGiupDo.setText("0987654321\n\n");
    }
    
    private void fakeKhachHangVip() {
        txtSubJect.setText("Chào mừng Quý Khách Hàng VIP đến với Cửa Hàng Túi Da Velisa Cao Cấp ");
        Calendar cal = Calendar.getInstance();
        dateThoiGian.setDate(cal.getTime());
        txtLoiMoDau.setText("Kính gửi Quý Khách Hàng,Chúng tôi xin gửi lời chào trân trọng và cảm ơn sâu sắc vì sự ủng hộ và lòng tin dành cho cửa hàng của chúng tôi.");
        txtNoiDung.setText("Đây là một thông điệp đặc biệt dành riêng cho Quý Khách Hàng VIP của chúng tôi. Chúng tôi muốn bày tỏ lòng biết ơn vô cùng sâu sắc với sự hỗ trợ không ngừng và mối quan hệ đáng trân trọng mà chúng ta đã xây dựng.\n"
                + "\n"
                + "Sự đóng góp và ủng hộ của Quý Khách Hàng đã là động lực lớn giúp chúng tôi không ngừng hoàn thiện và mang đến những sản phẩm tốt nhất.\n"
                + "\n"
                + "Chúng tôi luôn lắng nghe ý kiến phản hồi của Quý Khách để không ngừng cải thiện dịch vụ. Nếu có bất kỳ điều gì chúng tôi có thể hỗ trợ hoặc cải thiện, xin vui lòng liên hệ với chúng tôi.\n"
                + "\n"
                + "Chúng tôi hy vọng rằng Quý Khách Hàng sẽ tiếp tục được phục vụ tốt nhất và mong muốn có cơ hội được gặp gỡ và hỗ trợ Quý Khách Hàng trong tương lai.");
        txtCauKet.setText("Hãy liên hệ với chúng tôi nếu bạn có bất kỳ thắc mắc hoặc yêu cầu nào, chúng tôi luôn sẵn lòng hỗ trợ bạn.");
        txtThongTin.setText("Cửa hàng túi da velisa cao cấp - Địa chỉ: Trịnh Văn Bô - Hà Nội");
        txtLienHeGiupDo.setText("093717331");
    }
    
    private String getNoiDungEmail() {
        String result = "Ngày " + dateThoiGian.getDate() + "\n"
                + " " + txtLoiMoDau.getText() + "\n"
                + txtNoiDung.getText() + "\n"
                + " " + txtCauKet.getText() + "\n"
                + "Thông tin cửa hàng: " + txtThongTin.getText() + "\n"
                + "Liên hệ: " + txtLienHeGiupDo.getText();
        return result;
    }
    
    public void send(List<KhachHangHung> list) {
        if (list == null || list.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER,
                    "Danh sách khách hàng trống");
            return;
        }
        
        try {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            
            String accountName = txtUser.getText();
            String password = txtPass.getText();
            
            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(accountName, password);
                }
            });
            
            for (KhachHangHung kh : list) {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(accountName));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(kh.getEmail()));
                message.setSubject(txtSubJect.getText());
                message.setText(getNoiDungEmail());
                Transport.send(message);
                
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER,
                        "Đã gửi mail thành công đến " + kh.getEmail());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPass = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtSubJect = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNoiDung = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtLoiMoDau = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtCauKet = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtThongTin = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtLienHeGiupDo = new javax.swing.JTextField();
        dateThoiGian = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhachGui = new javax.swing.JTable();
        cbxLuaChon = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        lb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("Gửi mail");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tài khoản Gmail cửa hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N
        jPanel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setText("Gmail");

        txtUser.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtUser.setText("hungcodena2004@gmail.com");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setText("Mật khẩu");

        txtPass.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtPass.setText("icpbvgrzccnsiwwb");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUser, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                    .addComponent(txtPass))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nội dung gửi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Subject:");

        txtSubJect.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSubJect.setText("Chúc Mừng Năm Mới - Phúc Lộc Đầy Nhà");

        txtNoiDung.setColumns(20);
        txtNoiDung.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNoiDung.setRows(5);
        txtNoiDung.setText("Năm mới đã đến, là dịp để chúng ta cùng nhau đón nhận những khoảnh khắc đẹp nhất, hạnh phúc và an lành bên gia đình.\nChúng tôi xin gửi đến Quý khách hàng những lời chúc tốt đẹp nhất, hy vọng rằng năm mới sẽ mang lại nhiều may mắn, thành công và hạnh phúc.\n\nChân thành cảm ơn sự tin tưởng và đồng hành của Quý khách hàng.\nChúng tôi cam kết tiếp tục nỗ lực, mang đến những sản phẩm và dịch vụ tốt nhất để đáp ứng mọi nhu cầu của Quý khách.\n\nMong rằng mối quan hệ giữa chúng ta sẽ càng phát triển mạnh mẽ trong năm mới.");
        jScrollPane2.setViewportView(txtNoiDung);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Lời mở đầu:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Thời gian:");

        txtLoiMoDau.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtLoiMoDau.setText("Chào Quý khách hàng thân mến");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Nội dung");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Câu kết");

        txtCauKet.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCauKet.setText("Chúc Quý khách hàng một năm mới an khang, thịnh vượng và tràn đầy niềm vui!");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Thông tin cửa hàng");

        txtThongTin.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtThongTin.setText("958/14 Âu Cơ, Phường 14, Quận Tân Bình, Tp HCM");
        txtThongTin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtThongTinActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Liên hệ với chúng tôi:");

        txtLienHeGiupDo.setText("0866625229");

        dateThoiGian.setDateFormatString("dd-MM-yyyy");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtSubJect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dateThoiGian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txtLoiMoDau)))
                    .addComponent(jLabel8)
                    .addComponent(jLabel10)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(txtThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLienHeGiupDo))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCauKet, javax.swing.GroupLayout.PREFERRED_SIZE, 880, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtSubJect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addComponent(dateThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtLoiMoDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCauKet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtLienHeGiupDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(72, Short.MAX_VALUE))
        );

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton1.setText("Gửi");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách người gửi"));

        tblKhachGui.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Mã KH", "Họ Tên", "Email"
            }
        ));
        jScrollPane1.setViewportView(tblKhachGui);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        cbxLuaChon.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbxLuaChon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Trống", "Ngày lễ", "Khách hàng vip", "Chăm sóc khách hàng" }));
        cbxLuaChon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxLuaChonActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText("Thể loại:");

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton2.setText("Trở lại trang chủ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbxLuaChon, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lb)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton1))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(cbxLuaChon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Application.showForm(new FormQuanLyKhachHang());
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (txtSubJect.getText().isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER,
                    "Vui lòng nhập Chủ thể");
            return;
        }
        if (dateThoiGian.getDate() == null) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER,
                    "Ngày tháng chưa hợp lệ");
            return;
        }
        if (txtLoiMoDau.getText().isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER,
                    "Vui lòng nhập lời mở đầu");
            return;
        }
        if (txtNoiDung.getText().isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER,
                    "Nội dung không được để trống");
            return;
        }
        if (txtCauKet.getText().isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER,
                    "Vui lòng nhập câu kết thúc");
            return;
        }
        if (txtThongTin.getText().isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER,
                    "Vui lòng nhập thông tin");
            return;
        }
        if (txtLienHeGiupDo.getText().isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER,
                    "Vui lòng nhập liên hệ");
            return;
        }
        int luaChon = cbxLuaChon.getSelectedIndex();
        if(luaChon == 0) {
              Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER,
                    "Vui lòng chọn hình thức gửi mail");
            return;
        }
        send(listKhachHang);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbxLuaChonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxLuaChonActionPerformed
        String luaChon = (String) cbxLuaChon.getSelectedItem();
        if (luaChon.equalsIgnoreCase("Trống")) {
            dateThoiGian.setDate(null);
            txtSubJect.setText("");
            txtLoiMoDau.setText("");
            txtNoiDung.setText("");
            txtThongTin.setText("");
            txtLienHeGiupDo.setText("");
        } else if (luaChon.equalsIgnoreCase("Ngày lễ")) {
            fakeVanBanNgayLe();
        } else if (luaChon.equalsIgnoreCase("Khách hàng vip")) {
            fakeKhachHangVip();
        } else if (luaChon.equalsIgnoreCase("Chăm sóc khách hàng")) {
            fakeVanBanChamSoc();
        }        
    }//GEN-LAST:event_cbxLuaChonActionPerformed

    private void txtThongTinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtThongTinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtThongTinActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbxLuaChon;
    private com.toedter.calendar.JDateChooser dateThoiGian;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JTable tblKhachGui;
    private javax.swing.JTextField txtCauKet;
    private javax.swing.JTextField txtLienHeGiupDo;
    private javax.swing.JTextField txtLoiMoDau;
    private javax.swing.JTextArea txtNoiDung;
    private javax.swing.JTextField txtPass;
    private javax.swing.JTextField txtSubJect;
    private javax.swing.JTextField txtThongTin;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
