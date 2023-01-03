package ui;

import com.mycompany.core.Appointment;
import javax.swing.JOptionPane;
import static digitalSignature.MyKeyPair.dSCreate;
import digitalSignature.MySignature;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.PrivateKey;
import java.util.Scanner;

public class AddAppointmentView extends javax.swing.JFrame {

    static final String FILENAME = "appointment.txt";
    static final String MASTER_DIR = "master";
    static final String MASTER_BINARY = MASTER_DIR+"/mychain";
    MySignature sig = new MySignature();
    /**
     * Creates new form AddProduct
     */
    public AddAppointmentView() {
        initComponents();
        txtID.setEnabled(false);
    }
    
   public int autoIdCounter(){
       int count = 0;
       try{  
           File file = new File(FILENAME);
           if (file.exists()){
                Scanner sc = new Scanner(FILENAME);
                while(sc.hasNextLine()) {
                    sc.nextLine();
                    count++;
                }
                //count +=1;
            }else{
               count = 1;
           }
       }catch(Exception e ){
           e.getStackTrace();
       }
       
       return count;
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
        txtDate = new javax.swing.JTextField();
        btnAddAppointment = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtDeptName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtLPatientID = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtDoctorName = new javax.swing.JTextField();

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
        jLabel2.setText("Date");

        txtDate.setFont(new java.awt.Font("Unispace", 0, 14)); // NOI18N

        btnAddAppointment.setFont(new java.awt.Font("Unispace", 0, 18)); // NOI18N
        btnAddAppointment.setText("Create");
        btnAddAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddAppointmentActionPerformed(evt);
            }
        });

        btnCancel.setFont(new java.awt.Font("Unispace", 0, 18)); // NOI18N
        btnCancel.setText("Return");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Old English Text MT", 1, 48)); // NOI18N
        jLabel4.setText("Add New Appointment");

        txtDeptName.setFont(new java.awt.Font("Unispace", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Unispace", 0, 14)); // NOI18N
        jLabel6.setText("Department Name");

        jLabel7.setFont(new java.awt.Font("Unispace", 0, 14)); // NOI18N
        jLabel7.setText("Patient ID");

        txtLPatientID.setFont(new java.awt.Font("Unispace", 0, 14)); // NOI18N
        txtLPatientID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLPatientIDActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Unispace", 0, 14)); // NOI18N
        jLabel8.setText("Doctor Name");

        txtDoctorName.setFont(new java.awt.Font("Unispace", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(181, 181, 181)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(btnCancel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnAddAppointment))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtDoctorName, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtLPatientID, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtDeptName, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(jLabel4)))
                .addContainerGap(131, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel4)
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtLPatientID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtDoctorName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtDeptName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddAppointment)
                    .addComponent(btnCancel))
                .addGap(72, 72, 72))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
        new ManageAppointmentView().setVisible(true);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnAddAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddAppointmentActionPerformed
        String data = "";
        PrivateKey privateKey = null;
        if(txtDate.getText().equals("")
                ||txtLPatientID.getText().equals("")||txtDoctorName.getText().equals("")||txtDeptName.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Please fill up all fields!");
            return; 
        }
        
        String ID = "AP"+ Integer.toString(autoIdCounter());
                txtID.getText();
        //Double Check here
//        txtID.setText("POKEMON");
        String date = txtDate.getText();
        String patientID = txtLPatientID.getText();
        String doctorName = txtDoctorName.getText();
        String departmentName = txtDeptName.getText();
//        try{
//            if(Integer.parseInt(phoneNumber) < 0 || Integer.parseInt(IC)< 0){
//                JOptionPane.showMessageDialog(this, "Please make sure PhoneNumber and IC fields are more than 0!");
//                return;
//            }
//        }catch(NumberFormatException e){
//            JOptionPane.showMessageDialog(this, "Please make sure price and quantity fields are numbers!");
//            return; 
//        }

        Appointment a;
        a = new Appointment(ID,date, patientID, doctorName, departmentName);
        try{
            File file = new File(FILENAME);
            if (file.exists() && file.isFile()) {
                data = sig.sign(String.valueOf(a), privateKey);
            } else {
                privateKey = dSCreate();
                data = sig.sign(String.valueOf(a), privateKey);
            }
        } catch (Exception e) {
            System.out.print(e);
        }

        System.out.println("Digital Signature: "+ data);
         a = new Appointment(ID,date, patientID, doctorName, departmentName,data);
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME, true))) {
            bw.write(ID + "||" + date + "||" 
                    + patientID + "||" +doctorName + "||" + departmentName + "||" + data+"\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        JOptionPane.showMessageDialog(this, "New Appointment Added.");
    }//GEN-LAST:event_btnAddAppointmentActionPerformed

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDActionPerformed

    private void txtLPatientIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLPatientIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLPatientIDActionPerformed

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
            java.util.logging.Logger.getLogger(AddAppointmentView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddAppointmentView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddAppointmentView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddAppointmentView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddAppointmentView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddAppointment;
    private javax.swing.JButton btnCancel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtDeptName;
    private javax.swing.JTextField txtDoctorName;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtLPatientID;
    // End of variables declaration//GEN-END:variables
}
