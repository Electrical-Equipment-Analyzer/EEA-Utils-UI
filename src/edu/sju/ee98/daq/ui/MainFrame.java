/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui;

import edu.sju.ee98.daq.ui.action.EventExit;
import edu.sju.ee98.daq.ui.action.EventFileNew;
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
    public static final JFrame THIS = new MainFrame();
    
    private JMenuBar menuBar;

    private MainFrame() throws HeadlessException {
        super("SJU Data Acquisition");
        this.setVisible(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLayout(null);

        //Action==============
        EventExit eventExit = new EventExit();
        MainFrame.EVENT_POOL.put(eventExit.name(), eventExit);
        EventFileNew eventFileNew = new EventFileNew();
        MainFrame.EVENT_POOL.put(eventFileNew.name(), eventFileNew);
        
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
