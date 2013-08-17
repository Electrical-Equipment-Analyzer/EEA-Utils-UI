/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.menu;

import edu.sju.ee98.daq.ui.Manager;
import edu.sju.ee98.daq.ui.action.EventExit;
import edu.sju.ee98.daq.ui.action.EventFileNew;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Leo
 */
public class FFTBar extends JMenuBar {

    public FFTBar() {
        this.menuFile();
        this.menuEdit();
        this.menuHelp();
    }

    private void menuFile() {
        JMenu menu = new JMenu("File");
        menu.setMnemonic('F');

        menu.add(new MenuItem("New", 'N', EventFileNew.class.getName()));
        menu.add(new MenuItem("Open", 'O'));
        menu.addSeparator();
        menu.add(new MenuItem("Exit", 'x', EventExit.class.getName()));

        this.add(menu);
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

    private class MenuItem extends JMenuItem {

        public MenuItem(String text, char mnemonic, ActionListener l) {
            super(text);
            this.setMnemonic(mnemonic);
            this.addActionListener(l);
        }

        public MenuItem(String text, char mnemonic, String l) {
            this(text, mnemonic, (ActionListener) Manager.MANAGER.EVENT_POOL.get(l));
        }

        public MenuItem(String text, char mnemonic) {
            this(text, mnemonic, text.toLowerCase());
        }
    }
}
