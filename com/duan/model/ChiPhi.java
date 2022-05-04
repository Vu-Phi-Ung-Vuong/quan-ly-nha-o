/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duan.model;

import com.duan.helper.Xdate;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class ChiPhi {

    String maCP;
    String tenCP;
    Date ngaytao = Xdate.now();
    Date ngayhethan;
    String maNV;

    public ChiPhi() {
    }

    @Override
    public String toString() {
        return this.maCP;
    }

    public ChiPhi(String maCP, String tenCP, Date ngaytao, Date ngayhethan, String maNV) {
        this.maCP = maCP;
        this.tenCP = tenCP;
        this.ngaytao = ngaytao;
        this.ngayhethan = ngayhethan;
        this.maNV = maNV;
    }

    public String getMaCP() {
        return maCP;
    }

    public void setMaCP(String maCP) {
        this.maCP = maCP;
    }

    public String getTenCP() {
        return tenCP;
    }

    public void setTenCP(String tenCP) {
        this.tenCP = tenCP;
    }

    public Date getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(Date ngaytao) {
        this.ngaytao = ngaytao;
    }

    public Date getNgayhethan() {
        return ngayhethan;
    }

    public void setNgayhethan(Date ngayhethan) {
        this.ngayhethan = ngayhethan;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public Object[] toObject() {
        return new Object[]{
            maCP,tenCP, maNV, ngayhethan,ngaytao
        };
    }

}
