/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.util.ph31848;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author longnvph31848
 */
public class ConvertDateToLong {

    public static Long getLongTimeHienTai() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date()); // Set the time for the current date
        Long ngayTaoVaSua = cal.getTime().getTime() / 1000;
        return ngayTaoVaSua;
    }

    public static void main(String[] args) {
        System.out.println(getLongTimeHienTai());
    }
}
