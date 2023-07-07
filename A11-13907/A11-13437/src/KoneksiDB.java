
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

    public static Connection getConn() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/a11-13437", "root", "");
//            JOptionPane.showMessageDialog(null, "Koneksi Berhasil");
        } catch (SQLException ex) {
            Logger.getLogger(KoneksiDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    public static void main(String[] args) {
        getConn();
    }
}
