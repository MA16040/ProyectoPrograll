package lag_planillas;

import java.sql.Date;
import java.sql.ResultSet;
import lag_planillas.conexion;

public class empresas {

    conexion cn = new conexion();

    public ResultSet contarRegistros() {
        return (cn.getValores("SELECT COUNT(id) FROM empresas"));
    }

    public ResultSet buscar(String codigo) {
        return (cn.getValores("SELECT * FROM empresas WHERE id='" + codigo + "'"));
    }

    public void eliminar(String codigo) {
        cn.UID("DELETE FROM empresas WHERE id = '" + codigo + "'");
    }

    public void modificar(String idEmpresa, String nombre, String direccion, String telefono, String nit, String nrc, int idActEco, String repLegal) {
        cn.UID("UPDATE empresas SET nombre='" + nombre + "',direccion='" + direccion + "',telefono='" + telefono + "',nit = '" + nit + "',nrc ='" + nrc + "',id_act_economica='" + idActEco + "',rep_legal='" + repLegal + "' WHERE id = '" + idEmpresa + "'");
    }

    public ResultSet mayorRegistro() {
        return (cn.getValores("SELECT MAX(id) FROM empresas"));
    }

    public ResultSet llenarEmpresas() {
        return cn.getValores("SELECT * FROM empresas");
    }

    public void insertarEmpresa(int idEmpresa, String nombre, String direccion, String telefono, String nit, String nrc, int actEconomica, String repLegal) {
        cn.UID("INSERT INTO empresas(id,nombre,direccion,telefono,nit,nrc,id_act_economica,rep_legal) VALUES ('" + idEmpresa + "','" + nombre + "','" + direccion + "','" + telefono + "','" + nit + "','" + nrc + "','" + actEconomica + "','" + repLegal + "')");
    }

}
