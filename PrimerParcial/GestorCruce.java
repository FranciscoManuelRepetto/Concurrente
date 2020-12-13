/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repetto_FAI2548;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author repetto.francisco
 */
public class GestorCruce {

    private Semaphore semaforoNorte = new Semaphore(1, true); //Semaforo del cruce por norte
    private Semaphore semaforoOeste = new Semaphore(1, true);//Semaforo del cruce por oeste
    private Semaphore cruceLibre = new Semaphore(1);//Semaforo que marca si en el cruce no hay otro auto cruzando

    public void llegaNorte() throws InterruptedException {

        System.out.println(Thread.currentThread().getName() + ": llega al cruce norte");
        semaforoNorte.acquire();//Espera a que el semaforo se ponga en verde
        cruceLibre.acquire();//Espera a que en el cruce no haya ningun auto
    }

    public void llegaOeste() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + ": llega al cruce norte");
        semaforoOeste.acquire();//Espera a que el semaforo se ponga en verde
        cruceLibre.acquire();//Espera a que en el cruce no haya ningun auto
    }

    public void sale() {
        System.out.println(Thread.currentThread().getName() + ": ya cruzo el cruce");
        cruceLibre.release();
    }

    public void cambiaSemaforos() {
        while (true) {
            try {
                semaforoOeste.acquire();//Pone en rojo al semaforo oeste
                Thread.sleep(6000);
                semaforoNorte.acquire();//Pone al semaforo norte en rojo
                semaforoOeste.release();
                Thread.sleep(6000);
                semaforoNorte.release();
            } catch (InterruptedException ex) {
                Logger.getLogger(GestorCruce.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
