/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.screen;

import edu.sju.ee98.daq.core.data.Wave;
import edu.sju.ee98.daq.core.frequency.response.FrequencyResponseFile;
import edu.sju.ee98.daq.ui.swing.pane.FrequencyResponseConfigPane;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

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
        this.config = new FrequencyResponseConfigPane();
//        this.config.setBackground(Color.red);
//        this.config.setBorder(BorderFactory.createTitledBorder("Configure"));

        this.data = new JPanel();
        data.setBackground(Color.BLUE);

        Data abs = new Data("abs");
        Data arg = new Data("arg");
        Data hor = new Data("hor");
        abs.setBackground(Color.GREEN);
        arg.setBackground(Color.ORANGE);
        hor.setBackground(Color.YELLOW);

        GroupLayout layout = new GroupLayout(data);
        data.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(abs)
                .addComponent(arg)
                .addComponent(hor)
                .addContainerGap()
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup()
                        .addComponent(abs)
                        .addComponent(arg)
                        .addComponent(hor))
                .addContainerGap()
        );

    }

    public class Data extends JPanel {

        private JCheckBox enableCheckBox;
        private Color color;
        private JButton colorChooser;

        public Data(String title) {
            this.setBorder(BorderFactory.createTitledBorder(title));

            enableCheckBox = new JCheckBox("Enable");

            this.add(enableCheckBox);

            GroupLayout layout = new GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createSequentialGroup()
                    .addComponent(enableCheckBox)
                    .addGap(0, 0, Short.MAX_VALUE)
            );
            layout.setVerticalGroup(
                    layout.createSequentialGroup()
                    .addComponent(enableCheckBox)
                    .addGap(0, 0, Short.MAX_VALUE)
            );

        }

    }

    @Override
    public void addComponent(ScreenPanel screen) {
        GroupLayout layout = new GroupLayout(screen);
        screen.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                .addGap(5)
                .addGroup(layout.createParallelGroup()
                        .addGap(5)
                        .addComponent(grid, 800, 800, 800)
                        .addGap(5))
                .addGap(5)
                .addGroup(layout.createParallelGroup()
                        .addGap(5)
                        .addComponent(config, 300, 350, 400)
                        .addComponent(data, 300, 350, 400)
                        .addGap(5))
                .addGap(5)
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                .addGap(5)
                .addGroup(layout.createParallelGroup()
                        .addComponent(grid, 700, 700, 700)
                        .addGap(5)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(config)
                                .addComponent(data, 100, 150, 200)))
                .addGap(5)
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
