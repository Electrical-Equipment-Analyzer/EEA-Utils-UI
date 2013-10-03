/*
 * Copyright (c) 2013, St. John's University and/or its affiliates. All rights reserved.
 * Department of Electrical Engineering.
 */
package edu.sju.ee98.daq.ui.swing.pane;

import edu.sju.ee98.daq.ui.swing.DAQOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

/**
 *
 * @author 102m05008
 */
public class NewFilePane extends DAQOptionPane implements ActionListener {

    private JLabel label = new JLabel("File Type");
    private JComboBox file = new JComboBox(
            new String[]{AnalogConfigPane.NAME, FrequencyResponsePane.NAME});
    private JButton finishButton;

    public NewFilePane() {
        this.setSize(600, 450);
        initComponents();
    }

    private void testValue() {
    }

    private void initComponents() {
        testValue();
        this.label.setLocation(50, 50);
        this.label.setSize(100, 25);
        this.add(this.label);

        this.file.setLocation(150, 50);
        this.file.setSize(200, 25);
        this.add(this.file);

        finishButton = new JButton("Next");
        finishButton.setBounds(350, 350, 100, 30);
        finishButton.addActionListener(this);
        this.add(finishButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        value = this.file.getSelectedItem().toString();
        getDialog().dispose();
    }
}
