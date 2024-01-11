/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.form.thongke.utils;

/**
 *
 * @author Admin
 */
public class Common {

    private static String regexEmail = "^([a-zA-Z])([a-zA-Z0-9_.]*)([a-zA-Z0-9])@([a-zA-Z0-9]*\\.)*[a-zA-Z0-9]*$";

    public static boolean checkEmail(String email) {
        return email.matches(regexEmail) ? true : false;
    }
}
