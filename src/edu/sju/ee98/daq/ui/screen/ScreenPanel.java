/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.screen;

import edu.sju.ee98.daq.ui.WorkspacePanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.io.File;

/**
 *
 * @author Leo
 */
public class ScreenPanel extends WorkspacePanel {

    private ScreenLayout grid;
//    private ScreenWave wave;

    public ScreenPanel() {
        super(new File("new"));
//        this.setSize(1020, 300);
        this.setBackground(Color.white);
//        this.setLayout(null);
    }

    public void setGrid(ScreenLayout grid) {
        this.grid = grid;
        this.grid.addComponent(this);
    }

    public ScreenLayout getGrid() {
        return grid;
    }

//    public void setWave(ScreenWave wave) {
//        this.wave = wave;
//    }
//
//    public ScreenWave getWave() {
//        return wave;
//    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.grid != null) {
            this.grid.paintGrid(g);
            this.grid.paintWave(g);
        }
//        if (this.wave != null) {
//            g.setColor(Color.RED);
//            this.wave.paintWave(g, grid);
//            this.grid.paintData(g, this.grid.transferData(this.wave.getRate(), this.wave.getDoubleArray()));
//        }
    }
}
