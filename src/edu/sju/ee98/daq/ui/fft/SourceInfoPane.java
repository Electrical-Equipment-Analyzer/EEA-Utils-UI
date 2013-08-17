/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.fft;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Leo
 */
public class SourceInfoPane extends JPanel {

    public SourceInfoPane() {
        super(null);
        this.setSize(300, 300);
//        this.setBackground(Color.yellow);
        initComponents();
    }
    public final JInput voltage = new JInput("Voltage");
    public final JInput rate = new JInput("Rate");
    public final JInput length = new JInput("Length");

    private void initComponents() {

        rate.setLocation(50, 50);
        this.add(rate);
        voltage.setLocation(50, 100);
        this.add(voltage);
        length.setLocation(50, 150);
        this.add(length);

        JButton finish = new JButton("Finish");
        finish.setBounds(100, 200, 100, 30);
        this.add(finish);

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
