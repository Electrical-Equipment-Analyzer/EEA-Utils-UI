/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.oscilloscope.ui.panel;

import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author Leo
 */
public class Oscilloscope extends JPanel {

    private PanelWave panelWave = new PanelWave();
    private PanelControl panelControl_0 = new PanelControl(0);

    public Oscilloscope() {
        this.setSize(1200, 600);
        this.setBackground(Color.red);
        this.setLayout(null);

        panelWave.setLocation(0, 25);
        this.add(panelWave);

        panelControl_0.setLocation(520, 25);
        panelControl_0.setMonitor(panelWave);
        this.add(panelControl_0);

        panelControl_0.test();
    }
}
