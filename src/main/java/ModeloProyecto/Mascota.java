package ModeloProyecto;

import ModeloProyecto.HistorialClinico;
import ModeloProyecto.Propietario;

public class Mascota {

    private int id;
    private String nombre;
    private int edad;
    private double pesoKg;

    private Propietario propietario;
    private HistorialClinico historial;

    public Mascota(int id, String nombre, int edad, double pesoKg, Propietario propietario) {

        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.pesoKg = pesoKg;
        this.propietario = propietario;
        this.historial = new HistorialClinico(id);
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public HistorialClinico getHistorial() {
        return historial;
    }

    public String obtenerResumen() {
        return nombre + " - Edad: " + edad + " - Peso: " + pesoKg + " kg";
    }

    public double calcularRecargo(double tarifaBase) {
        return 0;
    }

    @Override
    public String toString() {
        return nombre;
    }
}