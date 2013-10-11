/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.wave;

import edu.sju.ee98.daq.ui.screen.ScreenGrid;
import edu.sju.ee98.daq.ui.screen.ScreenWave;
import edu.sju.ee98.ni.daqmx.config.NIAnalogConfig;
import edu.sju.ee98.ni.daqmx.data.NIAnalogWave;
import java.awt.Graphics;

/**
 *
 * @author Leo
 */
public class SAnalogWave extends NIAnalogWave implements ScreenWave {

    public SAnalogWave(NIAnalogConfig config) {
        super(config);
    }

    @Override
    public void paintWave(Graphics g, ScreenGrid grid) {
        grid.paintWave(g, this.getRate(), this.getData());
    }
    
}
