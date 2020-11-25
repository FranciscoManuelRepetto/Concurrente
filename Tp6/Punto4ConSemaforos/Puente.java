/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tp6.Punto4ConSemaforos;

import java.util.concurrent.Semaphore;

/**
 *
 * @author repetto.francisco
 */
public class Puente {

    private Semaphore semCarril;
    private Semaphore esperandoNorte;
    private Semaphore esperandoSur;

    public Puente() {
        this.semCarril = new Semaphore(1, true);
        this.esperandoNorte = new Semaphore(0, true);
        this.esperandoSur = new Semaphore(0, true);
    }

    public boolean intentarCruzar() {
        boolean puedoCruzar = false;
        if (semCarril.tryAcquire()) {
            //Puede usar el cruce si no hay autos en el carril
            puedoCruzar = true;
        }
        return puedoCruzar;
    }

    public void terminarCruzarNorte() {
        semCarril.release();
        if (esperandoNorte.hasQueuedThreads()) {
            esperandoNorte.release();
        } else {
            esperandoSur.release();
        }
    }

    public void esperandoNorte() throws InterruptedException {
        esperandoNorte.acquire();
    }

    public void terminarCruzarSur() {
        semCarril.release();
        if (esperandoSur.hasQueuedThreads()) {
            esperandoSur.release();
        } else {
            esperandoNorte.release();
        }
    }

    public void esperandoSur() throws InterruptedException {
        esperandoSur.acquire();
    }

}
