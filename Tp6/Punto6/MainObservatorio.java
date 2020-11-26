/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tp6.Punto6;

/**
 *
 * @author repetto.francisco
 */
public class MainObservatorio {
    public static void main(String[] args) {
        Observatorio observatorio = new Observatorio(2);
        
        //CREO HILOS VISITANTES
        int cantVisitantes = 20;
        int cantSillas = 4;
        Visitante[] visitantes = new Visitante[cantVisitantes];
        for (int i = 0; i < cantVisitantes - cantSillas; i++) {//Visitantes sin sillas de ruedas
            visitantes[i] = new Visitante(observatorio,false);
        }
        for (int i = cantVisitantes - cantSillas; i < cantVisitantes; i++) {//Visitantes con sillas de ruedas
            visitantes[i] = new Visitante(observatorio,true);
        }
        Thread[] hilosVisitantes = new Thread[cantVisitantes];
        for (int i = 0; i < cantVisitantes - cantSillas; i++) {
            hilosVisitantes[i] = new Thread(visitantes[i],VERDE+"Visitante"+i);
        }
        for (int i = cantVisitantes-cantSillas; i < cantVisitantes; i++) {
            hilosVisitantes[i] = new Thread(visitantes[i],AMARILLO+"VisitanteSilla"+i);
        }
        
        //CREO HILOS MANTENIMIENTO
        int cantMante = 3;
        Mantenimiento[] mante = new Mantenimiento[cantMante];
        for (int i = 0; i < cantMante; i++) {
            mante[i] = new Mantenimiento(observatorio); 
        }
        Thread[] hilosMante = new Thread[cantMante];
        for (int i = 0; i < cantMante; i++) {
            hilosMante[i] = new Thread(mante[i],ROJO+"Mantenimiento"+i);
        }
        
        //CREO HILOS INVESTIGADOR
        int cantInves = 3;
        Investigador[] inves = new Investigador[cantInves];
        for (int i = 0; i < cantInves; i++) {
            inves[i] = new Investigador(observatorio);
        }
        Thread[] hilosInves = new Thread[cantInves];
        for (int i = 0; i < cantInves; i++) {
            hilosInves[i] = new Thread(inves[i],CYAN+"Investigador"+i);
        }
        
        //Ejecuto todos los hilos
        for (int i = 0; i < cantVisitantes; i++) {
            hilosVisitantes[i].start();
        }
        for (int i = 0; i < cantMante; i++) {
            hilosMante[i].start();
        }
        for (int i = 0; i < cantInves; i++) {
            hilosInves[i].start();
        }
    }

    public static final String ROJO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String AMARILLO = "\u001B[33m";
    public static final String CYAN = "\u001B[36m";
}
