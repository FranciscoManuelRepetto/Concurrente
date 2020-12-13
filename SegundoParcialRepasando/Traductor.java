/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SegundoParcialTema3;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author repetto.francisco
 */
public class Traductor implements Runnable {

    Compañia compañia;

    public Traductor(Compañia unCompañia) {
        compañia = unCompañia;
    }

    public void run() {
        int capTraduciendo;
        while (true) {
            capTraduciendo = compañia.capsParaTraducir();
            traducir(capTraduciendo);
            compañia.agregarCapIngles(capTraduciendo);
        }
    }

    private void traducir(int capTraduciendo) {
        try {
            System.out.println(Thread.currentThread().getName() + ": traducioendo Cap:" + capTraduciendo);
            Thread.sleep(10000);
            System.out.println(Thread.currentThread().getName() + ": termino de traducir Cap:" + capTraduciendo);
        } catch (InterruptedException ex) {
            Logger.getLogger(Traductor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
