package ui;

import com.mycompany.core.Block;
import com.mycompany.core.Blockchain;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JOptionPane;
import com.mycompany.core.Patient;
import com.mycompany.core.TranxCollection;
import java.io.File;

public class AddPatientView extends javax.swing.JFrame {

            static final String MASTER_DIR = "master";
        static final String MASTER_BINARY = MASTER_DIR+"/mychain";
    /**
     * Creates new form AddProduct
     */
    public AddPatientView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtFName = new javax.swing.JTextField();
        btnAddProduct = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtGender = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtPhoneNumber = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtLName = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtIC = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtBloodType = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtDisability = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtPreExisting = new javax.swing.JTextField();
        txtMedicationPlan = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtCurrentDIsease = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(550, 150));
        setMinimumSize(new java.awt.Dimension(450, 550));

        jLabel1.setFont(new java.awt.Font("Unispace", 0, 14)); // NOI18N
        jLabel1.setText("ID");

        txtID.setFont(new java.awt.Font("Unispace", 0, 14)); // NOI18N
        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Unispace", 0, 14)); // NOI18N
        jLabel2.setText("First Name");

        txtFName.setFont(new java.awt.Font("Unispace", 0, 14)); // NOI18N

        btnAddProduct.setFont(new java.awt.Font("Unispace", 0, 18)); // NOI18N
        btnAddProduct.setText("Add Patient");
        btnAddProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProductActionPerformed(evt);
            }
        });

        btnCancel.setFont(new java.awt.Font("Unispace", 0, 18)); // NOI18N
        btnCancel.setText("Return");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Unispace", 0, 14)); // NOI18N
        jLabel3.setText("Gender");

        txtGender.setFont(new java.awt.Font("Unispace", 0, 14)); // NOI18N
        txtGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        txtGender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGenderActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Old English Text MT", 1, 48)); // NOI18N
        jLabel4.setText("Add New Patient");

        txtPhoneNumber.setFont(new java.awt.Font("Unispace", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Unispace", 0, 14)); // NOI18N
        jLabel6.setText("Phone Number ");

        jLabel7.setFont(new java.awt.Font("Unispace", 0, 14)); // NOI18N
        jLabel7.setText("Last Name");

        txtLName.setFont(new java.awt.Font("Unispace", 0, 14)); // NOI18N
        txtLName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLNameActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Unispace", 0, 14)); // NOI18N
        jLabel8.setText("IC Number");

        txtIC.setFont(new java.awt.Font("Unispace", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Unispace", 0, 14)); // NOI18N
        jLabel5.setText("Blood Type");

        txtBloodType.setFont(new java.awt.Font("Unispace", 0, 14)); // NOI18N
        txtBloodType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBloodTypeActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Unispace", 0, 14)); // NOI18N
        jLabel9.setText("Disability");

        txtDisability.setFont(new java.awt.Font("Unispace", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Unispace", 0, 14)); // NOI18N
        jLabel10.setText("Pre-existing Condition(s)");

        txtPreExisting.setFont(new java.awt.Font("Unispace", 0, 14)); // NOI18N

        txtMedicationPlan.setFont(new java.awt.Font("Unispace", 0, 14)); // NOI18N
        txtMedicationPlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMedicationPlanActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Unispace", 0, 14)); // NOI18N
        jLabel11.setText("Current Medication Plan");

        jLabel12.setFont(new java.awt.Font("Unispace", 0, 14)); // NOI18N
        jLabel12.setText("Current Disease");

        txtCurrentDIsease.setFont(new java.awt.Font("Unispace", 0, 14)); // NOI18N
        txtCurrentDIsease.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCurrentDIseaseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(296, 296, 296))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(234, 234, 234)
                        .addComponent(btnCancel)
                        .addGap(205, 205, 205)
                        .addComponent(btnAddProduct))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtGender, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtIC, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtLName, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFName, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtCurrentDIsease, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtPreExisting, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel5)
                                                .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
                                            .addComponent(jLabel9))
                                        .addGap(24, 24, 24)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtDisability, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtBloodType, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtMedicationPlan, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel4)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBloodType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtFName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDisability, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtLName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPreExisting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCurrentDIsease, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txtIC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(txtMedicationPlan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddProduct)
                    .addComponent(btnCancel))
                .addGap(72, 72, 72))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
        new MenuView().setVisible(true);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnAddProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProductActionPerformed
        if(txtID.getText().equals("")||txtFName.getText().equals("")
                ||txtLName.getText().equals("")||txtIC.getText().equals("")||txtPhoneNumber.getText().equals("")
                ||txtBloodType.getText().equals("")||txtDisability.getText().equals("")||txtPreExisting.getText().equals("")
                ||txtCurrentDIsease.getText().equals("")||txtMedicationPlan.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Please fill up all fields!");
            return; 
        }
        
        String ID = txtID.getText();
        String Fname = txtFName.getText();
        String Lname = txtLName.getText();
        String IC = txtIC.getText();
        String phoneNumber = txtPhoneNumber.getText();
        String gender = txtGender.getSelectedItem().toString();
        String bloodType = txtBloodType.getText();;
        String disability = txtDisability.getText();;
        String preExistingCondition = txtPreExisting.getText();;
        String currentDisease = txtCurrentDIsease.getText();;
        String currentMedicationPlan = txtMedicationPlan.getText();;
//        try{
//            if(Integer.parseInt(phoneNumber) < 0 || Integer.parseInt(IC)< 0){
//                JOptionPane.showMessageDialog(this, "Please make sure PhoneNumber and IC fields are more than 0!");
//                return;
//            }
//        }catch(NumberFormatException e){
//            JOptionPane.showMessageDialog(this, "Please make sure price and quantity fields are numbers!");
//            return; 
//        }
        
        Patient p;
        p = new Patient(ID,Fname,Lname,IC,phoneNumber,gender, bloodType, disability,preExistingCondition,currentDisease, currentMedicationPlan);
        Blockchain bc = Blockchain.getInstance(MASTER_BINARY);
        if ( !( new File(MASTER_DIR).exists() ) ) {
            /* make a dir if not found */            
            new File( MASTER_DIR ).mkdir();
            TranxCollection tranxs = new TranxCollection();
            tranxs.add(p);
            Block genesisBlock = new Block("0");
            genesisBlock.setTranxs(tranxs);
            bc.genesis(genesisBlock);
        }
        else {
            TranxCollection tranxs = new TranxCollection();
            tranxs.add(p);
            String prevHash = bc.get().getLast().getHeader().getCurrHash();
            Block newBlock = new Block( prevHash );
            newBlock.setTranxs(tranxs);
            bc.nextBlock(newBlock);
        }
        
        JOptionPane.showMessageDialog(this, "New Patient Added.");
    }//GEN-LAST:event_btnAddProductActionPerformed

    private void txtGenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGenderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGenderActionPerformed

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDActionPerformed

    private void txtLNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLNameActionPerformed

    private void txtBloodTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBloodTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBloodTypeActionPerformed

    private void txtMedicationPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMedicationPlanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMedicationPlanActionPerformed

    private void txtCurrentDIseaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCurrentDIseaseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCurrentDIseaseActionPerformed

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
            java.util.logging.Logger.getLogger(AddPatientView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddPatientView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddPatientView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddPatientView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddPatientView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddProduct;
    private javax.swing.JButton btnCancel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtBloodType;
    private javax.swing.JTextField txtCurrentDIsease;
    private javax.swing.JTextField txtDisability;
    private javax.swing.JTextField txtFName;
    private javax.swing.JComboBox<String> txtGender;
    private javax.swing.JTextField txtIC;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtLName;
    private javax.swing.JTextField txtMedicationPlan;
    private javax.swing.JTextField txtPhoneNumber;
    private javax.swing.JTextField txtPreExisting;
    // End of variables declaration//GEN-END:variables
}
