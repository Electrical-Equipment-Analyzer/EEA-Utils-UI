/*
 * Copyright (c) 2013, St. John's University and/or its affiliates. All rights reserved.
 * Department of Electrical Engineering.
 */
package edu.sju.ee98.daq.ui.swing;

import edu.sju.ee98.daq.ui.swing.pane.AnalogConfigPane;
import edu.sju.ee98.daq.ui.swing.pane.FrequencyResponsePane;
import edu.sju.ee98.daq.ui.swing.pane.NewFilePane;
import edu.sju.ee98.ni.daqmx.config.NIAnalogConfig;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.HeadlessException;
import javax.swing.JComponent;
import javax.swing.JDialog;

/**
 *
 * @author 102m05008
 */
public class DAQOptionPane extends JComponent {

    protected Object value;
    private JDialog dialog;

    public static void main(String[] args) {
        DAQOptionPane.showAnalogConfigDialog(null);
        System.exit(0);
    }

    public static String showNewFileDialog(Component parentComponent) {
        DAQOptionPane pane = new NewFilePane();
        pane.dialog = pane.createDialog(parentComponent, "New File");
        pane.dialog.show();
        return (String) pane.getValue();
    }

    public static NIAnalogConfig showAnalogConfigDialog(Component parentComponent) {
        DAQOptionPane pane = new AnalogConfigPane();
        pane.dialog = pane.createDialog(parentComponent, AnalogConfigPane.NAME);
        pane.dialog.show();
        return (NIAnalogConfig) pane.getValue();
    }

    public static void showFrequencyResponseDialog(Component parentComponent) {
        DAQOptionPane pane = new FrequencyResponsePane();
        pane.dialog = pane.createDialog(parentComponent, FrequencyResponsePane.NAME);
        pane.dialog.show();
        return;
    }

    public Object getValue() {
        return value;
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
