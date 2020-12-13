/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RepettoFAI2548Parcial2.EJercicio1;

import RepettoFAI2548Parcial2.EJercicio1.Buffer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author repetto.francisco
 */
public class Insertor implements Runnable {

    private Buffer buffer;

    public Insertor(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        while (true) {
            try {
                //Verifica si la cola de insertar no esta vacia y la de extraccion si
                if (!buffer.colaInsertarVacia() && buffer.colaExtraccionVacia()) {
                    buffer.oscilarColas();// Como esta vacia la cola de extraccion y la de inserccion no, cambia de etiqueta
                }
                String dato;
                dato = buffer.insertarDato();
                System.out.println(Thread.currentThread().getName() + ": inserto " + dato);
                Thread.sleep(6000);//Se duerme mas que el hilo extractor asi no insertan tantos para que cambien mas rapido de cola
            } catch (InterruptedException ex) {
                Logger.getLogger(Insertor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
