/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.menu;

import java.util.ResourceBundle;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

/**
 *
 * @author Leo
 */
public class SMenuBar extends JMenuBar {

    private static final ResourceBundle resource = ResourceBundle.getBundle("edu.sju.ee98.daq.ui.text.menu");
    public final FileMenu fileMenu;
    public final EditMenu editMenu;

    public SMenuBar() {
        fileMenu = new FileMenu(resource);
        this.add(fileMenu);
        editMenu = new EditMenu(resource);
        this.add(editMenu);

//        this.menuEdit();
        this.menuHelp();
    }

//    private void menuEdit() {
//        JMenu menu = new JMenu("Edit");
//        menu.setMnemonic('E');
//        menu.add(new DMenuItem("FFT", 'F'));
//        this.add(menu);
//    }

    private void menuHelp() {
        JMenu menu = new JMenu("Help");
        menu.setMnemonic('H');
        menu.add(new SMenuItem("About", 'A'));
        this.add(menu);
    }
}
