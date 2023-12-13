/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pointofsales;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author ACER
 */
public class transaksi extends javax.swing.JFrame {
    
    String Tanggal;
    private DefaultTableModel model;
    
  public void tambahTransaksi() {
    
    

    
    double totalSemua = 0.0;

    
    int jumlah = Integer.parseInt(txtJumlah.getText());
    int harga = Integer.parseInt(txtHarga.getText());

        int totalBiaya = jumlah * harga;
    double total = totalBiaya;  

    // Update nilai totalBayar dan diskon
    String status = (String) txtMember.getSelectedItem();
    
//    JOptionPane.showMessageDialog(this, "Total1 : " + totalBayar);
    loadData();
    int jumlahBaris = jTable1.getRowCount();
//    JOptionPane.showMessageDialog(this, "Total1 : " + jTable1.getValueAt(0, 5).toString());
    
    for (int i = 0; i < jumlahBaris; i++) {
        int totalBaris = Integer.parseInt(jTable1.getValueAt(i, 5).toString());
//        JOptionPane.showMessageDialog(this, "Total2 : " + totalBaris);
        totalSemua += totalBaris;
    }
    double discountPercentage = "Member".equals(status) ? 0.1 : 0.0;
    double discount = totalSemua * discountPercentage;
    double totalBayar = totalSemua - discount;
    

//    JOptionPane.showMessageDialog(this, "Total3 : " + totalSemua);
    txtTotalBayar.setText(String.valueOf(totalSemua));

   txtDiskon.setText(String.valueOf(discount));
    txtTampil.setText("Rp" + (totalBayar) + ",00");

    
    
    clear2();
    txtIdBarang.requestFocus();
}



    private void autonumber(){
        try{
            java.sql.Connection kon = (Connection) konektor.koneksi();
            Statement s = kon.createStatement();
            String sql = "SELECT * FROM datapenjualan ORDER BY NoFaktur DESC";
            ResultSet r = s.executeQuery(sql);
            if (r.next()){
                String NoFaktur = r.getString("NoFaktur").substring(2);
                String TR = "" + (Integer.parseInt(NoFaktur)+1);
                String Nol = "";
                
                if (TR.length()==1)
                {Nol = "000";}
                else if (TR.length()==2)
                {Nol = "00";}
                else if (TR.length()==3)
                {Nol = "0";}
                else if (TR.length()==4)
                {Nol = "";}
                txtNoTransaksi.setText("TR" + Nol + TR);
                    }else {
                txtNoTransaksi.setText("TR0001");
            }
            r.close();
            s.close();
        }catch (Exception e){
            System.out.println("autonumber error");
        }
    }
    
    public void loadData() {
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

    
    int jumlah = Integer.parseInt(txtJumlah.getText());
    int harga = Integer.parseInt(txtHarga.getText());

    
    int totalBiaya = jumlah * harga;

    
    model.addRow(new Object[]{
        txtNoTransaksi.getText(),
        txtIdBarang.getText(),
        txtNamaBarang.getText(),
        txtJumlah.getText(),
        txtHarga.getText(),
        totalBiaya 
    });

    
    int lastRow = model.getRowCount() - 1;

    
    if (lastRow >= 0) {
       
        jTable1.setValueAt(totalBiaya, lastRow, 5);
    } else {
       
        System.out.println("Tidak ada baris dalam model.");
    }
}

    
    public void kosong(){
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        
        while (model.getRowCount()>0){
            model.removeRow(0);
        }
    }
    
    public void utama(){
        txtNoTransaksi.setText("");
        txtIdBarang.setText("");
        txtNamaBarang.setText("");
        txtHarga.setText("");
        txtJumlah.setText("");
        autonumber();
    }
    
    public void clear(){
        txtIdCustomer.setText("");
        txtMember.setSelectedItem("null");
        txtNamaCustomer.setText("");
        txtTotalBayar.setText("0");
        txtBayar.setText("0");
        txtKembalian.setText("0");
        txtTampil.setText("0");
    }
    
    public void clear2(){
        txtIdBarang.setText("");
        txtNamaBarang.setText("");
        txtHarga.setText("");
        txtJumlah.setText("");
    }
    
    public String getStatusPelanggan(String idPelanggan) {
    String status = "Non Member"; 
    try {
        java.sql.Connection kon = (Connection) konektor.koneksi();
        String query = "SELECT status FROM datamember WHERE id_member = ?";
        PreparedStatement statement = kon.prepareStatement(query);
        statement.setString(1, idPelanggan);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            status = resultSet.getString("status");
        } else {
            JOptionPane.showMessageDialog(null, "Tidak ada member dengan ID " + idPelanggan);
        }

        resultSet.close();
        statement.close();
        kon.close();
    } catch (Exception e) {
        e.printStackTrace();
    }

    return status;
}

    private void updateInventory() {
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    int rowCount = model.getRowCount();

    try {
        Connection kon = konektor.koneksi();
        for (int i = 0; i < rowCount; i++) {
            String idBarang = (String) model.getValueAt(i, 1);
            int jumlah = Integer.parseInt(model.getValueAt(i, 3).toString());

            
            String updateInventoryQuery = "UPDATE inventori SET stok = stok - ? WHERE id_barang = ?";
            PreparedStatement updateInventoryStatement = kon.prepareStatement(updateInventoryQuery);
            updateInventoryStatement.setInt(1, jumlah);
            updateInventoryStatement.setString(2, idBarang);
            updateInventoryStatement.executeUpdate();
            updateInventoryStatement.close();
        }
    } catch (Exception e) {
        System.out.println("Update inventory error: " + e.getMessage());
    }
}
    
    public transaksi() {
        initComponents();
        
        model = new DefaultTableModel();
        
        jTable1.setModel(model);
        
        model.addColumn("No Transaksi");
        model.addColumn("ID Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Jumlah");
        model.addColumn("Harga");
        model.addColumn("Total");
        
        utama();
        Date date = new Date();
        SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
        
        txtTanggal.setText(s.format(date));
        txtTotalBayar.setText("0");
        txtBayar.setText("0");
        txtKembalian.setText("0");
        txtIdCustomer.requestFocus();
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
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNoTransaksi = new javax.swing.JTextField();
        txtIdCustomer = new javax.swing.JTextField();
        txtNamaCustomer = new javax.swing.JTextField();
        txtTanggal = new javax.swing.JTextField();
        txtMember = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtIdBarang = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtNamaBarang = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtHarga = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtJumlah = new javax.swing.JTextField();
        tambah = new javax.swing.JButton();
        hapus = new javax.swing.JButton();
        simpan = new javax.swing.JButton();
        txtTampil = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTotalBayar = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtDiskon = new javax.swing.JTextField();
        txtBayar = new javax.swing.JTextField();
        txtKembalian = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(241, 210, 210));

        jPanel2.setBackground(new java.awt.Color(248, 111, 3));

        jLabel11.setFont(new java.awt.Font("Trajan Pro 3", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("TRANSAKSI");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(282, 282, 282)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel1.setText("No Transaksi");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel2.setText("Status Customer");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel3.setText("ID Customer");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel4.setText("Nama Customer");

        txtNoTransaksi.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtNoTransaksi.setEnabled(false);
        txtNoTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNoTransaksiActionPerformed(evt);
            }
        });

        txtIdCustomer.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

        txtNamaCustomer.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtNamaCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaCustomerActionPerformed(evt);
            }
        });

        txtTanggal.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtTanggal.setEnabled(false);

        txtMember.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtMember.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Member", "Non Member" }));
        txtMember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMemberActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel5.setText("Tanggal");

        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "No Transaksi", "ID Barang", "Nama Barang", "Jumlah", "Harga", "Total"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel6.setText("ID Barang");

        txtIdBarang.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel7.setText("Nama Barang");

        txtNamaBarang.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel8.setText("Harga");

        txtHarga.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel9.setText("Jumlah");

        txtJumlah.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtJumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJumlahActionPerformed(evt);
            }
        });

        tambah.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        tambah.setText("Tambah");
        tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahActionPerformed(evt);
            }
        });

        hapus.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        hapus.setText("Hapus");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });

        simpan.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        simpan.setText("Simpan");
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });

        txtTampil.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        txtTampil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTampilActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel10.setText("Total Bayar");

        txtTotalBayar.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtTotalBayar.setEnabled(false);
        txtTotalBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalBayarActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel12.setText("Diskon");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel13.setText("Bayar");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel14.setText("Kembalian");

        txtDiskon.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtDiskon.setEnabled(false);
        txtDiskon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiskonActionPerformed(evt);
            }
        });

        txtBayar.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBayarActionPerformed(evt);
            }
        });

        txtKembalian.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtKembalian.setEnabled(false);
        txtKembalian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKembalianActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton1.setText("EXIT");
        jButton1.setToolTipText("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(txtIdBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(327, 327, 327))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)))
                        .addGap(68, 68, 68)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel4))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtNamaCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                                                    .addComponent(txtIdCustomer)))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(56, 56, 56)
                                                .addComponent(txtNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(42, 42, 42))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(57, 57, 57)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(58, 58, 58)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNoTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMember, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(txtTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(148, 148, 148))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(jButton1)
                                .addGap(34, 34, 34)
                                .addComponent(simpan)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(106, 106, 106)
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtTampil, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDiskon, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotalBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtKembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(154, 154, 154))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(490, 490, 490)
                                .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(hapus, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tambah, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(txtNoTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMember, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtIdCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNamaCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtIdBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tambah)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hapus))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTotalBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtDiskon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtTampil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtKembalian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(simpan))
                        .addGap(16, 16, 16))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 704, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNamaCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaCustomerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaCustomerActionPerformed

    private void txtNoTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNoTransaksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoTransaksiActionPerformed

    private void txtJumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJumlahActionPerformed
        // TODO add your handling code here:
        tambahTransaksi();
    }//GEN-LAST:event_txtJumlahActionPerformed

    private void txtDiskonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiskonActionPerformed
tambahTransaksi();        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiskonActionPerformed

    private void txtKembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKembalianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKembalianActionPerformed

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
tambahTransaksi(); 
// TODO add your handling code here:
    }//GEN-LAST:event_tambahActionPerformed

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
int row = jTable1.getSelectedRow();
model.removeRow(row);
tambahTransaksi();
txtBayar.setText("0");
txtKembalian.setText("0");
// TODO add your handling code here:
    }//GEN-LAST:event_hapusActionPerformed

    private void txtBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBayarActionPerformed
        // TODO add your handling code here:
        try {
            int total = 0, bayar, kembalian;
            bayar = (int) Double.parseDouble(txtBayar.getText());
            
            String[] satu = txtTampil.getText().split("Rp");
            String[] dua = satu[1].split(",");
            for(String tes: dua){
                total += Double.parseDouble(tes);
            }

            if (total > bayar) {
                JOptionPane.showMessageDialog(null, "Uang tidak cukup untuk melakukan pembayaran");
            } else {
                kembalian = bayar - total;
                txtKembalian.setText(String.valueOf(kembalian));
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Masukkan angka yang valid untuk pembayaran");
            e.printStackTrace();
        }
    
    }//GEN-LAST:event_txtBayarActionPerformed

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        
        String noTransaksi = txtNoTransaksi.getText();
        String tanggal = txtTanggal.getText();
        String IDCustomer = txtIdCustomer.getText();
        String Member = (String) txtMember.getSelectedItem();
        String total = txtTotalBayar.getText();
        
        try {
    java.sql.Connection kon = (Connection) konektor.koneksi();
    String sql = "INSERT INTO datapenjualan (NoFaktur, tanggal, id_customer, id_member, totalBeli) VALUES (?, ?, ?, ?, ?)";
    PreparedStatement p = kon.prepareStatement(sql);
    p.setString(1, noTransaksi);
    p.setString(2, tanggal);
    p.setString(3, IDCustomer);
    p.setString(4, Member);
    p.setString(5, total);
    p.executeUpdate();
    p.close();
} catch (Exception e) {
    e.printStackTrace();
}

try {
    java.sql.Connection kon = (Connection) konektor.koneksi();
    int baris = jTable1.getRowCount();

    for (int i = 0; i < baris; i++) {
        String sql = "INSERT INTO penjualan (Nofaktur, id_barang, nama_barang, jumlah, harga, total) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement p = kon.prepareStatement(sql);
        p.setString(1, jTable1.getValueAt(i, 0).toString());
        p.setString(2, jTable1.getValueAt(i, 1).toString());
        p.setString(3, jTable1.getValueAt(i, 2).toString());
        p.setString(4, jTable1.getValueAt(i, 3).toString());
        p.setString(5, jTable1.getValueAt(i, 4).toString());
        p.setString(6, jTable1.getValueAt(i, 5).toString());
        p.executeUpdate();
        p.close();
    }
} catch (Exception e) {
    e.printStackTrace();
}
        clear();
        utama();
        autonumber();
        kosong();
        updateInventory();
        txtTampil.setText("Rp. 0");
        
        
        
    }//GEN-LAST:event_simpanActionPerformed

    private void txtTotalBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalBayarActionPerformed
tambahTransaksi();        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalBayarActionPerformed

    private void txtMemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMemberActionPerformed
     // TODO add your handling code here:
    }//GEN-LAST:event_txtMemberActionPerformed

    private void txtTampilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTampilActionPerformed
tambahTransaksi();        // TODO add your handling code here:
    }//GEN-LAST:event_txtTampilActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
new mainMenu().setVisible(true);
    dispose();         // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton hapus;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton simpan;
    private javax.swing.JButton tambah;
    private javax.swing.JTextField txtBayar;
    private javax.swing.JTextField txtDiskon;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtIdBarang;
    private javax.swing.JTextField txtIdCustomer;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JTextField txtKembalian;
    private javax.swing.JComboBox<String> txtMember;
    private javax.swing.JTextField txtNamaBarang;
    private javax.swing.JTextField txtNamaCustomer;
    private javax.swing.JTextField txtNoTransaksi;
    private javax.swing.JTextField txtTampil;
    private javax.swing.JTextField txtTanggal;
    private javax.swing.JTextField txtTotalBayar;
    // End of variables declaration//GEN-END:variables
}
