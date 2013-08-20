/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.screen.grid;

import edu.sju.ee98.daq.ui.screen.ScreenGrid;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Leo
 */
public class SampleGrid implements ScreenGrid {

    private Axis horizontal;
    private Axis vertical;

    public SampleGrid(Axis horizontal, Axis vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public SampleGrid() {
        this.horizontal = new Axis(0, 1);
        this.vertical = new Axis(0, 1);
    }

    @Override
    public void paintGrid(Graphics g) {
        g.setColor(Color.BLACK);
        for (int h = 0; h < 8; h++) {
            g.drawLine(0, this.getHeight() / 8 * h, this.getWidth(), this.getHeight() / 8 * h);
        }
        for (int v = 0; v <= 10; v++) {
            g.drawLine(this.getWidth() / 10 * v, 0, this.getWidth() / 10 * v, this.getWidth());
        }
        for (int i = 0; i < 40; i++) {
            if (i % 5 == 0) {
                continue;
            }
            g.drawLine(this.getWidth() / 2 - 2, this.getHeight() / 40 * i, this.getWidth() / 2 + 2, this.getHeight() / 40 * i);
        }
        for (int i = 0; i < 50; i++) {
            if (i % 5 == 0) {
                continue;
            }
            g.drawLine(this.getWidth() / 50 * i, this.getHeight() / 2 - 2, this.getWidth() / 50 * i, this.getHeight() / 2 + 2);
        }
    }

    private int getHeight() {
        return 600;
    }

    private int getWidth() {
        return 600;
    }

    @Override
    public Integer[] transferData(double[] data) {
        Integer[] transfer = new Integer[this.getWidth()];
        for (int i = 0; i < transfer.length; i++) {
            try {
                transfer[i] = (int) (data[(int) ((i - (transfer.length - data.length / this.horizontal.getDiv()) / 2) * this.horizontal.getDiv()) + this.horizontal.getPostion()] * -10 / this.vertical.getDiv()) + (this.getHeight() / 2) - this.vertical.getPostion();
            } catch (java.lang.ArrayIndexOutOfBoundsException ex) {
                transfer[i] = null;
            }
        }
        return transfer;
    }
}
