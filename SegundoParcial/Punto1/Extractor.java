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
public class Extractor implements Runnable {

    private Buffer buffer;

    public Extractor(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        while (true) {
            try {
                if (buffer.colaExtraccionVacia()) {
                    //Verifica que la cola que quiere extraer no este vacia
                    buffer.esperarOscilacion();//Si esta vacia, espera a que oscilen las colas
                }
                String dato;
                dato = buffer.extraerDato();//Extrae un dato de la cola
                System.out.println(Thread.currentThread().getName() + ": extrajo " + dato);
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Extractor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
