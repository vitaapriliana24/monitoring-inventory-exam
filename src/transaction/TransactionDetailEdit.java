/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package transaction;

import productType.*;
import home.Home;
import java.sql.*;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import login.Login;
import product.ProductIndex;
import user.UserIndex;

/**
 *
 * @author LENOVO
 */
public class TransactionDetailEdit extends javax.swing.JFrame {

    public Statement statement;
    public ResultSet resultSet;
    Connection conn = config.MySQLConnection.BukaKoneksi();
    public String codeSelected;
    public int stockBefore;
    public Double productPrice;
    public Double productTotalPayment;
    public int productId;
    
    public TransactionDetailEdit(String code) {
        initComponents();
        if (code != null && !code.isEmpty()) {
            codeSelected = code;
        } 
        optionList();
        loadData(); 
        setLocationRelativeTo(null); 
    }

private void optionList() {
    String optionListSql = "SELECT code FROM t1_product WHERE is_Deleted = 0";
    try (PreparedStatement preparedStatementOpt = conn.prepareStatement(optionListSql);
         ResultSet rsOpt = preparedStatementOpt.executeQuery()) {

        ArrayList<String> categoryCode = new ArrayList<>();
        while (rsOpt.next()) {
            categoryCode.add(rsOpt.getString("code"));
        }
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(categoryCode.toArray(new String[0]));
        ComboProduct.setModel(model);

        if (codeSelected != null) {
            ComboProduct.setSelectedItem(codeSelected);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

private void loadData() {
    try {
        
        InputPrice.setText("-");
        InputTotalPayment.setText("-");
        ButtonSubmit.setEnabled(false);
  
        if (codeSelected != null && !codeSelected.isEmpty()) {
            String sql = "SELECT t.*,p.code AS product_code,p.price AS product_price FROM t2_transaction t JOIN t1_product p ON t.product_id = p.id WHERE t.is_deleted = 0 AND t.code = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setString(1, codeSelected);
                try (ResultSet rs = preparedStatement.executeQuery()) {

                    if (rs.next()) {
                        String code = rs.getString("code");
                        String product = rs.getString("product_code");
                        int qty = rs.getInt("qty");
                        Timestamp transactionDate = rs.getTimestamp("created_at");
                        Double totalPayment = rs.getDouble("total_payment");
                        Double price = rs.getDouble("product_price");

                        InputCode.setText(code);
                        InputDate.setDate(transactionDate);
                        InputQuantity.setValue(qty);
                        ComboProduct.setSelectedItem(product);
                        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("id","ID"));
                        String formattedTotalPayment = currencyFormatter.format(totalPayment);
                        String formattedPrice = currencyFormatter.format(price);
                        InputPrice.setText(formattedPrice);
                        InputTotalPayment.setText(formattedTotalPayment);
                    } else {
                        JOptionPane.showMessageDialog(this, "No product found with the selected code.");
                    }
                }
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TitleDetailUser = new javax.swing.JLabel();
        InputCode = new javax.swing.JTextField();
        LabelType = new javax.swing.JLabel();
        ButtonSubmit = new javax.swing.JButton();
        ButtonBack = new javax.swing.JButton();
        LabelName2 = new javax.swing.JLabel();
        ComboProduct = new javax.swing.JComboBox<>();
        LabelName3 = new javax.swing.JLabel();
        LabelName4 = new javax.swing.JLabel();
        InputQuantity = new javax.swing.JSpinner();
        LabelType1 = new javax.swing.JLabel();
        LabelName5 = new javax.swing.JLabel();
        InputDate = new com.toedter.calendar.JDateChooser();
        InputPrice = new javax.swing.JLabel();
        InputTotalPayment = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
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
        TitleDetailUser.setText("DETAIL TRANSACTION");
        TitleDetailUser.setToolTipText("");

        InputCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputCodeActionPerformed(evt);
            }
        });

        LabelType.setText("Code :");

        ButtonSubmit.setText("Submit");
        ButtonSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonSubmitActionPerformed(evt);
            }
        });

        ButtonBack.setText("Back");
        ButtonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonBackActionPerformed(evt);
            }
        });

        LabelName2.setText("Price :");

        ComboProduct.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ComboProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboProductActionPerformed(evt);
            }
        });

        LabelName3.setText("Product Code :");

        LabelName4.setText("Quantity :");

        LabelType1.setText("Date :");

        LabelName5.setText("Total Payment :");

        InputPrice.setText("InputPrice");

        InputTotalPayment.setText("InputTotalPayment");

        jButton1.setText("Check");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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

        MenuProductType.setText("Product Type");
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 133, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LabelName3)
                            .addComponent(LabelName4)
                            .addComponent(LabelName2)
                            .addComponent(LabelName5))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(ComboProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(InputQuantity, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(InputTotalPayment)
                                            .addComponent(InputPrice))
                                        .addGap(9, 9, 9))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(LabelType)
                                .addGap(110, 110, 110))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(LabelType1)
                                .addGap(115, 115, 115)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(InputCode)
                            .addComponent(InputDate, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE))))
                .addGap(129, 129, 129))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(228, 228, 228)
                        .addComponent(TitleDetailUser))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(ButtonBack)
                        .addGap(33, 33, 33)
                        .addComponent(ButtonSubmit)
                        .addGap(31, 31, 31)
                        .addComponent(jButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(TitleDetailUser, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InputCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelType))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelType1)
                    .addComponent(InputDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelName4)
                    .addComponent(InputQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelName3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelName2)
                    .addComponent(InputPrice))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelName5)
                    .addComponent(InputTotalPayment))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButtonSubmit)
                    .addComponent(ButtonBack)
                    .addComponent(jButton1))
                .addGap(36, 36, 36))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void InputCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputCodeActionPerformed

    private void ButtonSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonSubmitActionPerformed
    String code = InputCode.getText();
    Date date = InputDate.getDate();
    String qty = InputQuantity.getValue().toString();
    String product = (String) ComboProduct.getSelectedItem();
    if(date == null||code.equals("")||qty.equals("")||product.equals("")){
        JOptionPane.showMessageDialog(this, "Data is not valid (empty). Please try again.");
    }else{
        submit();
    }
    }//GEN-LAST:event_ButtonSubmitActionPerformed

    private void MenuHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuHomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MenuHomeActionPerformed

    private void ButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonBackActionPerformed
        TransactionIndex productCategoryIndex = new TransactionIndex();
        productCategoryIndex.setVisible(true);
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

    private void MenuProductTypeMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_MenuProductTypeMenuSelected
        ProductCategoryIndex productCategoryIndex = new ProductCategoryIndex();
        productCategoryIndex.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_MenuProductTypeMenuSelected

    private void ComboProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboProductActionPerformed

    }//GEN-LAST:event_ComboProductActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    String code = InputCode.getText();
    Date date = InputDate.getDate();
    int qty = Integer.parseInt(InputQuantity.getValue().toString());
    String product = (String) ComboProduct.getSelectedItem();
    if(date == null||code.equals("")||qty == 0||product.equals("")){
        JOptionPane.showMessageDialog(this, "Data is not valid (empty). Please try again.");
    } else {
        
        String productPriceSql = "SELECT id,price,stock FROM t1_product WHERE is_Deleted = 0 AND code = '" + product + "'";
        try (PreparedStatement preparedStatementOpt = conn.prepareStatement(productPriceSql);
             ResultSet rs = preparedStatementOpt.executeQuery()) {

            if (rs.next()) { 
                Double price = rs.getDouble("price");
                productId = rs.getInt("id");
                stockBefore = rs.getInt("stock");
                if (stockBefore < qty){
                    JOptionPane.showMessageDialog(this, "Unavaliable stock! Available stock: " + stockBefore + "\n Requested quantity: " + qty + ".\n Please adjust your order." );
                } else {
                    productTotalPayment = qty * price;
                    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("id","ID"));
                    String formattedTotalPayment = currencyFormatter.format(productTotalPayment);
                    String formattedPrice = currencyFormatter.format(price);
                    productPrice = price;
                    InputPrice.setText(formattedPrice);
                    InputTotalPayment.setText(formattedTotalPayment);
                    ButtonSubmit.setEnabled(true);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Product not found!");
                ButtonSubmit.setEnabled(false); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void MenuLogOutMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_MenuLogOutMenuSelected
        int response = JOptionPane.showConfirmDialog(this, "Are you sure want to log out?", "Log Out Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            this.dispose();
            Login loginFrame = new Login();
            loginFrame.setVisible(true);
        }
    }//GEN-LAST:event_MenuLogOutMenuSelected

    private void MenuReportMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_MenuReportMenuSelected
        ProductIndex productIndex = new ProductIndex();
        productIndex.setVisible(true);
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
private void submit() {
    String code = InputCode.getText();
    Date date = InputDate.getDate();
    Timestamp dateTimestamp = new Timestamp(date.getTime());
    int qty = Integer.parseInt(InputQuantity.getValue().toString());
    String product = (String) ComboProduct.getSelectedItem();

    try {
        statement = conn.createStatement();
        String checkSql = "SELECT COUNT(*) FROM t2_transaction WHERE code = '" + code + "'";
        ResultSet rs = statement.executeQuery(checkSql);
        rs.next();
        int count = rs.getInt(1);


                if (codeSelected == null || codeSelected.isEmpty()){
                    if (count > 0) {
                        JOptionPane.showMessageDialog(this, "Error: Code already exists.");
                    } else {
                        String sql = "INSERT INTO t2_transaction (code, qty, total_payment, product_id, created_at, updated_at) VALUES (?, ?, ?, ?, '"+dateTimestamp+"', CURRENT_TIMESTAMP);";
                        PreparedStatement preparedStatement = conn.prepareStatement(sql);
                        preparedStatement.setString(1, code);
                        preparedStatement.setInt(2, qty);
                        preparedStatement.setDouble(3, productTotalPayment);
                        preparedStatement.setInt(4, productId); 
                        preparedStatement.executeUpdate();
                        JOptionPane.showMessageDialog(this, "Data inserted successfully!");

                        //insert into t2_product_inventory_log
                        String transactionLogSql = "INSERT INTO t2_product_inventory_log (created_at, product_id, stock_before, stock_after, adjustment, reason_code) VALUES (CURRENT_TIMESTAMP,?, ?, (SELECT (stock - "+qty+") FROM t1_product WHERE code = ?), ?, ?)";
                        PreparedStatement preparedStatementTransactionLog = conn.prepareStatement(transactionLogSql);
                        preparedStatementTransactionLog.setInt(1, productId);
                        preparedStatementTransactionLog.setInt(2, stockBefore);
                        preparedStatementTransactionLog.setString(3, product);
                        preparedStatementTransactionLog.setInt(4, qty);
                        preparedStatementTransactionLog.setString(5, "TRANSACTION-INSERT");
                        preparedStatementTransactionLog.executeUpdate();

                        //update stock product
                        String productSql = "UPDATE t1_product SET  stock = (stock - ?), updated_at = CURRENT_TIMESTAMP WHERE id = ?";
                        PreparedStatement preparedStatementProduct = conn.prepareStatement(productSql);
                        preparedStatementProduct.setInt(1, qty);
                        preparedStatementProduct.setInt(2,productId);
                        preparedStatementProduct.executeUpdate();
                    }
                } else {
                    
                        statement = conn.createStatement();
                        String getStockSql = "SELECT stock_before FROM t2_product_inventory_log WHERE product_id = "+productId+" order by created_at DESC LIMIT 1";
                        ResultSet rsStock = statement.executeQuery(getStockSql);
                        rsStock.next();
                        Integer stockBeforeLog = rsStock.getInt("stock_before");
                        
                        String deleteLogBeforesql = "UPDATE t2_product_inventory_log SET is_deleted = 1 WHERE id = (SELECT id FROM t2_product_inventory_log WHERE product_id = "+productId+" order by created_at DESC LIMIT 1 )";
                        PreparedStatement psDeleteLogBeforesql = conn.prepareStatement(deleteLogBeforesql);
                        psDeleteLogBeforesql.executeUpdate();

                        String sql = "UPDATE t2_transaction SET code = ?, qty = ?, total_payment = ?, product_id = "+productId+", updated_at = CURRENT_TIMESTAMP WHERE code = ?;";
                        PreparedStatement preparedStatement = conn.prepareStatement(sql);
                        preparedStatement.setString(1, code);
                        preparedStatement.setInt(2, qty);
                        preparedStatement.setDouble(3, productTotalPayment);
                        preparedStatement.setString(4, codeSelected); 
                        preparedStatement.executeUpdate();
                        JOptionPane.showMessageDialog(this, "Data updated successfully!");

                        //insert into t2_product_inventory_log dengan data terbaru
                        String transactionLogSql = "INSERT INTO t2_product_inventory_log (created_at, product_id, stock_before, stock_after, adjustment, reason_code) VALUES (CURRENT_TIMESTAMP,?, ?, (SELECT (stock - "+qty+") FROM t1_product WHERE id = ?), ?, ?)";
                        PreparedStatement preparedStatementTransactionLog = conn.prepareStatement(transactionLogSql);
                        preparedStatementTransactionLog.setInt(1, productId);
                        preparedStatementTransactionLog.setInt(2, stockBeforeLog);
                        preparedStatementTransactionLog.setInt(3, productId);
                        preparedStatementTransactionLog.setInt(4, qty);
                        preparedStatementTransactionLog.setString(5, "TRANSACTION-EDIT");
                        preparedStatementTransactionLog.executeUpdate();

                        //update stock product
                        String productSql = "UPDATE t1_product SET  stock = "+(stockBeforeLog - qty)+", updated_at = CURRENT_TIMESTAMP WHERE id = ?";
                        PreparedStatement preparedStatementProduct = conn.prepareStatement(productSql);
                        preparedStatementProduct.setInt(1,productId);
                        preparedStatementProduct.executeUpdate();
                   }

        
        TransactionIndex productCategoryIndex = new TransactionIndex();
        productCategoryIndex.setVisible(true);
        this.dispose();

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Encryption Error: " + e.getMessage());
    }
}

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TransactionDetailEdit(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonBack;
    private javax.swing.JButton ButtonSubmit;
    private javax.swing.JComboBox<String> ComboProduct;
    private javax.swing.JTextField InputCode;
    private com.toedter.calendar.JDateChooser InputDate;
    private javax.swing.JLabel InputPrice;
    private javax.swing.JSpinner InputQuantity;
    private javax.swing.JLabel InputTotalPayment;
    private javax.swing.JLabel LabelName2;
    private javax.swing.JLabel LabelName3;
    private javax.swing.JLabel LabelName4;
    private javax.swing.JLabel LabelName5;
    private javax.swing.JLabel LabelType;
    private javax.swing.JLabel LabelType1;
    private javax.swing.JMenu MenuHome;
    private javax.swing.JMenu MenuLogOut;
    private javax.swing.JMenu MenuProduct;
    private javax.swing.JMenu MenuProductType;
    private javax.swing.JMenu MenuReport;
    private javax.swing.JMenu MenuTransaction;
    private javax.swing.JMenu MenuUSer;
    private javax.swing.JLabel TitleDetailUser;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration//GEN-END:variables
}
