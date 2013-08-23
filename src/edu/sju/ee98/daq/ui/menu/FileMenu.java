/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.menu;

import edu.sju.ee98.daq.ui.Manager;
import edu.sju.ee98.daq.ui.fft.DAQDialog;
import edu.sju.ee98.daq.ui.screen.ScreenPanel;
import edu.sju.ee98.daq.ui.screen.grid.SampleGrid;
import edu.sju.ee98.daq.ui.text.Format;
import edu.sju.ee98.ni.daqmx.LoadLibraryException;
import edu.sju.ee98.ni.daqmx.NIAnalogConfig;
import edu.sju.ee98.ni.daqmx.data.AnalogWave;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author Leo
 */
public class FileMenu extends JMenu implements ActionListener {

    private static final String LABEL = "file";
    public final JMenuItem newItem;
    public final JMenuItem openItem;
    public final JMenuItem saveItem;
    public final JMenuItem saveasItem;
    public final JMenuItem closeItem;
    public final JMenuItem exitItem;

    public FileMenu(ResourceBundle resource) {
        this.setText(Format.text(resource.getString(LABEL)));
        this.setMnemonic(Format.mnemonic(resource.getString(LABEL)));

        this.newItem = new DMenuItem(resource.getString(LABEL + ".new"), this);
        this.openItem = new DMenuItem(resource.getString(LABEL + ".open"), this);
        this.saveItem = new DMenuItem(resource.getString(LABEL + ".save"), this);
        this.saveasItem = new DMenuItem(resource.getString(LABEL + ".saveas"), this);
        this.closeItem = new DMenuItem(resource.getString(LABEL + ".close"), this);
        this.exitItem = new DMenuItem(resource.getString(LABEL + ".exit"), this);

        this.add(newItem);
        this.add(openItem);
        this.addSeparator();
        this.add(saveItem);
        this.add(saveasItem);
        this.addSeparator();
        this.add(closeItem);
        this.addSeparator();
        this.add(exitItem);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(newItem)) {
            Logger.getLogger(FileMenu.class.getName()).log(Level.FINER, "new");
            NIAnalogConfig config = DAQDialog.showAnalogConfigPane(Manager.MANAGER.getMainFrame());
            System.out.println(config);
            AnalogWave analogWave = new AnalogWave(config);
            if (config != null) {
                try {
                    analogWave.read();
                } catch (LoadLibraryException ex) {
                    JOptionPane.showMessageDialog(Manager.MANAGER.getMainFrame(), ex.getMessage(), ex.getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
                    int simulator = JOptionPane.showConfirmDialog(Manager.MANAGER.getMainFrame(), "Do you want to use the simulator?", "Qustion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (simulator == JOptionPane.YES_OPTION) {
                        analogWave.gen();
                    } else {
                        return;
                    }
                }
                System.out.println(Arrays.toString(analogWave.getDoubleArray()));
                ScreenPanel screen = new ScreenPanel();
                screen.setLocation(0, 0);
                screen.setGrid(new SampleGrid());
                screen.setWave(analogWave);
                screen.setDropTarget(null);
                screen.repaint();
                Manager.MANAGER.getMainFrame().work.addTab(screen);
                Manager.MANAGER.getMainFrame().work.setSelectedComponent(screen);
            }
        } else if (e.getSource().equals(openItem)) {
            Logger.getLogger(FileMenu.class.getName()).log(Level.FINER, "open");
        } else if (e.getSource().equals(saveItem)) {
            Logger.getLogger(FileMenu.class.getName()).log(Level.FINER, "save");
        } else if (e.getSource().equals(saveasItem)) {
            Logger.getLogger(FileMenu.class.getName()).log(Level.FINER, "saveas");
        } else if (e.getSource().equals(closeItem)) {
            Logger.getLogger(FileMenu.class.getName()).log(Level.FINER, "close");
            int index = Manager.MANAGER.getMainFrame().work.getSelectedIndex();
            Manager.MANAGER.getMainFrame().work.removeTabAt(index);
        } else if (e.getSource().equals(exitItem)) {
            Logger.getLogger(FileMenu.class.getName()).log(Level.FINER, "exit");
        }
    }
}
