/*
 * Copyright (c) 2013, St. John's University and/or its affiliates. All rights reserved.
 * Department of Electrical Engineering.
 */
package edu.sju.ee98.daq.ui.swing.pane;

import edu.sju.ee98.daq.core.config.AnalogConfig;
import edu.sju.ee98.daq.ui.Manager;
import edu.sju.ee98.daq.ui.screen.ScreenPanel;
import edu.sju.ee98.daq.ui.screen.SampleGrid;
import edu.sju.ee98.daq.ui.screen.ScreenWave;
import edu.sju.ee98.daq.ui.swing.DAQLabelInput;
import edu.sju.ee98.daq.ui.swing.DAQOptionPane;
import edu.sju.ee98.daq.ui.wave.SAnalogWave;
import edu.sju.ee98.ni.daqmx.LoadLibraryException;
import edu.sju.ee98.ni.daqmx.analog.AcqIntClk;
import edu.sju.ee98.ni.daqmx.analog.AnalogGenerator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author 102m05008
 */
public class AnalogConfigPane extends DAQOptionPane implements ActionListener {

    public static final String NAME = "Analog Input";
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
            value = new AnalogConfig(
                    physicalChannel.getText(),
                    Double.parseDouble(minVoltage.getText()), Double.parseDouble(maxVoltage.getText()),
                    Double.parseDouble(rate.getText()), Long.parseLong(length.getText()));
        } catch (java.lang.NumberFormatException ex) {
            JOptionPane.showMessageDialog(AnalogConfigPane.this.getRootPane(), "Please input a Integer!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        getDialog().dispose();
    }

    public static void create() {
        AnalogConfig config = DAQOptionPane.showAnalogConfigDialog(Manager.MANAGER.getMainFrame());
        System.out.println(config);
        if (config != null) {
            ScreenWave wave = null;
            try {
                AcqIntClk analog = new AcqIntClk(config, config);
                analog.read();
                wave = new SAnalogWave(analog);
            } catch (LoadLibraryException ex) {
                JOptionPane.showMessageDialog(Manager.MANAGER.getMainFrame(), ex.getMessage(), ex.getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
                int simulator = JOptionPane.showConfirmDialog(Manager.MANAGER.getMainFrame(), "Do you want to use the simulator?", "Qustion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (simulator == JOptionPane.YES_OPTION) {
                    wave = new SAnalogWave(new AnalogGenerator(1024, 1024, 1, 100));
                } else {
                    return;
                }
            }
            ScreenPanel screen = new ScreenPanel();
            screen.setLocation(0, 0);
            screen.setGrid(new SampleGrid());
            screen.setWave(wave);
            screen.setDropTarget(null);
            screen.repaint();
            Manager.MANAGER.getMainFrame().work.addTab(screen);
            Manager.MANAGER.getMainFrame().work.setSelectedComponent(screen);
        }
    }
}
