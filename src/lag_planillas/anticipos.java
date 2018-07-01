package lag_planillas;

import java.sql.Date;
import java.sql.ResultSet;

public class anticipos {

    conexion cn = new conexion();

    public ResultSet contarRegistros() {
        return (cn.getValores("SELECT COUNT(id_anticipo) FROM anticipos_por_trabajador"));
    }

    public ResultSet mayorRegistro() {
        return (cn.getValores("SELECT MAX(id_anticipo) FROM anticipos_por_trabajador"));
    }

    public ResultSet contarAnticiposEmpresaSucursalYTrabajador(int id, int id_sucursal, int id_trabajador) {
        return (cn.getValores("SELECT COUNT(id_anticipo) FROM anticipos_por_trabajador WHERE id = '" + id + "' AND id_sucursal = '" + id_sucursal + "'"
                + " AND id_trabajador = '" + id_trabajador + "'"));
    }

    public ResultSet mayorAnticipoEmpresaSucursalYTrabajador(int id, int id_sucursal, int id_trabajador) {
        return (cn.getValores("SELECT MAX(id_anticipo) FROM anticipos_por_trabajador WHERE id = '" + id + "' AND id_sucursal = '" + id_sucursal + "'"
                + " AND id_trabajador = '" + id_trabajador + "'"));
    }

    public void insertarAnticipo(int idEmpresa, int idSucursal, int idTrabajador, int idAnticipo, String nombre, Date fechaAnticipo, double valorAnticipo) {
        cn.UID("INSERT INTO anticipos_por_trabajador(id,id_sucursal,id_trabajador,id_anticipo,nombre,fechaAnticipo,valorAnticipo) VALUES ('" + idEmpresa + "','" + idSucursal + "','" + idTrabajador + "','" + idAnticipo + "','" + nombre + "','" + fechaAnticipo + "'," + valorAnticipo + ")");
    }

}
