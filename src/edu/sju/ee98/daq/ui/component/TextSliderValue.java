/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.component;

import javax.swing.JSlider;

/**
 *
 * @author Leo
 */
public interface TextSliderValue<E> {

    public void setSlider(JSlider slider);

    public E valueChange(String text);

    public E valueChange(int slider);

    public String toText(E value);

    public int toSlider(E value);

//    public void valueChange(E value);
}
