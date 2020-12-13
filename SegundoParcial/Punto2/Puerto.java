/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RepettoFAI2548Parcial2.Ejercicio2;

import java.util.concurrent.Semaphore;

/**
 *
 * @author repetto.francisco
 */
public class Puerto {

    private Semaphore lugaresTransbo;//Cantidad de espacios disponibles en el transbordador
    private Semaphore esperaAutos;//Espera de los autos arriba del transbordador
    private Semaphore esperaTransbordador;
    private Semaphore autosBajaron;//Cantidad de autos que bajaron

    public Puerto() {
        this.lugaresTransbo = new Semaphore(10, true);
        this.esperaAutos = new Semaphore(0, true);
        this.autosBajaron = new Semaphore(0);
        this.esperaTransbordador = new Semaphore(0);
    }

    public void subirseTransbordador() throws InterruptedException {
        lugaresTransbo.acquire();
        esperaTransbordador.release();
    }

    public void esperarCruzar() throws InterruptedException {
        esperaTransbordador.acquire(10);
    }

    public void esperarAutos() throws InterruptedException {
        esperaAutos.acquire();//Cuando deja de esperar, se baja del transbo
        esperaTransbordador.release();
    }

    public void avisarQueBajo() {
        if (esperaAutos.hasQueuedThreads()) {//Verifica si hay otro auto para bajar para no liberar permisos de mas
            esperaAutos.release();
        }
    }

    public void terminoDeCruzar() throws InterruptedException {
        esperaAutos.release();
        esperaTransbordador.acquire(10);
    }

    public void volvioTransbordador() {
        lugaresTransbo.release(10);
    }

}
