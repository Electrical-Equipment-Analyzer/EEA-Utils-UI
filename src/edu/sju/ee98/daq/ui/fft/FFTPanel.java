/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.fft;

import edu.sju.ee98.daq.ui.screen.ScreenPanel;
import edu.sju.ee98.daq.ui.screen.grid.SampleGrid;
import java.awt.Color;
import javax.swing.JTabbedPane;

/**
 *
 * @author Leo
 */
public class FFTPanel extends JTabbedPane {

    public FFTPanel() {
        this.setSize(1366, 655);

        tabSource();
        tabFastFourierTransform();
    }

    private void tabSource() {
        ScreenPanel screen = new ScreenPanel();
        screen.setLocation(0, 0);
        screen.setGrid(new SampleGrid());
        screen.setDropTarget(null);
        this.addTab("source", screen);
    }

    private void tabFastFourierTransform() {
        ScreenPanel screen = new ScreenPanel();
        screen.setLocation(0, 0);
//        screen.setGrid(new SampleGrid());
        this.addTab("FFT", screen);
    }
}
