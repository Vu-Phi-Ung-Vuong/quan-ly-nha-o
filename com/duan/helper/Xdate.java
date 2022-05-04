/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duan.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author THANH
 */
public class Xdate {
     static final SimpleDateFormat formarter = new SimpleDateFormat("MM/dd/yyyy");

    public static Date toDate(String date, String... pattern) {
        try {
            if (pattern.length > 0) {
                formarter.applyPattern(pattern[0]);
            }
            if (date == null) {
                return Xdate.now();
            }
            return formarter.parse(date);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
 public static String toString(Date date, String... pattern) {
        if (pattern.length > 0) {
            formarter.applyPattern(pattern[0]);
        }
        if (date == null) {
            date = Xdate.now();
        }
        return formarter.format(date);
    }
public static Date addDays(Date date ,long days){
    date.setTime(date.getTime()+days*24*60*60*1000);
    return date;
    
}
 public static Date now() {
        return new Date();   //new Date lấy giờ hiện tại
    }          
}
