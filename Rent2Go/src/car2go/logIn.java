package car2go;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class logIn extends javax.swing.JFrame {

    //File path for JSON
    private static final String FILE_NAME = "C:\\Users\\User\\Downloads\\Rent2Go\\Rent2Go\\src\\car2go\\car2go.json";
    private static final JSONParser jsonParser = new JSONParser();
    private JSONObject record;
    private JSONArray users;
    private JSONArray admin;

    public logIn() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        LblEmail = new javax.swing.JLabel();
        TfEmail = new javax.swing.JTextField();
        TfPswd = new javax.swing.JLabel();
        Pswd = new javax.swing.JPasswordField();
        checkPswrd = new javax.swing.JCheckBox();
        BtnLogin = new javax.swing.JButton();
        LblSignIn = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        LblNoAcc = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        exit = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Car2Go");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(1, 1));
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(500, 80));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(500, 400));

        LblEmail.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        LblEmail.setForeground(new java.awt.Color(0, 0, 0));
        LblEmail.setText("Email:");

        TfEmail.setForeground(new java.awt.Color(0, 0, 0));
        TfEmail.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        TfEmail.setToolTipText("");
        TfEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        TfPswd.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        TfPswd.setForeground(new java.awt.Color(0, 0, 0));
        TfPswd.setText("Password:");

        Pswd.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        Pswd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        checkPswrd.setBackground(new java.awt.Color(255, 255, 255));
        checkPswrd.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        checkPswrd.setForeground(new java.awt.Color(48, 53, 57));
        checkPswrd.setText("Show Password");
        checkPswrd.setBorder(null);
        checkPswrd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkPswrdActionPerformed(evt);
            }
        });

        BtnLogin.setBackground(new java.awt.Color(102, 0, 0));
        BtnLogin.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        BtnLogin.setForeground(new java.awt.Color(255, 255, 255));
        BtnLogin.setText("Log In");
        BtnLogin.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        BtnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLoginActionPerformed(evt);
            }
        });

        LblSignIn.setBackground(new java.awt.Color(0, 0, 0));
        LblSignIn.setFont(new java.awt.Font("Gtek Technology", 1, 50)); // NOI18N
        LblSignIn.setForeground(new java.awt.Color(48, 53, 57));
        LblSignIn.setText("sign in");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(checkPswrd)
                            .addComponent(LblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TfPswd)
                            .addComponent(Pswd)
                            .addComponent(BtnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(TfEmail, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(LblSignIn)))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LblSignIn)
                .addGap(34, 34, 34)
                .addComponent(LblEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(TfPswd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Pswd, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkPswrd)
                .addGap(18, 18, 18)
                .addComponent(BtnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setPreferredSize(new java.awt.Dimension(500, 50));

        LblNoAcc.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        LblNoAcc.setForeground(new java.awt.Color(48, 53, 57));
        LblNoAcc.setText("Don't have an account?");

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Sign Up");
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
                .addGap(136, 136, 136)
                .addComponent(LblNoAcc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(176, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblNoAcc)
                    .addComponent(jLabel1))
                .addGap(0, 35, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel7);

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

    private void BtnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLoginActionPerformed
        try {
            loadUserData();

            String inputEmail = TfEmail.getText().trim();
            String inputPassword = new String(Pswd.getPassword()).trim();

            if (inputEmail.isEmpty() || inputPassword.isEmpty()) {
                showErrorMessage("Please enter both email and password", "Login Failed");
                return;
            }

            // Check in users array
            if (users != null) {
                for (Object obj : users) {
                    JSONObject user = (JSONObject) obj;
                    String email = (String) user.get("email");
                    String password = (String) user.get("password");

                    if (email != null && password != null
                            && inputEmail.equalsIgnoreCase(email) && inputPassword.equals(password)) {
                        openUserHome();
                        return;
                    }
                }
            }

            // Check in admin array
            if (admin != null) {
                for (Object obj : admin) {
                    JSONObject adm = (JSONObject) obj;
                    String email = (String) adm.get("email");
                    String password = (String) adm.get("password");

                    if (email != null && password != null
                            && inputEmail.equalsIgnoreCase(email) && inputPassword.equals(password)) {
                        openAdminHome();
                        return;
                    }
                }
            }

            showErrorMessage("Invalid email or password", "Login Failed");

        } catch (IOException | ParseException ex) {
            Logger.getLogger(logIn.class.getName()).log(Level.SEVERE, null, ex);
            showErrorMessage("Error reading user data: " + ex.getMessage(), "Error");
        }
    }

    private void openUserHome() {
        userHome x = new userHome();
        x.setVisible(true);
        this.dispose();
    }

    private void openAdminHome() {
        adminHome x = new adminHome();
        x.setVisible(true);
        this.dispose();
    }

    private void showErrorMessage(String message, String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }

    private void loadUserData() throws IOException, ParseException {
        try (FileReader reader = new FileReader(FILE_NAME)) {
            record = (JSONObject) jsonParser.parse(reader);
            users = (JSONArray) record.get("users");
            admin = (JSONArray) record.get("admin");

            if (users == null) {
                users = new JSONArray();
            }
            if (admin == null) {
                admin = new JSONArray();
            }
        } catch (FileNotFoundException e) {
            showErrorMessage("Configuration file not found: " + FILE_NAME, "Error");
            throw e;
        }
    }//GEN-LAST:event_BtnLoginActionPerformed

    private void checkPswrdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkPswrdActionPerformed
        if (checkPswrd.isSelected()) {
            Pswd.setEchoChar((char) 0);  // Show password
        } else {
            Pswd.setEchoChar('*');       // Hide password
        }
    }//GEN-LAST:event_checkPswrdActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        signUp x = new signUp();
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
            java.util.logging.Logger.getLogger(logIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new logIn().setVisible(true);
            }
        });
    }
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnLogin;
    private javax.swing.JLabel LblEmail;
    private javax.swing.JLabel LblNoAcc;
    private javax.swing.JLabel LblSignIn;
    private javax.swing.JPasswordField Pswd;
    private javax.swing.JTextField TfEmail;
    private javax.swing.JLabel TfPswd;
    private javax.swing.JCheckBox checkPswrd;
    private javax.swing.JLabel exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    // End of variables declaration//GEN-END:variables
}
