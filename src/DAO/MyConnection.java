/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class MyConnection {

    public static Connection getConnect() {
        Connection cn = null;
        try {
            // nap drive 
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://localhost:1433;databaseName=SE1427";
            
            cn=DriverManager.getConnection(url,"long","Goboi123");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Khong ket noi duoc du lieu");
        } 
        return cn;
    }
}
