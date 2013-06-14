/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSlider;

/**
 *
 * @author Leo
 */
public class PanelHorizontal extends JPanel {

    public PanelHorizontal() {
        super(null);
        this.setSize(250, 150);
        this.setBorder(BorderFactory.createTitledBorder("Horizontal"));
        //
        JSlider postion = new JSlider(-10, 10, 0);
        postion.setBounds(10, 20, 230, 60);
        postion.setBorder(BorderFactory.createTitledBorder("Postion"));
        postion.setMajorTickSpacing(5);
        postion.setMinorTickSpacing(1);
        postion.setPaintTicks(true);
        this.add(postion);
        //
        JSlider div = new JSlider(-10, 10, 0);
        div.setBounds(10, 80, 230, 60);
        div.setBorder(BorderFactory.createTitledBorder("Postion"));
        div.setMajorTickSpacing(5);
        div.setMinorTickSpacing(1);
        div.setPaintTicks(true);
        this.add(div);
        
    }
    
}
