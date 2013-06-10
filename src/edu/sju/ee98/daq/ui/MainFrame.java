/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui;

import java.awt.HeadlessException;
import javax.swing.JFrame;

/**
 *
 * @author Leo
 */
public class MainFrame extends JFrame {

    public MainFrame() throws HeadlessException {
        super("SJU Data Acquisition");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 600);
        setLayout(null);
        //
        PanelWave panelWave = new PanelWave();
        panelWave.setLocation(0, 25);
        this.add(panelWave);

        PanelControl panelControl = new PanelControl();
        panelControl.setLocation(600, 25);
        this.add(panelControl);

    }
}
