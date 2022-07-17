/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Sql.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.khachhang;

/**
 *
 * @author Admin
 */
public class datbanDAO {

    ConnectDB con = new ConnectDB();
    Connection cnn = con.connect();

    public int add(khachhang kh) {
        try {
            String sql = "INSERT INTO customer VALUES(?,?,?,?,?,?,?)";
            PreparedStatement pr = cnn.prepareStatement(sql);
            pr.setString(1, kh.getName());
            pr.setInt(2, kh.getTable_id());
            pr.setString(3, kh.getPhone());
            pr.setString(4, kh.getEmail());
            pr.setString(5, kh.getDate());
            pr.setInt(6, kh.getQuantity());
            pr.setInt(7, kh.getStatus());
            int row = pr.executeUpdate();
            return row;
        } catch (SQLException ex) {
            Logger.getLogger(datbanDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        
    }
    public int edit(khachhang kh) {
        try {
            String sql = "UPDATE customer SET name =?,table_id=?,phone=?,email=?,create_at=?,quantity=? WHERE id=?";
            PreparedStatement pr = cnn.prepareStatement(sql);
            pr.setString(1, kh.getName());
            pr.setInt(2, kh.getTable_id());
            pr.setString(3, kh.getPhone());
            pr.setString(4, kh.getEmail());
            pr.setString(5, kh.getDate());
            pr.setInt(6, kh.getQuantity());
            pr.setInt(7, kh.getId());
            int row = pr.executeUpdate();
            return row;
        } catch (SQLException ex) {
            Logger.getLogger(datbanDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        
    }
     public int editstatus(khachhang kh) {
        try {
            String sql = "UPDATE customer SET status =? WHERE id=?";
            PreparedStatement pr = cnn.prepareStatement(sql);
           
            pr.setInt(1, kh.getStatus());
            
            pr.setInt(2, kh.getId());
            int row = pr.executeUpdate();
            return row;
        } catch (SQLException ex) {
            Logger.getLogger(datbanDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        
    }
    
    public List<khachhang> read(){
        List<khachhang> lskh = new ArrayList<>();
        try {
            String sql = "SELECT *,FORMAT(create_at, ' hh:mm:ss tt dd-MM-yyyy') AS 'create' FROM customer";
            PreparedStatement pr = cnn.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {                
                khachhang kh = new khachhang(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString("create"), rs.getInt(7),rs.getInt("status"));
                lskh.add(kh);
            }
                    } catch (SQLException ex) {
            Logger.getLogger(datbanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lskh;
    }
     public khachhang getId(int id){
       khachhang kh = null;
        try {
            String sql = "SELECT *,FORMAT(create_at, 'yyyy-MM-dd') AS 'create',FORMAT(create_at, 'HH') AS 'gio',FORMAT(create_at, 'mm') AS 'phut' FROM customer where id =?";
            PreparedStatement pr = cnn.prepareStatement(sql);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {                
                 kh = new khachhang(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString("create"), rs.getInt(7),rs.getString("gio"),rs.getString("phut"),rs.getInt("status"));
                
            }
                    } catch (SQLException ex) {
            Logger.getLogger(datbanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kh;
    }
     public khachhang getTable_id(int id){
       khachhang kh = null;
        try {
            String sql = "SELECT *,FORMAT(create_at, ' hh:mm:ss tt dd-MM-yyyy') AS 'create' FROM customer where table_id =?";
            PreparedStatement pr = cnn.prepareStatement(sql);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {                
                 kh = new khachhang(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString("create"), rs.getInt(7),rs.getInt("status"));
                
            }
                    } catch (SQLException ex) {
            Logger.getLogger(datbanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kh;
    }
     
     public boolean delete(ArrayList<Integer> lisdatban){
            boolean check = false;
          try {
              for (int id : lisdatban) {
                  String sql = "DELETE FROM customer WHERE id =? and status =0 or status =2";
                  PreparedStatement pr = cnn.prepareStatement(sql);
              pr.setInt(1, id);
              pr.executeUpdate();
              }
              check =true;
             
          } catch (SQLException ex) {
             
                System.err.println("co loi xay ra!");
             
          }  
           return check;
    }
         public List<khachhang> Search(String ten){
       List<khachhang> lskhachang =  new ArrayList<>();
             String sql = "SELECT *,FORMAT(create_at, ' hh:mm:ss tt dd-MM-yyyy') AS 'create' FROM customer WHERE name LIKE N'%"+ten+"%'";
        try{
            Statement st = cnn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
              khachhang kh = new khachhang(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString("create"), rs.getInt(7),rs.getInt("status"));
                lskhachang.add(kh);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "lỗi !");
        }
        return lskhachang;
    } 
}
