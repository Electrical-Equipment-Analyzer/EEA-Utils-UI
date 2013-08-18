/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.menu;

import edu.sju.ee98.daq.ui.Manager;
import edu.sju.ee98.daq.ui.fft.DAQDialog;
import edu.sju.ee98.daq.ui.text.Format;
import edu.sju.ee98.ni.daqmx.NIAnalogConfig;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
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
            NIAnalogConfig showAnalogConfigPane = DAQDialog.showAnalogConfigPane(Manager.MANAGER.getMainFrame());
            System.out.println(showAnalogConfigPane);
            System.out.println("new");
        } else if (e.getSource().equals(openItem)) {
            System.out.println("open");
        } else if (e.getSource().equals(saveItem)) {
            System.out.println("save");
        } else if (e.getSource().equals(saveasItem)) {
            System.out.println("saveas");
        } else if (e.getSource().equals(closeItem)) {
            System.out.println("close");
        } else if (e.getSource().equals(exitItem)) {
            System.out.println("exit");
        }
    }
}
