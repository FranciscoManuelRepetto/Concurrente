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
public class Socio implements Runnable {

    private Compañia compañia;
    private char idioma; //'C' es castellano y 'I' es ingles
    private int capQuiereVer; //Capitulo desde el que quiere empezar a ver el socio

    public Socio(Compañia compañia, char idioma, int capsQuiereVer) {
        this.compañia = compañia;
        this.idioma = idioma;
        this.capQuiereVer = capQuiereVer;
    }

    public void run() {
        try {
            System.out.println(Thread.currentThread()+": quiere ver el Cap"+capQuiereVer+" en idioma:"+idioma);
            if(idioma == 'C'){
                compañia.verCapituloCast(capQuiereVer);
            }else{
                compañia.verCapituloIngles(capQuiereVer);
            }
            Thread.sleep(7000);
            System.out.println(Thread.currentThread().getName()+": termino de ver el captitulo");
        } catch (InterruptedException ex) {
            Logger.getLogger(Socio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
