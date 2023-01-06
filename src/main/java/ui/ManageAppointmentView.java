package ui;

import com.mycompany.core.Appointment;
import digitalSignature.MySignature;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ManageAppointmentView extends javax.swing.JFrame {
    String APPFILENAME = "appointment.txt";
    DefaultTableModel model;
    Appointment currentApp;
    private static final String FILEHEADER = "ID||Date||PatientID||DoctorName||DepartmentName||DigitalSignature" + System.lineSeparator();
    private static final String FILEHEADER_2 = "patientID||DigitalSignature" + System.lineSeparator();
    MySignature sig = new MySignature();
    PublicKey publicKey ;
    boolean validity;
    digitalSignature ds;
    /**
     * Creates new form ManagerCustomerView
     */
    public ManageAppointmentView() {
        initComponents();
        loadTable();
    }
    
    public class digitalSignature{
        String patientId;
        String digitalSignature;

        public digitalSignature(String patientId, String digitalSignature) {
            this.patientId = patientId;
            this.digitalSignature = digitalSignature;
        }
        
        
    }
    
    public void keyFinder(String patientId){
        try {
            BufferedReader brTest = new BufferedReader(new FileReader("DigitalSignature.txt"));
            String data;
            while((data = brTest.readLine())!= null){
                String[] data1 = data.split("\\|\\|");
                String patientID = data1[0];
                String encryptedKey = data1[1];
                if(patientID.equals(patientId)){
                    byte[] b = Base64.getDecoder().decode(encryptedKey);
                    X509EncodedKeySpec spec = new X509EncodedKeySpec(b);
                    publicKey = KeyFactory.getInstance("RSA").generatePublic(spec);
                }
            }
        } catch (Exception e) {
        }
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
            
            Appointment newAppointment = new Appointment(ID,appDate,patientID,doctorName,department);
           
            try {
                keyFinder(ID);
                 validity = sig.verify(String.valueOf(newAppointment), digitalSignature, publicKey);
            } catch (Exception ex) {
                Logger.getLogger(AddAppointmentView.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            Object[] data = {ID, appDate, patientID, doctorName, department,digitalSignature,validity};
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

        btnDeleteApp = new javax.swing.JButton();
        btnMainMenu = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAppointment = new javax.swing.JTable();
        btnAddApp = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 818, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnDeleteApp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnAddApp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnMainMenu)
                                .addGap(61, 61, 61))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(jLabel1)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAddApp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeleteApp)
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnMainMenu)
                        .addGap(49, 49, 49))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        
        try (BufferedReader br = new BufferedReader(new FileReader("DigitalSignature.txt"))) {
            while((row = br.readLine())!= null){
                String[] str = row.split("\\|\\|");
                String patientID = str[0];
                String crypted = str[1];
                if(appID.equals(str)){
                    ds = new digitalSignature(patientID,crypted);
//                   currentApp = new Appointment(ID,date,patientID,doctorName,departmentName,digitalSignature);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader("DigitalSignature.txt"))) {
            String fileData = "";
            br.readLine();
            while ((row = br.readLine()) != null) {
                String[] data = row.split("\\|\\|");
                String txtId = data[0];
                if (!(appID.equals(txtId))) {
                    fileData += row + System.lineSeparator();
                }
            }
            try(BufferedWriter bw = new BufferedWriter(new FileWriter("DigitalSignature.txt"))){
                bw.write(FILEHEADER_2);
                bw.write(fileData);
            }          
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        loadTable();
    }//GEN-LAST:event_btnDeleteAppActionPerformed

    private void btnAddAppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddAppActionPerformed

        this.dispose();
        new AddAppointmentView().setVisible(true);
    }//GEN-LAST:event_btnAddAppActionPerformed

    private void btnMainMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMainMenuActionPerformed

        this.dispose();
        new MenuView().setVisible(true);
    }//GEN-LAST:event_btnMainMenuActionPerformed

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
    private javax.swing.JButton btnMainMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblAppointment;
    // End of variables declaration//GEN-END:variables
}
