/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.menu;

import edu.sju.ee98.daq.ui.text.Format;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

/**
 *
 * @author Leo
 */
class DMenuItem extends JMenuItem {

    public DMenuItem(String format, ActionListener l) {
        super(Format.text(format));
        this.setMnemonic(Format.mnemonic(format));
        this.addActionListener(l);
    }
    
//    public DMenuItem(String text, char mnemonic, ActionListener l) {
//        super(text);
//        this.setMnemonic(mnemonic);
//        this.addActionListener(l);
//    }

//    public DMenuItem(String text, char mnemonic, Event e) {
//        super(text);
//        this.setMnemonic(mnemonic);
//        if (e != null) {
//            this.addActionListener(e.get(text));
//        }
//    }

//    public DMenuItem(String text, char mnemonic, String l) {
//        this(text, mnemonic, Manager.MANAGER.EVENT_POOL.get(l));
//    }

    public DMenuItem(String text, char mnemonic) {
        super(text);
        this.setMnemonic(mnemonic);
    }
}