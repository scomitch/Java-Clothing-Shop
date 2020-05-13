/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author 30246059
 */
public class Order {
    
    //Instance Variables
    private int orderId;
    private Date orderDate;
    private double orderTotal;
    private String orderStatus;
    private HashMap<Integer, OrderLine> orderLines;
    
    //Properties.
    public int getOrderId() { return orderId; }
    public Date getOrderDate() { return orderDate; }
    public double getOrderTotal() { return orderTotal; }
    public String getOrderStatus() { return orderStatus; }
    public HashMap<Integer, OrderLine> getOrderLines() { return orderLines; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }
    public void setOrderTotal(double orderTotal) { this.orderTotal = orderTotal; }
    public void setOrderStatus(String orderStatus) { this.orderStatus = orderStatus; }
    public void setOrderLines(HashMap<Integer, OrderLine> orderLines) { this.orderLines = orderLines; }
    
    //Default Constructor.
    public Order() {
        this.orderId = -1;
        this.orderDate = new Date();
        this.orderTotal = 0.00;
        this.orderStatus = "Pending";
        this.orderLines = new HashMap();
    }
    
    //Overloaded Constructor.
    public Order(int orderId, Date orderDate, double orderTotal, String orderStatus) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderTotal = orderTotal;
        this.orderStatus = orderStatus;
        this.orderLines = new HashMap();
    }
    
    //Methods.
    
    //Method to calculate the OrderTotal.
    public void calculateOrderTotal(){
        //Init orderTotal as 0 to begin calculation.
        this.orderTotal = 0;
        
        //Loop through the OrderLines hashmap
        for(Map.Entry<Integer, OrderLine> olEntry : orderLines.entrySet()){
            //Create an OrderLine class from the entry
            OrderLine orderLine  = olEntry.getValue();
            //Calaulate OrderTotal from the line total.
            this.orderTotal = this.orderTotal + orderLine.getLineTotal();
        }
        
        //Update the Order Total on the database.
        DBUtil db = new DBUtil();
        db.updateOrderTotal(this.orderId, this.orderTotal);
    }
    
    //Method to add an orderLine.
    public void addOrderLine(OrderLine ol){
        //Init db.
        DBUtil db = new DBUtil();
        //Grab the OrderLineID from the Unique ID after inserting to database.
        int orderLineId = db.addOrderLine(ol, this.orderId);
        
        //Set the OrderLineID as the resultSet key (Unique ID from db)
        ol.setOrderLineId(orderLineId);
        //Add to hashmap.
        orderLines.put(orderLineId, ol);
        //Calculate the Order Total.
        calculateOrderTotal();
    }
    
    //Method to mark an order as complete.
    public void completeOrder(){
        //Set default val for orderStatus as "Complete"
        orderStatus = "Complete";
        
        //Call to the Database to complete the order.
        DBUtil db = new DBUtil();
        
        db.completeOrder(orderId);
        
        //Loop through the OrderLines hashmap.
        for(Map.Entry<Integer, OrderLine> olEntry : orderLines.entrySet()){
            OrderLine ol = olEntry.getValue();
            //Update the product quantity for the product when order is marked complete.
            db.updateProductQuantity(ol.getProduct().getProductID(), ol.getProduct().getStockLevel() - ol.getQuantity());
        }
    }
    
    //Method Remove an OrderLine.
    public void removeOrderLine(int productID){
        //Create an iterator to go through an orderLine.
        Iterator<Map.Entry<Integer, OrderLine>> iter = orderLines.entrySet().iterator();
        
        //Loop through the iterator.
        while(iter.hasNext()){
            
            //Grab an OrderLine and create a class.
            Map.Entry<Integer, OrderLine> olEntry = iter.next();
            OrderLine ol = olEntry.getValue();
            
            //If the product ID of the OrderLine matches the productID 
            if(ol.getProduct().getProductID() == productID)
            {
                //Remove from the iterator
                iter.remove();
                
                //Delete the order line from the db
                DBUtil db = new DBUtil();
                db.deleteOrderLine(ol.getOrderLineId());
                
                //Recalculate the order total.
                calculateOrderTotal();
            }
        }
    }
    
    //Method to find a product in a basket.
    public Optional<OrderLine> findProductInBasket(int productID)
    {
        //init Optional class to avoid nullPointers.
        Optional<OrderLine> orderLineWithProduct = Optional.empty();

        //Loop through order Lines.
        for(Map.Entry<Integer, OrderLine> olEntry : orderLines.entrySet())
        {
            //Make an orderLine class with each entry.
            OrderLine orderLine = olEntry.getValue();

            //If the product ID matches the paramter ID then
            if(orderLine.getProduct().getProductID() == productID)
            {
                //Assign the OrderLine to the OrderLineWithProduct
               orderLineWithProduct =  Optional.of(orderLine);
            }
        }
        //Return orderLineWithProduct.
        return orderLineWithProduct;
    }
    
    
    
    
    
    
}
