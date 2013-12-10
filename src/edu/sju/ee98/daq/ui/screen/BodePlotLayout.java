/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.screen;

import edu.sju.ee98.daq.core.data.Wave;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.GroupLayout;
import javax.swing.JPanel;

/**
 *
 * @author Leo
 */
public class BodePlotLayout implements ScreenLayout {

    private Axis vertical;
    private Axis horizontal;
    private GridPanel grid;

    private Wave wave;

    public BodePlotLayout(Wave wave) {
//        super(null);
//        this.setBackground(Color.red);
//        this.setLocation(0, 0);
//        this.setSize(1020, 50);
        this.horizontal = new Axis("horizontal".toUpperCase());
        this.vertical = new Axis("vertical".toUpperCase());
//        this.vertical.setLocation(0, 0);
//        this.horizontal.setLocation(500, 0);
//        this.add(this.vertical);
//        this.add(this.horizontal);
        this.grid = new GridPanel(10, 10, 480, 600, 40, 50, 5);
        this.wave = wave;
    }

    @Override
    public void addComponent(ScreenPanel screen) {
        GroupLayout layout = new GroupLayout(screen);
        screen.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(grid, 620, 620, 620)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup()
                        .addComponent(vertical, 400, 400, 400)
                        .addComponent(horizontal, 400, 400, 400))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addGap(25, 25, 25)
                        .addComponent(grid, 500, 500, 500)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(vertical, 50, 50, 50)
                                .addComponent(horizontal, 50, 50, 50)))
        );
    }

//    private int getGridX = 10;
//
//    private int getGridY = 60;
//
//    private int getGridHeight = 480;
//
//    private int getGridWidth = 600;
//
//    private int getVerticalGraduation = 40;
//
//    private int getHorizontalGraduation = 50;
//
//    private int getHardGraduation = 5;
//    @Override
//    public void paintWave(Graphics g) {
//        if (this.wave == null) {
//            return;
//        }
//        g.setColor(Color.black);
//        this.paintData(g, this.transferData(wave.getRate(), wave.getReal()));
//        try {
//            g.setColor(Color.blue);
//            this.paintData(g, this.transferData(wave.getRate(), wave.getImaginary()));
//            g.setColor(Color.red);
//            this.paintData(g, this.transferData(wave.getRate(), wave.getAbsolute()));
//            g.setColor(Color.green);
//            this.paintData(g, this.transferData(wave.getRate(), wave.getArgument()));
//        } catch (NullPointerException ex) {
//
//        }
//    }
    public Object getData() {
        return this.wave;
    }

    @Override
    public void paintGrid(Graphics g) {
    }

    @Override
    public void paintWave(Graphics g) {
    }
}
