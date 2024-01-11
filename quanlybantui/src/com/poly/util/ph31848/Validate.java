/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.util.ph31848;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author longnvph31848
 */
public class Validate {

    private static String REGEX_CHAR = "^[a-zA-Z\\p{L}\\s]+$";
    private static String REGEX_NUMBER = "^[0-9]\\d*$";
    private static String REGEX_FLOAT = "^\\d+(\\.\\d+)?$";
    private static String REGEX_MATHELOAI = "^TL\\d{4}$";

    public static boolean checkEmpty(String str) {
        return str.trim().isEmpty();
    }

    public static boolean checkChar(String str) {
        return str.trim().matches(REGEX_CHAR);
    }

    public static boolean checkNumber(String str) {
        return str.trim().matches(REGEX_NUMBER);
    }

    public static boolean checkFloat(String str) {
        return str.trim().matches(REGEX_FLOAT);
    }

    public static boolean checkMaTheLoai(String str) {
        return str.trim().matches(REGEX_MATHELOAI);
    }

    public static String renderStringSearchSQL(String column1, String column2, String search) {
        StringBuilder query = new StringBuilder();
        query.append("(").append(column1).append(" LIKE ").append("N\'%")
                .append(search).append("\'").append(" OR ").append(column1)
                .append(" LIKE ").append("N\'%").append(search).append("%\'")
                .append(" OR ").append(column1).append(" LIKE ").append("N\'")
                .append(search).append("%\'").append(" OR ").append(column2)
                .append(" LIKE ").append("N\'%").append(search).append("\'")
                .append(" OR ").append(column2).append(" LIKE ").append("N\'%")
                .append(search).append("%\'").append(" OR ").append(column2)
                .append(" LIKE ").append("N\'").append(search).append("%\'").append(")");
        return query.toString();
    }

    public static String renderStringSearchSQL(String column1, String column2, String column3, String search) {
        StringBuilder query = new StringBuilder();
        query.append("(").append(column1).append(" LIKE ").append("N\'%")
                .append(search).append("\'").append(" OR ").append(column1)
                .append(" LIKE ").append("N\'%").append(search).append("%\'")
                .append(" OR ").append(column1).append(" LIKE ").append("N\'")
                .append(search).append("%\'")
                .append(" OR ").append(column2)
                .append(" LIKE ").append("N\'%").append(search).append("\'")
                .append(" OR ").append(column2).append(" LIKE ").append("N\'%")
                .append(search).append("%\'").append(" OR ").append(column2)
                .append(" LIKE ").append("N\'").append(search).append("%\'")
                .append(" OR ").append(column3)
                .append(" LIKE ").append("N\'%").append(search).append("\'")
                .append(" OR ").append(column3).append(" LIKE ").append("N\'%")
                .append(search).append("%\'").append(" OR ").append(column3)
                .append(" LIKE ").append("N\'").append(search).append("%\'")
                .append(")");
        return query.toString();
    }

    public static boolean isCorrectTime(String str) {
        return str.trim().matches("^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$");
    }

    public static String formatDate(Date date) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String formatDate = sdf.format(date);
            return formatDate;
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(renderStringSearchSQL("tenTheLoai", "maTheLoai", "túi"));
    }
}
