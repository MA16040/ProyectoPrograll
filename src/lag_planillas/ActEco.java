package lag_planillas;

import java.sql.ResultSet;
import lag_planillas.conexion;

public class ActEco {

    conexion cn = new conexion();

    public ResultSet llenarActividadesEconomicas() {
        return cn.getValores("SELECT * from actividades_economicas");
    }

    public ResultSet contarRegistros() {
        return (cn.getValores("SELECT COUNT(id_act_economica) FROM actividades_economicas"));
    }

    public ResultSet mayorRegistro() {
        return (cn.getValores("SELECT MAX(id_act_economica) FROM actividades_economicas"));
    }
    
    public void insertarActEco(int idActEco, String actEco) {
        cn.UID("INSERT INTO actividades_economicas(id_act_economica,descripcion) VALUES ('" + idActEco + "','" + actEco + "')");
    }

}
