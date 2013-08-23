/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.screen.grid;

/**
 *
 * @author Leo
 */
public class Axis {

    private int postion;
    private double div;

    public Axis(int postion, double div) {
        this.postion = postion;
        this.div = div;
    }

    public void setPostion(int postion) {
        this.postion = postion;
    }

    public void setDiv(double div) {
        this.div = div;
    }

    public int getPostion() {
        return postion;
    }

    public double getDiv() {
        return div;
    }

    @Override
    public String toString() {
        return "PostionDiv{" + "postion=" + postion + ", div=" + div + '}';
    }
}
