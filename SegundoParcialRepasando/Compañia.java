/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor./*

 */
package SegundoParcialTema3;

import Utiles.Cola;
import Utiles.Lista;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author repetto.francisco
 */
public class Compañia {

    private Lista capsCastellano;
    private Lista capsIngles;
    private Cola faltaTraduc;

    private Lock castellano;
    private Lock ingles;
    private Lock traducir;
    private Condition esperaTraductores;
    private Condition esperaSocioCaste;
    private Condition esperaSocioIngles;

    public Compañia() {
        capsCastellano = new Lista();
        capsIngles = new Lista();
        Cola faltaTraduc = new Cola();

        castellano = new ReentrantLock();
        ingles = new ReentrantLock();
        esperaTraductores = this.traducir.newCondition();
        esperaSocioCaste = this.castellano.newCondition();
        esperaSocioIngles = this.ingles.newCondition();
    }

    public void agregarCapCastellano(int capFilmado) {
        //Agrega el capitulo nuevo a la lista de castellano
        castellano.lock();
        capsCastellano.agregarElemento("Cap" + capFilmado, capFilmado);
        castellano.unlock();
    }

    public void agregarCapTraducir(int capFilmado) {
        //Agrega el capitulo a la cola de traduccir
        traducir.lock();
        faltaTraduc.poner(capFilmado);
        esperaTraductores.signalAll();
        traducir.unlock();
    }

    public int capsParaTraducir() {
        traducir.lock();
        while (faltaTraduc.esVacia()) {
            try {
                esperaTraductores.await();
            } catch (InterruptedException ex) {
                Logger.getLogger(Compañia.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        int capTraducir = (int) faltaTraduc.obtenerFrente();
        faltaTraduc.sacar();
        traducir.unlock();
        return capTraducir;
    }

    public void agregarCapIngles(int capTraducido) {
        ingles.lock();
        capsIngles.agregarElemento("Cap" + capTraducido, capTraducido);
        ingles.unlock();
    }

    public void verCapituloCast(int capQuiereVer) throws InterruptedException {
        castellano.lock();
        while (capsCastellano.recuperar(capQuiereVer) == null) {
            esperaSocioCaste.await();
        }
        castellano.unlock();
    }

    public void verCapituloIngles(int capQuiereVer) throws InterruptedException {
        ingles.lock();
        while (capsIngles.recuperar(capQuiereVer) == null) {
            esperaSocioIngles.await();
        }
        ingles.unlock();
    }

}
