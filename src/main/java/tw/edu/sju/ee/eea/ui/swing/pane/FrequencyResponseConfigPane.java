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
    private SLabelInput generateDevice = new SLabelInput("Generate Device");
    private SLabelInput responseDevice = new SLabelInput("Response Device");
    private SLabelInput voltage = new SLabelInput("Voltage");
    private SLabelInput minFrequency = new SLabelInput("Min Frequency");
    private SLabelInput maxFrequrncy = new SLabelInput("Max Frequency");
    private SLabelInput length = new SLabelInput("Length");
    private SLabelInput rateMultiple = new SLabelInput("Rate Multiple");

    public FrequencyResponseConfigPane() {
        this.setBorder(BorderFactory.createTitledBorder("Configure"));
        setValue("PXI1Slot3", "PXI1Slot4", "2", "30", "3000000", "200", "64");
        initComponents();
    }

//    public FrequencyResponseConfigPane(FrequencyResponseConfig config) {
//        this.setBorder(BorderFactory.createTitledBorder("Configure"));
//        setValue(config.getGenerateChannel(), config.getResponseChannel(), String.valueOf(config.getVoltage()),
//                String.valueOf(config.getMinFrequency()), String.valueOf(config.getMaxFrequrncy()), String.valueOf(config.getLength()));
//        initComponents();
//    }
    public FrequencyResponseConfig getConfig() {
        return new FrequencyResponseConfig(
                generateDevice.getText(), responseDevice.getText(), Double.parseDouble(voltage.getText()),
                Double.parseDouble(minFrequency.getText()), Double.parseDouble(maxFrequrncy.getText()),
                Integer.parseInt(length.getText()), Integer.parseInt(rateMultiple.getText()));
    }

    @Override
    public String getText() {
        return "Finish";
    }

    private void setValue(String generateChannel, String responseChannel, String voltage, String minFrequency, String maxFrequrncy, String length, String rateMultiple) {
        this.generateDevice.setText(generateChannel);
        this.responseDevice.setText(responseChannel);
        this.voltage.setText(voltage);
        this.minFrequency.setText(minFrequency);
        this.maxFrequrncy.setText(maxFrequrncy);
        this.length.setText(length);
        this.rateMultiple.setText(rateMultiple);
    }

    private void initComponents() {
        this.add(generateDevice);
        this.add(responseDevice);
        this.add(voltage);
        this.add(minFrequency);
        this.add(maxFrequrncy);
        this.add(length);
        this.add(rateMultiple);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                .addGap(10)
                .addGroup(layout.createParallelGroup()
                        .addComponent(generateDevice)
                        .addComponent(responseDevice)
                        .addComponent(voltage)
                        .addComponent(minFrequency)
                        .addComponent(maxFrequrncy)
                        .addComponent(length)
                        .addComponent(rateMultiple))
                .addGap(10)
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                .addGap(20)
                .addComponent(generateDevice, 30, 30, 30)
                .addComponent(responseDevice, 30, 30, 30)
                .addComponent(voltage, 30, 30, 30)
                .addComponent(minFrequency, 30, 30, 30)
                .addComponent(maxFrequrncy, 30, 30, 30)
                .addComponent(length, 30, 30, 30)
                .addComponent(rateMultiple, 30, 30, 30)
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
