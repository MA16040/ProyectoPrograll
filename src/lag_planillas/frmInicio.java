package lag_planillas;

import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class frmInicio extends javax.swing.JFrame {

    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

    public void reinicio() {
        modeloResultados.setColumnCount(0);
        modeloResultados.setRowCount(0);
    }

    int cuantasColumnas;
    int columna;

    DefaultTableModel modeloResultados = new DefaultTableModel();
    ArrayList<String> encabezados = new ArrayList<>();

    fmrActEco activEco = new fmrActEco();
    frmCargos cargoo = new frmCargos();
    addAFP afpe = new addAFP();

    boolean registro = false;
    boolean prueba = false;
    boolean newRecord = false;

    conexion cn = new conexion();

    ResultSet rstClientes = null;
    planillas plan = new planillas();

    public void bloquearEmpleados() {
        txtIdEmpleado.setEditable(false);
        txtNombresEmp.setEditable(false);
        txtApellido1Emp.setEditable(false);
        txtApellido2Emp.setEditable(false);
        txtNITEmp.setEditable(false);
        txtDUIEmp.setEditable(false);
        txtNumISSSEmp.setEditable(false);
        txtNumAFPEmp.setEditable(false);
        txtNumTelEmp.setEditable(false);
        txtSueldoEmp.setEditable(false);
        cmbEmpresasDisponibles1.setEnabled(false);
        cmbSucursalesDisponibles1.setEnabled(false);
        cmbAFPEmp.setEnabled(false);
        cmbCargoEmp.setEnabled(false);
        dtcFechaNacimiento.setEnabled(false);
        dtcFechaIngreso.setEnabled(false);
        dtcFechaSalida.setEnabled(false);
        txtSueldoEmp.setEditable(false);
    }

    public void desbloquearEmpleados() {
        txtIdEmpleado.setEditable(false);
        txtNombresEmp.setEditable(true);
        txtNombresEmp.requestFocus();
        txtApellido1Emp.setEditable(true);
        txtApellido2Emp.setEditable(true);
        txtNITEmp.setEditable(true);
        txtDUIEmp.setEditable(true);
        txtNumISSSEmp.setEditable(true);
        txtNumAFPEmp.setEditable(true);
        txtNumTelEmp.setEditable(true);
        txtSueldoEmp.setEditable(true);
        cmbEmpresasDisponibles1.setEnabled(false);
        cmbSucursalesDisponibles1.setEnabled(false);
        cmbAFPEmp.setEnabled(true);
        cmbCargoEmp.setEnabled(true);
        dtcFechaNacimiento.setEnabled(true);
        dtcFechaIngreso.setEnabled(true);
        dtcFechaSalida.setEnabled(true);
        txtSueldoEmp.setEditable(true);
    }

    public void cargarEmpresas() {
        empresas.removeAllElements();
        nombresEmpresas.removeAllElements();
        rstEmpresas = empresa.llenarEmpresas();
        try {
            while (rstEmpresas.next()) {
                empresas.addElement(rstEmpresas.getInt(1));
                nombresEmpresas.addElement(rstEmpresas.getString(2));
            }
            cmbEmpresasDisponibles1.setModel(nombresEmpresas);
            lblEmpresaSeleccionada1.setText(String.valueOf(empresas.getElementAt(0)));
            cmbEmpresasDisponibles2.setModel(nombresEmpresas);
            lblEmpresaSeleccionada2.setText(String.valueOf(empresas.getElementAt(0)));
            cmbEmpresasDisponibles3.setModel(nombresEmpresas);
            lblEmpresaSeleccionada3.setText(String.valueOf(empresas.getElementAt(0)));
            cmbEmpresasDisponibles4.setModel(nombresEmpresas);
            lblEmpresaSeleccionada4.setText(String.valueOf(empresas.getElementAt(0)));
            cmbEmpresasDisponibles6.setModel(nombresEmpresas);
            lblEmpresaSeleccionada6.setText(String.valueOf(empresas.getElementAt(0)));
            cmbCalculosEmpresa.setModel(nombresEmpresas);
            lblEmpresaSeleccionada8.setText(String.valueOf(empresas.getElementAt(0)));
        } catch (SQLException ex) {
            Logger.getLogger(frmInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargarSucursales(int empresa) {
        sucursales.removeAllElements();
        nombresSucursales.removeAllElements();
        rstSucursales = sucursal.llenarSucursalesPorEmpresa(empresa);
        try {
            while (rstSucursales.next()) {
                sucursales.addElement(rstSucursales.getInt(2));
                nombresSucursales.addElement(rstSucursales.getString(3));
            }
            cmbSucursalesDisponibles1.setModel(nombresSucursales);
            lblSucursalSeleccionada1.setText(String.valueOf(sucursales.getElementAt(0)));
            cmbSucursalesDisponibles2.setModel(nombresSucursales);
            lblSucursalSeleccionada2.setText(String.valueOf(sucursales.getElementAt(0)));
            cmbSucursalesDisponibles3.setModel(nombresSucursales);
            lblSucursalSeleccionada3.setText(String.valueOf(sucursales.getElementAt(0)));
            cmbSucursalesDisponibles4.setModel(nombresSucursales);
            lblSucursalSeleccionada4.setText(String.valueOf(sucursales.getElementAt(0)));
            cmbCalculosSucursal.setModel(nombresSucursales);
            lblSucursalSeleccionada9.setText(String.valueOf(sucursales.getElementAt(0)));
        } catch (SQLException ex) {
            Logger.getLogger(frmInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargarEmpleados(String empresa, String sucursal) {
        trabajadores.removeAllElements();
        nombresTrabajadores.removeAllElements();
        rstTrabajadores = trabajador.llenarTrabajadoresPorEmpresaYSucursal(empresa, sucursal);
        try {
            while (rstTrabajadores.next()) {
                trabajadores.addElement(rstTrabajadores.getInt(3));
                nombresTrabajadores.addElement(rstTrabajadores.getString(4));
            }
            cmbEmpleadosDisponibles1.setModel(nombresTrabajadores);
            lblEmpleadoSeleccionado1.setText(String.valueOf(trabajadores.getElementAt(0)));
            cmbCalculosEmpleado.setModel(nombresTrabajadores);
        } catch (SQLException ex) {
            Logger.getLogger(frmInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargarActEco() {
        actEco.removeAllElements();
        nombresActEco.removeAllElements();
        rstActEco = acteco.llenarActividadesEconomicas();
        try {
            while (rstActEco.next()) {
                actEco.addElement(rstActEco.getInt(1));
                nombresActEco.addElement(rstActEco.getString(2));
            }
            cmbActEconomica.setModel(nombresActEco);
            lblActEcoSeleccionada.setText(String.valueOf(actEco.getElementAt(0)));
        } catch (SQLException ex) {
            Logger.getLogger(frmInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargarCargos() {
        cargos.removeAllElements();
        nombresCargos.removeAllElements();
        rstCargos = cargo.llenarCargos();
        try {
            while (rstCargos.next()) {
                cargos.addElement(rstCargos.getInt(1));
                nombresCargos.addElement(rstCargos.getString(2));
            }
            cmbCargoEmp.setModel(nombresCargos);
            lblCargoSeleccionado1.setText(String.valueOf(cargos.getElementAt(0)));
        } catch (SQLException ex) {
            Logger.getLogger(frmInicio.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cargarAFPS() {
        afps.removeAllElements();
        nombresAfps.removeAllElements();
        rstAfps = afp.llenarAfps();
        try {
            while (rstAfps.next()) {
                afps.addElement(rstAfps.getInt(1));
                nombresAfps.addElement(rstAfps.getString(2));
            }
            cmbAFPEmp.setModel(nombresAfps);
            lblAFPSeleccionado.setText(String.valueOf(afps.getElementAt(0)));
        } catch (SQLException ex) {
            Logger.getLogger(frmInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int obtenerDias(int id, int id_suc, int id_empleado) {
        calculos derecho = new calculos();
        ResultSet rstFechas = null;
        rstFechas = derecho.llenarFechasoSueldos(id, id_suc, id_empleado);
        Date fechaInicial = null;
        try {
            while (rstFechas.next()) {
                fechaInicial = rstFechas.getDate(2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(frmInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.util.Date fechaFinal;
        fechaFinal = new java.util.Date();
        long fechaFinal2 = fechaFinal.getTime();
        java.sql.Date fechaFinalReal = new java.sql.Date(fechaFinal2);
        int dias = (int) ((fechaFinalReal.getTime() - fechaInicial.getTime()) / 86400000);
        return dias;
    }

    public int obtenerAnios(int id, int id_suc, int id_empleado) {
        calculos derecho = new calculos();
        ResultSet rstFechas = null;
        rstFechas = derecho.llenarFechasoSueldos(id, id_suc, id_empleado);
        Date fechaInicial = null;
        try {
            while (rstFechas.next()) {
                fechaInicial = rstFechas.getDate(2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(frmInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.util.Date fechaFinal;
        fechaFinal = new java.util.Date();
        long fechaFinal2 = fechaFinal.getTime();
        java.sql.Date fechaFinalReal = new java.sql.Date(fechaFinal2);
        int dias = (int) ((fechaFinalReal.getTime() - fechaInicial.getTime()) / 86400000);
        int anios = dias / 365;
        return anios;
    }

    public double obtenerSueldo(int id, int id_suc, int id_empleado) {
        calculos derecho = new calculos();
        ResultSet rstSueldo = null;
        rstSueldo = derecho.llenarFechasoSueldos(id, id_suc, id_empleado);
        double sueldo = 0;
        try {
            while (rstSueldo.next()) {
                sueldo = rstSueldo.getDouble(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(frmInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sueldo;
    }

    DefaultComboBoxModel actEco = new DefaultComboBoxModel();
    DefaultComboBoxModel nombresActEco = new DefaultComboBoxModel();
    DefaultComboBoxModel empresas = new DefaultComboBoxModel();
    DefaultComboBoxModel nombresEmpresas = new DefaultComboBoxModel();
    DefaultComboBoxModel sucursales = new DefaultComboBoxModel();
    DefaultComboBoxModel nombresSucursales = new DefaultComboBoxModel();
    DefaultComboBoxModel trabajadores = new DefaultComboBoxModel();
    DefaultComboBoxModel nombresTrabajadores = new DefaultComboBoxModel();
    DefaultComboBoxModel afps = new DefaultComboBoxModel();
    DefaultComboBoxModel nombresAfps = new DefaultComboBoxModel();
    DefaultComboBoxModel cargos = new DefaultComboBoxModel();
    DefaultComboBoxModel nombresCargos = new DefaultComboBoxModel();

    ResultSet rstActEco = null;
    ResultSet rstEmpresas = null;
    ResultSet rstSucursales = null;
    ResultSet rstAfps = null;
    ResultSet rstTrabajadores = null;
    ResultSet rstCargos = null;
    ActEco acteco = new ActEco();
    empresas empresa = new empresas();
    sucursales sucursal = new sucursales();
    trabajadores trabajador = new trabajadores();
    afps afp = new afps();
    cargos cargo = new cargos();
    recibos recibo = new recibos();
    anticipos anticipo = new anticipos();

    trabajadores clTra = new trabajadores();
    sucursales clSuc = new sucursales();
    empresas clEmpr = new empresas();
    planillas clPlan = new planillas();
    anticipos clAnt = new anticipos();
    recibos clRec = new recibos();
    cargos clCargo = new cargos();
    ResultSet rs = null;
    int cantidad = 0;
    int mayor = 0;

    ResultSet rstPlanillas = null;
    planillas planilla = new planillas();

    public frmInicio() {
        initComponents();
        Columnas();

        modeloResultados = new DefaultTableModel();
        tblPlanilla.setModel(modeloResultados);

        btnModificarEmpresa.setVisible(false);
        btnEliminarEmpresa.setVisible(false);
        btnCambioEmpresa.setVisible(false);
        btnModificarSuc.setVisible(false);
        btnEliminarSuc.setVisible(false);
        btnCambioSucursal.setVisible(false);
        dtcFechaSalida.setEnabled(false);
        btnModificarEmp.setVisible(false);
        btnEliminarEmp.setVisible(false);
        btnCambioEmpleado.setVisible(false);

        cargarActEco();

        cargarEmpresas();

        cargarSucursales(1);

        cargarEmpleados("1", "1");

        cargarCargos();

        cargarAFPS();

        this.setExtendedState(MAXIMIZED_BOTH);
        txtIdEmpleado.setEditable(false);
        rs = null;
        rs = clTra.contarRegistrosPorEmpresaYSucursal(1, 1);
        try {
            while (rs.next()) {
                cantidad = rs.getInt(1);
                if (cantidad != 0) {
                    rs = null;
                    rs = clTra.mayorRegistroPorEmpresaYSucursal(1, 1);
                    while (rs.next()) {
                        mayor = rs.getInt(1) + 1;
                        if (mayor < 10) {
                            txtIdEmpleado.setText("00" + mayor);
                        } else if (mayor < 100) {
                            this.txtIdEmpleado.setText("0" + mayor);
                        } else {
                            txtIdEmpleado.setText("" + mayor);
                        }
                    }
                } else {
                    txtIdEmpleado.setText("00" + 1);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "AVISO DEL SISTEMA", 0);
        }

        this.setExtendedState(MAXIMIZED_BOTH);
        txtIdSucursal.setEditable(false);
        rs = null;
        rs = clSuc.contarRegistrosPorEmpresa(1);
        try {
            while (rs.next()) {
                cantidad = rs.getInt(1);
                if (cantidad != 0) {
                    rs = null;
                    rs = clSuc.mayorRegistroPorEmpresa(1);
                    while (rs.next()) {
                        mayor = rs.getInt(1) + 1;
                        if (mayor < 10) {
                            txtIdSucursal.setText("00" + mayor);
                        } else if (mayor < 100) {
                            this.txtIdSucursal.setText("0" + mayor);
                        } else {
                            txtIdSucursal.setText("" + mayor);
                        }
                    }
                } else {
                    txtIdSucursal.setText("00" + 1);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "AVISO DEL SISTEMA", 0);
        }

        this.setExtendedState(MAXIMIZED_BOTH);
        txtIdEmpresa.setEditable(false);
        rs = null;
        rs = clEmpr.contarRegistros();
        try {
            while (rs.next()) {
                cantidad = rs.getInt(1);
                if (cantidad != 0) {
                    rs = null;
                    rs = clEmpr.mayorRegistro();
                    while (rs.next()) {
                        mayor = rs.getInt(1) + 1;
                        if (mayor < 10) {
                            txtIdEmpresa.setText("00" + mayor);
                        } else if (mayor < 100) {
                            this.txtIdEmpresa.setText("0" + mayor);
                        } else {
                            txtIdEmpresa.setText("" + mayor);
                        }
                    }
                } else {
                    txtIdEmpresa.setText("00" + 1);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "AVISO DEL SISTEMA", 0);
        }

        this.setExtendedState(MAXIMIZED_BOTH);
        txtIdPlanilla.setEditable(false);
        rs = null;
        rs = clPlan.contarRegistros(1, 1);
        try {
            while (rs.next()) {
                cantidad = rs.getInt(1);
                if (cantidad != 0) {
                    rs = null;
                    rs = clPlan.mayorRegistro(1, 1);
                    while (rs.next()) {
                        mayor = rs.getInt(1) + 1;
                        if (mayor < 10) {
                            txtIdPlanilla.setText("00" + mayor);
                        } else if (mayor < 100) {
                            this.txtIdPlanilla.setText("0" + mayor);
                        } else {
                            txtIdPlanilla.setText("" + mayor);
                        }
                    }
                } else {
                    txtIdPlanilla.setText("00" + 1);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "AVISO DEL SISTEMA", 0);
        }

        this.setExtendedState(MAXIMIZED_BOTH);
        txtIdAnticipo.setEditable(false);
        rs = null;
        rs = clAnt.contarAnticiposEmpresaSucursalYTrabajador(1, 1, 1);
        try {
            while (rs.next()) {
                cantidad = rs.getInt(1);
                if (cantidad != 0) {
                    rs = null;
                    rs = clAnt.mayorAnticipoEmpresaSucursalYTrabajador(1, 1, 1);
                    while (rs.next()) {
                        mayor = rs.getInt(1) + 1;
                        if (mayor < 10) {
                            txtIdAnticipo.setText("00" + mayor);
                        } else if (mayor < 100) {
                            this.txtIdAnticipo.setText("0" + mayor);
                        } else {
                            txtIdAnticipo.setText("" + mayor);
                        }
                    }
                } else {
                    txtIdAnticipo.setText("00" + 1);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "AVISO DEL SISTEMA", 0);
        }

        this.setExtendedState(MAXIMIZED_BOTH);
        txtIdRecibo.setEditable(false);
        rs = null;
        rs = clRec.contarRecibosPorEmpresaYSucursal(1, 1);
        try {
            while (rs.next()) {
                cantidad = rs.getInt(1);
                if (cantidad != 0) {
                    rs = null;
                    rs = clRec.mayorRecibosPorEmpresaYSucursal(1, 1);
                    while (rs.next()) {
                        mayor = rs.getInt(1) + 1;
                        if (mayor < 10) {
                            txtIdRecibo.setText("00" + mayor);
                        } else if (mayor < 100) {
                            this.txtIdRecibo.setText("0" + mayor);
                        } else {
                            txtIdRecibo.setText("" + mayor);
                        }
                    }
                } else {
                    txtIdRecibo.setText("00" + 1);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "AVISO DEL SISTEMA", 0);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tbdpnInicio = new javax.swing.JTabbedPane();
        tbdpnFormularios = new javax.swing.JTabbedPane();
        lydpnEmpleados = new javax.swing.JLayeredPane();
        lblIdEmpleado = new javax.swing.JLabel();
        lblNombresEmp = new javax.swing.JLabel();
        lblApellido1Emp = new javax.swing.JLabel();
        lblApellido2Emp = new javax.swing.JLabel();
        lblFechaNacimiento = new javax.swing.JLabel();
        lblNITEmp = new javax.swing.JLabel();
        lblDUIEmp = new javax.swing.JLabel();
        lblNumAFPEmp = new javax.swing.JLabel();
        lblNumISSSEmp = new javax.swing.JLabel();
        txtIdEmpleado = new javax.swing.JTextField();
        txtNombresEmp = new javax.swing.JTextField();
        txtApellido1Emp = new javax.swing.JTextField();
        txtNumAFPEmp = new javax.swing.JTextField();
        txtNumISSSEmp = new javax.swing.JTextField();
        cmbAFPEmp = new javax.swing.JComboBox<>();
        sprtrEmpleado1 = new javax.swing.JSeparator();
        lblSueldoEmp = new javax.swing.JLabel();
        lblCargoEmp = new javax.swing.JLabel();
        lblFechaIngreso = new javax.swing.JLabel();
        lblFechaSalida = new javax.swing.JLabel();
        lblInfoEsp = new javax.swing.JLabel();
        sprtrEmpleado2 = new javax.swing.JSeparator();
        lblDatosGenerales = new javax.swing.JLabel();
        btnNuevoCargoEmp = new javax.swing.JButton();
        cmbEmpresasDisponibles1 = new javax.swing.JComboBox<>();
        cmbSucursalesDisponibles1 = new javax.swing.JComboBox<>();
        sprtrEmpleado3 = new javax.swing.JSeparator();
        btnCancelarEmp = new javax.swing.JButton();
        btnGuardarEmp = new javax.swing.JButton();
        btnConsultarEmp = new javax.swing.JButton();
        btnModificarEmp = new javax.swing.JButton();
        btnEliminarEmp = new javax.swing.JButton();
        cmbCargoEmp = new javax.swing.JComboBox<>();
        dtcFechaNacimiento = new com.toedter.calendar.JDateChooser();
        dtcFechaSalida = new com.toedter.calendar.JDateChooser();
        dtcFechaIngreso = new com.toedter.calendar.JDateChooser();
        txtSueldoEmp = new javax.swing.JTextField();
        txtDUIEmp = new javax.swing.JFormattedTextField();
        txtNITEmp = new javax.swing.JFormattedTextField();
        lblNumTelEmp = new javax.swing.JLabel();
        txtNumTelEmp = new javax.swing.JFormattedTextField();
        lblSeleccionarSucursal2 = new javax.swing.JLabel();
        lblSeleccionarEmpresa5 = new javax.swing.JLabel();
        lblAFPSeleccionado = new javax.swing.JLabel();
        lblEmpresaSeleccionada1 = new javax.swing.JLabel();
        lblSucursalSeleccionada1 = new javax.swing.JLabel();
        lblCargoSeleccionado1 = new javax.swing.JLabel();
        txtApellido2Emp = new javax.swing.JTextField();
        btnCambioEmpleado = new javax.swing.JButton();
        btnAgregarAFP = new javax.swing.JButton();
        lydpnSucursales = new javax.swing.JLayeredPane();
        lblSeleccionarEmpresa = new javax.swing.JLabel();
        cmbEmpresasDisponibles2 = new javax.swing.JComboBox<>();
        lblIdSucursal = new javax.swing.JLabel();
        txtIdSucursal = new javax.swing.JTextField();
        lblNombreSucursal = new javax.swing.JLabel();
        txtNombreSucursal = new javax.swing.JTextField();
        lblDireccionSuc = new javax.swing.JLabel();
        txtDireccionSuc = new javax.swing.JTextField();
        btnCancelarSuc = new javax.swing.JButton();
        btnGuardarSuc = new javax.swing.JButton();
        btnConsultarSuc = new javax.swing.JButton();
        btnModificarSuc = new javax.swing.JButton();
        btnEliminarSuc = new javax.swing.JButton();
        lblEmpresaSeleccionada2 = new javax.swing.JLabel();
        btnCambioSucursal = new javax.swing.JButton();
        lydpnEmpresas = new javax.swing.JLayeredPane();
        lblIdEmpresa = new javax.swing.JLabel();
        txtIdEmpresa = new javax.swing.JTextField();
        lblNombreEmpresa = new javax.swing.JLabel();
        txtNombreEmpresa = new javax.swing.JTextField();
        lblDireccionEmpresa = new javax.swing.JLabel();
        txtDireccionEmpresa = new javax.swing.JTextField();
        lblTelefonoEmpresa = new javax.swing.JLabel();
        lblNITEmpresa = new javax.swing.JLabel();
        lblNRCEmpresa = new javax.swing.JLabel();
        txtNRCEmpresa = new javax.swing.JTextField();
        lclActEcoEmpresa = new javax.swing.JLabel();
        cmbActEconomica = new javax.swing.JComboBox<>();
        lblRepLegalEmpresa = new javax.swing.JLabel();
        txtRepLegalEmpresa = new javax.swing.JTextField();
        btnCancelarEmpresa = new javax.swing.JButton();
        btnGuardarEmpresa = new javax.swing.JButton();
        btnConsultarEmpresa = new javax.swing.JButton();
        btnModificarEmpresa = new javax.swing.JButton();
        btnEliminarEmpresa = new javax.swing.JButton();
        txtTelefonoEmpresa = new javax.swing.JFormattedTextField();
        btnAgregarActEcon = new javax.swing.JButton();
        lblActEcoSeleccionada = new javax.swing.JLabel();
        txtNITEmp1 = new javax.swing.JFormattedTextField();
        btnCambioEmpresa = new javax.swing.JButton();
        tbdpnInformes = new javax.swing.JTabbedPane();
        lydpnAnticipos = new javax.swing.JLayeredPane();
        lblSeleccionarEmpresa1 = new javax.swing.JLabel();
        lblSeleccionarEmp = new javax.swing.JLabel();
        cmbEmpresasDisponibles3 = new javax.swing.JComboBox<>();
        cmbEmpleadosDisponibles1 = new javax.swing.JComboBox<>();
        lblFechaAnticipo = new javax.swing.JLabel();
        lblValorAnticipo = new javax.swing.JLabel();
        txtValorAnticipo = new javax.swing.JTextField();
        btnGuardarAnticipo = new javax.swing.JButton();
        lblNota4 = new javax.swing.JLabel();
        dtcFechaAnticipo = new com.toedter.calendar.JDateChooser();
        cmbSucursalesDisponibles4 = new javax.swing.JComboBox<>();
        lblSeleccionarSucursal4 = new javax.swing.JLabel();
        txtIdAnticipo = new javax.swing.JTextField();
        lblIdAnticipo = new javax.swing.JLabel();
        btnCancelarAnticipo = new javax.swing.JButton();
        lblEmpresaSeleccionada3 = new javax.swing.JLabel();
        lblSucursalSeleccionada4 = new javax.swing.JLabel();
        lblEmpleadoSeleccionado1 = new javax.swing.JLabel();
        lydpnPlanillas = new javax.swing.JLayeredPane();
        lblDeFecha = new javax.swing.JLabel();
        lblAFecha = new javax.swing.JLabel();
        btnGenerarPlanilla = new javax.swing.JButton();
        cmbEmpresasDisponibles4 = new javax.swing.JComboBox<>();
        cmbSucursalesDisponibles2 = new javax.swing.JComboBox<>();
        lblSeleccionarEmpresa2 = new javax.swing.JLabel();
        lblSeleccionarSucursal1 = new javax.swing.JLabel();
        btnCancelarPlanilla = new javax.swing.JButton();
        btnGuardarPlanilla = new javax.swing.JButton();
        lblVistaPrevia = new javax.swing.JLabel();
        lblIdPlanilla = new javax.swing.JLabel();
        txtIdPlanilla = new javax.swing.JTextField();
        dtcDeFecha = new com.toedter.calendar.JDateChooser();
        dtcAFecha = new com.toedter.calendar.JDateChooser();
        lblEmpresaSeleccionada4 = new javax.swing.JLabel();
        lblSucursalSeleccionada2 = new javax.swing.JLabel();
        scrlpnPlanillas = new javax.swing.JScrollPane();
        tblPlanilla = new javax.swing.JTable();
        lydpnRecibos = new javax.swing.JLayeredPane();
        cmbEmpresasDisponibles6 = new javax.swing.JComboBox<>();
        lblSeleccionarEmpresa4 = new javax.swing.JLabel();
        lblConceptoRecibo = new javax.swing.JLabel();
        txtConceptoRecibo = new javax.swing.JTextField();
        lblValorRecibo = new javax.swing.JLabel();
        txtValorRecibo = new javax.swing.JTextField();
        btnGuardarRecibo = new javax.swing.JButton();
        btnCancelarRecibo = new javax.swing.JButton();
        lblNombreFirmante = new javax.swing.JLabel();
        txtNombreFirmante = new javax.swing.JTextField();
        lblFechaRecibo = new javax.swing.JLabel();
        dtcFechaRecibo = new com.toedter.calendar.JDateChooser();
        cmbSucursalesDisponibles3 = new javax.swing.JComboBox<>();
        lblSeleccionarSucursal3 = new javax.swing.JLabel();
        txtIdRecibo = new javax.swing.JTextField();
        lblIdRecibo = new javax.swing.JLabel();
        lblEmpresaSeleccionada6 = new javax.swing.JLabel();
        lblSucursalSeleccionada3 = new javax.swing.JLabel();
        tbdpnCalculos = new javax.swing.JTabbedPane();
        lydpnVacaciones = new javax.swing.JLayeredPane();
        lblSeleccionarEmpleado1 = new javax.swing.JLabel();
        cmbCalculosEmpleado = new javax.swing.JComboBox<>();
        btnCalcular = new javax.swing.JButton();
        lblSeleccionarEmpresa7 = new javax.swing.JLabel();
        cmbCalculosEmpresa = new javax.swing.JComboBox<>();
        lblEmpresaSeleccionada8 = new javax.swing.JLabel();
        cmbCalculosSucursal = new javax.swing.JComboBox<>();
        lblSeleccionarSucursal10 = new javax.swing.JLabel();
        lblSucursalSeleccionada9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtVacaciones = new javax.swing.JTextField();
        txtAguinaldo = new javax.swing.JTextField();
        txtIndemnizacion = new javax.swing.JTextField();
        btmLimpiarCalculos = new javax.swing.JButton();
        lydpnHorasExtras = new javax.swing.JLayeredPane();
        lblSeleccionarEmpresa10 = new javax.swing.JLabel();
        txtSueldoMensual = new javax.swing.JTextField();
        scrlpnHorasExtras = new javax.swing.JScrollPane();
        jtblExtras = new javax.swing.JTable();
        btnNuevoCalculo = new javax.swing.JButton();
        btnCalcularHorasExtras = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PLANILLAS");
        setFocusable(false);
        setIconImage(new ImageIcon(getClass().getResource("/iconos/borders_accent.png")).getImage());
        setMaximumSize(new java.awt.Dimension(850, 650));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(850, 650));

        tbdpnInicio.setMaximumSize(new java.awt.Dimension(900, 700));
        tbdpnInicio.setMinimumSize(new java.awt.Dimension(800, 600));
        tbdpnInicio.setPreferredSize(new java.awt.Dimension(800, 600));

        tbdpnFormularios.setMaximumSize(new java.awt.Dimension(900, 700));
        tbdpnFormularios.setMinimumSize(new java.awt.Dimension(800, 600));
        tbdpnFormularios.setPreferredSize(new java.awt.Dimension(800, 600));

        lydpnEmpleados.setMaximumSize(new java.awt.Dimension(900, 700));
        lydpnEmpleados.setMinimumSize(new java.awt.Dimension(800, 600));

        lblIdEmpleado.setText("Identificador:");

        lblNombresEmp.setText("Nombres:");

        lblApellido1Emp.setText("Apellido1:");

        lblApellido2Emp.setText("Apellido2:");

        lblFechaNacimiento.setText("Fecha de Nac.:");

        lblNITEmp.setText("N.I.T.:");

        lblDUIEmp.setText("D.U.I:");

        lblNumAFPEmp.setText("N° AFP:");

        lblNumISSSEmp.setText("N° ISSS:");

        txtNombresEmp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombresEmpKeyTyped(evt);
            }
        });

        txtApellido1Emp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellido1EmpKeyTyped(evt);
            }
        });

        cmbAFPEmp.setModel(afps);
        cmbAFPEmp.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbAFPEmpItemStateChanged(evt);
            }
        });
        cmbAFPEmp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbAFPEmpFocusGained(evt);
            }
        });

        lblSueldoEmp.setText("Sueldo Mensual:");

        lblCargoEmp.setText("Cargo:");

        lblFechaIngreso.setText("Fecha de Ingreso:");

        lblFechaSalida.setText("Fecha de Salida:");

        lblInfoEsp.setText("Informacion Especifica:");

        lblDatosGenerales.setText("Informacion General:");

        btnNuevoCargoEmp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/MODIFICAR.png"))); // NOI18N
        btnNuevoCargoEmp.setText("Crear Cargo");
        btnNuevoCargoEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoCargoEmpActionPerformed(evt);
            }
        });

        cmbEmpresasDisponibles1.setModel(empresas);
        cmbEmpresasDisponibles1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbEmpresasDisponibles1ItemStateChanged(evt);
            }
        });
        cmbEmpresasDisponibles1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbEmpresasDisponibles1FocusGained(evt);
            }
        });

        cmbSucursalesDisponibles1.setModel(sucursales);
        cmbSucursalesDisponibles1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbSucursalesDisponibles1ItemStateChanged(evt);
            }
        });
        cmbSucursalesDisponibles1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbSucursalesDisponibles1FocusGained(evt);
            }
        });

        btnCancelarEmp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/CANCELAR.png"))); // NOI18N
        btnCancelarEmp.setText("Cancelar");
        btnCancelarEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarEmpActionPerformed(evt);
            }
        });

        btnGuardarEmp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/GUARDAR.png"))); // NOI18N
        btnGuardarEmp.setText("Guardar ");
        btnGuardarEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarEmpActionPerformed(evt);
            }
        });

        btnConsultarEmp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/BUSCAR.png"))); // NOI18N
        btnConsultarEmp.setText("Consultar");
        btnConsultarEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarEmpActionPerformed(evt);
            }
        });

        btnModificarEmp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/MODIFICAR.png"))); // NOI18N
        btnModificarEmp.setText("Modificar");
        btnModificarEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarEmpActionPerformed(evt);
            }
        });

        btnEliminarEmp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/BORRAR.png"))); // NOI18N
        btnEliminarEmp.setText("Eliminar");
        btnEliminarEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarEmpActionPerformed(evt);
            }
        });

        cmbCargoEmp.setModel(cargos);
        cmbCargoEmp.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbCargoEmpItemStateChanged(evt);
            }
        });
        cmbCargoEmp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbCargoEmpFocusGained(evt);
            }
        });

        dtcFechaNacimiento.setDateFormatString("yyyy-MM-dd");

        dtcFechaSalida.setDateFormatString("yyyy-MM-dd");

        dtcFechaIngreso.setDateFormatString("yyyy-MM-dd");

        txtSueldoEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSueldoEmpActionPerformed(evt);
            }
        });
        txtSueldoEmp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSueldoEmpKeyTyped(evt);
            }
        });

        try {
            txtDUIEmp.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("########-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txtNITEmp.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-######-###-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        lblNumTelEmp.setText("Telefono:");

        try {
            txtNumTelEmp.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        lblSeleccionarSucursal2.setText("Seleccione Sucursal:");

        lblSeleccionarEmpresa5.setText("Seleccione Empresa:");

        txtApellido2Emp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellido2EmpKeyTyped(evt);
            }
        });

        btnCambioEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/GUARDAR.png"))); // NOI18N
        btnCambioEmpleado.setText("Guardar Cambios");
        btnCambioEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambioEmpleadoActionPerformed(evt);
            }
        });

        btnAgregarAFP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/hotjobs.png"))); // NOI18N
        btnAgregarAFP.setText("Agregar AFP");
        btnAgregarAFP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarAFPActionPerformed(evt);
            }
        });

        lydpnEmpleados.setLayer(lblIdEmpleado, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(lblNombresEmp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(lblApellido1Emp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(lblApellido2Emp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(lblFechaNacimiento, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(lblNITEmp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(lblDUIEmp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(lblNumAFPEmp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(lblNumISSSEmp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(txtIdEmpleado, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(txtNombresEmp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(txtApellido1Emp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(txtNumAFPEmp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(txtNumISSSEmp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(cmbAFPEmp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(sprtrEmpleado1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(lblSueldoEmp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(lblCargoEmp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(lblFechaIngreso, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(lblFechaSalida, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(lblInfoEsp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(sprtrEmpleado2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(lblDatosGenerales, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(btnNuevoCargoEmp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(cmbEmpresasDisponibles1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(cmbSucursalesDisponibles1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(sprtrEmpleado3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(btnCancelarEmp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(btnGuardarEmp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(btnConsultarEmp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(btnModificarEmp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(btnEliminarEmp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(cmbCargoEmp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(dtcFechaNacimiento, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(dtcFechaSalida, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(dtcFechaIngreso, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(txtSueldoEmp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(txtDUIEmp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(txtNITEmp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(lblNumTelEmp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(txtNumTelEmp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(lblSeleccionarSucursal2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(lblSeleccionarEmpresa5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(lblAFPSeleccionado, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(lblEmpresaSeleccionada1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(lblSucursalSeleccionada1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(lblCargoSeleccionado1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(txtApellido2Emp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(btnCambioEmpleado, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnEmpleados.setLayer(btnAgregarAFP, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout lydpnEmpleadosLayout = new javax.swing.GroupLayout(lydpnEmpleados);
        lydpnEmpleados.setLayout(lydpnEmpleadosLayout);
        lydpnEmpleadosLayout.setHorizontalGroup(
            lydpnEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lydpnEmpleadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lydpnEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lydpnEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(sprtrEmpleado3, javax.swing.GroupLayout.PREFERRED_SIZE, 764, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(lydpnEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(lydpnEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, lydpnEmpleadosLayout.createSequentialGroup()
                                    .addComponent(lblInfoEsp)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(sprtrEmpleado1))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, lydpnEmpleadosLayout.createSequentialGroup()
                                    .addGroup(lydpnEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblFechaNacimiento)
                                        .addComponent(lblIdEmpleado)
                                        .addComponent(lblNombresEmp)
                                        .addComponent(lblApellido1Emp)
                                        .addComponent(lblApellido2Emp))
                                    .addGap(18, 18, 18)
                                    .addGroup(lydpnEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtIdEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtNombresEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtApellido1Emp, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dtcFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtApellido2Emp, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(35, 35, 35)
                                    .addGroup(lydpnEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblNumAFPEmp)
                                        .addComponent(lblDUIEmp)
                                        .addComponent(lblNumISSSEmp)
                                        .addComponent(lblNITEmp)
                                        .addComponent(lblNumTelEmp))
                                    .addGap(27, 27, 27)
                                    .addGroup(lydpnEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(lydpnEmpleadosLayout.createSequentialGroup()
                                            .addGroup(lydpnEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(txtNumTelEmp, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtNumAFPEmp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                                            .addGap(18, 18, 18)
                                            .addGroup(lydpnEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(cmbAFPEmp, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnAgregarAFP, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)))
                                        .addGroup(lydpnEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtNITEmp, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtDUIEmp, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNumISSSEmp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblAFPSeleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(114, 114, 114))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, lydpnEmpleadosLayout.createSequentialGroup()
                                    .addGroup(lydpnEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lblDatosGenerales, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, lydpnEmpleadosLayout.createSequentialGroup()
                                            .addGap(113, 113, 113)
                                            .addComponent(sprtrEmpleado2, javax.swing.GroupLayout.PREFERRED_SIZE, 754, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, lydpnEmpleadosLayout.createSequentialGroup()
                                            .addGroup(lydpnEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(lydpnEmpleadosLayout.createSequentialGroup()
                                                    .addComponent(lblSueldoEmp)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(txtSueldoEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(lydpnEmpleadosLayout.createSequentialGroup()
                                                    .addComponent(lblCargoEmp)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(cmbCargoEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(lblCargoSeleccionado1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnNuevoCargoEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addGroup(lydpnEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblFechaIngreso)
                                                .addComponent(lblFechaSalida))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(lydpnEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(dtcFechaIngreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(dtcFechaSalida, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGap(24, 24, 24)))
                            .addGroup(lydpnEmpleadosLayout.createSequentialGroup()
                                .addGroup(lydpnEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblSeleccionarEmpresa5)
                                    .addGroup(lydpnEmpleadosLayout.createSequentialGroup()
                                        .addComponent(cmbEmpresasDisponibles1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblEmpresaSeleccionada1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(lydpnEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(lydpnEmpleadosLayout.createSequentialGroup()
                                        .addComponent(cmbSucursalesDisponibles1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblSucursalSeleccionada1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblSeleccionarSucursal2)))))
                    .addGroup(lydpnEmpleadosLayout.createSequentialGroup()
                        .addGroup(lydpnEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnCancelarEmp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                            .addComponent(btnGuardarEmp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(lydpnEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnModificarEmp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnConsultarEmp, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(lydpnEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCambioEmpleado)
                            .addComponent(btnEliminarEmp)))))
        );
        lydpnEmpleadosLayout.setVerticalGroup(
            lydpnEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lydpnEmpleadosLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(lydpnEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSeleccionarEmpresa5)
                    .addComponent(lblSeleccionarSucursal2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(lydpnEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblEmpresaSeleccionada1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSucursalSeleccionada1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(lydpnEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbEmpresasDisponibles1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbSucursalesDisponibles1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sprtrEmpleado2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDatosGenerales)
                .addGap(11, 11, 11)
                .addGroup(lydpnEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdEmpleado)
                    .addComponent(lblNITEmp)
                    .addComponent(txtIdEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNITEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(lydpnEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombresEmp)
                    .addComponent(lblDUIEmp)
                    .addComponent(txtNombresEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDUIEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(lydpnEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblApellido1Emp)
                    .addComponent(txtApellido1Emp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNumISSSEmp)
                    .addComponent(txtNumISSSEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(lydpnEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lydpnEmpleadosLayout.createSequentialGroup()
                        .addGroup(lydpnEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblApellido2Emp)
                            .addComponent(lblNumAFPEmp)
                            .addComponent(txtNumAFPEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbAFPEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtApellido2Emp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(lydpnEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNumTelEmp)
                            .addComponent(txtNumTelEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAgregarAFP, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(lydpnEmpleadosLayout.createSequentialGroup()
                        .addComponent(lblAFPSeleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(lydpnEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblFechaNacimiento)
                            .addComponent(dtcFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(lydpnEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(lydpnEmpleadosLayout.createSequentialGroup()
                                .addComponent(lblInfoEsp, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(lydpnEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblSueldoEmp)
                                    .addComponent(txtSueldoEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(lydpnEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblCargoSeleccionado1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(lydpnEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblCargoEmp)
                                        .addComponent(cmbCargoEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(lydpnEmpleadosLayout.createSequentialGroup()
                                .addComponent(sprtrEmpleado1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(lydpnEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(lydpnEmpleadosLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(lydpnEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(dtcFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblFechaIngreso))
                                        .addGap(18, 18, 18)
                                        .addGroup(lydpnEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(dtcFechaSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblFechaSalida)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lydpnEmpleadosLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnNuevoCargoEmp)
                                        .addGap(1, 1, 1)))))
                        .addGap(33, 33, 33)
                        .addComponent(sprtrEmpleado3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(lydpnEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardarEmp)
                            .addComponent(btnConsultarEmp)
                            .addComponent(btnEliminarEmp))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(lydpnEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelarEmp)
                    .addComponent(btnModificarEmp)
                    .addComponent(btnCambioEmpleado))
                .addContainerGap(77, Short.MAX_VALUE))
        );

        tbdpnFormularios.addTab("Empleados", lydpnEmpleados);

        lydpnSucursales.setMaximumSize(new java.awt.Dimension(900, 700));
        lydpnSucursales.setMinimumSize(new java.awt.Dimension(800, 600));

        lblSeleccionarEmpresa.setText("Empresa a la cual le creara una Sucursal:");

        cmbEmpresasDisponibles2.setModel(empresas);
        cmbEmpresasDisponibles2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbEmpresasDisponibles2ItemStateChanged(evt);
            }
        });
        cmbEmpresasDisponibles2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbEmpresasDisponibles2FocusGained(evt);
            }
        });

        lblIdSucursal.setText("Identificador:");

        lblNombreSucursal.setText("Nombre Sucursal:");

        lblDireccionSuc.setText("Direccion Sucursal:");

        btnCancelarSuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/CANCELAR.png"))); // NOI18N
        btnCancelarSuc.setText("Cancelar");
        btnCancelarSuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarSucActionPerformed(evt);
            }
        });

        btnGuardarSuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/GUARDAR.png"))); // NOI18N
        btnGuardarSuc.setText("Guardar ");
        btnGuardarSuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarSucActionPerformed(evt);
            }
        });

        btnConsultarSuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/BUSCAR.png"))); // NOI18N
        btnConsultarSuc.setText("Consultar");
        btnConsultarSuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarSucActionPerformed(evt);
            }
        });

        btnModificarSuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/MODIFICAR.png"))); // NOI18N
        btnModificarSuc.setText("Modificar");
        btnModificarSuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarSucActionPerformed(evt);
            }
        });

        btnEliminarSuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/BORRAR.png"))); // NOI18N
        btnEliminarSuc.setText("Eliminar");
        btnEliminarSuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarSucActionPerformed(evt);
            }
        });

        btnCambioSucursal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/GUARDAR.png"))); // NOI18N
        btnCambioSucursal.setText("Guardar Cambios");
        btnCambioSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambioSucursalActionPerformed(evt);
            }
        });

        lydpnSucursales.setLayer(lblSeleccionarEmpresa, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnSucursales.setLayer(cmbEmpresasDisponibles2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnSucursales.setLayer(lblIdSucursal, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnSucursales.setLayer(txtIdSucursal, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnSucursales.setLayer(lblNombreSucursal, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnSucursales.setLayer(txtNombreSucursal, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnSucursales.setLayer(lblDireccionSuc, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnSucursales.setLayer(txtDireccionSuc, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnSucursales.setLayer(btnCancelarSuc, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnSucursales.setLayer(btnGuardarSuc, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnSucursales.setLayer(btnConsultarSuc, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnSucursales.setLayer(btnModificarSuc, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnSucursales.setLayer(btnEliminarSuc, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnSucursales.setLayer(lblEmpresaSeleccionada2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnSucursales.setLayer(btnCambioSucursal, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout lydpnSucursalesLayout = new javax.swing.GroupLayout(lydpnSucursales);
        lydpnSucursales.setLayout(lydpnSucursalesLayout);
        lydpnSucursalesLayout.setHorizontalGroup(
            lydpnSucursalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lydpnSucursalesLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(lydpnSucursalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lydpnSucursalesLayout.createSequentialGroup()
                        .addGroup(lydpnSucursalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(lydpnSucursalesLayout.createSequentialGroup()
                                .addComponent(lblDireccionSuc)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtDireccionSuc, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                                .addGap(46, 46, 46))
                            .addGroup(lydpnSucursalesLayout.createSequentialGroup()
                                .addGroup(lydpnSucursalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(lydpnSucursalesLayout.createSequentialGroup()
                                        .addComponent(btnGuardarSuc)
                                        .addGap(37, 37, 37)
                                        .addComponent(btnConsultarSuc, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnEliminarSuc))
                                    .addComponent(lblSeleccionarEmpresa)
                                    .addGroup(lydpnSucursalesLayout.createSequentialGroup()
                                        .addGroup(lydpnSucursalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblIdSucursal)
                                            .addComponent(lblNombreSucursal))
                                        .addGap(26, 26, 26)
                                        .addGroup(lydpnSucursalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtIdSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtNombreSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(cmbEmpresasDisponibles2, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(lblEmpresaSeleccionada2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(lydpnSucursalesLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(btnCancelarSuc, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(btnModificarSuc, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCambioSucursal)))
                .addContainerGap(331, Short.MAX_VALUE))
        );
        lydpnSucursalesLayout.setVerticalGroup(
            lydpnSucursalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lydpnSucursalesLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(lblSeleccionarEmpresa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(lydpnSucursalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblEmpresaSeleccionada2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbEmpresasDisponibles2))
                .addGap(39, 39, 39)
                .addGroup(lydpnSucursalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdSucursal)
                    .addComponent(txtIdSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(lydpnSucursalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombreSucursal)
                    .addComponent(txtNombreSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(lydpnSucursalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDireccionSuc)
                    .addComponent(txtDireccionSuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(lydpnSucursalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarSuc)
                    .addComponent(btnConsultarSuc)
                    .addComponent(btnEliminarSuc))
                .addGap(18, 18, 18)
                .addGroup(lydpnSucursalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelarSuc)
                    .addComponent(btnModificarSuc)
                    .addComponent(btnCambioSucursal))
                .addContainerGap(226, Short.MAX_VALUE))
        );

        tbdpnFormularios.addTab("Sucursales", lydpnSucursales);

        lydpnEmpresas.setMaximumSize(new java.awt.Dimension(900, 700));
        lydpnEmpresas.setMinimumSize(new java.awt.Dimension(800, 600));
        lydpnEmpresas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblIdEmpresa.setText("Identificador:");
        lydpnEmpresas.add(lblIdEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, -1, -1));
        lydpnEmpresas.add(txtIdEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, 44, -1));

        lblNombreEmpresa.setText("Empresa:");
        lydpnEmpresas.add(lblNombreEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, -1, -1));
        lydpnEmpresas.add(txtNombreEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 110, 254, -1));

        lblDireccionEmpresa.setText("Direccion:");
        lydpnEmpresas.add(lblDireccionEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, -1, -1));
        lydpnEmpresas.add(txtDireccionEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, 254, -1));

        lblTelefonoEmpresa.setText("Telefono:");
        lydpnEmpresas.add(lblTelefonoEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, -1, -1));

        lblNITEmpresa.setText("N.I.T:");
        lydpnEmpresas.add(lblNITEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, -1, -1));

        lblNRCEmpresa.setText("N.R.C:");
        lydpnEmpresas.add(lblNRCEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 230, -1, -1));

        txtNRCEmpresa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNRCEmpresaKeyTyped(evt);
            }
        });
        lydpnEmpresas.add(txtNRCEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 230, 140, -1));

        lclActEcoEmpresa.setText("Giro/Act.Economica:");
        lydpnEmpresas.add(lclActEcoEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 270, -1, -1));

        cmbActEconomica.setModel(actEco);
        cmbActEconomica.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbActEconomicaItemStateChanged(evt);
            }
        });
        cmbActEconomica.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbActEconomicaFocusGained(evt);
            }
        });
        lydpnEmpresas.add(cmbActEconomica, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 260, 190, -1));

        lblRepLegalEmpresa.setText("Representante Legal:");
        lydpnEmpresas.add(lblRepLegalEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 300, -1, -1));
        lydpnEmpresas.add(txtRepLegalEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 300, 190, -1));

        btnCancelarEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/CANCELAR.png"))); // NOI18N
        btnCancelarEmpresa.setText("Cancelar");
        btnCancelarEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarEmpresaActionPerformed(evt);
            }
        });
        lydpnEmpresas.add(btnCancelarEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 410, 120, -1));

        btnGuardarEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/GUARDAR.png"))); // NOI18N
        btnGuardarEmpresa.setText("Guardar ");
        btnGuardarEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarEmpresaActionPerformed(evt);
            }
        });
        lydpnEmpresas.add(btnGuardarEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 350, 120, -1));

        btnConsultarEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/BUSCAR.png"))); // NOI18N
        btnConsultarEmpresa.setText("Consultar");
        btnConsultarEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarEmpresaActionPerformed(evt);
            }
        });
        lydpnEmpresas.add(btnConsultarEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 350, 130, -1));

        btnModificarEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/MODIFICAR.png"))); // NOI18N
        btnModificarEmpresa.setText("Modificar");
        btnModificarEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarEmpresaActionPerformed(evt);
            }
        });
        lydpnEmpresas.add(btnModificarEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 410, 130, -1));

        btnEliminarEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/BORRAR.png"))); // NOI18N
        btnEliminarEmpresa.setText("Eliminar");
        btnEliminarEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarEmpresaActionPerformed(evt);
            }
        });
        lydpnEmpresas.add(btnEliminarEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 350, 130, -1));

        try {
            txtTelefonoEmpresa.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        lydpnEmpresas.add(txtTelefonoEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 170, 140, -1));

        btnAgregarActEcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/hotjobs.png"))); // NOI18N
        btnAgregarActEcon.setText("Agregar Actividad");
        btnAgregarActEcon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActEconActionPerformed(evt);
            }
        });
        lydpnEmpresas.add(btnAgregarActEcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 260, -1, -1));
        lydpnEmpresas.add(lblActEcoSeleccionada, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 260, 40, 20));

        try {
            txtNITEmp1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-######-###-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        lydpnEmpresas.add(txtNITEmp1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 200, 140, -1));

        btnCambioEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/GUARDAR.png"))); // NOI18N
        btnCambioEmpresa.setText("Guardar Cambios");
        btnCambioEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambioEmpresaActionPerformed(evt);
            }
        });
        lydpnEmpresas.add(btnCambioEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 410, -1, -1));

        tbdpnFormularios.addTab("Empresas", lydpnEmpresas);

        tbdpnInicio.addTab("Formularios", tbdpnFormularios);

        tbdpnInformes.setMaximumSize(new java.awt.Dimension(900, 700));
        tbdpnInformes.setMinimumSize(new java.awt.Dimension(800, 600));
        tbdpnInformes.setPreferredSize(new java.awt.Dimension(800, 600));

        lblSeleccionarEmpresa1.setText("Seleccione Empresa del Empleado:");

        lblSeleccionarEmp.setText("Seleccione El Empleado:");

        cmbEmpresasDisponibles3.setModel(empresas);
        cmbEmpresasDisponibles3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbEmpresasDisponibles3ItemStateChanged(evt);
            }
        });
        cmbEmpresasDisponibles3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbEmpresasDisponibles3FocusGained(evt);
            }
        });

        cmbEmpleadosDisponibles1.setModel(trabajadores);
        cmbEmpleadosDisponibles1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbEmpleadosDisponibles1ItemStateChanged(evt);
            }
        });
        cmbEmpleadosDisponibles1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbEmpleadosDisponibles1FocusGained(evt);
            }
        });

        lblFechaAnticipo.setText("Fecha del Anticipo:");

        lblValorAnticipo.setText("Valor del Anticipo $");

        txtValorAnticipo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtValorAnticipoKeyTyped(evt);
            }
        });

        btnGuardarAnticipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/GUARDAR.png"))); // NOI18N
        btnGuardarAnticipo.setText("Guardar");
        btnGuardarAnticipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarAnticipoActionPerformed(evt);
            }
        });

        lblNota4.setText("Nota: Este anticipo sera directamente descontado en planilla de sueldos y salarios.");

        dtcFechaAnticipo.setDateFormatString("yyyy-MM-dd");

        cmbSucursalesDisponibles4.setModel(sucursales);
        cmbSucursalesDisponibles4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbSucursalesDisponibles4ItemStateChanged(evt);
            }
        });
        cmbSucursalesDisponibles4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbSucursalesDisponibles4FocusGained(evt);
            }
        });

        lblSeleccionarSucursal4.setText("Seleccione Sucursal:");

        lblIdAnticipo.setText("Identificador:");

        btnCancelarAnticipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/CANCELAR.png"))); // NOI18N
        btnCancelarAnticipo.setText("Cancelar");
        btnCancelarAnticipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarAnticipoActionPerformed(evt);
            }
        });

        lydpnAnticipos.setLayer(lblSeleccionarEmpresa1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnAnticipos.setLayer(lblSeleccionarEmp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnAnticipos.setLayer(cmbEmpresasDisponibles3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnAnticipos.setLayer(cmbEmpleadosDisponibles1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnAnticipos.setLayer(lblFechaAnticipo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnAnticipos.setLayer(lblValorAnticipo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnAnticipos.setLayer(txtValorAnticipo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnAnticipos.setLayer(btnGuardarAnticipo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnAnticipos.setLayer(lblNota4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnAnticipos.setLayer(dtcFechaAnticipo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnAnticipos.setLayer(cmbSucursalesDisponibles4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnAnticipos.setLayer(lblSeleccionarSucursal4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnAnticipos.setLayer(txtIdAnticipo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnAnticipos.setLayer(lblIdAnticipo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnAnticipos.setLayer(btnCancelarAnticipo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnAnticipos.setLayer(lblEmpresaSeleccionada3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnAnticipos.setLayer(lblSucursalSeleccionada4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnAnticipos.setLayer(lblEmpleadoSeleccionado1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout lydpnAnticiposLayout = new javax.swing.GroupLayout(lydpnAnticipos);
        lydpnAnticipos.setLayout(lydpnAnticiposLayout);
        lydpnAnticiposLayout.setHorizontalGroup(
            lydpnAnticiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lydpnAnticiposLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(lydpnAnticiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lydpnAnticiposLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(lblNota4))
                    .addGroup(lydpnAnticiposLayout.createSequentialGroup()
                        .addGroup(lydpnAnticiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(lydpnAnticiposLayout.createSequentialGroup()
                                .addGap(86, 86, 86)
                                .addGroup(lydpnAnticiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblValorAnticipo)
                                    .addGroup(lydpnAnticiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblIdAnticipo)
                                        .addComponent(lblFechaAnticipo)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(lydpnAnticiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dtcFechaAnticipo, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtIdAnticipo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtValorAnticipo, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(lydpnAnticiposLayout.createSequentialGroup()
                                        .addComponent(btnGuardarAnticipo)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnCancelarAnticipo))))
                            .addGroup(lydpnAnticiposLayout.createSequentialGroup()
                                .addGroup(lydpnAnticiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblSeleccionarSucursal4)
                                    .addComponent(lblSeleccionarEmpresa1)
                                    .addComponent(lblSeleccionarEmp))
                                .addGap(29, 29, 29)
                                .addGroup(lydpnAnticiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbEmpresasDisponibles3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(lydpnAnticiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(cmbEmpleadosDisponibles1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cmbSucursalesDisponibles4, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(lydpnAnticiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblEmpresaSeleccionada3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSucursalSeleccionada4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEmpleadoSeleccionado1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 322, Short.MAX_VALUE))
        );
        lydpnAnticiposLayout.setVerticalGroup(
            lydpnAnticiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lydpnAnticiposLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lydpnAnticiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lydpnAnticiposLayout.createSequentialGroup()
                        .addGroup(lydpnAnticiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbEmpresasDisponibles3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSeleccionarEmpresa1))
                        .addGap(18, 18, 18)
                        .addGroup(lydpnAnticiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbSucursalesDisponibles4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSeleccionarSucursal4)))
                    .addGroup(lydpnAnticiposLayout.createSequentialGroup()
                        .addComponent(lblEmpresaSeleccionada3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblSucursalSeleccionada4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(lydpnAnticiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lydpnAnticiposLayout.createSequentialGroup()
                        .addGroup(lydpnAnticiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbEmpleadosDisponibles1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSeleccionarEmp))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(lydpnAnticiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblIdAnticipo)
                            .addComponent(txtIdAnticipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(lydpnAnticiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dtcFechaAnticipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFechaAnticipo))
                        .addGap(18, 18, 18)
                        .addGroup(lydpnAnticiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblValorAnticipo)
                            .addComponent(txtValorAnticipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblEmpleadoSeleccionado1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(lydpnAnticiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelarAnticipo)
                    .addComponent(btnGuardarAnticipo))
                .addGap(66, 66, 66)
                .addComponent(lblNota4)
                .addContainerGap())
        );

        tbdpnInformes.addTab("Anticipos a Empleados", lydpnAnticipos);

        lydpnPlanillas.setMaximumSize(new java.awt.Dimension(900, 700));
        lydpnPlanillas.setMinimumSize(new java.awt.Dimension(800, 600));

        lblDeFecha.setText("De Fecha:");

        lblAFecha.setText("A Fecha:");

        btnGenerarPlanilla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/word_imports.png"))); // NOI18N
        btnGenerarPlanilla.setText("Generar Planilla");
        btnGenerarPlanilla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarPlanillaActionPerformed(evt);
            }
        });

        cmbEmpresasDisponibles4.setModel(empresas);
        cmbEmpresasDisponibles4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbEmpresasDisponibles4ItemStateChanged(evt);
            }
        });
        cmbEmpresasDisponibles4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbEmpresasDisponibles4FocusGained(evt);
            }
        });

        cmbSucursalesDisponibles2.setModel(sucursales);
        cmbSucursalesDisponibles2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbSucursalesDisponibles2ItemStateChanged(evt);
            }
        });
        cmbSucursalesDisponibles2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbSucursalesDisponibles2FocusGained(evt);
            }
        });

        lblSeleccionarEmpresa2.setText("Seleccione Empresa:");

        lblSeleccionarSucursal1.setText("Seleccione Sucursal:");

        btnCancelarPlanilla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/CANCELAR.png"))); // NOI18N
        btnCancelarPlanilla.setText("Cancelar");
        btnCancelarPlanilla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarPlanillaActionPerformed(evt);
            }
        });

        btnGuardarPlanilla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/GUARDAR.png"))); // NOI18N
        btnGuardarPlanilla.setText("Guardar Historial");
        btnGuardarPlanilla.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnGuardarPlanillaFocusGained(evt);
            }
        });
        btnGuardarPlanilla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardarPlanillaMouseEntered(evt);
            }
        });
        btnGuardarPlanilla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarPlanillaActionPerformed(evt);
            }
        });

        lblVistaPrevia.setText("Vista Previa:");

        lblIdPlanilla.setText("Identificador de Planilla en pantalla:");

        dtcDeFecha.setDateFormatString("yyyy-MM-dd");

        dtcAFecha.setDateFormatString("yyyy-MM-dd");

        tblPlanilla.setModel(modeloResultados);
        tblPlanilla.getTableHeader().setReorderingAllowed(false);
        scrlpnPlanillas.setViewportView(tblPlanilla);

        lydpnPlanillas.setLayer(lblDeFecha, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnPlanillas.setLayer(lblAFecha, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnPlanillas.setLayer(btnGenerarPlanilla, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnPlanillas.setLayer(cmbEmpresasDisponibles4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnPlanillas.setLayer(cmbSucursalesDisponibles2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnPlanillas.setLayer(lblSeleccionarEmpresa2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnPlanillas.setLayer(lblSeleccionarSucursal1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnPlanillas.setLayer(btnCancelarPlanilla, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnPlanillas.setLayer(btnGuardarPlanilla, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnPlanillas.setLayer(lblVistaPrevia, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnPlanillas.setLayer(lblIdPlanilla, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnPlanillas.setLayer(txtIdPlanilla, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnPlanillas.setLayer(dtcDeFecha, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnPlanillas.setLayer(dtcAFecha, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnPlanillas.setLayer(lblEmpresaSeleccionada4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnPlanillas.setLayer(lblSucursalSeleccionada2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnPlanillas.setLayer(scrlpnPlanillas, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout lydpnPlanillasLayout = new javax.swing.GroupLayout(lydpnPlanillas);
        lydpnPlanillas.setLayout(lydpnPlanillasLayout);
        lydpnPlanillasLayout.setHorizontalGroup(
            lydpnPlanillasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lydpnPlanillasLayout.createSequentialGroup()
                .addGroup(lydpnPlanillasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lydpnPlanillasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblVistaPrevia))
                    .addGroup(lydpnPlanillasLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(lydpnPlanillasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSeleccionarEmpresa2)
                            .addGroup(lydpnPlanillasLayout.createSequentialGroup()
                                .addComponent(lblDeFecha)
                                .addGap(18, 18, 18)
                                .addComponent(dtcDeFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55)
                                .addComponent(lblAFecha)
                                .addGap(18, 18, 18)
                                .addComponent(dtcAFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(lydpnPlanillasLayout.createSequentialGroup()
                                .addComponent(cmbEmpresasDisponibles4, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(lblEmpresaSeleccionada4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(cmbSucursalesDisponibles2, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(lblSucursalSeleccionada2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(lydpnPlanillasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnGenerarPlanilla)
                        .addGap(30, 30, 30)
                        .addGroup(lydpnPlanillasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(lydpnPlanillasLayout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(btnCancelarPlanilla, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(lydpnPlanillasLayout.createSequentialGroup()
                                .addComponent(lblIdPlanilla)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(lydpnPlanillasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtIdPlanilla, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblSeleccionarSucursal1))))))
                .addContainerGap(183, Short.MAX_VALUE))
            .addGroup(lydpnPlanillasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnGuardarPlanilla)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(lydpnPlanillasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(lydpnPlanillasLayout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(scrlpnPlanillas)
                    .addGap(20, 20, 20)))
        );
        lydpnPlanillasLayout.setVerticalGroup(
            lydpnPlanillasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lydpnPlanillasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lydpnPlanillasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(lydpnPlanillasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblDeFecha)
                        .addComponent(lblAFecha))
                    .addComponent(dtcDeFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtcAFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(lydpnPlanillasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSeleccionarEmpresa2)
                    .addComponent(lblSeleccionarSucursal1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(lydpnPlanillasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(lydpnPlanillasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblEmpresaSeleccionada4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbEmpresasDisponibles4)
                        .addComponent(cmbSucursalesDisponibles2))
                    .addComponent(lblSucursalSeleccionada2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(lydpnPlanillasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGenerarPlanilla)
                    .addComponent(lblIdPlanilla)
                    .addComponent(txtIdPlanilla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblVistaPrevia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 266, Short.MAX_VALUE)
                .addGroup(lydpnPlanillasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarPlanilla)
                    .addComponent(btnCancelarPlanilla))
                .addGap(109, 109, 109))
            .addGroup(lydpnPlanillasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(lydpnPlanillasLayout.createSequentialGroup()
                    .addGap(188, 188, 188)
                    .addComponent(scrlpnPlanillas, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(230, Short.MAX_VALUE)))
        );

        tbdpnInformes.addTab("Planillas de Sueldos y Salarios", lydpnPlanillas);

        lydpnRecibos.setMaximumSize(new java.awt.Dimension(900, 700));
        lydpnRecibos.setMinimumSize(new java.awt.Dimension(800, 600));

        cmbEmpresasDisponibles6.setModel(empresas);
        cmbEmpresasDisponibles6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbEmpresasDisponibles6ItemStateChanged(evt);
            }
        });
        cmbEmpresasDisponibles6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbEmpresasDisponibles6FocusGained(evt);
            }
        });

        lblSeleccionarEmpresa4.setText("Seleccione La Empresa que Emitira el Recibo:");

        lblConceptoRecibo.setText("Concepto del Recibo:");

        lblValorRecibo.setText("Valor del Recibo:");

        txtValorRecibo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtValorReciboKeyTyped(evt);
            }
        });

        btnGuardarRecibo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/GUARDAR.png"))); // NOI18N
        btnGuardarRecibo.setText("Guardar");
        btnGuardarRecibo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarReciboActionPerformed(evt);
            }
        });

        btnCancelarRecibo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/CANCELAR.png"))); // NOI18N
        btnCancelarRecibo.setText("Cancelar");
        btnCancelarRecibo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarReciboActionPerformed(evt);
            }
        });

        lblNombreFirmante.setText("Nombre del Firmante:");

        txtNombreFirmante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreFirmanteKeyTyped(evt);
            }
        });

        lblFechaRecibo.setText("Fecha:");

        dtcFechaRecibo.setDateFormatString("yyyy-MM-dd");

        cmbSucursalesDisponibles3.setModel(sucursales);
        cmbSucursalesDisponibles3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbSucursalesDisponibles3ItemStateChanged(evt);
            }
        });
        cmbSucursalesDisponibles3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbSucursalesDisponibles3FocusGained(evt);
            }
        });

        lblSeleccionarSucursal3.setText("Seleccione Sucursal:");

        lblIdRecibo.setText("Identificador:");

        lydpnRecibos.setLayer(cmbEmpresasDisponibles6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnRecibos.setLayer(lblSeleccionarEmpresa4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnRecibos.setLayer(lblConceptoRecibo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnRecibos.setLayer(txtConceptoRecibo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnRecibos.setLayer(lblValorRecibo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnRecibos.setLayer(txtValorRecibo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnRecibos.setLayer(btnGuardarRecibo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnRecibos.setLayer(btnCancelarRecibo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnRecibos.setLayer(lblNombreFirmante, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnRecibos.setLayer(txtNombreFirmante, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnRecibos.setLayer(lblFechaRecibo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnRecibos.setLayer(dtcFechaRecibo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnRecibos.setLayer(cmbSucursalesDisponibles3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnRecibos.setLayer(lblSeleccionarSucursal3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnRecibos.setLayer(txtIdRecibo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnRecibos.setLayer(lblIdRecibo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnRecibos.setLayer(lblEmpresaSeleccionada6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnRecibos.setLayer(lblSucursalSeleccionada3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout lydpnRecibosLayout = new javax.swing.GroupLayout(lydpnRecibos);
        lydpnRecibos.setLayout(lydpnRecibosLayout);
        lydpnRecibosLayout.setHorizontalGroup(
            lydpnRecibosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lydpnRecibosLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(lydpnRecibosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lydpnRecibosLayout.createSequentialGroup()
                        .addGroup(lydpnRecibosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(lydpnRecibosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblConceptoRecibo)
                                .addComponent(lblNombreFirmante))
                            .addComponent(lblIdRecibo)
                            .addComponent(lblValorRecibo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(lydpnRecibosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtConceptoRecibo, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombreFirmante, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(lydpnRecibosLayout.createSequentialGroup()
                                .addComponent(txtValorRecibo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(lblFechaRecibo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dtcFechaRecibo, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtIdRecibo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(lydpnRecibosLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(btnGuardarRecibo)
                        .addGap(24, 24, 24)
                        .addComponent(btnCancelarRecibo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(lydpnRecibosLayout.createSequentialGroup()
                        .addGroup(lydpnRecibosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblSeleccionarEmpresa4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbEmpresasDisponibles6, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(34, 34, 34)
                        .addComponent(lblEmpresaSeleccionada6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(lydpnRecibosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSeleccionarSucursal3)
                            .addGroup(lydpnRecibosLayout.createSequentialGroup()
                                .addComponent(cmbSucursalesDisponibles3, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(lblSucursalSeleccionada3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(162, Short.MAX_VALUE))
        );
        lydpnRecibosLayout.setVerticalGroup(
            lydpnRecibosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lydpnRecibosLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(lydpnRecibosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSeleccionarEmpresa4)
                    .addComponent(lblSeleccionarSucursal3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(lydpnRecibosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lydpnRecibosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cmbSucursalesDisponibles3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbEmpresasDisponibles6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblEmpresaSeleccionada6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lblSucursalSeleccionada3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(lydpnRecibosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtIdRecibo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIdRecibo))
                .addGap(18, 18, 18)
                .addGroup(lydpnRecibosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(lydpnRecibosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblValorRecibo)
                        .addComponent(txtValorRecibo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblFechaRecibo))
                    .addComponent(dtcFechaRecibo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(lydpnRecibosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblConceptoRecibo)
                    .addComponent(txtConceptoRecibo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(lydpnRecibosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombreFirmante)
                    .addComponent(txtNombreFirmante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addGroup(lydpnRecibosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarRecibo)
                    .addComponent(btnCancelarRecibo))
                .addContainerGap(275, Short.MAX_VALUE))
        );

        tbdpnInformes.addTab("Recibos", lydpnRecibos);

        tbdpnInicio.addTab("Informes", null, tbdpnInformes, "");

        tbdpnCalculos.setMaximumSize(new java.awt.Dimension(900, 700));
        tbdpnCalculos.setMinimumSize(new java.awt.Dimension(800, 600));
        tbdpnCalculos.setPreferredSize(new java.awt.Dimension(800, 600));

        lydpnVacaciones.setMaximumSize(new java.awt.Dimension(900, 700));
        lydpnVacaciones.setMinimumSize(new java.awt.Dimension(800, 600));
        lydpnVacaciones.setName(""); // NOI18N

        lblSeleccionarEmpleado1.setText("Seleccione Al Empleado");

        cmbCalculosEmpleado.setModel(trabajadores);
        cmbCalculosEmpleado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbCalculosEmpleadoItemStateChanged(evt);
            }
        });
        cmbCalculosEmpleado.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbCalculosEmpleadoFocusGained(evt);
            }
        });

        btnCalcular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/calculator_black.png"))); // NOI18N
        btnCalcular.setText("Calcular");
        btnCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularActionPerformed(evt);
            }
        });

        lblSeleccionarEmpresa7.setText("Seleccione Empresa");

        cmbCalculosEmpresa.setModel(empresas);
        cmbCalculosEmpresa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbCalculosEmpresaItemStateChanged(evt);
            }
        });
        cmbCalculosEmpresa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbCalculosEmpresaFocusGained(evt);
            }
        });

        cmbCalculosSucursal.setModel(sucursales);
        cmbCalculosSucursal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbCalculosSucursalItemStateChanged(evt);
            }
        });
        cmbCalculosSucursal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbCalculosSucursalFocusGained(evt);
            }
        });

        lblSeleccionarSucursal10.setText("Seleccione Sucursal:");

        jLabel1.setText("Vacaciones:");

        jLabel2.setText("Aguinaldo:");

        jLabel3.setText("Indemnizacion:");

        btmLimpiarCalculos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/BORRAR.png"))); // NOI18N
        btmLimpiarCalculos.setText("Eliminar calculos");
        btmLimpiarCalculos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmLimpiarCalculosActionPerformed(evt);
            }
        });

        lydpnVacaciones.setLayer(lblSeleccionarEmpleado1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnVacaciones.setLayer(cmbCalculosEmpleado, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnVacaciones.setLayer(btnCalcular, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnVacaciones.setLayer(lblSeleccionarEmpresa7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnVacaciones.setLayer(cmbCalculosEmpresa, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnVacaciones.setLayer(lblEmpresaSeleccionada8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnVacaciones.setLayer(cmbCalculosSucursal, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnVacaciones.setLayer(lblSeleccionarSucursal10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnVacaciones.setLayer(lblSucursalSeleccionada9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnVacaciones.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnVacaciones.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnVacaciones.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnVacaciones.setLayer(txtVacaciones, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnVacaciones.setLayer(txtAguinaldo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnVacaciones.setLayer(txtIndemnizacion, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnVacaciones.setLayer(btmLimpiarCalculos, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout lydpnVacacionesLayout = new javax.swing.GroupLayout(lydpnVacaciones);
        lydpnVacaciones.setLayout(lydpnVacacionesLayout);
        lydpnVacacionesLayout.setHorizontalGroup(
            lydpnVacacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lydpnVacacionesLayout.createSequentialGroup()
                .addGroup(lydpnVacacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lydpnVacacionesLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(lydpnVacacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCalcular)
                            .addGroup(lydpnVacacionesLayout.createSequentialGroup()
                                .addGroup(lydpnVacacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(lydpnVacacionesLayout.createSequentialGroup()
                                        .addComponent(lblSeleccionarEmpleado1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cmbCalculosEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(lydpnVacacionesLayout.createSequentialGroup()
                                        .addComponent(lblSeleccionarEmpresa7)
                                        .addGap(82, 82, 82)
                                        .addComponent(cmbCalculosEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, lydpnVacacionesLayout.createSequentialGroup()
                                        .addComponent(lblSeleccionarSucursal10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cmbCalculosSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(37, 37, 37)
                                .addGroup(lydpnVacacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblSucursalSeleccionada9, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                                    .addComponent(lblEmpresaSeleccionada8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(btmLimpiarCalculos)))
                    .addGroup(lydpnVacacionesLayout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addGroup(lydpnVacacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(lydpnVacacionesLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtIndemnizacion, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))
                            .addGroup(lydpnVacacionesLayout.createSequentialGroup()
                                .addGroup(lydpnVacacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addGap(33, 33, 33)
                                .addGroup(lydpnVacacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtVacaciones, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                                    .addComponent(txtAguinaldo))))))
                .addContainerGap(284, Short.MAX_VALUE))
        );
        lydpnVacacionesLayout.setVerticalGroup(
            lydpnVacacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lydpnVacacionesLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(lydpnVacacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lydpnVacacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblSeleccionarEmpresa7)
                        .addComponent(lblEmpresaSeleccionada8, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                    .addComponent(cmbCalculosEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(lydpnVacacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lydpnVacacionesLayout.createSequentialGroup()
                        .addGroup(lydpnVacacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSeleccionarSucursal10)
                            .addComponent(cmbCalculosSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(lydpnVacacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbCalculosEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSeleccionarEmpleado1))
                        .addGap(18, 18, 18)
                        .addComponent(btnCalcular))
                    .addComponent(lblSucursalSeleccionada9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(lydpnVacacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtVacaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(lydpnVacacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtAguinaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(lydpnVacacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtIndemnizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btmLimpiarCalculos)
                .addContainerGap(274, Short.MAX_VALUE))
        );

        tbdpnCalculos.addTab("Derecho De Empleados", lydpnVacaciones);

        lydpnHorasExtras.setMaximumSize(new java.awt.Dimension(900, 700));
        lydpnHorasExtras.setMinimumSize(new java.awt.Dimension(800, 600));

        lblSeleccionarEmpresa10.setText("Sueldo Mensual:");

        txtSueldoMensual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSueldoMensualKeyTyped(evt);
            }
        });

        jtblExtras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Hora normal diurna (100%)", null, null},
                {"Hora normal nocturna (125%)", null, null},
                {"Hora normal diurna dia de descanso (150%)", null, null},
                {"Hora normal nocturna dia de descanso (225%)", null, null},
                {"Hora normal diurna dia de asueto (200%)", null, null},
                {"Hora normal nocturna dia de asueto (225%)", null, null},
                {"Hora extra diurna (150%)", null, null},
                {"Hora extra nocturna (200%)", null, null},
                {"Hora extra diurna dia de descanso (250%)", null, null},
                {"Hora extra nocturna dia de descanso (300%)", null, null},
                {"Hora extra diurna dia de asueto (350%)", null, null},
                {"Hora extra nocturna dia de asueto (400%)", null, null}
            },
            new String [] {
                "Tipo de hora", "Cantidad de horas", "Pago en concepto de horas ($)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrlpnHorasExtras.setViewportView(jtblExtras);

        btnNuevoCalculo.setText("Nuevo calculo");
        btnNuevoCalculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoCalculoActionPerformed(evt);
            }
        });

        btnCalcularHorasExtras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/calculator_black.png"))); // NOI18N
        btnCalcularHorasExtras.setText("Calcular Horas Extras");
        btnCalcularHorasExtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularHorasExtrasActionPerformed(evt);
            }
        });

        jLabel4.setText("TOTAL ($):");

        lydpnHorasExtras.setLayer(lblSeleccionarEmpresa10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnHorasExtras.setLayer(txtSueldoMensual, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnHorasExtras.setLayer(scrlpnHorasExtras, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnHorasExtras.setLayer(btnNuevoCalculo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnHorasExtras.setLayer(btnCalcularHorasExtras, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnHorasExtras.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lydpnHorasExtras.setLayer(lblTotal, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout lydpnHorasExtrasLayout = new javax.swing.GroupLayout(lydpnHorasExtras);
        lydpnHorasExtras.setLayout(lydpnHorasExtrasLayout);
        lydpnHorasExtrasLayout.setHorizontalGroup(
            lydpnHorasExtrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lydpnHorasExtrasLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(lydpnHorasExtrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNuevoCalculo)
                    .addGroup(lydpnHorasExtrasLayout.createSequentialGroup()
                        .addComponent(btnCalcularHorasExtras)
                        .addGap(61, 61, 61)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(lydpnHorasExtrasLayout.createSequentialGroup()
                        .addComponent(lblSeleccionarEmpresa10)
                        .addGap(18, 18, 18)
                        .addComponent(txtSueldoMensual, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scrlpnHorasExtras, javax.swing.GroupLayout.PREFERRED_SIZE, 718, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        lydpnHorasExtrasLayout.setVerticalGroup(
            lydpnHorasExtrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lydpnHorasExtrasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lydpnHorasExtrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSeleccionarEmpresa10)
                    .addComponent(txtSueldoMensual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(scrlpnHorasExtras, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(lydpnHorasExtrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCalcularHorasExtras)
                    .addComponent(jLabel4)
                    .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnNuevoCalculo)
                .addContainerGap(237, Short.MAX_VALUE))
        );

        tbdpnCalculos.addTab("Horas Extras", lydpnHorasExtras);

        tbdpnInicio.addTab("Calculos", tbdpnCalculos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbdpnInicio, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbdpnInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarEmpActionPerformed
        cmbEmpresasDisponibles1.setSelectedIndex(0);
        cmbSucursalesDisponibles1.setSelectedIndex(0);
        txtNombresEmp.setText("");
        txtApellido1Emp.setText("");
        txtApellido2Emp.setText("");
        dtcFechaNacimiento.setDate(null);
        txtNITEmp.setText("");
        txtDUIEmp.setText("");
        txtNumISSSEmp.setText("");
        txtNumAFPEmp.setText("");
        cmbAFPEmp.setSelectedIndex(0);
        txtNumTelEmp.setText("");
        txtSueldoEmp.setText("");
        dtcFechaIngreso.setDate(null);
        cmbCargoEmp.setSelectedIndex(0);
        txtNombresEmp.requestFocus();
        btnCambioEmpleado.setVisible(false);
        btnEliminarEmp.setVisible(false);
        btnModificarEmp.setVisible(false);
        btnGuardarEmp.setVisible(true);
        desbloquearEmpleados();
        cmbEmpresasDisponibles1.setEnabled(true);
        cmbSucursalesDisponibles1.setEnabled(true);
        dtcFechaSalida.setDate(null);
        JOptionPane.showMessageDialog(null, "Campos limpiados exitosamente.");

        int posicion;
        posicion = cmbEmpresasDisponibles1.getSelectedIndex();
        posicion = posicion + 1;
        int posicionn;
        posicionn = cmbSucursalesDisponibles1.getSelectedIndex();
        posicionn = posicionn + 1;
        this.setExtendedState(MAXIMIZED_BOTH);
        rs = null;
        rs = clTra.contarRegistrosPorEmpresaYSucursal(posicion, posicionn);
        try {
            while (rs.next()) {
                cantidad = rs.getInt(1);
                if (cantidad != 0) {
                    rs = null;
                    rs = clTra.mayorRegistroPorEmpresaYSucursal(posicion, posicionn);
                    while (rs.next()) {
                        mayor = rs.getInt(1) + 1;
                        if (mayor < 10) {
                            txtIdEmpleado.setText("00" + mayor);
                        } else if (mayor < 100) {
                            this.txtIdEmpleado.setText("0" + mayor);
                        } else {
                            txtIdEmpleado.setText("" + mayor);
                        }
                    }
                } else {
                    txtIdEmpleado.setText("00" + 1);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "AVISO DEL SISTEMA", 0);
        }
    }//GEN-LAST:event_btnCancelarEmpActionPerformed

    private void btnCancelarSucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarSucActionPerformed
        txtNombreSucursal.setText("");
        txtDireccionSuc.setText("");
        cmbEmpresasDisponibles2.setSelectedIndex(0);
        txtNombreSucursal.requestFocus();
        cmbEmpresasDisponibles2.setEnabled(true);
        txtIdSucursal.setEditable(false);
        txtNombreSucursal.setEditable(true);
        txtDireccionSuc.setEditable(true);
        btnCambioSucursal.setVisible(false);
        btnEliminarSuc.setVisible(false);
        btnModificarSuc.setVisible(false);
        btnGuardarSuc.setVisible(true);
        JOptionPane.showMessageDialog(null, "Campos limpiados exitosamente.");

        int posicion;
        posicion = cmbEmpresasDisponibles2.getSelectedIndex();
        posicion = posicion + 1;
        this.setExtendedState(MAXIMIZED_BOTH);
        rs = null;
        rs = clSuc.contarRegistrosPorEmpresa(posicion);
        try {
            while (rs.next()) {
                cantidad = rs.getInt(1);
                if (cantidad != 0) {
                    rs = null;
                    rs = clSuc.mayorRegistroPorEmpresa(posicion);
                    while (rs.next()) {
                        mayor = rs.getInt(1) + 1;
                        if (mayor < 10) {
                            txtIdSucursal.setText("00" + mayor);
                        } else if (mayor < 100) {
                            this.txtIdSucursal.setText("0" + mayor);
                        } else {
                            txtIdSucursal.setText("" + mayor);
                        }
                    }
                } else {
                    txtIdSucursal.setText("00" + 1);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "AVISO DEL SISTEMA", 0);
        }
    }//GEN-LAST:event_btnCancelarSucActionPerformed

    private void btnCancelarEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarEmpresaActionPerformed
        txtNombreEmpresa.setText("");
        txtDireccionEmpresa.setText("");
        txtTelefonoEmpresa.setText("");
        txtNITEmp1.setText("");
        txtNRCEmpresa.setText("");
        cmbActEconomica.setSelectedIndex(0);
        txtRepLegalEmpresa.setText("");

        txtIdEmpresa.setEditable(false);
        txtNombreEmpresa.requestFocus();
        txtNombreEmpresa.setEditable(true);
        txtDireccionEmpresa.setEditable(true);
        txtTelefonoEmpresa.setEditable(true);
        txtNITEmp1.setEditable(true);
        txtNRCEmpresa.setEditable(true);
        cmbActEconomica.setEnabled(true);
        txtRepLegalEmpresa.setEditable(true);
        JOptionPane.showMessageDialog(null, "Campos limpiados exitosamente.");
        this.setExtendedState(MAXIMIZED_BOTH);
        txtIdEmpresa.setEditable(false);
        rs = null;
        rs = clEmpr.contarRegistros();
        try {
            while (rs.next()) {
                cantidad = rs.getInt(1);
                if (cantidad != 0) {
                    rs = null;
                    rs = clEmpr.mayorRegistro();
                    while (rs.next()) {
                        mayor = rs.getInt(1) + 1;
                        if (mayor < 10) {
                            txtIdEmpresa.setText("00" + mayor);
                        } else if (mayor < 100) {
                            this.txtIdEmpresa.setText("0" + mayor);
                        } else {
                            txtIdEmpresa.setText("" + mayor);
                        }
                    }
                } else {
                    txtIdEmpresa.setText("00" + 1);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "AVISO DEL SISTEMA", 0);
        }
    }//GEN-LAST:event_btnCancelarEmpresaActionPerformed

    private void btnNuevoCargoEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoCargoEmpActionPerformed
        cargoo.setVisible(true);
    }//GEN-LAST:event_btnNuevoCargoEmpActionPerformed

    private void txtNombresEmpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombresEmpKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if (!Character.isLetter(tecla) && (tecla != ' ' || txtNombresEmp.getText().contains(" "))) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNombresEmpKeyTyped

    private void txtApellido1EmpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellido1EmpKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if (!Character.isLetter(tecla)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtApellido1EmpKeyTyped

    private void btnGuardarEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarEmpActionPerformed
        if (txtNombresEmp.getText().equals("") || txtApellido1Emp.getText().equals("") || txtApellido2Emp.getText().equals("") || dtcFechaNacimiento.getDate() == null
                || txtNITEmp.getText().equals("") || txtDUIEmp.getText().equals("") || txtNumISSSEmp.getText().equals("")
                || txtNumAFPEmp.getText().equals("") || txtNumTelEmp.getText().equals("") || txtSueldoEmp.getText().equals("") || dtcFechaIngreso.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Informacion incompleta para poder guardar.");
        } else {
            java.util.Date fechaNac = new java.util.Date();
            fechaNac = dtcFechaNacimiento.getDate();
            java.sql.Date fechaNaci = convertUtilToSql(fechaNac);
            java.util.Date fechaIngreso = new java.util.Date();
            fechaIngreso = dtcFechaIngreso.getDate();
            java.sql.Date fechaIngre = convertUtilToSql(fechaIngreso);

            trabajador.insertarTrabajador(Integer.parseInt(lblEmpresaSeleccionada1.getText()), Integer.parseInt(lblSucursalSeleccionada1.getText()),
                    Integer.parseInt(txtIdEmpleado.getText()), txtNombresEmp.getText(), txtApellido1Emp.getText(), txtApellido2Emp.getText(), fechaNaci,
                    txtNITEmp.getText(), txtDUIEmp.getText(), txtNumISSSEmp.getText(), txtNumAFPEmp.getText(),
                    Integer.parseInt(lblAFPSeleccionado.getText()), txtNumTelEmp.getText(), Double.parseDouble(txtSueldoEmp.getText()),
                    Integer.parseInt(lblCargoSeleccionado1.getText()), fechaIngre);
            JOptionPane.showMessageDialog(null, "Trabajador registrado con éxito.");
        }
        cmbEmpresasDisponibles1.setSelectedIndex(0);
        cmbSucursalesDisponibles1.setSelectedIndex(0);
        txtNombresEmp.setText("");
        txtApellido1Emp.setText("");
        txtApellido2Emp.setText("");
        dtcFechaNacimiento.setDate(null);
        txtNITEmp.setText("");
        txtDUIEmp.setText("");
        txtNumISSSEmp.setText("");
        txtNumAFPEmp.setText("");
        cmbAFPEmp.setSelectedIndex(0);
        txtNumTelEmp.setText("");
        txtSueldoEmp.setText("");
        dtcFechaIngreso.setDate(null);
        cmbCargoEmp.setSelectedIndex(0);
        txtNombresEmp.requestFocus();

        this.setExtendedState(MAXIMIZED_BOTH);
        txtIdEmpleado.setEditable(false);
        rs = null;
        rs = clTra.contarRegistrosPorEmpresaYSucursal(1, 1);
        try {
            while (rs.next()) {
                cantidad = rs.getInt(1);
                if (cantidad != 0) {
                    rs = null;
                    rs = clTra.mayorRegistroPorEmpresaYSucursal(1, 1);
                    while (rs.next()) {
                        mayor = rs.getInt(1) + 1;
                        if (mayor < 10) {
                            txtIdEmpleado.setText("00" + mayor);
                        } else if (mayor < 100) {
                            this.txtIdEmpleado.setText("0" + mayor);
                        } else {
                            txtIdEmpleado.setText("" + mayor);
                        }
                    }
                } else {
                    txtIdEmpleado.setText("00" + 1);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "AVISO DEL SISTEMA", 0);
        }
    }//GEN-LAST:event_btnGuardarEmpActionPerformed

    private void btnGuardarSucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarSucActionPerformed
        if (txtNombreSucursal.getText().equals("") || txtDireccionSuc.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informacion incompleta para poder guardar.");
        } else {
            sucursal.insertarSucursal(Integer.parseInt(lblEmpresaSeleccionada2.getText()), Integer.parseInt(txtIdSucursal.getText()), txtNombreSucursal.getText(), txtDireccionSuc.getText());
            JOptionPane.showMessageDialog(null, "Sucursal registrada con éxito.");
        }
        txtNombreSucursal.setText("");
        txtDireccionSuc.setText("");
        cmbEmpresasDisponibles2.setSelectedIndex(0);
        txtNombreSucursal.requestFocus();

        this.setExtendedState(MAXIMIZED_BOTH);
        txtIdSucursal.setEditable(false);
        rs = null;
        rs = clSuc.contarRegistrosPorEmpresa(1);
        try {
            while (rs.next()) {
                cantidad = rs.getInt(1);
                if (cantidad != 0) {
                    rs = null;
                    rs = clSuc.mayorRegistroPorEmpresa(1);
                    while (rs.next()) {
                        mayor = rs.getInt(1) + 1;
                        if (mayor < 10) {
                            txtIdSucursal.setText("00" + mayor);
                        } else if (mayor < 100) {
                            this.txtIdSucursal.setText("0" + mayor);
                        } else {
                            txtIdSucursal.setText("" + mayor);
                        }
                    }
                } else {
                    txtIdSucursal.setText("00" + 1);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "AVISO DEL SISTEMA", 0);
        }
    }//GEN-LAST:event_btnGuardarSucActionPerformed

    private void btnGuardarEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarEmpresaActionPerformed
        if (txtNombreEmpresa.getText().equals("") || txtDireccionEmpresa.getText().equals("")
                || txtTelefonoEmpresa.getText().equals("") || txtNITEmp1.getText().equals("") || txtNRCEmpresa.getText().equals("") || txtRepLegalEmpresa.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informacion incompleta para poder guardar.");
        } else {
            empresa.insertarEmpresa(Integer.parseInt(txtIdEmpresa.getText()), txtNombreEmpresa.getText(), txtDireccionEmpresa.getText(), txtTelefonoEmpresa.getText(), txtNITEmp1.getText(), txtNRCEmpresa.getText(), cmbActEconomica.getSelectedIndex() + 1, txtRepLegalEmpresa.getText());
            JOptionPane.showMessageDialog(null, "Empresa registrada con éxito.");
        }
        txtNombreEmpresa.setText("");
        txtDireccionEmpresa.setText("");
        txtTelefonoEmpresa.setText("");
        txtNITEmp1.setText("");
        txtNRCEmpresa.setText("");
        cmbActEconomica.setSelectedIndex(0);
        txtRepLegalEmpresa.setText("");
        txtNombreEmpresa.requestFocus();

        this.setExtendedState(MAXIMIZED_BOTH);
        txtIdEmpresa.setEditable(false);
        rs = null;
        rs = clEmpr.contarRegistros();
        try {
            while (rs.next()) {
                cantidad = rs.getInt(1);
                if (cantidad != 0) {
                    rs = null;
                    rs = clEmpr.mayorRegistro();
                    while (rs.next()) {
                        mayor = rs.getInt(1) + 1;
                        if (mayor < 10) {
                            txtIdEmpresa.setText("00" + mayor);
                        } else if (mayor < 100) {
                            this.txtIdEmpresa.setText("0" + mayor);
                        } else {
                            txtIdEmpresa.setText("" + mayor);
                        }
                    }
                } else {
                    txtIdEmpresa.setText("00" + 1);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "AVISO DEL SISTEMA", 0);
        }
    }//GEN-LAST:event_btnGuardarEmpresaActionPerformed

    private void btnGuardarPlanillaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarPlanillaActionPerformed
        if (dtcDeFecha.getDate() == null || dtcAFecha.getDate() == null || btnGenerarPlanilla.isEnabled() == true) {
            JOptionPane.showMessageDialog(null, "Informacion incompleta para poder guardar.");
        } else {
            rstTrabajadores = null;
            rstTrabajadores = clPlan.contarRegistrosEmpleados(Integer.parseInt(lblEmpresaSeleccionada4.getText()), Integer.parseInt(lblSucursalSeleccionada2.getText()));
            int conteo = 0;

            int planill = Integer.parseInt(txtIdPlanilla.getText());
            int id = Integer.parseInt(lblEmpresaSeleccionada4.getText());
            int sucursals = Integer.parseInt(lblSucursalSeleccionada2.getText());

            java.util.Date UDeFecha = new java.util.Date();
            UDeFecha = dtcDeFecha.getDate();
            java.sql.Date SDeFecha = convertUtilToSql(UDeFecha);
            java.util.Date UAFecha = new java.util.Date();
            UAFecha = dtcAFecha.getDate();
            java.sql.Date SAFecha = convertUtilToSql(UAFecha);

            try {
                while (rstTrabajadores.next()) {
                    conteo = rstTrabajadores.getInt(1);
                }

            } catch (SQLException ex) {
                Logger.getLogger(frmInicio.class.getName()).log(Level.SEVERE, null, ex);
            }
            conteo = conteo - 1;

            for (int contar = 0; contar <= conteo; contar++) {
                clPlan.insertarPlanilla(planill, id, sucursals, tblPlanilla.getValueAt(contar, 0), tblPlanilla.getValueAt(contar, 1), SDeFecha, SAFecha,
                        tblPlanilla.getValueAt(contar, 2), tblPlanilla.getValueAt(contar, 3), tblPlanilla.getValueAt(contar, 4), tblPlanilla.getValueAt(contar, 5),
                        tblPlanilla.getValueAt(contar, 6), tblPlanilla.getValueAt(contar, 7), tblPlanilla.getValueAt(contar, 8), tblPlanilla.getValueAt(contar, 9),
                        tblPlanilla.getValueAt(contar, 10), tblPlanilla.getValueAt(contar, 11), tblPlanilla.getValueAt(contar, 12), tblPlanilla.getValueAt(contar, 13));
            }
            JOptionPane.showMessageDialog(null, "Planilla registrada con éxito.");
        }

        this.setExtendedState(MAXIMIZED_BOTH);
        txtIdPlanilla.setEditable(false);
        rs = null;
        rs = clPlan.contarRegistros(1, 1);
        try {
            while (rs.next()) {
                cantidad = rs.getInt(1);
                if (cantidad != 0) {
                    rs = null;
                    rs = clPlan.mayorRegistro(1, 1);
                    while (rs.next()) {
                        mayor = rs.getInt(1) + 1;
                        if (mayor < 10) {
                            txtIdPlanilla.setText("00" + mayor);
                        } else if (mayor < 100) {
                            this.txtIdPlanilla.setText("0" + mayor);
                        } else {
                            txtIdPlanilla.setText("" + mayor);
                        }
                    }
                } else {
                    txtIdPlanilla.setText("00" + 1);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "AVISO DEL SISTEMA", 0);
        }
        dtcDeFecha.setDate(null);
        dtcAFecha.setDate(null);
        reinicio();
        dtcDeFecha.setEnabled(true);
        dtcAFecha.setEnabled(true);
        cmbEmpresasDisponibles4.setEnabled(true);
        cmbSucursalesDisponibles2.setEnabled(true);
        btnGenerarPlanilla.setEnabled(true);
    }//GEN-LAST:event_btnGuardarPlanillaActionPerformed

    private void btnGuardarReciboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarReciboActionPerformed
        if (txtValorRecibo.getText().equals("") || dtcFechaRecibo.getDate() == null || txtConceptoRecibo.getText().equals("")
                || txtNombreFirmante.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informacion incompleta para poder guardar.");
        } else {
            java.util.Date fechaNac = new java.util.Date();
            fechaNac = dtcFechaRecibo.getDate();
            java.sql.Date fechaNaci = convertUtilToSql(fechaNac);
            recibo.insertarRecibo(Integer.parseInt(lblEmpresaSeleccionada6.getText()), Integer.parseInt(lblSucursalSeleccionada3.getText()),
                    Integer.parseInt(txtIdRecibo.getText()), Double.parseDouble(txtValorRecibo.getText()), fechaNaci, txtConceptoRecibo.getText(), txtNombreFirmante.getText());
            JOptionPane.showMessageDialog(null, "Recibo registrado con éxito.");
        }
        cmbEmpresasDisponibles6.setSelectedIndex(0);
        cmbSucursalesDisponibles3.setSelectedIndex(0);
        txtValorRecibo.setText("");
        dtcFechaRecibo.setDate(null);
        txtConceptoRecibo.setText("");
        txtNombreFirmante.setText("");
        txtValorRecibo.requestFocus();
    }//GEN-LAST:event_btnGuardarReciboActionPerformed

    private void btnCancelarReciboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarReciboActionPerformed
        cmbEmpresasDisponibles6.setSelectedIndex(0);
        cmbSucursalesDisponibles3.setSelectedIndex(0);
        txtValorRecibo.setText("");
        dtcFechaRecibo.setDate(null);
        txtConceptoRecibo.setText("");
        txtNombreFirmante.setText("");
        txtValorRecibo.requestFocus();
        JOptionPane.showMessageDialog(null, "Campos limpiados exitosamente.");
    }//GEN-LAST:event_btnCancelarReciboActionPerformed

    private void btnCancelarPlanillaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarPlanillaActionPerformed
        dtcDeFecha.setDate(null);
        dtcAFecha.setDate(null);
        reinicio();
        JOptionPane.showMessageDialog(null, "Campos limpiados exitosamente.");
        dtcDeFecha.setEnabled(true);
        dtcAFecha.setEnabled(true);
        cmbEmpresasDisponibles4.setEnabled(true);
        cmbSucursalesDisponibles2.setEnabled(true);
        btnGenerarPlanilla.setEnabled(true);
    }//GEN-LAST:event_btnCancelarPlanillaActionPerformed

    private void cmbActEconomicaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbActEconomicaItemStateChanged
        int posicion;
        posicion = cmbActEconomica.getSelectedIndex();
        lblActEcoSeleccionada.setText(String.valueOf(actEco.getElementAt(posicion)));
    }//GEN-LAST:event_cmbActEconomicaItemStateChanged

    private void cmbEmpresasDisponibles2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbEmpresasDisponibles2ItemStateChanged
        int posicion;
        posicion = cmbEmpresasDisponibles2.getSelectedIndex();
        lblEmpresaSeleccionada2.setText(String.valueOf(empresas.getElementAt(posicion)));
        posicion = posicion + 1;
        this.setExtendedState(MAXIMIZED_BOTH);
        rs = null;
        rs = clSuc.contarRegistrosPorEmpresa(posicion);
        try {
            while (rs.next()) {
                cantidad = rs.getInt(1);
                if (cantidad != 0) {
                    rs = null;
                    rs = clSuc.mayorRegistroPorEmpresa(posicion);
                    while (rs.next()) {
                        mayor = rs.getInt(1) + 1;
                        if (mayor < 10) {
                            txtIdSucursal.setText("00" + mayor);
                        } else if (mayor < 100) {
                            this.txtIdSucursal.setText("0" + mayor);
                        } else {
                            txtIdSucursal.setText("" + mayor);
                        }
                    }
                } else {
                    txtIdSucursal.setText("00" + 1);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "AVISO DEL SISTEMA", 0);
        }

    }//GEN-LAST:event_cmbEmpresasDisponibles2ItemStateChanged

    private void cmbEmpresasDisponibles1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbEmpresasDisponibles1ItemStateChanged
        int posicion;
        posicion = cmbEmpresasDisponibles1.getSelectedIndex();
        lblEmpresaSeleccionada1.setText(String.valueOf(empresas.getElementAt(posicion)));

        posicion = posicion + 1;
        int posicionn;
        posicionn = cmbSucursalesDisponibles1.getSelectedIndex();
        posicionn = posicionn + 1;
        this.setExtendedState(MAXIMIZED_BOTH);
        rs = null;
        rs = clTra.contarRegistrosPorEmpresaYSucursal(posicion, posicionn);
        try {
            while (rs.next()) {
                cantidad = rs.getInt(1);
                if (cantidad != 0) {
                    rs = null;
                    rs = clTra.mayorRegistroPorEmpresaYSucursal(posicion, posicionn);
                    while (rs.next()) {
                        mayor = rs.getInt(1) + 1;
                        if (mayor < 10) {
                            txtIdEmpleado.setText("00" + mayor);
                        } else if (mayor < 100) {
                            this.txtIdEmpleado.setText("0" + mayor);
                        } else {
                            txtIdEmpleado.setText("" + mayor);
                        }
                    }
                } else {
                    txtIdEmpleado.setText("00" + 1);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "AVISO DEL SISTEMA", 0);
        }
    }//GEN-LAST:event_cmbEmpresasDisponibles1ItemStateChanged

    private void cmbEmpresasDisponibles6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbEmpresasDisponibles6ItemStateChanged
        int posicion;
        posicion = cmbEmpresasDisponibles6.getSelectedIndex();
        lblEmpresaSeleccionada6.setText(String.valueOf(empresas.getElementAt(posicion)));

        posicion = posicion + 1;
        int posicionn;
        posicionn = cmbSucursalesDisponibles3.getSelectedIndex();
        posicionn = posicionn + 1;
        this.setExtendedState(MAXIMIZED_BOTH);
        rs = null;
        rs = clRec.contarRecibosPorEmpresaYSucursal(posicion, posicionn);
        try {
            while (rs.next()) {
                cantidad = rs.getInt(1);
                if (cantidad != 0) {
                    rs = null;
                    rs = clRec.mayorRecibosPorEmpresaYSucursal(posicion, posicionn);
                    while (rs.next()) {
                        mayor = rs.getInt(1) + 1;
                        if (mayor < 10) {
                            txtIdRecibo.setText("00" + mayor);
                        } else if (mayor < 100) {
                            this.txtIdRecibo.setText("0" + mayor);
                        } else {
                            txtIdRecibo.setText("" + mayor);
                        }
                    }
                } else {
                    txtIdRecibo.setText("00" + 1);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "AVISO DEL SISTEMA", 0);
        }

    }//GEN-LAST:event_cmbEmpresasDisponibles6ItemStateChanged

    private void cmbEmpresasDisponibles4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbEmpresasDisponibles4ItemStateChanged
        int posicion;
        posicion = cmbEmpresasDisponibles4.getSelectedIndex();
        lblEmpresaSeleccionada4.setText(String.valueOf(empresas.getElementAt(posicion)));
        posicion = posicion + 1;
        int posicionn;
        posicionn = cmbSucursalesDisponibles2.getSelectedIndex();
        posicionn = posicionn + 1;
        this.setExtendedState(MAXIMIZED_BOTH);
        txtIdPlanilla.setEditable(false);
        rs = null;
        rs = clPlan.contarRegistros(posicion, posicionn);
        try {
            while (rs.next()) {
                cantidad = rs.getInt(1);
                if (cantidad != 0) {
                    rs = null;
                    rs = clPlan.mayorRegistro(posicion, posicionn);
                    while (rs.next()) {
                        mayor = rs.getInt(1) + 1;
                        if (mayor < 10) {
                            txtIdPlanilla.setText("00" + mayor);
                        } else if (mayor < 100) {
                            this.txtIdPlanilla.setText("0" + mayor);
                        } else {
                            txtIdPlanilla.setText("" + mayor);
                        }
                    }
                } else {
                    txtIdPlanilla.setText("00" + 1);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "AVISO DEL SISTEMA", 0);
        }
    }//GEN-LAST:event_cmbEmpresasDisponibles4ItemStateChanged

    private void cmbCalculosEmpresaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCalculosEmpresaItemStateChanged
        int posicion;
        posicion = cmbCalculosEmpresa.getSelectedIndex();
        lblEmpresaSeleccionada8.setText(String.valueOf(empresas.getElementAt(posicion)));
    }//GEN-LAST:event_cmbCalculosEmpresaItemStateChanged

    private void cmbSucursalesDisponibles1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbSucursalesDisponibles1ItemStateChanged
        int posicion;
        posicion = cmbSucursalesDisponibles1.getSelectedIndex();
        lblSucursalSeleccionada1.setText(String.valueOf(sucursales.getElementAt(posicion)));
        posicion = posicion + 1;
        int posicionn;
        posicionn = cmbEmpresasDisponibles1.getSelectedIndex();
        posicionn = posicionn + 1;
        this.setExtendedState(MAXIMIZED_BOTH);
        rs = null;
        rs = clTra.contarRegistrosPorEmpresaYSucursal(posicionn, posicion);
        try {
            while (rs.next()) {
                cantidad = rs.getInt(1);
                if (cantidad != 0) {
                    rs = null;
                    rs = clTra.mayorRegistroPorEmpresaYSucursal(posicionn, posicion);
                    while (rs.next()) {
                        mayor = rs.getInt(1) + 1;
                        if (mayor < 10) {
                            txtIdEmpleado.setText("00" + mayor);
                        } else if (mayor < 100) {
                            this.txtIdEmpleado.setText("0" + mayor);
                        } else {
                            txtIdEmpleado.setText("" + mayor);
                        }
                    }
                } else {
                    txtIdEmpleado.setText("00" + 1);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "AVISO DEL SISTEMA", 0);
        }
    }//GEN-LAST:event_cmbSucursalesDisponibles1ItemStateChanged

    private void cmbAFPEmpItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbAFPEmpItemStateChanged
        int posicion;
        posicion = cmbAFPEmp.getSelectedIndex();
        lblAFPSeleccionado.setText(String.valueOf(afps.getElementAt(posicion)));
    }//GEN-LAST:event_cmbAFPEmpItemStateChanged

    private void cmbSucursalesDisponibles2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbSucursalesDisponibles2ItemStateChanged
        int posicion;
        posicion = cmbSucursalesDisponibles2.getSelectedIndex();
        lblSucursalSeleccionada2.setText(String.valueOf(sucursales.getElementAt(posicion)));
        posicion = posicion + 1;
        int posicionn;
        posicionn = cmbEmpresasDisponibles4.getSelectedIndex();
        posicionn = posicionn + 1;
        this.setExtendedState(MAXIMIZED_BOTH);
        txtIdPlanilla.setEditable(false);
        rs = null;
        rs = clPlan.contarRegistros(posicionn, posicion);
        try {
            while (rs.next()) {
                cantidad = rs.getInt(1);
                if (cantidad != 0) {
                    rs = null;
                    rs = clPlan.mayorRegistro(posicionn, posicion);
                    while (rs.next()) {
                        mayor = rs.getInt(1) + 1;
                        if (mayor < 10) {
                            txtIdPlanilla.setText("00" + mayor);
                        } else if (mayor < 100) {
                            this.txtIdPlanilla.setText("0" + mayor);
                        } else {
                            txtIdPlanilla.setText("" + mayor);
                        }
                    }
                } else {
                    txtIdPlanilla.setText("00" + 1);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "AVISO DEL SISTEMA", 0);
        }
    }//GEN-LAST:event_cmbSucursalesDisponibles2ItemStateChanged

    private void cmbSucursalesDisponibles3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbSucursalesDisponibles3ItemStateChanged
        int posicion;
        posicion = cmbSucursalesDisponibles3.getSelectedIndex();
        lblSucursalSeleccionada3.setText(String.valueOf(sucursales.getElementAt(posicion)));

        posicion = posicion + 1;
        int posicionn;
        posicionn = cmbEmpresasDisponibles6.getSelectedIndex();
        posicionn = posicionn + 1;
        this.setExtendedState(MAXIMIZED_BOTH);
        rs = null;
        rs = clRec.contarRecibosPorEmpresaYSucursal(posicionn, posicion);
        try {
            while (rs.next()) {
                cantidad = rs.getInt(1);
                if (cantidad != 0) {
                    rs = null;
                    rs = clRec.mayorRecibosPorEmpresaYSucursal(posicionn, posicion);
                    while (rs.next()) {
                        mayor = rs.getInt(1) + 1;
                        if (mayor < 10) {
                            txtIdRecibo.setText("00" + mayor);
                        } else if (mayor < 100) {
                            this.txtIdRecibo.setText("0" + mayor);
                        } else {
                            txtIdRecibo.setText("" + mayor);
                        }
                    }
                } else {
                    txtIdRecibo.setText("00" + 1);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "AVISO DEL SISTEMA", 0);
        }
    }//GEN-LAST:event_cmbSucursalesDisponibles3ItemStateChanged

    private void txtSueldoEmpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSueldoEmpKeyTyped
        char caracter = evt.getKeyChar();
        if (((caracter < '0') || (caracter > '9'))
                && (!Character.isDigit(caracter))
                && (caracter != '.' || txtSueldoEmp.getText().contains("."))) {
            evt.consume();
        }
    }//GEN-LAST:event_txtSueldoEmpKeyTyped

    private void txtSueldoEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSueldoEmpActionPerformed

    }//GEN-LAST:event_txtSueldoEmpActionPerformed

    private void txtNombreFirmanteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreFirmanteKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if (!Character.isLetter(tecla) && (tecla != ' ' || txtNombreFirmante.getText().contains(" "))) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreFirmanteKeyTyped

    private void txtNRCEmpresaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNRCEmpresaKeyTyped
        char caracter = evt.getKeyChar();
        if (((caracter < '0') || (caracter > '9'))
                && (!Character.isDigit(caracter))
                && (caracter != '-' || txtSueldoEmp.getText().contains("-"))) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNRCEmpresaKeyTyped

    private void txtValorReciboKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorReciboKeyTyped
        char caracter = evt.getKeyChar();
        if (((caracter < '0') || (caracter > '9'))
                && (!Character.isDigit(caracter))
                && (caracter != '.' || txtValorRecibo.getText().contains("."))) {
            evt.consume();
        }
    }//GEN-LAST:event_txtValorReciboKeyTyped

    private void btnAgregarActEconActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActEconActionPerformed
        activEco.setVisible(true);
    }//GEN-LAST:event_btnAgregarActEconActionPerformed

    private void cmbCargoEmpItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCargoEmpItemStateChanged
        int posicion;
        posicion = cmbCargoEmp.getSelectedIndex();
        lblCargoSeleccionado1.setText(String.valueOf(cargos.getElementAt(posicion)));
    }//GEN-LAST:event_cmbCargoEmpItemStateChanged

    private void txtApellido2EmpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellido2EmpKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if (!Character.isLetter(tecla)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtApellido2EmpKeyTyped

    private void btnConsultarEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarEmpActionPerformed
        String codigoEmpr = JOptionPane.showInputDialog("Ingrese el id de la empresa.");
        String codigoSuc = JOptionPane.showInputDialog("Ingrese el id de la sucursal.");
        String codigoEmpl = JOptionPane.showInputDialog("Ingrese el id del empleado.");
        cargarSucursales(Integer.parseInt(codigoEmpr));
        rs = null;
        rs = trabajador.buscar(codigoEmpr, codigoSuc, codigoEmpl);
        try {
            if (rs.next()) {
                this.cmbEmpresasDisponibles1.setSelectedIndex(Integer.parseInt(rs.getString(1)) - 1);
                this.cmbSucursalesDisponibles1.setSelectedIndex(Integer.parseInt(rs.getString(2)) - 1);
                System.out.print("Bien");
                this.lblEmpresaSeleccionada1.setText(rs.getString(1));
                this.lblSucursalSeleccionada1.setText(rs.getString(2));
                this.txtIdEmpleado.setText("" + codigoEmpl);
                this.txtNombresEmp.setText(rs.getString(4));
                this.txtApellido1Emp.setText(rs.getString(5));
                this.txtApellido2Emp.setText(rs.getString(6));
                this.dtcFechaNacimiento.setDate(rs.getDate(7));
                this.txtNITEmp.setText(rs.getString(8));
                this.txtDUIEmp.setText(rs.getString(9));
                this.txtNumISSSEmp.setText(rs.getString(10));
                this.txtNumAFPEmp.setText(rs.getString(11));
                this.cmbAFPEmp.setSelectedIndex(Integer.parseInt(rs.getString(12)) - 1);
                this.lblAFPSeleccionado.setText(rs.getString(12));
                this.txtNumTelEmp.setText(rs.getString(13));
                this.txtSueldoEmp.setText(rs.getString(14));
                this.cmbCargoEmp.setSelectedIndex(Integer.parseInt(rs.getString(15)) - 1);
                this.lblCargoSeleccionado1.setText(rs.getString(15));
                this.dtcFechaIngreso.setDate(rs.getDate(16));
                System.out.print("Bien");
            }
            prueba = true;
            registro = true;
            newRecord = false;
            if (registro == false) {
                JOptionPane.showMessageDialog(rootPane, "Registro no encontrado!!!", "AVISO DEL SISTEMA", 0);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "AVISO DEL SISTEMA", 0);
        }
        btnEliminarEmp.setVisible(true);
        btnModificarEmp.setVisible(true);
        btnGuardarEmp.setVisible(false);
        btnCambioEmpleado.setVisible(true);
        bloquearEmpleados();
        registro = false;
    }//GEN-LAST:event_btnConsultarEmpActionPerformed

    private void btnModificarEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarEmpActionPerformed
        desbloquearEmpleados();
    }//GEN-LAST:event_btnModificarEmpActionPerformed

    private void btnEliminarEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarEmpActionPerformed
        if (txtNombresEmp.getText().equals("") || txtApellido1Emp.getText().equals("") || txtApellido2Emp.getText().equals("") || dtcFechaNacimiento.getDate() == null
                || txtNITEmp.getText().equals("") || txtDUIEmp.getText().equals("") || txtNumISSSEmp.getText().equals("")
                || txtNumAFPEmp.getText().equals("") || txtNumTelEmp.getText().equals("") || txtSueldoEmp.getText().equals("") || dtcFechaIngreso.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Informacion incompleta para poder guardar.");
        } else {
            int idEmp = this.cmbEmpresasDisponibles1.getSelectedIndex();
            idEmp = idEmp + 1;
            int idSuc = this.cmbSucursalesDisponibles1.getSelectedIndex();
            idSuc = idSuc + 1;
            String idTrab = this.txtIdEmpleado.getText();
            if (JOptionPane.showConfirmDialog(rootPane, "¿Desea eliminar el registro búscado?") == 0) {
                clTra.eliminar(idEmp, idSuc, idTrab);
                JOptionPane.showMessageDialog(rootPane, "Registro eliminado exitosamente!!", "AVISO DEL SISTEMA", 1);
            }

            btnCambioEmpleado.setVisible(false);
            btnEliminarEmp.setVisible(false);
            btnModificarEmp.setVisible(false);
            btnGuardarEmp.setVisible(true);
            cmbEmpresasDisponibles1.setSelectedIndex(0);
            cmbSucursalesDisponibles1.setSelectedIndex(0);
            txtNombresEmp.setText("");
            txtApellido1Emp.setText("");
            txtApellido2Emp.setText("");
            dtcFechaNacimiento.setDate(null);
            txtNITEmp.setText("");
            txtDUIEmp.setText("");
            txtNumISSSEmp.setText("");
            txtNumAFPEmp.setText("");
            cmbAFPEmp.setSelectedIndex(0);
            txtNumTelEmp.setText("");
            txtSueldoEmp.setText("");
            dtcFechaIngreso.setDate(null);
            dtcFechaSalida.setDate(null);
            cmbCargoEmp.setSelectedIndex(0);
            txtNombresEmp.requestFocus();
            txtIdEmpleado.setEditable(false);
            desbloquearEmpleados();
            dtcFechaSalida.setDate(null);
            txtIdEmpleado.setEditable(false);
            cmbEmpresasDisponibles1.setEnabled(true);
            cmbSucursalesDisponibles1.setEnabled(true);
        }
        int posicion;
        posicion = cmbEmpresasDisponibles1.getSelectedIndex();
        posicion = posicion + 1;
        int posicionn;
        posicionn = cmbSucursalesDisponibles1.getSelectedIndex();
        posicionn = posicionn + 1;
        this.setExtendedState(MAXIMIZED_BOTH);
        rs = null;
        rs = clTra.contarRegistrosPorEmpresaYSucursal(posicion, posicionn);
        try {
            while (rs.next()) {
                cantidad = rs.getInt(1);
                if (cantidad != 0) {
                    rs = null;
                    rs = clTra.mayorRegistroPorEmpresaYSucursal(posicion, posicionn);
                    while (rs.next()) {
                        mayor = rs.getInt(1) + 1;
                        if (mayor < 10) {
                            txtIdEmpleado.setText("00" + mayor);
                        } else if (mayor < 100) {
                            this.txtIdEmpleado.setText("0" + mayor);
                        } else {
                            txtIdEmpleado.setText("" + mayor);
                        }
                    }
                } else {
                    txtIdEmpleado.setText("00" + 1);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "AVISO DEL SISTEMA", 0);
        }
    }//GEN-LAST:event_btnEliminarEmpActionPerformed

    private void btnCambioEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambioEmpleadoActionPerformed
        if (txtNombresEmp.getText().equals("") || txtApellido1Emp.getText().equals("") || txtApellido2Emp.getText().equals("") || dtcFechaNacimiento.getDate() == null
                || txtNITEmp.getText().equals("") || txtDUIEmp.getText().equals("") || txtNumISSSEmp.getText().equals("")
                || txtNumAFPEmp.getText().equals("") || txtNumTelEmp.getText().equals("") || txtSueldoEmp.getText().equals("") || dtcFechaIngreso.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Informacion incompleta para poder guardar.");
        } else {
            java.util.Date fechaNac = new java.util.Date();
            fechaNac = dtcFechaNacimiento.getDate();
            java.sql.Date fechaNaci = convertUtilToSql(fechaNac);
            java.util.Date fechaIngreso = new java.util.Date();
            fechaIngreso = dtcFechaIngreso.getDate();
            java.sql.Date fechaIngre = convertUtilToSql(fechaIngreso);

            if (dtcFechaSalida.getDate() == null) {
                clTra.modificar2(Integer.parseInt(lblEmpresaSeleccionada1.getText()), Integer.parseInt(lblSucursalSeleccionada1.getText()),
                        Integer.parseInt(txtIdEmpleado.getText()), txtNombresEmp.getText(), txtApellido1Emp.getText(), txtApellido2Emp.getText(), fechaNaci,
                        txtNITEmp.getText(), txtDUIEmp.getText(), txtNumISSSEmp.getText(), txtNumAFPEmp.getText(),
                        Integer.parseInt(lblAFPSeleccionado.getText()), txtNumTelEmp.getText(), Double.parseDouble(txtSueldoEmp.getText()),
                        Integer.parseInt(lblCargoSeleccionado1.getText()), fechaIngre);
                JOptionPane.showMessageDialog(null, "Trabajador modificado con éxito.");
            } else {
                java.util.Date fechaSalida = new java.util.Date();
                fechaSalida = dtcFechaSalida.getDate();
                java.sql.Date fechaSalid = convertUtilToSql(fechaSalida);
                clTra.modificar(Integer.parseInt(lblEmpresaSeleccionada1.getText()), Integer.parseInt(lblSucursalSeleccionada1.getText()),
                        Integer.parseInt(txtIdEmpleado.getText()), txtNombresEmp.getText(), txtApellido1Emp.getText(), txtApellido2Emp.getText(), fechaNaci,
                        txtNITEmp.getText(), txtDUIEmp.getText(), txtNumISSSEmp.getText(), txtNumAFPEmp.getText(),
                        Integer.parseInt(lblAFPSeleccionado.getText()), txtNumTelEmp.getText(), Double.parseDouble(txtSueldoEmp.getText()),
                        Integer.parseInt(lblCargoSeleccionado1.getText()), fechaIngre, fechaSalid);
                JOptionPane.showMessageDialog(null, "Trabajador modificado con éxito.");
            }

            btnCambioEmpleado.setVisible(false);
            btnEliminarEmp.setVisible(false);
            btnModificarEmp.setVisible(false);
            btnGuardarEmp.setVisible(true);
            cmbEmpresasDisponibles1.setSelectedIndex(0);
            cmbSucursalesDisponibles1.setSelectedIndex(0);
            txtNombresEmp.setText("");
            txtApellido1Emp.setText("");
            txtApellido2Emp.setText("");
            dtcFechaNacimiento.setDate(null);
            txtNITEmp.setText("");
            txtDUIEmp.setText("");
            txtNumISSSEmp.setText("");
            txtNumAFPEmp.setText("");
            cmbAFPEmp.setSelectedIndex(0);
            txtNumTelEmp.setText("");
            txtSueldoEmp.setText("");
            dtcFechaIngreso.setDate(null);
            dtcFechaSalida.setDate(null);
            cmbCargoEmp.setSelectedIndex(0);
            txtNombresEmp.requestFocus();
            txtIdEmpleado.setEditable(false);
            desbloquearEmpleados();
            dtcFechaSalida.setDate(null);
            cmbEmpresasDisponibles1.setEnabled(true);
            cmbSucursalesDisponibles1.setEnabled(true);
        }

        int posicion;
        posicion = cmbEmpresasDisponibles1.getSelectedIndex();
        posicion = posicion + 1;
        int posicionn;
        posicionn = cmbSucursalesDisponibles1.getSelectedIndex();
        posicionn = posicionn + 1;
        this.setExtendedState(MAXIMIZED_BOTH);
        rs = null;
        rs = clTra.contarRegistrosPorEmpresaYSucursal(posicion, posicionn);
        try {
            while (rs.next()) {
                cantidad = rs.getInt(1);
                if (cantidad != 0) {
                    rs = null;
                    rs = clTra.mayorRegistroPorEmpresaYSucursal(posicion, posicionn);
                    while (rs.next()) {
                        mayor = rs.getInt(1) + 1;
                        if (mayor < 10) {
                            txtIdEmpleado.setText("00" + mayor);
                        } else if (mayor < 100) {
                            this.txtIdEmpleado.setText("0" + mayor);
                        } else {
                            txtIdEmpleado.setText("" + mayor);
                        }
                    }
                } else {
                    txtIdEmpleado.setText("00" + 1);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "AVISO DEL SISTEMA", 0);
        }
    }//GEN-LAST:event_btnCambioEmpleadoActionPerformed

    private void btnConsultarSucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarSucActionPerformed
        String codigoEmpr = JOptionPane.showInputDialog("Ingrese el id de la empresa.");
        String codigoSuc = JOptionPane.showInputDialog("Ingrese el id de la sucursal.");
        rs = null;
        rs = sucursal.buscar(codigoEmpr, codigoSuc);
        try {
            while (rs.next()) {
                this.cmbEmpresasDisponibles2.setSelectedIndex(Integer.parseInt(codigoEmpr) - 1);
                this.lblEmpresaSeleccionada2.setText(rs.getString(1));
                this.txtIdSucursal.setText(rs.getString(2));
                this.txtNombreSucursal.setText(rs.getString(3));
                this.txtDireccionSuc.setText(rs.getString(4));
                prueba = true;
                registro = true;
                newRecord = false;
            }
            if (registro == false) {
                JOptionPane.showMessageDialog(rootPane, "Registro no encontrado!!!", "AVISO DEL SISTEMA", 0);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "AVISO DEL SISTEMA", 0);
        }
        btnEliminarSuc.setVisible(true);
        btnModificarSuc.setVisible(true);
        btnGuardarSuc.setVisible(false);
        btnCambioSucursal.setVisible(true);
        cmbEmpresasDisponibles2.setEnabled(false);
        txtIdSucursal.setEditable(false);
        txtNombreSucursal.setEditable(false);
        txtDireccionSuc.setEditable(false);
        registro = false;
    }//GEN-LAST:event_btnConsultarSucActionPerformed

    private void btnModificarSucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarSucActionPerformed
        cmbEmpresasDisponibles2.setEnabled(false);
        txtIdSucursal.setEditable(false);
        txtNombreSucursal.setEditable(true);
        txtDireccionSuc.setEditable(true);
        txtNombreSucursal.requestFocus();
    }//GEN-LAST:event_btnModificarSucActionPerformed

    private void btnEliminarSucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarSucActionPerformed
        if (txtNombreSucursal.getText().equals("") || txtDireccionSuc.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informacion incompleta para poder eliminar.");
        } else {
            String codigo = this.txtIdEmpleado.getText();
            if (JOptionPane.showConfirmDialog(rootPane, "¿Desea eliminar el registro búscado?") == 0) {
                clSuc.eliminar(codigo);
                JOptionPane.showMessageDialog(rootPane, "Registro eliminado exitosamente!!", "AVISO DEL SISTEMA", 1);
            }

            btnCambioSucursal.setVisible(false);
            btnEliminarSuc.setVisible(false);
            btnModificarSuc.setVisible(false);
            btnGuardarSuc.setVisible(true);
            cmbEmpresasDisponibles2.setEnabled(true);
            cmbEmpresasDisponibles2.setSelectedIndex(0);
            txtNombreSucursal.setText("");
            txtNombreSucursal.requestFocus();
            txtDireccionSuc.setText("");
            cmbEmpresasDisponibles2.setEnabled(true);
            txtIdSucursal.setEditable(false);
            txtNombreSucursal.setEditable(true);
            txtDireccionSuc.setEditable(true);
        }
        int posicion;
        posicion = cmbEmpresasDisponibles2.getSelectedIndex();
        posicion = posicion + 1;
        this.setExtendedState(MAXIMIZED_BOTH);
        rs = null;
        rs = clSuc.contarRegistrosPorEmpresa(posicion);
        try {
            while (rs.next()) {
                cantidad = rs.getInt(1);
                if (cantidad != 0) {
                    rs = null;
                    rs = clSuc.mayorRegistroPorEmpresa(posicion);
                    while (rs.next()) {
                        mayor = rs.getInt(1) + 1;
                        if (mayor < 10) {
                            txtIdSucursal.setText("00" + mayor);
                        } else if (mayor < 100) {
                            this.txtIdSucursal.setText("0" + mayor);
                        } else {
                            txtIdSucursal.setText("" + mayor);
                        }
                    }
                } else {
                    txtIdSucursal.setText("00" + 1);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "AVISO DEL SISTEMA", 0);
        }
    }//GEN-LAST:event_btnEliminarSucActionPerformed

    private void btnCambioSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambioSucursalActionPerformed
        if (txtNombreSucursal.getText().equals("") || txtDireccionSuc.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informacion incompleta para poder guardar.");
        } else {
            clSuc.modificar(Integer.parseInt(lblEmpresaSeleccionada2.getText()),
                    Integer.parseInt(txtIdSucursal.getText()), txtNombreSucursal.getText(), txtDireccionSuc.getText());
            JOptionPane.showMessageDialog(null, "Sucursal modificada con éxito.");

            btnCambioSucursal.setVisible(false);
            btnEliminarSuc.setVisible(false);
            btnModificarSuc.setVisible(false);
            btnGuardarSuc.setVisible(true);
            cmbEmpresasDisponibles2.setEnabled(true);
            cmbEmpresasDisponibles2.setSelectedIndex(0);
            txtNombreSucursal.setText("");
            txtNombreSucursal.requestFocus();
            txtDireccionSuc.setText("");

            cmbEmpresasDisponibles2.setEnabled(true);
            txtIdSucursal.setEditable(false);
            txtNombreSucursal.setEditable(true);
            txtDireccionSuc.setEditable(true);

        }

        int posicion;
        posicion = cmbEmpresasDisponibles2.getSelectedIndex();
        posicion = posicion + 1;
        this.setExtendedState(MAXIMIZED_BOTH);
        rs = null;
        rs = clSuc.contarRegistrosPorEmpresa(posicion);
        try {
            while (rs.next()) {
                cantidad = rs.getInt(1);
                if (cantidad != 0) {
                    rs = null;
                    rs = clSuc.mayorRegistroPorEmpresa(posicion);
                    while (rs.next()) {
                        mayor = rs.getInt(1) + 1;
                        if (mayor < 10) {
                            txtIdSucursal.setText("00" + mayor);
                        } else if (mayor < 100) {
                            this.txtIdSucursal.setText("0" + mayor);
                        } else {
                            txtIdSucursal.setText("" + mayor);
                        }
                    }
                } else {
                    txtIdSucursal.setText("00" + 1);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "AVISO DEL SISTEMA", 0);
        }
    }//GEN-LAST:event_btnCambioSucursalActionPerformed

    private void btnConsultarEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarEmpresaActionPerformed
        String idEmpresa = JOptionPane.showInputDialog("Ingrese el id de la empresa.");
        rs = null;
        rs = empresa.buscar(idEmpresa);
        try {
            while (rs.next()) {
                this.txtIdEmpresa.setText(rs.getString(1));
                this.txtNombreEmpresa.setText(rs.getString(2));
                this.txtDireccionEmpresa.setText(rs.getString(3));
                this.txtTelefonoEmpresa.setText(rs.getString(4));
                this.txtNITEmp1.setText(rs.getString(5));
                this.txtNRCEmpresa.setText(rs.getString(6));
                this.cmbActEconomica.setSelectedIndex(Integer.parseInt(rs.getString(7)) - 1);
                this.lblActEcoSeleccionada.setText(rs.getString(7));
                this.txtRepLegalEmpresa.setText(rs.getString(8));
                prueba = true;
                registro = true;
                newRecord = false;
            }
            if (registro == false) {
                JOptionPane.showMessageDialog(rootPane, "Registro no encontrado!!!", "AVISO DEL SISTEMA", 0);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "AVISO DEL SISTEMA", 0);
        }

        btnEliminarEmpresa.setVisible(true);
        btnModificarEmpresa.setVisible(true);
        btnGuardarEmpresa.setVisible(false);
        btnCambioEmpresa.setVisible(true);
        txtIdEmpresa.setEditable(false);
        txtNombreEmpresa.setEditable(false);
        txtNombreEmpresa.requestFocus();
        txtDireccionEmpresa.setEditable(false);
        txtTelefonoEmpresa.setEditable(false);
        txtNITEmp1.setEditable(false);
        txtNRCEmpresa.setEditable(false);
        cmbActEconomica.setEnabled(false);
        txtRepLegalEmpresa.setEditable(false);
        registro = false;
    }//GEN-LAST:event_btnConsultarEmpresaActionPerformed

    private void btnModificarEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarEmpresaActionPerformed
        btnEliminarEmpresa.setVisible(true);
        btnModificarEmpresa.setVisible(true);
        btnGuardarEmpresa.setVisible(false);
        btnCambioEmpresa.setVisible(true);
        txtIdEmpresa.setEditable(false);
        txtNombreEmpresa.requestFocus();
        txtNombreEmpresa.setEditable(true);
        txtDireccionEmpresa.setEditable(true);
        txtTelefonoEmpresa.setEditable(true);
        txtNITEmp1.setEditable(true);
        txtNRCEmpresa.setEditable(true);
        cmbActEconomica.setEnabled(true);
        txtRepLegalEmpresa.setEditable(true);
    }//GEN-LAST:event_btnModificarEmpresaActionPerformed

    private void btnEliminarEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarEmpresaActionPerformed
        if (txtNombreEmpresa.getText().equals("") || txtDireccionEmpresa.getText().equals("")
                || txtTelefonoEmpresa.getText().equals("") || txtNITEmp1.getText().equals("") || txtNRCEmpresa.getText().equals("") || txtRepLegalEmpresa.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informacion incompleta para poder eliminar.");
        } else {
            String codigo = this.txtIdEmpresa.getText();
            if (JOptionPane.showConfirmDialog(rootPane, "¿Desea eliminar el registro búscado?") == 0) {
                clEmpr.eliminar(codigo);
                JOptionPane.showMessageDialog(rootPane, "Registro eliminado exitosamente!!", "AVISO DEL SISTEMA", 1);
            }
            btnCambioEmpresa.setVisible(false);
            btnEliminarEmpresa.setVisible(false);
            btnModificarEmpresa.setVisible(false);
            btnGuardarEmpresa.setVisible(true);
            cmbActEconomica.setSelectedIndex(0);
            txtNombreEmpresa.setText("");
            txtDireccionEmpresa.setText("");
            txtTelefonoEmpresa.setText("");
            txtNITEmp1.setText("");
            txtNRCEmpresa.setText("");
            txtRepLegalEmpresa.setText("");
            txtNombreEmpresa.requestFocus();
            txtIdEmpresa.setEditable(false);

            txtIdEmpresa.setEditable(false);
            txtNombreEmpresa.requestFocus();
            txtNombreEmpresa.setEditable(true);
            txtDireccionEmpresa.setEditable(true);
            txtTelefonoEmpresa.setEditable(true);
            txtNITEmp1.setEditable(true);
            txtNRCEmpresa.setEditable(true);
            cmbActEconomica.setEnabled(true);
            txtRepLegalEmpresa.setEditable(true);
        }
        this.setExtendedState(MAXIMIZED_BOTH);
        txtIdEmpresa.setEditable(false);
        rs = null;
        rs = clEmpr.contarRegistros();
        try {
            while (rs.next()) {
                cantidad = rs.getInt(1);
                if (cantidad != 0) {
                    rs = null;
                    rs = clEmpr.mayorRegistro();
                    while (rs.next()) {
                        mayor = rs.getInt(1) + 1;
                        if (mayor < 10) {
                            txtIdEmpresa.setText("00" + mayor);
                        } else if (mayor < 100) {
                            this.txtIdEmpresa.setText("0" + mayor);
                        } else {
                            txtIdEmpresa.setText("" + mayor);
                        }
                    }
                } else {
                    txtIdEmpresa.setText("00" + 1);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "AVISO DEL SISTEMA", 0);
        }
    }//GEN-LAST:event_btnEliminarEmpresaActionPerformed

    private void btnCambioEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambioEmpresaActionPerformed
        if (txtNombreEmpresa.getText().equals("") || txtDireccionEmpresa.getText().equals("")
                || txtTelefonoEmpresa.getText().equals("") || txtNITEmp1.getText().equals("") || txtNRCEmpresa.getText().equals("") || txtRepLegalEmpresa.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informacion incompleta para poder guardar.");
        } else {

            clEmpr.modificar(txtIdEmpresa.getText(), txtNombreEmpresa.getText(), txtDireccionEmpresa.getText(),
                    txtTelefonoEmpresa.getText(), txtNITEmp1.getText(), txtNRCEmpresa.getText(),
                    (cmbActEconomica.getSelectedIndex() + 1), txtRepLegalEmpresa.getText());
            JOptionPane.showMessageDialog(null, "Empresa registrada con éxito.");

            btnEliminarEmpresa.setVisible(false);
            btnModificarEmpresa.setVisible(false);
            btnGuardarEmpresa.setVisible(true);
            btnCambioEmpresa.setVisible(false);
            txtNombreEmpresa.setText("");
            txtNombreEmpresa.requestFocus();
            txtDireccionEmpresa.setText("");
            txtTelefonoEmpresa.setText("");
            txtNITEmp1.setText("");
            txtNRCEmpresa.setText("");
            cmbActEconomica.setSelectedIndex(0);
            txtRepLegalEmpresa.setText("");

            txtIdEmpresa.setEditable(false);
            txtNombreEmpresa.requestFocus();
            txtNombreEmpresa.setEditable(true);
            txtDireccionEmpresa.setEditable(true);
            txtTelefonoEmpresa.setEditable(true);
            txtNITEmp1.setEditable(true);
            txtNRCEmpresa.setEditable(true);
            cmbActEconomica.setEnabled(true);
            txtRepLegalEmpresa.setEditable(true);
        }
        this.setExtendedState(MAXIMIZED_BOTH);
        txtIdEmpresa.setEditable(false);
        rs = null;
        rs = clTra.contarRegistrosPorEmpresaYSucursal(1, 1);
        try {
            while (rs.next()) {
                cantidad = rs.getInt(1);
                if (cantidad != 0) {
                    rs = null;
                    rs = clEmpr.mayorRegistro();
                    while (rs.next()) {
                        mayor = rs.getInt(1) + 1;
                        if (mayor < 10) {
                            txtIdEmpresa.setText("00" + mayor);
                        } else if (mayor < 100) {
                            this.txtIdEmpresa.setText("0" + mayor);
                        } else {
                            txtIdEmpresa.setText("" + mayor);
                        }
                    }
                } else {
                    txtIdEmpresa.setText("00" + 1);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "AVISO DEL SISTEMA", 0);
        }
    }//GEN-LAST:event_btnCambioEmpresaActionPerformed

    private void btnCancelarAnticipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarAnticipoActionPerformed
        cmbEmpresasDisponibles3.setSelectedIndex(0);
        cmbSucursalesDisponibles4.setSelectedIndex(0);
        cmbEmpleadosDisponibles1.setSelectedIndex(0);
        dtcFechaAnticipo.setDate(null);
        txtValorAnticipo.setText("");
        JOptionPane.showMessageDialog(null, "Campos limpiados exitosamente.");
    }//GEN-LAST:event_btnCancelarAnticipoActionPerformed

    private void cmbSucursalesDisponibles4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbSucursalesDisponibles4ItemStateChanged
        int posicion, posicionn, posicionnn, pposicionn, pposicion, pposicionnn;
        posicion = cmbSucursalesDisponibles4.getSelectedIndex();
        lblSucursalSeleccionada4.setText(String.valueOf(sucursales.getElementAt(posicion)));
        posicionn = cmbEmpresasDisponibles3.getSelectedIndex();
        posicionnn = cmbEmpleadosDisponibles1.getSelectedIndex();

        pposicion = cmbSucursalesDisponibles4.getSelectedIndex();
        pposicion = pposicion + 1;
        pposicionn = cmbEmpresasDisponibles3.getSelectedIndex();
        pposicionn = pposicionn + 1;
        pposicionnn = cmbEmpleadosDisponibles1.getSelectedIndex();
        pposicionnn = pposicionnn + 1;

        this.setExtendedState(MAXIMIZED_BOTH);
        rs = null;
        rs = clAnt.contarAnticiposEmpresaSucursalYTrabajador(pposicionn, pposicion, pposicionnn);
        try {
            while (rs.next()) {
                cantidad = rs.getInt(1);
                if (cantidad != 0) {
                    rs = null;
                    rs = clAnt.mayorAnticipoEmpresaSucursalYTrabajador(pposicionn, pposicion, pposicionnn);
                    while (rs.next()) {
                        mayor = rs.getInt(1) + 1;
                        if (mayor < 10) {
                            txtIdAnticipo.setText("00" + mayor);
                        } else if (mayor < 100) {
                            this.txtIdAnticipo.setText("0" + mayor);
                        } else {
                            txtIdAnticipo.setText("" + mayor);
                        }
                    }
                } else {
                    txtIdAnticipo.setText("00" + 1);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "AVISO DEL SISTEMA", 0);
        }
    }//GEN-LAST:event_cmbSucursalesDisponibles4ItemStateChanged

    private void btnGuardarAnticipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarAnticipoActionPerformed
        if (dtcFechaAnticipo.getDate() == null || txtValorAnticipo.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informacion incompleta para poder guardar.");
        } else {
            java.util.Date fechaNac = new java.util.Date();
            fechaNac = dtcFechaAnticipo.getDate();
            java.sql.Date fechaNaci = convertUtilToSql(fechaNac);
            anticipo.insertarAnticipo(Integer.parseInt(lblEmpresaSeleccionada3.getText()), Integer.parseInt(lblSucursalSeleccionada4.getText()), Integer.parseInt(lblEmpleadoSeleccionado1.getText()), Integer.parseInt(txtIdAnticipo.getText()), "" + cmbEmpleadosDisponibles1.getSelectedItem(), fechaNaci, Double.parseDouble(txtValorAnticipo.getText()));
            JOptionPane.showMessageDialog(null, "Anticipo registrado con éxito.");
        }
        txtValorAnticipo.requestFocus();

        cmbEmpresasDisponibles3.setSelectedIndex(0);
        cmbSucursalesDisponibles4.setSelectedIndex(0);
        cmbEmpleadosDisponibles1.setSelectedIndex(0);
        dtcFechaAnticipo.setDate(null);
        txtValorAnticipo.setText("");

        this.setExtendedState(MAXIMIZED_BOTH);
        txtIdAnticipo.setEditable(false);
        rs = null;
        rs = clAnt.contarAnticiposEmpresaSucursalYTrabajador(1, 1, 1);
        try {
            while (rs.next()) {
                cantidad = rs.getInt(1);
                if (cantidad != 0) {
                    rs = null;
                    rs = clAnt.mayorAnticipoEmpresaSucursalYTrabajador(1, 1, 1);
                    while (rs.next()) {
                        mayor = rs.getInt(1) + 1;
                        if (mayor < 10) {
                            txtIdAnticipo.setText("00" + mayor);
                        } else if (mayor < 100) {
                            this.txtIdAnticipo.setText("0" + mayor);
                        } else {
                            txtIdAnticipo.setText("" + mayor);
                        }
                    }
                } else {
                    txtIdAnticipo.setText("00" + 1);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "AVISO DEL SISTEMA", 0);
        }
    }//GEN-LAST:event_btnGuardarAnticipoActionPerformed

    private void txtValorAnticipoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorAnticipoKeyTyped
        char caracter = evt.getKeyChar();
        if (((caracter < '0') || (caracter > '9'))
                && (!Character.isDigit(caracter))
                && (caracter != '.' || txtValorAnticipo.getText().contains("."))) {
            evt.consume();
        }
    }//GEN-LAST:event_txtValorAnticipoKeyTyped

    private void cmbEmpleadosDisponibles1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbEmpleadosDisponibles1ItemStateChanged
        int posicion, pposicionn, pposicionnn, pposicion;
        posicion = cmbEmpleadosDisponibles1.getSelectedIndex();
        lblEmpleadoSeleccionado1.setText(String.valueOf(trabajadores.getElementAt(posicion)));

        pposicion = posicion;
        pposicion = pposicion + 1;
        pposicionn = cmbEmpresasDisponibles3.getSelectedIndex();
        pposicionn = pposicionn + 1;
        pposicionnn = cmbSucursalesDisponibles4.getSelectedIndex();
        pposicionnn = pposicionnn + 1;

        this.setExtendedState(MAXIMIZED_BOTH);
        rs = null;
        rs = clAnt.contarAnticiposEmpresaSucursalYTrabajador(pposicionn, pposicionnn, pposicion);
        try {
            while (rs.next()) {
                cantidad = rs.getInt(1);
                if (cantidad != 0) {
                    rs = null;
                    rs = clAnt.mayorAnticipoEmpresaSucursalYTrabajador(pposicionn, pposicionnn, pposicion);
                    while (rs.next()) {
                        mayor = rs.getInt(1) + 1;
                        if (mayor < 10) {
                            txtIdAnticipo.setText("00" + mayor);
                        } else if (mayor < 100) {
                            this.txtIdAnticipo.setText("0" + mayor);
                        } else {
                            txtIdAnticipo.setText("" + mayor);
                        }
                    }
                } else {
                    txtIdAnticipo.setText("00" + 1);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "AVISO DEL SISTEMA", 0);
        }
    }//GEN-LAST:event_cmbEmpleadosDisponibles1ItemStateChanged

    private void cmbEmpresasDisponibles3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbEmpresasDisponibles3ItemStateChanged
        int posicion, posicionn, posicionnn, pposicion, pposicionn, pposicionnn;
        posicion = cmbEmpresasDisponibles3.getSelectedIndex();
        lblEmpresaSeleccionada3.setText(String.valueOf(empresas.getElementAt(posicion)));
        posicionn = cmbSucursalesDisponibles4.getSelectedIndex();
        posicionnn = cmbEmpleadosDisponibles1.getSelectedIndex();
        posicionn = posicionn + 1;
        posicion = posicion + 1;

        trabajadores.removeAllElements();
        nombresTrabajadores.removeAllElements();
        rstTrabajadores = trabajador.llenarTrabajadoresPorEmpresaYSucursal("" + posicion, "" + posicionn);
        try {
            while (rstTrabajadores.next()) {
                trabajadores.addElement(rstTrabajadores.getInt(3));
                nombresTrabajadores.addElement(rstTrabajadores.getString(4));
            }
            cmbEmpleadosDisponibles1.setModel(nombresTrabajadores);
            lblEmpleadoSeleccionado1.setText(String.valueOf(trabajadores.getElementAt(0)));
        } catch (SQLException ex) {
            Logger.getLogger(frmInicio.class.getName()).log(Level.SEVERE, null, ex);
        }

        pposicion = cmbEmpresasDisponibles3.getSelectedIndex();
        pposicion = pposicion + 1;
        pposicionn = cmbSucursalesDisponibles4.getSelectedIndex();
        pposicionn = pposicionn + 1;
        pposicionnn = cmbEmpleadosDisponibles1.getSelectedIndex();
        pposicionnn = pposicionnn + 1;

        this.setExtendedState(MAXIMIZED_BOTH);
        rs = null;
        rs = clAnt.contarAnticiposEmpresaSucursalYTrabajador(pposicion, pposicionn, pposicionnn);
        try {
            while (rs.next()) {
                cantidad = rs.getInt(1);
                if (cantidad != 0) {
                    rs = null;
                    rs = clAnt.mayorAnticipoEmpresaSucursalYTrabajador(pposicion, pposicionn, pposicionnn);
                    while (rs.next()) {
                        mayor = rs.getInt(1) + 1;
                        if (mayor < 10) {
                            txtIdAnticipo.setText("00" + mayor);
                        } else if (mayor < 100) {
                            this.txtIdAnticipo.setText("0" + mayor);
                        } else {
                            txtIdAnticipo.setText("" + mayor);
                        }
                    }
                } else {
                    txtIdAnticipo.setText("00" + 1);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "AVISO DEL SISTEMA", 0);
        }
    }//GEN-LAST:event_cmbEmpresasDisponibles3ItemStateChanged

    private void cmbEmpresasDisponibles2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbEmpresasDisponibles2FocusGained
        cargarEmpresas();
    }//GEN-LAST:event_cmbEmpresasDisponibles2FocusGained

    private void cmbEmpresasDisponibles1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbEmpresasDisponibles1FocusGained
        cargarEmpresas();
        cargarSucursales(Integer.parseInt(lblEmpresaSeleccionada1.getText()));
    }//GEN-LAST:event_cmbEmpresasDisponibles1FocusGained

    private void cmbEmpresasDisponibles3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbEmpresasDisponibles3FocusGained
        cargarEmpresas();
        cargarSucursales(Integer.parseInt(lblEmpresaSeleccionada3.getText()));
        cargarEmpleados(lblEmpresaSeleccionada3.getText(), lblSucursalSeleccionada4.getText());
    }//GEN-LAST:event_cmbEmpresasDisponibles3FocusGained

    private void cmbEmpresasDisponibles4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbEmpresasDisponibles4FocusGained
        cargarEmpresas();
        cargarSucursales(Integer.parseInt(lblEmpresaSeleccionada4.getText()));
    }//GEN-LAST:event_cmbEmpresasDisponibles4FocusGained

    private void cmbEmpresasDisponibles6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbEmpresasDisponibles6FocusGained
        cargarEmpresas();
        cargarSucursales(Integer.parseInt(lblEmpresaSeleccionada6.getText()));
    }//GEN-LAST:event_cmbEmpresasDisponibles6FocusGained

    private void cmbCalculosEmpresaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbCalculosEmpresaFocusGained
        cargarEmpresas();
        cargarSucursales(Integer.parseInt(lblEmpresaSeleccionada8.getText()));
        cargarEmpleados(lblEmpresaSeleccionada8.getText(), lblSucursalSeleccionada9.getText());
    }//GEN-LAST:event_cmbCalculosEmpresaFocusGained

    private void cmbSucursalesDisponibles1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbSucursalesDisponibles1FocusGained
        cargarSucursales(Integer.parseInt(lblEmpresaSeleccionada1.getText()));
    }//GEN-LAST:event_cmbSucursalesDisponibles1FocusGained

    private void cmbSucursalesDisponibles2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbSucursalesDisponibles2FocusGained
        cargarSucursales(Integer.parseInt(lblEmpresaSeleccionada4.getText()));
    }//GEN-LAST:event_cmbSucursalesDisponibles2FocusGained

    private void cmbSucursalesDisponibles3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbSucursalesDisponibles3FocusGained
        cargarSucursales(Integer.parseInt(lblEmpresaSeleccionada6.getText()));
    }//GEN-LAST:event_cmbSucursalesDisponibles3FocusGained

    private void cmbSucursalesDisponibles4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbSucursalesDisponibles4FocusGained
        cargarSucursales(Integer.parseInt(lblEmpresaSeleccionada3.getText()));
        cargarEmpleados(lblEmpresaSeleccionada3.getText(), lblSucursalSeleccionada4.getText());
    }//GEN-LAST:event_cmbSucursalesDisponibles4FocusGained

    private void cmbEmpleadosDisponibles1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbEmpleadosDisponibles1FocusGained
        cargarEmpleados(lblEmpresaSeleccionada3.getText(), lblSucursalSeleccionada4.getText());
    }//GEN-LAST:event_cmbEmpleadosDisponibles1FocusGained

    private void cmbActEconomicaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbActEconomicaFocusGained
        cargarActEco();
    }//GEN-LAST:event_cmbActEconomicaFocusGained

    private void cmbCargoEmpFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbCargoEmpFocusGained
        cargarCargos();
    }//GEN-LAST:event_cmbCargoEmpFocusGained

    private void cmbAFPEmpFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbAFPEmpFocusGained
        cargarAFPS();
    }//GEN-LAST:event_cmbAFPEmpFocusGained

    private void btnAgregarAFPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarAFPActionPerformed
        afpe.setVisible(true);
    }//GEN-LAST:event_btnAgregarAFPActionPerformed

    private void cmbCalculosSucursalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCalculosSucursalItemStateChanged
        int posicion;
        posicion = cmbCalculosSucursal.getSelectedIndex();
        lblSucursalSeleccionada9.setText(String.valueOf(sucursales.getElementAt(posicion)));
    }//GEN-LAST:event_cmbCalculosSucursalItemStateChanged

    private void cmbCalculosSucursalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbCalculosSucursalFocusGained
        cargarSucursales(Integer.parseInt(lblEmpresaSeleccionada8.getText()));
        cargarEmpleados(lblEmpresaSeleccionada8.getText(), lblSucursalSeleccionada9.getText());
    }//GEN-LAST:event_cmbCalculosSucursalFocusGained

    private void cmbCalculosEmpleadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCalculosEmpleadoItemStateChanged
        int posicion;
        posicion = cmbCalculosEmpleado.getSelectedIndex();
    }//GEN-LAST:event_cmbCalculosEmpleadoItemStateChanged

    private void cmbCalculosEmpleadoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbCalculosEmpleadoFocusGained
        cargarEmpleados(lblEmpresaSeleccionada8.getText(), lblSucursalSeleccionada9.getText());
    }//GEN-LAST:event_cmbCalculosEmpleadoFocusGained

    private void btnGenerarPlanillaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarPlanillaActionPerformed
        if (dtcDeFecha.getDate() == null || dtcAFecha.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Informacion incompleta para generar planilla.");
            reinicio();
            dtcDeFecha.setDate(null);
            dtcAFecha.setDate(null);
        } else {
            if ((dtcDeFecha.getDate().before(dtcAFecha.getDate())) == true) {
                Columnas();
                modeloResultados = new DefaultTableModel();
                tblPlanilla.setModel(modeloResultados);

                rstPlanillas = null;
                rstPlanillas = planilla.llenarTabla(Integer.parseInt(lblEmpresaSeleccionada4.getText()), Integer.parseInt(lblSucursalSeleccionada2.getText()));
                try {
                    for (columna = 0; columna <= 13; columna++) {
                        modeloResultados.addColumn(encabezados.get(columna));
                    }
                    while (rstPlanillas.next()) {

                        Object[] fila = new Object[columna];

                        fila[0] = rstPlanillas.getObject(3);
                        fila[1] = rstPlanillas.getObject(4);
                        fila[2] = rstPlanillas.getObject(14);

                        modeloResultados.addRow(fila);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
                dtcDeFecha.setEnabled(false);
                dtcAFecha.setEnabled(false);
                cmbEmpresasDisponibles4.setEnabled(false);
                cmbSucursalesDisponibles2.setEnabled(false);
                btnGenerarPlanilla.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione en orden las fechas.");
                reinicio();
                dtcDeFecha.setDate(null);
                dtcAFecha.setDate(null);
            }

        }
    }//GEN-LAST:event_btnGenerarPlanillaActionPerformed

    private void txtSueldoMensualKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSueldoMensualKeyTyped
        char caracter = evt.getKeyChar();
        if (((caracter < '0') || (caracter > '9')) && (caracter != KeyEvent.VK_BACK_SPACE) && (caracter != '.')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtSueldoMensualKeyTyped

    private void btnNuevoCalculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoCalculoActionPerformed
        txtSueldoMensual.setText("");
        btnCalcularHorasExtras.setEnabled(true);
        txtSueldoMensual.requestFocus();
        for (int i = 0; i < 12; i++) {
            jtblExtras.setValueAt(null, i, 2);
            jtblExtras.setValueAt(null, i, 1);
        }
        lblTotal.setText("");
    }//GEN-LAST:event_btnNuevoCalculoActionPerformed

    private void btnCalcularHorasExtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularHorasExtrasActionPerformed
        double total = 0;
        double porcentajes[] = {1, 1.25, 1.50, 1.75, 2, 2.25, 1.50, 2, 2.5, 3, 3.5, 4};
        if (txtSueldoMensual.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "El campo Sueldo Mensual Esta vacio.");
        } else {
            double sueldo = Double.parseDouble(txtSueldoMensual.getText());
            double sueldoDiario = ((sueldo / 30) / 8);
            for (int i = 0; i < 12; i++) {
                if (jtblExtras.getValueAt(i, 1) == null) {
                    int hora = 0;
                    total = total + (porcentajes[i] * hora * sueldoDiario);
                    jtblExtras.setValueAt(porcentajes[i] * hora * sueldoDiario, i, 2);
                } else {
                    int hora = (Integer) jtblExtras.getValueAt(i, 1);
                    jtblExtras.setValueAt(porcentajes[i] * hora * sueldoDiario, i, 2);
                    total = total + (porcentajes[i] * hora * sueldoDiario);
                }
            }
            total = total + Double.parseDouble(txtSueldoMensual.getText());
            total = Math.round(total * 100.0) / 100.0;
        }

        lblTotal.setText("" + total);
        btnCalcularHorasExtras.setEnabled(false);
        jtblExtras.setEnabled(true);
    }//GEN-LAST:event_btnCalcularHorasExtrasActionPerformed

    private void btnGuardarPlanillaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnGuardarPlanillaFocusGained

    }//GEN-LAST:event_btnGuardarPlanillaFocusGained

    private void btnGuardarPlanillaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarPlanillaMouseEntered
        if (tblPlanilla.getValueAt(1, 3).equals("") == true) {
            tblPlanilla.setValueAt("00.00", 0, 3);
        }
    }//GEN-LAST:event_btnGuardarPlanillaMouseEntered

    private void btnCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularActionPerformed
     int id=1+cmbCalculosEmpresa.getSelectedIndex();
        int id_Suc=1+cmbCalculosSucursal.getSelectedIndex();
        int id_trabajador=1+cmbCalculosEmpleado.getSelectedIndex();
        int anios=this.obtenerAnios(id,id_Suc, id_trabajador);
        int dias=this.obtenerDias(id, id_Suc, id_trabajador);
        double sueldo=this.obtenerSueldo(id, id_Suc, id_trabajador);
        if (anios > 1) {
            int factor = dias - (anios * 365);
            double vacaciones = (((sueldo / 2)+((sueldo/2)*0.3))/365)*(factor);
            txtVacaciones.setText(""+vacaciones);
        }else{
            double vacaciones = (((sueldo / 2)+((sueldo/2)*0.3))/365)*(dias);
           txtVacaciones.setText(""+vacaciones);
        }
        int factor = dias - (anios * 365);
        double indemnizacion=((sueldo/365))*factor;
        txtIndemnizacion.setText(""+indemnizacion);
         if(anios<1){
        double aguinaldo=((sueldo/2)/365)*dias;
        txtAguinaldo.setText(""+aguinaldo);
    }else if(anios<3){  
        double aguinaldo=((((sueldo/30)*15)/365)*factor);
        txtAguinaldo.setText(""+aguinaldo);
    }else if(anios<10){
        double aguinaldo=((((sueldo/30)*19)/365)*factor);
       txtAguinaldo.setText(""+aguinaldo);
    }else{          
        double aguinaldo=((((sueldo/30)*21)/365)*factor);
     txtAguinaldo.setText(""+aguinaldo);
    }

    }//GEN-LAST:event_btnCalcularActionPerformed

    private void btmLimpiarCalculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmLimpiarCalculosActionPerformed
        txtVacaciones.setText("");
        txtAguinaldo.setText("");
        txtIndemnizacion.setText("");

    }//GEN-LAST:event_btmLimpiarCalculosActionPerformed
    public void Columnas() {
        encabezados.clear();
        encabezados.add("Id");
        encabezados.add("Nombre(s)");
        encabezados.add("Sueldo");
        encabezados.add("Horas Ext.");
        encabezados.add("Vacaciones");
        encabezados.add("Indemnizacion");
        encabezados.add("Aguinaldo");
        encabezados.add("Otros Ingresos");
        encabezados.add("ISSS");
        encabezados.add("AFP");
        encabezados.add("ISR");
        encabezados.add("Anticipo");
        encabezados.add("Otros descuentos");
        encabezados.add("Liquido a pagar");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmInicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btmLimpiarCalculos;
    private javax.swing.JButton btnAgregarAFP;
    private javax.swing.JButton btnAgregarActEcon;
    private javax.swing.JButton btnCalcular;
    private javax.swing.JButton btnCalcularHorasExtras;
    private javax.swing.JButton btnCambioEmpleado;
    private javax.swing.JButton btnCambioEmpresa;
    private javax.swing.JButton btnCambioSucursal;
    private javax.swing.JButton btnCancelarAnticipo;
    private javax.swing.JButton btnCancelarEmp;
    private javax.swing.JButton btnCancelarEmpresa;
    private javax.swing.JButton btnCancelarPlanilla;
    private javax.swing.JButton btnCancelarRecibo;
    private javax.swing.JButton btnCancelarSuc;
    private javax.swing.JButton btnConsultarEmp;
    private javax.swing.JButton btnConsultarEmpresa;
    private javax.swing.JButton btnConsultarSuc;
    private javax.swing.JButton btnEliminarEmp;
    private javax.swing.JButton btnEliminarEmpresa;
    private javax.swing.JButton btnEliminarSuc;
    private javax.swing.JButton btnGenerarPlanilla;
    private javax.swing.JButton btnGuardarAnticipo;
    private javax.swing.JButton btnGuardarEmp;
    private javax.swing.JButton btnGuardarEmpresa;
    private javax.swing.JButton btnGuardarPlanilla;
    private javax.swing.JButton btnGuardarRecibo;
    private javax.swing.JButton btnGuardarSuc;
    private javax.swing.JButton btnModificarEmp;
    private javax.swing.JButton btnModificarEmpresa;
    private javax.swing.JButton btnModificarSuc;
    private javax.swing.JButton btnNuevoCalculo;
    private javax.swing.JButton btnNuevoCargoEmp;
    private javax.swing.JComboBox<String> cmbAFPEmp;
    private javax.swing.JComboBox<String> cmbActEconomica;
    private javax.swing.JComboBox<String> cmbCalculosEmpleado;
    private javax.swing.JComboBox<String> cmbCalculosEmpresa;
    private javax.swing.JComboBox<String> cmbCalculosSucursal;
    private javax.swing.JComboBox<String> cmbCargoEmp;
    private javax.swing.JComboBox<String> cmbEmpleadosDisponibles1;
    private javax.swing.JComboBox<String> cmbEmpresasDisponibles1;
    private javax.swing.JComboBox<String> cmbEmpresasDisponibles2;
    private javax.swing.JComboBox<String> cmbEmpresasDisponibles3;
    private javax.swing.JComboBox<String> cmbEmpresasDisponibles4;
    private javax.swing.JComboBox<String> cmbEmpresasDisponibles6;
    private javax.swing.JComboBox<String> cmbSucursalesDisponibles1;
    private javax.swing.JComboBox<String> cmbSucursalesDisponibles2;
    private javax.swing.JComboBox<String> cmbSucursalesDisponibles3;
    private javax.swing.JComboBox<String> cmbSucursalesDisponibles4;
    private com.toedter.calendar.JDateChooser dtcAFecha;
    private com.toedter.calendar.JDateChooser dtcDeFecha;
    private com.toedter.calendar.JDateChooser dtcFechaAnticipo;
    private com.toedter.calendar.JDateChooser dtcFechaIngreso;
    private com.toedter.calendar.JDateChooser dtcFechaNacimiento;
    private com.toedter.calendar.JDateChooser dtcFechaRecibo;
    private com.toedter.calendar.JDateChooser dtcFechaSalida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTable jtblExtras;
    private javax.swing.JLabel lblAFPSeleccionado;
    private javax.swing.JLabel lblAFecha;
    private javax.swing.JLabel lblActEcoSeleccionada;
    private javax.swing.JLabel lblApellido1Emp;
    private javax.swing.JLabel lblApellido2Emp;
    private javax.swing.JLabel lblCargoEmp;
    private javax.swing.JLabel lblCargoSeleccionado1;
    private javax.swing.JLabel lblConceptoRecibo;
    private javax.swing.JLabel lblDUIEmp;
    private javax.swing.JLabel lblDatosGenerales;
    private javax.swing.JLabel lblDeFecha;
    private javax.swing.JLabel lblDireccionEmpresa;
    private javax.swing.JLabel lblDireccionSuc;
    private javax.swing.JLabel lblEmpleadoSeleccionado1;
    private javax.swing.JLabel lblEmpresaSeleccionada1;
    private javax.swing.JLabel lblEmpresaSeleccionada2;
    private javax.swing.JLabel lblEmpresaSeleccionada3;
    private javax.swing.JLabel lblEmpresaSeleccionada4;
    private javax.swing.JLabel lblEmpresaSeleccionada6;
    private javax.swing.JLabel lblEmpresaSeleccionada8;
    private javax.swing.JLabel lblFechaAnticipo;
    private javax.swing.JLabel lblFechaIngreso;
    private javax.swing.JLabel lblFechaNacimiento;
    private javax.swing.JLabel lblFechaRecibo;
    private javax.swing.JLabel lblFechaSalida;
    private javax.swing.JLabel lblIdAnticipo;
    private javax.swing.JLabel lblIdEmpleado;
    private javax.swing.JLabel lblIdEmpresa;
    private javax.swing.JLabel lblIdPlanilla;
    private javax.swing.JLabel lblIdRecibo;
    private javax.swing.JLabel lblIdSucursal;
    private javax.swing.JLabel lblInfoEsp;
    private javax.swing.JLabel lblNITEmp;
    private javax.swing.JLabel lblNITEmpresa;
    private javax.swing.JLabel lblNRCEmpresa;
    private javax.swing.JLabel lblNombreEmpresa;
    private javax.swing.JLabel lblNombreFirmante;
    private javax.swing.JLabel lblNombreSucursal;
    private javax.swing.JLabel lblNombresEmp;
    private javax.swing.JLabel lblNota4;
    private javax.swing.JLabel lblNumAFPEmp;
    private javax.swing.JLabel lblNumISSSEmp;
    private javax.swing.JLabel lblNumTelEmp;
    private javax.swing.JLabel lblRepLegalEmpresa;
    private javax.swing.JLabel lblSeleccionarEmp;
    private javax.swing.JLabel lblSeleccionarEmpleado1;
    private javax.swing.JLabel lblSeleccionarEmpresa;
    private javax.swing.JLabel lblSeleccionarEmpresa1;
    private javax.swing.JLabel lblSeleccionarEmpresa10;
    private javax.swing.JLabel lblSeleccionarEmpresa2;
    private javax.swing.JLabel lblSeleccionarEmpresa4;
    private javax.swing.JLabel lblSeleccionarEmpresa5;
    private javax.swing.JLabel lblSeleccionarEmpresa7;
    private javax.swing.JLabel lblSeleccionarSucursal1;
    private javax.swing.JLabel lblSeleccionarSucursal10;
    private javax.swing.JLabel lblSeleccionarSucursal2;
    private javax.swing.JLabel lblSeleccionarSucursal3;
    private javax.swing.JLabel lblSeleccionarSucursal4;
    private javax.swing.JLabel lblSucursalSeleccionada1;
    private javax.swing.JLabel lblSucursalSeleccionada2;
    private javax.swing.JLabel lblSucursalSeleccionada3;
    private javax.swing.JLabel lblSucursalSeleccionada4;
    private javax.swing.JLabel lblSucursalSeleccionada9;
    private javax.swing.JLabel lblSueldoEmp;
    private javax.swing.JLabel lblTelefonoEmpresa;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblValorAnticipo;
    private javax.swing.JLabel lblValorRecibo;
    private javax.swing.JLabel lblVistaPrevia;
    private javax.swing.JLabel lclActEcoEmpresa;
    private javax.swing.JLayeredPane lydpnAnticipos;
    private javax.swing.JLayeredPane lydpnEmpleados;
    private javax.swing.JLayeredPane lydpnEmpresas;
    private javax.swing.JLayeredPane lydpnHorasExtras;
    private javax.swing.JLayeredPane lydpnPlanillas;
    private javax.swing.JLayeredPane lydpnRecibos;
    private javax.swing.JLayeredPane lydpnSucursales;
    private javax.swing.JLayeredPane lydpnVacaciones;
    private javax.swing.JScrollPane scrlpnHorasExtras;
    private javax.swing.JScrollPane scrlpnPlanillas;
    private javax.swing.JSeparator sprtrEmpleado1;
    private javax.swing.JSeparator sprtrEmpleado2;
    private javax.swing.JSeparator sprtrEmpleado3;
    private javax.swing.JTabbedPane tbdpnCalculos;
    private javax.swing.JTabbedPane tbdpnFormularios;
    private javax.swing.JTabbedPane tbdpnInformes;
    private javax.swing.JTabbedPane tbdpnInicio;
    private javax.swing.JTable tblPlanilla;
    private javax.swing.JTextField txtAguinaldo;
    private javax.swing.JTextField txtApellido1Emp;
    private javax.swing.JTextField txtApellido2Emp;
    private javax.swing.JTextField txtConceptoRecibo;
    private javax.swing.JFormattedTextField txtDUIEmp;
    private javax.swing.JTextField txtDireccionEmpresa;
    private javax.swing.JTextField txtDireccionSuc;
    private javax.swing.JTextField txtIdAnticipo;
    private javax.swing.JTextField txtIdEmpleado;
    private javax.swing.JTextField txtIdEmpresa;
    private javax.swing.JTextField txtIdPlanilla;
    private javax.swing.JTextField txtIdRecibo;
    private javax.swing.JTextField txtIdSucursal;
    private javax.swing.JTextField txtIndemnizacion;
    private javax.swing.JFormattedTextField txtNITEmp;
    private javax.swing.JFormattedTextField txtNITEmp1;
    private javax.swing.JTextField txtNRCEmpresa;
    private javax.swing.JTextField txtNombreEmpresa;
    private javax.swing.JTextField txtNombreFirmante;
    private javax.swing.JTextField txtNombreSucursal;
    private javax.swing.JTextField txtNombresEmp;
    private javax.swing.JTextField txtNumAFPEmp;
    private javax.swing.JTextField txtNumISSSEmp;
    private javax.swing.JFormattedTextField txtNumTelEmp;
    private javax.swing.JTextField txtRepLegalEmpresa;
    private javax.swing.JTextField txtSueldoEmp;
    private javax.swing.JTextField txtSueldoMensual;
    private javax.swing.JFormattedTextField txtTelefonoEmpresa;
    private javax.swing.JTextField txtVacaciones;
    private javax.swing.JTextField txtValorAnticipo;
    private javax.swing.JTextField txtValorRecibo;
    // End of variables declaration//GEN-END:variables
}
