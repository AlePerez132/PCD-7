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
public class Gato implements Runnable {

    int id;
    Comedero comedero;
    Random random;

    public Gato(Comedero comedero) {
        this.comedero = comedero;
    }

    @Override
    public void run() {
        id = (int) Thread.currentThread().getId();
        random = new Random(System.currentTimeMillis());
        System.out.println("El gato " + id + " quiere comer");
        try {
            comedero.entraGato(id);
        } catch (InterruptedException ex) {
            Logger.getLogger(Gato.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("El gato " + id + " come");
        try {
            sleep((random.nextInt(3) + 4) * 1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Gato.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("El gato " + id + " sale del comedero");
        comedero.saleGato(id);
    }

}
