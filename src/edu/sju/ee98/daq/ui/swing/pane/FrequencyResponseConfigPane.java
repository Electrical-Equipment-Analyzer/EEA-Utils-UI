/*
 * Copyright (c) 2013, St. John's University and/or its affiliates. All rights reserved.
 * Department of Electrical Engineering.
 */
package edu.sju.ee98.daq.ui.swing.pane;

import edu.sju.ee98.daq.core.frequency.response.FrequencyResponse;
import edu.sju.ee98.daq.core.frequency.response.FrequencyResponseConfig;
import edu.sju.ee98.daq.core.frequency.response.FrequencyResponseFile;
import edu.sju.ee98.daq.ui.Manager;
import edu.sju.ee98.daq.ui.screen.BodePlotLayout;
import edu.sju.ee98.daq.ui.screen.ScreenPanel;
import edu.sju.ee98.daq.ui.swing.SLabelInput;
import edu.sju.ee98.daq.ui.swing.SOptionDialog;
import edu.sju.ee98.daq.ui.swing.SOptionPanel;
import edu.sju.ee98.daq.ui.workspace.data.FrequencyResponsePanel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import org.apache.commons.math3.complex.Complex;

/**
 *
 * @author 薛聿明
 */
public class FrequencyResponseConfigPane extends SOptionPanel<FrequencyResponseConfig> {

    public static final String NAME = "Frequency Response";
    private SLabelInput generateChannel = new SLabelInput("Generate Channel");
    private SLabelInput responseChannel = new SLabelInput("Response Channel");
    private SLabelInput minFrequency = new SLabelInput("Min Frequency");
    private SLabelInput maxFrequrncy = new SLabelInput("Max Frequency");
    private SLabelInput voltage = new SLabelInput("Voltage");
    private SLabelInput length = new SLabelInput("Length");

    public FrequencyResponseConfigPane() {
//        this.setLayout(null);
        this.setBackground(Color.red);
        this.setBorder(BorderFactory.createTitledBorder("Configure"));
        initComponents();
    }

    public FrequencyResponseConfigPane(FrequencyResponseConfig config) {

    }

    public FrequencyResponseConfig getConfig() {
        return new FrequencyResponseConfig(
                    generateChannel.getText(), responseChannel.getText(), Double.parseDouble(voltage.getText()),
                    Double.parseDouble(minFrequency.getText()), Double.parseDouble(maxFrequrncy.getText()),
                    Integer.parseInt(length.getText()));
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
//        generateChannel.setLocation(50, 50);
//        responseChannel.setLocation(50, 100);
//        minFrequency.setLocation(50, 150);
//        maxFrequrncy.setLocation(50, 200);
//        voltage.setLocation(50, 250);
//        length.setLocation(50, 300);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                .addGap(20)
                .addGroup(layout.createParallelGroup()
                        .addComponent(generateChannel)
                        .addComponent(responseChannel)
                        .addComponent(minFrequency)
                        .addComponent(maxFrequrncy)
                        .addComponent(voltage)
                        .addComponent(length))
                .addGap(20)
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                .addGap(20)
                .addComponent(generateChannel, 20, 25, 30)
                .addComponent(responseChannel, 20, 25, 30)
                .addComponent(minFrequency, 20, 25, 30)
                .addComponent(maxFrequrncy, 20, 25, 30)
                .addComponent(voltage, 20, 25, 30)
                .addComponent(length, 20, 25, 30)
                .addGap(20)
        );

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            value = this.getConfig();
        } catch (java.lang.NumberFormatException ex) {
            JOptionPane.showMessageDialog(FrequencyResponseConfigPane.this.getRootPane(), "Please input a Integer!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    public static void create() {
        FrequencyResponseConfig config = SOptionDialog.showFrequencyResponseDialog(Manager.MANAGER.getMainFrame());
        System.out.println(config);
        if (config != null) {
            FrequencyResponseFile file = new FrequencyResponse(config).process();
//            ScreenPanel screen = new ScreenPanel();
//            screen.setLocation(0, 0);
//            screen.setGrid(new BodePlotLayout(file));
//            screen.setDropTarget(null);
//            screen.repaint();
            FrequencyResponsePanel frequencyResponsePanel = new FrequencyResponsePanel(file);
            Manager.MANAGER.getMainFrame().work.addTab(frequencyResponsePanel);
            Manager.MANAGER.getMainFrame().work.setSelectedComponent(frequencyResponsePanel);

        }
    }
}
