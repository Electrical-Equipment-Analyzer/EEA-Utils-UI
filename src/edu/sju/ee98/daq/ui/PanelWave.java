/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui;

import edu.sju.ee98.daq.data.Channel;
import edu.sju.ee98.daq.data.Monitor;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Leo
 */
public class PanelWave extends JPanel implements Monitor {

    private Channel[] channel = new Channel[4];

    public PanelWave() {
        this.setSize(500, 400);
        this.setBackground(Color.WHITE);
    }
    int zero = 200;

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Channel channel : this.channel) {
            paintChannel(g, channel);
        }
    }

    private void paintGrid() {
    }

    private void paintChannel(Graphics g, Channel channel) {
        if (channel == null) {
            return;
        }
        g.setColor(channel.getColor());
        paintData(g, channel.getData());
    }

    private void paintData(Graphics g, Integer[] data) {
        for (int i = 1; i < data.length; i++) {
            try {
                g.drawLine(i - 1, data[i - 1], i, data[i]);
            } catch (java.lang.NullPointerException ex) {
            }
        }
    }

    @Override
    public void setChannel(int index, Channel channel) {
        if (index < 0 || index > this.channel.length) {
            return;
        }
        this.channel[index] = channel;
    }
}
