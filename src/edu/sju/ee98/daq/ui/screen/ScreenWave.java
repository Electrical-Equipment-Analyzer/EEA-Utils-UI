/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.screen;

import edu.sju.ee98.ni.daqmx.data.NIWaveData;
import java.awt.Graphics;

/**
 *
 * @author Leo
 */
public interface ScreenWave extends NIWaveData {

    public void paintWave(Graphics g, ScreenGrid grid);
}
