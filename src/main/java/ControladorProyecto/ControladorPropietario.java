/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControladorProyecto;

import ModeloProyecto.Propietario;
import ModeloProyecto.Recepcionista;
import VistaProyecto.VistaPropietario;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mange
 */
public class ControladorPropietario {
   private VistaPropietario vista;
    private Recepcionista recepcionista;
    private DefaultTableModel tabla;

    public ControladorPropietario(VistaPropietario vista,
            Recepcionista recepcionista) {

        this.vista = vista;
        this.recepcionista = recepcionista;

        tabla = (DefaultTableModel) vista.tblPropietarios.getModel();

        vista.btnRegistrar.addActionListener(e -> registrar());
        vista.btnLimpiar.addActionListener(e -> limpiar());
        vista.btnVolver.addActionListener(e -> vista.dispose());
    }

    public void registrar() {

        try {

            int id = Integer.parseInt(vista.txtId.getText());
            String nombre = vista.txtNombre.getText();
            String documento = vista.txtDocumento.getText();
            String telefono = vista.txtTelefono.getText();
            String correo = vista.txtCorreo.getText();
            String direccion = vista.txtDireccion.getText();

            Propietario p = new Propietario(id, nombre, documento, telefono, correo, direccion);

            recepcionista.registrarPropietario(p);

            tabla.addRow(new Object[]{
                id, nombre, documento,
                telefono, correo, direccion
            });

            JOptionPane.showMessageDialog(vista,"Propietario registrado correctamente");

            limpiar();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(vista,"Ingrese un ID valido");
        }
    }

    public void limpiar() {

        vista.txtId.setText("");
        vista.txtNombre.setText("");
        vista.txtDocumento.setText("");
        vista.txtTelefono.setText("");
        vista.txtCorreo.setText("");
        vista.txtDireccion.setText("");

        vista.txtId.requestFocus();
    }
} 
    

