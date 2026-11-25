package ModeloProyecto;

import java.time.LocalDateTime;

public class Comprobante implements Imprimible {

    private String numero;
    private LocalDateTime fechaEmision;
    private String nota;
    private double totalPagado;

    public Comprobante(String numero) {
        this.numero = numero;
        this.fechaEmision = LocalDateTime.now();
    }

    public void generar(Cobro cobro) {

        int cantidadMascotas = cobro.getConsulta()
                .getCita().getMascota()
                .getPropietario().contarMascotas();

        totalPagado = cobro.calcularTotal(cantidadMascotas);

        nota = "SISTEMA DE GESTION VETERINARIA\n"
        + "Comprobante: " + numero + "\n"
        + "Fecha: " + fechaEmision + "\n"
        + "Tarifa base: $" + cobro.getTarifaBase() + "\n"
        + "Vacunas: $" + cobro.getCostoVacunas() + "\n"
        + "Medicamentos: $" + cobro.getCostoMedicamentos() + "\n"
        + "Recargo: $" + cobro.getRecargoExotica() + "\n"
        + "Sedacion: $" + cobro.getCostoSedacion() + "\n"
        + "Descuento: $" + cobro.getDescuento() + "\n"
        + "Total pagado: $" + totalPagado;
    }

    public String getNumero() {
        return numero;
    }

    public LocalDateTime getFechaEmision() {
        return fechaEmision;
    }

    public String getNota() {
        return nota;
    }

    public double getTotalPagado() {
        return totalPagado;
    }

    @Override
    public void imprimir() {
        System.out.println(nota);
    }
}