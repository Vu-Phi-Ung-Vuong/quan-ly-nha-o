/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duan.helper;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import static java.awt.Color.pink;
import static java.awt.Color.white;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author 
 */
public class utilityHelper {

   

    public static boolean checkNullText(JTextField txt) {
        txt.setBackground(white);
        if (txt.getText().trim().length() > 0) {
            return true;
        } else {
            txt.setBackground(pink);
            MgsBoxhelper.alert(txt.getRootPane(), "Không được để trống " );
            return false;
        }
    }
    public static boolean checkNullText1(JTextArea txt) {
        txt.setBackground(white);
        if (txt.getText().trim().length() > 0) {
            return true;
        } else {
            txt.setBackground(pink);
            MgsBoxhelper.alert(txt.getRootPane(), "Không được để trống " );
            return false;
        }
    }

    public static boolean checkDate(JDateChooser txt) {
        txt.setBackground(white);
        if(
        (txt.getDate()!= null)){
            return true;
        } else {
             MgsBoxhelper.alert(txt.getRootPane(), "Không được để trống " );
             return false;
        }
}  
      public static boolean checkNullcombox(JComboBox txt) {
        txt.setBackground(white);
        if (txt.getSelectedItem()!=null){
            return true;
        } else {
            txt.setBackground(pink);
            MgsBoxhelper.alert(txt.getRootPane(), "Không được để trống " );
            return false;
        }
    }
    
}

