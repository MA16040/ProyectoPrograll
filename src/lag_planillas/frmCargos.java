package lag_planillas;

import static java.awt.Frame.MAXIMIZED_BOTH;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class frmCargos extends javax.swing.JFrame {

    ResultSet rstCargo = null;
    ResultSet rs = null;
    cargos cargo = new cargos();
    cargos clCargo = new cargos();

    int cantidad = 0;
    int mayor = 0;

    public frmCargos() {
        initComponents();

        this.setExtendedState(MAXIMIZED_BOTH);
        txtIdCargo.setEditable(false);
        rs = null;
        rs = clCargo.contarRegistros();
        try {
            while (rs.next()) {
                cantidad = rs.getInt(1);
                if (cantidad != 0) {
                    rs = null;
                    rs = clCargo.mayorRegistro();
                    while (rs.next()) {
                        mayor = rs.getInt(1) + 1;
                        if (mayor < 10) {
                            txtIdCargo.setText("00" + mayor);
                        } else if (mayor < 100) {
                            this.txtIdCargo.setText("0" + mayor);
                        } else {
                            txtIdCargo.setText("" + mayor);
                        }
                    }
                } else {
                    txtIdCargo.setText("00" + 1);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "AVISO DEL SISTEMA", 0);
        }

        txtNombreCargo.requestFocus();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCancelarCargo = new javax.swing.JButton();
        lblNombreCargo = new javax.swing.JLabel();
        btnGuardarCargo = new javax.swing.JButton();
        txtNombreCargo = new javax.swing.JTextField();
        btnRegresar = new javax.swing.JButton();
        lblIdCargo = new javax.swing.JLabel();
        lblAgregarCargo = new javax.swing.JLabel();
        txtIdCargo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnCancelarCargo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/CANCELAR.png"))); // NOI18N
        btnCancelarCargo.setText("Cancelar");
        btnCancelarCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarCargoActionPerformed(evt);
            }
        });

        lblNombreCargo.setText("Cargo");

        btnGuardarCargo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/GUARDAR.png"))); // NOI18N
        btnGuardarCargo.setText("Guardar");
        btnGuardarCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCargoActionPerformed(evt);
            }
        });

        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/CANCELAR.png"))); // NOI18N
        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        lblIdCargo.setText("Identificador");

        lblAgregarCargo.setText("Agregar Cargo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnGuardarCargo)
                                .addGap(18, 18, 18)
                                .addComponent(btnCancelarCargo)
                                .addGap(18, 18, 18)
                                .addComponent(btnRegresar))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNombreCargo)
                                .addGap(49, 49, 49)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtIdCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNombreCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(lblIdCargo)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblAgregarCargo)
                .addGap(154, 154, 154))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAgregarCargo)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIdCargo))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombreCargo)
                    .addComponent(txtNombreCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarCargo)
                    .addComponent(btnCancelarCargo)
                    .addComponent(btnRegresar))
                .addContainerGap(154, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarCargoActionPerformed
        txtNombreCargo.setText("");
        txtNombreCargo.requestFocus();
        JOptionPane.showMessageDialog(null, "Campos limpiados exitosamente.");
    }//GEN-LAST:event_btnCancelarCargoActionPerformed

    private void btnGuardarCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCargoActionPerformed
        if (txtIdCargo.getText().equals("") || txtNombreCargo.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informacion incompleta para poder guardar.");
        } else {
            cargo.insertarCargo(Integer.parseInt(txtIdCargo.getText()), txtNombreCargo.getText());
            JOptionPane.showMessageDialog(null, "Cargo registrado con éxito.");
        }
        txtNombreCargo.setText("");
        txtNombreCargo.requestFocus();

        this.setExtendedState(MAXIMIZED_BOTH);
        txtIdCargo.setEditable(false);
        rs = null;
        rs = clCargo.contarRegistros();
        try {
            while (rs.next()) {
                cantidad = rs.getInt(1);
                if (cantidad != 0) {
                    rs = null;
                    rs = clCargo.mayorRegistro();
                    while (rs.next()) {
                        mayor = rs.getInt(1) + 1;
                        if (mayor < 10) {
                            txtIdCargo.setText("00" + mayor);
                        } else if (mayor < 100) {
                            this.txtIdCargo.setText("0" + mayor);
                        } else {
                            txtIdCargo.setText("" + mayor);
                        }
                    }
                } else {
                    txtIdCargo.setText("00" + 1);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "AVISO DEL SISTEMA", 0);
        }
    }//GEN-LAST:event_btnGuardarCargoActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

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
            java.util.logging.Logger.getLogger(frmCargos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmCargos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmCargos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmCargos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmCargos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelarCargo;
    private javax.swing.JButton btnGuardarCargo;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel lblAgregarCargo;
    private javax.swing.JLabel lblIdCargo;
    private javax.swing.JLabel lblNombreCargo;
    private javax.swing.JTextField txtIdCargo;
    private javax.swing.JTextField txtNombreCargo;
    // End of variables declaration//GEN-END:variables
}
