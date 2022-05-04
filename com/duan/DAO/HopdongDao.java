/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duan.DAO;

import com.duan.DAO.DuanDAO;
import com.duan.helper.jdbcHelper;
import com.duan.model.HopDong;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HungThinh02
 */
public class HopdongDao extends DuanDAO<HopDong, String> {


    public void insert(HopDong Model) {
        String sql = "INSERT INTO Hopdong(NgayBatDau,NgayKetThuc,GhiChu,MaNV)VALUES(?,?,?,?)";
        jdbcHelper.update(sql,
              
                Model.getNgayBD(),
                Model.getNgayKT(),
                Model.getGhiChu(),
                Model.getMaNV());
    }


    public void update(HopDong Model) {
        String sql = "UPDATE Hopdong SET NgayBatDau=?,NgayKetThuc=?,GhiChu=?,MaNV=? WHERE MaHD=?";
        jdbcHelper.update(sql,
                Model.getNgayBD(),
                Model.getNgayKT(),
                Model.getGhiChu(),
                Model.getMaNV(),
                Model.getMaHD());
    }

 
    public void delete(String key) {
        String sql = "DELETE FROM Hopdong WHERE MaHD=?";
        jdbcHelper.update(sql, key);
    }


    public List<HopDong> selectAll() {
        String sql= "select * from Hopdong";
        return selectBySql(sql);
    }

    public HopDong selectById(String MaHD) {
        String sql = "SELECT * FROM Hopdong WHERE MaHD=?";
        List<HopDong> list = this.selectBySql(sql, MaHD);
        return list.size() > 0 ? list.get(0) : null;
    }


    protected List<HopDong> selectBySql(String sql, Object... args) {
        List<HopDong> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = jdbcHelper.query(sql, args);
                while (rs.next()) {
                    HopDong model = readFromResultSet(rs);
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

    private HopDong readFromResultSet(ResultSet rs) throws SQLException {
        HopDong model = new HopDong();
        model.setMaHD(rs.getString("MaHD"));
        model.setNgayBD(rs.getDate("NgayBatDau"));
        model.setNgayKT(rs.getDate("NgayKetThuc"));
        model.setGhiChu(rs.getString("GhiChu"));
        model.setMaNV(rs.getString("MaNV"));
        return model;
    }




 
}
