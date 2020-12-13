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
public class Control implements Runnable {

    private GestorCruce gestorCruce;

    public Control(GestorCruce unGestorCruce) {
        this.gestorCruce = unGestorCruce;
    }

    public void run() {
        gestorCruce.cambiaSemaforos();
    }

}
