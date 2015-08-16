/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.edu.sju.ee.eea.ui.swing;

import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Leo
 */
public class SpinnerMetricModel extends SpinnerNumberModel {

//    public static final MetricPrefixFormat FORMAT = new MetricPrefixFormat("0.###");

    public SpinnerMetricModel(double value, double minimum, double maximum) {
        super(value, minimum, maximum, 0);
    }

    private static int getFirst(double value) {
        if (value > 1) {
            while (value >= 10) {
                value /= 10;
            }
        } else {
            while (value < 1) {
                value *= 10;
            }
        }
        return (int) value;
    }

    private Number incrValue(boolean up) {
        double value = (Double) super.getValue();
        int s = getFirst(value) % 10;
        double newValue = 0;
        switch (s) {
            case 1:
                newValue = (up ? value * 2 : value / 2);
                break;
            case 2:
                newValue = (up ? value / 2 * 5 : value / 2);
                break;
            case 5:
                newValue = (up ? value * 2 : value / 5 * 2);
                break;
        }

        if ((super.getMaximum() != null) && (super.getMaximum().compareTo(newValue) < 0)) {
            return null;
        } else if ((super.getMinimum() != null) && (super.getMinimum().compareTo(newValue) > 0)) {
            return null;
        } else {
            return newValue;
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
