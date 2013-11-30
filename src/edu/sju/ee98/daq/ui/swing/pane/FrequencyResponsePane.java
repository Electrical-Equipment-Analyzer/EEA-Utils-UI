/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.swing.pane;

import edu.sju.ee98.daq.core.config.FrequencyResponseConfig;
import edu.sju.ee98.daq.ui.Manager;
import edu.sju.ee98.daq.ui.screen.SampleGrid;
import edu.sju.ee98.daq.ui.screen.ScreenPanel;
import edu.sju.ee98.daq.ui.screen.ScreenWave;
import edu.sju.ee98.daq.ui.swing.DAQLabelInput;
import edu.sju.ee98.daq.ui.swing.DAQOptionPane;
import edu.sju.ee98.daq.ui.wave.SComplexWave;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Leo
 */
public class FrequencyResponsePane extends DAQOptionPane implements ActionListener {

    public static final String NAME = "Frequency Response";
    private DAQLabelInput generateChannel = new DAQLabelInput("Generate Channel");
    private DAQLabelInput responseChannel = new DAQLabelInput("Response Channel");
    private DAQLabelInput minFrequency = new DAQLabelInput("Min Frequency");
    private DAQLabelInput maxFrequrncy = new DAQLabelInput("Max Frequency");
    private DAQLabelInput voltage = new DAQLabelInput("Voltage");
    private DAQLabelInput length = new DAQLabelInput("Length");
    private JButton finishButton;

    public FrequencyResponsePane() {
        this.setSize(600, 450);
        initComponents();
    }

    private void testValue() {
        generateChannel.setText("Dev1/ao0");
        responseChannel.setText("Dev1/ai0:1");
        minFrequency.setText("40");
        maxFrequrncy.setText("4000");
        voltage.setText("2");
        length.setText("100");
    }

    private void initComponents() {
        testValue();
        generateChannel.setLocation(50, 50);
        this.add(generateChannel);
        responseChannel.setLocation(50, 100);
        this.add(responseChannel);
        minFrequency.setLocation(50, 150);
        this.add(minFrequency);
        maxFrequrncy.setLocation(50, 200);
        this.add(maxFrequrncy);
        voltage.setLocation(50, 250);
        this.add(voltage);
        length.setLocation(50, 300);
        this.add(length);

        finishButton = new JButton("Finish");
        finishButton.setBounds(450, 350, 100, 30);
        finishButton.addActionListener(this);
        this.add(finishButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            value = new FrequencyResponseConfig(
                    generateChannel.getText(), responseChannel.getText(), Double.parseDouble(voltage.getText()),
                    Double.parseDouble(minFrequency.getText()), Double.parseDouble(maxFrequrncy.getText()),
                    Integer.parseInt(length.getText()));
        } catch (java.lang.NumberFormatException ex) {
            JOptionPane.showMessageDialog(FrequencyResponsePane.this.getRootPane(), "Please input a Integer!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        getDialog().dispose();
    }

    public static void create() {
        FrequencyResponseConfig config = DAQOptionPane.showFrequencyResponseDialog(Manager.MANAGER.getMainFrame());
        System.out.println(config);
        if (config != null) {
            ScreenWave wave = null;
            wave = new SComplexWave(100, config.process());
            ScreenPanel screen = new ScreenPanel();
            screen.setLocation(0, 0);
            screen.setGrid(new SampleGrid());
            screen.setWave(wave);
            screen.setDropTarget(null);
            screen.repaint();
            Manager.MANAGER.getMainFrame().work.addTab(screen);
            Manager.MANAGER.getMainFrame().work.setSelectedComponent(screen);
        }
    }
}
