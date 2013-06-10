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
    private int data[];

    public int[] getData() {
        return data;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setData(int[] data) {
        this.data = data;
    }
}
