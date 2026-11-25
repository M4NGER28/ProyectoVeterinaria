package ControladorProyecto;

import ModeloProyecto.Recepcionista;
import VistaProyecto.*;
import javax.swing.JFrame;

public class ControladorMenu {

    private VistaMenu menu;
    private Recepcionista recepcionista;

    private VistaPropietario propietario;
    private VistaMascota mascota;
    private VistaCita cita;
    private VistaVeterinario veterinario;
    private VistaConsulta consulta;
    private VistaHistorial historial;
    private VistaCobro cobro;

    public ControladorMenu(VistaMenu menu) {

        this.menu = menu;

        recepcionista = new Recepcionista(1, "Recepcionista", "");

        propietario = new VistaPropietario();

        new ControladorPropietario(propietario, recepcionista);

        mascota = new VistaMascota();

        ControladorMascota controladorMascota = new ControladorMascota(mascota, recepcionista);

        cita = new VistaCita();

        ControladorCita controladorCita = new ControladorCita(cita, recepcionista);

        veterinario = new VistaVeterinario();

        ControladorVeterinario controladorVeterinario = new ControladorVeterinario(veterinario);

        consulta = new VistaConsulta();

        ControladorConsulta controladorConsulta = new ControladorConsulta(consulta, recepcionista, controladorVeterinario);

        historial = new VistaHistorial();

        ControladorHistorial controladorHistorial = new ControladorHistorial(historial, recepcionista);

        cobro = new VistaCobro();

        ControladorCobro controladorCobro = new ControladorCobro(cobro, recepcionista);

        menu.btnPropietario.addActionListener(e -> {
            abrir(propietario);
        });

        menu.btnMascota.addActionListener(e -> {
            controladorMascota.cargarPropietarios();
            abrir(mascota);
        });

        menu.btnCita.addActionListener(e -> {
            controladorCita.cargarMascotas();
            abrir(cita);
        });

        menu.btnVeterinario.addActionListener(e -> {
            abrir(veterinario);
        });

        menu.btnConsulta.addActionListener(e -> {
            controladorConsulta.cargarDatos();
            abrir(consulta);
        });

        menu.btnHistorial.addActionListener(e -> {
            controladorHistorial.cargarMascotas();
            abrir(historial);
        });

        menu.btnCobro.addActionListener(e -> {
            controladorCobro.cargarConsultas();
            abrir(cobro);
        });

        menu.btnSalir.addActionListener(e -> {
            System.exit(0);
        });
    }

    public void abrir(JFrame ventana) {

        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
}