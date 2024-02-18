/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pcd7;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alepd
 */
public class Perro extends Thread {

    int id;
    Comedero comedero;
    Random random;

    public Perro(Comedero comedero) {
        this.comedero = comedero;
    }

    @Override
    public void run() {
        id = (int) Thread.currentThread().getId();
        random = new Random(System.currentTimeMillis());
        System.out.println("El perro " + id + " quiere comer");
        try {
            comedero.entraPerro(id);
        } catch (InterruptedException ex) {
            Logger.getLogger(Perro.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("El perro " + id + " come");
        try {
            sleep((random.nextInt(3) + 4) * 1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Gato.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("El perro " + id + " sale del comedero");
        comedero.salePerro(id);
    }
}
