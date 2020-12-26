/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recuperatorio;

/**
 *
 * @author repetto.francisco
 */
public class MainFarmacia {

    public static void main(String[] args) {
        Farmacia farmacia = new Farmacia();

        //Creo hilo encargado
        Encargado encargado = new Encargado(farmacia);
        Thread hiloEncargado = new Thread(encargado, ROJO + "Encargado");

        //Creo hilo AuxiliarContable
        AuxiliarContable auxContable = new AuxiliarContable(farmacia);
        Thread hiloAuxContable = new Thread(auxContable, AMARILLO + "Aux.Contable");

        //Creo hilo AuxliarFarmacia
        AuxiliarFarmacia auxFarmacia = new AuxiliarFarmacia(farmacia, "Luis");
        Thread hiloAuxFarmacia = new Thread(auxFarmacia, VERDE + "Aux.Farmacia");

        //Ejecuto los hilos
        hiloEncargado.start();
        hiloAuxContable.start();
        hiloAuxFarmacia.start();
    }

    public static final String ROJO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String AMARILLO = "\u001B[33m";
}
