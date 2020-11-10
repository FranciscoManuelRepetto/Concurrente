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
public class Jefe extends Personal {
    private int numEmpleados;

    public Jefe(int numEmpleados, Saludo s, String n) {
        super(s, n);
        this.numEmpleados = numEmpleados;
    }
    
    public void run(){
            try {
                System.out.println("(Esperando...)");
                saludo.esperarEmpleados(numEmpleados);
                saludo.saludoJefe();
            } catch (InterruptedException ex) {
                Logger.getLogger(Personal.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    
    
    

}
