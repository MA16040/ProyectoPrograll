package lag_planillas;

import java.sql.ResultSet;

public class cargos {

    conexion cn = new conexion();

    public ResultSet llenarCargos() {
        return cn.getValores("SELECT * from cargos");
    }

    public ResultSet contarRegistros() {
        return (cn.getValores("SELECT COUNT(id_cargo) FROM cargos"));
    }

    public ResultSet mayorRegistro() {
        return (cn.getValores("SELECT MAX(id_cargo) FROM cargos"));
    }

    public void insertarCargo(int idCargo, String cargo) {
        cn.UID("INSERT INTO cargos(id_cargo,cargo) VALUES ('" + idCargo + "','" + cargo + "')");
    }

}
