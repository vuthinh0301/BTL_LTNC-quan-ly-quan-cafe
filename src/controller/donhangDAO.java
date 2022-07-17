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
import model.chitietDH;
import model.donhang;
import model.dsDonHang;
import model.sanpham;

/**
 *
 * @author Admin
 */
public class donhangDAO {
    ConnectDB con = new ConnectDB();
    Connection cnn = con.connect(); 
    
    public int addHoadon(donhang dh ,String gio){
        try {
            String sql = "INSERT INTO [order](table_id,create_at,status) VALUES(?,?,?)";
            PreparedStatement pr = cnn.prepareStatement(sql);
            pr.setInt(1, dh.getTable_id());
            pr.setString(2, gio);
            pr.setInt(3, dh.getStatus());
            int row = pr.executeUpdate();
            return row;
        } catch (SQLException ex) {
            Logger.getLogger(donhangDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
     public int GetMaHD(int id){
        String sql;
        int mahd = 0;
            sql = "select * from [order] where table_id = ? and status = 0";
        try{
          PreparedStatement pr = cnn.prepareStatement(sql);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            while(rs.next()){
                mahd = rs.getInt(1);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Không lấy được danh sách thực đơn !");
        }
        return mahd;        
    } 
      public List<donhang> getdsHD(){
        List<donhang> lsdonhang = new ArrayList<>();
        try {
            String sql = "select *,FORMAT( create_at, 'dd-MM-yyyy hh:mm:ss tt') AS 'create' from [order] WHERE status = 1";
            PreparedStatement pr = cnn.prepareStatement(sql);
           
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {                
               donhang dh =new donhang(rs.getInt(1), rs.getFloat(2), rs.getInt(3), rs.getInt(4), rs.getString("create"), rs.getFloat(6), rs.getInt(7));
               lsdonhang.add(dh);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(donhangDAO.class.getName()).log(Level.SEVERE, null, ex);
           
        }
          return lsdonhang;
    }
    
    public donhang getTableId(int id){
        donhang dh =null;
        try {
            String sql = "select *,FORMAT( create_at, 'dd-MM-yyyy hh:mm:ss tt') AS 'create' from [order] where table_id = ? and status = 0";
            PreparedStatement pr = cnn.prepareStatement(sql);
            pr.setInt(1, id);
           
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {                
                dh =new donhang(rs.getInt(1), rs.getFloat(2), rs.getInt(3), rs.getInt(4), rs.getString("create"), rs.getFloat(6), rs.getInt(7));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(donhangDAO.class.getName()).log(Level.SEVERE, null, ex);
           
        }
          return dh;
    }
    public ArrayList<dsDonHang> GetDsOrder(int ma){
       ArrayList<dsDonHang> arrDs = null;
        String sql;
            sql = "Select ct.pro_id,order_id, quantity,name, ct.price From order_details AS ct INNER JOIN product AS td ON ct.pro_id = td.id Where ct.order_id = '"+ma+"'";
        try{
            Statement st = cnn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            arrDs = new ArrayList<dsDonHang>();
            while(rs.next()){
                dsDonHang order = new dsDonHang(rs.getInt(1), rs.getInt(2), rs.getInt(3),rs.getString(4),rs.getFloat(5));
               
                arrDs.add(order);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Không lấy được danh sách Order !");
        }
        return arrDs;        
    }
     public int HuyHD(donhang hd){
        int update = 0;
        String sql = "Delete From [order] WHERE id = '"+hd.getId()+"'";
        try{
            Statement st = cnn.createStatement();
            update = st.executeUpdate(sql);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Thanh toán không thành công !");
        }
        return update;        
    }    
    public int InsertChiTietHD(chitietDH cthd){
        int insert = 0;
        String sql = "Insert into order_details  values (?,?,?,?)";
        try{
           PreparedStatement pr = cnn.prepareStatement(sql);
           pr.setInt(1, cthd.getOrder_id());
           pr.setInt(2, cthd.getPro_id());
           pr.setInt(3, cthd.getQuantity());
           pr.setFloat(4, cthd.getPrice());
            insert = pr.executeUpdate();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Thêm chi tiết hóa đơn không thành công !"+ex.toString());
        }
        return insert;
    }
     public int updatechitietHD(chitietDH cthd){
          int update =0;
            String sql = "UPDATE order_details SET quantity=?,price=? WHERE id =?";
         try {
          
            PreparedStatement pr = cnn.prepareStatement(sql);
            
            pr.setInt(1, cthd.getQuantity());
            pr.setFloat(2, cthd.getPrice());
            pr.setInt(3, cthd.getId());
            update = pr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(donhangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return update;
     }
    public chitietDH GetDsChiTiet(int ma, int maban){
        chitietDH arrDs = null;
        String sql;

            sql = "Select quantity, price, ct.id From order_details AS ct INNER JOIN [order] AS hd ON ct.order_id = hd.id Where pro_id = '"+ma+"' AND table_id = '"+maban+"' AND hd.status = 0";
        try{
            Statement st = cnn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                arrDs = new chitietDH();
                arrDs.setQuantity(rs.getInt(1));
                arrDs.setPrice(rs.getInt(2));
                arrDs.setId(rs.getInt(3));
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Không lấy được danh sách Order !");
        }
        return arrDs;        
    } 
     public int DeletePro(int mamon, int mahd, int maban){
        int check = 0;
        try{
            String sql;
            sql = "Delete From order_details Where pro_id = '"+mamon+"' AND order_id = '"+mahd+"'";
            Statement st = cnn.createStatement();
            st.executeUpdate(sql);
            check = 1;
            if(CheckDsMon(mahd, maban) == 0){
                check = 2;
            }
        }catch(SQLException ex){
            
        }
        return check;
    }
    public int CheckDsMon(int mahd, int maban){
        String sql;
        int dem = 0;
            sql = "Select * From [order] AS hd INNER JOIN order_details AS ct ON ct.order_id = hd.id Where table_id = '"+maban+"' AND ct.order_id = '"+mahd+"' AND status = 0";
        try{
            Statement st = cnn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                dem++;
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Không lấy được danh sách hóa đơn !");
        }
        return dem;        
    }        
   public int updateHoadon(donhang dh){
       int row = 0;
        try {
            String sql = "UPDATE [order] SET discount =? WHERE id =?";
            PreparedStatement pr = cnn.prepareStatement(sql);
            pr.setFloat(1, dh.getDiscount());
            pr.setInt(2, dh.getId());
            row = pr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(donhangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
   }
    public int ThanhToan(donhang dh){
       int row = 0;
        try {
            String sql = "UPDATE [order] SET total_price =?,status=1 WHERE id =?";
            PreparedStatement pr = cnn.prepareStatement(sql);
            pr.setFloat(1, dh.getTotal_price());
            pr.setInt(2, dh.getId());
            row = pr.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(donhangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
   }
       public List<sanpham> GetProByID(){
        List<sanpham> arrDs = new ArrayList<>();
        String sql;
            sql = "SELECT name, id FROM product where id in (Select pro_id From order_details)";
        try{
            Statement st = cnn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                sanpham order = new sanpham();
                order.setName(rs.getString(1));
                order.setId(rs.getInt(2));
                
                arrDs.add(order);
            }
        }catch(SQLException ex){
        }
        return arrDs;        
    } 
        public List<dsDonHang> GetGiaSoLuong(int ma){
        List<dsDonHang> arrDs = null;
        String sql;
            sql = "Select ct.price, ct.quantity, td.name From order_details AS ct INNER JOIN [order] AS hd ON ct.order_id = hd.id INNER JOIN product AS td ON td.id = ct.pro_id Where hd.status = 1 AND ct.pro_id = '"+ma+"'";
        try{
            
            Statement st = cnn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            arrDs = new ArrayList<>();
            while(rs.next()){
                
                dsDonHang order = new dsDonHang();
                order.setPrice(rs.getFloat(1));
                order.setQuantity(rs.getInt(2));
                order.setPro_name(rs.getString(3));
              
                arrDs.add(order);
            }
        }catch(SQLException ex){
        }
        return arrDs;        
    }
           public List<dsDonHang> GetHdByDate(String d1,String d2, int m){
        ArrayList<dsDonHang> arrDs = new ArrayList<>();
        String sql;
        
            sql = "Select ct.price, ct.quantity, td.name From order_details AS ct INNER JOIN [order] AS hd ON ct.order_id = hd.id INNER JOIN product AS td ON td.id = ct.pro_id Where hd.status = 1 AND hd.create_at BETWEEN '"+d1+"' AND '"+d2+" "+"23:59"+"' AND ct.pro_id ='"+m+"'";
        try{
            
            Statement st = cnn.createStatement();
            ResultSet rs = st.executeQuery(sql);
          
            while(rs.next()){
                dsDonHang order = new dsDonHang();
                order.setPrice(rs.getFloat(1));
                order.setQuantity(rs.getInt(2));
                order.setPro_name(rs.getString(3));
                arrDs.add(order);
            }
        }catch(SQLException ex){
        }
        return arrDs;        
    } 
            public  List<dsDonHang> GetCtHDByDate(int ma, String d1, String d2){
        List<dsDonHang> arrDs = new ArrayList<>();;
        String sql;

       
            sql = "Select ct.pro_id,order_id, quantity,name, ct.price,FORMAT(hd.create_at,'dd-MM-yyyy hh:mm:ss tt') AS 'create' From order_details AS ct INNER JOIN product AS td ON ct.pro_id = td.id  INNER JOIN [order] AS hd ON hd.id = ct.order_id Where ct.order_id = '"+ma+"' AND hd.create_at BETWEEN '"+d1+"' AND '"+d2+" "+"23:59"+"'";
        try{
            Statement st = cnn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                dsDonHang order = new dsDonHang(rs.getInt(1), rs.getInt(2), rs.getInt(3),rs.getString(4),rs.getFloat(5),rs.getString("create"));
               
                arrDs.add(order);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Không lấy được danh sách chi tiết hoa đơn !"+ex.toString());
        }
        return arrDs;        
    }     
}
