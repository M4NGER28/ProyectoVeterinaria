/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectoveterinaria;

import ControladorProyecto.ControladorMenu;
import VistaProyecto.VistaMenu;

/**
 *
 * @author mange
 */
public class MainProyectoVeterinaria {

    public static void main(String[] args) {
        VistaMenu menu = new VistaMenu();

        new ControladorMenu(menu);

        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
    }
}
    

