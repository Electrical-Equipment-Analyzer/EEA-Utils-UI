/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee.eea.ui.menu;

import javax.swing.JMenu;

/**
 *
 * @author Leo
 */
public class SMenu extends JMenu {

    public SMenu(String text, char mnemonic) {
        super(text);
        this.setMnemonic(mnemonic);
    }
    
}
