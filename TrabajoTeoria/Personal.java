/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tp6.EjerciciosTeoria;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author repetto.francisco
 */
public class Personal implements Runnable {

    String nombre;
    Saludo saludo;
    boolean esJefe;

    Personal(Saludo s, String n) {
        esJefe = false;
        nombre = n;
        saludo = s;
    }

    public void run() {}
}
