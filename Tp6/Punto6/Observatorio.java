/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tp6.Punto6;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author repetto.francisco
 */
public class Observatorio {

    private int maxEntrar;//Cantidad maxima de investigadores o mante
    private int entraron; // Cantidad de mante que ya entraron
    private int cantInves;//Cantidad de investigadores en la sala
    private int cantMante;//Cantidad de personal de matenimiento en la sala
    private int cantVisi;//Cantidad de visitantes en la sala
    private int capacidadActual;//Cantidad maxima que pueden entrar de visitantes al mismo tiempo
    private int cantSillaDeRuedas;//Cantidad de visitante en la sala que tienen silla de ruedas
    //Lock y conditions
    private Lock mutex;
    private Condition esperaMante;
    private Condition esperaInves;
    private Condition esperaVisitante;

    public Observatorio(int maxEntrar) {
        this.maxEntrar = maxEntrar;
        this.entraron = 0;
        this.cantInves = 0;
        this.cantMante = 0;
        this.cantVisi = 0;
        this.capacidadActual = 10;// Segun en el enunciado es 50 pero para probarlo
        this.cantSillaDeRuedas = 0;
        //Lock y conditions
        this.mutex = new ReentrantLock();
        this.esperaVisitante = this.mutex.newCondition();
        this.esperaMante = this.mutex.newCondition();
        this.esperaInves = this.mutex.newCondition();
    }

    public void entrarVisitante(boolean sillaDeRuedas) throws InterruptedException {
        mutex.lock();
        while (cantVisi >= capacidadActual || cantInves != 0 || cantMante != 0) {//Espera para entrar visitante
            esperaVisitante.await();
        }
        //Ya entro
        if (sillaDeRuedas) {
            cantSillaDeRuedas++;
            actualizarCapacidad();
        }
        cantVisi++;
        mutex.unlock();
    }

    public void salirVisitante(boolean sillaDeRuedas) {
        mutex.lock();
        cantVisi--;
        if (sillaDeRuedas) {
            cantSillaDeRuedas--;
            actualizarCapacidad();
        }
        if (cantVisi == 0) {
            esperaInves.signal();
            esperaMante.signalAll();
        }
        mutex.unlock();
    }

    private void actualizarCapacidad() {
        //En el enunciado dice que son 50 y 30 si hay silla de rueda pero para probarlo
        if (cantSillaDeRuedas != 0) {
            capacidadActual = 5;
        } else {
            capacidadActual = 10;
        }
    }

    public void entrarMantenimiento() throws InterruptedException {
        mutex.lock();
        while (entraron >= maxEntrar || cantInves != 0 || cantVisi != 0) {//Espera para entrar personal de mantenimiento
            esperaMante.await();
        }
        //Ya entro
        cantMante++;
        entraron++;
        mutex.unlock();
    }

    public void salirMantenimiento() {
        mutex.lock();
        cantMante--;
        if (cantMante == 0) {
            entraron = 0;
            esperaInves.signal();
            esperaVisitante.signalAll();
        }
        mutex.unlock();
    }

    public void entrarInvestigar() throws InterruptedException {
        mutex.lock();
        while (cantInves != 0 || cantVisi != 0 || cantMante != 0) {
            esperaInves.await();
        }
        cantInves++;
        mutex.unlock();
    }

    public void salirInvestigar() {
        mutex.lock();
        cantInves--;
        esperaInves.signal();
        esperaMante.signalAll();
        esperaVisitante.signalAll();
        mutex.unlock();
    }

}
