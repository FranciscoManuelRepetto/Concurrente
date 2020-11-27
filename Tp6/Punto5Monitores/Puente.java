/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tp6.Punto5ConMonitores;

/**
 *
 * @author repetto.francisco
 */
public class Puente {

    private int carril; //Cantidad que puede pasar en el puente a la vez
    private int permisoNorte; // Permiso de los autos del norte para pasar
    private int permisoSur;// Permiso de los autos del sur para para pasar
    private int cantCruzaron;//cantidad que pasaron desde una misma direccion consecutivamente
    private int esperandoNorte;//Cant de autos que estan esperando desde el norte para pasar
    private int esperandoSur;//Cant de autos que estan esperando desde el sur para pasar

    public Puente() {
        carril = 1;
        permisoNorte = 0;
        permisoSur = 0;
        cantCruzaron = 0;
        esperandoNorte = 0;
        esperandoSur = 0;
    }

    public synchronized boolean intentarCruzar() {
        boolean puedoCruzar = false;
        if (carril == 1 && cantCruzaron < 10) {
            cantCruzaron++;
            carril--;
            puedoCruzar = true;
        }
        return puedoCruzar;
    }

    public synchronized void terminarCruzarNorte() {
        if ((cantCruzaron == 10 && esperandoSur != 0) || esperandoNorte == 0) {
            cantCruzaron = 0;
            permisoSur++;
        } else {
            permisoNorte++;
        }
        carril++;
        notifyAll();
    }

    public synchronized void esperandoNorte() throws InterruptedException {
        esperandoNorte++;
        while (permisoNorte == 0) {
            wait();
        }
        permisoNorte--;
        esperandoNorte--;
    }

    public synchronized void terminarCruzarSur() {
        if ((cantCruzaron == 10 && esperandoNorte != 0) || esperandoSur == 0 ) {
            cantCruzaron = 0;
            permisoNorte++;
        } else {
            permisoSur++;
        }
        carril++;
        notifyAll();
    }

    public synchronized void esperandoSur() throws InterruptedException {
        esperandoSur++;
        while (permisoSur == 0) {
            wait();
        }
        permisoSur--;
        esperandoSur--;
    }

}
