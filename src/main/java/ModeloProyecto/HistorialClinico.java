/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloProyecto;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author mange
 */
public class HistorialClinico {
    private int id;
    private LocalDate fechaApertura;
    private ArrayList<Consulta> consultas;

    public HistorialClinico(int id) {

        this.id = id;
        this.fechaApertura = LocalDate.now();
        this.consultas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public LocalDate getFechaApertura() {
        return fechaApertura;
    }

    public void agregarConsulta(Consulta consulta) {
        consultas.add(consulta);
    }

    public ArrayList<Consulta> obtenerConsultas() {
        return consultas;
    }

    public ArrayList<Consulta> consultarHistorial() {
        return consultas;
    }
} 
    

