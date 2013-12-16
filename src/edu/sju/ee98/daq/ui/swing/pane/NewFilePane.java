/*
 * Copyright (c) 2013, St. John's University and/or its affiliates. All rights reserved.
 * Department of Electrical Engineering.
 */
package edu.sju.ee98.daq.ui.swing.pane;

import edu.sju.ee98.daq.ui.swing.SOptionPanel;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;

/**
 *
 * @author 薛聿明
 */
public class NewFilePane extends SOptionPanel<String> {

    public static final String NAME = "New File";
    private JLabel label = new JLabel("File Type");
    private JComboBox file = new JComboBox(
            new String[]{AnalogConfigPane.NAME, FrequencyResponseConfigPane.NAME});

    public NewFilePane() {
        this.setLayout(null);
        this.setSize(600, 450);
        initComponents();
    }

    @Override
    public String getText() {
        return "Next";
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        value = this.file.getSelectedItem().toString();
    }
}
