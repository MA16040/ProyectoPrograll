package lag_planillas;

import java.sql.Date;
import java.sql.ResultSet;

public class sucursales {

    conexion cn = new conexion();

    public ResultSet contarRegistros() {
        return (cn.getValores("SELECT COUNT(id_sucursal) FROM sucursales_por_empresa"));
    }

    public ResultSet buscar(String idEmpresa, String idSucursal) {
        return (cn.getValores("SELECT * FROM sucursales_por_empresa WHERE id ='" + idEmpresa + "' AND id_sucursal = '" + idSucursal + "'"));
    }
    
    public void modificar(int idEmpresa, int idSucursal, String nombre, String direccion) {
        cn.UID("UPDATE Sucursales_por_empresa SET nombre='" + nombre + "',direccion='" + direccion + "' WHERE id='" + idEmpresa + "' AND id_sucursal='" + idSucursal + "'");
    }
    
    public void eliminar(String codigo) {
        cn.UID("DELETE FROM sucursales_por_empresa WHERE id_sucursal='" + codigo + "'");
    }
    
    public ResultSet mayorRegistro() {
        return (cn.getValores("SELECT MAX(id_sucursal) FROM sucursales_por_empresa"));
    }

    public ResultSet contarRegistrosPorEmpresa(int id) {
        return (cn.getValores("SELECT COUNT(id_sucursal) FROM sucursales_por_empresa WHERE id = '" + id + "'"));
    }

    public ResultSet mayorRegistroPorEmpresa(int id) {
        return (cn.getValores("SELECT MAX(id_sucursal) FROM sucursales_por_empresa WHERE id = '" + id + "'"));
    }

    public ResultSet llenarSucursalesPorEmpresa(int id) {
        return cn.getValores("SELECT * FROM sucursales_por_empresa WHERE id='" + id + "'");
    }

    public void insertarSucursal(int idEmpresa, int id_Sucursal, String nombre, String direccion) {
        cn.UID("INSERT INTO sucursales_por_empresa(id,id_sucursal,nombre,direccion) VALUES ('" + idEmpresa + "','" + id_Sucursal + "','" + nombre + "','" + direccion + "')");
    }

}
