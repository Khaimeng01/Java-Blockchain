package ui;

import asymmetricKey.AsymmetricCrypto;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mycompany.core.Appointment;
import com.mycompany.core.Header;
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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import symmetricKey.symmetriccrypto;
import org.json.simple.JSONObject;

public class ManagePatientView extends javax.swing.JFrame {
    String LEDGERFILENAME = "myLedgerFile.txt";
    DefaultTableModel model;
    //Appointment currentApp;
    String[] patientDataArray;
    String[] testArray;
    //private static final String FILEHEADER = "ID||Date||PatientID||DoctorName||DepartmentName||DigitalSignature" + System.lineSeparator();
    MySignature sig = new MySignature();
    PrivateKey privateKey;
    boolean validity;
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

//        try {
//            BufferedReader brTest = new BufferedReader(new FileReader("test1.txt"));
//            String data = brTest .readLine();
//            System.out.println("DATA"+data);
//            byte[] b = Base64.getDecoder().decode(data);
////            System.out.println("BYTE"+Arrays.toString(b));
////            System.out.println("TEST_1");
////            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(b);
////            System.out.println("TEST_2");
////            privateKey = KeyFactory.getInstance("RSA").generatePrivate(spec);
//            key = new SecretKeySpec(b,0,b.length, "AES");
//        } catch (Exception e) {
//            e.printStackTrace();
//         }
        
        
        
        ArrayList<Patient> appList = new ArrayList<>();
        LinkedList<Header> headerList = new LinkedList<>();


        JSONParser parser = new JSONParser();
        
//        //Verify Faulty Block
//        try {
//           JSONArray array = (JSONArray) parser.parse(new FileReader("myLedgerFile.json"));
//            
//            for (Object o : array)
//            {
//                JSONObject block = (JSONObject) o;
//                JSONObject innerBlock = (JSONObject) block.get("header");
//                String indexString = (String) innerBlock.get("index").toString();
//                System.out.println("THIS IS HEADER INDEX: "+indexString);
//                int index = Integer.parseInt(indexString);
//                String currHash = (String) innerBlock.get("currHash").toString();
//                System.out.println("THIS IS HEADER CURRENT HASH: "+currHash);
//                String prevHash = (String) innerBlock.get("prevHash").toString();
//                System.out.println("THIS IS HEADER PREV HASH: "+prevHash);
//                String timestampString = (String) innerBlock.get("timestamp").toString();
//                System.out.println("THIS IS HEADER TIMESTAMP: "+timestampString);
//                long timestamp = Long.parseLong(timestampString);
//                headerList.add(new Header(index, currHash, prevHash, timestamp));  
//                
//            }
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(ManagePatientView.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(ManagePatientView.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ParseException ex) {
//            Logger.getLogger(ManagePatientView.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        //Verify Faulty Block 2    
//        for (int i=0; i < (headerList.size() -1);i++){
//            int j = i+1;
//            int beforeIndex = headerList.get(i).getIndex();
//            System.out.println("BEFORE INDEX: "+beforeIndex);
//            int nowIndex = headerList.get(j).getIndex();
//            System.out.println("NOW INDEX: "+nowIndex);
//            
//            //String beforePreviousHash = headerList.get(i).getPreviousHash();
//            String nowPreviousHash = headerList.get(j).getPreviousHash();
//            System.out.println("PREVIOUS HASH: "+nowPreviousHash);
//            
//            String beforeCurrentHash = headerList.get(i).getCurrentHash();
//            System.out.println("CURRENT HASH: "+beforeCurrentHash);
//            //String nowCurrentHash = headerList.get(j).getCurrentHash();
//            
//            long beforeTimestamp = headerList.get(i).getTimestamp();
//            System.out.println("BEFORE TIME: "+beforeTimestamp);
//            long nowTimestamp = headerList.get(j).getTimestamp();
//            System.out.println("NOW TIME: "+nowTimestamp);
//            
//            if (((nowIndex - beforeIndex) != 1)  || (!(nowTimestamp > beforeTimestamp)) ||(!(nowPreviousHash.equals(beforeCurrentHash)))){
//                System.out.println("I have found error!\n\n");
//                ledgerSecure = false;
//                break;
//            }else{
//                System.out.println("So far all okay.\n\n");
//            }
//        }
//        
//        //Verify Faulty Block 3
//        if (ledgerSecure == false){
//            System.out.println("Blocks in ledger are faulty. Ledger may have been tampered or corrupted.");
//            JOptionPane.showMessageDialog(this, "Blocks in ledger are faulty. Ledger may have been tampered or corrupted.");
//        }else{
//            System.out.println("Blocks in ledger have been checked and is currently secure.");
//            JOptionPane.showMessageDialog(this, "Blocks in ledger have been checked and is currently secure.");
//        }
        
        try {
            JsonElement json = new JsonParser().parse(new FileReader("myLedgerFile.json"));
            Gson gson = new Gson();
            List<Map<String, Object>> list = gson.fromJson(json, List.class);
//            Map data = gson.fromJson(reader, Map.class);
            System.out.println("DATA 5/1/2023 "+list);
            //For Loop
            for (int i=0; i<list.size();i++){
                String jsonString = String.valueOf(list.get(i));
                int startIndex = jsonString.indexOf("tranxlist=[");
                int endIndex = jsonString.lastIndexOf("]");
                String tranxlistString = jsonString.substring(startIndex, endIndex + 1);
                tranxlistString = tranxlistString.replaceAll("[{}]", "");
                tranxlistString = tranxlistString.replaceAll("\\[", "").replaceAll("\\]", "");
                tranxlistString = tranxlistString.replace("tranxlist=", "");
                System.out.println("Final tranxlistString: "+tranxlistString);
                patientDataArray = tranxlistString.split(",");
                System.out.println("patientdatarray: "+patientDataArray);
                
                String IDToSplit = patientDataArray[0];
                System.out.println("IDToSplit: "+IDToSplit);
                String[] IDSplit = IDToSplit.split("=");
                System.out.println("IDToSplit: "+IDSplit[1]);
                String ID = IDSplit[1];
                System.out.println("ID: "+ID);
                
                String FnameToSplit = patientDataArray[1];
                String Fname =  FnameToSplit.replace("Fname=", "");
                System.out.println("Fname: "+Fname);
                
                String LnameToSplit = patientDataArray[2];
                String Lname =  LnameToSplit.replace("Lname=", "");
                System.out.println("Lname: "+Lname);
                
                String ICToSplit = patientDataArray[3];
                String IC =  ICToSplit.replace("IC=", "");
                System.out.println("IC: "+IC);
                
                String phoneNumberToSplit = patientDataArray[4];
                String phoneNumber =  phoneNumberToSplit.replace("phoneNumber=", "");
                System.out.println("phoneNumber: "+phoneNumber);
                
                String genderToSplit = patientDataArray[5];
                String gender =  genderToSplit.replace("gender=", "");
                System.out.println("gender: "+gender);
                
                String bloodTypeToSplit = patientDataArray[6];
                String bloodType =  bloodTypeToSplit.replace("bloodType=", "");
                System.out.println("bloodType: "+bloodType);
                
                String disabilityToSplit = patientDataArray[7];
                String disability =  disabilityToSplit.replace("disability=", "");
                System.out.println("disability: "+disability);
                
                String preExistingConditionToSplit = patientDataArray[8];
                String preExistingCondition =  preExistingConditionToSplit.replace("preExistingCondition=", "");
                System.out.println("preExistingCondition: "+preExistingCondition);
                
                String currentDiseaseToSplit = patientDataArray[9];
                String currentDisease =  currentDiseaseToSplit.replace("currentDisease=", "");
                System.out.println("currentDisease: "+currentDisease);
                
                String currentMedPlanToSplit = patientDataArray[10];
                String currentMedPlan =  currentMedPlanToSplit.replace("currentMedicationPlan=", "");
                System.out.println("currentMedPlan: "+currentMedPlan);
                appList.add(new Patient(ID,Fname,Lname,IC,phoneNumber,gender,bloodType,disability,preExistingCondition, 
                currentDisease,currentMedPlan));
            }

//           JSONArray array = (JSONArray) parser.parse(new FileReader("myLedgerFile.json"));
//            
//            for (Object o : array)
//            {
//            JSONObject block = (JSONObject) o;
//            //JSONArray b = (JSONArray) block.get("tranxs");
//            JSONObject innerBlock = (JSONObject) block.get("tranxs");
////            String currHash = (String) innerBlock.get("merkelRoot").toString();
////            System.out.println(currHash);
////            String test = innerBlock.toJSONString();
//            JSONArray dataList = (JSONArray) innerBlock.get("tranxlist");
//            for (Object c : dataList)
//            {
//                System.out.println("TEST_4");
//                String dataArray = c.toString();
//                System.out.println("DataARRAY : "+ dataArray);
//                System.out.println(c+"");
//                dataArray = dataArray.replaceAll("[{}]", "");
//                dataArray = dataArray.replaceAll("\"", "");
//                System.out.println("DATA ARRAY: "+dataArray);
//                patientDataArray = dataArray.split(",");
//                
//                System.out.println("TEST_5");
//                String preExistingConditionToSplit = patientDataArray[0];
//                String[] preExistingConditionSplit = preExistingConditionToSplit.split(":");
//                String preExistingCondition = preExistingConditionSplit[1];
//                
//               
//                String phoneNumberToSplit = patientDataArray[1];
//                String[] phoneNumberSplit = phoneNumberToSplit.split(":");
//                System.out.println("Phone Number "+phoneNumberSplit[1]);
//                String phoneNumber = phoneNumberSplit[1];
//                System.out.println("Phone Number "+phoneNumber);
//                String pNReturn  = symm.decrypt(phoneNumber, key);
//                phoneNumber = pNReturn;
//
//                System.out.println("Phone Number: "+phoneNumber);
//                String genderToSplit = patientDataArray[2];
//                String[] genderSplit = genderToSplit.split(":");
//                String gender = genderSplit[1];
//                
//                String LnameToSplit = patientDataArray[3];
//                String[] LnameSplit = LnameToSplit.split(":");
//                String Lname = LnameSplit[1];
//                String LnReturn  = symm.decrypt(Lname, key);
//                Lname = LnReturn;
//                
//                String currentMedPlanToSplit = patientDataArray[4];
//                String[] currentMedPlanSplit = currentMedPlanToSplit.split(":");
//                String currentMedicationPlan = currentMedPlanSplit[1];
//                String disabilityToSplit = patientDataArray[5];
//                String[] disabilitySplit = disabilityToSplit.split(":");
//                String disability = disabilitySplit[1];
//                
//                String ICToSplit = patientDataArray[6];
//                String[] ICSplit = ICToSplit.split(":");
//                String IC = ICSplit[1];
//                System.out.println("IC : "+IC);
//                String IdReturn  = symm.decrypt(IC, key);
//                IC = IdReturn;
//                
//                
//                
//                String IDToSplit = patientDataArray[7];
//                String[] IDSplit = IDToSplit.split(":");
//                String ID = IDSplit[1];
//
//                
//                System.out.println("TEST_6");
//                String FnameToSplit = patientDataArray[8];
//                String[] FnameSplit = FnameToSplit.split(":");
//                String Fname = FnameSplit[1];
//                System.out.println("TEST_8");
//                System.out.println("FNAME : "+Fname);
//                String FnReturn  = symm.decrypt(Fname, key);
//                Fname = FnReturn;
//
//                System.out.println("First Name: "+Fname);
//                String bloodTypeToSplit = patientDataArray[9];
//                String[] bloodTypeSplit = bloodTypeToSplit.split(":");
//                String bloodType = bloodTypeSplit[1];
//                String currentDiseaseToSplit = patientDataArray[10];
//                String[] currentDiseaseSplit = currentDiseaseToSplit.split(":");
//                String currentDisease = currentDiseaseSplit[1];
//                
////                System.out.println("PATIENT ID: "+preExistingCondition);
//                appList.add(new Patient(ID,Fname,Lname,IC,phoneNumber,gender,bloodType,disability,preExistingCondition, 
//                        currentDisease,currentMedicationPlan));  

              
//            }
           
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ManagePatientView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ManagePatientView.class.getName()).log(Level.SEVERE, null, ex);
        } 
//        catch (ParseException ex) {
//            Logger.getLogger(ManagePatientView.class.getName()).log(Level.SEVERE, null, ex);
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
//                Appointment headerContent = new Appointment(ID,date,patientID,doctorName,departmentName,digitalSignature);   
//                if((headerContent.getID().matches(search+".*"))|| (headerContent.getDate().matches(search+".*")) || (headerContent.getPatientID().matches(search+".*"))
//                        || (headerContent.getDoctorName().matches(search+".*")) || (headerContent.getDepartmentName().matches(search+".*"))){
//                    appList.add(headerContent);
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
                .addGap(82, 82, 82)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddApp, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnViewAll, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMainMenu)
                .addGap(112, 112, 112))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSearch)
                .addGap(76, 76, 76))
            .addGroup(layout.createSequentialGroup()
                .addGap(381, 381, 381)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnViewAll)
                        .addGap(18, 18, 18)
                        .addComponent(btnAddApp)
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
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
