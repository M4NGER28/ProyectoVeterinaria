/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloProyecto;

/**
 *
 * @author mange
 */
public class TratamientoAplicado {
    private int id;
    private String tipo;
    private String descripcion;
    private int cantidad;
    private double costoUnitario;

    public TratamientoAplicado(int id, String tipo, String descripcion, int cantidad, double costoUnitario) {

        this.id = id;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.costoUnitario = costoUnitario;
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getCostoUnitario() {
        return costoUnitario;
    }

    public double calcularSubtotal() {
        return cantidad * costoUnitario;
    }
}
    

