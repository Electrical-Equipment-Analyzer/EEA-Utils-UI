/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.screen.grid;

import edu.sju.ee98.daq.ui.screen.ScreenGrid;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Leo
 */
public class SampleGrid extends JPanel implements ScreenGrid {

    private Axis vertical;
    private Axis horizontal;

    public SampleGrid() {
        super(null);
        this.setLocation(0, 0);
        this.setSize(1366, 50);
        this.horizontal = new Axis("horizontal".toUpperCase());
        this.vertical = new Axis("vertical".toUpperCase());
        this.vertical.setLocation(0, 0);
        this.horizontal.setLocation(500, 0);
        this.add(this.vertical);
        this.add(this.horizontal);
    }

    @Override
    public void paintGrid(Graphics g) {
        g.setColor(Color.BLACK);
        for (int h = 0; h <= getVerticalGraduation() / getHardGraduation(); h++) {
            int y = (this.getGridHeight() / (getVerticalGraduation() / getHardGraduation()) * h) + this.getGridY();
            g.drawLine(0 + this.getGridX(), y,
                    this.getGridWidth() + this.getGridX(), y);
        }
        for (int v = 0; v <= getHorizontalGraduation() / getHardGraduation(); v++) {
            int x = this.getGridWidth() / (getHorizontalGraduation() / getHardGraduation()) * v + this.getGridX();
            g.drawLine(x, 0 + this.getGridY(),
                    x, this.getGridHeight() + this.getGridY());
        }
        for (int i = 0; i < getVerticalGraduation(); i++) {
            if (i % 5 == 0) {
                continue;
            }
            g.drawLine(this.getGridWidth() / 2 - 2 + this.getGridX(), this.getGridHeight() / getVerticalGraduation() * i + this.getGridY(),
                    this.getGridWidth() / 2 + 2 + this.getGridX(), this.getGridHeight() / getVerticalGraduation() * i + this.getGridY());
        }
        for (int i = 0; i < getHorizontalGraduation(); i++) {
            if (i % 5 == 0) {
                continue;
            }
            g.drawLine(this.getGridWidth() / getHorizontalGraduation() * i + this.getGridX(), this.getGridHeight() / 2 - 2 + this.getGridY(),
                    this.getGridWidth() / getHorizontalGraduation() * i + this.getGridX(), this.getGridHeight() / 2 + 2 + this.getGridY());
        }
    }

    private int getGridX() {
        return 0;
    }

    private int getGridY() {
        return 60;
    }

    private int getGridHeight() {
        return 480;
    }

    private int getGridWidth() {
        return 600;
    }

    private int getVerticalGraduation() {
        return 40;
    }

    private int getHorizontalGraduation() {
        return 50;
    }

    private int getHardGraduation() {
        return 5;
    }

    @Override
    public Integer[] transferData(double[] data) {
        Integer[] transfer = new Integer[this.getGridWidth()];
        for (int i = 0; i < transfer.length; i++) {
            try {
                transfer[i] = (int) (data[(int) ((i - (transfer.length - data.length / this.horizontal.getDiv()) / 2) * this.horizontal.getDiv()) + this.horizontal.getPostion()] * -1 * (getGridHeight() / getVerticalGraduation()) / this.vertical.getDiv()) + this.getGridY() + (this.getGridHeight() / 2) - this.vertical.getPostion();
            } catch (java.lang.ArrayIndexOutOfBoundsException ex) {
                transfer[i] = null;
            }
        }
        return transfer;
    }
}
