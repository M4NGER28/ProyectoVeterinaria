/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControladorProyecto;

import ModeloProyecto.Cita;
import ModeloProyecto.Consulta;
import ModeloProyecto.Recepcionista;
import ModeloProyecto.TratamientoAplicado;
import ModeloProyecto.Vacuna;
import ModeloProyecto.Veterinario;
import VistaProyecto.VistaConsulta;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mange
 */
public class ControladorConsulta {
    private VistaConsulta vista;
    private Recepcionista recepcionista;
    private ControladorVeterinario controladorVeterinario;

    private ArrayList<TratamientoAplicado> medicamentos;
    private ArrayList<Vacuna> vacunas;

    private DefaultTableModel tablaMedicamentos;
    private DefaultTableModel tablaVacunas;

    private int idTratamiento = 1;

    public ControladorConsulta(VistaConsulta vista, Recepcionista recepcionista, ControladorVeterinario controladorVeterinario) {

        this.vista = vista;
        this.recepcionista = recepcionista;
        this.controladorVeterinario = controladorVeterinario;

        medicamentos = new ArrayList<>();
        vacunas = new ArrayList<>();

        tablaMedicamentos = (DefaultTableModel)
                vista.tblMedicamentos.getModel();

        tablaVacunas = (DefaultTableModel)
                vista.tblVacunas.getModel();

        vista.btnAgregarMedicamento.addActionListener(e -> agregarMedicamento());
        vista.btnAgregarVacuna.addActionListener(e -> agregarVacuna());
        vista.btnRegistrar.addActionListener(e -> registrar());
        vista.btnLimpiar.addActionListener(e -> limpiar());
        vista.btnVolver.addActionListener(e -> vista.dispose());
    }

    public void cargarDatos() {

        vista.cmbCita.removeAllItems();
        vista.cmbVeterinario.removeAllItems();

        for (Cita c : recepcionista.getCitas()) {

            vista.cmbCita.addItem(c.getId() + " - " + c.getMascota().getNombre());
        }

        for (Veterinario v : controladorVeterinario.getVeterinarios()) {

            vista.cmbVeterinario.addItem(v.getNombre());
        }
    }

    public void agregarMedicamento() {

        try {

            String descripcion = vista.txtMedicamento.getText();
            int cantidad = Integer.parseInt(vista.txtCantidad.getText());
            double costo = Double.parseDouble(vista.txtCostoMedicamento.getText());

            if (descripcion.trim().isEmpty() || cantidad <= 0 || costo < 0) {
                JOptionPane.showMessageDialog(vista,"Verifique los datos del medicamento");
                return;
            }

            TratamientoAplicado tratamiento = new TratamientoAplicado(idTratamiento, "Medicamento", descripcion, cantidad, costo);

            medicamentos.add(tratamiento);

            idTratamiento++;

            tablaMedicamentos.addRow(new Object[]{
                descripcion, cantidad, costo
            });

            vista.txtMedicamento.setText("");
            vista.txtCantidad.setText("");
            vista.txtCostoMedicamento.setText("");

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(vista, "Verifique la cantidad y el costo");
        }
    }

    public void agregarVacuna() {

        try {

            String nombre = vista.txtVacuna.getText();
            double costo = Double.parseDouble(vista.txtCostoVacuna.getText());

            if (nombre.trim().isEmpty() || costo < 0) {
                JOptionPane.showMessageDialog(vista,"Verifique los datos de la vacuna");
                return;
            }

            Vacuna vacuna = new Vacuna (nombre, costo, LocalDate.now());

            vacunas.add(vacuna);

            tablaVacunas.addRow(new Object[]{
                nombre, costo
            });

            vista.txtVacuna.setText("");
            vista.txtCostoVacuna.setText("");

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(vista, "Ingrese un costo valido");
        }
    }

    public void registrar() {

        try {

            int id = Integer.parseInt(vista.txtId.getText());

            int posicionCita = vista.cmbCita.getSelectedIndex();
            int posicionVeterinario =
                    vista.cmbVeterinario.getSelectedIndex();

            if (posicionCita == -1 || posicionVeterinario == -1) {

                JOptionPane.showMessageDialog(vista, "Seleccione una cita y un veterinario");
                return;
            }

            Cita cita = recepcionista.getCitas().get(posicionCita);

            if (cita.getEstado().equalsIgnoreCase("Atendida")|| cita.getEstado().equalsIgnoreCase("Cancelada")) {

                JOptionPane.showMessageDialog(vista,  "Esta cita no puede ser atendida");
                return;
            }

            Veterinario veterinario = controladorVeterinario.getVeterinarios() .get(posicionVeterinario);

            String diagnostico = vista.txtDiagnostico.getText();
            String notas = vista.txtNotas.getText();

            if (diagnostico.trim().isEmpty()) {

                JOptionPane.showMessageDialog(vista,  "Ingrese el diagnostico");
                return;
            }

            Consulta consulta = new Consulta(id, cita, veterinario);

            consulta.registrarDiagnostico(diagnostico);
            consulta.agregarNota(notas);

            for (TratamientoAplicado t : medicamentos) {

                consulta.agregarTratamiento(t);
            }

            for (Vacuna v : vacunas) {

                consulta.agregarVacuna(v);
            }

            consulta.cerrarConsulta();

            JOptionPane.showMessageDialog(vista, "Consulta registrada correctamente");

            limpiar();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(vista, "Ingrese un ID valido");
        }
    }

    public void limpiar() {

        vista.txtId.setText("");
        vista.txtDiagnostico.setText("");
        vista.txtNotas.setText("");

        vista.txtMedicamento.setText("");
        vista.txtCantidad.setText("");
        vista.txtCostoMedicamento.setText("");

        vista.txtVacuna.setText("");
        vista.txtCostoVacuna.setText("");

        medicamentos.clear();
        vacunas.clear();

        tablaMedicamentos.setRowCount(0);
        tablaVacunas.setRowCount(0);

        vista.txtId.requestFocus();
    }
}
    

