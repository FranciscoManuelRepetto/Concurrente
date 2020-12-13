/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SegundoParcialTema3;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author repetto.francisco
 */
public class Filmador implements Runnable {

    Compañia compañia;
    int capFilmado;
    int maxCaps;//Capitulos que tiene la serie

    public Filmador(Compañia unCompañia, int unMaxCaps) {
        compañia = unCompañia;
        capFilmado = 0;
        maxCaps = unMaxCaps;
    }

    public void run() {
        while (capFilmado < maxCaps) {
            try {
                capFilmado++;
                System.out.println(Thread.currentThread().getName() + ": filmando Cap" + capFilmado);
                Thread.sleep(6000);
                System.out.println(Thread.currentThread().getName() + ": termino de filmar Cap" + capFilmado);
                compañia.agregarCapCastellano(capFilmado);
                compañia.agregarCapTraducir(capFilmado);
            } catch (InterruptedException ex) {
                Logger.getLogger(Filmador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
