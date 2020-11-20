/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tp6.Punto8;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author repetto.francisco
 */
public class Donador implements Runnable {

    private Centro centro;
    private char espera; // Si es 'S', le gustaria esperar sentado y si es 'P' le gustaria parado

    public Donador(Centro centro, char espera) {
        this.centro = centro;
        this.espera = espera;
    }

    public void run() {
        try {
            llegando();//Llega al centro
            if (!centro.solicitarAtencion()) {//Avisa que va a donar sangre
                esperar();//Si no hay camillas, espera en la sala de espera
            }
            donando();//Le sacan sangre
            centro.dejarCentro();//Deja la camilla y se va
        } catch (InterruptedException ex) {
            Logger.getLogger(Donador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void llegando() throws InterruptedException {
        Thread.sleep((long) (Math.random() * 40) * 1000);
        System.out.println(Thread.currentThread().getName() + ": llego al centro");
    }

    private void esperar() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + ": esta esperando " + espera + " y quiere una revista");
        centro.agarrarRevista();
        System.out.println(Thread.currentThread().getName() + ": tiene una revista");
        centro.esperarAtencion();
    }

    private void donando() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + ": DONANDO");
        Thread.sleep((long) (Math.random() * (25 - 40 + 1) + 40) * 1000);// Para que tarde entre 25 s y 40s 
        System.out.println(Thread.currentThread().getName() + ": TERMINO DE DONAR");
    }
}
