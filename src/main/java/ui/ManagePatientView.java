package ui;

import com.mycompany.core.Appointment;
import digitalSignature.MySignature;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.google.gson.*;
import com.mycompany.core.Block;
import com.mycompany.core.Block.Header;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ManagePatientView extends javax.swing.JFrame {
    String LEDGERFILENAME = "myLedgerFile.txt";
    DefaultTableModel model;
    Appointment currentApp;
    private static final String FILEHEADER = "ID||Date||PatientID||DoctorName||DepartmentName||DigitalSignature" + System.lineSeparator();
    MySignature sig = new MySignature();
    PublicKey publicKey ;
    boolean validity;
    /**
     * Creates new form ManagerCustomerView
     */
    public ManagePatientView() throws FileNotFoundException {
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
        model.addColumn("First Name");
        model.addColumn("Last Name");
        model.addColumn("IC Number");
        model.addColumn("Phone Number");
        model.addColumn("Gender");
        model.addColumn("Blood Type");
        model.addColumn("Disability");
        model.addColumn("Pre-Existing Condition");
        model.addColumn("Current Disease");
        model.addColumn("Current Medication Plan");
        
//         try {
//            BufferedReader brTest = new BufferedReader(new FileReader("DigitalSignature.txt"));
//            String data = brTest .readLine();
//            System.out.println("DATA"+data);
//            byte[] b = Base64.getDecoder().decode(data);
////            byte[] b = data.getBytes("UTF-16");
//            System.out.println("BYTE"+Arrays.toString(b));
//            System.out.println("TEST_1");
//            X509EncodedKeySpec spec = new X509EncodedKeySpec(b);
//            System.out.println("TEST_2");
//            publicKey = KeyFactory.getInstance("RSA").generatePublic(spec);
//            System.out.println("TEST_3");
//            System.out.println("Publickey"+publicKey);
//        } catch (Exception e) {
//            System.out.println("FAILURE");
//         }
        
        //ArrayList<Appointment> appList = new Appointment().loadAppointment();
        ArrayList<Appointment> appList = new ArrayList<>();
        
//        BufferedReader reader = new BufferedReader(new FileReader(LEDGERFILENAME));
//        String json = "";
//        try {
//            StringBuilder sb = new StringBuilder();
//            String line = reader.readLine();
//
//            while (line != null) {
//                sb.append(line);
//                sb.append("\n");
//                line = reader.readLine();
//            }
//            json = sb.toString();
//        } finally {
//            reader.close();
//        }
//        JSONObject object = new JSONObject(json); // this will get you the entire JSON node
//        JSONArray array = object.getJSONArray("Node"); // put in whatever your JSON data name here, this will get you an array of all the nodes
        
//        GsonBuilder builder = new GsonBuilder(); 
//        Gson gson = builder.create(); 
//        BufferedReader bufferedReader = new BufferedReader(
//           new FileReader(LEDGERFILENAME));   
//
//        Block block = gson.fromJson(bufferedReader, Block.class); 
//        System.out.println("Block: "+ block.toString());

        
        try {
            JSONParser parser = new JSONParser();
            JSONArray a;
            a = (JSONArray) parser.parse(new FileReader("myLedgerFile.json"));
            for (Object o : a)
            {
            JSONObject block = (JSONObject) o;

            Header name = (Header) block.get("header");
            System.out.println(name);
//
//            String city = (String) block.get("header");
//            System.out.println(city);
//
//            String job = (String) block.get("job");
//            System.out.println(job);
            System.out.println("TEST1");
//            JSONArray cars = (JSONArray) block.get("tranxlist");
//            System.out.println("TEST2");
//            for (Object c : cars)
//            {
//              System.out.println(c+"");
//            }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ManagePatientView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ManagePatientView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ManagePatientView.class.getName()).log(Level.SEVERE, null, ex);
        }

          

        
        
//        try (BufferedReader br = new BufferedReader(new FileReader(LEDGERFILENAME))) {
//            br.readLine();
//            String row;
//            while((row = br.readLine())!= null){
//                String[] data = row.split("\\|\\|");    
//                String ID = data[0];
//                String date = data[1];
//                String patientID = data[2];
//                String doctorName = data[3];
//                String departmentName = data[4];
//                String digitalSignature = data[5];
//                appList.add(new Appointment(ID,date,patientID,doctorName,departmentName,digitalSignature));  
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        for(int i = 0; i < appList.size(); i++){
            String ID = appList.get(i).getID();
            String appDate = appList.get(i).getDate();
            String patientID = appList.get(i).getPatientID();
            String doctorName = appList.get(i).getDoctorName();
            String department = appList.get(i).getDepartmentName();
            String digitalSignature = appList.get(i).getDigitalSignature();
            
//             a = new Appointment(ID,date, patientID, doctorName, departmentName);
            
            Appointment newAppointment = new Appointment(ID,appDate,patientID,doctorName,department);
//            System.out.println("Appoitment : "+ID+","+appDate+","+patientID+","+doctorName+","+department+","+digitalSignature);
            System.out.println("POWER "+newAppointment.toString());
           
            try {
                 validity = sig.verify(String.valueOf(newAppointment), digitalSignature, publicKey);
                 System.out.println("Boolean "+ validity);
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
        
        try (BufferedReader br = new BufferedReader(new FileReader(LEDGERFILENAME))) {
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
        btnMainMenu = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAppointment = new javax.swing.JTable();
        btnAddApp = new javax.swing.JButton();
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

        btnMainMenu.setFont(new java.awt.Font("Unispace", 1, 14)); // NOI18N
        btnMainMenu.setText("Return");
        btnMainMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMainMenuActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Old English Text MT", 1, 48)); // NOI18N
        jLabel1.setText("Patients Data List");

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
        btnAddApp.setText("Add New Patient");
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
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 818, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAddApp, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnViewAll, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnMainMenu)
                        .addGap(64, 64, 64))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(73, 73, 73)
                        .addComponent(btnSearch)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(btnMainMenu)
                        .addGap(53, 53, 53))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnViewAll)
                        .addGap(18, 18, 18)
                        .addComponent(btnAddApp)
                        .addGap(27, 27, 27))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnViewAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewAllActionPerformed

        loadTable();
    }//GEN-LAST:event_btnViewAllActionPerformed

    private void btnAddAppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddAppActionPerformed

        this.dispose();
        new AddPatientView().setVisible(true);
    }//GEN-LAST:event_btnAddAppActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed

        String search = txtSearch.getText();
        try {
            loadTable(search);
        } catch (IOException ex) {
            Logger.getLogger(ManagePatientView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

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
                try {
                    new ManagePatientView().setVisible(true);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ManagePatientView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddApp;
    private javax.swing.JButton btnMainMenu;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnViewAll;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblAppointment;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
