/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sju.ee98.daq.ui;

import edu.sju.ee98.daq.ui.action.EventExit;
import edu.sju.ee98.daq.ui.action.EventFileNew;
import edu.sju.ee98.daq.ui.action.EventPool;

/**
 *
 * @author Leo
 */
public abstract class Manager {

    public static Manager MANAGER;
    public EventPool EVENT_POOL = new EventPool();
    protected MainFrame mainFrame;

    protected Manager() {
        initEvent();
        Manager.MANAGER = this;
    }

    public MainFrame getMain_frame() {
        return mainFrame;
    }

    private void initEvent() {

        //Action==============
        EVENT_POOL.put(new EventFileNew());
        EVENT_POOL.put(new EventExit());
    }
}
