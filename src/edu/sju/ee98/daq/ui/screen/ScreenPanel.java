/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.screen;

import edu.sju.ee98.daq.oscilloscope.data.Channel;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Leo
 */
public class ScreenPanel extends JPanel {

    private ScreenGrid grid;
    private Channel[] channel = new Channel[4];

    public ScreenPanel() {
        this.setSize(1366, 300);
        this.setBackground(Color.white);
        this.setLayout(null);
    }

    public void setGrid(ScreenGrid grid) {
        this.grid = grid;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.grid != null) {
            this.grid.paintGrid(g);
        }
        for (Channel channel : this.channel) {
            paintChannel(g, channel);
        }
    }

    private void paintChannel(Graphics g, Channel channel) {
        if (channel == null) {
            return;
        }
        g.setColor(channel.getColor());
        this.paintData(g, channel.getData());
    }

    private void paintData(Graphics g, Integer[] data) {
        for (int i = 1; i < data.length; i++) {
            try {
                g.drawLine(i - 1, data[i - 1], i, data[i]);
            } catch (java.lang.NullPointerException ex) {
            }
        }
    }
}
