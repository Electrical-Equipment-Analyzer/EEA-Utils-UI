/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.screen;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Leo
 */
public class GridPanel extends JPanel {

    private int x;
    private int y;
    private int height;
    private int width;
    private int verticalGraduation;
    private int horizontalGraduation;
    private int hardGraduation;

    private ArrayList<Data> data = new ArrayList<Data>();

    public GridPanel(int x, int y, int height, int width, int verticalGraduation, int horizontalGraduation, int hardGraduation) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.verticalGraduation = verticalGraduation;
        this.horizontalGraduation = horizontalGraduation;
        this.hardGraduation = hardGraduation;
    }

    public void add(Data data) {
        this.data.add(data);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.paintGrid(g);
        for (int i = 0; i < data.size(); i++) {
            data.get(i).paintData(g);
        }
    }

    private void paintGrid(Graphics g) {
        g.setColor(Color.BLACK);
        for (int h = 0; h <= verticalGraduation / hardGraduation; h++) {
            int y = (this.height / (verticalGraduation / hardGraduation) * h) + this.y;
            g.drawLine(0 + this.x, y,
                    this.width + this.x, y);
        }
        for (int v = 0; v <= horizontalGraduation / hardGraduation; v++) {
            int x = this.width / (horizontalGraduation / hardGraduation) * v + this.x;
            g.drawLine(x, 0 + this.y,
                    x, this.height + this.y);
        }
        for (int i = 0; i < verticalGraduation; i++) {
            if (i % 5 == 0) {
                continue;
            }
            g.drawLine(this.width / 2 - 2 + this.x, this.height / verticalGraduation * i + this.y,
                    this.width / 2 + 2 + this.x, this.height / verticalGraduation * i + this.y);
        }
        for (int i = 0; i < horizontalGraduation; i++) {
            if (i % 5 == 0) {
                continue;
            }
            g.drawLine(this.width / horizontalGraduation * i + this.x, this.height / 2 - 2 + this.y,
                    this.width / horizontalGraduation * i + this.x, this.height / 2 + 2 + this.y);
        }
    }

    public Data createData(Color color, double rate, double[] data, Axis horizontal, Axis vertical) {
        Integer[] transfer = new Integer[this.width];
        for (int i = 0; i < transfer.length; i++) {
            try {
                double t = rate / (this.width / this.horizontalGraduation * this.hardGraduation / horizontal.getDiv());
                transfer[i] = (int) (data[(int) ((i - (transfer.length - data.length / t) / 2) * t) + horizontal.getPostion()] * -1 * (height / verticalGraduation * hardGraduation) / vertical.getDiv()) + this.y + (this.height / 2) - vertical.getPostion();
            } catch (java.lang.ArrayIndexOutOfBoundsException ex) {
                transfer[i] = null;
            }
        }
        return new Data(color, transfer);
    }

    public class Data {

        private Color color;
        private Integer[] data;

        private Data(Color color, Integer[] data) {
            this.color = color;
            this.data = data;
        }

        private void paintData(Graphics g) {
            g.setColor(color);
            for (int i = 1; i < data.length; i++) {
                try {
                    int line = i + x;
                    g.drawLine(line - 1, data[i - 1], line, data[i]);
                } catch (java.lang.NullPointerException ex) {
                }
            }
        }

    }

}
