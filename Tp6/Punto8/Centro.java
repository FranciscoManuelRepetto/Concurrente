/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tp6.Punto8;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author repetto.francisco
 */
public class Centro {

    private int cantCamillas;//Cantidad de camillas en el centro
    private int cantRevistas;//Cantidad de revistas en el centro
    private final Lock lockCamillas;
    private final Lock lockRevistas;
    private final Condition esperanCamilla;
    private final Condition esperanRevista;

    public Centro(int cantCamillas, int cantRevistas) {
        this.cantCamillas = cantCamillas;
        this.cantRevistas = cantRevistas;
        lockCamillas = new ReentrantLock(true);
        lockRevistas = new ReentrantLock(true);
        esperanCamilla = this.lockCamillas.newCondition();
        esperanRevista = this.lockRevistas.newCondition();
    }

    public boolean solicitarAtencion() {//Sincronizado para asegurar la exclusion mutua de turnoEspera
        boolean puedeDonar = false;
        lockCamillas.lock();
        if (cantCamillas > 0) {
            puedeDonar = true;
            cantCamillas--;
        }
        lockCamillas.unlock();
        return puedeDonar;
    }

    public void agarrarRevista() throws InterruptedException {
        lockRevistas.lock();
        while (cantRevistas == 0) {
            esperanRevista.await();
        }
        cantRevistas--;
        lockRevistas.unlock();
    }

    void esperarAtencion() throws InterruptedException {
        //Espera a que haya camas disponibles
        lockCamillas.lock();
        while (cantCamillas == 0) {
            esperanCamilla.await();
        }
        cantCamillas--;
        lockCamillas.unlock();
        lockRevistas.lock();// Como lo van a atender, deja la revista
        cantRevistas++;
        esperanRevista.signal();// Avisa que dejo la revista
        lockRevistas.unlock();
    }

    void dejarCentro() {
        lockCamillas.lock();//Como termino de donar, deja la camilla y aumenta un turno
        cantCamillas++;
        esperanCamilla.signalAll();// Le avisa a todos para que asegurar que lo vea al que le toca su turno
        lockCamillas.unlock();
    }
}
