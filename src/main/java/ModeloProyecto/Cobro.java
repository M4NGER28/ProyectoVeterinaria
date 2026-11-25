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
public class Cobro {
     private int id;
    private LocalDateTime fecha;
    private double tarifaBase;
    private double costoVacunas;
    private double costoMedicamentos;
    private double recargoExotica;
    private double costoSedacion;
    private double descuento;
    private double total;

    private Consulta consulta;

    public Cobro(int id, Consulta consulta,
            double tarifaBase, double costoSedacion) {

        this.id = id;
        this.fecha = LocalDateTime.now();
        this.consulta = consulta;
        this.tarifaBase = tarifaBase;
        this.costoSedacion = costoSedacion;
    }

    public double calcularCostoVacunas() {

        costoVacunas = 0;

        Mascota mascota = consulta.getCita().getMascota();

        if (mascota instanceof MascotaConvencional) {

            for (Vacuna v : consulta.getVacunas()) {
                costoVacunas = costoVacunas + v.obtenerCosto();
            }
        }

        return costoVacunas;
    }

    public double calcularRecargoExotica() {

        Mascota mascota = consulta.getCita().getMascota();

        recargoExotica = mascota.calcularRecargo(tarifaBase);

        return recargoExotica;
    }

    public double calcularCostoSedacion() {

        Mascota mascota = consulta.getCita().getMascota();

        if (mascota instanceof MascotaExotica) {

            MascotaExotica exotica = (MascotaExotica) mascota;

            if (exotica.requiereSedacionEspecial()) {
                return costoSedacion;
            }
        }

        return 0;
    }
     public double calcularCostoMedicamentos() {

    costoMedicamentos = 0;

    for (TratamientoAplicado t : consulta.getMedicamentosAplicados()) {

        costoMedicamentos = costoMedicamentos + t.calcularSubtotal();
    }

    return costoMedicamentos;
}
    public double calcularSubtotal() {

    return tarifaBase + calcularCostoVacunas()+ calcularCostoMedicamentos() + calcularRecargoExotica() + calcularCostoSedacion();
}

    public double calcularDescuento(int cantidadMascotas) {

        if (cantidadMascotas > 2) {
            descuento = calcularSubtotal() * 0.10;
        } else {
            descuento = 0;
        }

        return descuento;
    }

    public double calcularTotal(int cantidadMascotas) {

        total = calcularSubtotal()
                - calcularDescuento(cantidadMascotas);

        return total;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public double getTarifaBase() {
        return tarifaBase;
    }

    public double getCostoVacunas() {
        return costoVacunas;
    }
    public double getCostoMedicamentos() {
    return costoMedicamentos;
}
    public double getRecargoExotica() {
        return recargoExotica;
    }

    public double getCostoSedacion() {
        return calcularCostoSedacion();
    }

    public double getDescuento() {
        return descuento;
    }

    public double getTotal() {
        return total;
    }

    public Consulta getConsulta() {
        return consulta;
    }
}

