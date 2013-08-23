/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.screen.grid;

import java.io.Serializable;
import javax.swing.AbstractSpinnerModel;

/**
 *
 * @author Leo
 */
public class SpinnerMetricModel extends AbstractSpinnerModel implements Serializable {

    private MetricNumber value;
    private double minimum, maximum;

    public SpinnerMetricModel(MetricNumber value, double minimum, double maximum) {
        this.value = value;
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public SpinnerMetricModel(double value, double minimum, double maximum) {
        this(new MetricNumber(value), minimum, maximum);
    }

    public double getDouble() {
        return value.doubleValue();
    }

    @Override
    public Object getValue() {
        return value.toString();
    }

    @Override
    public void setValue(Object value) {
        if ((value == null) || !(value instanceof MetricNumber)) {
            throw new IllegalArgumentException("illegal value");
        }
        if (!value.equals(this.value)) {
            this.value = (MetricNumber) value;
            fireStateChanged();
        }
    }

    private MetricNumber incrValue(boolean up) {

        int s = value.getFirst() % 10;
        double v = 0;
        switch (s) {
            case 1:
                v = (up ? value.doubleValue() * 2 : value.doubleValue() / 2);
                break;
            case 2:
                v = (up ? value.doubleValue() / 2 * 5 : value.doubleValue() / 2);
                break;
            case 5:
                v = (up ? value.doubleValue() * 2 : value.doubleValue() / 5 * 2);
                break;
            default:
                System.out.println(s);
        }

        if (v > maximum) {
            return null;
        }
        if (v < minimum) {
            return null;
        } else {
            return new MetricNumber(v);
        }
    }

    @Override
    public Object getNextValue() {
        return incrValue(true);
    }

    @Override
    public Object getPreviousValue() {
        return incrValue(false);
    }
}
