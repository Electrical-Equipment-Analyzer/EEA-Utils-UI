/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui;

import edu.sju.ee98.daq.ui.screen.ScreenPanel;
import edu.sju.ee98.daq.ui.screen.SampleGrid;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Leo
 */
public class MainPanel extends JTabbedPane {

    public MainPanel() {
        this.setSize(1366, 655);
        this.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Logger.getLogger(MainPanel.class.getName()).log(Level.FINE, String.valueOf(getSelectedIndex()));
                WorkPanel work = (WorkPanel) getSelectedComponent();
                if (work != null) {
                    work.init();
                } else {
                    try {
                        Manager.MANAGER.mainFrame.menuBar.fileMenu.saveItem.setEnabled(false);
                        Manager.MANAGER.mainFrame.menuBar.fileMenu.saveasItem.setEnabled(false);
                        Manager.MANAGER.mainFrame.menuBar.fileMenu.closeItem.setEnabled(false);
                    } catch (java.lang.NullPointerException ex) {
                    }
                }
            }
        });

        tabSource();
        tabFastFourierTransform();
    }

    public void addTab(WorkPanel work) {
        super.addTab(work.name, work);
    }

    private void tabSource() {
        ScreenPanel screen = new ScreenPanel();
        screen.setLocation(0, 0);
        screen.setGrid(new SampleGrid());
        screen.setDropTarget(null);
        this.addTab(screen);
    }

    private void tabFastFourierTransform() {
        ScreenPanel screen = new ScreenPanel();
        screen.saved = true;
        screen.setLocation(0, 0);
//        screen.setGrid(new SampleGrid());
        this.addTab(screen);
    }
}
