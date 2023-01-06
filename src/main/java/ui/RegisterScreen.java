package ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.Key;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import login.User;
import symmetricKey.randomsecretkey;
import symmetricKey.symmetriccrypto;

public class RegisterScreen extends javax.swing.JFrame {

    private static final String USERFILENAME = "user.txt";
    private static final String USERFILEHEADER = "Username||Password" + "\n";
    private static final String USERFILENAME2 = "randomkey.txt";
    
    /**
     * Creates new form AddCustomerView
     */
    public RegisterScreen() {
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

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        RegisterButton = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        GoBackButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        txtConfirmPassword = new javax.swing.JPasswordField();
        txtUsername = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Register Screen");
        setLocation(new java.awt.Point(500, 150));
        setMinimumSize(new java.awt.Dimension(850, 550));
        setResizable(false);

        jPanel1.setLayout(null);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        jLabel4.setText("Register New Staff");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(220, 20, 400, 60);

        jLabel6.setFont(new java.awt.Font("Unispace", 1, 14)); // NOI18N
        jLabel6.setText("Username");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(290, 180, 80, 20);

        RegisterButton.setFont(new java.awt.Font("Unispace", 1, 18)); // NOI18N
        RegisterButton.setText("Confirm Registration");
        RegisterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegisterButtonActionPerformed(evt);
            }
        });
        jPanel1.add(RegisterButton);
        RegisterButton.setBounds(290, 350, 280, 40);

        jLabel7.setFont(new java.awt.Font("Unispace", 1, 14)); // NOI18N
        jLabel7.setText("Password");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(290, 230, 80, 30);

        GoBackButton.setFont(new java.awt.Font("Unispace", 1, 18)); // NOI18N
        GoBackButton.setText("Cancel");
        GoBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GoBackButtonActionPerformed(evt);
            }
        });
        jPanel1.add(GoBackButton);
        GoBackButton.setBounds(370, 420, 120, 40);
        jPanel1.add(jSeparator1);
        jSeparator1.setBounds(0, 230, 850, 10);

        jLabel8.setFont(new java.awt.Font("Unispace", 1, 14)); // NOI18N
        jLabel8.setText("Confirm Password");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(220, 290, 150, 30);

        jLabel9.setFont(new java.awt.Font("Unispace", 1, 14)); // NOI18N
        jLabel9.setText("Login Credentials");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(340, 110, 170, 20);
        jPanel1.add(txtPassword);
        txtPassword.setBounds(380, 230, 216, 30);
        jPanel1.add(txtConfirmPassword);
        txtConfirmPassword.setBounds(380, 290, 216, 30);
        jPanel1.add(txtUsername);
        txtUsername.setBounds(380, 170, 216, 30);
        jPanel1.add(jLabel11);
        jLabel11.setBounds(0, 0, 850, 550);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RegisterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegisterButtonActionPerformed
       
        if(txtUsername.getText().equals("")
                ||new String(txtPassword.getPassword()).equals("")||new String(txtConfirmPassword.getPassword()).equals("")){
            JOptionPane.showMessageDialog(this, "Please fill up all the fields!");
            return;
        }
        String username = txtUsername.getText();
        char[] password = txtPassword.getPassword();
        char[] confirmPassword = txtConfirmPassword.getPassword();
        
        if(!new String(password).equals(new String(confirmPassword))){
            JOptionPane.showMessageDialog(this, "Password does not match!");
            return;
        }
        
        User user = new User(username,password);
        BufferedReader brUser;
        try {
            brUser = new BufferedReader(new FileReader(USERFILENAME));
            String row;
            brUser.readLine();
            while ((row = brUser.readLine())!= null){
                String[] data = row.split("\\|\\|");
                String txtUser = data[0];
                if(txtUser.equals(user.getUsername())){
                  JOptionPane.showMessageDialog(this, "Username has been used, please enter another username.");
                  return;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RegisterScreen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RegisterScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        symmetriccrypto symm = new symmetriccrypto();
        
        
        //HERE
        Key key = randomsecretkey.create();
        String passwordstr = String.valueOf(password);
        
        String encrypted = "";
        try {
            encrypted = symm.encrypt(passwordstr, key); 
            String decrypt = symm.decrypt(encrypted, key);
        }catch (Exception e) {
            e.printStackTrace();
        }
        
        byte[] keyBytes = key.getEncoded();
        String a = Base64.getEncoder().encodeToString(keyBytes);
        
        try (BufferedWriter bwUser = new BufferedWriter(new FileWriter(USERFILENAME2, true))) {
            bwUser.write(user.getUsername()+ "||"+ a + "\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        try (BufferedWriter bwUser = new BufferedWriter(new FileWriter(USERFILENAME, true))) {
            bwUser.write(user.getUsername() + "||" + encrypted +  "\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        JOptionPane.showMessageDialog(this, "New Staff successfully added!");
        this.dispose();
        new LoginView().setVisible(true);
        
    }//GEN-LAST:event_RegisterButtonActionPerformed

    private void GoBackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GoBackButtonActionPerformed
        this.dispose();
        new LoginView().setVisible(true);
    }//GEN-LAST:event_GoBackButtonActionPerformed

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
            java.util.logging.Logger.getLogger(RegisterScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegisterScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegisterScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegisterScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new RegisterScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton GoBackButton;
    private javax.swing.JButton RegisterButton;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPasswordField txtConfirmPassword;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
