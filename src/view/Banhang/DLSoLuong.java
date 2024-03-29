/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Banhang;

import controller.banDAO;
import controller.datbanDAO;
import controller.donhangDAO;
import controller.sanphamDAO;
import java.util.List;
import model.ban;
import model.chitietDH;
import model.donhang;
import model.khachhang;
import model.sanpham;
import view.Datban.JpDatban;

/**
 *
 * @author ThangIKCU
 */
public class DLSoLuong extends javax.swing.JDialog {
    int sl = 0;
    
    public String gioden, name_table;
    public int pro_id ;
    public int table_id;
    chitietDH cthd;
    List<sanpham> lssanpham;
    /**
     * Creates new form NewJDialog
     */
    
    /**
     * Creates new form NewJDialog
     * @param parent
     * @param modal
     * @param MaMon
     * @param tenban
     * @param MaBan
     */
    donhangDAO dhdao = new donhangDAO();
    sanphamDAO spdao = new sanphamDAO();
    banDAO bandao = new banDAO();
    public DLSoLuong(java.awt.Frame parent, boolean modal, int MaMon, String tenban, int MaBan) {
        super(parent, modal);
        initComponents();
       table_id = MaBan;
       name_table = tenban;
       pro_id = MaMon;
       Fill();
       cthd = dhdao.GetDsChiTiet(MaMon, MaBan);
        if(cthd != null){
            txtgia.setText(String.valueOf(cthd.getPrice()));
            txtSl.setText(String.valueOf(cthd.getQuantity()));
            
        }
       txtgia.setEnabled(false);
              

    }
    private void Fill(){
        lssanpham = spdao.GetId(pro_id);
        ban b=  bandao.getIdBan(table_id);
        txtSl.setText("1");
        lblban.setText(b.getName() + " ");
        
        lblTenMon.setText(lssanpham.get(0).getName());
          txtgia.setText(String.valueOf(lssanpham.get(0).getPrice()));

    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblTenMon = new javax.swing.JLabel();
        lblDVT = new javax.swing.JLabel();
        txtSl = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblban = new javax.swing.JLabel();
        txtgia = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(140, 140, 6));
        setLocation(new java.awt.Point(500, 200));
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0), 2));

        lblTenMon.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTenMon.setForeground(new java.awt.Color(51, 0, 0));
        lblTenMon.setText("Cà phê sữa");

        lblDVT.setText("Cốc");

        txtSl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSlKeyReleased(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("Đồng ý");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setText("Hủy");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tru(-).png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Cong(+).png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Số lượng:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Giá:");

        lblban.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lblban.setForeground(new java.awt.Color(0, 51, 0));
        lblban.setText("Bàn1");

        txtgia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtgiaKeyReleased(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/thucdon2.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(lblban))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(27, 27, 27)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblTenMon)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(txtSl, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(lblDVT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtgia, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)))))
                .addGap(39, 39, 39))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblban)
                    .addComponent(lblTenMon))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDVT)
                        .addComponent(jLabel1)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(txtgia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
            sl = Integer.parseInt(txtSl.getText());
            if(sl < 30){
                sl++;
                txtSl.setText(String.valueOf(sl));
            }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try{
            sl = Integer.parseInt(txtSl.getText());
            if(sl != 1 && sl != 0){
                sl--;
                txtSl.setText(String.valueOf(sl));
            }
        }catch(Exception e){
                txtSl.setText("1");
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
          
        if (dhdao.GetMaHD(table_id)==0) {
           donhang dh = new donhang();
            dh.setTable_id(table_id);
            dh.setStatus(0);
              int row = dhdao.addHoadon(dh, gioden);
        }
//        if(cthd != null){
//            ChiTietHD ct = new ChiTietHD();
//            ct.SetGia(Integer.parseInt(txtgia.getText()));
//            ct.SetSoLuong(Integer.parseInt(txtSl.getText()));
//            ct.SetMaChiTietHD(mon.GetMaChiTietHD());
//            int updatect = cn.UpdateChiTiet(ct);
//        }
        if (cthd!=null) {
              chitietDH ct = new chitietDH();
           
            ct.setPrice(Float.parseFloat(txtgia.getText()));
            ct.setQuantity(Integer.parseInt(txtSl.getText()));
            ct.setId(cthd.getId());
            
            int row = dhdao.updatechitietHD(ct);
        }
        if (cthd ==null) {
          
           chitietDH ct = new chitietDH();
            ct.setOrder_id(dhdao.GetMaHD(table_id));
            ct.setPro_id(pro_id);
            ct.setPrice(Float.parseFloat(txtgia.getText()));
            ct.setQuantity(Integer.parseInt(txtSl.getText()));
            int row = dhdao.InsertChiTietHD(ct);
        }

        
ban b = new ban();
        b.setStatus(1);
        b.setName(name_table);
        b.setId(table_id);
        int updateban = bandao.edit(b);

        JpBanhang.banhang.fillban();
        JpBanhang.banhang.updateUI();
        JpGoiMon.goimon.fillDsMon(dhdao.GetMaHD(table_id));
        JpGoiMon.goimon.updateUI();
       
         
        datbanDAO dbdao = new datbanDAO();
        ban ba = new ban();
        ba = bandao.getIdBan(table_id);
        khachhang khang = dbdao.getTable_id(table_id);
       
        if (khang !=null && khang.getStatus()==2) {
            khachhang kh = new khachhang();
             kh.setStatus(1);
             kh.setId(khang.getId());
        dbdao.editstatus(kh);
        JpDatban.datban.Filltable();
        JpDatban.datban.updateUI();
        }


        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtSlKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSlKeyReleased
        try{
            sl = Integer.parseInt(txtSl.getText());
            if(txtSl.getText().equals("0") || sl > 30)
                txtSl.setText("1");
        }catch(Exception e){
           txtSl.setText("1");
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_txtSlKeyReleased

    private void txtgiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtgiaKeyReleased
             // TODO add your handling code here:
    }//GEN-LAST:event_txtgiaKeyReleased

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblDVT;
    private javax.swing.JLabel lblTenMon;
    private javax.swing.JLabel lblban;
    private javax.swing.JTextField txtSl;
    private javax.swing.JTextField txtgia;
    // End of variables declaration//GEN-END:variables
}
