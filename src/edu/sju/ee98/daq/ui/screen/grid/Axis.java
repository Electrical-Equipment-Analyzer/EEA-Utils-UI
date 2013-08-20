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

//    @Override
//    public void postionPerformed(int postion) {
//        System.out.println(postion);
//        this.postion = postion;
//    }
    

//    @Override
    public void divPerformed(double  div) {
        this.div = div;
//        double step = 0;
//        step = Math.pow(10, (div / 3)) / 1000000000;
//        step = DIV[div % 3] * (step == 0 ? 1 : step);
//        this.div = 5 / step;
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
