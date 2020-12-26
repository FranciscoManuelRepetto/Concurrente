/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recuperatorio;

import Utiles.Pila;
import java.util.concurrent.locks.Lock;

/**
 *
 * @author repetto.francisco
 */
public class Farmacia {

    private Pila fichasGene;
    private Pila fichasConta;
    private Pila fichasEncar;
    private int pizarra;

    public Farmacia() {
        fichasGene = new Pila();
        fichasConta = new Pila();
        fichasEncar = new Pila();
        pizarra = 0;
    }

    public synchronized void ponerFichaGen(Ficha fichaNueva) {
        fichasGene.apilar(fichaNueva);
        notifyAll();
    }

    public synchronized Ficha sacarFichaAuxConta() throws InterruptedException {
        while (fichasGene.esVacia() && fichasConta.esVacia()) {
            //El auxiliar contable espera si no hay fichas disponbles en fichas-Generales o en fichas-Contables
            wait();
        }
        Ficha fichaActual;
        if (!fichasGene.esVacia()) {
            fichaActual = (Ficha) fichasGene.obtenerTope();
            fichasGene.desapilar();
        } else {
            fichaActual = (Ficha) fichasConta.obtenerTope();
            fichasConta.desapilar();
        }
        return fichaActual;
    }

    public synchronized void registrarImporteConta() {
        pizarra++;
    }

    public synchronized void ponerFichaEncar(Ficha fichaActual) {
        fichasEncar.apilar(fichaActual);
        notifyAll();
    }

    public synchronized Ficha sacarFichaEncarg() throws InterruptedException {
        while (fichasEncar.esVacia() && fichasGene.esVacia()) {
            //El auxiliar contable espera si no hay fichas disponbles en fichas-Generales o en fichas-Contables
            wait();
        }
        Ficha fichaActual;
        if (!fichasEncar.esVacia()) {//Si pudo salir del while es porque una de las pilas no esta vacia
            fichaActual = (Ficha) fichasEncar.obtenerTope();
            fichasEncar.desapilar();
        } else {
            fichaActual = (Ficha) fichasGene.obtenerTope();
            fichasGene.desapilar();
        }
        return fichaActual;
    }

    public synchronized void ponerFichaConta(Ficha fichaActual) {
        fichasConta.apilar(fichaActual);
    }

}
