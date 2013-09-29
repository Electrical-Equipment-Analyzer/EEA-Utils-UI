/*
 * Copyright (c) 2013, St. John's University and/or its affiliates. All rights reserved.
 * Department of Electrical Engineering.
 */
package edu.sju.ee98.daq.ui.fft;

import java.awt.LayoutManager;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 *
 * @author 102m05008
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
