/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duan.DAO;

import com.duan.model.ChiPhi;
import com.duan.helper.jdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO-PC
 */
public class QLChiPhiDao extends DuanDAO <ChiPhi,String> {
    String INSERT_SQL="INSERT INTO ChiPhi(TenCP,NgayTao,NgayHetHan,MaNV)VALUSE(?,?,?,?)";
    String UPDATE_SQL="UPDATE ChiPhi SET TenCP=?,NgayTao=?,NgayHetHan=?,MaNV=?WHERE MaCP=?";
    String DELETE_SQL="DELETE FROM ChiPhi WHERE MaCP=?";
    String SELECT_ALL_SQL="SELECT*FROM ChiPhi";
    String SELECT_BY_ID_SQL="SELECT*FROM ChiPhi WHERE MaCP=?";
    
     public void insert(ChiPhi entity) {
        String sql="INSERT INTO ChiPhi (TenCP,NgayTao,NgayHetHan,MaNV) VALUES (?,?,?,?)";
        jdbcHelper.update(sql, 
              
                entity.getTenCP(),
                entity.getNgaytao(),
                entity.getNgayhethan(),
                entity.getMaNV());
    }

   
    public void update(ChiPhi entity) {
        String sql="UPDATE ChiPhi SET TenCP=?,NgayTao=?,NgayHetHan=?,MaNV=? WHERE MaCP=?";
        jdbcHelper.update(sql,                  
                entity.getTenCP(),
                entity.getNgaytao(),
                entity.getNgayhethan(),
                entity.getMaNV(),
                entity.getMaCP());
    }

   
    public void delete(String id) {
         String sql="DELETE FROM ChiPhi WHERE MaCP=?";
        jdbcHelper.update(sql, id);
    }

   
    public List<ChiPhi> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    
    public ChiPhi selectById(String id) {
       List<ChiPhi> list=this.selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    
    protected List<ChiPhi> selectBySql(String sql, Object... args) {
        List<ChiPhi>list=new ArrayList<ChiPhi>();
        try{
            ResultSet rs=jdbcHelper.query(sql, args);
            while(rs.next()){
                ChiPhi entity=new ChiPhi();
                entity.setMaCP(rs.getString("MaCP"));
                entity.setTenCP(rs.getString("TenCP"));
                entity.setNgaytao(rs.getDate("NgayTao"));
                entity.setNgayhethan(rs.getDate("NgayHetHan"));
                entity.setMaNV(rs.getString("MaNV"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        }catch(Exception e){
            throw  new RuntimeException(e);
        }
    }
    public ChiPhi selectByID(int id) {
       List<ChiPhi> list=this.selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }





 
}
