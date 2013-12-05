/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.swing.pane;

import edu.sju.ee.daq.core.math.ComplexArray;
import edu.sju.ee.daq.core.math.MathArray;
import edu.sju.ee98.daq.core.data.Wave;
import edu.sju.ee98.daq.core.function.FrequencyResponse;
import edu.sju.ee98.daq.ui.Manager;
import edu.sju.ee98.daq.ui.screen.BodePlotPainter;
import edu.sju.ee98.daq.ui.screen.SamplePainter;
import edu.sju.ee98.daq.ui.screen.ScreenPanel;
import edu.sju.ee98.daq.ui.swing.SLabelInput;
import edu.sju.ee98.daq.ui.swing.SOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.util.MathArrays;

/**
 *
 * @author Leo
 */
public class FrequencyResponsePane extends SOptionPane implements ActionListener {

    public static final String NAME = "Frequency Response";
    private SLabelInput generateChannel = new SLabelInput("Generate Channel");
    private SLabelInput responseChannel = new SLabelInput("Response Channel");
    private SLabelInput minFrequency = new SLabelInput("Min Frequency");
    private SLabelInput maxFrequrncy = new SLabelInput("Max Frequency");
    private SLabelInput voltage = new SLabelInput("Voltage");
    private SLabelInput length = new SLabelInput("Length");
    private JButton finishButton;

    public FrequencyResponsePane() {
        this.setSize(600, 450);
        initComponents();
    }

    private void testValue() {
        generateChannel.setText("Dev1/ao0");
        responseChannel.setText("Dev1/ai0:1");
        minFrequency.setText("200");
        maxFrequrncy.setText("200000");
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
            value = new FrequencyResponse(
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
        FrequencyResponse config = SOptionPane.showFrequencyResponseDialog(Manager.MANAGER.getMainFrame());
        System.out.println(config);
        if (config != null) {
            Complex[] process = config.process();
            ScreenPanel screen = new ScreenPanel();
            screen.setLocation(0, 0);
            screen.setGrid(new BodePlotPainter(new Wave(100, process)));
            screen.setDropTarget(null);
            screen.repaint();
            Manager.MANAGER.getMainFrame().work.addTab(screen);
            Manager.MANAGER.getMainFrame().work.setSelectedComponent(screen);
            
        }
    }
}
