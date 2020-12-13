/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repetto_FAI2548;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author repetto.francisco
 */
public class Coche implements Runnable {

    private GestorCruce gestorCruce;
    private String cruce; // Marca el cruce que quiere hacer el coche

    public Coche(GestorCruce unGestorCruce, String unCruce) {
        this.gestorCruce = unGestorCruce;
        this.cruce = unCruce;
    }

    public void run() {
        try {
            if (cruce.equals("Norte")) {
                cruzaPorNorte();
            } else {
                cruzaPorOeste();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Coche.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cruzaPorNorte() throws InterruptedException {
        gestorCruce.llegaNorte();
        cruzando();
        gestorCruce.sale();
    }

    private void cruzaPorOeste() throws InterruptedException {
        gestorCruce.llegaOeste();
        cruzando();
        gestorCruce.sale();
    }

    private void cruzando() {
        try {
            System.out.println(Thread.currentThread().getName() + ": esta cruzando la calle por " + cruce);
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Coche.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
    
}
