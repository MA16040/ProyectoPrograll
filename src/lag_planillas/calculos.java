package lag_planillas;

import java.sql.ResultSet;

public class calculos {

    conexion cn = new conexion();

    public ResultSet llenarFechasoSueldos(int id, int id_suc, int id_empleado) {
        return cn.getValores("select sueldo,fechaIngreso from trabajadores_por_empresa where id='" + id + "' and id_sucursal='" + id_suc + "' and id_trabajador='" + id_empleado + "'");
    }

}
