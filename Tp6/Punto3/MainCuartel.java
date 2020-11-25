/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tp6.Punto3;

/**
 *
 * @author repetto.francisco
 */
public class MainCuartel {

    public static void main(String[] args) {
        Cuartel cuartel = new Cuartel();

        int cantSoldados = 10;
        Soldado[] arregloSol = new Soldado[cantSoldados];

        for (int i = 0; i < cantSoldados / 2; i++) {
            arregloSol[i] = new Soldado(cuartel, 'a', true);
        }

        for (int i = cantSoldados / 2; i < cantSoldados; i++) {
            arregloSol[i] = new Soldado(cuartel, 'g', false);
        }

        Thread[] soldados = new Thread[cantSoldados];
        for (int i = 0; i < cantSoldados/2; i++) {
            soldados[i] = new Thread(arregloSol[i],VERDE+"Soldado"+i);
        }
        
        for (int i = cantSoldados/2; i < cantSoldados ; i++) {
            soldados[i] = new Thread(arregloSol[i],AMARILLO+"Soldado"+i);
        }
        
        for (int i = 0; i < cantSoldados; i++) {
            soldados[i].start();
        }

    }

    public static final String VERDE = "\u001B[32m";
    public static final String AMARILLO = "\u001B[33m";
}
