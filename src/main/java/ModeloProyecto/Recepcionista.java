/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloProyecto;

import java.util.ArrayList;

/**
 *
 * @author mange
 */
public class Recepcionista {
     private int id;
    private String nombre;
    private String telefono;

    private ArrayList<Propietario> propietarios;
    private ArrayList<Mascota> mascotas;
    private ArrayList<Cita> citas;
    private ArrayList<Cobro> cobros;

    public Recepcionista(int id, String nombre,
            String telefono) {

        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;

        propietarios = new ArrayList<>();
        mascotas = new ArrayList<>();
        citas = new ArrayList<>();
        cobros = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void registrarPropietario(Propietario p) {
        propietarios.add(p);
    }

    public void registrarMascota(Mascota m) {

        mascotas.add(m);
        m.getPropietario().agregarMascota(m);
    }

    public void agendarCita(Cita c) {
        citas.add(c);
    }

    public void registrarCobro(Cobro c) {
        cobros.add(c);
    }

    public Comprobante emitirComprobante(Cobro c) {

        Comprobante comprobante =
                new Comprobante("C-" + c.getId());

        comprobante.generar(c);

        return comprobante;
    }

    public ArrayList<Propietario> getPropietarios() {
        return propietarios;
    }

    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }

    public ArrayList<Cita> getCitas() {
        return citas;
    }

    public ArrayList<Cobro> getCobros() {
        return cobros;
    }
}
    

