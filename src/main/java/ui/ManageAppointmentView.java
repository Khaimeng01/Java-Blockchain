package ui;

import com.mycompany.core.Appointment;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ManageAppointmentView extends javax.swing.JFrame {
    String APPFILENAME = "appointment.txt";
    DefaultTableModel model;
    Appointment currentApp;
    private static final String FILEHEADER = "ID||Date||PatientID||DoctorName||DepartmentName||DigitalSignature" + System.lineSeparator();
    /**
     * Creates new form ManagerCustomerView
     */
    public ManageAppointmentView() {
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
        model.addColumn("ID");
        model.addColumn("Appointment Date");
        model.addColumn("Patient ID");
        model.addColumn("Doctor's Name");
        model.addColumn("Department");
        model.addColumn("Signature");
        model.addColumn("Validity");
        
        //ArrayList<Appointment> appList = new Appointment().loadAppointment();
        ArrayList<Appointment> appList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(APPFILENAME))) {
            br.readLine();
            String row;
            while((row = br.readLine())!= null){
                String[] data = row.split("\\|\\|");    
                String ID = data[0];
                String date = data[1];
                String patientID = data[2];
                String doctorName = data[3];
                String departmentName = data[4];
                String digitalSignature = data[5];
                appList.add(new Appointment(ID,date,patientID,doctorName,departmentName,digitalSignature));  
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Khai Meng here
        for(int i = 0; i < appList.size(); i++){
            String ID = appList.get(i).getID();
            String appDate = appList.get(i).getDate();
            String patientID = appList.get(i).getPatientID();
            String doctorName = appList.get(i).getDoctorName();
            String department = appList.get(i).getDepartmentName();
            String digitalSignature = appList.get(i).getDigitalSignature();
            Object[] data = {ID, appDate, patientID, doctorName, department,digitalSignature};
            model.addRow(data);
        }
        tblAppointment.setModel(model);
    }
    
    private void loadTable(String search) throws FileNotFoundException, IOException{
        model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
               return false;
            }
        };
        model.addColumn("ID");
        model.addColumn("Appointment Date");
        model.addColumn("Patient ID");
        model.addColumn("Doctor's Name");
        model.addColumn("Department");
        model.addColumn("Signature");
        model.addColumn("Validity");
        
//        ArrayList<Appointment> appList = new Appointment().loadAppointment(search);
        ArrayList<Appointment> appList = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(APPFILENAME))) {
            br.readLine();
            String row;
            while((row = br.readLine())!= null){
                String[] data = row.split("\\|\\|");            
                String ID = data[0];
                String date = data[1];
                String patientID = data[2];
                String doctorName = data[3];
                String departmentName = data[4];
                String digitalSignature = data[5];
                Appointment c = new Appointment(ID,date,patientID,doctorName,departmentName,digitalSignature);   
                if((c.getID().matches(search+".*"))|| (c.getDate().matches(search+".*")) || (c.getPatientID().matches(search+".*"))
                        || (c.getDoctorName().matches(search+".*")) || (c.getDepartmentName().matches(search+".*"))){
                    appList.add(c);
                }             
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < appList.size(); i++){
            String ID = appList.get(i).getID();
            String appDate = appList.get(i).getDate();
            String patientID = appList.get(i).getPatientID();
            String doctorName = appList.get(i).getDoctorName();
            String department = appList.get(i).getDepartmentName();
            String digitalSignature = appList.get(i).getDigitalSignature();
            Object[] data = {ID, appDate, patientID, doctorName, department,digitalSignature};
            model.addRow(data);
        }
        tblAppointment.setModel(model);
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
        btnEditApp = new javax.swing.JButton();
        btnDeleteApp = new javax.swing.JButton();
        btnMainMenu = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAppointment = new javax.swing.JTable();
        btnAddApp = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnViewAll.setFont(new java.awt.Font("Unispace", 1, 14)); // NOI18N
        btnViewAll.setText("View All Appointments");
        btnViewAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewAllActionPerformed(evt);
            }
        });

        btnEditApp.setFont(new java.awt.Font("Unispace", 1, 14)); // NOI18N
        btnEditApp.setText("Edit Selected Appointment");
        btnEditApp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditAppActionPerformed(evt);
            }
        });

        btnDeleteApp.setFont(new java.awt.Font("Unispace", 1, 14)); // NOI18N
        btnDeleteApp.setText("Delete Selected Appointment");
        btnDeleteApp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteAppActionPerformed(evt);
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
        jLabel1.setText("Manage Appointments");

        tblAppointment.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblAppointment);

        btnAddApp.setFont(new java.awt.Font("Unispace", 1, 14)); // NOI18N
        btnAddApp.setText("Add New Appointment");
        btnAddApp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddAppActionPerformed(evt);
            }
        });

        btnSearch.setFont(new java.awt.Font("Unispace", 1, 14)); // NOI18N
        btnSearch.setText("Search");
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 818, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(73, 73, 73)
                                .addComponent(btnSearch))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnDeleteApp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnViewAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnAddApp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(btnMainMenu)
                                        .addGap(62, 62, 62))
                                    .addComponent(btnEditApp, javax.swing.GroupLayout.Alignment.TRAILING)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(jLabel1)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnViewAll)
                    .addComponent(btnEditApp))
                .addGap(22, 22, 22)
                .addComponent(btnAddApp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDeleteApp)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(459, Short.MAX_VALUE)
                .addComponent(btnMainMenu)
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnViewAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewAllActionPerformed

        loadTable();
    }//GEN-LAST:event_btnViewAllActionPerformed

    private void btnDeleteAppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteAppActionPerformed

        if(tblAppointment.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(this, "Please select an appointment to remove!");
            return;                
        }
        
        int rowSelect = tblAppointment.getSelectedRow();
        String appID = (String)model.getValueAt(rowSelect, 0);
        String row;      
        try (BufferedReader br = new BufferedReader(new FileReader(APPFILENAME))) {
            while((row = br.readLine())!= null){
                String[] str = row.split("\\|\\|");
                String ID = str[0];
                String date = str[1];
                String patientID = str[3];
                String doctorName = str[3];
                String departmentName = str[4];
                String digitalSignature = str[5];
                if(appID.equals(ID)){
                   currentApp = new Appointment(ID,date,patientID,doctorName,departmentName,digitalSignature);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new FileReader(APPFILENAME))) {
            String fileData = "";
            br.readLine();
            while ((row = br.readLine()) != null) {
                String[] data = row.split("\\|\\|");
                String txtId = data[0];
                if (!(appID.equals(txtId))) {
                    fileData += row + System.lineSeparator();
                }
            }
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(APPFILENAME))){
                bw.write(FILEHEADER);
                bw.write(fileData);
            }          
        } catch (IOException e) {
            e.printStackTrace();
        }
        //new UserProfileManager().deleteUser(new UserProfileLoader().createSalesExec(customerId));
        
        loadTable();
    }//GEN-LAST:event_btnDeleteAppActionPerformed

    private void btnAddAppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddAppActionPerformed

        this.dispose();
        new AddAppointmentView().setVisible(true);
    }//GEN-LAST:event_btnAddAppActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed

        String search = txtSearch.getText();
        try {
            loadTable(search);
        } catch (IOException ex) {
            Logger.getLogger(ManageAppointmentView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnMainMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMainMenuActionPerformed

        this.dispose();
        new MenuView().setVisible(true);
    }//GEN-LAST:event_btnMainMenuActionPerformed

    private void btnEditAppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditAppActionPerformed

        if(tblAppointment.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(this, "Please select an appointment to edit!");
            return;
        }
        this.dispose();
        int rowSelect = tblAppointment.getSelectedRow();
        String appID = (String)model.getValueAt(rowSelect, 0);
        String row;      
        try (BufferedReader br = new BufferedReader(new FileReader(APPFILENAME))) {
            while((row = br.readLine())!= null){
                String[] str = row.split("\\|\\|");
                String ID = str[0];
                String date = str[1];
                String patientID = str[3];
                String doctorName = str[3];
                String departmentName = str[4];
                String digitalSignature = str[5];
                
                if(appID.equals(ID)){
                   currentApp = new Appointment(ID,date,patientID,doctorName,departmentName,digitalSignature);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        new EditAppointmentView(currentApp).setVisible(true);
//        try (BufferedReader br = new BufferedReader(new FileReader(APPFILENAME))) {
//            String row;
//            String fileData = "";
//            br.readLine();
//            while ((row = br.readLine()) != null) {
//                String[] data = row.split("\\|\\|");
//                String ID = data[0];
//                String date = data[1];
//                String patientID = data[2];
//                String doctorName = data[3];
//                String departmentName = data[4];
//                Appointment c = new Appointment(ID,date,patientID,doctorName,departmentName);
//                if (appID.equals(ID)) {
//                    fileData += c.getID() + "||" + c.getDate() + "||" + c.getPatientID() + "||"
//                    + c.getDoctorName() + "||" + c.getDepartmentName() + System.lineSeparator();
//                } else {
//                    fileData += row + System.lineSeparator();
//                }
//            }
//            try(BufferedWriter bw = new BufferedWriter(new FileWriter(APPFILENAME))){
//                bw.write(fileData);
//            }
//            
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }//GEN-LAST:event_btnEditAppActionPerformed

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
            java.util.logging.Logger.getLogger(ManageAppointmentView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageAppointmentView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageAppointmentView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageAppointmentView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new ManageAppointmentView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddApp;
    private javax.swing.JButton btnDeleteApp;
    private javax.swing.JButton btnEditApp;
    private javax.swing.JButton btnMainMenu;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnViewAll;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblAppointment;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
