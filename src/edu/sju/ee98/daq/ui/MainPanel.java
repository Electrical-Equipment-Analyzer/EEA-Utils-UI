/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui;

import edu.sju.ee98.daq.core.frequency.response.FrequencyResponseConfig;
import edu.sju.ee98.daq.core.frequency.response.FrequencyResponseFile;
import edu.sju.ee98.daq.ui.screen.BodePlotLayout;
import edu.sju.ee98.daq.ui.screen.ScreenPanel;
import edu.sju.ee98.daq.ui.screen.SampleLayout;
import edu.sju.ee98.daq.ui.workspace.data.FrequencyResponsePanel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.apache.commons.math3.complex.Complex;

/**
 *
 * @author Leo
 */
public class MainPanel extends JTabbedPane {

    public MainPanel() {
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
        tabFrequencyResponse();
    }

    public void addTab(WorkPanel work) {
        super.addTab(work.name, work);
    }

    private void tabSource() {
        ScreenPanel screen = new ScreenPanel();
//        screen.setLocation(0, 0);
        screen.setGrid(new SampleLayout(null));
        screen.setDropTarget(null);
        this.addTab(screen);
    }

    private void tabFastFourierTransform() {
        ScreenPanel screen = new ScreenPanel();
//        screen.saved = true;
//        screen.setLocation(0, 0);
        screen.setGrid(new BodePlotLayout(null));
        this.addTab(screen);
    }

    private void tabFrequencyResponse() {
        Complex[] input = new Complex[1000];
        Complex[] output = new Complex[1000];
        for (int i = 0; i < input.length; i++) {
            input[i] = new Complex(i, i);
        }
        for (int i = 0; i < output.length; i++) {
            output[i] = new Complex(-i, -i);
        }
        WorkPanel tab = new FrequencyResponsePanel(new FrequencyResponseFile(
                new FrequencyResponseConfig("dev/ao0", "dev/ai0:1", 2, 100, 10000, 1000), input, output));
//        screen.saved = true;
//        screen.setLocation(0, 0);
        this.addTab(tab);
    }
}
