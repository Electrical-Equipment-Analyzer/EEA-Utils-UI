/*
 * Copyright (c) 2013, St. John's University and/or its affiliates. All rights reserved.
 * Department of Electrical Engineering.
 */
package edu.sju.ee98.daq.ui.swing.pane;

import edu.sju.ee98.daq.core.function.AnalogVoltage;
import edu.sju.ee98.daq.ui.Manager;
import edu.sju.ee98.daq.ui.screen.ScreenPanel;
import edu.sju.ee98.daq.ui.screen.SampleLayout;
import edu.sju.ee98.daq.ui.swing.SLabelInput;
import edu.sju.ee98.daq.ui.swing.SOptionDialog;
import edu.sju.ee98.daq.core.data.Wave;
import edu.sju.ee.ni.math.WaveGenerator;
import edu.sju.ee98.daq.ui.swing.SOptionPanel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author 薛聿明
 */
public class AnalogConfigPane extends SOptionPanel<AnalogVoltage> {

    public static final String NAME = "Analog Input";
    private SLabelInput physicalChannel = new SLabelInput("Physical Channel");
    private SLabelInput minVoltage = new SLabelInput("Min Voltage");
    private SLabelInput maxVoltage = new SLabelInput("Max Voltage");
    private SLabelInput rate = new SLabelInput("Rate");
    private SLabelInput length = new SLabelInput("Length");

    public AnalogConfigPane() {
        this.setLayout(null);
        this.setBackground(Color.ORANGE);
        initComponents();
    }

    @Override
    public String getText() {
        return "Finish";
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            value = new AnalogVoltage(
                    physicalChannel.getText(),
                    Double.parseDouble(minVoltage.getText()), Double.parseDouble(maxVoltage.getText()),
                    Double.parseDouble(rate.getText()), Integer.parseInt(length.getText()));
        } catch (java.lang.NumberFormatException ex) {
            JOptionPane.showMessageDialog(AnalogConfigPane.this.getRootPane(), "Please input a Integer!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    public static void create() {
        AnalogVoltage config = SOptionDialog.showAnalogConfigDialog(Manager.MANAGER.getMainFrame());
        System.out.println(config);
        if (config != null) {
            Wave wave = null;
            try {
                wave = config.process();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(Manager.MANAGER.getMainFrame(), ex.getMessage(), ex.getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
                int simulator = JOptionPane.showConfirmDialog(Manager.MANAGER.getMainFrame(), "Do you want to use the simulator?", "Qustion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (simulator == JOptionPane.YES_OPTION) {
                    WaveGenerator analogGenerator = new WaveGenerator(1024, 1024, 1, 100);
                    wave = new Wave(analogGenerator.getRate(), analogGenerator.getData());
                } else {
                    return;
                }
            }
            ScreenPanel screen = new ScreenPanel();
            screen.setLocation(0, 0);
            screen.setGrid(new SampleLayout(wave));
//            screen.setWave(wave);
            screen.setDropTarget(null);
            screen.repaint();
            Manager.MANAGER.getMainFrame().work.addTab(screen);
            Manager.MANAGER.getMainFrame().work.setSelectedComponent(screen);
        }
    }
}
