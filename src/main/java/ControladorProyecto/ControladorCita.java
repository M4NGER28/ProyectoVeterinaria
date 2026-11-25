/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControladorProyecto;

import ModeloProyecto.*;
import VistaProyecto.VistaCita;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author mange
 */
public class ControladorCita {
    private VistaCita vista;
    private Recepcionista recepcionista;
    private DefaultTableModel tabla;

    public ControladorCita(VistaCita vista, Recepcionista recepcionista) {

        this.vista = vista;
        this.recepcionista = recepcionista;

        tabla = (DefaultTableModel) vista.tblCitas.getModel();

        vista.btnAgendar.addActionListener(e -> agendar());
        vista.btnReprogramar.addActionListener(e -> reprogramar());
        vista.btnCancelar.addActionListener(e -> cancelar());
        vista.btnLimpiar.addActionListener(e -> limpiar());
        vista.btnVolver.addActionListener(e -> vista.dispose());
    }

    public void cargarMascotas() {

        vista.cmbMascota.removeAllItems();

        for (Mascota m : recepcionista.getMascotas()) {

            vista.cmbMascota.addItem(m.getNombre());
        }
    }

    public void agendar() {

        try {

            int id = Integer.parseInt(vista.txtId.getText());

            int posicion = vista.cmbMascota.getSelectedIndex();

            if (posicion == -1) {

                JOptionPane.showMessageDialog(vista,"Primero debe registrar una mascota");
                return;
            }

            Mascota mascota =
                    recepcionista.getMascotas().get(posicion);

            String fecha = vista.txtFecha.getText();
            String hora = vista.txtHora.getText();
            String motivo = vista.txtMotivo.getText();

            LocalDateTime fechaHora = LocalDateTime.parse(fecha + "T" + hora);

            Cita cita = new Cita(id, fechaHora, motivo, mascota);

            cita.programar();

            recepcionista.agendarCita(cita);

            tabla.addRow(new Object[]{id, mascota.getNombre(), fecha, hora, motivo, "Programada"
            });

            JOptionPane.showMessageDialog(vista, "Cita agendada correctamente");

            limpiar();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(vista, "Ingrese un ID valido");

        } catch (DateTimeParseException e) {

            JOptionPane.showMessageDialog(vista, "Verifique el formato de fecha y hora");
        }
    }

    public void reprogramar() {

        int fila = vista.tblCitas.getSelectedRow();

        if (fila == -1) {

            JOptionPane.showMessageDialog(vista, "Seleccione una cita de la tabla");
            return;
        }

        try {

            String fecha = vista.txtFecha.getText();
            String hora = vista.txtHora.getText();

            LocalDateTime fechaHora = LocalDateTime.parse(fecha + "T" + hora);

            Cita cita = recepcionista.getCitas().get(fila);

            cita.reprogramar(fechaHora);

            tabla.setValueAt(fecha, fila, 2);
            tabla.setValueAt(hora, fila, 3);
            tabla.setValueAt(cita.getEstado(), fila, 5);

            JOptionPane.showMessageDialog(vista, "Cita reprogramada correctamente");

            limpiar();

        } catch (DateTimeParseException e) {

            JOptionPane.showMessageDialog(vista,"Verifique la nueva fecha y hora");
        }
    }

    public void cancelar() {

        int fila = vista.tblCitas.getSelectedRow();

        if (fila == -1) {

            JOptionPane.showMessageDialog(vista, "Seleccione una cita de la tabla");
            return;
        }

        Cita cita = recepcionista.getCitas().get(fila);

        cita.cancelar();

        tabla.setValueAt(cita.getEstado(), fila, 5);

        JOptionPane.showMessageDialog(vista, "Cita cancelada correctamente");

        limpiar();
    }

    public void limpiar() {

        vista.txtId.setText("");
        vista.txtFecha.setText("");
        vista.txtHora.setText("");
        vista.txtMotivo.setText("");
        vista.txtEstado.setText("Programada");

        vista.tblCitas.clearSelection();
        vista.txtId.requestFocus();
    }
}

