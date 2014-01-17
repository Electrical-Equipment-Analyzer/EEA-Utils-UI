/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee.eea.ui.screen;

import java.awt.Graphics;

/**
 *
 * @author Leo
 */
public interface ScreenLayout {

    public void addComponent(ScreenPanel screen);

    public void paintGrid(Graphics g);

    public void paintWave(Graphics g);

    public Object getData();
//    public void paintData(Graphics g, Integer[] data);

//    public Integer[] transferData(double rate, double[] data);
}
