/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui;

import edu.sju.ee98.daq.oscilloscope.ui.panel.Oscilloscope;
import edu.sju.ee98.daq.ui.fft.FFTPanel;
import java.awt.HeadlessException;
import javax.swing.JFrame;

/**
 *
 * @author Leo
 */
public class MainFrame extends JFrame {

    public MainFrame() throws HeadlessException {
        super("SJU Data Acquisition");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLayout(null);

//        Oscilloscope o = new Oscilloscope();
        FFTPanel o = new FFTPanel();
        o.setLocation(0, 25);
        this.add(o);

        this.repaint();
    }
}
