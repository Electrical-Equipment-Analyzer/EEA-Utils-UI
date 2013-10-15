/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.wave;

import edu.sju.ee98.daq.ui.screen.ScreenGrid;
import edu.sju.ee98.daq.ui.screen.ScreenWave;
import edu.sju.ee98.ni.daqmx.data.NIWaveData;
import java.awt.Graphics;

/**
 *
 * @author Leo
 */
public class SAnalogWave implements ScreenWave {

    private double rate;
    private double[] data;

    public SAnalogWave(NIWaveData wave) {
        this.rate = wave.getRate();
        this.data = (double[]) wave.getData();
    }

    @Override
    public void paintWave(Graphics g, ScreenGrid grid) {
        grid.paintWave(g, rate, data);
    }

    @Override
    public Object getData() {
        return this.data;

    }

    @Override
    public double getRate() {
        return this.rate;
    }
}
