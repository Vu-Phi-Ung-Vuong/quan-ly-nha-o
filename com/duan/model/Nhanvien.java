/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duan.model;

import java.util.Date;

/**
 *
 * @author THANH
 */
public class Nhanvien {
    String MaNV;
    String Hoten;
    boolean Gioitinh;
    String Email;
     Date Ngaysinh;
     String SDT;
     String Password;
     boolean Vaitro;
     String Hinh;
     String QRcode;

    public Nhanvien() {
    }

    @Override
    public String toString() {
        return this.MaNV;
    }

    public Nhanvien(String MaNV, String Hoten, boolean Gioitinh, String Email, Date Ngaysinh, String SDT, String Password, boolean Vaitro, String Hinh, String QRcode) {
        this.MaNV = MaNV;
        this.Hoten = Hoten;
        this.Gioitinh = Gioitinh;
        this.Email = Email;
        this.Ngaysinh = Ngaysinh;
        this.SDT = SDT;
        this.Password = Password;
        this.Vaitro = Vaitro;
        this.Hinh = Hinh;
        this.QRcode = QRcode;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getHoten() {
        return Hoten;
    }

    public void setHoten(String Hoten) {
        this.Hoten = Hoten;
    }

    public boolean isGioitinh() {
        return Gioitinh;
    }

    public void setGioitinh(boolean Gioitinh) {
        this.Gioitinh = Gioitinh;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public Date getNgaysinh() {
        return Ngaysinh;
    }

    public void setNgaysinh(Date Ngaysinh) {
        this.Ngaysinh = Ngaysinh;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public boolean isVaitro() {
        return Vaitro;
    }

    public void setVaitro(boolean Vaitro) {
        this.Vaitro = Vaitro;
    }

    public String getHinh() {
        return Hinh;
    }

    public void setHinh(String Hinh) {
        this.Hinh = Hinh;
    }

    public String getQRcode() {
        return QRcode;
    }

    public void setQRcode(String QRcode) {
        this.QRcode = QRcode;
    }
    
           
}
