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
public class Propietario {
    private int id;
    private String nombre;
    private String documento;
    private String telefono;
    private String correo;
    private String direccion;
    private ArrayList<Mascota> mascotas;

    public Propietario(int id, String nombre, String documento,String telefono, String correo, String direccion) {

    this.id = id;
    this.nombre = nombre;
    this.documento = documento;
    this.telefono = telefono;
    this.correo = correo;
    this.direccion = direccion;
    this.mascotas = new ArrayList<>();
}
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void actualizarDatos(String telefono, String correo, String direccion){
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
    }
    
    public void agregarMascota(Mascota mascota){
        mascotas.add(mascota);
    }
    public int contarMascotas(){
        return mascotas.size();
    }
      public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }

    
    public String toString() {
        return nombre;
    }
}