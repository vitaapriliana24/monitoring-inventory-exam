/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package report;

import transaction.*;
import product.*;
import productType.*;
import home.Home;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import login.Login;
import user.UserIndex;

/**
 *
 * @author LENOVO
 */
public class ReportIndex extends javax.swing.JFrame {


    public Statement statement;
    public ResultSet resultSet;
    Connection conn = config.MySQLConnection.BukaKoneksi();
    java.sql.Date startDateSQL;
    java.sql.Date endDateSQL;
    
    public ReportIndex() throws ParseException {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Product Index");
        initComponents();
        setLocationRelativeTo(null); 
        loadDataToTable(null,null,null);
    }

    private void loadDataToTable(String keyword, String startDateParam,String endDateParam) throws ParseException {
        DefaultTableModel model = new DefaultTableModel(new String[]{"No", "Code", "Name", "Current Stock", "Stock Before", "Restock","Sold","Revenue"}, 0);
        try {
            statement = conn.createStatement();
            String sql;
            PreparedStatement preparedStatement = null;
            if(keyword == null || keyword.equals("")){
                if (startDateParam == null && endDateParam == null){
                    sql = "SELECT id,code,name,stock AS current_stock ,(SELECT stock_before FROM t2_product_inventory_log WHERE is_deleted = 0 AND product_id = p.id order by created_at ASC LIMIT 1 ) AS stock_before,(SELECT SUM(adjustment) FROM t2_product_inventory_log WHERE is_deleted = 0 AND product_id = p.id AND reason_code IN ('UPDATE-STOCK','INSERT-PRODUCT')) AS restock,(SELECT case when SUM(adjustment) is null then 0 else SUM(adjustment) END AS sold FROM t2_product_inventory_log WHERE is_deleted = 0 AND product_id = p.id AND reason_code IN ('TRANSACTION-INSERT','TRANSACTION-EDIT')) AS sold, (SELECT COALESCE(SUM(total_payment), 0) FROM t2_transaction WHERE is_deleted = 0 AND product_id = p.id GROUP BY product_id) AS revenue  FROM  t1_product p WHERE p.is_deleted = 0";
                    preparedStatement = conn.prepareStatement(sql);
                } else {
                    sql = "SELECT id,code,name,stock AS current_stock ,(SELECT stock_before FROM t2_product_inventory_log WHERE is_deleted = 0 AND (created_at BETWEEN ? AND ?) AND product_id = p.id order by created_at ASC LIMIT 1 ) AS stock_before,(SELECT SUM(adjustment) FROM t2_product_inventory_log WHERE is_deleted = 0 AND (created_at BETWEEN ? AND ?) AND product_id = p.id AND reason_code IN ('UPDATE-STOCK','INSERT-PRODUCT')) AS restock,(SELECT case when SUM(adjustment) is null then 0 else SUM(adjustment) END AS sold FROM t2_product_inventory_log WHERE is_deleted = 0 AND (created_at BETWEEN ? AND ?) AND product_id = p.id AND reason_code IN ('TRANSACTION-INSERT','TRANSACTION-EDIT')) AS sold  , (SELECT COALESCE(SUM(total_payment), 0) FROM t2_transaction WHERE is_deleted = 0 AND (created_at BETWEEN ? AND ?) AND product_id = p.id GROUP BY product_id ) AS revenue FROM  t1_product p WHERE p.is_deleted = 0";
                    preparedStatement = conn.prepareStatement(sql); 
                    preparedStatement.setString(1, startDateParam);
                    preparedStatement.setString(2, endDateParam); 
                    preparedStatement.setString(3, startDateParam); 
                    preparedStatement.setString(4, endDateParam); 
                    preparedStatement.setString(5, startDateParam);
                    preparedStatement.setString(6, endDateParam); 
                    preparedStatement.setString(7, startDateParam);
                    preparedStatement.setString(8, endDateParam);
                }
            } else {
                if (startDateParam == null && endDateParam == null){
                        sql = "SELECT id,code,name,stock AS current_stock ,(SELECT stock_before FROM t2_product_inventory_log WHERE is_deleted = 0 AND product_id = p.id order by created_at ASC LIMIT 1 ) AS stock_before,(SELECT SUM(adjustment) FROM t2_product_inventory_log WHERE is_deleted = 0 AND product_id = p.id AND reason_code IN ('UPDATE-STOCK','INSERT-PRODUCT')) AS restock,(SELECT case when SUM(adjustment) is null then 0 else SUM(adjustment) END AS sold FROM t2_product_inventory_log WHERE is_deleted = 0 AND product_id = p.id AND reason_code IN ('TRANSACTION-INSERT','TRANSACTION-EDIT')) AS sold , (SELECT COALESCE(SUM(total_payment), 0) FROM t2_transaction WHERE is_deleted = 0 AND product_id = p.id GROUP BY product_id ) AS revenue FROM  t1_product p WHERE (LOWER(code) LIKE LOWER(?) OR LOWER(name) LIKE LOWER(?) OR LOWER(stock) LIKE LOWER(?)) AND p.is_deleted = 0 ";
                        preparedStatement = conn.prepareStatement(sql); 
                        String searchPattern = "%" + keyword + "%";
                        preparedStatement.setString(1, searchPattern);
                        preparedStatement.setString(2, searchPattern);
                        preparedStatement.setString(3, searchPattern);
                } else {
                        sql = "SELECT id,code,name,stock AS current_stock ,(SELECT stock_before FROM t2_product_inventory_log WHERE is_deleted = 0 AND (created_at BETWEEN ? AND ?) AND product_id = p.id order by created_at ASC LIMIT 1 ) AS stock_before,(SELECT SUM(adjustment) FROM t2_product_inventory_log WHERE is_deleted = 0 AND (created_at BETWEEN ? AND ?) AND product_id = p.id AND reason_code IN ('UPDATE-STOCK','INSERT-PRODUCT')) AS restock,(SELECT case when SUM(adjustment) is null then 0 else SUM(adjustment) END AS sold FROM t2_product_inventory_log WHERE is_deleted = 0 AND (created_at BETWEEN ? AND ?) AND product_id = p.id AND reason_code IN ('TRANSACTION-INSERT','TRANSACTION-EDIT')) AS sold , (SELECT COALESCE(SUM(total_payment), 0) FROM t2_transaction WHERE (created_at BETWEEN ? AND ?) AND is_deleted = 0 AND product_id = p.id GROUP BY product_id ) AS revenue FROM  t1_product p WHERE (LOWER(code) LIKE LOWER(?) OR LOWER(name) LIKE LOWER(?) OR LOWER(stock) LIKE LOWER(?)) AND p.is_deleted = 0";
                        preparedStatement.setString(1, startDateParam);
                        preparedStatement.setString(2, endDateParam);
                        preparedStatement.setString(3, startDateParam);
                        preparedStatement.setString(4, endDateParam);
                        preparedStatement.setString(5, startDateParam);
                        preparedStatement.setString(6, endDateParam);
                        preparedStatement.setString(7, startDateParam);
                        preparedStatement.setString(8, endDateParam);
                        String searchPattern = "%" + keyword + "%";
                        preparedStatement.setString(9, searchPattern);
                        preparedStatement.setString(10, searchPattern);
                        preparedStatement.setString(11, searchPattern);

                    }
                }
                    resultSet = preparedStatement.executeQuery();
                    int no = 1;
                    while (resultSet.next()) {
                        String code = resultSet.getString("code");
                        String name = resultSet.getString("name");
                        int currentStock = resultSet.getInt("current_stock");
                        int stockBefore = resultSet.getInt("stock_before");      
                        int restock = resultSet.getInt("restock");            
                        int sold = resultSet.getInt("sold");
                        Double revenue = resultSet.getDouble("revenue");  
                        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("id","ID"));
                        String formattedRevenue = currencyFormatter.format(revenue);
                        model.addRow(new Object[]{no++, code, name, currentStock, stockBefore, restock, sold,formattedRevenue});
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Table.setModel(model);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Title = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        ButtonSearch = new javax.swing.JButton();
        InputSearch = new javax.swing.JTextField();
        StartDate = new com.toedter.calendar.JDateChooser();
        EndDate = new com.toedter.calendar.JDateChooser();
        ButtonFilter = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        MenuHome = new javax.swing.JMenu();
        MenuUser = new javax.swing.JMenu();
        MenuProductCategory = new javax.swing.JMenu();
        MenuProduct = new javax.swing.JMenu();
        MenuTransaction = new javax.swing.JMenu();
        MenuReport = new javax.swing.JMenu();
        MenuLogOut = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Title.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Title.setText("INVENTORY REPORT");
        Title.setToolTipText("");

        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No", "Code", "Name", "Current Stock", "Stock Before", "Restock", "Sold Stock", "Revenue"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(Table);
        if (Table.getColumnModel().getColumnCount() > 0) {
            Table.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        ButtonSearch.setText("Search");
        ButtonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonSearchActionPerformed(evt);
            }
        });

        InputSearch.setToolTipText("Email");
        InputSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputSearchActionPerformed(evt);
            }
        });

        ButtonFilter.setText("Filter");
        ButtonFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonFilterActionPerformed(evt);
            }
        });

        jLabel1.setText("Start Date :");

        jLabel2.setText("End Date :");

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
        jMenuBar1.add(MenuHome);

        MenuUser.setText("User");
        MenuUser.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                MenuUserMenuSelected(evt);
            }
        });
        jMenuBar1.add(MenuUser);

        MenuProductCategory.setText("Product Category");
        MenuProductCategory.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                MenuProductCategoryMenuSelected(evt);
            }
        });
        jMenuBar1.add(MenuProductCategory);

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
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(InputSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ButtonSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ButtonFilter))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(23, 23, 23))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Title)
                                .addGap(184, 184, 184)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EndDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(StartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(StartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(EndDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(InputSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ButtonSearch))
                        .addGap(12, 12, 12))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ButtonFilter)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonSearchActionPerformed
        String keyword = InputSearch.getText();
        try {
            loadDataToTable(keyword,null,null);
        } catch (ParseException ex) {
            Logger.getLogger(ReportIndex.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ButtonSearchActionPerformed

    private void InputSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputSearchActionPerformed

    private void MenuUserMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_MenuUserMenuSelected
        UserIndex userIndex = new UserIndex();
        userIndex.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_MenuUserMenuSelected

    private void MenuHomeMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_MenuHomeMenuSelected
        Home home = new Home();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_MenuHomeMenuSelected

    private void MenuProductCategoryMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_MenuProductCategoryMenuSelected
        ProductCategoryIndex productCategoryIndex = new ProductCategoryIndex();
        productCategoryIndex.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_MenuProductCategoryMenuSelected

    private void ButtonFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonFilterActionPerformed
        
        try { 
        Date startDate = StartDate.getDate();
        Date endDate = EndDate.getDate();
        if (startDate != null && endDate != null){  
            startDateSQL = new java.sql.Date(startDate.getTime());
            endDateSQL = new java.sql.Date(endDate.getTime());
            String startDateStr = startDateSQL.toString();
            String endDateStr = endDateSQL.toString();
            loadDataToTable(null,startDateStr,endDateStr);
        } else {
            JOptionPane.showMessageDialog(this, "Start date and End date are required.");
        }       
        } catch (ParseException ex) {
            Logger.getLogger(ReportIndex.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ButtonFilterActionPerformed

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

    private void MenuProductMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_MenuProductMenuSelected
        ProductIndex productIndex = new ProductIndex();
        productIndex.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_MenuProductMenuSelected

    private void MenuTransactionMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_MenuTransactionMenuSelected
        TransactionIndex transactionIndex = new TransactionIndex();
        transactionIndex.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_MenuTransactionMenuSelected

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ReportIndex().setVisible(true);
                } catch (ParseException ex) {
                    Logger.getLogger(ReportIndex.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonFilter;
    private javax.swing.JButton ButtonSearch;
    private com.toedter.calendar.JDateChooser EndDate;
    private javax.swing.JTextField InputSearch;
    private javax.swing.JMenu MenuHome;
    private javax.swing.JMenu MenuLogOut;
    private javax.swing.JMenu MenuProduct;
    private javax.swing.JMenu MenuProductCategory;
    private javax.swing.JMenu MenuReport;
    private javax.swing.JMenu MenuTransaction;
    private javax.swing.JMenu MenuUser;
    private com.toedter.calendar.JDateChooser StartDate;
    private javax.swing.JTable Table;
    private javax.swing.JLabel Title;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
