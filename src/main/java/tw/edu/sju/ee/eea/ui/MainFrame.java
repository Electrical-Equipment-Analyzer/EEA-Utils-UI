/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.edu.sju.ee.eea.ui;

import tw.edu.sju.ee.eea.ui.menu.SMenuBar;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import javax.swing.JFrame;

/**
 *
 * @author Leo
 */
public class MainFrame extends JFrame {
    
//     JFrame THIS = new MainFrame();
//    private Manager managet;
    public final SMenuBar menuBar;
    public final MainPanel work;

    public MainFrame(Manager manager) throws HeadlessException {
        super("SJU Data Acquisition");
//        this.managet = manager;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setSize(1020, 760);
//        this.setResizable(false);
//        this.setLayout(null);

        
        menuBar = new SMenuBar();
        //MenuBar============
        this.setJMenuBar(menuBar);
        
//        Oscilloscope o = new Oscilloscope();
        work = new MainPanel();
//        work.setLocation(0, 0);
        this.getContentPane().add(work, BorderLayout.CENTER);

//        this.repaint();
        this.setVisible(true);
        
    }
}
