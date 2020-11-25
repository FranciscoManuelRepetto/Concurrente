/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tp6.Punto1;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author repetto.francisco
 */
public class Agente implements Runnable {

    private SalaFumadores sala;
    private Random r;

    public Agente(SalaFumadores sala) {
        this.sala = sala;
        r = new Random();
    }

    public void run() {
        try {
            Thread.sleep(5000);//Se duerme para que lleguen antes los fumadores
        } catch (InterruptedException ex) {
            Logger.getLogger(Agente.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (true) {
            int i = (r.nextInt(3) + 1);
            cartelesAgente(i);
            sala.colocar(i); //Coloca dos de los tres ingredientes en la mesa
            sala.esperarFumador();
        }
    }

    private void cartelesAgente(int i) {
        int ing1 = i, ing2 = i + 1;
        if (ing2 == 4) {
            ing2 = 1;
        }
        System.out.println(Thread.currentThread().getName() + " coloco los ingredientes " + ing1 + " y " + ing2 + " en la mesa");
        System.out.println(Thread.currentThread().getName() + " espera a que fumador termine de fumar");
    }
}
