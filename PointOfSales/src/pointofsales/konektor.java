/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pointofsales;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
/**
 *
 * @author ACER
 */
public class konektor {
    static Connection mysqlconfig;

    public static Connection koneksi() {
        
        try {
            String url = "jdbc:mysql://localhost:3306/pointofsales";
            String user = "root";
            String pass = "";
            
            mysqlconfig = DriverManager.getConnection(url, user, pass);
            return mysqlconfig;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Tidak dapat terhubung ke Database: " + e.getMessage());
            throw new RuntimeException("Tidak dapat terhubung ke Database", e);
        }
    }
    
    public ResultSet getResultData(String query) {
        try {
            Connection connection = koneksi();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
