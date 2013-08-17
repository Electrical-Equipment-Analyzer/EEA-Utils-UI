/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui;

import edu.sju.ee98.daq.ui.fft.FFTPanel;
import edu.sju.ee98.daq.ui.menu.FFTBar;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

/**
 *
 * @author Leo
 */
public class MainFrame extends JFrame {
    
//     JFrame THIS = new MainFrame();
    private Manager managet;
    private JMenuBar menuBar;

    public MainFrame(Manager manager) throws HeadlessException {
        super("SJU Data Acquisition");
        this.managet = manager;
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setLayout(null);

        
        menuBar = new FFTBar();
        //MenuBar============
        this.setJMenuBar(menuBar);
        
//        Oscilloscope o = new Oscilloscope();
        FFTPanel o = new FFTPanel();
        o.setLocation(0, 0);
        this.add(o);

        this.repaint();
        
    }
}
