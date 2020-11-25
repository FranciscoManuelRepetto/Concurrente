/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tp6.Punto3;

import java.util.concurrent.Semaphore;

/**
 *
 * @author repetto.francisco
 */
public class Cuartel {

    private Semaphore semCapacidad;
    private Semaphore semMostradoresAlmu;
    private Semaphore semAbridores;
    private Semaphore semMostradorPos;

    public Cuartel() {
        semCapacidad = new Semaphore(100);
        semMostradoresAlmu = new Semaphore(5);
        semAbridores = new Semaphore(10);
        semMostradorPos = new Semaphore(3);
    }

    public void entrarCuartel() throws InterruptedException {//Pide permiso para entrar al cuartel
        semCapacidad.acquire();
    }

    public void pedirAlmuerzo() throws InterruptedException {//Espera a que haya un mostrador libre
        semMostradoresAlmu.acquire();
    }

    public void pedirAbridor() throws InterruptedException {
        semAbridores.acquire();
    }

    public void devolverAbridor() {
        semAbridores.release();
    }

    public void terminarPedirAlmuerzo() throws InterruptedException {
        semMostradoresAlmu.release();
    }

    public void salirCuartel() throws InterruptedException {
        semCapacidad.release();
    }

    public void pedirPostre() throws InterruptedException {
        semMostradorPos.acquire();
    }

    public void terminarPedirPostre() {
        semMostradorPos.release();
    }

}
