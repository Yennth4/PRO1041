package com.poly.form.nhanvien.view;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.util.UIScale;
import com.poly.Application;
import com.poly.form.nhanvien.entity.Login;
import com.poly.form.nhanvien.repository.NhanVienRepo;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import javax.swing.JOptionPane;
import raven.toast.Notifications;

public class DoimkForm extends javax.swing.JPanel {

    NhanVienRepo service = new NhanVienRepo();
    private LoginForm loginForm;

    public DoimkForm() {
        initComponents();
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        txtPassnew.putClientProperty(FlatClientProperties.STYLE, ""
                + "showRevealButton:true;"
                + "showCapsLock:true");
        txtPassnew1.putClientProperty(FlatClientProperties.STYLE, ""
                + "showRevealButton:true;"
                + "showCapsLock:true");
    }

    public DoimkForm(LoginForm loginForm) {
        this.loginForm = loginForm;
    }

    Login readform() {
        String username = txtPasscu.getText().trim();
        String pass = txtPassnew.getText().trim();
        String role = "";
        return new Login(username, pass, role, HEIGHT);
    }

    boolean check() {
        if (txtusername.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Username Trống");
            return false;
        }
        if (txtPasscu.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, " Cần Nhập Mật Khẩu Cũ ");
            return false;
        }
        if (txtPassnew.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Cần Nhập Mật Khẩu Mới ");
            return false;
        }
        if (txtPassnew1.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Cần Nhập Lại Mật Khẩu Mới");
            return false;
        }
        return true;
    }

    void reset() {
        txtusername.setText("");
        txtPasscu.setText("");
        txtPassnew.setText("");
        txtPassnew1.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        login = new javax.swing.JPanel();
        btnDoimk = new javax.swing.JButton();
        lbTitle = new javax.swing.JLabel();
        lbUser = new javax.swing.JLabel();
        txtPasscu = new javax.swing.JTextField();
        lbPass = new javax.swing.JLabel();
        txtPassnew = new javax.swing.JPasswordField();
        lbPass1 = new javax.swing.JLabel();
        txtPassnew1 = new javax.swing.JPasswordField();
        txtusername = new javax.swing.JTextField();
        lbUser1 = new javax.swing.JLabel();
        lb = new javax.swing.JLabel();

        jButton1.setText("Đổi Mật Khẩu");

        jLabel1.setFont(new java.awt.Font("Roboto Mono", 2, 12)); // NOI18N
        jLabel1.setText("Đổi Mật Khẩu");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        login.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnDoimk.setText("Đổi Mật Khẩu");
        btnDoimk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoimkActionPerformed(evt);
            }
        });

        lbTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("Đổi Mật Khẩu");
        lbTitle.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

        lbUser.setText("Mật Khẩu Cũ");

        lbPass.setText("Mật Khẩu Mới");

        lbPass1.setText("Nhập Lại");

        lbUser1.setText("UserName");

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginLayout.createSequentialGroup()
                        .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtPassnew)
                            .addComponent(lbTitle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                            .addComponent(btnDoimk, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, loginLayout.createSequentialGroup()
                                .addComponent(lbPass)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(6, 6, 6))
                    .addGroup(loginLayout.createSequentialGroup()
                        .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPassnew1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtPasscu)
                            .addComponent(txtusername)
                            .addGroup(loginLayout.createSequentialGroup()
                                .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbPass1)
                                    .addGroup(loginLayout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbUser)
                                            .addComponent(lbUser1))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbUser1)
                .addGap(5, 5, 5)
                .addComponent(txtusername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbUser)
                .addGap(5, 5, 5)
                .addComponent(txtPasscu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbPass)
                .addGap(5, 5, 5)
                .addComponent(txtPassnew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbPass1)
                .addGap(5, 5, 5)
                .addComponent(txtPassnew1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(btnDoimk)
                .addContainerGap())
        );

        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb.setText("ĐỎI MẬT KHẨU");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(204, 204, 204)
                .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(228, Short.MAX_VALUE))
            .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, 721, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb)
                .addGap(46, 46, 46)
                .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDoimkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoimkActionPerformed
        if (check()) {
            if (checkMatKhau()) {
                Login lg = readform();
                String username = txtusername.getText().trim();
                int result = service.updateMatkhau(lg, username);
                if (result > 0) {
                    Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Đổi mật khẩu thành công!");
                    //this.setVisible(false);
                    this.reset();
                } else {
                    Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Đổi mật khẩu thất bại!");
                }
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Mật khẩu mới và nhập lại không khớp nhau!");
            }
        }
    }//GEN-LAST:event_btnDoimkActionPerformed
    private boolean checkMatKhau() {
        String matKhauMoi = txtPassnew.getText().trim();
        String nhapLaiMatKhau = txtPassnew1.getText().trim();
        return matKhauMoi.equals(nhapLaiMatKhau);
    }
    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked

    }//GEN-LAST:event_jLabel1MouseClicked

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
                height += txtPasscu.getPreferredSize().height;
                height += UIScale.scale(textGap);

                height += lbPass.getPreferredSize().height;
                height += UIScale.scale(labelGap);
                height += txtPassnew.getPreferredSize().height;
                height += UIScale.scale(buttonGap);
                height += btnDoimk.getPreferredSize().height;
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
                txtPasscu.setBounds(x, y, width, txtPasscu.getPreferredSize().height);
                y += txtPasscu.getPreferredSize().height + UIScale.scale(textGap);

                lbPass.setBounds(x, y, width, lbPass.getPreferredSize().height);
                y += lbPass.getPreferredSize().height + UIScale.scale(labelGap);
                txtPassnew.setBounds(x, y, width, txtPassnew.getPreferredSize().height);
                y += txtPassnew.getPreferredSize().height + UIScale.scale(buttonGap);

                btnDoimk.setBounds(x, y, width, btnDoimk.getPreferredSize().height);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDoimk;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lbPass;
    private javax.swing.JLabel lbPass1;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lbUser;
    private javax.swing.JLabel lbUser1;
    private javax.swing.JPanel login;
    private javax.swing.JTextField txtPasscu;
    private javax.swing.JPasswordField txtPassnew;
    private javax.swing.JPasswordField txtPassnew1;
    private javax.swing.JTextField txtusername;
    // End of variables declaration//GEN-END:variables
}
