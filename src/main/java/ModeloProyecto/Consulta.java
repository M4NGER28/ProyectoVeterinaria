/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloProyecto;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author mange
 */
public class Consulta {
    private int id;
    private LocalDateTime fechaHora;
    private String diagnostico;
    private String notasMedicas;
    private ArrayList<TratamientoAplicado> medicamentosAplicados;
    private ArrayList<Vacuna> vacunas;

    private Cita cita;
    private Veterinario veterinario;

    public Consulta(int id, Cita cita, Veterinario veterinario) {

        this.id = id;
        this.fechaHora = LocalDateTime.now();
        this.cita = cita;
        this.veterinario = veterinario;
        this.diagnostico = "";
        this.notasMedicas = "";
        this.medicamentosAplicados = new ArrayList<>();
        this.vacunas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public String getNotasMedicas() {
        return notasMedicas;
    }

    public Cita getCita() {
        return cita;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    public void registrarDiagnostico(String texto) {
        diagnostico = texto;
    }

    public void agregarNota(String nota) {
        notasMedicas = notasMedicas + nota + "\n";
    }

    public void agregarTratamiento(TratamientoAplicado t) {
        medicamentosAplicados.add(t);
    }

    public void agregarVacuna(Vacuna vacuna) {
        vacunas.add(vacuna);
    }

    public ArrayList<Vacuna> getVacunas() {
        return vacunas;
    }

    public ArrayList<TratamientoAplicado> getMedicamentosAplicados() {
        return medicamentosAplicados;
    }

    public void cerrarConsulta() {

        if (!cita.getEstado().equals("Atendida")) {

            cita.getMascota().getHistorial().agregarConsulta(this);

            if (cita.getMascota() instanceof MascotaConvencional) {

                MascotaConvencional mascota = (MascotaConvencional) cita.getMascota();

                for (Vacuna v : vacunas) { mascota.registrarVacuna(v.getNombre());
                }
            }

            cita.finalizar();
        }
    }
}
    

