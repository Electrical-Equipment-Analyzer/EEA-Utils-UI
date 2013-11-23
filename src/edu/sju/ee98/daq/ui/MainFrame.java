/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui;

import edu.sju.ee98.daq.ui.menu.DAQMenuBar;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

/**
 *
 * @author Leo
 */
public class MainFrame extends JFrame {
    
//     JFrame THIS = new MainFrame();
//    private Manager managet;
    public final DAQMenuBar menuBar;
    public final MainPanel work;

    public MainFrame(Manager manager) throws HeadlessException {
        super("SJU Data Acquisition");
//        this.managet = manager;
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setSize(1366, 768);
//        this.setResizable(false);
        this.setLayout(null);

        
        menuBar = new DAQMenuBar();
        //MenuBar============
        this.setJMenuBar(menuBar);
        
//        Oscilloscope o = new Oscilloscope();
        work = new MainPanel();
        work.setLocation(0, 0);
        this.add(work);

        this.repaint();
        
    }
}
