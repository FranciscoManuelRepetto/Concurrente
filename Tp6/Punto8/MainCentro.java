/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tp6.Punto8;

/**
 *
 * @author repetto.francisco
 */
public class MainCentro {

    public static void main(String[] args) {
        Centro centro = new Centro(4, 9);

        int cantDonadores = 20;
        Donador[] arregloDonadores = new Donador[cantDonadores];

        for (int i = 0; i < cantDonadores / 2; i++) {
            arregloDonadores[i] = new Donador(centro, 'S');
        }

        for (int i = cantDonadores / 2; i < cantDonadores; i++) {
            arregloDonadores[i] = new Donador(centro, 'P');
        }

        Thread[] donadores = new Thread[cantDonadores];
        for (int i = 0; i < cantDonadores / 2; i++) {
            donadores[i] = new Thread(arregloDonadores[i], VERDE + "Donador" + i);
        }

        for (int i = cantDonadores / 2; i < cantDonadores; i++) {
            donadores[i] = new Thread(arregloDonadores[i], AMARILLO + "Donador" + i);
        }

        for (int i = 0; i < cantDonadores; i++) {
            donadores[i].start();
        }

    }

    public static final String VERDE = "\u001B[32m";
    public static final String AMARILLO = "\u001B[33m";
}
