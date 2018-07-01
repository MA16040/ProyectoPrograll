package lag_planillas;

import java.sql.ResultSet;

public class afps {
    
    conexion cn = new conexion();
    
    public ResultSet llenarAfps() {
        return cn.getValores("SELECT * from afps");
    }
    
     public ResultSet contarRegistros() {
        return (cn.getValores("SELECT COUNT(tipo_afp) FROM afps"));
    }

    public ResultSet mayorRegistro() {
        return (cn.getValores("SELECT MAX(tipo_afp) FROM afps"));
    }

    public void insertarAFP(int idAfp, String afp) {
        cn.UID("INSERT INTO afps(tipo_afp,descripcion_afp) VALUES ('" + idAfp + "','" + afp + "')");
    }
    
}
