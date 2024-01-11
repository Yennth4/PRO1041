/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.util.ph31848;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author longnvph31848
 */
public class GetDateTimeCurrent {

    public static String getTimeNow() {
        // Lấy ngày tháng năm giờ phút giây hiện tại
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Định dạng ngày tháng năm giờ phút giây theo định dạng "dd-MM-yyyy HHmmss"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmmss");
        String formattedDateTime = currentDateTime.format(formatter);
        return formattedDateTime;
    }

    public static void main(String[] args) {
        System.out.println(getTimeNow());
    }
}
