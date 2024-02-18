/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pcd7;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

/**
 *
 * @author alepd
 */
public class CanvasComedero extends Canvas {

    private ArrayList<String> colaEsperaGatos;
    private ArrayList<String> colaEsperaPerros;
    private ArrayList<String> Comedero;

    public CanvasComedero(int ancho, int alto) {
        this.setSize(ancho, alto);
        colaEsperaGatos = new ArrayList<>();
        colaEsperaPerros = new ArrayList<>();
        Comedero = new ArrayList<>();
    }

    public void insertaComedero(String animal) {
        Comedero.add(animal);
        repaint();
    }

    public void quitaComedero(String animal) {
        Comedero.remove(Comedero.indexOf(animal));
        repaint();
    }

    public void insertaColaEsperaGato(String gato) {
        colaEsperaGatos.add(gato);
        repaint();
    }

    public void quitaColaEsperaGato() {
        colaEsperaGatos.remove(0);
        repaint();
    }

    public void insertaColaEsperaPerro(String perro) {
        colaEsperaPerros.add(perro);
        repaint();
    }

    public void quitaColaEsperaPerro() {
        colaEsperaPerros.remove(0);
        repaint();
    }

    @Override
    public void paint(Graphics g) {

        Font letraChica = new Font("Arial", Font.PLAIN, 30);
        Font letraGrande = new Font("Banschrift", Font.BOLD, 50);
        Image img = createImage(getWidth(), getHeight());
        Graphics og = img.getGraphics();

        og.setFont(letraChica);
        og.setColor(Color.white);

        int x = 300;

        og.drawString("Gatos en espera: ", 50, 50);

        for (int i = 0; i < colaEsperaGatos.size(); i++) {
            og.drawString("" + colaEsperaGatos.get(i), x, 50);
            x += 75;

        }

        x = 300;
        og.drawString("Perros en espera: ", 50, 100);
        for (int i = 0; i < colaEsperaPerros.size(); i++) {
            og.drawString("" + colaEsperaPerros.get(i), x, 100);
            x += 75;
        }

        x = 100;
        for (int i = 0; i < 4; i++) {
            og.drawRect(x, 150, 150, 150);
            x += 150;
        }

        og.setFont(letraGrande);
        x = 130;
        for (int i = 0; i < Comedero.size(); i++) {
            og.drawString("" + Comedero.get(i), x, 240);
            x = x + 150;
        }

        g.drawImage(img, 0, 0, null);

    }
}
