/*
 * Copyright (c) 2013, St. John's University and/or its affiliates. All rights reserved.
 * Department of Electrical Engineering.
 */
package edu.sju.ee98.daq.ui.swing.pane;

import edu.sju.ee98.daq.core.frequency.response.FrequencyResponse;
import edu.sju.ee98.daq.ui.Manager;
import edu.sju.ee98.daq.ui.screen.BodePlotLayout;
import edu.sju.ee98.daq.ui.screen.ScreenPanel;
import edu.sju.ee98.daq.ui.swing.SLabelInput;
import edu.sju.ee98.daq.ui.swing.SOptionDialog;
import edu.sju.ee98.daq.ui.swing.SOptionPanel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import org.apache.commons.math3.complex.Complex;

/**
 *
 * @author 薛聿明
 */
public class FrequencyResponsePane extends SOptionPanel<FrequencyResponse> {

    public static final String NAME = "Frequency Response";
    private SLabelInput generateChannel = new SLabelInput("Generate Channel");
    private SLabelInput responseChannel = new SLabelInput("Response Channel");
    private SLabelInput minFrequency = new SLabelInput("Min Frequency");
    private SLabelInput maxFrequrncy = new SLabelInput("Max Frequency");
    private SLabelInput voltage = new SLabelInput("Voltage");
    private SLabelInput length = new SLabelInput("Length");

    public FrequencyResponsePane() {
        this.setLayout(null);
        this.setBackground(Color.red);
        initComponents();
    }

    @Override
    public String getText() {
        return "Finish";
    }

    private void testValue() {
        generateChannel.setText("Dev1/ao0");
        responseChannel.setText("Dev1/ai0:1");
        minFrequency.setText("200");
        maxFrequrncy.setText("200000");
        voltage.setText("2");
        length.setText("100");
    }

    private void initComponents() {
        testValue();
        this.add(generateChannel);
        this.add(responseChannel);
        this.add(minFrequency);
        this.add(maxFrequrncy);
        this.add(voltage);
        this.add(length);
        generateChannel.setLocation(50, 50);
        responseChannel.setLocation(50, 100);
        minFrequency.setLocation(50, 150);
        maxFrequrncy.setLocation(50, 200);
        voltage.setLocation(50, 250);
        length.setLocation(50, 300);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            value = new FrequencyResponse(
                    generateChannel.getText(), responseChannel.getText(), Double.parseDouble(voltage.getText()),
                    Double.parseDouble(minFrequency.getText()), Double.parseDouble(maxFrequrncy.getText()),
                    Integer.parseInt(length.getText()));
        } catch (java.lang.NumberFormatException ex) {
            JOptionPane.showMessageDialog(FrequencyResponsePane.this.getRootPane(), "Please input a Integer!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    public static void create() {
        FrequencyResponse config = SOptionDialog.showFrequencyResponseDialog(Manager.MANAGER.getMainFrame());
        System.out.println(config);
        if (config != null) {
            Complex[] process = config.process();
            ScreenPanel screen = new ScreenPanel();
            screen.setLocation(0, 0);
            screen.setGrid(new BodePlotLayout(null));
            screen.setDropTarget(null);
            screen.repaint();
            Manager.MANAGER.getMainFrame().work.addTab(screen);
            Manager.MANAGER.getMainFrame().work.setSelectedComponent(screen);

        }
    }
}
