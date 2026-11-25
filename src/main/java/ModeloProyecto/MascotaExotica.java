/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloProyecto;

/**
 *
 * @author mange
 */
public class MascotaExotica  extends Mascota{
    private String permisoAmbientalICA;
    private boolean requiereSedacion;

    public MascotaExotica(int id, String nombre, int edad, double pesoKg, Propietario propietario,String permisoAmbientalICA, boolean requiereSedacion) {

        super(id, nombre, edad, pesoKg, propietario);

        this.permisoAmbientalICA = permisoAmbientalICA;
        this.requiereSedacion = requiereSedacion;
    }

    public String getPermisoAmbientalICA() {
        return permisoAmbientalICA;
    }

    public boolean requiereSedacionEspecial() {
        return requiereSedacion;
    }

    public double calcularRecargo(double tarifaBase) {
        return tarifaBase * 0.25;
    }
}
    

