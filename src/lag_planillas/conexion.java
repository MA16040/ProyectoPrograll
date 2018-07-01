package lag_planillas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class conexion {

    private final String url = "jdbc:mysql://localhost:3306/planillas";
    private final String login = "root";
    private final String password = "programacion2";
    private Connection cnx = null;
    private Statement sttm = null;
    private ResultSet rst = null;

    public void UID(String sql) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cnx = DriverManager.getConnection(url, login, password);
            sttm = cnx.createStatement();
            sttm.executeUpdate(sql);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            System.exit(1);
        }
    }

    public ResultSet getValores(String sql) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cnx = DriverManager.getConnection(url, login, password);
            sttm = cnx.createStatement();
            rst = sttm.executeQuery(sql);
        } catch (ClassNotFoundException | SQLException c) {
            JOptionPane.showMessageDialog(null, "Error: " + c.getMessage());
            System.exit(1);
        } finally {
            return rst;
        }
    }

}
