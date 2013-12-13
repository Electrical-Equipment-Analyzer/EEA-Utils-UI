/*
 * Copyright (c) 2013, St. John's University and/or its affiliates. All rights reserved.
 * Department of Electrical Engineering.
 */
package edu.sju.ee98.daq.ui.swing;

import edu.sju.ee98.daq.core.frequency.response.FrequencyResponse;
import edu.sju.ee98.daq.core.function.AnalogVoltage;
import edu.sju.ee98.daq.ui.swing.pane.AnalogConfigPane;
import edu.sju.ee98.daq.ui.swing.pane.FrequencyResponsePane;
import edu.sju.ee98.daq.ui.swing.pane.NewFilePane;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;

/**
 *
 * @author 102m05008
 */
public class SOptionDialog extends JComponent implements ActionListener {

    private JDialog dialog;
    private SOptionPanel panel;
    private JButton finishButton;

    public SOptionDialog(SOptionPanel panel) {
        this.setSize(600, 450);
        this.panel = panel;
        this.panel.setBounds(0, 0, 600, 350);
        this.add(panel);
        finishButton = new JButton("Finish");
        finishButton.setBounds(450, 380, 100, 30);
        finishButton.addActionListener(this);
        this.add(finishButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.panel.actionPerformed(e);
        getDialog().dispose();
    }

    public static String showNewFileDialog(Component parentComponent) {
        NewFilePane panel = new NewFilePane();
        SOptionDialog pane = new SOptionDialog(panel);
        pane.dialog = pane.createDialog(parentComponent, "New File");
        pane.dialog.show();
        return panel.getValue();
    }

    public static AnalogVoltage showAnalogConfigDialog(Component parentComponent) {
        AnalogConfigPane panel = new AnalogConfigPane();
        SOptionDialog pane = new SOptionDialog(panel);
        pane.dialog = pane.createDialog(parentComponent, AnalogConfigPane.NAME);
        pane.dialog.show();
        return panel.getValue();
    }

    public static FrequencyResponse showFrequencyResponseDialog(Component parentComponent) {
        FrequencyResponsePane panel = new FrequencyResponsePane();
        SOptionDialog pane = new SOptionDialog(panel);
        pane.dialog = pane.createDialog(parentComponent, FrequencyResponsePane.NAME);
        pane.dialog.show();
        return panel.getValue();
    }

    private JDialog createDialog(Component parentComponent, String title)
            throws HeadlessException {
        final JDialog dialog;
        if (parentComponent instanceof Frame) {
            dialog = new JDialog((Frame) parentComponent, title, true);
        } else {
            dialog = new JDialog((Dialog) parentComponent, title, true);
        }

        initDialog(dialog, parentComponent);
        return dialog;
    }

    private void initDialog(final JDialog dialog, Component parentComponent) {
//        dialog.setComponentOrientation(this.getComponentOrientation());
        Container contentPane = dialog.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(this, BorderLayout.CENTER);
        dialog.setResizable(false);
//        if (JDialog.isDefaultLookAndFeelDecorated()) {
//            boolean supportsWindowDecorations =
//              UIManager.getLookAndFeel().getSupportsWindowDecorations();
//            if (supportsWindowDecorations) {
//                dialog.setUndecorated(true);
//                getRootPane().setWindowDecorationStyle(style);
//            }
//        }
        dialog.setSize(this.getSize());
        dialog.setLocationRelativeTo(parentComponent);
    }

    protected JDialog getDialog() {
        return dialog;
    }
}
