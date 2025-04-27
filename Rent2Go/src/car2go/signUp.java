package car2go;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class signUp extends javax.swing.JFrame {

    //Variables to store username and password
    private static String username, password;
    //File path for JSON
    private static String filepath = "C:\\Users\\User\\Downloads\\Java-Rice-main\\Java-Rice-main\\Rent2Go\\src\\car2go\\car2go.json";
    private static JSONParser jsonParser = new JSONParser();
    private static JSONObject record = new JSONObject();
    private static JSONArray userlist = new JSONArray();

    public signUp() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lblEmail = new javax.swing.JLabel();
        TfEmail = new javax.swing.JTextField();
        lblPswd = new javax.swing.JLabel();
        Pswd = new javax.swing.JPasswordField();
        btnCreateAcc = new javax.swing.JButton();
        lblCPswd = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        TfName = new javax.swing.JTextField();
        checkPswrd = new javax.swing.JCheckBox();
        CPswd = new javax.swing.JPasswordField();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        exit = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Car2Go");
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(500, 100));

        jLabel5.setFont(new java.awt.Font("Gtek Technology", 1, 50)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(48, 53, 57));
        jLabel5.setText("sign up");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addComponent(jLabel5)
                .addContainerGap(131, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(16, 16, 16))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 5, -1, -1));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(500, 400));

        lblEmail.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(48, 53, 57));
        lblEmail.setText("Email:");

        TfEmail.setBorder(null);

        lblPswd.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        lblPswd.setForeground(new java.awt.Color(48, 53, 57));
        lblPswd.setText("Password:");

        Pswd.setBorder(null);

        btnCreateAcc.setBackground(new java.awt.Color(102, 0, 0));
        btnCreateAcc.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        btnCreateAcc.setForeground(new java.awt.Color(255, 255, 255));
        btnCreateAcc.setText("Create Account");
        btnCreateAcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateAccActionPerformed(evt);
            }
        });

        lblCPswd.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        lblCPswd.setForeground(new java.awt.Color(48, 53, 57));
        lblCPswd.setText("Confirm Password:");

        lblName.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        lblName.setForeground(new java.awt.Color(48, 53, 57));
        lblName.setText("Name:");

        TfName.setBorder(null);

        checkPswrd.setBackground(new java.awt.Color(255, 255, 255));
        checkPswrd.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        checkPswrd.setForeground(new java.awt.Color(48, 53, 57));
        checkPswrd.setText("Show Password");
        checkPswrd.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(48, 53, 57)));
        checkPswrd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkPswrdActionPerformed(evt);
            }
        });

        CPswd.setBorder(null);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkPswrd)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(TfEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                        .addComponent(btnCreateAcc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TfName)
                        .addComponent(Pswd)
                        .addComponent(CPswd)
                        .addComponent(lblEmail)
                        .addComponent(lblName)
                        .addComponent(lblPswd)
                        .addComponent(lblCPswd)))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(lblName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TfName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblPswd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Pswd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCPswd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CPswd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkPswrd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(btnCreateAcc, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, -1, -1));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setPreferredSize(new java.awt.Dimension(500, 50));

        jLabel7.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(48, 53, 57));
        jLabel7.setText("Already have an account?");

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 0, 0));
        jLabel1.setText("Sign In");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(175, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 515, -1, -1));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setBackground(new java.awt.Color(102, 0, 0));
        jPanel2.setPreferredSize(new java.awt.Dimension(300, 600));

        exit.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        exit.setForeground(new java.awt.Color(200, 65, 45));
        exit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exit.setText("X");
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 277, Short.MAX_VALUE)
                .addComponent(exit))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 573, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.LINE_END);

        setSize(new java.awt.Dimension(800, 600));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateAccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateAccActionPerformed
        String name = TfName.getText().trim();
        String email = TfEmail.getText().trim();
        String password = new String(Pswd.getPassword()).trim();
        String confirmPassword = new String(CPswd.getPassword()).trim();

        // Validate fields
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if passwords match
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            filecheck(); // Load existing users

            // Check if email already exists
            boolean userExists = false;
            for (Object obj : userlist) {
                JSONObject user = (JSONObject) obj;
                if (email.equals(user.get("email"))) {
                    userExists = true;
                    break;
                }
            }

            if (userExists) {
                JOptionPane.showMessageDialog(this, "Email is already registered", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Create new user object with all required fields
                JSONObject newUser = new JSONObject();
                newUser.put("name", name);
                newUser.put("email", email);
                newUser.put("password", password);
                newUser.put("number", ""); // Default empty values for other fields
                newUser.put("address", "");
                newUser.put("gender", "");
                newUser.put("dob", "");
                newUser.put("phone", "");

                // Add and save
                userlist.add(newUser);
                record.put("users", userlist);

                try (FileWriter file = new FileWriter(filepath)) {
                    file.write(record.toJSONString());
                    file.flush();
                }

                JOptionPane.showMessageDialog(this, "Account created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                // Clear fields
                TfName.setText("");
                TfEmail.setText("");
                Pswd.setText("");
                CPswd.setText("");

                logIn x = new logIn();
                x.setVisible(true);
                this.dispose();
            }

        } catch (IOException | ParseException ex) {
            Logger.getLogger(signUp.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "An error occurred while saving user data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCreateAccActionPerformed

    private void checkPswrdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkPswrdActionPerformed
        if (checkPswrd.isSelected()) {
            Pswd.setEchoChar((char) 0);  // Show password
            CPswd.setEchoChar((char) 0);
        } else {
            Pswd.setEchoChar('*');       // Hide password
            CPswd.setEchoChar('*');
        }
    }//GEN-LAST:event_checkPswrdActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        logIn x = new logIn();
        x.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseClicked
        System.exit(0);
    }//GEN-LAST:event_exitMouseClicked

    public static void main(String args[]) {

        /* Set the Nimbus look and feel */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(adminVehicles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new signUp().setVisible(true);
            }
        });
    }

    public static void filecheck() throws IOException, ParseException {
        // Check if file exists
        java.io.File jsonFile = new java.io.File(filepath);

        // If file doesn't exist, create a new JSON structure
        if (!jsonFile.exists()) {
            record = new JSONObject();

            // Create admin array if it doesn't exist
            JSONArray adminArray = new JSONArray();
            JSONObject adminObj = new JSONObject();
            adminObj.put("email", "admin");
            adminObj.put("password", "admin");
            adminArray.add(adminObj);
            record.put("admin", adminArray);

            // Create empty users array
            userlist = new JSONArray();
            record.put("users", userlist);

            // Create empty cars array
            JSONArray carsArray = new JSONArray();
            record.put("cars", carsArray);

            // Write the initial structure to file
            try (FileWriter fileWriter = new FileWriter(filepath)) {
                fileWriter.write(record.toJSONString());
                fileWriter.flush();
            }

            return;
        }

        try (FileReader reader = new FileReader(filepath)) {
            record = (JSONObject) jsonParser.parse(reader);

            // Get users array or create if missing
            userlist = (JSONArray) record.get("users");
            if (userlist == null) {
                userlist = new JSONArray();
                record.put("users", userlist);
            }

            // Ensure admin array exists
            if (record.get("admin") == null) {
                JSONArray adminArray = new JSONArray();
                JSONObject adminObj = new JSONObject();
                adminObj.put("email", "admin");
                adminObj.put("password", "admin");
                adminArray.add(adminObj);
                record.put("admin", adminArray);
            }

            // Ensure cars array exists
            if (record.get("cars") == null) {
                record.put("cars", new JSONArray());
            }
        } catch (FileNotFoundException e) {
            // This should not happen as we check file existence before
            throw new IOException("File not found even after existence check", e);
        } catch (ParseException e) {
            // File exists but has invalid JSON format
            // Create new structure
            record = new JSONObject();
            userlist = new JSONArray();
            record.put("users", userlist);

            JSONArray adminArray = new JSONArray();
            JSONObject adminObj = new JSONObject();
            adminObj.put("email", "admin");
            adminObj.put("password", "admin");
            adminArray.add(adminObj);
            record.put("admin", adminArray);

            record.put("cars", new JSONArray());

            // Overwrite corrupted file
            try (FileWriter fileWriter = new FileWriter(filepath)) {
                fileWriter.write(record.toJSONString());
                fileWriter.flush();
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField CPswd;
    private javax.swing.JPasswordField Pswd;
    private javax.swing.JTextField TfEmail;
    private javax.swing.JTextField TfName;
    private javax.swing.JButton btnCreateAcc;
    private javax.swing.JCheckBox checkPswrd;
    private javax.swing.JLabel exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JLabel lblCPswd;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPswd;
    // End of variables declaration//GEN-END:variables
}
