/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duan.model;

import com.duan.helper.Xdate;
import java.util.Date;

public class HopDong {

    private String maHD;
    private Date NgayBD = Xdate.now();
    private Date NgayKT;
    private String ghiChu;
    private String maNV;

    @Override
    public String toString() {
        return this.maHD;
    }
    
    
    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public Date getNgayBD() {
        return NgayBD;
    }

    public void setNgayBD(Date NgayBD) {
        this.NgayBD = NgayBD;
    }

    public Date getNgayKT() {
        return NgayKT;
    }

    public void setNgayKT(Date NgayKT) {
        this.NgayKT = NgayKT;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }


    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }
     public Object[] toObject(){
        return new Object[]{
            maHD,NgayBD,NgayKT,ghiChu,maNV
        };
    }
}
