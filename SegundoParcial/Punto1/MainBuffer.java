/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RepettoFAI2548Parcial2.EJercicio1;

import RepettoFAI2548Parcial2.EJercicio1.Insertor;
import RepettoFAI2548Parcial2.EJercicio1.Extractor;
import RepettoFAI2548Parcial2.EJercicio1.Buffer;

/**
 *
 * @author repetto.francisco
 */
public class MainBuffer {

    public static void main(String[] args) {
        Buffer buffer = new Buffer();

        //Creo hilos Insertor
        int cantInsertores = 4;
        Insertor[] insertores = new Insertor[cantInsertores];
        for (int i = 0; i < cantInsertores; i++) {
            insertores[i] = new Insertor(buffer);
        }
        Thread[] hilosInsertores = new Thread[cantInsertores];
        for (int i = 0; i < cantInsertores; i++) {
            hilosInsertores[i] = new Thread(insertores[i], VERDE + "Insertor" + i);
        }

        ///Creo hilos Extractores
        int cantExtractores = 1;
        Extractor[] extractores = new Extractor[cantExtractores];
        for (int i = 0; i < cantExtractores; i++) {
            extractores[i] = new Extractor(buffer);
        }
        Thread[] hilosExtractores = new Thread[cantExtractores];
        for (int i = 0; i < cantExtractores; i++) {
            hilosExtractores[i] = new Thread(extractores[i], AMARILLO + "Extractor" + i);
        }

        //Ejecuto los hilos
        for (int i = 0; i < cantInsertores; i++) {
            hilosInsertores[i].start();
        }
        for (int i = 0; i < cantExtractores; i++) {
            hilosExtractores[i].start();
        }
    }

    public static final String VERDE = "\u001B[32m";
    public static final String AMARILLO = "\u001B[33m";

}
