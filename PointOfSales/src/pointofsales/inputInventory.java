/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pointofsales;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
/**
 *
 * @author ACER
 */
public class inputInventory extends javax.swing.JFrame {

    /**
     * Creates new form inputInventory
     */
    public inputInventory() {
        initComponents();
         try {
           BufferedImage beam = ImageIO.read(getClass().getResource("login.png"));
             setIconImage(beam); 
        } catch (IOException ex) {
            Logger.getLogger(inputInventory.class.getName()).log(Level.SEVERE, null, ex);
        }
       this.setTitle("Input Inventory");
       setLocationRelativeTo(null);
    
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtIdBarang = new javax.swing.JTextField();
        txtNamaBarang = new javax.swing.JTextField();
        txtHarga = new javax.swing.JTextField();
        txtPersediaan = new javax.swing.JTextField();
        exit = new javax.swing.JButton();
        save = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(700, 500));

        jPanel1.setBackground(new java.awt.Color(241, 210, 210));

        jPanel2.setBackground(new java.awt.Color(255, 102, 0));

        jLabel1.setFont(new java.awt.Font("Trajan Pro 3", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("INPUT INVENTORY");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(211, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(208, 208, 208))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel2.setText("ID Barang");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel3.setText("Nama Barang");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel4.setText("Harga");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel5.setText("Persediaan");

        txtIdBarang.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        txtIdBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtIdBarangMouseClicked(evt);
            }
        });
        txtIdBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdBarangActionPerformed(evt);
            }
        });

        txtNamaBarang.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        txtNamaBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNamaBarangMouseClicked(evt);
            }
        });
        txtNamaBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaBarangActionPerformed(evt);
            }
        });

        txtHarga.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        txtHarga.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtHargaMouseClicked(evt);
            }
        });
        txtHarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHargaActionPerformed(evt);
            }
        });

        txtPersediaan.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        txtPersediaan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPersediaanMouseClicked(evt);
            }
        });
        txtPersediaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPersediaanActionPerformed(evt);
            }
        });

        exit.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        exit.setText("EXIT");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });

        save.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        save.setText("SAVE");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtIdBarang, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                                    .addComponent(txtNamaBarang)
                                    .addComponent(txtHarga)
                                    .addComponent(txtPersediaan)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(exit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(save)))
                        .addGap(108, 108, 108))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtIdBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtPersediaan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exit)
                    .addComponent(save))
                .addGap(38, 38, 38))
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
    }// </editor-fold>//GEN-END:initComponents

    private void txtHargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHargaActionPerformed

    private void txtPersediaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPersediaanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPersediaanActionPerformed

    private void txtIdBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdBarangActionPerformed

    private void txtIdBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtIdBarangMouseClicked
txtIdBarang.setText(null);        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdBarangMouseClicked

    private void txtNamaBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaBarangActionPerformed

    private void txtNamaBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNamaBarangMouseClicked
txtNamaBarang.setText(null);        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaBarangMouseClicked

    private void txtHargaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtHargaMouseClicked
txtHarga.setText(null);        // TODO add your handling code here:
    }//GEN-LAST:event_txtHargaMouseClicked

    private void txtPersediaanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPersediaanMouseClicked
txtPersediaan.setText(null);        // TODO add your handling code here:
    }//GEN-LAST:event_txtPersediaanMouseClicked

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
String IdBarang = txtIdBarang.getText();
    String NamaBarang = txtNamaBarang.getText();
    String Harga = txtHarga.getText();
    String Persediaan = txtPersediaan.getText();
    
    
    if(IdBarang.equals("")||NamaBarang.equals("")||Harga.equals("")||Persediaan.equals("")){
            JOptionPane.showMessageDialog(null,"Isi Semua Form Yang Tersedia ");
        
    }
    
    else{
        try {
            String sql1 = String.format("INSERT INTO inventoribarang(id_barang, nama_barang, harga, stok)"
                    + " VALUES (\"%s\",\"%s\",\"%s\",\"%s\")",txtIdBarang.getText(), txtNamaBarang.getText(), txtHarga.getText(), txtPersediaan.getText());
            java.sql.Connection kon = (Connection) konektor.koneksi();
            java.sql.PreparedStatement pdt = kon.prepareStatement(sql1);
            pdt.execute();
           JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
        new inventoriBarang().setVisible(true);
        dispose();
        } catch (Exception b) {
            JOptionPane.showMessageDialog(this, b.getMessage());
            b.printStackTrace();
        }   
  
    }          // TODO add your handling code here:
    }//GEN-LAST:event_saveActionPerformed

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
new mainMenu().setVisible(true);
    dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_exitActionPerformed

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
            java.util.logging.Logger.getLogger(inputInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(inputInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(inputInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(inputInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new inputInventory().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton save;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtIdBarang;
    private javax.swing.JTextField txtNamaBarang;
    private javax.swing.JTextField txtPersediaan;
    // End of variables declaration//GEN-END:variables
}
