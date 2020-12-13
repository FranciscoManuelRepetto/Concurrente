/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repetto_FAI2548;

/**
 *
 * @author repetto.francisco
 */
public class Comedor {

    private int cantComederos;// Cantidad de comederos sanos
    private char turno; // Marca el turno de la especie que esta comiendo 
    private int cantComieron; // Cantidad de animales que comieron dentro del turno
    private int comiendoActualmente;// Marca la cantidad de animales que estan comiendo en el comedor 

    public Comedor(int unCantidadCome, char empiezaEspecie) {
        this.cantComederos = unCantidadCome;
        this.turno = empiezaEspecie;
        this.cantComieron = 0;
        this.comiendoActualmente = 0;
    }

    public synchronized boolean puedoAccederComer(char especie) {
        boolean pudoAcceder = false;
        if (turno == especie && cantComieron < cantComederos) {
            System.out.println(Thread.currentThread().getName() + ": accedio al comedor");
            pudoAcceder = true;
            cantComieron++;
            comiendoActualmente++;
        }
        return pudoAcceder;
    }

    public synchronized void terminoDeComer() {
        System.out.println(Thread.currentThread().getName() + ": se va del comedor");
        comiendoActualmente--;
        if (comiendoActualmente == 0) {
            //Si no hay mas animales de esta especie comiendo, cambia de turno
            cambiarTurno();
        }
    }

    private void cambiarTurno() {
        if (turno == 'p') {
            System.out.println("-------- CAMBIO TURNO A GATOS --------");
            turno = 'g';
        } else {
            System.out.println("-------- CAMBIO TURNO A PERROS --------");
            turno = 'p';
        }
        this.cantComieron = 0;
    }

}
