/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.menu;

import edu.sju.ee98.daq.core.data.DAQData;
import edu.sju.ee98.daq.core.frequency.response.FrequencyResponseFile;
import edu.sju.ee98.daq.ui.Manager;
import edu.sju.ee98.daq.ui.WorkspacePanel;
import edu.sju.ee98.daq.ui.swing.SOptionDialog;
import edu.sju.ee98.daq.ui.swing.pane.AnalogConfigPane;
import edu.sju.ee98.daq.ui.swing.pane.FrequencyResponseConfigPane;
import edu.sju.ee98.daq.ui.text.Format;
import edu.sju.ee98.daq.ui.workspace.data.FrequencyResponsePanel;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
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

        this.newItem = new SMenuItem(resource.getString(LABEL + ".new"), this);
        this.openItem = new SMenuItem(resource.getString(LABEL + ".open"), this);
        this.saveItem = new SMenuItem(resource.getString(LABEL + ".save"), this);
        this.saveasItem = new SMenuItem(resource.getString(LABEL + ".saveas"), this);
        this.closeItem = new SMenuItem(resource.getString(LABEL + ".close"), this);
        this.exitItem = new SMenuItem(resource.getString(LABEL + ".exit"), this);

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
            String file = SOptionDialog.showNewFileDialog(Manager.MANAGER.getMainFrame());
            System.out.println(file);
            if (file.equals(AnalogConfigPane.NAME)) {
                AnalogConfigPane.create();
            } else if (file.equals(FrequencyResponseConfigPane.NAME)) {
                FrequencyResponseConfigPane.create();
            }
        } else if (e.getSource().equals(openItem)) {
            Logger.getLogger(FileMenu.class.getName()).log(Level.FINER, "open");
            File file = openDialog(Manager.MANAGER.getMainFrame());
            DAQData open = DAQData.open(file);
            if (open instanceof FrequencyResponseFile) {
                FrequencyResponsePanel frequencyResponsePanel = new FrequencyResponsePanel(file, (FrequencyResponseFile) open);
                Manager.MANAGER.getMainFrame().work.addTab(frequencyResponsePanel);
                Manager.MANAGER.getMainFrame().work.setSelectedComponent(frequencyResponsePanel);
            }
        } else if (e.getSource().equals(saveItem)) {
            Logger.getLogger(FileMenu.class.getName()).log(Level.FINER, "save");
            WorkspacePanel work = (WorkspacePanel) Manager.MANAGER.getMainFrame().work.getSelectedComponent();
            work.save();
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

    private static File openDialog(Component parent) {
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(false);
        chooser.setSelectedFile(new File(System.getProperty("user.dir")));
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int show = chooser.showOpenDialog(parent);
        if (show == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        }
        return null;
    }
}
