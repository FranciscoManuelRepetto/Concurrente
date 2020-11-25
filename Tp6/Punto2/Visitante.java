/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tp6.Punto2;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author repetto.francisco
 */
public class Visitante implements Runnable {

    private GestorSala gestor;
    private boolean jubilada;

    public Visitante(GestorSala gestor, boolean jubilada) {
        this.gestor = gestor;
        this.jubilada = jubilada;
    }

    public void run() {
        try {
            llegando();
            if (!jubilada) {//Para que no lleguen todos juntos
                gestor.entrarSala();
            } else {
                gestor.entrarSalaJubilado();
            }
            visita();
            System.out.println(Thread.currentThread().getName() + " salio de la sala");
            gestor.salirSala();
        } catch (InterruptedException ex) {
            Logger.getLogger(Visitante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void llegando() {
        try {
            int llegando = (int) (Math.random() * 10);
            Thread.sleep(llegando * 1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Visitante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void visita() {
        try {
            Thread.sleep(12000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Visitante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
