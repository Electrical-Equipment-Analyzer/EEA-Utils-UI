/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.menu;

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
public class EditMenu extends JMenu implements ActionListener {

    private static final String LABEL = "edit";
    public final JMenuItem fftItem;

    public EditMenu(ResourceBundle resource) {
        this.setText(Format.text(resource.getString(LABEL)));
        this.setMnemonic(Format.mnemonic(resource.getString(LABEL)));
        this.fftItem = new DMenuItem(resource.getString(LABEL + ".fft"), this);
        this.add(fftItem);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(fftItem)) {
            Logger.getLogger(EditMenu.class.getName()).log(Level.FINER, fftItem.getText());
        }
    }
}
