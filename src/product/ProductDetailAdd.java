/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package product;

import productType.*;
import com.mysql.jdbc.StringUtils;
import home.Home;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import login.Login;
import report.ReportIndex;
import transaction.TransactionIndex;
import user.UserIndex;

/**
 *
 * @author LENOVO
 */
public class ProductDetailAdd extends javax.swing.JFrame {

    public Statement statement;
    public ResultSet resultSet;
    Connection conn = config.MySQLConnection.BukaKoneksi();
    public ProductDetailAdd() {
        initComponents();
        optionList();
        setLocationRelativeTo(null); 
    }

private void optionList() {
    String optionListSql = "SELECT code FROM t1_category_product WHERE is_Deleted = 0";
    try (PreparedStatement preparedStatementOpt = conn.prepareStatement(optionListSql);
         ResultSet rsOpt = preparedStatementOpt.executeQuery()) {

        ArrayList<String> categoryCode = new ArrayList<>();
        while (rsOpt.next()) {
            categoryCode.add(rsOpt.getString("code"));
        }
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(categoryCode.toArray(new String[0]));
        ComboCat.setModel(model);

    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TitleDetailUser = new javax.swing.JLabel();
        InputCode = new javax.swing.JTextField();
        LabelType = new javax.swing.JLabel();
        LabelName = new javax.swing.JLabel();
        InputName = new javax.swing.JTextField();
        LabelDescription = new javax.swing.JLabel();
        ButtonSubmit = new javax.swing.JButton();
        ButtonBack = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        InputDesc = new javax.swing.JTextArea();
        LabelName1 = new javax.swing.JLabel();
        InputStock = new javax.swing.JTextField();
        InputPrice = new javax.swing.JTextField();
        LabelName2 = new javax.swing.JLabel();
        ComboCat = new javax.swing.JComboBox<>();
        LabelName3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        MenuHome = new javax.swing.JMenu();
        MenuUSer = new javax.swing.JMenu();
        MenuProductType = new javax.swing.JMenu();
        MenuProduct = new javax.swing.JMenu();
        MenuTransaction = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        MenuLogOut = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TitleDetailUser.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TitleDetailUser.setText("DETAIL PRODUCT");
        TitleDetailUser.setToolTipText("");

        InputCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputCodeActionPerformed(evt);
            }
        });

        LabelType.setText("Code :");

        LabelName.setText("Name :");

        InputName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputNameActionPerformed(evt);
            }
        });

        LabelDescription.setText("Description :");

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

        InputDesc.setColumns(20);
        InputDesc.setRows(5);
        jScrollPane2.setViewportView(InputDesc);

        LabelName1.setText("Stock :");

        InputStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputStockActionPerformed(evt);
            }
        });

        InputPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputPriceActionPerformed(evt);
            }
        });

        LabelName2.setText("Price :");

        ComboCat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        LabelName3.setText("Category :");

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

        jMenu1.setText("Report");
        jMenu1.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                jMenu1MenuSelected(evt);
            }
        });
        jMenuBar1.add(jMenu1);

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
                .addGap(248, 248, 248)
                .addComponent(ButtonBack)
                .addGap(33, 33, 33)
                .addComponent(ButtonSubmit)
                .addGap(0, 264, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TitleDetailUser)
                .addGap(285, 285, 285))
            .addGroup(layout.createSequentialGroup()
                .addGap(175, 175, 175)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LabelDescription)
                        .addGap(53, 53, 53)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LabelType)
                                    .addComponent(LabelName)
                                    .addComponent(LabelName1))
                                .addGap(82, 82, 82))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LabelName2)
                                    .addComponent(LabelName3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(InputPrice)
                            .addComponent(InputName, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(InputCode)
                            .addComponent(InputStock, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ComboCat, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TitleDetailUser, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InputCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelType))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InputName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InputStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelName1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InputPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelName2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboCat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelName3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelDescription)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButtonSubmit)
                    .addComponent(ButtonBack))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void InputCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputCodeActionPerformed

    private void InputNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputNameActionPerformed

    private void ButtonSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonSubmitActionPerformed
    String name = InputName.getText();
    String code = InputCode.getText();
    String stock = InputStock.getText();
    String price = InputPrice.getText();
    String category = (String) ComboCat.getSelectedItem();
    String desc = InputDesc.getText();
    if(name.equals("")||code.equals("")||desc.equals("")||stock.equals("")||price.equals("")||category.equals("")){
        JOptionPane.showMessageDialog(this, "Data is not valid (empty). Please try again.");
    }else{
        addData();
    }
    }//GEN-LAST:event_ButtonSubmitActionPerformed

    private void MenuHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuHomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MenuHomeActionPerformed

    private void ButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonBackActionPerformed
        ProductIndex productCategoryIndex = new ProductIndex();
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

    private void InputStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputStockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputStockActionPerformed

    private void InputPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputPriceActionPerformed

    private void MenuLogOutMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_MenuLogOutMenuSelected
        int response = JOptionPane.showConfirmDialog(this, "Are you sure want to log out?", "Log Out Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            this.dispose();
            Login loginFrame = new Login();
            loginFrame.setVisible(true);
        }
    }//GEN-LAST:event_MenuLogOutMenuSelected

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

    private void jMenu1MenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_jMenu1MenuSelected
        try {
            ReportIndex reportIndex;
            reportIndex = new ReportIndex();
            reportIndex.setVisible(true);
        } catch (ParseException ex) {
            Logger.getLogger(ReportIndex.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
    }//GEN-LAST:event_jMenu1MenuSelected
private void addData() {
    String name = InputName.getText();
    String code = InputCode.getText();
    String stock = InputStock.getText();
    String price = InputPrice.getText();
    String category = (String) ComboCat.getSelectedItem();
    String desc = InputDesc.getText();

    try {
        statement = conn.createStatement();
        String checkSql = "SELECT COUNT(*) FROM t1_product WHERE code = '" + code + "'";
        ResultSet rs = statement.executeQuery(checkSql);
        rs.next();
        int count = rs.getInt(1);

            if (count > 0) {
                JOptionPane.showMessageDialog(this, "Error: Code already exists.");
            } else {
                String sql = "INSERT INTO t1_product (code, name,stock,price,category_product_id, description) VALUES (?, ?, ?,?,(SELECT id FROM t1_category_product WHERE code = '"+category+"'),?)";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, code);
                preparedStatement.setString(2, name);
                preparedStatement.setInt(3, Integer.parseInt(stock));
                preparedStatement.setDouble(4, Double.parseDouble(price));
                preparedStatement.setString(5, desc);
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data added successfully!");
                
                //insert into t2_product_inventory_log untuk record track stock inventory
                String transactionLogSql = "INSERT INTO t2_product_inventory_log (created_at, product_id, stock_before, stock_after, adjustment, reason_code) " +
             "VALUES (CURRENT_TIMESTAMP,(SELECT id FROM t1_product WHERE code = ?), ?, ?, ?, ?)";
                PreparedStatement preparedStatementTransactionLog = conn.prepareStatement(transactionLogSql);
                preparedStatementTransactionLog.setString(1, code);
                preparedStatementTransactionLog.setInt(2, 0);
                preparedStatementTransactionLog.setInt(3, Integer.parseInt(stock));
                preparedStatementTransactionLog.setInt(4, Integer.parseInt(stock));
                preparedStatementTransactionLog.setString(5, "INSERT-PRODUCT");
                preparedStatementTransactionLog.executeUpdate();
            }

        ProductIndex productCategoryIndex = new ProductIndex();
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
                new ProductDetailAdd().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonBack;
    private javax.swing.JButton ButtonSubmit;
    private javax.swing.JComboBox<String> ComboCat;
    private javax.swing.JTextField InputCode;
    private javax.swing.JTextArea InputDesc;
    private javax.swing.JTextField InputName;
    private javax.swing.JTextField InputPrice;
    private javax.swing.JTextField InputStock;
    private javax.swing.JLabel LabelDescription;
    private javax.swing.JLabel LabelName;
    private javax.swing.JLabel LabelName1;
    private javax.swing.JLabel LabelName2;
    private javax.swing.JLabel LabelName3;
    private javax.swing.JLabel LabelType;
    private javax.swing.JMenu MenuHome;
    private javax.swing.JMenu MenuLogOut;
    private javax.swing.JMenu MenuProduct;
    private javax.swing.JMenu MenuProductType;
    private javax.swing.JMenu MenuTransaction;
    private javax.swing.JMenu MenuUSer;
    private javax.swing.JLabel TitleDetailUser;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
