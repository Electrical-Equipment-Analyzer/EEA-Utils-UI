/*
 * Copyright (c) 2013, St. John's University and/or its affiliates. All rights reserved.
 * Department of Electrical Engineering.
 */
package edu.sju.ee.eea.ui.swing;

import edu.sju.ee.eea.core.frequency.response.FrequencyResponseConfig;
import edu.sju.ee.eea.core.function.AnalogVoltage;
import edu.sju.ee.eea.ui.swing.pane.AnalogConfigPane;
import edu.sju.ee.eea.ui.swing.pane.FrequencyResponseConfigPane;
import edu.sju.ee.eea.ui.swing.pane.NewFilePane;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
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
    private JButton button;

    public SOptionDialog(SOptionPanel panel) {
        this.setSize(600, 450);
        this.panel = panel;
        button = new JButton(panel.getText());
        button.addActionListener(this);
        initComponents();
    }

    private void initComponents() {
        this.add(panel);
        this.add(button);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addGap(10)
                        .addComponent(panel)
                        .addGap(10))
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button)
                        .addGap(30))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGap(10)
                        .addComponent(panel)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, 20)
//                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, 50)
                        .addComponent(button)
                        .addGap(20)
        );
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

    public static FrequencyResponseConfig showFrequencyResponseDialog(Component parentComponent) {
        FrequencyResponseConfigPane panel = new FrequencyResponseConfigPane();
        SOptionDialog pane = new SOptionDialog(panel);
        pane.dialog = pane.createDialog(parentComponent, FrequencyResponseConfigPane.NAME);
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
