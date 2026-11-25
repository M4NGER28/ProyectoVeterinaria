/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloProyecto;

/**
 *
 * @author mange
 */
public class Veterinario {
    private int id;
    private String nombre;
    private String especialidad;
    private String tarjetaProfesional;

    public Veterinario(int id, String nombre, String especialidad, String tarjetaProfesional) {

        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.tarjetaProfesional = tarjetaProfesional;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String getTarjetaProfesional() {
        return tarjetaProfesional;
    }

    public void atenderConsulta(Consulta c) {
        c.setVeterinario(this);
    }

    public void registrarDiagnostico(Consulta c, String diagnostico) {
        c.registrarDiagnostico(diagnostico);
    }

    @Override
    public String toString() {
        return nombre;
    }
}
    

