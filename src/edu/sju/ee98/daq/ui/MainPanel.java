/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui;

import edu.sju.ee98.daq.ui.screen.ScreenPanel;
import edu.sju.ee98.daq.ui.screen.grid.SampleGrid;
import java.awt.Color;
import java.awt.Component;
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
                System.out.println(getSelectedIndex());
                try {
                    ((WorkPanel) getSelectedComponent()).init();
                } catch (java.lang.NullPointerException ex) {
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
