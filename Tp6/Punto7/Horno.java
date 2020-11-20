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
public class Horno implements Runnable {

    private int pesoDePasteles;
    private Mostrador mostrador;

    public Horno(int pesoDePasteles, Mostrador mostrador) {
        this.pesoDePasteles = pesoDePasteles;
        this.mostrador = mostrador;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName()+" produce pasteles que pesan: "+pesoDePasteles);
        while (true) {
            try {
                cocinando();
                mostrador.esperarEspacio();
                mostrador.ponerPastel(pesoDePasteles);
                System.out.println(Thread.currentThread().getName()+" puso un pastel en el mostrador");
            } catch (InterruptedException ex) {
                Logger.getLogger(Horno.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void cocinando() {
        // Simula tiempo que el horno esta cocinando el pastel
        try {
            Thread.sleep((long) (Math.random() * (10 - 20 + 1) + 20) * 1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Horno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
