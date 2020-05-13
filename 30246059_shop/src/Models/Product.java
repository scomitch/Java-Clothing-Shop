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
public class Product {
    
    //Instance Variables for Product Class.
    private int productID;
    private String productName;
    private double price;
    private int stockLevel;
    
    //Properties for Product Class.
    public int getProductID() { return productID; }
    public String getProductName() { return productName; }
    public double getPrice() { return price; }
    public int getStockLevel() { return stockLevel; }

    public void setProductID(int productID) { this.productID = productID; }
    public void setProductName(String productName) { this.productName = productName; }
    public void setPrice(double price) { this.price = price; }
    public void setStockLevel(int stockLevel) { this.stockLevel = stockLevel; }
    

    //toString override setting a display message for the product.
    @Override
    public String toString()
    {
        String display = productName + " (£" + price + ")";
        return display;
    }
    //Default Constructor for Product Class.
    public Product(){
        this.productID = 0;
        this.productName = "";
        this.price = 0.0;
        this.stockLevel = 0;
    }
    
    //Overloaded Constructor (productID excl)
    public Product(String productName, double price, int stockLevel) {
        this.productName = productName;
        this.price = price;
        this.stockLevel = stockLevel;
    }
    
    //Overloaded Constructor (full)
    public Product(int productID, String productName, double price, int stockLevel) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.stockLevel = stockLevel;
    }
    
    
    
}
