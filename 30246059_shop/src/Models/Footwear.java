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
public class Footwear extends Product{
    
    //Instance Variables.
    private int size;
    
    //Properties.
    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }
    
    //Overloaed Constructor(Exclude ProductID)
    public Footwear(String productName, double price, int stockLevel, int size){
        super(productName, price, stockLevel);
        this.size = size;
    }
    
    //Overloaded Constructor (all)
    public Footwear(int productID, String productName, double price, int stockLevel, int size){
        super(productID, productName, price, stockLevel);
        this.size = size;
    }
    
    
}
