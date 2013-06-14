/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Leo
 */
public class PanelPostionDiv extends JPanel {

    private PostionDivListener pdListener;

    public PanelPostionDiv(String title) {
        super(null);
        this.setSize(250, 150);
        this.setBorder(BorderFactory.createTitledBorder(title));
        //
        JSlider postion = new JSlider(-500, 500, 0);
        postion.setBounds(10, 20, 230, 60);
        postion.setBorder(BorderFactory.createTitledBorder("Postion"));
        postion.setMajorTickSpacing(100);
        postion.setMinorTickSpacing(20);
        postion.setPaintTicks(true);
        postion.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (source.getValueIsAdjusting() && pdListener != null) {
                    pdListener.postionPerformed(source.getValue());
                }
            }
        });
        this.add(postion);
        //
        JSlider div = new JSlider(0, 33, 30);
        div.setBounds(10, 80, 230, 60);
        div.setBorder(BorderFactory.createTitledBorder("Div"));
        div.setMajorTickSpacing(6);
        div.setMinorTickSpacing(3);
        div.setPaintTicks(true);
        div.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (pdListener != null) {
                    pdListener.divPerformed(((JSlider) e.getSource()).getValue());
                }
            }
        });
        this.add(div);
    }

    public void setPDListener(PostionDivListener pdListener) {
        this.pdListener = pdListener;
    }
}
