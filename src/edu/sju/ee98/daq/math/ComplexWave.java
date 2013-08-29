/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.math;

import edu.sju.ee98.ni.daqmx.data.Grid;
import edu.sju.ee98.ni.daqmx.data.WaveData;
import java.awt.Color;
import java.awt.Graphics;
import org.apache.commons.math3.complex.Complex;

/**
 *
 * @author Leo
 */
public class ComplexWave implements WaveData {

    private double rate;
    private Complex[] data;

    public ComplexWave(double rate, Complex[] data) {
        this.rate = rate;
        this.data = data;
    }

    @Override
    public Complex[] getData() {
        return data;
    }

    private double[] getRealArray() {
        double[] temp = new double[data.length];
        for (int i = 0; i < data.length; i++) {
            temp[i] = data[i].getReal();
        }
        return temp;
    }

    private double[] getImaginaryArray() {
        double[] temp = new double[data.length];
        for (int i = 0; i < data.length; i++) {
            temp[i] = data[i].getImaginary();
        }
        return temp;
    }

    private double[] getAbsoluteArray() {
        double[] temp = new double[data.length];
        for (int i = 0; i < data.length; i++) {
            temp[i] = data[i].abs();
        }
        return temp;
    }

    public void setData(Complex[] data) {
        this.data = data;
    }

    @Override
    public double getRate() {
        return this.rate;
    }

    @Override
    public void paintWave(Graphics g, Grid grid) {
        g.setColor(Color.red);
        grid.paintWave(g, rate, this.getAbsoluteArray());
        g.setColor(Color.green);
        grid.paintWave(g, rate, this.getRealArray());
        g.setColor(Color.blue);
        grid.paintWave(g, rate, this.getImaginaryArray());
    }
}
