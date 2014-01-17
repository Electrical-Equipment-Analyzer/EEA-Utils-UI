/*
 * Copyright (c) 2013, St. John's University and/or its affiliates. All rights reserved.
 * Department of Electrical Engineering.
 */
package edu.sju.ee.eea.ui.swing;

import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 *
 * @author 薛聿明
 */
public abstract class SOptionPanel<T> extends JPanel implements ActionListener {

    protected T value;
    
    public abstract String getText();

    public final T getValue() {
        return value;
    }
}
