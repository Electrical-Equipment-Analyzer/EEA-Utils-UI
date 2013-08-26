/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.screen.grid;

import edu.sju.ee98.daq.ui.Manager;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Leo
 */
public class Axis extends JPanel implements ChangeListener {

    private JSpinner postion;
    private JSpinner div;

    public Axis() {
        super(null);
        this.setBackground(Color.red);
        this.setSize(500, 25);
        this.postion = new JSpinner(new SpinnerNumberModel(0, -1000, 1000, 1));
        this.postion.setBounds(50, 0, 100, 25);
        this.add(this.postion);
        this.div = new JSpinner(new SpinnerMetricModel(0.001, 0.000000000001, 1000000));
        this.div.setBounds(200, 0, 100, 25);
        this.add(this.div);
    }

    public Axis(int postion, double div) {
//        this.postion = postion;
//        this.div = div;
    }
//    public void setPostion(int postion) {
//        this.postion = postion;
//    }
//    public void setDiv(double div) {
//        this.div = div;
//    }

    public int getPostion() {
        return (int) postion.getValue();
    }

    public double getDiv() {
        return ((SpinnerMetricModel) div.getModel()).getDouble();
    }

    @Override
    public String toString() {
        return "PostionDiv{" + "postion=" + postion + ", div=" + div + '}';
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        Manager.MANAGER.getMainFrame().work.getSelectedComponent().repaint();
    }
}
