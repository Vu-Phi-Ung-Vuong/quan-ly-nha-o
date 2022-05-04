/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duan.DAO;

import com.duan.helper.jdbcHelper;
import com.duan.model.ThanhToan;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author ASUS
 */
public class ThanhToanDAO extends DuanDAO<ThanhToan, String> {

    String insert_sql = "insert into ThanhToan(MaCP,MaKH,DonGiaTT,NgayDongTien,TrangThai) values(?,?,?,?,?)";
    String update_sql = "update ThanhToan set DonGiaTT=?, NgayDongTien=?, TrangThai=? where MaCP=? and MaKH=?";
    String delete_sql = "Delete from ThanhToan where MaKH=? and MaCP=?";
    String select_all = "select * from ThanhToan  ";
    String select_all1 = "select * from ThanhToan where TrangThai = '1' ";
    String select_all2 = "select * from ThanhToan where TrangThai = '0' ";
    String selectbyId = "select * from ThanhToan where MaCP=?";
    String selectbyId1 = "select * from ThanhToan where MaCP=?";

    @Override
    public void insert(ThanhToan entity) {
        jdbcHelper.update(insert_sql,
                entity.getMaCP(),
                entity.getMaKH(),
                entity.getDongia(),
                entity.getNgaydongtien(),
                entity.isTrangthai());
    }

    @Override
    public void update(ThanhToan entity) {
        jdbcHelper.update(update_sql,
                entity.getDongia(),
                entity.getNgaydongtien(),
                entity.isTrangthai(),
                entity.getMaCP(),
                entity.getMaKH());
    }

    @Override
    public void delete(String MaKH) {
        jdbcHelper.update(delete_sql, MaKH);
    }

    public void delete1(String MaKH, String MaCP) {
        jdbcHelper.update(delete_sql, MaKH, MaCP);
    }

    @Override
    public List<ThanhToan> selectAll() {
        return this.selectBySql(select_all1);
    }

    public List<ThanhToan> selectAll1() {
        return this.selectBySql(select_all);
    }

    public List<ThanhToan> selectAll2() {
        return this.selectBySql(select_all2);
    }

    @Override
    public ThanhToan selectById(String key) {
        List<ThanhToan> list = this.selectBySql(selectbyId, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public ThanhToan selectById2(String key) {
        List<ThanhToan> list = this.selectBySql(selectbyId1, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<ThanhToan> selectBySql(String Sql, Object... args) {
        List<ThanhToan> list = new ArrayList<ThanhToan>();
        try {
            ResultSet rs = jdbcHelper.query(Sql, args);
            while (rs.next()) {
                ThanhToan th = new ThanhToan();
                th.setMaCP(rs.getString("MaCP"));
                th.setMaKH(rs.getString("MaKH"));
                th.setDongia(rs.getFloat("DonGiaTT"));
                th.setNgaydongtien(rs.getDate("NgayDongTien"));
                th.setTrangthai(rs.getBoolean("TrangThai"));
                list.add(th);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
