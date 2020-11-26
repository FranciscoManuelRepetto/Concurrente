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
public class Investigador implements Runnable {

    private Observatorio observatorio;

    public Investigador(Observatorio observatorio) {
        this.observatorio = observatorio;
    }

    public void run() {
        try {
            esperando();
            observatorio.entrarInvestigar();
            simularInvestigacion();
            observatorio.salirInvestigar();
        } catch (InterruptedException ex) {
            Logger.getLogger(Investigador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void esperando() throws InterruptedException {
        Thread.sleep((long) (Math.random() * (10 - 15 + 1) + 15) * 1000);// Para que tarde entre 10 s y 15s
        System.out.println(Thread.currentThread().getName() + ": quiere entrar");
    }

    private void simularInvestigacion() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + ": entro a la sala");
        Thread.sleep((long) (Math.random() * (10 - 15 + 1) + 15) * 1000);// Para que tarde entre 10 s y 15s
        System.out.println(Thread.currentThread().getName() + ": salio de la sala");
    }
}
