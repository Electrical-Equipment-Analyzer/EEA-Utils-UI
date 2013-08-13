/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.fft;

import edu.sju.ee98.daq.ui.screen.ScreenPanel;
import edu.sju.ee98.daq.ui.screen.grid.SampleGrid;
import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author Leo
 */
public class FFTPanel extends JPanel{
    
    
    public FFTPanel() {
        this.setSize(1366, 655);
        this.setBackground(Color.red);
        this.setLayout(null);
        
        //tab pannel
        ScreenPanel source = new ScreenPanel();
        source.setLocation(0, 0);
        source.setGrid(new SampleGrid());
        this.add(source);
        
        
    }
    
}
