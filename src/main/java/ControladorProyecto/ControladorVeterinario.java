/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControladorProyecto;

import ModeloProyecto.Veterinario;
import VistaProyecto.VistaVeterinario;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mange
 */
public class ControladorVeterinario {
    private VistaVeterinario vista;
    private ArrayList<Veterinario> veterinarios;
    private DefaultTableModel tabla;

    public ControladorVeterinario(VistaVeterinario vista) {

        this.vista = vista;

        veterinarios = new ArrayList<>();

        tabla = (DefaultTableModel) vista.tblVeterinarios.getModel();

        vista.btnRegistrar.addActionListener(e -> registrar());
        vista.btnLimpiar.addActionListener(e -> limpiar());
        vista.btnVolver.addActionListener(e -> vista.dispose());
    }

    public void registrar() {

        try {

            int id = Integer.parseInt(vista.txtId.getText());
            String nombre = vista.txtNombre.getText();
            String especialidad = vista.txtEspecialidad.getText();
            String tarjeta = vista.txtTarjetaProfesional.getText();

            Veterinario v = new Veterinario(
                    id, nombre, especialidad, tarjeta);

            veterinarios.add(v);

            tabla.addRow(new Object[]{
                id, nombre, especialidad, tarjeta
            });

            JOptionPane.showMessageDialog(vista, "Veterinario registrado correctamente");

            limpiar();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(vista, "Ingrese un ID valido");
        }
    }

    public void limpiar() {

        vista.txtId.setText("");
        vista.txtNombre.setText("");
        vista.txtEspecialidad.setText("");
        vista.txtTarjetaProfesional.setText("");

        vista.txtId.requestFocus();
    }

    public ArrayList<Veterinario> getVeterinarios() {

        return veterinarios;
    }
}
    

