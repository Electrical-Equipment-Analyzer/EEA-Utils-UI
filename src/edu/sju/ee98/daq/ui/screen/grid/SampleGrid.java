/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.screen.grid;

import edu.sju.ee98.daq.ui.screen.ScreenGrid;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Leo
 */
public class SampleGrid implements ScreenGrid {

    private JPanel control;
    private Axis horizontal;
    private Axis vertical;

    public SampleGrid() {
        this(new Axis(0, 1), new Axis(0, 1));
    }

    public SampleGrid(Axis horizontal, Axis vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
        initContorl();
    }
    private JSpinner hd;

    private void initContorl() {
        this.control = new JPanel(null);
        this.control.setBackground(Color.red);
        this.control.setBounds(0, 0, 500, 30);

        hd = new JSpinner(new SpinnerMetricModel(0.001, 0.000000000001, 1000000));
        hd.setBounds(50, 0, 100, 25);
        hd.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                vertical.setDiv(((SpinnerMetricModel) hd.getModel()).getDouble());
                
            }
        });
        this.control.add(hd);
    }

    @Override
    public JPanel getControl() {
        return control;
    }

    @Override
    public void paintGrid(Graphics g) {
        g.setColor(Color.BLACK);
        for (int h = 0; h < 9; h++) {
            g.drawLine(0 + this.getX(), this.getHeight() / 8 * h + this.getY(),
                    this.getWidth() + this.getX(), this.getHeight() / 8 * h + this.getY());
        }
        for (int v = 0; v <= 10; v++) {
            g.drawLine(this.getWidth() / 10 * v + this.getX(), 0 + this.getY(),
                    this.getWidth() / 10 * v + this.getX(), this.getHeight() + this.getY());
        }
        for (int i = 0; i < 40; i++) {
            if (i % 5 == 0) {
                continue;
            }
            g.drawLine(this.getWidth() / 2 - 2 + this.getX(), this.getHeight() / 40 * i + this.getY(),
                    this.getWidth() / 2 + 2 + this.getX(), this.getHeight() / 40 * i + this.getY());
        }
        for (int i = 0; i < 50; i++) {
            if (i % 5 == 0) {
                continue;
            }
            g.drawLine(this.getWidth() / 50 * i + this.getX(), this.getHeight() / 2 - 2 + this.getY(),
                    this.getWidth() / 50 * i + this.getX(), this.getHeight() / 2 + 2 + this.getY());
        }
    }

    private int getX() {
        return 0;
    }

    private int getY() {
        return 30;
    }

    private int getHeight() {
        return 500;
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
