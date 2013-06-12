/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui;

import edu.sju.ee98.daq.data.Channel;
import edu.sju.ee98.daq.data.Monitor;
import edu.sju.ee98.ni.daqmx.DAQmx;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Leo
 */
public class PanelControl extends JPanel {

    private int index;
    private Monitor monitor;
    private Channel channel = new Channel();
    //
    private JComboBox colorSelect;
    private JButton buttonSync;
    //
    private static final Map<String, Color> COLORS = new LinkedHashMap<String, Color>();

    static {
        COLORS.put("Red", Color.RED);
        COLORS.put("Orange", Color.ORANGE);
        COLORS.put("Yellow", Color.YELLOW);
        COLORS.put("Green", Color.GREEN);
        COLORS.put("Blue", Color.BLUE);
    }

    public PanelControl(int index) {
        this.index = index;
        this.setLayout(null);
        this.setSize(500, 150);
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createTitledBorder("Channel " + this.index));
        //Colors
        colorSelect = new JComboBox(COLORS.keySet().toArray());
        colorSelect.setBounds(10, 60, 80, 30);
        this.add(colorSelect);
        //Vertical
        JLabel verticalTitle = new JLabel("Vertical");
        verticalTitle.setBounds(110, 10, 80, 30);
        this.add(verticalTitle);
        //Button SYNC
        buttonSync = new JButton("SYNC");
        this.buttonSync.setBounds(410, 110, 80, 30);
        this.buttonSync.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                channel.setColor(COLORS.get(colorSelect.getSelectedItem()));
                sync();
            }
        });
        this.add(buttonSync);
        //end
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }

    public void sync() {
//        this.channel.setData(new DAQmx().acqIntClk());
        if (monitor != null) {
            monitor.setChannel(index, channel);
            monitor.repaint();
        }
    }

    public void test() {
        this.channel.setVerticalPostion(0);
        this.channel.setVerticalDiv(5);
        this.channel.setHorizontalPostion(0);
        this.channel.setHorizontalDiv(2);
        this.channel.setData(genData(2, 50));
        this.sync();
    }

    public static double[] genData(double a, double b) {
        double[] data = new double[1000];
        for (int x = 0; x < data.length; x++) {
            data[x] = Math.sin(x / b) * a;
        }
        return data;
    }
}
