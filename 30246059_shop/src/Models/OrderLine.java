/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author 30246059
 */
public class OrderLine {
    
    //Instance Variables.
    private int orderLineId;
    private Product product;
    private int quantity;
    private double lineTotal;
    
    //Properties.
    public int getOrderLineId() { return orderLineId; }
    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public double getLineTotal() { return lineTotal; }
    public void setOrderLineId(int orderLineId) { this.orderLineId = orderLineId; }
    public void setProduct(Product product) { this.product = product; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setLineTotal(double lineTotal) { this.lineTotal = lineTotal; }
    
    //Overloaded Constructor.
    public OrderLine(int orderLineId, Product product, int quantity) {
        this.orderLineId = orderLineId;
        this.product = product;
        this.quantity = quantity;
        this.lineTotal = product.getPrice() + quantity;
    }
    
    public OrderLine(int orderLineId, Product product, int quantity, double LineTotalIn){
        this.orderLineId = orderLineId;
        this.product = product;
        this.quantity = quantity;
        this.lineTotal = LineTotalIn;
    }
    
    
}
