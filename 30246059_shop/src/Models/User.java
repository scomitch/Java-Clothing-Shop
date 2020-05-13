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
public class User {
    
    //Instance Variables for User Class.
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    
    //Properties for User Class.
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getFirstname() { return firstname; }
    public String getLastname() { return lastname; }
    
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setFirstname(String firstname) { this.firstname = firstname; }
    public void setLastname(String lastname) { this.lastname = lastname; }
    
    //Default Constructor for User Class
    public User() {
        this.username = "Guest";
        this.password = "";
        this.firstname = "";
        this.lastname = "";
    }
    
    //Overloaded Constructor for User Class.
    public User(String username, String password, String firstname, String lastname) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }
    
    
}
