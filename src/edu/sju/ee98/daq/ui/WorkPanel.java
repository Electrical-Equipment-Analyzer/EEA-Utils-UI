/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui;

import java.net.URL;
import javax.swing.JPanel;

/**
 *
 * @author Leo
 */
public class WorkPanel extends JPanel {

    protected String name;
    protected URL location;
    protected boolean saved;

    public WorkPanel() {
        this.name = "new";
        saved = false;
    }

    public void init() {
        Manager.MANAGER.mainFrame.menuBar.fileMenu.saveItem.setEnabled(!saved);
        Manager.MANAGER.mainFrame.menuBar.fileMenu.saveasItem.setEnabled(!saved);
    }
}
