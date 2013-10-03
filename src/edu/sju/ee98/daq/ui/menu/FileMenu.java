/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.menu;

import edu.sju.ee98.daq.ui.Manager;
import edu.sju.ee98.daq.ui.swing.DAQOptionPane;
import edu.sju.ee98.daq.ui.swing.pane.AnalogConfigPane;
import edu.sju.ee98.daq.ui.swing.pane.FrequencyResponsePane;
import edu.sju.ee98.daq.ui.text.Format;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

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
            String file = DAQOptionPane.showNewFileDialog(Manager.MANAGER.getMainFrame());
            System.out.println(file);
            if (file.equals(AnalogConfigPane.NAME)) {
                AnalogConfigPane.create();
            } else if (file.equals(FrequencyResponsePane.NAME)) {
                FrequencyResponsePane.create();
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
