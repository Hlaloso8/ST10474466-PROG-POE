/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginchat;

/**
 *
 * @author Hlaloso
 */
public class userName {
    public boolean checkUsername(String username) {
        return username.length() <= 5 && username.contains("_");
    }
    
}
