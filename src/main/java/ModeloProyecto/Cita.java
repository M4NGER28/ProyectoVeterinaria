/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloProyecto;

import java.time.LocalDateTime;

/**
 *
 * @author mange
 */
public class Cita {
    private int id;
    private LocalDateTime fechaHora;
    private String motivo;
    private String estado;
    private Mascota mascota;

    public Cita(int id, LocalDateTime fechaHora, String motivo, Mascota mascota) {

        this.id = id;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.mascota = mascota;
        this.estado = "Programada";
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getEstado() {
        return estado;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void programar() {
        estado = "Programada";
    }

    public void reprogramar(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
        estado = "Reprogramada";
    }

    public void cancelar() {
        estado = "Cancelada";
    }

    public void finalizar() {
        estado = "Atendida";
    }
}
    

