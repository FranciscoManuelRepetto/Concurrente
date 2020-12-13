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
public class Transbordador implements Runnable {

    private Puerto puerto;

    public Transbordador(Puerto puerto) {
        this.puerto = puerto;
    }

    public void run() {
        while (true) {
            try {
                System.out.println(Thread.currentThread().getName() + ": esta esperando a llenarse");
                puerto.esperarCruzar();
                cruzarRio();
                puerto.terminoDeCruzar();
                System.out.println(Thread.currentThread().getName() + " vuelve vacio");
                cruzarRio();
                puerto.volvioTransbordador();
            } catch (InterruptedException ex) {
                Logger.getLogger(Transbordador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void cruzarRio() {
        try {
            System.out.println(Thread.currentThread().getName() + ": cruzando el rio");
            Thread.sleep(15000);
            System.out.println(Thread.currentThread().getName() + ": termino de cruzar el rio");
        } catch (InterruptedException ex) {
            Logger.getLogger(Transbordador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
