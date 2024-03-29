/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/AWTForms/Frame.java to edit this template
 */
package pcd7;

import java.awt.Color;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alepd
 */
public class Generador extends java.awt.Frame {

    /**
     * Creates new form Generador
     */
    public Generador() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Exit the Application
     */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        Generador gnr = new Generador();
        gnr.setSize(800, 400);
        gnr.setBackground(Color.black);
        gnr.setLocation(400, 50);

        CanvasComedero canvas = new CanvasComedero(800, 400);
        gnr.add(canvas);
        gnr.setVisible(true);

        Comedero comedero = new Comedero(canvas);
        Random random = new Random(System.currentTimeMillis());
        int numRandom;
        int numHilos = 20;
        ArrayList<Thread> listaHilos = new ArrayList<>();
        for (int i = 0; i < numHilos; i++) {
            numRandom = random.nextInt(2);
            try {
                sleep((numRandom + 1) * 1000);
                if (numRandom == 0) {
                    Perro hiloPerro = new Perro(comedero);
                    listaHilos.add(hiloPerro);
                    hiloPerro.start();
                } else {
                    Gato g = new Gato(comedero);
                    Thread hiloGato = new Thread(g);
                    listaHilos.add(hiloGato);
                    hiloGato.start();
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Generador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        for (int i = 0; i < numHilos; i++) {
            try {
                listaHilos.get(i).join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Generador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Generador.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
