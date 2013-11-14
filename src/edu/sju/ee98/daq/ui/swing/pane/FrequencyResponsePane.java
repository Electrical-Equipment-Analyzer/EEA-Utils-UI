/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.swing.pane;

import edu.sju.ee98.daq.core.config.AnalogConfig;
import edu.sju.ee98.daq.core.config.FrequencyResponseConfig;
import edu.sju.ee98.daq.ui.Manager;
import edu.sju.ee98.daq.ui.screen.SampleGrid;
import edu.sju.ee98.daq.ui.screen.ScreenPanel;
import edu.sju.ee98.daq.ui.screen.ScreenWave;
import edu.sju.ee98.daq.ui.swing.DAQLabelInput;
import edu.sju.ee98.daq.ui.swing.DAQOptionPane;
import edu.sju.ee98.daq.ui.wave.SComplexWave;
import edu.sju.ee98.ni.daqmx.LoadLibraryException;
import edu.sju.ee98.ni.daqmx.analog.AcqIntClk;
import edu.sju.ee98.ni.daqmx.analog.ContGenIntClk;
import edu.sju.ee98.ni.daqmx.analog.AnalogGenerator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import org.apache.commons.math3.complex.Complex;

/**
 *
 * @author Leo
 */
public class FrequencyResponsePane extends DAQOptionPane implements ActionListener {

    public static final String NAME = "Frequency Response";
    private DAQLabelInput outputChannel = new DAQLabelInput("Output Channel");
    private DAQLabelInput inputChannel = new DAQLabelInput("Input Channel");
    private DAQLabelInput minFrequency = new DAQLabelInput("Min Frequency");
    private DAQLabelInput maxFrequrncy = new DAQLabelInput("Max Frequency");
    private DAQLabelInput voltage = new DAQLabelInput("Voltage");
    private DAQLabelInput log = new DAQLabelInput("Log");//see jqplot
    private JButton finishButton;

    public FrequencyResponsePane() {
        this.setSize(600, 450);
        initComponents();
    }

    private void testValue() {
        outputChannel.setText("Dev1/ao0");
        inputChannel.setText("Dev1/ai0");
        minFrequency.setText("1");
        maxFrequrncy.setText("1000");
        voltage.setText("2");
        log.setText("1024");
    }

    private void initComponents() {
        testValue();
        outputChannel.setLocation(50, 50);
        this.add(outputChannel);
        inputChannel.setLocation(50, 100);
        this.add(inputChannel);
        minFrequency.setLocation(50, 150);
        this.add(minFrequency);
        maxFrequrncy.setLocation(50, 200);
        this.add(maxFrequrncy);
        voltage.setLocation(50, 250);
        this.add(voltage);
        log.setLocation(50, 300);
        this.add(log);

        finishButton = new JButton("Finish");
        finishButton.setBounds(450, 350, 100, 30);
        finishButton.addActionListener(this);
        this.add(finishButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            value = new FrequencyResponseConfig(
                    inputChannel.getText(), outputChannel.getText(), Double.parseDouble(voltage.getText()),
                    Double.parseDouble(minFrequency.getText()), Double.parseDouble(maxFrequrncy.getText()),
                    Integer.parseInt(log.getText()));
        } catch (java.lang.NumberFormatException ex) {
            JOptionPane.showMessageDialog(FrequencyResponsePane.this.getRootPane(), "Please input a Integer!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        getDialog().dispose();
    }

    private static Complex[] process(FrequencyResponseConfig config) {
        Complex[] data = new Complex[config.getLength()];
        ContGenIntClk out;
        AcqIntClk in;
        for (int i = 0; i < config.getLength(); i++) {
            try {
                out = config.createOutput();
                in = config.createInput();
                out.start();
                in.read();
                out.stop();
                Complex mainOut = out.getMainFrequency(config.getFrequency().getFrequency());
                Complex mainIn = in.getMainFrequency(config.getFrequency().getFrequency());
                data[i] = mainOut.divide(mainIn);
            } catch (LoadLibraryException ex) {
                Logger.getLogger(FrequencyResponsePane.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return data;
    }

    public static void create() {
        FrequencyResponseConfig config = DAQOptionPane.showFrequencyResponseDialog(Manager.MANAGER.getMainFrame());
        System.out.println(config);
        if (config != null) {
            ScreenWave wave = null;
            wave = new SComplexWave(1000,process(config));
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
