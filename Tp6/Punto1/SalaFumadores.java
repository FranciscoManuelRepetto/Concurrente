/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tp6.Punto1;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author repetto.francisco
 */
public class SalaFumadores {

    private int ingre1EnMesa, ingre2EnMesa;//1 es Tabaco, 2 es Papel, 3 es Fosforo
    private Semaphore semAgente;

    public SalaFumadores() {
        this.ingre1EnMesa = 0;
        this.ingre2EnMesa = 0;
        this.semAgente = new Semaphore(0);
    }

    public synchronized void entrarFumar(int id) throws InterruptedException {
        while (ingre1EnMesa == 0 || ingre1EnMesa == id || ingre2EnMesa == 0 || ingre2EnMesa == id) {
            wait();
        }
    }

    public synchronized void colocar(int primerIngre) {
        ingre1EnMesa = primerIngre;
        ingre2EnMesa = primerIngre + 1;
        if (ingre2EnMesa == 4) {
            ingre2EnMesa = 1;
        }
        notifyAll();
    }

    public void esperarFumador() {
        try {
            semAgente.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(SalaFumadores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void terminarFumar() {
        semAgente.release();
    }

    public int getIngre1EnMesa() {
        return ingre1EnMesa;
    }

    public int getIngre2EnMesa() {
        return ingre2EnMesa;
    }

    
    
    
}
