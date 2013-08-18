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
public class DAQMenuBar extends JMenuBar {

    private static final ResourceBundle resource = ResourceBundle.getBundle("edu.sju.ee98.daq.ui.text.menu");
    public final FileMenu fileMenu;
//    public final DMenuItem file_new = new DMenuItem("New", 'N', EventFile.NAME);
//    public final DMenuItem file_open = new DMenuItem("Open", 'O', EventFile.NAME);
//    public final DMenuItem file_save = new DMenuItem("Save", 'S', EventFile.NAME);
//    public final DMenuItem file_saveAs = new DMenuItem("Save As", 'a', EventFile.NAME);
//    public final DMenuItem file_close = new DMenuItem("Close", 'C', EventFile.NAME);
//    public final DMenuItem file_exit = new DMenuItem("Exit", 'E', EventFile.NAME);

//    public static void main(String[] args) {
//        Locale.setDefault(Locale.US);
//        ResourceBundle resource = ResourceBundle.getBundle("edu.sju.ee98.daq.ui.text.menu");
//        String string = resource.getString("file");
//        System.out.println(string);
//    }
    public DAQMenuBar() {
        //        this.menuFile();
        fileMenu = new FileMenu(resource);
        this.add(fileMenu);
        this.menuEdit();
        this.menuHelp();
    }

//    private void menuFile() {
//        file.add(file_new);
//        file.add(file_open);
//        file.addSeparator();
//        file.add(file_save);
//        file.add(file_saveAs);
//        file.addSeparator();
//        file.add(file_close);
//        file.addSeparator();
//        file.add(file_exit);
//        this.add(file);
//    }
    private void menuEdit() {
        JMenu menu = new JMenu("Edit");
        menu.setMnemonic('E');

        menu.add(new DMenuItem("FFT", 'F'));

        this.add(menu);
    }

    private void menuHelp() {
        JMenu menu = new JMenu("Help");
        menu.setMnemonic('H');

        menu.add(new DMenuItem("About", 'A'));

        this.add(menu);
    }
}
