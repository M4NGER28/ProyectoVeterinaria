/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloProyecto;

import java.util.ArrayList;

/**
 *
 * @author mange
 */
public class MascotaConvencional extends Mascota {
    private String raza;
    private ArrayList<String> esquemaVacunas;

    public MascotaConvencional(int id, String nombre, int edad, double pesoKg, Propietario propietario, String raza) {

        super(id, nombre, edad, pesoKg, propietario);

        this.raza = raza;
        this.esquemaVacunas = new ArrayList<>();
    }

    public String getRaza() {
        return raza;
    }

    public void registrarVacuna(String vacuna) {
        esquemaVacunas.add(vacuna);
    }

    public ArrayList<String> obtenerEsquemaVacunas() {
        return esquemaVacunas;
    }
}
    
    

