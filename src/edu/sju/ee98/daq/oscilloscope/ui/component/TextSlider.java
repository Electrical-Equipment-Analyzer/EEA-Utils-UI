/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.oscilloscope.ui.component;

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

    private TextSliderValue option;
    private JTextField text;
    private JSlider slider;

    public TextSlider(String title, TextSliderValue option) {
        super(null);
        this.option = option;
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
        option.setSlider(slider);
        slider.addChangeListener(this);
        this.add(slider);
        
        Object value = this.option.valueChange(this.slider.getValue());
        this.text.setText(this.option.toText(value));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object value = this.option.valueChange(this.text.getText());
        this.slider.setValue(this.option.toSlider(value));
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        Object value = this.option.valueChange(this.slider.getValue());
        this.text.setText(this.option.toText(value));
    }
}
