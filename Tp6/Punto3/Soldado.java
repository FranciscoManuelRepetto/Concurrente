/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tp6.Punto3;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author repetto.francisco
 */
public class Soldado implements Runnable {

    private Cuartel cuartel;
    private char bebida;//Si quiere agua es 'a' y si quiere gaseosa es 'g'
    private boolean postre;//Si el soldado quiere postre, es true y sino false

    public Soldado(Cuartel cuartel, char bebida, boolean postre) {
        this.cuartel = cuartel;
        this.bebida = bebida;
        this.postre = postre;
    }

    public void run() {
        llegando();
        try {
            cuartel.entrarCuartel();
            System.out.println(Thread.currentThread().getName() + " entro al cuartel");
            cuartel.pedirAlmuerzo();
            bebida();
            cuartel.terminarPedirAlmuerzo();
            postre();
            comer();
            cuartel.salirCuartel();
        } catch (InterruptedException ex) {
            Logger.getLogger(Soldado.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void llegando(){
        try {
            Thread.sleep((long) (Math.random()*20)*1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Soldado.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void bebida() {
        if (bebida == 'g') {
            try {
                System.out.println(Thread.currentThread().getName() + " pidio una gaseosa y necesita un abridor");
                cuartel.pedirAbridor();
                System.out.println(Thread.currentThread().getName() + " tiene un abridor");
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName() + " devuelve el abridor");
                cuartel.devolverAbridor();
            } catch (InterruptedException ex) {
                Logger.getLogger(Soldado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void postre() {
        if (postre) {
            try {
                System.out.println(Thread.currentThread().getName() + " pidio un postre");
                cuartel.pedirPostre();
                System.out.println(Thread.currentThread().getName() + " pidio un postre");
                cuartel.terminarPedirPostre();
            } catch (InterruptedException ex) {
                Logger.getLogger(Soldado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void comer() {
        try {
            System.out.println(Thread.currentThread().getName() + " esta comiendo");
            Thread.sleep(20000);
            System.out.println(Thread.currentThread().getName() + " termino de comer");
        } catch (InterruptedException ex) {
            Logger.getLogger(Soldado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
