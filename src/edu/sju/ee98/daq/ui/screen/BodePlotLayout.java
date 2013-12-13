/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.screen;

import edu.sju.ee98.daq.core.data.Wave;
import edu.sju.ee98.daq.core.frequency.response.FrequencyResponseFile;
import edu.sju.ee98.daq.ui.swing.pane.FrequencyResponsePane;
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

    private JPanel config;
    private JPanel data;

    private Wave wave;

    public BodePlotLayout(FrequencyResponseFile fr) {
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
        this.grid = new GridPanel(50, 50, 480, 600, 40, 50, 5);
        this.wave = wave;
        this.config();
    }

    private void config() {
        this.config = new FrequencyResponsePane();
//        this.config.setBackground(Color.red);
//        this.config.setBorder(BorderFactory.createTitledBorder("Configure"));

        this.data = new JPanel();
        this.data.setBackground(Color.BLUE);
    }

    @Override
    public void addComponent(ScreenPanel screen) {
        GroupLayout layout = new GroupLayout(screen);
        screen.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                .addGap(0, 5, 5)
                .addGroup(layout.createParallelGroup()
                        .addGap(0, 5, 5)
                        .addComponent(grid, 700, 700, 700)
                        .addGap(0, 5, 5))
                .addGap(0, 5, 5)
                .addGroup(layout.createParallelGroup()
                        .addGap(0, 5, 5)
                        .addComponent(config)
                        .addComponent(data)
                        .addGap(0, 5, 5))
                .addGap(0, 5, 5)
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                .addGap(0, 5, 5)
                .addGroup(layout.createParallelGroup()
                        .addComponent(grid, 700, 700, 700)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(config, 200, 350, 400)
                                .addComponent(data, 200, 350, 400)))
                .addGap(0, 5, 5)
        );
    }

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
