/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.action;

import edu.sju.ee98.daq.ui.Manager;
import edu.sju.ee98.daq.ui.action.Event;
import edu.sju.ee98.daq.ui.action.Event;
import edu.sju.ee98.daq.ui.fft.NewDialog;
import edu.sju.ee98.daq.ui.menu.DAQMenuBar;
import edu.sju.ee98.daq.ui.menu.DAQMenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Leo
 */
public class EventFile extends Event {
    
    public static final String NAME = "File";
    public static final char MNEMONIC = 'F';

    public EventFile() {
        this.put("New", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewDialog(Manager.MANAGER.getMainFrame());
            }
        });
        this.put("Close", new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
//                 ((DAQMenuBar) Manager.MANAGER.getMainFrame().getJMenuBar()).file_close.setEnabled(false);
                 
                System.out.println("exit");
            }
        });
        this.put("Exit", new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("exit");
            }
        });
    }

    @Override
    public String getName() {
        return NAME;
    }
}
