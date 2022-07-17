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
public class chitietDH {
    private int id;
    private int order_id;
    private int pro_id;
    private int quantity;
    private  float price;

    public chitietDH() {
    }

    public chitietDH(int id, int order_id, int pro_id, int quantity, float price) {
        this.id = id;
        this.order_id = order_id;
        this.pro_id = pro_id;
        this.quantity = quantity;
        this.price = price;
    }

    public chitietDH(int order_id, int pro_id, int quantity, float price) {
        this.order_id = order_id;
        this.pro_id = pro_id;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getPro_id() {
        return pro_id;
    }

    public void setPro_id(int pro_id) {
        this.pro_id = pro_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
