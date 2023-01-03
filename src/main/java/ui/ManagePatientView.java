package ui;

import Server.SalesExec;
import Server.Server;
import Server.UserProfileLoader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ManagePatientView extends javax.swing.JFrame {
    DefaultTableModel model;
    /**
     * Creates new form ManagerCustomerView
     */
    public ManagePatientView() {
        initComponents();
        loadTable();
    }
    
    private void loadTable(){
        model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
               return false;
            }
        };
        model.addColumn("Sales Executive ID");
        model.addColumn("First Name");
        model.addColumn("Last Name");
        model.addColumn("IC/Passport Number");
        model.addColumn("Phone Number");
        
        ArrayList<Patient> patientList = new UserProfileLoader().loadSalesExec();
        
        for(int i = 0; i < patientList.size(); i++){
            String execId = patientList.get(i).getExecID();
            String execfirstname = patientList.get(i).getExecFirstName();
            String execlastname = patientList.get(i).getExecLastName();
            String execnric = patientList.get(i).getExecNRIC();
            String phone = patientList.get(i).getExecPhone();
            Object[] data = {execId, execfirstname, execlastname, execnric, phone};
            model.addRow(data);
        }
        tblSalesExec.setModel(model);
    }
    
    private void loadTable(String search){
        model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
               return false;
            }
        };
        model.addColumn("Sales Executive ID");
        model.addColumn("First Name");
        model.addColumn("Last Name");
        model.addColumn("IC/Passport Number");
        model.addColumn("Phone Number");
        
        ArrayList<SalesExec> exec = new UserProfileLoader().loadSalesExec(search);
        
        for(int i = 0; i < exec.size(); i++){
            String execId = exec.get(i).getExecID();
            String execfirstname = exec.get(i).getExecFirstName();
            String execlastname = exec.get(i).getExecLastName();
            String execnric = exec.get(i).getExecNRIC();
            String phone = exec.get(i).getExecPhone();
            Object[] data = {execId, execfirstname, execlastname, execnric, phone};
            model.addRow(data);
        }
        tblSalesExec.setModel(model);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnViewAll = new javax.swing.JButton();
        btnEditSalesExec = new javax.swing.JButton();
        btnDeleteSalesExec = new javax.swing.JButton();
        btnMainMenu = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSalesExec = new javax.swing.JTable();
        btnAddSalesExec = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnViewAll.setFont(new java.awt.Font("Unispace", 1, 14)); // NOI18N
        btnViewAll.setText("View All Patients");
        btnViewAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewAllActionPerformed(evt);
            }
        });

        btnEditSalesExec.setFont(new java.awt.Font("Unispace", 1, 14)); // NOI18N
        btnEditSalesExec.setText("Edit Selected Patient");
        btnEditSalesExec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditSalesExecActionPerformed(evt);
            }
        });

        btnDeleteSalesExec.setFont(new java.awt.Font("Unispace", 1, 14)); // NOI18N
        btnDeleteSalesExec.setText("Delete Patient");
        btnDeleteSalesExec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteSalesExecActionPerformed(evt);
            }
        });

        btnMainMenu.setFont(new java.awt.Font("Unispace", 1, 14)); // NOI18N
        btnMainMenu.setText("Return");
        btnMainMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMainMenuActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Old English Text MT", 1, 48)); // NOI18N
        jLabel1.setText("Manage Patient Records");

        tblSalesExec.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblSalesExec);

        btnAddSalesExec.setFont(new java.awt.Font("Unispace", 1, 14)); // NOI18N
        btnAddSalesExec.setText("Add New Patient");
        btnAddSalesExec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddSalesExecActionPerformed(evt);
            }
        });

        btnSearch.setFont(new java.awt.Font("Unispace", 1, 14)); // NOI18N
        btnSearch.setText("Search ID or Name");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 818, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSearch))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnDeleteSalesExec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnViewAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAddSalesExec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEditSalesExec, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnMainMenu)
                                .addGap(62, 62, 62)))))
                .addContainerGap(21, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(166, 166, 166))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearch))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnViewAll)
                            .addComponent(btnEditSalesExec))
                        .addGap(22, 22, 22)
                        .addComponent(btnAddSalesExec)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeleteSalesExec)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnMainMenu)
                        .addGap(27, 27, 27))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnViewAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewAllActionPerformed

        loadTable();
    }//GEN-LAST:event_btnViewAllActionPerformed

    private void btnDeleteSalesExecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteSalesExecActionPerformed

        if(tblSalesExec.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(this, "Please select a sales executive to remove!");
            return;                
        }
        
        int rowSelect = tblSalesExec.getSelectedRow();
        String customerId = (String)model.getValueAt(rowSelect, 0);
        try {
            Registry myRegistry = LocateRegistry.getRegistry("127.0.0.1", 6969);
             
            // search for Server service
            Server impl = (Server) myRegistry.lookup("Server");
             
            // call server's methods    
            impl.deleteUser(new UserProfileLoader().createSalesExec(customerId));
             
            System.out.println("Connection established");
        } catch (Exception e) {
            System.out.println("Unable to connect with server. Please try again.");
            //System.exit(0);
            e.printStackTrace();
        }
        //new UserProfileManager().deleteUser(new UserProfileLoader().createSalesExec(customerId));
        
        loadTable();
    }//GEN-LAST:event_btnDeleteSalesExecActionPerformed

    private void btnAddSalesExecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddSalesExecActionPerformed

        this.dispose();
        new AddPatientView().setVisible(true);
    }//GEN-LAST:event_btnAddSalesExecActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed

        String search = txtSearch.getText();
        loadTable(search);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnMainMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMainMenuActionPerformed

        this.dispose();
        new AdminMenuView().setVisible(true);
    }//GEN-LAST:event_btnMainMenuActionPerformed

    private void btnEditSalesExecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditSalesExecActionPerformed

        if(tblSalesExec.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(this, "Please select a sales executive to edit!");
            return;
        }
        this.dispose();
        int rowSelect = tblSalesExec.getSelectedRow();
        String customerId = (String)model.getValueAt(rowSelect, 0);
        new EditSalesExecView(new UserProfileLoader().createSalesExec(customerId)).setVisible(true);
    }//GEN-LAST:event_btnEditSalesExecActionPerformed

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
            java.util.logging.Logger.getLogger(ManagePatientView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagePatientView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagePatientView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagePatientView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new ManagePatientView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddSalesExec;
    private javax.swing.JButton btnDeleteSalesExec;
    private javax.swing.JButton btnEditSalesExec;
    private javax.swing.JButton btnMainMenu;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnViewAll;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblSalesExec;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
