/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.swing;

import java.awt.Color;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 *
 * @author Leo
 */
public class DAQLabelInput extends JComponent {

    private JLabel label;
    private JTextField textField;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        JButton b = new JButton("sss");
        JButton c = new JButton("cc");
        Container contentPane = frame.getContentPane();
//        contentPane.setLayout(new FlowLayout());
        contentPane.setLayout(null);

//        GroupLayout layout = new GroupLayout(contentPane);
//        contentPane.setLayout(layout);
//        layout.setAutoCreateGaps(true);
//        layout.setAutoCreateContainerGaps(true);
//
//        layout.setHorizontalGroup(
//                layout.createSequentialGroup()
//                .addComponent(b)
//                .addComponent(c));
//        layout.setVerticalGroup(
//                layout.createSequentialGroup()
//                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//                .addComponent(b)
//                .addComponent(c)));

//        contentPane.add(b);
//        contentPane.add(c);

//        c.setBounds(10, 10, 50, 50);
//        c.setSize(100,100);
//        contentPane.add(c);
        DAQLabelInput daqLabelInput = new DAQLabelInput("aa");
//        daqLabelInput.setBounds(10, 10, 50, 50);
        daqLabelInput.setLocation(10, 10);
        contentPane.add(daqLabelInput);
        frame.repaint();
        frame.setVisible(true);
    }

    public DAQLabelInput(String text) {
//        this.setLayout(new FlowLayout());
        this.setBackground(Color.red);
        this.setSize(500, 30);
        this.label = new JLabel(text);
        this.textField = new JTextField(10);
//                this.label.setSize(100, 25);
//                this.textField.setSize(100, 25);
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        this.add(this.label);
//        this.updateUI();
        this.add(this.textField);
        layout.putConstraint(SpringLayout.WEST, label, 5, SpringLayout.WEST, this);
//        layout.putConstraint(SpringLayout.NORTH, label, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, textField, 150, SpringLayout.WEST, this);
//        layout.putConstraint(SpringLayout.NORTH, textField, 5, SpringLayout.NORTH, this);
    }

    public void setText(String text) {
        this.textField.setText(text);
    }

    public String getText() {
        return this.textField.getText();
    }
}
