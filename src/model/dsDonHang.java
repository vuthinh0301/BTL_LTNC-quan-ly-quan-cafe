/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Admin
 */
public class dsDonHang {
      private int pro_id,order_id,quantity;
    private String pro_name;
    private  float price;
private  String date;

    public dsDonHang(int pro_id, int order_id, int quantity, String pro_name, float price, String date) {
        this.pro_id = pro_id;
        this.order_id = order_id;
        this.quantity = quantity;
        this.pro_name = pro_name;
        this.price = price;
        this.date = date;
    }
    public dsDonHang() {
    }

    public dsDonHang(int pro_id, int order_id, int quantity, String pro_name, float price) {
        this.pro_id = pro_id;
        this.order_id = order_id;
        this.quantity = quantity;
        this.pro_name = pro_name;
        this.price = price;
    }

    public int getPro_id() {
        return pro_id;
    }

    public void setPro_id(int pro_id) {
        this.pro_id = pro_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
  
}
