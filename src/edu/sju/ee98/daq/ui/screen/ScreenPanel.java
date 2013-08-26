/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.screen;

import edu.sju.ee98.daq.oscilloscope.data.Channel;
import edu.sju.ee98.daq.ui.WorkPanel;
import edu.sju.ee98.ni.daqmx.data.WaveData;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Leo
 */
public class ScreenPanel extends WorkPanel {

    private ScreenGrid grid;
    private WaveData wave;

    public ScreenPanel() {
        this.setSize(1366, 300);
        this.setBackground(Color.white);
        this.setLayout(null);
    }

    public void setGrid(ScreenGrid grid) {
        this.grid = grid;
        this.grid.addAxisControl(this);
//        JPanel control = grid.getControl();
//        if (control != null) {
//            this.add(control);
//        }
    }

    public void setWave(WaveData wave) {
        this.wave = wave;
    }

    public WaveData getWave() {
        return wave;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.grid != null) {
            this.grid.paintGrid(g);
        }
        if (this.wave != null) {
            paintData(g, this.grid.transferData(this.wave.getDoubleArray()));
        }
    }

    private void paintChannel(Graphics g, Channel channel) {
        if (channel == null) {
            return;
        }
        g.setColor(channel.getColor());
//        this.paintData(g, channel.getData());
    }

    private void paintData(Graphics g, Integer[] data) {
        g.setColor(Color.RED);
        for (int i = 1; i < data.length; i++) {
            try {
                g.drawLine(i - 1, data[i - 1], i, data[i]);
            } catch (java.lang.NullPointerException ex) {
            }
        }
    }
}
