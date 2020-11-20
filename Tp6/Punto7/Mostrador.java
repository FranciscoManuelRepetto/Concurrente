/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tp6.Punto7;

/**
 *
 * @author repetto.francisco
 */
import Utiles.Cola;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Mostrador {

    private Cola colaPasteles;//Estructura que guarda el peso de los pasteles que estan el mostrador
    private int cantPastelesMax; // Cantidad maxima de pasteles que pueden estar en mostrador
    private int pastelesEnMostrador;// Cantidad de pasteles que hay actualmente 
    private int pesoMax;// Peso maximo que puede tener la caja
    private Caja caja;// Caja en el mostrador
    private boolean cajaLlena;
    //Locks y Conditions
    private Lock lockCola;
    private Lock lockCaja;
    private Condition esperaHorno;//Horno espera que haya espacio en el mostrador
    private Condition esperaEmpaquetador;//Empaquetador espera que haya pastel en mostrador
    private Condition esperaEmpaquetadorCaja;//Empaquetador espera que repongan la caja
    private Condition esperaBrazo;//Brazo espera que la caja este llena

    public Mostrador(int maxPasteles, int pesoMax) {
        this.colaPasteles = new Cola();
        this.cantPastelesMax = maxPasteles;
        this.pastelesEnMostrador = 0;
        this.pesoMax = pesoMax;
        this.caja = new Caja();
        this.cajaLlena = false;

        this.lockCola = new ReentrantLock();
        this.lockCaja = new ReentrantLock();
        this.esperaHorno = this.lockCola.newCondition();
        this.esperaEmpaquetador = this.lockCola.newCondition();
        this.esperaEmpaquetadorCaja = this.lockCaja.newCondition();
        this.esperaBrazo = this.lockCaja.newCondition();
    }

    //METODOS HORNO
    public void ponerPastel(int unPeso) {
        lockCola.lock();
        colaPasteles.poner(unPeso);
        pastelesEnMostrador++;
        esperaEmpaquetador.signalAll();
        lockCola.unlock();
    }

    public void esperarEspacio() throws InterruptedException {
        lockCola.lock();
        while (!(pastelesEnMostrador < cantPastelesMax)) {
            esperaHorno.await();
        }
        lockCola.unlock();
    }

    //METODOS EMPAQUETADOR
    public int tomarPastel() throws InterruptedException {
        int pesoPastel = 0;
        lockCola.lock();
        while (pastelesEnMostrador == 0) {
            esperaEmpaquetador.await();
        }
        pesoPastel = (int) colaPasteles.obtenerFrente();
        colaPasteles.sacar();
        pastelesEnMostrador--;
        esperaHorno.signal();
        lockCola.unlock();
        return pesoPastel;
    }

    public void soltarPastel(int pesoPastel) throws InterruptedException {
        lockCaja.lock();
        while (!(caja.getPeso()+pesoPastel <= pesoMax)) {
            cajaLlena = true;
            esperaBrazo.signal();
            System.out.println("CAJA LLENA");
            esperaEmpaquetadorCaja.await();
        }
        caja.agregarPastel(pesoPastel);
        lockCaja.unlock();
    }

    //METODOS BRAZO
    public void retirarCaja() throws InterruptedException {
        lockCaja.lock();
        while (!cajaLlena) {
            esperaBrazo.await();
        }
        lockCaja.unlock();
    }

    public void reponerCaja() {
        Caja cajaNueva = new Caja();
        lockCaja.lock();
        caja = cajaNueva;
        cajaLlena = false;
        esperaEmpaquetadorCaja.signalAll();
        lockCaja.unlock();
    }

}
