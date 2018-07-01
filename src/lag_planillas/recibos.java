package lag_planillas;

import java.sql.Date;
import java.sql.ResultSet;

public class recibos {

    conexion cn = new conexion();

    public ResultSet contarRegistros() {
        return (cn.getValores("SELECT COUNT(id_recibo) FROM recibos_por_empresa"));
    }

    public ResultSet mayorRegistro() {
        return (cn.getValores("SELECT MAX(id_recibo) FROM recibos_por_empresa"));
    }

    public ResultSet contarRecibosPorEmpresaYSucursal(int id, int id_sucursal) {
        return (cn.getValores("SELECT COUNT(id_recibo) FROM recibos_por_empresa WHERE id = '" + id + "' AND id_sucursal = '" + id_sucursal + "'"));
    }

    public ResultSet mayorRecibosPorEmpresaYSucursal(int id, int id_sucursal) {
        return (cn.getValores("SELECT MAX(id_recibo) FROM recibos_por_empresa WHERE id = '" + id + "' AND id_sucursal = '" + id_sucursal + "'"));
    }

    public void insertarRecibo(int idEmpresa, int idSucursal, int idRecibo, double valor, Date fecha, String concepto, String nombreTrabajador) {
        cn.UID("INSERT INTO recibos_por_empresa(id,id_sucursal,id_recibo,valor,fecha,conceptoRecibo,nombreTrabajadorPagado) VALUES ('" + idEmpresa + "','" + idSucursal + "','" + idRecibo + "'," + valor + ",'" + fecha + "','" + concepto + "','" + nombreTrabajador + "')");
    }

}
