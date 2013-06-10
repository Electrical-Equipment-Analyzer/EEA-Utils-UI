/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui;

import edu.sju.ee98.daq.data.Channel;
import edu.sju.ee98.daq.data.Monitor;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
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
    private JButton buttonSync;
    //
    private static final HashMap<String, Color> colors = new HashMap<String, Color>();

    static {
        colors.put("Red", Color.RED);
        colors.put("Orange", Color.ORANGE);
        colors.put("Yellow", Color.YELLOW);
        colors.put("Green", Color.GREEN);
        colors.put("Blue", Color.BLUE);
    }

    public PanelControl(int index) {
        this.index = index;
        this.setLayout(null);
        this.setSize(500, 150);
        this.setBackground(Color.white);
        //Title
        JLabel title = new JLabel("Channel " + this.index);
        title.setBounds(10, 10, 80, 30);
        this.add(title);
        //Colors
        JComboBox colorSelect = new JComboBox(colors.keySet().toArray());
        colorSelect.setBounds(10, 60, 80, 30);
        colorSelect.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                channel.setColor(colors.get(e.getItem()));
            }
        });
        this.add(colorSelect);

        //Button SYNC
        buttonSync = new JButton("SYNC");
        this.buttonSync.setBounds(410, 110, 80, 30);
        this.buttonSync.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        if (monitor != null) {
            monitor.setChannel(index, channel);
        }
        monitor.repaint();
    }

    public void test() {
        this.channel.setColor(Color.RED);
        this.channel.setData(genData(100, 50));
        this.sync();
    }

    public static int[] genData(double a, double b) {
        int[] data = new int[500];
        for (int x = 0; x < data.length; x++) {
            data[x] = (int) (Math.sin(x / b) * a * -1) + 200;
        }
        return data;
    }
}
