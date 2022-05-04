/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duan.model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class Khachhang {

    private String MaKH;
    private String HoTen;
    private boolean gioiTinh;
    private Date NgaySinh;
    private String DienThoai;
    private String Email;
    private String CMND;
    private String MaPT;
    private String MaHD;
    private String Hinh;

    @Override
    public String toString() {
        return this.MaKH;
    }

    public String getHinh() {
        return Hinh;
    }

    public void setHinh(String Hinh) {
        this.Hinh = Hinh;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public String getDienThoai() {
        return DienThoai;
    }

    public void setDienThoai(String DienThoai) {
        this.DienThoai = DienThoai;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public String getMaPT() {
        return MaPT;
    }

    public void setMaPT(String MaPT) {
        this.MaPT = MaPT;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public Khachhang(String MaKH, String HoTen, boolean gioiTinh, Date NgaySinh, String DienThoai, String Email, String CMND, String MaPT, String MaHD) {
        this.MaKH = MaKH;
        this.HoTen = HoTen;
        this.gioiTinh = gioiTinh;
        this.NgaySinh = NgaySinh;
        this.DienThoai = DienThoai;
        this.Email = Email;
        this.CMND = CMND;
        this.MaPT = MaPT;
        this.MaHD = MaHD;
    }

    public Khachhang() {
    }
    public Object[] toObject(){
        return new Object[]{
            MaKH,HoTen,gioiTinh,NgaySinh,DienThoai,Email,CMND,MaPT,MaHD
        };
    }
}
