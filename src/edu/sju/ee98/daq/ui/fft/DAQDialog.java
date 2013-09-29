/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.fft;

import edu.sju.ee98.daq.ui.swing.pane.AnalogConfigPane;
import edu.sju.ee98.ni.daqmx.config.NIAnalogConfig;
import java.awt.Container;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Leo
 */
public class DAQDialog extends JDialog {


    public DAQDialog(JFrame frame, DialogPanel panel) {
        super(frame, "New Source", true);
//        this.setSize(350, 300);
        this.setResizable(false);
        initComponents(panel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void initComponents(DialogPanel panel) {
        this.setSize(panel.getSize());
        panel.setDialog(this);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
//        AnalogConfigPane s = new AnalogConfigPane();
        panel.setLocation(0, 0);
        contentPane.add(panel);
    }

    public static NIAnalogConfig showAnalogConfigPane(JFrame root) {
        AnalogConfigPane analogConfigPane = new AnalogConfigPane();
//        new DAQDialog(root, analogConfigPane);
//        return analogConfigPane.getConfig();
        return null;
    }
}
