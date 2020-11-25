/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tp6.Punto1;

/**
 *
 * @author repetto.francisco
 */
public class MainFumador {
    public static void main(String[] args) {
        SalaFumadores sala = new SalaFumadores();
        
        Agente unAgente = new Agente(sala);
        
        Fumador fumadores[] = new Fumador[3];
        int cantFumadores = fumadores.length;
        
        for (int i = 0; i < cantFumadores; i++) {
            fumadores[i] = new Fumador(i+1,sala);
        }
        
        Thread agente = new Thread(unAgente, "Agente");
        
        Thread unFumadores[] = new Thread[cantFumadores];
        for (int i = 0; i < 3; i++) {
            unFumadores[i] = new Thread(fumadores[i],"Fumadores"+(i+1));
        }
        
        agente.start();
        for (int i = 0; i < cantFumadores ; i++) {
            unFumadores[i].start();
        }
    }
}
