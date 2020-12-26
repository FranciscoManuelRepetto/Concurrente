/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recuperatorio;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author repetto.francisco
 */
public class AuxiliarFarmacia implements Runnable {

    private Farmacia farmacia;
    private int numRecetaActual;
    private String nombreFirma;
    private int importe;

    public AuxiliarFarmacia(Farmacia farmacia, String nombre) {
        this.farmacia = farmacia;
        this.numRecetaActual = 0;
        this.nombreFirma = nombre;
        this.importe = 0;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(4000);//Simula el tiempo que pasa entre cliente y cliente
                Ficha nueva = crearFicha();
                farmacia.ponerFichaGen(nueva);
            } catch (InterruptedException ex) {
                Logger.getLogger(AuxiliarFarmacia.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private Ficha crearFicha() {
        numRecetaActual++;
        importe = (int) ((Math.random() * (25 - 33 + 1) + 33) * 100);
        System.out.println(Thread.currentThread().getName() + ": crea receta num" + numRecetaActual + " con importe: " + importe);
        Ficha nuevaFicha = new Ficha(numRecetaActual, importe, nombreFirma, 'G');
        return nuevaFicha;
    }

}
