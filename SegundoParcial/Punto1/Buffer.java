/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RepettoFAI2548Parcial2.EJercicio1;

/**
 *
 * @author repetto.francisco
 */
import Utiles.Cola;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {

    private Cola cola1;
    private Cola cola2;
    private int numDato;//Solamente sirve para ponerle un numero al dato que se inserta en la cola 
    private int etiquetaInserccion;
    private int etiquetaExtraccion;
    private Lock inserccion;
    private Lock extraccion;
    private Condition esperaExtractor;

    public Buffer() {
        this.cola1 = new Cola();
        this.cola2 = new Cola();
        this.numDato = 0;
        this.etiquetaInserccion = 1;
        this.etiquetaExtraccion = 2;
        this.inserccion = new ReentrantLock();
        this.extraccion = new ReentrantLock();
        this.esperaExtractor = this.extraccion.newCondition();
    }

    public boolean colaInsertarVacia() {
        boolean vacia;
        inserccion.lock();
        if (etiquetaInserccion == 1) {
            vacia = cola1.esVacia();
        } else {
            vacia = cola2.esVacia();
        }
        inserccion.unlock();
        return vacia;
    }

    public boolean colaExtraccionVacia() {
        boolean vacia;
        extraccion.lock();
        if (etiquetaExtraccion == 1) {
            vacia = cola1.esVacia();
        } else {
            vacia = cola2.esVacia();
        }
        extraccion.unlock();
        return vacia;
    }

    public String insertarDato() { //Retorna String, para despues mostrar que dato se inserto en la cola 
        String dato;
        inserccion.lock();
        numDato++;
        dato = "d" + numDato; // Guarda el dato
        if (etiquetaInserccion == 1) {
            cola1.poner(dato);
            dato = dato + "  en Cola1";//Guarda en que cola se inserto
        } else {
            cola2.poner(dato);
            dato = dato + "  en Cola2";
        }
        inserccion.unlock();
        return dato;
    }

    public void oscilarColas() {//Se cambia de etiquetas
        inserccion.lock();
        extraccion.lock();
        if (etiquetaInserccion == 1) {
            etiquetaInserccion = 2;
            etiquetaExtraccion = 1;
        } else {
            etiquetaInserccion = 1;
            etiquetaExtraccion = 2;
        }
        esperaExtractor.signalAll();
        inserccion.unlock();
        extraccion.unlock();
    }

    public String extraerDato() { // Retorna String para despues mostrar que dato se extrajo
        String dato;
        extraccion.lock();
        if (etiquetaExtraccion == 1) {
            dato = (String) cola1.obtenerFrente();//Guarda el dato
            dato = dato + "  en Cola1";//Guarda en que cola se extrajo
            cola1.sacar();
        } else {
            dato = (String) cola2.obtenerFrente();
            dato = dato + "  en Cola2";
            cola2.sacar();
        }
        extraccion.unlock();
        return dato;
    }

    public void esperarOscilacion() throws InterruptedException {
        extraccion.lock();
        while (colaExtraccionVacia()) {
            esperaExtractor.await();
        }
        extraccion.unlock();
    }
}
