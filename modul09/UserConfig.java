/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2.modul09;


import java.io.Serializable;
/**
 *
 * @author zan
 */
public class UserConfig implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String username;
    private int fontSize;
    
    // Constructor
    public UserConfig(String username, int fontSize) {
        this.username = username;
        this.fontSize = fontSize;
    }
    
    // Getter dan Setter
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public int getFontSize() {
        return fontSize;
    }
    
    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }
    
    @Override
    public String toString() {
        return "UserConfig{" +
                "username='" + username + '\'' +
                ", fontSize=" + fontSize +
                '}';
    }
}