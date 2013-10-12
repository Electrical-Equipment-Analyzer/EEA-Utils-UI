/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.wave;

import edu.sju.ee98.daq.ui.screen.ScreenGrid;
import edu.sju.ee98.daq.ui.screen.ScreenWave;
import edu.sju.ee98.ni.daqmx.analog.AcqIntClk;
import edu.sju.ee98.ni.daqmx.config.NIAnalogConfig;
import edu.sju.ee98.ni.daqmx.config.NIClkTiming;
import edu.sju.ee98.ni.daqmx.config.NIVoltageChan;
import edu.sju.ee98.ni.daqmx.wave.NIAnalogWave;
import java.awt.Graphics;

/**
 *
 * @author Leo
 */
public class SAnalogWave extends AcqIntClk implements ScreenWave {

    public SAnalogWave(NIVoltageChan voltageChan, NIClkTiming clkTiming) {
        super(voltageChan, clkTiming);
    }

    @Override
    public void paintWave(Graphics g, ScreenGrid grid) {
        grid.paintWave(g, this.getRate(), this.getData());
    }
    
}
