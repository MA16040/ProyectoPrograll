package lag_planillas;

import java.util.Date;
import java.sql.ResultSet;

public class planillas {

    conexion cn = new conexion();

    public ResultSet contarRegistros(int id, int sucursal) {
        return (cn.getValores("SELECT COUNT(id) FROM planillas WHERE id = '" + id + "'  AND id_sucursal = '" + sucursal + "'"));
    }

    public ResultSet mayorRegistro(int id, int sucursal) {
        return (cn.getValores("SELECT MAX(id) FROM planillas WHERE id = '" + id + "'  AND id_sucursal = '" + sucursal + "'"));
    }

    public ResultSet contarRegistrosEmpleados(int id, int sucursal) {
        return (cn.getValores("SELECT MAX(id_trabajador) FROM trabajadores_por_empresa WHERE id = '" + id + "'  AND id_sucursal = '" + sucursal + "'"));
    }

    public ResultSet llenarTabla(int idEmpresa, int idSucursal) {
        return cn.getValores("SELECT * FROM trabajadores_por_empresa WHERE id='" + idEmpresa + "' AND id_sucursal='" + idSucursal + "'");
    }

    public void insertarPlanilla(int idPlanilla, int id, int sucursal, Object empleado, Object nombreEmp, Date deFecha, Date aFecha, Object sueldo, Object horasExt, Object vacaci, Object indemniz, Object aguinald, Object otrosIng, Object isss, Object afp, Object isr, Object anticipo, Object otrosDesc, Object liquidoPagar) {
        cn.UID("INSERT INTO planillas(id_planilla, id, id_sucursal, id_trabajador, nombre, deFecha, aFecha, sueldo, horas_extras, vacaciones, indemnización, aguinaldo, otros_ingresos, isss, afp, isr, anticipo, otros_descuentos, liquido_a_pagar)VALUES('" + idPlanilla + "','" + id + "','" + sucursal + "', '" + empleado + "', '" + nombreEmp + "', '" + deFecha + "', '" + aFecha + "','" + sueldo + "', '" + horasExt + "', '" + vacaci + "', '" + indemniz + "','" + aguinald + "','" + otrosIng + "','" + isss + "','" + afp + "','" + isr + "', '" + anticipo + "','" + otrosDesc + "', '" + liquidoPagar + "')");
    }
}
