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
public class AuxiliarContable implements Runnable {

    private Farmacia farmacia;

    public AuxiliarContable(Farmacia farmacia) {
        this.farmacia = farmacia;
    }

    public void run() {
        while (true) {
            try {
                Ficha fichaActual = farmacia.sacarFichaAuxConta();
                int importe = fichaActual.getImporte();
                char fichero = fichaActual.getFicheroActual();
                System.out.println(Thread.currentThread().getName() + "tomo la receta del fichero: " + fichero
                        + " con importe: " + importe);
                farmacia.registrarImporteConta();
                if (fichero == 'G') {
                    System.out.println(Thread.currentThread().getName() + " puso la ficha en el fichero-Encargado");
                    fichaActual.setFicheroActual('E');
                    farmacia.ponerFichaEncar(fichaActual);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(AuxiliarContable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
