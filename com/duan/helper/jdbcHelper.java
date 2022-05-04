/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.duan.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author THANH
 */
public class jdbcHelper {
     static String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
     static String dburl="jdbc:sqlserver://localhost;database=QLPT1";
     static String username="sa";
     static String password="songlong";
     static{
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
    public static PreparedStatement getStmt(String sql,Object...args)throws SQLException{
        Connection con=DriverManager.getConnection(dburl, username, password);
        PreparedStatement stmt;
        if(sql.trim().startsWith("{"))
            stmt=con.prepareCall(sql);    
        else 
            stmt=con.prepareStatement(sql);
        for(int i=0; i<args.length;i++){
            stmt.setObject(i+1, args[i]);
        }
        return stmt;
    }
    
    public static ResultSet query(String sql, Object...args) throws SQLException{
         PreparedStatement stmt = jdbcHelper.getStmt(sql,args);
         return stmt.executeQuery();
    }
    public static Object value(String sql,Object...args){
         try {
             ResultSet rs  = jdbcHelper.query(sql, args);
             if(rs.next()){
                 return rs.getObject(0);
             }
             rs.getStatement().getConnection().close();
             return null;
             
         } catch (Exception e) {
    throw new RuntimeException(e);
         }
     }
    public static int update(String sql,Object...args){
        try {
            PreparedStatement stmt= jdbcHelper.getStmt(sql, args);
            try{
               return stmt.executeUpdate();
            }finally{
                stmt.getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
   
    }

