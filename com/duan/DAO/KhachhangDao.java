/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duan.DAO;

import com.duan.helper.jdbcHelper;
import com.duan.model.Khachhang;
import com.duan.model.ThanhToan;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class KhachhangDao extends DuanDAO<Khachhang, String>{

   
   public void insert(Khachhang Model) {
        String sql = "INSERT INTO Khachhang(HotenKH,GioiTinh,SoDT,Email,CMND,MaPT,MaHD,Hinh)VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcHelper.update(sql,
              
                Model.getHoTen(),
                Model.isGioiTinh(),
                Model.getDienThoai(),
                Model.getEmail(),
                Model.getCMND(),
                Model.getMaPT(),
                Model.getMaHD(),
                Model.getHinh()
        );
    } 
  
    public void update(Khachhang Model) {
         String sql = "UPDATE Khachhang SET HotenKH=?,GioiTinh=?,SoDT=?,Email=?,CMND=?,MaPT=?,MaHD=?,Hinh=? WHERE MaKH=?";
        jdbcHelper.update(sql,
                Model.getHoTen(),
                Model.isGioiTinh(),
                Model.getDienThoai(),
                Model.getEmail(),
                Model.getCMND(),
                Model.getMaPT(),
                Model.getMaHD(),
                Model.getHinh(),
                Model.getMaKH());
    }

   
   public void delete(String key) {
        String sql = "delete from Khachhang where MaKH=?";
        jdbcHelper.update(sql,key);
    }

   
    public List<Khachhang> selectAll() {
       String sql = "SELECT * FROM Khachhang";
        return selectBySql(sql);
    }

    public Khachhang selectById(String MaKH) {
        String sql = "SELECT * FROM Khachhang WHERE MaKH=?";
        List<Khachhang> list = this.selectBySql(sql, MaKH);
        return list.size() > 0 ? list.get(0) : null;
    
    }

    public Khachhang selectByHD(Integer MaKH) {
        String sql = "SELECT * FROM Khachhang WHERE MaHD=?";
        List<Khachhang> list = this.selectBySql(sql, MaKH);
        return list.size() > 0 ? list.get(0) : null;
    
    }
   
    public List<Khachhang> selectBySql(String sql, Object... args) {
       List<Khachhang> list = new ArrayList<Khachhang>();
        try {
                ResultSet rs = null;
                try {
                    rs = jdbcHelper.query(sql, args);
                     
 
                while (rs.next()) {
                        Khachhang model = readFromResultSet(rs);
                        list.add(model);
                    }
                } finally {
                    rs.getStatement().getConnection().close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        return list;
    }
    private  Khachhang readFromResultSet(ResultSet rs) throws SQLException {
            Khachhang model = new Khachhang();
            model.setMaKH(rs.getString("MaKH"));
            model.setHoTen(rs.getString("HotenKH"));
            model.setGioiTinh(rs.getBoolean("GioiTinh") );
            model.setEmail(rs.getString("Email"));
            model.setDienThoai(rs.getString("SoDT"));
            model.setCMND(rs.getString("CMND"));
            model.setMaPT(rs.getString("MaPT"));
            model.setMaHD(rs.getString("MaHD"));
            model.setHinh(rs.getString("Hinh"));
            return model;
        }

 

    public Khachhang selectById1(String maPT) {
        String sql = "SELECT * FROM Khachhang WHERE maPT=?";
        List<Khachhang> list = this.selectBySql(sql, maPT);
        return list.size() > 0 ? list.get(0) : null;
    
    } 
   
}
    

