/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Leo
 */
public class TextSlider extends JPanel implements ActionListener, ChangeListener {

    private TextSliderValue value;
    private JTextField text;
    private JSlider slider;

    public TextSlider(String title, TextSliderValue value) {
        super(null);
        this.value = value;
        this.setSize(250, 60);
        this.setBorder(BorderFactory.createTitledBorder(title));
        //
        text = new JTextField();
        text.setBounds(10, 25, 35, 20);
        text.addActionListener(this);
        this.add(text);
        //
        slider = new JSlider();
        slider.setBounds(45, 20, 200, 30);
        value.setSlider(slider);
        slider.addChangeListener(this);
        this.add(slider);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object value = this.value.valueChange(this.text.getText());
        this.slider.setValue(this.value.toSlider(value));
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        Object value = this.value.valueChange(this.slider.getValue());
        this.text.setText(this.value.toText(value));
    }
}
