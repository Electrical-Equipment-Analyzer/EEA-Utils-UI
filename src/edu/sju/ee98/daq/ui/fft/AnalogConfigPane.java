/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.fft;

import edu.sju.ee98.ni.daqmx.NIAnalogConfig;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Leo
 */
public class AnalogConfigPane extends DialogPanel {

//    private boolean finish = false;
    private NIAnalogConfig config;
    private JInput voltage = new JInput("Voltage");
    private JInput rate = new JInput("Rate");
    private JInput length = new JInput("Length");

    public AnalogConfigPane() {
        super(null);
        this.setSize(300, 300);
//        this.setBackground(Color.yellow);
        initComponents();
    }

    private void initComponents() {

        rate.setLocation(50, 50);
        this.add(rate);
        voltage.setLocation(50, 100);
        this.add(voltage);
        length.setLocation(50, 150);
        this.add(length);

        JButton finishButton = new JButton("Finish");
        finishButton.setBounds(100, 200, 100, 30);
        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    config = new NIAnalogConfig(Integer.parseInt(voltage.getText()), Float.parseFloat(rate.getText()), Integer.parseInt(length.getText()));
                } catch (java.lang.NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Please input a Integer!", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                dialog.dispose();
            }
        });
        this.add(finishButton);

    }

    public NIAnalogConfig getConfig() {
        return config;
//        if (!finish) {
//            return null;
//        }
//        return new NIAnalogConfig(Integer.parseInt(voltage.getText()), Float.parseFloat(rate.getText()), Integer.parseInt(length.getText()));
    }

    private void add(JInput input) {
        this.add(input.label);
        this.add(input.textField);
    }

    private class JInput {

        protected JLabel label = new JLabel();
        protected JTextField textField = new JTextField();

        private JInput(String text) {
            this.label.setSize(100, 25);
            this.textField.setSize(100, 25);
            label.setText(text);
        }

        private void setLocation(int x, int y) {
            this.label.setLocation(x, y);
            this.textField.setLocation(x + 100, y);
        }

        public String getText() {
            return this.textField.getText();
        }
    }
}
