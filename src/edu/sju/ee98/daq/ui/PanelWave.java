/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Leo
 */
public class PanelWave extends JPanel {

    public PanelWave() {
        this.setSize(500, 400);
        this.setBackground(Color.white);
    }
    int zero = 200;

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        int[] data = genData();
        for (int i = 1; i < data.length; i++) {
            g.drawLine(i - 1, data[i - 1], i, data[i]);
        }
    }

    public static int[] genData() {
        int[] data = new int[500];
        for (int x = 0; x < data.length; x++) {
            data[x] = (int) (Math.sin(x / 50.0) * -100) + 200;
        }
        return data;
    }
}
