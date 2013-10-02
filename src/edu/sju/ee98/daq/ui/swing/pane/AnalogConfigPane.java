/*
 * Copyright (c) 2013, St. John's University and/or its affiliates. All rights reserved.
 * Department of Electrical Engineering.
 */
package edu.sju.ee98.daq.ui.swing.pane;

import edu.sju.ee98.daq.core.config.AnalogInputConfig;
import edu.sju.ee98.daq.ui.swing.DAQLabelInput;
import edu.sju.ee98.daq.ui.swing.DAQOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author 102m05008
 */
public class AnalogConfigPane extends DAQOptionPane implements ActionListener {

    private DAQLabelInput physicalChannel = new DAQLabelInput("Physical Channel");
    private DAQLabelInput minVoltage = new DAQLabelInput("Min Voltage");
    private DAQLabelInput maxVoltage = new DAQLabelInput("Max Voltage");
    private DAQLabelInput rate = new DAQLabelInput("Rate");
    private DAQLabelInput length = new DAQLabelInput("Length");
    private JButton finishButton;

    public AnalogConfigPane() {
        this.setSize(600, 450);
        initComponents();
    }

    private void testValue() {
        physicalChannel.setText("Dev1/ai0");
        minVoltage.setText("-20");
        maxVoltage.setText("20");
        rate.setText("10000");
        length.setText("1024");
    }

    private void initComponents() {
        testValue();
        physicalChannel.setLocation(50, 50);
//        physicalChannel.setSize(500, 30);
        this.add(physicalChannel);
        minVoltage.setLocation(50, 100);
        this.add(minVoltage);
        maxVoltage.setLocation(50, 150);
        this.add(maxVoltage);
        rate.setLocation(50, 200);
        this.add(rate);
        length.setLocation(50, 250);
        this.add(length);

        finishButton = new JButton("Finish");
        finishButton.setBounds(100, 300, 100, 30);
        finishButton.addActionListener(this);
        this.add(finishButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            value = new AnalogInputConfig(
                    physicalChannel.getText(),
                    Double.parseDouble(minVoltage.getText()), Double.parseDouble(maxVoltage.getText()),
                    Double.parseDouble(rate.getText()), Long.parseLong(length.getText()));
        } catch (java.lang.NumberFormatException ex) {
            JOptionPane.showMessageDialog(AnalogConfigPane.this.getRootPane(), "Please input a Integer!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        getDialog().dispose();
    }
}
