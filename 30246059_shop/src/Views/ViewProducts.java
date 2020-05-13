/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Models.Customer;
import Models.DBUtil;
import Models.Order;
import Models.OrderLine;
import Models.Product;
import Models.Staff;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.swing.DefaultListModel;

/**
 *
 * @author 30246059
 */
public class ViewProducts extends javax.swing.JFrame {

    //Global vars for {loggedCustomer, products & currentOrder}
    private Customer loggedCustomer;
    private HashMap<Integer, Product> products;
    private Order currentOrder;
    /**
     * Creates new form MainMenu
     * @param customer
     */
    public ViewProducts(Customer customer) {
        //Initialize the global vars with passed parameters.
        loggedCustomer = customer;
        initComponents();

        //Init db and load products.
        DBUtil db = new DBUtil();
        products = db.loadProducts();

        //If the user is registered then display a greeting and grab the current order.
        if(loggedCustomer.isIsRegistered() == true){
            lblGreeting.setText(loggedCustomer.displayGreeting());
            currentOrder = loggedCustomer.findLatestOrder();
        } else {
            //If it's a guest just display a greeting and remove the view basket button.
            lblGreeting.setText("Hello Guest.");
            btnViewBasket.setVisible(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblCustomerHome = new javax.swing.JLabel();
        lblGreeting = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblError = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listCat = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        listProd = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        btnBasket = new javax.swing.JButton();
        btnViewBasket = new javax.swing.JButton();
        txtQuantity = new javax.swing.JTextField();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(700, 700));

        lblCustomerHome.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        lblCustomerHome.setForeground(new java.awt.Color(153, 153, 153));
        lblCustomerHome.setText("View Products");

        lblError.setForeground(new java.awt.Color(255, 51, 51));

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("Select a Category:");

        listCat.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Clothing", "Footwear" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listCat.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listCatValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(listCat);

        jScrollPane2.setViewportView(listProd);

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setText("Select a Product:");

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setText("Quantity:");

        btnBasket.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnBasket.setForeground(new java.awt.Color(153, 153, 153));
        btnBasket.setText("Add to Basket");
        btnBasket.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(153, 153, 153), null, null));
        btnBasket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBasketActionPerformed(evt);
            }
        });

        btnViewBasket.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnViewBasket.setForeground(new java.awt.Color(153, 153, 153));
        btnViewBasket.setText("View Basket");
        btnViewBasket.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(153, 153, 153), null, null));
        btnViewBasket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewBasketActionPerformed(evt);
            }
        });

        btnBack.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        btnBack.setForeground(new java.awt.Color(153, 153, 153));
        btnBack.setText("Back");
        btnBack.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(153, 153, 153), null, null));
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                        .addComponent(btnViewBasket, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblCustomerHome)
                        .addGap(217, 217, 217))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblError)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnBasket, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(253, 253, 253))))
            .addGroup(layout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addComponent(lblGreeting)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCustomerHome)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblGreeting)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addComponent(lblError))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBasket, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(237, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(btnViewBasket, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Check the basket.
    private void btnBasketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBasketActionPerformed
        //Validation to make sure the loggedCustomer is registered.
        if(loggedCustomer.isIsRegistered() == true){
            //Validation to make sure there is a selected product in the Model.
            if(listProd.getSelectedIndex() == -1){
                lblError.setText("Please select a product to add to your basket.");
            } else {
                //Cast the selected value to an object and then forth to a Product class.
                Object selectedObject = (Object)listProd.getSelectedValue();
                Product selectedProduct = (Product)selectedObject;

                //Create optional orderLine with the product in the basket.
                Optional<OrderLine> orderLineWithProduct = currentOrder.findProductInBasket(selectedProduct.getProductID());

                //If there is no orderLine then we need to create one.
                if(!orderLineWithProduct.isPresent())
                {
                    //Check to see if the quantity is a number.
                    boolean numeric = isNumeric(txtQuantity.getText());
                    if(numeric){
                        //If there is a number and the quantity is between 1 and 99 (To avoid abuse)
                        if(Integer.parseInt(txtQuantity.getText()) > 0 && Integer.parseInt(txtQuantity.getText()) < 99){
                            //If the quantity is greater than the stock level then return an error.
                            if(Integer.parseInt(txtQuantity.getText()) > selectedProduct.getStockLevel()){
                                lblError.setText("Quantity exceeds stock level");
                                return;
                            }
                            //Else create a new orderLine with the selected product.
                            OrderLine newOrderLine = new OrderLine(0, selectedProduct, Integer.parseInt(txtQuantity.getText()), Integer.parseInt(txtQuantity.getText()) * selectedProduct.getPrice());
                            //Add the order line to their current Order.
                            currentOrder.addOrderLine(newOrderLine);
                            lblError.setText("Product Added to Basket");
                        } else {
                            lblError.setText("Quantity must be between 1 and 99");
                        }
                        
                    } else {
                        lblError.setText("Error. Quantity must be a number");
                    }
                    
                }
                //If there is an orderLine then there has to be a continuation.
                else
                {
                    //Init an OrderLine class by getting the current orderLine with the product.
                    OrderLine actualOrderLineWithProduct = orderLineWithProduct.get();

                    //Check to see if the quantity is numeric.
                    boolean numeric = isNumeric(txtQuantity.getText());
                    if(numeric){
                        //Make sure the quantity is between 1 and 99 (to avoid abuse)
                        if(Integer.parseInt(txtQuantity.getText()) > 0 && Integer.parseInt(txtQuantity.getText()) < 99){
                            //Declare vars to hold integer values (For easier debugging)
                            int quantity = actualOrderLineWithProduct.getQuantity();
                            int txtboxquantity = Integer.parseInt(txtQuantity.getText());
                            int stockLevel = selectedProduct.getStockLevel();
                            //If the current stock level of the product is greater than what is being requested to be added.
                            //Then it means there is enough stock for the product to be processed.
                            if ((stockLevel) >= (txtboxquantity + quantity)) {
                                //Calculate the new quantity
                                int newQuantity = txtboxquantity + quantity;

                                //Calculate the new lineTotal.
                                double lineTotal = actualOrderLineWithProduct.getLineTotal() + (txtboxquantity * selectedProduct.getPrice());

                                //Set the new line total and quantity for the order.
                                actualOrderLineWithProduct.setLineTotal(lineTotal);
                                actualOrderLineWithProduct.setQuantity(txtboxquantity + actualOrderLineWithProduct.getQuantity());

                                //Init the db and edit the order line (with the new addition)
                                DBUtil db = new DBUtil();
                                db.editOrderLine(actualOrderLineWithProduct);

                                //Calculate the order total again to update.
                                currentOrder.calculateOrderTotal();
                                lblError.setText("Product Added to Basket");
                            } else //If CANNOT add to basket
                            {
                                lblError.setText("Stock Level Not Available");
                            }
                        } else {
                            lblError.setText("Quantity must be between 1 and 99");
                        }
                        
                    } else {
                        lblError.setText("Error. Quantity must be a number");
                    }
            }
            }
        } else {
            //This means the user requesting an addition isn't registered so nothing should happen.
            //Prompt them to log in to continue.
            CustomerLogin login = new CustomerLogin();
            this.dispose();
            login.setVisible(true);
            loggedCustomer = null;
        }
    }//GEN-LAST:event_btnBasketActionPerformed

    private void btnViewBasketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewBasketActionPerformed
        //Make sure the user is registered before continuing.
        if(loggedCustomer.isIsRegistered() == true){
            //Frame transfer to the View Basket frame.
            ViewBasket basket = new ViewBasket(loggedCustomer);
            this.dispose();
            basket.setVisible(true);
        } else {
            //Frame transfer to the Customer Login page (user doesn't have privs to access)
            CustomerLogin login = new CustomerLogin();
            this.dispose();
            login.setVisible(true);
            loggedCustomer = null;
        }
    }//GEN-LAST:event_btnViewBasketActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        //Make sure the user is registered before continuing.
        if(loggedCustomer.isIsRegistered() == true){
            //Frame transfer to the Customer Home frame.
            CustomerHome cusHome = new CustomerHome(loggedCustomer);
            this.dispose();
            cusHome.setVisible(true);
        } else {
            //Frame transfer to the Customer Login page (user doesn't have privs to access)
            CustomerLogin login = new CustomerLogin();
            this.dispose();
            login.setVisible(true);
            loggedCustomer = null;
        }
    }//GEN-LAST:event_btnBackActionPerformed

    private void listCatValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listCatValueChanged
        //Create a new DefaultListModel for the products.
        DefaultListModel productList = new DefaultListModel();

        //Get the selected categories.
        String selectedCat = listCat.getSelectedValue();

        //Loop through the products.
        for(Map.Entry<Integer, Product> prodEntry : products.entrySet()){
            //Create a product class for each entry.
            Product product = prodEntry.getValue();

            //If the product equals either Clothing or Footwear AND it has stock, display in the ListModel.
            if(product.getClass().getName().equals("Models." + selectedCat)){
                if(product.getStockLevel() > 0){
                    productList.addElement(product);
                }
            }
        }
        
        listProd.setModel(productList);
    }//GEN-LAST:event_listCatValueChanged

    public static boolean isNumeric(String num){
        try {
            double d = Double.parseDouble(num);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default looklblMainMenuel.
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
            java.util.logging.Logger.getLogger(ViewProducts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewProducts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewProducts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewProducts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                //new CustomerHome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnBasket;
    private javax.swing.JButton btnViewBasket;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblCustomerHome;
    private javax.swing.JLabel lblError;
    private javax.swing.JLabel lblGreeting;
    private javax.swing.JList<String> listCat;
    private javax.swing.JList<String> listProd;
    private javax.swing.JTextField txtQuantity;
    // End of variables declaration//GEN-END:variables
}
