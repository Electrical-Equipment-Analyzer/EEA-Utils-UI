/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.action;

import edu.sju.ee98.daq.ui.MainFrame;
import edu.sju.ee98.daq.ui.Manager;
import edu.sju.ee98.daq.ui.fft.NewDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Leo
 */
public class EventFileNew implements Event, ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("new");
        new NewDialog(Manager.MANAGER.getMain_frame());
    }
}
