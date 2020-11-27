/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tp6.Punto5ConSemaforos;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author repetto.francisco
 */
public class Coche implements Runnable {

    private Puente puente;
    private char direccion; //Si vienen por norte es 'N' y si vienen por sur 'S' 

    public Coche(Puente puente, char direccion) {
        this.puente = puente;
        this.direccion = direccion;
    }

    public void run() {
        if (direccion == 'N') {
            cruzarPorNorte();
        } else {
            if (direccion == 'S') {
                cruzarPorSur();
            }
        }
    }

    private void cruzarPorNorte() {
        boolean yaCruce = false;
        System.out.println(Thread.currentThread().getName() + " quiero cruzar por el NORTE");
        while (!yaCruce) {
            if (puente.intentarCruzar()) {
                cruzando();
                puente.terminarCruzarNorte();
                yaCruce = true;
            } else {
                try {
                    puente.esperandoNorte();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Coche.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void cruzarPorSur() {
        boolean yaCruce = false;
        System.out.println(Thread.currentThread().getName() + ": quiero cruzar por el SUR");
        while (!yaCruce) {
            if (puente.intentarCruzar()) {
                cruzando();
                puente.terminarCruzarSur();
                yaCruce = true;
            } else {
                try {
                    puente.esperandoSur();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Coche.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void cruzando() {
        try {
            System.out.println(Thread.currentThread().getName()+": ESTOY CRUZANDO!");
            Thread.sleep(10000);
            System.out.println(Thread.currentThread().getName()+": TERMINE DE CRUZAR");
        } catch (InterruptedException ex) {
            Logger.getLogger(Coche.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
