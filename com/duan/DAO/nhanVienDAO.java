/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duan.DAO;


import com.duan.helper.jdbcHelper;
import com.duan.model.Nhanvien;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author THANH
 */
public class nhanVienDAO extends DuanDAO<Nhanvien, String>{
public void insert(Nhanvien model){
        String sql="INSERT INTO Nhanvien ( MaNV, Hoten, GioiTinh, Email, Ngaysinh, SoDT, Password, Vaitro, Hinh) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcHelper.update(sql, 
                model.getMaNV(),
                model.getHoten(),
                model.isGioitinh(), 
                model.getEmail(), 
                model.getNgaysinh(), 
                model.getSDT(),
                model.getPassword(), 
                model.isVaitro(),
                model.getHinh());
    }
    
    public void update(Nhanvien model){
        String sql="UPDATE Nhanvien SET Hoten=?, GioiTinh=?, Email=?, Ngaysinh=?, SoDT=?, Password=?, Vaitro=?, Hinh=? WHERE MaNV=?";
        jdbcHelper.update(sql,
                model.getHoten(),
                model.isGioitinh(), 
                model.getEmail(), 
                model.getNgaysinh(), 
                model.getSDT(),
                model.getPassword(), 
                model.isVaitro(),
                model.getHinh(),
                model.getMaNV());
    }
    
    public void delete(String MaNV){
        String sql="DELETE FROM Nhanvien WHERE MaNV=?";
        jdbcHelper.update(sql, MaNV);
    }
    
    public List<Nhanvien> selectAll() {
        String sql="SELECT * FROM Nhanvien";
        return this.selectBySql(sql);
    }
    
    public Nhanvien selectById(String MaNV){
        String sql="SELECT * FROM Nhanvien WHERE MaNV=?";
        List<Nhanvien> list = this.selectBySql(sql, MaNV);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    protected List<Nhanvien> selectBySql(String sql, Object...args){
        List<Nhanvien> list =new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = jdbcHelper.query(sql, args);
                while(rs.next()){
                    Nhanvien entity=new Nhanvien();
                    entity.setMaNV(rs.getString("MaNV"));
                    entity.setHoten(rs.getString("Hoten"));
                    entity.setGioitinh(rs.getBoolean("GioiTinh"));
                    entity.setEmail(rs.getString("Email"));
                    entity.setNgaysinh(rs.getDate("Ngaysinh"));
                    entity.setSDT(rs.getString("SoDT"));
                    entity.setPassword(rs.getString("Password"));
                    entity.setVaitro(rs.getBoolean("Vaitro"));
                    entity.setHinh(rs.getString("Hinh"));
                    entity.setQRcode(rs.getString("QRcode"));
                    list.add(entity);
                }
            } 
            finally{
                rs.getStatement().getConnection().close();
            }
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }
    public void updateQRcode(Nhanvien nv){
        String sql="UPDATE Nhanvien set QRcode=? where MaNV=?";
        jdbcHelper.update(sql,
                nv.getQRcode(),
                nv.getMaNV());
    }
    


}



  
    

