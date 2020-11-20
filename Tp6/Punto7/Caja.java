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
public class Caja {

    private int peso;

    public Caja() {
        peso = 0;
    }

    public void agregarPastel(int pesoNuevo) {
        peso = peso + pesoNuevo;
    }

    public int getPeso() {
        return peso;
    }
}
