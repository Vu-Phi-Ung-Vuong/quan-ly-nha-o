/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duan.helper;

import com.duan.model.Nhanvien;



/**
 *
 * @author THANH
 */
public class Auth {
    public static Nhanvien user=null;
    public static void clear(){
        Auth.user = null;
        
    }
    public static boolean isLogin(){
       return  Auth.user != null;

    }
    public static boolean isManager(){
     return Auth.isLogin()&& user.isVaitro();
        
    }


}
