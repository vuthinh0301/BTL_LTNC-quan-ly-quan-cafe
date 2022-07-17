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
public class donhang {
    private int id;
    private float discount;
    private int table_id;
    private int emp_id;
    private String create_at;
    private float total_price;
    private int status;

    public donhang() {
    }

    public donhang(int id, float discount, int table_id, int emp_id, String create_at, float total_price, int status) {
        this.id = id;
        this.discount = discount;
        this.table_id = table_id;
        this.emp_id = emp_id;
        this.create_at = create_at;
        this.total_price = total_price;
        this.status = status;
    }

    public donhang(float discount, int table_id, int emp_id, String create_at, float total_price, int status) {
        this.discount = discount;
        this.table_id = table_id;
        this.emp_id = emp_id;
        this.create_at = create_at;
        this.total_price = total_price;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public int getTable_id() {
        return table_id;
    }

    public void setTable_id(int table_id) {
        this.table_id = table_id;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(float total_price) {
        this.total_price = total_price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
