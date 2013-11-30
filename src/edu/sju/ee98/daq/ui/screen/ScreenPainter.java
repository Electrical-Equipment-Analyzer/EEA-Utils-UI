/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.screen;

import java.awt.Graphics;

/**
 *
 * @author Leo
 */
public interface ScreenPainter {

    public void paintGrid(Graphics g);

    public void paintWave(Graphics g);
    
    public Object getData();
//    public void paintData(Graphics g, Integer[] data);

//    public Integer[] transferData(double rate, double[] data);
}
