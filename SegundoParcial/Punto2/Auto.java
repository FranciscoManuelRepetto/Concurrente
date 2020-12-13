/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RepettoFAI2548Parcial2.Ejercicio2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author repetto.francisco
 */
public class Auto implements Runnable {

    private Puerto puerto;

    public Auto(Puerto puerto) {
        this.puerto = puerto;
    }

    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + ": quiere subir al transbordador");
            simulacion();
            puerto.subirseTransbordador();
            System.out.println(Thread.currentThread().getName() + ": SE SUBIO");
            puerto.esperarAutos();
            System.out.println(Thread.currentThread().getName() + ": ESTA BAJANDO");
            simulacion();
            System.out.println(Thread.currentThread().getName() + ": SE BAJO");
            puerto.avisarQueBajo();
        } catch (InterruptedException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void simulacion() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
