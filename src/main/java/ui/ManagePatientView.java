package ui;

import asymmetricKey.AsymmetricCrypto;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mycompany.core.Header;
import digitalSignature.MySignature;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import com.mycompany.core.Patient;
import com.mycompany.hashing.Hasher;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.spec.SecretKeySpec;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import symmetricKey.symmetriccrypto;
import org.json.simple.JSONObject;

public class ManagePatientView extends javax.swing.JFrame {
    String LEDGERFILENAME = "myLedgerFile.txt";
    static final String MASTER_DIR = "tempMaster";
    static final String MASTER_BINARY = MASTER_DIR+"/mychain";
    private Hasher hasher = new Hasher();
    
    DefaultTableModel model;
    //Appointment currentApp;
    String[] patientDataArray;
    String[] testArray;
    //private static final String FILEHEADER = "ID||Date||PatientID||DoctorName||DepartmentName||DigitalSignature" + System.lineSeparator();
    MySignature sig = new MySignature();
    PrivateKey privateKey;
    boolean validity;
    PublicKey publicKey;
    AsymmetricCrypto ASC = new AsymmetricCrypto();
    symmetriccrypto symm = new symmetriccrypto();
    
    Key key;
    boolean ledgerSecure = true;
    /**
     * Creates new form ManagerCustomerView
     */
    public ManagePatientView() throws FileNotFoundException, Exception {
        initComponents();
        loadTable();
    }
    
    public static void addtoFile(byte[] keyBytes){
        
        String a = Base64.getEncoder().encodeToString(keyBytes);
        try (BufferedWriter bwUser = new BufferedWriter(new FileWriter("ledgerkey.txt", true))) {
            bwUser.write( a );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public  void retriveKey(){
            try {
                BufferedReader brTest = new BufferedReader(new FileReader("ledgerkey.txt"));
                String data = brTest .readLine();
                byte[] b = Base64.getDecoder().decode(data);
                key = new SecretKeySpec(b,0,b.length, "AES");
            }catch (Exception e) {
            e.printStackTrace();
            }
    }
    
    public void keyFinder(String patientId){

        try {
            BufferedReader brTest = new BufferedReader(new FileReader("DigitalSignature2.txt"));
            String data;
            while((data = brTest.readLine())!= null){
                String[] data1 = data.split("\\|\\|");
                String ID = data1[0];
                String encryptedKey = data1[1];
                if(ID.equals(patientId)){
                    byte[] b = Base64.getDecoder().decode(encryptedKey);
                    X509EncodedKeySpec spec = new X509EncodedKeySpec(b);
                    publicKey= KeyFactory.getInstance("RSA").generatePublic(spec);
                }
            }
            } catch (Exception e) {
            }
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
        model.addColumn("Digital Signature");
        model.addColumn("Validity");

        
        //Delete Later
            File file = new File("ledgerkey.txt");
            if (file.exists()) {
                try {
                    BufferedReader brTest = new BufferedReader(new FileReader("ledgerkey.txt"));
                    String data = brTest .readLine();
                    byte[] b = Base64.getDecoder().decode(data);
                    key = new SecretKeySpec(b,0,b.length, "AES");
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }

        ArrayList<Patient> appList = new ArrayList<>();
        LinkedList<Header> headerList = new LinkedList<>();
        List<String> tempLst = new ArrayList<>();
        ArrayList<String> merkelRootList = new ArrayList<>();
        String merkelRoot;


        JSONParser parser = new JSONParser();

        //Verify Faulty Block
        try {
           JSONArray array = (JSONArray) parser.parse(new FileReader("myLedgerFile.json"));
            
            for (Object o : array)
            {
                JSONObject block = (JSONObject) o;
                JSONObject innerBlock = (JSONObject) block.get("header");
                String indexString = (String) innerBlock.get("index").toString();
                int index = Integer.parseInt(indexString);
                String currHash = (String) innerBlock.get("currHash").toString();
                String prevHash = (String) innerBlock.get("prevHash").toString();
                String timestampString = (String) innerBlock.get("timestamp").toString();
                long timestamp = Long.parseLong(timestampString);
                headerList.add(new Header(index, currHash, prevHash, timestamp));  
                
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ManagePatientView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ManagePatientView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ManagePatientView.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Verify Faulty Block 2    
        for (int i=0; i < (headerList.size() -1);i++){
            int j = i+1;
            int beforeIndex = headerList.get(i).getIndex();
            int nowIndex = headerList.get(j).getIndex();
            
            //String beforePreviousHash = headerList.get(i).getPreviousHash();
            String nowPreviousHash = headerList.get(j).getPreviousHash();
            
            String beforeCurrentHash = headerList.get(i).getCurrentHash();
            //String nowCurrentHash = headerList.get(j).getCurrentHash();
            
            long beforeTimestamp = headerList.get(i).getTimestamp();
            long nowTimestamp = headerList.get(j).getTimestamp();
            
            if (((nowIndex - beforeIndex) != 1)  || (!(nowTimestamp > beforeTimestamp)) ||(!(nowPreviousHash.equals(beforeCurrentHash)))){
                ledgerSecure = false;
                break;
            }else{
            }
        }
        
        //Verify Faulty Block 3
        if (ledgerSecure == false){
            JOptionPane.showMessageDialog(this, "Blocks in ledger are faulty. Ledger may have been tampered or corrupted.");
        }else{
            JOptionPane.showMessageDialog(this, "Blocks in ledger have been checked and is currently secure.");
        }
        
        //Verify Data
        try {
           JSONArray array = (JSONArray) parser.parse(new FileReader("myLedgerFile.json"));
            
            for (Object o : array)
            {
                JSONObject block = (JSONObject) o;
                JSONObject tranxs = (JSONObject) block.get("tranxs");
                merkelRoot = (String) tranxs.get("merkelRoot").toString();
                merkelRootList.add(merkelRoot); 
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ManagePatientView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ManagePatientView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ManagePatientView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        File file2 = new File("master\\mychain.bin");
            if (file2.exists()) {
            try {
                JsonElement json = new JsonParser().parse(new FileReader("myLedgerFile.json"));
                Gson gson = new Gson();
                List<Map<String, Object>> list = gson.fromJson(json, List.class);
                //For Loop
                for (int i=0; i<list.size();i++){
                    String jsonString = String.valueOf(list.get(i));
                    int startIndex = jsonString.indexOf("tranxlist=[");
                    int endIndex = jsonString.lastIndexOf("]");
                    String tranxlistString = jsonString.substring(startIndex, endIndex + 1);
                    tranxlistString = tranxlistString.replaceAll("[{}]", "");
                    tranxlistString = tranxlistString.replaceAll("\\[", "").replaceAll("\\]", "");
                    tranxlistString = tranxlistString.replace("tranxlist=", "");
                    patientDataArray = tranxlistString.split(",");

                    String IDToSplit = patientDataArray[0];
                    String[] IDSplit = IDToSplit.split("=");
                    String ID = IDSplit[1];

                    String FnameToSplit = patientDataArray[1];
                    String Fname =  FnameToSplit.replace("Fname=", "");
                    Fname =  Fname.replaceFirst("\\s", "");
//                    String FnReturn  = symm.decrypt(Fname, key);
//                    Fname = FnReturn;

                    String LnameToSplit = patientDataArray[2];
                    String Lname =  LnameToSplit.replace("Lname=", "");
                    Lname =  Lname.replaceFirst("\\s", "");
//                    String LnReturn  = symm.decrypt(Lname, key);
//                    Lname = LnReturn;

                    String ICToSplit = patientDataArray[3];
                    String IC =  ICToSplit.replace("IC=", "");
                    IC =  IC.replaceFirst("\\s", "");
//                    String ICReturn  = symm.decrypt(IC, key);
//                    IC = ICReturn;

                    String phoneNumberToSplit = patientDataArray[4];
                    String phoneNumber =  phoneNumberToSplit.replace("phoneNumber=", "");
                    phoneNumber =  phoneNumber.replaceFirst("\\s", "");
//                    String phoneNumberReturn  = symm.decrypt(phoneNumber, key);
//                    phoneNumber = phoneNumberReturn;

                    String genderToSplit = patientDataArray[5];
                    String gender =  genderToSplit.replace("gender=", "");
                    gender =  gender.replaceFirst("\\s", "");

                    String bloodTypeToSplit = patientDataArray[6];
                    String bloodType =  bloodTypeToSplit.replace("bloodType=", "");
                    bloodType =  bloodType.replaceFirst("\\s", "");

                    String disabilityToSplit = patientDataArray[7];
                    String disability =  disabilityToSplit.replace("disability=", "");
                    disability =  disability.replaceFirst("\\s", "");

                    String preExistingConditionToSplit = patientDataArray[8];
                    String preExistingCondition =  preExistingConditionToSplit.replace("preExistingCondition=", "");
                    preExistingCondition =  preExistingCondition.replaceFirst("\\s", "");

                    String currentDiseaseToSplit = patientDataArray[9];
                    String currentDisease =  currentDiseaseToSplit.replace("currentDisease=", "");
                    currentDisease =  currentDisease.replaceFirst("\\s", "");

                    String currentMedPlanToSplit = patientDataArray[10];
                    String currentMedPlan =  currentMedPlanToSplit.replace("currentMedicationPlan=", "");
                    currentMedPlan =  currentMedPlan.replaceFirst("\\s", "");
                    
                    String digitalSignatureToSplit = patientDataArray[11];
                    String digitalSignature =  digitalSignatureToSplit.replace("digitalSignature=", "");
                    digitalSignature =  digitalSignature.replaceFirst("\\s", "");
                    
                    appList.add(new Patient(ID,Fname,Lname,IC,phoneNumber,gender,bloodType,disability,preExistingCondition, 
                    currentDisease,currentMedPlan, digitalSignature));
                    tempLst.add(appList.get(i).getID() + appList.get(i).getFname() + appList.get(i).getLname() + 
                            appList.get(i).getIC() + appList.get(i).getPhoneNumber() + appList.get(i).getGender()
                            + appList.get(i).getBloodType() + appList.get(i).getDisability() + appList.get(i).getPreExistingCondition()
                            + appList.get(i).getCurrentDisease() + appList.get(i).getCurrentMedicationPlan());
                    String tohash = tempLst.get(i).toString();
                    String merkelRootcheck = hasher.sha256(tohash);
                    if (merkelRootList.get(i).equals(merkelRootcheck)){
                        JOptionPane.showMessageDialog(this, "Patient data with ID: " +ID+ " in the ledger have been checked and is currently secure.");
                    }else{
                        JOptionPane.showMessageDialog(this, "Patient data with ID: " +ID+ " in the ledger is faulty. It may have been tamepered or corrupted.");
                    }
                    String FnReturn  = symm.decrypt(Fname, key);
                    Fname = FnReturn;
                    String LnReturn  = symm.decrypt(Lname, key);
                    Lname = LnReturn;
                    String ICReturn  = symm.decrypt(IC, key);
                    IC = ICReturn;
                    String phoneNumberReturn  = symm.decrypt(phoneNumber, key);
                    phoneNumber = phoneNumberReturn;
                    appList.get(i).setFname(Fname);
                    appList.get(i).setLname(Lname);
                    appList.get(i).setIC(IC);
                    appList.get(i).setPhoneNumber(phoneNumber);
                }           
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ManagePatientView.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ManagePatientView.class.getName()).log(Level.SEVERE, null, ex);
            } 
            }else{
                
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
            String digitalSignature = appList.get(i).getDigitalSignature();
            
            String pa = ID+Fname+Lname+IC+phoneNumber+gender+bloodType+disability+preExistingCondition+currentDisease+currentMedicationPlan;
            
            try {
                 keyFinder(ID);
                 validity = sig.verify(pa, digitalSignature, publicKey);
            } catch (Exception ex) {
                Logger.getLogger(AddAppointmentView.class.getName()).log(Level.SEVERE, null, ex);
            }
            Object[] data = {ID, Fname,Lname, IC, phoneNumber, gender, bloodType, disability, preExistingCondition, currentDisease, currentMedicationPlan, digitalSignature, validity};
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

        ArrayList<Patient> appList = new ArrayList<>();
        
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

        btnMainMenu = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAppointment = new javax.swing.JTable();
        btnAddApp = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnMainMenu.setFont(new java.awt.Font("Unispace", 1, 14)); // NOI18N
        btnMainMenu.setText("Return");
        btnMainMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMainMenuActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(btnAddApp, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMainMenu)
                .addGap(112, 112, 112))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(379, 379, 379))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(42, 42, 42)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMainMenu)
                    .addComponent(btnAddApp))
                .addGap(50, 50, 50))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddAppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddAppActionPerformed

        this.dispose();
        new AddPatientView().setVisible(true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblAppointment;
    // End of variables declaration//GEN-END:variables
}
