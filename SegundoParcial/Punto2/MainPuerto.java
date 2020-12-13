/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RepettoFAI2548Parcial2.Ejercicio2;

/**
 *
 * @author repetto.francisco
 */
public class MainPuerto {

    public static void main(String[] args) {
        Puerto puerto = new Puerto();

        //CREO HILOS AUTOS
        int cantAutos = 20;
        Auto[] autos = new Auto[cantAutos];
        for (int i = 0; i < cantAutos; i++) {
            autos[i] = new Auto(puerto);
        }
        Thread[] hilosAuto = new Thread[cantAutos];
        for (int i = 0; i < cantAutos; i++) {
            hilosAuto[i] = new Thread(autos[i], VERDE + "Auto" + i);
        }

        //CREO HILO TRANSBORDADOR
        Transbordador trans = new Transbordador(puerto);
        Thread hiloTrans = new Thread(trans, AMARILLO + "Transbordador");

        //EJECUTO LOS HILOS
        for (int i = 0; i < cantAutos; i++) {
            hilosAuto[i].start();
        }
        hiloTrans.start();
    }

    public static final String VERDE = "\u001B[32m";
    public static final String AMARILLO = "\u001B[33m";
}
