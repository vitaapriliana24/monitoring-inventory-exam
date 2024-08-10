/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package user;


import java.sql.*;
import com.mysql.jdbc.StringUtils;
import home.Home;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import login.Login;
import product.ProductIndex;
import productType.ProductCategoryIndex;
import report.ReportIndex;
import security.PasswordEncryption;
import transaction.TransactionIndex;

public class UserDetail extends javax.swing.JFrame {

    public Statement statement;
    public ResultSet resultSet;
    Connection conn = config.MySQLConnection.BukaKoneksi();
    public String emailSelected;
    
    public UserDetail(String email) {
        initComponents();
        if (!StringUtils.isEmptyOrWhitespaceOnly(email)){
            emailSelected = email;
            loadData();
        }
        setLocationRelativeTo(null);
    }
    
    private void loadData() {
        try {
            if (emailSelected != null && !emailSelected.isEmpty()) {
                String sql = "SELECT name, email, password FROM t1_user WHERE email = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, emailSelected);
                ResultSet rs = preparedStatement.executeQuery();

                if (rs.next()) {
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String encryptedPassword = rs.getString("password");

                    String decryptedPassword = PasswordEncryption.decrypt(encryptedPassword);

                    InputName.setText(name);
                    InputEmail.setText(email);
                    InputPassword.setText(decryptedPassword); 
                } else {
                    JOptionPane.showMessageDialog(this, "No user found with the selected email.");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Decryption Error: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TitleDetailUser = new javax.swing.JLabel();
        InputName = new javax.swing.JTextField();
        LabelName = new javax.swing.JLabel();
        LabelEmail = new javax.swing.JLabel();
        InputEmail = new javax.swing.JTextField();
        LabelPassword = new javax.swing.JLabel();
        ButtonSubmit = new javax.swing.JButton();
        InputPassword = new javax.swing.JTextField();
        ButtonBack = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        MenuHome = new javax.swing.JMenu();
        MenuUSer = new javax.swing.JMenu();
        MenuProductType = new javax.swing.JMenu();
        MenuProduct = new javax.swing.JMenu();
        MenuTransaction = new javax.swing.JMenu();
        MenuReport = new javax.swing.JMenu();
        MenuLogOut = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TitleDetailUser.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TitleDetailUser.setText("DETAIL USER");
        TitleDetailUser.setToolTipText("");

        InputName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputNameActionPerformed(evt);
            }
        });

        LabelName.setText("Name :");

        LabelEmail.setText("Email :");

        InputEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputEmailActionPerformed(evt);
            }
        });

        LabelPassword.setText("Password :");

        ButtonSubmit.setText("Submit");
        ButtonSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonSubmitActionPerformed(evt);
            }
        });

        InputPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputPasswordActionPerformed(evt);
            }
        });

        ButtonBack.setText("Back");
        ButtonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonBackActionPerformed(evt);
            }
        });

        MenuHome.setText("Home");
        MenuHome.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                MenuHomeMenuSelected(evt);
            }
        });
        MenuHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuHomeActionPerformed(evt);
            }
        });
        jMenuBar1.add(MenuHome);

        MenuUSer.setText("User");
        MenuUSer.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                MenuUSerMenuSelected(evt);
            }
        });
        jMenuBar1.add(MenuUSer);

        MenuProductType.setText("Product Category");
        MenuProductType.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                MenuProductTypeMenuSelected(evt);
            }
        });
        jMenuBar1.add(MenuProductType);

        MenuProduct.setText("Product");
        MenuProduct.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                MenuProductMenuSelected(evt);
            }
        });
        jMenuBar1.add(MenuProduct);

        MenuTransaction.setText("Transaction");
        MenuTransaction.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                MenuTransactionMenuSelected(evt);
            }
        });
        jMenuBar1.add(MenuTransaction);

        MenuReport.setText("Report");
        MenuReport.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                MenuReportMenuSelected(evt);
            }
        });
        jMenuBar1.add(MenuReport);

        MenuLogOut.setText("Log Out");
        MenuLogOut.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                MenuLogOutMenuSelected(evt);
            }
        });
        jMenuBar1.add(MenuLogOut);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(216, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LabelEmail)
                            .addComponent(LabelPassword)
                            .addComponent(LabelName))
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(InputName, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                            .addComponent(InputEmail, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(InputPassword, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(187, 187, 187))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(TitleDetailUser)
                        .addGap(306, 306, 306))))
            .addGroup(layout.createSequentialGroup()
                .addGap(248, 248, 248)
                .addComponent(ButtonBack)
                .addGap(33, 33, 33)
                .addComponent(ButtonSubmit)
                .addGap(0, 264, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(TitleDetailUser, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InputName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelName))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InputEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelEmail))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelPassword)
                    .addComponent(InputPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButtonSubmit)
                    .addComponent(ButtonBack))
                .addGap(29, 29, 29))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void InputNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputNameActionPerformed

    private void InputEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputEmailActionPerformed

    private void ButtonSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonSubmitActionPerformed
    String email = InputEmail.getText();
    String name = InputName.getText();
    String password = InputPassword.getText();
    if(email.equals("")||name.equals("")||password.equals("")){
        JOptionPane.showMessageDialog(this, "Data is not valid (empty). Please try again.");
    }else{
        submit();
    }
    }//GEN-LAST:event_ButtonSubmitActionPerformed

    private void InputPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputPasswordActionPerformed

    private void MenuHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuHomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MenuHomeActionPerformed

    private void ButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonBackActionPerformed
        UserIndex userIndex = new UserIndex();
        userIndex.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_ButtonBackActionPerformed

    private void MenuUSerMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_MenuUSerMenuSelected
        UserIndex userIndex = new UserIndex();
        userIndex.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_MenuUSerMenuSelected

    private void MenuHomeMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_MenuHomeMenuSelected
        Home home = new Home();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_MenuHomeMenuSelected

    private void MenuLogOutMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_MenuLogOutMenuSelected
        int response = JOptionPane.showConfirmDialog(this, "Are you sure want to log out?", "Log Out Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            this.dispose();
            Login loginFrame = new Login();
            loginFrame.setVisible(true);
        }
    }//GEN-LAST:event_MenuLogOutMenuSelected

    private void MenuReportMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_MenuReportMenuSelected
        try {
            ReportIndex reportIndex;
            reportIndex = new ReportIndex();
            reportIndex.setVisible(true);
        } catch (ParseException ex) {
            Logger.getLogger(ReportIndex.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
    }//GEN-LAST:event_MenuReportMenuSelected

    private void MenuTransactionMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_MenuTransactionMenuSelected
        TransactionIndex transactionIndex = new TransactionIndex();
        transactionIndex.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_MenuTransactionMenuSelected

    private void MenuProductMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_MenuProductMenuSelected
        ProductIndex productIndex = new ProductIndex();
        productIndex.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_MenuProductMenuSelected

    private void MenuProductTypeMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_MenuProductTypeMenuSelected
        ProductCategoryIndex productCategoryIndex = new ProductCategoryIndex();
        productCategoryIndex.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_MenuProductTypeMenuSelected
private void submit() {
    String email = InputEmail.getText();
    String name = InputName.getText();
    String password = InputPassword.getText();

    try {
        
        String encryptedPassword = PasswordEncryption.encrypt(password);

        statement = conn.createStatement();
        String checkSql = "SELECT COUNT(*) FROM t1_user WHERE email = '" + email + "'";
        ResultSet rs = statement.executeQuery(checkSql);
        rs.next();
        int count = rs.getInt(1);

        if (emailSelected != null && !emailSelected.isEmpty()) {
            String sql = "UPDATE t1_user SET name = ?, password = ?, email = ?, updated_at = CURRENT_TIMESTAMP WHERE email = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, encryptedPassword); 
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, emailSelected);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data updated successfully!");
        } else {
            if (count > 0) {
                JOptionPane.showMessageDialog(this, "Error: Email already exists.");
            } else {
                String sql = "INSERT INTO t1_user (email, name, password) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, encryptedPassword);
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data added successfully!");
            }
        }

        UserIndex userIndex = new UserIndex();
        userIndex.setVisible(true);
        this.dispose();

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Encryption Error: " + e.getMessage());
    }
}


private String hashPassword(String password) {
    try {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hashedBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    } catch (NoSuchAlgorithmException e) {
        throw new RuntimeException("Error hashing password", e);
    }
}

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserDetail(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonBack;
    private javax.swing.JButton ButtonSubmit;
    private javax.swing.JTextField InputEmail;
    private javax.swing.JTextField InputName;
    private javax.swing.JTextField InputPassword;
    private javax.swing.JLabel LabelEmail;
    private javax.swing.JLabel LabelName;
    private javax.swing.JLabel LabelPassword;
    private javax.swing.JMenu MenuHome;
    private javax.swing.JMenu MenuLogOut;
    private javax.swing.JMenu MenuProduct;
    private javax.swing.JMenu MenuProductType;
    private javax.swing.JMenu MenuReport;
    private javax.swing.JMenu MenuTransaction;
    private javax.swing.JMenu MenuUSer;
    private javax.swing.JLabel TitleDetailUser;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration//GEN-END:variables
}
