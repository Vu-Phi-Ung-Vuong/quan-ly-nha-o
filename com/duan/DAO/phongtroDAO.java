/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duan.DAO;

import com.duan.model.phongtro;
import com.duan.helper.jdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO-PC
 */
public class phongtroDAO extends DuanDAO <phongtro,Integer> {
    String INSERT_SQL="INSERT INTO Phongtro(MaPT,LoaiPT,DienTich,GiaPhong,GhiChu)VALUSE(?,?,?,?,?)";
    String UPDATE_SQL="UPDATE Phongtro SET LoaiPT=?,DienTich=?,GiaPhong=?,GhiChu=?WHERE MaPT=?";
    String DELETE_SQL="DELETE FROM Phongtro WHERE MaPT=?";
    String SELECT_ALL_SQL="SELECT*FROM Phongtro";
    String SELECT_BY_ID_SQL="SELECT*FROM Phongtro WHERE MaPT=?";
    @Override public void insert(phongtro entity) {
        String sql="INSERT INTO Phongtro (LoaiPT,DienTich,GiaPhong,GhiChu) VALUES (?,?,?,?)";
        jdbcHelper.update(sql, 
             
                entity.getLoaiPT(),
                entity.getDienTich(),
                entity.getGiaPhong(),
                entity.getGhiChu());
    }

    @Override
    public void update(phongtro entity) {
        String sql="UPDATE Phongtro SET LoaiPT=?,DienTich=?,GiaPhong=?,GhiChu=? WHERE MaPT=?";
        jdbcHelper.update(sql, 
                entity.getLoaiPT(),
                entity.getDienTich(),
                entity.getGiaPhong(),
                entity.getGhiChu(),
                entity.getMaPT());
    }

    @Override
    public void delete(Integer id) {
         String sql="DELETE FROM Phongtro WHERE MaPT=?";
        jdbcHelper.update(sql, id);
    }
    public void delete(String id) {
         String sql="DELETE FROM Phongtro WHERE MaPT=?";
        jdbcHelper.update(sql, id);
    }

    @Override
    public List<phongtro> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    public phongtro selectById(String id) {
       List<phongtro> list=this.selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }
    public phongtro selectById(Integer id) {
       List<phongtro> list=this.selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<phongtro> selectBySql(String sql, Object... args) {
        List<phongtro>list=new ArrayList<phongtro>();
        try{
            ResultSet rs=jdbcHelper.query(sql, args);
            while(rs.next()){
                phongtro entity=new phongtro();
                entity.setMaPT(rs.getString("MaPT"));
                entity.setLoaiPT(rs.getString("LoaiPT"));
                entity.setDienTich(rs.getFloat("DienTich"));
                entity.setGiaPhong(rs.getDouble("GiaPhong"));
                entity.setGhiChu(rs.getString("GhiChu"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        }catch(Exception e){
            throw  new RuntimeException(e);
        }
    }
    public phongtro selectByID(int id) {
       List<phongtro> list=this.selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    
}
