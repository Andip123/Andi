/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author D2A_02
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class KoneksiDB {
    public static Connection conn;
    public static Statement stm;
    public String usr;
    
    public KoneksiDB(String usr){
        try {
            this.usr = usr;
            conn=DriverManager.getConnection("jdbc:mysql://localhost/andiprabandaru", "root", "");
            stm=conn.createStatement();
            JOptionPane.showMessageDialog(null, "Koneksi Berhasil");
        } catch (SQLException ex) {
            Logger.getLogger(KoneksiDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public static Connection getConn(){
        return conn;
    }
    public static void main(String[]args){
        KoneksiDB koneksiDB=new KoneksiDB("andxiprabandaru");
    }
}
