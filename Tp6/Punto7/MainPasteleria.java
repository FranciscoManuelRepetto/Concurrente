/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tp6.Punto7;

/**
 *
 * @author repetto.francisco
 */
public class MainPasteleria {

    public static final String VERDE = "\u001B[32m";
    public static final String AMARILLO = "\u001B[33m";
    public static final String PURPLE = "\u001B[35m";
    
    public static void main(String[] args) {
        
        int cantMaxPasteles = 10;
        int pesoMax = 5;
        System.out.println("--CANTIDAD MAXIMA DE PASTELES EN CINTA: "+cantMaxPasteles+
                "--PESO MAXIMO POR CAJA: "+pesoMax+"--");
        
        Mostrador mostrador = new Mostrador(cantMaxPasteles,pesoMax);
        
        //CREO HILOS HORNOS
        Horno hornos[] = new Horno[3];
        for (int i = 0; i < 3; i++) {
            hornos[i] = new Horno(i+1,mostrador);
        }
        Thread hilosHornos[] = new Thread[3];
        for (int i = 0; i < 3; i++) {
            hilosHornos[i] = new Thread(hornos[i],VERDE+"Horno"+(i+1));
        }
        
        //CREO HILOS EMPAQUETADOR
        Empaquetador[] empaquetadores = new Empaquetador[2];
        for (int i = 0; i < 2; i++) {
            empaquetadores[i] = new Empaquetador(mostrador);
        }
        Thread hilosEmpaquetadores[] = new Thread[2];
        for (int i = 0; i < 2; i++) {
            hilosEmpaquetadores[i] = new Thread(empaquetadores[i],AMARILLO+"Empaquetador"+(i+1));
        }
        
        //CREO HILO BRAZO
        Brazo brazo = new Brazo(mostrador);
        Thread hiloBrazo = new Thread(brazo,PURPLE+"Brazo");
        

        //INICIALIZO TODOS LOS HILOS
        for (int i = 0; i < 3; i++) {
            hilosHornos[i].start();
        }
        for (int i = 0; i < 2; i++) {
            hilosEmpaquetadores[i].start();
        }
        hiloBrazo.start();
        
    }
}
