/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.swing;

import java.awt.Color;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 *
 * @author Leo
 */
public class SLabelInput extends JComponent {

    private JLabel label;
    private JTextField textField;

    public SLabelInput(String text) {
//        this.setLayout(new FlowLayout());
//        this.setBackground(Color.red);
        this.setSize(500, 30);
        this.label = new JLabel(text);
        this.textField = new JTextField(10);
//                this.label.setSize(100, 25);
//                this.textField.setSize(100, 25);
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        this.add(this.label);
//        this.updateUI();
        this.add(this.textField);
        layout.putConstraint(SpringLayout.WEST, label, 5, SpringLayout.WEST, this);
//        layout.putConstraint(SpringLayout.NORTH, label, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, textField, 150, SpringLayout.WEST, this);
//        layout.putConstraint(SpringLayout.NORTH, textField, 5, SpringLayout.NORTH, this);
    }

    public void setText(String text) {
        this.textField.setText(text);
    }

    public String getText() {
        return this.textField.getText();
    }
}
