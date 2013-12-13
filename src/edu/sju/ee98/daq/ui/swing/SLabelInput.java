/*
 * Copyright (c) 2013, St. John's University and/or its affiliates. All rights reserved.
 * Department of Electrical Engineering.
 */
package edu.sju.ee98.daq.ui.swing;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 *
 * @author 薛聿明
 */
public class SLabelInput extends JComponent {

    private JLabel label;
    private JTextField textField;

    public SLabelInput(String text) {
        this.setSize(500, 30);
        this.label = new JLabel(text);
        this.textField = new JTextField(10);

        this.add(this.label);
        this.add(this.textField);

        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        layout.putConstraint(SpringLayout.WEST, label, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.WEST, textField, 150, SpringLayout.WEST, this);
    }

    public void setText(String text) {
        this.textField.setText(text);
    }

    public String getText() {
        return this.textField.getText();
    }
}
