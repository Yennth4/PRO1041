/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.util.ph31848;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author longnvph31848
 */
public class OpenFile {

    public static void openDocxFile(String path) {
        try {

            // Kiểm tra xem Desktop API có thể được sử dụng không
            if (Desktop.isDesktopSupported()) {
                // Lấy Desktop instance
                Desktop desktop = Desktop.getDesktop();

                // Kiểm tra xem tệp có tồn tại không
                File file = new File(path);
                if (file.exists()) {
                    // Mở tệp với ứng dụng mặc định
                    desktop.open(file);
                } else {
                    System.out.println("Tệp không tồn tại: " + path);
                }
            } else {
                System.out.println("Desktop không được hỗ trợ trên hệ thống này.");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
