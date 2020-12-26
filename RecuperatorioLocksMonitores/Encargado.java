/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recuperatorio;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author repetto.francisco
 */
public class Encargado implements Runnable {

    private Farmacia farmacia;
    private int montoGlobal;

    public Encargado(Farmacia farmacia) {
        this.farmacia = farmacia;
        montoGlobal = 0;
    }

    public void run() {
        while (true) {
            try {
                Ficha fichaActual = farmacia.sacarFichaEncarg();
                //Toma los datos de la ficha
                int importe = fichaActual.getImporte();
                char fichero = fichaActual.getFicheroActual();
                System.out.println(Thread.currentThread().getName() + " tomo la receta del fichero: " + fichero
                        + " con importe: " + importe);
                montoGlobal = montoGlobal + importe;
                if (fichero == 'G') {
                    System.out.println(Thread.currentThread().getName() + " puso la ficha en el fichero-Contable");
                    fichaActual.setFicheroActual('C');
                    farmacia.ponerFichaConta(fichaActual);
                }
                System.out.println(Thread.currentThread().getName() + " monto global: " + montoGlobal);
            } catch (InterruptedException ex) {
                Logger.getLogger(Encargado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
