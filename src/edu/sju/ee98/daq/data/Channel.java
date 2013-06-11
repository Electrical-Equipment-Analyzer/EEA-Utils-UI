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
    private int verticalDiv;
    private int verticalPostion;
    private int horizontalDiv;
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

    public void setVerticalDiv(int verticalDiv) {
        this.verticalDiv = verticalDiv;
    }

    public void setVerticalPostion(int verticalPostion) {
        this.verticalPostion = verticalPostion;
    }

    public void setHorizontalDiv(int horizontalDiv) {
        this.horizontalDiv = horizontalDiv;
    }

    public void setHorizontalPostion(int horizontalPostion) {
        this.horizontalPostion = horizontalPostion;
    }

    public void setData(double[] data) {
        this.data = new Integer[500];
        for (int i = 0; i < this.data.length; i++) {
            try {
                this.data[i] = (int) (data[i + 250 + this.horizontalPostion] * -1) + 200 - this.verticalPostion;
            } catch (java.lang.ArrayIndexOutOfBoundsException ex) {
                this.data[i] = null;
            }
        }
    }
}
