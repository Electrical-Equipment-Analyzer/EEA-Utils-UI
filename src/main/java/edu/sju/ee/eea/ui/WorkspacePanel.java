/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee.eea.ui;

import edu.sju.ee.eea.core.data.DAQData;
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

    protected File file;
    protected boolean saved;
    protected T data;

    public WorkspacePanel(File file) {
        this.file = file;
        saved = false;
    }
    
    public String getFileName() {
        return this.file.getName();
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
        if (!this.file.exists()) {
            this.file = saveDialog(this, this.getFileName());
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
