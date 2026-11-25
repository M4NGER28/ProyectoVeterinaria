/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControladorProyecto;

import ModeloProyecto.Cobro;
import ModeloProyecto.Comprobante;
import ModeloProyecto.Consulta;
import ModeloProyecto.Mascota;
import ModeloProyecto.Propietario;
import ModeloProyecto.Recepcionista;
import VistaProyecto.VistaCobro;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author mange
 */
public class ControladorCobro {
   private VistaCobro vista;
    private Recepcionista recepcionista;
    private ArrayList<Consulta> consultas;

    private Cobro cobro;
    private Comprobante comprobante;

    private double costoSedacion = 30000;

    public ControladorCobro(VistaCobro vista,
            Recepcionista recepcionista) {

        this.vista = vista;
        this.recepcionista = recepcionista;

        consultas = new ArrayList<>();

        vista.cmbConsulta.addActionListener(e -> mostrarDatos());
        vista.btnCalcular.addActionListener(e -> calcular());
        vista.btnGenerar.addActionListener(e -> generar());
        vista.btnImprimir.addActionListener(e -> imprimir());
        vista.btnLimpiar.addActionListener(e -> limpiar());
        vista.btnVolver.addActionListener(e -> vista.dispose());
    }

    public void cargarConsultas() {

        limpiar();

        vista.cmbConsulta.removeAllItems();
        consultas.clear();

        for (Mascota m : recepcionista.getMascotas()) {

            for (Consulta c : m.getHistorial().obtenerConsultas()) {

                consultas.add(c);
            }
        }

        for (Consulta c : consultas) {

            vista.cmbConsulta.addItem(c.getId() + " - " + c.getCita().getMascota().getNombre());
        }
    }

    public void mostrarDatos() {

        int posicion = vista.cmbConsulta.getSelectedIndex();

        if (posicion == -1) {
            return;
        }

        Consulta consulta = consultas.get(posicion);

        Mascota mascota = consulta.getCita().getMascota();

        Propietario propietario = mascota.getPropietario();

        vista.txtMascota.setText(mascota.getNombre());

        vista.txtPropietario.setText(propietario.getNombre());

        vista.txtCantidadMascotas.setText(String.valueOf(propietario.contarMascotas()));

        cobro = null;
        comprobante = null;

        vista.txtCostoVacunas.setText("");
        vista.txtRecargo.setText("");
        vista.txtSedacion.setText("");
        vista.txtSubtotal.setText("");
        vista.txtDescuento.setText("");
        vista.txtTotal.setText("");
        vista.txtComprobante.setText("");
    }

    public void calcular() {

        cobro = null;
        comprobante = null;
        vista.txtComprobante.setText("");

        try {

            int posicion = vista.cmbConsulta.getSelectedIndex();

            if (posicion == -1) {

                JOptionPane.showMessageDialog(vista, "Seleccione una consulta");
                return;
            }

            int id = Integer.parseInt(vista.txtId.getText());

            double tarifaBase = Double.parseDouble(vista.txtTarifaBase.getText());

            if (id <= 0 || tarifaBase < 0) {

                JOptionPane.showMessageDialog(vista, "Verifique el ID y la tarifa base");
                return;
            }

            Consulta consulta = consultas.get(posicion);

            int cantidadMascotas = consulta.getCita().getMascota().getPropietario().contarMascotas();

            cobro = new Cobro(id, consulta,
                    tarifaBase, costoSedacion);

            double vacunas = cobro.calcularCostoVacunas();
            double medicamentos = cobro.calcularCostoMedicamentos();
            double recargo = cobro.calcularRecargoExotica();
            double sedacion = cobro.calcularCostoSedacion();
            double subtotal = cobro.calcularSubtotal();

            double descuento =
                    cobro.calcularDescuento(cantidadMascotas);

            double total = cobro.calcularTotal(cantidadMascotas);

            vista.txtCostoVacunas.setText(String.valueOf(vacunas));
            vista.txtCostoMedicamentos.setText("");
            vista.txtRecargo.setText(String.valueOf(recargo));
            vista.txtSedacion.setText(String.valueOf(sedacion));
            vista.txtSubtotal.setText(String.valueOf(subtotal));
            vista.txtDescuento.setText(String.valueOf(descuento));
            vista.txtTotal.setText(String.valueOf(total));

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(vista, "Ingrese un ID y una tarifa base validos");
        }
    }

    public void generar() {

        calcular();

        if (cobro == null) {
            return;
        }

        for (Cobro c : recepcionista.getCobros()) {

            if (c.getConsulta() == cobro.getConsulta()|| c.getId() == cobro.getId()) {

                JOptionPane.showMessageDialog(vista, "La consulta o el ID ya tiene un cobro");
                return;
            }
        }

        recepcionista.registrarCobro(cobro);

        comprobante = recepcionista.emitirComprobante(cobro);

        vista.txtComprobante.setText(comprobante.getNota());

        JOptionPane.showMessageDialog(vista, "Comprobante generado correctamente");
    }

    public void imprimir() {

        if (comprobante == null) {

            JOptionPane.showMessageDialog(vista,"Primero genere el comprobante");
            return;
        }

        comprobante.imprimir();

        JOptionPane.showMessageDialog(vista,"Comprobante enviado a la consola");
    }

    public void limpiar() {

        vista.cmbConsulta.setSelectedIndex(-1);

        vista.txtId.setText("");
        vista.txtMascota.setText("");
        vista.txtPropietario.setText("");
        vista.txtCantidadMascotas.setText("");
        vista.txtTarifaBase.setText("");
        vista.txtCostoVacunas.setText("");
        vista.txtCostoMedicamentos.setText("");
        vista.txtRecargo.setText("");
        vista.txtSedacion.setText("");
        vista.txtSubtotal.setText("");
        vista.txtDescuento.setText("");
        vista.txtTotal.setText("");
        vista.txtComprobante.setText("");

        cobro = null;
        comprobante = null;

        vista.txtId.requestFocus();
    }
} 

