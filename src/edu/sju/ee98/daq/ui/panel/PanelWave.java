/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.panel;

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

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.paintGrid(g);
        for (Channel channel : this.channel) {
            paintChannel(g, channel);
        }
    }

    private void paintGrid(Graphics g) {
        g.setColor(Color.BLACK);
        for (int h = 0; h < 8; h++) {
            g.drawLine(0, this.getHeight() / 8 * h, this.getWidth(), this.getHeight() / 8 * h);
        }
        for (int v = 0; v <= 10; v++) {
            g.drawLine(this.getWidth() / 10 * v, 0, this.getWidth() / 10 * v, this.getWidth());
        }
        for (int i = 0; i < 40; i++) {
            if (i % 5 == 0) {
                continue;
            }
            g.drawLine(this.getWidth() / 2 - 2, this.getHeight() / 40 * i, this.getWidth() / 2 + 2, this.getHeight() / 40 * i);
        }
        for (int i = 0; i < 50; i++) {
            if (i % 5 == 0) {
                continue;
            }
            g.drawLine(this.getWidth() / 50 * i, this.getHeight() / 2 - 2, this.getWidth() / 50 * i, this.getHeight() / 2 + 2);
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

    @Override
    public void setChannel(int index, Channel channel) {
        if (index < 0 || index > this.channel.length) {
            return;
        }
        this.channel[index] = channel;
    }
}
