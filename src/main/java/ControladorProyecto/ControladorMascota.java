/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControladorProyecto;

import ModeloProyecto.Mascota;
import ModeloProyecto.MascotaConvencional;
import ModeloProyecto.MascotaExotica;
import ModeloProyecto.Propietario;
import ModeloProyecto.Recepcionista;
import VistaProyecto.VistaMascota;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mange
 */
public class ControladorMascota {
    private VistaMascota vista;
    private Recepcionista recepcionista;
    private DefaultTableModel tabla;

    public ControladorMascota(VistaMascota vista,Recepcionista recepcionista) {

        this.vista = vista;
        this.recepcionista = recepcionista;

        tabla = (DefaultTableModel) vista.tblMascotas.getModel();

        vista.btnRegistrar.addActionListener(e -> registrar());
        vista.btnLimpiar.addActionListener(e -> limpiar());
        vista.btnVolver.addActionListener(e -> vista.dispose());
        vista.cmbTipo.addActionListener(e -> cambiarTipo());

        cambiarTipo();
    }

    public void cargarPropietarios() {

        vista.cmbPropietario.removeAllItems();

        for (Propietario p : recepcionista.getPropietarios()) {

            vista.cmbPropietario.addItem(p.getNombre());
        }
    }

    public void cambiarTipo() {

        boolean convencional = vista.cmbTipo.getSelectedIndex() == 0;

        vista.txtRaza.setEnabled(convencional);
        vista.txtPermiso.setEnabled(!convencional);
        vista.chkSedacion.setEnabled(!convencional);
    }

    public void registrar() {

        try {

            int id = Integer.parseInt(vista.txtId.getText().trim());
            String nombre = vista.txtNombre.getText();
            int edad = Integer.parseInt(vista.txtEdad.getText().trim());
            double peso = Double.parseDouble(vista.txtPeso.getText().trim().replace(",", "."));

            int posicion = vista.cmbPropietario.getSelectedIndex();

            if (posicion == -1) {

                JOptionPane.showMessageDialog(vista, "Primero debe registrar un propietario");
                return;
            }

            Propietario propietario = recepcionista.getPropietarios().get(posicion);

            String tipo = vista.cmbTipo.getSelectedItem().toString();

            Mascota mascota;

            if (vista.cmbTipo.getSelectedIndex() == 0) {

                String raza = vista.txtRaza.getText();

                mascota = new MascotaConvencional(id, nombre, edad, peso, propietario, raza);

            } else {

                String permiso = vista.txtPermiso.getText();
                boolean sedacion = vista.chkSedacion.isSelected();

                mascota = new MascotaExotica (id, nombre, edad, peso, propietario,permiso, sedacion);
            }

            recepcionista.registrarMascota(mascota);

            tabla.addRow(new Object[]{
                id, nombre, edad, peso,
                tipo, propietario.getNombre()
            });

            JOptionPane.showMessageDialog(vista, "Mascota registrada correctamente");

            limpiar();

        } catch (NumberFormatException e) {

             JOptionPane.showMessageDialog(vista, "Error en el dato numerico: " + e.getMessage());
}
    }

    public void limpiar() {

        vista.txtId.setText("");
        vista.txtNombre.setText("");
        vista.txtEdad.setText("");
        vista.txtPeso.setText("");
        vista.txtRaza.setText("");
        vista.txtPermiso.setText("");
        vista.chkSedacion.setSelected(false);
        vista.cmbTipo.setSelectedIndex(0);

        vista.txtId.requestFocus();
    }
}
    

