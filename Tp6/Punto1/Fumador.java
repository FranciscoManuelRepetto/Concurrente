/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tp6.Punto1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author repetto.francisco
 */
public class Fumador implements Runnable {
    private int id;
    private SalaFumadores sala;
    
    public Fumador(int id, SalaFumadores sala) {
        this.id = id;
        this.sala = sala;
    }

    public void run() {
        while (true) {
            try {
                System.out.println(Thread.currentThread().getName() + " con id: " + id + " quiere fumar");
                sala.entrarFumar(id);
                fumar();
                sala.terminarFumar();
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Fumador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void fumar() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " esta fumando");
        Thread.sleep(10000);
        System.out.println(Thread.currentThread().getName() + " termino de fumar");
    }
}
