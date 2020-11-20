/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tp6.Punto7;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author repetto.francisco
 */
public class Brazo implements Runnable {

    private Mostrador mostrador;

    public Brazo(Mostrador mostrador) {
        this.mostrador = mostrador;
    }

    public void run() {
        while (true) {
            try {
                mostrador.retirarCaja();
                System.out.println(Thread.currentThread().getName()+" repuso la caja");
                mostrador.reponerCaja();
            } catch (InterruptedException ex) {
                Logger.getLogger(Brazo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
