package com.poly.form.nhanvien.view;
// code chung

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.util.UIScale;
import com.poly.Application;
import com.poly.entity.NhanVien;
import com.poly.form.nhanvien.entity.Login;
import com.poly.form.nhanvien.entity.NhanVienEntity;
import com.poly.form.nhanvien.repository.NhanVienRepo;
import com.poly.menu.Menu;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.util.List;
import java.util.Optional;
import javax.swing.JOptionPane;
import raven.toast.Notifications;

public class LoginForm extends javax.swing.JPanel {

    DoimkForm doimk = new DoimkForm();
    NhanVienRepo service = new NhanVienRepo();
    private Login loggedInUser;
    private NhanVienEntity loggedInNhanVien;
    private Menu menu;

    public LoginForm() {
        initComponents();
        txtpassword.addActionListener(e -> btnLoginActionPerformed(null));
        setLayout(new LoginFormLayout());
        txtpassword.putClientProperty(FlatClientProperties.STYLE, ""
                + "showRevealButton:true;"
                + "showCapsLock:true");
        this.menu = new Menu();
    }

    private boolean checkPassword(String inputPassword, String storedPassword) {
        return inputPassword.equals(storedPassword);
    }

    Login readform() {
        String username = txtUser.getText().trim();
        String password = new String(txtpassword.getPassword()).trim();
        String role = "";
        return new Login(username, password, role, HEIGHT);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        login = new javax.swing.JPanel();
        btnLogin = new javax.swing.JButton();
        lbTitle = new javax.swing.JLabel();
        lbUser = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        lbPass = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btndoimk = new javax.swing.JButton();
        txtpassword = new javax.swing.JPasswordField();

        jButton1.setText("Đổi Mật Khẩu");

        jButton2.setText("Tạo Tài Khoản");

        login.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        lbTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("LOGIN");
        lbTitle.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

        lbUser.setText("User Name");

        lbPass.setText("Password");

        jLabel1.setFont(new java.awt.Font("Roboto Mono", 2, 12)); // NOI18N
        jLabel1.setText("Đổi Mật Khẩu");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        btndoimk.setText("Tại Đây");
        btndoimk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndoimkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginLayout.createSequentialGroup()
                .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, loginLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(loginLayout.createSequentialGroup()
                                .addComponent(lbPass)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, loginLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(lbUser)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(6, 6, 6))
            .addGroup(loginLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtpassword)
                    .addComponent(txtUser, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(loginLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btndoimk)
                        .addGap(0, 94, Short.MAX_VALUE)))
                .addContainerGap())
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitle)
                .addGap(10, 10, 10)
                .addComponent(lbUser)
                .addGap(5, 5, 5)
                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(lbPass)
                .addGap(18, 18, 18)
                .addComponent(txtpassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btndoimk, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(204, 204, 204)
                .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(221, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    private NhanVienEntity checkLogin(String username, String password) {
        // Lấy danh sách tất cả các đăng nhập từ Repo
        List<Login> users = service.getAllLogins();

        // Kiểm tra xem ô nhập liệu tên người dùng có trống không
        if (txtUser.getText().isBlank()) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "UserName Trống");
            return null;
        }

        // Kiểm tra xem ô nhập liệu mật khẩu có trống không
        if (txtpassword.getPassword().length == 0) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "PassWord Trống");
            return null;
        }

        // Nếu danh sách đăng nhập không rỗng
        if (users != null) {
            // Tìm đăng nhập phù hợp với tên người dùng và mật khẩu
            Optional<Login> matchingLogin = users.stream()
                    .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                    .findFirst();

            // Nếu tìm thấy đăng nhập phù hợp
            if (matchingLogin.isPresent()) {
                // Lấy id của nhân viên từ đăng nhập và tìm kiếm thông tin nhân viên
                int nhanVienId = (int) matchingLogin.get().getNhanVienId();
                Optional<NhanVienEntity> nhanVienOptional = service.getNhanVienByLogin(username, password);
                // Trả về thông tin nhân viên hoặc null nếu không tìm thấy
                return nhanVienOptional.orElse(null);
            }
        }

        // Trả về null nếu không tìm thấy đăng nhập phù hợp
        return null;
    }

    private Login checkLogin1(String username, String password) {
        List<Login> users = service.getAllLogins();
        if (txtUser.getText().isBlank()) {
            return null;
        }
        if (txtpassword.getPassword().equals("")) {
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "PassWord Trống");
            return null;
        }
        if (users != null) {
            Optional<Login> matchingLogin = users.stream()
                    .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                    .findFirst();

            return matchingLogin.orElse(null);
        } else {
            return null;
        }
    }
    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        String username = txtUser.getText().trim();
        String password = new String(txtpassword.getPassword());

        NhanVienEntity loggedInNhanVien = checkLogin(username, password);
        Login loggedInUser = checkLogin1(username, password);
        
        if (loggedInNhanVien != null && checkLogin1(username, password) != null) {
            // Nếu người dùng được tìm thấy, set loggedInNhanVien và hiển thị thông báo
            this.loggedInNhanVien = loggedInNhanVien;
            this.loggedInUser = loggedInUser;
            System.out.println("Id:" + loggedInNhanVien.getId());
            System.out.println("Tên: " + loggedInNhanVien.getTen());
            System.out.println("Ngày sinh: " + loggedInNhanVien.getNgaySinh());
            System.out.println("Giới tính: " + loggedInNhanVien.getGioiTinh());
            System.out.println("Trạng thái: " + loggedInNhanVien.getTrangThai());
            Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Đăng nhập thành công!");
            Application.login(loggedInUser);
            
        } else {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Tên đăng nhập hoặc mật khẩu không đúng!");
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked

    }//GEN-LAST:event_jLabel1MouseClicked

    private void btndoimkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndoimkActionPerformed
        DoimkForm changePass = new DoimkForm();
        int result = JOptionPane.showConfirmDialog(null, changePass, "Đổi Mật Khẩu", JOptionPane.OK_CANCEL_OPTION);
    }//GEN-LAST:event_btndoimkActionPerformed

    private class LoginFormLayout implements LayoutManager {

        @Override
        public void addLayoutComponent(String name, Component comp) {
        }

        @Override
        public void removeLayoutComponent(Component comp) {
        }

        @Override
        public Dimension preferredLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                return new Dimension(0, 0);
            }
        }

        @Override
        public Dimension minimumLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                return new Dimension(0, 0);
            }
        }

        @Override
        public void layoutContainer(Container parent) {
            synchronized (parent.getTreeLock()) {
                int width = parent.getWidth();
                int height = parent.getHeight();
                int loginWidth = UIScale.scale(320);
                int loginHeight = login.getPreferredSize().height;
                int x = (width - loginWidth) / 2;
                int y = (height - loginHeight) / 2;
                login.setBounds(x, y, loginWidth, loginHeight);
            }
        }
    }

    private class LoginLayout implements LayoutManager {

        private final int titleGap = 10;
        private final int textGap = 10;
        private final int labelGap = 5;
        private final int buttonGap = 50;

        @Override
        public void addLayoutComponent(String name, Component comp) {
        }

        @Override
        public void removeLayoutComponent(Component comp) {
        }

        @Override
        public Dimension preferredLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                Insets insets = parent.getInsets();
                int height = insets.top + insets.bottom;

                height += lbTitle.getPreferredSize().height;
                height += UIScale.scale(titleGap);
                height += lbUser.getPreferredSize().height;
                height += UIScale.scale(labelGap);
                height += txtUser.getPreferredSize().height;
                height += UIScale.scale(textGap);

                height += lbPass.getPreferredSize().height;
                height += UIScale.scale(labelGap);
//                height += txtPass.getPreferredSize().height;
                height += UIScale.scale(buttonGap);
                height += btnLogin.getPreferredSize().height;
                return new Dimension(0, height);
            }
        }

        @Override
        public Dimension minimumLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                return new Dimension(0, 0);
            }
        }

        @Override
        public void layoutContainer(Container parent) {
            synchronized (parent.getTreeLock()) {
                Insets insets = parent.getInsets();
                int x = insets.left;
                int y = insets.top;
                int width = parent.getWidth() - (insets.left + insets.right);

                lbTitle.setBounds(x, y, width, lbTitle.getPreferredSize().height);
                y += lbTitle.getPreferredSize().height + UIScale.scale(titleGap);

                lbUser.setBounds(x, y, width, lbUser.getPreferredSize().height);
                y += lbUser.getPreferredSize().height + UIScale.scale(labelGap);
                txtUser.setBounds(x, y, width, txtUser.getPreferredSize().height);
                y += txtUser.getPreferredSize().height + UIScale.scale(textGap);

                lbPass.setBounds(x, y, width, lbPass.getPreferredSize().height);
                y += lbPass.getPreferredSize().height + UIScale.scale(labelGap);
//                txtPass.setBounds(x, y, width, txtPass.getPreferredSize().height);
//                y += txtPass.getPreferredSize().height + UIScale.scale(buttonGap);

                btnLogin.setBounds(x, y, width, btnLogin.getPreferredSize().height);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btndoimk;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbPass;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lbUser;
    private javax.swing.JPanel login;
    private javax.swing.JTextField txtUser;
    private javax.swing.JPasswordField txtpassword;
    // End of variables declaration//GEN-END:variables
}
