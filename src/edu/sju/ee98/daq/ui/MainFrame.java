/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui;

import edu.sju.ee98.daq.data.Channel;
import java.awt.Color;
import java.awt.HeadlessException;
import javax.swing.JFrame;

/**
 *
 * @author Leo
 */
public class MainFrame extends JFrame {
    
    private PanelWave panelWave = new PanelWave();
    private PanelControl panelControl_0 = new PanelControl(0);
    
    public MainFrame() throws HeadlessException {
        super("SJU Data Acquisition");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 600);
        setLayout(null);
        
        panelWave.setLocation(0, 25);
        this.add(panelWave);
        
        panelControl_0.setLocation(520, 25);
        panelControl_0.setMonitor(panelWave);
        this.add(panelControl_0);
        
        panelControl_0.test();
        
        
    }
    
}
