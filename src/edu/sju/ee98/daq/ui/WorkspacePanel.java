/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui;

import edu.sju.ee98.daq.core.data.DAQData;
import java.awt.Component;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

/**
 *
 * @author Leo
 * @param <T>
 */
public class WorkspacePanel<T extends DAQData> extends JPanel {

    protected String name;
    protected File file;
    protected boolean saved;
    protected T data;

    public WorkspacePanel(String name) {
        this.name = name;
        saved = false;
    }

    public void init() {
        try {
            Manager.MANAGER.mainFrame.menuBar.fileMenu.closeItem.setEnabled(true);
            Manager.MANAGER.mainFrame.menuBar.fileMenu.saveItem.setEnabled(!saved);
            Manager.MANAGER.mainFrame.menuBar.fileMenu.saveasItem.setEnabled(!saved);
        } catch (java.lang.NullPointerException ex) {
        }
    }

    public void save() {
        if (this.file == null) {
            this.file = saveDialog(this, this.name);
        }
        this.data.save(this.file);
    }

    private static File saveDialog(Component parent, String name) {
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(false);
        chooser.setSelectedFile(new File(System.getProperty("user.dir"), name));
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int show = chooser.showSaveDialog(parent);
        if (show == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        }
        return null;
    }
}
