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
public class Clothing extends Product{
    
    //Instance Variables
    private String measurement;
    
    //Properties
    public String getMeasurement() { return measurement; }
    public void setMeasurement(String measurement) { this.measurement = measurement; }
    
    //Overloaded Constructor (Excl ProductID)
    public Clothing(String productName, double price, int stockLevel, String measurement){
        super(productName, price, stockLevel);
        this.measurement = measurement;
    }
    
    //Overloaded Constructor (all)
    public Clothing(int productID, String productName, double price, int stockLevel, String measurement){
        super(productID, productName, price, stockLevel);
        this.measurement = measurement;
    }
}
