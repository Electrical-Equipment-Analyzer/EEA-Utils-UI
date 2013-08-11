/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.screen.grid;

import edu.sju.ee98.daq.ui.screen.ScreenGrid;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Leo
 */
public class SampleGrid implements ScreenGrid {

    @Override
    public void paintGrid(Graphics g) {
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

    private int getHeight() {
        return 300;
    }

    private int getWidth() {
        return 300;
    }
}
