/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tp6.Punto6;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author repetto.francisco
 */
public class Visitante implements Runnable {

    private Observatorio observatorio;
    private boolean sillaDeRuedas;//Si es true, entonces el visitante esta en sillas de ruedas

    public Visitante(Observatorio observatorio, boolean sillaDeRuedas) {
        this.observatorio = observatorio;
        this.sillaDeRuedas = sillaDeRuedas;
    }

    public void run() {
        try {
            llegando();
            observatorio.entrarVisitante(sillaDeRuedas);
            simularVisita();
            observatorio.salirVisitante(sillaDeRuedas);
        } catch (InterruptedException ex) {
            Logger.getLogger(Visitante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void simularVisita() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + ": entro");
        Thread.sleep((long) (Math.random() * (5 - 10 + 1) + 10) * 1000);// Para que tarde entre 5 s y 10s
        System.out.println(Thread.currentThread().getName() + ": salio");
    }

    private void llegando() throws InterruptedException {
        Thread.sleep((long) (Math.random() * (6 - 15 + 1) + 15) * 1000);// Para que tarde entre 6 s y 15s
        System.out.println(Thread.currentThread().getName()+" quiere entrar");
    }

}
