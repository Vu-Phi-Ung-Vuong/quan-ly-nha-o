/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duan.model;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class ThanhToan {

    public String MaCP;
    public String MaKH;
    public Float dongia;
    public Date ngaydongtien;
    private boolean trangthai = true;

    public ThanhToan() {

    }

    public ThanhToan(String MaCP, String MaKH, Float dongia, Date ngaydongtien, boolean trangthai) {
        this.MaCP = MaCP;
        this.MaKH = MaKH;
        this.dongia = dongia;
        this.ngaydongtien = ngaydongtien;
        this.trangthai = trangthai;
    }

    public String getMaCP() {
        return MaCP;
    }

    public void setMaCP(String MaCP) {
        this.MaCP = MaCP;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public Float getDongia() {
        return dongia;
    }

    public void setDongia(Float dongia) {
        this.dongia = dongia;
    }

    public Date getNgaydongtien() {
        return ngaydongtien;
    }

    public void setNgaydongtien(Date ngaydongtien) {
        this.ngaydongtien = ngaydongtien;
    }

    public boolean isTrangthai() {
        return trangthai;
    }

    public void setTrangthai(boolean trangthai) {
        this.trangthai = trangthai;
    }
    public Object[] toObject(){
        return new Object[]{
            MaCP,MaKH,dongia,ngaydongtien,trangthai
        };
    }
}
