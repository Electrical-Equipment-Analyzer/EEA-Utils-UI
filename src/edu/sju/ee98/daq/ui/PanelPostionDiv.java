/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui;

import edu.sju.ee98.daq.ui.component.TextSlider;
import edu.sju.ee98.daq.ui.component.TextSliderValue;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSlider;

/**
 *
 * @author Leo
 */
public class PanelPostionDiv extends JPanel {

    private PostionDivListener pdListener;

    public PanelPostionDiv(String title) {
        super(null);
        this.setSize(270, 150);
        this.setBorder(BorderFactory.createTitledBorder(title));
        //
        TextSlider postion = new TextSlider("postion", new TextSliderValue<Integer>() {
            @Override
            public void setSlider(JSlider slider) {
                slider.setMinimum(-500);
                slider.setMaximum(500);
                slider.setValue(0);
                slider.setMajorTickSpacing(100);
                slider.setMinorTickSpacing(20);
            }

            @Override
            public Integer valueChange(String text) {
                int value = Integer.parseInt(text);
                pdListener.postionPerformed(value);
                return value;
            }

            @Override
            public Integer valueChange(int slider) {
                if (pdListener != null) {
                    pdListener.postionPerformed(slider);
                }
                return slider;
            }

            @Override
            public String toText(Integer value) {
                return String.valueOf(value);
            }

            @Override
            public int toSlider(Integer value) {
                return value;
            }
        });
        postion.setLocation(10, 20);
        this.add(postion);
        //
        TextSlider div = new TextSlider("div", new TextSliderValue<Double>() {
            @Override
            public void setSlider(JSlider slider) {
                slider.setMinimum(0);
                slider.setMaximum(33);
                slider.setValue(30);
                slider.setMajorTickSpacing(6);
                slider.setMinorTickSpacing(3);
            }

            @Override
            public Double valueChange(String text) {
                double value = Double.parseDouble(text);
                pdListener.divPerformed((int) value);
                return value;
            }
            private final int[] DIV = {1, 2, 5};

            @Override
            public Double valueChange(int slider) {
                double step = 0;
                step = Math.pow(10, (slider / 3)) / 1000000000;
                step = DIV[slider % DIV.length] * (step == 0 ? 1 : step);
                step = 5 / step;
                if (pdListener != null) {
                    pdListener.divPerformed(step/5);
                }
                return step;
            }

            @Override
            public String toText(Double value) {
                return String.valueOf(value);
            }

            @Override
            public int toSlider(Double value) {
                return (int) (double) value;
            }
        });
        div.setLocation(10, 80);
        this.add(div);

//        JSlider postion = new JSlider(-500, 500, 0);
//        postion.setBounds(10, 20, 230, 60);
//        postion.setBorder(BorderFactory.createTitledBorder("Postion"));
//        postion.setMajorTickSpacing(100);
//        postion.setMinorTickSpacing(20);
//        postion.setPaintTicks(true);
//        postion.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                JSlider source = (JSlider) e.getSource();
//                if (source.getValueIsAdjusting() && pdListener != null) {
//                    pdListener.postionPerformed(source.getValue());
//                }
//            }
//        });
//        this.add(postion);
        //
//        JSlider div = new JSlider(0, 33, 30);
//        div.setBounds(10, 80, 230, 60);
//        div.setBorder(BorderFactory.createTitledBorder("Div"));
//        div.setMajorTickSpacing(6);
//        div.setMinorTickSpacing(3);
//        div.setPaintTicks(true);
//        div.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                if (pdListener != null) {
//                    pdListener.divPerformed(((JSlider) e.getSource()).getValue());
//                }
//            }
//        });
//        this.add(div);
    }

    public void setPDListener(PostionDivListener pdListener) {
        this.pdListener = pdListener;
    }
}
