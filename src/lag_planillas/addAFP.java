package lag_planillas;

import static java.awt.Frame.MAXIMIZED_BOTH;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import lag_planillas.afps;

public class addAFP extends javax.swing.JFrame {

    ResultSet rstCargo = null;
    ResultSet rs = null;
    afps afp = new afps();
    afps clAFPS = new afps();

    int cantidad = 0;
    int mayor = 0;
    
    public addAFP() {
        initComponents();
        
        this.setExtendedState(MAXIMIZED_BOTH);
        txtIdAFP.setEditable(false);
        rs = null;
        rs = clAFPS.contarRegistros();
        try {
            while (rs.next()) {
                cantidad = rs.getInt(1);
                if (cantidad != 0) {
                    rs = null;
                    rs = clAFPS.mayorRegistro();
                    while (rs.next()) {
                        mayor = rs.getInt(1) + 1;
                        if (mayor < 10) {
                            txtIdAFP.setText("00" + mayor);
                        } else if (mayor < 100) {
                            this.txtIdAFP.setText("0" + mayor);
                        } else {
                            txtIdAFP.setText("" + mayor);
                        }
                    }
                } else {
                    txtIdAFP.setText("00" + 1);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "AVISO DEL SISTEMA", 0);
        }

        txtDescAFP.requestFocus();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCancelarAFP = new javax.swing.JButton();
        btnGuardarAFP = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        txtDescAFP = new javax.swing.JTextField();
        lblDescAFP = new javax.swing.JLabel();
        lblIdAFP = new javax.swing.JLabel();
        txtIdAFP = new javax.swing.JTextField();
        lblAgregarAFP = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnCancelarAFP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/CANCELAR.png"))); // NOI18N
        btnCancelarAFP.setText("Cancelar");
        btnCancelarAFP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarAFPActionPerformed(evt);
            }
        });

        btnGuardarAFP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/GUARDAR.png"))); // NOI18N
        btnGuardarAFP.setText("Guardar");
        btnGuardarAFP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarAFPActionPerformed(evt);
            }
        });

        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/CANCELAR.png"))); // NOI18N
        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        lblDescAFP.setText("Descripción");

        lblIdAFP.setText("Identificador");

        lblAgregarAFP.setText("Agregar Cargo");

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
                                .addComponent(btnGuardarAFP)
                                .addGap(18, 18, 18)
                                .addComponent(btnCancelarAFP)
                                .addGap(18, 18, 18)
                                .addComponent(btnRegresar))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblDescAFP)
                                .addGap(49, 49, 49)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtIdAFP, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDescAFP, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(lblIdAFP)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblAgregarAFP)
                .addGap(154, 154, 154))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAgregarAFP)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdAFP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIdAFP))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDescAFP)
                    .addComponent(txtDescAFP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarAFP)
                    .addComponent(btnCancelarAFP)
                    .addComponent(btnRegresar))
                .addContainerGap(137, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarAFPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarAFPActionPerformed
        txtDescAFP.setText("");
        txtDescAFP.requestFocus();
        JOptionPane.showMessageDialog(null, "Campos limpiados exitosamente.");
    }//GEN-LAST:event_btnCancelarAFPActionPerformed

    private void btnGuardarAFPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarAFPActionPerformed
        if (txtIdAFP.getText().equals("") || txtDescAFP.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informacion incompleta para poder guardar.");
        } else {
            afp.insertarAFP(Integer.parseInt(txtIdAFP.getText()), txtDescAFP.getText());
            JOptionPane.showMessageDialog(null, "AFP registrado con éxito.");
        }
        txtDescAFP.setText("");
        txtDescAFP.requestFocus();

        this.setExtendedState(MAXIMIZED_BOTH);
        txtIdAFP.setEditable(false);
        rs = null;
        rs = clAFPS.contarRegistros();
        try {
            while (rs.next()) {
                cantidad = rs.getInt(1);
                if (cantidad != 0) {
                    rs = null;
                    rs = clAFPS.mayorRegistro();
                    while (rs.next()) {
                        mayor = rs.getInt(1) + 1;
                        if (mayor < 10) {
                            txtIdAFP.setText("00" + mayor);
                        } else if (mayor < 100) {
                            this.txtIdAFP.setText("0" + mayor);
                        } else {
                            txtIdAFP.setText("" + mayor);
                        }
                    }
                } else {
                    txtIdAFP.setText("00" + 1);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "AVISO DEL SISTEMA", 0);
        }
    }//GEN-LAST:event_btnGuardarAFPActionPerformed

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
            java.util.logging.Logger.getLogger(addAFP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(addAFP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(addAFP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(addAFP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new addAFP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelarAFP;
    private javax.swing.JButton btnGuardarAFP;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel lblAgregarAFP;
    private javax.swing.JLabel lblDescAFP;
    private javax.swing.JLabel lblIdAFP;
    private javax.swing.JTextField txtDescAFP;
    private javax.swing.JTextField txtIdAFP;
    // End of variables declaration//GEN-END:variables
}
