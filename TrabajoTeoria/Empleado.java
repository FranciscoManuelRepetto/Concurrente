/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tp6.EjerciciosTeoria;

/**
 *
 * @author repetto.francisco
 */
public class Empleado extends Personal {

    public Empleado(Saludo s, String n) {
        super(s, n);
    }
  
    public void run() {
        System.out.println(nombre+" llego");
        saludo.avisarAJefe();
        saludo.esperarJefe(nombre);
        saludo.avisarMain();
    }
}
