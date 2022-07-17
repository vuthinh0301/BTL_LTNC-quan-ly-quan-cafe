/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.QuanLy;

import controller.danhmucDAO;
import controller.sanphamDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.danhmuc;
import model.sanpham;
import view.Banhang.JpBanhang;
import view.Banhang.jpThucDon;
import view.run;

/**
 *
 * @author Admin
 */
public class Jp_QlSanpham extends javax.swing.JPanel {

    public static Jp_QlSanpham sanpham;
    /**
     * Creates new form Jp_QlDanhmuc
     */
    List<sanpham> lssanpham = new ArrayList<>();
    sanphamDAO sanphamdao = new sanphamDAO();
    List<danhmuc> lsdanhmuc = new ArrayList<>();
    danhmucDAO danhmucdao = new danhmucDAO();

    public Jp_QlSanpham() {
        initComponents();
        sanpham = this;
        Filltable();
    }

    public void Filltable() {
        DefaultTableModel dt = new DefaultTableModel();
        dt.addColumn("Mã");
        dt.addColumn("Tên danh mục");
        dt.addColumn("Tên sản phẩm");
        dt.addColumn("giá sản phẩm");
        dt.addColumn("Ngày tạo");
        dt.addColumn("trạng thái");
        lssanpham = sanphamdao.read();
        lsdanhmuc = danhmucdao.read();
        if (lsdanhmuc != null) {
            int soloai = 0;
            for (sanpham sp : lssanpham) {
                for (danhmuc dm : lsdanhmuc) {
                    if (dm.getId() == sp.getCat_id()) {
                        soloai++;
                        Object data[] = {sp.getId(), dm.getName(), sp.getName(), sp.getPrice(), sp.getDate(), sp.getStatus()};
                        dt.addRow(data);
                    }
                }
            }
            lblthongtin.setText(String.valueOf(soloai) + " sản phẩm");
        }

        jTbsanpham.setModel(dt);

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
        jTbsanpham = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        lblthongtin = new javax.swing.JLabel();

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Danh Sách Sản Phẩm");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Tìm Kiếm :");

        txtTim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKeyReleased(evt);
            }
        });

        jTbsanpham.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTbsanpham);

        btnAdd.setBackground(new java.awt.Color(102, 0, 153));
        btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        btnAdd.setText("Thêm");
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
        jLabel3.setText("Tổng số sản phẩm:");

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
        DL_ThemSanpham add = new DL_ThemSanpham(run.QlCafe,true);
        add.setVisible(true);


    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        int select = jTbsanpham.getSelectedRow();
        if (select < 0) {
            JOptionPane.showMessageDialog(null, "Bạn chưa chọn sản phẩm nào !");
        } else {
            int id = (int) jTbsanpham.getValueAt(select, 0);
            DL_SuaSanpham sua = new DL_SuaSanpham(run.QlCafe,true,id);
            sua.setVisible(true);
        }

        // TODO add your handling code here:

    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        // TODO add your handling code here:
        int[] select = jTbsanpham.getSelectedRows();
        sanphamDAO dao = new sanphamDAO();
        if (select.length <= 0) {
            JOptionPane.showMessageDialog(null, "Bạn chưa chọn sản phẩm nào !");
        } else {
            List<Integer> soluong = new ArrayList<Integer>();
           String sp = null ;
            for (int i : select) {
                 int id = (int) jTbsanpham.getValueAt(i, 0);
                 soluong.add(id);
                 String ten = (String) jTbsanpham.getValueAt(i, 2);
                  sp +=ten +"\n";
            }
          
            
            int qs = JOptionPane.showConfirmDialog(null, "Xóa sản phẩm: \n" + sp ,"Xóa sản phẩm", JOptionPane.YES_NO_OPTION);
            if (qs ==JOptionPane.YES_OPTION) {
                 boolean row = dao.delete((ArrayList<Integer>) soluong);
                 if (row==true) {
                     Filltable();
                      jpThucDon.td.fill();
                 jpThucDon.td.updateUI();
                    
                }else{
                 JOptionPane.showMessageDialog(null, "Không xóa được loại !");
                 }
            }
        }
      
    }//GEN-LAST:event_btnDelActionPerformed

    private void txtTimKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKeyReleased
        // TODO add your handling code here:
        lssanpham = sanphamdao.Search(txtTim.getText());
         DefaultTableModel dt = new DefaultTableModel();
        dt.addColumn("Mã");
        dt.addColumn("Tên danh mục");
        dt.addColumn("Tên sản phẩm");
        dt.addColumn("giá sản phẩm");
        dt.addColumn("Ngày tạo");
        dt.addColumn("trạng thái");
        lsdanhmuc = danhmucdao.read();
        if (lsdanhmuc != null) {
            int soloai = 0;
            for (sanpham sp : lssanpham) {
                for (danhmuc dm : lsdanhmuc) {
                    if (dm.getId() == sp.getCat_id()) {
                        soloai++;
                        Object data[] = {sp.getId(), dm.getName(), sp.getName(), sp.getPrice(), sp.getDate(), sp.getStatus()};
                        dt.addRow(data);
                    }
                }
            }
            lblthongtin.setText(String.valueOf(soloai) + "loại");
        }

        jTbsanpham.setModel(dt);
        
    }//GEN-LAST:event_txtTimKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnEdit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTbsanpham;
    private javax.swing.JLabel lblthongtin;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables
}
