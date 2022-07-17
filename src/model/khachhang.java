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
public class khachhang {
    private  int id;
    private String name;
    private int table_id;
    private String phone;
    private String email;
    private  String date;
    private int quantity;
    private String gio;
    private String phut;
    private int status;
    public khachhang() {
    }

    public khachhang(int id, String name, int table_id, String phone, String email, String date, int quantity, int status) {
        this.id = id;
        this.name = name;
        this.table_id = table_id;
        this.phone = phone;
        this.email = email;
        this.date = date;
        this.quantity = quantity;
        this.status = status;
    }

    public khachhang(int id, String name, int table_id, String phone, String email, String date, int quantity, String gio, String phut, int status) {
        this.id = id;
        this.name = name;
        this.table_id = table_id;
        this.phone = phone;
        this.email = email;
        this.date = date;
        this.quantity = quantity;
        this.gio = gio;
        this.phut = phut;
        this.status = status;
    }

    public khachhang(int id, String name, int table_id, String phone, String email, String date, int quantity, String gio, String phut) {
        this.id = id;
        this.name = name;
        this.table_id = table_id;
        this.phone = phone;
        this.email = email;
        this.date = date;
        this.quantity = quantity;
        this.gio = gio;
        this.phut = phut;
    }
        
    public khachhang(int id, String name, int table_id, String phone, String email, String date, int quantity) {
        this.id = id;
        this.name = name;
        this.table_id = table_id;
        this.phone = phone;
        this.email = email;
        this.date = date;
        this.quantity = quantity;
    }

    public khachhang(String name, int table_id, String phone, String email, String date, int quantity) {
        this.name = name;
        this.table_id = table_id;
        this.phone = phone;
        this.email = email;
        this.date = date;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTable_id() {
        return table_id;
    }

    public void setTable_id(int table_id) {
        this.table_id = table_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getGio() {
        return gio;
    }

    public void setGio(String gio) {
        this.gio = gio;
    }

    public String getPhut() {
        return phut;
    }

    public void setPhut(String phut) {
        this.phut = phut;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

   
}
