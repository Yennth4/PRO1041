/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.thongke.utils;

import com.poly.form.thongke.entity.NhanVienEntity;
import com.poly.form.thongke.entity.SanPhamEntity;
import com.poly.form.thongke.service.impl.NhanVienServiceImpl;
import com.poly.form.thongke.service.impl.SanPhamServiceImpl;
import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Admin
 */
public class SendMail {

    private List<NhanVienEntity> listNhanVien = new NhanVienServiceImpl().findAll();
    private List<SanPhamEntity> listSanPham = new SanPhamServiceImpl().findAll();
    private String username = "lucluat055@gmail.com";
    private String password = "xeljrsawcxhmaagi";
    private String subject = "Báo Cáo Sếp! ";
    private StringBuilder rowNhanVien = new StringBuilder("");
    private StringBuilder rowSanPham = new StringBuilder("");
    String msg;

    public SendMail() {
        for (int i = 0; i < listNhanVien.size(); i++) {
            NhanVienEntity nhanVienEntity = listNhanVien.get(i);
            int stt = i + 1;
            rowNhanVien.append(
                    "<tr>" + "<td>" + stt + "</td>" + nhanVienEntity.toString() + "</tr>"
            );
        }
        for (int i = 0; i < listSanPham.size(); i++) {
            SanPhamEntity entity = listSanPham.get(i);
            int stt = i + 1;
            rowSanPham.append(
                    "<tr>" + "<td>" + stt + "</td>" + entity.toString() + "</tr>"
            );
        }
        msg = "<!DOCTYPE html> "
                + "<html lang=\"en\"> "
                + "<head> "
                + "    <meta charset=\"UTF-8\"> "
                + " <Style> "
                + "        .container{ "
                + "            font-family: 'Arial', sans-serif; "
                + "        } "
                + "    </Style> "
                + "</head> "
                + "<body> "
                + "<h2>Dear Sep,</h2>"
                + "<h4> Em xin gui bao cao thang nay!</h4>"
                + "<h4>Danh sach nhan vien lam viec trong thang</h4>"
                + " <table> "
                + "        <thead> "
                + "            <tr> "
                + "                <th> STT</th> "
                + "                <th> Ma Nhan Vien</th> "
                + "                <th> Ten</th> "
                + "                <th> Gioi Tinh</th> "
                + "                <th> So Dien Thoai</th> "
                + "                <th> So Luong Ban Duoc</th> "
                + "                <th> Chiem Phan Tram</th> "
                + "            </tr> "
                + "        </thead> "
                + "        <tbody> "
                + rowNhanVien
                + "        </tbody> "
                + "    </table>"
                + " <br/>"
                + "<h4>Danh sach san pham ban chay</h4>"
                + " <table> "
                + "        <thead> "
                + "            <tr> "
                + "                <th> STT</th> "
                + "                <th> Ma Nhan Vien</th> "
                + "                <th> Ten</th> "
                + "                <th> Gioi Tinh</th> "
                + "                <th> So Dien Thoai</th> "
                + "                <th> So Luong Ban Duoc</th> "
                + "                <th> Chiem Phan Tram</th> "
                + "            </tr> "
                + "        </thead> "
                + "        <tbody> "
                + rowSanPham
                + "        </tbody> "
                + "    </table>"
                + " <h4>Chuc Sep Co Mot Ngay Vui Ve!</h4>"
                + " <h4>Tran Trong.</h4>"
                + "</body> "
                + "</html>";
    }

    public boolean Send(String to) {

        //thông số kết nối smtp server
        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // kết nối smtp server
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
//mail: xelj rsaw cxhm aagi
//        website:  jlcf xpds gpbd jwyx
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(msg, "UTF-8");
            //đặt tiếng việt
            message.setHeader("Content-type", "text/HTML; charset=UTF-8");

            Transport.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
