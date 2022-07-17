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
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.ban;
import model.dsDonHang;
import model.donhang;
import model.khachhang;
import model.sanpham;
import view.run;

/**
 *
 * @author TRUNG HÒA
 */
public final class JpGoiMon extends javax.swing.JPanel {
        /**
     * Creates new form JpGoiMon
     */
    public static JpGoiMon goimon;
    int id;
    
    int maban;
    String tenban ;
    int MaHD, tienmon = 0, tongtien = 0;
    List<dsDonHang> lschitiet;
   
     NumberFormat chuyentien = new DecimalFormat("#,###,###");
    public JpGoiMon(int maban,String tenban ,int status) {
       goimon = this;
       this.maban = maban;
       this.tenban = tenban;
        initComponents();
               jpDsMon.setVisible(false);
        jpThongTinThanhToan.setVisible(false);
        jScrollPane1.setVisible(false);
        lblTenBan.setText(tenban);
        donhangDAO dhdao = new donhangDAO();
        donhang dh = dhdao.getTableId(maban);
        datbanDAO dbdao = new datbanDAO();
        khachhang kh= dbdao.getTable_id(maban);
        if (kh !=null) {
            lblgioden.setText(kh.getDate());
        }
        if (dh !=null) {
            lschitiet = dhdao.GetDsOrder(dh.getId());
             fillDsMon(0);
            MaHD = dh.getId();
            lblgioden.setText(dh.getCreate_at());  
            
        }
        
         lbltrangthai.setText("Trống");
//        if (status ==1) {
//            btngoi.setText("Hủy bàn");
//             lbltrangthai.setText("Đang phục vụ");
//             return;
//        }
        if (status==2) {
             lbltrangthai.setText("Đã đặt trước");
             return;
        }
        if(status ==1&& lschitiet ==null){
          btngoi.setText("Hủy bàn");
         
        } 
        if (status==1) {
             lbltrangthai.setText("Đang phục vụ");
            btndatban.setVisible(false);
              jpThucDon thucdon = new jpThucDon();
            thucdon.tenban = tenban;
            thucdon.maban = maban;
            jpthucdon.removeAll();
            jpthucdon.add(thucdon);
            jpthucdon.updateUI();
        }
//        if (status==1 && lschitiet !null) {
//             btngoi.setText("Hủy bàn");
//        }
       
    }
    public void fillthongtin(){
        donhangDAO dhdao = new donhangDAO();
    donhang dh = dhdao.getTableId(maban);
    MaHD = dh.getId();
    int ck = dhdao.CheckDsMon(MaHD, maban);
    btngoi.setVisible(true);
        if (ck==0) {
             btngoi.setVisible(false);
            HuyHD();
        }
        if (dh.getDiscount()>100) {
            lblgiamgia.setText(String.valueOf(chuyentien.format(dh.getDiscount()))+"VNĐ");
            if (tienmon-dh.getDiscount()<=0) {
                tongtien =0;
                lbltongtien.setText("0 VNĐ");
            }else{
            tongtien = (int) (tienmon - dh.getDiscount());
            lbltongtien.setText(String.valueOf(chuyentien.format(tongtien))+" VNĐ");
            }
        }else{
             tongtien = (int) (tienmon-(tienmon *dh.getDiscount()/100));
          
         lblgiamgia.setText(String.valueOf(chuyentien.format(dh.getDiscount()))+" %");
          lbltongtien.setText(String.valueOf(chuyentien.format(tongtien))+"VNĐ");
        }
       lbltienmon.setText(String.valueOf(chuyentien.format(tienmon))+" VNĐ");
    }
//   
   public void fillDsMon(int mahd){
        donhangDAO dhdao =new donhangDAO();
       if(mahd != 0){
            lschitiet = dhdao.GetDsOrder(mahd);
            donhang dh = dhdao.getTableId(maban);
            tienmon = 0;
        }
        mahd = MaHD;
        if(lschitiet != null){
            jpDsMon.setVisible(true);
            jpThongTinThanhToan.setVisible(true);
            jScrollPane1.setVisible(true);
            btngoi.setText("Thanh toán");
                     
            JPanel[] pn = new JPanel[lschitiet.size()];
            jpDsMon.removeAll();
            jpDsMon.setLayout(new BoxLayout(jpDsMon, BoxLayout.Y_AXIS));
            for(int i=0;i<lschitiet.size();i++){
                tienmon += lschitiet.get(i).getPrice()* lschitiet.get(i).getQuantity();
                pn[i] = new JPanel();
                pn[i].setName(String.valueOf(lschitiet.get(i).getPro_id()));
                pn[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
                pn[i].setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));
                pn[i].setBackground(Color.decode("#b3ff99"));
                pn[i].setLayout(new GridLayout());
                pn[i].setPreferredSize(new Dimension(270, 50));
                pn[i].setMaximumSize(new Dimension(270, 50));
                pn[i].setMinimumSize(new Dimension(270, 50));
                pn[i].add(new JLabel(String.valueOf(lschitiet.get(i).getPro_name()),JLabel.CENTER)).setForeground(Color.decode("#001a66"));
                pn[i].add(new JLabel(String.valueOf(chuyentien.format(lschitiet.get(i).getPrice()))+" VNĐ",JLabel.CENTER)).setForeground(Color.decode("#ff0000"));
                pn[i].add(new JLabel(String.valueOf(lschitiet.get(i).getQuantity())+" "+ "cốc",JLabel.RIGHT)).setForeground(Color.decode("#008000"));
                Icon ic = new ImageIcon("src/images/DeleteRed.png");
                JLabel lbl = new JLabel("zz", ic,JLabel.CENTER);
                lbl.setForeground(Color.decode("#b3ff99"));
                lbl.setName(String.valueOf(lschitiet.get(i).getPro_id()));
                pn[i].add(lbl).addMouseListener(new MouseAdapter() {
                  @Override
                    public void mouseClicked(MouseEvent e){
                        int qs;
                        sanphamDAO spdao = new sanphamDAO();
                        List<sanpham> td = spdao.GetId(Integer.parseInt(e.getComponent().getName()));
                        
                        qs = JOptionPane.showConfirmDialog(null, "Hủy món: "+td.get(0).getName()+" ?", "Hủy món", JOptionPane.YES_NO_OPTION);
                        if (qs == JOptionPane.YES_OPTION) {
                            int xoa = dhdao.DeletePro(Integer.parseInt(e.getComponent().getName()),MaHD, maban);
                            if(xoa == 1){
                                fillDsMon(MaHD);
                            }
                            if(xoa == 2){
                                fillDsMon(MaHD);
                                HuyHD();
                            }
                        }
                    }
                });
                pn[i].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e){
                        DLSoLuong sl = new DLSoLuong(run.QlCafe, true, Integer.parseInt(e.getComponent().getName()), tenban, maban);
                        sl.setVisible(true);
                    }
                });
                jpDsMon.add(pn[i]);
                jpDsMon.updateUI();
                
            }

            fillthongtin();
        }      
   }  
 private void HuyHD(){
        
        JButton btnhuy = new JButton("Hủy bàn");
        btnhuy.setPreferredSize(new Dimension(100, 40));
        btnhuy.setBounds(100, 50, 100, 40);
        jpDsMon.setLayout(null);
        btngoi.setVisible(false);
        jpThongTinThanhToan.setVisible(false);
        btnhuy.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e){
                ban b = new ban();
                b.setStatus(0);
                b.setId(maban);
                b.setName(lblTenBan.getText());
                banDAO ban = new banDAO();
                ban.edit(b);

                JpBanhang.banhang.fillban();
                JpGoiMon.goimon.removeAll();
                JpBanhang.banhang.fillLb();

                donhang hd = new donhang();
                hd.setId(MaHD);
                donhangDAO dhdao = new donhangDAO();
                dhdao.HuyHD(hd);
            }
        });
        jpDsMon.add(btnhuy);
        jpDsMon.updateUI();        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jpThongTinBan = new javax.swing.JPanel();
        lblTenBan = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblgioden = new javax.swing.JLabel();
        lbltrangthai = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btndatban = new javax.swing.JButton();
        btngoi = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jpThongTinThanhToan = new javax.swing.JPanel();
        lbltongtien = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblgiamgia = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbltienmon = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jpDsMon = new javax.swing.JPanel();
        jpthucdon = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 0, 0));
        jLabel5.setText("Thời gian phục vụ gần nhất");

        setBackground(Color.decode("#e6e6e6"));

        jpThongTinBan.setBackground(Color.decode("#e6e6e6"));
        jpThongTinBan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jpThongTinBan.setAutoscrolls(true);

        lblTenBan.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblTenBan.setForeground(new java.awt.Color(102, 51, 0));
        lblTenBan.setText("Bàn 1");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 0));
        jLabel2.setText("Giờ đến:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 0, 0));
        jLabel3.setText("Trạng thái:");

        lblgioden.setText(".....");

        lbltrangthai.setText("....");

        jPanel1.setBackground(Color.decode("#e6e6e6"));

        btndatban.setFont(new java.awt.Font("Tahoma", 1, 9)); // NOI18N
        btndatban.setForeground(new java.awt.Color(102, 51, 0));
        btndatban.setText("Đóng");
        btndatban.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btndatban.setPreferredSize(new java.awt.Dimension(100, 40));
        btndatban.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndatbanActionPerformed(evt);
            }
        });
        jPanel1.add(btndatban);

        btngoi.setFont(new java.awt.Font("Tahoma", 1, 9)); // NOI18N
        btngoi.setForeground(new java.awt.Color(102, 51, 0));
        btngoi.setText("Gọi món");
        btngoi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btngoi.setPreferredSize(new java.awt.Dimension(100, 40));
        btngoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btngoiActionPerformed(evt);
            }
        });
        jPanel1.add(btngoi);

        jSeparator1.setBackground(Color.decode("#e6e6e6"));
        jSeparator1.setForeground(new java.awt.Color(21, 75, 158));

        jpThongTinThanhToan.setBackground(Color.decode("#e6e6e6"));
        jpThongTinThanhToan.setAutoscrolls(true);
        jpThongTinThanhToan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jpThongTinThanhToan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jpThongTinThanhToanMousePressed(evt);
            }
        });

        lbltongtien.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbltongtien.setForeground(new java.awt.Color(255, 0, 0));
        lbltongtien.setText("0");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 0, 51));
        jLabel8.setText("Giảm giá:");

        lblgiamgia.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblgiamgia.setForeground(new java.awt.Color(51, 0, 51));
        lblgiamgia.setText("0");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 0, 51));
        jLabel7.setText("Tiền món:");

        lbltienmon.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lbltienmon.setText("0");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 0));
        jLabel9.setText("THÀNH TIỀN:");

        javax.swing.GroupLayout jpThongTinThanhToanLayout = new javax.swing.GroupLayout(jpThongTinThanhToan);
        jpThongTinThanhToan.setLayout(jpThongTinThanhToanLayout);
        jpThongTinThanhToanLayout.setHorizontalGroup(
            jpThongTinThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpThongTinThanhToanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpThongTinThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpThongTinThanhToanLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jpThongTinThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpThongTinThanhToanLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblgiamgia, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                            .addGroup(jpThongTinThanhToanLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbltienmon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(23, 23, 23))
                    .addGroup(jpThongTinThanhToanLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbltongtien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(44, 44, 44))
        );
        jpThongTinThanhToanLayout.setVerticalGroup(
            jpThongTinThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpThongTinThanhToanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpThongTinThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lbltienmon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpThongTinThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lblgiamgia))
                .addGap(18, 18, 18)
                .addGroup(jpThongTinThanhToanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lbltongtien))
                .addContainerGap())
        );

        jScrollPane1.setBackground(Color.decode("#e6e6e6"));
        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jpDsMon.setBackground(Color.decode("#e6e6e6"));
        jpDsMon.setMaximumSize(new java.awt.Dimension(32767, 400));
        jpDsMon.setOpaque(false);

        javax.swing.GroupLayout jpDsMonLayout = new javax.swing.GroupLayout(jpDsMon);
        jpDsMon.setLayout(jpDsMonLayout);
        jpDsMonLayout.setHorizontalGroup(
            jpDsMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 297, Short.MAX_VALUE)
        );
        jpDsMonLayout.setVerticalGroup(
            jpDsMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 265, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jpDsMon);

        javax.swing.GroupLayout jpThongTinBanLayout = new javax.swing.GroupLayout(jpThongTinBan);
        jpThongTinBan.setLayout(jpThongTinBanLayout);
        jpThongTinBanLayout.setHorizontalGroup(
            jpThongTinBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpThongTinBanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpThongTinBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpThongTinBanLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jpThongTinBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpThongTinBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpThongTinBanLayout.createSequentialGroup()
                                .addComponent(lbltrangthai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(260, 260, 260))
                            .addGroup(jpThongTinBanLayout.createSequentialGroup()
                                .addComponent(lblgioden, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(227, 227, 227))))
                    .addGroup(jpThongTinBanLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTenBan)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jpThongTinBanLayout.createSequentialGroup()
                .addGroup(jpThongTinBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpThongTinBanLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jpThongTinThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpThongTinBanLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jpThongTinBanLayout.createSequentialGroup()
                .addGroup(jpThongTinBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpThongTinBanLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jpThongTinBanLayout.setVerticalGroup(
            jpThongTinBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpThongTinBanLayout.createSequentialGroup()
                .addGroup(jpThongTinBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpThongTinBanLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(lblTenBan)))
                .addGap(18, 18, 18)
                .addGroup(jpThongTinBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblgioden))
                .addGap(18, 18, 18)
                .addGroup(jpThongTinBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lbltrangthai))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpThongTinThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        jpthucdon.setBackground(Color.decode("#e6e6e6"));
        jpthucdon.setLayout(new java.awt.BorderLayout());

        jLabel1.setMaximumSize(new java.awt.Dimension(1258, 519));
        jLabel1.setMinimumSize(new java.awt.Dimension(1258, 519));
        jLabel1.setPreferredSize(new java.awt.Dimension(410, 470));
        jpthucdon.add(jLabel1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpThongTinBan, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jpthucdon, javax.swing.GroupLayout.DEFAULT_SIZE, 1268, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpthucdon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpThongTinBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleParent(this);
    }// </editor-fold>//GEN-END:initComponents
  

    private void btngoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btngoiActionPerformed
    if(btngoi.getText().equals("Hủy bàn")){
            
            jpthucdon.removeAll();
            jpthucdon.add(jLabel1);
            jpthucdon.updateUI();
            jpThongTinThanhToan.setVisible(false);
            lblgioden.setText("......");
            lbltrangthai.setText("Trống");
           int TrangThai = 0;
            ban b = new ban(id, lblTenBan.getText(), TrangThai);
            banDAO dao = new banDAO();
            int Update = dao.edit(b);
            JpBanhang.banhang.fillban();
            btngoi.setText("Gọi món");
            btndatban.setVisible(true);
            btndatban.setText("Đóng");
            return;
            
        }     
         if(btngoi.getText().equals("Gọi món")){
            jpthucdon.setVisible(true);
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm a");
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            lblgioden.setText(df.format(date));
            lbltrangthai.setText("Đang phục vụ");
            btndatban.setVisible(false);
         
            btngoi.setText("Hủy bàn");

            jpThucDon thucdon;
            thucdon = new jpThucDon();
            thucdon.maban = maban;
            thucdon.tenban = tenban;
            
            thucdon.gioden = sf.format(date);
            jpthucdon.removeAll();
            jpthucdon.add(thucdon);
            jpthucdon.revalidate();
        }
         if (btngoi.getText().equals("Thanh toán")) {
            DLThanhToan thanhtoan = new DLThanhToan(run.QlCafe, true, tongtien, tenban, maban, MaHD);
            thanhtoan.setVisible(true);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btngoiActionPerformed

    private void btndatbanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndatbanActionPerformed
        JpBanhang.banhang.fillLb();
        // TODO add your handling code here:
    }//GEN-LAST:event_btndatbanActionPerformed

    private void jpThongTinThanhToanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpThongTinThanhToanMousePressed
      DLGiamGia giamgia = new DLGiamGia(run.QlCafe, true, MaHD, tenban, tienmon);
      giamgia.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jpThongTinThanhToanMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btndatban;
    private javax.swing.JButton btngoi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel jpDsMon;
    private javax.swing.JPanel jpThongTinBan;
    private javax.swing.JPanel jpThongTinThanhToan;
    private javax.swing.JPanel jpthucdon;
    private javax.swing.JLabel lblTenBan;
    private javax.swing.JLabel lblgiamgia;
    private javax.swing.JLabel lblgioden;
    private javax.swing.JLabel lbltienmon;
    private javax.swing.JLabel lbltongtien;
    private javax.swing.JLabel lbltrangthai;
    // End of variables declaration//GEN-END:variables
}
