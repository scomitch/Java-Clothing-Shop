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
public class Staff extends User {
    
    //Instance Variables.
    private String position;
    private double salary;
    
    //Properties.
    public String getPosition() { return position; }
    public double getSalary() { return salary; }
    public void setPosition(String position) { this.position = position; }
    public void setSalary(double salary) { this.salary = salary; } 

    //Method to display a staff greeting.
    public String displayGreeting(){

        return "<html>Hello, " + getUsername() + "<br>You are Logged In as a staff member</html>";
    }
    
    //Default Constructor
    public Staff(){
        super();
        this.position = "Staff";
        this.salary = 0.0;
    }
    
    //Overloaded Constructor
    public Staff(String username, String password, String firstname, String lastname, String position, double salary){
        super(username, password, firstname, lastname);
        this.position = position;
        this.salary = salary;
    }
}
