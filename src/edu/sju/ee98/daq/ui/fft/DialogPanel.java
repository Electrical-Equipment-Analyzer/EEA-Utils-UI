/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.fft;

import java.awt.LayoutManager;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 *
 * @author Leo
 */
public class DialogPanel extends JPanel {

    protected JDialog dialog;

    public DialogPanel(LayoutManager layout) {
        super(layout);
    }

    public void setDialog(JDialog dialog) {
        this.dialog = dialog;
    }
    
}
