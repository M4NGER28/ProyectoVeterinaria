/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControladorProyecto;

import ModeloProyecto.Consulta;
import ModeloProyecto.Mascota;
import ModeloProyecto.Recepcionista;
import ModeloProyecto.TratamientoAplicado;
import ModeloProyecto.Vacuna;
import VistaProyecto.VistaHistorial;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mange
 */
public class ControladorHistorial {
    private VistaHistorial vista;
    private Recepcionista recepcionista;
    private DefaultTableModel tabla;
    private ArrayList<Consulta> consultas;

    public ControladorHistorial(VistaHistorial vista,Recepcionista recepcionista) {

        this.vista = vista;
        this.recepcionista = recepcionista;

        consultas = new ArrayList<>();

        tabla = (DefaultTableModel) vista.tblHistorial.getModel();

        vista.btnConsultar.addActionListener(e -> consultar());
        vista.btnDetalles.addActionListener(e -> mostrarDetalles());
        vista.btnLimpiar.addActionListener(e -> limpiar());
        vista.btnVolver.addActionListener(e -> vista.dispose());
    }

    public void cargarMascotas() {

        limpiar();

        vista.cmbMascota.removeAllItems();

        for (Mascota m : recepcionista.getMascotas()) {

            vista.cmbMascota.addItem(m.getNombre());
        }
    }

    public void consultar() {

        int posicion = vista.cmbMascota.getSelectedIndex();

        if (posicion == -1) {

            JOptionPane.showMessageDialog(vista, "Seleccione una mascota");
            return;
        }

        Mascota mascota = recepcionista.getMascotas().get(posicion);

        vista.txtPropietario.setText(mascota.getPropietario().getNombre());

        consultas = mascota.getHistorial().obtenerConsultas();

        tabla.setRowCount(0);
        vista.txtDetalles.setText("");

        for (Consulta c : consultas) {

            tabla.addRow(new Object[]{
                c.getId(), c.getFechaHora(), c.getVeterinario().getNombre(), c.getDiagnostico()
            });
        }

        if (consultas.isEmpty()) {

            JOptionPane.showMessageDialog(vista, "La mascota no tiene consultas registradas");
        }
    }

    public void mostrarDetalles() {

        int fila = vista.tblHistorial.getSelectedRow();

        if (fila == -1) {

            JOptionPane.showMessageDialog(vista, "Seleccione una consulta de la tabla");
            return;
        }

        Consulta c = consultas.get(fila);

        String detalles = "CONSULTA No. " + c.getId() + "\n";

        detalles += "Fecha: " + c.getFechaHora() + "\n";
        detalles += "Veterinario: " + c.getVeterinario().getNombre() + "\n";
        detalles += "\nDIAGNOSTICO:\n";
        detalles += c.getDiagnostico() + "\n";
        detalles += "\nNOTAS MEDICAS:\n";
        detalles += c.getNotasMedicas() + "\n";
        detalles += "\nMEDICAMENTOS:\n";

        for (TratamientoAplicado t : c.getMedicamentosAplicados()) {

            detalles += t.getDescripcion() + " - Cantidad: " + t.getCantidad() + " - Costo: " + t.calcularSubtotal() + "\n";
        }

        detalles += "\nVACUNAS:\n";

        for (Vacuna v : c.getVacunas()) {

            detalles += v.getNombre() + " - Costo: " + v.obtenerCosto() + "\n";
        }

        vista.txtDetalles.setText(detalles);
    }

    public void limpiar() {

        vista.txtPropietario.setText("");
        vista.txtDetalles.setText("");

        tabla.setRowCount(0);

        consultas = new ArrayList<>();
    }
}
    

