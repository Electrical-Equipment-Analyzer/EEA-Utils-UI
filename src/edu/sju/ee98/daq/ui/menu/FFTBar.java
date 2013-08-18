/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.menu;

import edu.sju.ee98.daq.ui.Manager;
import edu.sju.ee98.daq.ui.action.Event;
import edu.sju.ee98.daq.ui.action.EventFile;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Leo
 */
public class FFTBar extends JMenuBar {

    private JMenu file;
    
    public FFTBar() {
        this.menuFile();
        this.menuEdit();
        this.menuHelp();
    }

    private void menuFile() {
        String text = EventFile.NAME;
         file = new JMenu(text);
        file.setMnemonic(EventFile.MNEMONIC);

        file.add(new MenuItem("New", 'N', text));
        file.add(new MenuItem("Open", 'O', text));
        file.addSeparator();
        file.add(new MenuItem("Save", 'S', text));
        file.add(new MenuItem("Save As", 'A', text));
        file.addSeparator();
        file.add(new MenuItem("Close", 'c', text));
        file.addSeparator();
        file.add(new MenuItem("Exit", 'x', text));

        this.add(file);
    }

    private void menuEdit() {
        JMenu menu = new JMenu("Edit");
        menu.setMnemonic('E');

        menu.add(new MenuItem("FFT", 'F'));

        this.add(menu);
    }

    private void menuHelp() {
        JMenu menu = new JMenu("Help");
        menu.setMnemonic('H');

        menu.add(new MenuItem("About", 'A'));

        this.add(menu);
    }

    public JMenu getFile() {
        return file;
    }

    private class MenuItem extends JMenuItem {

        public MenuItem(String text, char mnemonic, ActionListener l) {
            super(text);
            this.setMnemonic(mnemonic);
            this.addActionListener(l);
        }

        public MenuItem(String text, char mnemonic, Event e) {
            super(text);
            this.setMnemonic(mnemonic);
            if (e != null) {
                this.addActionListener(e.get(text));
            }
        }

        public MenuItem(String text, char mnemonic, String l) {
            this(text, mnemonic, Manager.MANAGER.EVENT_POOL.get(l));
        }

        public MenuItem(String text, char mnemonic) {
            this(text, mnemonic, text.toLowerCase());
        }
    }
}
