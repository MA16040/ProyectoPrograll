package lag_planillas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Conexion {
    
    private String error = null;
    
    private String url = "jdbc:mysql://127.0.0.1:3306/planillas";
    private String login = "root"; //Administrador de MySQL
    private String password = "programacion2";
    private Connection cnx = null;
    private Statement sttm = null;
    private ResultSet rst = null;
    
    //método para Update, Insert, Delete
    public void UID(String sql) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cnx = DriverManager.getConnection(url, login, password);
            sttm = cnx.createStatement();
            sttm.executeUpdate(sql); //statement
          
        } catch (ClassNotFoundException | SQLException c) {
            JOptionPane.showMessageDialog(null, "ERROR: " + c.getMessage());
            System.exit(1); //salir de aplicación
        }
    }
    //Método para Consultar
    public ResultSet getValores(String sql) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cnx = DriverManager.getConnection(url, login, password);
            sttm = cnx.createStatement();
            rst = sttm.executeQuery(sql);  //resultset
        } catch (ClassNotFoundException | SQLException c) {
            JOptionPane.showMessageDialog(null, "ERROR: " + c.getMessage());
            System.exit(1);
        } finally {
            return rst;
        }
    }

    public void desconectar() {
    try {

          cnx.close();
          System.out.println("Se cerro la conexion");
        } catch (SQLException ex) {
          
//            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     public Connection conectar() {        
        Connection link =null;
        try
        {
          Class.forName("com.mysql.jdbc.Driver");  //nombre dle driver
          link =DriverManager.getConnection(url,login,password);//pasar como parametros la cadena conexion
        }
         catch (Exception e) {
             JOptionPane.showMessageDialog(null,"ha fallado "+ e);
        }
        return link;    
}
     }