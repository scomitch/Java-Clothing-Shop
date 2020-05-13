/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author 30246059
 */
public class Customer extends User{
    
    //Instance Variables
    private String addressLine1;
    private String addressLine2;
    private String town;
    private String postcode;
    private boolean isRegistered;
    private HashMap<Integer, Order> orders;
    
    //Properties.
    public String getAddressLine1() { return addressLine1; }
    public String getAddressLine2() { return addressLine2; }
    public String getTown() { return town; }
    public String getPostcode() { return postcode; }
    public boolean isIsRegistered() { return isRegistered; }
    public HashMap<Integer, Order> getOrders() { return orders; }

    public void setAddressLine1(String addressLine1) { this.addressLine1 = addressLine1; }
    public void setAddressLine2(String addressLine2) { this.addressLine2 = addressLine2; }
    public void setTown(String town) { this.town = town; }
    public void setPostcode(String postcode) { this.postcode = postcode; }
    public void setIsRegistered(boolean isRegistered) { this.isRegistered = isRegistered; }
    public void setOrders(HashMap<Integer, Order> orders) { this.orders = orders; }
    
    //Sets a greeting to be displayed to the customer.
    public String displayGreeting(){

        return "<html>Hello, " + getUsername() + "<br>You are Logged In as a customer</html>";
    }
    
    //Default Constructor()
    public Customer(){
        super();
        this.addressLine1 = "01 Default Street";
        this.addressLine2 = "Flat Default";
        this.town = "Default City";
        this.postcode = "D3 F4LT";
        this.isRegistered = true;
        orders = new HashMap();
    }
    
    //Overloaded Constructor.
    public Customer(String username, String password, String firstname, String lastname, String addressLine1, String addressLine2, String town, String postcode){
        super(username, password, firstname, lastname);
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.town = town;
        this.postcode = postcode;
        this.isRegistered = true;
        orders = new HashMap();
    }
    
    
    //Methods.
    
    //Finds the latest order from the customer.
    public Order findLatestOrder(){
        //Intantiates a new order.
        Order currentOrder = new Order();
        
        //Checks if the order list is empty.
        if(orders.isEmpty()){
            //If not add instantiated order to the hashmap.
            addOrder(currentOrder);
        } else {
            //orders isn't empty so iterate through each order.
            currentOrder = orders.entrySet().iterator().next().getValue();
            
            //For each order in the entryset.
            for(Map.Entry<Integer, Order> orderEntry : orders.entrySet()){
                //Create a new order class with the entry value.
                Order order = orderEntry.getValue();
                
                //Filter to find the most recent order.
                if(order.getOrderDate().after(currentOrder.getOrderDate())){
                    currentOrder = order;
                }
            }
            
            //Checks if the order is completed (which means a new order must be made)
            if(currentOrder.getOrderStatus().equals("Complete")){
                //Create a new order and add the new order.
                currentOrder = new Order();
                addOrder(currentOrder);
            }
        }
        //Return the current order.
        return currentOrder;
    }
    
    //Add a new order to the database.
    public void addOrder(Order o){
        //Init database class.
        DBUtil db = new DBUtil();
        //Get the order ID when a return is made from the insertion of a new order.
        int orderId = db.addOrder(o, this.getUsername());
        
        //Set the order ID to the current order.
        o.setOrderId(orderId);
        //Place the order into the orders hashmap.
        orders.put(orderId, o);
    }
}
