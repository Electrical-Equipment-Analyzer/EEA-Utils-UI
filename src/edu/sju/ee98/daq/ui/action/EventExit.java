/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Leo
 */
public class EventExit implements Event, ActionListener {

    @Override
    public String name() {
        return "exit";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Exit");
    }

}
