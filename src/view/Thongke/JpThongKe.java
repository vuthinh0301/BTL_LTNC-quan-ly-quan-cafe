/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Thongke;


import controller.banDAO;
import controller.danhmucDAO;
import controller.donhangDAO;
import controller.nhanvienDAO;
import controller.sanphamDAO;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.ban;
import model.danhmuc;
import model.donhang;
import model.dsDonHang;
import model.nhanvien;
import model.sanpham;

/**
 *
 * @author ThangIKCU
 */
public final class JpThongKe extends javax.swing.JPanel {
  
    /**
     * Creates new form JpThongKe
     */
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    NumberFormat chuyentien = new DecimalFormat("#,###,###");
    public JpThongKe() {
        initComponents();
        FillTableHD();
        
        loadinfo();
        FillTableMon();
        jDateChooser1.setDateFormatString("dd-MM-yyyy");
        jDateChooser2.setDateFormatString("dd-MM-yyyy");
        
    }
    public void loadinfo(){
        banDAO bandao = new banDAO();
        List<ban> lsban = (List<ban>) bandao.read();
        if (lsban.size()>0) {
            int soban =0;
            for (ban b : lsban) {
                soban++;
            }
            lbltongban.setText(String.valueOf(soban));
        }
        danhmucDAO dmdao = new danhmucDAO();
        List<danhmuc> lsdanhmuc = dmdao.read();
        if (lsdanhmuc.size()>0) {
            int sodanhmuc = 0;
            for (danhmuc dm : lsdanhmuc) {
                sodanhmuc++;
            }
            lbltongloai.setText(String.valueOf(sodanhmuc));
        }
        sanphamDAO spdao = new sanphamDAO();
        List<sanpham> lssanpham = spdao.read();
        if (lssanpham.size()>0) {
            int sosp = 0;
            for (sanpham sp : lssanpham) {
                sosp++;
            }
            lbltongmon.setText(String.valueOf(sosp));
        }
        nhanvienDAO nvdao = new nhanvienDAO();
        List<nhanvien> lsnhanvien = nvdao.read();
        if (lsnhanvien.size()>0) {
            int sonv=0;
            for (nhanvien nv : lsnhanvien) {
                sonv++;
            }
            lbltaikhoan.setText(String.valueOf(sonv));
        }
    }
    public void FillTableHD() {
        donhangDAO dhdao = new donhangDAO();
        banDAO bandao = new banDAO();
        List<donhang> lsdonhang = dhdao.getdsHD();
        DefaultTableModel tbmodel = new DefaultTableModel();
        tbmodel.addColumn("Mã");
        tbmodel.addColumn("Thời gian");
        tbmodel.addColumn("Tiền món");
        tbmodel.addColumn("Giảm giá");
        tbmodel.addColumn("Thành tiền");
        tbmodel.addColumn("Điểm bán");
        tbmodel.addColumn("Các món");
        if (lsdonhang.size()>0) {
             int hd = 0, tongtien=0, tongtienmon =0,giam=0, tonggiam =0;
             for (donhang dh : lsdonhang) {
                hd++;
                tongtien+=dh.getTotal_price();
                String tenban = bandao.getIdBan(dh.getTable_id()).getName();
                List<dsDonHang> lsdsdonhang = dhdao.GetDsOrder(dh.getId());
                String cacmon="";
                int tienmon =0;
                 for (dsDonHang dsdh : lsdsdonhang) {
                     tienmon +=dsdh.getPrice() * dsdh.getQuantity();
                     cacmon +=dsdh.getPro_name()+"("+dsdh.getQuantity()+")"+", ";
                 }
                 tongtienmon +=tienmon;
                 String dv="";
               if(dh.getDiscount() >100){
                        giam = (int) dh.getDiscount();
                    }
                    if(dh.getDiscount() == 0){
                        giam = 0;
                    }
                    if(dh.getDiscount() <=100 && dh.getDiscount() != 0){
                        giam = (int) (dh.getDiscount() * tienmon / 100);
                        dv = "("+String.valueOf(dh.getDiscount())+"%)";
                    }
                    tonggiam += giam;
                tbmodel.addRow(new Object[]{dh.getId(), dh.getCreate_at(), chuyentien.format(tienmon), chuyentien.format(giam)+dv , chuyentien.format(dh.getTotal_price()), tenban, cacmon});
                
             
            }
              lblgiam.setText(chuyentien.format(tonggiam)+" VNĐ");
            lbltienmon.setText(chuyentien.format(tongtienmon)+" VNĐ");
            lbltienthu.setText(chuyentien.format(tongtienmon - tonggiam)+" VNĐ");
            lblhd.setText(String.valueOf(hd)+" hóa đơn");
        }
        tbaleHD.setModel(tbmodel);
         tbaleHD.getColumnModel().getColumn(0).setMaxWidth(50);
        tbaleHD.getColumnModel().getColumn(1).setMinWidth(140);
        tbaleHD.getColumnModel().getColumn(1).setMaxWidth(130);
        tbaleHD.getColumnModel().getColumn(2).setMaxWidth(100);
        tbaleHD.getColumnModel().getColumn(3).setMaxWidth(100);
        tbaleHD.getColumnModel().getColumn(4).setMaxWidth(100);
        tbaleHD.getColumnModel().getColumn(5).setMaxWidth(100);
    } 
    public void FillTableMon() {
        donhangDAO dhdao = new donhangDAO();
       List<sanpham> lssanpham = dhdao.GetProByID();
        
        DefaultTableModel tbModel = new DefaultTableModel();

       tbModel.addColumn("Tên món");
        tbModel.addColumn("Số lượng");
        tbModel.addColumn("Doanh thu");
        if (lssanpham != null) {
            
            int somon = 0,tienmon=0;
            for (sanpham td : lssanpham) {
                List<dsDonHang> lsdsdonhang = dhdao.GetGiaSoLuong(td.getId());
                if(dhdao.GetGiaSoLuong(td.getId()).size() > 0){
                    int gia =0,soluong =0;
                     for(dsDonHang i : lsdsdonhang){
                         somon += i.getQuantity();
                         soluong += i.getQuantity();
                         gia += i.getPrice()* i.getQuantity();
                     }
                     tienmon += gia;
                         tbModel.addRow(new Object[]{lsdsdonhang.get(0).getPro_name(), soluong, chuyentien.format(gia)+" VNĐ"});
                }
            }
            lblmon.setText(String.valueOf(somon)+" món");

        } 
        tbmon.setModel(tbModel);

    }    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbmon = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbaleHD = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        lblhd = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblmon = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbltienthu = new javax.swing.JLabel();
        lbltienmon = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblgiam = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbltongban = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbltongmon = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbltongloai = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbltaikhoan = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jPanel1 = new javax.swing.JPanel();

        setForeground(new java.awt.Color(162, 11, 11));

        tbmon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tên món", "Đơn vị tính", "Số lượng", "Doanh thu"
            }
        ));
        jScrollPane1.setViewportView(tbmon);

        tbaleHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Thời gian", "Tiền món", "Giảm giá", "Thành tiền", "Điểm bán", "Các món"
            }
        ));
        jScrollPane2.setViewportView(tbaleHD);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Tổng số hóa đơn thanh toán:");

        lblhd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblhd.setForeground(new java.awt.Color(82, 15, 172));
        lblhd.setText(".....");

        jLabel2.setText("Tổng số món đã bán:");

        lblmon.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblmon.setForeground(new java.awt.Color(10, 85, 157));
        lblmon.setText(".....");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Tiền thu về:");

        lbltienthu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbltienthu.setForeground(new java.awt.Color(162, 11, 11));
        lbltienthu.setText(".....");

        lbltienmon.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbltienmon.setForeground(new java.awt.Color(162, 11, 11));
        lbltienmon.setText("....");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Tiền giảm giá: ");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Tiền món:");

        lblgiam.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblgiam.setForeground(new java.awt.Color(16, 121, 95));
        lblgiam.setText("......");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Tổng số bàn: ");

        lbltongban.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltongban.setForeground(new java.awt.Color(17, 131, 40));
        lbltongban.setText("jLabel7");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Tổng số món:");

        lbltongmon.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltongmon.setForeground(new java.awt.Color(17, 131, 40));
        lbltongmon.setText("jLabel7");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Tổng số loại:");

        lbltongloai.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltongloai.setForeground(new java.awt.Color(17, 131, 40));
        lbltongloai.setText("jLabel7");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Tổng số nhân viên:");

        lbltaikhoan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltaikhoan.setForeground(new java.awt.Color(17, 131, 40));
        lbltaikhoan.setText("jLabel7");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(172, 0, 5));
        jLabel11.setText("Thống kê theo món");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(153, 0, 0));
        jLabel12.setText("Thống kê theo hóa đơn");

        jLabel13.setText("Từ ngày");

        jLabel14.setText("Đến ngày");

        jButton1.setText("Thống kê");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(23, 12, 132));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 72, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(404, 404, 404)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel12)
                                .addGap(525, 525, 525)
                                .addComponent(jLabel11))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(27, 27, 27)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbltienthu, javax.swing.GroupLayout.DEFAULT_SIZE, 1487, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lbltienmon, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblgiam, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(76, 76, 76)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lbltongban)
                                        .addGap(32, 32, 32)
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lbltongmon)
                                        .addGap(32, 32, 32)
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbltongloai)
                                        .addGap(32, 32, 32)
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lbltaikhoan))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblhd, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(301, 301, 301)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblmon)
                                .addGap(428, 428, 428)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblhd)
                    .addComponent(lblmon)
                    .addComponent(jLabel2))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lbltienmon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lbltienthu)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblgiam, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(lbltongmon)
                        .addComponent(jLabel6)
                        .addComponent(lbltongban)
                        .addComponent(jLabel8)
                        .addComponent(lbltongloai)
                        .addComponent(jLabel9)
                        .addComponent(lbltaikhoan)))
                .addContainerGap(51, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void fillbydate1(){
        donhangDAO dhdao = new donhangDAO();
        
        List<sanpham> lssanpham = dhdao.GetProByID();
         String d1 = sdf.format(jDateChooser1.getDate());
        String d2 = sdf.format(jDateChooser2.getDate());
         DefaultTableModel tbModel = new DefaultTableModel();
        tbModel.addColumn("Tên món");
        tbModel.addColumn("Số lượng");
        tbModel.addColumn("Doanh thu");
        if (lssanpham.size()>0) {
            
            int somon = 0,tienmon=0;
            for (sanpham td : lssanpham) {
                List<dsDonHang> lsdsdonhang = dhdao.GetHdByDate(d1,d2,td.getId());
                if(lsdsdonhang.size() > 0){
                    int gia =0,soluong =0;
                     for(dsDonHang i : lsdsdonhang){
                         somon += i.getQuantity();
                         soluong += i.getQuantity();
                         gia += i.getPrice()* i.getQuantity();
                     }
                     tienmon += gia;
                         tbModel.addRow(new Object[]{lsdsdonhang.get(0).getPro_name(), soluong, chuyentien.format(gia)+" VNĐ"});
                }
            }
            lblmon.setText(String.valueOf(somon)+" món");

        } 
        tbmon.setModel(tbModel);
      
        
    }
    public void fillbydate2(){
         donhangDAO dhdao = new donhangDAO();
          String d1 = sdf.format(jDateChooser1.getDate());
        String d2 = sdf.format(jDateChooser2.getDate());
        banDAO bandao = new banDAO();
        List<donhang> lsdonhang = dhdao.getdsHD();
        DefaultTableModel tbmodel = new DefaultTableModel();
        tbmodel.addColumn("Mã");
        tbmodel.addColumn("Thời gian");
        tbmodel.addColumn("Tiền món");
        tbmodel.addColumn("Giảm giá");
        tbmodel.addColumn("Thành tiền");
        tbmodel.addColumn("Điểm bán");
        tbmodel.addColumn("Các món");
        if (lsdonhang.size()>0) {
             int hd = 0, tongtien=0, tongtienmon =0,giam=0, tonggiam =0;
             for (donhang dh : lsdonhang) {
                
                tongtien+=dh.getTotal_price();
                String tenban = bandao.getIdBan(dh.getTable_id()).getName();
                List<dsDonHang> lsdsdonhang = dhdao.GetCtHDByDate(dh.getId(),d1,d2);
                if(lsdsdonhang.size() > 0){
                    hd++;
                String cacmon="";
                int tienmon =0;
                 for (dsDonHang dsdh : lsdsdonhang) {
                     tienmon +=dsdh.getPrice() * dsdh.getQuantity();
                     cacmon +=dsdh.getPro_name()+"("+dsdh.getQuantity()+")"+", ";
                 }
                 tongtienmon +=tienmon;
                 String dv="";
               if(dh.getDiscount() >100){
                        giam = (int) dh.getDiscount();
                    }
                    if(dh.getDiscount() == 0){
                        giam = 0;
                    }
                    if(dh.getDiscount() <=100 && dh.getDiscount() != 0){
                        giam = (int) (dh.getDiscount() * tienmon / 100);
                        dv = "("+String.valueOf(dh.getDiscount())+"%)";
                    }
                    tonggiam += giam;
                tbmodel.addRow(new Object[]{lsdsdonhang.get(0).getOrder_id(), lsdsdonhang.get(0).getDate(), chuyentien.format(tienmon), chuyentien.format(giam)+dv , chuyentien.format(dh.getTotal_price()), tenban, cacmon});
                
                }
            }
              lblgiam.setText(chuyentien.format(tonggiam)+" VNĐ");
            lbltienmon.setText(chuyentien.format(tongtienmon)+" VNĐ");
            lbltienthu.setText(chuyentien.format(tongtienmon - tonggiam)+" VNĐ");
            lblhd.setText(String.valueOf(hd)+" hóa đơn");
        }
        tbaleHD.setModel(tbmodel);
         tbaleHD.getColumnModel().getColumn(0).setMaxWidth(50);
        tbaleHD.getColumnModel().getColumn(1).setMinWidth(140);
        tbaleHD.getColumnModel().getColumn(1).setMaxWidth(130);
        tbaleHD.getColumnModel().getColumn(2).setMaxWidth(100);
        tbaleHD.getColumnModel().getColumn(3).setMaxWidth(100);
        tbaleHD.getColumnModel().getColumn(4).setMaxWidth(100);
        tbaleHD.getColumnModel().getColumn(5).setMaxWidth(100);

    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
if(((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText().isEmpty() || ((JTextField)jDateChooser2.getDateEditor().getUiComponent()).getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Bạn cần chọn ngày để thống kê !");
            return;
        }
        fillbydate1();
        fillbydate2();       
        
        //SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblgiam;
    private javax.swing.JLabel lblhd;
    private javax.swing.JLabel lblmon;
    private javax.swing.JLabel lbltaikhoan;
    private javax.swing.JLabel lbltienmon;
    private javax.swing.JLabel lbltienthu;
    private javax.swing.JLabel lbltongban;
    private javax.swing.JLabel lbltongloai;
    private javax.swing.JLabel lbltongmon;
    private javax.swing.JTable tbaleHD;
    private javax.swing.JTable tbmon;
    // End of variables declaration//GEN-END:variables
}
