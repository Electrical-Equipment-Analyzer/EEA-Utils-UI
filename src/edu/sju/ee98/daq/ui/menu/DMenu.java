/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.menu;

import javax.swing.JMenu;

/**
 *
 * @author Leo
 */
public class DMenu extends JMenu {

    public DMenu(String text, char mnemonic) {
        super(text);
        this.setMnemonic(mnemonic);
    }
    
}
