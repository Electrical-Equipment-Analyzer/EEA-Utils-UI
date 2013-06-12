/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.data;

import java.awt.Color;

/**
 *
 * @author Leo
 */
public class Channel {

    private Color color;
    private double verticalDiv;
    private int verticalPostion;
    private double horizontalDiv;
    private int horizontalPostion;
    private Integer data[];

    public Integer[] getData() {
        return data;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setVerticalDiv(double verticalDiv) {
        this.verticalDiv = verticalDiv;
    }

    public void setVerticalPostion(int verticalPostion) {
        this.verticalPostion = verticalPostion;
    }

    public void setHorizontalDiv(double horizontalDiv) {
        this.horizontalDiv = horizontalDiv;
    }

    public void setHorizontalPostion(int horizontalPostion) {
        this.horizontalPostion = horizontalPostion;
    }

    public void setData(double[] data) {
        this.data = new Integer[500];
        for (int i = 0; i < this.data.length; i++) {
            try {
                this.data[i] = (int) (data[(int) ((i - (this.data.length - data.length / this.horizontalDiv) / 2) * this.horizontalDiv) + this.horizontalPostion] * -20 * this.verticalDiv) + 200 - this.verticalPostion;
            } catch (java.lang.ArrayIndexOutOfBoundsException ex) {
                this.data[i] = null;
            }
        }
    }
}
