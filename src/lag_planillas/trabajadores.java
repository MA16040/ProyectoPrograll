package lag_planillas;

import java.sql.Date;
import java.sql.ResultSet;

public class trabajadores {

    conexion cn = new conexion();

    public ResultSet contarRegistros() {
        return (cn.getValores("SELECT COUNT(id_trabajador) FROM trabajadores_por_empresa"));
    }

    public ResultSet buscar(String Id, String IdSuc, String IdEmpl) {
        return (cn.getValores("SELECT * FROM trabajadores_por_empresa WHERE id = '" + Id + "' AND id_sucursal = '" + IdSuc + "' AND id_trabajador = '" + IdEmpl + "'"));
    }

    public void modificar(int idEmpresa, int idSucursal, int idTrabajador, String nombres, String apellido1, String apellido2, Date fechaNacimiento, String nit, String dui, String numeroIsss, String numeroAfp, int tipoAfp, String telefono, double sueldo, int idCargo, Date fechaIngreso, Date fechaSalida) {
        cn.UID("UPDATE trabajadores_por_empresa SET nombres='" + nombres + "',apellido_uno='" + apellido1 + "',apellido_dos='" + apellido2 + "',fecha_nacimiento='" + fechaNacimiento + "',nit='" + nit + "',dui='" + dui + "',num_isss='" + numeroIsss + "',num_afp='" + numeroAfp + "',tipo_afp='" + tipoAfp + "',telefono='" + telefono + "',sueldo=" + sueldo + ",id_cargo='" + idCargo + "',fechaIngreso='" + fechaIngreso + "',fechaSalida='" + fechaSalida + "' WHERE id = '" + idEmpresa + "' AND id_sucursal= '" + idSucursal + "' AND id_trabajador = '" + idSucursal + "'");
    }

    public void modificar2(int idEmpresa, int idSucursal, int idTrabajador, String nombres, String apellido1, String apellido2, Date fechaNacimiento, String nit, String dui, String numeroIsss, String numeroAfp, int tipoAfp, String telefono, double sueldo, int idCargo, Date fechaIngreso) {
        cn.UID("UPDATE trabajadores_por_empresa SET nombres='" + nombres + "',apellido_uno='" + apellido1 + "',apellido_dos='" + apellido2 + "',fecha_nacimiento='" + fechaNacimiento + "',nit='" + nit + "',dui='" + dui + "',num_isss='" + numeroIsss + "',num_afp='" + numeroAfp + "',tipo_afp='" + tipoAfp + "',telefono='" + telefono + "',sueldo=" + sueldo + ",id_cargo='" + idCargo + "',fechaIngreso='" + fechaIngreso + "',fechaSalida='0000-00-00' WHERE id = '" + idEmpresa + "' AND id_sucursal= '" + idSucursal + "' AND id_trabajador = '" + idSucursal + "'");
    }

    public ResultSet mayorRegistro() {
        return (cn.getValores("SELECT MAX(id_trabajador) FROM trabajadores_por_empresa"));
    }

    public ResultSet contarRegistrosPorEmpresaYSucursal(int id, int id_sucursal) {
        return (cn.getValores("SELECT COUNT(id_trabajador) FROM trabajadores_por_empresa WHERE id = '" + id + "' AND id_sucursal = '" + id_sucursal + "'"));
    }

    public ResultSet mayorRegistroPorEmpresaYSucursal(int id, int id_sucursal) {
        return (cn.getValores("SELECT MAX(id_trabajador) FROM trabajadores_por_empresa WHERE id = '" + id + "' AND id_sucursal = '" + id_sucursal + "'"));
    }

    public ResultSet llenarTrabajadoresPorEmpresaYSucursal(String id, String id_sucursal) {
        return cn.getValores("SELECT * FROM trabajadores_por_empresa WHERE id = '" + id + "' AND id_sucursal = '" + id_sucursal + "'");
    }

    public void eliminar(int Id, int IdSuc, String IdEmpl) {
        cn.UID("DELETE FROM trabajadores_por_empresa WHERE id = " + Id + " AND id_sucursal = " + IdSuc + " AND id_trabajador= " + IdEmpl + "");
    }

    public void insertarTrabajador(int idEmpresa, int idSucursal, int idTrabajador, String nombres, String apellido1, String apellido2, Date fechaNacimiento, String nit, String dui, String numeroIsss, String numeroAfp, int tipoAfp, String telefono, double sueldo, int idCargo, Date fechaIngreso) {
        cn.UID("INSERT INTO trabajadores_por_empresa(id,id_sucursal,id_trabajador,nombres,apellido_uno,apellido_dos,fecha_nacimiento,nit,dui,num_isss,num_afp,tipo_afp,telefono,sueldo,id_cargo,fechaIngreso,fechaSalida) VALUES ('" + idEmpresa + "','" + idSucursal + "','" + idTrabajador + "','" + nombres + "','" + apellido1 + "','" + apellido2 + "','" + fechaNacimiento + "','" + nit + "','" + dui + "','" + numeroIsss + "','" + numeroAfp + "','" + tipoAfp + "','" + telefono + "'," + sueldo + ",'" + idCargo + "','" + fechaIngreso + "','0000-00-00')");
    }

    public void insertarTrabajadores(int idEmpresa, int idSucursal, int idTrabajador, String nombres, String apellido1, String apellido2, Date fechaNacimiento, String nit, String dui, String numeroIsss, String numeroAfp, int tipoAfp, String telefono, double sueldo, int idCargo, Date fechaIngreso, Date fechaSalida) {
        cn.UID("INSERT INTO trabajadores_por_empresa(id,id_sucursal,id_trabajador,nombres,apellido_uno,apellido_dos,fecha_nacimiento,nit,dui,num_isss,num_afp,tipo_afp,telefono,sueldo,id_cargo,fechaIngreso,fechaSalida) VALUES ('" + idEmpresa + "','" + idSucursal + "','" + idTrabajador + "','" + nombres + "','" + apellido1 + "','" + apellido2 + "','" + fechaNacimiento + "','" + nit + "','" + dui + "','" + numeroIsss + "','" + numeroAfp + "','" + tipoAfp + "','" + telefono + "','" + sueldo + "','" + idCargo + "','" + fechaIngreso + "','" + fechaSalida + "',)");
    }

}
