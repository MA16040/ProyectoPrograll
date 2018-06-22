/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lag_planillas;

/**
 *
 * @author Diferido de solidos...
 */

public class Trabajadores {
    Conexion cn =new Conexion();
    public void insertarDatosTrabajadores(String Nombres, String Ape1, String Ape2, String cargo, int DUI, int NIT, int identificador) {
          cn.UID("INSERT INTO trabajadores(id_trabajador) VALUES('" + identificador + "',)");
    }
}
