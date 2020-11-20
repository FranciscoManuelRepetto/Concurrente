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
public class Empaquetador implements Runnable {

    private Mostrador mostrador;

    public Empaquetador(Mostrador mostrador) {
        this.mostrador = mostrador;
    }

    public void run() {
        while (true) {
            try {
                int pesoPastel;
                pesoPastel = mostrador.tomarPastel();
                System.out.println(Thread.currentThread().getName()+" tomo un pastel que pesa: "+pesoPastel);
                mostrador.soltarPastel(pesoPastel);
                System.out.println(Thread.currentThread().getName()+" puso el pastel en la caja");
            } catch (InterruptedException ex) {
                Logger.getLogger(Empaquetador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
