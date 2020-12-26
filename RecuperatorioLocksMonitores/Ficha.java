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
public class Ficha {

    private int numReceta;
    private int importe;
    private String nombreCol;//Nombre del colegiado que firmo la receta
    private char ficheroActual;//Si es 'G' es general, si es 'C' es contable y si es 'E' es encargado

    public Ficha(int numReceta, int importe, String nombreCol, char ficheroActual) {
        this.numReceta = numReceta;
        this.importe = importe;
        this.nombreCol = nombreCol;
        this.ficheroActual = ficheroActual;
    }

    public void setFicheroActual(char ficheroActual) {
        this.ficheroActual = ficheroActual;
    }

    public int getImporte() {
        return importe;
    }

    public char getFicheroActual() {
        return ficheroActual;
    }

}
