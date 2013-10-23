/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.wave;

import edu.sju.ee98.daq.core.data.ComplexWave;
import edu.sju.ee98.daq.ui.screen.ScreenGrid;
import edu.sju.ee98.daq.ui.screen.ScreenWave;
import java.awt.Color;
import java.awt.Graphics;
import org.apache.commons.math3.complex.Complex;

/**
 *
 * @author Leo
 */
public class SComplexWave extends ComplexWave implements ScreenWave {

    public SComplexWave(double rate, Complex[] data) {
        super(rate, data);
    }

    @Override
    public void paintWave(Graphics g, ScreenGrid grid) {
        g.setColor(Color.red);
        grid.paintWave(g, this.getRate(), this.getAbsoluteArray());
        g.setColor(Color.green);
        grid.paintWave(g, this.getRate(), this.getArgumentArray());
        
        g.setColor(Color.black);
        grid.paintWave(g, this.getRate(), this.getRealArray());
        g.setColor(Color.blue);
        grid.paintWave(g, this.getRate(), this.getImaginaryArray());
    }
}
