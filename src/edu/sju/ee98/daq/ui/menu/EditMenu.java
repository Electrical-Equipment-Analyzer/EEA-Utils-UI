/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.menu;

import edu.sju.ee.daq.core.math.ComplexArrays;
import edu.sju.ee98.daq.ui.Manager;
import edu.sju.ee98.daq.ui.screen.ScreenPanel;
import edu.sju.ee98.daq.ui.screen.SampleGrid;
import edu.sju.ee98.daq.ui.text.Format;
import edu.sju.ee98.daq.ui.wave.SComplexWave;
import edu.sju.ee98.ni.daqmx.data.NIWaveData;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.complex.ComplexUtils;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;

/**
 *
 * @author Leo
 */
public class EditMenu extends JMenu implements ActionListener {

    private static final String LABEL = "edit";
    public final JMenuItem fftForwardItem;
    public final JMenuItem fftInverseItem;
    public final JMenuItem complexAbsolute;

    public EditMenu(ResourceBundle resource) {
        this.setText(Format.text(resource.getString(LABEL)));
        this.setMnemonic(Format.mnemonic(resource.getString(LABEL)));
        this.fftForwardItem = new DMenuItem(resource.getString(LABEL + ".fftf"), this);
        this.fftInverseItem = new DMenuItem(resource.getString(LABEL + ".ffti"), this);
        this.complexAbsolute = new DMenuItem(resource.getString(LABEL + ".complex.absolute"), this);

        this.add(fftForwardItem);
        this.add(fftInverseItem);
        this.addSeparator();
        this.add(complexAbsolute);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(fftForwardItem)) {
            Logger.getLogger(EditMenu.class.getName()).log(Level.FINER, fftForwardItem.getText());
            Component selectedComponent = Manager.MANAGER.getMainFrame().work.getSelectedComponent();
            if (selectedComponent instanceof ScreenPanel) {
                NIWaveData wave = ((ScreenPanel) selectedComponent).getWave();
                ScreenPanel fft = new ScreenPanel();
                fft.setLocation(0, 0);
                fft.setGrid(new SampleGrid());
                fft.setWave(new SComplexWave(wave.getRate(),
                        transform(wave.getData(), TransformType.FORWARD)));
                fft.setDropTarget(null);
                fft.repaint();
                Manager.MANAGER.getMainFrame().work.addTab(fft);
                Manager.MANAGER.getMainFrame().work.setSelectedComponent(fft);
            }
        } else if (e.getSource().equals(fftInverseItem)) {
            Logger.getLogger(EditMenu.class.getName()).log(Level.FINER, fftInverseItem.getText());
            Component selectedComponent = Manager.MANAGER.getMainFrame().work.getSelectedComponent();
            if (selectedComponent instanceof ScreenPanel) {
                NIWaveData wave = ((ScreenPanel) selectedComponent).getWave();
                System.out.println(wave);
                ScreenPanel fft = new ScreenPanel();
                fft.setLocation(0, 0);
                fft.setGrid(new SampleGrid());
                fft.setWave(new SComplexWave(wave.getRate(),
                        transform(wave.getData(), TransformType.INVERSE)));
                fft.setDropTarget(null);
                fft.repaint();
                Manager.MANAGER.getMainFrame().work.addTab(fft);
                Manager.MANAGER.getMainFrame().work.setSelectedComponent(fft);
            }
        } else if (e.getSource().equals(complexAbsolute)) {
            Logger.getLogger(EditMenu.class.getName()).log(Level.FINER, complexAbsolute.getText());
            Component selectedComponent = Manager.MANAGER.getMainFrame().work.getSelectedComponent();
            if (selectedComponent instanceof ScreenPanel) {
                NIWaveData wave = ((ScreenPanel) selectedComponent).getWave();
                System.out.println(wave);
                ScreenPanel complex = new ScreenPanel();
                complex.setLocation(0, 0);
                complex.setGrid(new SampleGrid());
                complex.setWave(new SComplexWave(wave.getRate(),
                        ComplexUtils.convertToComplex(ComplexArrays.getAbsolute((Complex[]) wave.getData()))));
                complex.setDropTarget(null);
                complex.repaint();
                Manager.MANAGER.getMainFrame().work.addTab(complex);
                Manager.MANAGER.getMainFrame().work.setSelectedComponent(complex);
            }
        }
    }

    private static Complex[] transform(Object input, TransformType type) {
        FastFourierTransformer transformer = new FastFourierTransformer(DftNormalization.STANDARD);
        try {
            Complex[] complx = null;
            if (input instanceof double[]) {
                complx = transformer.transform((double[]) input, type);
            } else if (input instanceof Complex[]) {
                complx = transformer.transform((Complex[]) input, type);
            }
            return complx;
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
        return null;
    }
}
