/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui;

import edu.sju.ee98.daq.ui.action.EventExit;
import edu.sju.ee98.daq.ui.action.EventPool;
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
    
    public static final EventPool EVENT_POOL = new EventPool();
    private JMenuBar menuBar = new FFTBar();

    public MainFrame() throws HeadlessException {
        super("SJU Data Acquisition");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLayout(null);

        //Action==============
        EventExit eventExit = new EventExit();
        MainFrame.EVENT_POOL.put(eventExit.name(), eventExit);
        
        //MenuBar============
        this.setJMenuBar(menuBar);
        
//        Oscilloscope o = new Oscilloscope();
        FFTPanel o = new FFTPanel();
        o.setLocation(0, 0);
        this.add(o);

        this.repaint();
        
    }
}
