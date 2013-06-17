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
    private PostionDiv vertical = new PostionDiv(0, 5);
    private PostionDiv horizontal = new PostionDiv(0, 2);
//    private double verticalDiv;
//    private int verticalPostion;
//    private double horizontalDiv;
//    private int horizontalPostion;
    private double data[];

    public void setData(double[] data) {
        this.data = data;
    }

    public Integer[] getData() {
        return this.transferData(data);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public PostionDiv getVertical() {
        return vertical;
    }

    public PostionDiv getHorizontal() {
        return horizontal;
    }

//    public void setVerticalDiv(double verticalDiv) {
//        this.verticalDiv = verticalDiv;
//    }
//
//    public void setVerticalPostion(int verticalPostion) {
//        this.verticalPostion = verticalPostion;
//    }
//
//    public void setHorizontalDiv(double horizontalDiv) {
//        this.horizontalDiv = horizontalDiv;
//    }
//
//    public void setHorizontalPostion(int horizontalPostion) {
//        this.horizontalPostion = horizontalPostion;
//    }
    public Integer[] transferData(double[] data) {
        System.out.println(this.vertical);
        System.out.println(this.horizontal);
        Integer[] transfer = new Integer[500];
        for (int i = 0; i < transfer.length; i++) {
            try {
                transfer[i] = (int) (data[(int) ((i - (transfer.length - data.length / this.horizontal.getDiv()) / 2) * this.horizontal.getDiv()) + this.horizontal.getPostion()] * -10 / this.vertical.getDiv()) + 200 - this.vertical.getPostion();
            } catch (java.lang.ArrayIndexOutOfBoundsException ex) {
                transfer[i] = null;
            }
        }
        return transfer;
    }
}
