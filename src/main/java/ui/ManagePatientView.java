package ui;

import asymmetricKey.AsymmetricCrypto;
import com.mycompany.core.Appointment;
import digitalSignature.MySignature;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import com.mycompany.core.Patient;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.spec.SecretKeySpec;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import symmetricKey.symmetriccrypto;

public class ManagePatientView extends javax.swing.JFrame {
    String LEDGERFILENAME = "myLedgerFile.txt";
    DefaultTableModel model;
    //Appointment currentApp;
    String[] patientDataArray;
    //private static final String FILEHEADER = "ID||Date||PatientID||DoctorName||DepartmentName||DigitalSignature" + System.lineSeparator();
    MySignature sig = new MySignature();
    PrivateKey privateKey;
    boolean validity;
    AsymmetricCrypto ASC = new AsymmetricCrypto();
    symmetriccrypto symm = new symmetriccrypto();
    
    Key key;
    /**
     * Creates new form ManagerCustomerView
     */
    public ManagePatientView() throws FileNotFoundException, Exception {
        initComponents();
        loadTable();
    }
    
    public static String removePadding(String s) {
    // Check for padding characters at the end of the string
    if (s.charAt(s.length() - 1) == '=') {
        // Count the number of padding characters
        int numPadding = 0;
        for (int i = s.length() - 1; s.charAt(i) == '='; i--) {
            numPadding++;
        }

        // Strip the padding characters from the string
        s = s.substring(0, s.length() - numPadding);
    }

    return s;
}
    
    private void loadTable() throws Exception{

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

        try {
            BufferedReader brTest = new BufferedReader(new FileReader("test1.txt"));
            String data = brTest .readLine();
            System.out.println("DATA"+data);
            byte[] b = Base64.getDecoder().decode(data);
//            System.out.println("BYTE"+Arrays.toString(b));
//            System.out.println("TEST_1");
//            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(b);
//            System.out.println("TEST_2");
//            privateKey = KeyFactory.getInstance("RSA").generatePrivate(spec);
            key = new SecretKeySpec(b,0,b.length, "AES");
        } catch (Exception e) {
            e.printStackTrace();
         }
        
        
        
        ArrayList<Patient> appList = new ArrayList<>();


        JSONParser parser = new JSONParser();
        
        try {
           JSONArray array = (JSONArray) parser.parse(new FileReader("myLedgerFile.json"));
            
            for (Object o : array)
            {
            JSONObject block = (JSONObject) o;
            //JSONArray b = (JSONArray) block.get("tranxs");
            JSONObject innerBlock = (JSONObject) block.get("tranxs");
            String city = (String) innerBlock.get("merkelRoot").toString();
            System.out.println(city);
            JSONArray dataList = (JSONArray) innerBlock.get("tranxlist");
            for (Object c : dataList)
            {
                System.out.println("TEST_4");
                String dataArray = c.toString();
                System.out.println("DataARRAY : "+ dataArray);
                dataArray = dataArray.replaceAll("[{}]", "");
                dataArray = dataArray.replaceAll("\"", "");
                System.out.println("DATA ARRAY: "+dataArray);
                patientDataArray = dataArray.split(",");
                
                System.out.println("TEST_5");
                String preExistingConditionToSplit = patientDataArray[0];
                String[] preExistingConditionSplit = preExistingConditionToSplit.split(":");
                String preExistingCondition = preExistingConditionSplit[1];
                
               
                String phoneNumberToSplit = patientDataArray[1];
                String[] phoneNumberSplit = phoneNumberToSplit.split(":");
                System.out.println("Phone Number "+phoneNumberSplit[1]);
                String phoneNumber = phoneNumberSplit[1];
                System.out.println("Phone Number "+phoneNumber);
                String pNReturn  = symm.decrypt(phoneNumber, key);
                phoneNumber = pNReturn;

                
                
                String genderToSplit = patientDataArray[2];
                String[] genderSplit = genderToSplit.split(":");
                String gender = genderSplit[1];
                
                String LnameToSplit = patientDataArray[3];
                String[] LnameSplit = LnameToSplit.split(":");
                String Lname = LnameSplit[1];
                String LnReturn  = symm.decrypt(Lname, key);
                Lname = LnReturn;
                
                String currentMedPlanToSplit = patientDataArray[4];
                String[] currentMedPlanSplit = currentMedPlanToSplit.split(":");
                String currentMedicationPlan = currentMedPlanSplit[1];
                String disabilityToSplit = patientDataArray[5];
                String[] disabilitySplit = disabilityToSplit.split(":");
                String disability = disabilitySplit[1];
                
                String ICToSplit = patientDataArray[6];
                String[] ICSplit = ICToSplit.split(":");
                String IC = ICSplit[1];
                System.out.println("IC : "+IC);
                String IdReturn  = symm.decrypt(IC, key);
                IC = IdReturn;
                
                
                
                String IDToSplit = patientDataArray[7];
                String[] IDSplit = IDToSplit.split(":");
                String ID = IDSplit[1];

                
                System.out.println("TEST_6");
                String FnameToSplit = patientDataArray[8];
                String[] FnameSplit = FnameToSplit.split(":");
                String Fname = FnameSplit[1];
                System.out.println("TEST_8");
                System.out.println("FNAME : "+Fname);
                String FnReturn  = symm.decrypt(Fname, key);
                Fname = FnReturn;

                
                String bloodTypeToSplit = patientDataArray[9];
                String[] bloodTypeSplit = bloodTypeToSplit.split(":");
                String bloodType = bloodTypeSplit[1];
                String currentDiseaseToSplit = patientDataArray[10];
                String[] currentDiseaseSplit = currentDiseaseToSplit.split(":");
                String currentDisease = currentDiseaseSplit[1];
                
//                System.out.println("PATIENT ID: "+preExistingCondition);
                appList.add(new Patient(ID,Fname,Lname,IC,phoneNumber,gender,bloodType,disability,preExistingCondition, 
                        currentDisease,currentMedicationPlan));  

              System.out.println(c+"");
            }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ManagePatientView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ManagePatientView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ManagePatientView.class.getName()).log(Level.SEVERE, null, ex);
        }


        for(int i = 0; i < appList.size(); i++){
            String ID = appList.get(i).getID();
            String Fname = appList.get(i).getFname();
            String Lname = appList.get(i).getLname();
            String IC = appList.get(i).getIC();
            String phoneNumber = appList.get(i).getPhoneNumber();
            String gender = appList.get(i).getGender();
            String bloodType = appList.get(i).getBloodType();
            String disability = appList.get(i).getDisability();
            String preExistingCondition = appList.get(i).getPreExistingCondition();
            String currentDisease = appList.get(i).getCurrentDisease();
            String currentMedicationPlan = appList.get(i).getCurrentMedicationPlan();
            Object[] data = {ID, Fname,Lname, IC, phoneNumber, gender, bloodType, disability, preExistingCondition, currentDisease, currentMedicationPlan};
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
        
//        ArrayList<Appointment> appList = new Appointment().loadAppointment(search);
        ArrayList<Patient> appList = new ArrayList<>();


        JSONParser parser = new JSONParser();
        
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
//                Appointment c = new Appointment(ID,date,patientID,doctorName,departmentName,digitalSignature);   
//                if((c.getID().matches(search+".*"))|| (c.getDate().matches(search+".*")) || (c.getPatientID().matches(search+".*"))
//                        || (c.getDoctorName().matches(search+".*")) || (c.getDepartmentName().matches(search+".*"))){
//                    appList.add(c);
//                }             
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        for(int i = 0; i < appList.size(); i++){
            String ID = appList.get(i).getID();
            String Fname = appList.get(i).getFname();
            String Lname = appList.get(i).getLname();
            String IC = appList.get(i).getIC();
            String phoneNumber = appList.get(i).getPhoneNumber();
            String gender = appList.get(i).getGender();
            String bloodType = appList.get(i).getBloodType();
            String disability = appList.get(i).getDisability();
            String preExistingCondition = appList.get(i).getPreExistingCondition();
            String currentDisease = appList.get(i).getCurrentDisease();
            String currentMedicationPlan = appList.get(i).getCurrentMedicationPlan();
            Object[] data = {ID, Fname,Lname, IC, phoneNumber, gender, bloodType, disability, preExistingCondition, currentDisease, currentMedicationPlan};
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 935, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 23, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAddApp, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnViewAll, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnMainMenu)
                        .addGap(112, 112, 112))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 761, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSearch)
                .addGap(76, 76, 76))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(275, 275, 275))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearch))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addComponent(btnViewAll)
                        .addGap(18, 18, 18)
                        .addComponent(btnAddApp)
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnMainMenu)
                        .addGap(50, 50, 50))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnViewAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewAllActionPerformed

        try {
            loadTable();
        } catch (Exception ex) {
            Logger.getLogger(ManagePatientView.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                } catch (Exception ex) {
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
