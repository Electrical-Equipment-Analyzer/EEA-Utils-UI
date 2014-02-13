/*
 * Copyright (c) 2013, St. John's University and/or its affiliates. All rights reserved.
 * Department of Electrical Engineering.
 */
package tw.edu.sju.ee.eea.ui.swing.pane;

import tw.edu.sju.ee.eea.core.frequency.response.FrequencyResponseConfig;
import tw.edu.sju.ee.eea.ui.swing.SLabelInput;
import tw.edu.sju.ee.eea.ui.swing.SOptionPanel;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;

/**
 *
 * @author 薛聿明
 */
public class FrequencyResponseConfigPane extends SOptionPanel<FrequencyResponseConfig> {

    public static final String NAME = "Frequency Response";
    private SLabelInput generateChannel = new SLabelInput("Generate Channel");
    private SLabelInput responseChannel = new SLabelInput("Response Channel");
    private SLabelInput voltage = new SLabelInput("Voltage");
    private SLabelInput minFrequency = new SLabelInput("Min Frequency");
    private SLabelInput maxFrequrncy = new SLabelInput("Max Frequency");
    private SLabelInput length = new SLabelInput("Length");

    public FrequencyResponseConfigPane() {
        this.setBorder(BorderFactory.createTitledBorder("Configure"));
        setValue("PXI1Slot3", "Dev1/ai0:1", "2", "200", "200000", "100");
        initComponents();
    }

    public FrequencyResponseConfigPane(FrequencyResponseConfig config) {
        this.setBorder(BorderFactory.createTitledBorder("Configure"));
        setValue(config.getGenerateChannel(), config.getResponseChannel(), String.valueOf(config.getVoltage()),
                String.valueOf(config.getMinFrequency()), String.valueOf(config.getMaxFrequrncy()), String.valueOf(config.getLength()));
        initComponents();
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

    private void setValue(String generateChannel, String responseChannel, String voltage, String minFrequency, String maxFrequrncy, String length) {
        this.generateChannel.setText(generateChannel);
        this.responseChannel.setText(responseChannel);
        this.minFrequency.setText(minFrequency);
        this.maxFrequrncy.setText(maxFrequrncy);
        this.voltage.setText(voltage);
        this.length.setText(length);
    }

    private void initComponents() {
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
//        voltage.setLocation(50, 300);
//        length.setLocation(50, 300);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                .addGap(10)
                .addGroup(layout.createParallelGroup()
                        .addComponent(generateChannel)
                        .addComponent(responseChannel)
                        .addComponent(minFrequency)
                        .addComponent(maxFrequrncy)
                        .addComponent(voltage)
                        .addComponent(length))
                .addGap(10)
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                .addGap(20)
                .addComponent(generateChannel, 30, 30, 30)
                .addComponent(responseChannel, 30, 30, 30)
                .addComponent(minFrequency, 30, 30, 30)
                .addComponent(maxFrequrncy, 30, 30, 30)
                .addComponent(voltage, 30, 30, 30)
                .addComponent(length, 30, 30, 30)
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
}
