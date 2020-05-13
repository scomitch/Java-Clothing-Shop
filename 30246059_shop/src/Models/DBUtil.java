/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author 30246059
 */
public class DBUtil {
    
    //Instance Variables for DB Connection
    private static final String LOCATION = "jdbc:ucanaccess://data\\Database.accdb";
    private static Connection con;
    
    //Grab Admins.
    public static HashMap<String, Staff> loadStaff(){
        //Create a new hashmap of staff.
        HashMap<String, Staff> staff = new HashMap();
        try {
            //Create connection strings and query.
            con = DriverManager.getConnection(LOCATION);
            Statement stmt = con.createStatement();
            String query = "SELECT * FROM Staff";
            //Init resultset for the query result.
            ResultSet result = stmt.executeQuery(query);
            
            //Loop through the results and create a staff class for each employee entry.
            while(result.next()){
                String forename = result.getString("UserForename");
                String surname = result.getString("UserSurname");
                String position = result.getString("UserPosition");
                double salary = result.getDouble("UserSalary");
                String pass = result.getString("UserPin");
                String username = result.getString("Username");
                boolean isStaff = result.getBoolean("UserStaff");
                
                Staff employee = new Staff(username, pass, forename, surname, position, salary);
                //Insert the staff into the hashmap.
                staff.put(username, employee);
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Error: " +  e);
        } finally {
            //Return the hashmap of staff.
            return staff;
        }
    }
    
    //Grab the Connection.
    public static Connection getConnection(){
        try {
            con = DriverManager.getConnection(LOCATION);
        } catch (SQLException e) {
            System.out.println("ERROR: " + e);
            return null;
        }
        return con;
    }
    
    //Custom manipulation method for the database.
    public static void manipulateDatabase(String query){
        try {
            con = DriverManager.getConnection(LOCATION);
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }
    
    //Custom query method for the database.
    public static ArrayList<String> queryDatabase(Connection con, String query, String table){
        ArrayList<String> resultList = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(query);
            
            while(result.next()){
                resultList.add(result.getString(table));
            }
            
        } catch (SQLException e) {
            System.out.println("ERROR: " + e);
            return null;
        }
        return resultList;
    }
    
    //Compare and log in an administrator.
    public static Staff staffLogin(String username, String password){
        HashMap<String, Staff> staff = loadStaff();
        
        if(staff.containsKey(username)){
           Staff foundStaff = staff.get(username);
           if(foundStaff.getPassword().equals(password)){
               //Correct Password is Present.
               return foundStaff;
           } else {
               return null;
           }
        } else {
           return null; 
        }
    }
    
    public boolean registerCustomer(Customer customer){
        try {
            //Create Connection String
            con = DriverManager.getConnection(LOCATION);
            Statement stmt = con.createStatement();
            String check = "SELECT * FROM Users WHERE Username = '" + customer.getUsername() + "'";
            ResultSet rs = stmt.executeQuery(check);
            //If no usernames exist with the current input then insert the new users into the database.
            if(!rs.next()){
                String query = "INSERT INTO Users (Username, UserPin, UserForename, UserSur, UserAddress1, UserAddress2, UserTown, UserPostcode, UserRegistered) VALUES ('" + customer.getUsername() + "', '" + customer.getPassword() + "', '" + customer.getFirstname() + "', '" + customer.getLastname() + "', '" + customer.getAddressLine1() + "', '" + customer.getAddressLine2() + "', '" + customer.getTown() + "','" + customer.getPostcode() + "', '" + true + "') ";
                System.out.println(query);
                stmt.executeUpdate(query);
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Error registering customer to db. Err: " + ex);
            return false;
        }
    }
    
    //Grab Admins.
    public HashMap<String, Customer> loadCustomers(){
        //Create a new hashmap of customers.
        HashMap<String, Customer> customers = new HashMap();
        try {
            //Init database query and string
            con = DriverManager.getConnection(LOCATION);
            Statement stmt = con.createStatement();
            String query = "SELECT * FROM Users WHERE UserRegistered = 1";
            ResultSet result = stmt.executeQuery(query);
            //Loop through result set and create a customer class for each entry and add it to the hashmap.
            while(result.next()){
                String userID = result.getString("Username");
                String forename = result.getString("UserForename");
                String surname = result.getString("UserSur");
                String pass = result.getString("UserPin");
                String username = result.getString("Username");
                boolean isRegistered = result.getBoolean("UserRegistered");
                String address1 = result.getString("UserAddress1");
                String address2 = result.getString("UserAddress2");
                String postcode = result.getString("UserPostcode");
                String town = result.getString("UserTown");
                
                Customer customer = new Customer(username, pass, forename, surname, address1, address2, town, postcode);
                customers.put(username, customer);

            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: " +  e);
        } finally {
            //Load all lines and order from the customer.
            customers = loadCustomerOrders(customers);
            customers = loadCustomerOrderLines(customers);
            return customers;
        }
    }
    
    //Method to load all products to the program.
    public HashMap<Integer, Product> loadProducts(){
        //Init a hashmap of empty products.
        HashMap<Integer, Product> products = new HashMap();
        try {
            //Connect and query the database.
            con = DriverManager.getConnection(LOCATION);
            Statement stmt = con.createStatement();
            String query = "SELECT * FROM Products";
            ResultSet rs = stmt.executeQuery(query);
            
            //Default values for the extra attributes.
            String measurement = "";
            int size = 0;
            
            //Loop through the resultset and create a product class.
            while(rs.next()){
                int productID = rs.getInt("ProductId");
                String name = rs.getString("ProductName");
                double cost = rs.getDouble("Price");
                int stockLevel = rs.getInt("StockLevel");
                measurement = rs.getString("Measurement");
                size = rs.getInt("Size");
                
                //Specify what type of product the resultSet entry is by checking if it has a size.
                if(size == 0){
                    //It is clothing.
                    Clothing clothing = new Clothing(productID, name, cost, stockLevel, measurement);
                    products.put(productID, clothing);
                } else {
                    //It is footwear (size will == null)
                    Footwear footwear = new Footwear(productID, name, cost, stockLevel, size);
                    products.put(productID, footwear);
                }
                
            }
        } catch (Exception e) {
            System.out.println("Error loading products");
        } finally {
            //Return products.
            return products;
        }
    }
    
    //Method to load all customer orders to the database.
    public HashMap<String, Customer> loadCustomerOrders(HashMap<String, Customer> customers) {
        try {
            //Init and query the database to get all orders.
            con = DriverManager.getConnection(LOCATION);
            Statement stmt = con.createStatement();
            String query = "SELECT * FROM Order";
            ResultSet rs = stmt.executeQuery(query);

            //Loop through result set and create an order class for each responding order.
            while (rs.next()) {
                int orderId = rs.getInt("OrderID");
                Date orderDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("OrderDate"));
                String customerName = rs.getString("Customer");
                double orderTotal = rs.getDouble("OrderTotal");
                String status = rs.getString("OrderStatus");
                
                Order order = new Order(orderId, orderDate, orderTotal, status);
                
                //If the customer name matches with a customer on the hashmap then add an order to that specific customer class.
                if(customers.containsKey(customerName)){
                    Customer cusOrder = customers.get(customerName);
                    cusOrder.getOrders().put(orderId, order);
                }
            }
        } catch (Exception e) {
            System.out.println("Unable to load customer orders from db");
        } finally {
            //Return the customers.
            return customers;
        }
    }
    
    //Load the customer order lines.
    public HashMap<String, Customer> loadCustomerOrderLines(HashMap<String, Customer> customers){
        
        //Load the products into a new hashmap.
        HashMap<Integer, Product> products = loadProducts();
        
        try {
            //Init and query the database.
            con = DriverManager.getConnection(LOCATION);
            Statement stmt = con.createStatement();
            String query = "SELECT * FROM OrderLine";
            ResultSet rs = stmt.executeQuery(query);
            
            //Loop through the result sets and create an orderline class for each result.
            while(rs.next()){
                int orderLineID = rs.getInt("OrderLineID");
                int productID = rs.getInt("ProductID");
                int quantity = rs.getInt("Quantity");
                double lineTotal = rs.getDouble("LineTotal");
                int orderId = rs.getInt("OrderID");
                
                //If an orderLine product ID matches with a hashmap product, connect them.
                if(products.containsKey(productID)){
                    //Grab the specific product class from the products hashmap.
                    Product product = products.get(productID);
                    
                    //Create a new OrderLine with the result set information
                    OrderLine ol = new OrderLine(orderLineID, product, quantity, lineTotal);
                    
                    //Loop through the customers and if the OrderID matches with the customers, connect them.
                    for(Map.Entry<String, Customer> cusEntry : customers.entrySet()){
                        Customer customer = cusEntry.getValue();
                        if(customer.getOrders().containsKey(orderId)){
                            Order orderWithOrderLine = customer.getOrders().get(orderId);
                            orderWithOrderLine.getOrderLines().put(orderLineID, ol);
                        }
                    }
                }
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Unable to load orderlines from db");
        } finally {
            //Return customers.
            return customers;
        }
    }
    
    //Customer Login method.
    public Customer customerLogin(String username, String password){
        //Load customers and add to a hashmap.
        HashMap<String, Customer> customers = loadCustomers();
        
        if(customers.containsKey(username)){
           Customer foundCustomer = customers.get(username);
           if(foundCustomer.getPassword().equals(password)){
               //Correct Password is Present.
               return foundCustomer;
           } else {
               return null;
           }
        } else {
           return null; 
        }
    }
    
    //Insert Order Data.
    public int addOrder(Order order, String customerEmail){
        //Init default value of 0.
        int orderId = 0;
        
        try {
            //Init and query the database.
            con = DriverManager.getConnection(LOCATION);
            Statement stmt = con.createStatement();
            String query = "INSERT INTO Order (OrderDate, Customer, OrderTotal, OrderStatus) VALUES ('" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(order.getOrderDate()) + "','" + customerEmail + "','" + order.getOrderTotal() + "','" + order.getOrderStatus() + "')";
            stmt.executeUpdate(query);
            //Grab the unique key from the insertion.
            ResultSet rs = stmt.getGeneratedKeys();
            
            //If the insertion worked make orderId the uniqueID.
            if(rs.next()){
                orderId = rs.getInt(1);
            }
            
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        
        //Return the Order Id.
        return orderId;
    }
    
    //Method to add an orderLine to the class.
    public int addOrderLine(OrderLine orderLine, int orderID){
        //Init default value for orderLineId.
        int orderLineId = 0;
        
        try {
            //Init and query database.
            con = DriverManager.getConnection(LOCATION);
            Statement stmt = con.createStatement();
            String query = "INSERT INTO OrderLine (ProductID, Quantity, LineTotal, OrderID) VALUES ('" + orderLine.getProduct().getProductID()+ "','" + orderLine.getQuantity() + "','" + orderLine.getLineTotal() + "','" +  orderID + "')";
            stmt.executeUpdate(query);
            //Grab the Unique ID from the database.
            ResultSet rs = stmt.getGeneratedKeys();
            //If the insertion worked place the unique key to the orderLineId Default val.
            if(rs.next()){
                orderLineId = rs.getInt(1);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        
        //Return the orderLineId.
        return orderLineId;
    }
    
    //Method to update the order total.
    public void updateOrderTotal(int orderID, double newTotal){
        //Init and query the database with the new Order Totals.
        try {
            con = DriverManager.getConnection(LOCATION);
            Statement stmt = con.createStatement();
            String query = "UPDATE Order SET OrderTotal = '" + newTotal + "' WHERE OrderID = \"" + orderID + "\"";
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }

    }
    
    //Method to edit a product.
    public void editProduct(Product product){
        //Default Values for the extra attributes.
        String measurement = "";
        int size = 0;
        
        //Determine if the product is Clothing or Footwear using class reflection.
        if(product.getClass().getName().equals("Models.Clothing")){
            Clothing clothing = (Clothing)product;
            measurement = String.valueOf(clothing.getMeasurement());
        } else {
            Footwear footwear = (Footwear)product;
            size = footwear.getSize();
        }
        
        //Init and query the database, updating the products with the new product information.
        try {
            con = DriverManager.getConnection(LOCATION);
            Statement stmt = con.createStatement();
            String query = "UPDATE Products SET ProductName = '" + product.getProductName() + "', Price = '" + product.getPrice() + "', StockLevel = '" + product.getStockLevel() + "', Measurement = '" + measurement + "', Size = '" + size + "' WHERE ProductId = '" + product.getProductID() + "'";
            stmt.executeUpdate(query);
        } catch (Exception e) {
            System.out.println("Error updating product to db");
        }
    }
    
    //Method to edit an orderLine.
    public void editOrderLine(OrderLine ol){
        try {
            //Init and query the db with new OrderLine information.
            con = DriverManager.getConnection(LOCATION);
            Statement stmt = con.createStatement();
            String query = "UPDATE OrderLine SET Quantity = '" + 1 + "', " + "LineTotal = '" + ol.getLineTotal() + "' " + "WHERE OrderLineID = '" + ol.getOrderLineId() + "'";
            stmt.executeUpdate(query);
        } catch (Exception ex) {
            System.out.println("Error updating order line to db: " + ex.getMessage());
        }
    }
    
    //Method to edit a customer.
    public void editCustomer(Customer customer){
        try {
            //Init and query the db with new Customer information.
            con = DriverManager.getConnection(LOCATION);
            Statement stmt = con.createStatement();
            String query = "UPDATE Users SET UserName = '" + customer.getUsername() + "', UserPin = '" + customer.getPassword() + "', UserForename = '" + customer.getFirstname() + "', UserSur = '" + customer.getLastname() + "', UserAddress1 = '" + customer.getAddressLine1() + "', UserAddress2 = '" + customer.getAddressLine2() + "', UserPostcode = '" + customer.getPostcode() + "', UserTown = '" + customer.getTown() + "' WHERE UserName = '" + customer.getUsername() + "'";
            stmt.executeUpdate(query);
        } catch (Exception ex) {
            System.out.println("Error updating customer to db: " + ex.getMessage());
        }
    }
    
    //Method to delete a customer.
    public void deleteCustomer(int customerName){
        try {
            //Init and query the db to delete a Customer.
            con = DriverManager.getConnection(LOCATION);
            Statement stmt = con.createStatement();
            String query = "DELETE FROM Users WHERE Username = '" + customerName + "'";
            stmt.executeUpdate(query);
        } catch (Exception ex){
            System.out.println("Error deleting user from db");
        }
    }
    
    //Method to delete a product.
    public void deleteProduct(int productId){
        try {
            //Init and query the db to delete a product.
            con = DriverManager.getConnection(LOCATION);
            Statement stmt = con.createStatement();
            String query = "DELETE FROM Products WHERE ProductId = '" + productId + "'";
            System.out.println(query);
            stmt.executeUpdate(query);
        } catch (Exception ex){
            System.out.println("Error deleting product from db err: " + ex);
        }
    }
    
    //Method to delete an orderLine
    public void deleteOrderLine(int orderLineID){
        try {
            //Init and query the db to delete an OrderLine
            con = DriverManager.getConnection(LOCATION);
            Statement stmt = con.createStatement();
            System.out.println("Deleting Order ID " + orderLineID);
            String query = "DELETE FROM OrderLine WHERE OrderLineID = '" + orderLineID + "'";
            System.out.println(query);
            stmt.executeUpdate(query);
        } catch (Exception e) {
            System.out.println("Error deleting orderline from DB");
        }
    }
    
    //Method to mark an order as completed.
    public void completeOrder(int orderId){
        try {
            //Init and query the DB to mark an Order as completed.
            con = DriverManager.getConnection(LOCATION);
            Statement stmt = con.createStatement();
            String query = "UPDATE Order SET OrderStatus = 'Complete' WHERE OrderID = '" + orderId + "'";
            stmt.executeUpdate(query);
        } catch (Exception e) {
            System.out.println("Error completing order in db");
        }
    }
    
    //Method to update a product Quantity.
    public void updateProductQuantity(int productID, int newQuantity){
        try {
            //Init and query the db to set the StockLevel of a product to a defined value.
            con = DriverManager.getConnection(LOCATION);
            Statement stmt = con.createStatement();
            String query = "UPDATE Products SET StockLevel = '" + newQuantity + "' WHERE ProductId = '" + productID + "'";
            stmt.executeUpdate(query);
        } catch (Exception e) {
            System.out.println("Error updating quantity");
        }
    }
    
    //Method to delete a user
    public void deleteUser(String username){
        try {
            //Init and query the db to delete a user.
            con = DriverManager.getConnection(LOCATION);
            Statement stmt = con.createStatement();
            String query = "DELETE FROM Users WHERE Username = '" + username + "'";
            stmt.executeUpdate(query);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    
    //Method to add a product.
    public void addProduct(Product product){
        //Init default values.
        String measurement = "";
        int size = 0;
        
        //Check to see what product type needs to be added through class reflection.
        if(product.getClass().getName().equals("Models.Clothing")){
            Clothing clothing = (Clothing)product;
            measurement = String.valueOf(clothing.getMeasurement());
        } else {
            Footwear footwear = (Footwear)product;
            size = footwear.getSize();
        }
        
        try {
            //Init and query the db to add a product.
            con = DriverManager.getConnection(LOCATION);
            Statement stmt = con.createStatement();
            String query = "INSERT INTO Products (ProductName, Price, StockLevel, Measurement, Size) VALUES ('" + product.getProductName() + "', '" + product.getPrice() + "', '" + product.getStockLevel() + "', '" + measurement + "', '" + size + "')";
            System.out.println(query);
            stmt.executeUpdate(query);
        } catch (Exception ex) {
            System.out.println("Error adding product to db err: " + ex.getMessage());
        }
    }
}
