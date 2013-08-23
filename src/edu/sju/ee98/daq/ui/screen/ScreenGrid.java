/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.screen;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Leo
 */
public interface ScreenGrid {

    public JPanel getControl();
    
    public void paintGrid(Graphics g);

    public Integer[] transferData(double[] data);
}
