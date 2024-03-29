/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Datban;

import controller.banDAO;
import controller.datbanDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ban;
import model.khachhang;
import view.Banhang.JpBanhang;
import view.QuanLy.Jp_QlBan;
import view.run;

/**
 *
 * @author Admin
 */
public class JpDatban extends javax.swing.JPanel {    
    public static JpDatban datban; 
    /**
     * Creates new form Jp_QlDanhmuc
     */
    public JpDatban() {
        initComponents();
        datban= this;
      Filltable();
     
    }
    List<khachhang> lskhachhang ;
    List<ban> lsban ;
    public void Filltable(){
        DefaultTableModel dt = new DefaultTableModel();
        dt.addColumn("Mã");
        dt.addColumn("Tên khách hàng");
        dt.addColumn("Số điện thoại");
        dt.addColumn("Email");
        dt.addColumn("Tên bàn");
        dt.addColumn("Thời gian");
        dt.addColumn("Số lượng khách hàng");
        dt.addColumn("Trạng thái");
        datbanDAO dbdao = new datbanDAO();
        banDAO bandao = new banDAO();
        lskhachhang = dbdao.read();
        lsban = bandao.read();
         if (lskhachhang!=null) {
             int sl = 0;
                for (khachhang kh : lskhachhang) {
                     for (ban b : lsban) {
                         if (kh.getTable_id() == b.getId()) {
                             String st ;
                             sl ++;
                             if (kh.getStatus()==1) {
                                 st = "Đang phục vụ";
                             }else if (kh.getStatus()==0) {
                                 st="Đã Thanh toán";
                             }else{
                              st="Chưa Đến";
                             }
                               Object data[]= {kh.getId(),kh.getName(),kh.getPhone(),kh.getEmail(),b.getName(),kh.getDate(),"Đi "+kh.getQuantity()+" người",
                               st};
                               dt.addRow(data);
        }
                         }
          
                }
                lblthongtin.setText(sl +" Khách hàng");
            }
         
        
    jTbban.setModel(dt);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTim = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTbban = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        lblthongtin = new javax.swing.JLabel();

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Đặt Bàn Trước");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Tìm Kiếm :");

        txtTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimActionPerformed(evt);
            }
        });
        txtTim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKeyReleased(evt);
            }
        });

        jTbban.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTbban.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTbbanMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTbban);

        btnAdd.setBackground(new java.awt.Color(102, 0, 153));
        btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        btnAdd.setText("Đặt bàn");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setBackground(new java.awt.Color(0, 153, 153));
        btnEdit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit.png"))); // NOI18N
        btnEdit.setText("Sửa");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDel.setBackground(java.awt.Color.red);
        btnDel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDel.setForeground(new java.awt.Color(255, 255, 255));
        btnDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        btnDel.setText("Xóa");
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Tổng số khách hàng:");

        lblthongtin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblthongtin.setForeground(new java.awt.Color(255, 0, 0));
        lblthongtin.setText("jLabel4");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(220, 220, 220)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblthongtin)
                .addGap(264, 264, 264))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnDel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblthongtin))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        DL_DatBan dl = new DL_DatBan(run.QlCafe, true);
        dl.setVisible(true);
       
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        int select = jTbban.getSelectedRow();
        if(select<0){
            JOptionPane.showMessageDialog(null, "Bạn chưa chọn khách hàng nào !");
        }else{
            int id = (int) jTbban.getValueAt(select, 0);
          String tatus =  (String) jTbban.getValueAt(select, 7);
            if (tatus =="Đã Thanh toán") {
               JOptionPane.showMessageDialog(null, "Bàn Này đã thanh toán rồi!");
            }
            else{
              if(tatus =="Đang phục vụ"){
                 JOptionPane.showMessageDialog(null, "Bàn Này đang được phục vụ rồi!");
            }else{
            DL_Sua edit = new DL_Sua(run.QlCafe, true, id);
        edit.setVisible(true);
              }
            }
        
                 
        } 
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        // TODO add your handling code here:
         int[] select = jTbban.getSelectedRows();
       if(select.length<0){
            JOptionPane.showMessageDialog(null, "bạn chưa chọn bàn nào !");
        }else{
         List<Integer> slban = new ArrayList<>();
         String sl = "";
         String status =null;
           for (int i : select) {
               int id = (int) jTbban.getValueAt(i, 0);
               slban.add(id);
               String tenban = (String) jTbban.getValueAt(i, 1);
               sl +=tenban+"\n";
              status = (String) jTbban.getValueAt(i, 7);
           }
           datbanDAO dbdao = new datbanDAO();
         int kq =  JOptionPane.showConfirmDialog(null, "Xóa bàn: \n"+sl, "Xóa bàn", JOptionPane.YES_NO_OPTION);
           if (kq ==JOptionPane.YES_OPTION) {
               if (status =="Đang phục vụ") {
                    JOptionPane.showMessageDialog(null, "Không thể xóa vì bàn đang được phục vụ!");
               }else{
                boolean check = dbdao.delete((ArrayList<Integer>) slban);
               if (check == true) {
                   Filltable();
                   banDAO bandao = new banDAO();
                   ban b = new ban();
                   b.setStatus(0);
                   bandao.editstatus(b);
                   
                    JpBanhang.banhang.fillban();
                    JpBanhang.banhang.updateUI();
               }
               }
             
           }
       }
     

    }//GEN-LAST:event_btnDelActionPerformed

    private void txtTimKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKeyReleased
       datbanDAO dbdao = new datbanDAO();
       lskhachhang = dbdao.Search(txtTim.getText());
        DefaultTableModel dt = new DefaultTableModel();
        dt.addColumn("Mã");
        dt.addColumn("Tên khách hàng");
        dt.addColumn("Số điện thoại");
        dt.addColumn("Email");
        dt.addColumn("Tên bàn");
        dt.addColumn("Thời gian");
        dt.addColumn("Số lượng khách hàng");
        dt.addColumn("Trạng thái");
        banDAO bandao = new banDAO();
        lsban = bandao.read();
         if (lskhachhang!=null) {
             int sl = 0;
                for (khachhang kh : lskhachhang) {
                     for (ban b : lsban) {
                         if (kh.getTable_id() == b.getId()) {
                             String st ;
                             sl ++;
                             if (kh.getStatus()==1) {
                                 st = "Đang phục vụ";
                             }else if (kh.getStatus()==0) {
                                 st="Đã Thanh toán";
                             }else{
                              st="Chưa Đến";
                             }
                               Object data[]= {kh.getId(),kh.getName(),kh.getPhone(),kh.getEmail(),b.getName(),kh.getDate(),"Đi "+kh.getQuantity()+" người",
                               st};
                               dt.addRow(data);
        }
                         }
          
                }
                lblthongtin.setText(sl +" Khách hàng");
            }
         
        
    jTbban.setModel(dt);
    }//GEN-LAST:event_txtTimKeyReleased

    private void txtTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_txtTimActionPerformed

    private void jTbbanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTbbanMousePressed
        // TODO add your handling code here:
    
    }//GEN-LAST:event_jTbbanMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnEdit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTbban;
    private javax.swing.JLabel lblthongtin;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables
}
