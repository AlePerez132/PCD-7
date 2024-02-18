/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pcd7;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author alepd
 */
public class Comedero {

    private final int N = 4;  // Definir el límite total de animales
    private int NPerros = 0;
    private int NGatos = 0;

    private Lock lock = new ReentrantLock();
    private Condition colaGato = lock.newCondition();
    private Condition colaPerro = lock.newCondition();
    private ArrayList<String> colaEsperaGatos = new ArrayList<>();
    private ArrayList<String> gatosComedero = new ArrayList<>();
    private ArrayList<String> colaEsperaPerros = new ArrayList<>();
    private ArrayList<String> perrosComedero = new ArrayList<>();

    private CanvasComedero canvas;

    public Comedero(CanvasComedero canvas) {
        this.canvas = canvas;
    }

    public void entraGato(int id) throws InterruptedException {
        String gato = "G" + id;
        lock.lock();
        try {
            while ((NGatos + NPerros >= N || NPerros == 3) || (NGatos == 2 && NPerros == 1)) {
                System.out.println("\t\tEl gato " + gato + " tiene que esperar");
                colaEsperaGatos.add(gato);
                canvas.insertaColaEsperaGato(gato);
                colaGato.await();
            }
            gatosComedero.add(gato);
            canvas.insertaComedero(gato);
            NGatos++;
        } finally {
            lock.unlock();
        }
    }

    public void saleGato(int id) {
        String gato = "G" + id;
        lock.lock();
        try {
            NGatos--;
            gatosComedero.remove(gatosComedero.indexOf(gato));
            canvas.quitaComedero(gato);
            if (!colaEsperaGatos.isEmpty()) {
                colaEsperaGatos.remove(0);
                canvas.quitaColaEsperaGato();
                colaGato.signal();
            } else if (!colaEsperaPerros.isEmpty()) {
                if (NGatos < 3 && !(NGatos == 1 && NPerros == 2)) {
                    colaEsperaPerros.remove(0);
                    canvas.quitaColaEsperaPerro();
                    colaPerro.signal();
                }
            } else {
                colaGato.signal();
                colaPerro.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void entraPerro(int id) throws InterruptedException {
        String perro = "P" + id;
        lock.lock();
        try {
            while ((NGatos + NPerros >= N || NGatos >= 3) || (NGatos == 1 && NPerros == 2)) {
                System.out.println("\t\tEl perro " + perro + " tiene que esperar");
                colaEsperaPerros.add(perro);
                canvas.insertaColaEsperaPerro(perro);
                colaPerro.await();
            }
            perrosComedero.add(perro);
            canvas.insertaComedero(perro);
            NPerros++;
        } finally {
            lock.unlock();
        }
    }

    public void salePerro(int id) {
        String perro = "P" + id;
        lock.lock();
        try {
            NPerros--;
            perrosComedero.remove(perrosComedero.indexOf(perro));
            canvas.quitaComedero(perro);
            if (!colaEsperaPerros.isEmpty()) {
                colaEsperaPerros.remove(0);
                canvas.quitaColaEsperaPerro();
                colaPerro.signal();
            } else if (!colaEsperaPerros.isEmpty()) {
                if (NPerros < 3 && !(NPerros == 1 && NGatos == 2)) {
                    colaEsperaGatos.remove(0);
                    canvas.quitaColaEsperaGato();
                    colaGato.signal();
                }
            } else {
                colaGato.signal();
                colaPerro.signal();
            }
        } finally {
            lock.unlock();
        }
    }
}
