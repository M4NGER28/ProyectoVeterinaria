/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloProyecto;

import java.time.LocalDate;

/**
 *
 * @author mange
 */
public class Vacuna {
    private String nombre;
    private double costo;
    private LocalDate fechaAplicacion;

    public Vacuna(String nombre, double costo, LocalDate fechaAplicacion) {

        this.nombre = nombre;
        this.costo = costo;
        this.fechaAplicacion = fechaAplicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaAplicacion() {
        return fechaAplicacion;
    }

    public double obtenerCosto() {
        return costo;
    }
}
    

