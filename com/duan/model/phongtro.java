/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duan.model;

/**
 *
 * @author LENOVO-PC
 */
public class phongtro {
    String maPT;
    String loaiPT;
    double giaPhong;
    String ghiChu;
    float dienTich;
    public phongtro() {
    }

    public phongtro(String maPT, String loaiPT, double giaPhong, String ghiChu, float dienTich) {
        this.maPT = maPT;
        this.loaiPT = loaiPT;
        this.giaPhong = giaPhong;
        this.ghiChu = ghiChu;
        this.dienTich = dienTich;
    }

    public String getMaPT() {
        return maPT;
    }

    public void setMaPT(String maPT) {
        this.maPT = maPT;
    }

    @Override
    public String toString() {
        return this.maPT;
    }

   

   

    public float getDienTich() {
        return dienTich;
    }

    public void setDienTich(float dienTich) {
        this.dienTich = dienTich;
    }

    


  

    public String getLoaiPT() {
        return loaiPT;
    }

    public void setLoaiPT(String loaiPT) {
        this.loaiPT = loaiPT;
    }

    public double getGiaPhong() {
        return giaPhong;
    }

    public void setGiaPhong(double giaPhong) {
        this.giaPhong = giaPhong;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    
    
}
