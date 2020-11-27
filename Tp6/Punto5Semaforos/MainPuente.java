/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tp6.Punto5ConSemaforos;

/**
 *
 * @author repetto.francisco
 */
public class MainPuente {

    public static void main(String[] args) {
        Puente puente = new Puente();

        int cantAutos = 6;
        Coche[] arregloAutos = new Coche[cantAutos];

        for (int i = 0; i < cantAutos / 2; i++) {
            arregloAutos[i] = new Coche(puente, 'N');
        }

        for (int i = cantAutos / 2; i < cantAutos; i++) {
            arregloAutos[i] = new Coche(puente, 'S');
        }

        Thread[] autos = new Thread[cantAutos];
        for (int i = 0; i < cantAutos / 2; i++) {
            autos[i] = new Thread(arregloAutos[i], VERDE + "Coche" + i);
        }

        for (int i = cantAutos / 2; i < cantAutos; i++) {
            autos[i] = new Thread(arregloAutos[i], AMARILLO + "Coche" + i);
        }

        for (int i = 0; i < cantAutos; i++) {
            autos[i].start();
        }

    }

    public static final String VERDE = "\u001B[32m";
    public static final String AMARILLO = "\u001B[33m";
}
