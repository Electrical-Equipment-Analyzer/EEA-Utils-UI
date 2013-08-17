/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.fft;

import java.awt.Container;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author Leo
 */
public class NewDialog extends JDialog {

    public NewDialog(JFrame frame) {
        super(frame, "New Source", true);
        this.setSize(350, 300);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void initComponents() {
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        SourceInfoPane s = new SourceInfoPane();
        s.setLocation(0, 0);
        contentPane.add(s);
    }
}
