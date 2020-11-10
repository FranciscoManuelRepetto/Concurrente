/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tp6.EjerciciosTeoria;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author repetto.francisco
 */
public class TestSaludo {

    public static void main(String argv[]) {
        try {
            String[] nombresEmpleados = {"Pablo", "Luis", "Andrea",
                "Pedro", "Paula","Tincho","Sele","ElManu","Fran","Selena"};
            Saludo hola = new Saludo();
            int cantEmpleados = 10;
            Thread[] elPersonal = new Thread[cantEmpleados];
            Thread jefe = new Thread(new Jefe(cantEmpleados,hola, "JEFE"));
            for (int i = 0; i < elPersonal.length; i++) {
                elPersonal[i] = new Thread(new Empleado(hola,
                        nombresEmpleados[i]));
            }
            jefe.start();
            for (int i = 0; i < elPersonal.length; i++) {
                elPersonal[i].start();
            }

            /*//SOLUCION 1
            jefe.join();
            for (int i = 0; i < 6; i++) {
                elPersonal[i].join();
            }
             System.out.println("LISTO, ahora que todos han saludado - a trabajar");
             */
            
            //Solucion 2
            hola.esperarMain(elPersonal.length - 1);
            System.out.println("LISTO, ahora que todos han saludado - a trabajar");

        } catch (InterruptedException ex) {
            Logger.getLogger(TestSaludo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
